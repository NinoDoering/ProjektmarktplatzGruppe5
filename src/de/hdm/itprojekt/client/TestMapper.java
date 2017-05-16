package de.hdm.itprojekt.client;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Person;

import java.*;		//Pakete, welche zum Ausführen benötigt werden.
import javax.*;
import java.sql.*;

public class TestMapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	PersonMapper.personMapper().findPersonByKey(1);
		
		//Person eins = PersonMapper.personMapper().findPersonByKey(8);
		
		//System.out.println(PersonMapper.personMapper().findPersonByKey(8));
		
		
		//System.out.println(eins.getTitel());
	//	System.out.println(eins.getVorname());
	//	System.out.println(eins.getNachname());
		
	
	/*	Person p = new Person(); 
		
		p.setIdPerson(3);
		p.setTitel("Dr");
		p.setVorname("Johnny");
		p.setNachname("Depp");
		
		 
		 PersonMapper.personMapper().insert(p); 
		
		
		
		
		//PersonMapper.personMapper().delete(3);
		
		
		
		
		
		Person p2 = new Person(); 
		PersonMapper.personMapper().update(p2); 
	
	p2.setIdBusinessObject(3); 
	p2.setNachname("Trottel");
	
	*/
		Person p6 = new Person();
		
		p6.setIdPerson(12);
		p6.setTitel("DRMRK");
		p6.setVorname("hallo");
		p6.setNachname("NINO");
		
		PersonMapper.personMapper().insert(p6);
		
		System.out.println(p6.toString());
		
		
		
Person p7 = new Person();
		
		p7.setIdPerson(1);
		p7.setTitel("DRMRK");
		p7.setVorname("hallo");
		p7.setNachname("NINhdhdhdO");
		
		PersonMapper.personMapper().insert(p7);
		
		System.out.println(p7.toString());
		
	
		
Person p8 = new Person();
		
		
		p8.setTitel("DRMRK");
		p8.setVorname("hallo");
		p8.setNachname("NINO");
		
		PersonMapper.personMapper().insert(p8);
		
		System.out.println(p8.toString());
Person p9 = new Person();
		
		
		p9.setTitel("DRMRK");
		p9.setVorname("TONSKE");
		p9.setNachname("OLA");
		
		PersonMapper.personMapper().insert(p9);
		 
		System.out.println(p9.toString());
		
// Update Test ausgeführt bei der erzeugten Person p9 und den Vornamen geändert in Motrip
p9 = PersonMapper.personMapper().findPersonByKey(30);
p9.setVorname("Motrip");
PersonMapper.personMapper().update(p9);

		}
	}
	


