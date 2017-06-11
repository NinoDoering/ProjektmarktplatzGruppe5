package de.hdm.itprojekt.client;

import de.hdm.itprojekt.*;

import java.util.Vector;

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

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;


public class ActivitySuchen extends HorizontalPanel  {
	

	
	//proxy
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	//deklarieren von gui variablen
	Label lblAnzeige;
	Button btnButton;
Vector<AnzeigeM> alleAnzeigen;
	TextBox tbBeispiel;
	Label lblBeispiel;
	Label lbl1;
	FlexTable projekttabelle = new FlexTable();
	
	
	// Personen suchen Aktivit�t
	public ActivitySuchen() {
	final	ActivitySuchen as = this;
		
	Projekt p = new Projekt();
alleAnzeigen = new Vector<AnzeigeM>();
	p.setId(1);
		gwtproxy.getAusschreibungByProjekt(p, new AsyncCallback<Vector<Ausschreibung>>() {
			
			@Override
			public void onSuccess(Vector<Ausschreibung> result) {
				// TODO Auto-generated method stub
				Window.alert("So"+result.firstElement().getBeschreibung());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
		gwtproxy.getAllMarktplaetze( new AsyncCallback<Vector<Marktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim Anzeigen");
			}

			@Override
			public void onSuccess(Vector<Marktplatz> result) {
				clear();
			
			for (Marktplatz m : result) { 
		
					final AnzeigeM anzeigen = new AnzeigeM(as);
					
					anzeigen.ID = m.getId();
					anzeigen.btnBezeichnung.setText(m.getBezeichnung());
					add(anzeigen);
					alleAnzeigen.add(anzeigen);
				anzeigen.setStyleName("sub");
				}

			
			}
		});
		
		
		
lblBeispiel = new Label("Projektmarktpl�tze");

tbBeispiel = new TextBox();
btnButton = new Button("Abschicken");
lblAnzeige = new Label("");



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
		
			gwtproxy.findPersonByKey(Integer.parseInt(tbBeispiel.getValue()), callback);
	}

}