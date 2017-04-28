package de.hdm.itprojekt.server.db;
import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;

public class TeamMapper {
	
	
		private static TeamMapper teamMapper = null;
		
		protected TeamMapper(){}; 
		
		public static TeamMapper teamMapper(){
			if (teamMapper == null){ 
				teamMapper = new teamMapper();
			}
			return teamMapper;
		}
		


}


	


