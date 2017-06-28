package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.gui.RoleManagement;

import de.hdm.itprojekt.shared.GreetingServiceAsync;
//import de.hdm.itprojekt.client.gui.RoleManagement.getUser;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;

public class RoleManagementReport extends FlexTable {
	
	//Ermöglicht den Zugriff auf die Servermethoden
	private ReportGeneratorAsync reportGenerator; //= ClientSideSettings.getReportGenerator();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();

	//die auswahlliste
	private static ListBox orgEinheit = new ListBox();
	
	//Speichern der einzelnen Rollen
	private GreetingServiceAsync gwtproxyReport;
	private static Unternehmen unternehmen;
	private static Person person;
	private static Team team;
	private NavigatorReport navigatorReport;
	
	public RoleManagementReport(final Person person, final NavigatorReport navigatorReport){
		this.navigatorReport=navigatorReport;
		this.setWidget(1, 0, new Label("Organisationseinheiten: "));
		this.setWidget(1, 1, orgEinheit);
		this.setStylePrimaryName("IdentityPanel");
		
		this.reportGenerator= ClientSideSettings.getReportGenerator();
		this.gwtproxyReport = ClientSideSettings.getMarktplatzVerwaltung();
		
		
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	    cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		orgEinheit.setWidth("250px");
		
		reportGenerator.findPersonByKey(person.getId(), new getPerson());
		
		orgEinheit.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				navigatorReport.reloadReport();
			}
		});	
	}
		public static int getSelectedRoleManagementIdReport(){
			if(person.getIdTeam()!=null){
				if(orgEinheit.getSelectedIndex()==0){
					return person.getId();
				}
				else if(orgEinheit.getSelectedIndex()==1){
					return team.getId();
				}
				else if(orgEinheit.getSelectedIndex()==2){
					return unternehmen.getId();
				}
			}
			else if(person.getIdTeam()==null){
				if(orgEinheit.getSelectedIndex()==0){
					return person.getId();
				}
				else if(orgEinheit.getSelectedIndex()==1){
					return unternehmen.getId();
				}
			}
			return 0;	
		}
		
		public Organisationseinheit getSelectedRoleManagementAsObjectReport(){
			if(person.getIdTeam()!=null){
				if(orgEinheit.getSelectedIndex()==0){
					return person;
				}
				else if(orgEinheit.getSelectedIndex()==1){
					return team;
				}
				else if(orgEinheit.getSelectedIndex()==2){
					return unternehmen;
				}
			}
			else if(person.getIdTeam()==null){
				if(orgEinheit.getSelectedIndex()==0){
					return person;
				}
				else if(orgEinheit.getSelectedIndex()==1){
					return unternehmen;
				}
			}
			return null;
		}
		
		public Person getUser(){
			return person;
		}
		
		public Team getTeamOfUser(){
			return team;
		}
		
		public Unternehmen getUnternehmenOfUser(){
			return unternehmen;
		}
		
		public  ListBox getOwnOrgUnits(){
			return orgEinheit;
		}
		
		public void deactivateOrgUnits(){
			orgEinheit.setEnabled(false);
		}
		
		public void activateOrgUnits(){
			orgEinheit.setEnabled(true);
		}
		
		public void setOwnOrgUnitToZero(){
			orgEinheit.setSelectedIndex(0);
		}
		
		public void reinitialize(){
			reportGenerator.findPersonByKey(person.getId(), new getPerson());
			
		}
		
		private RoleManagementReport getThis(){
			return this;
		}
		
		
private class getPerson implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Fehler, die Person konnte nicht geladen werden.");
	}

	@Override
	public void onSuccess(Person result) {
		// TODO Auto-generated method stub
		orgEinheit.clear();
		person=result;
		Integer idPersonReport = result.getId();
		orgEinheit.addItem("Person: " + result.getVorname() + " " + result.getNachname(), idPersonReport.toString());
		
		if(person.getIdTeam()!=null){
			reportGenerator.findTeamByKey(result.getIdTeam(), new getTeam());
		}
		else if(person.getIdUnternehmen()!=null){
			reportGenerator.findUnternehmenByKey(result.getIdUnternehmen(), new getUnternehmen());
		}
	}
}
			
private class getTeam implements AsyncCallback<Team>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Fehler, das Team konnte nicht geladen werden.");
	}

	@Override
	public void onSuccess(Team result) {
		// TODO Auto-generated method stub
		Integer idTeamReport = result.getId();
		orgEinheit.addItem("Team: " + result.getTeamName(), idTeamReport.toString());
		team=result;
		if(person.getIdUnternehmen()!=null){
			reportGenerator.findUnternehmenByKey(person.getIdUnternehmen(), new getUnternehmen());
		}
	}
}

private class getUnternehmen implements AsyncCallback<Unternehmen>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Fehler, das Unternehmen konnte nicht geladen werden.");
	}

	@Override
	public void onSuccess(Unternehmen result) {
		// TODO Auto-generated method stub
		Integer idUnternehmenReport=result.getId();
		orgEinheit.addItem("Unternehmen: " + result.getFirmenName(), idUnternehmenReport.toString());
		unternehmen=result;
	}
}
}
