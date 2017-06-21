package de.hdm.itprojekt.client.gui;

import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.itprojekt.client.Showcase;

public class ImpressumMeetProjects extends Showcase{

	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<b>Impressum</b>";
	}

	@Override
	protected void run() {
		
		
		RootPanel.get("Anzeige").setWidth("100%");
		
		this.append("<div class='Impressum'>"
				+ "<b>Betreuer bei diesem IT-Projekt waren Herr Prof. Dr. Rathke und Thies."
				+ "<b>Studiengang: Wirtschaftsinformatik und Digitale Medien</b></br>"
				+ "Hochschule der Medien"+ "</br>"
				+ "Nobelstrasse 10</br>"
				+ "70569 Stuttgart</br></br>"
				+ "Kontakt</br>Telefon: +49 711 8923 10</br>"
				+ "<br><br>Der Studiengang Wirtschaftsinformatik und digitale Medien wird rechtlich vertreten durch die Hochschule der Medien Stuttgart." 
				+ "<br> <br><A HREF=\"https://www.hdm-stuttgart.de/impressum\"TARGET=\"_blank\">Impressum der Hochschule</A>"
				+ "</div>");
	}
}                        