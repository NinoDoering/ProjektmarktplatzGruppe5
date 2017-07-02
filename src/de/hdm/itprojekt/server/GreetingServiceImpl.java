package de.hdm.itprojekt.server;

import de.hdm.itprojekt.shared.GreetingService;
import de.hdm.itprojekt.shared.GreetingServiceAsync;
import de.hdm.itprojekt.client.Projektmarktplatz;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
import de.hdm.itprojekt.server.db.BeteiligungMapper;
import de.hdm.itprojekt.server.db.BewerbungMapper;
import de.hdm.itprojekt.server.db.BewertungMapper;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.MarktplatzMapper;
import de.hdm.itprojekt.server.db.OrganisationseinheitMapper;
import de.hdm.itprojekt.server.db.PartnerprofilMapper;
import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;

import de.hdm.itprojekt.server.db.TeamMapper;
import de.hdm.itprojekt.server.db.UnternehmenMapper;
import de.hdm.itprojekt.shared.FieldVerifier;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Bewertung;
import de.hdm.itprojekt.shared.bo.Eigenschaft;
//import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Partnerprofil;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Implementierungsklasse des Interfaces GreetingService.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird
	// geloescht)

	/**
	 * Referenz auf den EigenschaftMapper, der Eigenschaftobjekte mit der Datenbank abgleicht.
	 */
	private EigenschaftMapper eigMapper = null;
	
	/**
	 * Referenz auf den OrganisationseinheitMapper, der Organisationsobjekte mit der Datenbank abgleicht.
	 */
	private OrganisationseinheitMapper orgaMapper = null;
	
	/**
	 * Referenz auf den MarktplatzMapper, der Marktplatzobjekte mit der Datenbank abgleicht.
	 */
	private MarktplatzMapper mpMapper = null;
	
	/**
	 * Referenz auf den PartnerprofilMapper, der Partnerprofilobjekte mit der Datenbank abgleicht.
	 */
	private PartnerprofilMapper ppMapper = null;
	
	/**
	 * Referenz auf den PeronMapper, der Personobjekte mit der Datenbank abgleicht.
	 */
	private PersonMapper persMapper = null;
	
	/**
	 * Referenz auf den ProjektMapper, der Projektobjekte mit der Datenbank abgleicht.
	 */
	private ProjektMapper prjktMapper = null;
	
	/**
	 * Referenz auf den TeamMapper, der Teamobjekte mit der Datenbank abgleicht.
	 */
	private TeamMapper teamMapper = null;
	
	/**
	 * Referenz auf den UnternehmenMapper, der Unternehmenobjekte mit der Datenbank abgleicht.
	 */
	private UnternehmenMapper unternehmenMapper = null;
	
	/**
	 * Referenz auf den AusschreibungMapper, der Ausschreibungobjekte mit der Datenbank abgleicht.
	 */
	private AusschreibungMapper ausschreibungMapper = null;
	
	/**
	 * Referenz auf den BeteiligungMapper, der Beteiligungsobjekte mit der Datenbank abgleicht.
	 */
	private BeteiligungMapper beteiligungMapper = null;
	
	/**
	 * Referenz auf den BewerbungMapper, der Bewerbungsobjekte mit der Datenbank abgleicht.
	 */
	private BewerbungMapper bewerbungMapper = null;
	
	/**
	 * Referenz auf den BewertungMapper, der Bewertungsobjekte mit der Datenbank abgleicht.
	 */
	private BewertungMapper bewertungMapper = null;

	/**
	   * <p>
	   * Ein <code>RemoteServiceServlet</code> wird unter GWT mittels
	   * <code>GWT.create(Klassenname.class)</code> Client-seitig erzeugt. Hierzu
	   * ist ein solcher No-Argument-Konstruktor anzulegen. Ein Aufruf eines anderen
	   * Konstruktors ist durch die Client-seitige Instantiierung durch
	   * <code>GWT.create(Klassenname.class)</code> nach derzeitigem Stand nicht
	   * mÃ¶glich.
	   * </p>
	   * <p>
	   * Es bietet sich also an, eine separate Instanzenmethode zu erstellen, die
	   * Client-seitig direkt nach <code>GWT.create(Klassenname.class)</code>
	   * aufgerufen wird, um eine Initialisierung der Instanz vorzunehmen.
	   * </p>
	   * 
	   * @see #init()
	   * @author Thies
	   */
	public GreetingServiceImpl() throws IllegalArgumentException {

	}

	/**
	 * Initialisierung des Objekts. Nach der Instantiierung muss diese Methode
	 * aufgerufen werden.
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException {

		// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse
		// wird geloescht)
		this.eigMapper = EigenschaftMapper.eigenschaftMapper();
		this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper();
		this.mpMapper = MarktplatzMapper.marktplatzMapper();
		this.ppMapper = PartnerprofilMapper.partnerprofilMapper();
		this.persMapper = PersonMapper.personMapper();
		this.prjktMapper = ProjektMapper.projektMapper();
		// this.pmpMapper = MarktplatzMapper.marktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
		this.ausschreibungMapper = AusschreibungMapper.ausschreibungMapper();
		this.bewertungMapper = BewertungMapper.bewertungMapper();
		this.bewerbungMapper = BewerbungMapper.bewerbungmapper();
		this.beteiligungMapper = BeteiligungMapper.beteiligungMapper();
	}

	/**
	 * Suchen einer Ausschreibung über ein Partnerprofilobjekt
	 * @param p
	 * @return Ausschreibung zum uebergebenen Partnerprofilobjekt
	 */
	public Ausschreibung getAusschreibungByPartnerprofilId(Partnerprofil p) throws IllegalArgumentException {
		return this.ausschreibungMapper.findAusschreibungbyIdPartnerprofil(p.getId());
	}

	/**
	 * Suchen eines Partnerprofils über ein Ausschreibungsobjekt
	 * @param a
	 * @return Partnerprofilobjekt zum uebergebenen Ausschreibungsobjekt
	 */
	public Ausschreibung getpartnerprofilIdbyAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		return this.ausschreibungMapper.findpartnerprofilIdbyAusschreibung(a.getId());
	}

/*##########################################################
 * START EIGENSCHAFT
 #########################################################*/

	/**
	 * Anlegen einer Eigenschaft
	 * 
	 * @param idPartnerprofil,
	 *            arbeitsgebiet, berufserfahrungsJahre, employmentStatus,
	 *            ausbildung, abschluss, sprachkenntnisse
	 * @return Eigenschaft in Datenbank anlegen
	 */
	@Override
	public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, String berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse)
			throws IllegalArgumentException {
		Eigenschaft e = new Eigenschaft();

		e.setId(1);
		e.setIdPartnerprofil(idPartnerprofil);
		e.setArbeitsgebiet(arbeitsgebiet);
		e.setAusbildung(ausbildung);
		e.setBerufserfahrungsJahre(berufserfahrungsJahre);
		e.setSprachkenntnisse(sprachkenntnisse);
		e.setEmploymentStatus(employmentStatus);
		e.setAbschluss(abschluss);

		return this.eigMapper.insertEigenschaft(e);
	}

	/**
	 * Loeschen einer Eigenschaft
	 * @param e
	 * @return Eigenschaft wird aus Datenbank (DB) gelöscht
	 */
	@Override
	public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		this.eigMapper.deleteEigenschaft(e);

	}

	/**
	 * Speichern einer Eigenschaft
	 * @param e
	 * @return Eigenschaft wird in DB gespeichert
	 */
	@Override
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		eigMapper.updateEigenschaft(e);

	}
	
	/**
	 * Speichern einer Eigenschaft
	 * @param e
	 * @return Eigenschaft wird in DB gespeichert
	 */
	@Override
	public void saveEigenschaftnachPP(Eigenschaft e) throws IllegalArgumentException {
		eigMapper.updateEigenschaftnachPP(e);

	}
	
	/**
	 * Ausgeben einer Eigenschaft nach der übergebenen ID
	 * @param idEigenschaft
	 * @return Eigenschaftsobjekt wird in DB angelegt
	 */
	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException {
		return this.eigMapper.findEigenschaftByKey(idEigenschaft);
	}

//// getAll nicht ben�tigt
//public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException {
//	return this.eigMapper.findAllEigenschaften();
//}



/*##########################################################
 * START PARTNERPROFIL
 #########################################################*/

// createPartnerprofil

	/**
	 * Anlegen eines Partnerprofils
	 * @return Partnerprofil wird in DB angelegt
	 */
	@Override
	public Partnerprofil anlegenPartnerprofil() throws IllegalArgumentException {

		Partnerprofil pp = new Partnerprofil();

		return this.ppMapper.insert(pp);
	}

	/**
	 * Loeschen eines Partnerprofils
	 * @param pp
	 * @return Partnerprofil wird aus DB geloescht
	 */
	@Override
	public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {

		// Methode muss noch erstellt werden!!
		Vector<Eigenschaft> e = this.getEigenschaftByPartnerprofil(pp);

		if (e != null) {
			for (Eigenschaft eigenschaft : e) {
				this.eigMapper.deleteEigenschaft(eigenschaft);
			}
			this.ppMapper.delete(pp);
			// Datentyp ist Partnerprofil

		}
	}

	/**
	 * Vector mit Eigenschaftsobjekten zu übergebenem Partnerprofil ausgeben
	 * @param pp
	 * @return Vector mit Eigenschaftsobjekten
	 */
	public Vector<Eigenschaft> getEigenschaftByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {

		Vector<Eigenschaft> result = new Vector<Eigenschaft>();

		if (pp != null && this.eigMapper != null) {
			Vector<Eigenschaft> eigenschaft = this.eigMapper.findEigenschaftByPartnerprofil(pp.getId());

			if (eigenschaft != null) {
				result.addAll(eigenschaft);
			}
		}
		return result;

	}

	/**
	 * Ausgabe von Eigenschaftsobjekten, die dem übergebenen Fremdschlüssel des Partnerprofils entsprechen
	 * @param idPartnerprofil
	 * @return Vector mit Eigenschaftsobjekten
	 */
	public Vector<Eigenschaft> getEigenschaftByIdPartnerprofil(int idPartnerprofil) throws IllegalArgumentException {

		Vector<Eigenschaft> result = new Vector<Eigenschaft>();

		if (this.eigMapper != null) {
			Vector<Eigenschaft> eigenschaft = this.eigMapper.findEigenschaftByPartnerprofil(idPartnerprofil);

			if (eigenschaft != null) {
				result.addAll(eigenschaft);
			}
		}
		return result;

	}

	/**
	 * Ausgabe des Partnerprofils mit übergebener ID
	 * @param idPartnerprofil
	 * @return Parnerprofilobjekt
	 */
	@Override
	public Partnerprofil getPartnerprofilbyId(int idPartnerprofil) throws IllegalArgumentException {
		return this.ppMapper.findPartnerprofilByKey(idPartnerprofil);
	}

	/**
	 * Speichern eines Partnerprofilobjekts
	 * @param pp
	 * @return Partnerprofilobjekt
	 */
	@Override
	public void savePartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		ppMapper.update(pp);
	}

	/**
	 * Ausgabe aller Partnerprofile
	 * @return alle Partnerprofile
	 */
	public Vector<Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException {
		return this.ppMapper.findAllPartnerprofil();
	}
	
	/**
	 * Ausgabe von Partnerprofilen, die zum uebergebenen Ausschreibungsobjekt gehoeren
	 * @param a
	 * @return Partnerprofilobjekte
	 */
	public Partnerprofil getPartnerprofilByAusschreibung(Ausschreibung a) {

		if (a != null && this.ppMapper != null) {
			return this.ppMapper.findPartnerprofilByKey(a.getIdPartnerprofil());

		} else {
			return null;
		}
	}


/*##########################################################
 * START PERSON
 #########################################################*/

	/**
	 * Anlegen einer Person in der Datenbank
	 * 
	 * @param idUnternehmen,
	 *            idTeam, idPartnerprofil, vorname, nachname, titel,
	 *            emailAddresse, standort, adresse
	 * @return Person wird in Datenbank anlegen
	 */
	@Override
	public Person anlegenPerson(int idUnternehmen, int idTeam, int idPartnerprofil, String vorname, String nachname,
			String titel, String emailAddresse, String standort, String adresse) throws IllegalArgumentException {
		Person pe = new Person();

		pe.setId(1);
		pe.setIdUnternehmen(idUnternehmen);
		pe.setIdTeam(idTeam);
		pe.setIdPartnerprofil(idPartnerprofil);
		pe.setVorname(vorname);
		pe.setNachname(nachname);
		pe.setTitel(titel);
		pe.setEmailAddresse(emailAddresse);
		pe.setStandort(standort);
		pe.setAdresse(adresse);

		return this.persMapper.insertPerson(pe);
	}

	/**
	 * Loeschen einer Person aus der Datenbank
	 * @param pe
	 * @return Person wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenPerson(Person pe) throws IllegalArgumentException {

		Vector<Bewerbung> b = this.getBewerbungByBewerber(pe);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(pe);
		Vector<Projekt> p = this.getProjektByPerson(pe);
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(pe); 

		// Bewerbungen der Person loeschen
		if (b != null) {
			for (Bewerbung bewerbung : b) {
				this.loeschenBewerbung(bewerbung);
			}
		}

		// zugeh�rige Beteiligungen l�schen
		if (beteiligung != null) {
			for (Beteiligung be : beteiligung) {
				this.loeschenBeteiligung(be);
			}
		}

		// von Person erstellte Projekte l�schen
		if (p != null) {
			for (Projekt projekt : p) {
				this.loeschenProjekt(projekt);
			}
		}

		// Partnerprofil der Person l�schen
		if (pp != null) {
			this.loeschenPartnerprofil(pp);
		}

		// Person entg�ltig l�schen
		this.persMapper.deletePerson(pe);

	}

	/**
	 * Alle Projekte ausgeben, die von einer bestimmten Person erstellt wurden
	 * @param o
	 * @return Vector mit Projektobjekten
	 */
	public Vector<Projekt> getProjektByPerson(Organisationseinheit o) throws IllegalArgumentException {

		Vector<Projekt> result = new Vector<Projekt>();

		if (o != null && this.prjktMapper != null) {
			Vector<Projekt> projekt = this.prjktMapper.findProjektbyPerson(o.getId());

			if (o != null) {
				result.addAll(projekt);
			}
		}

		return result;
	}

//	// Beteiligung 
//	
//	public Vector<Beteiligung> getBeteiligungByProjekt(Projekt projekt) throws IllegalArgumentException {
//		Vector<Beteiligung> result = this.geta
//		
//		for (Beteiligung beteiligung : result) {
//			beteiligung.setIdProjek
//		}
//		
//	}
	
	
	/**
	 * Ausgabe von Beteiligungen des Beteiligten
	 * @param o
	 * @return Vector mit Beteiligungsobjekten
	 */
	public Vector<Beteiligung> getBeteiligungByBeteiligter(Organisationseinheit o) throws IllegalArgumentException {

		Vector<Beteiligung> result = new Vector<Beteiligung>();

		if (o != null && this.beteiligungMapper != null) {
			Vector<Beteiligung> beteiligung = this.beteiligungMapper.findBeteiligungByBeteiligter(o.getId());

			if (o != null) {
				result.addAll(beteiligung);
			}
		}

		return result;
	}

	/**
	 * Bewerbungen eines Bewerbers ausgeben
	 * @param pe
	 * @return Vector mit Bewerbungsobjekten
	 */
	public Vector<Bewerbung> getBewerbungByBewerber(Person pe) {

		Vector<Bewerbung> result = new Vector<Bewerbung>();

		if (pe != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> bewerbung = this.bewerbungMapper.findBewerbungByBewerber(pe.getId());

			if (pe != null) {
				result.addAll(bewerbung);
			}
		}

		return result;
	}
	
	/**
	 * Bewerbungen eines Bewerbers ausgeben
	 * @param o
	 * @return Vector mit Bewerbungsobjekten
	 */
	public Vector<Bewerbung> getBewerbungByBewerber(Organisationseinheit o) throws IllegalArgumentException {

		Vector<Bewerbung> result = new Vector<Bewerbung>();

		if (o != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> bewerbung = this.bewerbungMapper.findBewerbungByBewerber(o.getId());

			if (o != null) {
				result.addAll(bewerbung);
			}
		}

		return result;
	}

	/**
	 * Speichern einer Person in der Datenbank
	 * @param pe
	 * @return Personenobjekt wird in Datenbank gespeichert
	 */
	@Override
	public void savePerson(Person pe) throws IllegalArgumentException {
		persMapper.updatePerson(pe);

	}
	
	/**
	 * Speichern einer Person in der Datenbank
	 * @param pe
	 * @return Personenobjekt wird in Datenbank gespeichert
	 */
	public Person savePersonPers(Person pe) throws IllegalArgumentException {
		return this.persMapper.updatePerson(pe);}

	/**
	 * Personenobjekt nach übergebener ID ausgeben
	 * @param idPerson
	 * @return Personenobjekt
	 */
	@Override
	public Person getPersonById(int idPerson) throws IllegalArgumentException {
		return this.persMapper.findPersonByKey(idPerson);
	}

	
	/**
	 * Ausgeben aller Personen
	 * @return Vector mit allen Personenobjekten
	 */
	 public Vector<Person> getAllPersons() throws IllegalArgumentException {
	 return this.persMapper.findAllPerson();
	 }
	 
	//
	//// getPersonByNachname
	// public Vector<Person> getPersonByNachname(String nachname) throws
	// IllegalArgumentException {
	// return this.persMapper.findPersonByNachname(nachname);
	// }



/*##########################################################
 * START PROJEKT
 #########################################################*/


	/**
	 * Anlegen eines Projektobjekts in der Datenbank
	 * 
	 * @param idPerson, idMarktplatz, beschreibung, bezeichnung, startDatum, endDatum
	 */
	@Override
	public Projekt anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung,
			Date startDatum, Date endDatum) throws IllegalArgumentException {

		Projekt p = new Projekt();

		p.setId(1);
		p.setBezeichnung(bezeichnung);
		p.setBeschreibung(beschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);
		p.setIdPerson(idPerson);
		p.setIdMarktplatz(idMarktplatz);

		return this.prjktMapper.insertProjekt(p);
	}

	/**
	 * Loeschen eines Projektobjekts
	 * @param p
	 * @return Projektobjekt wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenProjekt(Projekt p) throws IllegalArgumentException {

		Vector<Ausschreibung> a = this.getAusschreibungByProjekt(p);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByProjekt(p);

		// zugehoerige Ausschreibungen loeschen
		if (a != null) {
			for (Ausschreibung ausschreibung : a) {
				this.loeschenAusschreibung(ausschreibung);
			}
		}

		// zugehoerige Beteiligungen loeschen
		if (beteiligung != null) {
			for (Beteiligung b : beteiligung) {
				this.loeschenBeteiligung(b);
			}
		}

		this.prjktMapper.deleteProjekt(p);

	}

	/**
	 * Ausschreibungen eines Projekts ausgeben
	 * @param p
	 * @return Vector mit Ausschreibungsobjekten, die zu einem Projekt gehoeren
	 */
	public Vector<Ausschreibung> getAusschreibungByProjekt(Projekt p) throws IllegalArgumentException {
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		if (p != null && this.ausschreibungMapper != null) {
			Vector<Ausschreibung> ausschreibung = this.ausschreibungMapper.findAusschreibungByProjekt(p.getId());

			if (ausschreibung != null) {
				result.addAll(ausschreibung);
			}
		}

		return result;
	}

	/**
	 * Ausgeben eines Projekts, das der übergebenen ID entspricht
	 * @param idProjekt
	 * @return Projektobjekt, das der uebergebenen ID entspricht
	 */
	@Override
	public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByKey(idProjekt);
	}

	/**
	 * Speichern eines Projektobjekts
	 * @param p
	 * @return Projektobjekt wird in Datenbank gespeichert
	 */
	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		prjktMapper.updateProjekt(p);
	}

	/**
	 * Ausgeben aller Projektobjekte
	 * @return Vector mit allen Projektobjekten
	 */
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException {
		return this.prjktMapper.findAllProjekt();
	}

	/**
	 * Ausgeben von Projektobjekten, die der uebergebenen Bezeichnung entsprechen
	 * @param bezeichnung
	 * @return Vector mit allen Projektobjekten, die der uebergebenen Bezeichnung entsprechen
	 */
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByBezeichnung(bezeichnung);
	}
	
	/**
	 * Ausgeben eines Projekts ueber die uebergebene Ausschreibung
	 * @param a
	 * @return Projektobjekt, das zur Ausschreibung gehoert
	 */
	public Projekt getProjektByAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		Projekt projekt = this.getProjektbyId(a.getIdProjekt());
		return projekt;
	}

/*##########################################################
 * START MARKTPLATZ
 #########################################################*/


	/**
	 * Anlegen eines Marktplatzes in der Datenbank
	 * @param geschaeftsgebiet, bezeichnung
	 * @return Marktplatz wird in Datenbank angelegt.
	 */
	@Override
	public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung) throws IllegalArgumentException {
		Marktplatz pm = new Marktplatz();

		pm.setId(1);
		pm.setGeschaeftsgebiet(geschaeftsgebiet);
		pm.setBezeichnung(bezeichnung);

		return this.mpMapper.insertMarktplatz(pm);
	}

	/**
	 * Loeschen eines Marktplatzobjekts aus der Datenbank
	 * @param pm
	 * @return Marktplatzobjekt wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException {

		Vector<Projekt> p = this.getProjektbyMarktplatz(pm);

		// zugehoerige Projekte loeschen
		
			for (Projekt projekt : p) {
				this.loeschenProjekt(projekt);
			}
		

		this.mpMapper.deleteMarktplatz(pm);

	}

	/**
	 * Ausgabe von Projekten, die dem uebergebenen Marktplatzobjekt angehoeren
	 * @param pm
	 * @return Vector mit allen Projektobjekten, die dem Marktplatz angehoergen
	 */
	public Vector<Projekt> getProjektbyMarktplatz(Marktplatz pm) throws IllegalArgumentException {

		Vector<Projekt> result = new Vector<Projekt>();

		if (pm != null && this.prjktMapper != null) {
			Vector<Projekt> projekt = this.prjktMapper.findProjektbyMarktplatz(pm.getId());

			if (pm != null) {
				result.addAll(projekt);
			}
		}

		return result;
	}

	/**
	 * Ausgabe eines Marktplatzes, der der uebergebenen ID entspricht
	 * @param idMarktplatz
	 * @return Marktplatzobjekt
	 */
	@Override
	public Marktplatz getMarktplatzById(int idMarktplatz) throws IllegalArgumentException {
		return this.mpMapper.findMarktplatzByKey(idMarktplatz);
	}

	/**
	 * Speichern eines Marktplatzes in der Datenbank
	 * @param pm
	 * @return Marktplatzobjekt wird in Datenbank gespeichert
	 */
	@Override
	public void saveMarktplatz(Marktplatz pm) throws IllegalArgumentException {
		mpMapper.updateMarktplatz(pm);

	}

	/**
	 * Ausgabe aller Marktplatzobjekte
	 * @return alle Marktplatzobjekte
	 */
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
		return this.mpMapper.findAllMarktplatz();
	}
	
	/**
	 * Alle Marktplaetze einer Person ausgeben
	 * @param o
	 * @return Vector mit Marktplatzobjekten, die der Person angehoeren
	 */
	public Vector<Marktplatz> getMarktplaetzeByPerson(Organisationseinheit o) throws IllegalArgumentException {
		Vector<Marktplatz> result = new Vector<Marktplatz>();
		
		if (o != null && this.mpMapper != null) {
			Vector<Marktplatz> mp = this.mpMapper.findMarktplatzByPerson(o.getId());
			
			if(o != null){
				result.addAll(mp);
			}
		}
		return result;
	}
	
	
	//// getMarktplatzByBezeichnung
	// public Vector<Marktplatz> getMarktplatzByBezeichnung(String bezeichnung)
	// throws IllegalArgumentException {
	// return this.mpMapper.findMarktplatzByBezeichnung(bezeichnung);
	// }
	//
	//// updateMarktplatz
	// public void updateMarktplatz(Marktplatz marktplatz) throws
	// IllegalArgumentException {
	//
	// }
	//
	//// deleteMarktplatz
	// public void deleteMarktplatz(int idMarktplatz) throws
	// IllegalArgumentException {
	// this.mpMapper.deleteMarktplatz(idMarktplatz);
	// // falscher Datentyp
	// }

/*##########################################################
 * START TEAM
 ############################################################*/


	/**
	 * Anlegen eines Teams in der Datenbank
	 * @param idUnternehmen, idPartnerprofil, teamName, adresse, standort
	 * @return Team wird in Datenbank angelegt
	 */
	@Override
	public Team anlegenTeam(int idUnternehmen, int idPartnerprofil, String teamName, String adresse, String standort)
			throws IllegalArgumentException {
		Team t = new Team();

		t.setId(1);
		t.setTeamName(teamName);
//		t.setMitgliederAnzahl(mitgliederAnzahl);
		t.setIdUnternehmen(idUnternehmen);
		t.setIdPartnerprofil(idPartnerprofil);
		t.setAdresse(adresse);
		t.setStandort(standort);

		return this.teamMapper.insertTeam(t);
	}

	/**
	 * Loeschen eines Teams aus der Datenbank
	 * @param t
	 * @return Team wird aus Datenkbank geloescht
	 */
	@Override
	public void loeschenTeam(Team t) throws IllegalArgumentException {

	//	Vector<Bewerbung> b = this.getBewerbungByBewerber(t);
		Vector<Projekt> p = this.getProjektByPerson(t);
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(t);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(t);

		// zugehoerige Beteiligungen loeschen
		if (beteiligung != null) {
			for (Beteiligung beteiligungen : beteiligung) {
				this.loeschenBeteiligung(beteiligungen);
			}
		}

		// zugehoerige Partnerprofile loeschen
		if (pp != null) {
			this.loeschenPartnerprofil(pp);
		}

		// zugehoerige Bewerbungen l�schen
//		if (b != null) {
//			for (Bewerbung bewerbungen : b) {
//				this.loeschenBewerbung(bewerbungen);
//			}
//		}

		// von Team erstellte Projekte loeschen
		if (p != null) {
			for (Projekt projekt : p) {
				this.loeschenProjekt(projekt);
			}
		}

		this.teamMapper.deleteTeam(t);

	}
	
	/**
	 * Loeschen eines Teams aus der Datenbank
	 * @param t
	 * @return Team wird aus Datenbank geloescht
	 */
	public void loeschenTeamInteger(Integer t) throws IllegalArgumentException {
		this.teamMapper.deleteTeamInteger(t);
	}
	
	/**
	 * Ausgabe eines Partnerprofils, das zur uebergebenen Organisationseinheit gehoert
	 * @param o
	 * @return Partnerprofilobjekt
	 */
	public Partnerprofil getPartnerprofilByOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {

		if (o instanceof Team) {
			return this.ppMapper.findPartnerprofilByKey(this.teamMapper.findTeamByKey(o.getId()).getIdPartnerprofil());
		} else if (o instanceof Person) {
			return this.ppMapper
					.findPartnerprofilByKey(this.persMapper.findPersonByKey(o.getId()).getIdPartnerprofil());
		} else if (o instanceof Unternehmen) {
			return this.ppMapper.findPartnerprofilByKey(
					this.unternehmenMapper.findUnternehmenByKey(o.getId()).getIdPartnerprofil());
		} else {
			return null;
		}

	}

	/**
	 * Speichern eines Teams in der Datenbank
	 * @param t
	 * @return Team wird in Datenbank gespeichert
	 */
	@Override
	public void saveTeam(Team t) throws IllegalArgumentException {
		teamMapper.updateTeam(t);

	}
	
	/**
	 * Speichern eines Teams in der Datenbank
	 * @param t
	 * @return Team wird in Datenbank gespeichert
	 */
	public Team saveTeamt(Team t) throws IllegalArgumentException {
		return this.teamMapper.updateTeam(t);
	}

	/**
	 * Ausgeben eines Teams, das der uebergebenen ID entspricht
	 * @param idTeam
	 * @return Teamobjekt
	 */
	@Override
	public Team getTeamById(int idTeam) throws IllegalArgumentException {
		return this.teamMapper.findTeamByKey(idTeam);
	}

	/**
	 * Ausgabe aller Teams
	 * @return Vector mit allen Teamobjekten 
	 */
	 public Vector<Team> getAllTeams() throws IllegalArgumentException {
	 return this.teamMapper.findAllTeam();	 }
	 
	//
	//// getTeamByTeamName
	// public Vector<Team> getTeamByTeamName(String teamName) throws
	// IllegalArgumentException {
	// return this.teamMapper.findByTeamName(teamName);
	// }
	
/*##########################################################
 * START UNTERNEHMEN
 #########################################################*/

	/**
	 * Anlegen eines Unternehmens in der Datenbank
	 * @param idPartnerprofil, firmenName, adresse, standort
	 * @return Unternehmensobjekt
	 */
	@Override
	public Unternehmen anlegenUnternehmen(int idPartnerprofil, String firmenName, String adresse, String standort) throws IllegalArgumentException {

		Unternehmen u = new Unternehmen();

		u.setId(1);
		u.setFirmenName(firmenName);
		u.setIdPartnerprofil(idPartnerprofil);
		u.setAdresse(adresse);
		u.setStandort(standort);

		return this.unternehmenMapper.insertUnternehmen(u);
	}

	/**
	 * Loeschen eines Unternehmens aus der Datenbank
	 * @param u
	 * @return Unternehmen wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException {

		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(u);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(u);
		Vector<Bewerbung> b = this.getAllBewerbungenByOrganisationseinheit(u);

		if (beteiligung != null) {
			for (Beteiligung beteiligungen : beteiligung) {
				this.loeschenBeteiligung(beteiligungen);
			}
		}

		if (b != null) {
			for (Bewerbung bewerbungen : b) {
				this.loeschenBewerbung(bewerbungen);
			}
		}

		if (pp != null) {
			this.loeschenPartnerprofil(pp);
		}

		this.unternehmenMapper.deleteUnternehmen(u);
	}
	
//	/**
//	 * Loeschen eines Unternehmenobjekts aus der Datenbank
//	 * @param u
//	 * @return Unternehmensobjekt wird aus Datenbank geloescht
//	 */
//	public void loeschenUnternehmenInteger(Integer u) throws IllegalArgumentException {
//		this.unternehmenMapper.deleteUnternehmenInteger(u);
//	}

	/**
	 * Ausgabe eines Unternehmensobjekts, das der uebergebenen ID entspricht
	 * @param idUnternehmen
	 * @return Unternehmensobjekt
	 */
	@Override
	public Unternehmen getUnternehmenById(int idUnternehmen) throws IllegalArgumentException {
		return this.unternehmenMapper.findUnternehmenByKey(idUnternehmen);
	}

	/**
	 * Speichern eines Unternehmensobjekts in der Datenbank
	 * @param u
	 * @return Unternehmenobjekt wird in Datenbank gespeichert
	 */
	@Override
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		unternehmenMapper.updateUnternehmen(u);

	}

	/**
	 * Ausgabe eines Unternehmens, das dem uebergebenem Firmennamen entspricht
	 * @param firmenName
	 * @return Unternehmenobjekt
	 */
	public Unternehmen getUnternehmenByFirmenName(String firmenName) throws IllegalArgumentException {

		return this.unternehmenMapper.findUnternehmenByFirmenName(firmenName);
	}
	
	/**
	 * Ausgabe aller Unternehmenobjekte
	 * @return Vector mit allen Unternehmenobjekten
	 */
	public Vector<Unternehmen> getAllUnternehmen() throws IllegalArgumentException {
		return unternehmenMapper.findAllUnternehmen();
	}
	
	/*##########################################################
	 * START BETEILIGUNG
	 #########################################################*/
	
	
	/**
	 * Anlegen einer Beteiligung in der Datenbank
	 * @param beteiligung
	 * @return Beteiligungsobjekt wird in Datenbank angelegt
	 */
	@Override
	public Beteiligung anlegenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {		
		return this.beteiligungMapper.insertBeteiligung(beteiligung);
	}
	
	/**
	 * Anlegen einer Beteiligung
	 * @param beteiligungszeit
	 * @param idOrganisationseinheit
	 * @param idProjekt
	 * @param idBewertung
	 * @return Beteiligungsobjekt wird in Datenbank angelegt
	 * @throws IllegalArgumentException
	 */
	public Beteiligung anlegenBeteiligung(int beteiligungszeit, int idOrganisationseinheit, int idProjekt,
			int idBewertung) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Beteiligung b = new Beteiligung();
		
		b.setId(1);	
		b.setBeteiligungszeit(beteiligungszeit);
		b.setIdBeteiligter(idOrganisationseinheit);
		b.setIdProjekt(idProjekt);
		b.setIdBewertung(idBewertung);
		
		return this.beteiligungMapper.insertBeteiligung(b);
	}

	/**
	 * Loeschen einer Beteiligung aus der Datenbank
	 * @param beteiligung
	 * @return Beteiligungsobjekt wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
		this.beteiligungMapper.deleteBeteiligung(beteiligung);

	}

	/**
	 * Speichern einer Beteiligung
	 * @param beteiligung
	 * @return Beteiligung wird in Datenbank gespeichert
	 */
	@Override
	public void saveBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
		beteiligungMapper.updateBeteiligung(beteiligung);

	}

	/**
	 * Beteiligung ausgeben, die der uebergebenen ID entspricht
	 * @param idBeteiligung
	 * @return Beteiligungsobjekt
	 */
	public Beteiligung getBeteiligungById(int idBeteiligung) throws IllegalArgumentException {
		return this.beteiligungMapper.findBeteiligungByKey(idBeteiligung);
	}

	/**
	 * Ausgabe aller Beteiligungen
	 * @return Vector mit allen Beteiligungen
	 */
	public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException {
		return this.beteiligungMapper.findAllBeteiligungen();
	}

	/**
	 * Beteiligungen zu uebergebenem Projekt ausgeben
	 * @param p
	 * @return Vector mit Beteiligungsobjekten
	 */
	public Vector<Beteiligung> getBeteiligungByProjekt(Projekt p) throws IllegalArgumentException {
		return this.beteiligungMapper.findBeteiligungByProjekt(p.getId());
	}

/*
 * ########################################################## START
 * AUSSCHREIBUNG #########################################################
 */

	/**
	 * Anlegen einer Ausschreibung in der Datenbank
	 * 
	 * @param idAusschreibender,
	 *            idProjekt, bezeichnung, beschreibung, endDatum,
	 *            idPartnerprofil, ausschreibungsstatus
	 * @return Ausschreibung wird in Datenbank angelegt
	 */
	@Override
	public Ausschreibung anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung,
			String beschreibung, Date endDatum, int idPartnerprofil, Status ausschreibungsstatus)
			throws IllegalArgumentException {
		Ausschreibung a = new Ausschreibung();

		a.setId(1);
		a.setBezeichnung(bezeichnung);
		a.setEndDatum(endDatum);
		a.setBeschreibung(beschreibung);
		a.setIdProjekt(idProjekt);
		a.setIdAusschreibender(idAusschreibender);
		a.setIdPartnerprofil(idPartnerprofil);
		a.setAusschreibungsstatus(ausschreibungsstatus.laufend);

		return this.ausschreibungMapper.insertAusschreibung(a);
	}

	/**
	 * Loeschen der uebergebenen Ausschreibung
	 * @param a
	 * @return Ausschreibung wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException {

		Vector<Bewerbung> b = this.getAllBewerbungenByAusschreibung(a);

		if (b != null) {
			for (Bewerbung bewerbung : b) {

				this.loeschenBewerbung(bewerbung);

			}
		}

		this.ausschreibungMapper.deleteAusschreibung(a);

	}

	/**
	 * Alle Bewerbungen auf uebergebene Ausschreibung ausgeben
	 * @param a
	 * @return Vector mit allen Bewerbungsobjekten, die zur uebergebenen Ausschreibung gehoeren
	 */
	public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException {

		Vector<Bewerbung> result = new Vector<Bewerbung>();

		if (a != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> b = this.bewerbungMapper.findBewerbungByAusschreibung(a.getId()); // hier?

			if (b != null) {
				result.addAll(b);
			}
		}

		return result;
	}

	/**
	 * Ausgabe einer Ausschreibung, die der uebergebenen ID entspricht
	 * @param idAusschreibung
	 * @return Ausschreibungsobjekt
	 */
	@Override
	public Ausschreibung getAusschreibungbyId(int idAusschreibung) throws IllegalArgumentException {
		return this.ausschreibungMapper.findAusschreibungByKey(idAusschreibung);
	}

	/**
	 * Speichern der uebergebenen Ausschreibung
	 * @param a
	 * @return Ausschreibung wird in Datenbank gespeichert
	 */
	@Override
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		ausschreibungMapper.updateAusschreibung(a);
	}
	
	/**
	 * Ausschreibungen des uebergebenen Ausschreibenden ausgeben
	 * @param o
	 * @return Vector mit Ausschreibungsobjekten, die der uebergebenen Organisationseinheit entsprechen
	 */
	@Override
	public Vector<Ausschreibung> getAusschreibungByAusschreibender(Organisationseinheit o)
			throws IllegalArgumentException {
		
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		if(o != null && this.ausschreibungMapper != null){
			Vector<Ausschreibung> a = this.ausschreibungMapper.findAusschreibungByAusschreibender(o.getId());
				if(a != null){
					result.addAll(a);
				}
		}
		return result;
	}
	
	/**
	 * Ausschreibung einer Bewerbung ausgeben
	 * @param b
	 * @return Ausschreibungsobjekt einer Bewerbung
	 */
	public Ausschreibung getAusschreibungByBewerbung(Bewerbung b) throws IllegalArgumentException {
		Ausschreibung ausschreibung = this.getAusschreibungbyId(b.getIdAusschreibung());
		return ausschreibung;		
	}

/*##########################################################
 * START BEWERBUNG
 #########################################################*/

	/**
	 * Anlegen einer Bewerbung in der Datenbank
	 * 
	 * @param idOrganisationseinheit,
	 *            idAusschreibung, bewerbungstext, erstellDatum,
	 *            bewerbungsStatus
	 * @return Bewerbung wird in der Datenbank angelegt
	 */
	@Override
	public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
			Date erstellDatum, String bewerbungsStatus) throws IllegalArgumentException {
		Bewerbung b = new Bewerbung();

		b.setId(1);
		b.setIdOrganisationseinheit(idOrganisationseinheit);
		b.setIdAusschreibung(idAusschreibung);
		b.setBewerbungsText(bewerbungstext);
		b.setErstellDatum(erstellDatum);
		b.setStatus(bewerbungsStatus);

		return this.bewerbungMapper.insertBewerbung(b);
	}

	/**
	 * Loeschen einer Bewerbung aus der Datenbank
	 * @param b
	 * @return Bewerbungsobjekt wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException {

		Bewertung bewertung = this.getBewertungByBewerbung(b);

		if (bewertung != null) {
			this.loeschenBewertung(bewertung);
		}

		this.bewerbungMapper.deleteBewerbung(b);
	}

	/**
	 * Bewertung zur uebergebenen Bewerbung ausgeben
	 * @param b
	 * @return Bewertungsobjekt
	 */
	public Bewertung getBewertungByBewerbung(Bewerbung b) throws IllegalArgumentException {

		if (b != null && this.bewertungMapper != null) {
			Bewertung bewertung = this.bewertungMapper.findBewertungByBewerbung(b.getId());
			return bewertung;
		}

		return null;
	}

	/**
	 * Ausgeben einer Bewerbung, die der uebergebenen ID entspricht
	 * @param idBewerbung
	 * @return Bewerbungsobjekt
	 */
	@Override
	public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException {
		return this.bewerbungMapper.findBewerbungByKey(idBewerbung);
	}

	/**
	 * Speichern einer Bewerbung in der Datenbank
	 * @param b
	 * @return Bewerbung wird in Datenbank gespeichert
	 */
	@Override
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
		bewerbungMapper.updateBewerbung(b);

	}
	
	/**
	 * Bewerbungsstatus aktualisieren
	 * @param b
	 * @return Bewerbunsstatus wird aktualisiert
	 */
	@Override
	public Bewerbung bewerbungsStatusAktualisierne(Bewerbung b) {
		// TODO Auto-generated method stub
		return this.bewerbungMapper.updateBewerbungsstatus(b);
	}


	/**
	 * Ausgabe aller Bewerbungen
	 * @return Vector mit allen Bewerbungen
	 */
	public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException {
		return this.bewerbungMapper.findAllBewerbungen();
	}
	
	/**
	 * Ausgabe einer Bewerbung, ueber uebergebene Ausschreibungs-ID
	 * @param idAusschreibung
	 * @return Vector mit Bewerbungsobjekten
	 */
	public Vector<Bewerbung> getBewerbungByAusschreibungId(int idAusschreibung) throws IllegalArgumentException {
		Vector <Bewerbung> result = new Vector <Bewerbung>();
			if(this.bewerbungMapper != null){
				
				Vector<Bewerbung> b = this.bewerbungMapper.findBewerbungByAusschreibung(idAusschreibung);
				
				if (b != null){
					result.addAll(b);
				}
			}
			return result; 
	}

	/*##########################################################
	 * START BEWERTUNG
	 #########################################################*/

	/**
	 * Anlegen einer Bewertung in der Datenbank
	 * @param idBewerbung, textuelleBewertung, fliessKommaBewertung
	 * @return Bewertung wird in der Datenbank angelegt
	 */
	@Override
	public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung) throws IllegalArgumentException {
		Bewertung bewertung = new Bewertung();

		bewertung.setId(1);
		bewertung.setIdBewerbung(idBewerbung);
		bewertung.setTextuelleBewertung(textuelleBewertung);
		bewertung.setFliesskommaBewertung(fliessKommaBewertung);
		
		return this.bewertungMapper.insertBewertung(bewertung);
	}

	/**
	 * Loeschen einer Bewertung aus der Datenbank
	 * @param bewertung
	 * @return Bewertung wird aus Datenbank geloescht
	 */
	@Override
	public void loeschenBewertung(Bewertung bewertung) throws IllegalArgumentException {

		Beteiligung beteiligung = this.getBeteiligungByBewertung(bewertung);

		if (beteiligung != null) {
			this.loeschenBeteiligung(beteiligung);
		}

		this.bewertungMapper.deleteBewertung(bewertung);

	}

	/**
	 * Beteiligung einer Bewertung ausgeben
	 * @param bewertung
	 * @return Beteiligungsobjekt
	 */
	public Beteiligung getBeteiligungByBewertung(Bewertung bewertung) throws IllegalArgumentException {
		return this.beteiligungMapper.findBeteiligungByBewertung(bewertung.getId());
	}

	/**
	 * Ausgeben einer Bewertung, die der uebergebenen ID entspricht
	 * @param idBewertung
	 * @return Bewertungsobjekt
	 */
	@Override
	public Bewertung getBewertungById(int idBewertung) throws IllegalArgumentException {
		return this.bewertungMapper.findBewertungByKey(idBewertung);
	}

	/**
	 * Speichern einer Bewertung in der Datenbanken
	 * @param bewertung
	 * @return Bewertung wird in Datenbank gespeichert
	 */
	@Override
	public void saveBewertung(Bewertung bewertung) throws IllegalArgumentException {
		bewertungMapper.updateBewertung(bewertung);

	}
	
	/*##########################################################
	 * START ORGANISATIONSEINHEIT
	 #########################################################*/

	/**
	 * Ausgabe der Organisationseinheit, die der uebergebenen ID entspricht
	 * @param idOrganisationseinheit
	 * @return Organisationseinheitsobjekt
	 */
	public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) throws IllegalArgumentException {

		Person pe = persMapper.findPersonByKey(idOrganisationseinheit);
		Unternehmen u = unternehmenMapper.findUnternehmenByKey(idOrganisationseinheit);
		Team t = teamMapper.findTeamByKey(idOrganisationseinheit);

		if (pe != null) {
			return pe;
		}
		if (u != null) {
			return u;
		}
		if (t != null) {
			return t;
		} else
			return null;
	}

	/**
	 * Ausgabe aller Organisationseinheiten
	 * @return alle Organisationseinheitsobjekte
	 */
	public Vector<Organisationseinheit> getAllOrganisationseinheiten() throws IllegalArgumentException {

		Vector<Organisationseinheit> orgaEinheiten = new Vector<Organisationseinheit>();

		Vector<Person> pe = persMapper.findAllPerson();
		Vector<Team> t = teamMapper.findAllTeam();
		Vector<Unternehmen> u = unternehmenMapper.findAllUnternehmen();

		orgaEinheiten.addAll(pe);
		orgaEinheiten.addAll(t);
		orgaEinheiten.addAll(u);

		return orgaEinheiten;
	}
	
	// Anforderungen

	// 3.Abfrage von allen Ausschreibungen
	/**
	 * Ausgabe aller Ausschreibungen
	 * @return alle Ausschreibungsobjekte
	 */
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException {
		return this.ausschreibungMapper.findAllAusschreibungen();
	}

	// 4. Abfrage aller Ausschreibungen, die auf Partnerprofil des Benutzers
	// passen
	/**
	 * Ausgabe aller Ausschreibungen, die dem uebergebenen Partnerprofil entsprechen
	 * @param pp
	 * @return Vector mit allen Ausschreibungen, die zum uebergebenen Partnerprofil gehoeren
	 */
	public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		return this.ausschreibungMapper.findAusschreibungByPartnerprofil(pp.getId());
	}

	// 6. Abfrage der eigenen Bewerbungen und den zugehoerigen Ausschreibungen
	// des Benutzers
	/**
	 * Ausgabe von Bewerbungen auf eine Ausschreibung
	 * @param a
	 * @return Vector mit Bewerbungsobjekten, die zur uebergebenen Ausschreibung gehoeren
	 */
	public Vector<Bewerbung> getBewerbungByAusschreibung(Ausschreibung a) throws IllegalArgumentException {

		Vector<Bewerbung> result = new Vector<Bewerbung>();
		if (a != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> b = this.bewerbungMapper.findBewerbungByAusschreibung(a.getId());

			if (b != null) {
				result.addAll(b);
			}
		}

		return result;
	}

	// alle Ausschreibungen von der OrgaEinheit erstellt anzeigen
	// @Override
	// public Vector<Ausschreibung> getAllAusschreibungen(Organisationseinheit
	// o)
	// throws IllegalArgumentException {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	//
	//
	// 5. Abfragen aller Bewerbungen auf Ausschreibungen des Benutzers
	
	/**
	 * Ausgabe Aller Bewerbungen, die der uebergebenen Organisationseinheit entsprechen
	 * @param o
	 * @return Vector mit Bewertungsobjekten
	 */
	@Override
	public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheit(Organisationseinheit o)
			throws IllegalArgumentException {

		Vector<Bewerbung> result = new Vector<Bewerbung>();
		if (o != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> b = this.bewerbungMapper.findBewerbungByBewerber(o.getId());

			if (b != null) {
				result.addAll(b);
			}
		}

		return result;
	}
	//
	//
	// @Override
	// public Vector<Beteiligung> getAllBeteiligungenToProject(Projekt p) throws
	// IllegalArgumentException {
	// return this.beteiligungMapper.findBeteiligungByProjekt(p);
	// }

	/**
	 * Ausgabe aller Beteiligungen zu einem Projekt
	 * @param beteiligungen
	 * @return Vector mit allen Beteiligungne zu allen Projekten
	 */
	public Vector<Projekt> getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung) throws IllegalArgumentException {

		Vector<Projekt> projects = new Vector<Projekt>();
		for (Beteiligung b : beteiligung) {
			Projekt p = this.getProjektbyId(b.getIdProjekt());
			projects.add(p);
		}

		return projects;

		// return this.beteiligungMapper.findBeteiligungByProjekt(p.getId());
	}

	/**
	 * Ausgabe eines Projekts ueber die uebergebene Beteiligung
	 * @param b
	 * @return Projektobjekt
	 */
	public Projekt getProjektByBeteiligung(Beteiligung b){
		
		Projekt p = this.getProjektbyId(b.getIdProjekt());
		return p;
	}
}