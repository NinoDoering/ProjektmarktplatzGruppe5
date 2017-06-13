package de.hdm.itprojekt.shared;
import de.hdm.itprojekt.shared.bo.*;

import java.util.Vector;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import de.hdm.itprojekt.shared.report.*;




@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	public void setPerson() throws IllegalArgumentException;
	
	public  abstract AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport (Organisationseinheit o) throws IllegalArgumentException;

	public Vector<Ausschreibung> getAusschreibungByMatchingPartnerprofil(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException;
	
	public abstract AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport () throws IllegalArgumentException;
	
	public abstract AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport (Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport (Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(
			
			Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	
	public abstract FanIn createFanInAnalyse (Organisationseinheit o) throws IllegalArgumentException;
	
	ProjektverflechtungReport createProjektverflechtungenReport(int id);
	
	public Person findPersonByKey(int id) throws IllegalArgumentException;
	
	public Team findTeamByKey(int id) throws IllegalArgumentException;
	
	public Unternehmen findUnternehmenByKey (int id) throws IllegalArgumentException;

	
	
	
}