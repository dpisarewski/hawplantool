package de.dueddel.hawplantool.util;

import de.dueddel.hawplantool.konstanten.DatumKonstanten;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Die Klasse <code>DatumUtil</code> bietet Hilfsmethoden für den Umgang mit Daten und Uhrzeiten.
 */
public class DatumUtil {

	/**
	 * Multiplikationsfaktor einer Tagesstunde zur Ermittlung einer Zeitdauer in Minuten.
	 */
	public static final int FAKTOR_STUNDE = 60;
	/**
	 * Multiplikationsfaktor einer Tagesstunde zur Darstellung einer Uhrzeit als Integer-Wert.
	 */
	public static final int FAKTOR_STUNDE_FUER_UHRZEIT = 100;

	/**
	 * Gibt das übergebene Datum als String zurück, im Format "TT.MM.JJJJ".
	 *
	 * @param datum
	 * @return String
	 */
	public static String getDatumString(Date datum) {
		return new SimpleDateFormat("dd.MM.yyyy").format(datum);
	}

	/**
	 * Ermittelt, ob die beiden Date-Objekte das gleiche Datum repräsentieren. Die Uhrzeit wird dabei nicht beachtet, lediglich Tag, Monat und Jahr werden zum Vergleich herangezogen.
	 *
	 * @param datum
	 * @param anderesDatum
	 * @return boolean
	 */
	public static boolean isGleicherTag(Date datum, Date anderesDatum) {
		Calendar kalender = Calendar.getInstance();
		kalender.setTime(datum);
		Calendar referenzKalender = Calendar.getInstance();
		referenzKalender.setTime(anderesDatum);

		return kalender.get(Calendar.DAY_OF_MONTH) == referenzKalender.get(Calendar.DAY_OF_MONTH) && kalender.get(Calendar.MONTH) == referenzKalender.get(Calendar.MONTH) && kalender.get(Calendar.YEAR) == referenzKalender.get(Calendar.YEAR);
	}

	/**
	 * Ermittelt aus dem übergebenen Date-Objekt den Wochentag als Integer-Wert.
	 *
	 * @param datum
	 * @return int
	 * @see Calendar
	 * @see Calendar#DAY_OF_WEEK
	 */
	public static int getWochentag(Date datum) {
		Calendar kalender = Calendar.getInstance();
		kalender.setTime(datum);

		return kalender.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * Übersetzt den Wochentag-String in einen Integer-Wert.
	 *
	 * @param wochentag
	 * @return int
	 * @see Calendar
	 * @see Calendar#DAY_OF_WEEK
	 */
	public static int getWochentag(String wochentag) {
		if (DatumKonstanten.MONTAG.equalsIgnoreCase(wochentag)) {
			return Calendar.MONDAY;
		} else if (DatumKonstanten.DIENSTAG.equalsIgnoreCase(wochentag)) {
			return Calendar.TUESDAY;
		} else if (DatumKonstanten.MITTWOCH.equalsIgnoreCase(wochentag)) {
			return Calendar.WEDNESDAY;
		} else if (DatumKonstanten.DONNERSTAG.equalsIgnoreCase(wochentag)) {
			return Calendar.THURSDAY;
		} else if (DatumKonstanten.FREITAG.equalsIgnoreCase(wochentag)) {
			return Calendar.FRIDAY;
		} else if (DatumKonstanten.SAMSTAG.equalsIgnoreCase(wochentag)) {
			return Calendar.SATURDAY;
		} else if (DatumKonstanten.SONNTAG.equalsIgnoreCase(wochentag)) {
			return Calendar.SUNDAY;
		}
		return DatumKonstanten.UNBEKANNT_INT;
	}

	/**
	 * Ermittelt aus dem übergebenen Date-Objekt den Wochentag als String.
	 *
	 * @param datum
	 * @return String
	 * @see DatumKonstanten
	 */
	public static String getWochentagString(Date datum) {
		return new SimpleDateFormat("EE").format(datum);
	}

	/**
	 * Übersetzt den Integer-Wert eines Wochentags in einen String.
	 *
	 * @param wochentag
	 * @return String
	 */
	public static String getWochentagString(int wochentag) {
		String wochentagString = null;
		switch (wochentag) {
			case Calendar.MONDAY:
				wochentagString = DatumKonstanten.MONTAG;
				break;
			case Calendar.TUESDAY:
				wochentagString = DatumKonstanten.DIENSTAG;
				break;
			case Calendar.WEDNESDAY:
				wochentagString = DatumKonstanten.MITTWOCH;
				break;
			case Calendar.THURSDAY:
				wochentagString = DatumKonstanten.DONNERSTAG;
				break;
			case Calendar.FRIDAY:
				wochentagString = DatumKonstanten.FREITAG;
				break;
			case Calendar.SATURDAY:
				wochentagString = DatumKonstanten.SAMSTAG;
				break;
			case Calendar.SUNDAY:
				wochentagString = DatumKonstanten.SONNTAG;
				break;
			default:
				wochentagString = DatumKonstanten.UNBEKANNT_STRING;
		}
		return wochentagString;
	}

	/**
	 * Ermittelt aus dem übergebenen Date-Objekt die Uhrzeit als Integer-Wert.
	 * <br/><br/>
	 * Bsp.: Wird als Parameter ein Datum mit der Uhrzeit 14:15 Uhr übergeben, so gibt diese Methode 1415 zurück.
	 *
	 * @param datum
	 * @return int
	 */
	public static int getUhrzeit(Date datum) {
		Calendar kalender = Calendar.getInstance();
		kalender.setTime(datum);

		return kalender.get(Calendar.HOUR_OF_DAY) * FAKTOR_STUNDE_FUER_UHRZEIT + kalender.get(Calendar.MINUTE);
	}

	/**
	 * Ermittelt aus den übergebenen Minuten die Uhrzeit als Integer-Wert.
	 * <br/><br/>
	 * Bsp.: Wird als Parameter der Wert 80 übergeben, so gibt diese Methode 120 (also die Uhrzeit 1:20 Uhr) zurück, da es 80 Minuten von 0:00 Uhr bis 1:20 Uhr ist.
	 *
	 * @param minuten
	 * @return int
	 */
	public static int getUhrzeit(int minuten) {
		int stunde = minuten / FAKTOR_STUNDE * FAKTOR_STUNDE_FUER_UHRZEIT;
		int minute = minuten % FAKTOR_STUNDE;
		return stunde + minute;
	}

	/**
	 * Ermittelt aus dem übergebenen Date-Objekt die Uhrzeit als String, im Format "hh:mm".
	 *
	 * @param datum
	 * @return String
	 */
	public static String getUhrzeitString(Date datum) {
		return new SimpleDateFormat("kk:mm").format(datum);
	}

	/**
	 * Ermittelt aus dem übergebenen Integer-Wert die Uhrzeit als String, im Format "hh:mm".
	 *
	 * @param uhrzeit
	 * @return String
	 */
	public static String getUhrzeitString(int uhrzeit) {
		int stunde = getUhrzeitStunde(uhrzeit);
		int minute = getUhrzeitMinute(uhrzeit);

		return stunde + ":" + (minute < 10 ? 0 : "") + minute;
	}

	/**
	 * Ermittelt aus dem übergebenen Datum die Tagesstunde, also die Anzahl vergangener Stunden seit 0:00 Uhr.
	 * <br/><br/>
	 * Bsp.: Wird als Parameter ein Datum mit der Uhrzeit 16:48 übergeben, so gibt diese Methode 16 zurück.
	 *
	 * @param datum
	 * @return int
	 */
	public static int getUhrzeitStunde(Date datum) {
		return getUhrzeitStunde(getUhrzeit(datum));
	}

	/**
	 * Ermittelt aus dem übergebenen Datum die Minuten nach der vollen Tagesstunde.
	 *
	 * @param datum
	 * @return int
	 */
	public static int getUhrzeitMinute(Date datum) {
		return getUhrzeitMinute(getUhrzeit(datum));
	}

	/**
	 * Ermittelt aus der übergebenen Uhrzeit die Tagesstunde, also die Anzahl vergangener Stunden seit 0:00 Uhr (inklusive Korrektur der Stunden abhängig von den Minuten).
	 * <br/><br/>
	 * Bsp.: Wird als Parameter der Wert 1562 übergeben (also die nicht existente Uhrzeit 15:62 Uhr), so gibt diese Methode 16 zurück, da der Wert eigentlich die Uhrzeit 16:02 repräsentiert.
	 *
	 * @param uhrzeit
	 * @return int
	 * @see DatumUtil#getUhrzeitStundeWieAngegeben(int)
	 */
	public static int getUhrzeitStunde(int uhrzeit) {
		return getUhrzeitStundeWieAngegeben(uhrzeit) + getUhrzeitMinuteWieAngegeben(uhrzeit) / FAKTOR_STUNDE;
	}

	/**
	 * Ermittelt aus der übergebenen Uhrzeit die Tagesstunde, so wie sie angegeben wurde (also ohne Korrektur der Stunden abhängig von den Minuten).
	 * <br/><br/>
	 * Bsp.: Wird als Parameter der Wert 1562 übergeben (also die nicht existente Uhrzeit 15:62 Uhr), so gibt diese Methode 15 zurück.
	 *
	 * @param uhrzeit
	 * @return int
	 * @see DatumUtil#getUhrzeitStunde(int)
	 */
	public static int getUhrzeitStundeWieAngegeben(int uhrzeit) {
		return uhrzeit / FAKTOR_STUNDE_FUER_UHRZEIT;
	}

	/**
	 * Ermittelt aus der übergebenen Uhrzeit die Minuten nach der vollen Tagesstunde (inklusive Korrektur der Minuten).
	 * <br/><br/>
	 * Bsp.: Wird als Parameter der Wert 1562 übergeben (also die Uhrzeit 15:62), so gibt diese Methode 2 zurück, da der Wert eigentlich die Uhrzeit 16:02 repräsentiert.
	 *
	 * @param uhrzeit
	 * @return int
	 * @see DatumUtil#getUhrzeitMinuteWieAngegeben(int)
	 */
	public static int getUhrzeitMinute(int uhrzeit) {
		return getUhrzeitMinuteWieAngegeben(uhrzeit) % FAKTOR_STUNDE;
	}

	/**
	 * Ermittelt aus der übergebenen Uhrzeit die Minuten nach der Tagesstunde, so wie sie angegeben wurde (also ohne Korrektur der Minuten).
	 * <br/><br/>
	 * Bsp.: Wird als Parameter der Wert 1562 übergeben (also die Uhrzeit 15:62), so gibt diese Methode 62 zurück.
	 *
	 * @param uhrzeit
	 * @return int
	 * @see DatumUtil#getUhrzeitMinute(int)
	 */
	public static int getUhrzeitMinuteWieAngegeben(int uhrzeit) {
		return uhrzeit % FAKTOR_STUNDE_FUER_UHRZEIT;
	}

	/**
	 * Ermittelt zum übergebenen Date-Objekt die Tagesminute, also die Anzahl vergangener Minuten seit 0:00 Uhr.
	 *
	 * @param datum
	 * @return int
	 */
	public static int getMinute(Date datum) {
		return getMinute(getUhrzeit(datum));
	}

	/**
	 * Ermittelt zur übergebenen Uhrzeit die Tagesminute, also die Anzahl vergangener Minuten seit 0:00 Uhr.
	 *
	 * @param uhrzeit
	 * @return int
	 */
	public static int getMinute(int uhrzeit) {
		int stunde = getUhrzeitStunde(uhrzeit);
		int minute = getUhrzeitMinute(uhrzeit);

		return stunde * FAKTOR_STUNDE + minute;
	}

	/**
	 * Ermittelt die Tagesdifferenz zweier Daten (die Anzahl der zwischenliegenden Nächte).
	 *
	 * @param datum
	 * @param anderesDatum
	 * @return int
	 */
	public static int getAnzahlTageZwischenDenDaten(Date datum, Date anderesDatum) {
		long differenzInMillis;

		Calendar kalender = Calendar.getInstance();
		kalender.setTime(datum);
		setUhrzeit(kalender, 0);

		Calendar andererKalender = Calendar.getInstance();
		andererKalender.setTime(anderesDatum);
		setUhrzeit(andererKalender, 0);

		if (kalender.after(andererKalender)) {
			differenzInMillis = kalender.getTimeInMillis() - andererKalender.getTimeInMillis();
		} else {
			differenzInMillis = andererKalender.getTimeInMillis() - kalender.getTimeInMillis();
		}

		return (int) (differenzInMillis / 1000 / 60 / 60 / 24);
	}

	/**
	 * Legt die Uhrzeit für ein Calendar-Objekt fest.
	 *
	 * @param kalender
	 * @param uhrzeit
	 */
	public static void setUhrzeit(Calendar kalender, int uhrzeit) {
		kalender.clear(Calendar.SECOND);
		kalender.clear(Calendar.MILLISECOND);
		kalender.set(Calendar.HOUR_OF_DAY, DatumUtil.getUhrzeitStunde(uhrzeit));
		kalender.set(Calendar.MINUTE, DatumUtil.getUhrzeitMinute(uhrzeit));
	}
}