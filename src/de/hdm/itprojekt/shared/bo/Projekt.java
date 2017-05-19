package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Projekt extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private int idProjekt;
	private String bezeichnung;
	private String beschreibung;
	private Date startDatum;
	private Date endDatum;
	private int idPerson;
	
	public int getIdAusschreibung(){
		return getIdAusschreibung();
	}
	
	public int getIdBeteiligung(){
		return getIdBeteiligung();	
	}
	
	public int getIdProjekt() {
		return idProjekt;
	}

	public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public Date getEndDatum() {
		return endDatum;
	}

	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	
	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String toString() {
		return super.toString() +" "
		+ this.idProjekt + " " 
		+ this.bezeichnung + " " 
		+ this.beschreibung + " " 
		+ this.startDatum + " " 
		+ this.endDatum + " "
		+ this.idPerson;
	}

	
	// toString vervollstaending 
	

}
