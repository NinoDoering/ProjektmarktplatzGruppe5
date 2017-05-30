package de.hdm.itprojekt.shared.bo;

public class Bewertung extends BusinessObject {

	private static final long serialVersionUID = 1L;
	private float fliessKommaBewertung;
	private String textuelleBewertung;
	private int idBewerbung = 0;
	
	
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
	public int getIdBewerbung() {
		return idBewerbung;
	}
	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	}
	
	@Override
	public String toString() {
		return "Bewertung [fliessKommaBewertung=" + fliessKommaBewertung + ", textuelleBewertung=" + textuelleBewertung
				+ ", idBewerbung=" + idBewerbung + "]";
	}

	
	
	
	
}
