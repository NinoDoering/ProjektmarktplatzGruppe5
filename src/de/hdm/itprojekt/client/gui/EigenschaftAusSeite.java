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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.itprojekt.client.Navigator;
import de.hdm.itprojekt.client.Showcase;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public class EigenschaftAusSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Ausschreibung a1 = new Ausschreibung();
	private Projekt p1 = new Projekt();
	private Marktplatz m1 = new Marktplatz();
	private Eigenschaft eig = new Eigenschaft();
	private Person projektLeiter = new Person();
	private RoleManagement rm = null;
	private Navigator navi = null;
	CellTable<Eigenschaft> eigenschafttabelle = new CellTable<Eigenschaft>();
	CellTable<Ausschreibung>	pptabelle = new CellTable<Ausschreibung>();
	private HorizontalPanel hpanelEigenschaft = new HorizontalPanel();
	private VerticalPanel vpanelEigenschaft = new VerticalPanel(); 
	private HorizontalPanel beforeHere = new HorizontalPanel();
	private Button backToAusschreibung = new Button ("Zurück zu den eigenerstellten Ausschreibungen");
	private Button backToAusschreibung1 = new Button ("Zurück zum Projektmarktplatz");
	public EigenschaftAusSeite() {
		// TODO Auto-generated constructor stub
	}
 
	public EigenschaftAusSeite(Marktplatz m1, Ausschreibung a1, Projekt p1, final RoleManagement rm, final Navigator navi){
		this.m1=m1;
		this.a1=a1;
		this.p1 =p1;
		this.rm=rm;
		this.navi=navi;
		Label lblAUsschreibung = new Label("Sie befinden sich auf den Qualifikationen folgender Ausschreibung: " +a1.getBezeichnung());
		beforeHere.add(lblAUsschreibung);
	}
	
	
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Eigenschaft folgender Ausschreibung <h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		RootPanel.get("Anzeige").setWidth("100%");
		eigenschafttabelle.setWidth("100%", true);
		eigenschafttabelle.setStylePrimaryName("celltable");
		vpanelEigenschaft.add(eigenschafttabelle);
		hpanelEigenschaft.add(backToAusschreibung);
		hpanelEigenschaft.add(backToAusschreibung1);
		
		this.add(beforeHere);
		this.add(hpanelEigenschaft);
		this.add(vpanelEigenschaft);
		
		
		
		
		TextColumn<Ausschreibung> ausschrBez = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
	
		TextColumn<Ausschreibung> ppID = new TextColumn<Ausschreibung>(){
	
			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getIdPartnerprofil()+"";
			}
		
		
	};
	
		
	
		TextColumn<Eigenschaft> arbeitsgebiet = new TextColumn<Eigenschaft>(){
			
			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getArbeitsgebiet();
			}
		
		
	};
		TextColumn<Eigenschaft> ausbildung = new TextColumn<Eigenschaft>(){
			
			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getAusbildung();
			}
		
		
	};
	
		TextColumn<Eigenschaft> berufserfahrungsjahre = new TextColumn<Eigenschaft>(){
			
			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getBerufserfahrungsJahre()+"";
			}
		
		
	};
		
		TextColumn<Eigenschaft> sprachkenntnisse = new TextColumn<Eigenschaft>(){
			
			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getSprachkenntnisse();
			}
		
		
	};
	
		TextColumn<Eigenschaft> employmentStatus = new TextColumn<Eigenschaft>(){
			
			@Override
			public String getValue(Eigenschaft object) {
				// TODO Auto-generated method stub
				return object.getEmploymentStatus();
			}
		
		
	};
	
	TextColumn<Eigenschaft> abschluss = new TextColumn<Eigenschaft>(){
		
		@Override
		public String getValue(Eigenschaft object) {
			// TODO Auto-generated method stub
			return object.getAbschluss();
		}
	
	
};

		
		eigenschafttabelle.addColumn(arbeitsgebiet, "Arbeitsgebiet");
		eigenschafttabelle.addColumn(ausbildung,"Erfordeliche Ausbildung: ");
		eigenschafttabelle.addColumn(berufserfahrungsjahre, "gewünschte Berufserfahrungsjahre");
		eigenschafttabelle.addColumn(sprachkenntnisse, "Erfordeliche Sprachkenntnisse");
		eigenschafttabelle.addColumn(employmentStatus, "Arbeitszeit");
		eigenschafttabelle.addColumn(abschluss, "Abschluss");						
		gwtproxy.getEigenschaftByIdPartnerprofil(a1.getIdPartnerprofil(), new getEignschaftausDB());
		
		//Clickhandler 
		
		backToAusschreibung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new EigeneAusschreibungen(a1, eig, rm, navi, p1, m1);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
			}
		});
		
	backToAusschreibung1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Showcase showcase = new ProjektmarktplatzSeite(rm, navi);
				RootPanel.get("Anzeige").clear();
				RootPanel.get("Anzeige").add(showcase);
				
			}
		});
		
	}
	
	
	
		private class getEignschaftausDB implements AsyncCallback<Vector<Eigenschaft>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("leider etwas schief gelaufen");
			}

			@Override
			public void onSuccess(Vector<Eigenschaft> result) {
				eigenschafttabelle.setRowData(0, result);
				eigenschafttabelle.setRowCount(result.size(), true);
				
				
			}

		} }
	
		
