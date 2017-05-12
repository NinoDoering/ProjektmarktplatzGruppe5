package de.hdm.itprojekt.client;

import de.hdm.itprojekt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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

		
			
		Button btn1 = new Button("Projektmarktplatz suchen"); 
		
		Button btn2 = new Button ("Projektmarktplatz erstellen");
		
		HorizontalPanel hpMain = new HorizontalPanel();
		
		btn1.setStyleName("btn1");
		btn2.setStyleName("btn2");
		
		hpMain.add(btn1);
		
		hpMain.add(btn2);

		
		
		RootPanel.get("PMarktSuch").add(hpMain);
		
		
		
	}
	// erneut wichtig!!!
	// teeeeeeeeest
}
