package de.dueddel.hawplantool.gui;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.fabrik.SwingFabrik;
import de.dueddel.hawplantool.gui.fehleranzeige.FehlerDialog;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>EinstellungenDialog</code>
 */
public class EinstellungenDialog extends Dialog implements ActionListener {

	private VerarbeitungsObjekt verarbeitungsObjekt;
	private JButton buttonOk;
	private JButton buttonAbbruch;
	private JButton buttonHilfe;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>EinstellungenDialog</code>.
	 */
	public EinstellungenDialog(JFrame owner, VerarbeitungsObjekt verarbeitungsObjekt) throws HAWPlanToolException {
		super(owner, verarbeitungsObjekt.getKurzbeschreibung() + " - Einstellungen");
		this.verarbeitungsObjekt = verarbeitungsObjekt;

		initDialog(owner);
	}

	private void initDialog(JFrame owner) throws HAWPlanToolException {
//		Komponenten initialisieren
		buttonOk = SwingFabrik.erzeugeButton("Ok", 'o', this);
		buttonAbbruch = SwingFabrik.erzeugeButton("Abbruch", 'a', this);
		buttonHilfe = SwingFabrik.erzeugeButton("Hilfe", 'h', this);

		JPanel dialogPanel = new JPanel();
		JPanel einstellungenPanel = verarbeitungsObjekt.getPanelFuerKonfiguration(owner);
		JPanel buttonPanel = ButtonBarFactory.buildHelpOKCancelBar(buttonHilfe, buttonOk, buttonAbbruch);

//		Layout festlegen
		String spalten = "pref:grow";
		String zeilen = "fill:pref:grow, 4dlu, pref";
		FormLayout formLayout = new FormLayout(spalten, zeilen);
		dialogPanel.setLayout(formLayout);
		dialogPanel.setBorder(Borders.DIALOG_BORDER);

//		Komponenten anordnen
		CellConstraints cc = new CellConstraints();
		dialogPanel.add(einstellungenPanel, cc.xy(1, 1));
		dialogPanel.add(buttonPanel, cc.xy(1, 3));

		getContentPane().add(dialogPanel);
		getRootPane().setDefaultButton(buttonOk);
		setResizable(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOk) {
			try {
				verarbeitungsObjekt.aktionBeiKonfigurationOk();
			} catch (HAWPlanToolException e1) {
				new FehlerDialog(null, "Fehler beim Ausführen der Bestätigungs-Aktion.", e1).zeigeDialog();
			}
			schliesseDialog();
		} else if (e.getSource() == buttonAbbruch) {
			try {
				verarbeitungsObjekt.aktionBeiKonfigurationAbbruch();
			} catch (HAWPlanToolException e1) {
				new FehlerDialog(null, "Fehler beim Ausführen der Abbruch-Aktion.", e1).zeigeDialog();
			}
			schliesseDialog();
		} else if (e.getSource() == buttonHilfe) {
			Dialog dialogHilfe = SwingFabrik.erzeugeHilfeDialog(verarbeitungsObjekt, null);
			dialogHilfe.zeigeDialog();
		}
	}
}