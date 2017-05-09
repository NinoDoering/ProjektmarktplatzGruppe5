package de.hdm.itprojekt.shared.bo;

import java.util.*;
//test
/** erzeugen der Klasse Ausschreibung */

public class Ausschreibung extends BusinessObject  {
	
	
	private static final long serialVersionUID = 1L; 
	private String bezeichnung; 
	private int idAusschreibung = 0; 
	private Date endDatum;
	private String beschreibung;

	public int getIdProjekt(){
		return getIdProjekt();
	}
	
	public int getIdBewerbung(){
		return getIdBewerbung();
	}
	
	public int getIdPartnerprofil(){
		return getIdPartnerprofil();
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getIdAusschreibung() {
		return idAusschreibung;
	}

	public void setIdAusschreibung(int idAusschreibung) {
		this.idAusschreibung = idAusschreibung;
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
		return super.toString() + " "
		+ this.idAusschreibung + " "
		+ this.bezeichnung + " "
		+ this.beschreibung+ " "
		+this.endDatum; 
	}
	
	
	

}
