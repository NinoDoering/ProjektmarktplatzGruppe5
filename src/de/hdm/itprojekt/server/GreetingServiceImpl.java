package de.hdm.itprojekt.server;

import de.hdm.itprojekt.client.GreetingService;
import de.hdm.itprojekt.client.Projektmarktplatz;
import de.hdm.itprojekt.server.db.AusschreibungMapper;
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
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//Rueckgaengig
//ZweiterVersuch

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

public void init() throws IllegalArgumentException{
		
		//this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird gel�scht)
	
		this.persMapper = PersonMapper.personMapper();
		this.marktMapper = MarktplatzMapper.marktplatzMapper(); 
		this.ausMapper = AusschreibungMapper.ausschreibungMapper(); 
	}
	

	private MarktplatzMapper marktMapper = null; 
	private PersonMapper persMapper = null;
	private AusschreibungMapper ausMapper = null; 


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
		return this.ausMapper.findAusschreibungByKey(idAusschreibung);
	}
	
	//-------Marktplatz--------
	public Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung) 
		throws IllegalArgumentException {
		Marktplatz pm = new Marktplatz(); 
		
		pm.setId(1);
		pm.setGeschaeftsgebiet(geschaeftsgebiet);
		pm.setBezeichnung(bezeichnung);
		
		 return this.marktMapper.insertMarktplatz(pm); 
	}
	
}

