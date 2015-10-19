package de.dueddel.hawplantool.gui.fehleranzeige;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.ArrayList;

/**
 * <code>FehlerPanel</code>
 */
public class FehlerPanel extends JPanel implements ActionListener, KeyListener {

	private JLabel fehlerNachricht;
	private JPanel nachrichtPanel;

	private FehlerTextArea fehlerTextArea;
	private JScrollPane fehlerScrollPane;

	private FehlerDialog fehlerDialog;
	private JButton buttonDetails;
	private JPanel buttonBarDetails;
	private JButton buttonSchliessen;
	private JPanel buttonBarSchliessen;

	private boolean detailsAnzeigen;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>FehlerPanel</code>.
	 */
	public FehlerPanel(FehlerDialog dialog, String fehlerMeldung, Throwable fehler) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(Borders.DIALOG_BORDER);
		fehlerDialog = dialog;
		komponentenInitialisieren(fehlerMeldung, fehler);
		komponentenAnordnen();
	}

	private void komponentenInitialisieren(String fehlerMeldung, Throwable fehler) {
//		Kurzinfos zum Fehler
		fehlerNachricht = new JLabel();
		if (fehlerMeldung != null && !"".equals(fehlerMeldung.trim())) {
			fehlerNachricht.setText(fehlerMeldung);
		} else {
			fehlerNachricht.setText("Ein Fehler trat auf.");
		}
		nachrichtPanel = new JPanel(new GridLayout(0, 1));
		nachrichtPanel.setBorder(Borders.EMPTY_BORDER);
		nachrichtPanel.add(fehlerNachricht);
		if (fehler != null) {
			nachrichtPanel.add(new JSeparator(JSeparator.HORIZONTAL));
//			Infos zur Ursache anhängen
			Collection<Throwable> fehlerColl = new ArrayList<Throwable>();
			addUrsachen(fehlerColl, fehler);
			for (Throwable ursache : fehlerColl) {
				nachrichtPanel.add(new JLabel("Ursache: " + ursache.getMessage()));
			}
		}

//		Button zum Ein-/Ausblenden von Details
		buttonDetails = new JButton();
		buttonDetails.addActionListener(this);
//		damit der Button aussieht wie ein Link
		buttonDetails.setBorder(null);
		buttonDetails.setForeground(Color.BLUE);
		buttonDetails.setBorderPainted(false);
//		Button mit Button-Bar ausrichten
		buttonBarDetails = ButtonBarFactory.buildLeftAlignedBar(buttonDetails);
		buttonBarDetails.setBorder(Borders.createEmptyBorder(Sizes.DLUY2, Sizes.ZERO, Sizes.ZERO, Sizes.ZERO));

//		Details (Stacktrace des Fehlers)
		fehlerTextArea = new FehlerTextArea();
		fehlerTextArea.setFehler(fehler);
		fehlerScrollPane = new JScrollPane(fehlerTextArea);
		fehlerScrollPane.setPreferredSize(new Dimension(500, 350));

//		Button zum Schließen
		buttonSchliessen = new JButton("Schließen");
		buttonSchliessen.addActionListener(this);
		buttonBarSchliessen = ButtonBarFactory.buildCenteredBar(buttonSchliessen);
		buttonBarSchliessen.setBorder(Borders.createEmptyBorder(Sizes.DLUY8, Sizes.ZERO, Sizes.ZERO, Sizes.ZERO));

//		KeyListener hinzufügen für Abbruch
		fehlerTextArea.addKeyListener(this);
		fehlerScrollPane.addKeyListener(this);
		buttonDetails.addKeyListener(this);
		buttonSchliessen.addKeyListener(this);
	}

	private void komponentenAnordnen() {
		removeAll();
		if (fehlerNachricht.getText() != null && !"".equals(fehlerNachricht.getText())) {
			add(nachrichtPanel);
		}
		if (fehlerTextArea.getFehler() != null) {
			add(buttonBarDetails);
			buttonDetails.setText("Stacktrace einblenden");
			if (detailsAnzeigen) {
				buttonDetails.setText("Stacktrace ausblenden");
				add(fehlerScrollPane);
			}
		}
		add(buttonBarSchliessen);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonDetails) {
			detailsAnzeigen = !detailsAnzeigen;
			komponentenAnordnen();
			fehlerDialog.getRootPane().setDefaultButton(buttonSchliessen);
			fehlerDialog.pack();
		} else if (e.getSource() == buttonSchliessen) {
			fehlerDialog.schliesseDialog();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_ENTER) {
			buttonSchliessen.doClick();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	private void addUrsachen(Collection<Throwable> fehlerColl, Throwable fehler) {
		fehlerColl.add(fehler);

		Throwable ursache = fehler.getCause();
		if (ursache != null) {
			addUrsachen(fehlerColl, ursache);
		}
	}
}