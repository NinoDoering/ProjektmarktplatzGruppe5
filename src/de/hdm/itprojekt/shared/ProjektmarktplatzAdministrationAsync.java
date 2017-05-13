package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

public interface ProjektmarktplatzAdministrationAsync <Person, Unternehmen, Account, 
														Team, Projekt, Ausschreibung, Projektmarktplatz, Bewertung, Bewerbung> {
	
	
	void createAccountFor(Person p, AsyncCallback <Account> callback);
	
	void save(Person p, AsyncCallback <Void> callback);
	
	void createUnternehmen(String firmenName,
		      AsyncCallback <Unternehmen> callback);
	
	void createPerson(String vorname, String nachname,
		      AsyncCallback <Person> callback);
	
	void createProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Projekt projekt,
		      AsyncCallback <Projektmarktplatz> callback);
	
	void createBewertung(String textuelleBewertung, float fließKommaBewertung,
		      AsyncCallback <Bewertung> callback);
	
	void createBewerbung(String bewerber, float berwerbungsText,
		      AsyncCallback <Bewerbung> callback);
	
	void createProjekt(String bezeichnung, String beschreibung, Person projektleiter, AsyncCallback<Projekt> callback);
	
	void createTeam(String teamName, AsyncCallback <Team> callback);
	
	void createAusschreibung(String bezeichnung, String beschreibung,
		      AsyncCallback<Ausschreibung> callback);
	
	void getPartnerprofilOf(Person p, AsyncCallback <Vector<Account>> callback);
	
	void getPersonByName(String nachname,
		      AsyncCallback <Vector<Person>> callback);
	
	
	
	void init (AsyncCallback <Void> callback);
	
	
	
}
