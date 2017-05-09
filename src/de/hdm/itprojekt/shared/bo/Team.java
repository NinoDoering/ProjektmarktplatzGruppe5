package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idTeam = 0;
	private String teamName;
	private int mitgliederAnzahl=0;

	
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}
	
	public int getIdTeam() {
		return idTeam;
	}

	public void setId(int idTeam) {
		this.idTeam = idTeam;
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
	
	public String toString(){
		return super.toString()	
				+ this.idTeam +", "
				+ this.mitgliederAnzahl + ", "
				+ this.teamName; 

}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}}
