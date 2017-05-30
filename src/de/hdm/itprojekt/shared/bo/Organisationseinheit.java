package de.hdm.itprojekt.shared.bo;

public class Organisationseinheit extends BusinessObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String adresse;
	
	private String Standort;
	
	
	private int idPartnerprofil;


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getStandort() {
		return Standort;
	}


	public void setStandort(String standort) {
		Standort = standort;
	}


	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}


	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}


	@Override
	public String toString() {
		return "Organisationseinheit [adresse=" + adresse + ", Standort=" + Standort + ", idPartnerprofil="
				+ idPartnerprofil + "]";
	}

	
	
}
