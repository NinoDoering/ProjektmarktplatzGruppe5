package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {

	private String idTeam;
	private int mitgliederAnzahl=0;
	private String teamName;

				
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getIdTeamName() {
		return idTeam;
	}
	public void setTeam(String idTeam) {
		this.idTeam = idTeam;
	}
	public int getMitgliederAnzahl() {
		return mitgliederAnzahl;
	}
	public void setMitgliederAnzahl(int mitgliederAnzahl) {
		this.mitgliederAnzahl = mitgliederAnzahl;
	}
	
	public String toString(){
		return super.toString()	
				+ this.idTeam +", "
				+ this.mitgliederAnzahl + ", "
				+ this.teamName; 
		
	
}
	public String getIdTeam() {
		return idTeam;
	}
	public void setIdTeam(String idTeam) {
		this.idTeam = idTeam;
	}}
