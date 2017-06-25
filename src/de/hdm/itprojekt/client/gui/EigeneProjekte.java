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

import de.hdm.itprojekt.client.Showcase;
import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigeneProjekte extends Showcase {

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	
	CellTable<Projekt> eigeneprojektetabelle = new CellTable<Projekt>();

	private HorizontalPanel hpanelEigeneProjekte = new HorizontalPanel();
	private VerticalPanel vpanelEigeneProjekte = new VerticalPanel();
	private Organisationseinheit o = new Organisationseinheit();
	

	 public EigeneProjekte(Organisationseinheit o1) {
		// TODO Auto-generated constructor stub
		 this.o=o1;
	}
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Ihre eigenen Projekte </h1>";
	}


	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigeneprojektetabelle.setWidth("100%", true);
		eigeneprojektetabelle.setStylePrimaryName("celltable");
		vpanelEigeneProjekte.add(eigeneprojektetabelle);
	
		this.add(vpanelEigeneProjekte);
		this.add(hpanelEigeneProjekte);
		
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
	
			eigeneprojektetabelle.addColumn(projektBez, "Bezeichnung");
			eigeneprojektetabelle.addColumn(projektBeschr, "Beschreibung");
			eigeneprojektetabelle.addColumn(projektStartD, "Startdatum");
			eigeneprojektetabelle.addColumn(projektEndD, "Enddatum");
			gwtproxy.getProjektByPerson(o, new getEigeneProjekte());
	}
	
			private class getEigeneProjekte implements AsyncCallback<Vector<Projekt>>{

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert(" getEigeneProjekte Hat nicht funktioniert"); 
				}

				@Override
				public void onSuccess(Vector<Projekt> result) {
					// TODO Auto-generated method stub
					eigeneprojektetabelle.setRowData(0, result);
					eigeneprojektetabelle.setRowCount(result.size(), true);
				}

		
				}
			}
	

