package de.hdm.itprojekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
//Rueckgaengig
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
