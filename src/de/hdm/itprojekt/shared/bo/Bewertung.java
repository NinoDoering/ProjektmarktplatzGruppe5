package de.hdm.itprojekt.shared.bo;

public class Bewertung extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Bewertung der Bewerbung als Fließkommazahl
	 */
	private float fliesskommaBewertung = 0.0f;
	
	/**
	 * Textuelle Bewertung der Bewerbung
	 */
	private String textuelleBewertung ="";
	
	/**
	 * Fremdschlüsselbeziehung zur zugehoerigen Bewerbung.
	 */
	private int idBewerbung = 0;
	
	/**
	 * Auslesen der Bewertung als Kommazahl der Bewertung
	 * @return fliesskommaBewertung
	 */
	public float getFliesskommaBewertung() {
		return fliesskommaBewertung;
	}
	
	/**
	 * Setzen der Bewertung als Kommazahl zur Bewerbung
	 * @param fliessKommaBewertung
	 */
	public void setFliesskommaBewertung(float fliessKommaBewertung) {
		this.fliesskommaBewertung = fliessKommaBewertung;
	}
	
	/**
	 * Auslesen der textuellen Bewertung
	 * @return textuelleBewertung
	 */
	public String getTextuelleBewertung() {
		return textuelleBewertung;
	}
	
	/**
	 * Textuelle Bewertung festlegen
	 * @param textuelleBewertung
	 */
	public void setTextuelleBewertung(String textuelleBewertung) {
		this.textuelleBewertung = textuelleBewertung;
	}
	
	/**
	 * Auslesen des Fremdschlüssels der Bewerbung.
	 * @return idBewerbung
	 */
	public int getIdBewerbung() {
		return idBewerbung;
	}
	
	/**
	 * Setzen des Fremdschlüssels der Bewerbung.
	 * @param idBewerbung
	 */
	public void setIdBewerbung(int idBewerbung) {
		this.idBewerbung = idBewerbung;
	}
	
	/**
	 * Ausgabe des Objekts in Textform mit allen zugehoerigen Attributen
	 */
	@Override
	public String toString() {
		return "Bewertung [fliessKommaBewertung=" + fliesskommaBewertung + ", textuelleBewertung=" + textuelleBewertung
				+ ", idBewerbung=" + idBewerbung + "]";
	}

	
	
	
	
}
