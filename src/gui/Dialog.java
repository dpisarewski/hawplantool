package de.dueddel.hawplantool.gui;

import javax.swing.*;

/**
 * <code>Dialog</code>
 */
public abstract class Dialog extends JDialog {

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>Dialog</code>.
	 */
	public Dialog(JFrame owner, String titel) {
		super(owner, titel, true);
		setResizable(false);
	}

	/**
	 * Zeigt den Dialog an.
	 */
	public void zeigeDialog() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Schlieﬂt den Dialog.
	 */
	public void schliesseDialog() {
		setVisible(false);
		dispose();
	}
}