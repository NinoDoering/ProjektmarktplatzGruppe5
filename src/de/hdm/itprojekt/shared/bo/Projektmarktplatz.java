package de.hdm.itprojekt.shared.bo;

public class Projektmarktplatz {

	private int idProjektmarktplatz = 0;
	private static final long serialVersionUID = 1L;
	private String geschaeftsgebiet;
	private String bezeichnung;
	private Projekt projekt;
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public int getIdProjekt(){
		return getIdProjekt();
	}

	public int getIdProjektmarktplatz() {
		return idProjektmarktplatz;
	}

	public void setIdProjektmarktplatz(int idProjektmarktplatz) {
		this.idProjektmarktplatz = idProjektmarktplatz;
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
		return super.toString()+ " "
		+ this.idProjektmarktplatz +" "
		+ this.geschaeftsgebiet + " " 
		+ this.projekt; 

	}
	
}