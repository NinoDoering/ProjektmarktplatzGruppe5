package de.hdm.itprojekt.shared.bo;

public class Unternehmen extends Organisationseinheit {

	//
	
	private static final long serialVersionUID = 1L;
	private String firmenName;
	
	public String getFirmenName() {
		return firmenName;
	}

	public void setFirmenName(String firmenName) {
		this.firmenName = firmenName;
	}
  
	@Override
	public String toString() {
		return "Unternehmen [firmenName=" + firmenName + "]";
	}
	

}