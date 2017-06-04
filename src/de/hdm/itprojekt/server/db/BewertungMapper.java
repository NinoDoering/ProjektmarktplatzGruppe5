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
				bewertung.setFliessKommaBewertung(rs.getFloat("fließkommaBewertung"));
				
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
			stmt.executeUpdate("INSERT INTO bewertung (idBewerbung, textuelleBewertung, fließkommaBewertung) " 
					+ "VALUES ('"
					+ bewertung.getId() + "','" 
					+ bewertung.getIdBewerbung() + "','" 
					+ bewertung.getTextuelleBewertung() + "','"
					+ bewertung.getFliessKommaBewertung() + "')");
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
					+ "fließkommaBewertung='" + bewertung.getFliessKommaBewertung() + "' ,'"
					+ " WHERE idBewertung= '" + bewertung.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bewertung;
	}
	
	public Bewertung findByBewertung(Bewertung bewertung){
		return this.findBewertungByKey(bewertung.getId());
	}
	
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
	        bewertung.setFliessKommaBewertung(rs.getFloat("fließkommaBewertung"));
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