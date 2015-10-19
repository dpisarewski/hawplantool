package de.dueddel.hawplantool.gui;

import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import de.dueddel.hawplantool.HAWPlanToolException;

import javax.swing.*;

/**
 * <code>ProgrammFenster</code>
 */
public class ProgrammFenster extends JFrame {

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>ProgrammFenster</code>.
	 */
	public ProgrammFenster() throws HAWPlanToolException {
		super("HAW-Plan Tool");

		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			throw new HAWPlanToolException("Fehler beim Setzen des Look and Feels", e);
		}

		ProgrammPanel programmPanel = new ProgrammPanel(this);
		getContentPane().add(programmPanel);
	}

	/**
	 * Zeigt das Programm-Fenster an.
	 */
	public void zeigeFenster() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}