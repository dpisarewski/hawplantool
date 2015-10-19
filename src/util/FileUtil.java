package de.dueddel.hawplantool.util;

import de.dueddel.hawplantool.HAWPlanToolException;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Die Klasse <code>FileUtil</code> bietet Hilfsmethoden für den Umgang mit Dateien.
 */
public class FileUtil {

	/**
	 * Ermittelt die URLs der Dateien.
	 *
	 * @param dateien Dateien
	 * @return URL-Array der Dateien
	 * @throws HAWPlanToolException
	 */
	public static URL[] getURLs(File[] dateien) throws HAWPlanToolException {
		URL[] dateiURLs = null;

//		URLs der Dateien ermitteln
		if (dateien != null) {
			dateiURLs = new URL[dateien.length];
			for (int i = 0; i < dateien.length; i++) {
				try {
					dateiURLs[i] = dateien[i].toURL();
				} catch (MalformedURLException e) {
					throw new HAWPlanToolException("Fehler beim Ermitteln der URL zur Datei '" + dateien[i] + "'.", e);
				}
			}
		}
		return dateiURLs;
	}

	/**
	 * Ermittelt alle Dateien mit bestimmter Daeiendung innerhalb eines Verzeichnisses.
	 *
	 * @param verzeichnis
	 * @param dateiEndung
	 * @return File-Array
	 * @throws HAWPlanToolException
	 */
	public static File[] getDateien(File verzeichnis, String dateiEndung) throws HAWPlanToolException {
//		Verzeichnis überprüfen
		if (!verzeichnis.exists() || !verzeichnis.isDirectory()) {
			throw new HAWPlanToolException("Die Datei '" + verzeichnis.getAbsolutePath() + "' exisitiert nicht oder ist kein Verzeichnis.");
		}

//		Dateiendung überprüfen und ggf. korrigieren
		final String dateiEndungFinal = (dateiEndung.startsWith(".") ? dateiEndung : "." + dateiEndung).toLowerCase();

//		Dateien aus dem Verzeichnis ermitteln
		return verzeichnis.listFiles(new FileFilter() {
//			nach Dateiendung filtern

			public boolean accept(File pathname) {
				return pathname.getPath().toLowerCase().endsWith(dateiEndungFinal);
			}
		});
	}

	/**
	 * Filtert die Dateien mit dem angegebenen Dateinamen heraus.
	 *
	 * @param dateien
	 * @param zuFilterndeDatei
	 * @return File-Array
	 */
	public static File[] filtereDatei(File[] dateien, String zuFilterndeDatei) {
		Collection<File> dateienGefiltert = new ArrayList<File>();

		for (int i = 0; i < dateien.length; i++) {
			File datei = dateien[i];
			if (!datei.getName().equalsIgnoreCase(zuFilterndeDatei)) {
				dateienGefiltert.add(datei);
			}
		}

		Iterator dateienGefiltertIt = dateienGefiltert.iterator();
		File[] dateienGefiltertArray = new File[dateienGefiltert.size()];
		for (int i = 0; dateienGefiltertIt.hasNext(); i++) {
			dateienGefiltertArray[i] = (File) dateienGefiltertIt.next();
		}

		return dateienGefiltertArray;
	}
}