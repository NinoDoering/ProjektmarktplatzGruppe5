
package de.hdm.itprojekt.shared.bo;


public class Beteiligung extends BusinessObject {
	
	
	private static final long serialVersionIUD = 1L;
	private int beteiligungszeit = 0;
	private int idBeteiligter;
	private int idProjekt;
	private int idBewertung;
	
	
	
	public int getBeteiligungszeit() {
		return beteiligungszeit;
	}
	public void setBeteiligungszeit(int beteiligungszeit) {
		this.beteiligungszeit = beteiligungszeit;
	}
	public int getIdBeteiligter() {
		return idBeteiligter;
	}
	public void setIdBeteiligter(int idBeteiligter) {
		this.idBeteiligter = idBeteiligter;
	}
	public int getIdProjekt() {
		return idProjekt;
	}
	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}
	public int getIdBewertung() {
		return idBewertung;
	}
	public void setIdBewertung(int idBewertung) {
		this.idBewertung = idBewertung;
	}
	
	@Override
	public String toString() {
		return "Beteiligung [beteiligungszeit=" + beteiligungszeit + ", idBeteiligter=" + idBeteiligter + ", idProjekt="
				+ idProjekt + ", idBewertung=" + idBewertung + "]";
	}
	
	
	
}

