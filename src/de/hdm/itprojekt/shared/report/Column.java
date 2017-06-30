package de.hdm.itprojekt.shared.report;
import java.io.Serializable;



public class Column implements Serializable {

/**
 * Bildung einer Spalte eines Rows Objekts. Diese Spalten(Columns) implementieren das
 * Serializable Interface. Dadurch können diese Columns zwischen Client und 
 * Server transferiert werden
 */
private static final long serialVersionUID = 1L;
	

	// Wert des Row Objekts entspricht dem Eintrag eines Rows
	private String value = "";
	private int valueInt;

	/**No-Argument Konstruktor implementieren in Serialisierbare Klassen 
	 * Sonst würde der Default-Konstruktor gelten
	 */
	public Column(){	
	}
	

	// Spaltenwert wird hier vom Konstruktor erzeugt
	public Column(String v){
		this.value = v;
	}
	
	// Int für Beteiligungszeit int
	public Column(int vi){
		this.valueInt = vi;
	}


	// Auslesen
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	// Column Objekt in ein String umwandeln
	public String toString(){
		return this.value;
	}

}
