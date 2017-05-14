package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.*;

@RemoteServiceRelativePath("projektmarktplatzdministration")
public interface ProjektmarktplatzAdministration extends RemoteService {

	
	//Diese Methode bei jeder Instantiierung verwenden, das heißt diese Methode init() 
	//danach verwenden, dient der Überprüng der Instanz => "throws ILLEGALARGUMENTEXCEPTION" für Fehlermeldung zuständig
	/*public void init() throws IllegalArgumentException;

	Object createProjekt(String bezeichnung, String beschreibung, Object projektleiter) throws IllegalArgumentException;

	Object createAccountFor(Object p) throws IllegalArgumentException;

	void save(Object p) throws IllegalArgumentException;

	Object createUnternehmen(String firmenName) throws IllegalArgumentException;

	Object createPerson(String vorname, String nachname) throws IllegalArgumentException;

	Object createBewertung(String textuelleBewertung, float fließKommaBewertung) throws IllegalArgumentException;

	Object createBewerbung(String bewerber, float berwerbungsText) throws IllegalArgumentException;

	Object createTeam(String teamName) throws IllegalArgumentException;

	Object createAusschreibung(String bezeichnung, String beschreibung) throws IllegalArgumentException;

	Vector getPartnerprofilOf(Object p) throws IllegalArgumentException;

	Vector getPersonByName(String nachname) throws IllegalArgumentException;

	Vector getProjektByName(String bezeichnung) throws IllegalArgumentException;
	*/
	
	
	//Projektmarktplatz pm
	Object anlegenProjektmarktplatz(Object projektmarktplatz, String geschaeftsgebiet, String bezeichnung,
			Object projekt)throws IllegalArgumentException;
	Vector editierenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Object projekt) throws IllegalArgumentException;
	void loeschenProjektmarktplatz(Object projekt, Object projektmarktplatz)throws IllegalArgumentException;
	void save(Object pm);
	
	Vector getProjektmarktplatz(Object projekt) throws IllegalArgumentException;
	
	//Projekt p 
	Object anlegenProjekt(Object projekt, String projektleiter, String beschreibung, Date startDatum, Date endDatum) throws IllegalArgumentException;
	Vector editierenProjekt(String projektleiter, String beschreibung, Date startDatum, Date endDatum) throws IllegalArgumentException;
	void loeschenProjekt(Object projekt) throws IllegalArgumentException;
	
	Vector getProjekt(Object projekt) throws IllegalArgumentException;
	
	//Ausschreibung a
	Object anlegenAusschreibung(Object a, String bezeichnung, String beschreibung, Date endDatum) throws IllegalArgumentException;
	Vector editierenAusschreibung(Object a, String bezeichnung, String beschreibung, Date endDatum) throws IllegalArgumentException;
	void loeschenAusschreibung(Object a) throws IllegalArgumentException;

	Vector getAusschreibung(Object ausschreibung, String bezeichnung) throws IllegalArgumentException;
	
	//Partnerprofil pp
	Partnerprofil anlegenPartnerprofil(int idParnterprofil, Partnerprofil pp, String eigenschaft) throws IllegalArgumentException;
	Vector<Partnerprofil> editierenPartnerprofil(Partnerprofil pp, String eigenschaft) throws IllegalArgumentException;
	void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException;
	
	Vector<Partnerprofil> getPartnerprofil(int idPartnerprofil, Partnerprofil pp, String eigenschaften) throws IllegalArgumentException;
	
	//Bewerbung b


	

	

	
	
	
	
}
