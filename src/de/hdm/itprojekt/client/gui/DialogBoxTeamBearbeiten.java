package de.hdm.itprojekt.client.gui;

import java.util.Vector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.ListBox;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextArea;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//import de.hdm.itprojekt.client.Navigator;
//import de.hdm.itprojekt.client.Showcase;
//import de.hdm.itprojekt.client.gui.*;
//import de.hdm.itprojekt.shared.GreetingService;
//import de.hdm.itprojekt.shared.GreetingServiceAsync;
//import de.hdm.itprojekt.shared.bo.Person;
//import de.hdm.itprojekt.shared.bo.Team;
//import de.hdm.itprojekt.shared.bo.Unternehmen;

public class DialogBoxTeamBearbeiten extends DialogBox {
	
	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private Person person = new Person();
	private Team team = null;
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Unternehmen unternehmen = null;
	
	private VerticalPanel teamVP = new VerticalPanel();
	private HorizontalPanel teamHP = new HorizontalPanel();
	
	private Button speichernTeam = new Button("team speichern");
	private Button abbrechenTeam = new Button("Abbrechen");
	
	private FlexTable teamBearbeitenTabelle = new FlexTable();
	
	private Label teamNameLabel = new Label("Team ");
	private TextArea teamNameArea = new TextArea();
	private Label teamAdresseLabel = new Label("Adresse ");
	private TextArea teamAdresseArea = new TextArea();
	private Label teamORTLabel = new Label("Standort ");
	private TextArea teamORTArea = new TextArea();	
	private Label teamUnternehmenLabel = new Label("Unternehmen:");	
//	private TextArea teamUntArea = new TextArea();	
	private ListBox lb = new ListBox();
	
	public DialogBoxTeamBearbeiten(final RoleManagement rm, final Navigator navi, Team t){
		this.rm=rm;
		this.navi=navi;
		this.team=t;
		this.setText("Team bearbeiten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		teamNameArea.setValue(team.getTeamName());
		teamAdresseArea.setValue(team.getAdresse());
		teamORTArea.setValue(team.getStandort());
		speichernTeam.setStylePrimaryName("teambtn");
		abbrechenTeam.setStylePrimaryName("teambtn");
		teamHP.add(speichernTeam);
		teamHP.add(abbrechenTeam);
		
		abbrechenTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				hide();
				
			}
			
		});
		gwtproxy.getAllUnternehmen(new getAllUnternehmen());
		
		speichernTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
			
				
				team.setTeamName(teamNameArea.getText());
				team.setAdresse(teamAdresseArea.getText());
				team.setStandort(teamORTArea.getText());
				team.setId(team.getId());
				
			
														
				gwtproxy.saveTeam(team, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						hide();
						Showcase showcase = new TeamSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Team konnte nicht bearbeitet werden");
						
					}
				});
			}
				
			});
			
		
		teamBearbeitenTabelle.setWidget(1, 0, teamNameLabel);
		teamBearbeitenTabelle.setWidget(1, 1, teamNameArea);
		teamBearbeitenTabelle.setWidget(2, 0, teamAdresseLabel);
		teamBearbeitenTabelle.setWidget(2, 1, teamAdresseArea);
		teamBearbeitenTabelle.setWidget(3, 0, teamORTLabel);
		teamBearbeitenTabelle.setWidget(3, 1, teamORTArea);
		teamBearbeitenTabelle.setWidget(4, 0, teamUnternehmenLabel);
		teamBearbeitenTabelle.setWidget(4, 1, lb);
		teamVP.add(teamBearbeitenTabelle);
		teamVP.add(teamHP);
		this.add(teamVP);		
		
	}
	private class getAllUnternehmen implements AsyncCallback<Vector<Unternehmen>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Unternehmen> result) {
			Window.alert("Alle Unternehmen gegettet");
			for (Unternehmen unternehmen : result) {
				lb.addItem(unternehmen.getFirmenName());				
			}
			
		}
		
		
		


	}

}
