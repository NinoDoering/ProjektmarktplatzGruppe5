package de.hdm.itprojekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.server.db.PersonMapper;
//Rueckgaengig
//ZweiterVersuch!
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);

	void showPersonByKey(PersonMapper input, AsyncCallback<String> callback);




	
	// mm erneut wichtig!!!!
}
