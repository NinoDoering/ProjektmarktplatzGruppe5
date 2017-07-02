package de.hdm.itprojekt.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class LoginInfo implements IsSerializable {
	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String nickname;
	
	/**
	 * Abfrage, ob Nutzer angemeldet ist
	 * @return loggedIn
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Festlegen, ob ein Nutzer angemeldet ist
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	/**
	 * Auslesen der LoginUrl
	 * @return loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}
	
	/**
	 * Setzen der LoginUrl
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	/**
	 * Auslesen der LogoutUrl
	 * @return logoutUrl
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}
	
	/**
	 * Setzen der LogoutUrl
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}
	
	/**
	 * Auslesen der E-Mail-Adresse
	 * @return emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Setzen der E-Mail-Adresse
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Auslesen des Nicknamens
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}
	
	/**
	 * Setzen des Nicknamens
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}