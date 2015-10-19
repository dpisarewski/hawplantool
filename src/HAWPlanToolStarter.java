package de.dueddel.hawplantool;

import de.dueddel.hawplantool.gui.ProgrammFenster;
import de.dueddel.hawplantool.gui.fehleranzeige.FehlerDialog;

/**
 * <code>HAWPlanToolStarter</code>
 */
public class HAWPlanToolStarter {

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>HAWPlanToolStarter</code>.
	 */
	public HAWPlanToolStarter() throws HAWPlanToolException {
		new ProgrammFenster().zeigeFenster();
	}

	public static void main(String[] args) {
		try {
			new HAWPlanToolStarter();
		} catch (HAWPlanToolException e) {
			new FehlerDialog(null, e.getMessage(), e).zeigeDialog();
		}
	}
}