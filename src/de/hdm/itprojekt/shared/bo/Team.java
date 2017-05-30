package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {


	private static final long serialVersionUID = 1L;
	
	private String teamName;
	private int mitgliederAnzahl= 0;
	private int idUnternehmen = 0;
	
	
	public int getIdUnternehmen() {
		return idUnternehmen;
	}
	public void setIdUnternehmen(int idUnternehmen) {
		this.idUnternehmen = idUnternehmen;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public int getMitgliederAnzahl() {
		return mitgliederAnzahl;
	}
	public void setMitgliederAnzahl(int mitgliederAnzahl) {
		this.mitgliederAnzahl = mitgliederAnzahl;
	}
	
	@Override
	public String toString() {
		return "Team [teamName=" + teamName + ", mitgliederAnzahl=" + mitgliederAnzahl + ", idUnternehmen="
				+ idUnternehmen + "]";
	}
	}
