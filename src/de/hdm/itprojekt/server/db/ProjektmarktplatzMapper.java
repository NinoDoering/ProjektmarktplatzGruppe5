package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;


public class ProjektmarktplatzMapper {
	
	private static ProjektmarktplatzMapper projektmarktplatzMapper = null;
	
	protected ProjektmarktplatzMapper(){}; 
	
	public static ProjektmarktplatzMapper projektmarktplatzMapper(){
		if (projektmarktplatzMapper == null){ 
			projektmarktplatzMapper = new ProjektmarktplatzMapper();
		}
		return projektmarktplatzMapper;
	}

	
	public Projektmarktplatz insert (Projektmarktplatz p){
		
		Connection con = DBConnection.connection(); 
	
		try{ 
			Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM projektmarktplatz "); 
	 
	   if (rs.next()) { 
		   
		  
	        p.setId(rs.getInt("maxid")+1);

	        stmt = con.createStatement();
	   
	
	        stmt.executeUpdate(" INSERT INTO projektmarktplatz (id, geschaeftsgebiet, projekt)"
	        		+ " VALUES (" + p.getId()+ " ,'" + p.getGeschaeftsgebiet() + "','"+ p.getProjekt()+ "')" ); 
	
	   }
	   }
 catch (SQLException e) {
	 e.printStackTrace();
 
 }
		return p; 
}
}
