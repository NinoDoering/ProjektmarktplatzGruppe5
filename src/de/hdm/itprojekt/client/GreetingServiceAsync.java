package de.hdm.itprojekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.server.db.PersonMapper;
//Rueckgaengig
//ZweiterVersuch!
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void showPersonByKey(PersonMapper idPerson, AsyncCallback<String> callback);




	
	// mm erneut wichtig!!!!
}
