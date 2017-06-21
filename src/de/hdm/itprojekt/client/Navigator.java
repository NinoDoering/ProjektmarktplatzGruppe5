package de.hdm.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.shared.bo.Person;

public class Navigator extends StackPanel{

	VerticalPanel homeNavigator = new VerticalPanel();
	
	VerticalPanel personalNavigator = new VerticalPanel();
	HorizontalPanel rechtsUnten = new HorizontalPanel();
	Button btnBack = new Button ("Zurück zur Startseite");
	
	
	Button meineBewerbungen = new Button("Meine Bewerbungen");
	
	Button projektmarktplatzSuchen = new Button("Projektmarktplätze");
	
	Button agb = new Button("AGB");
	Button impressum = new Button("Impressum");
	

	public Navigator(final Person person){
		
		rechtsUnten.add(impressum);
		rechtsUnten.add(agb);
		rechtsUnten.add(btnBack);
		
		
		
		homeNavigator.add(projektmarktplatzSuchen);
		projektmarktplatzSuchen.setWidth("200px");
		projektmarktplatzSuchen.setStylePrimaryName("navi-button");

		homeNavigator.setSpacing(5);
		homeNavigator.setWidth("100%");
		
		personalNavigator.add(meineBewerbungen);
		meineBewerbungen.setWidth("200px");
		meineBewerbungen.setStylePrimaryName("navi-button");
		
		RootPanel.get("RechtsUnten").add(rechtsUnten);

	
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(homeNavigator, "Startseite");
		this.add(personalNavigator, "Persönliche Funktionen");
		
		agb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new AGBMeetProjects();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		impressum.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new ImpressumMeetProjects();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
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

		
		btnBack.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				homeNavigator.removeFromParent();
				personalNavigator.removeFromParent();
				Window.Location.reload();
			}});
			
	
			
	}}
	
	
	

