package de.hdm.itprojekt.shared.bo;

public class Person extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private int idPerson; //Setter zu ID lassen wir weg
	private char geschlecht;
	private String vorname;
	private String nachname;
	
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
	
	public char getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
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
		+ this.geschlecht + " " 
		+  this.vorname + " " 
		+ this.nachname;
	
	}
}
