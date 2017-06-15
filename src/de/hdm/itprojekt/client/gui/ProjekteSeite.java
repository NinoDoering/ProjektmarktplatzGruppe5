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

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Projekt;

public class ProjekteSeite extends Showcase{

	private static final String ssmalleprojektmarktplaetze = null;
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Marktplatz mp = new Marktplatz();
	CellTable<Projekt> projekttabelle = new CellTable<Projekt>();
	
	private HorizontalPanel hpanelProjekte = new HorizontalPanel();
	private VerticalPanel vpanelProjekte = new VerticalPanel();
	
	private Projekt p1 = new Projekt();
	
	final SingleSelectionModel<Projekt> ssmalleprojekte = new SingleSelectionModel<Projekt>();
	
	Button anlegenprojekt = new Button("Anlegen");
	
	public ProjekteSeite(){
	
	}
	
	public ProjekteSeite(Marktplatz m1){
		this.mp= m1;
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Projekte durchsuchen</h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		projekttabelle.setWidth("100%", true);
		vpanelProjekte.add(projekttabelle);
		hpanelProjekte.add(anlegenprojekt);
		this.add(hpanelProjekte);
		this.add(vpanelProjekte);
		
		projekttabelle.setSelectionModel(ssmalleprojekte);
		
		ssmalleprojekte.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				//WEITERMACHEN FÜR AUSSCHREIBUNG !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}
		});
		
		TextColumn<Projekt> projektBez = new TextColumn<Projekt>(){

			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
	
		TextColumn<Projekt> projektBeschr = new TextColumn<Projekt>(){
	
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getBeschreibung();
			}
		
		
	};
	
		TextColumn<Projekt> projektStartD = new TextColumn<Projekt>(){
	
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getStartDatum().toString();
			}
		
		
	};

		TextColumn<Projekt> projektEndD = new TextColumn<Projekt>(){
			
			@Override
			public String getValue(Projekt object) {
				// TODO Auto-generated method stub
				return object.getEndDatum().toString();
			}
		
		
	};
	
		projekttabelle.addColumn(projektBez, "Bezeichnung");
		projekttabelle.addColumn(projektBeschr, "Beschreibung");
		projekttabelle.addColumn(projektStartD, "Startdatum");
		projekttabelle.addColumn(projektEndD, "Enddatum");
		gwtproxy.getProjektbyMarktplatz(mp, new getProjekteAusDB());
}
		private class getProjekteAusDB implements AsyncCallback<Vector<Projekt>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Hat nicht funktioniert");
			}

			@Override
			public void onSuccess(Vector<Projekt> result) {
				// TODO Auto-generated method stub
				projekttabelle.setRowData(0, result);
				projekttabelle.setRowCount(result.size(), true);
			}
			
			
		}

}
