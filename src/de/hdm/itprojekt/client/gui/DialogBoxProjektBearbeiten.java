package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Showcase;
//import de.hdm.itprojekt.client.gui.DialogBoxProjektAnlegen.projektinDB;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

public class DialogBoxProjektBearbeiten extends DialogBox {
private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	VerticalPanel projektVP = new VerticalPanel();
	HorizontalPanel projektHP = new HorizontalPanel();
	private Marktplatz mp2 = new Marktplatz();
	private Projekt p1 = new Projekt();
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	
	Label projektbezeichnung = new Label("Projektbezeichnung");
	TextArea projektbez = new TextArea();
	
	Label projektbeschreibung = new Label("Projektbeschreibung");
	TextArea projektbeschr = new TextArea();
	
	Label startdatum = new Label("Startdatum des Projektes");
	DatePicker startD = new DatePicker();
	Label textStart = new Label();
	
	
	Label enddatum = new Label("Enddatum des Projektes");
	DatePicker endD = new DatePicker();
	final Label textEnde = new Label();
	//!!!!!!!!!!!!!!! f�r test Person ID manuell eingeben !!!!!!!!!!!!!!!!!!!!!!!!
	Label projektleiter = new Label("ID des zugeh�rigen Projektleiters");
	TextBox proLeit	= new TextBox();
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	FlexTable projektdialogboxtabelle = new FlexTable(); 
	
	public DialogBoxProjektBearbeiten(final Projekt selectedObject){
			this.setText("Projektmarktplatz anlegen");
			this.setAnimationEnabled(false);
			this.setGlassEnabled(true);
			this.p1 = selectedObject;
		
		
		projektHP.add(ok);
		projektHP.add(abbrechen);
		
		
		ok.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						if(projektbez.getText().isEmpty()){
							Window.alert("Bitte Geben Sie einen Projektmarktplatznamen ein");	
						}
						else{
							gwtproxy.saveProjekt(p1, new projektBearbeiten());
						}
					}
				});
		
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new ProjekteSeite();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		
		projektdialogboxtabelle.setWidget(1, 0, projektbezeichnung);
		projektdialogboxtabelle.setWidget(1, 1, projektbez);
		projektdialogboxtabelle.setWidget(2, 0, projektbeschreibung);
		projektdialogboxtabelle.setWidget(2, 1, projektbeschr);
		projektdialogboxtabelle.setWidget(3, 0, startdatum);
		projektdialogboxtabelle.setWidget(3, 1, startD);
		projektdialogboxtabelle.setWidget(4, 0, enddatum);
		projektdialogboxtabelle.setWidget(4, 1, endD);
		projektdialogboxtabelle.setWidget(5, 0, projektleiter);
		projektdialogboxtabelle.setWidget(5, 1, proLeit);
		
		
		projektVP.add(projektdialogboxtabelle);
		projektVP.add(projektHP);
		this.add(projektVP);
		
		
		

	}
	
	private class projektBearbeiten implements  AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			Window.alert("Veränderung wurden gespeichert !");
			Showcase showcase = new ProjekteSeite(mp2);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase); 
		}

		
		
		
		
	
}}
