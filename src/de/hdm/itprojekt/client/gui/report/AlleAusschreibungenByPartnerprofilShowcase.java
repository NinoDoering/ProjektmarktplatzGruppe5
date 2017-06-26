package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

public class AlleAusschreibungenByPartnerprofilShowcase extends Showcase {
	
	private RoleManagement rolemanagementReport = null;

	public AlleAusschreibungenByPartnerprofilShowcase(RoleManagement rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Report von allen Ausschreibungen zu einem Partnerprofil entsprechen";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		
		reportGenerator.createAllAusschreibungenByPartnerprofilReport(rolemanagementReport.getSelectedRoleAsObject(), new AsyncCallback<AllAusschreibungenByPartnerprofilReport>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
			}

			@Override
			public void onSuccess(AllAusschreibungenByPartnerprofilReport result) {
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
