package de.dueddel.hawplantool.verarbeitung.input;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;

import java.util.Collection;

/**
 * <code>VeranstaltungsTerminErmittler</code>
 */
public interface VeranstaltungsTerminErmittler extends VerarbeitungsObjekt {

	/**
	 * Ermittelt alle Veranstaltungen.
	 *
	 * @return <code>Collection</code> aus <code>VeranstaltungsTermin</code>-Objekten
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	public Collection<VeranstaltungsTermin> getVeranstaltungsTermineUngefiltert() throws HAWPlanToolException;

	/**
	 * Ermittelt alle dem Filter enstsprechenden Veranstaltungen.
	 *
	 * @param filter
	 * @return <code>Collection</code> aus <code>VeranstaltungsTermin</code>-Objekten
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	public Collection<VeranstaltungsTermin> getVeranstaltungsTermine(VeranstaltungsTerminFilter filter) throws HAWPlanToolException;
}