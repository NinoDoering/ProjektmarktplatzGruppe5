package de.hdm.itprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
	
	private CellTable<BeteiligungMixProjekt> eigeneBeteiligungTabelle = new CellTable<BeteiligungMixProjekt>();
	
	final SingleSelectionModel<BeteiligungMixProjekt> ssmallEigeneEigenschaft = new SingleSelectionModel<BeteiligungMixProjekt>();
	
	private HorizontalPanel hpanelEigBet = new HorizontalPanel();
	private VerticalPanel vpanelEigBet = new VerticalPanel();
	private Beteiligung beteiligung;
	
	Vector<BeteiligungMixProjekt> mix = new Vector<EigeneBeteiligungen.BeteiligungMixProjekt>();
	
	private CellTable<Projekt> zugehörigesProjektTabelle = new CellTable<Projekt>();
	
	//BO´s 
	
	private RoleManagement rm = null;
	private Navigator navi = null;
	
	//Buttons 
	private Button anzeignBeteil = new Button("Beteiligung anzeigen");
	private Button loeschenBeteil = new Button("Beteiligung zurückziehen");
	
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
		hpanelEigBet.add(loeschenBeteil);
		
		this.add(hpanelEigBet);
		this.add(vpanelEigBet);
		
		 
		
		eigeneBeteiligungTabelle.setSelectionModel(ssmallEigeneEigenschaft);
		
		
		loeschenBeteil.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				final Beteiligung neueBeteiligung= new Beteiligung();
				neueBeteiligung.setId(ssmallEigeneEigenschaft.getSelectedObject().getIdBeteiligung());
				gwtproxy.loeschenBeteiligung(neueBeteiligung, new AsyncCallback<Void>() {
					
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Fehler beim Kündigen");
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Erfolgreich gekündigt");
						Showcase showcase = new EigeneBeteiligungen(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);

					}
				});
			}
		});
		
		
		ssmallEigeneEigenschaft.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				
				
			
			}
		});
	
		TextColumn<BeteiligungMixProjekt> beteiligungsZeit = new TextColumn<BeteiligungMixProjekt>() {
			
			@Override
			public String getValue(BeteiligungMixProjekt object) {
				// TODO Auto-generated method stub
				return object.getBeteiligungszeit()+"";
			}
		};
		
	TextColumn<BeteiligungMixProjekt> proName = new TextColumn<BeteiligungMixProjekt>() {
				
				@Override
				public String getValue(BeteiligungMixProjekt object) {
					// TODO Auto-generated method stub
					return object.getProjektbezeichnung();
				}
			};
	

			
			
		
		
		
		eigeneBeteiligungTabelle.addColumn(proName, "Projektbezeichnung");
		eigeneBeteiligungTabelle.addColumn(beteiligungsZeit, "Beteiligungszeit in Tagen: ");
		
		gwtproxy.getBeteiligungByBeteiligter(rm.getUser(), new getEigeneBeteiligung());
		
		
	
	}
	
	
	private class getEigeneBeteiligung implements AsyncCallback<Vector<Beteiligung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Beteiligung> result) {
			for (Beteiligung beteiligung : result) {
				final BeteiligungMixProjekt betUndPro = new BeteiligungMixProjekt(); 
				betUndPro.setIdBeteiligung(beteiligung.getId());
				betUndPro.setBeteiligungszeit(beteiligung.getBeteiligungszeit());

				gwtproxy.getProjektByBeteiligung(beteiligung, new AsyncCallback<Projekt>() {
					
					@Override
					public void onSuccess(Projekt result) {
						betUndPro.setProjektbezeichnung(result.getBezeichnung());
						
						mix.add(betUndPro);
						
						
						eigeneBeteiligungTabelle.setRowData(mix);
						eigeneBeteiligungTabelle.setRowCount(mix.size(), true);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
						
					}
				});
				
			}
			
		}

}
	
	
private class BeteiligungMixProjekt{
		
		private int beteiligungszeit ;
		private String projektbezeichnung ;
		private Date projektStartdatum;
		private int idBeteiligung;
		private int idProjekt;
		
		
		public int getIdBeteiligung() {
			return idBeteiligung;
		}
		public void setIdBeteiligung(int idBeteiligung) {
			this.idBeteiligung = idBeteiligung;
		}
		public int getIdProjekt() {
			return idProjekt;
		}
		public void setIdProjekt(int idProjekt) {
			this.idProjekt = idProjekt;
		}
		public Date getProjektStartdatum() {
			return projektStartdatum;
		}
		public void setProjektStartdatum(Date projektStartdatum) {
			this.projektStartdatum = projektStartdatum;
		}
		public int getBeteiligungszeit() {
			return beteiligungszeit;
		}
		public void setBeteiligungszeit(int beteiligungszeit) {
			this.beteiligungszeit = beteiligungszeit;
		}
		public String getProjektbezeichnung() {
			return projektbezeichnung;
		}
		public void setProjektbezeichnung(String projektbezeichnung) {
			this.projektbezeichnung = projektbezeichnung;
		}
		
		
	}
	
}
