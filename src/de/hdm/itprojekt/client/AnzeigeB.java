package de.hdm.itprojekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;

public class AnzeigeB extends HorizontalPanel {
	
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	public int ID;
	public Label headerA;
	public Label lblId;
	public Label lblBewerbungsStatus;
	
	public AnzeigeB(){
		
	}
	public AnzeigeB(final ActivityBewerbungen AS){
		
		
		lblId = new Label();
		lblBewerbungsStatus = new Label();
		
		
		
		
		
		
		
		
	}
public class alleBewerbungenausDB{
	
}
}
