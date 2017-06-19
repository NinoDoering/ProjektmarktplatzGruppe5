package de.hdm.itprojekt.client;


import de.hdm.itprojekt.client.gui.*;
import de.hdm.itprojekt.shared.FieldVerifier;
import de.hdm.itprojekt.shared.LoginServiceAsync;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.ReportGeneratorAsync;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

/**
 * Entry point Klasse des Projektmarktplatzes. Dafür benötigen wir die Methode
 * <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {

	
	private GreetingServiceAsync projektmarktplatzVerwaltung;
	private LoginServiceAsync loginService;
	
	/**
	 * Deklarierung von Gui-Elementen
	 */
	final Button Logout = new Button("Logout");
	private Button loginButton = new Button("Login");
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel1 = new Label("Herzlich Willkommen auf Prokeko");
	private Label loginLabel = new Label("Bitte melde dich mit deinem Google Account an, um Zugang zu Prokeko zu erhalten.");
	private Anchor signInLink= new Anchor("Login");
	private Anchor signOutLink = new Anchor("Logout");
	
	@Override
	public void onModuleLoad() {
		
		/*
		 * Zuerst instanzieren wir jeweils eine EditorService-Instanz & eine
		 * LoginService Instanz
		 */
		
		//projektmarktplatzVerwaltung = ClientSideSettings.getProjektmarktplatzVerwaltung();
		loginService = ClientSideSettings.getLoginService();
		
		//Überprüfen des Login-Status
		//LoginServiceAsync loginService = GWT.create(LoginService.class); 
		/**
		 * @param GWT.getHostPageBaseURL
		 * @return LoginInfo
		 */
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			
			

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler: " + caught.toString());
				
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()){
					itprojektload();
				} else{
					loadLogin();
				}
				
			}
		});	
	
	}
	
	
	/**
	 * Methode die die Login-Seite läd
	 */
		private void loadLogin(){
			
			loginLabel.setStylePrimaryName("startseite_label");
			loginLabel1.setStylePrimaryName("willkommen_label");
			loginPanel.setSpacing(10);
			loginPanel.add(loginLabel1);
			loginPanel.add(loginLabel);
//			loginPanel.add(loginButton);
			signInLink.setHref(loginInfo.getLoginUrl());

			RootPanel.get("Navigator").add(loginPanel);
			RootPanel.get("Navigator").add(loginButton);
			
			loginButton.setWidth("150px");
			loginButton.setStylePrimaryName("login-btn");
			loginButton.addClickHandler(new ClickHandler() {
				
				
				
				@Override
				public void onClick(ClickEvent event) {
					Window.open(signInLink.getHref(), "_self",
							"");	
				}
			});
		}
		
		
		private void itprojektload(){
			HorizontalPanel addPanel = new HorizontalPanel();
			VerticalPanel mainPanel = new VerticalPanel();
			Showcase showcase = new Startseite();
			mainPanel.add(addPanel);
			mainPanel.add(showcase);
			RootPanel.get("Anzeige").add(mainPanel);
			RootPanel.get("Navigator").add(new Navigator());		
		}
	
}