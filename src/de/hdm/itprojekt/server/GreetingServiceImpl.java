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
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;

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


		
		//this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird gelï¿½scht)
	
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper?
	private MarktplatzMapper mpMapper = null;														// bestehen?
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





	@Override
	
	
// --------Person------	
	public Person findPersonByKey(int key) 
			
			//throws IllegalArgumentException 
	{
		// TODO Auto-generated method stub
		return this.persMapper.findPersonByKey(key);
		
	}

//--------Ausschreibung------	

	@Override
	public Ausschreibung findAusschreibungByKey(int idAusschreibung) {
		// TODO Auto-generated method stub
		return this.ausschreibungMapper.findAusschreibungByKey(idAusschreibung);
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
	
	
	
	

//-------Marktplatz-------	
	
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException {
		return this.mpMapper.findAllMarktplatz();
	}


	
	
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
	public Marktplatz get1Marktplatz() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}




// ------------Projekt-----------
	
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

	//------------Bewerbungen--------------
	public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException {
		return this.bewerbungMapper.findAllBewerbungen();
	}

		
}

