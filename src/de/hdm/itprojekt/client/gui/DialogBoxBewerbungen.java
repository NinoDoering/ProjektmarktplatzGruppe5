package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;

public class DialogBoxBewerbungen extends DialogBox {
	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	VerticalPanel bewerbungVP = new VerticalPanel();
	HorizontalPanel bewerbungHP = new HorizontalPanel();
	Button beenden = new Button ("Bewerbung schliessen");
	TextArea bewerbungsText = new TextArea();
	FlexTable bewerbungsTextTabelle = new FlexTable();
	
	private Bewerbung idBewerbung;
	
	public DialogBoxBewerbungen(Bewerbung idAuswahl){
		this.idBewerbung=idAuswahl;
	}
	
	public DialogBoxBewerbungen(String text){
		beenden.setStylePrimaryName("navi-button");
		setText(text);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.center();
		bewerbungsText.setReadOnly(true);
		bewerbungsText.setText(text);
		bewerbungsText.setCharacterWidth(80);
		bewerbungsText.setVisibleLines(30);
		bewerbungsTextTabelle.setWidget(0, 0, bewerbungsText);
		bewerbungsTextTabelle.setWidget(1, 0, beenden);
		bewerbungVP.add(bewerbungsTextTabelle);
		bewerbungVP.add(bewerbungsText);
		bewerbungVP.add(beenden);
		
		setWidget(bewerbungVP);
		
		beenden.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				DialogBoxBewerbungen.this.hide();
			}
		});
	
}
}
