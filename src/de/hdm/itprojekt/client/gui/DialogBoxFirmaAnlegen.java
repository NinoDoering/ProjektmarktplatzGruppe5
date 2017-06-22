package de.hdm.itprojekt.client.gui;

import java.util.Vector;

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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Unternehmen;

public class DialogBoxFirmaAnlegen extends DialogBox {

	//ES FOLGEN NOCH ÄNDERUNGEN bzw. FEHLERBEHEBUNG-> morgen
	
	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	private Person person = new Person();
	private Unternehmen unter = new Unternehmen();
	
	private VerticalPanel unterVP = new VerticalPanel();
	private HorizontalPanel unterHP = new HorizontalPanel();
	
	private Button firmaSpeichern = new Button("Unternehmen Speichern");
	private Button firmaAbbrechen = new Button("Abbrechen");
	
	private FlexTable firmaTable = new FlexTable ();
	
	private Label firmaLabel = new Label("Firma ");
	private TextArea firmaArea = new TextArea();
	
	private Label firmaStrasseLabel = new Label("Straße ");
	private TextArea firmaStrasseArea = new TextArea();
	
	private Label firmaStrassennummer = new Label("Nr. ");
	private TextArea firmaStrassennummerArea = new TextArea();
	
	private Label firmaPLZ = new Label ("PLZ ");
	private TextArea firmaPLZArea = new TextArea();
	
	private Label firmaORT = new Label ("Ort ");
	private TextArea firmaORTArea = new TextArea();
	

	
	//private IdentityMarketChoice identityMarketChoice = null;
	
	public DialogBoxFirmaAnlegen(final Person person){
		this.person=person;
		this.setText("Unternehmen erstellen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		firmaSpeichern.setStylePrimaryName("firmabtn");
		firmaAbbrechen.setStylePrimaryName("firmabtn");
		unterHP.add(firmaSpeichern);
		unterHP.add(firmaAbbrechen);
		
//		firmaBox.getElement().setPropertyString("platzhalter", "Name von der Firma");
//		firmaStrasseBox.getElement().setPropertyString("platzhalter", "Straße");
//		firmaStrassennummerBox.getElement().setPropertyString("platzhalter", "Nr. ");
//		firmaPLZBox.getElement().setPropertyString("platzhalter", "PLZ");
//		firmaORTBox.getElement().setPropertyString("platzhalter", "Ort");
		
		firmaAbbrechen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		
		
		});
		firmaSpeichern.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				 GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
				
				 gwtproxy.anlegenUnternehmen(person.getId(), unter.getFirmenName(), new insertUnternehmenDB());
				
	}		
});
	
		firmaTable.setWidget(1, 0, firmaLabel);
		firmaTable.setWidget(1, 1, firmaArea);
//		firmaTable.setWidget(2, 0, firmaStrasseLabel);
//		firmaTable.setWidget(2, 1, firmaStrasseArea);
//		firmaTable.setWidget(3, 0, firmaStrassennummer);
//		firmaTable.setWidget(3, 1, firmaStrassennummerArea);
//		firmaTable.setWidget(4, 0, firmaORT);
//		firmaTable.setWidget(4, 1, firmaORTArea);
//		firmaTable.setWidget(5, 0, firmaPLZ);
//		firmaTable.setWidget(5, 1, firmaPLZArea);
		unterVP.add(firmaTable);
		unterVP.add(unterHP);
		this.add(unterVP);
					
	}
	
private class insertUnternehmenDB implements AsyncCallback<Unternehmen>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Unternehmen result) {
				// TODO Auto-generated method stub

				 gwtproxy.anlegenUnternehmen(unter.getIdPartnerprofil(), unter.getFirmenName(), new AsyncCallback<Unternehmen>());
			}
			
				 @Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Person result) {
						// TODO Auto-generated method stub
						person.setIdUnternehmen(result.getId());
						
						gwtproxy.savePerson(person, new updatePersonDB());
						hide();
						Navigator ng = new Navigator(person);
						
						RootPanel.get("idendity").clear();
						RootPanel.get("idendity").add(new IdentityMarketChoice(person, ng));
						
						RootPanel.get("Navigator").clear();
						RootPanel.get("Navigator").add(ng);
						
						Showcase showcase = new PersonSeite(person);
						
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
					
					
private class updatePersonDB implements AsyncCallback<Person>{

	@Override
	public void onFailure(Throwable caught) {
	// TODO Auto-generated method stub
							
	}

	@Override
	public void onSuccess(Person result) {
	// TODO Auto-generated method stub
			
	}
	
	}
}
}
