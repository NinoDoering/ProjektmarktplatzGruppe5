package de.hdm.itprojekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.TopBar;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class DialogBoxBewerbungAnlegen extends DialogBox {
	
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private VerticalPanel bewerbungVP = new VerticalPanel();
	private HorizontalPanel bewerbungHP = new HorizontalPanel();
	
	//DialogBox Buttons 
	private Button bewerbungSenden = new Button ("Bewerbung senden");
	private Button bewerbungAbbrechen = new Button ("Bewerbung abbrechen");
	
	// Widgets für die DialogBox
	private Label bewerbungsTextLabel = new Label("Bitte tragen sie hier Ihren Bewerbungstext ein: ");
	private TextArea bewerbungsText = new TextArea();
	
	private FlexTable bewerbungsTabelle = new FlexTable();
	
	// BO's
	private Ausschreibung ausschreibungAuswaehlen = new Ausschreibung ();
	private Marktplatz marktplatz1 = new Marktplatz();
	private Projekt projekt1 = new Projekt();
	private Bewerbung bewerbungsSchreiben = new Bewerbung ();
	private Person person1 = new Person();
	private Organisationseinheit orga = new Organisationseinheit();
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	private DatePicker datumBewerbung = new DatePicker();
	private Label lblDatum = new Label();
	
public DialogBoxBewerbungAnlegen(final Ausschreibung ausschreibungAuswahl, final RoleManagement rm, Navigator navi){
		this.ausschreibungAuswaehlen=ausschreibungAuswahl;
		this.rm = rm ;
		this.navi = navi;
//		this.marktplatz1=mp;
//		this.projekt1=projekt;
		}
	
	public DialogBoxBewerbungAnlegen(final Ausschreibung ausschreibungAuswahl, final RoleManagement rm, Navigator navi, Marktplatz mp, Projekt projekt){
		
		this.ausschreibungAuswaehlen=ausschreibungAuswahl;
		this.rm = rm ;
		this.navi = navi;
		this.marktplatz1=mp;
		this.projekt1=projekt;
		
		
		this.setText("Bewerbung anlegen");
		this.setAnimationEnabled(false);
		this.setGlassEnabled(true);
		
		bewerbungHP.add(bewerbungSenden);
		bewerbungHP.add(bewerbungAbbrechen);
		
		Window.alert("Projekt :              "+ausschreibungAuswahl.getBezeichnung());
		
		// Datepicker
		datumBewerbung.addValueChangeHandler(new ValueChangeHandler<Date>() {
			
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				// TODO Auto-generated method stub
				Date date = event.getValue();
				String datum = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
				lblDatum.setText(datum);
			}
		});
		datumBewerbung.setValue(new Date(), true);
		
		bewerbungSenden.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				Window.alert("" + rm.getSelectedRoleID());
//				Window.alert("DIESE :"+ ausschreibungAuswahl.getBezeichnung());
//				Window.alert("vor proxy");
			gwtproxy.anlegenBewerbung(rm.getSelectedRoleID(), ausschreibungAuswahl.getId(), bewerbungsText.getText(), datumBewerbung.getValue(), BewerbungsStatus.eingereicht, new bewerbungInDB());
			}
		});
		
		
		bewerbungAbbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				hide();
			}
		});
		
		bewerbungVP.add(bewerbungsTabelle);
		bewerbungVP.add(bewerbungHP);
		this.add(bewerbungVP);
		
		bewerbungsTabelle.setWidget(1, 0, bewerbungsTextLabel);
		bewerbungsTabelle.setWidget(1, 1, bewerbungsText);
		bewerbungsTabelle.setWidget(2, 0, datumBewerbung);
		
		
	}
		private class bewerbungInDB implements AsyncCallback<Bewerbung>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("etwas falsch gelaufen beim Bewerben");
			}

			@Override
			public void onSuccess(Bewerbung result) {
				// TODO Auto-generated method stub
				hide();
				Window.alert("Anscheinend funkts es   "+projekt1.getId()+"  " +marktplatz1.getId() + "  "+ person1.getId());
				Showcase showcase = new AusschreibungSeite(marktplatz1, projekt1, ausschreibungAuswaehlen, rm, navi);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
			}
			
			
			
		}
	
}
	
	


