package de.hdm.itprojekt.shared.bo;

public class Bewertung extends BusinessObject {

	private int idBewertung = 0;
	private float fliessKommaBewertung;
	private String textuelleBewertung;
	private static final long serialVersionUID = 1L;
	
	public int getIdBewerbung(){
		return getIdBewerbung();
	}
	
	public int getIdBeteiligung(){
		return getIdBeteiligung();
	}
	
	public Bewertung(){
		super();
	}
	
	public int getIdBewertung() {
		return idBewertung;
	}
	public void setIdBewertung(int idBewertung) {
		this.idBewertung = idBewertung;
	}
	public float getFliessKommaBewertung() {
		return fliessKommaBewertung;
	}
	public void setFliessKommaBewertung(float fliessKommaBewertung) {
		this.fliessKommaBewertung = fliessKommaBewertung;
	}
	public String getTextuelleBewertung() {
		return textuelleBewertung;
	}
	public void setTextuelleBewertung(String textuelleBewertung) {
		this.textuelleBewertung = textuelleBewertung;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		return super.toString()+ " "+
		this.idBewertung+ " "+
		this.fliessKommaBewertung+ " "+
		this.textuelleBewertung;
	}
}
