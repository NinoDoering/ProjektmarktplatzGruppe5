package de.hdm.itprojekt.server;

import java.util.logging.Logger;

import de.hdm.itprojekt.shared.CommonSettings;

	public class ServersideSettings extends CommonSettings {

	private static final String LOGGER_NAME = "Projektmarktplatz Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
	}

}
