package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportGeneratorAsync {

	void getAllAusschreibungen(AsyncCallback<AllAuschreibungen> callback);

	void getAllAuschreibungenFromPartnerprofil(AsyncCallback<AllAusschreibungenFromPartnerprofil> callback);

	void getAllBewerbungenFromAusschreibungen(AsyncCallback<AllBewerbungenFromAusschreibung> callback);

	void getAllBewerbungenMitAusschreibungen(AsyncCallback<AllBewerbungenMitAusschreibungen> callback);

	void getBeteiligungAndBewerbungen(AsyncCallback<BeteiligungUndBewerbungen> callback);

	void getAnzahlBewerbungenUndAusschreibungen(AsyncCallback<AnzahlBewerbungenUndAusschreibungen> callback);

	void createReportSimpleAllAusschreibungen(AsyncCallback<ReportSimple> callback);

	void createAllAusschreibungOfAllProjektReport(AsyncCallback<AllAusschreibungOfAllProjektReport> callback);

	void createAllProjektOfAllAusschreibungReport(AsyncCallback<AllProjektOfAllAusschreibung> callback);

	void createAllProjektenmarktplatzOfPersonReport(Person p,
			AsyncCallback<AllProjektenmarktplatzOfPersonReport> callback);

	void init(AsyncCallback<Void> callback);

	void setUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	void setPerson(Person p, AsyncCallback<Void> callback);

	void setTeam(Team t, AsyncCallback<Void> callback);

	void setProjekt(Projekt p, AsyncCallback<Void> callback);

	void setPartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

	void setProjektmarktplatz(Projektmarktplatz pm, AsyncCallback<Void> callback);

	void setAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	void setBewertung(Bewertung btg, AsyncCallback<Void> callback);

	void setBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void setBeteiligung(Beteiligung bg, AsyncCallback<Void> callback);

}
