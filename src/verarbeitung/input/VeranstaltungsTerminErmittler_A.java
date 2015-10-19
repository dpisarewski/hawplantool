package de.dueddel.hawplantool.verarbeitung.input;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt_A;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <code>VeranstaltungsTerminErmittler_A</code>
 */
public abstract class VeranstaltungsTerminErmittler_A extends VerarbeitungsObjekt_A implements VeranstaltungsTerminErmittler {

	/**
	 * Ermittelt alle dem Filter enstsprechenden Veranstaltungen.
	 *
	 * @param filter VeranstaltungsTerminFilter, darf auch NULL sein (dann wird nicht gefiltert)
	 * @return <code>Collection</code> aus <code>VeranstaltungsTermin</code>-Objekten
	 * @throws de.dueddel.hawplantool.HAWPlanToolException
	 *
	 */
	public Collection<VeranstaltungsTermin> getVeranstaltungsTermine(VeranstaltungsTerminFilter filter) throws HAWPlanToolException {
		Collection<VeranstaltungsTermin> termineGefiltert = new ArrayList<VeranstaltungsTermin>();
		Collection<VeranstaltungsTermin> termineUngefiltert = null;
		try {
			termineUngefiltert = getVeranstaltungsTermineUngefiltert();
		} catch (HAWPlanToolException e) {
			throw new HAWPlanToolException("Fehler beim Ermitteln der Termine.", e);
		}

		if (termineUngefiltert != null && filter != null) {
			for (VeranstaltungsTermin termin : termineUngefiltert) {

				try {
					if (filter.isNichtZuFiltern(termin)) {
						termineGefiltert.add(termin);
					}
				} catch (HAWPlanToolException e) {
					throw new HAWPlanToolException("Fehler beim Filtern des Termins '" + termin + "'.", e);
				}
			}
		}

		return termineGefiltert;
	}
}