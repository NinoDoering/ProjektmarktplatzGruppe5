package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {

	private String teamName;
	private int mitgliederAnzahl=0;
	
	
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
	
	public String toString(){
		return super.toString()	
				+ this.teamName +" "
				+ this.mitgliederAnzahl; 
}}
