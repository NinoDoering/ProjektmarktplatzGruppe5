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

	public Marktplatz insert(Marktplatz p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idMarktplatz) AS maxid " + "FROM marktplatz ");

			if (rs.next()) {

				p.setIdMarktplatz(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO marktplatz (idMarktplatz, geschaeftsgebiet, bezeichnung) " + "VALUES ('"+p.getIdMarktplatz()+ "','"
								+ p.getGeschaeftsgebiet() + "','" + p.getBezeichnung() + "')");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
	}

	public Marktplatz findByKey(int idMarktplatz) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idMarktplatz, bezeichnung, geschaeftsgebiet FROM marktplatz"
							+ "WHERE idMarktplatz=" + idMarktplatz );
			// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung
			// angezeigt werden

			if (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
				//p.setProjekt(rs.getProjekt("beschreibung")); // fehler beheben

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Vector<Marktplatz> findAll() {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> result = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			// Datenbankabfrage aller Projekte alphabetisch sortiert nach
			// bezeichnung

			ResultSet rs = stmt.executeQuery("SELECT idMarktplatz, bezeichnung, geschaeftsgebiet, projekt "
					+ "FROM marktplatz" + "ORDER BY bezeichnung");
			while (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
				p.setProjekt(rs.getProjekt("projektleiter")); // fehler ??

				result.addElement(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Marktplatz> findByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> result = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enthält
			ResultSet rs = stmt.executeQuery("SELECT idMarktplatz, bezeichnung, geschaeftsgebiet, projekt "
					+ " FROM marktplatz " + "WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdMarktplatz(rs.getInt("idProjektmarktplatz"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
				p.setProjekt(rs.getProjekt("projektleiter")); // fehler ??

				result.addElement(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Marktplatz update(Marktplatz p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// SQL Statment, welches das Updaten von Projekte erlaubt

			stmt.executeUpdate("UPDATE marktplatz " + "SET bezeichnung=\"" + p.getBezeichnung() + "\", "
					+ "geschaeftsgebiet=\"" + p.getGeschaeftsgebiet() + "\", " + 
					"WHERE idProjektmarktplatz" + p.getIdMarktplatz());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void delete(Marktplatz p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery(
					"DELETE * from marktplatz" + "WHERE idMarktplatz =" + p.getIdMarktplatz());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}