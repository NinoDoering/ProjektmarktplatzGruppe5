package de.hdm.itprojekt.shared.report;
import java.io.Serializable;
import java.util.Date;



public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private String titel = "";
	

	private Date erstellDatum = null;
	
	

	private Paragraph header = null;

	

	public String getTitel() {
		return titel;
	}


	public void setTitel(String titel) {
		this.titel = titel;
	}


	public Date getErstelldatum() {
		return erstellDatum;
	}

	

	public void setErstelldatum(Date erstellDatum) {
		this.erstellDatum = erstellDatum;
	}

	// zurückgeben von Paragraph Objekt, dass die Kopfdaten beinhaltet
	public Paragraph getHeader() {
		return header;
	}


	public void setHeader(Paragraph header) {
		this.header = header;
}

}
