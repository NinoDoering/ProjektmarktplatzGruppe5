package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.AllBewerbungenToOneAusschreibungReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

public class AlleBewerbungenToOneAusschreibungShowcase extends Showcase{

	private RoleManagement rolemanagementReport = null;
	
	public AlleBewerbungenToOneAusschreibungShowcase(RoleManagement rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Report von allen Bewerbungen die auf eine eigene Ausschreibung eingegangen sind";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		
		reportGenerator.createAllBewerbungenToOneAusschreibungReport(rolemanagementReport.getSelectedIdentityAsObject(), new AsyncCallback<AllBewerbungenToOneAusschreibungReport>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
			}

			@Override
			public void onSuccess(AllBewerbungenToOneAusschreibungReport result) {
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
