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
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;
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
//Rueckgaengig
//ZweiterVersuch

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {


		
		//this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird geloescht)
	
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper bestehen?
	private MarktplatzMapper mpMapper = null;
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private TeamMapper teamMapper = null;
	private UnternehmenMapper unternehmenMapper = null;
	private AusschreibungMapper ausschreibungMapper = null;
	private BeteiligungMapper beteiligungMapper = null;
	private BewerbungMapper bewerbungMapper = null;
	private BewertungMapper bewertungMapper = null;
	

public GreetingServiceImpl() throws IllegalArgumentException {

}


// Initialisierung
public void init() throws IllegalArgumentException {

	// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse
	// wird geloescht)
	this.eigMapper = EigenschaftMapper.eigenschaftMapper();
	this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper(); // kommt noch raus?
	this.mpMapper = MarktplatzMapper.marktplatzMapper();
	this.ppMapper = PartnerprofilMapper.partnerprofilMapper();
	this.persMapper = PersonMapper.personMapper();
	this.prjktMapper = ProjektMapper.projektMapper();
	//this.pmpMapper = MarktplatzMapper.marktplatzMapper();
	this.teamMapper = TeamMapper.teamMapper();
	this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
	this.ausschreibungMapper = AusschreibungMapper.ausschreibungMapper(); 
	this.bewertungMapper = BewertungMapper.bewertungMapper();
	this.bewerbungMapper = BewerbungMapper.bewerbungmapper();
	this.beteiligungMapper = BeteiligungMapper.beteiligungMapper();
	}



//test 


		public Ausschreibung getAusschreibungByPartnerprofilId(Partnerprofil p) throws IllegalArgumentException {
			return this.ausschreibungMapper.findAusschreibungbyIdPartnerprofil(p.getId());
		}

		
		public Ausschreibung getpartnerprofilIdbyAusschreibung (Ausschreibung a) throws IllegalArgumentException{
			return this.ausschreibungMapper.findpartnerprofilIdbyAusschreibung(a.getId());
		}

/*##########################################################
 * START EIGENSCHAFT
 #########################################################*/

// createEigenschaft

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

	@Override
	public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		this.eigMapper.deleteEigenschaft(e);

	}

	@Override
	public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
		eigMapper.updateEigenschaft(e);

	}
	@Override
	public void saveEigenschaftnachPP(Eigenschaft e) throws IllegalArgumentException {
		eigMapper.updateEigenschaftnachPP(e);

	}
	

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

@Override
public Partnerprofil anlegenPartnerprofil()
		throws IllegalArgumentException {
	
	Partnerprofil pp = new Partnerprofil();

	return this.ppMapper.insert(pp);
}

// loeschenPartnerprofil
@Override
public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
	
	// Methode muss noch erstellt werden!!
			Vector<Eigenschaft> e = this.getEigenschaftByPartnerprofil(pp);
			
			if(e != null){
				for(Eigenschaft eigenschaft : e){
					this.eigMapper.deleteEigenschaft(eigenschaft);
				}
				this.ppMapper.delete(pp);
				// Datentyp ist Partnerprofil
	
	}
} 

// getEigenschaftByPartnerprofil()
public Vector<Eigenschaft> getEigenschaftByPartnerprofil(Partnerprofil pp) {

	Vector<Eigenschaft> result = new Vector<Eigenschaft>();

	if (pp != null && this.eigMapper != null) {
		Vector<Eigenschaft> eigenschaft = this.eigMapper.findEigenschaftByPartnerprofil(pp.getId());

		if (eigenschaft != null) {
			result.addAll(eigenschaft);
		}
	}
	return result;

}

// getEigenschaftByPartnerprofil
public Vector<Eigenschaft> getEigenschaftByIdPartnerprofil(int idPartnerprofil) {
	
	Vector<Eigenschaft> result = new Vector<Eigenschaft>();

	if (this.eigMapper != null) {
		Vector<Eigenschaft> eigenschaft = this.eigMapper.findEigenschaftByPartnerprofil(idPartnerprofil);

		if (eigenschaft != null) {
			result.addAll(eigenschaft);
		}
	}
	return result;
	
}

	// getPartnerprofilbyId
	@Override
	public Partnerprofil getPartnerprofilbyId(int idPartnerprofil) throws IllegalArgumentException {
		return this.ppMapper.findPartnerprofilByKey(idPartnerprofil);
	}

	// savePartnerprofil
	@Override
	public void savePartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		ppMapper.update(pp);
	}

	// getAllPartnerprofile
	public Vector<Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException {
		return this.ppMapper.findAllPartnerprofil();
	}
	
	// getPartnerprofilByAusschreibung
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

	// anlegenPerson
	@Override
	public Person anlegenPerson(int idUnternehmen, int idTeam, int idPartnerprofil, String vorname, String nachname, String titel, String emailAddresse, String standort, String adresse)
			throws IllegalArgumentException {
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

	// loeschenPerson
	@Override
	public void loeschenPerson(Person pe) throws IllegalArgumentException {

		Vector<Bewerbung> b = this.getBewerbungByBewerber(pe);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(pe);
		Vector<Projekt> p = this.getProjektByPerson(pe);
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(pe); // muss
																			// noch
																			// eingearbeitet
																			// werden
		// PP nicht vorhanden in Mapper

		// Bewerbungen der Person l�schen
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

	// getProjektByPerson
	public Vector<Projekt> getProjektByPerson(Organisationseinheit o) {

		Vector<Projekt> result = new Vector<Projekt>();

		if (o != null && this.prjktMapper != null) {
			Vector<Projekt> projekt = this.prjktMapper.findProjektbyPerson(o.getId());

			if (o != null) {
				result.addAll(projekt);
			}
		}

		return result;
	}

	// getBeteiligungByBeteiligter
	public Vector<Beteiligung> getBeteiligungByBeteiligter(Organisationseinheit o) {

		Vector<Beteiligung> result = new Vector<Beteiligung>();

		if (o != null && this.beteiligungMapper != null) {
			Vector<Beteiligung> beteiligung = this.beteiligungMapper.findBeteiligungByBeteiligter(o.getId());

			if (o != null) {
				result.addAll(beteiligung);
			}
		}

		return result;
	}

	// getBewerbungByBewerber(Person pe)
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
	
	// getBewerbungByBewerber(Organisationseinheit o)
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

	// savePerson
	@Override
	public void savePerson(Person pe) throws IllegalArgumentException {
		persMapper.updatePerson(pe);

	}

	// getPersonById
	@Override
	public Person getPersonById(int idPerson) throws IllegalArgumentException {
		return this.persMapper.findPersonByKey(idPerson);
		// String in PersonMapper
	}

	
	// getAllPersons
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


	// anlegenProjekt
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

	// loeschenProjekt
	@Override
	public void loeschenProjekt(Projekt p) throws IllegalArgumentException {

		Vector<Ausschreibung> a = this.getAusschreibungByProjekt(p);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByProjekt(p);

		// zugeh�rige Ausschreibungen l�schen
		if (a != null) {
			for (Ausschreibung ausschreibung : a) {
				this.loeschenAusschreibung(ausschreibung);
			}
		}

		// zugeh�rige Beteiligungen l�schen
		if (beteiligung != null) {
			for (Beteiligung b : beteiligung) {
				this.loeschenBeteiligung(b);
			}
		}

		this.prjktMapper.deleteProjekt(p);

	}

	// getAusschreibungByProjekt
	public Vector<Ausschreibung> getAusschreibungByProjekt(Projekt p) {
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		if (p != null && this.ausschreibungMapper != null) {
			Vector<Ausschreibung> ausschreibung = this.ausschreibungMapper.findAusschreibungByProjekt(p.getId());

			if (ausschreibung != null) {
				result.addAll(ausschreibung);
			}
		}

		return result;
	}

	// getProjektbyId
	@Override
	public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByKey(idProjekt);
	}

	// saveProjekt
	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		prjktMapper.updateProjekt(p);

	}

	// getAllProjekte
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException {
		return this.prjktMapper.findAllProjekt();
	}

	// getProjektByBezeichnung
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByBezeichnung(bezeichnung);
	}
	
	// getProjektByAusschreibung
	public Projekt getProjektByAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		Projekt projekt = this.getProjektbyId(a.getIdProjekt());
		return projekt;
	}

	// SCHAUEN OB CREATE-METHODEN RICHTIG SIND

/*##########################################################
 * START MARKTPLATZ
 #########################################################*/


	// anlegenMarktplatz
	@Override
	public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung) throws IllegalArgumentException {
		Marktplatz pm = new Marktplatz();

		pm.setId(1);
		pm.setGeschaeftsgebiet(geschaeftsgebiet);
		pm.setBezeichnung(bezeichnung);

		return this.mpMapper.insertMarktplatz(pm);
	}

	// loeschenMarktplatz
	@Override
	public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException {

		Vector<Projekt> p = this.getProjektbyMarktplatz(pm);

		// zugehoerige Projekte loeschen
		if (p != null) {
			for (Projekt projekt : p) {
				this.loeschenProjekt(projekt);
			}
		}

		this.mpMapper.deleteMarktplatz(pm);

	}

	// getProjektbyMarktplatz
	public Vector<Projekt> getProjektbyMarktplatz(Marktplatz pm) {

		Vector<Projekt> result = new Vector<Projekt>();

		if (pm != null && this.prjktMapper != null) {
			Vector<Projekt> projekt = this.prjktMapper.findProjektbyMarktplatz(pm.getId());

			if (pm != null) {
				result.addAll(projekt);
			}
		}

		return result;
	}

	// getMarktplatzById
	@Override
	public Marktplatz getMarktplatzById(int idMarktplatz) throws IllegalArgumentException {
		return this.mpMapper.findMarktplatzByKey(idMarktplatz);
	}

	// saveMarktplatz
	@Override
	public void saveMarktplatz(Marktplatz pm) throws IllegalArgumentException {
		mpMapper.updateMarktplatz(pm);

	}

	// getAllMarktplaetze
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
		return this.mpMapper.findAllMarktplatz();
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


	// anlegenTeam
	@Override
	public Team anlegenTeam(int idUnternehmen, int idPartnerprofil, String teamName, int mitgliederAnzahl)
			throws IllegalArgumentException {
		Team t = new Team();

		t.setId(1);
		t.setTeamName(teamName);
		t.setMitgliederAnzahl(mitgliederAnzahl);
		t.setIdUnternehmen(idUnternehmen);
		t.setIdPartnerprofil(idPartnerprofil);

		return this.teamMapper.insertTeam(t);
	}

	// loeschenTeam
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

	// getPartnerprofilByOrganisationseinheit
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

	// saveTeam
	@Override
	public void saveTeam(Team t) throws IllegalArgumentException {
		teamMapper.updateTeam(t);

	}

	// getTeamById
	@Override
	public Team getTeamById(int idTeam) throws IllegalArgumentException {
		return this.teamMapper.findTeamByKey(idTeam);
	}
	// ben�tigen wir nicht
	//// getAllTeams
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

	// anlegenUnternehmen
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

	// loeschenUnternehmen ---------- gemeinsam durchgehen
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

	// getUnternehmenById
	@Override
	public Unternehmen getUnternehmenById(int idUnternehmen) {
		return this.unternehmenMapper.findUnternehmenByKey(idUnternehmen);
	}

	// saveUnternehmen
	@Override
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		unternehmenMapper.updateUnternehmen(u);

	}

	// getUnternehmenByFirmenName
	public Vector<Unternehmen> getUnternehmenByFirmenName(String firmenName) throws IllegalArgumentException {

		return this.unternehmenMapper.findUnternehmenByFirmenName(firmenName);
	}
	
	// alle Unternehmen 
	
	public Vector<Unternehmen> getAllUnternehmen() throws IllegalArgumentException {
		return unternehmenMapper.findAllUnternehmen();
	}
	
	/*##########################################################
	 * START BETEILIGUNG
	 #########################################################*/
	
	
	// anlegenBeteiligung
	@Override
	public Beteiligung anlegenBeteiligung(String beteiligungszeit, int idOrganisationseinheit, int idProjekt, int idBewertung)
			throws IllegalArgumentException {
		Beteiligung b = new Beteiligung();

		b.setId(1);
		b.setBeteiligungszeit(beteiligungszeit);
		b.setIdBeteiligter(idOrganisationseinheit);
		b.setIdProjekt(idProjekt);
		b.setIdBewertung(idBewertung);
		
		return this.beteiligungMapper.insertBeteiligung(b);

	}

	// loeschenBeteiligung
	@Override
	public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
		this.beteiligungMapper.deleteBeteiligung(beteiligung);

	}

	// saveBeteiligung
	@Override
	public void saveBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
		beteiligungMapper.updateBeteiligung(beteiligung);

	}

	// getBeteiligungById
	public Beteiligung getBeteiligungById(int idBeteiligung) throws IllegalArgumentException {
		return this.beteiligungMapper.findBeteiligungByKey(idBeteiligung);
	}

	// getAllBeteiligungen
	public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException {
		return this.beteiligungMapper.findAllBeteiligungen();
	}

	// getBeteiligungByProjekt
	public Vector<Beteiligung> getBeteiligungByProjekt(Projekt p) {
		return this.beteiligungMapper.findBeteiligungByProjekt(p.getId());
	}

/*
 * ########################################################## START
 * AUSSCHREIBUNG #########################################################
 */

	// anlegenAusschreibung
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

	// loeschenAusschreibung
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

	// getAllBewerbungenByAusschreibung
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

	// getAusschreibungbyId
	@Override
	public Ausschreibung getAusschreibungbyId(int idAusschreibung) throws IllegalArgumentException {
		return this.ausschreibungMapper.findAusschreibungByKey(idAusschreibung);
	}

	// saveAusschreibung
	@Override
	public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
		ausschreibungMapper.updateAusschreibung(a);
	}
	
	// getAusschreibungByAusschreibender
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
	
	// getAusschreibungByBewerbung
	public Ausschreibung getAusschreibungByBewerbung(Bewerbung b) throws IllegalArgumentException {
		Ausschreibung ausschreibung = this.getAusschreibungbyId(b.getIdAusschreibung());
		return ausschreibung;		
	}

/*##########################################################
 * START BEWERBUNG
 #########################################################*/

	// anlegenBewerbung
	@Override
	public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
			Date erstellDatum, BewerbungsStatus bewerbungsStatus) throws IllegalArgumentException {
		Bewerbung b = new Bewerbung();

		b.setId(1);
		b.setIdOrganisationseinheit(idOrganisationseinheit);
		b.setIdAusschreibung(idAusschreibung);
		b.setBewerbungsText(bewerbungstext);
		b.setErstellDatum(erstellDatum);
		b.setBewerbungsStatus(bewerbungsStatus);

		return this.bewerbungMapper.insertBewerbung(b);
	}

	// loeschenBewerbung
	@Override
	public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException {

		Bewertung bewertung = this.getBewertungByBewerbung(b);

		if (bewertung != null) {
			this.loeschenBewertung(bewertung);
		}

		this.bewerbungMapper.deleteBewerbung(b);
	}

	// getBewertungByBewerbung
	public Bewertung getBewertungByBewerbung(Bewerbung b) {

		if (b != null && this.bewertungMapper != null) {
			Bewertung bewertung = this.bewertungMapper.findBewertungByBewerbung(b.getId());
			return bewertung;
		}

		return null;
	}

	// getBewerbungbyId
	@Override
	public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException {
		return this.bewerbungMapper.findBewerbungByKey(idBewerbung);
	}

	// saveBewerbung
	@Override
	public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
		bewerbungMapper.updateBewerbung(b);

	}

	// getAllBewerbungen
	public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException {
		return this.bewerbungMapper.findAllBewerbungen();
	}
	
	
	// getBewerbungByAusschreibungId
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

	// anlegenBewertung
	@Override
	public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung) {
		Bewertung bewertung = new Bewertung();

		bewertung.setId(1);
		bewertung.setIdBewerbung(idBewerbung);
		bewertung.setTextuelleBewertung(textuelleBewertung);
		bewertung.setFliesskommaBewertung(fliessKommaBewertung);
		
		return this.bewertungMapper.insertBewertung(bewertung);
	}

	// loeschenBewertung
	@Override
	public void loeschenBewertung(Bewertung bewertung) throws IllegalArgumentException {

		Beteiligung beteiligung = this.getBeteiligungByBewertung(bewertung);

		if (beteiligung != null) {
			this.loeschenBeteiligung(beteiligung);
		}

		this.bewertungMapper.deleteBewertung(bewertung);

	}

	// getBeteiligungByBewertung
	public Beteiligung getBeteiligungByBewertung(Bewertung bewertung) {
		return this.beteiligungMapper.findBeteiligungByBewertung(bewertung.getId());
	}

	// getBewertungById
	@Override
	public Bewertung getBewertungById(int idBewertung) throws IllegalArgumentException {
		return this.bewertungMapper.findBewertungByKey(idBewertung);
	}

	// saveBewertung
	@Override
	public void saveBewertung(Bewertung bewertung) throws IllegalArgumentException {
		bewertungMapper.updateBewertung(bewertung);

	}
	
	/*##########################################################
	 * START ORGANISATIONSEINHEIT
	 #########################################################*/

	// getOrganisationseinheitById
	public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) {

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

	// getAllOrganisationseinheiten
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
	// getAllAusschreibungen
	public Vector<Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException {
		return this.ausschreibungMapper.findAllAusschreibungen();
	}

	// 4. Abfrage aller Ausschreibungen, die auf Partnerprofil des Benutzers
	// passen
	public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		return this.ausschreibungMapper.findAusschreibungByPartnerprofil(pp.getId());
	}

	// 6. Abfrage der eigenen Bewerbungen und den zugehoerigen Ausschreibungen
	// des Benutzers
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

	// getAllBeteiligungenToProjekt
	public Vector<Projekt> getAllBeteiligungenToProjekt(Vector<Beteiligung> beteiligung) {

		Vector<Projekt> projects = new Vector<Projekt>();
		for (Beteiligung b : beteiligung) {
			Projekt p = this.getProjektbyId(b.getIdProjekt());
			projects.add(p);
		}

		return projects;

		// return this.beteiligungMapper.findBeteiligungByProjekt(p.getId());
	}






	// AllMarktpl�tzeAnzeigenlassen
}