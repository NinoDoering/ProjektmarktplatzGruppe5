package de.hdm.itprojekt.client;
import de.hdm.itprojekt.server.db.*;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Person;
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

	void findPersonByKey(String key, AsyncCallback<Person> callback);



	// void addProjektmarktplatz(String bezeichnung, AsyncCallback<Projektmarktplatz> callback);

	


	
	// mm erneut wichtig!!!!
}
