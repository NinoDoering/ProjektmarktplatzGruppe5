package de.hdm.itprojekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.*;



public class ClientSideSettings extends CommonSettings{

//	private static ProjektmarktplatzVerwaltungAsync projektmarktplatzVerwaltung = null;
	
	
	private static ReportGeneratorAsync reportGenerator = null;
	
	
	private static LoginServiceAsync loginService = null;
	
	private static final String LOGGER_NAME = "Projektmarktplatz Web Client";
	
	private static GreetingServiceAsync gwtproxy2 = null;
	
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	
	
	public static Logger getLogger(){
		return log;
	}
	

	public static LoginServiceAsync getLoginService(){
		if(loginService == null){
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}
	
	
	
	public static GreetingServiceAsync getMarktplatzVerwaltung(){
		//Falls bis jetzt noch keine PMV Instanz bestand
		if (gwtproxy2 == null){
			gwtproxy2 = GWT.create(GreetingService.class);
		}
		
		return gwtproxy2;
	}
	

	
	public static ReportGeneratorAsync getReportGenerator(){
		
		if(reportGenerator == null){
			reportGenerator = GWT.create(ReportGenerator.class);
			
			
			reportGenerator.init(new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
				
				}

				@Override
				public void onSuccess(Void result) {				
				}
			});
			
			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>(){

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