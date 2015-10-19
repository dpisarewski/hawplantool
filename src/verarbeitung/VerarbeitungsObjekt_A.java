package de.dueddel.hawplantool.verarbeitung;

import com.jgoodies.forms.factories.Borders;
import de.dueddel.hawplantool.HAWPlanToolException;

import javax.swing.*;

/**
 * <code>VerarbeitungsObjekt_A</code>
 */
public abstract class VerarbeitungsObjekt_A implements VerarbeitungsObjekt {

	private JFrame owner;

	public boolean isKonfigurierbar() throws HAWPlanToolException {
		return getPanelFuerKonfiguration(null).getComponentCount() > 0;
	}

	public JPanel getPanelFuerKonfiguration(JFrame owner) throws HAWPlanToolException {
		this.owner = owner;
		JPanel panel = new JPanel();
		initialisierePanelFuerEinstellungen(panel);
		panel.setBorder(Borders.EMPTY_BORDER);
		return panel;
	}

	/**
	 * Ordnet die Dialogkomponeten f�r die Konfiguration des VerarbeitungsObjektes auf dem �bergebenen Panel an.
	 *
	 * @param panel
	 */
	protected abstract void initialisierePanelFuerEinstellungen(JPanel panel) throws HAWPlanToolException;

	@Override
	public String toString() {
		return getKurzbeschreibung();
	}

	/**
	 * Gibt ein JFrame zur�ck, welches bei der Konfiguration des VerarbeitungsObjektes im Bedarfsfall als Owner von zus�tzlichen Dialogen dienlich sein kann.
	 *
	 * @return JFrame
	 */
	public JFrame getOwnerFuerDialoge() {
		return owner;
	}

	public int compareTo(Object o) {
		if (o != null && o instanceof VerarbeitungsObjekt) {
			return getKurzbeschreibung().toLowerCase().compareTo(((VerarbeitungsObjekt) o).getKurzbeschreibung().toLowerCase());
		}
		return 0;
	}
}