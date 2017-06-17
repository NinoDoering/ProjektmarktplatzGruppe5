package de.hdm.itprojekt.shared;
import de.hdm.itprojekt.server.db.*;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
//Rueckgaengig
//ZweiterVersuch!
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	
	
// 	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	
	
//	void showPersonByKey(int idPerson, AsyncCallback<Person> callback);
//
//	void showPersonByKey(Person personKeyFromDBServer, AsyncCallback<String> callback);
//
//
//
//	void getProjektmarktplatzAll(AsyncCallback<Vector<Projektmarktplatz>> callback);

	

	void findPersonByKey(int key, AsyncCallback<Person> callback);
	
	//-------Ausschreibung-----
	
	void anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung, Date endDatum,
			int idPartnerprofil, Status ausschreibungsstatus, AsyncCallback<Ausschreibung> callback);
	
	void findAusschreibungByKey (int idAusschreibung, AsyncCallback<Ausschreibung> callback); 
	
	//-------Marktplatz-----

	void getAllMarktplaetze( AsyncCallback<Vector<Marktplatz>> callback);
	
	
	void anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung, AsyncCallback<Marktplatz> callback);
	
	void loeschenMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);
	
	void getMarktplatzById(int idMarktplatz, AsyncCallback<Marktplatz> callback);

	void saveMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);

	void get1Marktplatz(AsyncCallback<Marktplatz> callback);

	
	void getProjektbyMarktplatz(Marktplatz pm, AsyncCallback<Vector<Projekt>> callback);

	void getAusschreibungByProjekt(Projekt p, AsyncCallback<Vector<Ausschreibung>> callback);

	
	//-----Projekte----
	
	void anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum,
			Date endDatum, AsyncCallback<Projekt> callback);

	//-----Bewerbungen-----
	void getAllBewerbungen(AsyncCallback<Vector<Bewerbung>> callback);

	
	

	// void addProjektmarktplatz(String bezeichnung, AsyncCallback<Projektmarktplatz> callback);

	


	
	// mm erneut wichtig!!!!
}
