package de.dueddel.hawplantool.verarbeitung.daten;

import java.util.Date;

/**
 * <code>VeranstaltungsTermin_Impl</code>
 */
public class VeranstaltungsTermin_Impl implements VeranstaltungsTermin {

	private String name;
	private String prof;
	private String ort;
	private Date beginn;
	private Date ende;
	private String semestergruppe;
	private String kategorie;

	/**
	 * Konstruktor. Erzeugt eine Instanz der Klasse <code>VeranstaltungsTermin</code>.
	 */
	public VeranstaltungsTermin_Impl() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Date getBeginn() {
		return beginn;
	}

	public long getBeginnLong() {
		return getBeginn().getTime();
	}

	public void setBeginn(Date beginn) {
		this.beginn = beginn;
	}

	public Date getEnde() {
		return ende;
	}

	public long getEndeLong() {
		return getEnde().getTime();
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	public String getSemestergruppe() {
		return semestergruppe;
	}

	public void setSemestergruppe(String semestergruppe) {
		this.semestergruppe = semestergruppe;
	}

	public String getKategorie() {
		return kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getUid() {
		return toString().replaceAll(" ", "");
	}

	@Override
	public String toString() {
		return getName() + " " + getProf() + " " + getOrt() + " " + getBeginnLong() + " " + getEndeLong();
	}

	public int compareTo(Object o) {
		if (o instanceof VeranstaltungsTermin) {
			VeranstaltungsTermin t = (VeranstaltungsTermin) o;
//			Vergleich mit Beginn
			if (!getBeginn().equals(t.getBeginnLong())) {
				return getBeginn().compareTo(t.getBeginn());
			}
//			Vergleich mit Namen
			if (!getName().equals(t.getName())) {
				return getName().compareTo(t.getName());
			}
//			Vergleich mit Ort
			if (!getOrt().equals(t.getOrt())) {
				return getOrt().compareTo(t.getOrt());
			}
//			Vergleich mit toString
			return toString().compareTo(t.toString());
		}
		return 0;
	}
}