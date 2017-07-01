package de.hdm.itprojekt.shared.bo;

public class Person extends Organisationseinheit{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Vorname der Person
	 */
	private String vorname;
	
	/**
	 * Nachname der Person
	 */
	private String nachname;
	
	/**
	 * Titel der Person
	 */
	private String titel;
	
	/**
	 * Fremdschlüsselbeziehung zum Team, dem die Person angehoert
	 */
	private Integer idTeam = null;
	
	/**
	 * Fremdschlüsselbeziehung zum Unternehmen, dem die Person angehoert
	 */
	private Integer idUnternehmen = null;
	
	/**
	 * E-Mail-Adresse der Person
	 */
	private String emailAddresse;
	
	/**
	 * Auslesen der E-Mail-Adresse der Person
	 * @return
	 */
	public String getEmailAddresse() {
		return emailAddresse;
	}

	/**
	 * Setzen der E-Mail-Adresse der Person
	 * @param emailAddresse
	 */
	public void setEmailAddresse(String emailAddresse) {
		this.emailAddresse = emailAddresse;
	}

	/**
	 * Auslesen der ID des Projekts
	 * @return getIdProjekt
	 */
	public int getIdProjekt(){
		return getIdProjekt();
	}
	
	/**
	 * Auslesen des Vornamens einer Person
	 * @return vorname
	 */
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Setzen des Vornamens der Person
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	/**
	 * Auslesen des Nachnamens der Person
	 * @return nachname
	 */
	public String getNachname() {
		return nachname;
	}
	
	/**
	 * Setzen des Nachnamens
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * Erzeugen einer textuellen Darstellung einer Person
	 */
	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", nachname=" + nachname + ", titel=" + titel + ", idTeam=" + idTeam
				+ ", idUnternehmen=" + idUnternehmen + "]";
	}


	/**
	 * Auslesen des Titels der Person
	 * @return titel
	 */
	public String getTitel() {
		return titel;
	}

	/**
	 * Setzen des Titels der Person
	 * @param titel
	 */
	public void setTitel(String titel) {
		this.titel = titel;
	}

	/**
	 * Auslesen des Fremdschlüssels des Teams, dem die Person angehoert
	 * @return idTeam
	 */
	public Integer getIdTeam() {
		return idTeam;
	}

	/**
	 * Setzen des Fremdschlüssels des Teams, dem die Person angehoert
	 * @param idTeam
	 */
	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}

	/**
	 * Auslesen des Fremdschlüssels des Unternehmens, dem die Person angehoert
	 * @return idUnternehmen
	 */
	public Integer getIdUnternehmen() {
		return idUnternehmen;
	}

	/**
	 * Setzen des Fremdschlüssels des Unternehmens, dem die Person angehoert
	 * @param idUnternehmen
	 */
	public void setIdUnternehmen(Integer idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}

}
