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

public class ProjektmarktplatzSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Marktplatz> marktplatztabelle = new CellTable<Marktplatz>();
	
	HorizontalPanel hpanelMarktplatz = new HorizontalPanel();
	VerticalPanel vpanelMarktplatz = new VerticalPanel();
	
	private Marktplatz m1= new Marktplatz();

	final SingleSelectionModel<Marktplatz> ssmalleprojektmarktplaetze = new SingleSelectionModel<Marktplatz>();
	
	Button anlegenbutton = new Button("Neuen Markplatz anlegen");
	
	String deleteMarktplatz = new String("Löschen");
	Button deleteM	 = new Button();
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Marktplatz durchsuchen</h1>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Anzeige").setWidth("100%");
		
		marktplatztabelle.setWidth("100%", true);
		
		vpanelMarktplatz.add(marktplatztabelle);
		hpanelMarktplatz.add(anlegenbutton);
		this.add(hpanelMarktplatz);
		this.add(vpanelMarktplatz);
		
		marktplatztabelle.setSelectionModel(ssmalleprojektmarktplaetze);
		
		ssmalleprojektmarktplaetze.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				m1 = ssmalleprojektmarktplaetze.getSelectedObject();
				Showcase showcase = new ProjekteSeite(m1);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
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
		anlegenbutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxProjektmarktplatzAnlegen();
				dialogbox.center();
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
				Window.alert("Funktioniert");
			}
			
		}
		}
