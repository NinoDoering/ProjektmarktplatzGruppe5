package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class MarktplatzMapper {

	private static MarktplatzMapper marktplatzMapper = null;

	protected MarktplatzMapper() {
	};

	public static MarktplatzMapper marktplatzMapper() {
		if (marktplatzMapper == null) {
			marktplatzMapper = new MarktplatzMapper();
		}
		return marktplatzMapper;
	}

	//insert
	public Marktplatz insertMarktplatz (Marktplatz pm) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idMarktplatz) AS maxid FROM marktplatz ");

			if (rs.next()) {

				pm.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO marktplatz (idMarktplatz, geschaeftsgebiet, bezeichnung) " 
				+ "VALUES ('"
				+pm.getId()+ "','"+ 
				pm.getGeschaeftsgebiet() + "','" + 
				pm.getBezeichnung() + "')");
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			

		}
		
		return pm;
	}

		
		
		
	
	
	//Marktplatz nach Id ausgeben
	public Marktplatz findMarktplatzByKey (int idMarktplatz) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM marktplatz " 
			+ " WHERE idMarktplatz= " + idMarktplatz );
			// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung
			// angezeigt werden

			if (rs.next()) {
				Marktplatz pm = new Marktplatz();
				pm.setId(rs.getInt("idMarktplatz"));
				pm.setBezeichnung(rs.getString("bezeichnung"));
				pm.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));

				return pm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	//jeden vorhandenen Marktplatz ausgeben
	public Vector<Marktplatz> findAllMarktplatz() {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> vector = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			// Datenbankabfrage aller Projekte alphabetisch sortiert nach
			// bezeichnung

			ResultSet rs = stmt.executeQuery("SELECT * FROM marktplatz " 
			+ " ORDER BY bezeichnung DESC");
			while (rs.next()) {
				Marktplatz pm = new Marktplatz();
				pm.setId(rs.getInt("idMarktplatz"));
				pm.setBezeichnung(rs.getString("bezeichnung"));
				pm.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));

				vector.addElement(pm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vector;
	}

	//Marktplatz nach Bezeichnung ausgeben
	public Vector<Marktplatz> findMarktplatzByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> vector = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enth�lt
			ResultSet rs = stmt.executeQuery("SELECT idMarktplatz, bezeichnung, geschaeftsgebiet "
					+ " FROM marktplatz " 
					+ " WHERE bezeichnung= '" + bezeichnung 
					+ "' ORDER BY bezeichnung");

			while (rs.next()) {
				Marktplatz pm = new Marktplatz();
				pm.setId(rs.getInt("idMarktplatz"));
				pm.setBezeichnung(rs.getString("bezeichnung"));
				pm.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));

				vector.addElement(pm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	// Person mit zugehoerigen Projekten ausgeben
	public Vector<Marktplatz> findMarktplatzByPerson(int idPerson) {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> result = new Vector<Marktplatz>();
		
		try {
			Statement stmt = con.createStatement();
			/*
			 * SQL-Statement gibt Eintraege aus, welche die eingegebenen
			 * Bezeichnungen enthalten
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT idMarktplatz, geschaeftsgebiet, bezeichnung FROM Marktplatz "
							+ "WHERE idPerson= '" + idPerson + "' ORDER BY idMarktplatz DESC");
			
			while (rs.next()) {
				Marktplatz mp = new Marktplatz();
				mp.setId(rs.getInt("idMarktplatz"));
				mp.setGeschaeftsgebiet("geschaeftsgebiet");
				mp.setBezeichnung("bezeichnung");
				
				result.addElement(mp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	

	//update
	public Marktplatz updateMarktplatz (Marktplatz pm) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// SQL Statment, welches das Updaten von Projekte erlaubt

			stmt.executeUpdate("UPDATE marktplatz " + "SET bezeichnung='" 
					+ pm.getBezeichnung() + "', geschaeftsgebiet='"
					+ pm.getGeschaeftsgebiet() 
					+ "' WHERE idMarktplatz= " + pm.getId());
			
			
			

			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pm;
	}

	//delete
	public void deleteMarktplatz(Marktplatz pm) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"DELETE FROM marktplatz " 
			+ "WHERE idMarktplatz=" + pm.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}