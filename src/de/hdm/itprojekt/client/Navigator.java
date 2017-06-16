package de.hdm.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.gui.ProjektmarktplatzSeite;

public class Navigator extends StackPanel{

	VerticalPanel homeNavigator = new VerticalPanel();
	
	VerticalPanel personalNavigator = new VerticalPanel();
	
	Button btnBack = new Button ("Zurueck zur Startseite");
	
	Button meinProfil = new Button("Mein Profil");
	
	Button meineBewerbungen = new Button("Meine Bewerbungen");
	
	Button projektmarktplatzSuchen = new Button("Projektmarktplätze");
	
	public Navigator(){
		homeNavigator.add(projektmarktplatzSuchen);
		projektmarktplatzSuchen.setWidth("200px");
		projektmarktplatzSuchen.setStylePrimaryName("navi-button");
		
		homeNavigator.add(btnBack);
		btnBack.setWidth("200px");
		btnBack.setStylePrimaryName("navi-button");
		
		homeNavigator.add(meinProfil);
		meinProfil.setWidth("200px");
		meinProfil.setStylePrimaryName("navi-button");
		
		homeNavigator.setSpacing(5);
		homeNavigator.setWidth("100%");
		
		personalNavigator.add(meineBewerbungen);
		meineBewerbungen.setWidth("200px");
		meineBewerbungen.setStylePrimaryName("navi-button");
		
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
			}
		});
			
	}
	
	
	
}