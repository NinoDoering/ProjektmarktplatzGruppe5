package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.server.db.*;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
//import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	/**
	 * Initialisierung des Objekts. Nach der Instantiierung muss diese Methode aufgerufen werden.
	 * @throws IllegalArgumentException
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#init();
	 */
	public void init() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByBeteiligung(Beteiligung);
	 */
	public Projekt getProjektByBeteiligung(Beteiligung b) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByPartnerprofilId(Partnerprofil);
	 */
	public Ausschreibung getAusschreibungByPartnerprofilId(Partnerprofil p) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getpartnerprofilIdbyAusschreibung(Ausschreibung);
	 */
	public Ausschreibung getpartnerprofilIdbyAusschreibung(Ausschreibung a) throws IllegalArgumentException;


	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenMarktplatz(String, String);
	 */
	public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenMarktplatz(Marktplatz);
	 */
	public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException;;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getMarktplatzById(int);
	 */
	public Marktplatz getMarktplatzById(int idMarktplatz) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveMarktplatz(Marktplatz);
	 */
	public void saveMarktplatz(Marktplatz pm) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllMarktplaetze();
	 */
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getMarktplaetzeByPerson(Organisationseinheit);
	 */
	public Vector<Marktplatz> getMarktplaetzeByPerson(Organisationseinheit o) throws IllegalArgumentException;

	/*##########################################################
	 * START PROJEKT
	 #########################################################*/


	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenProjekt(int, int, String, String, Date, Date);
	 */
	public Projekt anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung,
			Date startDatum, Date endDatum) throws IllegalArgumentException;


	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenProjekt(Projekt);
	 */
	public void loeschenProjekt(Projekt p) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektbyId(int);
	 */
	public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveProjekt(Projekt);
	 */
	public void saveProjekt(Projekt p) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByPerson(Organisationseinheit);
	 */
	public Vector<Projekt> getProjektByPerson(Organisationseinheit o) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllProjekte();
	 */
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByBezeichnung(String);
	 */
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektbyMarktplatz(Marktplatz);
	 */
	public Vector<Projekt> getProjektbyMarktplatz(Marktplatz pm) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getProjektByAusschreibung(Ausschreibung);
	 */
	public Projekt getProjektByAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	
	/*##########################################################
	 * START AUSSCHREIBUNG
	 #########################################################*/


	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenAusschreibung(int, int, String, String, Date, int, Status);
	 */
	public Ausschreibung anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung,
			String beschreibung, Date endDatum, int idPartnerprofil, Status ausschreibungsstatus)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenAusschreibung(Ausschreibung);
	 */
	public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungbyId(int);
	 */
	public Ausschreibung getAusschreibungbyId(int idAusschreibung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveAusschreibung(Ausschreibung);
	 */
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByProjekt(Projekt);
	 */
	public Vector<Ausschreibung> getAusschreibungByProjekt(Projekt p) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByAusschreibender(Organisationseinheit);
	 */
	public Vector<Ausschreibung> getAusschreibungByAusschreibender(Organisationseinheit o)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAusschreibungByBewerbung(Bewerbung);
	 */
	public Ausschreibung getAusschreibungByBewerbung(Bewerbung b) throws IllegalArgumentException;

	/*##########################################################
	 * START AUSSCHREIBUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenPartnerprofil();
	 */
	public Partnerprofil anlegenPartnerprofil() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenPartnerprofil(Partnerprofil);
	 */
	public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilbyId(int);
	 */
	public Partnerprofil getPartnerprofilbyId(int idPartnerprofil) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePartnerprofil(Partnerprofil);
	 */
	public void savePartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftByPartnerprofil(Partnerprofil);
	 */
	public Vector<Eigenschaft> getEigenschaftByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllPartnerprofile();
	 */
	public Vector<Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilByOrganisationseinheit(Organisationseinheit);
	 */
	public Partnerprofil getPartnerprofilByOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftByIdPartnerprofil(int);
	 */
	public Vector<Eigenschaft> getEigenschaftByIdPartnerprofil(int idPartnerprofil) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPartnerprofilByAusschreibung(Ausschreibung);
	 */
	public Partnerprofil getPartnerprofilByAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	/*##########################################################
	 * START BEWERBUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBewerbung(int, int, String, Date, String);
	 */
	public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
			Date erstellDatum, String status) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBewerbung(Bewerbung);
	 */
	public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungbyId(int);
	 */
	public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBewerbung(Bewerbung);
	 */
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByBewerber(Person);
	 */
	public Vector<Bewerbung> getBewerbungByBewerber(Person p) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByBewerber(Organisationseinheit);
	 */
	public Vector<Bewerbung> getBewerbungByBewerber(Organisationseinheit o) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungen();
	 */
	public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByAusschreibung(Ausschreibung);
	 */
	public Vector<Bewerbung> getBewerbungByAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewerbungByAusschreibungId(int);
	 */
	public Vector<Bewerbung> getBewerbungByAusschreibungId(int idAusschreibung) throws IllegalArgumentException;

	/*##########################################################
	 * START EIGENSCHAFT
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenEigenschaft(int, String, String, String, String, String, String);
	 */
	public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, String berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenEigenschaft(Eigenschaft);
	 */
	public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getEigenschaftById(int);
	 */
	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveEigenschaft(Eigenschaft);
	 */
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveEigenschaftnachPP(Eigenschaft);
	 */
	void saveEigenschaftnachPP(Eigenschaft e) throws IllegalArgumentException;

	/*##########################################################
	 * START BEWERTUNG
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBewertung(int, String, float);
	 */
	public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBewertung(Bewertung);
	 */
	public void loeschenBewertung(Bewertung bewertung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewertungById(int);
	 */
	public Bewertung getBewertungById(int idBewertung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBewertung(Bewertung);
	 */
	public void saveBewertung(Bewertung bewertung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBewertungByBewerbung(Bewerbung);
	 */
	public Bewertung getBewertungByBewerbung(Bewerbung b) throws IllegalArgumentException;

	// public Beteiligung anlegenBeteiligung(int beteiligungszeit, int
	// idOrganisationseinheit, int idProjekt, int idBewertung)
	// throws IllegalArgumentException;
	

	/*##########################################################
	 * START BETEILIGUNG
	 #########################################################*/
	
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenBeteiligung(Beteiligung)
	 */
	Beteiligung anlegenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenBeteiligung(Beteiligung);
	 */
	public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungById(int);
	 */
	public Beteiligung getBeteiligungById(int idBeteiligung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveBeteiligung(Beteiligung);
	 */
	public void saveBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByBeteiligter(Organisationseinheit);
	 */
	public Vector<Beteiligung> getBeteiligungByBeteiligter(Organisationseinheit o) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBeteiligungen();
	 */
	public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByBewertung(Bewertung);
	 */
	public Beteiligung getBeteiligungByBewertung(Bewertung bewertung) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getBeteiligungByProjekt(Projekt);
	 */
	public Vector<Beteiligung> getBeteiligungByProjekt(Projekt p) throws IllegalArgumentException;

	
	/*##########################################################
	 * START PERSON
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenPerson(int, int, int, String, String, String, String, String, String);
	 */
	public Person anlegenPerson(int idTeam, /* Integer ganz */int idUnternehmen, int idPartnerprofil, String vorname,
			String nachname, String titel, String emailAddresse, String standort, String adresse)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenPerson(Person);
	 */
	public void loeschenPerson(Person pe) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getPersonById(int);
	 */
	public Person getPersonById(int idPerson) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePerson(Person);
	 */
	public void savePerson(Person pe) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllPersons();
	 */
	public Vector<Person> getAllPersons() throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#savePersonPers(Person);
	 */
	public Person savePersonPers(Person pe) throws IllegalArgumentException;

	/*##########################################################
	 * START TEAM
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenTeam(int, int, String, String, String);
	 */
	public Team anlegenTeam(int idUnternehmen, int idPartnerprofil, String teamName,
			String adresse, String standort) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenTeam(Team);
	 */
	public void loeschenTeam(Team t) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenTeamInteger(Integer);
	 */
	public void loeschenTeamInteger(Integer t) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getTeamById(int);
	 */
	public Team getTeamById(int idTeam) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveTeam(Team);
	 */
	public void saveTeam(Team t) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveTeamt(Team);
	 */
	public Team saveTeamt(Team t) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllTeams();
	 */
	public Vector<Team> getAllTeams() throws IllegalArgumentException;

	
	/*##########################################################
	 * START UNTERNEHMEN
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#anlegenUnternehmen(int, String, String, String);
	 */
	public Unternehmen anlegenUnternehmen(/* Integer ganz ausgeschrieben?? */int idPartnerprofil, String firmenName,
			String adresse, String standort) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenUnternehmen(Unternehmen);
	 */
	public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException;

//	/**
//	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#loeschenUnternehmenInteger(Integer);
//	 */
//	public void loeschenUnternehmenInteger(Integer u) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getUnternehmenById(int);
	 */
	public Unternehmen getUnternehmenById(int idUnternehmen) throws IllegalArgumentException;;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#saveUnternehmen(Unternehmen);
	 */
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getUnternehmenByFirmenName(String);
	 */
	public Unternehmen getUnternehmenByFirmenName(String firmenName) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllUnternehmen();
	 */
	public Vector<Unternehmen> getAllUnternehmen() throws IllegalArgumentException;

	/*##########################################################
	 * START ORGANISATIONSEINHEIT
	 #########################################################*/

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getOrganisationseinheitById(int);
	 */
	public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllOrganisationseinheiten();
	 */
	public Vector<Organisationseinheit> getAllOrganisationseinheiten() throws IllegalArgumentException;

	// Getter-By-All....Aufrufe und Anforderungen definieren

	// Anforderung 3. Aufruf aller Ausschreibungen
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllAusschreibungen();
	 */
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException;

	// 4. Alle Ausschreibungen die zum Partnerprofil passen
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllAusschreibungByPartnerprofil(Partnerprofil);
	 */
	public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	// 5. und 7. Aufruf vom Projektleiter nach allen Bewerbungen auf die
	// Ausschreibungen die er erstellt hat
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungenByAusschreibung(Ausschreibung);
	 */
	public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	// Aufruf der eigenen Bewerbungen als Bewerber und dazugehoerigen
	// Ausschreibungen
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBewerbungenByOrganisationseinheit(Organisationseinheit);
	 */
	public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException;

	// 7. Aufruf von Beteiligungen eines Bewerbers aus Sicht des Projektleiters
	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#getAllBeteiligungenToProjekt(Vector);
	 */
	public Vector<Projekt> getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung)
			throws IllegalArgumentException;

	/**
	 * @see  de.hdm.itprojekt.server.GreetingServiceImpl#bewerbungsStatusAktualisierne(Bewerbung);
	 */
	public Bewerbung bewerbungsStatusAktualisierne(Bewerbung b);

}
