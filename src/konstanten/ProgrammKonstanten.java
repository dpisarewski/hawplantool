package de.dueddel.hawplantool.konstanten;

import java.io.File;

/**
 * <code>ProgrammKonstanten</code>.
 */
public interface ProgrammKonstanten {

	public static final File PROGRAMM_VERZEICHNIS = new File(System.getProperty("user.dir"));
	public static final String NAME_LIB_VERZEICHNIS = "lib";
	public static final File PROGRAMM_LIB_VERZEICHNIS = new File(PROGRAMM_VERZEICHNIS, NAME_LIB_VERZEICHNIS);
}