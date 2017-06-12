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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
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



//		----- Startklasse bzw. Startseite unseres Projekte
//		wird zuerst bei Start aufegrufen siehe EntryPoint


//		 unsere Vernetzung läuft wie folgt bis jetzt : 
//		1. Projektmarktplatz 
//		2.ActiviySuchen
//		3.AnzeigeM
//		4.AnzeigeP
//		5.AnzeigeA


public class Projektmarktplatz implements EntryPoint {
	
	HorizontalPanel hpMain;
	
	ActivitySuchen projektmarktplatzSuchen;
	Marktplatzanlegen marktplatzanlegen;
	
	Button btn1;
	Button btn2;
	Button btnBack;
	Button meinProfil;
	
//	----- onModuleLoad lässt das Programm bzw unsere Seite starten 
//			vergleich mar mit einer main-Methode in einem regulaeren Java projekt

	public void onModuleLoad() {

		
//		 Initialisierung der Objekte bzw Widgets auf der Startseite 
		 
		//Button Start
		btn1 = new Button("Projektmarktplatz suchen"); 

		btn2 = new Button ("Projektmarktplatz erstellen");
		
		btnBack = new Button ("Zurueck zur Startseite");
		
		meinProfil = new Button("Mein Profil");
		
		
		//Button Ende
		
		
		//Panels Start
		
		
		hpMain = new HorizontalPanel();
		
		projektmarktplatzSuchen = new ActivitySuchen();
		
		marktplatzanlegen = new Marktplatzanlegen();
		
		//Panels Ende
		
		//styling um Widget in der CSS datei zu bearbeiten 
		btn1.setStyleName("btn1");
		btn2.setStyleName("btn2");
		meinProfil.setStyleName("myProfil");
		btnBack.setStyleName("BackBtn");
		hpMain.setStyleName("hpmain");
	
		//styling ende
		
		
		//Baumstruktur 
//			dem hpMain-Mainpannel werden die Buttons hinzugefügt
		hpMain.add(btn1);	
		hpMain.add(btn2);
		
		hpMain.add(meinProfil);
		
		
		

		
		RootPanel.get("ProjektmarktplatzSuchen").add(hpMain);
		//RootPanel.get("Marktplatzerstellen").add(btnBack);
		//RootPanel.get("Marktplatzerstellen").add(vpSub);
		


		//Baumstruktur ende
		
		
		//ClickEvents bestimmen was passiert wenn auf ein Button geklickt wird 
		
		
		//
		btn1.addClickHandler(new ClickHandler() {



			@Override
			public void onClick(ClickEvent event) {
				//hpMain.removeFromParent();
				hpMain.remove(btn1);		//remove steht dafür dass bestimtme Widgets entfernt werden und mit add neue hinzugefügt werden 
				hpMain.remove(btn2);		// hpMain.add(projektmarktplatzSuchen); sorgt sozusagen dafür dass eine die neue seite aufgerufen wird 
											// siehe Klasse ActivitySuchen
				
				hpMain.add(projektmarktplatzSuchen);

			
			}
		});
			
		
		
		btn2.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				hpMain.remove(btn1);
				hpMain.remove(btn2);
				hpMain.add(marktplatzanlegen);
			}
			
		});
		
		btnBack.addClickHandler(new ClickHandler() {



			@Override
			public void onClick(ClickEvent event) {
				//hpMain.removeFromParent();
				
				Window.Location.reload();
				// Button laesst die Seite neuladen, um erneut auf die Starteite zu gelangen

			}
		});
	

	}
	 
}
