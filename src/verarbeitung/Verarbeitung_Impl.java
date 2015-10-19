package de.dueddel.hawplantool.verarbeitung;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.editor.VeranstaltungsTerminEditor;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;
import de.dueddel.hawplantool.verarbeitung.output.ErgebnisErzeuger;

import java.util.Collection;

/**
 * <code>Verarbeitung_Impl</code>
 */
public class Verarbeitung_Impl implements Verarbeitung {

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>Verarbeitung_Impl</code>.
	 */
	public Verarbeitung_Impl() {
	}

	public void starteVerarbeitung(VeranstaltungsTerminErmittler terminErmittler, VeranstaltungsTerminFilter terminFilter, VeranstaltungsTerminEditor terminEditor, ErgebnisErzeuger ergebnisErzeuger) throws HAWPlanToolException {
		pruefeObjektAufNULL(terminErmittler, "Terminquelle");
		pruefeObjektAufNULL(terminFilter, "Terminfilter");
		pruefeObjektAufNULL(terminEditor, "Termineditor");
		pruefeObjektAufNULL(ergebnisErzeuger, "Ergebniserzeuger");

//		Input mit dem Filter holen
		Collection<VeranstaltungsTermin> veranstaltungsTermine = terminErmittler.getVeranstaltungsTermine(terminFilter);
//		Input bearbeiten
		veranstaltungsTermine = terminEditor.bearbeiteTermine(veranstaltungsTermine);
//		Output erzeugen
		if (veranstaltungsTermine == null || veranstaltungsTermine.isEmpty()) {
			throw new HAWPlanToolException("Der Output kann nicht erzeugt werden, da keine Termine ermittelt wurden.");
		}
		ergebnisErzeuger.erzeugeErgebnis(veranstaltungsTermine);
	}

	private void pruefeObjektAufNULL(Object objekt, String objektArt) throws HAWPlanToolException {
		if (objekt == null) {
			throw new HAWPlanToolException("Das Objekt '" + objektArt + "' ist NULL. Verarbeitung nicht möglich.");
		}
	}
}