package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ActivitySuchen extends HorizontalPanel {

	public ActivitySuchen() {
		// Projektmarktplatz suchen Aktivit�t
		
Label lblBeispiel = new Label("Projektmarktpl�tze");

TextBox tbBeispiel = new TextBox();
Button btnButton = new Button("Abschicken");
Label lblAnzeige = new Label("Heyo");

add(tbBeispiel);
add(btnButton);
add(lblAnzeige);
	
	

	}
	
}
