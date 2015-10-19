package de.dueddel.hawplantool.verarbeitung.input;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import de.dueddel.hawplantool.HAWPlanToolException;
import de.dueddel.hawplantool.fabrik.ObjektFabrik;
import de.dueddel.hawplantool.util.DateiLeser;
import de.dueddel.hawplantool.util.DatumUtil;
import de.dueddel.hawplantool.util.StringUtil;
import de.dueddel.hawplantool.verarbeitung.daten.VeranstaltungsTermin;

public class HawPlanTxtUtil {

	private static final String TRENNZEICHEN_KW_LISTE = ",";
	private static final String TRENNZEICHEN_KW_SPANNE = "-";
	private static final String TRENNZEICHEN_ZEIT = ":";
	private static final String DEFAULT_KATEGORIE = "HAW";
	private static final String NOT_IN_FILE = "Not defined in new Format";
	
	public static Collection<VeranstaltungsTermin> getTermine(File datei) throws HAWPlanToolException {
		Collection<VeranstaltungsTermin> termine = new ArrayList<VeranstaltungsTermin>();
		if (datei != null) {

			// Datei mit HAW-Plan auslesen
			DateiLeser dateiLeser = ObjektFabrik.erzeugeDateiLeser(datei);
			Iterator<String> zeilenIt = dateiLeser.getZeilenIterator();
			while (zeilenIt.hasNext()) {
				String zeile = (String) zeilenIt.next();
				if (isVeranstaltung(zeile)) {
					System.out.println(zeile);
					String[] values = zeile.split("\t");
					ArrayList<Integer> weeks = getKalenderwochen(values[4].trim());
					for (Integer week : weeks) {
						Date beginn = getDatumUndZeit(week, values[1], values[2]);
						Date ende = getDatumUndZeit(week, values[1], values[3]);
						
						VeranstaltungsTermin termin = ObjektFabrik.erzeugeVeranstaltungsTermin();
						
						termin.setName(values[0]);
						termin.setProf(NOT_IN_FILE);
						termin.setOrt(NOT_IN_FILE);
						termin.setKategorie(DEFAULT_KATEGORIE);
						termin.setBeginn(beginn);
						termin.setEnde(ende);
						termin.setSemestergruppe(values[0].split("-")[0]);
						termine.add(termin);
					}
					
				}

			}

		}
		return termine;
	}

	
	private static Date getDatumUndZeit(int kalenderwoche, String tag, String zeit) throws HAWPlanToolException {
		
		GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
		gregorianCalendar.clear(GregorianCalendar.SECOND);
		gregorianCalendar.clear(GregorianCalendar.SECOND);
		gregorianCalendar.set(GregorianCalendar.WEEK_OF_YEAR, kalenderwoche);
		gregorianCalendar.set(GregorianCalendar.DAY_OF_WEEK, DatumUtil.getWochentag(tag));

		String stunde = zeit.split(":")[0];
		String minute = zeit.split(":")[1];

		try {
			gregorianCalendar.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(stunde.trim()));
			gregorianCalendar.set(GregorianCalendar.MINUTE, Integer.parseInt(minute.trim()));
		} catch (NumberFormatException e) {
			throw new HAWPlanToolException("Fehler beim Parsen in einen int. KW=" + kalenderwoche + ", tag=" + tag + ", zeit=" + stunde + TRENNZEICHEN_ZEIT + minute, e);
		}

		return gregorianCalendar.getTime();
	}
	
	private static boolean isVeranstaltung(String zeile) {
		if (zeile.isEmpty()) {
			return false;
		} else if (zeile.startsWith("Name")) {
			return false;
		} else if (zeile.startsWith("Liste")) {
			return false;
		} else if (zeile.startsWith("<Hier klicken")) {
			return false;
		} else {
			return true;
		}
	}

	private static ArrayList<Integer> getKalenderwochen(String zeile) throws HAWPlanToolException {
		ArrayList<Integer> kalenderwochen = new ArrayList<Integer>();

		// Zeile untergliedern bei Auflistung von Kalenderwochen (z.B. "21, 24,
		// 26-27" in drei Teile gliedern)
		Collection<String> kwListe = StringUtil.getTokens(zeile, TRENNZEICHEN_KW_LISTE, false);

		// über die Liste iterieren
		for (String kwListeToken : kwListe) {

			// Token weiter untergliedern, falls eine Zeitspanne angegeben wurde
			// (z.B.
			// "26-27" in zwei Teile gliedern)
			Collection<String> kwAngaben = StringUtil.getTokens(kwListeToken, TRENNZEICHEN_KW_SPANNE, false);
			
			// wenn Zeitspanne
			if (kwAngaben.size() == 2) {
				int kwSpanneStart;
				int kwSpanneEnde;
				try {
					Iterator<String> spanneIt = kwAngaben.iterator();
					kwSpanneStart = Integer.valueOf(spanneIt.next().trim());
					kwSpanneEnde = Integer.valueOf(spanneIt.next().trim());
				} catch (NumberFormatException e) {
					throw new HAWPlanToolException("Fehler beim Parsen der KW-Angabe Zeitspanne'" + kwAngaben + "' aus der Zeile '" + zeile + "' in Integer-Werte.", e);
				}

				// die einzelnen Kalenderwochen der Zeitspanne erfassen
				for (int kw = kwSpanneStart; kw <= kwSpanneEnde; kw++) {
					kalenderwochen.add(kw);
				}
			}

			// wenn keine Zeitspanne, sondern konkrete KW-Angabe
			else {
				String kwAngabe = kwAngaben.iterator().next().trim();
				try {
					// KW-Angabe erfassen
					kalenderwochen.add(Integer.valueOf(kwAngabe));
				} catch (NumberFormatException e) {
					throw new HAWPlanToolException("Fehler beim Parsen der KW-Angabe konkrete'" + kwAngabe + "' aus der Zeile '" + zeile + "' in Integer-Werte.", e);
				}
			}
		}

		return kalenderwochen;
	}

}
