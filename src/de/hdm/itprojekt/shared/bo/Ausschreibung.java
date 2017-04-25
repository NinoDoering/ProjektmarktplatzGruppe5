package de.hdm.itprojekt.shared.bo;

import java.util.*;

/** erzeugen der Klasse Ausschreibung */

public class Ausschreibung extends BusinessObject  {
	
	private static final long serialVersionUID = 1L; 
	
	private String bezeichnung; 
	
	private int id = 0; 
	
	private Date endDatum;
	
	private String beschreibung;

	
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	/** textuelle Aussgabe, besthende aus dem Text der 
	 *  Superklasse ( Klassenname und Id ) und der Bezeichnung 
	 *  der Ausschreibung 
	 */
	
	public String toString() { 
		return super.toString() + " "+ this.bezeichnung + " "+ this.beschreibung+ " "+this.endDatum; 
	}
	
	
	

}
