package de.dueddel.hawplantool.fabrik;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.util.FileUtil;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.editor.VeranstaltungsTerminEditor;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;
import de.dueddel.hawplantool.verarbeitung.output.ErgebnisErzeuger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Der <code>PluginErmittler</code> ermittelt alle Klassen, welche die Plugin-Schnittstellen "bedienen" und instanziert die entsprechenden Objekte. Nebenbei wird dafür gesorgt, dass auch Fremdbibliotheken geladen werden, ohne dass diese manuell im Classpath ergänzt werden müssen.
 */
public class PluginErmittler {

	private static final String DATEIENDUNG_CLASS = ".class";
	private static final String DATEIENDUNG_JAR = ".jar";
	private static final String NAME_JAR = "hawplantool";

	private static Collection<VeranstaltungsTerminErmittler> terminErmittler;
	private static Collection<VeranstaltungsTerminFilter> terminFilter;
	private static Collection<VeranstaltungsTerminEditor> terminEditoren;
	private static Collection<ErgebnisErzeuger> ergebnisErzeuger;
	private static boolean isInitialisiert;

	public static Collection<VeranstaltungsTerminErmittler> getTerminErmittler() throws HAWPlanToolException {
		if (!isInitialisiert) {
			init();
		}
		return terminErmittler;
	}

	public static Collection<VeranstaltungsTerminFilter> getTerminFilter() throws HAWPlanToolException {
		if (!isInitialisiert) {
			init();
		}
		return terminFilter;
	}

	public static Collection<VeranstaltungsTerminEditor> getTerminEditoren() throws HAWPlanToolException {
		if (!isInitialisiert) {
			init();
		}
		return terminEditoren;
	}

	public static Collection<ErgebnisErzeuger> getErgebnisErzeuger() throws HAWPlanToolException {
		if (!isInitialisiert) {
			init();
		}
		return ergebnisErzeuger;
	}

	/**
	 * Initialisiert den <code>PluginErmittler</code>.
	 */
	public static void init() throws HAWPlanToolException {
		terminErmittler = new ArrayList<VeranstaltungsTerminErmittler>();
		terminFilter = new ArrayList<VeranstaltungsTerminFilter>();
		terminEditoren = new ArrayList<VeranstaltungsTerminEditor>();
		ergebnisErzeuger = new ArrayList<ErgebnisErzeuger>();

//		JAR-Dateien ermitteln
		File[] jarDateien = FileUtil.getDateien(getToolPfad(), DATEIENDUNG_JAR);
		jarDateien = FileUtil.filtereDatei(jarDateien, NAME_JAR + DATEIENDUNG_JAR);

//		Namen der Klassen ermitteln
		Collection<String> klassenNamen = getKlassenNamen(jarDateien);

//		URLs der JAR-Dateien für den URLClassLoader ermitteln
		URL[] klassenUrls = FileUtil.getURLs(jarDateien);

//		URLClassLoader erzeugen
		URLClassLoader classLoader = getURLClassLoader(klassenUrls);

//		Klassen laden und Objekte instanzieren
		ermittlePluginObjekte(klassenNamen, classLoader);

		isInitialisiert = true;
	}

	/**
	 * Ermittelt den Pfad, in dem sich die JAR-Datei des HAW-Plan Tools befindet. Dies ist nötig, da sich das Ausführungsverzeichnis von dem Verzeichnis mit der JAR-Datei unterscheiden kann.
	 *
	 * @return File-Objekt dem Pfad des Tools
	 */
	private static File getToolPfad() {
		return new File("lib");
	}

	/**
	 * Ermittelt diejenigen Klassen, die den Plugin-Schnittstellen des Tools entsprechen und fügt deren Instanzen der jeweiligen Liste hinzu.
	 *
	 * @param klassenNamen
	 * @param classLoader
	 * @throws HAWPlanToolException
	 */
	private static void ermittlePluginObjekte(Collection<String> klassenNamen, URLClassLoader classLoader) throws HAWPlanToolException {
		for (String klasseName : klassenNamen) {
			Object objekt = erzeugeObjekt(classLoader, klasseName);

			if (objekt != null) {
				if (objekt instanceof VeranstaltungsTerminErmittler) {
					terminErmittler.add((VeranstaltungsTerminErmittler) objekt);
				} else if (objekt instanceof VeranstaltungsTerminFilter) {
					terminFilter.add((VeranstaltungsTerminFilter) objekt);
				} else if (objekt instanceof VeranstaltungsTerminEditor) {
					terminEditoren.add((VeranstaltungsTerminEditor) objekt);
				} else if (objekt instanceof ErgebnisErzeuger) {
					ergebnisErzeuger.add((ErgebnisErzeuger) objekt);
				}
			}
		}
	}

	/**
	 * Erzeugt einen URLClassLoader.
	 *
	 * @param klassenUrls
	 * @return URLClassLoader
	 */
	private static URLClassLoader getURLClassLoader(URL[] klassenUrls) {
		URLClassLoader classLoader = null;
		if (klassenUrls != null) {
			classLoader = URLClassLoader.newInstance(klassenUrls, ClassLoader.getSystemClassLoader());
			Thread.currentThread().setContextClassLoader(classLoader);
		}
		return classLoader;
	}

	/**
	 * Instanziert ein Objekt mit Hilfe des übergebenen ClassLoaders.
	 *
	 * @param classLoader
	 * @param nameDerKlasse
	 * @return
	 * @throws HAWPlanToolException
	 */
	private static Object erzeugeObjekt(URLClassLoader classLoader, String nameDerKlasse) throws HAWPlanToolException {
		Object objekt = null;

		try {
//			Klasse laden
			Class klasse = classLoader.loadClass(nameDerKlasse);

//			Objekt instanzieren
			if (klasse != null && isImplementierungVonInterface(klasse, VerarbeitungsObjekt.class)) {
				objekt = klasse.newInstance();
			}

		} catch (NoClassDefFoundError e) {
//			Error nicht verarbeiten, weil dieser auch bei Fremdbibliotheken kommen kann, aber das ist an dieser Stelle egal.
//			throw new HAWPlanToolException("Fehler beim Laden der Klasse '" + nameDerKlasse + "'.", e);
		} catch (ClassNotFoundException e) {
			throw new HAWPlanToolException("Fehler beim Laden der Klasse '" + nameDerKlasse + "'.", e);
		} catch (IllegalAccessException e) {
			throw new HAWPlanToolException("Fehler beim Instanzieren eines Objekts der Klasse '" + nameDerKlasse + "'.", e);
		} catch (InstantiationException e) {
			throw new HAWPlanToolException("Fehler beim Instanzieren eines Objekts der Klasse '" + nameDerKlasse + "'.", e);
		}

		return objekt;
	}

	/**
	 * Ermittelt aus den JAR-Dateien die Namen aller darin enthaltenen Klassen.
	 *
	 * @param jarDateien
	 * @return
	 * @throws HAWPlanToolException
	 */
	private static Collection<String> getKlassenNamen(File[] jarDateien) throws HAWPlanToolException {
		Collection<String> klassenNamen = new ArrayList<String>();

//		über alle JAR-Dateien iterieren
		for (int i = 0; i < jarDateien.length; i++) {
			File jarDatei = jarDateien[i];

			try {
//				IntputStream erzeugen, um JAR-Datei auslesen zu können
				ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(jarDatei));

//				JAR-Datei auslesen und über alle Einträge in der JAR-Datei iterieren
				ZipEntry zipEntry;
				while ((zipEntry = zipInputStream.getNextEntry()) != null) {

//					wenn Datei
					if (!zipEntry.isDirectory()) {
						String nameZipEntry = zipEntry.getName();

//						wenn kompilierte Klasse
						if (nameZipEntry.endsWith(DATEIENDUNG_CLASS)) {

//							Dateiendung abschneiden, Verzeichnis-Trennzeichen durch Punkte ersetzen und der Liste mit den Klassennamen hinzufügen
							klassenNamen.add(nameZipEntry.substring(0, nameZipEntry.lastIndexOf(DATEIENDUNG_CLASS)).replaceAll("/", "."));
						}
					}
				}

			} catch (FileNotFoundException e) {
				throw new HAWPlanToolException("Fehler Erzeugen eines InputStreams für die Datei '" + jarDatei.getAbsolutePath() + "'.", e);
			} catch (IOException e) {
				throw new HAWPlanToolException("Fehler Ermitteln des NextEntries aus der Datei'" + jarDatei.getAbsolutePath() + "'.", e);
			}
		}

		return klassenNamen;
	}

	/**
	 * Prüft, ob das übergebene <code>Class</code>-Objekt das übergebene Interface implementiert.
	 *
	 * @param klasse
	 * @param klasseInterface
	 * @return TRUE, wenn die übergebene Interface-Klasse überhaupt ein Interface ist und wnen die Klasse das Interface auch implementiert; anderenfalls FALSE
	 */
	private static boolean isImplementierungVonInterface(Class klasse, Class klasseInterface) {
		if (klasseInterface.isInterface()) {
			Class[] interfaces = getAlleInterfaces(klasse);
			for (int i = 0; i < interfaces.length; i++) {
				Class einInterface = interfaces[i];
				if (einInterface.equals(klasseInterface)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ermittelt alle Interfaces der Klasse (auch jene der Oberklassen).
	 *
	 * @param klasse
	 * @return Interfaces der Klasse
	 */
	private static Class[] getAlleInterfaces(Class klasse) {
		Class oberklasse = klasse.getSuperclass();
		Class[] interfaces = klasse.getInterfaces();
		Class[] alleInterfaces = interfaces;

		if (oberklasse != null) {
//			rekursiv alle Interfaces der Oberklasse ermitteln
			Class[] interfacesDerOberklasse = getAlleInterfaces(oberklasse);
			alleInterfaces = new Class[interfaces.length + interfacesDerOberklasse.length];

			for (int i = 0; i < interfaces.length; i++) {
				alleInterfaces[i] = interfaces[i];
			}
			for (int i = interfaces.length; i < interfaces.length + interfacesDerOberklasse.length; i++) {
				alleInterfaces[i] = interfacesDerOberklasse[i - interfaces.length];
			}
		}
		return alleInterfaces;
	}
}