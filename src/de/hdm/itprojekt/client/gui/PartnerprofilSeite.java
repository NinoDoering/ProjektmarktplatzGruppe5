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
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Projekt;

public class PartnerprofilSeite extends Showcase {
	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);

	private Partnerprofil ppTest = new Partnerprofil();
	
	private int ppId = 0;
	
	public PartnerprofilSeite(Partnerprofil ppTestNr2){
		this.ppTest=ppTestNr2;
		
	}
	
	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "<h1> Partnerprofil- Test <h1>";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		gwtproxy.getPartnerprofilbyId(ppId, new getPPIdAusDB());
	}
		
	
	
	private class getPPIdAusDB implements AsyncCallback<Partnerprofil>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Partnerprofil result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
	
//CellTable<Partnerprofil> partnerprofiltabelle = new CellTable<Partnerprofil>();
//	
//	private HorizontalPanel hpanelPartnerprofil = new HorizontalPanel();
//	private VerticalPanel vpanelPartnerprofil = new VerticalPanel();
//	private Ausschreibung a1 = new Ausschreibung();
//	private int ppTest = 0;
//	
//	 public PartnerprofilSeite() {
//		// TODO Auto-generated constructor stub
//	}
//	 
//	 public PartnerprofilSeite(Ausschreibung a1) {
//			// TODO Auto-generated constructor stub
//		 this.a1=a1;
//		} 
//	 
//	
//	@Override
//	protected String getHeadlineText() {
//		// TODO Auto-generated method stub
//		return "<h1>Partnerprofile durchsuchen</h1>";
//	}
//
//	@Override
//	protected void run() {
//		// TODO Auto-generated method stub
//		RootPanel.get("Anzeige").setWidth("100%");
//		partnerprofiltabelle.setWidth("100%", true);
//		vpanelPartnerprofil.add(partnerprofiltabelle);
//		this.add(hpanelPartnerprofil);
//		this.add(vpanelPartnerprofil);
//		
//		TextColumn<Partnerprofil> ppId = new TextColumn<Partnerprofil>() {
//
//			@Override
//			public String getValue(Partnerprofil object) {
//				// TODO Auto-generated method stub
//				return object.getIdAusschreibung()+"";
//				
//			}
//			
//		};
//		
//		partnerprofiltabelle.addColumn(ppId, "Testfür Nino");
//	//	gwtproxy.getPartnerprofilbyId(ppTest, new getPartnerProfilausDB());
//		gwtproxy.getAllPartnerprofile(a1,new getPartnerprofileAusDB());
//	}
//	
//		
//		private class getPartnerprofileAusDB implements AsyncCallback<Vector<Partnerprofil>>{
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert("AMK");
//			}
//
//			@Override
//			public void onSuccess(Vector<Partnerprofil> result) {
//				// TODO Auto-generated method stub
//				partnerprofiltabelle.setRowData(0, result);
//				partnerprofiltabelle.setRowCount(result.size(), true);
//				Window.alert("JAAAAMAAAAAN");
//			}
//			
//			
//			
//		}
//			
//			
//		
//
//			
//	
//}
