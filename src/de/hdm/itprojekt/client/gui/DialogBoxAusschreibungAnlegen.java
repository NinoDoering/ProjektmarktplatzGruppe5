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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

// --------------------------------ENUM STATUS FEHLT-----------------------



public class DialogBoxAusschreibungAnlegen extends DialogBox {

private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
// Panels erstellen
	private VerticalPanel ausschreibungVP = new VerticalPanel();
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel qualiHP = new HorizontalPanel();
	private HorizontalPanel ausschreibungHP = new HorizontalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();

	
// Objekte und Variablen für die Parameterübergabe
	private Projekt p2 = new Projekt();
	private Marktplatz mp = new Marktplatz();
	private Person projektLeiter = new Person();
	private int partnerprofil ;
	private String bez,beschr;
	private Ausschreibung a = new Ausschreibung();
	private Eigenschaft e = new Eigenschaft();
	
	// Buttons erstellen
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");
	private Button eigenschaftenBearbeiten = new Button("Qualifikationen hinzufügen");
	private Button qabbrechen = new Button("Abbrechen");
	private Button speichern = new Button("Speichern");
	
	// Labels und TextAreas erstellen
	private Label ausschreibungbezeichnung = new Label("Ausschreibungsbezeichnung");
	private TextArea aussbez = new TextArea();
	private Label ausschreibungsbeschreibung = new Label("Ausschreibungsbeschreibung");
	private TextArea aussbeschr = new TextArea();
	private Label bewerbungsfrist = new Label("Bewerbungsfrist");
	private DatePicker aussbefrist = new DatePicker();
	private Label textBewerbungsfrist = new Label();
	
	// IDs m�ssen noch manuell eingegeben werden 
	private Label idPartnerprofil = new Label("Id des Partnerprofil");
	private TextArea idPP = new TextArea();
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	

	//	Listbox
	private ListBox arbeitsgebietEigenschaften = new ListBox();
	private ListBox ausbildungEigenschaften = new ListBox();
	private ListBox berufserfahrungsjahreEigenschaften = new ListBox();
	private ListBox sprachkenntnisseEigenschaften = new ListBox();
	private ListBox employmentstatusEigenschaften = new ListBox();
	private ListBox abschlussEigenschaften = new ListBox();
	
	private Label arbeitsgebietLabel = new Label("Arbeitsgebiet:");
	private Label ausbildungLabel = new Label("Ausbildung:");
	private Label berufserfahrungsjahreLabel = new Label("Berufserfahrungsjahre");
	private Label sprachkenntnisseLabel = new Label("Sprachkenntnisse");
	private Label employmentstatusLabel = new Label("Employmentstatus");
	private Label abschlussLabel = new Label ("Abschluss:");
	
	private FlexTable eigenschaftaendern = new FlexTable();
	
	private Projekt p = new Projekt();
	private Person pe= new Person();
	private Eigenschaft eigt = new Eigenschaft();
	private Partnerprofil pP = new Partnerprofil();
	private Ausschreibung aussch = new Ausschreibung();
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	//Dbox q ende

	
	
	FlexTable ausschreibungdialogboxtabelle = new FlexTable();
	
	public DialogBoxAusschreibungAnlegen(final Projekt zugehoerigesProjekt,final RoleManagement rm, final Navigator navi, final Person projektLeiter){
		this.p2=zugehoerigesProjekt;
		this.projektLeiter =projektLeiter;
		Label zugehProjektBez = new Label("Ausschreibung für folgendes Projekt: "+ zugehoerigesProjekt.getBezeichnung());
		this.rm = rm;
		this.navi = navi;
		
		Window.alert("Projekt  :              "+zugehoerigesProjekt.getBezeichnung());
		
		//dbox a
		qualiHP.add(abschlussLabel);
		
		eigenschaftaendern.setWidget(0, 0, arbeitsgebietLabel);
		eigenschaftaendern.setWidget(0, 1, arbeitsgebietEigenschaften);
		eigenschaftaendern.setWidget(1, 0, ausbildungLabel);
		eigenschaftaendern.setWidget(1, 1, ausbildungEigenschaften);
		eigenschaftaendern.setWidget(2, 0, berufserfahrungsjahreLabel);
		eigenschaftaendern.setWidget(2, 1, berufserfahrungsjahreEigenschaften);
		eigenschaftaendern.setWidget(3, 0, sprachkenntnisseLabel);
		eigenschaftaendern.setWidget(3, 1, sprachkenntnisseEigenschaften);
		eigenschaftaendern.setWidget(4, 0, employmentstatusLabel);
		eigenschaftaendern.setWidget(4, 1, employmentstatusEigenschaften);
		eigenschaftaendern.setWidget(5, 0, abschlussLabel);
		eigenschaftaendern.setWidget(5, 1, abschlussEigenschaften);
		
		eigenschaftaendern.setWidget(8, 0, speichern);
		eigenschaftaendern.setWidget(8, 1, qabbrechen);
		eigenschaftaendern.setVisible(false);
		
		qualiHP.add(eigenschaftaendern);
	
		//dbox e
		
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
		ausschreibungHP.add(eigenschaftenBearbeiten);
		
		// Neues Partnerprofil anlegen 
		gwtproxy.anlegenPartnerprofil(new AsyncCallback<Partnerprofil>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Partnerprofil result) {
				// TODO Auto-generated method stub
				partnerprofil= result.getId();
			}
		});
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Window.alert(" Projekt ?? :   "+rm.getSelectedRoleID() +" " + zugehoerigesProjekt.getBezeichnung());
				gwtproxy.anlegenAusschreibung(rm.getSelectedRoleID(), zugehoerigesProjekt.getId(), aussbez.getText(),
						aussbeschr.getText(), aussbefrist.getValue(), partnerprofil,
						Status.laufend, new ausschreibungInDB());
				
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
					hide();
						
					}
				});
		
		
				
		qabbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eigenschaftaendern.setVisible(false);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		eigenschaftenBearbeiten.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
			//	hide();
				Qualimethode(a,e,aussbez.getText(),aussbeschr.getText());
				//RootPanel.get("Anzeige").clear();
				//RootPanel.get("Anzeige").add(dialogBoxAusschreibungEigBearbeiten);
				eigenschaftaendern.setVisible(true);
			}
			
		});
		
		ausschreibungVP.add(ausschreibungdialogboxtabelle);
		ausschreibungVP.add(qualiHP);
		ausschreibungVP.add(ausschreibungHP);
		
		this.add(ausschreibungVP);
		ausschreibungdialogboxtabelle.setWidget(1, 0, zugehProjektBez);
		ausschreibungdialogboxtabelle.setWidget(2, 0, ausschreibungbezeichnung);
		ausschreibungdialogboxtabelle.setWidget(2, 1, aussbez);
		ausschreibungdialogboxtabelle.setWidget(3, 0, ausschreibungsbeschreibung);
		ausschreibungdialogboxtabelle.setWidget(3, 1, aussbeschr);
		ausschreibungdialogboxtabelle.setWidget(4, 0, bewerbungsfrist);
		ausschreibungdialogboxtabelle.setWidget(4, 1, aussbefrist);
//		ausschreibungdialogboxtabelle.setWidget(5, 0, idPartnerprofil);
//		ausschreibungdialogboxtabelle.setWidget(5, 1, idPP);
	
	
	}
	public DialogBoxAusschreibungAnlegen(final Projekt zugehoerigesProjekt, final Person projektLeiter,String bez,String besch,RoleManagement rm, Navigator navi){
		this.rm=rm;
		this.navi=navi;
		//( zugehoerigesProjekt, rm, navi, projektLeiter);
	aussbez.setText(bez);	
	aussbeschr.setText(besch);		
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
			Showcase showcase = new AusschreibungSeite(p2, mp, projektLeiter);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
			
		}
}
public void Qualimethode(final Ausschreibung a, final Eigenschaft e,String artikelbez, String artikelbesch){
	this.bez = artikelbez;
	this.beschr = artikelbesch;
	this.setAnimationEnabled(false);
	this.setGlassEnabled(true);
	this.setText("Qualifikationen bearbeiten");
	speichern.setStylePrimaryName("profilButton");
	abbrechen.setStylePrimaryName("profilButton");
	

	arbeitsgebietEigenschaften.addItem("IT");
	arbeitsgebietEigenschaften.addItem("Automobil");
	arbeitsgebietEigenschaften.addItem("Versicherungen");
	arbeitsgebietEigenschaften.addItem("Finanzen");
	arbeitsgebietEigenschaften.addItem("Immobilien");

	
	
	ausbildungEigenschaften.addItem("Softwareentwickler");
	ausbildungEigenschaften.addItem("Automobilkaufmann");
	ausbildungEigenschaften.addItem("Versicherungskaufmann"); 
	ausbildungEigenschaften.addItem("Finanzkaufmann"); 
	ausbildungEigenschaften.addItem("Immobilienkaufmann");
	
	berufserfahrungsjahreEigenschaften.addItem("1 bis 5 Jahre");
	berufserfahrungsjahreEigenschaften.addItem("5 bis 10");
	berufserfahrungsjahreEigenschaften.addItem("10  bis 20");

	sprachkenntnisseEigenschaften.addItem("Französisch");
	sprachkenntnisseEigenschaften.addItem("Englisch");
	sprachkenntnisseEigenschaften.addItem("Spanisch");
	
	employmentstatusEigenschaften.addItem("Student");
	employmentstatusEigenschaften.addItem("Angestellter");
	
	abschlussEigenschaften.addItem("Mittlere Reife");
	abschlussEigenschaften.addItem("Abitur");
	abschlussEigenschaften.addItem("Bachelor");
	abschlussEigenschaften.addItem("Master");
	
	

	
	speichern.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

//			gwtproxy.saveAusschreibung(aussch, new AsyncCallback<Void>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					// TODO Auto-generated method stub
//					Window.alert("saved"+caught);
//					
//				}
//
//				@Override
//				public void onSuccess(Void result) {
//					// TODO Auto-generated method stub
//					aussch.setIdPartnerprofil(aussch.getId());
//					Window.alert("saved");
//				}

			
//			});
			
			gwtproxy.anlegenEigenschaft(partnerprofil, arbeitsgebietEigenschaften.getSelectedValue(), berufserfahrungsjahreEigenschaften.getSelectedValue(),sprachkenntnisseEigenschaften.getSelectedValue(),employmentstatusEigenschaften.getSelectedValue(), ausbildungEigenschaften.getSelectedValue(), abschlussEigenschaften.getSelectedValue(), new AsyncCallback<Eigenschaft>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("geht ned" + aussch.getIdPartnerprofil());
				}


				@Override
				public void onSuccess(Eigenschaft result) {
					// TODO Auto-generated method stub
					Window.alert("klappt" + partnerprofil+ arbeitsgebietEigenschaften.getSelectedValue()+ berufserfahrungsjahreEigenschaften.getSelectedValue() + sprachkenntnisseEigenschaften.getSelectedValue() + employmentstatusEigenschaften.getSelectedValue() + ausbildungEigenschaften.getSelectedValue() + abschlussEigenschaften.getSelectedValue());

					gwtproxy.anlegenPartnerprofil(new AsyncCallback<Partnerprofil>() {
						
						@Override
						public void onSuccess(Partnerprofil result) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			});
			
			
		}
				
	});
	
}
}