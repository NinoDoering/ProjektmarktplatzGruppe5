package de.hdm.itprojekt.shared.bo;

public class Team extends Organisationseinheit {

<<<<<<< HEAD
	private String idTeam;
	private int mitgliederAnzahl=0;
	private String teamName;
=======
	private int id;
	private String teamName;
	private int mitgliederAnzahl=0;  // ?
	
>>>>>>> branch 'master' of https://github.com/NinoDoering/ProjektmarktplatzGruppe5.git
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
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
				+ this.idTeam +" "
				+ this.mitgliederAnzahl; 
		
		//Test2
}}
