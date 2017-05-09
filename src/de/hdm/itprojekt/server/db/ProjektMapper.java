package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class ProjektMapper {

	private static ProjektMapper projektMapper = null;

	protected ProjektMapper() {
	};

	public static ProjektMapper projektMapper() {
		if (projektMapper == null) {
			projektMapper = new ProjektMapper();
		}
		return projektMapper;
	}

	public Projekt insert(Projekt p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM projekt ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						" INSERT INTO projekt (id, beschreibung, bezeichnung, projektleiter, startDatum, endDatum)"
								+ " VALUES (" + p.getId() + " ,'" + p.getBeschreibung() + "','" + p.getBezeichnung()
								+ "','" + p.getProjektleiter() + "','" + p.getStartDatum() + "', '" + p.getEndDatum()
								+ "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
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
			p.setProjektleiter(rs.getPerson("projektleiter")); /// Fehler beheben 
			
			return p; 
		}
	}
	catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	return null;
	
}

	public Vector<Projekt> findAll() {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// Datenbankabfrage aller Projekte alphabetisch sortiert nach
			// bezeichnung

			ResultSet rs = stmt
					.executeQuery("SELECT id, bezeichnung, beschreibung, startDatum, endDatum, projektleiter "
							+ "FROM projekt" + "ORDER BY bezeichnung");
			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("id"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setProjektleiter(rs.getPerson("projektleiter")); // fehler ??

				result.addElement(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Projekt> findByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			// SQL Statement, gibt Eintraege aus welche die eingegeben
			// Bezeichung enthält
			ResultSet rs = stmt
					.executeQuery("SELECT id, bezeichnung, beschreibung, startDatum, endDatum, projektleiter "
							+ " FROM projekt " + "WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("id"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setProjektleiter(rs.getPerson("projektleiter")); // fehler ??

				result.addElement(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Projekt update(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// SQL Statment, welches das Updaten von Projekte erlaubt

			stmt.executeUpdate("UPDATE projekt " + "SET bezeichnung=\"" + p.getBezeichnung() + "\", "
					+ "beschreibung=\"" + p.getBeschreibung() + "\", " + "startDatum=\"" + p.getStartDatum() + "\","
					+ "endDatum=\"" + p.getEndDatum() + "\"," + "projektleiter=\"" + p.getProjektleiter() + "\" "
					+ "WHERE id" + p.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void delete(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE from projekt" + "WHERE id =" + p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
