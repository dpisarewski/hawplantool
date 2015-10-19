package de.dueddel.hawplantool.fabrik;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.gui.Dialog;
import de.dueddel.hawplantool.gui.EinstellungenDialog;
import de.dueddel.hawplantool.gui.HilfeDialog;
import de.dueddel.hawplantool.konstanten.ProgrammKonstanten;
import de.dueddel.hawplantool.util.DatumUtil;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;

/**
 * Die Klasse <code>SwingFabrik</code> bietet Fabrik- und Hilfsmethoden für die Erzeugung von und für den Umgang mit Swing-Komponenten.
 */
public class SwingFabrik {

	/**
	 * Erzeugt einen JSpinner zur Eingabe einer Uhrzeit, angezeigt wird sie im Format des folgenden Beispiels: "12:45 Uhr"
	 * <br/><br/>
	 * Der JSpinner gibt über die Methode <code>JSpinner#getValue()</code> ein Date-Objekt zurück, dieses kann bspw. mit dem DatumUtil verarbeitet werden.
	 *
	 * @param uhrzeit Defaultwert im Integer-Format, Bsp.: 1430 für 14:30 Uhr
	 * @return JSpinner
	 * @see de.dueddel.hawplantool.util.DatumUtil
	 * @see JSpinner#getValue()
	 */
	public static JSpinner erzeugeSpinnerUhrzeit(int uhrzeit) {
		return erzeugeZeitSpinner(uhrzeit, "HH:mm 'Uhr'");
	}

	/**
	 * Erzeugt einen JSpinner zur Eingabe einer Zeitdauer, angezeigt wird sie im Format des folgenden Beispiels: "10 h 13 min"
	 * <br/><br/>
	 * Der JSpinner gibt über die Methode <code>JSpinner#getValue()</code> ein Date-Objekt zurück, dieses kann bspw. mit dem DatumUtil verarbeitet werden.
	 *
	 * @param zeit Defaultwert im Integerformat, Bsp.: 340 für 3 h und 40 min
	 * @return JSpinner
	 * @see de.dueddel.hawplantool.util.DatumUtil
	 * @see JSpinner#getValue()
	 */
	public static JSpinner erzeugeSpinnerZeitdauer(int zeit) {
		return erzeugeZeitSpinner(zeit, "HH 'h' mm 'min'");
	}

	/**
	 * Erzeugt einen JSpinner zur Eingabe einer Zeit.
	 *
	 * @param zeit
	 * @param pattern
	 * @return JSpinner
	 */
	private static JSpinner erzeugeZeitSpinner(int zeit, String pattern) {
		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, pattern));

		Calendar kalender = Calendar.getInstance();
		DatumUtil.setUhrzeit(kalender, zeit);
		spinner.setValue(kalender.getTime());

		return spinner;
	}

	/**
	 * Erzeugt einen Button und registriert den übergebenen ActionListener.
	 *
	 * @param text
	 * @param actionListener
	 * @return JButton
	 */
	public static JButton erzeugeButton(String text, ActionListener actionListener) {
		JButton button = new JButton(text);
		button.addActionListener(actionListener);
		return button;
	}

	/**
	 * Erzeugt einen Button, setzt den Mnemonic und registriert den übergebenen ActionListener.
	 *
	 * @param text
	 * @param mnemonic
	 * @param actionListener
	 * @return JButton
	 */
	public static JButton erzeugeButton(String text, char mnemonic, ActionListener actionListener) {
		JButton button = erzeugeButton(text, actionListener);
		button.setMnemonic(mnemonic);
		return button;
	}

	/**
	 * Erzeugt einen Seperator.
	 *
	 * @param text
	 * @return JComponent
	 */
	public static JComponent erzeugeSeparator(String text) {
		return DefaultComponentFactory.getInstance().createSeparator(text);
	}

	/**
	 * Erzeugt einen Dialog für die Konfiguration eines VerarbeitungsObjektes.
	 *
	 * @param verarbeitungsObjekt
	 * @param owner
	 * @return Dialog
	 */
	public static Dialog erzeugeEinstellungenDialog(VerarbeitungsObjekt verarbeitungsObjekt, JFrame owner) throws HAWPlanToolException {
		return new EinstellungenDialog(owner, verarbeitungsObjekt);
	}

	/**
	 * Erzeugt einen Dialog für die Anzeige des Hilfetextes eines VerarbeitungsObjektes.
	 *
	 * @param verarbeitungsObjekt
	 * @param owner
	 * @return Dialog
	 */
	public static Dialog erzeugeHilfeDialog(VerarbeitungsObjekt verarbeitungsObjekt, JFrame owner) {
		return new HilfeDialog(owner, verarbeitungsObjekt);
	}

	/**
	 * Gibt eine vom Benutzer über einen JFileChooser ausgewählte und dem angegebenen Dateiformat entsprechende Datei zum Laden zurück.
	 *
	 * @return File
	 */
	public static File getZuLadendeDatei(String dateiFormat, JFrame owner) {
		return getZuLadendeDatei(null, dateiFormat, owner);
	}

	/**
	 * Gibt eine vom Benutzer über einen JFileChooser ausgewählte und dem angegebenen Dateiformat entsprechende Datei zum Laden zurück.
	 *
	 * @return File
	 */
	public static File getZuLadendeDatei(File voreingestellterPfad, String dateiFormat, JFrame owner) {
		File datei = null;
		JFileChooser fileChooser = erzeugeFileChooser(voreingestellterPfad, dateiFormat);

//		Datei-Laden-Dialog anzeigen und die gewählte Datei holen
		if (fileChooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
			datei = fileChooser.getSelectedFile();
		}
		return datei;
	}

	/**
	 * Gibt eine vom Benutzer über einen JFileChooser ausgewählte und dem angegebenen Dateiformat entsprechende Datei zum Speichern zurück.
	 *
	 * @return File
	 */
	public static File getZuSpeicherndeDatei(String dateiFormat, JFrame owner) {
		return getZuSpeicherndeDatei(null, dateiFormat, owner);
	}

	/**
	 * Gibt eine vom Benutzer über einen JFileChooser ausgewählte und dem angegebenen Dateiformat entsprechende Datei zum Speichern zurück.
	 *
	 * @return File
	 */
	public static File getZuSpeicherndeDatei(File voreingestellterPfad, String dateiFormat, JFrame owner) {
		File datei = null;
		JFileChooser fileChooser = erzeugeFileChooser(voreingestellterPfad, dateiFormat);

//		Datei-Speichern-Dialog anzeigen und die gewählte Datei holen
		if (fileChooser.showSaveDialog(owner) == JFileChooser.APPROVE_OPTION) {
			datei = fileChooser.getSelectedFile();
		}
		return datei;
	}

	/**
	 * Erzeugt einen JFileChooser zur Auswahl von Dateien eines bestimmten Dateiformats, die geladen/gespeichert werden sollen.
	 *
	 * @param voreingestellterPfad
	 * @param dateiFormat
	 * @return JFileChooser
	 */
	private static JFileChooser erzeugeFileChooser(File voreingestellterPfad, final String dateiFormat) {
		if (voreingestellterPfad == null || !voreingestellterPfad.exists()) {
			voreingestellterPfad = ProgrammKonstanten.PROGRAMM_VERZEICHNIS;
		}

		JFileChooser fileChooser = new JFileChooser(voreingestellterPfad) {
			@Override
			public File getSelectedFile() {
				File datei = super.getSelectedFile();
//				Dateiendung ergänzen, wenn nicht angegeben
				if (datei != null && !datei.getName().endsWith("." + dateiFormat)) {
					datei = new File(datei.getAbsolutePath() + "." + dateiFormat);
				}
				return datei;
			}
		};
		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File eineDatei) {
				return eineDatei.isDirectory() || eineDatei.getName().endsWith("." + dateiFormat);
			}

			public String getDescription() {
				return dateiFormat + "-Dateien";
			}
		});

		return fileChooser;
	}
}