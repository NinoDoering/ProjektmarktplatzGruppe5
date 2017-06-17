package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import de.hdm.itprojekt.shared.bo.Projekt;

// --------------------------------ENUM STATUS FEHLT-----------------------



public class DialogBoxAusschreibungAnlegen extends DialogBox {

private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	VerticalPanel ausschreibungVP = new VerticalPanel();
	HorizontalPanel ausschreibungHP = new HorizontalPanel();
	
	private Projekt p1 = new Projekt();
	
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	
	Label ausschreibungbezeichnung = new Label("Ausschreibungsbezeichnung");
	TextArea aussbez = new TextArea();
	
	Label ausschreibungsbeschreibung = new Label("Ausschreibungsbeschreibung");
	TextArea aussbeschr = new TextArea();
	
	Label bewerbungsfrist = new Label("Bewerbungsfrist");
	DatePicker aussbefrist = new DatePicker();
	Label textBewerbungsfrist = new Label();
	
	// IDs müssen noch manuell eingegeben werden 
	Label idPartnerprofil = new Label("Id des Partnerprofil");
	TextArea idPP = new TextArea();
	
	Label idAusschreibender = new Label("Id des Ausschreibenden");
	TextArea idASD = new TextArea();
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	FlexTable ausschreibungdialogboxtabelle = new FlexTable();
	
	public DialogBoxAusschreibungAnlegen(final Projekt zugehoerigesProjekt){
		this.p1=zugehoerigesProjekt;
		Label zugehProjektBez = new Label(zugehoerigesProjekt.getBezeichnung());
		
		//Datepicker 
		
		aussbefrist.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				textBewerbungsfrist.setText(datum);
			}
		});
		
		aussbefrist.setValue(new Date(), true);
		
		this.setText("Ausschreibung anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		ausschreibungHP.add(ok);
		ausschreibungHP.add(abbrechen);
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				gwtproxy.anlegenAusschreibung(Integer.parseInt(idASD.getText()), zugehoerigesProjekt.getId(), aussbez.getText(), aussbeschr.getText(), aussbefrist.getValue(), Integer.parseInt(idPP.getText()), statu, callback);
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
					hide();
						
					}
				});
		
	}
}

