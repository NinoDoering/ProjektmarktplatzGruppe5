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

/**
 * Klasse zur Erstellung des Navigators, über den der Nutzer navigieren kann,
 * auf welche Bereiche der Seite er zugreifen möchte. Im Navigator werden alle Buttons und Panels
 * 
 * @author Thomas
 *
 */
public class Navigator extends StackPanel {

	/**
	 * Clickhandler um zu speichern, auf welchen Button geklickt wurde
	 */
	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;

	// Anlegen der Panels
	VerticalPanel homeNavigator = new VerticalPanel();
	VerticalPanel personalNavigator = new VerticalPanel();
	HorizontalPanel rechtsUnten = new HorizontalPanel();
	
	// Anlegen der Hyperlinks
	RoleManagement rm = new RoleManagement();
	Hyperlink home = new Hyperlink();
	Anchor reportLink = new Anchor();
	Button btnBack = new Button("Zurück zur Startseite");
	Button meineBewerbungen = new Button("Meine Bewerbungen");
	Button projektmarktplatzSuchen = new Button("Projektmarktplätze");
	Button agb = new Button("AGB");
	Button impressum = new Button("Impressum");
	Button reportButton = new Button("Report Generator");

	
	public Navigator() {

	}

	/**
	 * Objekt dieser Klasse stellt eine Navigation fuer den Nutzer zur Verfuegung
	 * @param person
	 */
	public Navigator(final Person person) {

		rechtsUnten.add(impressum);
		rechtsUnten.add(agb);
		rechtsUnten.add(btnBack);

		homeNavigator.add(projektmarktplatzSuchen);
		projektmarktplatzSuchen.setWidth("200px");
		projektmarktplatzSuchen.setStylePrimaryName("navi-button");

		homeNavigator.setSpacing(5);
		homeNavigator.setWidth("100%");

		// personalNavigator.add(meineBewerbungen);
		// meineBewerbungen.setWidth("200px");
		// meineBewerbungen.setStylePrimaryName("navi-button");

		personalNavigator.add(reportButton);
		reportButton.setWidth("200px");
		reportButton.setStylePrimaryName("navi-button");

		RootPanel.get("RechtsUnten").add(rechtsUnten);

		this.setWidth("250px");
		this.addStyleName("gwt-StackPanel");
		this.add(homeNavigator, "Startseite");
		this.add(personalNavigator, "Administrationsbereich");

		/**
		 * Erstellung des AGB-Buttons
		 */
		agb.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new AGBMeetProjects();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});

		/**
		 * Erstellung des Impressum-Buttons
		 */
		impressum.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new ImpressumMeetProjects();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});

		/**
		 * Erstellung des Projektmarktplatz-Suchen-Buttons
		 */
		projektmarktplatzSuchen.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				Showcase showcase = new ProjektmarktplatzSeite(rm, getNavigator());
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				currentClickHandler = this;
				currentClickEvent = event;
			}
		});


		/**
		 * Erstellung des "Zurück"-Buttons 
		 */
		btnBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				homeNavigator.removeFromParent();
				personalNavigator.removeFromParent();
				Window.Location.reload();
			}
		});

		/**
		 * Erstellung des Buttons, der zur ReportGenerator-Seite weiterleitet
		 */
		reportButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				reportLink.setHref(GWT.getHostPageBaseURL() + "ProjektmarktplatzReports.html");
				Window.open(reportLink.getHref(), "_self", "");

			}

		});

	}

	//Getter und Setter der ClickHandler
	public ClickHandler getCurrentClickHandler() {
		return currentClickHandler;
	}

	public ClickEvent getCurrentClickEvent() {
		return currentClickEvent;
	}

	public void setCurrentClickHandler(ClickHandler c) {
		currentClickHandler = c;
	}

	public void setCurrentClickEvent(ClickEvent e) {
		currentClickEvent = e;
	}

	/**
	 * Seite neu laden
	 */
	public void reload() {
		currentClickHandler.onClick(currentClickEvent);
	}

	/**
	 * Auslesen der Rolle des Nutzers
	 * @return rm
	 */
	public RoleManagement getIdRole() {
		return rm;
	}

	/**
	 * Setzen der Rolle des Nutzers
	 * @param idRole
	 */
	public void setIdRole(RoleManagement idRole) {
		this.rm = idRole;
	}

	/**
	 * Ausgeben des Navigators
	 * @return Navigator
	 */
	public Navigator getNavigator() {
		return this;
	}

	// public IdentityMarketChoice getIdentityMarketChoice(){
	// return identityMarketChoice;
	// }
	// public void setIdentityMarketChoice(IdentityMarketChoice
	// identityMarketChoice){
	// this.identityMarketChoice=identityMarketChoice;
	// }
}
