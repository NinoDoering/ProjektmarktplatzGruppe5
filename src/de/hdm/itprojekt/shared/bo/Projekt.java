package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Projekt extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Bezeichnung des Projekts
	 */
	private String bezeichnung;
	
	/**
	 * Beschreibung des Projekts
	 */
	private String beschreibung;
	
	/**
	 * Startdatum des Projekts
	 */
	private Date startDatum;
	
	/**
	 * Enddatum des Projekts
	 */
	private Date endDatum;
	
	/**
	 * Fremdschlüsselbeziehung zur Person, die an dem Projekt beteiligt ist.
	 */
	private int idPerson;
	
	/**
	 * Fremdschlüsselbeziehung zum Projektmarktplatz, auf dem das Projekt angelegt wurde
	 */
	private int idMarktplatz;
	
	/**
	 * Auslesen der Projektbezeichnung
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}
	
	/**
	 * Setzen der Projektbezeichnung
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/**
	 * Auslesen der Beschreibung des Projekts
	 * @return beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * Setzen der Beschreibung des Projekts
	 * @param beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Auslesen des Startdatums des Projekts
	 * @return
	 */
	public Date getStartDatum() {
		return startDatum;
	}
	
	/**
	 * Setzen des Startdatums des Projekts
	 * @param startDatum
	 */
	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}
	
	/**
	 * Auslesen des Enddatums des Projekts
	 * @return endDatum
	 */
	public Date getEndDatum() {
		return endDatum;
	}
	
	/**
	 * Setzen des Enddatums des Projekts
	 * @param endDatum
	 */
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}
	
	/**
	 * Auslesen des Fremdschlüssels der Person
	 * @return idPerson
	 */
	public int getIdPerson() {
		return idPerson;
	}
	
	/**
	 * Setzen des Fremdschlüssels der Person
	 * @param idPerson
	 */
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	
	/**
	 * Auslesen des Fremdschlüssel des Marktplatzes
	 * @return idMarktplatz
	 */
	public int getIdMarktplatz() {
		return idMarktplatz;
	}
	
	/**
	 * Setzen des Fremdschlüssels des Marktplatzes
	 * @param idMarktplatz
	 */
	public void setIdMarktplatz(int idMarktplatz) {
		this.idMarktplatz = idMarktplatz;
	}
	
	/**
	 * Erzeugen einer textuellen Darstellung eines Projekts
	 */
	@Override
	public String toString() {
		return "Projekt [bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", startDatum=" + startDatum
				+ ", endDatum=" + endDatum + ", idPerson=" + idPerson + ", idMarktplatz=" + idMarktplatz + "]";
	}
	
	
	

}
