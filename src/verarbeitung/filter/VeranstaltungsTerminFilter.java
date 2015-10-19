package de.dueddel.hawplantool.verarbeitung.filter;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;

/**
 * <code>VeranstaltungsTerminFilter</code>
 */
public interface VeranstaltungsTerminFilter extends VerarbeitungsObjekt {

	/**
	 * Überprüft, ob der übergebene <code>VeranstaltungsTermin</code> zu filtern ist.
	 *
	 * @param termin
	 * @return TRUE, wenn die Filterregel auf den <code>VeranstaltungsTermin</code> zutrifft, andererseits FALSE
	 */
	public boolean isZuFiltern(VeranstaltungsTermin termin) throws HAWPlanToolException;

	/**
	 * Überprüft, ob der übergebene <code>VeranstaltungsTermin</code> nicht zu filtern ist.
	 *
	 * @param termin
	 * @return TRUE, wenn der <code>VeranstaltungsTermin</code> nicht der Filterregel entspricht, andererseits FALSE
	 */
	public boolean isNichtZuFiltern(VeranstaltungsTermin termin) throws HAWPlanToolException;

	/**
	 * Setzt den aktuellen <code>VeranstaltungsTerminErmittler</code>. Dieser kann dann -sofern notwendig- für die Konfiguration verwendet werden.
	 *
	 * @param terminErmittler
	 */
	public void setAktuellenTerminErmittlerFuerKonfiguration(VeranstaltungsTerminErmittler terminErmittler) throws HAWPlanToolException;
}