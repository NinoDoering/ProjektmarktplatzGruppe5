
package de.hdm.itprojekt.shared.bo;

import java.util.Date;
import java.sql.*;

public class Beteiligung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Zeitraum einer Beteiligung
	 */
	private int beteiligungszeit;
	
	/*
	 * Beziehung zu Beteiligtem über Fremdschlüssel
	 */
	private int idBeteiligter;
	
	/*
	 * Beziehung zu Projekt über Fremdschlüssel
	 */
	private int idProjekt;
	
	/*
	 * Beziehung zu Bewertung über Fremdschlüssel
	 */
	private int idBewertung;
	
	/*
	 * Auslesen der Beteiligungszeit
	 * @return beteiligungszeit	
	 */
	public int getBeteiligungszeit() {
		return beteiligungszeit;
	}
	
	/*
	 * Festlegen der Beteiligungszeit
	 * @param i
	 */
	public void setBeteiligungszeit(int i) {
		this.beteiligungszeit = i;
	}
	
	/*
	 * Auslesen der Id des Beteiligten
	 * @return idBeteiligter
	 */
	public int getIdBeteiligter() {
		return idBeteiligter;
	}
	
	/*
	 * Festlegen der Id des Beteiligten
	 * @param idBeteiligter
	 */
	public void setIdBeteiligter(int idBeteiligter) {
		this.idBeteiligter = idBeteiligter;
	}
	
	/*
	 * Auslesen der Id des Projekts
	 * @param idProjekt
	 */
	public int getIdProjekt() {
		return idProjekt;
	}
	
	/*
	 * Festlegen der Id des Projekts
	 * @param idProjekt
	 */
	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}
	
	/*
	 * Auslesen der Id der zugehoerigen Bewertung
	 * @return idBewertung
	 */
	public int getIdBewertung() {
		return idBewertung;
	}
	
	/*
	 * Festlegen der Bewertungs-Id
	 * @param idBewertung
	 */
	public void setIdBewertung(int idBewertung) {
		this.idBewertung = idBewertung;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.hdm.itprojekt.shared.bo.BusinessObject#toString()
	 */
	@Override
	public String toString() {
		return "Beteiligung [beteiligungszeit=" + beteiligungszeit + ", idBeteiligter=" + idBeteiligter + ", idProjekt="
				+ idProjekt + ", idBewertung=" + idBewertung + "]";
	}	
}

