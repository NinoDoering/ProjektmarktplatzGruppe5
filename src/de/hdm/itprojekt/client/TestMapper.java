package de.hdm.itprojekt.client;

import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.Person;

import java.*;		//Pakete, welche zum Ausführen benötigt werden.
import javax.*;
import java.sql.*;

public class TestMapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	PersonMapper.personMapper().findPersonByKey(1);
		
		Person eins = PersonMapper.personMapper().findPersonByKey(2);
		
		System.out.println(PersonMapper.personMapper().findPersonByKey(2));
		
		
		System.out.println(eins.getTitel());
		System.out.println(eins.getVorname());
		System.out.println(eins.getNachname());
		
	
		
		Person p = new Person(); 
		
		p.setIdPerson(3);
		p.setTitel("Dr");
		p.setVorname("Johnny");
		p.setNachname("Depp");
		
		 
		// PersonMapper.personMapper().insert(p); 
		
		
		
		
		//PersonMapper.personMapper().delete(3);
		
		
		
		
		
		Person p2 = new Person(); 
		PersonMapper.personMapper().update(p2); 
	
	p2.setIdBusinessObject(3); 
	p2.setNachname("Trottel");
	
		
		
		
		
	}

}
