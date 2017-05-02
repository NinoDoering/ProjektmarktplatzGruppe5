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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

//Rueckgaengig
//ZweiterVersuch
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {
	Label l = new Label("Hallo meine Freunde der Sonne. Der  ist der coolste!");
	Label l1 = new Label(" Unser Projektmarktplatz von der Gruppe 5");
	
	public void onModuleLoad() {
		RootPanel.get().add(l);
		RootPanel.get().add(l1);
		
		
		Button b = new Button("klick mich hart");
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				l.setText("nein ,  is der Coolste");
			}
			
			
			
		});
		RootPanel.get("dieter").add(b);
		
		
		
	}
	// erneut wichtig!!!
}
