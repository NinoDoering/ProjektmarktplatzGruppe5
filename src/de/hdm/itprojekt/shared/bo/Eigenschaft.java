package de.hdm.itprojekt.shared.bo;

public class Eigenschaft extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Eigenschaft Arbeitsgebiet
	 */
	private String arbeitsgebiet;
	
	/**
	 * Eigenschaft Ausbildung
	 */
	private String ausbildung;
	
	/**
	 * Eigenschaft Berufserfahrungsjahre
	 */
	private String berufserfahrungsJahre;
	
	/**
	 * Eigenschaft Sprachkenntnisse
	 */
	private String sprachkenntnisse;
	
	/**
	 * Eigenschaft Status, der anzeigt, ob eine Organisationseinheit (Person, Team, Unternehmen) angestellt ist.
	 */
	private String employmentStatus;
	
	/**
	 * Eigenschaft Abschluss, die den Schulabschluss angibt
	 */
	private String abschluss;
	
	/**
	 * Fremdschlüsselbeziehung zum Partnerprofil
	 */
	private int idPartnerprofil;
	

	/**
	 * Ausgeben des Arbeitsgebiets
	 * @return arbeitsgebiet
	 */
	public String getArbeitsgebiet() {
		return arbeitsgebiet;
	}

	/**
	 * Setzen des Arbeitsgebiets
	 * @param arbeitsgebiet
	 */
	public void setArbeitsgebiet(String arbeitsgebiet) {
		this.arbeitsgebiet = arbeitsgebiet;
	}

	/**
	 * Ausgeben der Ausbildung
	 * @return ausbildung
	 */
	public String getAusbildung() {
		return ausbildung;
	}

	/**
	 * Setzen der Ausbildung
	 * @param ausbildung
	 */
	public void setAusbildung(String ausbildung) {
		this.ausbildung = ausbildung;
	}

	/**
	 * Ausgeben der Berufserfahrungsjahre
	 * @return berufserfahrungsJahre
	 */
	public String getBerufserfahrungsJahre() {
		return berufserfahrungsJahre;
	}

	/**
	 * Setzen der Berufserfahrungsjahre
	 * @param berufserfahrungsJahre
	 */
	public void setBerufserfahrungsJahre(String berufserfahrungsJahre) {
		this.berufserfahrungsJahre = berufserfahrungsJahre;
	}

	/**
	 * Ausgeben der Sprachkenntnisse
	 * @return sprachkenntnisse
	 */
	public String getSprachkenntnisse() {
		return sprachkenntnisse;
	}

	/**
	 * Setzen der Sprachkenntnisse
	 * @param sprachkenntnisse
	 */
	public void setSprachkenntnisse(String sprachkenntnisse) {
		this.sprachkenntnisse = sprachkenntnisse;
	}

	/**
	 * Ausgeben des Status, ob eine Organisationseinheit (Person, Team, Unternehmen) angestellt ist
	 * @return employmentStatus
	 */
	public String getEmploymentStatus() {
		return employmentStatus;
	}

	/**
	 * Setzen des Status, ob eine Organisationseinheit (Person, Team, Unternehmen) angestellt ist
	 * @param employmentStatus
	 */
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}


	/**
	 * Ausgeben des Abschlusses
	 * @return abschluss
	 */
	public String getAbschluss() {
		return abschluss;
	}

	/**
	 * Setzen des Abschlusses
	 * @param abschluss
	 */
	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}

	/**
	 * Ausgeben der ID vom Partnerprofil
	 * @return idPartnerprofil
	 */
	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}

	/**
	 * Setzen der ID des Partnerprofils
	 * @param idPartnerprofil
	 */
	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	/**
	 * Erzeugen einer textuellen Darstellung einer Eigenschaft
	 */
	@Override
	public String toString() {
		return "Eigenschaft [arbeitsgebiet=" + arbeitsgebiet + ", ausbildung=" + ausbildung + ", berufserfahrungsJahre="
				+ berufserfahrungsJahre + ", sprachkenntnisse=" + sprachkenntnisse + ", employmentStatus="
				+ employmentStatus + ", abschluss=" + abschluss + ", idPartnerprofil=" + idPartnerprofil + "]";
	}

	
	
	
}
	
	