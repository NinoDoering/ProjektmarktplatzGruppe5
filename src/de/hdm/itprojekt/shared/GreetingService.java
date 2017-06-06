package de.hdm.itprojekt.shared;
import de.hdm.itprojekt.server.db.*;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

//Rueckgaengig
//zweiterVersuch

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService  {
	
	

	
	

	

	// --------Person------	
	Person findPersonByKey(int key);
	
	
	//--------Ausschreibung------	
	Ausschreibung findAusschreibungByKey(int idAusschreibung);
	
	//---------Marktplatz-----
	
	Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung)
				throws IllegalArgumentException;

	
	

}
