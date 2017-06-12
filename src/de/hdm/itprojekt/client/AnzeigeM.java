package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.thirdparty.javascript.jscomp.parsing.parser.trees.ThisExpressionTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AnzeigeM extends HorizontalPanel {
	// extends HPanel damit der Klasse widget hinzugefügt werden
	
	
	//proxy bzw RPC sorgt für die Kommunikation der GUI mit der Administrations (GreetingServiceImpl ) 
		// das proxy ruft die Methoden der Impl auf 
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	



public int IDM;	
public Button btnBezeichnung; 
public Button btnnew;
final DialogBox dialogBox;



	public AnzeigeM(final ActivitySuchen AS)
	{
		

	 btnBezeichnung = new Button();
	
	 btnnew = new Button("+"); 
	
	  
	btnBezeichnung.setStyleName("Abstand");
	btnnew.setStyleName("Abstand");
			
			add(btnBezeichnung);
			add(btnnew);
//		Hinzufügen der Buttons zu AnzeigeM
		
		
		// Start der ClickHandler 
		btnBezeichnung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Marktplatz pm = new Marktplatz();
				pm.setId(IDM);
				gwtproxy.getProjektbyMarktplatz(pm, new AsyncCallback<Vector<Projekt>>() {

					@Override
					
					//bedeutet immer was soll passieren wenn etwas nicht Klappt
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					
					// was soll passieren wenn der AsyncCallback erfolgreich war 
					public void onSuccess(Vector<Projekt> result) {
						// TODO Auto-generated method stub
						AS.clear();
						// clear bedeutet aehnlich wie remove entferne die anderen Marktplaetze sobald ein Mplatz gewaehlt wurde 
						
						//for schleife 
						for(Projekt p : result){
							AnzeigeP anzeigep = new AnzeigeP(AS);
							anzeigep.btnBezeichnung.setText(p.getBezeichnung());
							anzeigep.ID = p.getId();
							anzeigep.setStyleName("subb");
							AS.add(anzeigep);
						}
					}
				});
			}
		});
		//anzeigen
		btnnew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dialogBox.center();
			}
		});
		
		//von hier 
		
		// eine Dialog PopUp Box wird erstellt 
		 dialogBox = new DialogBox();
		 
		 
		 		// instanzieirung der Objekte für dei DialogBox 
		 
				dialogBox.setText("Neuer Projektmarktplatz");
				dialogBox.setAnimationEnabled(true);
				final Button createButton = new Button("Erstelle Marktplatz");
				final Button closeButton = new Button("Abbrechen");
				final TextBox tbName = new TextBox();
				final TextBox tbGeschaeft = new TextBox();
				
				
				tbName.setText("Bezeichnung");
				tbGeschaeft.setText("Geschäftsgebiet");
				
				closeButton.getElement().setId("closeButton");
				final Label textToServerLabel = new Label();
				final HTML serverResponseLabel = new HTML();
				VerticalPanel dialogVPanel = new VerticalPanel();
				
				//Hinzufuegen der Objekte zum dialogVPanel  
				dialogVPanel.addStyleName("dialogVPanel");
				dialogVPanel.add(new HTML("<b>Hier können Sie einen Marktplatz erstellen:</b>"));
				dialogVPanel.add(textToServerLabel);
				dialogVPanel.add(new HTML("<br><b>Ihre Eingaben:</b>"));
				dialogVPanel.add(serverResponseLabel);
				dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
				dialogVPanel.add(tbName);
				dialogVPanel.add(tbGeschaeft);
				dialogVPanel.add(closeButton);
				dialogVPanel.add(createButton);
				
				// der DialogBox wird das dialogVPaneL zugewiesen 
				dialogBox.setWidget(dialogVPanel);

				// was passiert beim Klick des Abbrechen (close) buttons 
				closeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
						
						// was passiert beim Klick des Erstellen (create) Buttons 
				createButton.addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						
						// proxy ruft die anlegen eines Marktplatzes Methode auf 
						gwtproxy.anlegenMarktplatz(tbName.getText(), tbGeschaeft.getText(), new AsyncCallback<Marktplatz>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Marktplatz result) {
								// TODO Auto-generated method stub
								Window.alert("Klappt"); 	//kleines auf popendes fenster was zeigt dass alles gut ging 
								dialogBox.hide();			// Unser PopUp Box soll dancher wieder sich schliessen 
							}
						});
						
					}
				});
					
					}
				});

	
	
 
	}}
