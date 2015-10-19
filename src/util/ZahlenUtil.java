package de.dueddel.hawplantool.util;

/**
 * Die Klasse <code>ZahlenUtil</code> bietet Hilfsmethoden für den Umgang mit Zahlen.
 */
public class ZahlenUtil {

	/**
	 * Rundet die übergebene Zahl auf eine der übergebenen Genauigkeit entsprechende Zahl auf (rundet niemals ab!).
	 * <br/><br/>
	 * Bsp.: Bei den Parametern zahl=202 und genauigkeit=10 würde 210 zurückgegeben werden.
	 *
	 * @param zahl
	 * @param genauigkeit
	 * @return int
	 */
	public static int rundeAuf(int zahl, int genauigkeit) {
		return zahl % genauigkeit == 0 ? zahl : (zahl + (genauigkeit - zahl % genauigkeit));
	}
}