package de.hdm.itprojekt.shared.bo;

public class Unternehmen extends Organisationseinheit {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name des Unternehmens
	 */
	private String firmenName;
	
	/**
	 * Auslesen des Firmennamens
	 * @return firmenName
	 */
	public String getFirmenName() {
		return firmenName;
	}

	/**
	 * Setzen des Firmennamens
	 * @param firmenName
	 */
	public void setFirmenName(String firmenName) {
		this.firmenName = firmenName;
	}
  
	/**
	 * Erzeugen einer textuellen Darstellung eines Unternehmens
	 */
	@Override
	public String toString() {
		return "Unternehmen [firmenName=" + firmenName + "]";
	}
	

}