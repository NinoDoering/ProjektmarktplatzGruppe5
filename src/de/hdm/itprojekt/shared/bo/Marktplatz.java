package de.hdm.itprojekt.shared.bo;

public class Marktplatz {

	private int idMarktplatz = 0;
	private static final long serialVersionUID = 1L;
	private String geschaeftsgebiet;
	private String bezeichnung;
	private int idProjekt;
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
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

	public int getIdProjekt() {
		return idProjekt;
	}

	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}

	public String toString(){
		return super.toString()+ " "
		+ this.idMarktplatz +" "
		+ this.geschaeftsgebiet + " "
		+ this.bezeichnung; 
		
	}
	
}
