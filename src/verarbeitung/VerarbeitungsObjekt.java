package de.dueddel.hawplantool.verarbeitung;

import de.dueddel.hawplantool.HAWPlanToolException;

import javax.swing.*;

/**
 * <code>VerarbeitungsObjekt</code>
 */
public interface VerarbeitungsObjekt extends Comparable {

	/**
	 * Gibt eine Kurzbeschreibung für das VerarbeitungsObjekt zurück.
	 *
	 * @return <code>String</code> mit Kurzbeschreibung
	 */
	public String getKurzbeschreibung();

	/**
	 * Gibt eine Beschreibung für das VerarbeitungsObjekt zurück.
	 *
	 * @return <code>String</code> mit Beschreibung
	 */
	public String getBeschreibung();

	/**
	 * Ermittelt, ob das VerarbeitungsObjekt konfiguriert werden kann.
	 *
	 * @return <code>TRUE</code>, wenn es konfiguriert werden kann, anderenfalls <code>FALSE</code>
	 */
	public boolean isKonfigurierbar() throws HAWPlanToolException;

	/**
	 * Gibt das Panel für die Konfiguration des VerarbeitungsObjektes zurück.
	 *
	 * @param owner
	 * @return Jpanel
	 */
	public JPanel getPanelFuerKonfiguration(JFrame owner) throws HAWPlanToolException;

	/**
	 * Führt die Aktion zum Übernehmen der Konfiguration aus.
	 */
	public void aktionBeiKonfigurationOk() throws HAWPlanToolException;

	/**
	 * Führt die Aktion zum Verwerfen der Konfiguration aus.
	 */
	public void aktionBeiKonfigurationAbbruch() throws HAWPlanToolException;

}