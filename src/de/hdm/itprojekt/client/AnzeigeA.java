package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class AnzeigeA extends HorizontalPanel {
	
	
public int ID;	

public Button btnBezeichnung; 
public Button btnnew;

// TextBoxen f�r Neues Projekt 

	public AnzeigeA()
	{
		
	
	 btnBezeichnung = new Button();
	
	 btnnew = new Button("+"); 
	
	 // TextBoxen f�r Neues Projekt 
btnBezeichnung.setStyleName("Abstand");
	btnnew.setStyleName("Abstand");
		add(btnBezeichnung);
		add(btnnew);
		
	}
	
 
}
