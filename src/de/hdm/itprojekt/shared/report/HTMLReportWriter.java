package de.hdm.itprojekt.shared.report;

import java.util.Vector;

import com.google.gwt.user.client.Window;



public class HTMLReportWriter {
	
private String reportText = "";
	
	
	
	public void resetReportText(){
		this.reportText = "";
	}
	
	/**
	 * Umwandeln eines ParagraphObjekts in HTML.
	 * Rückgabe in HTML-Text.
	 */
	public String paragraphToHTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
		    return this.paragraphToHTML((CompositeParagraph) p);
		}
		else {
		    return this.paragraphToHTML((SimpleParagraph) p);
		}
	}	
	
	/**
	 * Umwandeln eines CompositeParagraphs in HTML.
	 */
	 public String paragraphToHTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getParagraphsSize(); i++) {
		result.append("<p>" + p.getParagraphByIndex(i) + "</p>");
		}
		return result.toString();
	}	
	
	/**
	 * Umwandeln eines SimpleParagraphs in HTML.
	 */
	 public String paragraphToHTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}
	 
	// Erstellen eines HeaderTexts in HTML
	 public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}
	 
	 // HTML Trailer Text erstellen
	  public String getTrailer() {
		 return "</body></html>";
	}
	  
	// Auslesen des Ergebnisses
	  public String getReportText() {
		 return this.getHeader() + this.reportText + this.getTrailer();
	}

	  
	  public void process(AllAusschreibungenReport r){
		  
		  //Ergebnis resetten bzw. löschen
		  this.resetReportText();
		  
			
		  // StringBuffer Objekt erstellt, in denen schrittweise Ergebnisse eingetragen werden
		  StringBuffer result = new StringBuffer();
		  
		  
		    /*
		     * Schrittweise werden die einzelnen Bestandteile des Reports
		     * ausgelesen und in HTML-Form übersetzt.
		     */
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		        	
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		             
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       /* 
		        * Ergebnis mittels getReportText() auslesen.
		        */
		       this.reportText = result.toString();
	  }
	  
	  
	  public void process(AllAusschreibungenByPartnerprofilReport r){
	
	  }
	  

	  
	  public void process(FanInFanOutReport r){
	 
	  }
	  
	  public void process(AllBewerbungenByAusschreibungReport r){
	
		  this.resetReportText();
		  

		
		  StringBuffer result = new StringBuffer();
		  
		  
	
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       

		       this.reportText = result.toString();
	  }  
	  
	  
	  public void process(FanIn r){
		  
	
		  this.resetReportText();
		  


		  StringBuffer result = new StringBuffer();
		  
		
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		
		       this.reportText = result.toString();
	  }
	  
	  public void process(FanOut r){
		  
		
		  this.resetReportText();
		  

		
		  StringBuffer result = new StringBuffer();
		  
		  
		
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		       
		       this.reportText = result.toString();
	  }
	  
	 
	  
	  public void process(AllBeteiligungenToProjectReport r){
		  
		
		  this.resetReportText();
		  

	
		  StringBuffer result = new StringBuffer();
		  
		  
	
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		 
		       this.reportText = result.toString();
	  }
	  
public void process(AllBewerbungenByOrganisationseinheitReport r){
		  
		
		  this.resetReportText();
		  

		  
		   
		  StringBuffer result = new StringBuffer();
		  
		  
		 
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
		 
		       this.reportText = result.toString();
	  }
	  
	  public void processSimpleReport(Report report){
		  
		  SimpleReport r = (SimpleReport)report;
		  
		  
		  this.resetReportText();
		  

		  StringBuffer result = new StringBuffer();
		  
		  
		
		  	result.append("<H1>" + r.getTitel() + "</H1>");
		  	result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		  	result.append("<td valign=\"top\"><b>" + paragraphToHTML(r.getHeader())
	        + "</b></td>");
		  	result.append("</tr><tr><td></td><td>" + r.getErstelldatum().toString()
		  	        + "</td></tr></table>");
		  	
		  	
		  	 Vector<Row> rows = r.getRows();
		     result.append("<table style=\"width:400px\">");
		     
		     for (int i = 0; i < rows.size(); i++) {
		         Row row = rows.elementAt(i);
		         result.append("<tr>");
		         for (int k = 0; k < row.getColumnsSize(); k++) {
		           if (i == 0) {
		             result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnByIndex(k)
		                 + "</td>");
		           }
		           else {
		             if (i > 1) {
		               result.append("<td style=\"border-top:1px solid silver\">"
		                   + row.getColumnByIndex(k) + "</td>");
		             }
		             else {
		               result.append("<td valign=\"top\">" + row.getColumnByIndex(k) + "</td>");
		             }
		           }
		         }
		         result.append("</tr>");
		       }

		       result.append("</table>");
		       
	
		       this.reportText = result.toString();
}

	
}
