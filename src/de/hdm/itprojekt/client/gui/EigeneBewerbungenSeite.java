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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Person;

public class EigeneBewerbungenSeite extends Showcase{

private  GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	
	CellTable<Bewerbung> eigenebewerbungentabelle = new CellTable<Bewerbung>();
	
	HorizontalPanel hpanelBewerbung = new HorizontalPanel();
	VerticalPanel vpanelBewerbung = new VerticalPanel();
	
	private Bewerbung b1 = new Bewerbung();
	private Person p = new  Person();
	private RoleManagement rm  = null;
	private Navigator navi = null;
	
	final SingleSelectionModel<Bewerbung> ssmalleBerwerbungen = new SingleSelectionModel<Bewerbung>();
	
	private String deleteBewerbung = new String("L�schen");
	private Button deleteBew	 = new Button("Bewerbung zurueckziehen");
	private Button bewertung = new Button("Bewertung ansehen");
	
	public EigeneBewerbungenSeite(RoleManagement rm, Navigator navi) {
		// TODO Auto-generated constructor stub
		this.rm =rm;
		this.navi = navi;
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
		hpanelBewerbung.add(deleteBew);
		hpanelBewerbung.add(bewertung);
		vpanelBewerbung.add(eigenebewerbungentabelle);
		//hpanelBewerbung.add(anlegenbutton);
		this.add(hpanelBewerbung);
		this.add(vpanelBewerbung);
	
		eigenebewerbungentabelle.setSelectionModel(ssmalleBerwerbungen);
		
		ssmalleBerwerbungen.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				b1= ssmalleBerwerbungen.getSelectedObject();
			
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
		
		gwtproxy.getBewerbungByBewerber(rm.getUser(), new getEigeneBewerbung());
		
		
		deleteBew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				gwtproxy.loeschenBewerbung(b1, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Loeschen erfolgreich");
						Showcase showcase = new EigeneBewerbungenSeite(rm, navi);
						RootPanel.get("Anzeige").clear();
						RootPanel.get("Anzeige").add(showcase);
					}
				});
			}
		});
		
		bewertung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				gwtproxy.getBewertungByBewerbung(b1, new AsyncCallback<Bewertung>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Bewertung result) {
						Bewertung bewertung = result;
					if (bewertung.getIdBewerbung()!=0) {
						
						// TODO Auto-generated method stub
						final DialogBox dialogbox = new DialogBox();
						dialogbox.setText("Ihre Bewertung zu Ihrer Bewerbung");
						dialogbox.setAnimationEnabled(false);
						dialogbox.setGlassEnabled(true);
						dialogbox.center();
						FlexTable bewerttabelle = new FlexTable();
						Button ok = new Button("Ok");
						Label lbltextbewert = new Label("Ihre textuelle Bewertung: ");
						Label lblfliessbewert = new Label("Ihre Bewertung in Kommazahl: ");
						Label textBewert = new Label(result.getTextuelleBewertung());
						Label fliess = new Label(result.getFliesskommaBewertung()+"");
						dialogbox.center();
						bewerttabelle.setWidget(0, 0, lbltextbewert);
						bewerttabelle.setWidget(0, 1, textBewert);
						bewerttabelle.setWidget(1, 0, lblfliessbewert);
						bewerttabelle.setWidget(1, 1, fliess);
						bewerttabelle.setWidget(2, 0, ok);
						dialogbox.add(bewerttabelle);
						
						ok.addClickHandler(new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								dialogbox.hide();
							}
						});
						
					}
						else {
							System.out.println(("Sie haben keine Bewertung auf diese Bewerbung"));
										}
						
						
						
					
					
					//SUcces
					}
					
					
					
				});
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
	
	
	private class BewerbungAusschreibungHybrid{
		
		private int idBewerbung;
		private String bewerbungstext;
		private String bewerbungsStatus;
		
	}
	
	
	}
	

