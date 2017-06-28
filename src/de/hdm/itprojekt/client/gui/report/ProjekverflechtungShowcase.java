package de.hdm.itprojekt.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.report.HTMLReportWriter;
import de.hdm.itprojekt.shared.report.ProjektverflechtungReport;

public class ProjekverflechtungShowcase extends Showcase {

	private RoleManagementReport rolemanagementReport = null;

	public ProjekverflechtungShowcase(RoleManagementReport rolemanagementReport){
		this.rolemanagementReport=rolemanagementReport;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Report für die Projektverflechtungen</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
		VerticalPanel projekverflechtungVP = new VerticalPanel();
		
		final HTMLPanel htmlPanel = new HTMLPanel();
		final Showcase scase = this;
		final ListBox boxBewerbung = new ListBox();
	
		boxBewerbung.addItem("Wählen Sie bitte einen Bewerber aus.");
		
		reportGenerator.getBewerberByAusschreibungen(rolemanagementReport.getSelectedRoleManagementAsObjectReport(), new AsyncCallback<Vector<Organisationseinheit>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				scase.append("Fehlerausgabe " + caught.getMessage());
			}

			@Override
			public void onSuccess(Vector<Organisationseinheit> result) {
				// TODO Auto-generated method stub
				for(Organisationseinheit b : result){
					if(b instanceof Person){
						boxBewerbung.addItem(((Person)b).getVorname() + " " + ((Person)b).getNachname() + " , id: " + b.getId());
					}
					else if(b instanceof Team){
						boxBewerbung.addItem(((Team)b).getTeamName() + " , id: " + b.getId());
					}
					else if(b instanceof Unternehmen){
						boxBewerbung.addItem(((Unternehmen)b).getFirmenName() + " , id: " + b.getId());
					}
			}
					
		}
		});
		projekverflechtungVP.add(boxBewerbung);
		projekverflechtungVP.add(htmlPanel);
		this.add(projekverflechtungVP);
		
		//ClickHandler für die boxBewerbung
		boxBewerbung.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				ReportGeneratorAsync reportGenerator = ClientSideSettings.getReportGenerator();
				htmlPanel.clear();
				
				String a = boxBewerbung.getValue(boxBewerbung.getSelectedIndex());
				String b = a.substring(a.indexOf(':')+1, a.length());
				int idAuswahl = Integer.valueOf(b);
				
				reportGenerator.createProjektverflechtungenReport(idAuswahl, new AsyncCallback<ProjektverflechtungReport>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						scase.append("Fehlerausgabe " + caught.getMessage());
					}

					@Override
					public void onSuccess(ProjektverflechtungReport result) {
						// TODO Auto-generated method stub
						if(result!=null){
						HTMLReportWriter writerHTML = new HTMLReportWriter();
						writerHTML.process(result);
						scase.append(writerHTML.getReportText());
						}
					}
				});	
			}
		});
	}
}