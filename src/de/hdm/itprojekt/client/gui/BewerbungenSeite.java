package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.gui.DialogBoxProjektmarktplatzAnlegen;
import de.hdm.itprojekt.client.gui.ProjekteSeite;
import de.hdm.itprojekt.client.gui.ProjektmarktplatzSeite.getProjektmarktplatzAusDB;
import de.hdm.itprojekt.server.*;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.*;

public class BewerbungenSeite extends Showcase{
//	VerticalPanel vpanel = new VerticalPanel();
	private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Bewerbung> bewerbungentabelle = new CellTable<Bewerbung>();
	
	HorizontalPanel hpanelBewerbung = new HorizontalPanel();
	VerticalPanel vpanelBewerbung = new VerticalPanel();
	
	private Bewerbung b1 = new Bewerbung();
	
	final SingleSelectionModel<Bewerbung> ssmalleBerwerbungen = new SingleSelectionModel<Bewerbung>();
	
	String deleteBewerbung = new String("L�schen");
	Button deleteBew	 = new Button();
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Meine Bewerbungen</h1>";
	}
	

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
RootPanel.get("Anzeige").setWidth("100%");
		
		bewerbungentabelle.setWidth("100%", true);
		
		vpanelBewerbung.add(bewerbungentabelle);
		//hpanelBewerbung.add(anlegenbutton);
		this.add(hpanelBewerbung);
		this.add(vpanelBewerbung);
		
		bewerbungentabelle.setSelectionModel(ssmalleBerwerbungen);
		
		ssmalleBerwerbungen.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				// TODO Auto-generated method stub
				//m1 = ssmalleBerwerbungen.getSelectedObject();
				//Showcase showcase = new ProjekteSeite(b1);
				//RootPanel.get("Anzeige").clear();
				//RootPanel.get("Anzeige").add(showcase);
				Window.alert("erstmal nix");
		}
		});
		
		TextColumn<Bewerbung> bewerbungtabellebewerbungstext = new TextColumn<Bewerbung>(){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getBewerbungsText();
			}
			
		};
		
		TextColumn<Bewerbung> bewerbungtabellestatus = new TextColumn<Bewerbung>(){

			@Override
			public String getValue(Bewerbung object) {
				// TODO Auto-generated method stub
				return object.getBewerbungsStatus().toString2();
			}
			
		};
	
		bewerbungentabelle.addColumn(bewerbungtabellebewerbungstext, "Bewerbungstext");
		bewerbungentabelle.addColumn(bewerbungtabellestatus, "Bewerbungsstatus");
		gwtproxy.getAllBewerbungen(new getBewerbungenAusDB());
		//gwtproxy.getBewerbungByBewerber(o, callback);
		
		
		deleteBew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("erstmal nix");
			}
		});
	}
	public class getBewerbungenAusDB implements AsyncCallback<Vector<Bewerbung>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Hat nicht funktioniert");
		}

		public void onSuccess(Vector<Bewerbung> result) {
			bewerbungentabelle.setRowData(0, result);
			bewerbungentabelle.setRowCount(result.size(), true);
		}
		
	}
	
	
	
	}
	
	
	
	
	
/*	
	public ActivityBewerbungen(){
		final ActivityBewerbungen as = this;
		
//		alleBewerbungen = new Vector<AnzeigeB>();
		lbltest = new Label();
				
				Bewerbung b = new Bewerbung();
				b.setId(1);
				gwtproxy = GWT.create(GreetingService.class);
				gwtproxy.getAllBewerbungen(new AsyncCallback<Vector<Bewerbung>>(){
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Vector<Bewerbung> result) {
											
						Window.alert("Funktioniert");
						
						clear();
					
						for(Bewerbung b : result){
							AnzeigeB anzeigeb = new  AnzeigeB();
							
							anzeigeb.headerA.setText("Sie befinden sich auf folgender Bewerbung:  ");
							anzeigeb.lblId.setText("ID: "+b.getId());
							anzeigeb.lblBewerbungsStatus.setText("Bewerbungsstatus: "+b.getBewerbungsStatus());
							anzeigeb.ID = b.getId();
							anzeigeb.setStyleName("subb");
							add(anzeigeb);
							
							
					}
					}
					
				});
			
		
	
}



}*/
