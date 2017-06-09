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

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

public class Anzeige extends HorizontalPanel {
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	
private Label lblID;
private Button lblBezeichnung; 
private Label testnino;
	public Anzeige()
	{
		
		lblID  = new Label(); 
	 lblBezeichnung = new Button();
	 testnino = new Label();
		add(lblID);
		add(lblBezeichnung);
		add(testnino);
		 
		lblBezeichnung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				Marktplatz pm = new Marktplatz();
				pm.setId(Integer.parseInt(lblID.getText()));
				gwtproxy.getProjektbyMarktplatz(pm, new AsyncCallback<Vector<Projekt>>() {
					
					@Override
					public void onSuccess(Vector<Projekt> result) {
						// TODO Auto-generated method stub
						
					//	Window.alert("Erster Datensatz ist " + result.firstElement().getBezeichnung());
						
						// Bezeichnung der Projekte jetzt nicht als window sonder als Text und beim Klick verschwindet 
						// Button und ID 
						
						remove(lblBezeichnung);
						remove(lblID);
					
						testnino.setText(result.firstElement().getBezeichnung()); 
						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
			}
		});
		
	}
	
	
	public String getLblID() {
		
		return lblID.getText();
	}
	public void setLblID(String text) {
		this.lblID.setText(text);
	}

	public void setLblBezeichnung(String text) {
		this.lblBezeichnung.setText(text);
	}
	
	
	
}
