package de.hdm.itprojekt.shared.bo;

public class Projektmarktplatz {

	private int id = 0;
	
	private static final long serialVersionUID = 1L;
	
	private String gesch�ftsgebiet;
	
	private Projekt projekt;

	public int getId() {
		return id;
	}

	public String getGesch�ftsgebiet() {
		return gesch�ftsgebiet;
	}

	public void setGesch�ftsgebiet(String gesch�ftsgebiet) {
		this.gesch�ftsgebiet = gesch�ftsgebiet;
	}
	
	public String toString(){
		return super.toString()	
				+ this.id +" "
				+ this.gesch�ftsgebiet; 
	}

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}
	
		
	
}
