package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Person;

public class DialogBoxBeteiligung extends DialogBox {
	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	// Panels und weitere GUI Elemente erstellen
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	private FlexTable beteiligungstabelle = new FlexTable();

	private Button beteiligungAnlegen = new Button();
	
	private Label beteiligungsZeit = new Label("Beteiligungszeit");
	private Label zugehoerigesProjekt = new Label ("Beteiligung wird zu folgendem Projekt erstellt: ");
	
	private TextArea beteiligungsZeitTxta = new TextArea();
	private TextArea zugehoerigesProjektTxta = new TextArea();
	
	private Beteiligung beteiligung = new Beteiligung();
	private Ausschreibung ausschreibung;
	private Bewertung bewertung;
	private Person person;
	private Bewerbung bewerbung;
	
	
	//Konstruktor mit Paremeter zur Übergabe
	public DialogBoxBeteiligung(final Bewerbung bewerbung, final Ausschreibung ausschreibung, Bewertung bewertung, Person person){
		this.bewerbung = bewerbung;
		this.ausschreibung = ausschreibung;
		this.bewertung= bewertung;
		this.person = person;
		
		this.setText("Projektbeteiligung anlegen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		
		
		beteiligungstabelle.setWidget(0, 0, beteiligungsZeit);
		beteiligungstabelle.setWidget(1, 0, beteiligungsZeitTxta);
		
		vPanel.add(beteiligungstabelle);
		vPanel.add(beteiligungAnlegen);
		hPanel.add(vPanel);
		
	
		this.add(hPanel);
		
		// Beginn der ClickHandler
		
		beteiligungAnlegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				beteiligung.setBeteiligungszeit(beteiligungsZeit.getText());
				beteiligung.setIdBeteiligter(ausschreibung.getIdAusschreibender());
				beteiligung.setIdProjekt(ausschreibung.getIdProjekt());
				
				bewerbung.setBewerbungsStatus("zusage");
			
				
			}
		
	});
}
	
	
}

	

