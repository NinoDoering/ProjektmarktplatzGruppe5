package de.hdm.itprojekt.shared.report;
import java.util.Vector;



public class CompositeParagraph extends Paragraph {

	private static final long serialVersionUID = 1L;

	
	private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();
	
	
	
	public void addSubParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);
	}
	  
	
	public void removeSubParagraph(SimpleParagraph p) {
		this.subParagraphs.removeElement(p);
	}
	
	
	public Vector<SimpleParagraph> getSubParagraphs() {
		return this.subParagraphs;
	}
	  
	
	public int getParagraphsSize() {
		return this.subParagraphs.size();
	}
	
	
	public SimpleParagraph getParagraphByIndex(int i) {
		return this.subParagraphs.elementAt(i);
	}
	
	
	public String toString() {
	   
		/**
		 * Leerer Buffer in denen alle Unterabschnitte, die
		 * in Strings umgewandelt wurden eingetragen werden 
		 * können
		 */
	    StringBuffer result = new StringBuffer();


	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.elementAt(i);

	    // in String umwandeln und an Buffer eintragen
	      result.append(p.toString() + "\n");
	    }

	 // Buffer in String umwandeln und zurückgeben
	    return result.toString();
}
}
