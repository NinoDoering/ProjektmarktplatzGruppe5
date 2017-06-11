package de.hdm.itprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Projekt;

public class AnzeigeP extends HorizontalPanel {
	
	
public int ID;	

public Button btnBezeichnung; 
public Button btnnew;
private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
final DialogBox dialogBox;
// TextBoxen für Neues Projekt 

	public AnzeigeP(final ActivitySuchen AS)
	{
		
	
	 btnBezeichnung = new Button();
	
	 btnnew = new Button("+"); 
	
	 // TextBoxen für Neues Projekt 
btnBezeichnung.setStyleName("Abstand");
	btnnew.setStyleName("Abstand");
		add(btnBezeichnung);
		add(btnnew);
		btnBezeichnung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Projekt p = new Projekt();
				p.setId(ID);
				gwtproxy.getAusschreibungByProjekt(p, new AsyncCallback<Vector<Ausschreibung>>() {
					
					@Override
					public void onSuccess(Vector<Ausschreibung> result) {
						// TODO Auto-generated method stub
						AS.clear();
						for(Ausschreibung a : result){
							AnzeigeA anzeigea = new  AnzeigeA();
							anzeigea.lblBeschreibung.setText("Beschreibung: "+a.getBeschreibung());
							anzeigea.lblStatus.setText("Status: "+ a.getAusschreibungsstatus());
							anzeigea.btnBezeichnung.setText(a.getBezeichnung());
							anzeigea.ID = a.getId();
							anzeigea.setStyleName("subb");
							AS.add(anzeigea);
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		});
		
		
		
		 dialogBox = new DialogBox();
		 
			dialogBox.setText("Remote Procedure Call");
			dialogBox.setAnimationEnabled(true);
			final Button createButton = new Button("Create");
			final Button closeButton = new Button("Close");
			final TextBox tbName = new TextBox();
			final TextBox tbGeschaeft = new TextBox();
			// We can set the id of a widget by accessing its Element
			closeButton.getElement().setId("closeButton");
			final Label textToServerLabel = new Label();
			final HTML serverResponseLabel = new HTML();
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Hier können Sie einen Marktplatz erstellen:</b>"));
			dialogVPanel.add(textToServerLabel);
			dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
			dialogVPanel.add(serverResponseLabel);
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(tbName);
			dialogVPanel.add(tbGeschaeft);
			dialogVPanel.add(closeButton);
			dialogVPanel.add(createButton);
			dialogBox.setWidget(dialogVPanel);

			// Add a handler to close the DialogBox
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				
				}
			});

	//bis hier 	

			btnnew.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					dialogBox.center();
				}
			});
		
	}
	
	
	
 
}
