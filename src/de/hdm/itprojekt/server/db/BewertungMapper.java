package de.hdm.itprojekt.server.db;

import java.sql.*;

import de.hdm.itprojekt.shared.bo.*;

public class BewertungMapper {

	//findAllBewertung auch als Methode in der Klasse??
	
	private static BewertungMapper bewertungMapper = null;

	
	protected BewertungMapper(){
		
	}
	
	public static BewertungMapper bewertungMapper(){
		if (bewertungMapper == null){
			bewertungMapper = new BewertungMapper();
		}
		return bewertungMapper;
	}
	
	//alleByID
	public Bewertung findBewertungByKey(int idBewertung){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			//SQL
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewertung " 
											+ " WHERE idBewertung= " + idBewertung);
		
			if(rs.next()){
				Bewertung bewertung = new Bewertung();
				
				bewertung.setId(rs.getInt("idBewertung"));
				bewertung.setTextuelleBewertung(rs.getString("textuelleBewertung"));
				bewertung.setFliesskommaBewertung(rs.getFloat("fliesskommaBewertung"));
				
				return bewertung;
			}
		}
	catch (SQLException e){
		e.printStackTrace();
		return null;
	}
	return null;
	}
	
	//insert
	public Bewertung insertBewertung(Bewertung bewertung){
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewertung) AS maxid " + " FROM bewertung ");

			if (rs.next()) {

				bewertung.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO bewertung (idBewertung, idBewerbung, textuelleBewertung, fliesskommaBewertung) " 
					+ "VALUES ('"
					+ bewertung.getId() + "','" 
					+ bewertung.getIdBewerbung() + "','" 
					+ bewertung.getTextuelleBewertung() + "','"
					+ bewertung.getFliesskommaBewertung() + "')");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return bewertung;
	}

	//delete
	public void deleteBewertung(Bewertung bewertung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE * FROM bewertung" 
								+ " WHERE idBewertung= " + bewertung.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update
	public Bewertung updateBewertung(Bewertung bewertung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE bewertung " 
					+ "SET idBewertung='" + bewertung.getId() + "' ,'" 
					+ "idBewerbung='" + bewertung.getIdBewerbung() + "' ,'" 
					+ "textuelleBewertung='" + bewertung.getTextuelleBewertung() + "' ,'" 
					+ "fliesskommaBewertung='" + bewertung.getFliesskommaBewertung() + "' ,'"
					+ " WHERE idBewertung= '" + bewertung.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bewertung;
	}
	
	//Objekt Bewertung ausgeben nach der ID
	public Bewertung findByBewertung(Bewertung bewertung){
		return this.findBewertungByKey(bewertung.getId());
	}
	
	//Bewertung nach Bewerbung ausgeben
	public Bewertung findBewertungByBewerbung(int idBewerbung){
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM bewertung "
	          +  " WHERE idBewerbung= " + idBewerbung);

	      if (rs.next()) {
	        Bewertung bewertung = new Bewertung();
	        bewertung.setId(rs.getInt("idBewertung"));
	        bewertung.setTextuelleBewertung(rs.getString("textuelleBewertung"));
	        bewertung.setFliesskommaBewertung(rs.getFloat("fliesskommaBewertung"));
	        bewertung.setIdBewerbung(rs.getInt("idBewerbung"));
	        return bewertung;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }  
	return null;
	}
	
	
}