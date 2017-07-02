package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;

/**
 * Diese Klasse regelt den Ablauf, der Bearbeitung einer Eigenschaft.
 * Sie wird benötigt, sobald ein Nutzer seine Eigenschaften bearbeiten möchte.
 * Die Eigenschafts-Klasse enthält mehrere Eigenschaften, die eine Person annehmen kann, wie z.B. die Sprachkenntnisse
 * Die Klasse enthält GUI-Elemente, die zur Darstellung benötigt wird.
 */
public class DialogBoxPersonEigenschaftenBearbeiten extends DialogBox{

GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button eigtAnlegen = new Button("Eigenschaft anlegen");
	private Button eigtBearbeit = new Button("persönliche Eigenschaft ändern");
	private Button abbrechen = new Button("Abbrechen");
	private Button eigtBearbeitSave = new Button("Änderungen speichern");

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
	
	private Label eigtAngelegt = new Label("Sie haben ihre Eigenschaft angelegt");
	private Button ok = new Button("OK!");
	
	private FlexTable eigenschaftaendern = new FlexTable();
	private Person pe = new Person();
	private Partnerprofil pp = new Partnerprofil();
	private Ausschreibung a = new Ausschreibung();
	
	public DialogBoxPersonEigenschaftenBearbeiten(final Person person, final Partnerprofil pp, final Eigenschaft eigenschaft){
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.pe = person;
		this.pp = pp;
		this.setText("Eigenschaften ändern");
		eigtAnlegen.setStylePrimaryName("profilButton");
		abbrechen.setStylePrimaryName("profilButton");
		eigtBearbeit.setStylePrimaryName("profilButton");
		eigtBearbeitSave.setStylePrimaryName("profilButton");
		
		eigtAngelegt.setVisible(false);
		ok.setVisible(false);
		
	

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
		
		eigtAnlegen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eigenschaft.setArbeitsgebiet(arbeitsgebietEigenschaften.getSelectedValue());
				eigenschaft.setAusbildung(ausbildungEigenschaften.getSelectedValue());
				eigenschaft.setBerufserfahrungsJahre(berufserfahrungsjahreEigenschaften.getSelectedValue());
				eigenschaft.setSprachkenntnisse(sprachkenntnisseEigenschaften.getSelectedValue());
				eigenschaft.setEmploymentStatus(employmentstatusEigenschaften.getSelectedValue());
				eigenschaft.setAbschluss(abschlussEigenschaften.getSelectedValue());
				eigenschaft.setIdPartnerprofil(a.getIdPartnerprofil());
			    gwtproxy.anlegenEigenschaft(pe.getIdPartnerprofil(), arbeitsgebietEigenschaften.getSelectedValue(),
			    		berufserfahrungsjahreEigenschaften.getSelectedValue(),
			    		employmentstatusEigenschaften.getSelectedValue(),
			    		ausbildungEigenschaften.getSelectedValue(), abschlussEigenschaften.getSelectedValue(),
			    		sprachkenntnisseEigenschaften.getSelectedValue(), new AsyncCallback<Eigenschaft>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("Es ist ein Fehler unterlaufen ");
							}

							@Override
							public void onSuccess(Eigenschaft result) {
								// TODO Auto-generated method stub
							
								arbeitsgebietEigenschaften.setVisible(false);
								ausbildungEigenschaften.setVisible(false);
								berufserfahrungsjahreEigenschaften.setVisible(false);
								sprachkenntnisseEigenschaften.setVisible(false);
								employmentstatusEigenschaften.setVisible(false);
								abschlussEigenschaften.setVisible(false);
								
								arbeitsgebietLabel.setVisible(false);
								ausbildungLabel.setVisible(false);
								berufserfahrungsjahreLabel.setVisible(false);
								sprachkenntnisseLabel.setVisible(false);
								employmentstatusLabel.setVisible(false);
								abschlussLabel.setVisible(false);
							
								eigtAngelegt.setVisible(true);
								ok.setVisible(true);
								
							}
						});
			}
				
		
			
		});
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		});
		
		abbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		});
		
		
		eigtBearbeit.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				arbeitsgebietEigenschaften.setVisible(true);
				ausbildungEigenschaften.setVisible(true);
				berufserfahrungsjahreEigenschaften.setVisible(true);
				sprachkenntnisseEigenschaften.setVisible(true);
				employmentstatusEigenschaften.setVisible(true);
				abschlussEigenschaften.setVisible(true);
				
				arbeitsgebietLabel.setVisible(true);
				ausbildungLabel.setVisible(true);
				berufserfahrungsjahreLabel.setVisible(true);
				sprachkenntnisseLabel.setVisible(true);
				employmentstatusLabel.setVisible(true);
				abschlussLabel.setVisible(true);
				
				eigtAngelegt.setVisible(false);
				ok.setVisible(false);
				
				eigenschaft.setArbeitsgebiet(arbeitsgebietEigenschaften.getSelectedValue());
				eigenschaft.setSprachkenntnisse(sprachkenntnisseEigenschaften.getSelectedValue());
				eigenschaft.setAusbildung(ausbildungEigenschaften.getSelectedValue());
				eigenschaft.setBerufserfahrungsJahre(berufserfahrungsjahreEigenschaften.getSelectedValue());
				eigenschaft.setEmploymentStatus(employmentstatusEigenschaften.getSelectedValue());
				eigenschaft.setAbschluss(abschlussEigenschaften.getSelectedValue());
		
				gwtproxy.saveEigenschaftnachPP(eigenschaft, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
						
						
						
						arbeitsgebietEigenschaften.setVisible(false);
						ausbildungEigenschaften.setVisible(false);
						berufserfahrungsjahreEigenschaften.setVisible(false);
						sprachkenntnisseEigenschaften.setVisible(false);
						employmentstatusEigenschaften.setVisible(false);
						abschlussEigenschaften.setVisible(false);
						
						arbeitsgebietLabel.setVisible(false);
						ausbildungLabel.setVisible(false);
						berufserfahrungsjahreLabel.setVisible(false);
						sprachkenntnisseLabel.setVisible(false);
						employmentstatusLabel.setVisible(false);
						abschlussLabel.setVisible(false);
						
						
						eigtAngelegt.setVisible(true);
						ok.setVisible(true);
					}
				});
			}
		});

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
		
		eigenschaftaendern.setWidget(8, 0, eigtAnlegen);
		eigenschaftaendern.setWidget(8, 1, abbrechen);
		eigenschaftaendern.setWidget(8, 2, eigtBearbeit);
		eigenschaftaendern.setWidget(8, 3, eigtBearbeitSave);
		
		eigenschaftaendern.setWidget(2, 2, eigtAngelegt);
		eigenschaftaendern.setWidget(4, 2, ok);
		vpanel.add(eigenschaftaendern);
		this.add(vpanel);
		
	}		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	