package de.hdm.itprojekt.shared.bo;

public class Eigenschaft extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private String arbeitsgebiet;
	private String ausbildung;
	private String berufserfahrungsJahre;
	private String sprachkenntnisse;
	private String employmentStatus;
	private String abschluss;
	private int idPartnerprofil;
	

	public String getArbeitsgebiet() {
		return arbeitsgebiet;
	}



	public void setArbeitsgebiet(String arbeitsgebiet) {
		this.arbeitsgebiet = arbeitsgebiet;
	}



	public String getAusbildung() {
		return ausbildung;
	}



	public void setAusbildung(String ausbildung) {
		this.ausbildung = ausbildung;
	}



	public String getBerufserfahrungsJahre() {
		return berufserfahrungsJahre;
	}



	public void setBerufserfahrungsJahre(String berufserfahrungsJahre) {
		this.berufserfahrungsJahre = berufserfahrungsJahre;
	}



	public String getSprachkenntnisse() {
		return sprachkenntnisse;
	}



	public void setSprachkenntnisse(String sprachkenntnisse) {
		this.sprachkenntnisse = sprachkenntnisse;
	}



	public String getEmploymentStatus() {
		return employmentStatus;
	}



	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}



	public String getAbschluss() {
		return abschluss;
	}



	public void setAbschluss(String abschluss) {
		this.abschluss = abschluss;
	}



	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}



	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	
	@Override
	public String toString() {
		return "Eigenschaft [arbeitsgebiet=" + arbeitsgebiet + ", ausbildung=" + ausbildung + ", berufserfahrungsJahre="
				+ berufserfahrungsJahre + ", sprachkenntnisse=" + sprachkenntnisse + ", employmentStatus="
				+ employmentStatus + ", abschluss=" + abschluss + ", idPartnerprofil=" + idPartnerprofil + "]";
	}

	
	
	
}
	
	