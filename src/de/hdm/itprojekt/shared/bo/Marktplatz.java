package de.hdm.itprojekt.shared.bo;

public class Marktplatz extends BusinessObject {

	private int idMarktplatz = 0;
	private static final long serialVersionUID = 1L;
	private String geschaeftsgebiet;
	private String bezeichnung;
	
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public int getIdProjekt(){
		return getIdProjekt();
	}

	public int getIdMarktplatz() {
		return idMarktplatz;
	}

	public void setIdMarktplatz(int idMarktplatz) {
		this.idMarktplatz = idMarktplatz;
	}
		
	public String getGeschaeftsgebiet() {
		return geschaeftsgebiet;
	}

	public void setGeschaeftsgebiet(String geschaeftsgebiet) {
		this.geschaeftsgebiet = geschaeftsgebiet;
	}
/*
	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}


*/
	public String toString(){
		return super.toString()+ " "
		+ this.idMarktplatz +" "
		+ this.geschaeftsgebiet;
		

	}
	
}
