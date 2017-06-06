package de.hdm.itprojekt.client;

import com.google.gwt.user.client.ui.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.*;

public class Marktplatzerstellen extends VerticalPanel {
	private HorizontalPanel hp1;
	private HorizontalPanel hp2;
	private Button btnErstellen;
	private Label lblBezeichnung;
	private Label lblGeschaeft;
	private TextBox tbBezeichnung;
	private TextBox tbGeschaeft;
public Marktplatzerstellen(){
		hp1 = new HorizontalPanel();
		hp2 = new HorizontalPanel();
		lblBezeichnung = new Label("Marktplatz erstellen");
		lblGeschaeft = new Label("Geschäftsgebiet");
		tbBezeichnung = new TextBox();
		tbGeschaeft = new TextBox();
		hp1.add(lblGeschaeft);
		hp1.add(tbGeschaeft);
		hp2.add(lblBezeichnung);
		hp2.add(tbBezeichnung);
		add(hp1);
		add(hp2);
		add(btnErstellen);
		
}
	
}
	
	//
//	private GreetingServiceAsync gwtproxy = GWT.create(GreetingService.class);
//	
//	
//	CellTable<Marktplatz> cTableMarktplaetze = new CellTable<Marktplatz>();
//	private TextBox tBox1; 
//	private TextBox tBox2;
//	
//	private Button b1; 
//	
//	private Label l1;
//	private Label l2; 
//	
//	public void MarktplatzErstellen(){
//	
//	l1 = new Label("Erstelle einen neuen Marktplatz");
//	b1 = new Button("Marktplatz anlegen"); 
//	tBox1 = new TextBox();
//	
//	b1.addClickHandler(new ClickHandler(){
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			
//			
//			if (gwtproxy == null) {
//			     
//			      gwtproxy = GWT.create(GreetingService.class);
//			    }
//			
//			AsyncCallback<Marktplatz> callback = new AsyncCallback<Marktplatz>() {
//			
//				 public void onFailure(Throwable caught) {
//				        // TODO: Do something with errors.
//				    	  Window.alert("Fehler");
//				    }
//				
//				public void onSuccess(Marktplatz result){
//					 if (gwtproxy == null) {
//					      gwtproxy = GWT.create(GreetingService.class);
//					    }
//					 AsyncCallback<Marktplatz> callback = new AsyncCallback<Marktplatz>() {
//						 public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//								Window.alert("Fehler beim Laden der Daten aus der Datenbank");
//							}
//						 public void onSuccess(Vector<Marktplatz> result) {
//						 cTableMarktplaetze.setRowData(0, result);
//						 cTableMarktplaetze.setRowCount(result.size(), true);
//							 
//					 }
//						 
//	};
//	
//		gwtproxy.anlegenMarktplatz(geschaeftsgebiet, bezeichnung, callback);
//				
//	
//			
//	
//	
//
//	
//	




