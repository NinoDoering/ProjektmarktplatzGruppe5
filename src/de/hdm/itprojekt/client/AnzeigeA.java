package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AnzeigeA extends HorizontalPanel {
	
	
public int ID;	
public String text;
public Button btnBezeichnung; 
public Button btnnew;
public Button btnBewerben;
public Label headerA;
public Label lblBeschreibung;
public Label lblStatus;
public Label lblBewerbFrist;
public Label lblBezeichnung;
private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
// TextBoxen für Neues Projekt 
 
	public AnzeigeA()
	{
		
		 btnnewA = new Button("+");
		
	 btnBezeichnung = new Button();
	
	 btnnew = new Button("+"); 
	 btnBewerben = new Button("online bewerben");
	  headerA = new Label(); 
	 lblBezeichnung = new Label(); 
	 lblBeschreibung = new Label();
	 lblStatus = new Label();
	 lblBewerbFrist =new Label();
	 
	 // TextBoxen für Neues Projekt 
	 btnBezeichnung.setStyleName("Abstand");
	 btnnew.setStyleName("Abstand");
	 btnBezeichnung.setText("Beschreibung:");
		add(btnBezeichnung);
		
		btnBewerben.setStyleName("btn1");
		btnBezeichnung.addClickHandler(new ClickHandler() {
	
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			clear();
			lblStatus.setStyleName("Abstandou");
			add(headerA);
			add(lblBezeichnung);
			add(lblBeschreibung);
			add(lblStatus);
			add(lblBewerbFrist);
			add(btnBewerben);
			
		}
	});
		
	}
	
 
}
