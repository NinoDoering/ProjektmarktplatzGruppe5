package de.hdm.itprojekt.shared.bo;

public class Projektmarktplatz {

	private int id = 0;
	
	private static final long serialVersionUID = 1L;
	
	private String geschaeftsgebiet;
	
	private Projekt projekt;

	public int getId() {
		return id;
	}

	public String getGeschaeftsgebiet() {
		return geschaeftsgebiet;
	}

	public void setGeschaeftsgebiet(String geschaeftsgebiet) {
		this.geschaeftsgebiet = geschaeftsgebiet;
	}
	

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}
	
	public String toString(){
		return super.toString()	
				+ this.id +" "
				+ this.geschaeftsgebiet + " " + this.projekt; 
	}
	
}
