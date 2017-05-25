
package de.hdm.itprojekt.shared.bo;


public class Beteiligung extends BusinessObject {
	
	/**
	 * 
	 */
	
	private static final long serialVersionIUD = 1L;
	private int idBeteiligung; 
	private int idProjekt;
	private int idBewerbung;
	private int idBewertung;
	
	
	public void setIdBewertung(int idBewertung){
		this.idBewertung=idBewertung;
	}
	
	public int getIdBewertung(){
		return idBewertung;
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

	public int getIdBewerbung() {
		return idBewerbung;
	}

	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	} 
	

	public String toString() { 
		return super.toString() + " "
		+ this.idBewerbung + " "
		+ this.idProjekt ;
	}

	public static long getSerialversioniud() {
		return serialVersionIUD;
	}
	
}

