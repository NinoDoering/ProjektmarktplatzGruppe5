package de.hdm.itprojekt.shared;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.report.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	public void setPerson() throws IllegalArgumentException;
	
	public abstract AllAusschreibungenByPartnerprofilReport createAllAusschreibungenByPartnerprofilReport (Partnerprofil pp) throws IllegalArgumentException;

	public abstract AllAusschreibungenReport createAllAusschreibungenReport() throws IllegalArgumentException;
	
	public abstract AllBeteiligungenToProjectReport createAllBeteiligungenToProjectReport () throws IllegalArgumentException;
	
	public abstract AllBewerbungenByAusschreibungReport createAllBewerbungenByAusschreibungReport () throws IllegalArgumentException;
	
	public abstract AllBewerbungenByOrganisationseinheitReport createAllBewerbungenByOrganisationseinheitReport () throws IllegalArgumentException;
	
	public abstract FanInFanOutReport createFanInFanOutReport() throws IllegalArgumentException;
	
	public abstract FanIn createFanInAnalyse (Organisationseinheit o) throws IllegalArgumentException;
	
	
}