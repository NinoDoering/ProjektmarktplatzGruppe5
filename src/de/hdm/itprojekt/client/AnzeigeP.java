package de.hdm.itprojekt.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

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
							
							anzeigea.headerA.setText("Sie befinden sich auf folgender Ausschreibung:  ");
							anzeigea.lblBezeichnung.setText("Bezeichnung: "+a.getBezeichnung());
							anzeigea.lblBeschreibung.setText("Beschreibung: "+a.getBeschreibung());
							anzeigea.lblBewerbFrist.setText("Bewerbungsfrist: "+a.getEndDatum());
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
		
		btnnew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				dialogBox.center();
			}
		});
		
		 dialogBox = new DialogBox();
		 
			dialogBox.setText("Neues Projekt");
			dialogBox.setAnimationEnabled(true);
			final Button createButton = new Button("Erstellen");
			final Button closeButton = new Button("Abbrechen");
			final Label proBez = new Label("Bezeichnung; ");
			final Label proBeschr = new Label("Beschreibung: ");
			final TextBox tbBezeichnung = new TextBox();
			final TextBox tbBeschreibung = new TextBox();
			final DatePicker startD = new DatePicker();
			final Label startDatum = new Label("Startdatum: ");
			final DatePicker endD = new DatePicker();
			final Label endDatum = new Label("Enddatum: ");
			final Label text = new Label();
			
			// Erstellung des DatePicker für das StartDatum
			
			startD.addValueChangeHandler(new ValueChangeHandler<Date>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					Date startDate = event.getValue();
					
					String datum = DateTimeFormat.getFormat("MM/dd/yyyy").format(startDate);
					text.setText(datum);
				}
			});
			
			startD.setValue( new Date(), true);
			
			// Erstellung des DatePicker für das EndDatum
			
			endD.addValueChangeHandler(new ValueChangeHandler<Date>() {

				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					// TODO Auto-generated method stub
					Date startDate = event.getValue();
					
					String datum = DateTimeFormat.getFormat("MM/dd/yyyy").format(startDate);
					text.setText(datum);
				}
			});
			
			//DateBox für das Startdatum eines Projektes erstellen 
			
			DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy");
			DateBox dateBox = new DateBox();
		      dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
			
		      
		    //DateBox für das Enddatum eines Projektes erstellen
		      DateTimeFormat dateEnd = DateTimeFormat.getFormat("MM/dd/yyyy");
		      
		      DateBox dateEndBox = new DateBox();
		      dateEndBox.setFormat(new DateBox.DefaultFormat(dateFormat));
			
		     // Hinzufügen der Label,TextBoxen und  DateBoxen zum PopUp Fenster 
		      
			closeButton.getElement().setId("closeButton");
			final Label textToServerLabel = new Label();
			final HTML serverResponseLabel = new HTML();
			VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Ein neues Projekt erstellen:</b>"));
			dialogVPanel.add(textToServerLabel);
			dialogVPanel.add(new HTML("<br><b>Ihre Eingaben:</b>"));
			dialogVPanel.add(serverResponseLabel);
			dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
			dialogVPanel.add(proBez);
			dialogVPanel.add(tbBezeichnung);
			dialogVPanel.add(proBeschr);
			dialogVPanel.add(tbBeschreibung);
		
			dialogVPanel.add(text);
			//dialogVPanel.add(startD);  // DatePicker wird nicht hinzugefügt , da unnötig da 
										// durch DateBox ein DatePicker aufpopt 
			
			
			dialogVPanel.add(startDatum);							
			dialogVPanel.add(dateBox);
			dialogVPanel.add(endDatum);
			dialogVPanel.add(dateEndBox);
			dialogVPanel.add(closeButton);
			dialogVPanel.add(createButton);
			dialogBox.setWidget(dialogVPanel);

			// was passiert beim Klick des Abbrechen (close) buttons 
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				
				}
			});

	
			// was passiert beim Klick des Erstellen (create) Buttons
			
//			createButton.addClickHandler(new ClickHandler() {
//				
//				@Override
//				public void onClick(ClickEvent event) {
//					// TODO Auto-generated method stub
//					gwtproxy.anlegenProjekt(idPerson, idMarktplatz, beschreibung, bezeichnung, startDatum, endDatum, callback);
//				}
//			})
		
	}
	
	
	
 
}
