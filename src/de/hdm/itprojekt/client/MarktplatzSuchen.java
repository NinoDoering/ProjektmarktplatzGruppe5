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

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;

public class MarktplatzSuchen extends HorizontalPanel {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	Label marktplatzShow;
	FlexTable marktplaetze = new FlexTable();
	
	public MarktplatzSuchen() {
		
		marktplatzShow = new Label("Vorhande Marktplaetze"); 
		
		marktplaetze.setText(0, 0, "Marktplaetze");
		add(marktplatzShow);
		AnzeigeM anzeige1 = new AnzeigeM();
		anzeige1.setLblID("2");
		anzeige1.setLblBezeichnung("Chemie");
		add(anzeige1);
		
		
	}
	
	
}
