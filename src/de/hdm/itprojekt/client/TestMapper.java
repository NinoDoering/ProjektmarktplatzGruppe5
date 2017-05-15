package de.hdm.itprojekt.client;

import de.hdm.itprojekt.server.db.PersonMapper;

public class TestMapper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	PersonMapper.personMapper().findPersonByKey(1);
		
		System.out.println(PersonMapper.personMapper().findPersonByKey(1));
		
		
		
		
	}

}
