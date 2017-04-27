package de.hdm.itprojekt.shared.bo;

public class Eigenschaft extends BusinessObject {
	
	private int id = 0;
	private String arbeitsgebiet;
	private String ausbildung;
	private float berufserfahrungsJahre;
	private String sprachkenntnisse;
	private String employmentStatus;
	private String abschluss;
	private static final long serialVersionUID = 1L;
	
	public Eigenschaft(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public float getBerufserfahrungsJahre() {
		return berufserfahrungsJahre;
	}
	public void setBerufserfahrungsJahre(float berufserfahrungsJahre) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		return super.toString()+" "+
		this.id+ " "+
		this.arbeitsgebiet+ " "+
		this.ausbildung+ " "+
		this.abschluss+ " "+
		this.berufserfahrungsJahre+ " "+
		this.employmentStatus+ " "+
		this.sprachkenntnisse;
	}
	

}
