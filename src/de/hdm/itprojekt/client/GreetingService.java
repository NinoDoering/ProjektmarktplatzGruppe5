package de.hdm.itprojekt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//Rueckgaengig
//zweiterVersuch

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	
	String findPersonByKey(int key) throws IllegalArgumentException;
	
	// k erneut wichtig!!!!
}
