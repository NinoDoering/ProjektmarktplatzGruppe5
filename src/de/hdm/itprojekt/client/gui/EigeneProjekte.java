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
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigeneProjekte extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	
	CellTable<Projekt> eigeneprojektetabelle = new CellTable<Projekt>();

	private Organisationseinheit o = new Organisationseinheit();
	final SingleSelectionModel<Projekt> ssmallEigeneProjekte = new SingleSelectionModel<Projekt>();
	private Projekt p1 = new Projekt();
	private Person pers1 = new Person();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Marktplatz markt1 = new Marktplatz();
	private Organisationseinheit orga = new Organisationseinheit();
	
	//Buttons

	private Button bearbeitenProjekt = new Button("Gewähltes Projekt bearbeiten");
	
	private Button anzeigenProjekt = new Button("Gewähltes Projekt anzeigen");
	
	private Button loeschenProjekt = new Button("Gewähltes Projekt löschen");
	
	//Panels 
	private HorizontalPanel hpanelEigeneProjekte = new HorizontalPanel();
	private VerticalPanel vpanelEigeneProjekte = new VerticalPanel();
	
	 public EigeneProjekte(final Organisationseinheit o1) {
		// TODO Auto-generated constructor stub
		
		 //Damit man von den Ausschreibungen zurück zu den eigenen Ausschreibungen kommt.
		 this.o=o1;
		Person person = new Person();
		person.setId(o1.getId());
		pers1 = person;
	}
	
	 public EigeneProjekte(final Projekt projekt, final Person person) {
			// TODO Auto-generated constructor stub
			 this.p1= projekt;
			 this.pers1= person;
		}
	 public EigeneProjekte(RoleManagement rm,  Navigator navi) {
			// TODO Auto-generated constructor stub
		 this.rm=rm;
		 this.navi=navi;
		}
	
	
	 public EigeneProjekte(final  Marktplatz mp,  final RoleManagement rm, final Navigator navi) {
			// TODO Auto-generated constructor stub
			 this.markt1=mp;
			 this.rm=rm;
			 this.navi=navi;
		}
	 
	 public EigeneProjekte(final Marktplatz mp2, final RoleManagement rm, final Navigator navi, final Projekt p){
		 this.markt1=mp2;
		 this.rm=rm;
		 this.navi=navi;
		 this.p1=p;
	
	 }
	 
	public EigeneProjekte(Projekt p12, Marktplatz markt12, Organisationseinheit o2, Person pers12) {
		// TODO Auto-generated constructor stub
		 this.p1= p12;
		 this.markt1=markt12;
		 this.o=o2;
		 this.pers1= pers12;
	
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Ihre eigenen Projekte </h1>";
	}


	@Override
	protected void run() {
		
		
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigeneprojektetabelle.setWidth("100%", true);
		eigeneprojektetabelle.setStylePrimaryName("celltable");
		vpanelEigeneProjekte.add(eigeneprojektetabelle);
	
				//CRUD Buttons den Panel adden
				hpanelEigeneProjekte.add(anzeigenProjekt);
				hpanelEigeneProjekte.add(bearbeitenProjekt);
				hpanelEigeneProjekte.add(loeschenProjekt);
		
		
		this.add(hpanelEigeneProjekte);
		this.add(vpanelEigeneProjekte);
		
		eigeneprojektetabelle.setSelectionModel(ssmallEigeneProjekte);
		
		ssmallEigeneProjekte.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				p1 = ssmallEigeneProjekte.getSelectedObject();
			}
		});
		
		
		
		
		
		
		// Anzeige Button um Ausschreibungen anzuzeigen
		anzeigenProjekt.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						Showcase showcase = new AusschreibungSeite(p1, pers1);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
		
		
		// Update Button
	bearbeitenProjekt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
		
			
				Projekt p1 = ssmallEigeneProjekte.getSelectedObject();
				if (p1 != null){
					DialogBox dialogBoxProjektBearbeiten = new DialogBoxEigeneProjekteBearbeiten(p1,markt1, rm, navi);
//					RootPanel.get("Anzeige").clear();
//					RootPanel.get("Anzeige").add(dialogBoxProjektBearbeiten);
					dialogBoxProjektBearbeiten.center();					
				}
			}
		});
		
		
		
	loeschenProjekt.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Projekt projekt = ssmallEigeneProjekte.getSelectedObject();
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
					Showcase showcase = new EigeneProjekte(o);
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
	
			eigeneprojektetabelle.addColumn(projektBez, "Bezeichnung");
			eigeneprojektetabelle.addColumn(projektBeschr, "Beschreibung");
			eigeneprojektetabelle.addColumn(projektStartD, "Startdatum");
			eigeneprojektetabelle.addColumn(projektEndD, "Enddatum");
			gwtproxy.getProjektByPerson(rm.getUser(), new getEigeneProjekte());
	}
	
			private class getEigeneProjekte implements AsyncCallback<Vector<Projekt>>{

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert(" getEigeneProjekte Hat nicht funktioniert"); 
				}

				@Override
				public void onSuccess(Vector<Projekt> result) {
					// TODO Auto-generated method stub
//					Window.alert("Funktioniert");
					eigeneprojektetabelle.setRowData(0, result);
					eigeneprojektetabelle.setRowCount(result.size(), true);
				}

		
				}
	}
			
	

