package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Projekt;

// IN DIESER KLASSE WERDEN DIE EIGENEN BETEILIGUNGEN ANGEZEIGT

public class EigeneBeteiligungen extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	private CellTable<Beteiligung> eigeneBeteiligungTabelle = new CellTable<Beteiligung>();
	final SingleSelectionModel<Beteiligung> ssmallEigeneEigenschaft = new SingleSelectionModel<Beteiligung>();
	private HorizontalPanel hpanelEigBet = new HorizontalPanel();
	private VerticalPanel vpanelEigBet = new VerticalPanel();
	private Beteiligung beteiligung;
	
	private CellTable<Projekt> zugehörigesProjektTabelle = new CellTable<Projekt>();
	
	//BO´s 
	
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	//Buttons 
	private Button anzeignBeteil = new Button("Beteiligung anzeigen");
	
	//Konstruktoren
	
	public EigeneBeteiligungen(final RoleManagement rm , final Navigator navi) {
		this.rm=rm;
		this.navi=navi;
	}
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Hier finden sie Ihre Beteiligungen </h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigeneBeteiligungTabelle.setWidth("100%", true);
		eigeneBeteiligungTabelle.setStylePrimaryName("celltable");
		vpanelEigBet.add(eigeneBeteiligungTabelle);
		
		
		zugehörigesProjektTabelle.setWidth("100%", true);
		zugehörigesProjektTabelle.setStylePrimaryName("celltable");
		vpanelEigBet.add(zugehörigesProjektTabelle);
		
	
		
		hpanelEigBet.add(anzeignBeteil);
		
		this.add(hpanelEigBet);
		this.add(vpanelEigBet);
		
		eigeneBeteiligungTabelle.setSelectionModel(ssmallEigeneEigenschaft);
		
		ssmallEigeneEigenschaft.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				beteiligung= ssmallEigeneEigenschaft.getSelectedObject();
			}
		});
	
		TextColumn<Beteiligung> beteiligungsZeit = new TextColumn<Beteiligung>() {
			
			@Override
			public String getValue(Beteiligung object) {
				// TODO Auto-generated method stub
				return object.getBeteiligungszeit()+"";
			}
		};
		
TextColumn<Beteiligung> proid = new TextColumn<Beteiligung>() {
			
			@Override
			public String getValue(Beteiligung object) {
				// TODO Auto-generated method stub
				return object.getIdProjekt()+"";
			}
		};
	
	TextColumn<Projekt> zugehörigesProjekt = new TextColumn<Projekt>() {
				
				@Override
				public String getValue(Projekt object) {
					// TODO Auto-generated method stub
					return object.getBezeichnung();
				}
			};
			
			
		
		
		eigeneBeteiligungTabelle.addColumn(beteiligungsZeit, "Beteiligungszeit in Tagen: ");
		eigeneBeteiligungTabelle.addColumn(proid, "PROJEKTID");
		
		
		gwtproxy.getBeteiligungByBeteiligter(rm.getUser(), new getEigeneBeteiligung());
		
	
	}
	
	private class getEigeneBeteiligung implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {
			// TODO Auto-generated method stub
			Window.alert("Funktioniert" + rm.getUser().getId());
			
			eigeneBeteiligungTabelle.setRowData(0, result);
			eigeneBeteiligungTabelle.setRowCount(result.size(), true);
		}

}}
