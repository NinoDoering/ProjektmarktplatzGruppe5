package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.parsing.parser.trees.ThisExpressionTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AnzeigeM extends HorizontalPanel {
	
	
public int ID;	

public Button btnBezeichnung; 
public Button btnnew;
private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
// TextBoxen für Neues Projekt 

	public AnzeigeM()
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
				Marktplatz pm = new Marktplatz();
				pm.setId(ID);
				gwtproxy.getProjektbyMarktplatz(pm, new AsyncCallback<Vector<Projekt>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Vector<Projekt> result) {
						// TODO Auto-generated method stub
						clear();
						for(Projekt p : result){
							AnzeigeP anzeigep = new AnzeigeP();
							anzeigep.btnBezeichnung.setText(p.getBezeichnung());
							anzeigep.ID = p.getId();
							add(anzeigep);
						}
					}
				});
			}
		});
		
	}
	
 
}
