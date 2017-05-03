package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/NinoDoering/ProjektmarktplatzGruppe5.git

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
		   
		  
	        p.setIdProjektmarktplatz(rs.getInt("maxid") + 1);

	        p.setId(rs.getInt("maxid")+1);

	        stmt = con.createStatement();
	   
	
	        stmt.executeUpdate(" INSERT INTO projektmarktplatz (idProjektmarktplatz, geschaeftsgebiet, projekt)"
	        		+ " VALUES (" + p.getIdProjektmarktplatz()+ " ,'" + p.getGeschaeftsgebiet() + "','"+ p.getProjekt()+ "')" ); 
	
	   }
	   }
 catch (SQLException e) {
	 e.printStackTrace();
 
 }
		return p; 
}
public Projektmarktplatz findByKey (int id) {
		
		Connection con = DBConnection.connection();
		
		
	try	{
		Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT idProjektmarktplatz, bezeichnung, geschaeftsgebiet, projekt FROM projektmarktplatz"
				+ "WHERE idProjektmarktplatz=" + id + "ORDER BY bezeichnung");
		// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung angezeigt werden	
		
		if (rs.next()) {
			Projektmarktplatz p = new Projektmarktplatz ();
			p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
			p.setProjekt(rs.getProjekt("beschreibung")); //fehler beheben
			
			
			return p; 
		}
	}
	catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	return null;
	
}
public Vector <Projektmarktplatz> findAll() {
	Connection con = DBConnection.connection(); 
	Vector <Projektmarktplatz> result = new Vector<Projektmarktplatz> ();
	
	try {
		Statement stmt = con.createStatement();
		
		// Datenbankabfrage aller Projekte  alphabetisch sortiert nach bezeichnung 
		
		ResultSet rs = stmt.executeQuery("SELECT idProjektmarktplatz, bezeichnung, geschaeftsgebiet, projekt " 
				+ "FROM projektmarktplatz" + "ORDER BY bezeichnung");
		while (rs.next()) {
			Projektmarktplatz p = new Projektmarktplatz();
			p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
			p.setProjekt(rs.getProjekt("projektleiter"));  //fehler ?? 
			
			result.addElement(p);
		}
		
		}
	catch (SQLException e){
		e.printStackTrace();
	}
	
	return result; 
}

public Vector <Projektmarktplatz> findByBezeichnung (String bezeichnung){
	Connection con = DBConnection.connection();
	Vector <Projektmarktplatz> result = new Vector <Projektmarktplatz> ();
	
	try {
		Statement stmt = con.createStatement(); 
		
		// SQL Statement, gibt Eintraege aus welche die eingegeben Bezeichung enthält 
		ResultSet rs = stmt.executeQuery("SELECT idProjektmarktplatz, bezeichnung, geschaeftsgebiet, projekt "
				+ " FROM projektmarktplatz "+ "WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung"); 
		
		while (rs.next()) {
			Projektmarktplatz p = new Projektmarktplatz();
			p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
			p.setProjekt(rs.getProjekt("projektleiter"));  //fehler ?? 
			
		result.addElement(p);
	}
}
catch (SQLException e) {
	e.printStackTrace();
}
	return result;
}

public Projektmarktplatz update(Projektmarktplatz p){
	Connection con = DBConnection.connection();
	
try {
	Statement stmt = con.createStatement();
	
	// SQL Statment, welches das Updaten von Projekte erlaubt 
	
	stmt.executeUpdate("UPDATE projektmarktplatz " + "SET bezeichnung=\"" + p.getBezeichnung()
	+ "\", " + "geschaeftsgebiet=\"" + p.getGeschaeftsgebiet() + "\", "
	+ "\"," + "projektleiter=\"" + p.getProjektleiter()+ "\" "  //safe falsch
	+ "WHERE idProjektmarktplatz" + p.getIdProjektmarktplatz()); 
		
	
}
catch (SQLException e) {
      e.printStackTrace();
    }
return p;
}

public void  delete(Projektmarktplatz p){
	Connection con = DBConnection.connection();
	
try {
	Statement stmt = con.createStatement();
	stmt.executeQuery("DELETE * from projektmarktplatz" + "WHERE idProjektmarktplatz =" + p.getIdProjektmarktplatz());
}
catch (SQLException e) {
      e.printStackTrace();
    }
}}
