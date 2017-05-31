package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.text.SimpleDateFormat;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;


public class BeteiligungMapper {
	
	private static BeteiligungMapper beteiligungMapper = null;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	protected BeteiligungMapper() {
	}

	public static BeteiligungMapper beteiligungMapper() {
		if (beteiligungMapper == null) {
			beteiligungMapper = new BeteiligungMapper();
		}

		return beteiligungMapper;
	}

	public Beteiligung findBeteiligungByKey(int idBeteiligung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM beteiligung " 
								+ " WHERE idBeteiligung= " + idBeteiligung 
								+ " ORDER BY idBeteilgung");
			
			if (rs.next()) {
				Beteiligung beteiligung = new Beteiligung();
				beteiligung.setId(rs.getInt("idBeteiligung"));
				beteiligung.setIdProjekt(rs.getInt("idProjekt"));
				beteiligung.setIdBewertung(rs.getInt("idBewertung"));
				beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
				beteiligung.setBeteiligungszeit(rs.getDate("beteiligungszeit"));
						
				return beteiligung;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Beteiligung> findAllBeteiligungen() {
		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung " 
											+ " ORDER BY idBeteiligung");
			
			while (rs.next()) {
				Beteiligung beteiligung = new Beteiligung();
				beteiligung.setId(rs.getInt("idBeteiligung"));
				beteiligung.setIdProjekt(rs.getInt("idProjekt"));
				beteiligung.setIdBewertung(rs.getInt("idBewertung"));
				beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
				beteiligung.setBeteiligungszeit(rs.getDate("beteiligungszeit"));
						
				result.addElement(beteiligung);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Beteiligung> findByProjekt(int idProjekt){
		  
		    Connection con = DBConnection.connection();
		    Vector<Beteiligung> result = new Vector<Beteiligung>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung "
		          + "WHERE idProjekt =" + idProjekt);

		     
		      while (rs.next()) {
		        Beteiligung beteiligung = new Beteiligung();
		        beteiligung.setId(rs.getInt("idBeteiligung"));
		        beteiligung.setIdProjekt(rs.getInt("idProjekt"));
		        beteiligung.setIdBewertung(rs.getInt("idBewertung"));
		        beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
		        beteiligung.setBeteiligungszeit(rs.getDate("beteiligungszeit"));
		        
		        result.add(beteiligung);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    } 
		  return result;
	  }


	public Vector<Beteiligung> findByBewertung(int idBewertung){
		  
	    Connection con = DBConnection.connection();
	    Vector<Beteiligung> result = new Vector<Beteiligung>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung "
	          + "WHERE idProjekt =" + idBewertung);

	     
	      while (rs.next()) {
	        Beteiligung beteiligung = new Beteiligung();
	        beteiligung.setId(rs.getInt("idBeteiligung"));
	        beteiligung.setIdProjekt(rs.getInt("idProjekt"));
	        beteiligung.setIdBewertung(rs.getInt("idBewertung"));
	        beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
	        beteiligung.setBeteiligungszeit(rs.getDate("beteiligungszeit"));
	        
	        result.add(beteiligung);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    } 
	  return result;
  }

	public Vector<Beteiligung> findByBeteiligter(int idBeteiligter){
		  
	    Connection con = DBConnection.connection();
	    Vector<Beteiligung> result = new Vector<Beteiligung>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung "
	          + "WHERE idProjekt =" + idBeteiligter);

	     
	      while (rs.next()) {
	        Beteiligung beteiligung = new Beteiligung();
	        beteiligung.setId(rs.getInt("idBeteiligung"));
	        beteiligung.setIdProjekt(rs.getInt("idProjekt"));
	        beteiligung.setIdBewertung(rs.getInt("idBewertung"));
	        beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
	        beteiligung.setBeteiligungszeit(rs.getDate("beteiligungszeit"));
	        
	        result.add(beteiligung);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    } 
	  return result;
  }
	
	
	public Beteiligung insertBeteiligung (Beteiligung beteiligung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBeteiligung) AS maxid " + "FROM beteiligung ");
			if (rs.next()) {

				beteiligung.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO beteiligung (idBeteiligung, idProjekt, idBewertung, beteiligungszeit, idBeteiligter) " 
									+ "VALUES ('" 
									+ beteiligung.getId() + "','"
									+ beteiligung.getIdBewertung() + "','"
									+ beteiligung.getIdProjekt() + "','" 
									+ format.format(beteiligung.getBeteiligungszeit()) + "','" 
									+ beteiligung.getIdBeteiligter() + "')");
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return beteiligung;
	}

	public Beteiligung updateBeteiligung (Beteiligung beteiligung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE beteiligung " 
					+ "SET idBeteiligung='" + beteiligung.getId() + "' ,'" 
					+ "idBeteiligter='" + beteiligung.getIdBeteiligter() + "' ,'" 
					+ "idProjekt='" + beteiligung.getIdProjekt() + "' ,'" 
					+ "idBewertung'" + beteiligung.getIdBewertung() + "' ,'"
					+ "Beteiligungszeit'" + beteiligung.getBeteiligungszeit() + "' ,'" 
					+ "WHERE idBeteiligung ='"+ beteiligung.getId());
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return beteiligung;
	}

	public void deleteBeteiligung (Beteiligung beteiligung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beteiligung " 
								+ " WHERE idBeteiligung= " + beteiligung.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
