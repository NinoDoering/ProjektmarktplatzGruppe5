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
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Partnerprofil;

public class EigenschaftAusSeite extends Showcase{

	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
	private Ausschreibung a1 = new Ausschreibung();
	CellTable<Eigenschaft> eigenschafttabelle = new CellTable<Eigenschaft>();
	private Partnerprofil pp1 = new Partnerprofil();
	private HorizontalPanel hpanelEigenschaft = new HorizontalPanel();
	private VerticalPanel vpanelEigenschaft = new VerticalPanel(); 
	
	public EigenschaftAusSeite() {
		// TODO Auto-generated constructor stub
	}
	
	public EigenschaftAusSeite(Ausschreibung a1){
		this.a1=a1;
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
		vpanelEigenschaft.add(eigenschafttabelle);
		this.add(hpanelEigenschaft);
		this.add(vpanelEigenschaft);
		
		TextColumn<Ausschreibung> ausschrBez = new TextColumn<Ausschreibung>(){

			@Override
			public String getValue(Ausschreibung object) {
				// TODO Auto-generated method stub
				return object.getBezeichnung();
			}
		
		
	};
	
		TextColumn<Partnerprofil> ppID = new TextColumn<Partnerprofil>(){
	
			@Override
			public String getValue(Partnerprofil object) {
				// TODO Auto-generated method stub
				return object.getId()+"";
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
		eigenschafttabelle.addColumn(ausbildung,"Ausbildung");
		eigenschafttabelle.addColumn(berufserfahrungsjahre, "gewünschte Berufserfahrungsjahre");
		eigenschafttabelle.addColumn(sprachkenntnisse, "Erfordeliche Sprachkenntnisse");
		eigenschafttabelle.addColumn(employmentStatus, "Arbeitszeit");
		eigenschafttabelle.addColumn(ausbildung, "Erfordeliche Ausbildung: ");
		gwtproxy.getEigenschaftByPartnerprofil(pp1, new getEignschaftausDB());
	}
		
		private class getEignschaftausDB implements AsyncCallback<Vector<Eigenschaft>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("leider etwas schief gelaufen");
			}

			@Override
			public void onSuccess(Vector<Eigenschaft> result) {
				Window.alert(" geht aber zeigt nix an");
				eigenschafttabelle.setRowData(0, result);
				eigenschafttabelle.setRowCount(result.size(), true);
			}
			
			
			
		}
	
		
}
