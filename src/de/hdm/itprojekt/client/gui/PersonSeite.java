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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;

public class PersonSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Person p = new Person();
	private Button pp = new Button("Nächste Seite zum Partnerprofil");
	private Navigator ng = null;
	private Button bearbeitenbutton = new Button ("Profil Bearbeiten");
	private Button speichernbutton = new Button ("Profil Speichern");
	private Button abbrechenbutton = new Button ("Profil Abbrechen");
	private Button deletebutton = new Button ("Profil Löschen");
	private Button teambutton = new Button ("Team Hinzufügen");
	private Button unternehmenbutton = new Button ("Unternehmen Hinzufügen");

	private FlexTable table = new FlexTable();
	private FlexTable flexTable = new FlexTable();
	private static DialogBox team = new DialogBox();
	private static DialogBox unternehmen = new DialogBox();
	
	public PersonSeite(){
		
	}
	public PersonSeite(Person p){
		this.p = p;
	}
	private VerticalPanel personVP = new VerticalPanel();
	private HorizontalPanel personHP = new HorizontalPanel();
	
	
	private ListBox listeTitel = new ListBox();
	private TextBox boxTitel = new TextBox();
	private TextBox boxName = new TextBox();
	private TextBox boxNachname = new TextBox();
	private TextBox boxAdresse = new TextBox();
	private TextBox boxStandort = new TextBox();
	private TextBox boxTeamName = new TextBox();
	private TextBox boxFirmenName = new TextBox();
	private TextBox boxEmail = new TextBox();
	
	private Label labelTitel = new Label("Titel");
	private Label labelName = new Label ("Vorname");
	private Label labelNachname = new Label("Nachname");
	private Label labelAdresse = new Label ("Adresse");
	private Label labelStandort = new Label ("Standort");
	private Label labelTeamName = new Label ("Teamname");
	private Label labelFirmenName = new Label ("Firmenname");
	private Label labelEmail = new Label ("E-Mail");
	

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Mein Profil </h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		gwtproxy.getPersonById(p.getId(), new GetPersonAusDB());
		
		this.add(pp);
		
		bearbeitenbutton.setStylePrimaryName("profilButton");
		speichernbutton.setStylePrimaryName("profilButton");
		deletebutton.setStylePrimaryName("profilButton");
		teambutton.setStylePrimaryName("profilButton");
		unternehmenbutton.setStylePrimaryName("profilButton");

		listeTitel.addItem("Frau");
		listeTitel.addItem("Herr");
		
		personVP.setSpacing(7);
		
		table.setWidget(0, 1, boxTitel);
		table.setWidget(0, 0, labelTitel);
		table.setWidget(1, 1, boxName);
		table.setWidget(1, 0, labelName);
		table.setWidget(2, 1, boxNachname);
		table.setWidget(2, 0, labelNachname);
		table.setWidget(3, 1, boxAdresse);
		table.setWidget(3, 0, labelAdresse);
		table.setWidget(4, 1, boxStandort);
		table.setWidget(4, 0, labelStandort);
		table.setWidget(5, 1, boxTeamName);
		table.setWidget(5, 0, labelTeamName);
		table.setWidget(6, 1, boxFirmenName);
		table.setWidget(6, 0, labelFirmenName);
		table.setWidget(7, 1, boxEmail);
		table.setWidget(7, 0, labelEmail);
		
		flexTable.setWidget(0, 0, bearbeitenbutton);
		flexTable.setWidget(0, 1, speichernbutton);
		flexTable.setWidget(1, 1, abbrechenbutton);
		flexTable.setWidget(1, 0, deletebutton);
		flexTable.setWidget(2, 1, teambutton);
		flexTable.setWidget(2, 0, unternehmenbutton);

	bearbeitenbutton.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event){
		table.setWidget(1, 1, boxTitel);
		
		boxName.setReadOnly(true);
		boxNachname.setReadOnly(true);
		boxAdresse.setReadOnly(true);
		boxStandort.setReadOnly(true);
		boxTeamName.setReadOnly(true);
		boxFirmenName.setReadOnly(true);
		boxEmail.setReadOnly(true);
		
		bearbeitenbutton.setVisible(false);
		speichernbutton.setVisible(true);
		abbrechenbutton.setVisible(true);
	}
});
	
	abbrechenbutton.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event){
			Showcase scase = new PersonSeite(p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(scase);
		}
	});

	speichernbutton.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event){
			gwtproxy.getPersonById(p.getId(), new SpeichernProfilCallback());
		
		}
	});

	pp.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event){
			Showcase scase = new EigenschaftAusSeite();
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(scase);
		}
	});

final class GetPersonAusDB implements AsyncCallback <Person>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("ssss");

		}

		@Override
		public void onSuccess(Person result) {
			Window.alert("s");
			boxName.setText(result.getVorname());
			boxNachname.setText(result.getNachname());
			boxAdresse.setText(result.getAdresse());
			boxStandort.setText(result.getStandort());
			boxTitel.setText(result.getTitel());
		}
		
	}
//class GetPersonByKey implements AsyncCallback<Person>{
//	
//	@Override
//	public void onFailure(Throwable caught) {
//		// TODO Auto-generated method stub
//		Window.alert("Fehlgeschlagen");
//	}
//
//	@Override
//	public void onSuccess(Person result) {
//		// TODO Auto-generated method stub
//		
//
//		}	
//	}
//}
	
final class BearbeitenvomProfilCallback implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Bearbeiten vom Profil hat nicht funktioniert.");
	}

	@Override
	public void onSuccess(Person result) {
		// TODO Auto-generated method stub
	//	result.getVorname(boxTitel.getText(boxTitel.getSelectedText()));
		Window.alert("yes");
	
	}
	
}
	
final class SpeichernProfilCallback implements AsyncCallback<Void>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Fehler beim speichern.");
	}

	@Override
	public void onSuccess(Void result) {
		// TODO Auto-generated method stub
		Window.alert("ja");
		Showcase scase = new PersonSeite(p);
		RootPanel.get("Anzeige").clear();
		RootPanel.get("Anzeige").add(scase);
	}
}




	}

}

