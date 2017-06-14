package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.server.*;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;

public class ActivityBewerbungen extends HorizontalPanel {
//	VerticalPanel vpanel = new VerticalPanel();
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	Vector<AnzeigeB> alleBewerbungen;
	Label lbltest;
	
	
	public ActivityBewerbungen(){
		final ActivityBewerbungen as = this;
		
//		alleBewerbungen = new Vector<AnzeigeB>();
		lbltest = new Label();
				
				Bewerbung b = new Bewerbung();
				b.setId(1);
				gwtproxy = GWT.create(GreetingService.class);
				gwtproxy.getAllBewerbungen(new AsyncCallback<Vector<Bewerbung>>(){
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Vector<Bewerbung> result) {
											
						Window.alert("Funktioniert");
						
						clear();
					
						for(Bewerbung b : result){
							AnzeigeB anzeigeb = new  AnzeigeB();
							
							anzeigeb.headerA.setText("Sie befinden sich auf folgender Bewerbung:  ");
							anzeigeb.lblId.setText("ID: "+b.getId());
							anzeigeb.lblBewerbungsStatus.setText("Bewerbungsstatus: "+b.getBewerbungsStatus());
							anzeigeb.ID = b.getId();
							anzeigeb.setStyleName("subb");
							add(anzeigeb);
							
							
					}
					}
					
				});
			
		
	
}
}
