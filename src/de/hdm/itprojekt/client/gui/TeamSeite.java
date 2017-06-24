package de.hdm.itprojekt.client.gui;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
public class TeamSeite extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Team> teamtabelle = new CellTable<Team>();
	
	private HorizontalPanel hpanelTeam = new HorizontalPanel();
	private VerticalPanel vpanelTeam = new VerticalPanel();
	private Team t1 = new Team();
	private Person p1 = new Person();
	final SingleSelectionModel<Team> ssmallteams = new SingleSelectionModel<Team>();
	
	public TeamSeite(final Person person){
		this.p1 = person;
		
		
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
		
		this.add(vpanelTeam);
		
		teamtabelle.setSelectionModel(ssmallteams);

		ssmallteams.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				t1 = ssmallteams.getSelectedObject();
				ssmallteams.getSelectedObject().getId();
				p1.setIdTeam(ssmallteams.getSelectedObject().getId());
			
					gwtproxy.savePerson(p1, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("TEAM-ADD ging nicht");
						}

						@Override
						public void onSuccess(Void result) {
							
							
							// TODO Auto-generated method stub
							Window.alert("team geändert");
							Showcase showcase = new PersonSeite(p1);
							RootPanel.get("Anzeige").clear();
							RootPanel.get("Anzeige").add(showcase);
						}
					});
				}
			
	});
		TextColumn<Team> teamName = new TextColumn<Team>(){

			@Override
			public String getValue(Team object) {
				// TODO Auto-generated method stub
				return object.getTeamName();
			}
		
		
	};

		teamtabelle.addColumn(teamName, "Team-Name");
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

