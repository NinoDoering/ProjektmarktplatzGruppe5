package de.hdm.itprojekt.client;

import de.hdm.itprojekt.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.client.GreetingService;
import de.hdm.itprojekt.client.GreetingServiceAsync;
import de.hdm.itprojekt.server.GreetingServiceImpl;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.server.*;

public class Marktplatzerstellen extends HorizontalPanel {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	
	
	private TextBox tBox1; 
	private TextBox tBox2;
	
	private Button b1; 
	
	private Label l1;
	private Label l2; 
	
	public MarktplatzErstellen(){
	
	l1 = new Label("Erstelle einen neuen Marktplatz");
	b1 = new Button("Marktplatz anlegen"); 
	tBox1 = new TextBox();
	
	b1.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			gwtproxy.anlegenMarktplatz(tBox1.getText(), new MarktplatzAnlegenCallback()); 
			}
		
		
	});
	
	tBox1.getElement().setPropertyString("placeholder", "Marktplatzname");
	
	
}
	
	
}
