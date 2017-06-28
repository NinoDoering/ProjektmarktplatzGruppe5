package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;

public class DialogBoxTeamBearbeiten extends DialogBox {
	
	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private Person person = new Person();
	private Team team = new Team();
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	private VerticalPanel teamVP = new VerticalPanel();
	private HorizontalPanel teamHP = new HorizontalPanel();
	
	private Button speichernTeam = new Button("team speichern");
	private Button abbrechenTeam = new Button("Abbrechen");
	
	private FlexTable teamBearbeitenTabelle = new FlexTable();
	
	private Label teamNameLabel = new Label("Team ");
	private TextArea teamNameArea = new TextArea();
	private Label teamAnzahlLabel = new Label("Mitgliederanzahl ");
	private TextArea teamAnzahlArea = new TextArea();
	private Label teamUnternehmenLabel = new Label("idUnternehmen");
	private TextArea teamUnternehmenArea = new TextArea();	
	
	public DialogBoxTeamBearbeiten(final RoleManagement rm, final Navigator navi){
		this.rm=rm;
		this.navi=navi;
		this.setText("Team bearbeiten");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		
		speichernTeam.setStylePrimaryName("teambtn");
		abbrechenTeam.setStylePrimaryName("teambtn");
		teamHP.add(speichernTeam);
		teamHP.add(abbrechenTeam);
		
		abbrechenTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		speichernTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		teamBearbeitenTabelle.setWidget(1, 0, teamNameLabel);
		teamBearbeitenTabelle.setWidget(1, 1, teamNameArea);
		teamBearbeitenTabelle.setWidget(3, 0, teamAnzahlLabel);
		teamBearbeitenTabelle.setWidget(3, 1, teamAnzahlArea);
		teamBearbeitenTabelle.setWidget(4, 0, teamUnternehmenLabel);
		teamBearbeitenTabelle.setWidget(4, 1, teamUnternehmenArea);
		teamVP.add(teamBearbeitenTabelle);
		teamVP.add(teamHP);
		this.add(teamVP);		
		
	}
	

}
