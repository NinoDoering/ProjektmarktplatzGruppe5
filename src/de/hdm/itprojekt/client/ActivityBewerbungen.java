package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.server.*;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;

public class ActivityBewerbungen extends HorizontalPanel {

	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	Vector<AnzeigeB> alleBewerbungen;
	Label lbltest;
	
	public ActivityBewerbungen(){
		final ActivityBewerbungen as = this;
		
		alleBewerbungen = new Vector<AnzeigeB>();
		lbltest = new Label();
		gwtproxy.getAllBewerbungen(new AsyncCallback<Vector<Bewerbung>>() {
			
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim anzeigen");
			}
			
			@Override
			public void onSuccess(Vector<Bewerbung> result) {
				clear();
				
			for(Bewerbung b : result){
				
				final AnzeigeB anzeigen = new AnzeigeB(as);
				
				anzeigen.ID = b.getId();
				add(anzeigen);
				
				alleBewerbungen.add(anzeigen);
				anzeigen.setStyleName("sub");
				
			}
			Window.alert("alles gut");
						}
	
			
		});
	}
	
	
}
