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
	
	/**
	 * Variable "beteiligungMapper"ist aufgrund des Bezeichners "static" nur einmal für die Instanzen dieser Klasse
	 * verfügbar. Sie speichert die die einzige Instanz dieser Klasse.
	 * @author Thies
	 */
	private static BeteiligungMapper beteiligungMapper = null;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse erstellen kann
	 * Sie speichert die einzige Instanz dieser Klasse
	 * @author Thies
	 */
	protected BeteiligungMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der "BeteiligungMapper"-Klasse sicher,
	 * sodass nur eine  Instanz von <code>BeteiligungMapper</code> existieren kann.
	 * @return beteiligungMapper
	 */
	public static BeteiligungMapper beteiligungMapper() {
		if (beteiligungMapper == null) {
			beteiligungMapper = new BeteiligungMapper();
		}

		return beteiligungMapper;
	}

	/**
	 * Suchen einer Beteiligung über die übergebene Beteiligungsnummer
	 * @param idBeteiligung
	 * @return Ausschreibungsobjekt, das der übergebenen Beteiligungsnummer entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Beteiligung findBeteiligungByKey(int idBeteiligung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM beteiligung " 
								+ " WHERE idBeteiligung= " + idBeteiligung 
								+ " ORDER BY idBeteiligung");
			
			if (rs.next()) {
				Beteiligung beteiligung = new Beteiligung();
				beteiligung.setId(rs.getInt("idBeteiligung"));
				beteiligung.setIdProjekt(rs.getInt("idProjekt"));
				beteiligung.setIdBewertung(rs.getInt("idBewertung"));
				beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
				beteiligung.setBeteiligungszeit(rs.getInt("beteiligungszeit"));
						
				return beteiligung;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen aller Beteiligungen
	 * @return alle Beteiligungsobjekte
	 */
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
				beteiligung.setBeteiligungszeit(rs.getInt("beteiligungszeit"));
						
				result.addElement(beteiligung);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Suchen von Beteiligungen über den Fremdschlüssel (idProjekt) der zugehoerigen Projekte
	 * @param idProjekt
	 * @return Vector mit Beteiligungsobjekten, die zum Projekt mit übergebenem Fremdschlüssel gehören
	 */
	public Vector<Beteiligung> findBeteiligungByProjekt(int idProjekt){
		  
		    Connection con = DBConnection.connection();
		    Vector<Beteiligung> result = new Vector<Beteiligung>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung "
		          + " WHERE idProjekt= " + idProjekt);

		     
		      while (rs.next()) {
		        Beteiligung beteiligung = new Beteiligung();
		        beteiligung.setId(rs.getInt("idBeteiligung"));
		        beteiligung.setIdProjekt(rs.getInt("idProjekt"));
		        beteiligung.setIdBewertung(rs.getInt("idBewertung"));
		        beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
		        beteiligung.setBeteiligungszeit(rs.getInt("beteiligungszeit"));
		        
		        result.add(beteiligung);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    } 
		  return result;
	  }


	/**
	 * Suchen von Beteiligungen über den Fremdschlüssel (idBewertung) der zugehoerigen Bewertungen
	 * @param idBewertung
	 * @return Vector mit Beteiligungssobjekten, die zur Bewertung mit übergebenem Fremdschlüssel gehören
	 */
	public Beteiligung findBeteiligungByBewertung(int idBewertung){
		  
	    Connection con = DBConnection.connection();
	    

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung "
	          + " WHERE idProjekt= " + idBewertung);

	     
	      while (rs.next()) {
	        Beteiligung beteiligung = new Beteiligung();
	        beteiligung.setId(rs.getInt("idBeteiligung"));
	        beteiligung.setIdProjekt(rs.getInt("idProjekt"));
	        beteiligung.setIdBewertung(rs.getInt("idBewertung"));
	        beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
	        beteiligung.setBeteiligungszeit(rs.getInt("beteiligungszeit"));
	        
	        
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	      return null;
	    } 
	  return null;
  }

	/**
	 * Suchen von Beteiligungen über den Fremdschlüssel (idBeteiligter) des Beteiligten
	 * @param idBeteiligter
	 * @return Vector mit Beteiligungssobjekten, die zum Beteiligten mit übergebenem Fremdschlüssel gehören
	 */
	public Vector<Beteiligung> findBeteiligungByBeteiligter(int idBeteiligter) {

		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM beteiligung " + " WHERE idBeteiligter= " + idBeteiligter);

			while (rs.next()) {
				Beteiligung beteiligung = new Beteiligung();
				beteiligung.setId(rs.getInt("idBeteiligung"));
				beteiligung.setIdProjekt(rs.getInt("idProjekt"));
				beteiligung.setIdBewertung(rs.getInt("idBewertung"));
				beteiligung.setIdBeteiligter(rs.getInt("idBeteiligter"));
				beteiligung.setBeteiligungszeit(rs.getInt("beteiligungszeit"));

				result.add(beteiligung);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 **
	 * Einfuegen eines <code>Beteiligung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschluessel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt. @author Thies
	 * 
	 * @param beteiligung
	 * @return Beteiligungsobjekt wird in die Datenbank eingefuegt
	 */
	public Beteiligung insertBeteiligung(Beteiligung beteiligung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBeteiligung) AS maxid " + "FROM beteiligung ");
			if (rs.next()) {

				beteiligung.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO beteiligung (idBeteiligung, idBewertung, idProjekt, beteiligungszeit, idBeteiligter) "
								+ "VALUES (" + beteiligung.getId() + " ,'" + beteiligung.getIdBewertung() + "','"
								+ beteiligung.getIdProjekt() + "','" + beteiligung.getBeteiligungszeit() + "','"
								+ beteiligung.getIdBeteiligter() + "')");
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return beteiligung;
	}

	/**
	 * Update des übergebenen Beteiligungsobjekts
	 * @param beteiligung
	 * @return Das übergebene Beteiligungsobjekt
	 */
	public Beteiligung updateBeteiligung(Beteiligung beteiligung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE beteiligung " + "SET idBeteiligung='" + beteiligung.getId() + "' ,'"
					+ "idBeteiligter='" + beteiligung.getIdBeteiligter() + "' ,'" + "idProjekt='"
					+ beteiligung.getIdProjekt() + "' ,'" + "idBewertung'" + beteiligung.getIdBewertung() + "' ,'"
					+ "Beteiligungszeit'" + beteiligung.getBeteiligungszeit() + "' ,'" + " WHERE idBeteiligung= '"
					+ beteiligung.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return beteiligung;
	}

	/**
	 * Loeschen des uebergebenen Beteiligungsobjekts
	 * @param beteiligung das uebergebene Beteiligungsobjekt
	 */
	public void deleteBeteiligung(Beteiligung beteiligung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beteiligung " + " WHERE idBeteiligung= " + beteiligung.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
