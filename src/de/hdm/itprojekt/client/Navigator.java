package de.hdm.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.client.gui.PersonSeite;

import de.hdm.itprojekt.client.gui.ProjektmarktplatzSeite;

public class Navigator extends StackPanel{

	VerticalPanel homeNavigator = new VerticalPanel();
	
	VerticalPanel personalNavigator = new VerticalPanel();
	
	Button btnBack = new Button ("Zurueck zur Startseite");
	
	Button meinProfil = new Button("Mein Profil");
	
	Button meineBewerbungen = new Button("Meine Bewerbungen");
	
	Button projektmarktplatzSuchen = new Button("Projektmarktplätze");
	
	Button Logout = new Button("Logout");
	
	public Navigator(){
		
		homeNavigator.add(Logout);
		Logout.setWidth("200px");
		Logout.setStylePrimaryName("navi-button");
		
		homeNavigator.add(projektmarktplatzSuchen);
		projektmarktplatzSuchen.setWidth("200px");
		projektmarktplatzSuchen.setStylePrimaryName("navi-button");
		
		homeNavigator.add(btnBack);
		btnBack.setWidth("200px");
		btnBack.setStylePrimaryName("navi-button");
		
		homeNavigator.setSpacing(5);
		homeNavigator.setWidth("100%");
		
		personalNavigator.add(meineBewerbungen);
		meineBewerbungen.setWidth("200px");
		meineBewerbungen.setStylePrimaryName("navi-button");
		
		personalNavigator.add(meinProfil);
		meinProfil.setWidth("200px");
		meinProfil.setStylePrimaryName("navi-button");
		
		personalNavigator.setSpacing(5);
		personalNavigator.setWidth("100%");
	
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(homeNavigator, "Startseite");
		this.add(personalNavigator, "Persönliche Funktionen");
		projektmarktplatzSuchen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new ProjektmarktplatzSeite();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			
			}});
	
		
		meineBewerbungen.addClickHandler(new ClickHandler(){
			
			
			@Override
			public void onClick(ClickEvent event) {
				Showcase showcase = new BewerbungenSeite();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
			}});
		meinProfil.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("Anzeige").clear();
				Showcase sh = new PersonSeite();
				RootPanel.get("Anzeige").add(sh);
			
			}
		});
		
		btnBack.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				homeNavigator.removeFromParent();
				personalNavigator.removeFromParent();
				Window.Location.reload();
			}});
			
		Logout.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				//logout funktion 
			}
		});
			
	}}
	
	
	

