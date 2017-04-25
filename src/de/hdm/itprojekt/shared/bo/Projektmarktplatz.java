package de.hdm.itprojekt.shared.bo;

public class Projektmarktplatz {

	private int id = 0;
	
	private static final long serialVersionUID = 1L;
	
	private String geschäftsgebiet;
	
	private Projekt projekt;

	public int getId() {
		return id;
	}

	public String getGeschäftsgebiet() {
		return geschäftsgebiet;
	}

	public void setGeschäftsgebiet(String geschäftsgebiet) {
		this.geschäftsgebiet = geschäftsgebiet;
	}
	
	public String toString(){
		return super.toString()	
				+ this.id +" "
				+ this.geschäftsgebiet; 
	}

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}
	
		
	
}
