package de.hdm.itprojekt.client;
import de.hdm.itprojekt.server.db.*;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Person;

//Rueckgaengig
//zweiterVersuch

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService  {
	
	
	 // String greetServer(String name) throws IllegalArgumentException;
	
//	Person showPersonByKey(int idPerson);
//
//	String showPersonByKey(Person personKeyFromDBServer);
//	
//	Vector<Projektmarktplatz> getProjektmarktplatzAll(); 
	
	
	Person findPersonByKey(int key);
	
	// public Projektmarktplatz addProjektmarktplatz(String bezeichnung);
	
	// k erneut wichtig!!!!
}
