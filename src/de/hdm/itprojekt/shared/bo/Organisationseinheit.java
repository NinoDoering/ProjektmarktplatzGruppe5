package de.hdm.itprojekt.shared.bo;

public class Organisationseinheit extends BusinessObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String adresse;
	
	private String standort;
	
	
	private int idPartnerprofil;


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getStandort() {
		return standort;
	}


	public void setStandort(String standort) {
		this.standort = standort;
	}


	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}


	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}


	@Override
	public String toString() {
		return "Organisationseinheit [adresse=" + adresse + ", Standort=" + standort + ", idPartnerprofil="
				+ idPartnerprofil + "]";
	}

	
	
}
