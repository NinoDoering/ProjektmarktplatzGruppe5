package de.hdm.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;

import de.hdm.itprojekt.shared.bo.Person;




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
import de.hdm.itprojekt.client.LoginInfo;

		public class TopBar extends StackPanel{

			private static ClickHandler currentClickHandler = null;
			private static ClickEvent currentClickEvent = null;
//			RoleManagement rm = new RoleManagement();
			
			HorizontalPanel TopBarPanel = new HorizontalPanel();
			Button LogOUT = new Button("Ausloggen");
			Button meinProfil = new Button("Mein Profil");
//			Button manageRole = new Button("Identität verwalten");
			public TopBar(final Person person, final RoleManagement rm, final Navigator navi){
			
			
			TopBarPanel.add(meinProfil);
//			TopBarPanel.add(manageRole);
			TopBarPanel.add(LogOUT);
			
			
//			LogOUT.setWidth("150px");
//			LogOUT.setStylePrimaryName("loginbutton");

			RootPanel.get("TopBar").add(TopBarPanel);
			
			LogOUT.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
//					Window.open(signOutLink.getHref(), "_self", "");;
				}
				
			});
			
//			manageRole.addClickHandler(new ClickHandler(){
//
//				@Override
//				public void onClick(ClickEvent event) {
//					RoleManagement sh = new RoleManagement(person);
//					RootPanel.get("Anzeige").add(sh);
//					
//				}
//				
//			});
			
		meinProfil.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("Anzeige").clear();
				Showcase sh = new PersonSeite(rm, navi);
				RootPanel.get("Anzeige").add(sh);

			}
		});
			
			
	}

	public ClickHandler getCurrentClickHandler() {
		return currentClickHandler;
	}

	public ClickEvent getCurrentClickEvent() {
		return currentClickEvent;
	}

	public void setCurrentClickHandler(ClickHandler a) {
		currentClickHandler = a;
	}

	public void setCurrentClickEvent(ClickEvent b) {
		currentClickEvent = b;
	}

	public void reload() {
		currentClickHandler.onClick(currentClickEvent);
	}

	// public RoleManagement getIdRole() {
	// return rm;
	// }
	//
	//
	// public void setIdRole(RoleManagement idRole) {
	// this.rm = idRole;
	// }

	public TopBar() {

	}
}


