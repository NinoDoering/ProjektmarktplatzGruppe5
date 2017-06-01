package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Projekt extends BusinessObject{

	private static final long serialVersionUID = 1L;
	private String bezeichnung;
	private String beschreibung;
	private Date startDatum;
	private Date endDatum;
	private int idPerson;
	private int idMarktplatz;
	
	
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
	public int getIdMarktplatz() {
		return idMarktplatz;
	}
	public void setIdMarktplatz(int idMarktplatz) {
		this.idMarktplatz = idMarktplatz;
	}
	@Override
	public String toString() {
		return "Projekt [bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", startDatum=" + startDatum
				+ ", endDatum=" + endDatum + ", idPerson=" + idPerson + ", idMarktplatz=" + idMarktplatz + "]";
	}
	
	
	

}
