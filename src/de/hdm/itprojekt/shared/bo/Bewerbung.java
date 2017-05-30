package de.hdm.itprojekt.shared.bo;

import java.util.Date;
import java.sql.*;

public class Bewerbung extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	private int idOrganisationseinheit;
	private String bewerbungsText;
	private Date erstellDatum;
	private int idAusschreibung;
	public enum Status {eingereicht, zusage, absage} ;
	private Status bewerbungsstatus = Status.eingereicht;
	public int getIdOrganisationseinheit() {
		return idOrganisationseinheit;
	}
	public void setIdOrganisationseinheit(int idOrganisationseinheit) {
		this.idOrganisationseinheit = idOrganisationseinheit;
	}
	public String getBewerbungsText() {
		return bewerbungsText;
	}
	public void setBewerbungsText(String bewerbungsText) {
		this.bewerbungsText = bewerbungsText;
	}
	public Date getErstellDatum() {
		return erstellDatum;
	}
	public void setErstellDatum(Date erstellDatum) {
		this.erstellDatum = erstellDatum;
	}
	public int getIdAusschreibung() {
		return idAusschreibung;
	}
	public void setIdAusschreibung(int idAusschreibung) {
		this.idAusschreibung = idAusschreibung;
	}
	public Status getBewerbungsstatus() {
		return bewerbungsstatus;
	}
	public void setBewerbungsstatus(Status bewerbungsstatus) {
		this.bewerbungsstatus = bewerbungsstatus;
	}
	
	@Override
	public String toString() {
		return "Bewerbung [idOrganisationseinheit=" + idOrganisationseinheit + ", bewerbungsText=" + bewerbungsText
				+ ", erstellDatum=" + erstellDatum + ", idAusschreibung=" + idAusschreibung + ", bewerbungsstatus="
				+ bewerbungsstatus + "]";
	}
	
	

	
}
