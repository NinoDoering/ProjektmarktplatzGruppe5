package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.client.rpc.AsyncCallback;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.server.db.PersonMapper;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
//
public interface ProjektmarktplatzAdministrationAsync {

	// Person anhand von Id ausgeben in der Gui , funktioniert 
	
	
	//void findPersonByKey(String key, AsyncCallback<Person> callback);
	
	void init(AsyncCallback<Void> callback);
	
	// Marktplatz Asynch
	

	void anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung, AsyncCallback<Marktplatz> callback);
	


	void loeschenMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);
	
	void getMarktplatzById(int idMarktplatz, AsyncCallback<Marktplatz> callback);

	void saveMarktplatz(Marktplatz pm, AsyncCallback<Void> callback);



	
	// Projekt Asynch
	
	void anlegenProjekt(int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum, Date endDatum,
			AsyncCallback<Projekt> callback);


	void loeschenProjekt(Projekt p, AsyncCallback<Void> callback);
	
	void getProjektbyId(int idProjekt, AsyncCallback<Projekt> callback);

	void saveProjekt(Projekt p, AsyncCallback<Void> callback);

	
	// Ausschreibung Asynch
	
	void anlegenAusschreibung(int idProjekt, String bezeichnung, String beschreibung, Date endDatum,
			AsyncCallback<Ausschreibung> callback);



	void loeschenAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);
	
	void getAusschreibungbyId(int idAusschreibung, AsyncCallback<Ausschreibung> callback);

	void saveAusschreibung(Ausschreibung a, AsyncCallback<Void> callback);

	
	
	// Partnerprofil Asynch
	
	void anlegenPartnerprofil(int idAusschreibung, int idOrganisationseinheit, AsyncCallback<Partnerprofil> callback);


	void loeschenPartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);

	void getPartnerprofilbyId(int idPartnerprofil, AsyncCallback<Partnerprofil> callback);

	void savePartnerprofil(Partnerprofil pp, AsyncCallback<Void> callback);
	

	// Bewerbung Asynch
	
	void anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext, Date erstellDatum,
			AsyncCallback<Bewerbung> callback);

	void loeschenBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	void getBewerbungbyId(int idBewerbung, AsyncCallback<Bewerbung> callback);

	void saveBewerbung(Bewerbung b, AsyncCallback<Void> callback);

	
	
	// Eigenschaft Asynch	

	void anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String sprachkenntnisse, AsyncCallback<Eigenschaft> callback);


	void loeschenEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	void getEigenschaftById(int idEigenschaft, AsyncCallback<Eigenschaft> callback);

	void saveEigenschaft(Eigenschaft e, AsyncCallback<Void> callback);

	
	// Bewertung Asynch
	
	void anlegenBewertung(int idBewerbung, String textuelleBewertung, double fliessKommaBewertung,
			AsyncCallback<Bewertung> callback);


	void loeschenBewertung(Bewertung bewertung, AsyncCallback<Void> callback);

	void getBewertungById(int idBewertung, AsyncCallback<Bewertung> callback);
	
	void saveBewertung(Bewertung bewertung, AsyncCallback<Void> callback);
	
	
		// Beteiligung Asynch
	
	
	void anlegenBeteiligung(int idOrganisationseinheit, int idProjekt, int idBewertung,
			AsyncCallback<Beteiligung> callback);


	void loeschenBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);

	void getBeteiligungById(int idBeteiligung, AsyncCallback<Beteiligung> callback);
	
	void saveBeteiligung(Beteiligung beteiligung, AsyncCallback<Void> callback);


	// Person Asynch
	void anlegenPerson(int idOrganisationseinheit, int idPartnerprofil, char geschlecht, String vorname,
			String nachname, AsyncCallback<Person> callback);



	void loeschenPerson(Person pe, AsyncCallback<Void> callback);
	
	void getPersonById(int idPerson, AsyncCallback<Person> callback);

	void savePerson(Person pe, AsyncCallback<Void> callback);


	// Team Asynch

	void anlegenTeam(int idOrganisationseinheit, int idPartnerprofil, String teamName, int mitgliederAnzahl,
			AsyncCallback<Team> callback);


	void loeschenTeam(Team t, AsyncCallback<Void> callback);
	
	void getTeamById(int idTeam, AsyncCallback<Team> callback);

	void saveTeam(Team t, AsyncCallback<Void> callback);


	// Unternehmen u
	
	void anlegenUnternehmen(int idOrganisationseinheit, int idPartnerprofil, String firmenName, AsyncCallback<Unternehmen> callback);

	void loeschenUnternehmen(Unternehmen u, AsyncCallback<Void> callback);
	
	void getUnternehmenById(int idUnternehmen, AsyncCallback<Unternehmen> callback);

	void saveUnternehmen(Unternehmen u, AsyncCallback<Void> callback);



	// Anforderungen und Aufrufe definieren mit Asynch 


	void getAllAusschreibungenByOrganisationseinheit(Organisationseinheit o,
			AsyncCallback<Vector<Ausschreibung>> callback);

	void getAllAusschreibungByPartnerprofil(Partnerprofil pp, AsyncCallback<Vector<Ausschreibung>> callback);

	void getAllBewerbungenByAusschreibung(Ausschreibung a, AsyncCallback<Vector<Bewerbung>> callback);

	void getAllBewerbungenByOrganisationseinheut(Organisationseinheit o, AsyncCallback<Vector<Bewerbung>> callback);

	void getAllBeteiligungenToProject(Projekt p, AsyncCallback<Vector<Beteiligung>> callback);
	

































}
