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
	GreetingServiceAsync gwtproxy2 = GWT.create(GreetingService.class);

	private Person person = new Person();
	private Unternehmen unter = new Unternehmen();
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	private VerticalPanel unterVP = new VerticalPanel();
	private HorizontalPanel unterHP = new HorizontalPanel();
	
	private Button firmaSpeichern = new Button("Unternehmen Speichern");
	private Button firmaAbbrechen = new Button("Abbrechen");
	
	private FlexTable firmaTable = new FlexTable ();
	
	private Label firmaLabel = new Label("Firma ");
	private TextArea firmaArea = new TextArea();
	
	private Label firmaAdresseLabel = new Label("Straße ");
	private TextArea firmaAdresseArea = new TextArea();
	
	private Label firmaORTLabel = new Label ("Ort ");
	private TextArea firmaORTArea = new TextArea();
	

	
	//private IdentityMarketChoice identityMarketChoice = null;
	
	public DialogBoxFirmaAnlegen(final RoleManagement rm, final Navigator navi){
		this.rm=rm;
		this.navi=navi;
//		this.person=person;
		this.setText("Unternehmen erstellen");
		this.setAnimationEnabled(true);
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
				gwtproxy.anlegenPartnerprofil(new AsyncCallback<Partnerprofil>(){

					@Override
					public void onFailure(Throwable caught) {
						
						
					}

					@Override
					public void onSuccess(Partnerprofil result) {
						// TODO Auto-generated method stub
						Window.alert("bis zu gwtproxy2");
						gwtproxy2.anlegenUnternehmen(result.getId(), firmaArea.getText(), firmaAdresseArea.getText(), firmaORTArea.getText(), new AsyncCallback<Unternehmen>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("unternehmen of failure");
								
							}

							@Override
							public void onSuccess(Unternehmen result) {
								hide();
								Showcase showcase = new UnternehmenSeite(rm, navi);
								RootPanel.get("Anzeige").clear();
								RootPanel.get("Anzeige").add(showcase);
								
							}
							
						});
					}
					
				});
				
				 
				
	}		
});
	
		firmaTable.setWidget(1, 0, firmaLabel);
		firmaTable.setWidget(1, 1, firmaArea);
		firmaTable.setWidget(2, 0, firmaAdresseLabel);
		firmaTable.setWidget(2, 1, firmaAdresseArea);
		firmaTable.setWidget(3, 0, firmaORTLabel);
		firmaTable.setWidget(3, 1, firmaORTArea);
		unterVP.add(firmaTable);
		unterVP.add(unterHP);
		this.add(unterVP);
					
	}
	
//private class insertUnternehmenDB implements AsyncCallback<Unternehmen>{
//
//			
//
//			@Override
//			public void onSuccess(Unternehmen result) {
//				// TODO Auto-generated method stub
//				hide();
//				Showcase showcase = new UnternehmenSeite(person);
//				RootPanel.get("Anzeige").clear();
//				RootPanel.get("Anzeige").add(showcase);
//				 
//			}
//			
//				 @Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						
//					}

//					@Override
//					public void onSuccess(Person result) {
//						// TODO Auto-generated method stub
//						person.setIdUnternehmen(result.getId());
//						
//						gwtproxy.savePerson(person, new updatePersonDB());
//						hide();
//						Navigator ng = new Navigator(person);
//						
//						RootPanel.get("identity").clear();
//						RootPanel.get("identity").add(new IdentityMarketChoice(person, ng));
//						
//						RootPanel.get("Navigator").clear();
//						RootPanel.get("Navigator").add(ng);
//						
//						Showcase showcase = new PersonSeite(person);
//						
//						RootPanel.get("Anzeige").clear();
//						RootPanel.get("Anzeige").add(showcase);
//					}
					
					
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

