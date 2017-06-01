package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import de.hdm.itprojekt.server.db.*;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.*;

@RemoteServiceRelativePath("projektmarktplatzdministration")
public interface ProjektmarktplatzAdministration extends RemoteService {

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
	

	public Projekt anlegenProjekt(/*idAusschreibender??*/ int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum, Date endDatum)
			throws IllegalArgumentException;



	public void loeschenProjekt(Projekt p) throws IllegalArgumentException;

	public Projekt getProjektbyId (int idProjekt) throws IllegalArgumentException;
	
	public void saveProjekt(Projekt p) throws IllegalArgumentException;

	
	
	
	// Ausschreibung a
	
	public Ausschreibung anlegenAusschreibung(int idProjekt, String bezeichnung, String beschreibung, Date endDatum)
			throws IllegalArgumentException;



	public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	public Ausschreibung getAusschreibungbyId (int idAusschreibung) throws IllegalArgumentException;
	
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException;

	
	// Partnerprofil pp
	
	public Partnerprofil anlegenPartnerprofil(int idAusschreibung, int idOrganisationseinheit)
			throws IllegalArgumentException;


	public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;

	public Partnerprofil getPartnerprofilbyId (int idPartnerprofil)throws IllegalArgumentException;
	
	public void savePartnerprofil(Partnerprofil pp)throws IllegalArgumentException;

	
	// Bewerbung b
	
	public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung , String bewerbungstext, Date erstellDatum)
			throws IllegalArgumentException;



	public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException;

	public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException;
	
	public void saveBewerbung (Bewerbung b)throws IllegalArgumentException;

	
	// Eigenschaft e
	
	public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String sprachkenntnisse) throws IllegalArgumentException;


	public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException;

	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException;
	
	public void saveEigenschaft (Eigenschaft e)throws IllegalArgumentException;
	

	// Bewertung bewertung
	
	public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, double fliessKommaBewertung);


	public void loeschenBewertung(Bewertung bewertung)throws IllegalArgumentException;

	public Bewertung getBewertungById (int idBewertung) throws IllegalArgumentException;
	
	public void saveBewertung (Bewertung bewertung)throws IllegalArgumentException;
	

	// Beteiligung beteiligung
	
	public Beteiligung anlegenBeteiligung(int idOrganisationseinheit, int idProjekt, int idBewertung)
			throws IllegalArgumentException;



	public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException;

	public Beteiligung getBeteiligungById (int idBeteiligung) throws IllegalArgumentException;
	
	public void saveBeteiligung (Beteiligung beteiligung) throws IllegalArgumentException;

	

	// Person pe
	
	public Person anlegenPerson(int idOrganisationseinheit, int idPartnerprofil, char geschlecht, String vorname, String nachname)
			throws IllegalArgumentException;

	public void loeschenPerson(Person pe) throws IllegalArgumentException;

	public Person getPersonById(int idPerson) throws IllegalArgumentException;
	
	public void savePerson (Person pe)throws IllegalArgumentException;
	

	// Team t
	
	public Team anlegenTeam(int idOrganisationseinheit, int idPartnerprofil, String teamName, int mitgliederAnzahl) throws IllegalArgumentException;

	public void loeschenTeam(Team t) throws IllegalArgumentException;

	public Team getTeamById(int idTeam) throws IllegalArgumentException;
	
	public void saveTeam (Team t) throws IllegalArgumentException;

	
	// Unternehmen u
	
	public Unternehmen anlegenUnternehmen(int idOrganisationseinheit, int idPartnerprofil, String firmenName) throws IllegalArgumentException;

	public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException;

	public Unternehmen getUnternehmenById (int idUnternehmen);
	
	public void saveUnternehmen (Unternehmen u) throws IllegalArgumentException;
	
	

	// Getter-By-All....Aufrufe und Anforderungen definieren 
	
	//Anforderung 3. Aufruf aller Ausschreibungen
	public Vector<Ausschreibung> getAllAusschreibungenByOrganisationseinheit (Organisationseinheit o) throws IllegalArgumentException;

	//4. Alle Ausschreibungen die zum Partnerprofil passen
	public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp)throws IllegalArgumentException;

	//5. und 7. Aufruf vom Projektleiter nach allen Bewerbungen auf die Ausschreibungen die er erstellt hat
	public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException;
	
	//Aufruf der eigenen Bewerbungen als Bewerber und dazugeh�rigen Ausschreibungen

	public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheut(Organisationseinheit o) throws IllegalArgumentException;
	
	// 7. Aufruf von Beteiligungen eines Bewerbers aus Sicht des Projektleiters
	public Vector <Beteiligung> getAllBeteiligungenToProject(Projekt p)throws IllegalArgumentException;

//neu

}
