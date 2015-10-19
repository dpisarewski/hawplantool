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
	 * Bearbeitet die �bergebene Termin-Collection. Je nach Einsatzziel des VeranstaltungsTerminEditors werden die VeranstaltungsTermin-Objekte angepasst, aus der Collection entfernt oder der Collection neu hinzugef�gt. Gegebenenfalls ist der R�ckgabewert sogar eine komplett neue Collection mit v�llig anderen Terminen als jenen, die der Methode �bergeben wurden.
	 *
	 * @param termine
	 * @return Collection
	 * @throws HAWPlanToolException
	 */
	public Collection<VeranstaltungsTermin> bearbeiteTermine(Collection<VeranstaltungsTermin> termine) throws HAWPlanToolException;
}