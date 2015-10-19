package de.dueddel.hawplantool.verarbeitung.editor;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;

import java.util.Collection;

/**
 * <code>VeranstaltungsTerminEditor</code>
 */
public interface VeranstaltungsTerminEditor extends VerarbeitungsObjekt {

	/**
	 * Bearbeitet die übergebene Termin-Collection. Je nach Einsatzziel des VeranstaltungsTerminEditors werden die VeranstaltungsTermin-Objekte angepasst, aus der Collection entfernt oder der Collection neu hinzugefügt. Gegebenenfalls ist der Rückgabewert sogar eine komplett neue Collection mit völlig anderen Terminen als jenen, die der Methode übergeben wurden.
	 *
	 * @param termine
	 * @return Collection
	 * @throws HAWPlanToolException
	 */
	public Collection<VeranstaltungsTermin> bearbeiteTermine(Collection<VeranstaltungsTermin> termine) throws HAWPlanToolException;
}