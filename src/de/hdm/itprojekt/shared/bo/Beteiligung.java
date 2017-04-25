package de.hdm.itprojekt.shared.bo;

public class Beteiligung extends BusinessObject {

	private int id = 0;
	private static final long serialVersionUID = 1L;
	
	public Beteiligung(){
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String toString(){
		return super.toString()+" "+
		this.id;
	}
	
}
