//package de.hdm.itprojekt.client.gui;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Vector;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.cellview.client.CellTable;
//import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
//import com.google.gwt.user.cellview.client.SimplePager;
//import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
//import com.google.gwt.user.cellview.client.TextColumn;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.HasHorizontalAlignment;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.view.client.ListDataProvider;
//import com.google.gwt.view.client.SelectionChangeEvent;
//import com.google.gwt.view.client.SelectionChangeEvent.Handler;
//import com.google.gwt.view.client.SingleSelectionModel;
//import com.google.gwt.view.client.ListDataProvider;
//import de.hdm.itprojekt.client.ClientSideSettings;
//import de.hdm.itprojekt.client.Navigator;
//import de.hdm.itprojekt.client.Showcase;
//import de.hdm.itprojekt.shared.GreetingService;
//import de.hdm.itprojekt.shared.GreetingServiceAsync;
//import de.hdm.itprojekt.shared.bo.Bewerbung;
//import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
//import de.hdm.itprojekt.shared.bo.Bewertung;
//import de.hdm.itprojekt.shared.bo.Organisationseinheit;
//import de.hdm.itprojekt.shared.bo.Person;
//import de.hdm.itprojekt.shared.bo.Team;
//import de.hdm.itprojekt.shared.bo.Unternehmen;
//
//
//
//public class BewerbungenAufAusschreibungSeite extends Showcase{
//
//		 GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
//	
//		 private Button bewerbungBewerten = new Button("Bewerbung bewerten");
//		 
//		 private CellTable<Bewerbung> eingegangeneBewerbungentabelle = new CellTable<Bewerbung>();
//
//		 final SingleSelectionModel<Bewerbung> ssmalleingBewerbungen = new SingleSelectionModel<Bewerbung>();
//		 private HorizontalPanel hpanelEingBewerbung = new HorizontalPanel();
//		 private VerticalPanel vpanelEingBewerbung = new VerticalPanel();
//		 
//		@Override
//		protected String getHeadlineText() {
//			// TODO Auto-generated method stub
//			return "<h1> Eingegangene Bewerbungen auf Ihre Ausschreibungen </h1>";
//		}
//
//		@Override
//		protected void run() {
//			// TODO Auto-generated method stub
//			RootPanel.get("Anzeige").setWidth("100%");
//			eingegangeneBewerbungentabelle.setWidth("100%", true);
//			eingegangeneBewerbungentabelle.setStylePrimaryName("celltable");
//			vpanelEingBewerbung.add(eingegangeneBewerbungentabelle);
//			
//			hpanelEingBewerbung.add(bewerbungBewerten);
//			
//			this.add(hpanelEingBewerbung);
//			this.add(vpanelEingBewerbung);
//			
//			eingegangeneBewerbungentabelle.setSelectionModel(ssmalleingBewerbungen);
//			
//			ssmalleingBewerbungen.addSelectionChangeHandler(new Handler() {
//				
//				@Override
//				public void onSelectionChange(SelectionChangeEvent event) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//			
//			gwtproxy.getAllBewerbungenByOrganisationseinheit(o, callback);
//			
//		}
//		 
//		 
//
//
//
//
//
//
//
//
//
//
//
//}
//
