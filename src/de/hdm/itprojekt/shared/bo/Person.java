package de.hdm.itprojekt.shared.bo;

public class Person extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private int id; //Setter zu ID lassen wir weg
	private char geschlecht;
	private String vorname;
	private String nachname;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	// muss vervollständigt werden
	public String toString(){
		return super.toString() + this.id; 
	
}}
