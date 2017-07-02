package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

/**
 * Diese Klasse ist dafür zustaendig, dass eine Organisationseinheit (Person,
 * Team, Unternehmen) ein Projekt erstellen kann. Eine Organisationseinheit kann
 * ein Projekt erstellen, sobald diese auf die Funktion "Projekt erstellen"
 * klickt. Erstellt die Organisationseinheit ein Projekt, so ist diese automatisch der Projektleiter. 
 */
public class DialogBoxProjektAnlegen extends DialogBox{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	VerticalPanel projektVP = new VerticalPanel();
	HorizontalPanel projektHP = new HorizontalPanel();
	private Marktplatz mp2 = new Marktplatz();
	private Person projektLeiter = new Person();
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	private RoleManagement rm = null;
	private Navigator navi = null;
	Label projektbezeichnung = new Label("Projektbezeichnung");
	TextArea projektbez = new TextArea();
	
	Label projektbeschreibung = new Label("Projektbeschreibung");
	TextArea projektbeschr = new TextArea();
	
	Label startdatum = new Label("Startdatum des Projektes");
	DatePicker startD = new DatePicker();
	Label textStart = new Label();
	
	
	Label enddatum = new Label("Enddatum des Projektes");
	DatePicker endD = new DatePicker();
	final Label textEnde = new Label();
	//!!!!!!!!!!!!!!! f�r test Person ID manuell eingeben !!!!!!!!!!!!!!!!!!!!!!!!
	Label projektleiter = new Label("ID des zugeh�rigen Projektleiters");
	TextBox proLeit	= new TextBox();
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	FlexTable projektdialogboxtabelle = new FlexTable(); 
	
	public DialogBoxProjektAnlegen(final Marktplatz zugehoerigerMarktplatz, final RoleManagement rm, final Navigator navi){
		this.mp2= zugehoerigerMarktplatz;
		this.rm = rm;
		this.navi = navi;
		Label zugehMarktplatzBez = new Label(zugehoerigerMarktplatz.getBezeichnung());
		
		//Datepicker 
		startD.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				textStart.setText(datum);
			}
			
		});
		
		startD.setValue(new Date(), true);
		
		endD.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				textEnde.setText(datum);
			}
		});
		
		endD.setValue(new Date(), true);
		
		this.setText("Projekt anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		projektHP.add(ok);
		projektHP.add(abbrechen);
		

		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			Window.alert("Neues Projekt gehört zum " + zugehoerigerMarktplatz.getBezeichnung()+" Marktplatz");
//			Window.alert("Projektleiter hat die ID: " + rm.getUser().getId());
				gwtproxy.anlegenProjekt(rm.getUser().getId(), zugehoerigerMarktplatz.getId(), projektbeschr.getText(), projektbez.getText(), startD.getValue(), endD.getValue(), new projektinDB());
				
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			hide();
				
			}
		});
		
		projektVP.add(projektdialogboxtabelle);
		projektVP.add(projektHP);
		this.add(projektVP);
		projektdialogboxtabelle.setWidget(1, 0, projektbezeichnung);
		projektdialogboxtabelle.setWidget(1, 1, projektbez);
		projektdialogboxtabelle.setWidget(2, 0, projektbeschreibung);
		projektdialogboxtabelle.setWidget(2, 1, projektbeschr);
		projektdialogboxtabelle.setWidget(3, 0, startdatum);
		projektdialogboxtabelle.setWidget(3, 1, startD);
		projektdialogboxtabelle.setWidget(4, 0, enddatum);
		projektdialogboxtabelle.setWidget(4, 1, endD);
//		projektdialogboxtabelle.setWidget(5, 0, projektleiter);
//		projektdialogboxtabelle.setWidget(5, 1, proLeit);
		
	}
	
	private class projektinDB implements AsyncCallback<Projekt>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim Erstellen des Projektes");
		}

		@Override
		public void onSuccess(Projekt result) {
			Window.alert("Ein neues Projekt wurde erfolgreich erstellt!");
			hide();
//			Showcase showcase = new ProjekteSeite(result, mp2);
//			RootPanel.get("Anzeige").clear();
//			RootPanel.get("Anzeige").add(showcase);
			
			Showcase showcase = new ProjekteSeite(mp2, rm, navi);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
			
		}
		
		
	}
	
}
