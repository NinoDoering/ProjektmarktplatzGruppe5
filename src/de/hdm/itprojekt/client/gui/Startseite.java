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
		this.append("<p> blablaba</p>");
		
		this.add(homePanel);
	}

	
	
	
	
}
