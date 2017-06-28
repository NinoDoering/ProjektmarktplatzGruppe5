package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.FanInFanOutReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

public class FanInFanOutShowcase extends Showcase {

	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Report der FanInFanOut Analyse</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();

		reportGenerator.createFanInFanOutReport(new AsyncCallback<FanInFanOutReport>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
			}

			@Override
			public void onSuccess(FanInFanOutReport result) {
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
