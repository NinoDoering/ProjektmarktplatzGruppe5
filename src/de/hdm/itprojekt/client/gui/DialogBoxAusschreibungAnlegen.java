package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

// --------------------------------ENUM STATUS FEHLT-----------------------



public class DialogBoxAusschreibungAnlegen extends DialogBox {

private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	VerticalPanel ausschreibungVP = new VerticalPanel();
	HorizontalPanel ausschreibungHP = new HorizontalPanel();
	
	private Projekt p2 = new Projekt();
	private Marktplatz mp = new Marktplatz();
	
	Button ok = new Button("OK");
	Button abbrechen = new Button("Abbrechen");
	
	Label ausschreibungbezeichnung = new Label("Ausschreibungsbezeichnung");
	TextArea aussbez = new TextArea();
	
	Label ausschreibungsbeschreibung = new Label("Ausschreibungsbeschreibung");
	TextArea aussbeschr = new TextArea();
	
	Label bewerbungsfrist = new Label("Bewerbungsfrist");
	DatePicker aussbefrist = new DatePicker();
	Label textBewerbungsfrist = new Label();
	
	// IDs m�ssen noch manuell eingegeben werden 
	Label idPartnerprofil = new Label("Id des Partnerprofil");
	TextArea idPP = new TextArea();
	
	Label idAusschreibender = new Label("Id des Ausschreibenden");
	TextArea idASD = new TextArea();
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	

	
	
	FlexTable ausschreibungdialogboxtabelle = new FlexTable();
	
	public DialogBoxAusschreibungAnlegen(final Projekt zugehoerigesProjekt){
		this.p2=zugehoerigesProjekt;
		Label zugehProjektBez = new Label("Ausschreibung für folgendes Projekt: "+ zugehoerigesProjekt.getBezeichnung());
		
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
				gwtproxy.anlegenAusschreibung(Integer.parseInt(idASD.getText()), zugehoerigesProjekt.getId(),
						aussbez.getText(), aussbeschr.getText(), aussbefrist.getValue(),
						Integer.parseInt(idPP.getText()), Status.laufend, new ausschreibungInDB());
														// Ausschreibungen werden beim erstellen auf laufend gesetzt 
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
					hide();
						
					}
				});
		
		ausschreibungVP.add(ausschreibungdialogboxtabelle);
		ausschreibungVP.add(ausschreibungHP);
		this.add(ausschreibungVP);
		ausschreibungdialogboxtabelle.setWidget(1, 0, ausschreibungbezeichnung);
		ausschreibungdialogboxtabelle.setWidget(1, 1, aussbez);
		ausschreibungdialogboxtabelle.setWidget(2, 0, ausschreibungsbeschreibung);
		ausschreibungdialogboxtabelle.setWidget(2, 1, aussbeschr);
		ausschreibungdialogboxtabelle.setWidget(3, 0, bewerbungsfrist);
		ausschreibungdialogboxtabelle.setWidget(3, 1, aussbefrist);
		ausschreibungdialogboxtabelle.setWidget(4, 0, idAusschreibender);
		ausschreibungdialogboxtabelle.setWidget(4, 1, idASD);
		ausschreibungdialogboxtabelle.setWidget(5, 0, idPartnerprofil);
		ausschreibungdialogboxtabelle.setWidget(5, 1, idPP);
		ausschreibungdialogboxtabelle.setWidget(6, 0, zugehProjektBez);
	
	}
	
	private class ausschreibungInDB implements AsyncCallback<Ausschreibung>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("etwas falsch gelaufen");
			
		}

		@Override
		public void onSuccess(Ausschreibung result) {
			// TODO Auto-generated method stub
			hide();
			Showcase showcase = new AusschreibungSeite(p2);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
			
		}
}

}