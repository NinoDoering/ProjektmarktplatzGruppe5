package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProjektmarktplatzAdministrationImpl extends RemoteServiceServlet implements ProjektmarktplatzAdministration {


	// private BusinessObjectMapper boMapper = null;
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper?
	private MarktplatzMapper mpMapper = null;														// bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private MarktplatzMapper pmpMapper = null;
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
		this.pmpMapper = MarktplatzMapper.marktplatzMapper();
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
			int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse) throws IllegalArgumentException {
		Eigenschaft e = new Eigenschaft();

		e.setIdPartnerprofil(idPartnerprofil); 
		e.setArbeitsgebiet(arbeitsgebiet);
		e.setAusbildung(ausbildung);
		e.setBerufserfahrungsJahre(berufserfahrungsJahre);
		e.setSprachkenntnisse(sprachkenntnisse);
		e.setEmploymentStatus(employmentStatus);
		e.setAbschluss(abschluss);

		e.setId(1);
		return this.eigMapper.insertEigenschaft(e);
	}

	// getEigenschaftById
	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException {
		return this.eigMapper.findEigenschaftByKey(idEigenschaft);
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
		
		return this.persMapper.insert(pers);
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
		persMapper.update(pers);
	}
	
	// deletePerson
	public void deletePerson(int idPerson) throws IllegalArgumentException {
		this.persMapper.delete(idPerson);
		}
	
	/*##########################################################
	 * START PROJEKT
	 #########################################################*/
	
	// createProjekt
	public Projekt createProjekt(
			int idProjekt,
			String bezeichnung,
			String beschreibung,
			Date startDatum,
			Date endDatum,
			int idPerson
			) throws IllegalArgumentException {
		Projekt projekt = new Projekt();
		
		projekt.setIdProjekt(idProjekt);
		projekt.setBezeichnung(bezeichnung);
		projekt.setBeschreibung(beschreibung);
		projekt.setStartDatum(startDatum);
		projekt.setEndDatum(endDatum);
		projekt.setIdPerson(idPerson);
		
		return this.prjktMapper.insert(projekt);
	}
	
	// getProjektById
	public Projekt getProjektById(int idProjekt) throws IllegalArgumentException {
		return this.prjktMapper.findByKey(idProjekt);
	}
	
	// getAllProjekte
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException {
		return this.prjktMapper.findAll();
	}
	
	// getProjektByBezeichnung
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.prjktMapper.findByBezeichnung(bezeichnung);
	}
	
	// updateProjekt
	public void updateProjekt(Projekt projekt) throws IllegalArgumentException {
		prjktMapper.update(projekt);
	}
	
	//deleteProjekt (getAusschreibungenOfProjekt() muss noch hinzugefuegt werden
	public void deleteProjekt(int idProjekt) throws IllegalArgumentException {
		
		// zugehoerige Ausschreibungen werden gelöscht
		Vektor<Ausschreibung> ausschreibungen = this.getAusschreibungenOfProjekt(idProjekt);
		
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
		
		Vektor<Partnerprofil> partnerprofile = this.getPartnerprofileOfAusschreibung(idAusschreibung);
		
		if (partnerprofile != null) {
			for (int idPartnerprofil : partnerprofile){
				this.deletePartnerprofil(idPartnerprofil);
			}
		}
		
		this.prjktMapper.delete(idProjekt);
		// gleicher Fehler
		}
	
	/*##########################################################
	 * START MARKTPLATZ
	 #########################################################*/
	
	//createMarktplatz
	public Marktplatz createMarktplatz(
			int idMarktplatz,
			String geschaeftsgebiet,
			String bezeichnung
			) throws IllegalArgumentException{
		Marktplatz marktplatz = new Marktplatz();
		
		marktplatz.setIdMarktplatz(idMarktplatz);
		marktplatz.setGeschaeftsgebiet(geschaeftsgebiet);
		marktplatz.setBezeichnung(bezeichnung);
		
		return this.mpMapper.insertMarktplatz(marktplatz);
	}
	
	// getMarktplatzById
	public Marktplatz findByKey(int idMarktplatz) throws IllegalArgumentException {
		return this.mpMapper.findMarktplatzById(idMarktplatz);
	}
	
	// getAllMarktplaetze
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
		return this.mpMapper.findAll();
	}
	
	// getMarktplatzByBezeichnung
	public Vector<Marktplatz> getMarktplatzByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.mpMapper.findMarktplatzByBezeichnung(bezeichnung);
	}
	
	// updateMarktplatz
	public void updateMarktplatz(Marktplatz marktplatz) throws IllegalArgumentException {
		mpMapper.updateMarktplatz(marktplatz);
	}
	
	// deleteMarktplatz
	public void deleteMarktplatz(int idMarktplatz) throws IllegalArgumentException {
		this.mpMapper.deleteMarktplatz(idMarktplatz);
		// falscher Datentyp
		}
	
	/*##########################################################
	 * START TEAM
	 #########################################################*/
	
	// createTeam
	public Team createTeam(
			int idTeam,
			String teamName,
			int mitgliederAnzahl
			) throws IllegalArgumentException {
		
		Team team = new Team();
		
		team.setId(idTeam);
		team.setTeamName(teamName);
		team.setMitgliederAnzahl(mitgliederAnzahl);
		
		return this.teamMapper.insert(team);
	}
	
	// getTeamById
	public Team getTeamById(int idTeam) throws IllegalArgumentException {
		return this.teamMapper.findbyKey(idTeam);
	}
	
	// getAllTeams
	public Vector<Team> getAllTeams() throws IllegalArgumentException {
		return this.teamMapper.findAll();
	}
	
	// getTeamByTeamName
	public Vector<Team> getTeamByTeamName(String teamName) throws IllegalArgumentException {
		return this.teamMapper.findByTeamName(teamName);
	}
	
	// updateTeam
		public void updateTeam(Team team) throws IllegalArgumentException {
			teamMapper.update(team);
		}
		
	// deleteTeam
		public void deleteTeam(int idTeam) throws IllegalArgumentException {
			this.teamMapper.delete(idTeam);
			// falscher Datentyp
		}
		
		/*##########################################################
		 * START UNTERNEHMEN
		 #########################################################*/	

		// createUnternehmen
		public Unternehmen createUnternehmen(
				String firmenName,
				int idUnternehmen
				) throws IllegalArgumentException {
	
			Unternehmen unternehmen = new Unternehmen();
			
			unternehmen.setIdUnternehmen(idUnternehmen);
			unternehmen.setFirmenName(firmenName);
			
			return this.unternehmenMapper.insert(unternehmen);
		}
		
		// getUnternehmenByFirmenName
		public Unternehmen getUnternehmenByFirmenName(String firmenName) 
				throws IllegalArgumentException {
			
			return this.unternehmenMapper.findByFirmenName(firmenName);
		}
		
		// getUnternehmenById? ############################################
		
		
		// updateUnternehmen
		public void updateUnternehmen(Unternehmen unternehmen) throws IllegalArgumentException {
			unternehmenMapper.update(unternehmen);
		}
		
		/* deleteUnternehmen ----- 
			Hier muss doch auch noch dann die idUnternehmen Variable erstellt werden!!
			*/
		public void deleteUnternehmen(int idUnternehmen) throws IllegalArgumentException {
			this.unternehmenMapper.delete(idUnternehmen);
		}
		
		/*##########################################################
		 * START BETEILIGUNG
		 #########################################################*/
		
		// createBeteiligung
		public Beteiligung createBeteiligung(
				int idBeteiligung,
				int idProjekt,
				int idBewerbung,
				int idBewertung) throws IllegalArgumentException {
			
			Beteiligung beteiligung = new Beteiligung();
			
			beteiligung.setIdBeteiligung(idBeteiligung);
			beteiligung.setIdProjekt(idProjekt);
			beteiligung.setIdBewerbung(idBewerbung);
			beteiligung.setIdBewertung(idBewertung);
			
			return this.beteiligungMapper.insertBeteiligung(beteiligung);
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
			
			return this.ausschreibungMapper.insert(ausschreibung);
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

		// Aus der ProjektmarktplatzAdiministration implementierte Methoden
		@Override
		public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Marktplatz getMarktplatzById(int idMarktplatz) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveMarktplatz(Marktplatz pm) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Projekt anlegenProjekt(int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum,
				Date endDatum) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenProjekt(Projekt p) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveProjekt(Projekt p) throws IllegalArgumentException {
		
			this.prjktMapper.update(p);// TODO Auto-generated method stub
			
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
		public Beteiligung anlegenBeteiligung(int idOrganisationseinheit, int idProjekt, int idBewertung)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
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
		public Team anlegenTeam(int idOrganisationseinheit, int idPartnerprofil, String teamName, int mitgliederAnzahl)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenTeam(Team t) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveTeam(Team t) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Unternehmen anlegenUnternehmen(int idOrganisationseinheit, int idPartnerprofil, String firmenName)
				throws IllegalArgumentException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Unternehmen getUnternehmenById(int idUnternehmen) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveUnternehmen(Unternehmen u) throws IllegalArgumentException {
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