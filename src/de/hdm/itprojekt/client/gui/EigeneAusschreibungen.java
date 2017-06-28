package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigeneAusschreibungen extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Ausschreibung> eigeneAusschreibungtabelle = new CellTable<Ausschreibung>();
	final SingleSelectionModel<Ausschreibung> ssmallEigeneAusschreibung = new SingleSelectionModel<Ausschreibung>();
	private Projekt p1 = new Projekt();
	private Person pers1 = new Person();
	private Marktplatz markt1 = new Marktplatz();
	private Organisationseinheit orga = new Organisationseinheit();
	private Ausschreibung ausschr1 = null;
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	
	// Buttons 
	private Button bearbeitenAusschreibung = new Button("Gewähltes Ausschreibung bearbeiten");
	private Button anzeigenAusschreibung = new Button("Gewähltes Ausschreibung anzeigen");
	private Button loeschenAusschreibung = new Button("Gewählte Ausschreibung löschen");
	private Button bewerbungenAnsehen = new Button("Eingeganngene Bewerbungen einsehen"); 
	//Panels 
	private HorizontalPanel  hpanelEigeneAusschreibung = new HorizontalPanel();
	private VerticalPanel vpanelEigeneAusschreibung = new VerticalPanel();
	
	// Konstruktoren
	
	 public EigeneAusschreibungen( Person person) {
		// TODO Auto-generated constructor stub
		 
		 this.pers1= person;
	}
	 public EigeneAusschreibungen(final RoleManagement rm, final Navigator navi, final Projekt projekt){
		 this.rm=rm;
		 this.navi=navi;
		 this.p1=projekt;
	 }
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Ihre eigenen Ausschreibung </h1>";
	}

	@Override
	protected void run() {

		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigeneAusschreibungtabelle.setWidth("100%", true);
		eigeneAusschreibungtabelle.setStylePrimaryName("celltable");
		vpanelEigeneAusschreibung.add(eigeneAusschreibungtabelle);
		
			hpanelEigeneAusschreibung.add(anzeigenAusschreibung);
			hpanelEigeneAusschreibung.add(bearbeitenAusschreibung);
			hpanelEigeneAusschreibung.add(loeschenAusschreibung);
			hpanelEigeneAusschreibung.add(bewerbungenAnsehen);
			
			this.add(hpanelEigeneAusschreibung);
			this.add(vpanelEigeneAusschreibung);
			
			eigeneAusschreibungtabelle.setSelectionModel(ssmallEigeneAusschreibung);
			
			ssmallEigeneAusschreibung.addSelectionChangeHandler(new Handler() {
				
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					// TODO Auto-generated method stub
					ausschr1= ssmallEigeneAusschreibung.getSelectedObject();
				}
			});
			
			TextColumn<Ausschreibung> ausschrBez = new TextColumn<Ausschreibung>(){

				@Override
				public String getValue(Ausschreibung object) {
					// TODO Auto-generated method stub
					return object.getBezeichnung();
				}
			
			
		};
			
			eigeneAusschreibungtabelle.addColumn(ausschrBez, "Bezeichnung ihrer Ausschreibung");
			
			
			gwtproxy.getAusschreibungByAusschreibender(rm.getUser(), new getAusschreibungByLeiterAusDB());
			
			
			
			//ClickHandler 
			
			anzeigenAusschreibung.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Showcase showcase = new EigenschaftAusSeite(markt1,ausschr1, p1, rm, navi);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
			
			bewerbungenAnsehen.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Showcase showcase = new EingegangeneBewerbungenSeite(rm, navi, p1, ausschr1);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
		
	}
	
	private class getAusschreibungByLeiterAusDB implements AsyncCallback<Vector<Ausschreibung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Ausschreibung> result) {
			
			// TODO Auto-generated method stub
			eigeneAusschreibungtabelle.setRowData(0, result);
			eigeneAusschreibungtabelle.setRowCount(result.size(), true);
		}}
}