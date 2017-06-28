package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.AllBewerbungenByAusschreibungReport;
//import de.hdm.itprojekt.shared.report.AllBewerbungenToOneAusschreibungReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

// EIGENTLICH IST DAS EIGENE BEWERBUNGEN

public class AlleBewerbungenByAusschreibungShowcase extends Showcase{

	private RoleManagementReport rolemanagementReport = null;
	
	public AlleBewerbungenByAusschreibungShowcase(RoleManagementReport rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Eingegangene Bewerbungen auf eigene Ausschreibungen </h1>";
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		
		reportGenerator.createAllBewerbungenByAusschreibungReport(rolemanagementReport.getSelectedRoleManagementAsObjectReport(), new AsyncCallback<AllBewerbungenByAusschreibungReport>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
			}

			@Override
			public void onSuccess(AllBewerbungenByAusschreibungReport result) {
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
