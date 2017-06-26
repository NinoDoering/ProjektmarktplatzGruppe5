package de.hdm.itprojekt.client.gui;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.gui.BewerbungenAufAusschreibungSeite.BewertungBewerbung;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;

public class DialogBoxBewerbungBewerten extends DialogBox {
	
	GreetingServiceAsync greetingservice = ClientSideSettings.getMarktplatzVerwaltung();
	final BewertungBewerbung bewertungBewerbung;

	VerticalPanel bewerbungBewertungVp = new VerticalPanel();
	HorizontalPanel buttonHp = new HorizontalPanel();
	HorizontalPanel bewerbungBewertungHp = new HorizontalPanel();
	HorizontalPanel bewertungHp = new HorizontalPanel();
	
	FlexTable bewerbungFt = new FlexTable();
	FlexTable bewertungFt = new FlexTable();
	
	Button bewerberAnnehmenButton = new Button("Annehmen");
	Button bewerberAblehnenButton = new Button("Ablehnen");
	Button speichernButton= new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	
	Label bewerberLbl = new Label("Bewerber:");
	Label bewerbungstextLbl = new Label("Anschreiben: ");
	Label textuelleBewertungLbl = new Label("Bewertungstext ");
	Label BewertungLbl = new Label("Bewertung: ");
	
	TextBox bewerberTxt = new TextBox();
	TextArea bewerbungstextTxta = new TextArea();
	TextArea textuelleBewertungTxta = new TextArea();
	ListBox bewertungLb = new ListBox();
	String[] bewertungen = {"0,1", "0,2", "0,3","0,4", "0,5", "0,6", "0,7", "0,8", "0,9", "1,0"};
	
	private RoleManagement roleManagement =null;
	private Navigator navigator=null;
	
// Konstruktor erstellen
	public DialogBoxBewerbungBewerten (BewertungBewerbung bb, RoleManagement roleManagement, final Navigator navigator){ 
		this.roleManagement=roleManagement;
		this.navigator=navigator;
		this.setText("Bewerbung bewerten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.center();
		
		bewertungBewerbung = bb;
		
		//Array befüllen
		
		for(int i = 0; i<bewertungen.length; i++){
			bewertungLb.addItem(bewertungen[i]);
		}
		
		bewertungHp.add(speichernButton);
		bewertungHp.add(abbrechenButton);
		buttonHp.add(bewerberAnnehmenButton);
		buttonHp.add(bewerberAblehnenButton);
		
		//Erstellen der FlexTable
		bewerbungFt.setWidget(1, 0, bewerberLbl);
		bewerbungFt.setWidget(1, 1, bewerberTxt);
		bewerbungFt.setWidget(2, 0, bewerbungstextLbl);
		bewerbungFt.setWidget(2, 1, bewerbungstextTxta);
		bewerbungFt.setWidget(3, 1, buttonHp);
		
		bewertungFt.setWidget(1, 0, textuelleBewertungLbl);
		bewertungFt.setWidget(1, 1, bewertungLb);
		bewertungFt.setWidget(2, 0, textuelleBewertungLbl);
		bewertungFt.setWidget(2, 1, textuelleBewertungTxta);
		bewertungFt.setWidget(3, 1, bewertungHp);
		
		bewerberTxt.setEnabled(false);
		bewerbungstextTxta.setEnabled(false);
		bewerbungstextTxta.setPixelSize(200, 200);
		textuelleBewertungTxta.setPixelSize(200, 200);
		
		bewerberTxt.setText(bewertungBewerbung.getBewerber());
		bewerbungstextTxta.setText(bewertungBewerbung.getBewerbungstext());
		textuelleBewertungTxta.setText(bewertungBewerbung.getTextuelleBewertung());
	
	//Panels zu Widgets zuweisen
		
		bewerbungBewertungHp.add(bewerbungFt);
		bewerbungBewertungHp.add(bewertungFt);
		bewerbungBewertungVp.add(bewerbungBewertungHp);
		
		//ClickHandler anlegen für die jeweiligen Buttonfunktionen
		
		bewerberAnnehmenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(textuelleBewertungTxta.getText().isEmpty()){
					Window.alert("Bitte geben Sie eine Stellungnahme ab!");
				}else{
					Bewerbung b = new Bewerbung();
					b.setId(bewertungBewerbung.getIdBewerbung());
					b.setIdAusschreibung(bewertungBewerbung.getIdAusschreibung());
					b.setIdOrganisationseinheit(bewertungBewerbung.getIdBewerber());
					greetingservice.getAusschreibungByBewerbung(b, new GetAusschreibungFromBewerbungCallback(){						
					});
				}
			}
		});
		
		
		bewerberAblehnenButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(bewertungBewerbung.getBewerbungsstatus()== BewerbungsStatus.absage){
					Window.alert("Bewerber wurde bereits abgesagt!");
					hide();
					navigator.reload();
				} else if (bewertungBewerbung.getBewerbungsstatus()== BewerbungsStatus.zusage){
					Window.alert("Bewerber wurde bereits zugesagt!");
					hide();
					navigator.reload();
				} else{ Bewerbung b = new Bewerbung();
						Bewertung bewertung = new Bewertung();
						
						b.setBewerbungsStatus(BewerbungsStatus.absage);
						b.setIdOrganisationseinheit(bewertungBewerbung.getIdBewerber());
						b.setId(bewertungBewerbung.getIdBewerbung());
						b.setBewerbungsText(bewertungBewerbung.getBewerbungstext());
						b.setIdAusschreibung(bewertungBewerbung.getIdAusschreibung());
						b.setErstellDatum(bewertungBewerbung.getErstellDatum());
						greetingservice.saveBewerbung(b, new SaveBewerbungCallback());
						
						// Listbox Bewertung von String in Float umwandeln
						try{
							
							String numberString = bewertungLb.getSelectedItemText();
							Float number = (float) NumberFormat.getDecimalFormat().parse(numberString);
							float number2 = number/10;
							
							bewertung.setFliesskommaBewertung(number2);
							bewertungBewerbung.setWert(number2);
						
						// Werte setzen
							
							bewertungBewerbung.setTextuelleBewertung(textuelleBewertungTxta.getText());
							bewertung.setIdBewerbung(bewertungBewerbung.getIdBewerbung());
							bewertung.setTextuelleBewertung(bewertungBewerbung.getTextuelleBewertung());
							
							greetingservice.anlegenBewertung(bewertung.getIdBewerbung(), bewertung.getTextuelleBewertung(), bewertung.getFliesskommaBewertung(), new AsyncCallback<Bewertung>(){
							
							@Override
							public void onFailure(Throwable caught){
								Window.alert("Die Erstellung der Bewertung ist fehlgeschlagen!");
								
							}
							
							@Override
							public void onSuccess(Bewertung result){
								Window.alert("Der Bewerber wurde abgelehnt!");
								hide();
								navigator.reload();
							}
							});
						} catch (Exception e){
							e.getStackTrace();
				}					
		}				
	}						
});
	
	speichernButton.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			Bewertung bewertung = new Bewertung();
			try{
				String numberString = bewertungLb.getSelectedItemText();
				Float number =  (float) NumberFormat.getDecimalFormat().parse(numberString);
				float number2 = number/10;
				
				bewertung.setFliesskommaBewertung(number2);
				bewertungBewerbung.setWert(number2);
			} catch (Exception e){
				e.getStackTrace();
			}
		
		bewertung.setId(bewertungBewerbung.getIdBewerbung());
		bewertungBewerbung.setTextuelleBewertung(textuelleBewertungTxta.getText());
		bewertung.setIdBewerbung(bewertungBewerbung.getIdBewerbung());
		bewertung.setTextuelleBewertung(bewertungBewerbung.getTextuelleBewertung());
		
		//Prüfen ob Bewertung neu erstellt oder überschrieben wurde
		if(bewertungBewerbung.getIdBewertung()==0){
			greetingservice.anlegenBewertung(bewertung.getIdBewerbung(), bewertung.getTextuelleBewertung(),bewertung.getFliesskommaBewertung(), new AnlegenBewertungCallback());
		}else{
			greetingservice.saveBewertung(bewertung, new SaveBewertungCallback());
		}
		}
	});

	abbrechenButton.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event){
			hide();
		}
	});
}
	
	//Callbacks
private class SaveBewertungCallback implements AsyncCallback<Void>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Speichern der Bewertung ist fehlgeschlagen! Bitte versuchen Sie es erneut!");
		
	}

	@Override
	public void onSuccess(Void result) {
		Window.alert("Die Bewertung wurde erfolgreich gespeichert.");
		hide();
		navigator.reload();
	}
}
private class SaveBewerbungCallback implements AsyncCallback<Void>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Speichern der Bewertung ist fehlgeschlagen!");
		
	}

	@Override
	public void onSuccess(Void result) {
		Window.alert("Die Bewertung wurde erfolgspreich gespeichert.");
		hide();
		navigator.reload();
		
	}
}

private class AnlegenBewertungCallback implements AsyncCallback<Bewertung>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Anlegen der Bewertung ist fehlgeschlagen!");
		
	}

	@Override
	public void onSuccess(Bewertung result) {
		Window.alert("Die Bewertung wurde erfolgreich angelegt.");
		hide();
		navigator.reload();
		
	}
}
private class GetAusschreibungFromBewerbungCallback implements AsyncCallback<Ausschreibung>{
	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Fehler. Versuchen Sie es erneut.");
	}
	@Override
	public void onSuccess(Ausschreibung result) {
		bewertungBewerbung.setIdProjekt(result.getIdProjekt());
		greetingservice.getProjektbyId(result.getIdProjekt(),new GetProjektCallback());
	}
}
private class GetProjektCallback implements AsyncCallback<Projekt>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Projekt result) {
		Bewerbung b = new Bewerbung();
		Bewertung bewertung = new Bewertung();
		bewertungBewerbung.setStartdatum(result.getStartDatum());
		bewertungBewerbung.setEnddatum(result.getEndDatum());
		bewertungBewerbung.setIdProjekt(result.getId());
		
		if(bewertungBewerbung.getBewerbungsstatus() == BewerbungsStatus.zusage){
			Window.alert("Der Bewerber wurde bereits angenommen!");
		}
		else if(bewertungBewerbung.getBewerbungsstatus() == BewerbungsStatus.absage){
			Window.alert("Der Bewerber wurde bereits abgelehnt!");
		}
		else{
			b.setIdOrganisationseinheit(bewertungBewerbung.getIdBewerber());	
			b.setId(bewertungBewerbung.getIdBewerbung());
			b.setBewerbungsStatus(BewerbungsStatus.zusage);
			b.setBewerbungsText(bewertungBewerbung.getBewerbungstext());
			b.setIdAusschreibung(bewertungBewerbung.getIdAusschreibung());
			b.setErstellDatum(bewertungBewerbung.getErstellDatum());
			
greetingservice.saveBewerbung(b, new SaveBewerbungCallback());
try{
		String numberString = bewertungLb.getSelectedItemText();
		Float number = (float) NumberFormat.getDecimalFormat().parse(numberString);
		float number2 = number /10;
		
		bewertung.setFliesskommaBewertung(number2);
		bewertungBewerbung.setWert(number2);
		
/* Werte setzen
 * 
 */
		bewertungBewerbung.setTextuelleBewertung(textuelleBewertungTxta.getText());
		bewertung.setIdBewerbung(bewertungBewerbung.getIdBewerbung());
		bewertung.setTextuelleBewertung(bewertungBewerbung.getTextuelleBewertung());
		
		greetingservice.anlegenBewertung(bewertung.getIdBewerbung(), bewertung.getTextuelleBewertung(), bewertung.getFliesskommaBewertung(), new AsyncCallback<Bewertung>(){
		

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Erstellen der Beteiligung ist Fehlgeschlagen!");
			}

			@Override
			public void onSuccess(Bewertung result) {
				
			greetingservice.anlegenBeteiligung(bewertungBewerbung.getBeteiligungszeit(), bewertungBewerbung.getIdBewerber(),bewertungBewerbung.getIdProjekt(), result.getId(), new BeteiligungErstelltCallback());
			}
		});
	} catch (Exception e){
		e.getStackTrace();
	}
  } 
 }	
}
class BeteiligungErstelltCallback implements AsyncCallback<Beteiligung>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Die Projektbeteiligung konnte nicht erstellt werden!");
		
	}

	@Override
	public void onSuccess(Beteiligung result) {
		Window.alert("Der Bewerber wurde angenommen!");
		hide();
		navigator.reload();
		
		}
	}
}