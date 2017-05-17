package de.hdm.itprojekt.server;

import java.util.ArrayList;
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
															// bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private ProjektmarktplatzMapper pmpMapper = null;
	private TeamMapper teamMapper = null;
	private UnternehmenMapper unternehmenMapper = null;

	// No-Argument-Konstruktor
	public ProjektmarktplatzAdministrationImpl() throws IllegalArgumentException {

	}

	// Initialisierung
	public void init() throws IllegalArgumentException {

		// this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse
		// wird gel�scht)
		this.eigMapper = EigenschaftMapper.eigenschaftMapper();
		this.orgaMapper = OrganisationseinheitMapper.organisationseinheitMapper();
		this.ppMapper = PartnerprofilMapper.partnerprofilMapper();
		this.persMapper = PersonMapper.personMapper();
		this.prjktMapper = ProjektMapper.projektMapper();
		this.pmpMapper = ProjektmarktplatzMapper.projektmarktplatzMapper();
		this.teamMapper = TeamMapper.teamMapper();
		this.unternehmenMapper = UnternehmenMapper.unternehmenMapper();
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
	
	// 
}
