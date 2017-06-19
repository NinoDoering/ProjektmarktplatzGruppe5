package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.BusinessObject;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;

public class PersonSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Person> persontabelle = new CellTable<Person>();
	
	HorizontalPanel hpanelPerson = new HorizontalPanel();
	VerticalPanel vpanelPerson = new VerticalPanel();
	
	private Organisationseinheit o = new Organisationseinheit ();
	
	private Person person1= new Person();
	
	final SingleSelectionModel<Person> ssmalleperson = new SingleSelectionModel<Person>();
	
	Button anzeigebutton = new Button("Profil Suchen");
	Button bearbeitenbutton = new Button ("Profil Bearbeiten");
	Button speichernbutton = new Button ("Speichern");
	Button deletebutton = new Button ("Profil Löschen");
	
	String deletePerson = new String("Löschen");
	
	public PersonSeite() {
		// TODO Auto-generated constructor stub
	}
	
	public PersonSeite(Organisationseinheit o){
		this.o=o;
	}

	public PersonSeite(Person p1) {
		this.person1=p1;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Mein Profil</h1>";
	}

	@Override
	protected void run() {
		
		RootPanel.get("Anzeige").setWidth("100%");
		
		persontabelle.setWidth("100%", true);
		
		vpanelPerson.add(persontabelle);
		hpanelPerson.add(anzeigebutton);
		hpanelPerson.add(bearbeitenbutton);
		hpanelPerson.add(speichernbutton);
		hpanelPerson.add(deletebutton);
		this.add(hpanelPerson);
		this.add(vpanelPerson);
		
		
		persontabelle.setSelectionModel(ssmalleperson);
		
		ssmalleperson.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				person1 = ssmalleperson.getSelectedObject();
				Showcase showcase = new PersonSeite(person1);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
		});
		
		TextColumn<Person> persontabellenId = new TextColumn<Person>(){

			@Override
			public String getValue(Person object) {
				// TODO Auto-generated method stub
			return object.getId()+" "; 
			}
			
		};
		
		TextColumn<Person> persontabellentitel = new TextColumn<Person>(){

			@Override
			public String getValue(Person object) {
				// TODO Auto-generated method stub
				return object.getTitel();
			}
			
		};
		
		
		TextColumn<Person> personspaltenname = new TextColumn<Person>(){

			@Override
			public String getValue(Person object) {
				// TODO Auto-generated method stub
				return object.getVorname();
			}
			
		};
		
		TextColumn<Person> persontabellenachname = new TextColumn<Person>(){

			@Override
			public String getValue(Person object) {
				// TODO Auto-generated method stub
				return object.getNachname();
			}
			
		};
		
		persontabelle.addColumn(persontabellenId, "Id");
		persontabelle.addColumn(persontabellentitel, "Titel");
		persontabelle.addColumn(personspaltenname, "Vorname");
		persontabelle.addColumn(persontabellenachname, "Nachname");
		
		gwtproxy.getPersonById(person1.getId(), new getPersonByKey());
		
		anzeigebutton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
			}
		});
	}
public class getPersonByKey implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		Window.alert("Hat nicht Funktioniert.");
		
	}

	@Override
	public void onSuccess(Person result) {
		// TODO Auto-generated method stub
//		persontabelle.setRowData(0, result);
//		persontabelle.setRowCount(result.size(), true);
		Window.alert("Hat Funktioniert.");
		person1.setId(1);

	}
	
}
}

