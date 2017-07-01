package de.hdm.itprojekt.shared.bo;

public class Partnerprofil extends BusinessObject {
	
	private static final long serialVersionUID = 1L; //?
	
	/**
	 * Null-Konstruktor zu Partnerprofil
	 */
	public Partnerprofil() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * Auslesen des Fremdschlüssels der Ausschreibung
	 * @return getIdAusschreibung
	 */
	public int getIdAusschreibung(){
		return getIdAusschreibung();
	}
	
	/**
	 * Auslesen des Fremdschlüssels der Bewerbung
	 * @return getIdBewerbung
	 */
	public int getIdBewerbung(){
		return getIdBewerbung();
	}
	
	/**
	 * Auslesen des Fremdschlüssels der Eigenschaft
	 * @return getIdEigenschaft
	 */
	public int getIdEigenschaft(){
		return getIdEigenschaft();
	}
	
	/**
	 * Auslesen des Fremdschlüssels der Organisationseinheit
	 * @return getIdOrganisationseinheit
	 */
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}

	/**
	 * Erzeugen einer textuellen Darstellung eines Partnerprofils
	 */
	@Override
	public String toString() {
		return "Partnerprofil []";
	}

	
	
}
