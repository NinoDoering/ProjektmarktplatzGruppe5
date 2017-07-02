package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.AllAusschreibungenByPartnerprofilReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

/**
 * Diese Klasse ist dafuer zustaendig, alle Ausschreibungen, passend zum Partnerprofil auszugeben.
 */
public class AlleAusschreibungenByPartnerprofilShowcase extends Showcase {
	
	private RoleManagementReport rolemanagementReport = null;

	public AlleAusschreibungenByPartnerprofilShowcase(RoleManagementReport rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	public AlleAusschreibungenByPartnerprofilShowcase(){
		
	}
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Report von allen Ausschreibungen zu einem Partnerprofil entsprechen</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		
		reportGenerator.getAusschreibungByMatchingPartnerprofil(rolemanagementReport.getSelectedRoleManagementAsObjectReport(), new AsyncCallback<AllAusschreibungenByPartnerprofilReport>(){

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
