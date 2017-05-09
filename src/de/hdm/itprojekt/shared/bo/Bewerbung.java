package de.hdm.itprojekt.shared.bo;

import java.util.Date;
import java.sql.*;

public class Bewerbung extends BusinessObject {

	private int idBewerbung = 0;
	private String bewerber;
	private String bewerbungsText;
	private Date erstellDatum;
	private static final long serialVersionUID = 1L;
	
	public int getIdAusschreibung(){
		return getIdAusschreibung();
	}
	
	public int getIdBewertung(){
		return getIdBewertung();
	}
	
	public int getIdPartnerprofil(){
		return getIdPartnerprofil();
	}
	
	public Bewerbung(){
		super();
	}
	
	public int getIdBewerber() {
		return idBewerbung;
	}
	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	}
	public String getBewerber() {
		return bewerber;
	}
	public void setBewerber(String bewerber) {
		this.bewerber = bewerber;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
			return super.toString()+ " "+
			this.idBewerbung+ " "+
			this.bewerber+ " "+
			this.bewerbungsText+ " "+
			this.erstellDatum;
	}
	
	
}
