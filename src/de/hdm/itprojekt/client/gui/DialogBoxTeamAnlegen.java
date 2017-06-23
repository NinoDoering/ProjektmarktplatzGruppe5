package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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

public class DialogBoxTeamAnlegen extends DialogBox {

	//ES FOLGEN NOCH ÄNDERUNGEN bzw. FEHLERBEHEBUNG-> morgen

	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Person person = new Person ();
	private Unternehmen unter = new Unternehmen();
	private Team team = new Team();
	private FlexTable teamTable = new FlexTable();
	private HorizontalPanel teamHP = new HorizontalPanel();
	private VerticalPanel teamVP = new VerticalPanel();
	
	private Button speichernTeam = new Button("Team Speichern");
	private Button abbrechenTeam = new Button("Abbrechen");
	
	private Label teamNameLabel = new Label("Team ");
	private TextArea teamNameArea = new TextArea();
	
	private Label teamAnzahlLabel = new Label("Mitgliederanzahl ");
	private TextArea teamAnzahlArea = new TextArea();
	
	private Label teamUnternehemenLabel = new Label("idUnternehmen");
	private TextArea teamUnternehemenArea = new TextArea();
	
	
	public DialogBoxTeamAnlegen(final Person person){
		teamNameArea.setValue(person.getIdTeam()+"");
		
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Team");
		speichernTeam.setStylePrimaryName("teambtn");
		abbrechenTeam.setStylePrimaryName("teambtn");
		
		
		
		teamHP.add(speichernTeam);
		teamHP.add(abbrechenTeam);
		
		abbrechenTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new PersonSeite(person);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
			
		});
		
		speichernTeam.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(teamNameArea.getText().isEmpty()){
					Window.alert("Bitte Geben Sie einen Team-ID an ein");
					
				}else {
					person.setIdTeam(Integer.parseInt(teamNameArea.getText()));
					gwtproxy.savePerson(person, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("TEAMADD GEHT nicht");
						}

						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							Window.alert("team geändert");
							Showcase showcase = new PersonSeite(person);
							RootPanel.get("Anzeige").clear();
							RootPanel.get("Anzeige").add(showcase);
						}
					});
				}
			}
			
		});
		
		teamTable.setWidget(1, 0, teamNameLabel);
		teamTable.setWidget(1, 1, teamNameArea);
		teamTable.setWidget(2, 0, teamAnzahlLabel);
		teamTable.setWidget(2, 1, teamAnzahlArea);
		teamTable.setWidget(3, 0, teamUnternehemenLabel);
		teamTable.setWidget(3, 1, teamUnternehemenArea);
		
		teamVP.add(teamTable);
		teamVP.add(teamHP);
		this.add(teamVP);
	}}
	
