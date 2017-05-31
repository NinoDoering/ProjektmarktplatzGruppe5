package de.hdm.itprojekt.shared.bo;

public class Marktplatz extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private String geschaeftsgebiet;
	private String bezeichnung;
	
	
	public String getGeschaeftsgebiet() {
		return geschaeftsgebiet;
	}
	public void setGeschaeftsgebiet(String geschaeftsgebiet) {
		this.geschaeftsgebiet = geschaeftsgebiet;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	
	@Override
	public String toString() {
		return "Marktplatz [geschaeftsgebiet=" + geschaeftsgebiet + ", bezeichnung=" + bezeichnung + "]";
	}
	
	
}