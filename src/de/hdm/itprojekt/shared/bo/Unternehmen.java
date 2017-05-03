package de.hdm.itprojekt.shared.bo;

public class Unternehmen extends Organisationseinheit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firmenName;
	private int idUnternehmen = 0;
	
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}
	
	public int getIdUnternehmen() {
		return idUnternehmen;
	}
	
	public String getFirmenName() {
		return firmenName;
	}

	public void setFirmenName(String firmenName) {
		this.firmenName = firmenName;
	}
	
	public String toString(){
		return super.toString()+" "
				+ this.idUnternehmen +" "
				+ this.firmenName;
	
}
}