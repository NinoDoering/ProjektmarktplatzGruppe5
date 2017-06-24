package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;
import de.hdm.itprojekt.shared.report.AllAusschreibungenReport;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;


public class AlleAusschreibungen extends Showcase{

	//Können die Überschrift der AnzeigeSeite auch ändern
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Alle Ausschreibung im Bezug zum Report</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		this.append("Ausgabe von allen Ausschreibung die auf dem Marktplatz vorhanden sind.");
		
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		reportGenerator.createAllAusschreibungenReport(new AsyncCallback<AllAusschreibungenReport>(){
			
			public void onFailure(Throwable caught){
				scase.append("Fehlerausgabe " + caught.getMessage() );
			
			}
			@Override
			public void onSuccess(AllAusschreibungenReport result) {
				// TODO Auto-generated method stub
				if(result!=null){
				HTMLReportWriter writerHTML = new HTMLReportWriter();
				writerHTML.process(result);
				scase.append(writerHTML.getReportText());
				}
			}
		});
	}
}
