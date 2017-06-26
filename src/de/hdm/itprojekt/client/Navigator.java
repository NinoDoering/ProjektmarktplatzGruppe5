package de.hdm.itprojekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.shared.bo.Person;

public class Navigator extends StackPanel{
	
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;

	VerticalPanel homeNavigator = new VerticalPanel();
	
	VerticalPanel personalNavigator = new VerticalPanel();
	HorizontalPanel rechtsUnten = new HorizontalPanel();
	
	//Anlegen der Hyperlinks
	Hyperlink home = new Hyperlink();
	Anchor reportLink= new Anchor("ReportGenerator");
	
	Button btnBack = new Button ("Zurück zur Startseite");
	
	
	Button meineBewerbungen = new Button("Meine Bewerbungen");
	
	Button projektmarktplatzSuchen = new Button("Projektmarktplätze");

	Button agb = new Button("AGB");
	Button impressum = new Button("Impressum");
	
	Button reportButton = new Button("Report Generator");
	
	
	public Navigator(){
		
	}
	
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
				/** Clirim muss sich darum noch k�mmern
				 * 
				 */
					
					//TONY PART
//				identityMarketChoice.setOwnOrgUnitToZero();
//				identityMarketChoice.deactivateProjectMarkets();
//				identityMarketChoice.deactivateOrgUnits();
				Showcase showcase = new ProjektmarktplatzSeite(person);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}});
	
		
		meineBewerbungen.addClickHandler(new ClickHandler(){
			
			
			@Override
			public void onClick(ClickEvent event) {
				
				//TONY PART
//				identityMarketChoice.activateProjectMarkets();
//				identityMarketChoice.activateOrgUnits();
				Showcase showcase = new BewerbungenSeite();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				currentClickHandler=this;
				currentClickEvent=event;
			}});

		
		btnBack.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				homeNavigator.removeFromParent();
				personalNavigator.removeFromParent();
				Window.Location.reload();
			}});
	
		reportButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL()+"ProjektmarktplatzReports.html");
				Window.open(reportLink.getHref(), "_self", "");
				
				
			}
		
		});

	}
		public ClickHandler getCurrentClickHandler() {
			return currentClickHandler;
		}

		public ClickEvent getCurrentClickEvent() {
			return currentClickEvent;
		}

		public void setCurrentClickHandler(ClickHandler c){
			currentClickHandler = c;
		}
		public void setCurrentClickEvent(ClickEvent e){
			currentClickEvent = e;
		}
		
		public void reload(){
			currentClickHandler.onClick(currentClickEvent);
		}
	
		public Navigator getNavigator(){
			return this;
		}
		
		//TONY PART
//		public IdentityMarketChoice getIdentityMarketChoice(){
//			return identityMarketChoice;
//		}
//		public void setIdentityMarketChoice(IdentityMarketChoice identityMarketChoice){
//			this.identityMarketChoice=identityMarketChoice;
//	}
}
	
	



	
	
	

