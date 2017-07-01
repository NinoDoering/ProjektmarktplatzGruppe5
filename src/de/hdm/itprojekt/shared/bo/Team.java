package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {


	private static final long serialVersionUID = 1L;
	
	/**
	 * Name des Teams
	 */
	private String teamName;
	
	/**
	 * Anzahl der Mitglieder des Teams
	 */
	private int mitgliederAnzahl= 0;
	
	/**
	 * Fremdschlüsselbeziehung zum Unternehmen, dem das Team angehoert
	 */
	private int idUnternehmen = 0;
	
	/**
	 * Auslesen der Fremdschlüssels des Unternehmen
	 * @return idUnternehmen
	 */
	public int getIdUnternehmen() {
		return idUnternehmen;
	}
	
	/**
	 * Setzen des Fremdschlüssels des Unternehmens
	 * @param idUnternehmen
	 */
	public void setIdUnternehmen(int idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}
	
	/**
	 * Auslesen des Namens des Teams
	 * @return teamName
	 */
	public String getTeamName() {
		return teamName;
	}
	
	/**
	 * Setzen des Namens des Teams
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * Auslesen der Mitgliederanzahl des Teams
	 * @return mitgliederAnzahl
	 */
	public int getMitgliederAnzahl() {
		return mitgliederAnzahl;
	}
	
	/**
	 * Setzen der Mitgliederanzahl
	 * @param mitgliederAnzahl
	 */
	public void setMitgliederAnzahl(int mitgliederAnzahl) {
		this.mitgliederAnzahl = mitgliederAnzahl;
	}
	
	/**
	 * Erzeugen einer textuellen Darstellung des Teams
	 */
	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", mitgliederAnzahl=" + mitgliederAnzahl + ", idUnternehmen="
				+ idUnternehmen + "]";
	}
	}
