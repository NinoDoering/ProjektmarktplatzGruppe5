package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Person;

public class DialogBoxBewerbungAnlegen extends DialogBox {
	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	VerticalPanel bewerbungVP = new VerticalPanel();
	HorizontalPanel bewerbungHP = new HorizontalPanel();
	
	Button bewerbungSenden = new Button ("Bewerbung senden");
	Button bewerbungAbbrechen = new Button ("Bewerbung abbrechen");
	
	//private Ausschreibung a = new Ausschreibung ();
	//private Bewerbung b = new Bewerbung();
	
	private Ausschreibung ausschreibungAuswaehlen = new Ausschreibung ();
	private Bewerbung bewerbungsSchreiben = new Bewerbung ();
	private Person person1 = new Person();
	Label bewerbungsTextLabel = new Label();
	TextArea bewerbungsText = new TextArea();
	FlexTable bewerbungsTextTabelle = new FlexTable();
	
	public DialogBoxBewerbungAnlegen(final Ausschreibung ausschreibungAuswahl, final Person personBewerber){
		//this.ausschreibungAuswaehlen=ausschreibungAuswahl;
		this.ausschreibungAuswaehlen=ausschreibungAuswahl;
		this.person1=personBewerber;
		//this.a=b;
		setText("Bewerbung erstellen");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		bewerbungsText.setCharacterWidth(50);
		bewerbungsText.setVisibleLines(20);
		bewerbungsTextTabelle.setWidget(1, 0, bewerbungsTextLabel);
		bewerbungsTextTabelle.setWidget(1, 1, bewerbungsText);
		bewerbungVP.add(bewerbungsTextTabelle);
		bewerbungHP.add(bewerbungSenden);
		bewerbungHP.add(bewerbungAbbrechen);
		bewerbungVP.add(bewerbungHP);
		this.add(bewerbungVP);
		//this.add(bewerbungHP);
		
		
		bewerbungAbbrechen.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				DialogBoxBewerbungAnlegen.this.hide();
			}
		});
		
		bewerbungSenden.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
		
				
				if(bewerbungsText.getText().isEmpty()){
					Window.alert("Bitte tragen Sie hier Ihr Motivationsschreiben für die Ausschreibung ein.");
			}
				else{
					
			gwtproxy.anlegenBewerbung(bewerbungsSchreiben, new insertBewerbunginDB());
				}
			}
		
		});
	}
private class insertBewerbunginDB implements AsyncCallback<Bewerbung>{

	@Override
	public void onFailure(Throwable caught) {
		Window.alert("Das Motivationsschreiben konnte nicht gesendet werden!");
	}

	@Override
	public void onSuccess(Bewerbung result) {
		bewerbungsSchreiben.setBewerbungsStatus(BewerbungsStatus.eingereicht);
		bewerbungsSchreiben.setIdAusschreibung(ausschreibungAuswaehlen.getId());
		bewerbungsSchreiben.setIdOrganisationseinheit(person1.getId());
		bewerbungsSchreiben.setBewerbungsText(bewerbungsText.getValue());
		bewerbungsSchreiben.setErstellDatum(new Date());
		Window.alert("Das Motivationsschreiben wurde erfolgreich gesendet!");
		hide();
//		Showcase showcase = new BewerbungsSeite();
//		RootPanel.get("Navigator").clear();
//		RootPanel.get("Navigator").add(showcase);
		}		
	}	
}
