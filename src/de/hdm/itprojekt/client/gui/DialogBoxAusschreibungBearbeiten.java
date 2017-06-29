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
import com.google.gwt.user.client.ui.ListBox;
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

public class DialogBoxAusschreibungBearbeiten extends DialogBox {
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	// Panels erstellen
	private VerticalPanel ausschreibungVP = new VerticalPanel();
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel ausschreibungHP = new HorizontalPanel();
	private HorizontalPanel eigenschaftenHP = new HorizontalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	
	// Objekte und Variablen erstellt
	private Projekt p = new Projekt();
	private Marktplatz mp = new Marktplatz();
	private Person projektLeiter = new Person();
	private String bez,beschr;
	private Ausschreibung a = new Ausschreibung();
	private Ausschreibung a1 = new Ausschreibung();
	private Eigenschaft eig = new Eigenschaft();

	

	
	
	// Buttons erstellt
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");
	private Button eigenschaftenBearbeiten = new Button("Qualifikationen bearbeiten");
	private Button eigenschaftenAbbrechen = new Button("Abbrechen");
	private Button speichern = new Button("Speichern");
	
	// Labels und TextAreas erstellt
	private Label ausschreibungbezeichnung = new Label("Ausschreibungsbezeichnung");
	private TextArea aussbez = new TextArea();
	private Label ausschreibungsbeschreibung = new Label("Ausschreibungsbeschreibung");
	private TextArea aussbeschr = new TextArea();
	private Label bewerbungsfrist = new Label("Bewerbungsfrist");
	private DatePicker aussbefrist = new DatePicker();
	private Label textBewerbungsfrist = new Label();
	
	private Ausschreibung ausschreibung = null;
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	//	Listbox für Eigenschaften
	private ListBox arbeitsgebietEigenschaften = new ListBox();
	private ListBox ausbildungEigenschaften = new ListBox();
	private ListBox berufserfahrungsjahreEigenschaften = new ListBox();
	private ListBox sprachkenntnisseEigenschaften = new ListBox();
	private ListBox employmentstatusEigenschaften = new ListBox();
	private ListBox abschlussEigenschaften = new ListBox();
	
	
	//Labels für Eigenschaften
	private Label arbeitsgebietLabel = new Label("Arbeitsgebiet:");
	private Label ausbildungLabel = new Label("Ausbildung:");
	private Label berufserfahrungsjahreLabel = new Label("Berufserfahrungsjahre");
	private Label sprachkenntnisseLabel = new Label("Sprachkenntnisse");
	private Label employmentstatusLabel = new Label("Employmentstatus");
	private Label abschlussLabel = new Label ("Abschluss:");
	
	//Flextable erstellen
	private FlexTable ausschreibungdialogboxtabelle = new FlexTable();
	private FlexTable eigenschaftbearbeiten = new FlexTable();
	
	
	
	
	
	

	//Konstruktor mit benötigten Übergabeparameter Fremdschlüsselübergabe
	public DialogBoxAusschreibungBearbeiten(final Ausschreibung ausschreibung, final Eigenschaft e, RoleManagement rm, Navigator navi, final Projekt zugehoerigesProjekt,
			final Marktplatz m1){
		aussbez.setValue(ausschreibung.getBezeichnung());
		aussbeschr.setValue(ausschreibung.getBeschreibung());
		aussbefrist.setValue(ausschreibung.getEndDatum(), true);
		this.ausschreibung = ausschreibung;
		this.p= zugehoerigesProjekt;
		this.eig = e;
		Label zugehProjektBez = new Label("Ausschreibung für folgendes Projekt bearbeiten: "+ zugehoerigesProjekt.getBezeichnung());
		this.mp= m1;
		this.rm = rm;
		this.navi = navi;
		
		//
		



		
		// Widgets in Flextable eigenschaftBearbeiten
		eigenschaftbearbeiten.setWidget(0, 0, arbeitsgebietLabel);
		eigenschaftbearbeiten.setWidget(0, 1, arbeitsgebietEigenschaften);
		eigenschaftbearbeiten.setWidget(1, 0, ausbildungLabel);
		eigenschaftbearbeiten.setWidget(1, 1, ausbildungEigenschaften);
		eigenschaftbearbeiten.setWidget(2, 0, berufserfahrungsjahreLabel);
		eigenschaftbearbeiten.setWidget(2, 1, berufserfahrungsjahreEigenschaften);
		eigenschaftbearbeiten.setWidget(3, 0, sprachkenntnisseLabel);
		eigenschaftbearbeiten.setWidget(3, 1, sprachkenntnisseEigenschaften);
		eigenschaftbearbeiten.setWidget(4, 0, employmentstatusLabel);
		eigenschaftbearbeiten.setWidget(4, 1, employmentstatusEigenschaften);
		eigenschaftbearbeiten.setWidget(5, 0, abschlussLabel);
		eigenschaftbearbeiten.setWidget(5, 1, abschlussEigenschaften);
		eigenschaftbearbeiten.setWidget(8, 0, speichern);
		eigenschaftbearbeiten.setWidget(8, 1, eigenschaftenAbbrechen);
		eigenschaftbearbeiten.setVisible(false);
		
		eigenschaftenHP.add(eigenschaftbearbeiten);
		// Ende der DialogBox Eigenschaften bearbeiten


	
		ausschreibungHP.add(ok);
		ausschreibungHP.add(abbrechen);
		ausschreibungHP.add(eigenschaftenBearbeiten);
		
		
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
		
		gwtproxy.getpartnerprofilIdbyAusschreibung(ausschreibung, new AsyncCallback<Ausschreibung>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Ausschreibung result) {
				// TODO Auto-generated method stub
			
			}
		});
		
		// Beginn der ClickHandler
		
		// speicherung der Ausschreibung
		ok.addClickHandler(new ClickHandler() {
			
			
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(aussbez.getText().isEmpty()){
					Window.alert("Bitte Geben Sie einen Ausschreibungsnamen ein");	
				}
				else{
					ausschreibung.setBezeichnung(aussbez.getText());
					ausschreibung.setBeschreibung(aussbeschr.getText());
					ausschreibung.setEndDatum(aussbefrist.getValue());
					
					gwtproxy.saveAusschreibung(ausschreibung, new ausschreibungBearbeiten() );
					
				}
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
				// TODO Auto-generated method stub
//				Showcase showcase = new AusschreibungSeite(ausschreibung, rm, navi);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		eigenschaftenAbbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eigenschaftbearbeiten.setVisible(false);
			}
		});
		
		// Bei Klick des Qualifikation bearbeiten Buttons, wird die Eigenschaftenmethode aufgerufen
		eigenschaftenBearbeiten.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Eigenschaftenmethode(a,e,a.getBezeichnung(), a.getBeschreibung());
				eigenschaftbearbeiten.setVisible(true);
			}
		});	
			ausschreibungVP.add(ausschreibungdialogboxtabelle);
			ausschreibungVP.add(eigenschaftenHP);
			ausschreibungVP.add(ausschreibungHP);	
		
		this.add(ausschreibungVP);
		ausschreibungdialogboxtabelle.setWidget(1, 0, zugehProjektBez);
		ausschreibungdialogboxtabelle.setWidget(2, 0, ausschreibungbezeichnung);
		ausschreibungdialogboxtabelle.setWidget(2, 1, aussbez);
		ausschreibungdialogboxtabelle.setWidget(3, 0, ausschreibungsbeschreibung);
		ausschreibungdialogboxtabelle.setWidget(3, 1, aussbeschr);
		ausschreibungdialogboxtabelle.setWidget(4, 0, bewerbungsfrist);
		ausschreibungdialogboxtabelle.setWidget(4, 1, aussbefrist);
	}
	

	// Methode um die geänderten Eigenschaften in der Listbox zu speichern
	public void Eigenschaftenmethode(final Ausschreibung a, final Eigenschaft e,String aussbez, String aussbeschr){
		this.bez = aussbez;
		this.beschr = aussbeschr;
		this.eig = e;
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Qualifikationen hinzufügen");
		speichern.setStylePrimaryName("profilButton");
		abbrechen.setStylePrimaryName("profilButton");
		

		arbeitsgebietEigenschaften.addItem("IT");
		arbeitsgebietEigenschaften.addItem("Automobil");
		arbeitsgebietEigenschaften.addItem("Versicherungen");
		arbeitsgebietEigenschaften.addItem("Finanzen");
		arbeitsgebietEigenschaften.addItem("Immobilien");
		arbeitsgebietEigenschaften.addItem("Pharmaindustrie");
		arbeitsgebietEigenschaften.addItem("Luft- und Raumfahrt");
		
		ausbildungEigenschaften.addItem("Softwareentwickler");
		ausbildungEigenschaften.addItem("Automobilkaufmann");
		ausbildungEigenschaften.addItem("Versicherungskaufmann"); 
		ausbildungEigenschaften.addItem("Finanzkaufmann"); 
		ausbildungEigenschaften.addItem("Immobilienkaufmann");
		ausbildungEigenschaften.addItem("Pharmazeut");
		ausbildungEigenschaften.addItem("Luft- und Raumfahrt Ingenieur");
		
		berufserfahrungsjahreEigenschaften.addItem("1 bis 5 Jahre");
		berufserfahrungsjahreEigenschaften.addItem("5 bis 10");
		berufserfahrungsjahreEigenschaften.addItem("10  bis 20");
		berufserfahrungsjahreEigenschaften.addItem("20  bis 50 Jahre");

		sprachkenntnisseEigenschaften.addItem("Französisch");
		sprachkenntnisseEigenschaften.addItem("Englisch");
		sprachkenntnisseEigenschaften.addItem("Spanisch");
		sprachkenntnisseEigenschaften.addItem("Englisch + Französisch ");
		sprachkenntnisseEigenschaften.addItem("Englisch + Spanisch ");
		sprachkenntnisseEigenschaften.addItem("Französisch + Spanisch");
		sprachkenntnisseEigenschaften.addItem("Englisch + Französisch + Spanisch ");
		
		employmentstatusEigenschaften.addItem("Student");
		employmentstatusEigenschaften.addItem("Angestellter");
		employmentstatusEigenschaften.addItem("Selbstständig");
		
		abschlussEigenschaften.addItem("Hauptschulabschluss");
		abschlussEigenschaften.addItem("Mittlere Reife");
		abschlussEigenschaften.addItem("Abitur");
		abschlussEigenschaften.addItem("Bachelor");
		abschlussEigenschaften.addItem("Master");
		abschlussEigenschaften.addItem("Doktor");
		
		// speichern des eigenschafts objektes 
		speichern.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				e.setArbeitsgebiet(arbeitsgebietEigenschaften.getSelectedValue());
				e.setAusbildung(ausbildungEigenschaften.getSelectedValue());
				e.setBerufserfahrungsJahre(berufserfahrungsjahreEigenschaften.getSelectedValue());
				e.setSprachkenntnisse(sprachkenntnisseEigenschaften.getSelectedValue());
				e.setEmploymentStatus(employmentstatusEigenschaften.getSelectedValue());
				e.setAbschluss(abschlussEigenschaften.getSelectedValue());
				//	
				e.setIdPartnerprofil(ausschreibung.getIdPartnerprofil());
				//
				gwtproxy.saveEigenschaftnachPP(e, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Das Speichern der Eigenschaften hat nicht funktioniert!");
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
						Window.alert("Das Speichern der Eigenschaften war erfolgreich!");
					}
			
				});
			
		}
		
		
		});
		
}
	


	public class ausschreibungBearbeiten implements AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			hide();
			Window.alert("Ausschreibung wurde gespeichert !");
			Showcase showcase = new AusschreibungSeite(mp, p, ausschreibung, rm, navi);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}
		
	}
}
