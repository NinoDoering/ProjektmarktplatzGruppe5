package de.hdm.itprojekt.server;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.*;
import de.hdm.itprojekt.shared.bo.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProjektmarktplatzAdministrationImpl extends RemoteServiceServlet
		implements ProjektmarktplatzAdministration {

	/*
	 * public static final int DEFAULT_CASH_ACCOUNT_ID = 10000; ergibt keinen Sinn
	 */

	private BusinessObjectMapper boMapper = null;
	private EigenschaftMapper eigMapper = null;
	private OrganisationseinheitMapper orgaMapper = null; // Bleibt die Mapper bestehen?
	private PartnerprofilMapper ppMapper = null;
	private PersonMapper persMapper = null;
	private ProjektMapper prjktMapper = null;
	private ProjektmarktplatzMapper pmpMapper = null;
	private TeamMapper teamMapper = null;
	private UnternehmenMapper unternehmenMapper = null;

}
