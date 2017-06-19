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
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;

//Rueckgaengig
//zweiterVersuch

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService  {
	// Diese Methode bei jeder Instantiierung verwenden, das heißt diese Methode
		// init()
		// danach verwenden, dient der Überprüng der Instanz => "throws
		// ILLEGALARGUMENTEXCEPTION" für Fehlermeldung zuständig
		public void init() throws IllegalArgumentException;

		
		// Projektmarktplatz pm
		
		
		
		public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung)throws IllegalArgumentException;;


		
		public void loeschenMarktplatz(Marktplatz pm)throws IllegalArgumentException;;
		
		public Marktplatz getMarktplatzById (int idMarktplatz) throws IllegalArgumentException; 
		
		public void saveMarktplatz (Marktplatz pm) throws IllegalArgumentException;

		
		// Projekt p	

		public Projekt anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum, Date endDatum)
				throws IllegalArgumentException;

		public void loeschenProjekt(Projekt p) throws IllegalArgumentException;

		public Projekt getProjektbyId (int idProjekt) throws IllegalArgumentException;
		
		public void saveProjekt(Projekt p) throws IllegalArgumentException;

		public Vector<Projekt> getProjektByPerson(Organisationseinheit o) throws IllegalArgumentException;
		
		public Vector<Projekt> getAllProjekte() throws IllegalArgumentException;
		
		public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException;
		
		public Vector<Projekt> getProjektbyMarktplatz(Marktplatz pm) throws IllegalArgumentException;
		
		
		// Ausschreibung a
		
		public Ausschreibung anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung, Date endDatum,
				int idPartnerprofil, Status ausschreibungsstatus)
				throws IllegalArgumentException;

		public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException;
		
		public Ausschreibung getAusschreibungbyId (int idAusschreibung) throws IllegalArgumentException;
		
		public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;
		
		public Vector<Ausschreibung> getAusschreibungByProjekt(Projekt p) throws IllegalArgumentException;

		
		// Partnerprofil pp
		
		public Partnerprofil anlegenPartnerprofil()
				throws IllegalArgumentException;


		public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

		public Partnerprofil getPartnerprofilbyId (int idPartnerprofil)throws IllegalArgumentException;
		
		public void savePartnerprofil(Partnerprofil pp)throws IllegalArgumentException;
		
		public Vector<Eigenschaft> getEigenschaftByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

		public Vector <Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException;
		
		public Partnerprofil getPartnerprofilByOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
		
		public Vector<Eigenschaft> getEigenschaftByIdPartnerprofil(int idPartnerprofil) throws IllegalArgumentException;
		
		
		// Bewerbung b
		
		public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
				Date erstellDatum, BewerbungsStatus bewerbungsStatus) throws IllegalArgumentException;


		public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException;

		public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException;
		
		public void saveBewerbung (Bewerbung b) throws IllegalArgumentException;
		
		public Vector<Bewerbung> getBewerbungByBewerber(Organisationseinheit o) throws IllegalArgumentException;
		
		public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException;
		
		public Vector<Bewerbung> getBewerbungByAusschreibung(Ausschreibung a) throws IllegalArgumentException;

		
		// Eigenschaft e
		
		public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
				String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse) throws IllegalArgumentException;


		public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException;

		public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException;
		
		public void saveEigenschaft (Eigenschaft e)throws IllegalArgumentException;
		

		// Bewertung bewertung
		
		Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung);


		public void loeschenBewertung(Bewertung bewertung)throws IllegalArgumentException;

		public Bewertung getBewertungById (int idBewertung) throws IllegalArgumentException;
		
		public void saveBewertung (Bewertung bewertung) throws IllegalArgumentException;
		
		public Bewertung getBewertungByBewerbung(Bewerbung b) throws IllegalArgumentException;

		// Beteiligung beteiligung
		
		public Beteiligung anlegenBeteiligung(int idBeteiligter, int idProjekt, int idBewertung, double beteiligungszeit)
				throws IllegalArgumentException;


		public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException;

		public Beteiligung getBeteiligungById (int idBeteiligung) throws IllegalArgumentException;
		
		public void saveBeteiligung (Beteiligung beteiligung) throws IllegalArgumentException;

		public Vector<Beteiligung> getBeteiligungByBeteiligter (Organisationseinheit o) throws IllegalArgumentException;
		
		public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException;
		
		public Beteiligung getBeteiligungByBewertung(Bewertung bewertung) throws IllegalArgumentException;
		
		public Vector<Beteiligung> getBeteiligungByProjekt(Projekt p) throws IllegalArgumentException;
		

		// Person pe
		
		public Person anlegenPerson(int idTeam, /*Integer ganz*/int idUnternehmen, int idPartnerprofil, String vorname, String nachname)
				throws IllegalArgumentException;

		public void loeschenPerson(Person pe) throws IllegalArgumentException;

		public Person getPersonById(int idPerson) throws IllegalArgumentException;
		
		public void savePerson (Person pe)throws IllegalArgumentException;
		

		// Team t
		
		public Team anlegenTeam(/*Integer ganz ausgeschrieben??*/int idUnternehmen, int idPartnerprofil, String teamName, int mitgliederAnzahl) throws IllegalArgumentException;

		public void loeschenTeam(Team t) throws IllegalArgumentException;

		public Team getTeamById(int idTeam) throws IllegalArgumentException;
		
		public void saveTeam (Team t) throws IllegalArgumentException;

		
		// Unternehmen u
		
		public Unternehmen anlegenUnternehmen(/*Integer ganz ausgeschrieben??*/int idPartnerprofil, String firmenName) throws IllegalArgumentException;

		public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException;

		public Unternehmen getUnternehmenById (int idUnternehmen);
		
		public void saveUnternehmen (Unternehmen u) throws IllegalArgumentException;
		
		public Vector<Unternehmen> getUnternehmenByFirmenName(String firmenName) throws IllegalArgumentException;
		

		// Organisationseinheit
		
		public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) throws IllegalArgumentException;
		
		

		// Getter-By-All....Aufrufe und Anforderungen definieren
		
		//Anforderung 3. Aufruf aller Ausschreibungen
		public Vector<Ausschreibung> getAllAusschreibungen () throws IllegalArgumentException;

		//4. Alle Ausschreibungen die zum Partnerprofil passen
		public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp)throws IllegalArgumentException;

		//5. und 7. Aufruf vom Projektleiter nach allen Bewerbungen auf die Ausschreibungen die er erstellt hat
		public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException;
		
		//Aufruf der eigenen Bewerbungen als Bewerber und dazugehoerigen Ausschreibungen
		public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException;
		
		// 7. Aufruf von Beteiligungen eines Bewerbers aus Sicht des Projektleiters
		public Vector<Projekt> getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung) throws IllegalArgumentException;

	}
