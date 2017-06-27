package de.hdm.itprojekt.client;
import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.*;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;

import java.*;		//Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.Date;



public class TestMapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		
		
//		System.out.println(BewerbungMapper.bewerbungmapper().findBewerbungByBewerber(1));
//		
//		Marktplatz mp1 = new Marktplatz(); 
//	
//		mp1.setBezeichnung("MS OFFICEEE");
//		mp1.setGeschaeftsgebiet("EXCEL");
//		
//		
//		MarktplatzMapper.marktplatzMapper().insertMarktplatz(mp1);
		
		
		
//		System.out.println(MarktplatzMapper.marktplatzMapper().findAllMarktplatz());
		
		
		Eigenschaft eigt = new Eigenschaft();
		
		eigt.setAbschluss("abui");
		eigt.setArbeitsgebiet("IT");
		eigt.setBerufserfahrungsJahre("2");
		eigt.setIdPartnerprofil(2);
		eigt.setSprachkenntnisse("french");
		
		EigenschaftMapper.eigenschaftMapper().insertEigenschaft(eigt);
	}
	
}

