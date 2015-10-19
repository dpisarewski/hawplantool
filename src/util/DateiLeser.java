package de.dueddel.hawplantool.util;

import de.dueddel.hawplantool.HAWPlanToolException;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

/**
 * Ein <code>DateiLeser</code> dient dem Lesen von Textdateien.
 */
public interface DateiLeser {

	/**
	 * Gibt das <code>File</code>-Objekt zurück.
	 *
	 * @return File
	 */
	public File getDatei();

	/**
	 * Setzt das <code>File</code>-Objekt.
	 *
	 * @param datei
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	public void setDatei(File datei) throws HAWPlanToolException;

	/**
	 * Gibt den gesamten Dateiinhalt zurück.
	 *
	 * @return String
	 */
	public String getDateiInhalt();

	/**
	 * Gibt den gesamten Dateiinhalt zurück.
	 *
	 * @return Collection mit Strings
	 */
	public Collection<String> getDateiInhaltCollection();

	/**
	 * Gibt eine einzelne Zeile der Datei zurück. Als Parameter wird die Nummer der Zeile (beginnend bei 1 für die erste Zeile) erwartet.
	 *
	 * @param zeilenNummer
	 * @return String
	 */
	public String getZeile(int zeilenNummer);

	/**
	 * Gibt die Anzahl der Zeilen der Datei zurück.
	 *
	 * @return int
	 */
	public int getAnzahlZeilen();

	/**
	 * Gibt einen <code>Iterator</code> zurück, mit welchem über alle Zeilen der Datei iteriert werden kann.
	 *
	 * @return Iterator
	 */
	public Iterator<String> getZeilenIterator();
}