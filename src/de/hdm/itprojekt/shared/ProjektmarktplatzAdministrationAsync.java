package de.hdm.itprojekt.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public interface ProjektmarktplatzAdministrationAsync <Person, Unternehmen, Team, Projekt, Ausschreibung, 
														Projektmarktplatz, Bewertung, Bewerbung> {
	
	
	//Projektmarktplatz pm
	void anlegenProjektmarktplatz(Projektmarktplatz pm, String geschaeftsgebiet, String bezeichnung, Projekt projekt,
		    AsyncCallback <Projektmarktplatz> callback);	
	void editierenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Projekt projekt, 
			AsyncCallback <Vector<Projektmarktplatz>> callback);
	//Überlegung einen speicher-Methode anzulegen? //Was sagt ihr dazu?
	void loeschenProjektmarktplatz(Projekt p, Projektmarktplatz pm, 
			AsyncCallback <Void> callback);
	void save(Projektmarktplatz pm, AsyncCallback <Void> callback);
	
	void getProjektmarktplatz(Projekt projekt, 
			AsyncCallback <Vector<Projektmarktplatz>> callback);
	
	
	//Projekt p 
	void anlegenProjekt(Projekt p, String projektleiter, String beschreibung, Date startDatum, Date endDatum, 
			AsyncCallback <Projekt> callback);
	void editierenProjekt(String projektleiter, String beschreibung, Date startDatum, Date endDatum, 
			AsyncCallback <Vector<Projekt>> callback);
	void loeschenProjekt(Projekt p, 
			AsyncCallback <Void> callback);
	
	void getProjekt(Projekt p, 
			AsyncCallback <Vector<Projekt>> callback);
	
	
	//Ausschreibung a
	void anlegenAusschreibung(Ausschreibung a, String bezeichnung, String beschreibung, Date endDatum,
			AsyncCallback <Ausschreibung> callback);
	void editierenAusschreibung(Ausschreibung a, String bezeichnung, String beschreibung, Date endDatum,
			AsyncCallback <Vector<Ausschreibung>> callback);
	void loeschenAusschreibung(Ausschreibung a,
			AsyncCallback <Void> callback);
	
	void getAusschreibung(Ausschreibung a, String bezeichnung,
			AsyncCallback <Vector<Ausschreibung>> callback);
	
	//Partnerprofil pp
	void anlegenPartnerprofil(int idParnterprofil, Partnerprofil pp, String eigenschaft,
			AsyncCallback <Partnerprofil> callback);
	void editierenPartnerprofil(Partnerprofil pp, String eigenschaft,
			AsyncCallback <Vector<Partnerprofil>> callback);
	void loeschenPartnerprofil(Partnerprofil pp,
			AsyncCallback <Void> callback);
	
	void getPartnerprofil(int idPartnerprofil, Partnerprofil pp, String eigenschaften,
			AsyncCallback <Vector<Partnerprofil>> callback);
	
	//Bewerberung b
	
	
	
	
	
	//......
	
	/*void createAccountFor(Person p, AsyncCallback <Account> callback);
	
	void save(Person p, AsyncCallback <Void> callback);
	
	void createUnternehmen(String firmenName,
		      AsyncCallback <Unternehmen> callback);
	
	void createPerson(String vorname, String nachname,
		      AsyncCallback <Person> callback);
	
	
	
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
	
	void getProjektByName(String bezeichnung, AsyncCallback <Vector<Projekt>> callback);
	
	
	
	
	void init (AsyncCallback <Void> callback);
	*/
	
	
	
}
