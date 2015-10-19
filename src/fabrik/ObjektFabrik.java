package de.dueddel.hawplantool.fabrik;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.util.DateiLeser;
import de.dueddel.hawplantool.util.DateiLeser_Impl;
import de.dueddel.hawplantool.verarbeitung.Verarbeitung;
import de.dueddel.hawplantool.verarbeitung.Verarbeitung_Impl;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin_Impl;
import de.dueddel.hawplantool.verarbeitung.editor.VeranstaltungsTerminEditor;
import de.dueddel.hawplantool.verarbeitung.filter.VeranstaltungsTerminFilter;
import de.dueddel.hawplantool.verarbeitung.input.VeranstaltungsTerminErmittler;
import de.dueddel.hawplantool.verarbeitung.output.ErgebnisErzeuger;

import java.io.File;
import java.util.Collection;

/**
 * Die Klasse <code>ObjektFabrik</code> bietet Fabrikmethoden zur Erzeugung verschiedener Objekte.
 */
public class ObjektFabrik {

	/**
	 * Erzeugt eine Instanz der Klasse <code>DateiLeser</code>.
	 *
	 * @param datei
	 * @return DateiLeser
	 * @throws HAWPlanToolException
	 */
	public static DateiLeser erzeugeDateiLeser(String datei) throws HAWPlanToolException {
		return erzeugeDateiLeser(new File(datei));
	}

	/**
	 * Erzeugt eine Instanz der Klasse <code>DateiLeser</code>.
	 *
	 * @param datei
	 * @return DateiLeser
	 * @throws HAWPlanToolException
	 */
	public static DateiLeser erzeugeDateiLeser(File datei) throws HAWPlanToolException {
		return new DateiLeser_Impl(datei);
	}

	/**
	 * Erzeugt eine Instanz der Klasse <code>VeranstaltungsTermin</code>.
	 *
	 * @return VeranstaltungsTermin
	 */
	public static VeranstaltungsTermin erzeugeVeranstaltungsTermin() {
		return new VeranstaltungsTermin_Impl();
	}

	/**
	 * Erzeugt eine Instanz der Klasse <code>Verarbeitung</code>.
	 *
	 * @return Verarbeitung
	 */
	public static Verarbeitung erzeugeVerarbeitung() {
		return new Verarbeitung_Impl();
	}

	/**
	 * Gibt eine Liste aller eingebundenen <code>VeranstaltungsTerminErmittler</code>-Objekte zurück.
	 *
	 * @return Collection mit den geladenen Plugin-Objekten
	 * @throws HAWPlanToolException
	 */
	public static Collection<VeranstaltungsTerminErmittler> getVeranstaltungsTerminErmittler() throws HAWPlanToolException {
		return PluginErmittler.getTerminErmittler();
	}

	/**
	 * Gibt eine Liste aller eingebundenen <code>VeranstaltungsTerminFilter</code>-Objekte zurück.
	 *
	 * @return Collection mit den geladenen Plugin-Objekten
	 * @throws HAWPlanToolException
	 */
	public static Collection<VeranstaltungsTerminFilter> getVeranstaltungsTerminFilter() throws HAWPlanToolException {
		return PluginErmittler.getTerminFilter();
	}

	/**
	 * Gibt eine Liste aller eingebundenen <code>VeranstaltungsTerminEditor</code>-Objekte zurück.
	 *
	 * @return Collection mit den geladenen Plugin-Objekten
	 * @throws HAWPlanToolException
	 */
	public static Collection<VeranstaltungsTerminEditor> getVeranstaltungsTerminEditoren() throws HAWPlanToolException {
		return PluginErmittler.getTerminEditoren();
	}

	/**
	 * Gibt eine Liste aller eingebundenen <code>ErgebnisErzeuger</code>-Objekte zurück.
	 *
	 * @return Collection mit den geladenen Plugin-Objekten
	 * @throws HAWPlanToolException
	 */
	public static Collection<ErgebnisErzeuger> getErgebnisErzeuger() throws HAWPlanToolException {
		return PluginErmittler.getErgebnisErzeuger();
	}
}