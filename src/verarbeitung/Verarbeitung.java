package de.dueddel.hawplantool.verarbeitung;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.editor.VeranstaltungsTerminEditor;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;
import de.dueddel.hawplantool.verarbeitung.output.ErgebnisErzeuger;

/**
 * <code>Verarbeitung</code>
 */
public interface Verarbeitung {

	/**
	 * Startet die Verarbeitung zum Ermitteln und Filtern der Termine sowie zum Erzeugen des Ergebnisses.
	 *
	 * @param terminErmittler
	 * @param terminFilter
	 * @param ergebnisErzeuger
	 * @throws HAWPlanToolException
	 */
	public void starteVerarbeitung(VeranstaltungsTerminErmittler terminErmittler, VeranstaltungsTerminFilter terminFilter, VeranstaltungsTerminEditor terminEditor, ErgebnisErzeuger ergebnisErzeuger) throws HAWPlanToolException;
}