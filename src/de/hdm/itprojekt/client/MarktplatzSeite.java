//package de.hdm.itprojekt.client;
//
//import java.util.Vector;
//
//import com.google.gwt.cell.client.ClickableTextCell;
//import com.google.gwt.core.client.*;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.cellview.client.CellTable;
//import com.google.gwt.user.cellview.client.Column;
//import com.google.gwt.user.cellview.client.TextColumn;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.view.client.SingleSelectionModel;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//
//import de.hdm.itprojekt.shared.GreetingService;
//import de.hdm.itprojekt.shared.GreetingServiceAsync;
//import de.hdm.itprojekt.shared.bo.Marktplatz;
//import de.hdm.itprojekt.client.ClientSideSettings;
//import de.hdm.itprojekt.client.Showcase;
//
//
//public class MarktplatzSeite extends Showcase {
//
//GreetingService gwtproxy = GWT.create(GreetingService.class); 
//	
//	CellTable<Marktplatz> ct_alleMarktplaetze = new CellTable<Marktplatz>();
//	CellTable<Marktplatz> ct_eigeneMarktplaetze = new CellTable<Marktplatz>();
//	
//	
//	private TextBox projektbox = new TextBox();
//	HorizontalPanel hpanel_projektmarktplatz = new HorizontalPanel();
//	VerticalPanel vpanel = new VerticalPanel();
//	
//	// Buttons NUR erstellen
//	Button deletemarktplatz = new Button("Marktplatz Löschen");
//	Button createmarktplatz = new Button("Marktplatz Anlegen");
//
//	// Erlaubt, dass in der Tabelle nur eins ausgewählt werden darf
//	
//	
//	
//	final SingleSelectionModel<Marktplatz> ssm = new SingleSelectionModel<Marktplatz>();
//	final SingleSelectionModel<Marktplatz> ssm_fremde = new SingleSelectionModel<Marktplatz>();
//	
//	
//	@Override
//	protected String getHeadlineText() {
//		return "Projektmarktplatz Suche";
//	}
//
//	@Override
//	protected void run() {
//		//Größe des "div" Containers, sprich der Seite
//		RootPanel.get("Details").setWidth("100%");
//		// Größe der Tablle im div Container, sprich der Seite
//		ct_alleMarktplaetze.setWidth("100%", true);
//		// Größe der Tablle im div Container, sprich der Seite
//		ct_eigeneMarktplaetze.setWidth("100%", true);
//		
//		
//		// Hinzufügen der Buttons und Textbox zum Panel
//		hpanel_projektmarktplatz.add(createmarktplatz);
//		hpanel_projektmarktplatz.add(deletemarktplatz);
//		hpanel_projektmarktplatz.add(projektbox);
//		
//		// Hinzufügen der Tabelle ins VerticalPanel
//		vpanel.add(ct_alleMarktplaetze);
////		hpanel_projektmarktplatz.add(ct_alleProjektmarktplaetze);
//		vpanel.add(ct_eigeneMarktplaetze);
//				
//		// In die seite laden
//		this.add(hpanel_projektmarktplatz);
//		this.add(vpanel);
//		
//		
//		//Stylen der Buttons 
////		createprojektmarktplatz.setStylePrimaryName("navi-button");
////		deleteprojektmarktplatz.setStylePrimaryName("navi-button");
//		
//		
//		ct_alleMarktplaetze.setSelectionModel(ssm);
//		ct_eigeneMarktplaetze.setSelectionModel(ssm_fremde);
//		
//		
//		// Was soll in der Tabelle angezeigt werden?		
////		TextColumn<Projektmarktplatz> ProjektmarktplatzTabelleSpaltenName = new TextColumn<Projektmarktplatz>() {
////			@Override
////			public String getValue(Projektmarktplatz object) {
////				return object.getBez();
////			}
////		};
//		 Column<Marktplatz, String> linkColumn = 
//				    new Column<Marktplatz, String>(new ClickableTextCell())  {
//				    
//						@Override
//						public String getValue(Marktplatz object) {
//							// TODO Auto-generated method stub
//							
//							return object.getGeschaeftsgebiet();
//							return object.getBezeichnung();
//						}
//				    };
//	
//		
//		// Wie soll die Spalte (Column) heißen?
////		ct_alleProjektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Alle Projektmarktplätze");
////		ct_eigeneProjektmarktplaetze.addColumn(ProjektmarktplatzTabelleSpaltenName, "Die eigenen Projektmarktplätze");
//		ct_alleMarktplaetze.addColumn(linkColumn, "Bezeichnung");
//	
//		filltable();	
//		loschenProjektmarktplatz();
//		anlegenProjektmarktplatz();
//			}
//	
//
//		// Beim ersten Mal laden der Seite, die Daten aus der Datenbank lesen
//	
//	private void filltable(){
//	
//		//((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//		 if (gwtproxy == null) {
//	      gwtproxy = GWT.create(GreetingService.class);
//	    }
//		 AsyncCallback<Vector<Projektmarktplatz>> callback = new AsyncCallback<Vector<Projektmarktplatz>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//			}
//			public void onSuccess(Vector<Marktplatz> result) {
//				// TODO Auto-generated method stub
//				ct_alleMarktplaetze.setRowData(0, result);
//				ct_alleMarktplaetze.setRowCount(result.size(), true);
//				
//					
//				}
//			};
//			
//		adminService.getProjektmarktplatzAll(callback);
//	}
//	
//	// Löschen aus der Datenbank und Tabelle
//	
//	
//	// Fehler beim Löschen von Projektmarktplatz!!!!... Der Fehler liegt bei selectedObcejt!
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	 private void loschenProjektmarktplatz(){
////		deletemarktplatz.addClickHandler(new ClickHandler(){
////
////			@Override
////			public void onClick(ClickEvent event) {
////				// TODO Auto-generated method stub
////				// "selectedobject" sprich die angewählte Zeile in der Tabelle wird instanziiert
////				Marktplatz selectedObject = ssm.getSelectedObject();
////				if (selectedObject != null){
////					//((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
////					 if (gwtproxy == null) {
////				      gwtproxy = GWT.create(GreetingService.class);
////				    }
////					 AsyncCallback<Marktplatz> callback = new AsyncCallback<Marktplatz>(){
////
////						@Override
////						public void onFailure(Throwable caught) {
////							// TODO Auto-generated method stub
////							Window.alert("Fehler beim Löschen");
////							
////						}
////
////						@Override
////						public void onSuccess(Marktplatz result) {
////							// TODO Auto-generated method stub
////							Window.alert("Der Projektmarktplatz wurde erfolgreich gelöscht");
////							refreshlist();
////						}
////						};
////						gwtproxy.loeschenMarktplatz(selectedObject, callback);
////						
////				}
////}
////		});
////	 }
//	 
//	
//	
//	
//	
//	
//	
//	
//	 // Liste erneuern, der Trigger ist das Löschen eines Projektmarktplatzes
//	 
//	 
//	 
////	 private void refreshlist(){
////		// ((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
////		 if (gwtproxy == null) {
////	      gwtproxy = GWT.create(GreetingService.class);
////	    }
////		 AsyncCallback<Vector<Marktplatz>> callback = new AsyncCallback<Vector<Marktplatz>>(){
////
////			@Override
////			public void onFailure(Throwable caught) {
////				// TODO Auto-generated method stub
////				Window.alert("Fehler beim Laden der Daten aus der Datenbank");
////			}
////			@Override
////			public void onSuccess(Vector<Marktplatz> result) {
////				// TODO Auto-generated method stub
////				ct_alleMarktplaetze.setRowData(0, result	);
////				ct_alleMarktplaetze.setRowCount(result.size(), true);
////				
////					
////				}
////			};
////		gwtproxy..getProjektmarktplatzAll(callback);
////		 
////	 }
//	 
//	 
//	 
//	 
//	 private void anlegenProjektmarktplatz(){
//		 		createmarktplatz.addClickHandler(new ClickHandler(){
//
//							 			
//		 			@Override
//					public void onClick(ClickEvent event) {
//						// TODO Auto-generated method stub
//						
//					
//			    // Initialize the service proxy.
//				//((ServiceDefTarget)adminService).setServiceEntryPoint("/IT_Projekt_SS17/projektmarktplatz");
//			    if (gwtproxy == null) {
//			     
//			      gwtproxy = GWT.create(GreetingService.class);
//			    }
//			    
//			     // Set up the callback object.
//			    AsyncCallback<Marktplatz> callback = new AsyncCallback<Marktplatz>() {
//			   
//			      public void onFailure(Throwable caught) {
//			        // TODO: Do something with errors.
//			    	  Window.alert("onFailure");
//			    }
//				
//					@Override
//					public void onSuccess(Marktplatz result) {
//						filltable();  		
//									}
//				
//			    };
//			    
//			     // Make the call to the stock price service.
//				   gwtproxy.anlegenMarktplatz(projektbox.getValue(), callback);
//			    
//	 }
//});
//}
//	
//	 
//}
