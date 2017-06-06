package de.hdm.itprojekt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;

public class Marktplatzanlegen extends VerticalPanel {
	
	//proxy
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
		
	
	private HorizontalPanel hp1;
	private HorizontalPanel hp2;
	private Button btnErstellen;
	private Label lblBezeichnung;
	private Label lblGeschaeft;
	private TextBox tbBezeichnung;
	private TextBox tbGeschaeft;
	public Marktplatzanlegen(){
		hp1 = new HorizontalPanel();
		hp2 = new HorizontalPanel();
		lblBezeichnung = new Label("Marktplatz erstellen");
		lblGeschaeft = new Label("Geschaeftsgebiet");
		tbBezeichnung = new TextBox();
		tbGeschaeft = new TextBox();
		btnErstellen = new Button("Erstellen");
		hp1.add(lblGeschaeft);
		hp1.add(tbGeschaeft);
		hp2.add(lblBezeichnung);
		hp2.add(tbBezeichnung);
		add(hp1);
		add(hp2);
		add(btnErstellen);
		
		btnErstellen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			gwtproxy.anlegenMarktplatz(tbGeschaeft.getText(), tbBezeichnung.getText(), new AsyncCallback<Marktplatz>() {
				
				@Override
				public void onSuccess(Marktplatz result) {
					// TODO Auto-generated method stub
					lblBezeichnung.setText("Success"+ tbBezeichnung.getText());
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					lblBezeichnung.setText("Fehler");
				}
			});
				
			}
		});
		
	}
	
}
