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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class ProjekteSeite extends Showcase{

	private static final String ssmalleprojektmarktplaetze = null;
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Marktplatz mp = new Marktplatz();
	private Person person = new Person();
	private Organisationseinheit orga = new Organisationseinheit();
	CellTable<Projekt> projekttabelle = new CellTable<Projekt>();
	
	private HorizontalPanel hpanelProjekte = new HorizontalPanel();
	private VerticalPanel vpanelProjekte = new VerticalPanel();
	
	private HorizontalPanel beforeHere = new HorizontalPanel();
	
	private Projekt p1 = new Projekt();
	
	final SingleSelectionModel<Projekt> ssmalleprojekte = new SingleSelectionModel<Projekt>();
	
	private Button anlegenprojekt = new Button("Neues Projekt anlegen");
	
	private Button bearbeitenProjekt = new Button("Gewähltes Projekt bearbeiten");
	
	private Button anzeigenProjekt = new Button("Gewähltes Projekt anzeigen");
	
	private Button loeschenProjekt = new Button("Gewähltes Projekt löschen");

	public ProjekteSeite(){
	
	}
	
	public ProjekteSeite(Marktplatz m1, Person person){
		this.mp = m1;
		this.person = person;
	}
	
	public ProjekteSeite(Projekt p2, Person person){
		this.p1=p2;
		this.person = person;
	}
	
	public ProjekteSeite(Projekt p2, Marktplatz markt,Organisationseinheit o1, Person person ){
		this.p1=p2;
		this.person = person;
		this.orga=o1;
		this.mp=markt;
		person.setId(orga.getId());

	}
		
	public ProjekteSeite(Projekt p2, Marktplatz m1, Person projektLeiter){
		this.mp= m1;
		this.p1=p2;
		this.person= projektLeiter;
		Label lblMarktplatz =  new Label("Sie befinden sich auf folgendem Marktplatz: " +m1.getBezeichnung()+" ");
		Label hilfeBedienung = new Label("   Bitte wählen sie ein Projekt aus um danach darauf zu greifen zu können");
		beforeHere.add(lblMarktplatz);
		beforeHere.add(hilfeBedienung);
		beforeHere.setSpacing(20);
	}
	
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Projekte durchsuchen</h1>";
	}

	@Override
	protected void run() {

		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		projekttabelle.setWidth("100%", true);
		projekttabelle.setStylePrimaryName("celltable");
		vpanelProjekte.add(projekttabelle);
		
		hpanelProjekte.add(anzeigenProjekt);
		hpanelProjekte.add(anlegenprojekt);
		
		//Werden aus gekommentiert da nicht jeder alle Projekte bearbeiten oder l�schen darf 
//		hpanelProjekte.add(bearbeitenProjekt);
//		hpanelProjekte.add(loeschenProjekt);
		
		
		//hpanelProjekte.add(lblMarktplatz);
		this.add(beforeHere);
		this.add(hpanelProjekte);
		this.add(vpanelProjekte);
		
		
		
		projekttabelle.setSelectionModel(ssmalleprojekte);
		
		ssmalleprojekte.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				//WEITERMACHEN FÜR AUSSCHREIBUNG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				p1= ssmalleprojekte.getSelectedObject();
//				Showcase showcase = new AusschreibungSeite(p1);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
				
			}
		});
		
		
		anzeigenProjekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new AusschreibungSeite(p1,mp, person);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		
		
		anlegenprojekt.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxProjektAnlegen(mp, person);
				dialogbox.center();
			}
		});
		
		
		//Werden nicht verwendet da nicht jeder alle Projekte bearbeiten oder l�schen darf 
		bearbeitenProjekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Projekt p1 = ssmalleprojekte.getSelectedObject();
				if (p1 != null){
					DialogBox dialogBoxProjektBearbeiten = new DialogBoxProjektBearbeiten(p1, mp, person);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(dialogBoxProjektBearbeiten);
				
					
				}
			}
		});
		
		
		
	
		//Werden nicht verwendet da nicht jeder alle Projekte bearbeiten oder l�schen darf 
		loeschenProjekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Projekt projekt = ssmalleprojekte.getSelectedObject();
				gwtproxy.loeschenProjekt(projekt, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Fehler beim Löschen");
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Das Projekt wurde erfolgreich gelöscht");
						Showcase showcase = new ProjekteSeite(p1,mp, person);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
			}
		});

		
		TextColumn<Projekt> projektBez = new TextColumn<Projekt>(){

			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
	
		TextColumn<Projekt> projektBeschr = new TextColumn<Projekt>(){
	
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getBeschreibung();
			}
		
		
	};
	
		TextColumn<Projekt> projektStartD = new TextColumn<Projekt>(){
	
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getStartDatum().toString();
			}
		
		
	};

		TextColumn<Projekt> projektEndD = new TextColumn<Projekt>(){
			
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		
	};
	
		projekttabelle.addColumn(projektBez, "Bezeichnung");
		projekttabelle.addColumn(projektBeschr, "Beschreibung");
		projekttabelle.addColumn(projektStartD, "Startdatum");
		projekttabelle.addColumn(projektEndD, "Enddatum");
		gwtproxy.getProjektbyMarktplatz(mp, new getProjekteAusDB());		
		
		
		

		
	}
	
	
	
		private class getProjekteAusDB implements AsyncCallback<Vector<Projekt>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Projekte anzeigen hat nicht funktioniert");
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				// TODO Auto-generated method stub
				projekttabelle.setRowData(0, result);
				projekttabelle.setRowCount(result.size(), true);
			}
			
			
		}

}
