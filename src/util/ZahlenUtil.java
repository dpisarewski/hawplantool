package de.dueddel.hawplantool.util;

/**
 * Die Klasse <code>ZahlenUtil</code> bietet Hilfsmethoden f�r den Umgang mit Zahlen.
 */
public class ZahlenUtil {

	/**
	 * Rundet die �bergebene Zahl auf eine der �bergebenen Genauigkeit entsprechende Zahl auf (rundet niemals ab!).
	 * <br/><br/>
	 * Bsp.: Bei den Parametern zahl=202 und genauigkeit=10 w�rde 210 zur�ckgegeben werden.
	 *
	 * @param zahl
	 * @param genauigkeit
	 * @return int
	 */
	public static int rundeAuf(int zahl, int genauigkeit) {
		return zahl % genauigkeit == 0 ? zahl : (zahl + (genauigkeit - zahl % genauigkeit));
	}
}