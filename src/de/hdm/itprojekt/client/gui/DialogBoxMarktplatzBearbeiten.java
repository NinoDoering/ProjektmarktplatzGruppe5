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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

public class DialogBoxMarktplatzBearbeiten extends DialogBox {
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	private VerticalPanel vpanel = new VerticalPanel();
	private HorizontalPanel hpanel = new HorizontalPanel();
	private RoleManagement rm = null;
	private Navigator navi = null;
	private Marktplatz markt1 = new Marktplatz();
	private Button ok = new Button("OK");
	private Button abbrechen = new Button("Abbrechen");
	
	private Label marktplatzname = new Label("Projektmarktplatzbezeichnung: ");
	private TextArea bezeichnung = new TextArea();
	private Label geschaeftsgebiet = new Label("Geschäftsgebiet: ");
	private TextArea gebiet = new TextArea();
	
	private Person person = new Person();
	
	
	
	
	private FlexTable marktplatzdialogboxtabelle = new FlexTable();
	public DialogBoxMarktplatzBearbeiten(final Marktplatz m1, final RoleManagement rm, final Navigator navi){
		this.setText("Projektmarktplatz anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		this.person = person;
		this.rm=rm;
		this.navi=navi;
		this.markt1=m1;
		hpanel.add(ok);
		hpanel.add(abbrechen);
		
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				
					markt1.setBezeichnung(bezeichnung.getText());
					markt1.setGeschaeftsgebiet(gebiet.getText());
					
					
					gwtproxy.saveMarktplatz(markt1, new  updateMarktplatzinDB());
				}
			
		});
		abbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			hide();
			}
		});
		vpanel.add(marktplatzdialogboxtabelle);
		vpanel.add(hpanel);
		this.add(vpanel);
		marktplatzdialogboxtabelle.setWidget(1, 0, marktplatzname);
		marktplatzdialogboxtabelle.setWidget(1, 1, bezeichnung);
		marktplatzdialogboxtabelle.setWidget(2, 0, geschaeftsgebiet);
		marktplatzdialogboxtabelle.setWidget(2, 1, gebiet);
	}
	private class updateMarktplatzinDB implements  AsyncCallback<Void>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Fehler beim bearbeiten");
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			hide();
			Window.alert("Marktplatzbearbeiten erfolgreich");
			Showcase showcase = new ProjektmarktplatzSeite(rm, navi);
			RootPanel.get("Anzeige").clear();
			RootPanel.get("Anzeige").add(showcase);
		}

		
			
			
		}
		
	}


