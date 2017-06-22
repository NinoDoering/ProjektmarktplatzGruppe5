package de.hdm.itprojekt.shared.bo;

public class Person extends Organisationseinheit{

	private static final long serialVersionUID = 1L;
	
	
	private String vorname;
	private String nachname;
	private String titel;
	private Integer idTeam = null;
	private Integer idUnternehmen = null;
	private String emailAddresse;
	
	public String getEmailAddresse() {
		return emailAddresse;
	}


	

	public void setEmailAddresse(String emailAddresse) {
		this.emailAddresse = emailAddresse;
	}


	public int getIdProjekt(){
		return getIdProjekt();
	}
	 

	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	

	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", nachname=" + nachname + ", titel=" + titel + ", idTeam=" + idTeam
				+ ", idUnternehmen=" + idUnternehmen + "]";
	}


	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}


	public Integer getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(Integer idTeam) {
		this.idTeam = idTeam;
	}


	public Integer getIdUnternehmen() {
		return idUnternehmen;
	}


	public void setIdUnternehmen(Integer idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}

}
