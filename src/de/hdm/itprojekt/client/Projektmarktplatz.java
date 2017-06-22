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
 * Entry point Klasse des Projektmarktplatzes. Dafür benötigen wir die Methode
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
	
		
		
		//Logout funktion muss noch iwo rein!
		//signOutLink.setHref(loginInfo.getLogoutUrl());
		
		
		
		marktplatzVerwaltung = ClientSideSettings.getMarktplatzVerwaltung();
		loginService = ClientSideSettings.getLoginService();
		
		//Überprüfen des Login-Status
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
									
									itprojektload(person.getId());
									break;
									
								}
							}
						if(isUserRegistered==false){
							RootPanel.get("Details").clear();
							
							RootPanel.get("Details").add(new RegistrierungsFormular());
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
		
		

		private void itprojektload(int id){
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
			RootPanel.get("Navigator").add(new Navigator());	
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

			RootPanel.get("Navigator").add(new Navigator(person));		
			
			meinProfil.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					RootPanel.get("Anzeige").clear();
					Showcase sh = new PersonSeite(person);
					RootPanel.get("Anzeige").add(sh);
				
				}
			});
			
			
			
			
		}

}

//	public class RegistrierungsFormular extends Showcase{
//
//
//	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
//	
//	private VerticalPanel vMain = new VerticalPanel();
//	private FlexTable buttonPanel = new FlexTable();
//	private FlexTable formPanel = new FlexTable();
//	
//	
//	private Button saveButton = new Button("Absenden");
//	
//	
//	private ListBox anredeEing = new ListBox();
//	private TextBox vNameEing = new TextBox();
//	private TextBox nNameEing = new TextBox();
//	private TextBox strasseEing = new TextBox();
//	private TextBox hausnrEing = new TextBox();
//	private TextBox plzEing = new TextBox();
//	private TextBox ortEing = new TextBox();
//	private TextBox emailEing = new TextBox();
//	
//	
//	private Label anredeTxt = new Label("Anrede");
//	private Label vNameTxt = new Label("Vorname");
//	private Label nNameTxt = new Label("Nachname");
//	private Label strasseTxt = new Label("Straße");
//	private Label hausnrTxt = new Label("Hausnummer");
//	private Label plzTxt = new Label("Postleitzahl");
//	private Label ortTxt = new Label("Ort");
//	private Label emailTxT = new Label("Google-Mail");
//	
//	private GreetingServiceAsync marktplatzVerwaltung = 
//			ClientSideSettings.getMarktplatzVerwaltung();
//
//	@Override
//	protected String getHeadlineText() {
//		// TODO Auto-generated method stub
//		return "Hier können Sie sich für MeetProjects registrieren";
//	}
//
//	@Override
//	protected void run() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
