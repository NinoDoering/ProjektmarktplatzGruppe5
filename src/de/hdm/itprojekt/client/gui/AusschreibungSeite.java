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

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AusschreibungSeite extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Projekt p1 = new Projekt();
	private Bewerbung bwerb = new Bewerbung();
	private Ausschreibung a1 = new Ausschreibung();
	private Ausschreibung pp1 = new Ausschreibung();
	private Marktplatz mp = new Marktplatz();
	CellTable<Ausschreibung> ausschreibungtabelle = new CellTable<Ausschreibung>();
	//private Label lblPro = new Label("hallo " +p1.getIdMarktplatz() );
	private HorizontalPanel hpanelAusschreibung = new HorizontalPanel();
	private VerticalPanel vpanelAusschreibung = new VerticalPanel();
	private HorizontalPanel beforeHereProjekt = new HorizontalPanel();
	private HorizontalPanel beforeHereMarktplatz = new HorizontalPanel();
	final SingleSelectionModel<Ausschreibung> ssmalleausschreibung = new SingleSelectionModel<Ausschreibung>();
	
	
	Button anlegenAusschreibung = new Button("Neue Ausschreibung anlegen");
	Button bewerbenAusschreibung = new Button("Auf diese Ausschreibung bewerben");

	 public AusschreibungSeite() {
		
	}
	 // konstruktor um fremdschl�ssel zu �bergeben
	 // damit die ausschreibungen zum passenden projekt angezeigt werden 
	 public AusschreibungSeite(Projekt p1) {
			this.p1=p1;
			Label lblProjekt = new Label("Sie befinden sich auf folgendem Projekt: "+p1.getBezeichnung()+" ");
			beforeHereProjekt.add(lblProjekt);
	 }
	 
	 public AusschreibungSeite(Bewerbung b){
		 this.bwerb=b;
	 }
	 
	 public AusschreibungSeite(Marktplatz mp1){
		 this.mp=mp1;
			 
		
	 }
	 
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
		vpanelAusschreibung.add(ausschreibungtabelle);
		hpanelAusschreibung.add(anlegenAusschreibung);
		hpanelAusschreibung.add(bewerbenAusschreibung);
		
	
		this.add(beforeHereMarktplatz);
		this.add(beforeHereProjekt);
		this.add(hpanelAusschreibung);
		this.add(vpanelAusschreibung);
		
		ausschreibungtabelle.setSelectionModel(ssmalleausschreibung);
		
		ssmalleausschreibung.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				// für Eigenschft
				pp1= ssmalleausschreibung.getSelectedObject();
				Showcase showcase = new EigenschaftAusSeite(ssmalleausschreibung.getSelectedObject());
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
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
		gwtproxy.getAusschreibungByProjekt(p1, new getAusschreibungAusDB());
		
		anlegenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				DialogBox dialogbox = new DialogBoxAusschreibungAnlegen(p1);
				dialogbox.center();
			}
		});
		
		bewerbenAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				Dieser Teil ist für den Button BEWERBUNG FÜR DIESE AUSSCHREIBEN ERSTELLEN
				DialogBox db1 = new DialogBoxBewerbungAnlegen(ssmalleausschreibung.getSelectedObject(), null);
				db1.center();
			}
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

