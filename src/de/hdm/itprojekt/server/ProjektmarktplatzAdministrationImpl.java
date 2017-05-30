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

	//test 123
	//Hallo
	
	
	// private BusinessObjectMapper boMapper = null;
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper
	private MarktplatzMapper mpMapper = null;														// bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private ProjektmarktplatzMapper pmpMapper = null;
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
		this.pmpMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
		this.ausschreibungMapper = AusschreibungMapper.ausschreibungmapper(); 
											//.ausschreibungMapper muss das heißen auch in Mapperklasse
		this.bewerbungMapper = BewerbungMapper.bewerbungmapper();//gleicher Fehler!!!!!
		this.beteiligungMapper = BeteiligungMapper.beteiligungMapper(); // gleicher Fall
		}

	// createEigenschaft
	public Eigenschaft createEigenschaft(
			/* int idEigenschaft, */
			String arbeitsgebiet, String ausbildung, float berufserfahrungsJahre, String sprachkenntnisse,
			String employmentStatus, String abschluss) throws IllegalArgumentException {
		Eigenschaft eig = new Eigenschaft();

		/* eig.setIdEigenschaft(idEigenschaft); Ist diese Methode richtig? */
		eig.setArbeitsgebiet(arbeitsgebiet);
		eig.setAusbildung(ausbildung);
		eig.setBerufserfahrungsJahre(berufserfahrungsJahre);
		eig.setSprachkenntnisse(sprachkenntnisse);
		eig.setEmploymentStatus(employmentStatus);
		eig.setAbschluss(abschluss);

		return this.eigMapper.insert(eig);
	}

	// getEigenschaftById
	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException {
		return this.eigMapper.findByKey(idEigenschaft);
	}

	// getAll
	public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException {
		return this.eigMapper.findAll();
	}
	
	// updateEigenschaft
	public void updateEigenschaft(Eigenschaft eig) throws IllegalArgumentException {
		eigMapper.update(eig);		
	}
	
	// deleteEigenschaft
	public void deleteEigenschaft(int idEigenschaft) throws IllegalArgumentException {
		this.eigMapper.delete(idEigenschaft);
	}
	
	
// START ORGANISATIONSEINHEIT____________________________________________
	
	
	// createOrganisationseinheit
	public Organisationseinheit createOrganisationseinheit(int idOrganisationseinheit) throws IllegalArgumentException {
		Organisationseinheit orgaEinheit = new Organisationseinheit();
		orgaEinheit.setIdOrganisationseinheit(idOrganisationseinheit);

		return this.orgaMapper.insert(orgaEinheit);
	}

	// getOrganisationseinheitById
	public Organisationseinheit getOrganisationseinheitById(int idOrganisationseinheit) throws IllegalArgumentException {
		return this.orgaMapper.findByKey(idOrganisationseinheit);
	}
	
	// updateOrganisationseinheit    #############################################
	//############################################################################
	
	
	// deleteOrganisationseinheit
	public void deleteOrganisationseinheit(int idOrganisationseinheit) throws IllegalArgumentException {
		this.orgaMapper.delete(idOrganisationseinheit);
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
			Person projektleiter
			) throws IllegalArgumentException {
		Projekt projekt = new Projekt();
		
		projekt.setIdProjekt(idProjekt);
		projekt.setBezeichnung(bezeichnung);
		projekt.setBeschreibung(beschreibung);
		projekt.setStartDatum(startDatum);
		projekt.setEndDatum(endDatum);
		projekt.setProjektleiter(projektleiter);
		
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
	
	//deleteProjekt
	public void deleteProjekt(int idProjekt) throws IllegalArgumentException {
		this.prjktMapper.delete(idProjekt);
		}
	
	/*##########################################################
	 * START MARKTPLATZ
	 #########################################################*/
	
	//createMarktplatz
	public void createMarktplatz(
			int idProjektmarktplatz,
			String geschaeftsgebiet,
			String bezeichnung
			) throws IllegalArgumentException{
		Marktplatz marktplatz = new Marktplatz();
		
		marktplatz.setIdMarktplatz(idMarktplatz);
		marktplatz.setGeschaeftsgebiet(geschaeftsgebiet);
		marktplatz.setBezeichnung(bezeichnung);
		
		return this.pmpMapper.insert(marktplatz);
	}
	
	// getMarktplatzById
	public Marktplatz findByKey(int idMarktplatz) throws IllegalArgumentException {
		return this.mpMapper.findByKey(idMarktplatz);
	}
	
	// getAllMarktplaetze
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
		return this.mpMapper.findAll();
	}
	
	// getMarktplatzByBezeichnung
	public Vector<Marktplatz> getMarktplatzByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.mpMapper.findByBezeichnung(bezeichnung);
	}
	
	// updateMarktplatz
	public void updateMarktplatz(Marktplatz marktplatz) throws IllegalArgumentException {
		mpMapper.update(marktplatz);
	}
	
	// deleteMarktplatz
	public void deleteMarktplatz(int idMarktplatz) throws IllegalArgumentException {
		this.mpMapper.delete(idMarktplatz);
		}
	
	/*##########################################################
	 * START TEAM
	 #########################################################*/
	
	// createTeam
	public void createTeam(
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
		}
		
		/*##########################################################
		 * START UNTERNEHMEN
		 #########################################################*/	

		// createUnternehmen
		public void createUnternehmen(
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
			
			return this.getUnternehmenByFirmenName(firmenName);
		}
		
		// getUnternehmenById? ############################################
		
		
		// updateUnternehmen
		public void updateUnternehmen(Unternehmen unternehmen) throws IllegalArgumentException {
			teamMapper.update(unternehmen);
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
				int idPerson) throws IllegalArgumentException {
			
			Beteiligung beteiligung = new Beteiligung();
			
			beteiligung.setIdBeteiligung(idBeteiligung);
			beteiligung.setIdProjekt(idProjekt);
			beteiligung.setIdPerson(idPerson);
			
			return this.beteiligungMapper.insert(beteiligung);
		}
		
		// getBeteiligungById
		public Beteiligung getBeteiligungById(int idBeteiligung) throws IllegalArgumentException {
			return this.beteiligungMapper.findByKey(idBeteiligung);
		}
		
		// getAllBeteiligungen
		public Vector<Beteiligung> getAllBeteiligungen() throws IllegalArgumentException {
			return this.beteiligungMapper.findAll();
		}
		
		// falsch?
		//public Vector<Beteiligung> findByOwner
}