package de.hdm.itprojekt.client;

import de.hdm.itprojekt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

//Rueckgaengig
//ZweiterVersuch
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {
	
	
	public void onModuleLoad() {
		
		/*    
		HorizontalPanel h1Panel = new HorizontalPanel();
		HorizontalPanel h2Panel = new HorizontalPanel();
		VerticalPanel vPanel = new VerticalPanel();
		

		Label myLbl = new Label("Projektmarktplatz");

		vPanel.add(myLbl);
			
		
		Button btn2 = new Button ("Projektmarktplatz erstellen");
		h2Panel.add(btn2);
		
		RootPanel.get("btn").add(h1Panel);
		RootPanel.get("btn").add(h2Panel);
		
		
		
		*/
		
		Button btn1 = new Button("Projektmarktplatz suchen"); 
		
		SimplePanel simplePanel = new SimplePanel();
		simplePanel.add(btn1);
		RootPanel.get("Projektmarktplatz suchen").add(simplePanel);

			
	
		
	}
	// erneut wichtig!!!
}
