package de.hdm.itprojekt.shared.bo;

import java.util.Date;
import java.sql.*;

public class Bewerbung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Beziehung zu Organisationseinheit ueber Fremdschlüssel
	 */
	private int idOrganisationseinheit;
	
	/*
	 * Bewerbungstext
	 */
	private String bewerbungsText;
	
	/*
	 * Erstellungsdatum der Bewerbung
	 */
	private Date erstellDatum;
	
	/*
	 * ID der Ausschreibung
	 */
	private int idAusschreibung;
	
	/*
	 * Status der Bewerbung. Dieser kann "eingereicht", "zusage" oder "absage" annehmen.
	 */
	public enum BewerbungsStatus {eingereicht, zusage, absage;
		public String toString2(){
		switch(this) {
	      case eingereicht: return "eingereicht";
	      case zusage: return "zusage";
	      case absage: return "absage";
	      default: throw new IllegalArgumentException();
	}	
	} }
	
	/*
	 * Bewerbungsstatus wird im Voraus auf "eingereicht" gesetzt
	 */
	private BewerbungsStatus bewerbungsStatus = BewerbungsStatus.eingereicht;
	
	/*
	 * Ausgabe der Organisationseinheits-ID
	 * @return idOrganisationseinheit
	 */
	public int getIdOrganisationseinheit() {
		return idOrganisationseinheit;
	}
	
	/*
	 * Festlegen der Organisationseinheits-ID
	 * @param idOrganisationseinheit
	 */
	public void setIdOrganisationseinheit(int idOrganisationseinheit) {
		this.idOrganisationseinheit = idOrganisationseinheit;
	}
	
	/*
	 * Ausgabe des Bewerbungstextes
	 * @return bewerbungsText
	 */
	public String getBewerbungsText() {
		return bewerbungsText;
	}
	
	/*
	 * Festlegen des Bewerbungstextes
	 * @param bewerbungsText
	 */
	public void setBewerbungsText(String bewerbungsText) {
		this.bewerbungsText = bewerbungsText;
	}
	
	/*
	 * Augabe des Erstellungsdatums
	 * @return erstellDatum
	 */
	public Date getErstellDatum() {
		return erstellDatum;
	}
	
	/*
	 * Festlegen des Erstelldatums
	 * @param erstellDatum
	 */
	public void setErstellDatum(Date erstellDatum) {
		this.erstellDatum = erstellDatum;
	}
	
	/*
	 * Ausgabe der Ausschreibungs-ID
	 * @return idAusschreibung
	 */
	public int getIdAusschreibung() {
		return idAusschreibung;
	}
	
	/*
	 * Festlegen der Ausschreibungs-ID
	 * @param isAusschreibung
	 */
	public void setIdAusschreibung(int idAusschreibung) {
		this.idAusschreibung = idAusschreibung;
	}
	
	/*
	 * Ausgabe des Bewerbungsstatus
	 * @return bewerbungsStatus 
	 */
	public BewerbungsStatus getBewerbungsStatus() {
		return bewerbungsStatus;
	}
	
	/*
	 * Festlegen des Bewerbungsstatus
	 * @param bewerbungsStatus
	 */
	public void setBewerbungsStatus(BewerbungsStatus bewerbungsStatus) {
		this.bewerbungsStatus = bewerbungsStatus;
	}
	
	/*
	 * (non-Javadoc)
	 * @see de.hdm.itprojekt.shared.bo.BusinessObject#toString()
	 */
	@Override
	public String toString() {
		return "Bewerbung [idOrganisationseinheit=" + idOrganisationseinheit + ", bewerbungsText=" + bewerbungsText
				+ ", erstellDatum=" + erstellDatum + ", idAusschreibung=" + idAusschreibung + ", bewerbungsstatus="
				+ bewerbungsStatus + "]";
	}
	
}
