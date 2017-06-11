package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AnzeigeP extends HorizontalPanel {
	
	
public int ID;	

public Button btnBezeichnung; 
public Button btnnew;
private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
// TextBoxen für Neues Projekt 

	public AnzeigeP()
	{
		
	
	 btnBezeichnung = new Button();
	
	 btnnew = new Button("+"); 
	
	 // TextBoxen für Neues Projekt 
btnBezeichnung.setStyleName("Abstand");
	btnnew.setStyleName("Abstand");
		add(btnBezeichnung);
		add(btnnew);
		btnBezeichnung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Projekt p = new Projekt();
				p.setId(ID);
				gwtproxy.getAusschreibungByProjekt(p, new AsyncCallback<Vector<Ausschreibung>>() {
					
					@Override
					public void onSuccess(Vector<Ausschreibung> result) {
						// TODO Auto-generated method stub
						clear();
						for(Ausschreibung a : result){
							AnzeigeA anzeigea = new  AnzeigeA();
							anzeigea.btnBezeichnung.setText(a.getBezeichnung());
							anzeigea.ID = a.getId();
							add(anzeigea);
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
	}
	
 
}
