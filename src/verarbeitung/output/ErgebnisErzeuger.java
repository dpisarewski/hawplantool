package de.dueddel.hawplantool.verarbeitung.output;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;

import java.util.Collection;

/**
 * <code>ErgebnisErzeuger</code>
 */
public interface ErgebnisErzeuger extends VerarbeitungsObjekt {

	/**
	 * Erzeugt aus den übergebenen Terminen ein Ergebnis.
	 *
	 * @param termine
	 * @throws HAWPlanToolException
	 */
	public void erzeugeErgebnis(Collection<VeranstaltungsTermin> termine) throws HAWPlanToolException;
}