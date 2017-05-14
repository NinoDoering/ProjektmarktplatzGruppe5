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
	void init() throws IllegalArgumentException; 
	
	//Projektmarktplatz pm
	Object anlegenProjektmarktplatz(Object projektmarktplatz, String geschaeftsgebiet, String bezeichnung,
			Object projekt)throws IllegalArgumentException;
	Vector editierenProjektmarktplatz(String geschaeftsgebiet, String bezeichnung, Object projekt) throws IllegalArgumentException;
	void loeschenProjektmarktplatz(Object projekt, Object projektmarktplatz)throws IllegalArgumentException;
	void save(Object pm) throws IllegalArgumentException;
	
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
	Object anlegenBewerbung(Object b, String bewerber, String bewerbungstext, Date erstellDatum) throws IllegalArgumentException;
	Vector editierenBewerbung(String beweber, String bewerbungstext, Date erstellDatum) throws IllegalArgumentException;
	void loeschenBewerbung(Object b, String bewerber) throws IllegalArgumentException;
	
	Vector getBewerbung(Object b, String bewerber) throws IllegalArgumentException;
	
	//Eigenschaft e
	Eigenschaft anlegenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String sprachkenntnisse) throws IllegalArgumentException;
	Vector<Eigenschaft> editierenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String sprachkenntnisse) throws IllegalArgumentException;
	void loeschenEigenschaft(Eigenschaft e, String arbeitsgebiet, float berufserfahrungsJahre, String employmentStatus,
			String ausbildung, String sprachkenntnisse) throws IllegalArgumentException;
	
	Vector<Eigenschaft> getEigenschaft(Eigenschaft e, String arbeitsgebeit, float berufserfahrungsJahre,
			String employmentStatus) throws IllegalArgumentException;

	//Bewertung bg
	Object anlegenBewertung(Object bewertung, String textuelleBewertung, float fließKommaBewertung);
	Vector editierenBewertung(Object bg, String textuelleBewertung, float fließKommaBewertung) throws IllegalArgumentException;
	void loeschenBewertung(Object bg, String textuelleBewertung, float fließKommaBewertung) throws IllegalArgumentException;

	Vector getBewertung(Object bg, float fließKommaBewertung) throws IllegalArgumentException;
	
	//Beteiligung beteiligung
	Beteiligung anlegenBeteiligung(Beteiligung beteiligung, String name, int idBeteiligung, int idProjekt);
	Vector<Beteiligung> editierenBeteiligung(Beteiligung betiligung);
	void loeschenBeteiligung(Beteiligung beteiligung);

	Vector<Beteiligung> getBeteiligung(Beteiligung btg, int idOrganisationseinheit) throws IllegalArgumentException;

	//Es folgt noch......
}
