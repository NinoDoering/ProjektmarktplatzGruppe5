package de.hdm.itprojekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.*;

/**
 * Klasse mit Eigenschaften und Diensten, die fuer alle Client-seitigen Klassen
 * relevant sind.
 * 
 * @author Thies
 * 
 */
public class ClientSideSettings extends CommonSettings {

	// private static ProjektmarktplatzVerwaltungAsync
	// projektmarktplatzVerwaltung = null;

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
	 * Dienst namens <code>ReportGenerator</code>.
	 * @author Thies
	 */
	private static ReportGeneratorAsync reportGenerator = null;

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
	 * Dienst namens <code>LoginService</code>.
	 * @author Thies
	 */
	private static LoginServiceAsync loginService = null;

	/**
	   * Name des Client-seitigen Loggers.
	   */
	private static final String LOGGER_NAME = "Projektmarktplatz Web Client";

	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen
	 * Dienst namens <code>GreetingService</code>.
	 * @author Thies
	 */
	private static GreetingServiceAsync gwtproxy2 = null;

	/**
	 * Instanz des Client-seitigen Loggers.
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 *  Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
	 * @return die Logger-Instanz fuer die Server-Seite
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * Durch die Methode wird der LoginService erstellt, sofern dieser noch nicht besteht.
	 * Bei erneutem Aufruf der Methode wird das bereits angelegte Objekt zurückgegeben.
	 * 
	 * @return eindeutige Instanz des Typs <code>LoginServiceAsync</code>
	 */
	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}


	/**
	 * Durch die Methode wird der GreetingService erstellt, sofern dieser noch nicht besteht.
	 * Bei erneutem Aufruf der Methode wird das bereits angelegte Objekt zurückgegeben.
	 * 
	 * @return eindeutige Instanz des Typs <code>GreetingServiceAsync</code>
	 */
	public static GreetingServiceAsync getMarktplatzVerwaltung() {
		// Falls bis jetzt noch keine PMV Instanz bestand
		if (gwtproxy2 == null) {
			gwtproxy2 = GWT.create(GreetingService.class);
		}

		return gwtproxy2;
	}

	/**
	 * Durch die Methode wird der ReportGenerator erstellt, sofern dieser noch nicht besteht.
	 * Bei erneutem Aufruf der Methode wird das bereits angelegte Objekt zurückgegeben.
	 * 
	 * @return eindeutige Instanz des Typs <code>ReportGeneratorAsync</code>
	 */
	public static ReportGeneratorAsync getReportGenerator() {

		if (reportGenerator == null) {
			reportGenerator = GWT.create(ReportGenerator.class);

			reportGenerator.init(new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(Void result) {
				}
			});

			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientSideSettings.getLogger().severe("nicht initialisiert!");
					Window.alert(caught.toString());
				}

				@Override
				public void onSuccess(Void result) {
					ClientSideSettings.getLogger().info("initialisiert!");

				}

			};
			reportGenerator.init(initReportGeneratorCallback);
		}

		return reportGenerator;
	}
}