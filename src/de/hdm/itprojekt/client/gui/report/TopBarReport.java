package de.hdm.itprojekt.client.gui.report;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;

import de.hdm.itprojekt.shared.bo.Person;

import com.google.gwt.user.client.ui.Button;

public class TopBarReport extends StackPanel{

	private static ClickHandler currentClickHandlerReport = null; 
	private static ClickEvent currentClickEvent = null;
	
	 HorizontalPanel topBarPanelReport = new HorizontalPanel();
	 Button logOutReport = new Button("Ausloggen");
	public TopBarReport(){
		
	}
	 public TopBarReport (final Person person){
		 topBarPanelReport.add(logOutReport);
		 
		 RootPanel.get("TopBarReport").add(topBarPanelReport);
		 
		 logOutReport.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	 }
}
