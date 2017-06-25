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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class DialogBoxAusschreibungBearbeiten extends DialogBox {
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	private VerticalPanel ausschreibungVP = new VerticalPanel();
	private HorizontalPanel ausschreibungHP = new HorizontalPanel();
	
	private Projekt p2 = new Projekt();
	private Marktplatz mp = new Marktplatz();
	private Person projektLeiter = new Person();
	
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");
	
	private Label ausschreibungbezeichnung = new Label("Ausschreibungsbezeichnung");
	private TextArea aussbez = new TextArea();
	
	private Label ausschreibungsbeschreibung = new Label("Ausschreibungsbeschreibung");
	private TextArea aussbeschr = new TextArea();
	
	private Label bewerbungsfrist = new Label("Bewerbungsfrist");
	private DatePicker aussbefrist = new DatePicker();
	private Label textBewerbungsfrist = new Label();
	
	
	private FlexTable ausschreibungdialogboxtabelle = new FlexTable();
	
	//Konstruktor mit benötigten Übergabeparameter Fremdschlüsselübergabe
	public DialogBoxAusschreibungBearbeiten(final Ausschreibung selectedObjectA, final Projekt p1, final Marktplatz m1, final Person projektLeiter){
		aussbez.setValue(selectedObjectA.getBezeichnung());
		aussbeschr.setValue(selectedObjectA.getBeschreibung());
		aussbefrist.setValue(selectedObjectA.getEndDatum(), true);
		
		this.setText("Ausschreibung bearbeiten");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.p2 = p1 ;
		this.mp= m1;
		this.projektLeiter=projektLeiter;

		ausschreibungHP.add(ok);
		ausschreibungHP.add(abbrechen);
		
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(aussbez.getText().isEmpty()){
					Window.alert("Bitte Geben Sie einen Ausschreibungsnamen ein");	
				}
				else{
					selectedObjectA.setBezeichnung(aussbez.getText());
					selectedObjectA.setBeschreibung(aussbeschr.getText());
					selectedObjectA.setEndDatum(aussbefrist.getValue());
					gwtproxy.saveAusschreibung(selectedObjectA, new ausschreibungBearbeiten() );
					
				}
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new AusschreibungSeite();
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		aussbefrist.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				textBewerbungsfrist.setText(datum);
			}
		});
		
		
		ausschreibungdialogboxtabelle.setWidget(1, 0, ausschreibungbezeichnung);
		ausschreibungdialogboxtabelle.setWidget(1, 1, aussbez);
		ausschreibungdialogboxtabelle.setWidget(2, 0, ausschreibungsbeschreibung);
		ausschreibungdialogboxtabelle.setWidget(2, 1, aussbeschr);
		ausschreibungdialogboxtabelle.setWidget(3, 0, bewerbungsfrist);
		ausschreibungdialogboxtabelle.setWidget(3, 1, aussbefrist);
		
		ausschreibungVP.add(ausschreibungdialogboxtabelle);
		ausschreibungVP.add(ausschreibungHP);
		this.add(ausschreibungVP);
		
	}
	
	private class ausschreibungBearbeiten implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			Window.alert("Veränderung wurden gespeichert !");
			Showcase showcase = new AusschreibungSeite(p2,mp, projektLeiter);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}
		
	}
}
