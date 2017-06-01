package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProjektmarktplatzAdministrationImpl extends RemoteServiceServlet
		implements ProjektmarktplatzAdministration {


	// private BusinessObjectMapper boMapper = null;
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper?
	private MarktplatzMapper mpMapper = null;														// bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	//private MarktplatzMapper pmpMapper = null;
	private TeamMapper teamMapper = null;
	private UnternehmenMapper unternehmenMapper = null;
	private AusschreibungMapper ausschreibungMapper = null;
	private BeteiligungMapper beteiligungMapper = null;
	private BewerbungMapper bewerbungMapper = null;

	// No-Argument-Konstruktor
	public ProjektmarktplatzAdministrationImpl() throws IllegalArgumentException {

	}

	// Initialisierung
	public void init() throws IllegalArgumentException {

		// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse
		// wird gelöscht)
		this.eigMapper = EigenschaftMapper.eigenschaftMapper();
		this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper();
		this.mpMapper = MarktplatzMapper.marktplatzMapper();
		this.ppMapper = PartnerprofilMapper.partnerprofilMapper();
		this.persMapper = PersonMapper.personMapper();
		this.prjktMapper = ProjektMapper.projektMapper();
		//this.pmpMapper = MarktplatzMapper.marktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
		this.ausschreibungMapper = AusschreibungMapper.ausschreibungMapper(); 
											//.ausschreibungMapper muss das heißen auch in Mapperklasse
		this.bewerbungMapper = BewerbungMapper.bewerbungmapper();//gleicher Fehler!!!!!
		this.beteiligungMapper = BeteiligungMapper.beteiligungMapper(); // gleicher Fall
		}

	
	/*##########################################################
	 * START EIGENSCHAFT
	 #########################################################*/
	
	// createEigenschaft
	public Eigenschaft createEigenschaft(
			int idEigenschaft, String arbeitsgebiet, String ausbildung, float berufserfahrungsJahre, String sprachkenntnisse,
			String employmentStatus, String abschluss) throws IllegalArgumentException {
		Eigenschaft eig = new Eigenschaft();

		eig.setIdEigenschaft(idEigenschaft); 
		eig.setArbeitsgebiet(arbeitsgebiet);
		eig.setAusbildung(ausbildung);
		eig.setBerufserfahrungsJahre(berufserfahrungsJahre);
		eig.setSprachkenntnisse(sprachkenntnisse);
		eig.setEmploymentStatus(employmentStatus);
		eig.setAbschluss(abschluss);

		return this.eigMapper.insertEigenschaft(eig);
	}

	// getEigenschaftById
	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException {
		return this.eigMapper.findEigenschaftById(idEigenschaft);
	}

	// getAll
	public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException {
		return this.eigMapper.findAllEigenschaften();
	}
	
	// updateEigenschaft
	public void updateEigenschaft(Eigenschaft eig) throws IllegalArgumentException {
		eigMapper.updateEigenschaft(eig);		
	}
	
	// deleteEigenschaft
	public void deleteEigenschaft(int idEigenschaft) throws IllegalArgumentException {
		this.eigMapper.deleteEigenschaft(idEigenschaft); 
		// in Mapper ist Eigenschaft eig. Wir sollen doch aber mit int idEigenschaft?
											
	}
	
	
	/*##########################################################
	 * START ORGANISATIONSEINHEIT
	 #########################################################*/
	
	
	// createOrganisationseinheit
	public Organisationseinheit createOrganisationseinheit(int idOrganisationseinheit) throws IllegalArgumentException {
		Organisationseinheit orgaEinheit = new Organisationseinheit();
		orgaEinheit.setIdOrganisationseinheit(idOrganisationseinheit);

		return this.orgaMapper.insertOrganisationseinheit(orgaEinheit);
	}

	// getOrganisationseinheitById
	public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) throws IllegalArgumentException {
		return this.orgaMapper.findOrganisationseinheitById(idOrganisationseinheit);
	}
	
	// updateOrganisationseinheit
	public void updateOrganisationseinheit(Organisationseinheit org) throws IllegalArgumentException {
		orgaMapper.updateOrganisationseinheit(org);
	}
	
	// deleteOrganisationseinheit
	public void deleteOrganisationseinheit(int idOrganisationseinheit) throws IllegalArgumentException {
		this.orgaMapper.deleteOrganisationseinheit(idOrganisationseinheit);
		// gleicher Fehler wie oben --> Falscher Datentyp
	}
	
	
	
	/*##########################################################
	 * START PARTNERPROFIL
	 #########################################################*/
	
	// createPartnerprofil
	public Partnerprofil createPartnerprofil(int idPartnerprofil) throws IllegalArgumentException {
		Partnerprofil pp = new Partnerprofil();
		pp.setIdPartnerprofil(idPartnerprofil);
		
		return this.ppMapper.insert(pp);
	}
	
	// getPartnerprofilById
	public Partnerprofil getPartnerprofilById(int idPartnerprofil){
		return this.ppMapper.findByKey(idPartnerprofil);
	}
	
	// getAllPartnerprofile
	public Vector <Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException {
		return this.ppMapper.findAll();
	}
	
	// updatePartnerprofil
	public void updatePartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		ppMapper.update(pp);		
	}
	
	// deletePartnerprofil
	public void deletePartnerprofil(int idPartnerprofil) throws IllegalArgumentException {
		this.ppMapper.delete(idPartnerprofil);
		// Falscher Datentyp
	}
	
	
	
	/*##########################################################
	 * START PERSON
	 #########################################################*/
	
	
	// createPerson / insert
	public Person createPerson(
	int idPerson,
	String vorname,
	String nachname,
	String titel) throws IllegalArgumentException {
		Person pers = new Person();
		
		pers.setIdPerson(idPerson);
		pers.setVorname(vorname);
		pers.setNachname(nachname);
		pers.setTitel(titel);
		
		return this.persMapper.insertTeam(pers);
	}
	
	// getPersonById
	public Person getPersonById(int idPerson) throws IllegalArgumentException {
		return this.persMapper.findPersonByKey(idPerson);
		// String in PersonMapper
	}
	
	// getAllPersons
	public Vector<Person> getAllPersons() throws IllegalArgumentException {
		return this.persMapper.findAll();
	}
	
	// getPersonByNachname
	public Vector<Person> getPersonByNachname(String nachname) throws IllegalArgumentException {
		return this.persMapper.findByNachname(nachname);
	}
	
	// updatePerson
	public void updatePerson(Person pers) throws IllegalArgumentException {
		persMapper.updateTeam(pers);
	}
	
	// deletePerson
	public void deletePerson(int idPerson) throws IllegalArgumentException {
		this.persMapper.delete(idPerson);
		}
	
	/*##########################################################
	 * START PROJEKT
	 #########################################################*/
	
	// createProjekt
	
	@Override
	public Projekt anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum,
			Date endDatum) throws IllegalArgumentException {

		Projekt p = new Projekt();
		
		p.setId(1);
		p.setBezeichnung(bezeichnung);
		p.setBeschreibung(beschreibung);
		p.setStartDatum(startDatum);
		p.setEndDatum(endDatum);
		p.setIdPerson(idPerson);
		
		return this.prjktMapper.insert(p);
		
	}

	@Override
	public void loeschenProjekt(Projekt p) throws IllegalArgumentException {
		// zugehoerige Ausschreibungen werden gelöscht
		Vector<Ausschreibung> ausschreibungen = this.getAusschreibungByProject
		
		if (ausschreibungen != null) {
			for (int idAusschreibung : ausschreibungen) {
				this.deleteAusschreibung(idAusschreibung);
			}
		}
		
		/* zugehörige Partnerprofile werden gelöscht / die Ausschreibungsids werden benötigt
		*und dann erst können die Partnerprofile gelöscht werden
		*ich muss ausschreibung löschen irgendwo schon definieren
		*/
		
		int idAusschreibung; // kann das richtig sein?
		
		Vector<Partnerprofil> partnerprofile = this.getPartnerprofileOfAusschreibung(idAusschreibung);
		
		if (partnerprofile != null) {
			for (int idPartnerprofil : partnerprofile){
				this.deletePartnerprofil(idPartnerprofil);
			}
		
			this.prjktMapper.delete(idProjekt);
			// gleicher Fehler
		}
	}

	@Override
	public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException {
		return this.prjktMapper.findByKey(idProjekt);
	}

	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		prjktMapper.update(p);	
		
	}
	
	// getAllProjekte
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException {
		return this.prjktMapper.findAll();
	}
	
	// getProjektByBezeichnung
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.prjktMapper.findByBezeichnung(bezeichnung);
	}
	

//SCHAUEN OB CREATEMETHODEN RICHTIGE SIND
	
	/*##########################################################
	 * START MARKTPLATZ
	 #########################################################*/
	@Override
	public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung)
			throws IllegalArgumentException {
		Marktplatz pm = new Marktplatz();
		
		pm.setId(1);
		pm.setGeschaeftsgebiet(geschaeftsgebiet);
		pm.setBezeichnung(bezeichnung);
		
		return this.mpMapper.insertMarktplatz(pm);
	}

	@Override
	public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException {
		this.mpMapper.deleteMarktplatz(pm);

		
	}

	@Override
	public Marktplatz getMarktplatzById(int idMarktplatz) throws IllegalArgumentException {
		return this.mpMapper.findMarktplatzByKey(idMarktplatz);
	}

	@Override
	public void saveMarktplatz(Marktplatz pm) throws IllegalArgumentException {
		mpMapper.updateMarktplatz(pm);// TODO Auto-generated method stub
		
	}
	

	// benötigen wir nicht
//	// getAllMarktplaetze
//	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
//		return this.mpMapper.findAll();
//	}
//	
//	// getMarktplatzByBezeichnung
//	public Vector<Marktplatz> getMarktplatzByBezeichnung(String bezeichnung) throws IllegalArgumentException {
//		return this.mpMapper.findMarktplatzByBezeichnung(bezeichnung);
//	}
//	
//	// updateMarktplatz
//	public void updateMarktplatz(Marktplatz marktplatz) throws IllegalArgumentException {
//
//	}
//	
//	// deleteMarktplatz
//	public void deleteMarktplatz(int idMarktplatz) throws IllegalArgumentException {
//		this.mpMapper.deleteMarktplatz(idMarktplatz);
//		// falscher Datentyp
//		}
	
	/*##########################################################
	 * START TEAM
	 #########################################################*/
	@Override
	public Team anlegenTeam(int idOrganisationseinheit, int idPartnerprofil, String teamName, int mitgliederAnzahl)
			throws IllegalArgumentException {
		Team t = new Team();
		
		t.setId(1);
		t.setTeamName(teamName);
		t.setMitgliederAnzahl(mitgliederAnzahl);
		
		return this.teamMapper.insertTeam(t);
	}

	@Override
	public void loeschenTeam(Team t) throws IllegalArgumentException {
		this.teamMapper.delete(idTeam);
		
	}

	@Override
	public void saveTeam(Team t) throws IllegalArgumentException {
		teamMapper.updateTeam(t);
		
	}

	@Override
	public Team getTeamById(int idTeam) throws IllegalArgumentException {
		return this.teamMapper.findTeamByKey(idTeam);
	}
	 // benötigen wir nicht
//	// getAllTeams
//	public Vector<Team> getAllTeams() throws IllegalArgumentException {
//		return this.teamMapper.findAll();
//	}
//	
//	// getTeamByTeamName
//	public Vector<Team> getTeamByTeamName(String teamName) throws IllegalArgumentException {
//		return this.teamMapper.findByTeamName(teamName);
//	}
	
		
		/*##########################################################
		 * START UNTERNEHMEN
		 #########################################################*/	

	@Override
	public Unternehmen anlegenUnternehmen(int idOrganisationseinheit, int idPartnerprofil, String firmenName)
			throws IllegalArgumentException {

		Unternehmen unternehmen = new Unternehmen();
		
		unternehmen.setId(1);
		unternehmen.setFirmenName(firmenName);
		
		return this.unternehmenMapper.insertUnternehmen(unternehmen);
	}

	@Override
	public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException {
		this.unternehmenMapper.delete(u);
		
	}

	@Override
	public Unternehmen getUnternehmenById(int idUnternehmen) {
		return this.unternehmenMapper.findUnternehmenByKey(idUnternehmen);
	}

	@Override
	public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
		unternehmenMapper.updateUnternehmen(u);
		
	}
		
		// getUnternehmenByFirmenName
		public Unternehmen getUnternehmenByFirmenName(String firmenName) 
				throws IllegalArgumentException {
			
			return this.unternehmenMapper.findByFirmenName(firmenName);
		}	
	
		
		
		/*##########################################################
		 * START BETEILIGUNG
		 #########################################################*/
		
		// was ist mit Start- und Enddatum
		@Override
		public Beteiligung anlegenBeteiligung(int idOrganisationseinheit, int idProjekt, int idBewertung)
				throws IllegalArgumentException {
			Beteiligung b = new Beteiligung();
			
			b.setId(1);
			b.setIdOrganisationseinheit(idOrganisationseinheit);
			b.setIdProjekt(idProjekt);
			b.setIdBewertung(idBewertung);
			
			return this.beteiligungMapper.insertBeteiligung(b);
	
		}

		@Override
		public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}
		// createBeteiligung
		public Beteiligung createBeteiligung(
				int idBeteiligung,
				int idProjekt,
				int idBewerbung,
				int idBewertung) throws IllegalArgumentException {
			
			
		}
		
		// getBeteiligungById
		public Beteiligung getBeteiligungById(int idBeteiligung) throws IllegalArgumentException {
			return this.beteiligungMapper.findBeteiligungById(idBeteiligung);
		}
		
		// getAllBeteiligungen
		public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException {
			return this.beteiligungMapper.findAllBeteiligungen();
		}
		
		// falsch?
		//public Vector<Beteiligung> findByOwner
		
		// updateBeteiligung
		public void updateBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
			beteiligungMapper.updateBeteiligung(beteiligung);			
		}
		
		// deleteBeteiligung
		public void deleteBeteiligung(int idBeteiligung) throws IllegalArgumentException {
			this.beteiligungMapper.deleteBeteiligung(idBeteiligung);
			// falscher Datentyp
		}
		
		
		/*##########################################################
		 * START AUSSCHREIBUNG
		 #########################################################*/
		
		// createAusschreibung
		public Ausschreibung createAusschreibung(
				String bezeichnung, 
				int idAusschreibung, 
				Date endDatum,
				String beschreibung,
				int idProjekt) throws IllegalArgumentException {
			
			Ausschreibung ausschreibung = new Ausschreibung();
			
			ausschreibung.setBezeichnung(bezeichnung);
			ausschreibung.setIdAusschreibung(idAusschreibung);
			ausschreibung.setEndDatum(endDatum);
			ausschreibung.setBeschreibung(beschreibung);
			ausschreibung.setIdProjekt(idProjekt);
			
			return this.ausschreibungMapper.insertTeam(ausschreibung);
		}
		
		// getAusschreibungById
		public Ausschreibung getAusschreibungById(int idAusschreibung) throws IllegalArgumentException {
			return this.ausschreibungMapper.findAusschreibungById(idAusschreibung);
		}
		
		// getAllAusschreibungen
		public Vector <Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException {
			return this.ausschreibungMapper.findAllAusschreibungen();
		}
		
		// findByAusschreibung???
		
		
		// updateAusschreibung
		public void updateAusschreibung(Ausschreibung ausschreibung) throws IllegalArgumentException {
			ausschreibungMapper.updateAusschreibung(ausschreibung);			
		}
		
		// deleteAusschreibung
		public void deleteAusschreibung(int idAusschreibung) throws IllegalArgumentException {
			this.ausschreibungMapper.deleteAusschreibung(idAusschreibung);
			// falscher Datentyp
		}
				
		
		/*##########################################################
		 * START BEWERBUNG
		 #########################################################*/
		
		// createBewerbung
		public Bewerbung createBewerbung(
				int idBewerbung,
				String bewerber,
				String bewerbungsText,
				Date erstellDatum
				) throws IllegalArgumentException {
			
			Bewerbung bewerbung = new Bewerbung();
			
			bewerbung.setIdBewerbung(idBewerbung); // wird zu idBusinessObject?
			bewerbung.setBewerber(bewerber);
			bewerbung.setBewerbungsText(bewerbungsText);
			bewerbung.setErstellDatum(erstellDatum);
			
			return this.bewerbungMapper.insertBewerbung(bewerbung);
		}
		
		// getBewerbungById
		public Bewerbung getBewerbungById(int idBewerbung) throws IllegalArgumentException {
			return this.bewerbungMapper.findBewerbungById(idBewerbung);
		}
		
		// getAllBewerbungen
		public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException {
			return this.bewerbungMapper.findAllBewerbungen();
		}
		
		// Klaerungsbedarf
		// getBewerbungByBewerber
		public Vector <Bewerbung> getBewerbungByBewerber
		(Person Bewerber /*richtig?*/) 
				throws IllegalArgumentException {
			return this.bewerbungMapper.findBewerbungByBewerber(Bewerber);
		}
		
		// updateBewerbung
		public void updateBewerbung(Bewerbung bewerbung) throws IllegalArgumentException {
			bewerbungMapper.updateBewerbung(bewerbung);
		}
		
		// deleteBewerbung
		public void deleteBewerbung (int idBewerbung) throws IllegalArgumentException {
			bewerbungMapper.deleteBewerbung(idBewerbung);
			// falscher Datentyp
		}

		

		@Override
		public Ausschreibung anlegenAusschreibung(int idProjekt, String bezeichnung, String beschreibung, Date endDatum)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Ausschreibung getAusschreibungbyId(int idAusschreibung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Partnerprofil anlegenPartnerprofil(int idAusschreibung, int idOrganisationseinheit)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Partnerprofil getPartnerprofilbyId(int idPartnerprofil) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void savePartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
				Date erstellDatum) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenBewerbung(Bewerbung b) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
				String employmentStatus, String ausbildung, String sprachkenntnisse) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenEigenschaft(Eigenschaft e) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveEigenschaft(Eigenschaft e) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, double fliessKommaBewertung) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenBewertung(Bewertung bewertung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Bewertung getBewertungById(int idBewertung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveBewertung(Bewertung bewertung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		

		@Override
		public Person anlegenPerson(int idOrganisationseinheit, int idPartnerprofil, char geschlecht, String vorname,
				String nachname) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenPerson(Person pe) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void savePerson(Person pe) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

	

		@Override
		public Vector<Ausschreibung> getAllAusschreibungenByOrganisationseinheit(Organisationseinheit o)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheut(Organisationseinheit o)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Vector<Beteiligung> getAllBeteiligungenToProject(Projekt p) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}
}