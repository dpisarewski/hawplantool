package de.dueddel.hawplantool.verarbeitung;

import de.dueddel.hawplantool.HAWPlanToolException;

import javax.swing.*;

/**
 * <code>VerarbeitungsObjekt</code>
 */
public interface VerarbeitungsObjekt extends Comparable {

	/**
	 * Gibt eine Kurzbeschreibung f�r das VerarbeitungsObjekt zur�ck.
	 *
	 * @return <code>String</code> mit Kurzbeschreibung
	 */
	public String getKurzbeschreibung();

	/**
	 * Gibt eine Beschreibung f�r das VerarbeitungsObjekt zur�ck.
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
	 * Gibt das Panel f�r die Konfiguration des VerarbeitungsObjektes zur�ck.
	 *
	 * @param owner
	 * @return Jpanel
	 */
	public JPanel getPanelFuerKonfiguration(JFrame owner) throws HAWPlanToolException;

	/**
	 * F�hrt die Aktion zum �bernehmen der Konfiguration aus.
	 */
	public void aktionBeiKonfigurationOk() throws HAWPlanToolException;

	/**
	 * F�hrt die Aktion zum Verwerfen der Konfiguration aus.
	 */
	public void aktionBeiKonfigurationAbbruch() throws HAWPlanToolException;

}