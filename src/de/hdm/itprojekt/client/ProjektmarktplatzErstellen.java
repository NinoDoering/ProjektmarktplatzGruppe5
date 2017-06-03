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
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

	public class ProjektmarktplatzErstellen extends HorizontalPanel {

// proxy 
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class); 
// gui variablen
	
	FlexTable ausschreibung = new FlexTable(); 
	 Button bMarktplatz;
	 Label eins;
	 Label zwei;
	 Label drei;
	 TextBox tBox;
	 // Projektmarktplatz suchen AKtivität
	public ProjektmarktplatzErstellen(){
	
		eins = new Label ("Marktplätze"); 
		tBox = new TextBox(); 
		bMarktplatz = new Button("SEND"); 
		zwei = new Label ("Hallo"); 
		
		bMarktplatz.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				doThis(); 
				
			}
			
			
		}); 
		
	ausschreibung.setText(0,0, "Salut"); 
	add(tBox);
	add(bMarktplatz);
	add(zwei);
	add(ausschreibung);
	
	
	}
		private void doThis() {
			if (gwtproxy == null){
				gwtproxy = GWT.create(GreetingService.class);
			}

			AsyncCallback<Ausschreibung> callback = new AsyncCallback<Ausschreibung>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Geht nix");
					
				}

				@Override
				public void onSuccess(Ausschreibung result) {
					// TODO Auto-generated method stub
					Window.alert("Jawoll");
					ausschreibung.setText(0, 0, result.getBezeichnung());
				}
		};
		
		gwtproxy.findAusschreibungByKey(Integer.parseInt(tBox.getValue()), callback);
	}}