package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.report.AllBewerbungenByOrganisationseinheitReport;
import de.hdm.itprojekt.shared.report.AllBewerbungenWithAusschreibungenReport;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;

public class AlleBewerbungenByOrganisationseinheitShowcase extends Showcase {

	// !!!! 	EIGENTLICH IST DAS bEWERBUNGEN BY AUSSCHREIBUNGEN 
	
	
	
	private RoleManagementReport rolemanagementReport = null;

	public AlleBewerbungenByOrganisationseinheitShowcase(RoleManagementReport rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Report der eigenen Bewerbungen: </h1>";
	}

	@Override
	protected void run() {
//		Window.alert("Hallo wahid"+ rolemanagementReport.getSelectedRoleManagementIdReport());
		// TODO Auto-generated method stub
		final Showcase scase = this;
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
//		Window.alert("Hallo wahid 2" + rolemanagementReport.getSelectedRoleManagementIdReport());
		reportGenerator.createAllBewerbungenWithAusschreibungenReport(rolemanagementReport.getSelectedRoleManagementAsObjectReport(),
				new AsyncCallback<AllBewerbungenWithAusschreibungenReport>(){
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
				Window.alert("Hallo wahid 3");
			}

			@Override
			public void onSuccess(AllBewerbungenWithAusschreibungenReport result) {
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
