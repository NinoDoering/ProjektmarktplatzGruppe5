//Inhalt OK aber alle drei Klassen müssen in Greet...
//Inhalt OK aber alle drei Klassen müssen in Greet...
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
	private BewertungMapper bewertungMapper = null;

	// No-Argument-Konstruktor
	public ProjektmarktplatzAdministrationImpl() throws IllegalArgumentException {

	}

	// Initialisierung
	public void init() throws IllegalArgumentException {

		// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse
		// wird gelöscht)
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
		this.bewertungMapper = BewertungMapper.bewertungMapper();									//.ausschreibungMapper muss das heißen auch in Mapperklasse
		this.bewerbungMapper = BewerbungMapper.bewerbungmapper();//gleicher Fehler!!!!!
		this.beteiligungMapper = BeteiligungMapper.beteiligungMapper(); // gleicher Fall
		}

	
	/*##########################################################
	 * START EIGENSCHAFT
	 #########################################################*/
	
	// createEigenschaft
	
	@Override
	public Eigenschaft anlegenEigenschaft(int idPartnerprofil, String arbeitsgebiet, float berufserfahrungsJahre,
			String employmentStatus, String ausbildung, String abschluss, String sprachkenntnisse) throws IllegalArgumentException {
		Eigenschaft e = new Eigenschaft();

		e.setId(1); 
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


	public Eigenschaft getEigenschaftById(int idEigenschaft) throws IllegalArgumentException {
		return this.eigMapper.findEigenschaftByKey(idEigenschaft);
	}

//	// getAll nicht benötigt
//	public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException {
//		return this.eigMapper.findAllEigenschaften();
//	}
	
	
	
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

	@Override
	public void loeschenPartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		
		// Methode muss noch erstellt werden!!
				Vector<Eigenschaft> e = this.getEigenschaftByPartnerprofil(pp);
				
				if(e != null){
					for(Eigenschaft eigenschaft : e){
						this.eigMapper.deleteEigenschaft(e);
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

	@Override
	public Partnerprofil getPartnerprofilbyId(int idPartnerprofil) throws IllegalArgumentException {
		return this.ppMapper.findPartnerprofilByKey(idPartnerprofil);
	}

	@Override
	public void savePartnerprofil(Partnerprofil pp) throws IllegalArgumentException {
		ppMapper.update(pp);
	}
	
	// getAllPartnerprofile
	public Vector <Partnerprofil> getAllPartnerprofile() throws IllegalArgumentException {
		return this.ppMapper.findAllProjekt();
	}
	
	
	/*##########################################################
	 * START PERSON
	 #########################################################*/
	
	@Override
	public Person anlegenPerson(int idUnternehmen, int idTeam, int idPartnerprofil, String vorname,
			String nachname) throws IllegalArgumentException {
		Person pe = new Person();
		
		pe.setId(1);
		pe.setIdUnternehmen(idUnternehmen);
		pe.setIdTeam(idTeam);
		pe.setIdPartnerprofil(idPartnerprofil);
		pe.setVorname(vorname);
		pe.setNachname(nachname);
		
		
		return this.persMapper.insertPerson(pe);
	}

	@Override
	public void loeschenPerson(Person pe) throws IllegalArgumentException {
		
		Vector<Bewerbung> b = this.getBewerbungByBewerber(pe);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(pe);
		Vector<Projekt> p = this.getProjektByPerson(pe);
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(pe); // muss noch eingearbeitet werden
		// PP nicht vorhanden in Mapper
		
		// Bewerbungen der Person löschen
		if(b != null){
			for(Bewerbung bewerbung : b){
				this.loeschenBewerbung(bewerbung);
			}
		}
		
		// zugehörige Beteiligungen löschen
		if (beteiligung != null){
			for (Beteiligung be : beteiligung){
				this.loeschenBeteiligung(be);
			}	
		}	
		
		// von Person erstellte Projekte löschen
		if(p != null){
			for(Projekt projekt : p){
				this.loeschenProjekt(projekt);
			}
		}
		
		// Partnerprofil der Person löschen
		if (pp != null){
			this.loeschenPartnerprofil(pp);
		}
		
		// Person entgültig löschen
		this.persMapper.deletePerson(pe);
	
	}
	
	
	//########################################################################################################
	//########################################################################################################
	
	
	// getPartnerprofilByOrganisationseinheit ----- DIE MUSS NOCH BEARBEITET WERDEN
	public Partnerprofil getPartnerprofilByOrganisationseinheit(Person pe) {
		
		return null;
	}

	//########################################################################################################
	
	
	
	// getProjektByPerson
	public Vector<Projekt> getProjektByPerson(Person pe) {
		
		Vector<Projekt> result = new Vector<Projekt>();

		if (pe != null && this.prjktMapper != null) {
			Vector<Projekt> projekt = this.prjktMapper.findProjektbyPerson(pe.getId());

			if (pe != null) {
				result.addAll(projekt);
			}
		}
		
		return result;
	}

	// getBeteiligungByBeteiligter ---------- Kontrolle richtiger Datentyp
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

	// getBewerbungByBewerber
	public Vector<Bewerbung> getBewerbungByBewerber(Person pe){
		
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		if (pe != null && this.bewerbungMapper != null) {
			Vector<Bewerbung> bewerbung = this.bewerbungMapper.findBewerbungByBewerber(pe.getId());

			if (pe != null) {
				result.addAll(bewerbung);
			}
		}
		
		return result;
	}

	@Override
	public void savePerson(Person pe) throws IllegalArgumentException {
		persMapper.updatePerson(pe);
		
	}
	
	@Override
	public Person getPersonById(int idPerson) throws IllegalArgumentException {
		return this.persMapper.findPersonByKey(idPerson);
		// String in PersonMapper
	}
	
	//wird nicht benötigt
//	// getAllPersons
//	public Vector<Person> getAllPersons() throws IllegalArgumentException {
//		return this.persMapper.findAll();
//	}
//	
//	// getPersonByNachname
//	public Vector<Person> getPersonByNachname(String nachname) throws IllegalArgumentException {
//		return this.persMapper.findPersonByNachname(nachname);
//	}
	

	
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
		p.setIdMarktplatz(idMarktplatz);
		
		return this.prjktMapper.insertProjekt(p);		
		}

	// loeschenProjekt
	@Override
	public void loeschenProjekt(Projekt p) throws IllegalArgumentException {
		
		Vector<Ausschreibung> a = this.getAusschreibungByProjekt(p);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByProjekt(p);
		
		// zugehörige Ausschreibungen löschen
		if (a != null) {
			for(Ausschreibung ausschreibung : a){
				this.loeschenAusschreibung(ausschreibung);
			}
		}
		
		// zugehörige Beteiligungen löschen
		if (beteiligung != null) {
			for(Beteiligung b : beteiligung){
				this.loeschenBeteiligung(b);
			}
		}
		
		this.prjktMapper.deleteProjekt(p);
		
	}

	// getBeteiligungByProjekt
	public Vector<Beteiligung> getBeteiligungByProjekt(Projekt p) {
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		if (p != null && this.beteiligungMapper != null) {
			Vector<Beteiligung> beteiligung = this.beteiligungMapper.findBeteiligungByProjekt(p.getId());

			if (beteiligung != null) {
				result.addAll(beteiligung);
			}
		}
		return result;
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

	@Override
	public Projekt getProjektbyId(int idProjekt) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByKey(idProjekt);
	}

	@Override
	public void saveProjekt(Projekt p) throws IllegalArgumentException {
		prjktMapper.update(p);	
		
	}
	
	// getAllProjekte
	public Vector<Projekt> getAllProjekte() throws IllegalArgumentException {
		return this.prjktMapper.findAllProjekt();
	}
	
	// getProjektByBezeichnung
	public Vector<Projekt> getProjektByBezeichnung(String bezeichnung) throws IllegalArgumentException {
		return this.prjktMapper.findProjektByBezeichnung(bezeichnung);
	}
	

//SCHAUEN OB CREATE-METHODEN RICHTIG SIND
	
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

	// loeschenMarktplatz ------------- Was ist mit weiteren 
	// Dingen wie Ausschreibung, Bewerbung usw.?
	@Override
	public void loeschenMarktplatz(Marktplatz pm) throws IllegalArgumentException {
		
		Vector <Projekt> p = this.getProjektbyMarktplatz(pm);
		
		// zugehoerige Projekte loeschen
		if(p != null){
			for(Projekt projekt : p){
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

	
	// loeschenTeam --- wird Team genauso wie Person behandelt?
	@Override
	public void loeschenTeam(Team t) throws IllegalArgumentException {
		
		Vector<Bewerbung> b = this.getBewerbungByBewerber(t);
		Vector<Projekt> p = this.getProjektByPerson(t);
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(t); // überarbeiten
		Vector<Beteiligung> beteiligung = this.getBeteiligungByBeteiligter(t); // --- richtiger Datentyp?
		
		// zugehoerige Beteiligungen loeschen
		if (beteiligung != null) {
			for(Beteiligung b : beteiligung){
				this.loeschenBeteiligung(b);
			}
		}
		
		// zugehoerige Partnerprofile loeschen
		if (pp != null) {
				this.loeschenPartnerprofil(pp);
		}
		
		
		this.teamMapper.deleteTeam(t);
		
	}
	//###########################################################################################
	//############################################################################################
	
	
	
	
	// getPartnerprofilByOrganisationseinheit
	public Partnerprofil getPartnerprofilByOrganisationseinheit(Organisationseinheit o) throws IllegalArgumentException {
		
		// GEMEINSAM BEARBEITEN         ###################################################################
	}
	
	
	
	

	// getBeteiligungByBeteiligter
	//public Vector<Beteiligung> getBeteiligungByBeteiligter(Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		//return null;
	//}

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
	public Unternehmen anlegenUnternehmen(int idPartnerprofil, String firmenName)
			throws IllegalArgumentException {

		Unternehmen u = new Unternehmen();
		
		u.setId(1);
		u.setFirmenName(firmenName);
		u.setIdPartnerprofil(idPartnerprofil);
		
		return this.unternehmenMapper.insertUnternehmen(u);
	}

	// loeschenUnternehmen ---------- gemeinsam durchgehen
	@Override
	public void loeschenUnternehmen(Unternehmen u) throws IllegalArgumentException {
		
		Partnerprofil pp = this.getPartnerprofilByOrganisationseinheit(u);
		Vector<Beteiligung> beteiligung = this.getBeteiligungByOrganisationseinheit(u);
		Vector<Bewerbung> b = this.getAllBewerbungenByOrganisationseinheit(u); // ------ falsch? 
																				// Weil nicht in Klasse vorhanden
		
		this.unternehmenMapper.deleteUnternehmen(u);
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
	public Unternehmen getUnternehmenByFirmenName(String firmenName) throws IllegalArgumentException {

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
			b.setIdBeteiligter(idOrganisationseinheit);
			b.setIdProjekt(idProjekt);
			b.setIdBewertung(idBewertung);
			
			return this.beteiligungMapper.insertBeteiligung(b);
	
		}

		// loeschenBeteiligung --- vollständig
		@Override
		public void loeschenBeteiligung(Beteiligung beteiligung) throws IllegalArgumentException {
			this.beteiligungMapper.deleteBeteiligung(beteiligung);
			
		}

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
		
		// falsch?
		//public Vector<Beteiligung> findByOwner
		
		
		
		/*##########################################################
		 * START AUSSCHREIBUNG
		 #########################################################*/
		
		@Override
		public Ausschreibung anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung, Date endDatum)
				throws IllegalArgumentException {
			Ausschreibung a = new Ausschreibung();
			
			a.setId(1);
			a.setBezeichnung(bezeichnung);
			a.setEndDatum(endDatum);
			a.setBeschreibung(beschreibung);
			a.setIdProjekt(idProjekt);
			
			return this.ausschreibungMapper.insertAusschreibung(a);
		}

		@Override
		public void loeschenAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			
			Vector<Bewerbung> bewerbungen = this.getAllBewerbungenByAusschreibung(a);
			
			if (bewerbungen != null) {
				for (Bewerbung b : bewerbungen) {

					this.bewerbungMapper.deleteBewerbung(b);
			
				}
			}
			
			this.ausschreibungMapper.deleteAusschreibung(a);
			
		}
		
		public Vector<Bewerbung> getAllBewerbungenByAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			
			Vector<Bewerbung> result = new Vector<Bewerbung>();

			if (a != null && this.bewerbungMapper != null) {
				Vector<Bewerbung> b = this.bewerbungMapper.findBewerbungByAusschreibung(a.getId()); // Was ist hier?

				if (b != null) {
					result.addAll(b);
				}
			}
			
			return result;
		}

		@Override
		public Ausschreibung getAusschreibungbyId(int idAusschreibung) throws IllegalArgumentException {
			return this.ausschreibungMapper.findAusschreibungByKey(idAusschreibung);
		}

		@Override
		public void saveAusschreibung(Ausschreibung a) throws IllegalArgumentException {
			ausschreibungMapper.updateAusschreibung(a);		
			
		}

		
	
		// getAllAusschreibungen
		public Vector <Ausschreibung> getAllAusschreibungen() throws IllegalArgumentException {
			return this.ausschreibungMapper.findAllAusschreibungen();
		}
		
		// findByAusschreibung???
		
	
	
		
		/*##########################################################
		 * START BEWERBUNG
		 #########################################################*/

		@Override
		public Bewerbung anlegenBewerbung(int idOrganisationseinheit, int idAusschreibung, String bewerbungstext,
				Date erstellDatum) throws IllegalArgumentException {
			Bewerbung b = new Bewerbung();
			
			b.setId(1); 
			b.setIdOrganisationseinheit(idOrganisationseinheit);
			b.setIdAusschreibung(idAusschreibung);
			b.setBewerbungsText(bewerbungstext);
			b.setErstellDatum(erstellDatum);
			
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

		@Override
		public Bewerbung getBewerbungbyId(int idBewerbung) throws IllegalArgumentException {
			return this.bewerbungMapper.findBewerbungByKey(idBewerbung);
		}

		@Override
		public void saveBewerbung(Bewerbung b) throws IllegalArgumentException {
			bewerbungMapper.updateBewerbung(b);
			
		}

		
		// getAllBewerbungen
		public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException {
			return this.bewerbungMapper.findAllBewerbungen();
		}
		

		

	

		



		/*##########################################################
		 * START BEWERTUNG
		 #########################################################*/

		@Override
		public Bewertung anlegenBewertung(int idBewerbung, String textuelleBewertung, float fliessKommaBewertung) {
			Bewertung bewertung = new Bewertung();
			
			bewertung.setId(1); 
			bewertung.setIdBewerbung(idBewerbung);
			bewertung.setTextuelleBewertung(textuelleBewertung);
			bewertung.setFliessKommaBewertung(fliessKommaBewertung);
	
			
			return this.bewertungMapper.insertBewertung(bewertung);
		}

		@Override
		public void loeschenBewertung(Bewertung bewertung) throws IllegalArgumentException {
			return this.bewertungMapper.deleteBewertung(idBewertung);
			
		}

		@Override
		public Bewertung getBewertungById(int idBewertung) throws IllegalArgumentException {
			return this.bewertungMapper.findBewertungByKey(idBewertung);
		}

		@Override
		public void saveBewertung(Bewertung bewertung) throws IllegalArgumentException {
			bewertungMapper.updateBewertung(bewertung);
			
		}

		

		

	
//
//		@Override
//		public Vector<Ausschreibung> getAllAusschreibungenByOrganisationseinheit(Organisationseinheit o)
//				throws IllegalArgumentException {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Vector<Ausschreibung> getAllAusschreibungByPartnerprofil(Partnerprofil pp)
//				throws IllegalArgumentException {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override

//
//		@Override
//		public Vector<Bewerbung> getAllBewerbungenByOrganisationseinheit(Organisationseinheit o)
//				throws IllegalArgumentException {
//			return this.bewerbungMapper.findBewerbungByBewerber(o);
//		}
//		
//
//		@Override
//		public Vector<Beteiligung> getAllBeteiligungenToProject(Projekt p) throws IllegalArgumentException {
//			return this.beteiligungMapper.findBeteiligungByProjekt(p);
//		}
}