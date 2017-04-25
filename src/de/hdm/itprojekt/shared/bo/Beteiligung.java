
package de.hdm.itprojekt.shared.bo;

//test

public class Beteiligung extends BusinessObject {
	
	private static final long serialVersionIUD = 1L;
	
	private int id; 
	
	private int idProjekt; 
	
	private int idPerson;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProjekt() {
		return idProjekt;
	}

	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	} 
	

	public String toString() { 
		return super.toString() + " "+ this.idPerson + " "+ this.idProjekt ;
	}
	
}

