package de.dueddel.hawplantool.util;

import de.dueddel.hawplantool.HAWPlanToolException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * <code>DateiLeser</code>
 */
public class DateiLeser_Impl implements DateiLeser {

	private File datei;
	private ArrayList<String> zeilenListe;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>DateiLeser</code>.
	 */
	public DateiLeser_Impl(File datei) throws HAWPlanToolException {
		setDatei(datei);
	}

	/**
	 * Gibt das <code>File</code>-Objekt zurück.
	 *
	 * @return File
	 */
	public File getDatei() {
		return datei;
	}

	/**
	 * Setzt das <code>File</code>-Objekt.
	 *
	 * @param datei
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	public void setDatei(File datei) throws HAWPlanToolException {
		if (datei == null) {
			throw new HAWPlanToolException("Die Datei darf nich NULL sein.");
		}
		this.datei = datei;
		fuelleZeilenListeMitInhalt();
	}

	/**
	 * Liest schon mal den Dateiinhalt ein und cached ihn zeilenweise in der als private gekapselten <code>ArrayList</code> zeilenListe.
	 *
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	private void fuelleZeilenListeMitInhalt() throws HAWPlanToolException {
		zeilenListe = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(datei));
			String zeile = null;
			while ((zeile = reader.readLine()) != null) {
				zeilenListe.add(zeile);
			}
		} catch (FileNotFoundException e) {
			throw new HAWPlanToolException("Die Datei '" + datei.getAbsolutePath() + "' konnte nicht gefunden werden.", e);
		} catch (IOException e) {
			throw new HAWPlanToolException("I/O-Fehler beim Lesen der Datei '" + datei.getAbsolutePath() + "'.", e);
		}
	}

	/**
	 * Gibt den gesamten Dateiinhalt zurück.
	 *
	 * @return String
	 */
	public String getDateiInhalt() {
		StringBuffer dateiInhalt = new StringBuffer();
		for (Iterator<String> zeilenIt = zeilenListe.iterator(); zeilenIt.hasNext();) {
			String zeile = zeilenIt.next();
			dateiInhalt.append(zeile);
		}
		return dateiInhalt.toString();
	}

	/**
	 * Gibt den gesamten Dateiinhalt zurück.
	 *
	 * @return Collection
	 */
	public Collection<String> getDateiInhaltCollection() {
		return zeilenListe;
	}

	/**
	 * Gibt eine einzelne Zeile der Datei zurück. Als Parameter wird die Nummer der Zeile (beginnend bei 1 für die erste Zeile) erwartet.
	 *
	 * @param zeilenNummer
	 * @return String
	 */
	public String getZeile(int zeilenNummer) {
		int zeilenIndex = zeilenNummer - 1;
		String zeile = null;
		try {
			zeile = zeilenListe.get(zeilenIndex);
		}
		// wenn zeilenNummer kleiner oder gleich "0"
		catch (ArrayIndexOutOfBoundsException e) {
			// diese Zeile gibt es nicht, NULL wird zurück gegeben
		}
		// wenn zeilenNummer größer als die Anzahl der Zeilen
		catch (IndexOutOfBoundsException e) {
			// diese Zeile gibt es nicht, NULL wird zurück gegeben
		}
		return zeile;
	}

	/**
	 * Gibt die Anzahl der Zeilen der Datei zurück.
	 *
	 * @return int
	 */
	public int getAnzahlZeilen() {
		return zeilenListe.size();
	}

	/**
	 * Gibt einen <code>Iterator</code> zurück, mit welchem über alle Zeilen der Datei iteriert werden kann.
	 *
	 * @return Iterator
	 */
	public Iterator<String> getZeilenIterator() {
		return zeilenListe.iterator();
	}
}