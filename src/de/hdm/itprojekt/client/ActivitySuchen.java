package de.hdm.itprojekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.itprojekt.server.db.PersonMapper;

public class ActivitySuchen extends HorizontalPanel implements ClickHandler {
	// proxy
	private final GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	// deklarieren von gui variablen
	Label lblAnzeige;
	Button btnButton;
	TextBox tbBeispiel;
	Label lblBeispiel;
	Label PersonKeyFromServerLabel;

	public ActivitySuchen() {
		// Projektmarktplatz suchen Aktivität

		lblBeispiel = new Label("Projektmarktplätze");

		tbBeispiel = new TextBox();
		btnButton = new Button("Abschicken");
		lblAnzeige = new Label("Testen der Datenbankverbindung");
		final Label PersonKeyFromServerLabel = new Label();

		btnButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				getPersonKeyFromServer();
				
			}
			
			public void getPersonKeyFromServer() {
		
				PersonMapper PersonKeyFromDBServer = PersonMapper.personMapper().findPersonByKey(8);
	
				

				lblAnzeige.setText(tbBeispiel.getText());
				

				/*
				 * gwtproxy.greetServer(tbBeispiel.getText(), new
				 * AsyncCallback<String>() {
				 * 
				 * @Override public void onSuccess(String result) { // TODO
				 * Auto-generated method stub lblAnzeige.setText(result);
				 * 
				 * }
				 * 
				 * @Override public void onFailure(Throwable caught) { // TODO
				 * Auto-generated method stub lblAnzeige.setText(""+caught); }
				 * });
				 */

				gwtproxy.showPersonByKey(PersonKeyFromDBServer,  new AsyncCallback<String>() {

					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						lblAnzeige.setText(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						lblAnzeige.setText("" + caught);
					}
				});

			}
		});
		add(tbBeispiel);
		add(btnButton);
		add(lblAnzeige);

	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}




}
