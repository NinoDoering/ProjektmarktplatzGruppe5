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
import de.hdm.itprojekt.shared.bo.*;

public class TopBar extends HorizontalPanel{

	private static ClickHandler currentClickHandler = null;
	private static ClickEvent currentClickEvent = null;
	
	HorizontalPanel TopBarPanel = new HorizontalPanel();
	
	Button LogOUT = new Button("Ausloggen");
	Button meinProfil = new Button("Mein Profil");
	Button manageRole = new Button("Identität verwalten");
	
	public TopBar(final Person person){
	
	
	TopBarPanel.add(meinProfil);
	TopBarPanel.add(manageRole);
	TopBarPanel.add(LogOUT);
	
	LogOUT.setWidth("150px");
	LogOUT.setStylePrimaryName("loginbutton");

	LogOUT.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
//			Window.open(signOutLink.getHref(), "_self", "");;
			
		}
		
	});
	
	manageRole.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	meinProfil.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("Anzeige").clear();
			Showcase sh = new PersonSeite(person);
			RootPanel.get("Anzeige").add(sh);
		
		}
	});
	
	
	
}}
