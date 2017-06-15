package de.hdm.itprojekt.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.client.LoginInfo;
import de.hdm.itprojekt.shared.LoginService;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	private static final long serialVersionUID = 1L;
	
	public LoginInfo login(String requestUrl){
			UserService userService = UserServiceFactory.getUserService();
				User user = userService.getCurrentUser();
				LoginInfo logInInfo = new LoginInfo();
			
				if (user != null){
					logInInfo.setLoggedIn(true);
					logInInfo.setEmailAddress(user.getEmail());
					//logInInfo.setNickname(user.getNickname());
					logInInfo.setLogoutUrl(userService.createLogoutURL(requestUrl));
				}
				else {
					logInInfo.setLoggedIn(false);
					logInInfo.setLoginUrl(userService.createLoginURL(requestUrl));
				}
				return logInInfo;
				
				}
	}


