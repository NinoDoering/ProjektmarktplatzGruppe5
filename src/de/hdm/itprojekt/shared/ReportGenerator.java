package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	void init() throws IllegalArgumentException;

	void setUnternehmen(Unternehmen u) throws IllegalArgumentException;

	AllProjektenmarktplatzOfPersonReport createAllProjektenmarktplatzOfPersonReport(Person p)
			throws IllegalArgumentException;

	AllAusschreibungOfAllProjektReport createAllAusschreibungOfAllProjektReport() throws IllegalArgumentException;

	AllProjektOfAllAusschreibung createAllProjektOfAllAusschreibungReport() throws IllegalArgumentException;

	ReportSimple createReportSimpleAllAusschreibungen() throws IllegalArgumentException;

	AllAuschreibungen getAllAusschreibungen() throws IllegalArgumentException;

	AllAusschreibungenFromPartnerprofil getAllAuschreibungenFromPartnerprofil() throws IllegalArgumentException;

	AllBewerbungenFromAusschreibung getAllBewerbungenFromAusschreibungen() throws IllegalArgumentException;

	AllBewerbungenMitAusschreibungen getAllBewerbungenMitAusschreibungen() throws IllegalArgumentException;

	BeteiligungUndBewerbungen getBeteiligungAndBewerbungen() throws IllegalArgumentException;

	AnzahlBewerbungenUndAusschreibungen getAnzahlBewerbungenUndAusschreibungen() throws IllegalArgumentException;

	void setPerson(Person p) throws IllegalArgumentException;

	void setTeam(Team t) throws IllegalArgumentException;

	void setProjekt(Projekt p) throws IllegalArgumentException;

	void setPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	void setProjektmarktplatz(Marktplatz pm) throws IllegalArgumentException;

	void setAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	void setBewertung(Bewertung btg) throws IllegalArgumentException;

	void setBewerbung(Bewerbung b) throws IllegalArgumentException;

	void setBeteiligung(Beteiligung bg) throws IllegalArgumentException;

}
