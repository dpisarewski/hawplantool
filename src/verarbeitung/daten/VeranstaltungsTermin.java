package de.dueddel.hawplantool.verarbeitung.daten;

import java.util.Date;

/**
 * Ein <code>VeranstaltungsTermin</code>-Objekt kann Termine f�r bestimmte Veranstaltungen repr�sentieren.
 * Beipiele f�r solche Veranstaltungen sind Vorlesungen, �bungen, Praktika oder auch Tutorien.
 */
public interface VeranstaltungsTermin extends Comparable {

	/**
	 * Gibt den Namen der Veranstaltung zur�ck.
	 *
	 * @return String
	 */
	public String getName();

	/**
	 * Setzt den Namen der Veranstaltung.
	 *
	 * @param name
	 */
	public void setName(String name);

	/**
	 * Gibt den Dozenten/Professor der Veranstaltung zur�ck.
	 *
	 * @return String
	 */
	public String getProf();

	/**
	 * Setzt den Dozenten/Professor der Veranstaltung.
	 *
	 * @param prof
	 */
	public void setProf(String prof);

	/**
	 * Gibt den Ort der Veranstaltung zur�ck.
	 *
	 * @return String
	 */
	public String getOrt();

	/**
	 * Setzt den Ort der Veranstaltung.
	 *
	 * @param ort
	 */
	public void setOrt(String ort);

	/**
	 * Gibt den Beginn des Termins zur�ck.
	 *
	 * @return Date
	 */
	public Date getBeginn();

	/**
	 * Gibt den Beginn des Termins als Long-Wert zur�ck.
	 *
	 * @return long
	 */
	public long getBeginnLong();

	/**
	 * Setzt den Beginn des Termins.
	 *
	 * @param beginn
	 */
	public void setBeginn(Date beginn);

	/**
	 * Gibt das Ende des Termins zur�ck.
	 *
	 * @return Date
	 */
	public Date getEnde();

	/**
	 * Gibt das Ende des Termins als Long-Wert zur�ck.
	 *
	 * @return long
	 */
	public long getEndeLong();

	/**
	 * Setzt das Ende des Termins.
	 *
	 * @param ende
	 */
	public void setEnde(Date ende);

	/**
	 * Gibt die Semestergruppe zur�ck.
	 *
	 * @return String
	 */
	public String getSemestergruppe();

	/**
	 * Setzt die Semestergruppe.
	 *
	 * @param semestergruppe
	 */
	public void setSemestergruppe(String semestergruppe);

	/**
	 * Gibt die Kategorie zur�ck.
	 *
	 * @return String
	 */
	public String getKategorie();

	/**
	 * Setzt die Kategorie.
	 *
	 * @param kategorie
	 */
	public void setKategorie(String kategorie);

	/**
	 * Gibt eine eindeutige ID des Termins zur�ck.
	 *
	 * @return String
	 */
	public String getUid();
}