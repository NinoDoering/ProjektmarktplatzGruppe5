package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.server.db.*;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
//import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#init()
	 */
	void init(AsyncCallback<Void> callback);

	/*##########################################################
	 * START MARKTPLATZ
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenMarktplatz(String, String);
	 */
	void anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung, AsyncCallback<Marktplatz> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenMarktplatz(Marktplatz);
	 */
	void loeschenMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getMarktplatzById(int);
	 */
	void getMarktplatzById(int idMarktplatz, AsyncCallback<Marktplatz> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveMarktplatz(Marktplatz);
	 */
	void saveMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllMarktplaetze();
	 */
	void getAllMarktplaetze(AsyncCallback<Vector<Marktplatz>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getMarktplaetzeByPerson(Organisationseinheit);
	 */
	void getMarktplaetzeByPerson(Organisationseinheit o, AsyncCallback<Vector<Marktplatz>> callback);

	/*##########################################################
	 * START PROJEKT
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenProjekt(int, int, String, String, Date, Date);
	 */
	void anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum,
			Date endDatum, AsyncCallback<Projekt> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenProjekt(Projekt);
	 */
	void loeschenProjekt(Projekt p, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektbyId(int);
	 */
	void getProjektbyId(int idProjekt, AsyncCallback<Projekt> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveProjekt(Projekt);
	 */
	void saveProjekt(Projekt p, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByPerson(Organisationseinheit);
	 */
	void getProjektByPerson(Organisationseinheit o, AsyncCallback<Vector<Projekt>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllProjekte();
	 */
	void getAllProjekte(AsyncCallback<Vector<Projekt>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByBezeichnung(String);
	 */
	void getProjektByBezeichnung(String bezeichnung, AsyncCallback<Vector<Projekt>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektbyMarktplatz(Marktplatz);
	 */
	void getProjektbyMarktplatz(Marktplatz pm, AsyncCallback<Vector<Projekt>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByAusschreibung(Ausschreibung);
	 */
	void getProjektByAusschreibung(Ausschreibung a, AsyncCallback<Projekt> callback);

	/*##########################################################
	 * START AUSSCHREIBUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenAusschreibung(int, int, String, String, Date, int, Status);
	 */
	void anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung,
			Date endDatum, int idPartnerprofil, Status ausschreibungsstatus, AsyncCallback<Ausschreibung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenAusschreibung(Ausschreibung);
	 */
	void loeschenAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungbyId(int);
	 */
	void getAusschreibungbyId(int idAusschreibung, AsyncCallback<Ausschreibung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveAusschreibung(Ausschreibung);
	 */
	void saveAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByProjekt(Projekt);
	 */
	void getAusschreibungByProjekt(Projekt p, AsyncCallback<Vector<Ausschreibung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByAusschreibender(Organisationseinheit);
	 */
	void getAusschreibungByAusschreibender(Organisationseinheit o, AsyncCallback<Vector<Ausschreibung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByBewerbung(Bewerbung);
	 */
	void getAusschreibungByBewerbung(Bewerbung b, AsyncCallback<Ausschreibung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByPartnerprofilId(Partnerprofil);
	 */
	void getAusschreibungByPartnerprofilId(Partnerprofil p, AsyncCallback<Ausschreibung> callback);

	/*##########################################################
	 * START PARTNERPROFIL
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenPartnerprofil();
	 */
	void anlegenPartnerprofil(AsyncCallback<Partnerprofil> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenPartnerprofil(Partnerprofil);
	 */
	void loeschenPartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilbyId(int);
	 */
	void getPartnerprofilbyId(int idPartnerprofil, AsyncCallback<Partnerprofil> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePartnerprofil(Partnerprofil);
	 */
	void savePartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllPartnerprofile();
	 */
	void getAllPartnerprofile(AsyncCallback<Vector<Partnerprofil>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilByOrganisationseinheit(Organisationseinheit);
	 */
	void getPartnerprofilByOrganisationseinheit(Organisationseinheit o, AsyncCallback<Partnerprofil> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilByAusschreibung(Ausschreibung);
	 */
	void getPartnerprofilByAusschreibung(Ausschreibung a, AsyncCallback<Partnerprofil> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getpartnerprofilIdbyAusschreibung(Ausschreibung);
	 */
	void getpartnerprofilIdbyAusschreibung(Ausschreibung a, AsyncCallback<Ausschreibung> callback);

	/*##########################################################
	 * START BEWERBUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBewerbung(int, int, String, Date, String);
	 */
	void anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext, Date erstellDatum,
			String bewerbungsStatus, AsyncCallback<Bewerbung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBewerbung(Bewerbung);
	 */
	void loeschenBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungbyId(int);
	 */
	void getBewerbungbyId(int idBewerbung, AsyncCallback<Bewerbung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBewerbung(Bewerbung);
	 */
	void saveBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByBewerber(Person);
	 */
	void getBewerbungByBewerber(Person p, AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByBewerber(Organisationseinheit);
	 */
	void getBewerbungByBewerber(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungen();
	 */
	void getAllBewerbungen(AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByAusschreibung(Ausschreibung);
	 */
	void getBewerbungByAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByAusschreibungId(int);
	 */
	void getBewerbungByAusschreibungId(int idAusschreibung, AsyncCallback<Vector<Bewerbung>> callback);

	/*##########################################################
	 * START EIGENSCHAFT
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenEigenschaft(int, String, String, String, String, String, String);
	 */
	void anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, String berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse,
			AsyncCallback<Eigenschaft> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenEigenschaft(Eigenschaft);
	 */
	void loeschenEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftById(int);
	 */
	void getEigenschaftById(int idEigenschaft, AsyncCallback<Eigenschaft> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveEigenschaft(Eigenschaft);
	 */
	void saveEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveEigenschaftnachPP(Eigenschaft);
	 */
	void saveEigenschaftnachPP(Eigenschaft e, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftByPartnerprofil(Partnerprofil);
	 */
	void getEigenschaftByPartnerprofil(Partnerprofil pp, AsyncCallback<Vector<Eigenschaft>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftByIdPartnerprofil(int);
	 */
	void getEigenschaftByIdPartnerprofil(int idPartnerprofil, AsyncCallback<Vector<Eigenschaft>> callback);

	/*##########################################################
	 * START BEWERTUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBewertung(int, String, float);
	 */
	void anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung,
			AsyncCallback<Bewertung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBewertung(Bewertung);
	 */
	void loeschenBewertung(Bewertung bewertung, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewertungById(int);
	 */
	void getBewertungById(int idBewertung, AsyncCallback<Bewertung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBewertung(Bewertung);
	 */
	void saveBewertung(Bewertung bewertung, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewertungByBewerbung(Bewerbung);
	 */
	void getBewertungByBewerbung(Bewerbung b, AsyncCallback<Bewertung> callback);

	/*##########################################################
	 * START BETEILIGUNG
	 #########################################################*/
	
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBeteiligung(Beteiligung);
	 */
	void anlegenBeteiligung(Beteiligung beteiligung, AsyncCallback<Beteiligung> callback);

	// void anlegenBeteiligung(int beteiligungszeit, int idOrganisationseinheit,
	// int idProjekt, int idBewertung,
	// AsyncCallback<Beteiligung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBeteiligung(Beteiligung);
	 */
	void loeschenBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungById(int);
	 */
	void getBeteiligungById(int idBeteiligung, AsyncCallback<Beteiligung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBeteiligung(Beteiligung);
	 */
	void saveBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByBeteiligter(Organisationseinheit);
	 */
	void getBeteiligungByBeteiligter(Organisationseinheit o, AsyncCallback<Vector<Beteiligung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBeteiligungen();
	 */
	void getAllBeteiligungen(AsyncCallback<Vector<Beteiligung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByBewertung(Bewertung);
	 */
	void getBeteiligungByBewertung(Bewertung bewertung, AsyncCallback<Beteiligung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByProjekt(Projekt);
	 */
	void getBeteiligungByProjekt(Projekt p, AsyncCallback<Vector<Beteiligung>> callback);

	/*##########################################################
	 * START PERSON
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenPerson(int, int, int, String, String, String, String, String, String);
	 */
	void anlegenPerson(int idTeam, int idUnternehmen, int idPartnerprofil, String vorname, String nachname,
			String titel, String emailAddresse, String standort, String adresse, AsyncCallback<Person> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenPerson(Person);
	 */
	void loeschenPerson(Person pe, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPersonById(int);
	 */
	void getPersonById(int idPerson, AsyncCallback<Person> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePerson(Person);
	 */
	void savePerson(Person pe, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllPersons();
	 */
	void getAllPersons(AsyncCallback<Vector<Person>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePersonPers(Person);
	 */
	void savePersonPers(Person pe, AsyncCallback<Person> callback);

	/*##########################################################
	 * START TEAM
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenTeam(int, int, String, String, String);
	 */
	void anlegenTeam(int idUnternehmen, int idPartnerprofil, String teamName, String adresse, String standort,
			AsyncCallback<Team> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenTeam(Team);
	 */
	void loeschenTeam(Team t, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenTeamInteger(Integer);
	 */
	void loeschenTeamInteger(Integer t, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getTeamById(int);
	 */
	void getTeamById(int idTeam, AsyncCallback<Team> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveTeam(Team);
	 */
	void saveTeam(Team t, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveTeamt(Team);
	 */
	void saveTeamt(Team t, AsyncCallback<Team> callback);

	void getAllTeams(AsyncCallback<Vector<Team>> callback);

	/*##########################################################
	 * START UNTERNEHMEN
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenUnternehmen(int, String, String, String);
	 */
	void anlegenUnternehmen(int idPartnerprofil, String firmenName, String adresse, String standort,
			AsyncCallback<Unternehmen> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenUnternehmen(Unternehmen);
	 */
	void loeschenUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenUnternehmenInteger(Integer);
	 */
	void loeschenUnternehmenInteger(Integer u, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getUnternehmenById(int);
	 */
	void getUnternehmenById(int idUnternehmen, AsyncCallback<Unternehmen> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveUnternehmen(Unternehmen);
	 */
	void saveUnternehmen(Unternehmen u, AsyncCallback<Void> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getUnternehmenByFirmenName(String);
	 */
	void getUnternehmenByFirmenName(String firmenName, AsyncCallback<Unternehmen> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllUnternehmen();
	 */
	void getAllUnternehmen(AsyncCallback<Vector<Unternehmen>> callback);

	/*##########################################################
	 * START ORGANISATIONSEINHEIT
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getOrganisationseinheitById(int);
	 */
	void getOrganisationseinheitById(int idOrganisationseinheit, AsyncCallback<Organisationseinheit> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllOrganisationseinheiten();
	 */
	void getAllOrganisationseinheiten(AsyncCallback<Vector<Organisationseinheit>> callback);

	/*##########################################################
	 * START ANFORDERUNGEN
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllAusschreibungByPartnerprofil(Partnerprofil);
	 */
	void getAllAusschreibungByPartnerprofil(Partnerprofil pp, AsyncCallback<Vector<Ausschreibung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungenByAusschreibung(Ausschreibung);
	 */
	void getAllBewerbungenByAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungenByOrganisationseinheit(Organisationseinheit);
	 */
	void getAllBewerbungenByOrganisationseinheit(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBeteiligungenToProjekt(Vector);
	 */
	void getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung, AsyncCallback<Vector<Projekt>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllAusschreibungen();
	 */
	void getAllAusschreibungen(AsyncCallback<Vector<Ausschreibung>> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#bewerbungsStatusAktualisierne(Bewerbung);
	 */
	void bewerbungsStatusAktualisierne(Bewerbung b, AsyncCallback<Bewerbung> callback);

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByBeteiligung(Beteiligung);
	 */
	void getProjektByBeteiligung(Beteiligung b, AsyncCallback<Projekt> callback);

}
