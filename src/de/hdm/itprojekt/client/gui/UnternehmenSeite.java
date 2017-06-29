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
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;

public class UnternehmenSeite extends Showcase {

	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Unternehmen> unternehmentabelle = new CellTable<Unternehmen>();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private HorizontalPanel hpanelUnternehmen = new HorizontalPanel();
	private VerticalPanel vpanelUnternehmen = new VerticalPanel();
	private Partnerprofil pp = new Partnerprofil();
	private Unternehmen u1 = new Unternehmen();
	private Person p1 = new Person();
	private Team t1 = new Team();
	private Button unternehmenAnlegen = new Button("Neues Unternehmen anlegen");
	private Button unternehmenBearbeiten = new Button("Unternehmen bearbeiten");
	private Button unternehmenBeitreten = new Button("Unternehmen beitreten");
	private Button unternehmenLoeschen = new Button("Unternehmen löschen");
	final SingleSelectionModel<Unternehmen> ssmallunternehmen = new SingleSelectionModel<Unternehmen>();
	
	public UnternehmenSeite(final Person person) {
		this.p1= person; 
	}
	
	public UnternehmenSeite(final RoleManagement rm, final Navigator navi){
		this.rm=rm;
		this.navi=navi;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Wählen sie ein Unternehmen aus </h1>";
	}

	@Override
	protected void run() {
		
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		unternehmentabelle.setWidth("100%", true);
		unternehmentabelle.setStylePrimaryName("celltable");
		vpanelUnternehmen.add(unternehmentabelle);
		
		hpanelUnternehmen.add(unternehmenAnlegen);
		hpanelUnternehmen.add(unternehmenBearbeiten);
		hpanelUnternehmen.add(unternehmenBeitreten);
		hpanelUnternehmen.add(unternehmenLoeschen);
		this.add(vpanelUnternehmen);
		this.add(hpanelUnternehmen);
		
		unternehmentabelle.setSelectionModel(ssmallunternehmen);
		
		ssmallunternehmen.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				u1= ssmallunternehmen.getSelectedObject();
			}
		});		
			//Spalten für die Unternhemen Tabelle	
		
		TextColumn<Unternehmen> firmenName = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getFirmenName();
			}
				
		
		};
		
		
		TextColumn<Unternehmen> standort = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getStandort();
			}
				
		
		};
		
		
		TextColumn<Unternehmen> adresse = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getAdresse();
			}
				
		
		};
		
		
		unternehmenAnlegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxFirmaAnlegen(rm, navi);
				dialogbox.center();
			}
			
		});
		
		unternehmenBeitreten.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				u1 = ssmallunternehmen.getSelectedObject();
				ssmallunternehmen.getSelectedObject().getId();
				rm.getUser().setIdUnternehmen(ssmallunternehmen.getSelectedObject().getId());
					
				gwtproxy.savePerson(rm.getUser(), new AsyncCallback<Void>() {
					
					
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Unternehmen-ADD ging nicht");
					}
					
					
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Unternehmen geändert");
						Showcase showcase = new PersonSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				
			});
			
		}});
		
		unternehmenBearbeiten.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				DialogBox dialogbox = new DialogBoxUnternehmenBearbeiten(rm, navi, u1);
				dialogbox.center();
				
			}
			
		});
		
		unternehmenLoeschen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				u1.setId(rm.getUser().getIdUnternehmen());
				if (rm.getUser().getIdTeam() == null){
					rm.getUser().setIdUnternehmen(0);
				}
				gwtproxy.getPartnerprofilByOrganisationseinheit(rm.getUnternehmenOfUser(), new AsyncCallback<Partnerprofil>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						// TODO Auto-generated method stub
						pp=result;
						u1.setIdPartnerprofil(result.getId());
//						t1.setIdPartnerprofil(pp.getId());
						
						gwtproxy.savePersonPers(rm.getUser(), new AsyncCallback<Person>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Person result) {
								gwtproxy.loeschenUnternehmenInteger(u1.getId(), new AsyncCallback<Void>(){

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										gwtproxy.loeschenPartnerprofil(pp, new AsyncCallback<Void>(){

											@Override
											public void onFailure(Throwable caught) {
												// TODO Auto-generated method stub
												
											}

											@Override
											public void onSuccess(Void result) {
												Window.alert("bis232");
												
											}
											
										});
									}

								
								
							});
								
							}

							
											
						
					
							
						});
					}
					
				});
				
				
				
				
				
				
			}
//			
		});
		
			// Spalten Ende
		
			//Spalten der Tabele hinzufügen
			unternehmentabelle.addColumn(firmenName, "Firmenname");
			unternehmentabelle.addColumn(standort, "Standort");
			unternehmentabelle.addColumn(adresse, "Adresse");
			
			//Alle Unternehmen anzeigen lassen inder Tabelle 
			gwtproxy.getAllUnternehmen(new getAlleUnternehmenausDB());
	}		
	
			// Callback um Unternehmen aus der DB zu erhalten 
			private class getAlleUnternehmenausDB implements AsyncCallback<Vector<Unternehmen>>{

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Unternehmen> result) {
					// TODO Auto-generated method stub
					
					unternehmentabelle.setRowData(0, result);
					unternehmentabelle.setRowCount(result.size(), true);
				}
				
				
			}
}


//gwtproxy.loeschenUnternehmen(u1, new AsyncCallback<Void>(){
//
//	@Override
//	public void onFailure(Throwable caught) {
//		// TODO Auto-generated method stub
//		Window.alert("Fehler beim Löschen des Unternehmens");
//	}
//
//	@Override
//	public void onSuccess(Void result) {
//		// TODO Auto-generated method stub
//		Window.alert("Das Unternehmen wurde erfolgreich gelöscht");
//		Showcase showcase = new UnternehmenSeite(rm, navi);
//		RootPanel.get("Anzeige").clear();
//		RootPanel.get("Anzeige").add(showcase);
//	}
//	
//});
//