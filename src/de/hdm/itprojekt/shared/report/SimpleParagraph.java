package de.hdm.itprojekt.shared.report;




public class SimpleParagraph extends Paragraph {
	
	
	
private static final long serialVersionUID = 1L;

	
	private String text = "";


	public SimpleParagraph(){
	}
	
	/**
	 * Konstruktor, der bei der Insanziierung eines
	 * SimpleParagraph Objekts bzw. Absatzes 
	 * den zugehörigen Inhalt setzt
	 */
	public SimpleParagraph(String t){
		this.text = t;
	}
	
	// Auslesen des Absatzes
	public String getText() {
		return text;
	}

	
	public void setText(String text) {
		this.text = text;
	}

	
	// SimpleParagraph Objekt umwandeln in einen String
	public String toString() {
		return this.text;
}
}
