package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void setPerson(AsyncCallback<Void> callback);

	void createAllAusschreibungenByPartnerprofilReport(Partnerprofil pp,
			AsyncCallback<AllAusschreibungenByPartnerprofilReport> callback);

	void createAllAusschreibungenReport(AsyncCallback<AllAusschreibungenReport> callback);

	void createAllBeteiligungenToProjectReport(AsyncCallback<AllBeteiligungenToProjectReport> callback);

	void createAllBewerbungenByAusschreibungReport(AsyncCallback<AllBewerbungenByAusschreibungReport> callback);

	void createAllBewerbungenByOrganisationseinheitReport(
			AsyncCallback<AllBewerbungenByOrganisationseinheitReport> callback);

	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);

	void createFanInAnalyse(Organisationseinheit o, AsyncCallback<FanIn> callback);

	
	
}
