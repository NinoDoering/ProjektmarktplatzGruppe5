package de.hdm.itprojekt.shared.bo;

public class Bewertung extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private float fliesskommaBewertung;
	private String textuelleBewertung;
	private int idBewerbung = 0;
	
	
	public float getFliesskommaBewertung() {
		return fliesskommaBewertung;
	}
	public void setFliesskommaBewertung(float fliessKommaBewertung) {
		this.fliesskommaBewertung = fliessKommaBewertung;
	}
	public String getTextuelleBewertung() {
		return textuelleBewertung;
	}
	public void setTextuelleBewertung(String textuelleBewertung) {
		this.textuelleBewertung = textuelleBewertung;
	}
	public int getIdBewerbung() {
		return idBewerbung;
	}
	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	}
	
	@Override
	public String toString() {
		return "Bewertung [fliessKommaBewertung=" + fliesskommaBewertung + ", textuelleBewertung=" + textuelleBewertung
				+ ", idBewerbung=" + idBewerbung + "]";
	}

	
	
	
	
}
