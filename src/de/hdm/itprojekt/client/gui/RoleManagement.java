package de.hdm.itprojekt.client.gui;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.*;
import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.shared.FieldVerifier;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.*;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RoleManagement extends FlexTable {

	
private static RoleManagement navigation=null;
	
	private ListBox orgEinheit = new ListBox();
//	private static ListBox Listbox2 = new ListBox();
	
	private FlexCellFormatter cellFormatter = this.getFlexCellFormatter();
	private GreetingServiceAsync gwtproxy2 = ClientSideSettings.getMarktplatzVerwaltung();
	private static Person person;

	
	public static void setPerson(Person person) {
		RoleManagement.person = person;
	}
	
	private static Team team;
	private static Unternehmen unternehmen;
	private static Vector<Marktplatz> marktplaetze;
	private boolean marktplatz = false;
	
	
	public RoleManagement (Person person){
	
		this.setWidget(1, 0, new Label("Rolle: "));		
		this.setWidget(1, 1, orgEinheit);

//		this.setWidget(2, 0, new Label("Projektmarktplatz: "));		
//		this.setWidget(2, 1, Listbox2);
		
		
		this.setStylePrimaryName("RolePanel");
		
		cellFormatter.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_RIGHT);
	    cellFormatter.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		orgEinheit.setWidth("250px");
//		Listbox2.setWidth("250px");
		
	
		
		
		gwtproxy2.getPersonById(person.getId(), new getUser());

//		orgEinheit.addItem(person.getName());
//		orgEinheit.addItem(team.getName());
//		orgEinheit.addItem(unternehmen.getName());
		
		
		
		orgEinheit.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
//				topbar.reload();
				
			}

			

		});
	}

	
	public int getSelectedIndex(){
		
		int selectedId = orgEinheit.getSelectedIndex();
		
		return selectedId;
	}

	public int getSelectedRoleID(){
		if(person.getIdTeam() != null){
			if(orgEinheit.getSelectedIndex() == 0){
				return person.getId();
			}else if(orgEinheit.getSelectedIndex() == 1){
				return team.getId();
			}else if(orgEinheit.getSelectedIndex() == 2){
				return unternehmen.getId();
			}
		}else if (person.getIdTeam() == null){
				if (orgEinheit.getSelectedIndex() == 0){
					return person.getId();
			}else if(orgEinheit.getSelectedIndex() == 1){
				return unternehmen.getId();   
			}
		}
	return 0; 
	}
	
	public Organisationseinheit getSelectedRoleAsObject(){

		if(person.getIdTeam() != null){
			if(orgEinheit.getSelectedIndex()==0){
				return person;
			}else if(orgEinheit.getSelectedIndex()==1){
				return team;
			}else if(orgEinheit.getSelectedIndex()==2){
				return unternehmen;
			}
		}else if(person.getIdTeam() == null){
			if(orgEinheit.getSelectedIndex()==0){
				return person;
			}else if(orgEinheit.getSelectedIndex()==1){
				return unternehmen;
			}

		}
		return null;
	}
	
//	public int getSelectedProjectMarketplaceId(){
//		for(Projektmarktplatz p : projektmarktplaetze){
//			if(p.getBez()==Listbox2.getSelectedItemText()){
//				return p.getID();
//			}
//		}
//		return 0;
//	}
	
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
	
//	public ListBox Listbox2(){
//		return Listbox2;
//	}
	
	public void deactivateOrgUnits(){
		orgEinheit.setEnabled(false);
	}
	
//	public void deactivateProjectMarkets(){
//		Listbox2.setEnabled(false);
//	}
	
	public void activateOrgUnits(){
		orgEinheit.setEnabled(true);
	}
	
//	public void activateProjectMarkets(){
//		Listbox2.setEnabled(true);
//	}
	
	public void setOwnOrgUnitToZero(){
		orgEinheit.setSelectedIndex(0);
	}
	
	public void reinitialize(){
		 gwtproxy2.getPersonById(person.getId(), new getUser());
	}
	
	private RoleManagement getThis(){
		return this;
	}
	
//	public boolean getisMarktplatzSet(){
//		return marktplatz;
//	}

	
	
private class getUser implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Person konnte nicht gefunden werden");
//		Window.alert("wegen des folgenden Fehlers: " + caught.toString());
		
	}

	@Override
	public void onSuccess(Person result) {
			orgEinheit.clear();
//			Listbox2.clear();
			person = result;
			Integer personID = result.getId();
			orgEinheit.addItem("Person: " + result.getVorname() + " " +
												result.getNachname() , personID.toString());
			
			if (person.getIdTeam() !=null) {
				gwtproxy2.getTeamById(result.getIdTeam(), new getTeam());
			}else if (person.getIdUnternehmen() != null){
				gwtproxy2.getUnternehmenById(result.getIdUnternehmen(), new getUnternehmen());
			}
//			adminService.getMarktplatzByPerson(result, new getProjektmarktplatz());
			
		}
	
	}
	
	private class getTeam implements AsyncCallback<Team>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Team der Person konnte nicht geladen werden");		
		}

		@Override
		public void onSuccess(Team result) {
			
			Integer TeamID=result.getId();
			orgEinheit.addItem("Team: "+result.getTeamName(),TeamID.toString());	
			team=result;
			if(person.getIdUnternehmen()!=null){
				
				gwtproxy2.getUnternehmenById(person.getIdUnternehmen(), new getUnternehmen());
			}
			
		}
		
	}
	
	private class getUnternehmen implements AsyncCallback<Unternehmen>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Unternehmen der Person konnte nicht geladen werden");			
		}

		@Override
		public void onSuccess(Unternehmen result) {
			Integer UnternehmenID=result.getId();
			orgEinheit.addItem("Unternehmen: "+result.getFirmenName(),UnternehmenID.toString());
			unternehmen=result;
			}
			
		}


	
}