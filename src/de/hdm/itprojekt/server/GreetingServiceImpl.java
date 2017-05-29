package de.hdm.itprojekt.server;

import de.hdm.itprojekt.client.GreetingService;
import de.hdm.itprojekt.client.Projektmarktplatz;
import de.hdm.itprojekt.server.db.EigenschaftMapper;
import de.hdm.itprojekt.server.db.OrganisationseinheitMapper;
import de.hdm.itprojekt.server.db.PartnerprofilMapper;
import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.server.db.ProjektMapper;

import de.hdm.itprojekt.server.db.TeamMapper;
import de.hdm.itprojekt.server.db.UnternehmenMapper;
import de.hdm.itprojekt.shared.FieldVerifier;
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
		
		//this.boMapper = BusinessObjectMapper.businessObjectMapper(); (Klasse wird gelöscht)
	
		this.persMapper = PersonMapper.personMapper();
		
	}
	
	private PersonMapper persMapper = null;
	
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br> Java"
				;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public Person findPersonByKey(String key) 
			
			//throws IllegalArgumentException 
	{
		// TODO Auto-generated method stub
		return this.persMapper.findPersonByKey(key);
		
	}

//	@Override
//	public String showPersonByKey(int idPerson) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public String showPersonByKey(Person personKeyFromDBServer) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Vector<Projektmarktplatz> getProjektmarktplatzAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Person showPersonByKey(String text) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	// erneut wichtig!!!!
}
