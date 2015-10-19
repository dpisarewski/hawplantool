package de.dueddel.hawplantool.verarbeitung.filter;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt_A;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;

/**
 * <code>VeranstaltungsTerminFilter_A</code>
 */
public abstract class VeranstaltungsTerminFilter_A extends VerarbeitungsObjekt_A implements VeranstaltungsTerminFilter {

	/**
	 * Überprüft, ob der übergebene <code>VeranstaltungsTermin</code> nicht zu filtern ist.
	 *
	 * @param termin
	 * @return TRUE, wenn der <code>VeranstaltungsTermin</code> nicht der Filterregel entspricht, andererseits FALSE
	 */
	public boolean isNichtZuFiltern(VeranstaltungsTermin termin) throws HAWPlanToolException {
		return !isZuFiltern(termin);
	}

	/**
	 * Setzt den aktuellen <code>VeranstaltungsTerminErmittler</code>. Dieser kann dann -sofern notwendig- für die Konfiguration verwendet werden.
	 *
	 * @param terminErmittler
	 */
	public void setAktuellenTerminErmittlerFuerKonfiguration(VeranstaltungsTerminErmittler terminErmittler) throws HAWPlanToolException {
	}
}