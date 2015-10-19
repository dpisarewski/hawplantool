package de.dueddel.hawplantool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Die Klasse <code>StringUtil</code> bietet Hilfsmethoden für den Umgang mit Zeichenketten.
 */
public class StringUtil {

	/**
	 * Zerlegt einen String anhand von Trennezichen in Teilstrings und gibt diese in einer Collection zurück (wahlweise mit/ohne Trennzeichen).
	 *
	 * @param zeichenkette
	 * @param trennzeichen
	 * @param inklusiveTrennzeichen
	 * @return String
	 */
	public static Collection<String> getTokens(String zeichenkette, String trennzeichen, boolean inklusiveTrennzeichen) {
		Collection<String> tokens = new ArrayList<String>();

		StringTokenizer tokenizer = new StringTokenizer(zeichenkette, trennzeichen, inklusiveTrennzeichen);

		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}

		return tokens;
	}

	/**
	 * Fügt die Strings aus einer Collection zu einem Array des Typs String zusammen.
	 *
	 * @param strings
	 * @return String-Array
	 */
	public static String[] getStringArray(Collection<String> strings) {
		String[] stringArray = new String[strings.size()];

		Iterator<String> stringsIt = strings.iterator();
		for (int i = 0; stringsIt.hasNext(); i++) {
			stringArray[i] = stringsIt.next();
		}

		return stringArray;
	}

	/**
	 * Prüft, ob die übergebene Zeichenkette mindestens ein Zeichen enthält. Bei der Prüfung werden nicht sichtbare Zeichen (Leerzeichen, Tab, etc.) nicht beachtet.
	 *
	 * @param string
	 * @return TRUE, wenn der String nicht NULL ist und mindestens ein (sichtbares) Zeichen enthält; anderenfalls FALSE
	 * @see StringUtil#isStringNullOderLeer(String)
	 */
	public static boolean isStringNichtLeer(String string) {
		return !isStringNullOderLeer(string);
	}

	/**
	 * Prüft, ob der übergebene String NULL ist oder eine leere Zeichenkette repräsentiert. Bei der Prüfung werden nicht sichtbare Zeichen (Leerzeichen, Tab, etc.) nicht beachtet.
	 *
	 * @param string
	 * @return TRUE, wenn der String NULL ist oder kein (sichtbares) Zeichen enthält; anderenfalls FALSE
	 * @see StringUtil#isStringNichtLeer(String)
	 */
	public static boolean isStringNullOderLeer(String string) {
		return string == null || "".equals(string.trim());
	}
}