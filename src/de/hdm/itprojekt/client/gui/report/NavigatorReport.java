package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.ImpressumMeetProjects;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.client.gui.Startseite;

public class NavigatorReport extends StackPanel {
	
	private VerticalPanel panelStartseite = new VerticalPanel();
	private VerticalPanel panelReport = new VerticalPanel();
	private VerticalPanel panelNavigator = new VerticalPanel();

	private ClickHandler clickHandler = null;
	private ClickEvent clickEvent = null;
	
	private Anchor meetProjectsLink = new Anchor();
	
	private Button startseiteButton = new Button ("Startseite");
	private Button impressumMeetProjectsButton = new Button ("Impressum");
	private Button meetProjectsButton = new Button ("MeetProjects");
	
	private Button ausschreibungenButton = new Button("Alle Ausschreibungen aufrufen");
	private Button ausschreibungToPartnerprofilButton = new Button("Ausschreibungen zu Partnerprofil aufrufen");
	private Button bewerbungenToOneAusschreibungenButton = new Button("Bewerbungen zu eigenen Ausschreibungen aufrufen");
	private Button bewerbungenByOrganisationseinheitButton = new Button("Eigene Bewerbungen aufrufen");
	private Button projektverflechtungenButton = new Button("Projektverflechtungen aufrufen");
	private Button fanInFanOutAnalyseButton = new Button("Fan-in/Fan-out Analyse aufrufen");
	private RoleManagement roleManagement = null;
	
	public NavigatorReport(){
		//startseite der Panels
		panelStartseite.add(startseiteButton);
		startseiteButton.setWidth("200px");
		startseiteButton.setStylePrimaryName("navi-button");
		
		panelStartseite.add(impressumMeetProjectsButton);
		impressumMeetProjectsButton.setWidth("200px");
		impressumMeetProjectsButton.setStylePrimaryName("navi-button");
		
		panelStartseite.setSpacing(6);
		
		//report der Panels
		panelReport.add(ausschreibungenButton);
		ausschreibungenButton.setWidth("200px");
		ausschreibungenButton.setStylePrimaryName("navi-button");
		
		panelReport.add(ausschreibungToPartnerprofilButton);
		ausschreibungToPartnerprofilButton.setWidth("200px");
		ausschreibungToPartnerprofilButton.setStylePrimaryName("navi-button");
		
		panelReport.add(bewerbungenToOneAusschreibungenButton);
		bewerbungenToOneAusschreibungenButton.setWidth("200px");
		bewerbungenToOneAusschreibungenButton.setStylePrimaryName("navi-button");
		
		panelReport.add(bewerbungenByOrganisationseinheitButton);
		bewerbungenByOrganisationseinheitButton.setWidth("200px");
		bewerbungenByOrganisationseinheitButton.setStylePrimaryName("navi-button");
		
		panelReport.add(projektverflechtungenButton);
		projektverflechtungenButton.setWidth("200px");
		projektverflechtungenButton.setStylePrimaryName("navi-button");
		
		panelReport.add(fanInFanOutAnalyseButton);
		fanInFanOutAnalyseButton.setWidth("200px");
		fanInFanOutAnalyseButton.setStylePrimaryName("navi-button");
		
		panelReport.setSpacing(6);
	
		//einstellungen der Panels
		panelNavigator.add(meetProjectsButton);
		meetProjectsButton.setWidth("200px");
		meetProjectsButton.setStylePrimaryName("navi-button");
		panelNavigator.setSpacing(6);
		
		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(panelReport, "Startseite");
		this.add(panelNavigator, "Persönliche Funktionen");
		
		//ClickHandler für die ganzen Button
		startseiteButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(new Startseite());
				clickHandler= this;
				clickEvent=event;				
			}
		});
		
		impressumMeetProjectsButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(new ImpressumMeetProjects());
			}
		});
	
		ausschreibungenButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new AlleAusschreibungenShowcase();
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;	
			}	
		});
		
		ausschreibungToPartnerprofilButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new AlleAusschreibungenByPartnerprofilShowcase(roleManagement);
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;	
			}
		});
		
		bewerbungenToOneAusschreibungenButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new AlleBewerbungenByOrganisationseinheitShowcase(roleManagement);
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;
			}
		});
		
		bewerbungenByOrganisationseinheitButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new AlleBewerbungenToOneAusschreibungShowcase(roleManagement);
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;
			}
		});
		
		projektverflechtungenButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new ProjekverflechtungShowcase(roleManagement);
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;
			}
		});
		
		fanInFanOutAnalyseButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new FanInFanOutShowcase();
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;
			}
		});
		
		//Zürück zur Startseite wird noch eingefügt
		
	}
	
	//getter und setter methoden
	public ClickHandler getCurrentClickHandler(){
		return clickHandler;
	}

	public void setCurrentClickHandler(ClickHandler clickHandler){
		this.clickHandler=clickHandler;
	}
	
	public ClickEvent getCurrentClickHandlerEvent(){
		return clickEvent;
	}
	
	public void setCurrentClickHandlerEvent(ClickEvent clickEvent){
		this.clickEvent=clickEvent;
	}
	
	public void setRoleManagement(RoleManagement roleManagement){
		this.roleManagement=roleManagement;
	}
	
	//reload(aktualisiert) den letzten Showcase Butten der geklickt wurde
	public void reload(){
		clickHandler.onClick(clickEvent);
	}
	
}
