package de.hdm.itprojekt.shared.bo;

import java.util.*;
//test


public class Ausschreibung extends BusinessObject  {
	
	
	private static final long serialVersionUID = 1L; 
	
	/*
	 * Bezeichnung der Ausschreibung 
	 */
	private String bezeichnung;
	
	/*
	 * Datum, bis zu dem man sich auf die Ausschreibung bewerben kann.
	 */
	private Date endDatum;
	
	/*
	 * Beschreibung der Ausschreibung
	 */
	private String beschreibung;
	
	/*
	 * Beziehung zu Projekt über Fremdschlüssel
	 */
	private int idProjekt;
	
	/*
	 * Beziehung zu Partnerprofil über Fremdschlüssel
	 */
	private int idPartnerprofil;
	
	/*
	 * Beziehung zu Ausschreibendem (Person, Team, Unternehmen) über Fremdschlüssel
	 */
	private int idAusschreibender;
	
	/*
	 * Status der Ausschreibung. Dieser kann besetzt, abgebrochen oder laufend sein.
	 */
    public enum Status {besetzt, abgebrochen, laufend;
    	public String toString3(){
    		switch(this){
    		case besetzt: return "besetzt";
    		case abgebrochen: return "abgebrochen";
    		case laufend: return "laufend";
    		default: throw new IllegalArgumentException();
    		}
    	}
    }
    
    /*
     * Der Ausschreibungsstatus wird vorerst auf laufend gesetzt.
     */
	private Status ausschreibungsstatus = Status.laufend;
	
    /*
     * Id der Ausschreibung wird gesetzt
     * @param idProjekt
     */
    public void setIdProjekt(int idProjekt) {
		this.idProjekt = idProjekt;
	}

    /*
     * Auslesen der Id der Ausschreibung
     * @return idProjekt
     */
	public int getIdProjekt(){
		return idProjekt;
	}
	
	/*
	 * Auslesen der Bezeichnung der Ausschreibung
	 * @return bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/*
	 * Festlegen der Bezeichnung
	 * @param bezeichnung
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	/*
	 * Auslesen des Fremdschlüssels idPartnerofil von Partnerprofil
	 * @return idPartnerprofil
	 */
	public int getIdPartnerprofil() {
		return idPartnerprofil;
	}

	/*
	 * idPartnerprofil festlegen
	 * @param idPartnerprofil 
	 */
	public void setIdPartnerprofil(int idPartnerprofil) {
		this.idPartnerprofil = idPartnerprofil;
	}

	/*
	 * Auslesen von Fremdschlüssel idAusschreibender
	 * @return idAusschreibender
	 */
	public int getIdAusschreibender() {
		return idAusschreibender;
	}

	/*
	 * Id des Ausschreibenden festlegen
	 * @param idAusschreibender
	 */
	public void setIdAusschreibender(int idAusschreibender) {
		this.idAusschreibender = idAusschreibender;
	}

	/*
	 * Auslesen des Enddatums
	 * @return endDatum 	
	 */
	public Date getEndDatum() {
		return endDatum;
	}

	/*
	 * Enddatum festlegen
	 * @param endDatum
	 */
	public void setEndDatum(Date endDatum) {
		this.endDatum = endDatum;
	}

	/*
	 * Auslesen der Ausschreibungsbeschreibung
	 * @return beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/*
	 * Beschreibung der Ausschreibung festlegen
	 * @param beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	} 
	
	/*
	 * Auslesen ausschreibungsstatus
	 * @return ausschreibungsstatus
	 */
	public Status getAusschreibungsstatus(){
		return ausschreibungsstatus;
	}
	
	/*
	 * Ausschreibungsstatus festlegen
	 * @param ausschreibungsstatus
	 */
	public void setAusschreibungsstatus(Status ausschreibungsstatus){
		this.ausschreibungsstatus = ausschreibungsstatus;
	}
	

	/*
	 * (non-Javadoc)
	 * @see de.hdm.itprojekt.shared.bo.BusinessObject#toString()
	 */
	@Override
	public String toString() {
		return "Ausschreibung [bezeichnung=" + bezeichnung + ", endDatum=" + endDatum + ", beschreibung=" + beschreibung
				+ ", idProjekt=" + idProjekt + ", idPartnerprofil=" + idPartnerprofil + ", idAusschreibender="
				+ idAusschreibender + ", ausschreibungsstatus=" + ausschreibungsstatus + "]";
	}
	
	

}
