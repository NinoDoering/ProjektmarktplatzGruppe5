package de.hdm.itprojekt.shared.bo;

import java.io.Serializable;
//test
/** Erzeugung der Superklasse BusinessObjects, vererbt 
* an alle anderen BO Klassen,  */ 

public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	// eine eindeutige Id aller BO's 
	
	private int idBusinessObject = 0;
	
	
		public int getIdBusinessObject() {
		return this.idBusinessObject; 
	}
	
	public void setIdBusinessObject(int idBusinessObject) {
		this.idBusinessObject= idBusinessObject; 
	}
	
	/** eine Instanz dieser Klasse wird textuell dargestellt 
	*  Die Ausgabe enth�lt den Klassennamen und die Id des Objektes */
	
	public String toString(){
		
		return this.getClass().getName() + " " 
		+ this.idBusinessObject; 
		
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
	        if (bo.getIdBusinessObject() == this.idBusinessObject)
	          return true;
	      }
	      catch (IllegalArgumentException e) {
	        /*
	         * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
	         * zur�ck.
	         */
	        return false;
	      }
	    }
	    /*
	     * Wenn bislang keine Gleichheit bestimmt werden konnte, dann m�ssen
	     * schlie�lich false zur�ckgeben.
	     */
	    return false;
	  }
	
	public int hashCode() {
		return this.idBusinessObject; 
	}
}
