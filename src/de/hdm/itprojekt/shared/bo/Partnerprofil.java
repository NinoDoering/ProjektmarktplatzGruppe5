package de.hdm.itprojekt.shared.bo;

public class Partnerprofil extends BusinessObject {
	
	private static final long serialVersionUID = 1L; //?
	private int idPartnerprofil;
	
	public int getIdAusschreibung(){
		return getIdAusschreibung();
	}
	
	public int getIdBewerbung(){
		return getIdBewerbung();
	}
	
	public int getIdEigenschaft(){
		return getIdEigenschaft();
	}
	
	public int getIdOrganisationseinheit(){
		return getIdOrganisationseinheit();
	}

	public int getIdPartnerporfil() {
		return idPartnerprofil;
	}

	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	public String toString(){
		return super.toString() +" "
		+ this.idPartnerprofil;
	}
}
