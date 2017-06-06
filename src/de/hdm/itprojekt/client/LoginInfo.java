package de.hdm.itprojekt.client;
import java.io.Serializable;
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;

	public boolean isLoggedIn(){
		return loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
}
}
