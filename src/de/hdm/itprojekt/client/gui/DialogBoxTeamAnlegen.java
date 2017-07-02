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

/**
 * Diese Klasse ist dafür zustaendig, dass sich eine Person einem Team
 * zuordnen kann. Eine Person kann sich einem Team hinzufuegen, sobald
 * diese auf die Funktion "Mein Profil" klickt und die Funktion "Team
 * hinzufügen" auswählt. Die Teams stehen der Person dann zur Auswahl. Das
 * Team kann von einer Person jederzeit gewechselt werden.
 */
public class DialogBoxTeamAnlegen extends DialogBox {
	
	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Person person = new Person ();
	private Unternehmen u = new Unternehmen();
	private Team t = new Team();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private FlexTable teamTable = new FlexTable();
	private HorizontalPanel teamHP = new HorizontalPanel();
	private VerticalPanel teamVP = new VerticalPanel();
	private ListBox lb = new ListBox();
	private Button speichernTeam = new Button("Team Speichern");
	private Button abbrechenTeam = new Button("Abbrechen");
	
	private Label teamNameLabel = new Label("Team ");
	private TextArea teamNameArea = new TextArea();
	
	private Label teamAdresseLabel = new Label("Adresse: ");
	private TextArea teamAdresseArea = new TextArea();
	
	private Label teamORTLabel = new Label("Standort: ");
	private TextArea teamORTArea = new TextArea();
	private Label teamUnternehmenLabel = new Label("Unternehmen:");
	
	public DialogBoxTeamAnlegen(final RoleManagement rm, final Navigator navi){
//		teamNameArea.setValue("");
		
		this.rm=rm;
		this.navi=navi;
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
				hide();
			}
			
		});
		gwtproxy.getAllUnternehmen(new getAllUnternehmen());

		
		
		
		speichernTeam.addClickHandler(new ClickHandler(){

			@Override
	public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
				
		gwtproxy.anlegenPartnerprofil(new AsyncCallback<Partnerprofil>(){

						@Override
						public void onFailure(Throwable caught) {
							
							
						}

					@Override
			public void onSuccess(final Partnerprofil result1) {
						
						Window.alert("ppID ist:" + result1.getId());
						lb.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
//								String name = lb.getSelectedItemText();
								
							}});	
						String name= lb.getSelectedItemText();
				//						String name1 = lb.getSelectedItemText();
				gwtproxy.getUnternehmenByFirmenName(name= lb.getSelectedItemText(), new AsyncCallback<Unternehmen>() {
					
					@Override
					public void onFailure(Throwable caught) {
						
						
					}
							@Override
							public void onSuccess (Unternehmen result2) {
								
								u.setId(result2.getId());
																		
								
						
				gwtproxy.anlegenTeam(u.getId(), result1.getId(), teamNameArea.getText(), teamAdresseArea.getText(), teamORTArea.getText(), new AsyncCallback<Team>() {
							
					@Override
					public void onFailure(Throwable caught) {
						
						hide();
					}
							@Override
							public void onSuccess(Team result) {
								Window.alert("team " +teamNameArea.getText()+  " wurde dem Unternehmen "+ u.getFirmenName()+ " hinzugefügt");
								Showcase sc = new TeamSeite(rm,navi);
								RootPanel.get("Anzeige").clear();
								RootPanel.get("Anzeige").add(sc);
								hide();
							}
							
							
						});
							
		
					}
				
					
					
			});}});}});
			
			
		
		
		teamTable.setWidget(1, 0, teamNameLabel);
		teamTable.setWidget(1, 1, teamNameArea);
		teamTable.setWidget(2, 0, teamAdresseLabel);
		teamTable.setWidget(2, 1, teamAdresseArea);
		teamTable.setWidget(3, 0, teamORTLabel);
		teamTable.setWidget(3, 1, teamORTArea);
		teamTable.setWidget(4, 0, teamUnternehmenLabel);
		teamTable.setWidget(4, 1, lb);
		
		teamVP.add(teamTable);
		teamVP.add(teamHP);
		this.add(teamVP);
	}

//if(teamNameArea.getText().isEmpty()){
//	Window.alert("Bitte Geben Sie einen Team-ID an ein");
//	
//}else {
//	person.setIdTeam(Integer.parseInt(teamNameArea.getText()));
//	gwtproxy.savePerson(person, new AsyncCallback<Void>() {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			Window.alert("TEAMADD GEHT nicht");
//		}
//
//		@Override
//		public void onSuccess(Void result) {
//			// TODO Auto-generated method stub
//			Window.alert("team geändert");
//			Showcase showcase = new TeamSeite(rm, navi);
//			RootPanel.get("Anzeige").clear();
//			RootPanel.get("Anzeige").add(showcase);
//		}
//	});
//}

private class getAllUnternehmen implements AsyncCallback<Vector<Unternehmen>>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Vector<Unternehmen> result) {
		for (Unternehmen unternehmen : result) {
			lb.addItem(unternehmen.getFirmenName());
			
		}
		
	}
	
	
	


}}