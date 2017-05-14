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
	
	//.......Erneuert.......
	Object anlegenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Object projekt) throws IllegalArgumentException;

	Vector editierenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Object projekt) throws IllegalArgumentException;

	void loeschenProjektmarktplatz(Object p) throws IllegalArgumentException;

	Vector getProjektmarktplatz(Object projekt) throws IllegalArgumentException;

	Object anlegenProjekt(String projektleiter, String beschreibung, Date startDatum, Date endDatum) throws IllegalArgumentException;

	

	
	
	
	
}
