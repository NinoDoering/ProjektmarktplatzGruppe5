package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;


public class ProjektMapper {
	
	private static ProjektMapper projektMapper = null;
	
	protected ProjektMapper(){}; 
	
	public static ProjektMapper projektMapper(){
		if (projektMapper == null){ 
			projektMapper = new ProjektMapper();
		}
		return projektMapper;
	}
	
	
	public Projekt insert (Projekt p){
		
		Connection con = DBConnection.connection(); 
	
		try{ 
			Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM projekt "); 
	 
	   if (rs.next()) { 
		   
		  
	        p.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();
	   
	
	        stmt.executeUpdate(" INSERT INTO projekt (id, beschreibung, bezeichnung, projektleiter, startDatum, endDatum)"
	        		+ " VALUES (" + p.getId()+ " ,'"  p.getBeschreibung() + "','"+ p.getBezeichnung()+ "','" + p.getProjektleiter()
	        		+ "','"+ p.getStartDatum()+ "', '" + p.getEndDatum()+ "')" ); 
	// Fehler 
	   }
	   }
 catch (SQLException e) {
	 e.printStackTrace();
 }
}
	public Projekt findByKey (int id) {
		
		Connection con = DBConnection.connection();
		
		
	try	{
		Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT id, bezeichnung, beschreibung, startDatum, endDatum, projektleiter FROM projekt"
				+ "WHERE id=" + id + "ORDER BY bezeichnung");
		// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung angezeigt werden	
		
		if (rs.next()) {
			Projekt p = new Projekt ();
			p.setId(rs.getInt("id"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setBeschreibung(rs.getString("beschreibung"));
			p.setStartDatum(rs.getDate("startDatum"));
			p.setEndDatum(rs.getDate("endDatum"));
			p.setProjektleiter(rs.get("projektleiter")); /// Fehler beheben 
			
			return p; 
		}
	}
	catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	return null;
	
}}