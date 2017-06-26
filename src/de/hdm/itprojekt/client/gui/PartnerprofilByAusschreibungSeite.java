package de.hdm.itprojekt.client.gui;
import java.util.Vector;
import java.util.ArrayList;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.itprojekt.client.ClientSideSettings;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.Navigator;

import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.client.gui.BewerbungenAufAusschreibungSeite.BewertungBewerbung;

public class PartnerprofilByAusschreibungSeite extends VerticalPanel {
	
	GreetingServiceAsync greetingService = ClientSideSettings.getMarktplatzVerwaltung();

	CellTable<Eigenschaft> partnerprofilByAusschreibungCt = new CellTable<Eigenschaft>();
	Button loeschenButton = new Button("Löschen");
	Button zurueckButton = new Button("Zurück");
	HorizontalPanel buttonPanel = new HorizontalPanel();

	private RoleManagement roleManagement=null;
	private Navigator navigator =null;
	
	public PartnerprofilByAusschreibungSeite(final int idPartnerprofil,  final RoleManagement roleManagement, final Navigator navigator){
		this.roleManagement=roleManagement;
		this.navigator=navigator;
		
		
		
		
		RootPanel.get("Anzeige").setWidth("70%");
		partnerprofilByAusschreibungCt.setWidth("100%", true);
	
	//Partnerprofil mit Callback aufrufen
	greetingService.getPartnerprofilbyId(idPartnerprofil, new AsyncCallback<Partnerprofil>(){
		
		public void onFailure(Throwable caught) {
		}
	
		// Callback um Eigenschaften des Partnerprofils zu holen
	
		public void onSuccess(Partnerprofil result) {
			greetingService.getEigenschaftByPartnerprofil(result,new AsyncCallback<Vector<Eigenschaft>>(){

				public void onFailure(Throwable caught) {
					Window.alert("Das Laden des Partnerprofils ist fehlgeschlagen.");
				}
	
				public void onSuccess(Vector<Eigenschaft> result) {
					
					/**Anlegen eines ListDataDataProvider Pager Objects um die Liste an
					* Objekten beim Umschalten von mehreren Celltables zu schalten
					*/
					final ListDataProvider dataProvider = new ListDataProvider();
					SimplePager pager;
					SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
					pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
					pager.setDisplay(partnerprofilByAusschreibungCt);
					dataProvider.addDataDisplay(partnerprofilByAusschreibungCt);
					dataProvider.setList(new ArrayList<Eigenschaft>(result));
					pager.setPageSize(10);
					
					HorizontalPanel hp_pager = new HorizontalPanel();
					hp_pager.setWidth("100%");
					hp_pager.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
					hp_pager.add(pager);
					add(hp_pager);
					partnerprofilByAusschreibungCt.setRowCount(result.size(), true);
					partnerprofilByAusschreibungCt.setRowData(0, result);
					
				}
			});
		}

});
	partnerprofilByAusschreibungCt.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
	
	// TextColumns für Celltable
	
	TextColumn<Eigenschaft> abschlussColumn = new TextColumn<Eigenschaft>(){

		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getAbschluss();
		}
		
	};
	
	TextColumn<Eigenschaft> arbeitsgebietColumn = new TextColumn<Eigenschaft>(){

		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getArbeitsgebiet();
		}
		
	};
	TextColumn<Eigenschaft> ausbildungColumn = new TextColumn<Eigenschaft>(){

		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getAusbildung();
		}
		
	};
	
	TextColumn<Eigenschaft> berufserfahrungsJahreColumn = new TextColumn<Eigenschaft>(){

		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getBerufserfahrungsJahre();
		}
		
	};
	
	TextColumn<Eigenschaft> employmentStatusColumn = new TextColumn<Eigenschaft>(){

		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getEmploymentStatus();
		}
	};	
		
	TextColumn<Eigenschaft> sprachkenntnisseColumn = new TextColumn<Eigenschaft>(){

			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getSprachkenntnisse();
			}	
		};
		
		partnerprofilByAusschreibungCt.addColumn(arbeitsgebietColumn,"Arbeitsgebiet" );
		partnerprofilByAusschreibungCt.addColumn(ausbildungColumn,"Ausbildung" );
		partnerprofilByAusschreibungCt.addColumn(berufserfahrungsJahreColumn,"Berufserfahrungsjahre" );
		partnerprofilByAusschreibungCt.addColumn(sprachkenntnisseColumn,"Sprachkenntnisse" );
		partnerprofilByAusschreibungCt.addColumn(employmentStatusColumn,"EmploymentStatus" );
		partnerprofilByAusschreibungCt.addColumn(abschlussColumn,"Abschluss");
		
		final SingleSelectionModel<Eigenschaft> selectionModel = new SingleSelectionModel<>();
		partnerprofilByAusschreibungCt.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			@Override
			public void onSelectionChange(SelectionChangeEvent event){
	
			}
	});
	
	partnerprofilByAusschreibungCt.setWidth("100%");
	
	buttonPanel.add(zurueckButton);
	buttonPanel.add(loeschenButton);
	
	
	zurueckButton.setStylePrimaryName("cell-btn");
	loeschenButton.setStylePrimaryName("cell-btn");
	
	this.setSpacing(8);
	this.add(buttonPanel);
	this.add(partnerprofilByAusschreibungCt);
	
	zurueckButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			navigator.reload();
				
		}
});
	
	//ClickHandler um Eigenschaft zu löschen
	loeschenButton.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event) {
			Eigenschaft selectedEigenschaft = selectionModel.getSelectedObject();
			greetingService.loeschenEigenschaft(selectedEigenschaft, new AsyncCallback<Void>() {
				public void onFailure(Throwable caught) {
					Window.alert("Fehler: Die Eigenschaft konnte nicht gelöscht werden.");
				}
				public void onSuccess(Void result) {
					Window.alert("Die Eigenschaft wurde erfolgreich gelöscht.");

					RootPanel.get("Anzeige").clear();
					RootPanel.get("Anzeige").add(new PartnerprofilByAusschreibungSeite(MeineAusschreibungenSeite.getIdPartnerprofilOfSelectedAusschreibung(), false, false, roleManagement, navigator));
					
				}
			});
		}
});
	zurueckButton.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			navigator.reload();					
		}
	});
	
	}

  }


	