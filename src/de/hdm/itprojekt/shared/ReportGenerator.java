package de.hdm.itprojekt.shared;
import de.hdm.itprojekt.shared.bo.*;

import java.util.Vector;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import de.hdm.itprojekt.shared.report.*;




@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	
	AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport (Organisationseinheit o) throws IllegalArgumentException;

	public AllAusschreibungenByPartnerprofilReport getAusschreibungByMatchingPartnerprofil(Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException;
	
	public abstract AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport (int id) throws IllegalArgumentException;
	
	public abstract AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport (Organisationseinheit o) throws IllegalArgumentException;
	
	public abstract AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport (int id) throws IllegalArgumentException;
	
	public abstract AllBewerbungenWithAusschreibungenReport createAllBewerbungenWithAusschreibungenReport(Organisationseinheit o) throws IllegalArgumentException;
	
//	public abstract AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(int idAusschreibung) throws IllegalArgumentException;
	
	public abstract AllBewerbungenToOneAusschreibungReport createAllBewerbungenToOneAusschreibungReport(Organisationseinheit o) throws IllegalArgumentException;

	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	
	public abstract FanIn createFanInAnalyse () throws IllegalArgumentException;
	
	public abstract FanOut createFanOutAnalyse() throws IllegalArgumentException;
	
	ProjektverflechtungReport createProjektverflechtungenReport(int id) throws IllegalArgumentException;
	
	public Person findPersonByKey(int id) throws IllegalArgumentException;
	
	public Team findTeamByKey(int id) throws IllegalArgumentException;
	
	public Unternehmen findUnternehmenByKey (int id) throws IllegalArgumentException;

	public Vector<Organisationseinheit> getBewerberByAusschreibungen(Organisationseinheit o) throws IllegalArgumentException;

	
	
}