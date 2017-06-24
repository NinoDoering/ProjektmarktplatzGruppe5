package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Unternehmen;

public class UnternehmenSeite extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private CellTable<Unternehmen> unternehmentabelle = new CellTable<Unternehmen>();
	
	private HorizontalPanel hanelUnternehmen = new HorizontalPanel();
	private VerticalPanel vpanelUnternehmen = new VerticalPanel();
	
	private Unternehmen u1 = new Unternehmen();
	private Person p1 = new Person();
	
	final SingleSelectionModel<Unternehmen> ssmallunternehmen = new SingleSelectionModel<Unternehmen>();
	
	public UnternehmenSeite(final Person person) {
		this.p1= person; 
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Wählen sie ein Unternehmen aus </h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		unternehmentabelle.setWidth("100%", true);
		vpanelUnternehmen.add(unternehmentabelle);
		
		this.add(vpanelUnternehmen);
		
		unternehmentabelle.setSelectionModel(ssmallunternehmen);
		
		ssmallunternehmen.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				u1 = ssmallunternehmen.getSelectedObject();
				ssmallunternehmen.getSelectedObject().getId();
				p1.setIdUnternehmen(ssmallunternehmen.getSelectedObject().getId());
				
				gwtproxy.savePerson(p1, new AsyncCallback<Void>() {
					
			
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Unternehmen-ADD ging nicht");
					}
					
					
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Unternehmen geändert");
						Showcase showcase = new PersonSeite(p1);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
				
			}
		});
		
			//Spalten für die Unternhemen Tabelle	
		
		TextColumn<Unternehmen> firmenName = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getFirmenName();
			}
				
		
		};
		
		
		TextColumn<Unternehmen> standort = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getStandort();
			}
				
		
		};
		
		
		TextColumn<Unternehmen> adresse = new TextColumn<Unternehmen>() {

			@Override
			public String getValue(Unternehmen object) {
				// TODO Auto-generated method stub
				return object.getAdresse();
			}
				
		
		};
		
			// Spalten Ende
		
			//Spalten der Tabele hinzufügen
			unternehmentabelle.addColumn(firmenName, "Firmenname");
			unternehmentabelle.addColumn(standort, "Standort");
			unternehmentabelle.addColumn(adresse, "Adresse");
			
			//Alle Unternehmen anzeigen lassen inder Tabelle 
			gwtproxy.getAllUnternehmen(new getAlleUnternehmenausDB());
	}		
	
	
			// Callback um Unternehmen aus der DB zu erhalten 
			private class getAlleUnternehmenausDB implements AsyncCallback<Vector<Unternehmen>>{

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Unternehmen> result) {
					// TODO Auto-generated method stub
					unternehmentabelle.setRowData(0, result);
					unternehmentabelle.setRowCount(result.size(), true);
				}
				
				
			}
}
