package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);



	void createAllAusschreibungenByPartnerprofilReport(Organisationseinheit o,
			AsyncCallback<AllAusschreibungenByPartnerprofilReport> callback);

	void createAllAusschreibungenReport(AsyncCallback<AllAusschreibungenReport> callback);

	void createAllBeteiligungenToProjectReport(int id, AsyncCallback<AllBeteiligungenToProjectReport> callback);

	void createAllBewerbungenByAusschreibungReport(Organisationseinheit o,
			AsyncCallback<AllBewerbungenByAusschreibungReport> callback);

	void createAllBewerbungenByOrganisationseinheitReport(int id,
			AsyncCallback<AllBewerbungenByOrganisationseinheitReport> callback);
	
	void createAllBewerbungenToOneAusschreibungReport(int idAusschreibung,
			AsyncCallback<AllBewerbungenToOneAusschreibungReport> callback);

	void createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o,
			AsyncCallback<AllBewerbungenWithAusschreibungenReport> callback);

	void createFanInFanOutReport(AsyncCallback<FanInFanOutReport> callback);

	void createFanInAnalyse(AsyncCallback<FanIn> callback);

	void findPersonByKey(int id, AsyncCallback<Person> callback);

	void findTeamByKey(int id, AsyncCallback<Team> callback);

	void findUnternehmenByKey(int id, AsyncCallback<Unternehmen> callback);

	void createProjektverflechtungenReport(int id, AsyncCallback<ProjektverflechtungReport> callback);

	void getAusschreibungByMatchingPartnerprofil(Organisationseinheit o,
			AsyncCallback<AllAusschreibungenByPartnerprofilReport> callback);

	void createFanOutAnalyse(AsyncCallback<FanOut> callback);



	
	
}
