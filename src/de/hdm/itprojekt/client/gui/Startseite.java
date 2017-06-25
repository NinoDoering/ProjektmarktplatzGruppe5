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
		this.append("<p> <h1> Herzlich Willkommen zu MeetProjects </h1>"
				 + "<br> <text> MeetProjects ist eine neue Art Projekte zu erstellen, um Sie mit anderen Menschen in der MeetProjects-Community zu teilen.  </text>"
				 + "<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>" +
					
					" </p>");
		
		this.add(homePanel);
	}

	
	
	
	
}
