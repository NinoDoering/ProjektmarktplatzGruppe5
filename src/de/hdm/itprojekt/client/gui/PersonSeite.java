package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.report.Column;

public class PersonSeite extends Showcase{

	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Person p = new Person();
	private Organisationseinheit o1 = new Organisationseinheit();
	private CellTable<Eigenschaft> personEigenschaftTabelle = new CellTable <Eigenschaft>();
	private Eigenschaft eig = new Eigenschaft();
	final SingleSelectionModel<Eigenschaft> selectionEigenschaft = new SingleSelectionModel();
	
	private RoleManagement roleManagement = new RoleManagement();
	private Navigator navigator = new Navigator();
	
	
	
	private Button pp = new Button("Nächste Seite zum Partnerprofil");
	private Button eigeneProjekte = new Button("Zu meinen Projekten");
	private Button eigeneBewerbungen = new Button("Meine Bewerbungen");
<<<<<<< HEAD
	private Button eigeneAusschreibungen = new Button ("Meine Ausschreibungen");
	
=======
	private Button eigeneAusschreibungen = new Button("Meine Ausschreibungen");
>>>>>>> refs/heads/master
	private Navigator ng = null;
	private RoleManagement roleManagement= null;
	private Button bearbeitenbutton = new Button ("Profil Bearbeiten");
	private Button speichernbutton = new Button ("Profil Speichern");
	private Button abbrechenbutton = new Button ("Profil Abbrechen");
	private Button deletebutton = new Button ("Profil Löschen");
	
	private Button teambutton = new Button ("Team Hinzufügen");
	private Button unternehmenbutton = new Button ("Unternehmen Hinzufügen");
	private Button eigenschaftButton = new Button ("Eigenschaften Hinzufügen");

	private FlexTable tablePerson = new FlexTable();
	private FlexTable flexTableButtons = new FlexTable();
	private FlexTable pTable = new FlexTable();
	private FlexTable buttonTable = new FlexTable();
	private FlexTable teamTable = new FlexTable();
	private FlexTable unternehmenTable = new FlexTable();
	
	private static DialogBox team = new DialogBox();
	private Button teamBearbeiten = new Button("Team Bearbeiten");
	private Button teamSpeichern = new Button("Team Speichern");
	private Button teamAbbrechen = new Button("Team Abbrechen");
	
	private static DialogBox unternehmen = new DialogBox();
	private Button unternehmenBearbeiten = new Button("Unternehmen Bearbeiten");
	private Button unternehmenSpeichern = new Button("Unternehmen Speichern");
	private Button unternehmenAbbrechen = new Button("Unternehmen Abbrechen");
	
	public PersonSeite(){
		
	}
	public PersonSeite(Person p ){
		this.p = p;
	}
	private VerticalPanel personVP = new VerticalPanel();	
	private VerticalPanel hinzuVP = new VerticalPanel();
	private HorizontalPanel personHP = new HorizontalPanel();
	private ListBox listeAnrede = new ListBox();
	private TextBox boxTitel = new TextBox();
	private TextBox boxName = new TextBox();
	private TextBox boxNachname = new TextBox();
	private TextBox boxAdresse = new TextBox();
	private TextBox boxStandort = new TextBox();
	private TextBox boxTeamName = new TextBox();
	private TextBox boxFirmenName = new TextBox();
	private TextBox boxEmail = new TextBox();
	
	private Label labelAnrede = new Label("Anrede");
	private Label labelTitel = new Label("Titel");
	private Label labelName = new Label ("Vorname");
	private Label labelNachname = new Label("Nachname");
	private Label labelAdresse = new Label ("Adresse");
	private Label labelStandort = new Label ("Standort");
	private Label labelTeamName = new Label ("Teamname: ");
	private Label labelFirmenName = new Label ("Firmenname: ");
	private Label labelEmail = new Label ("E-Mail");
	
//	private Anchor personloeschen = new Anchor ("Profil Löschen");
//	private Anchor klickFunktion = new Anchor ("Unternehmen/Team Löschen/Erstellen");
//	


	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Mein Profil </h1>";
	}
	
	@Override
	protected void run() {
//	personloeschen.addClickHandler(new ClickHandler() {
//		
//		@Override
//		public void onClick(ClickEvent event) {
//
//		}
//	});
	
//	klickFunktion.addClickHandler(new ClickHandler() {
//				
//				@Override
//				public void onClick(ClickEvent event) {
//					DialogBox box = new DialogBoxTeamUnternehmen(p);
//					box.center();
//				}
//			});
	
	
		
		gwtproxy.getPersonById(p.getId(), new GetPersonAusDB());

		
		
		if(p.getIdTeam()!=null){
			gwtproxy.getTeamById(p.getIdTeam(), new GetTeamAusDB());
		}
		if(p.getIdUnternehmen()!=null){
			gwtproxy.getUnternehmenById(p.getIdUnternehmen(), new GetUnternehmenAusDB());
		}

		
		boxTitel.setReadOnly(true);
		boxName.setReadOnly(true);
		boxNachname.setReadOnly(true);
		boxAdresse.setReadOnly(true);
		boxStandort.setReadOnly(true);
		boxTeamName.setReadOnly(true);
		boxFirmenName.setReadOnly(true);
		boxEmail.setReadOnly(true);
		
		this.add(pp);
		this.add(eigeneProjekte);
		this.add(eigeneBewerbungen);
		this.add(eigeneAusschreibungen);
		
		bearbeitenbutton.setStylePrimaryName("profilButton");
		speichernbutton.setStylePrimaryName("profilButton");
		deletebutton.setStylePrimaryName("profilButton");
		teambutton.setStylePrimaryName("profilButton");
		unternehmenbutton.setStylePrimaryName("profilButton");
		teamBearbeiten.setStylePrimaryName("profilButton");
		teamSpeichern.setStylePrimaryName("profilButton");
		teamAbbrechen.setStylePrimaryName("profilButton");
		unternehmenBearbeiten.setStylePrimaryName("profilButton");
		unternehmenSpeichern.setStylePrimaryName("profilButton");
		unternehmenAbbrechen.setStylePrimaryName("profilButton");
		eigenschaftButton.setStylePrimaryName("profilButton");

		listeAnrede.addItem("Frau");
		listeAnrede.addItem("Herr");
		
		personVP.setSpacing(8);
		
		tablePerson.setWidget(0, 1, listeAnrede);
		tablePerson.setWidget(0, 0, labelAnrede);
		tablePerson.setWidget(1, 1, boxTitel);
		tablePerson.setWidget(1, 0, labelTitel);
		tablePerson.setWidget(2, 1, boxName);
		tablePerson.setWidget(2, 0, labelName);
		tablePerson.setWidget(3, 1, boxNachname);
		tablePerson.setWidget(3, 0, labelNachname);
		tablePerson.setWidget(4, 1, boxAdresse);
		tablePerson.setWidget(4, 0, labelAdresse);
		tablePerson.setWidget(5, 1, boxStandort);
		tablePerson.setWidget(5, 0, labelStandort);
		tablePerson.setWidget(7, 1, boxEmail);
		tablePerson.setWidget(7, 0, labelEmail);
		tablePerson.setCellSpacing(10);
		
		flexTableButtons.setWidget(0, 0, bearbeitenbutton);
		flexTableButtons.setWidget(1, 0, speichernbutton);		
		flexTableButtons.setWidget(2, 0, abbrechenbutton);		
		flexTableButtons.setWidget(3, 0, deletebutton);
		
		
//		boxTitel.setReadOnly(true);
//		boxName.setReadOnly(true);
//		boxNachname.setReadOnly(true);
//		boxAdresse.setReadOnly(true);
//		boxStandort.setReadOnly(true);
//		boxTeamName.setReadOnly(true);
//		boxFirmenName.setReadOnly(true);
//		boxEmail.setReadOnly(true);
		
		
		teamTable.setWidget(0, 1, labelTeamName);
		teamTable.setWidget(1, 1, boxTeamName);
		teamTable.setWidget(2, 1, teambutton);
		teamTable.setCellSpacing(10);
		
		
		unternehmenTable.setWidget(0, 1, labelFirmenName);
		unternehmenTable.setWidget(1, 1, boxFirmenName);
		unternehmenTable.setWidget(2, 1, unternehmenbutton);
		unternehmenTable.setCellSpacing(10);
	
		
//		personVP.add(personloeschen);
//		personVP.add(klickFunktion);
//		personVP.add(flexTableButtons);
//		personVP.add(tablePerson);
//		personVP.add(unternehmenTable);
		
		hinzuVP.add(eigenschaftButton);
		hinzuVP.add(personEigenschaftTabelle);

//		ppVP.add(teamTable);
//		ppHP.add(ppVP);
		personHP.add(tablePerson);
		personHP.add(flexTableButtons);
		personHP.add(teamTable);
		personHP.add(unternehmenTable);
	
		this.add(personHP);

		this.setSpacing(8);

	
		
	// Teamhinzufügen oder ändern
		
	teambutton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					
					//DialogBox dialoxBoxTeamAnlegen = new DialogBoxTeamAnlegen(p);
					Showcase showcase = new TeamSeite(p);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
		
	// Unternehmen hinzufügen oder ändern
	
	unternehmenbutton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Showcase showcase = new UnternehmenSeite(p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}
	});
		
	
		// Profil bearbeiten
		bearbeitenbutton.addClickHandler(new ClickHandler(){
				
				@Override
				public void onClick(ClickEvent event){
				tablePerson.setWidget(1, 1, boxTitel);
				
				boxTitel.setReadOnly(false);
				boxName.setReadOnly(false);
				boxNachname.setReadOnly(false);
				boxAdresse.setReadOnly(false);
				boxStandort.setReadOnly(false);
				boxTeamName.setReadOnly(false);
				boxFirmenName.setReadOnly(false);
				boxEmail.setReadOnly(true);
				
				bearbeitenbutton.setVisible(false);
				deletebutton.setVisible(false);
				speichernbutton.setVisible(true);
				abbrechenbutton.setVisible(true);
				
				}
		});
	
		// Profil-änderungen speichern
		speichernbutton.addClickHandler(new ClickHandler(){
				
				@Override
				public void onClick(ClickEvent event){
					gwtproxy.savePerson(p, new SpeichernProfilCallback());
					
					
					
					
					
				}
			});
		
		// Profilbeabreiten abbrechen
		abbrechenbutton.addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event){
				Showcase scase = new PersonSeite(p);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(scase);
			}
		});
	
	unternehmenBearbeiten.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			boxFirmenName.setReadOnly(false);
			
			unternehmenBearbeiten.setVisible(false);
			unternehmenSpeichern.setVisible(true);
			unternehmenAbbrechen.setVisible(true);

		}
		
	});
	
	teamBearbeiten.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			boxTeamName.setReadOnly(false);
			
			
			teamBearbeiten.setVisible(false);
			teamBearbeiten.setVisible(true);
			teamBearbeiten.setVisible(true);

		}
		
	});
	
	
	
	
	teamAbbrechen.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Showcase scase = new PersonSeite(p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(scase);
		}
	});
	
	unternehmenAbbrechen.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Showcase scase = new PersonSeite(p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(scase);
		}	
	});
	
	

	

	pp.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event){
			Showcase scase = new EigeneBewerbungenSeite(p);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(scase);
		}
	});
	
	
	eigeneProjekte.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
	
			gwtproxy.getProjektByPerson(p, new AsyncCallback<Vector<Projekt>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("projektebyPerson geht NICHT");
				}

				public void onSuccess(Vector<Projekt> result) {
					// TODO Auto-generated method stub
					
					Showcase showcase = new EigeneProjekte(p);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
		}
	
	});
	
	eigeneAusschreibungen.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			Showcase showcase = new EigeneAusschreibungen(roleManagement, navigator);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}
	
	});
	
	eigeneBewerbungen.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			gwtproxy.getBewerbungByBewerber(p, new AsyncCallback<Vector<Bewerbung>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				public void onSuccess(Vector<Bewerbung> result) {
					// TODO Auto-generated method stub
					Showcase showcase = new EigeneBewerbungenSeite(p);
					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(showcase);
				}
			});
		}
	});
	
	eigeneAusschreibungen.addClickHandler(new ClickHandler() {
	

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Showcase showcase = new EigeneAusschreibungen(roleManagement, ng);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}
		
	});
	}
	

	
	
private class BearbeitenvomProfilCallback implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(Person result) {
		// TODO Auto-generated method stub
	//	result.getVorname(boxTitel.getText(boxTitel.getSelectedText()));
	
	}
	
}
	
private class SpeichernProfilCallback implements AsyncCallback<Void>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSuccess(Void result) {
		p.setVorname(boxTitel.getText());
		p.setNachname(boxNachname.getText());
		p.setAdresse(boxAdresse.getText());
		p.setStandort(boxStandort.getText());
		
		boxTitel.setReadOnly(true);
		boxName.setReadOnly(true);
		boxNachname.setReadOnly(true);
		boxAdresse.setReadOnly(true);
		boxStandort.setReadOnly(true);
		boxTeamName.setReadOnly(true);
		boxFirmenName.setReadOnly(true);
		boxEmail.setReadOnly(true);
	}
}

private class GetPersonAusDB implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onSuccess(Person result) {
			
			boxName.setText(result.getVorname());
			boxNachname.setText(result.getNachname());
			boxAdresse.setText(result.getAdresse());
			boxStandort.setText(result.getStandort());
			boxTitel.setText(result.getTitel());
			boxEmail.setText(p.getEmailAddresse());
			
			
		}
		
	}

private class GetTeamAusDB implements AsyncCallback<Team>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Team result) {
		// TODO Auto-generated method stub
		boxTeamName.setReadOnly(true);
		boxTeamName.setText(result.getTeamName());

	}
	
}

private class GetUnternehmenAusDB implements AsyncCallback<Unternehmen>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Unternehmen result) {
		// TODO Auto-generated method stub
		boxFirmenName.setReadOnly(true);
		boxFirmenName.setText(result.getFirmenName());

	}
	
}

}

