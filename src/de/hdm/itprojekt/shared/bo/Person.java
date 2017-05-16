package de.hdm.itprojekt.shared.bo;

public class Person extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private int idPerson; //Setter zu ID lassen wir weg
	private String vorname;
	private String nachname;
	private String titel;
	
	public int getIdProjekt(){
		return getIdProjekt();
	}
	
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}
	
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
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

	public String toString(){
		return super.toString() +" "
		+ this.idPerson + " " 
		+ this.titel + " "
		+ this.vorname + " " 
		+ this.nachname;
	
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
}
