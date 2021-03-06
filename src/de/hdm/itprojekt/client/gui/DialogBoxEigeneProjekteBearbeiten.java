package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.aria.client.SelectedValue;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
//import de.hdm.itprojekt.client.gui.DialogBoxProjektAnlegen.projektinDB;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

/**
 * Diese Klasse ist dafür zustaendig, ein Projekt bearbeiten zu könnnen. Ein
 * Projekt kann erstellt werden, sobald der Nutzer die Funktion "Projekt
 * erstellen" angeklickt hat. Hat diese Person das Projekt erstellt, so ist
 * diese automatisch der Projektleiter des Projekts. Ein Projekt wird in einem
 * Marktplatz erstellt.
 * 
 * @author Thomas
 *
 */
public class DialogBoxEigeneProjekteBearbeiten extends DialogBox {
private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private VerticalPanel projektVP = new VerticalPanel();
	private HorizontalPanel projektHP = new HorizontalPanel();
	private Marktplatz mp2 = new Marktplatz();
	private Person person = new Person();
	private Navigator navi;
	private RoleManagement rm;
	private Projekt p = new Projekt();
	private Ausschreibung ausschr1 = new Ausschreibung();
	
	private Organisationseinheit orga = new Organisationseinheit();
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");
	
	private Label projektbezeichnung = new Label("Projektbezeichnung");
	private TextArea projektbez = new TextArea();
	
	private Label projektbeschreibung = new Label("Projektbeschreibung");
	private TextArea projektbeschr = new TextArea();
	
	private Label startdatum = new Label("Startdatum des Projektes");
	private DatePicker startD = new DatePicker();
	private Label textStart = new Label();
	
	
	private Label enddatum = new Label("Enddatum des Projektes");
	private DatePicker endD = new DatePicker();
	final Label textEnde = new Label();
	
	
	private FlexTable projektdialogboxtabelle = new FlexTable(); 
	
	public DialogBoxEigeneProjekteBearbeiten(final Projekt selectedObject, final Person projektLeiter){
		projektbez.setValue(selectedObject.getBezeichnung());	
		projektbeschr.setValue(selectedObject.getBeschreibung());
		startD.setValue(selectedObject.getStartDatum(), true);
		endD.setValue(selectedObject.getEndDatum(), true);
		this.person= projektLeiter;
	}
	
	public DialogBoxEigeneProjekteBearbeiten( final Person projektLeiter){
		this.person=projektLeiter;
	}
	
//	public DialogBoxEigeneProjekteBearbeiten(final Projekt p, final RoleManagement rm, final Navigator navi, final Marktplatz mp2){
////		this.eig=eig;
//		this.navi=navi;
//		this.rm=rm;
//		this.p=p;
//		this.mp2=mp2;
//	}
//	
//	public DialogBoxEigeneProjekteBearbeiten(final Projekt selectedObject, final Marktplatz m1, Organisationseinheit o1,  final Person projektLeiter){
//		projektbez.setValue(selectedObject.getBezeichnung());	
//		projektbeschr.setValue(selectedObject.getBeschreibung());
//		startD.setValue(selectedObject.getStartDatum(), true);
//		endD.setValue(selectedObject.getEndDatum(), true);
//		this.mp2 = m1 ; 
//		
//		this.person= projektLeiter;
//		
//	}
	
	
	public DialogBoxEigeneProjekteBearbeiten(final Projekt selectedObject, final Marktplatz m1, final RoleManagement rm, Navigator navi){
		projektbez.setValue(selectedObject.getBezeichnung());	
		projektbeschr.setValue(selectedObject.getBeschreibung());
		startD.setValue(selectedObject.getStartDatum(), true);
		endD.setValue(selectedObject.getEndDatum(), true);
			this.setText("Projektmarktplatz anlegen");
			this.setAnimationEnabled(false);
			this.setGlassEnabled(true);
			this.mp2 = m1 ;
			this.p=selectedObject;
			this.rm=rm;
			this.navi=navi;
			

			
			projektHP.add(ok);
			projektHP.add(abbrechen);
		
		
	
			
			
		ok.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						if(projektbez.getText().isEmpty()){
							Window.alert("Bitte Geben Sie einen Projektmarktplatznamen ein");	
						}
						else{
							selectedObject.setBeschreibung(projektbeschr.getText());
							selectedObject.setBezeichnung(projektbez.getText());
							selectedObject.setStartDatum(startD.getValue());
							selectedObject.setEndDatum(endD.getValue());
							gwtproxy.saveProjekt(selectedObject, new projektBearbeiten());
							
							
						}
					}
				});
		
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
//				Showcase showcase = new ProjekteSeite();
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
				hide();
			}
		});
		startD.addValueChangeHandler(new ValueChangeHandler<Date>() {
					
					@Override
					public void onValueChange(ValueChangeEvent<Date> event) {
						// TODO Auto-generated method stub
						Date date = event.getValue();
						String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
						textStart.setText(datum);
					}
					
				});
		
		
		
		endD.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				textEnde.setText(datum);
			}
		});
		
		
		
		projektdialogboxtabelle.setWidget(1, 0, projektbezeichnung);
		projektdialogboxtabelle.setWidget(1, 1, projektbez);
		projektdialogboxtabelle.setWidget(2, 0, projektbeschreibung);
		projektdialogboxtabelle.setWidget(2, 1, projektbeschr);
		projektdialogboxtabelle.setWidget(3, 0, startdatum);
		projektdialogboxtabelle.setWidget(3, 1, startD);
		projektdialogboxtabelle.setWidget(4, 0, enddatum);
		projektdialogboxtabelle.setWidget(4, 1, endD);
			
		projektVP.add(projektdialogboxtabelle);
		projektVP.add(projektHP);
		this.add(projektVP);

	}
	
	private class projektBearbeiten implements  AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			hide();
			Showcase showcase = new EigeneProjekte(mp2, rm, navi, p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		
			
		}
		
		
		
		
		
	
}}
