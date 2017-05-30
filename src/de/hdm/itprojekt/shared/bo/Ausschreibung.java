package de.hdm.itprojekt.shared.bo;

import java.util.*;
//test


public class Ausschreibung extends BusinessObject  {
	
	
	private static final long serialVersionUID = 1L; 
	private String bezeichnung; 
	private Date endDatum;
	private String beschreibung;
	private int idProjekt;
	private int idPartnerprofil;
	private int idAusschreibender;
    public enum Status {besetzt, abgebrochen, laufend};
	private Status ausschreibungsstatus = Status.laufend;
    
    public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}

	public int getIdProjekt(){
		return idProjekt;
	}
	
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}

	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	public int getIdAusschreibender() {
		return idAusschreibender;
	}

	public void setIdAusschreibender(int idAusschreibender) {
		this.idAusschreibender = idAusschreibender;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	} 
	
	public Status getAusschreibungsstatus(){
		return ausschreibungsstatus;
	}
	
	public void setAusschreibungsstatus(Status ausschreibungsstatus){
		this.ausschreibungsstatus = ausschreibungsstatus;
	}

	@Override
	public String toString() {
		return "Ausschreibung [bezeichnung=" + bezeichnung + ", endDatum=" + endDatum + ", beschreibung=" + beschreibung
				+ ", idProjekt=" + idProjekt + ", idPartnerprofil=" + idPartnerprofil + ", idAusschreibender="
				+ idAusschreibender + ", ausschreibungsstatus=" + ausschreibungsstatus + "]";
	}
	
	

}
