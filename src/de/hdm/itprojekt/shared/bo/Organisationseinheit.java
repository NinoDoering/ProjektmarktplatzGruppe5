package de.hdm.itprojekt.shared.bo;

public class Organisationseinheit extends BusinessObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Adresse der Organisationseinheit
	 */
	private String adresse;
	
	/**
	 * Standort der Organisationseinheit
	 */
	private String standort;
	
	/**
	 * Fremdschlüsselbeziehung zum zugehoerigem Partnerprofil
	 */
	private Integer idPartnerprofil = 0;

	/**
	 * Auslesen der Adresse der Organisationseinheit
	 * @return adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Setzen der Adresse der Organisationseinheit
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Auslesen des Standorts der Organisationseinheit
	 * @return standort
	 */
	public String getStandort() {
		return standort;
	}

	/**
	 * Setzen des Standorts der Organisationseinheit
	 * @param standort
	 */
	public void setStandort(String standort) {
		this.standort = standort;
	}

	/**
	 * Auslesen des Fremdschlüssels des zugehoerigen Partnerprofils
	 * @return idPartnerprofil
	 */
	public Integer getIdPartnerprofil() {
		return idPartnerprofil;
	}

	/**
	 * Setzen des Fremdschlüssels des zugehoerigen Partnerprofils
	 * @param idPartnerprofil
	 */
	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	/**
	 * Erzeugen einer textuellen Darstellung einer Organisationseinheit
	 */
	@Override
	public String toString() {
		return "Organisationseinheit [adresse=" + adresse + ", Standort=" + standort + ", idPartnerprofil="
				+ idPartnerprofil + "]";
	}
}
