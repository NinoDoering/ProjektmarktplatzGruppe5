package de.hdm.itprojekt.shared.bo;

import java.util.Date;
import java.sql.*;

public class Bewerber extends BusinessObject {

	private int id = 0;
	private String bewerber;
	private String bewerbungsText;
	private Date erstellDatum;
	private static final long serialVersionUID = 1L;
	
	public Bewerber(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
			this.id+ " "+
			this.bewerber+ " "+
			this.bewerbungsText+ " "+
			this.erstellDatum;
	}
	
	
}
