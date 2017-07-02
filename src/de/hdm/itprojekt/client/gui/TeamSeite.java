package de.hdm.itprojekt.client.gui;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;

/**
 * Diese Klasse ist dafuer zustaendig, das Team verwalten zu koennen. Eine
 * Person kann auf dieser Seite entweder einem Team beitreten, das Team
 * bearbeiten, anlegen oder löschen. Die Seite ist ueber den Button "Team
 * bearbeiten" auf der eigenen Profilseite zu erreichen.
 */
public class TeamSeite extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Team> teamtabelle = new CellTable<Team>();
	
	private HorizontalPanel hpanelTeam = new HorizontalPanel();
	private VerticalPanel vpanelTeam = new VerticalPanel();
	private Team t1 = null;
	private Team team2 = null;
	private Person p1 = null;
	private Partnerprofil pp = new Partnerprofil();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Button teamBeitreten = new Button("Team beitreten");
	private Button teamBearbeiten = new Button("Team bearbeiten");
	private Button teamAnlegen = new Button("Team Anlegen");
	private Button teamLoeschen = new Button("Team löschen");
	final SingleSelectionModel<Team> ssmallteams = new SingleSelectionModel<Team>();
	
	public TeamSeite(final RoleManagement rm, final Navigator navi){
		this.rm=rm;
		this.navi=navi;
		
		
	}
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Wählen sie ein Team aus </h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		teamtabelle.setWidth("100%", true);
		teamtabelle.setStylePrimaryName("celltable");
		vpanelTeam.add(teamtabelle);
		hpanelTeam.add(teamAnlegen);
		hpanelTeam.add(teamBearbeiten);
		hpanelTeam.add(teamBeitreten);
		hpanelTeam.add(teamLoeschen);
		
		this.add(vpanelTeam);
		this.add(hpanelTeam);
		teamtabelle.setSelectionModel(ssmallteams);

		ssmallteams.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
			t1 = ssmallteams.getSelectedObject();
			}});
		TextColumn<Team> teamName = new TextColumn<Team>(){

			@Override
			public String getValue(Team object) {
				// TODO Auto-generated method stub
				return object.getTeamName();
			}
		
		
	};
	
	TextColumn<Team> standort = new TextColumn<Team>() {

		@Override
		public String getValue(Team object) {
			// TODO Auto-generated method stub
			return object.getStandort();
		}
			
	
	};
	
	
	TextColumn<Team> adresse = new TextColumn<Team>() {

		@Override
		public String getValue(Team object) {
			// TODO Auto-generated method stub
			return object.getAdresse();
		}
		
		
	
	};
	teamBearbeiten.addClickHandler( new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
//			Window.alert("idteam" + t1.getTeamName()+ "rm: "+ rm.getUser()+ "navi: " + navi.getTitle());
			team2 = ssmallteams.getSelectedObject();
			DialogBox dialogbox = new DialogBoxTeamBearbeiten(rm, navi, team2);
			dialogbox.center();
			
		}
		
	});
	
	teamLoeschen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			team2 = ssmallteams.getSelectedObject();
			if (rm.getUser().getIdTeam() == null){
				rm.getUser().setIdUnternehmen(0);
			}
			gwtproxy.getPartnerprofilByOrganisationseinheit(rm.getUnternehmenOfUser(), new AsyncCallback<Partnerprofil>(){
				
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("pp wurde nicht gefunden");
					
				}
	
				@Override
				public void onSuccess(Partnerprofil result) {
					// TODO Auto-generated method stub
					pp=result;
					team2.setIdPartnerprofil(result.getId());
//					t1.setIdPartnerprofil(pp.getId());
					Window.alert("uId: "+team2.getId());
					Window.alert("pp id: "+result.getId());
					gwtproxy.savePersonPers(rm.getUser(), new AsyncCallback<Person>(){
	
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("savepersonpers ging nicht");
							
						}
	
						@Override
						public void onSuccess(Person result) {
							gwtproxy.loeschenTeamInteger(team2.getId(), new AsyncCallback<Void>(){
	
								@Override
								public void onFailure(Throwable caught) {
									Window.alert("LoeschenTeamInteger ging nicht");
									
								}
	
								@Override
								public void onSuccess(Void result) {
									// TODO Auto-generated method stub
									gwtproxy.loeschenPartnerprofil(pp, new AsyncCallback<Void>(){
	
										@Override
										public void onFailure(Throwable caught) {
											Window.alert("pploeschen ging nicht");
											
										}
	
										@Override
										public void onSuccess(Void result) {
											Window.alert("bis187");
											Showcase sc = new TeamSeite(rm, navi);
											RootPanel.get("Anzeige").clear();
											RootPanel.get("Anzeige").add(sc);
										}
										
									});
								}});}});}});
			
		}
	});
	
	
	
	
	
	teamBeitreten.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			t1 = ssmallteams.getSelectedObject();
			ssmallteams.getSelectedObject().getId();
			rm.getUser().setIdTeam(ssmallteams.getSelectedObject().getId());
		
				gwtproxy.savePerson(rm.getUser(), new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("TEAM-ADD ging nicht");
					}

					@Override
					public void onSuccess(Void result) {
						
						
						// TODO Auto-generated method stub
						Window.alert("team geändert");
						Showcase showcase = new PersonSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
			}
			
		});
	teamAnlegen.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxTeamAnlegen(rm, navi);
				dialogbox.center();
			
		}
		
	});
		teamtabelle.addColumn(teamName, "Team-Name");
		teamtabelle.addColumn(standort, "Standort");
		teamtabelle.addColumn(adresse, "Adresse");
		gwtproxy.getAllTeams(new getAlleTeamsausDB());
}
		
		private class getAlleTeamsausDB implements AsyncCallback<Vector<Team>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim Abfragen");
			}

			@Override
			public void onSuccess(Vector<Team> result) {
				// TODO Auto-generated method stub
				teamtabelle.setRowData(0, result);
				teamtabelle.setRowCount(result.size(), true);
			}
			
		}}

