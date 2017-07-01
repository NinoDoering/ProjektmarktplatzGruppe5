package de.hdm.itprojekt.shared.bo;

public class Marktplatz extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Geschaeftsgebiet des Projektmarktplatzes
	 */
	private String geschaeftsgebiet;
	
	/**
	 * Bezeichnung des Projektmarktplatzes
	 */
	private String bezeichnung;
	
	/**
	 * Auslesen des Geschaeftsgebiets des Projektmarktplatzes
	 * @return geschaeftsgebiet
	 */
	public String getGeschaeftsgebiet() {
		return geschaeftsgebiet;
	}
	
	/**
	 * Setzen des Geschaeftsgebietes des Projektmarktplatzes
	 * @param geschaeftsgebiet
	 */
	public void setGeschaeftsgebiet(String geschaeftsgebiet) {
		this.geschaeftsgebiet = geschaeftsgebiet;
	}
	
	/**
	 * Auslesen der Bezeichnung des Projektmarktplatzes
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/**
	 * Setzen der Bezeichnung des Projektmarktplatzes
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * Erzeugen einer textuellen Darstellung eines Marktplatzes
	 */
	@Override
	public String toString() {
		return "Marktplatz [geschaeftsgebiet=" + geschaeftsgebiet + ", bezeichnung=" + bezeichnung + "]";
	}
	
	
}