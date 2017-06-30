package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungSeite extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Projekt p1 = new Projekt();
	private Bewerbung bwerb = new Bewerbung();
	private Ausschreibung a1 = new Ausschreibung();
	private Ausschreibung pp1 = new Ausschreibung();
	private Marktplatz mp = new Marktplatz();
	private Eigenschaft eig = new Eigenschaft();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Projekt selectedprojekt = null;
	private Ausschreibung ausschreibung = null;
	private Person projektLeiter = new Person();
	CellTable<Ausschreibung> ausschreibungtabelle = new CellTable<Ausschreibung>();
	//private Label lblPro = new Label("hallo " +p1.getIdMarktplatz() );
	private HorizontalPanel hpanelAusschreibung = new HorizontalPanel();
	private VerticalPanel vpanelAusschreibung = new VerticalPanel();
	private HorizontalPanel beforeHereProjekt = new HorizontalPanel();
	private HorizontalPanel beforeHereMarktplatz = new HorizontalPanel();
	final SingleSelectionModel<Ausschreibung> ssmalleausschreibung = new SingleSelectionModel<Ausschreibung>();
	
	
	private Button anlegenAusschreibung = new Button("Neue Ausschreibung anlegen");
	private Button bewerbenAusschreibung = new Button("Auf diese Ausschreibung bewerben");
	private Button anzeigenAusschreibung = new Button("Qualifikationen anzeigen");
	private Button loeschenAusschreibung = new Button("gewählte Ausschreibung löschen");
	private Button bearbeitenAusschreibung = new Button("gewählte Ausschreibung bearbeiten");
	private Button backToProjekte = new Button("Zurück zu den Projekten");

	
	private Button backtoeigenePro = new Button("zrueckzueigenenProjekten");

	public AusschreibungSeite(final Marktplatz mp, final Projekt selectedprojekt, final Ausschreibung as, final RoleManagement rm, final Navigator navi) {
		this.mp = mp;
		this.selectedprojekt = selectedprojekt;
		this.a1 = as;
		this.rm = rm;
		this.navi = navi;
		this.eig = eig;
		
		}
	
	public AusschreibungSeite(final Marktplatz mp, final Projekt selectedprojekt, final RoleManagement rm, final Navigator navi) {
	this.mp = mp;
	this.selectedprojekt = selectedprojekt;
	this.rm = rm;
	this.navi = navi;
	 Label lblProjekt = new Label("Sie befinden sich auf folgendem Projekt: "+selectedprojekt.getBezeichnung()+" ");
	 Label lblMarktplatz = new Label("Sie befinden sich auf folgendem Marktplatz: "+mp.getBezeichnung());
	 beforeHereProjekt.add(lblMarktplatz);
	 beforeHereProjekt.add(lblProjekt);
	 beforeHereProjekt.setSpacing(20);

	}
	 // konstruktor um fremdschl�ssel zu �bergeben
	 // damit die ausschreibungen zum passenden projekt angezeigt werden 
	 public AusschreibungSeite(Projekt p1, Marktplatz mp1, Person projektLeiter) {
			this.p1=p1;
			this.mp=mp1;
			this.projektLeiter = projektLeiter;
	
	 }
	 
	 public AusschreibungSeite(Bewerbung b){
		 this.bwerb=b;
	 }
	 public AusschreibungSeite(Projekt projekt, Person person){
		 this.p1=projekt;
		 this.projektLeiter=person;
	 }
	 
//	 public AusschreibungSeite(Marktplatz mp1){
//		 this.mp=mp1;
//		 Label lblProjekt = new Label("Sie befinden sich auf folgendem Marktplatz "+mp.getBezeichnung());
//		 beforeHereProjekt.add(lblProjekt);
//			 
//		
//	 }
	 
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Ausschreibung durchsuchen</h1>";
	}

	@Override
	protected void run() {
		
		
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		ausschreibungtabelle.setWidth("100%", true);
		ausschreibungtabelle.setStylePrimaryName("celltable");
		vpanelAusschreibung.add(ausschreibungtabelle);
		hpanelAusschreibung.add(backtoeigenePro);
		hpanelAusschreibung.add(backToProjekte);
		hpanelAusschreibung.add(anzeigenAusschreibung);		
		hpanelAusschreibung.add(bewerbenAusschreibung);
		hpanelAusschreibung.add(bearbeitenAusschreibung);
		hpanelAusschreibung.add(loeschenAusschreibung);
		hpanelAusschreibung.add(anlegenAusschreibung);
	
		this.add(beforeHereMarktplatz);
		this.add(beforeHereProjekt);
		this.add(hpanelAusschreibung);
		this.add(vpanelAusschreibung);
		
		backtoeigenePro.setVisible(false);
		bearbeitenAusschreibung.setVisible(false);
		loeschenAusschreibung.setVisible(false);
		ausschreibungtabelle.setSelectionModel(ssmalleausschreibung);
		
		ssmalleausschreibung.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				// für Eigenschft
				pp1= ssmalleausschreibung.getSelectedObject();
//				Showcase showcase = new EigenschaftAusSeite(ssmalleausschreibung.getSelectedObject());
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
				
			}
		});
		
		TextColumn<Ausschreibung> ausschrBez = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
		
		TextColumn<Ausschreibung> ausschrBeschr = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBeschreibung();
			}
		
		
	};
	
		TextColumn<Ausschreibung> ausschrBefrist = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		
	};
	
		TextColumn<Ausschreibung> ausschrStatus = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getAusschreibungsstatus().toString();
			}
		
		
	};
		TextColumn<Ausschreibung> ppId = new TextColumn<Ausschreibung>(){
			
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getIdPartnerprofil()+"";
			}
		
		
	};
		ausschreibungtabelle.addColumn(ppId, "Id des Partnerprofil");
		ausschreibungtabelle.addColumn(ausschrBez, "Bezeichnung");
		ausschreibungtabelle.addColumn(ausschrBeschr, "Beschreibung");
		ausschreibungtabelle.addColumn(ausschrBefrist,"Bewerbungsfrist");
		ausschreibungtabelle.addColumn(ausschrStatus, "Status der Ausschreibung");
		gwtproxy.getAusschreibungByProjekt(selectedprojekt, new getAusschreibungAusDB());
		
		
		//START der Clickhandler 
		
		
		
		backtoeigenePro.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				Showcase showcase = new EigeneProjekte(projektLeiter);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		
		// Zurück zu den Projekten
		backToProjekte.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new ProjekteSeite(mp, rm, navi);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		
		
		anzeigenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new EigenschaftAusSeite(mp, ssmalleausschreibung.getSelectedObject(), selectedprojekt, rm, navi);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		bearbeitenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Ausschreibung a1 = ssmalleausschreibung.getSelectedObject();
				if (a1 != null){
					DialogBox dialogBoxAusschreibungBearbeiten = new DialogBoxAusschreibungBearbeiten(ssmalleausschreibung.getSelectedObject(),eig, rm, navi, 
							selectedprojekt, mp);
					dialogBoxAusschreibungBearbeiten.center();
				}
				}
		});
		
		
		loeschenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Ausschreibung ausschreibung = ssmalleausschreibung.getSelectedObject();
				gwtproxy.loeschenAusschreibung(ausschreibung, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Fehler beim Löschen der Ausschreibung");
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Die Ausschreibung wurde erfolgreich gelöscht");
						Showcase showcase = new AusschreibungSeite(mp, selectedprojekt, a1, rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
			}
		});
		
		
		anlegenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Window.alert(" Projekt" + p1.getBezeichnung());
				// TODO Auto-generated method stub
				DialogBox dialogbox = new DialogBoxAusschreibungAnlegen(mp, selectedprojekt, rm, navi);
				dialogbox.center();
			}
		});
		
		bewerbenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Ausschreibung a1 = ssmalleausschreibung.getSelectedObject();
				if (a1 != null){
//				Dieser Teil ist für den Button BEWERBUNG FÜR DIESE AUSSCHREIBEN ERSTELLEN
				DialogBox db1 = new DialogBoxBewerbungAnlegen(a1, rm, navi, mp, selectedprojekt);
//				db1.setWidth("100%");
//				RootPanel.get("Anzeige").add(db1);
				db1.center();
				}}
		});
		
		

		
		
	}		
		private class getAusschreibungAusDB implements AsyncCallback<Vector<Ausschreibung>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("leider etwas schief gelaufen");
			}

			@Override
			public void onSuccess(Vector<Ausschreibung> result) {
				// TODO Auto-generated method stub
				ausschreibungtabelle.setRowData(0, result);
				ausschreibungtabelle.setRowCount(result.size(), true);
				
			}
		}
	}

