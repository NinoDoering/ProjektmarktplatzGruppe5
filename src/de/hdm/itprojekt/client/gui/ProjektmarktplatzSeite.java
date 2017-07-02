package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

/**
 * Diese Seite ist dafuer zustaendig, Marktplaetze anzeigen zu lassen. Ebenso
 * kann man auf der Seite Marktplaetze anlegen oder loeschen. Erreichbar ist
 * diese Seite ueber den Navigator und ueber den Button "Projektmarktplaetze"
 */
public class ProjektmarktplatzSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Marktplatz> marktplatztabelle = new CellTable<Marktplatz>();
	
	HorizontalPanel hpanelMarktplatz = new HorizontalPanel();
	VerticalPanel vpanelMarktplatz = new VerticalPanel();
	
	private Marktplatz m1= new Marktplatz();

	final SingleSelectionModel<Marktplatz> ssmalleprojektmarktplaetze = new SingleSelectionModel<Marktplatz>();
	
	private Button anlegenbutton = new Button("Neuen Markplatz anlegen");
	private Button auswaehlenMarktplatz = new Button("Projektmarktplatz anzeigen");
	private Button deleteMarktplatz = new Button("Marktplatz Löschen");
	private Button updateMarktplatz = new Button("Marktplatz bearbeiten");
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Marktplatz durchsuchen</h1>";
	}
	private Person person = new Person();
	public ProjektmarktplatzSeite(Person person){
		this.person = person;
	}
	public ProjektmarktplatzSeite(RoleManagement rm, Navigator navi) {
		this.rm = rm;
		this.navi = navi;
	}
	@Override
	protected void run() {
		
		RootPanel.get("Anzeige").setWidth("100%");
		
		marktplatztabelle.setWidth("100%", true);
		marktplatztabelle.setStylePrimaryName("celltable");
		vpanelMarktplatz.add(marktplatztabelle);
		hpanelMarktplatz.add(auswaehlenMarktplatz);
		hpanelMarktplatz.add(anlegenbutton);
		hpanelMarktplatz.add(updateMarktplatz);
		hpanelMarktplatz.add(deleteMarktplatz);
		this.add(hpanelMarktplatz);
		this.add(vpanelMarktplatz);
		
		
		
		marktplatztabelle.setSelectionModel(ssmalleprojektmarktplaetze);
		
		ssmalleprojektmarktplaetze.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				m1 = ssmalleprojektmarktplaetze.getSelectedObject();
//				Showcase showcase = new ProjekteSeite(m1, rm, navi);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
				
			}
		});
		
		
		
		TextColumn<Marktplatz> marktplatztabellespaltenname = new TextColumn<Marktplatz>(){

			@Override
			public String getValue(Marktplatz object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
			
		};
		
		TextColumn<Marktplatz> marktplatztabellegeschaeftsgebiet = new TextColumn<Marktplatz>(){

			@Override
			public String getValue(Marktplatz object) {
				// TODO Auto-generated method stub
				return object.getGeschaeftsgebiet();
			}
			
		};
	
		
		marktplatztabelle.addColumn(marktplatztabellespaltenname, "Bezeichnung");
		marktplatztabelle.addColumn(marktplatztabellegeschaeftsgebiet, "Geschäftsgebiet");
		gwtproxy.getAllMarktplaetze(new getProjektmarktplatzAusDB());
		
		
		//start der Clickhandler
		
		auswaehlenMarktplatz.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjekteSeite(m1, rm, navi);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
			}
			
		});
		
		anlegenbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxProjektmarktplatzAnlegen(rm, navi);
				dialogbox.center();
			}
		});
		
		updateMarktplatz.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				DialogBox dialogbox = new DialogBoxMarktplatzBearbeiten(m1, rm, navi);
				dialogbox.center();
				
				
				
			}
		});
		
		deleteMarktplatz.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				gwtproxy.loeschenMarktplatz(m1, new AsyncCallback<Void>() {
					
					@Override
					public void onSuccess(Void result) {
						Showcase showcase = new ProjektmarktplatzSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
						Window.alert("id von marktplatz ist: " + m1.getId());
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("nicht funktioniert");
						
					}
				});
				
			}
			
		});
		
	}
		public class getProjektmarktplatzAusDB implements AsyncCallback<Vector<Marktplatz>>{
		
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Hat nicht funktioniert");
			}
		
			@Override
			public void onSuccess(Vector<Marktplatz> result) {
				marktplatztabelle.setRowData(0, result);
				marktplatztabelle.setRowCount(result.size(), true);
			
			}
			
		}
		}
