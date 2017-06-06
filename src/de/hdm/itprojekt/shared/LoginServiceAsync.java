package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.itprojekt.client.LoginInfo;


public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	
}
