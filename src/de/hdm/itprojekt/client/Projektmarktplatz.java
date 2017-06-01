package de.hdm.itprojekt.client;
import de.hdm.itprojekt.*;
import de.hdm.itprojekt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

//Rueckgaengig
//ZweiterVersuch
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Projektmarktplatz implements EntryPoint {
	
	HorizontalPanel hpMain;
	ActivitySuchen projektmarktplatzSuchen;
	//ProjektmarktplatzErstellen projektmarktplatzErstellen;
	Button btn1;
	Button btn2;
	


	public void onModuleLoad() {


		btn1 = new Button("Projektmarktplatz suchen"); 

		btn2 = new Button ("Projektmarktplatz erstellen");
		//Button Ende
	//Panels
		
		hpMain = new HorizontalPanel();
		projektmarktplatzSuchen = new ActivitySuchen();
		//projektmarktplatzErstellen = new ProjektmarktplatzErstellen();
		//Panels Ende
		//styling
		btn1.setStyleName("btn1");
		btn2.setStyleName("btn2");
		hpMain.setStyleName("hpmain");
		//styling ende
		
		
		//Baumstruktur
		hpMain.add(btn1);	
		hpMain.add(btn2);
	

		RootPanel.get("ProjektmarktplatzSuchen").add(hpMain);
		RootPanel.get("ProjektmarktplatzErstellen").add(hpMain);


		//Baumstruktur ende
		//ClickEvents
		
		btn1.addClickHandler(new ClickHandler() {



			@Override
			public void onClick(ClickEvent event) {
				//hpMain.removeFromParent();
				hpMain.remove(btn1);
				hpMain.remove(btn2);
				hpMain.add(projektmarktplatzSuchen);

			
			}
		});
			
		btn2.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				hpMain.remove(btn1);
				hpMain.remove(btn2);
			//	hpMain.add(projektmarktplatzErstellen);
			}
			
		});
		
		//Ende Clickevents

	}
	// erneut wichtig!!!
	// teeeeeeeeest
}
