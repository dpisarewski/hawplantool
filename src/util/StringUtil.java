package de.dueddel.hawplantool.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Die Klasse <code>StringUtil</code> bietet Hilfsmethoden f�r den Umgang mit Zeichenketten.
 */
public class StringUtil {

	/**
	 * Zerlegt einen String anhand von Trennezichen in Teilstrings und gibt diese in einer Collection zur�ck (wahlweise mit/ohne Trennzeichen).
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
	 * F�gt die Strings aus einer Collection zu einem Array des Typs String zusammen.
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
	 * Pr�ft, ob die �bergebene Zeichenkette mindestens ein Zeichen enth�lt. Bei der Pr�fung werden nicht sichtbare Zeichen (Leerzeichen, Tab, etc.) nicht beachtet.
	 *
	 * @param string
	 * @return TRUE, wenn der String nicht NULL ist und mindestens ein (sichtbares) Zeichen enth�lt; anderenfalls FALSE
	 * @see StringUtil#isStringNullOderLeer(String)
	 */
	public static boolean isStringNichtLeer(String string) {
		return !isStringNullOderLeer(string);
	}

	/**
	 * Pr�ft, ob der �bergebene String NULL ist oder eine leere Zeichenkette repr�sentiert. Bei der Pr�fung werden nicht sichtbare Zeichen (Leerzeichen, Tab, etc.) nicht beachtet.
	 *
	 * @param string
	 * @return TRUE, wenn der String NULL ist oder kein (sichtbares) Zeichen enth�lt; anderenfalls FALSE
	 * @see StringUtil#isStringNichtLeer(String)
	 */
	public static boolean isStringNullOderLeer(String string) {
		return string == null || "".equals(string.trim());
	}
}