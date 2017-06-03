package de.hdm.itprojekt.client;

import de.hdm.itprojekt.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.client.GreetingService;
import de.hdm.itprojekt.client.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;


public class ActivitySuchen extends HorizontalPanel  {
	
	
	
	//proxy
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	//deklarieren von gui variablen
	Label lblAnzeige;
	Button btnButton;
	TextBox tbBeispiel;
	Label lblBeispiel;
	Label lbl1;
	FlexTable projekttabelle = new FlexTable();
	
	// Personen suchen Aktivit�t
	public ActivitySuchen() {
		
		
		
lblBeispiel = new Label("Projektmarktpl�tze");

tbBeispiel = new TextBox();
btnButton = new Button("Abschicken");
lblAnzeige = new Label("Heyo");



btnButton.addClickHandler(new ClickHandler() {
	
	@Override
	public void onClick(ClickEvent event) {

	doStuff();	
		
	}
});

projekttabelle.setText(0, 0, "blabla");
add(tbBeispiel);
add(btnButton);
add(lblAnzeige);
add(projekttabelle);

	
	

	}
	private void doStuff(){
		
		if (gwtproxy == null){
			gwtproxy = GWT.create(GreetingService.class);
		}

		AsyncCallback<Person> callback = new AsyncCallback<Person>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("onFailure");
			}

			@Override
			public void onSuccess(Person result) {
				// TODO Auto-generated method stub
				
				projekttabelle.setText(0, 0, result.getNachname());
			}
			
		};
		//	gwtproxy.findPersonByKey(Integer.tbBeispiel.getText(), callback);
			gwtproxy.findPersonByKey(Integer.parseInt(tbBeispiel.getValue()), callback);
	}

}