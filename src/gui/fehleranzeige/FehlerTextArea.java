package de.dueddel.hawplantool.gui.fehleranzeige;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Eine <code>FehlerTextArea</code> ist ein nicht editierbares Textanzeigefeld für Fehler.
 */
public class FehlerTextArea extends JTextArea {

	private Throwable fehler;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>FehlerTextArea</code>.
	 */
	public FehlerTextArea() {
		setEditable(false);
	}

	public void setFehler(Throwable fehler) {
		this.fehler = fehler;
		aktualisiereFehlerText();
	}

	public Throwable getFehler() {
		return fehler;
	}

	private void aktualisiereFehlerText() {
		if (fehler != null) {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			fehler.printStackTrace(printWriter);
			String stackTraceString = stringWriter.toString();

			setText(stackTraceString);
			setCaretPosition(0);
		} else {
			setText(null);
		}
	}
}