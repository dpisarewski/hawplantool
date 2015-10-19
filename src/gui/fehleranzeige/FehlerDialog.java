package de.dueddel.hawplantool.gui.fehleranzeige;

import de.dueddel.hawplantool.gui.Dialog;

import javax.swing.*;

/**
 * <code>FehlerDialog</code>
 */
public class FehlerDialog extends Dialog {

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>FehlerDialog</code>.
	 */
	public FehlerDialog(JFrame owner, String fehlerMeldung) {
		this(owner, fehlerMeldung, null);
	}

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>FehlerDialog</code>.
	 */
	public FehlerDialog(JFrame owner, Throwable fehler) {
		this(owner, null, fehler);
	}

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>FehlerDialog</code>.
	 */
	public FehlerDialog(JFrame owner, String fehlerMeldung, Throwable fehler) {
		super(owner, "Ein Fehler trat auf.");
		initDialog(fehlerMeldung, fehler);
		schliesseDialog();
	}

	/**
	 * Initialisiert die Komponenten des Dialogs und ordnet sie auf dem Dialog an.
	 *
	 * @param fehlerMeldung
	 * @param fehler
	 */
	private void initDialog(String fehlerMeldung, Throwable fehler) {
		FehlerPanel fehlerPanel = new FehlerPanel(this, fehlerMeldung, fehler);
		getContentPane().add(fehlerPanel);
	}
}