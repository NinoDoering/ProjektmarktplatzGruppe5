package de.hdm.itprojekt.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.ListDataProvider;
import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;


//Hybridklasse Bewertung und Bewerbung
public class BewerbungenAufAusschreibungSeite extends VerticalPanel{

		GreetingServiceAsync greetingService = ClientSideSettings.getMarktplatzVerwaltung();
		/**
		 * GUI-Elemente und globale Variablen und Objekte deklarieren.
		 * 
		 */
		CellTable<BewertungBewerbung> bewertungBewerbungCt = new CellTable<BewertungBewerbung>();
		
		Vector<BewertungBewerbung> mix = new Vector<BewertungBewerbung>();
		Vector<Bewerbung> bewerbungen = new Vector<Bewerbung>();
		Vector <String> bewerber = new Vector<String>();
		Vector<Bewertung> bewertungen = new Vector<Bewertung>();
		Bewertung bewertung = null;

		Button bewerbungBewertenButton = new Button("Bewerbung bewerten");
		Button partnerprofilAnzeigen = new Button("Partnerprofil des Bewerbers");
		Button zurueckButton = new Button("Zurück");
		Navigator navigator = null;
		private RoleManagement roleManagement;

		HorizontalPanel buttonPanel = new HorizontalPanel();
		
		/**
		 * Anlegen des Konstruktors
		 * @param ausschreibungId
		 * @param navigation
		 * @param roleManagement
		 */
		public BewerbungenAufAusschreibungSeite(int idAusschreibung, final Navigator navigator, final RoleManagement roleManagement){
			this.roleManagement=roleManagement;
			this.navigator=navigator;
			RootPanel.get("Details").setWidth("70%");
			bewertungBewerbungCt.setWidth("100%", true);
			greetingService.getBewerbungByAusschreibungId(idAusschreibung, new GetBewerbungenCallback());
			
			
			
			
			/**
			 * TextColumns anlegen und der Celltable hinzufügen		
			 */
			TextColumn<BewertungBewerbung> erstelldatumColumn = new TextColumn<BewertungBewerbung>(){

				@Override
				public String getValue(BewertungBewerbung object) {
					return object.getErstellDatum().toString();
				}
	};
	
	TextColumn<BewertungBewerbung> bewerbungsstatusColumn = new TextColumn<BewertungBewerbung>(){

		@Override
		public String getValue(BewertungBewerbung object) {
			return object.getBewerbungsstatus().toString();
		}
	};
			
	TextColumn<BewertungBewerbung> bewertungsWertColumn = new TextColumn<BewertungBewerbung>(){

		@Override
		public String getValue(BewertungBewerbung object) {
			float wert = object.getWert();
			String stringWert = Float.toString(wert);
			return stringWert;
		}
	};
	
	TextColumn<BewertungBewerbung> bewerberColumn = new TextColumn<BewertungBewerbung>(){

		@Override
		public String getValue(BewertungBewerbung object) {
			return object.getBewerber();
		}
	};
	
	
	bewertungBewerbungCt.addColumn(bewerberColumn, "Bewerber");
	bewertungBewerbungCt.addColumn(erstelldatumColumn, "Erstellsdatum");
	bewertungBewerbungCt.addColumn(bewerbungsstatusColumn, "Bewerbungsstatus");
	bewertungBewerbungCt.addColumn(bewertungsWertColumn, "Bewertung");
	bewertungBewerbungCt.setWidth("100%");
	
	/**
	 * Anlegen der SingleSelection der CellTable
	 * Speichert die ausgewählte Zeile des Celltables
	 */
	final SingleSelectionModel<BewertungBewerbung> selectionModel = new SingleSelectionModel<>();
	bewertungBewerbungCt.setSelectionModel(selectionModel);	
	bewertungBewerbungCt.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
	/**
	 * Hinzufügen der Buttons zum ButtonPanel
	 */
	buttonPanel.add(zurueckButton);
	buttonPanel.add(partnerprofilAnzeigen);
	buttonPanel.add(bewerbungBewertenButton);

	
	zurueckButton.setStylePrimaryName("cell-btn");
	partnerprofilAnzeigen.setStylePrimaryName("cell-btn");
	bewerbungBewertenButton.setStylePrimaryName("cell-btn");
	
	this.setSpacing(8);
	this.add(buttonPanel);
	this.add(bewertungBewerbungCt);
	
	
	/**
	 * ClickHandler der Buttons anlegen
	 * Öffnen der DialogBox BewerbungBewerten
	 */
	bewerbungBewertenButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			if(selectionModel.getSelectedObject() == null){
				Window.alert("Bitte wähle zuerst eine Bewerbung aus.");
			} else{
			DialogBoxBewerbungBewerten dbb = new DialogBoxBewerbungBewerten(selectionModel.getSelectedObject(), roleManagement, navigator);
			dbb.center();
			dbb.show();
			}		
		}
});
	/**
	 * Click Handler um zur Hauptseite zurück zu gelangen		
	 */
	zurueckButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(new MeineAusschreibungen(roleManagement, navigator));	
		}
});
	
	/**
	 * Click Handler um PartnerProfilAusschreibung aufzurufen
	 */
	partnerprofilAnzeigen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			if(selectionModel.getSelectedObject() != null){
				int idPartnerprofil = selectionModel.getSelectedObject().getidPartnerprofil();
			//Klasse muss noch erstellt werden
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(new PartnerprofilByAusschreibungSeite(idPartnerprofil, false, true, roleManagement, navigator));
				
				} else {
					Window.alert("Bitte wähle zuerst eine Bewerbung aus.");
			}
		}
	});
}
		/**
		 * Impelemtierung der Inneren privaten Klasse um Celltable mit einheitlichem Datensatz zu befüllen
		 *
		 */
		public class BewertungBewerbung{
			
			private String bewerbungstext;
			private BewerbungsStatus bewerbungsstatus;
			private Date erstellDatum;
			private String textuelleBewertung;
			private float wert;
			private String bewerber;
			private int idBewerbung;
			private int idBewertung;
			private int idAusschreibung;
			private int idProjekt;
			private int idPartnerprofil;
			private int idBewerber;
			private Date startDatum;
			private Date endDatum;
			private double beteiligungszeit;
	
			public double getBeteiligungszeit() {
				return beteiligungszeit;
			}

			public void setBeteiligungszeit(double beteiligungszeit) {
				this.beteiligungszeit = beteiligungszeit;
			}

			public int getIdBewertung() {
				return idBewertung;
			}
			
			public void setIdBewertung(int idBewertung) {
				this.idBewertung= idBewertung;
			}
			
			public String getBewerber() {
				return bewerber;
			}

			public void setBewerber(String bewerber) {
				this.bewerber = bewerber;
			}
			
			public String getBewerbungstext() {
				return bewerbungstext;
			}
			
			public void setBewerbungstext(String bewerbungstext) {
				this.bewerbungstext = bewerbungstext;
			}
			
			public BewerbungsStatus getBewerbungsstatus() {
				return bewerbungsstatus;
			}
			
			public void setBewerbungsstatus(BewerbungsStatus bewerbungsstatus) {
				this.bewerbungsstatus = bewerbungsstatus;
			}
			
			public Date getErstellDatum() {
				return erstellDatum;
			}
			
			public void setErstellDatum(Date erstellDatum) {
				this.erstellDatum = erstellDatum;
			}
			
			public String getTextuelleBewertung() {
				return textuelleBewertung;
			}
			
			public void setTextuelleBewertung (String textuelleBewertung) {
				this.textuelleBewertung = textuelleBewertung;
			}
			
			public float getWert(){
				return wert;
			}
		
			
			public void setWert(float Wert) {
				this.wert = wert;
			}
			
			public int getIdBewerbung() {
				return idBewerbung;
			}

			public void setIdBewerbung(int idBewerbung) {
				this.idBewerbung = idBewerbung;
			}

			public int getIdAusschreibung() {
				return idAusschreibung;
			}

			public void setIdAusschreibung(int idAusschreibung) {
				this.idAusschreibung = idAusschreibung;
			}

			public int getIdProjekt() {
				return idProjekt;
			}

			public void setIdProjekt(int idProjekt) {
				this.idProjekt = idProjekt;
			}


			public int getidPartnerprofil() {
				return idPartnerprofil;
			}

			public void setIdPartnerprofil(int idPartnerprofil) {
				this.idPartnerprofil = idPartnerprofil;
			}

			public int getIdBewerber() {
				return idBewerber;
			}

			public void setIdBewerber(int idBewerber) {
				this.idBewerber = idBewerber;
			}

			

			public Date getStartdatum() {
				return startDatum;
			}

			public void setStartdatum(Date startdatum) {
				this.startDatum = startdatum;
			}

			public Date getEnddatum() {
				return endDatum;
			}

			public void setEnddatum(Date enddatum) {
				this.endDatum = enddatum;
			}
			
	}	
	
	
		/**
		 * Anlegen der Callbacks
		 * Bei erfolgreichem Callback wird ein Vector mit Bewerbungen als result zurückgibt.
		 * 
		 * @author Tom 
		 *
		 */
		private class GetBewerbungenCallback implements AsyncCallback<Vector<Bewerbung>>{
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());	
			}
			public void onSuccess(Vector<Bewerbung> result) {
				
				bewerbungen = result;			
				/**
				 * Vector mit Bewerbungen wird durchgegangen und jeweils deren verschiedene Attribute in das Objekt localMix
				 * gesetzt.
				 */
				for(int i=0;i<bewerbungen.size();i++){
					
					final BewertungBewerbung localMix = new BewertungBewerbung();
					localMix.setIdBewerber(bewerbungen.get(i).getIdOrganisationseinheit());
					/**
					 *  Die Bewertung wird beim Callback zu der übergebenen Bewerbung als
					 * result zurückgegeben
					 */
	
	
					greetingService.getBewertungByBewerbung(bewerbungen.get(i), new AsyncCallback<Bewertung>(){
	
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: " + caught.toString());
						}
						public void onSuccess(Bewertung result) {
							localMix.setTextuelleBewertung(result.getTextuelleBewertung());
							localMix.setWert(result.getFliesskommaBewertung());
							localMix.setIdBewertung(result.getId());
							/**
							 * Die Organisationseinheit wird zu der übergebenen Beteiligung als
							 * result zurückgegeben
							 *
							 */
							bewertungBewerbungCt.setRowCount(mix.size(), true);
							bewertungBewerbungCt.setRowData(0,mix);
						}
					});
	
					/**
					 * Die Organisationseinheit wird zu der übergebenen Bewerbung als
					 * result zurückgegeben
					 */
					greetingService.getOrganisationseinheitById(bewerbungen.get(i).getIdOrganisationseinheit(), new AsyncCallback<Organisationseinheit>() {
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: " + caught.toString());
						}
						public void onSuccess(Organisationseinheit result) {
							/**
							 * Prüfung ob die Organisationseinheit eine Person, ein Team oder ein Unternehmen ist.
							 * Demnach werden die Attribute gesetzt
							 */
							if(result instanceof Person){
								String bewerber = ((Person) result).getVorname() + " " + ((Person) result).getNachname();
								localMix.setBewerber(bewerber);
							} else if(result instanceof Team){
								String bewerber = ((Team)result).getTeamName();
								localMix.setBewerber(bewerber);
							} else if(result instanceof Unternehmen){
								String bewerber = ((Unternehmen) result).getFirmenName();
								localMix.setBewerber(bewerber);
							}	
							localMix.setIdPartnerprofil(result.getIdPartnerprofil());
							bewertungBewerbungCt.setRowCount(mix.size(), true);
							bewertungBewerbungCt.setRowData(0,mix);
						}
	});
					
					localMix.setIdBewerbung(bewerbungen.get(i).getId());
					localMix.setBewerbungstext(bewerbungen.get(i).getBewerbungsText());
					localMix.setIdAusschreibung(bewerbungen.get(i).getIdAusschreibung());
					localMix.setErstellDatum(bewerbungen.get(i).getErstellDatum());
					localMix.setBewerbungsstatus(bewerbungen.get(i).getBewerbungsStatus());
					/**
					 * Das localMix-Objekt wird dem Vector hinzugefügt
					 */
					mix.add(localMix);
					
					/**Anlegen eines ListDataDataProvider Pager Objects um die Liste an
					* Objekten beim Umschalten bei mehreren Celltables zu halten
					*/
				}	
					final ListDataProvider dataProvider = new ListDataProvider();

					// Panel hinzufügen
					SimplePager pager;
					SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
					pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
					pager.setDisplay(bewertungBewerbungCt);
					dataProvider.addDataDisplay(bewertungBewerbungCt);
					dataProvider.setList(new ArrayList<BewertungBewerbung>(mix));
					pager.setPageSize(10);
					
					HorizontalPanel hp_pager = new HorizontalPanel();
					hp_pager.setWidth("100%");
					hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
					hp_pager.add(pager);
					add(hp_pager);
								
		}
	}	

}

