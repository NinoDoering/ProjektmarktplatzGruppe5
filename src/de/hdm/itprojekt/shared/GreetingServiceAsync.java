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
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;


//Rueckgaengig
//ZweiterVersuch!
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	
	// Person anhand von Id ausgeben in der Gui , funktioniert 
	
	
		//void findPersonByKey(String key, AsyncCallback<Person> callback);
		
		void init(AsyncCallback<Void> callback);
		
		
		// Marktplatz --Asynch
		

		void anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung, AsyncCallback<Marktplatz> callback);

		void loeschenMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);
		
		void getMarktplatzById(int idMarktplatz, AsyncCallback<Marktplatz> callback);

		void saveMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);
		
		void getAllMarktplaetze( AsyncCallback<Vector<Marktplatz>> callback);
		
		void getMarktplaetzeByPerson(Organisationseinheit o, AsyncCallback<Vector<Marktplatz>> callback);

		
		// Projekt Asynch
		
		void anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung,
				Date startDatum, Date endDatum, AsyncCallback<Projekt> callback);

		void loeschenProjekt(Projekt p, AsyncCallback<Void> callback);
		
		void getProjektbyId(int idProjekt, AsyncCallback<Projekt> callback);

		void saveProjekt(Projekt p, AsyncCallback<Void> callback);
		
		void getProjektByPerson(Organisationseinheit o, AsyncCallback<Vector<Projekt>> callback);
		
		void getAllProjekte(AsyncCallback<Vector<Projekt>> callback);
		
		void getProjektByBezeichnung(String bezeichnung, AsyncCallback<Vector<Projekt>> callback);
		
		void getProjektbyMarktplatz(Marktplatz pm, AsyncCallback<Vector<Projekt>> callback);
		
		void getProjektByAusschreibung(Ausschreibung a, AsyncCallback<Projekt> callback);

		
		// Ausschreibung Asynch
		
		void anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung, Date endDatum,
				int idPartnerprofil, Status ausschreibungsstatus, AsyncCallback<Ausschreibung> callback);

		void loeschenAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
		
		void getAusschreibungbyId(int idAusschreibung, AsyncCallback<Ausschreibung> callback);

		void saveAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

		void getAusschreibungByProjekt(Projekt p, AsyncCallback<Vector<Ausschreibung>> callback);
		
		void getAusschreibungByAusschreibender(Organisationseinheit o, AsyncCallback<Vector<Ausschreibung>> callback);
		
		void getAusschreibungByBewerbung(Bewerbung b, AsyncCallback<Ausschreibung> callback);
		
		void getAusschreibungByPartnerprofilId(Partnerprofil p, AsyncCallback<Ausschreibung> callback);

		
		// Partnerprofil Asynch
		
		void anlegenPartnerprofil(AsyncCallback<Partnerprofil> callback);

		void loeschenPartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

		void getPartnerprofilbyId(int idPartnerprofil, AsyncCallback<Partnerprofil> callback);

		void savePartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);
		
		void getAllPartnerprofile(AsyncCallback<Vector<Partnerprofil>> callback);
		
		void getPartnerprofilByOrganisationseinheit(Organisationseinheit o, AsyncCallback<Partnerprofil> callback);
		
		void getPartnerprofilByAusschreibung(Ausschreibung a, AsyncCallback<Partnerprofil> callback);
		
		void getpartnerprofilIdbyAusschreibung(Ausschreibung a, AsyncCallback<Ausschreibung> callback);
		

		// Bewerbung Asynch
		
		void anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext, Date erstellDatum,
				BewerbungsStatus bewerbungsStatus, AsyncCallback<Bewerbung> callback);

		void loeschenBewerbung(Bewerbung b, AsyncCallback<Void> callback);

		void getBewerbungbyId(int idBewerbung, AsyncCallback<Bewerbung> callback);

		void saveBewerbung(Bewerbung b, AsyncCallback<Void> callback);

		void getBewerbungByBewerber(Person p, AsyncCallback<Vector<Bewerbung>> callback);
		
		void getBewerbungByBewerber(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);
		
		void getAllBewerbungen(AsyncCallback<Vector<Bewerbung>> callback);
		
		void getBewerbungByAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);
		
		void getBewerbungByAusschreibungId(int idAusschreibung, AsyncCallback<Vector<Bewerbung>> callback);
		
		
		// Eigenschaft Asynch	

		void anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, String berufserfahrungsJahre,
				String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse,
				AsyncCallback<Eigenschaft> callback);

		void loeschenEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

		void getEigenschaftById(int idEigenschaft, AsyncCallback<Eigenschaft> callback);

		void saveEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);
		
		void saveEigenschaftnachPP(Eigenschaft e, AsyncCallback<Void> callback);
		
		void getEigenschaftByPartnerprofil(Partnerprofil pp, AsyncCallback<Vector<Eigenschaft>> callback);
		
		void getEigenschaftByIdPartnerprofil(int idPartnerprofil, AsyncCallback<Vector<Eigenschaft>> callback);

		
		// Bewertung Asynch
		
		void anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung,
				AsyncCallback<Bewertung> callback);

		void loeschenBewertung(Bewertung bewertung, AsyncCallback<Void> callback);

		void getBewertungById(int idBewertung, AsyncCallback<Bewertung> callback);
		
		void saveBewertung(Bewertung bewertung, AsyncCallback<Void> callback);
		
		void getBewertungByBewerbung(Bewerbung b, AsyncCallback<Bewertung> callback);

		
		
		// Beteiligung Asynch
		void anlegenBeteiligung(Beteiligung beteiligung, AsyncCallback<Beteiligung> callback);
		
//		void anlegenBeteiligung(int beteiligungszeit, int idOrganisationseinheit, int idProjekt, int idBewertung,
//				AsyncCallback<Beteiligung> callback);

		void loeschenBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);

		void getBeteiligungById(int idBeteiligung, AsyncCallback<Beteiligung> callback);
		
		void saveBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);
		
		void getBeteiligungByBeteiligter(Organisationseinheit o, AsyncCallback<Vector<Beteiligung>> callback);
		
		void getAllBeteiligungen(AsyncCallback<Vector<Beteiligung>> callback);
		
		void getBeteiligungByBewertung(Bewertung bewertung, AsyncCallback<Beteiligung> callback);
		
		void getBeteiligungByProjekt(Projekt p, AsyncCallback<Vector<Beteiligung>> callback);
		

		// Person Asynch

		void anlegenPerson(int idTeam, int idUnternehmen, int idPartnerprofil, String vorname, String nachname, String titel, String emailAddresse, String standort, String adresse,
				AsyncCallback<Person> callback);

		void loeschenPerson(Person pe, AsyncCallback<Void> callback);
		
		void getPersonById(int idPerson, AsyncCallback<Person> callback);

		void savePerson(Person pe, AsyncCallback<Void> callback);
		
		void getAllPersons(AsyncCallback<Vector<Person>> callback);
		
		void savePersonPers(Person pe, AsyncCallback<Person> callback);



		// Team Asynch

		void anlegenTeam(int idUnternehmen, int idPartnerprofil, String teamName, String adresse, String standort,
				AsyncCallback<Team> callback);


		void loeschenTeam(Team t, AsyncCallback<Void> callback);
		
		void loeschenTeamInteger(Integer t, AsyncCallback<Void> callback);
		
		void getTeamById(int idTeam, AsyncCallback<Team> callback);

		void saveTeam(Team t, AsyncCallback<Void> callback);
		
		void saveTeamt(Team t, AsyncCallback<Team> callback);
		
		void getAllTeams(AsyncCallback<Vector<Team>> callback);


		// Unternehmen u
		
		void anlegenUnternehmen(int idPartnerprofil, String firmenName, String adresse, String standort, AsyncCallback<Unternehmen> callback);

		void loeschenUnternehmen(Unternehmen u, AsyncCallback<Void> callback);
		
		void loeschenUnternehmenInteger(Integer u, AsyncCallback<Void> callback);
		
		void getUnternehmenById(int idUnternehmen, AsyncCallback<Unternehmen> callback);

		void saveUnternehmen(Unternehmen u, AsyncCallback<Void> callback);
		
		void getUnternehmenByFirmenName(String firmenName, AsyncCallback <Unternehmen> callback);

		void getAllUnternehmen(AsyncCallback<Vector<Unternehmen>> callback);


		// Organisationseinheit o
		
		void getOrganisationseinheitById(int idOrganisationseinheit, AsyncCallback<Organisationseinheit> callback);
		
		void getAllOrganisationseinheiten(AsyncCallback<Vector<Organisationseinheit>> callback);


		// Anforderungen und Aufrufe definieren mit Asynch 

		void getAllAusschreibungByPartnerprofil(Partnerprofil pp, AsyncCallback<Vector<Ausschreibung>> callback);

		void getAllBewerbungenByAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);

		void getAllBewerbungenByOrganisationseinheit(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);

		void getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung, AsyncCallback<Vector<Projekt>> callback);

		void getAllAusschreibungen(AsyncCallback<Vector<Ausschreibung>> callback);

		void bewerbungsStatusAktualisierne(Bewerbung b, AsyncCallback<Bewerbung> callback);
		
		void getProjektByBeteiligung(Beteiligung b, AsyncCallback<Projekt> callback);

	}
