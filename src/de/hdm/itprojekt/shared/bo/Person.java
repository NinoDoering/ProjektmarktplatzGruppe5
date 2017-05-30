package de.hdm.itprojekt.shared.bo;

public class Person extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String nachname;
	private String titel;
	private int idTeam;
	private int idUnternehmen;
	 
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


	public int getIdTeam() {
		return idTeam;
	}


	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}


	public int getIdUnternehmen() {
		return idUnternehmen;
	}


	public void setIdUnternehmen(int idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}

}
