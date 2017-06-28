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
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.itprojekt.server.*;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.*;

public class EingegangeneBewerbungenSeite extends Showcase{
	
	
	
	//FUNKTIONIERT NOCH NICHT
	
//	VerticalPanel vpanel = new VerticalPanel();
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Bewerbung> externeBewerbungentabelle = new CellTable<Bewerbung>();
	
	HorizontalPanel hpanelexternBewerbung = new HorizontalPanel();
	VerticalPanel vpanelexternBewerbung = new VerticalPanel();
	private Organisationseinheit o1 = new Organisationseinheit();
	private Bewerbung b1 = new Bewerbung();
	private Projekt projekt = new Projekt(); 
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Ausschreibung ausschreibung = new Ausschreibung(); 	
	private Bewerbung bewerbung = null;
	private Button bewerten = new Button("Bewerbung bewerten");
	
	final SingleSelectionModel<Bewerbung> ssmalleExterneBerwerbungen = new SingleSelectionModel<Bewerbung>();
	
	String deleteBewerbung = new String("L�schen");
	Button deleteBew	 = new Button();
	
	 public EingegangeneBewerbungenSeite(final RoleManagement rm, final Navigator navi, final Projekt projekt, final Ausschreibung aus1) {
		// TODO Auto-generated constructor stub
		 this.rm=rm;
		 this.navi=navi;
		 this.projekt=projekt;
		 this.ausschreibung=aus1;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Eingegangene Bewerbungen diese Ausschreibung</h1>";
	}
	

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		RootPanel.get("Anzeige").setWidth("100%");
		
		externeBewerbungentabelle.setWidth("100%", true);
		externeBewerbungentabelle.setWidth("100%", true);
		externeBewerbungentabelle.setStylePrimaryName("celltable");
		vpanelexternBewerbung.add(externeBewerbungentabelle);
		hpanelexternBewerbung.add(bewerten);
		this.add(hpanelexternBewerbung);
		this.add(vpanelexternBewerbung);
		
		externeBewerbungentabelle.setSelectionModel(ssmalleExterneBerwerbungen);
		
		ssmalleExterneBerwerbungen.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				
				bewerbung=ssmalleExterneBerwerbungen.getSelectedObject();
				
		}
		});
		
		TextColumn<Bewerbung> bewerbungtabellebewerbungstext = new TextColumn<Bewerbung>(){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getBewerbungsText();
			}
			
		};
		
		TextColumn<Bewerbung> bewerbungsdatum = new TextColumn<Bewerbung>(){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getErstellDatum().toString();
			}
			
		};
	
		bewerten.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				DialogBox dialogBoxBewertung = new DialogBoxBeteiligung();
				dialogBoxBewertung.center();
			}
		});
		
		externeBewerbungentabelle.addColumn(bewerbungtabellebewerbungstext, "Bewerbungstext");
		externeBewerbungentabelle.addColumn(bewerbungsdatum, "Datum der Bewerbung");
		gwtproxy.getBewerbungByAusschreibung(ausschreibung, new getExterneBewerbungenAusDB());
		
	
		
	}
	
	private class getExterneBewerbungenAusDB implements AsyncCallback<Vector<Bewerbung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			// TODO Auto-generated method stub
			externeBewerbungentabelle.setRowData(0, result);
			externeBewerbungentabelle.setRowCount(result.size(), true);
		}

	
		
		
		
	}
	
	}
	
	
	
	
	

