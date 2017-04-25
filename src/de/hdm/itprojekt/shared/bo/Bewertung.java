package de.hdm.itprojekt.shared.bo;

public class Bewertung extends BusinessObject {

	private int id = 0;
	private float fliessKommaBewertung;
	private String textuelleBewertung;
	private static final long serialVersionUID = 1L;
	
	public Bewertung(){
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		this.id+ " "+
		this.fliessKommaBewertung+ " "+
		this.textuelleBewertung;
	}
}
