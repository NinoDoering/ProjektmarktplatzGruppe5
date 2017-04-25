package de.hdm.itprojekt.shared.bo;

import java.io.Serializable;
//test
/** Erzeugung der Superklasse BusinessObjects, vererbt 
* an alle anderen BO Klassen,  */ 

public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	// eine eindeutige Id aller BO's 
	
	private int id = 0;
	
	
	public int getId() {
		return this.id; 
	}
	
	public void setId(int id) {
		this.id= id; 
	}
	
	/** eine Instanz dieser Klasse wird textuell dargestellt 
	*  Die Ausgabe enthält den Klassennamen und die Id des Objektes */
	
	public String toString(){
		
		return this.getClass().getName() + " " + this.id; 
		
	}
	
	/** inhaltlicher Abgleich zweier Business Objects anhand der Id*/
	
	public boolean equals(Object o) {
	    /*
	     * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
	     * werden kann, sind immer wichtig!
	     */
	    if (o != null && o instanceof BusinessObject) {
	      BusinessObject bo = (BusinessObject) o;
	      try {
	        if (bo.getId() == this.id)
	          return true;
	      }
	      catch (IllegalArgumentException e) {
	        /*
	         * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
	         * zurück.
	         */
	        return false;
	      }
	    }
	    /*
	     * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
	     * schließlich false zurückgeben.
	     */
	    return false;
	  }
	
	public int hashCode() {
		return this.id; 
	}
}
