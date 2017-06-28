package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.client.gui.report.*;
import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Projektmarktplatz;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.EigeneBewerbungenSeite;
import de.hdm.itprojekt.client.gui.ImpressumMeetProjects;
import de.hdm.itprojekt.client.gui.RoleManagement;
import de.hdm.itprojekt.client.gui.Startseite;
import de.hdm.itprojekt.shared.GreetingServiceAsync;

public class NavigatorReport extends StackPanel {
	
	private VerticalPanel panelStartseite = new VerticalPanel();
	private VerticalPanel panelReport = new VerticalPanel();
	private VerticalPanel panelNavigator = new VerticalPanel();

	GreetingServiceAsync gwtproxy2 = ClientSideSettings.getMarktplatzVerwaltung();
	private ClickHandler clickHandler = null;
	private ClickEvent clickEvent = null;
	
	private Anchor meetProjectsLink = new Anchor();
	
	private Button startseiteButton = new Button ("Startseite");
	private Button impressumMeetProjectsButton = new Button ("Impressum");
	private Button meetProjectsButton = new Button ("MeetProjects");
	
	private Button ausschreibungenButton = new Button("Alle Ausschreibungen aufrufen");
	private Button ausschreibungToPartnerprofilButton = new Button("Ausschreibungen zu Partnerprofil aufrufen");
	private Button bewerbungenByAusschreibungenButton = new Button("Eigene Bewerbungen aufrufen");
	private Button bewerbungenByOrganisationseinheitButton = new Button("Bewerbungen zu eigenen Ausschreibungen aufrufen");
	private Button projektverflechtungenButton = new Button("Projektverflechtungen aufrufen");
	private Button fanInFanOutAnalyseButton = new Button("Fan-in/Fan-out Analyse aufrufen");
	private RoleManagementReport roleManagementReport = null;
	
	public NavigatorReport(final Person person){
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
		
		panelReport.add(bewerbungenByAusschreibungenButton);
		bewerbungenByAusschreibungenButton.setWidth("200px");
		bewerbungenByAusschreibungenButton.setStylePrimaryName("navi-button");
		
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
		this.add(panelReport, "Reports");
		this.add(panelNavigator, "Administrationsbereich");
		
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
				Showcase reportScase = new AlleAusschreibungenByPartnerprofilShowcase(roleManagementReport);
				RootPanel.get("AnzeigeReport").clear();
				RootPanel.get("AnzeigeReport").add(reportScase);
				clickHandler= this;
				clickEvent=event;	
			}
		});
		
		bewerbungenByAusschreibungenButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase reportScase = new AlleBewerbungenByOrganisationseinheitShowcase(roleManagementReport);
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
				Showcase reportScase = new AlleBewerbungenByAusschreibungShowcase(roleManagementReport);
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
				Showcase reportScase = new ProjekverflechtungShowcase(roleManagementReport);
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
		meetProjectsButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				meetProjectsLink.setHref(GWT.getHostPageBaseURL()+"Projektmarktplatz.html");
				Window.open(meetProjectsLink.getHref(), "_self", "");
			}
		});
	}
	
	//getter und setter methoden
	public ClickHandler getCurrentClickHandlerReport(){
		return clickHandler;
	}

	public void setCurrentClickHandlerReport(ClickHandler clickHandler){
		this.clickHandler=clickHandler;
	}
	
	public ClickEvent getCurrentClickHandlerEventReport(){
		return clickEvent;
	}
	
	public void setCurrentClickHandlerEventReport(ClickEvent clickEvent){
		this.clickEvent=clickEvent;
	}
	
	public void setRoleManagementReport(RoleManagementReport roleManagementReport){
		this.roleManagementReport=roleManagementReport;
	}
	
	
	//reload(aktualisiert) den letzten Showcase Butten der geklickt wurde
	public void reloadReport(){
		clickHandler.onClick(clickEvent);
	}
	public RoleManagementReport getIdRoleReport() {
		return roleManagementReport;
	}


	public void setIdRoleReport(RoleManagementReport idRoleReport) {
		this.roleManagementReport = idRoleReport;
	}
	

	public NavigatorReport getNavigatorReport(){
		return this;
	}
	
}
