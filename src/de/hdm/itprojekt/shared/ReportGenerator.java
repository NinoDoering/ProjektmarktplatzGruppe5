package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	
	void init() throws IllegalArgumentException;
	
	void setUnternehmen(Unternehmen u) throws IllegalArgumentException;

	AllProjektenmarktplatzOfPersonReport createAllProjektenmarktplatzOfPersonReport(Person p) throws IllegalArgumentException;

	AllAusschreibungOfAllProjektReport createAllAusschreibungOfAllProjektReport() throws IllegalArgumentException;

	AllProjektOfAllAusschreibung createAllProjektOfAllAusschreibungReport() throws IllegalArgumentException;

	ReportSimple createReportSimpleAllAusschreibungen();

	AllAuschreibungen getAllAusschreibungen();

	AllAusschreibungenFromPartnerprofil getAllAuschreibungenFromPartnerprofil();

	AllBewerbungenFromAusschreibung getAllBewerbungenFromAusschreibungen();

	AllBewerbungenMitAusschreibungen getAllBewerbungenMitAusschreibungen();

	BeteiligungUndBewerbungen getBeteiligungAndBewerbungen();

	AnzahlBewerbungenUndAusschreibungen getAnzahlBewerbungenUndAusschreibungen();

	void setPerson(Person p);

	void setTeam(Team t);

	void setProjekt(Projekt p);

	void setPartnerprofil(Partnerprofil pp);

	void setProjektmarktplatz(Projektmarktplatz pm);

	void setAusschreibung(Ausschreibung a);

	void setBewertung(Bewertung btg);

	void setBewerbung(Bewerbung b);

	void setBeteiligung(Beteiligung bg);

}
