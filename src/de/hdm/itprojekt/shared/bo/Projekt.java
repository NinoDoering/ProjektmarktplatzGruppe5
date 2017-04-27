package de.hdm.itprojekt.shared.bo;

import java.util.Date;

public class Projekt {

	private static final long serialVersionUID = 1L;
	private int id;
	private String bezeichnung;
	private String beschreibung;

	private Date startDatum;
	private Date endDatum;
	private Person projektleiter;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Person getProjektleiter() {
		return projektleiter;
	}

	public void setProjektleiter(Person projektleiter) {
		this.projektleiter = projektleiter;
	}

	public String toString() {
		return super.toString() + this.id + " " + this.bezeichnung + " " + this.beschreibung + " " + this.startDatum
				+ " " + this.endDatum + " " + this.projektleiter;
	}
}
