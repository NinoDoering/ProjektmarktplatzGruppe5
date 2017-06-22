package de.hdm.itprojekt.client;


import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.shared.FieldVerifier;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.LoginService;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point Klasse des Projektmarktplatzes. Daf�r ben�tigen wir die Methode
 * <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {

	
	private GreetingServiceAsync marktplatzVerwaltung;
	private LoginServiceAsync loginService;
	
	/**
	 * Deklarierung von Gui-Elementen
	 */
	private Anchor signInLink= new Anchor("Login");
	private Anchor signOutLink = new Anchor("Logout");
	private VerticalPanel loginPanel = new VerticalPanel();
	final Button Logout = new Button("Logout");
	private Button loginButton = new Button("Login");
	private LoginInfo loginInfo = null;
	private Label WillkommenLabel = new Label("Herzlich Willkommen");
	private Label AnmeldenLabel = new Label("Bitte melde dich mit deinem Google Account an.");
	
	
	@Override
	public void onModuleLoad() {
	
		
		marktplatzVerwaltung = ClientSideSettings.getMarktplatzVerwaltung();
		loginService = ClientSideSettings.getLoginService();
		
		LoginServiceAsync loginService = GWT.create(LoginService.class); 
		

		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
				
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()){
					
					marktplatzVerwaltung.getAllPersons(new AsyncCallback<Vector<Person>>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Kein Login moeglich!");
							
						}

						@Override
						public void onSuccess(Vector<Person> result) {
							boolean isUserRegistered = false;
							
							for (Person person : result){
								if(person.getEmailAddresse()==loginInfo.getEmailAddress()){
									isUserRegistered = true;
									
									itprojektload(person);
									break;
									
								}
							}
						if(isUserRegistered==false){
							RootPanel.get("Anzeige").clear();
							
							RootPanel.get("Anzeige").add(new Registrierungsformular());
						}
						}
						
					});
					
				} else{
					loadLogin();
				}
				
			}
		});	
	
	}
	
	
	
		private void loadLogin(){
			
			loginButton.setWidth("150px");
			loginButton.setStylePrimaryName("buttonLogin");
			AnmeldenLabel.setStylePrimaryName("labelStartseite");
			WillkommenLabel.setStylePrimaryName("labelWillkommen");
			loginPanel.setSpacing(15);
			loginPanel.add(WillkommenLabel);
			loginPanel.add(AnmeldenLabel);
			loginPanel.add(loginButton);
			signInLink.setHref(loginInfo.getLoginUrl());

			RootPanel.get("Navigator").add(loginPanel);
			RootPanel.get("Navigator").add(loginButton);
			loginButton.addClickHandler(new ClickHandler(){
				
				
				
				@Override
				public void onClick(ClickEvent event) {
					Window.open(signInLink.getHref(), "_self",
							"");	
				}
			});
		}
		
		

		private void itprojektload(final Person person){
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Navigator").clear();
			
			signOutLink.setHref(loginInfo.getLogoutUrl());
			Button LogOUT = new Button("Ausloggen");
			HorizontalPanel addPanel = new HorizontalPanel();
			VerticalPanel mainPanel = new VerticalPanel();
			HorizontalPanel rechtsOben = new HorizontalPanel();

			Showcase showcase = new Startseite();
			Button meinProfil = new Button("Mein Profil");
			
			rechtsOben.add(meinProfil);
			rechtsOben.add(LogOUT);

			mainPanel.add(addPanel);
			mainPanel.add(showcase);
			RootPanel.get("RechtsOben").add(rechtsOben);
			RootPanel.get("Anzeige").add(mainPanel);
			RootPanel.get("Navigator").add(new Navigator());	//TONY PART : RootPanel.get("Navigator").add(new Navigator(person));	
			signOutLink.setHref(loginInfo.getLogoutUrl());
			Logout.setWidth("150px");
			Logout.setStylePrimaryName("loginbutton");

			Logout.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					Window.open(signOutLink.getHref(), "_self", "");;
					
				}
				
			});
			
			ReportGeneratorAsync ReportGenerator = ClientSideSettings.getReportGenerator();

			RootPanel.get("Navigator").add(new Navigator());		//TONY PART RootPanel.get("Navigator").add(new Navigator(person));		
			
			meinProfil.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get("Anzeige").clear();
					Showcase sh = new PersonSeite(person);
					RootPanel.get("Anzeige").add(sh);
				
				}
			});
			
			
			
			
		}

		private class Registrierungsformular extends Showcase{


			private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
			
			private VerticalPanel vMain = new VerticalPanel();
			private FlexTable buttonPanel = new FlexTable();
			private FlexTable formPanel = new FlexTable();
			
			
			private Button saveButton = new Button("Absenden");
			
			
			private ListBox titelEing = new ListBox();
			private TextBox vNameEing = new TextBox();
			private TextBox nNameEing = new TextBox();
			private TextBox adresseEing = new TextBox();
			private TextBox ortEing = new TextBox();
			private TextBox emailEing = new TextBox();
			
			
			private Label titelTxt = new Label("Titel");
			private Label vNameTxt = new Label("Vorname");
			private Label nNameTxt = new Label("Nachname");
			private Label adresseTxt = new Label("Straße/Hausnummer");
			private Label ortTxt = new Label("PLZ/Ort");
			private Label emailTxT = new Label("Google-Mail");
			
			private GreetingServiceAsync marktplatzVerwaltung = 
					ClientSideSettings.getMarktplatzVerwaltung();

			@Override
			protected String getHeadlineText() {
				// TODO Auto-generated method stub
				return "Hier können Sie sich für MeetProjects registrieren";
			}

			@Override
			protected void run() {
			
				emailEing.setText(loginInfo.getEmailAddress());
				emailEing.setReadOnly(true);
				//Stylen der Buttons
				saveButton.setStylePrimaryName("navi-button");
				
				//Hinzufügen der Inhalte der titelEing
				titelEing.addItem("Prof.");
				titelEing.addItem("Prof. Dr.");
				titelEing.addItem("Prof. Dr. Ing.");
				titelEing.addItem("Dr. Ing.");
				titelEing.addItem("Ing.");
				titelEing.addItem("Prof. Ing.");
				
				// Befüllen der FlexTable
				formPanel.setWidget(0, 1, emailEing);
				formPanel.setWidget(0, 0, emailTxT);
				
				formPanel.setWidget(1, 1, titelEing);
				formPanel.setWidget(1, 0, titelTxt);

				formPanel.setWidget(2, 1, vNameEing);
				formPanel.setWidget(2, 0, vNameTxt);

				formPanel.setWidget(3, 1, nNameEing);
				formPanel.setWidget(3, 0, nNameTxt);

				formPanel.setWidget(4, 1, adresseEing);
				formPanel.setWidget(4, 0, adresseTxt);

				
				formPanel.setWidget(7, 1, ortEing);
				formPanel.setWidget(7, 0, ortTxt);


				vMain.setSpacing(6);
				buttonPanel.setWidget(0, 1, saveButton);

				vMain.add(formPanel);
				vMain.add(buttonPanel);
				this.add(vMain);
				
				//ClickHandler, der bei einem Klick auf den Absenden Button den ProfilBearbeitenCallback ausführt.

				saveButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						try {
							vNameEing.getText();
							marktplatzVerwaltung.anlegenPartnerprofil(new NeuePersonAnlegen());
						} catch (Exception e) {
							Window.alert("Z 299!");
						}
					
					}
				});
				
			
			}
			
			private class NeuePersonAnlegen implements AsyncCallback<Partnerprofil>{
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Partnerprofil result) {
					if(vNameEing.getText().isEmpty()==false){
					marktplatzVerwaltung.anlegenPerson(new Integer(0), new Integer(0), result.getId(), vNameEing.getText(), nNameEing.getText(), titelEing.getSelectedItemText(), emailEing.getText(), ortEing.getText(), adresseEing.getText(),   
							   new AsyncCallback<Person>() {

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Neuer User konnte nicht erstellt werden!");
								}

								@Override
								public void onSuccess(Person result) {
									Window.alert("Glückwunsch " + vNameEing.getText() +" "+ nNameEing.getText()+"! Sie sind jetzt Teilnehmer bei MeetProjects!");
									itprojektload(result);
									
								}

								
							});
				}
			}
			
		}
				
			}
}

