package de.hdm.itprojekt.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DialogBox;
import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Unternehmen;

/**
 * Diese Klasse ist dafür zustaendig, dass eine Person das Unternehmen, der es angehoert, wechseln kann.
 * Eine Person kann das Unternehmen wechseln, sobald diese die Funktion "Team bearbeiten" auswählt.
 * Daraufhin erscheint das Fenster, in dem sie die Unternehmen auswaehlen kann. Das
 * Unternehmen kann von einer Person jederzeit gewechselt werden.
 */
public class DialogBoxUnternehmenBearbeiten extends DialogBox {

	GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private Person person = new Person();
	private Unternehmen unternehmen = null;
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	private VerticalPanel unterVP = new VerticalPanel();
	private HorizontalPanel unterHP = new HorizontalPanel();
	
	private Button firmaSpeichern = new Button("Unternehmen speichern");
	private Button firmaAbbrechen = new Button("Abbrechen");
	
	private FlexTable firmaBearbeitenTabelle = new FlexTable();
	
	private Label firmaLabel = new Label("Firma ");
	private TextArea firmaArea = new TextArea();
	private Label firmaAdresseLabel = new Label("Straße ");
	private TextArea firmaAdresseArea = new TextArea();
	private Label firmaORTLabel = new Label ("Ort ");
	private TextArea firmaORTArea = new TextArea();
	
	public DialogBoxUnternehmenBearbeiten(final RoleManagement rm, final Navigator navi, Unternehmen u){
		this.rm=rm;
		this.navi=navi;
		this.unternehmen=u;
		this.setText("Unternehmen bearbeiten");
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		firmaArea.setValue(unternehmen.getFirmenName());
		firmaAdresseArea.setValue(unternehmen.getAdresse());
		firmaORTArea.setValue(unternehmen.getStandort());
		firmaSpeichern.setStylePrimaryName("firmabtn");
		firmaAbbrechen.setStylePrimaryName("firmabtn");
		unterHP.add(firmaSpeichern);
		unterHP.add(firmaAbbrechen);
		
		
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
				Window.alert(" "+ unternehmen.getId());
				unternehmen.setFirmenName(firmaArea.getText());
				unternehmen.setAdresse(firmaAdresseArea.getText());
				unternehmen.setStandort(firmaORTArea.getText());
				unternehmen.setId(unternehmen.getId());
				
				gwtproxy.saveUnternehmen(unternehmen, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Unternehmen konnte nicht bearbeitet werden");
						
					}

					@Override
					public void onSuccess(Void result) {
						
						hide();
						Showcase showcase = new UnternehmenSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
						
					}

					
					
				});
			}
			
		});
		
		firmaBearbeitenTabelle.setWidget(1, 0, firmaLabel);
		firmaBearbeitenTabelle.setWidget(1, 1, firmaArea);
		firmaBearbeitenTabelle.setWidget(2, 0, firmaAdresseLabel);
		firmaBearbeitenTabelle.setWidget(2, 1, firmaAdresseArea);
		firmaBearbeitenTabelle.setWidget(3, 0, firmaORTLabel);
		firmaBearbeitenTabelle.setWidget(3, 1, firmaORTArea);
		unterVP.add(firmaBearbeitenTabelle);
		unterVP.add(unterHP);
		this.add(unterVP);
		
	}
	
	

}
