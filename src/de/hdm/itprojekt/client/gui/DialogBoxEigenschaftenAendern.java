package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;

public class DialogBoxEigenschaftenAendern extends DialogBox{

GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private Button ok = new Button("Ok");
	private Button abbrechen = new Button("Abbrechen");

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

	public DialogBoxEigenschaftenAendern(final Person person, final Partnerprofil pp, final Eigenschaft eigenschaft){
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.setText("Eigenschaften ändern");
		ok.setStylePrimaryName("profilButton");
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
		
		ok.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eigenschaft.setArbeitsgebiet(arbeitsgebietEigenschaften.getSelectedValue());
				eigenschaft.setAusbildung(ausbildungEigenschaften.getSelectedValue());
				eigenschaft.setBerufserfahrungsJahre(berufserfahrungsjahreEigenschaften.getSelectedValue());
				eigenschaft.setSprachkenntnisse(sprachkenntnisseEigenschaften.getSelectedValue());
				eigenschaft.setEmploymentStatus(employmentstatusEigenschaften.getSelectedValue());
				eigenschaft.setAbschluss(abschlussEigenschaften.getSelectedValue());
			
			     gwtproxy.saveEigenschaft(eigenschaft, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						hide();
						Showcase showcase = new PersonSeite(person);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
			     });
			}
					
		});
		abbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
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
		
		eigenschaftaendern.setWidget(8, 0, ok);
		eigenschaftaendern.setWidget(8, 1, abbrechen);
		
		vpanel.add(eigenschaftaendern);
		this.add(vpanel);
		
	}		
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	