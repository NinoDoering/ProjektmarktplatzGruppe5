package de.hdm.itprojekt.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HTMLPanel extends VerticalPanel {

	public void append(String text){
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("itproject-simpletext");
	    this.add(content);
	}
	
	
}
