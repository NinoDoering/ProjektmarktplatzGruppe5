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
	
// extends HPanel damit der Klasse widget hinzugefügt werden siehe unten bei *#* 
	
	
	//proxy bzw RPC sorgt für die Kommunikation der GUI mit der Administrations (GreetingServiceImpl ) 
	// das proxy ruft die Methoden der Impl auf 
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	//deklarieren von gui variablen
	Label lblAnzeige;
	Button btnButton;
	Vector<AnzeigeM> alleAnzeigen;
	TextBox tbBeispiel;
	Label lblBeispiel;
	Label lbl1;
	FlexTable projekttabelle = new FlexTable();
	
	
	// Personen suchen Aktivitaet  konstruktor um alle Projektmarktplätze aus der DB in der GUI anzu zeigen
	
	public ActivitySuchen() {
	final	ActivitySuchen as = this;
		
	Projekt p = new Projekt();
	alleAnzeigen = new Vector<AnzeigeM>();
	p.setId(1);
		
		
	
		// unser Proxy ruft die Methode auf um alle Marktplaete anzuzeigen
		gwtproxy.getAllMarktplaetze( new AsyncCallback<Vector<Marktplatz>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Fehler beim Anzeigen");
			}

			@Override
			public void onSuccess(Vector<Marktplatz> result) {
				clear();
				
				
				// for schleife durchlaeuft  so lange bis alles Marktplaetze ausgegeben werden 
				// "Marktplatz m : result" steht als abkürzung fuer for (int i = 0; i < .... i++ )

			for (Marktplatz m : result) { 
		
					final AnzeigeM anzeigen = new AnzeigeM(as);
					
					anzeigen.ID = m.getId();
					anzeigen.btnBezeichnung.setText(m.getBezeichnung()); 
					 //ainzeigen objekt ruft button btnBezeichnung aus der AnzeigeM klasse auf  
					//und mit setText wird über m.getBezeichnung  die Info aus der Db geholt 
					
					add(anzeigen);
					// anzeigen wird dem Panel bzw ActivitySucehn hinzugefügt   sieh oben *#*
					alleAnzeigen.add(anzeigen);
					//anzeigen wird dem Vector alleAnzeigen  hinzugefügt 
				anzeigen.setStyleName("sub");
				}

			
			}
		});
		


		//!!!!!!!!!!!!!!!!! alles unter dieser Zeile     für das erste mal ignorieren  !!!!!!!!
		
	
		
		
//		gwtproxy.getAusschreibungByProjekt(p, new AsyncCallback<Vector<Ausschreibung>>() {
//		
//		@Override
//		public void onSuccess(Vector<Ausschreibung> result) {
//			// TODO Auto-generated method stub
//			Window.alert("So"+result.firstElement().getBeschreibung());
//		}
//		
//		@Override
//		public void onFailure(Throwable caught) {
//			// TODO Auto-generated method stub
//			
//		}
//	});
		
		
		
		
lblBeispiel = new Label("Projektmarktplï¿½tze");

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