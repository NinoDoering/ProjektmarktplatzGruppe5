package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class ProjektmarktplatzMapper {

	private static ProjektmarktplatzMapper marktplatzMapper = null;

	protected ProjektmarktplatzMapper() {
	};

	public static ProjektmarktplatzMapper marktplatzMapper() {
		if (marktplatzMapper == null) {
			marktplatzMapper = new ProjektmarktplatzMapper();
		}
		return marktplatzMapper;
	}

	public Marktplatz insert(Marktplatz p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idProjektmarktplatz) AS maxid " + "FROM projektmarktplatz ");

			if (rs.next()) {

				p.setIdProjektmarktplatz(rs.getInt("maxid") + 1);

				// doppelt ?? p.setIdProjektmarktplatz(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO projektmarktplatz (idProjektmarktplatz, geschaeftsgebiet, bezeichnung)"
						+ " VALUES (" + p.getIdProjektmarktplatz() + " ,'" + p.getGeschaeftsgebiet() + "','"
						+ p.getBezeichnung() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
	}

	public Marktplatz findByKey(int idProjektmarktplatz) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

		/*
			
			ResultSet rs = stmt.executeQuery(
					"SELECT idProjektmarktplatz, geschaeftsgebiet, bezeichnung" + "FROM projektmarktplatz"
							+ "WHERE idProjektmarktplatz=" + idProjektmarktplatz );
	*/
			
			ResultSet rs = stmt.executeQuery(
					"SELECT idProjektmarktplatz, geschaeftsgebiet, bezeichnung" +" FROM projektmarktplatz " + "WHERE idProjektmarktplatz=" + idProjektmarktplatz );


			

			if (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
				p.setGeschaeftsgebiet(rs.getString("geschaeftsgebiet"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				
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

			ResultSet rs = stmt.executeQuery("SELECT idProjektmarktplatz, bezeichnung, geschaeftsgebiet, projekt "
					+ "FROM projektmarktplatz" + "ORDER BY bezeichnung");
			while (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
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
			ResultSet rs = stmt.executeQuery("SELECT idProjektmarktplatz, bezeichnung, geschaeftsgebiet, projekt "
					+ " FROM projektmarktplatz " + "WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Marktplatz p = new Marktplatz();
				p.setIdProjektmarktplatz(rs.getInt("idProjektmarktplatz"));
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

			stmt.executeUpdate("UPDATE projektmarktplatz " + "SET bezeichnung=\"" + p.getBezeichnung() + "\", "
					+ "geschaeftsgebiet=\"" + p.getGeschaeftsgebiet() + "\", " + "\"," + "projektleiter=\""
					+ p.getProjektleiter() + "\" " // safe falsch
					+ "WHERE idProjektmarktplatz" + p.getIdProjektmarktplatz());

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
					"DELETE * from projektmarktplatz" + "WHERE idProjektmarktplatz =" + p.getIdProjektmarktplatz());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}