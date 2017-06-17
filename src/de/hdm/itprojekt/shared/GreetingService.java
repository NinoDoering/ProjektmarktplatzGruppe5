package de.hdm.itprojekt.shared;
import de.hdm.itprojekt.server.db.*;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Bewerbung;
import de.hdm.itprojekt.shared.bo.Marktplatz;
import de.hdm.itprojekt.shared.bo.Person;
import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;

//Rueckgaengig
//zweiterVersuch

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService  {
	
	

	
	

	

	// --------Person------	
	Person findPersonByKey(int key);
	
	
	//--------Ausschreibung------	
	Ausschreibung findAusschreibungByKey(int idAusschreibung);
	
	public Ausschreibung anlegenAusschreibung(int idAusschreibender, int idProjekt, String bezeichnung, String beschreibung, Date endDatum,
			int idPartnerprofil, Status ausschreibungsstatus)
			throws IllegalArgumentException;
	
	
	//---------Marktplatz-----
	
	
	public Vector<Marktplatz> getAllMarktplaetze() throws IllegalArgumentException;
	
	
	Marktplatz anlegenMarktplatz(String geschaeftsgebiet, String bezeichnung)
				throws IllegalArgumentException;
	
	public void loeschenMarktplatz(Marktplatz pm)throws IllegalArgumentException;
	
	public Marktplatz getMarktplatzById (int idMarktplatz) throws IllegalArgumentException; 
	
	public void saveMarktplatz (Marktplatz pm) throws IllegalArgumentException;
	
	public Vector<Projekt> getProjektbyMarktplatz(Marktplatz pm) throws IllegalArgumentException; 

	public Marktplatz get1Marktplatz() throws IllegalArgumentException;
	
	
	public Vector<Ausschreibung> getAusschreibungByProjekt(Projekt p) throws IllegalArgumentException;
	
	
	//------ Projekt -----
	
	public Projekt anlegenProjekt(int idPerson, int idMarktplatz, String beschreibung, String bezeichnung, Date startDatum, Date endDatum)
			throws IllegalArgumentException;
	//-------Bewerbungen-------
	public Vector<Bewerbung> getAllBewerbungen() throws IllegalArgumentException;

}
