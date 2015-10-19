package de.dueddel.hawplantool.gui;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.fabrik.ObjektFabrik;
import de.dueddel.hawplantool.fabrik.SwingFabrik;
import de.dueddel.hawplantool.gui.fehleranzeige.FehlerDialog;
import de.dueddel.hawplantool.verarbeitung.Verarbeitung;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;
import de.dueddel.hawplantool.verarbeitung.editor.VeranstaltungsTerminEditor;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;
import de.dueddel.hawplantool.verarbeitung.output.ErgebnisErzeuger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * <code>ProgrammFenster</code>
 */
public class ProgrammPanel extends JPanel implements ActionListener {

	private JFrame owner;

	private JLabel labelInput;
	private JLabel labelOutput;
	private JLabel labelFilter;
	private JLabel labelEditor;
	private JComboBox comboBoxAuswahlInput;
	private JComboBox comboBoxAuswahlOutput;
	private JComboBox comboBoxAuswahlFilter;
	private JComboBox comboBoxAuswahlEditor;
	private JButton buttonEinstellungenInput;
	private JButton buttonEinstellungenOutput;
	private JButton buttonEinstellungenFilter;
	private JButton buttonEinstellungenEditor;
	private JButton buttonHilfeInput;
	private JButton buttonHilfeOutput;
	private JButton buttonHilfeFilter;
	private JButton buttonHilfeEditor;
	private JButton buttonVerarbeitungStarten;
	private JComponent separatorKonfiguration;
	private JComponent separatorVerarbeitung;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>ProgrammFenster</code>.
	 */
	public ProgrammPanel(JFrame owner) throws HAWPlanToolException {
		this.owner = owner;

//		Komponenten initialisieren und Layout festlegen
		init();

//		Komponenten anordnen
		construct();
	}

	private void init() throws HAWPlanToolException {
		labelInput = new JLabel("Quelle");
		labelFilter = new JLabel("Filter");
		labelEditor = new JLabel("Editor");
		labelOutput = new JLabel("Ergebnis");

		Object[] ermittler = ObjektFabrik.getVeranstaltungsTerminErmittler().toArray();
		Object[] filter = ObjektFabrik.getVeranstaltungsTerminFilter().toArray();
		Object[] editoren = ObjektFabrik.getVeranstaltungsTerminEditoren().toArray();
		Object[] erzeuger = ObjektFabrik.getErgebnisErzeuger().toArray();

		Arrays.sort(ermittler);
		Arrays.sort(filter);
		Arrays.sort(editoren);
		Arrays.sort(erzeuger);

		comboBoxAuswahlInput = new JComboBox(ermittler);
		comboBoxAuswahlFilter = new JComboBox(filter);
		comboBoxAuswahlEditor = new JComboBox(editoren);
		comboBoxAuswahlOutput = new JComboBox(erzeuger);

		buttonEinstellungenInput = SwingFabrik.erzeugeButton("Einstellungen", this);
		buttonHilfeInput = SwingFabrik.erzeugeButton("?", this);
		buttonEinstellungenFilter = SwingFabrik.erzeugeButton("Einstellungen", this);
		buttonHilfeFilter = SwingFabrik.erzeugeButton("?", this);
		buttonEinstellungenEditor = SwingFabrik.erzeugeButton("Einstellungen", this);
		buttonHilfeEditor = SwingFabrik.erzeugeButton("?", this);
		buttonEinstellungenOutput = SwingFabrik.erzeugeButton("Einstellungen", this);
		buttonHilfeOutput = SwingFabrik.erzeugeButton("?", this);
		buttonVerarbeitungStarten = SwingFabrik.erzeugeButton("Und ab die Post!", 'p', this);

		separatorKonfiguration = SwingFabrik.erzeugeSeparator("Konfiguration");
		separatorVerarbeitung = SwingFabrik.erzeugeSeparator("Verarbeitung");

		String spalten = "right:pref, 8dlu, pref:grow, 4dlu, pref, 4dlu, pref";
		String zeilen = "pref:grow, 4dlu:grow(0.5), pref:grow, 4dlu:grow(0.2), pref:grow, 4dlu:grow(0.2), pref:grow, 4dlu:grow(0.2), pref:grow, 4dlu:grow(0.2), pref:grow, 4dlu:grow(0.5), pref:grow";
		FormLayout formLayout = new FormLayout(spalten, zeilen);
		setLayout(formLayout);
		setBorder(Borders.DIALOG_BORDER);
	}

	private void construct() {
		CellConstraints cc = new CellConstraints();

		add(separatorKonfiguration, cc.xyw(1, 1, 7));

		add(labelInput, cc.xy(1, 3));
		add(comboBoxAuswahlInput, cc.xy(3, 3));
		add(buttonEinstellungenInput, cc.xy(5, 3));
		add(buttonHilfeInput, cc.xy(7, 3));

		add(labelFilter, cc.xy(1, 5));
		add(comboBoxAuswahlFilter, cc.xy(3, 5));
		add(buttonEinstellungenFilter, cc.xy(5, 5));
		add(buttonHilfeFilter, cc.xy(7, 5));

		add(labelEditor, cc.xy(1, 7));
		add(comboBoxAuswahlEditor, cc.xy(3, 7));
		add(buttonEinstellungenEditor, cc.xy(5, 7));
		add(buttonHilfeEditor, cc.xy(7, 7));

		add(labelOutput, cc.xy(1, 9));
		add(comboBoxAuswahlOutput, cc.xy(3, 9));
		add(buttonEinstellungenOutput, cc.xy(5, 9));
		add(buttonHilfeOutput, cc.xy(7, 9));

		add(separatorVerarbeitung, cc.xyw(1, 11, 7));

		add(ButtonBarFactory.buildOKBar(buttonVerarbeitungStarten), cc.xyw(1, 13, 7));
	}

	public void actionPerformed(ActionEvent e) {
//		wenn Klick auf einen Einstellungen-Button
		if (e.getSource() == buttonEinstellungenInput || e.getSource() == buttonEinstellungenFilter || e.getSource() == buttonEinstellungenEditor || e.getSource() == buttonEinstellungenOutput) {
			klickEinstellungen(e);
			repaint();
		}

//		wenn Klick auf einen Hilfe-Button
		else if (e.getSource() == buttonHilfeInput || e.getSource() == buttonHilfeFilter || e.getSource() == buttonHilfeEditor || e.getSource() == buttonHilfeOutput) {
			klickHilfe(e);
		}

//		Start der Verarbeitung
		else if (e.getSource() == buttonVerarbeitungStarten) {
			klickStart();
		}
	}

	private void klickStart() {
		try {
			Verarbeitung verarbeitung = ObjektFabrik.erzeugeVerarbeitung();
			verarbeitung.starteVerarbeitung(getTerminErmittler(), getTerminFilter(), getTerminEditor(), getErgebnisErzeuger());
			JOptionPane.showMessageDialog(owner, "Ergebnis erfolgreich erzeugt.");
		} catch (HAWPlanToolException exc) {
			new FehlerDialog(owner, "Die Verarbeitung wurde abgebrochen.", exc).zeigeDialog();
		}
	}

	private void klickHilfe(ActionEvent e) {
		VerarbeitungsObjekt verarbeitungsObjekt;

		if (e.getSource() == buttonHilfeInput) {
			verarbeitungsObjekt = getTerminErmittler();
		} else if (e.getSource() == buttonHilfeFilter) {
			verarbeitungsObjekt = getTerminFilter();
		} else if (e.getSource() == buttonHilfeEditor) {
			verarbeitungsObjekt = getTerminEditor();
		} else {
			verarbeitungsObjekt = getErgebnisErzeuger();
		}

		if (verarbeitungsObjekt == null) {
			new FehlerDialog(owner, "Es wurde keine Auswahl getroffen, das Objekt ist NULL. Ein Aufruf der Hilfe ist daher nicht möglich.").zeigeDialog();
		} else {
			Dialog dialogHilfe = SwingFabrik.erzeugeHilfeDialog(verarbeitungsObjekt, owner);
			dialogHilfe.zeigeDialog();
		}
	}

	private void klickEinstellungen(ActionEvent e) {
		VerarbeitungsObjekt verarbeitungsObjekt;

		if (e.getSource() == buttonEinstellungenInput) {
			verarbeitungsObjekt = getTerminErmittler();
		} else if (e.getSource() == buttonEinstellungenFilter) {
			try {
				getTerminFilter().setAktuellenTerminErmittlerFuerKonfiguration(getTerminErmittler());
			} catch (HAWPlanToolException e1) {
				new FehlerDialog(owner, "Fehler beim Initialisieren des Einstellungsdialogs. Die Quelle wurde offenbar noch nicht richtig konfiguriert, dies ist für diesen Filter allerdings nötig.", e1).zeigeDialog();
			}
			verarbeitungsObjekt = getTerminFilter();
		} else if (e.getSource() == buttonEinstellungenEditor) {
			verarbeitungsObjekt = getTerminEditor();
		} else {
			verarbeitungsObjekt = getErgebnisErzeuger();
		}

		if (verarbeitungsObjekt == null) {
			new FehlerDialog(owner, "Es wurde keine Auswahl getroffen, das Objekt ist NULL. Einstellungen sind daher nicht möglich.").zeigeDialog();
		} else {
			try {
//				wenn einstellbar, dann Einstellungsdialog zeigen
				if (verarbeitungsObjekt.isKonfigurierbar()) {
					Dialog dialogEinstellungen = SwingFabrik.erzeugeEinstellungenDialog(verarbeitungsObjekt, owner);
					dialogEinstellungen.zeigeDialog();
				}
//				ansonsten eine entsprechende Meldung bringen
				else {
					JOptionPane.showMessageDialog(owner, "Bei diesem Plugin sind keine Einstellungen möglich.");
				}
			} catch (HAWPlanToolException e1) {
				new FehlerDialog(owner, "Fehler beim Erzeugen des Einstellungen-Dialogs.", e1).zeigeDialog();
			}
		}
	}

	private VeranstaltungsTerminErmittler getTerminErmittler() {
		return (VeranstaltungsTerminErmittler) comboBoxAuswahlInput.getSelectedItem();
	}

	private VeranstaltungsTerminFilter getTerminFilter() {
		return (VeranstaltungsTerminFilter) comboBoxAuswahlFilter.getSelectedItem();
	}

	private VeranstaltungsTerminEditor getTerminEditor() {
		return (VeranstaltungsTerminEditor) comboBoxAuswahlEditor.getSelectedItem();
	}

	private ErgebnisErzeuger getErgebnisErzeuger() {
		return (ErgebnisErzeuger) comboBoxAuswahlOutput.getSelectedItem();
	}
}