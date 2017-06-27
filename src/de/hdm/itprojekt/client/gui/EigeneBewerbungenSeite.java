package de.hdm.itprojekt.client.gui;

import java.util.Vector;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.objectweb.asm.tree.TryCatchBlockNode;

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
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.client.gui.BewerbungenSeite.getBewerbungenAusDB;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;

public class EigeneBewerbungenSeite extends Showcase{

private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Bewerbung> eigenebewerbungentabelle = new CellTable<Bewerbung>();
	
	HorizontalPanel hpanelBewerbung = new HorizontalPanel();
	VerticalPanel vpanelBewerbung = new VerticalPanel();
	
	private Bewerbung b1 = new Bewerbung();
	private Person p = new  Person();
	
	final SingleSelectionModel<Bewerbung> ssmalleBerwerbungen = new SingleSelectionModel<Bewerbung>();
	
	String deleteBewerbung = new String("L�schen");
	Button deleteBew	 = new Button();
	
	public EigeneBewerbungenSeite(Person p) {
		// TODO Auto-generated constructor stub
		this.p =p; 
	}



	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1>Meine Bewerbungen</h1>";
	}
	

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
RootPanel.get("Anzeige").setWidth("100%");
		
	eigenebewerbungentabelle.setWidth("100%", true);
	eigenebewerbungentabelle.setStylePrimaryName("celltable");
		
		vpanelBewerbung.add(eigenebewerbungentabelle);
		//hpanelBewerbung.add(anlegenbutton);
		this.add(hpanelBewerbung);
		this.add(vpanelBewerbung);
		
		eigenebewerbungentabelle.setSelectionModel(ssmalleBerwerbungen);
		
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
	
		eigenebewerbungentabelle.addColumn(bewerbungtabellebewerbungstext, "Bewerbungstext");
		eigenebewerbungentabelle.addColumn(bewerbungtabellestatus, "Bewerbungsstatus");
		
		gwtproxy.getBewerbungByBewerber(p, new getEigeneBewerbung());
		
		
		deleteBew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("erstmal nix");
			}
		});
	}
	
	public class getEigeneBewerbung implements AsyncCallback<Vector<Bewerbung>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void onSuccess(Vector<Bewerbung> result) {
			// TODO Auto-generated method stub
			
		
			eigenebewerbungentabelle.setRowData(0, result);
			eigenebewerbungentabelle.setRowCount(result.size(), true);
		}
		
		
	}
	
	
	
	}
	

