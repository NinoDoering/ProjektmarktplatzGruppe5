
package de.hdm.itprojekt.shared.bo;

//test

public class Beteiligung extends BusinessObject {
	
	/**
	 * 
	 */
	
	private static final long serialVersionIUD = 1L;
	private int idBeteiligung; 
	private int idProjekt;
	private int idPerson;
	
	public int getIdBewertung(){
		return getIdBewertung();
	}
	
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}
	
	public int getIdBeteiligung() {
		return idBeteiligung;
	}
	

	public void setIdBeteiligung(int idBeteiligung) {
		this.idBeteiligung = idBeteiligung;
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
		return super.toString() + " "
		+ this.idPerson + " "
		+ this.idProjekt ;
	}

	public static long getSerialversioniud() {
		return serialVersionIUD;
	}
	
}

