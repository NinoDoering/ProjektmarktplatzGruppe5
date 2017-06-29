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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class DialogBoxBeteiligung extends DialogBox {
	
	
	private GreetingServiceAsync gwtproxy = ClientSideSettings.getMarktplatzVerwaltung();
	
	// Panels und weitere GUI Elemente erstellen
	private VerticalPanel vPanelBewertung = new VerticalPanel();
	private HorizontalPanel hPanelBewertung = new HorizontalPanel();
	
	
	private FlexTable beteiligungstabelle = new FlexTable();

	private Button beteiligungAnlegen = new Button("Beteiligung anlegen");
	private Button bewertungAnlegen = new Button("Bewertung speichern");
	private Button abbrechen = new Button("Abbrechen");
	private Button noBeteiligung = new Button("Beteiligung verweigern");
	
	private Label beteiligungsZeit = new Label("Beteiligungszeit");
	private Label zugehoerigesProjekt = new Label ("Beteiligung wird zu folgendem Projekt erstellt: ");
	private Label lbltextBewertung = new Label("Ihre textuelle Bewerbung: ");
	private Label lblfloatBewertung = new Label("Fliesskommabewertung: ");
	private ListBox floatBewertung = new ListBox();
	
	
	
	private TextBox beteiligungsZeitTxta = new TextBox();
	private TextArea textuelleBewertung = new TextArea();
	
	
	private Beteiligung beteiligung = new Beteiligung();

	private Bewertung bewertung;
	private Person person;
	private Bewerbung bewerbung;
	private Ausschreibung ausschreibung1;
	private Projekt projekt1;
	
	private RoleManagement rm = null;
	private Navigator navi = null;


	public DialogBoxBeteiligung(final RoleManagement rm, final Navigator navi, final Ausschreibung ausschreibung , final Bewerbung bewerbung ){
		this.rm=rm;
		this.navi=navi;
		this.ausschreibung1=ausschreibung;
		this.bewerbung=bewerbung;
		
		this.setText("Beteiligung und Bewertung anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		hPanelBewertung.add(bewertungAnlegen);
		hPanelBewertung.add(beteiligungAnlegen);
		hPanelBewertung.add(abbrechen);
		
		
		floatBewertung.addItem("0.0");
		floatBewertung.addItem("0.1");
		floatBewertung.addItem("0.2");
		floatBewertung.addItem("0.3");
		floatBewertung.addItem("0.4");
		floatBewertung.addItem("0.5");
		floatBewertung.addItem("0.6");
		floatBewertung.addItem("0.7");
		floatBewertung.addItem("0.8");
		floatBewertung.addItem("0.9");
		floatBewertung.addItem("1.0");
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		});
		
		noBeteiligung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		});
		
		beteiligungAnlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert("final RoleManagement rm,"+ rm.getUser().getId() + " final Ausschreibung ausschreibung ," +ausschreibung.getId()+"final Bewerbung bewerbung"+ bewerbung.getId());
			}
		});
		
		bewertungAnlegen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				gwtproxy.anlegenBewertung(bewerbung.getId(), textuelleBewertung.getText(), Float.parseFloat(floatBewertung.getSelectedValue()), new insertBewertungInDB());
				hide();
			}
		});
		
		
		
		vPanelBewertung.add(beteiligungstabelle);
		vPanelBewertung.add(hPanelBewertung);
		this.add(vPanelBewertung);
		beteiligungstabelle.setWidget(1, 0, lbltextBewertung);
		beteiligungstabelle.setWidget(1, 1, textuelleBewertung);
		beteiligungstabelle.setWidget(2, 0, lblfloatBewertung);
		beteiligungstabelle.setWidget(2, 1, floatBewertung);
		beteiligungstabelle.setWidget(3, 0, beteiligungsZeit);
		beteiligungstabelle.setWidget(3, 1, beteiligungsZeitTxta);
		beteiligungstabelle.setWidget(4, 0, bewertungAnlegen);
		beteiligungstabelle.setWidget(4, 1, beteiligungAnlegen);
		beteiligungstabelle.setWidget(5, 0, abbrechen);
		beteiligungstabelle.setWidget(5, 1, noBeteiligung);
		
		
	}
	
	private class insertBewertungInDB implements AsyncCallback<Bewertung>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
		Window.alert("Fehler beim Erstellen des Bewertung");
		}

		@Override
		public void onSuccess(Bewertung result) {
			// TODO Auto-generated method stub
			Window.alert("Bewertung erstellt");
			
			Showcase showcase = new EingegangeneBewerbungenSeite(rm, navi, ausschreibung1);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
			
		}
	}
}
	


