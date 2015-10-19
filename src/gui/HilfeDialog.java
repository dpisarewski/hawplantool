package de.dueddel.hawplantool.gui;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import de.dueddel.hawplantool.fabrik.SwingFabrik;
import de.dueddel.hawplantool.verarbeitung.VerarbeitungsObjekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <code>EinstellungenDialog</code>
 */
public class HilfeDialog extends Dialog implements ActionListener {

	private VerarbeitungsObjekt verarbeitungsObjekt;
	private JButton buttonOk;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>EinstellungenDialog</code>.
	 */
	public HilfeDialog(JFrame owner, VerarbeitungsObjekt verarbeitungsObjekt) {
		super(owner, verarbeitungsObjekt.getKurzbeschreibung() + " - Hilfe");
		this.verarbeitungsObjekt = verarbeitungsObjekt;

		initDialog();
	}

	private void initDialog() {
//		Komponenten initialisieren
		buttonOk = SwingFabrik.erzeugeButton("Ok", 'o', this);

		JPanel dialogPanel = new JPanel();
		JTextArea hilfeTextArea = new JTextArea(verarbeitungsObjekt.getBeschreibung());
		hilfeTextArea.setEditable(false);
		hilfeTextArea.setLineWrap(true);
		hilfeTextArea.setWrapStyleWord(true);
		JScrollPane hilfeScrollPane = new JScrollPane(hilfeTextArea);
		hilfeScrollPane.setPreferredSize(new Dimension(300, 300));

		JPanel buttonPanel = ButtonBarFactory.buildOKBar(buttonOk);

//		Layout festlegen
		String spalten = "pref:grow";
		String zeilen = "fill:pref:grow, 4dlu, pref";
		FormLayout formLayout = new FormLayout(spalten, zeilen);
		dialogPanel.setLayout(formLayout);
		dialogPanel.setBorder(Borders.DIALOG_BORDER);

//		Komponenten anordnen
		CellConstraints cc = new CellConstraints();
		dialogPanel.add(hilfeScrollPane, cc.xy(1, 1));
		dialogPanel.add(buttonPanel, cc.xy(1, 3));

		getContentPane().add(dialogPanel);
		getRootPane().setDefaultButton(buttonOk);
		setResizable(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOk) {
			schliesseDialog();
		}
	}
}