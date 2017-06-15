package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Showcase;

public class Startseite extends Showcase{

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		VerticalPanel homePanel = new VerticalPanel();
		this.append("<p> <h1> Herzlich Willkommen zu SEBHATU </h1>"
				 + "<br> <text> SEBHATU ist eine neue Art Projekte zu erstellen um sie mit anderen Menschen in der SEBHATU-Community zu teilen.  </text>" +
				" </p>");
		
		this.add(homePanel);
	}

	
	
	
	
}
