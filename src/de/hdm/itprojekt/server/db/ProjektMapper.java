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

			ResultSet rs = stmt.executeQuery("SELECT MAX(idProjekt) AS maxid " + "FROM projekt ");

			if (rs.next()) {

				p.setIdProjekt(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						" INSERT INTO projekt (idProjekt, beschreibung, bezeichnung, projektleiter, startDatum, endDatum)"
								+ " VALUES (" + p.getIdProjekt() + " ,'" + p.getBeschreibung() + "','" + p.getBezeichnung()
								+ "','" + p.getProjektleiter() + "','" + p.getStartDatum() + "', '" + p.getEndDatum()
								+ "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
	}

	public Projekt findByKey (int idProjekt) {
		
		Connection con = DBConnection.connection();
		
		
	try	{
		Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, projektleiter FROM projekt"
				+ "WHERE idProjekt=" + idProjekt + "ORDER BY bezeichnung");
		// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung angezeigt werden	
		
		if (rs.next()) {
			Projekt p = new Projekt ();
			p.setIdProjekt(rs.getInt("idProjekt"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setBeschreibung(rs.getString("beschreibung"));
			p.setStartDatum(rs.getDate("startDatum"));
			p.setEndDatum(rs.getDate("endDatum"));
			//p.setProjektleiter(rs.getPerson("projektleiter")); /// Fehler weil in PersonMapper kein Projektleiter definiert wurde
			Person pers;			

			pers = new Person();
			pers.setVorname(rs.getString("bezeichnung"));
					//p.setProjektleiter(pers); //Fehler weil in PersonMapper kein Projektleiter definiert wurde
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
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, projektleiter "
							+ "FROM projekt" + "ORDER BY bezeichnung");
			while (rs.next()) {
				Projekt p = new Projekt();
				p.setIdProjekt(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				//p.setProjektleiter(rs.getPerson("projektleiter")); // fehler ??
				Person pers;			

				pers = new Person();
				pers.setVorname(rs.getString("bezeichnung"));
						p.setProjektleiter(pers);
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
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, projektleiter "
							+ " FROM projekt "
							+ "INNER JOIN Person on Projekt.projektleiter = Person.projektleiter " + 
							"WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setIdProjekt(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				//p.setProjektleiter(rs.getPerson("projektleiter")); // fehler ??
				Person pers;			

				pers = new Person();
				pers.setVorname(rs.getString("bezeichnung"));
						p.setProjektleiter(pers);
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
					+ "WHERE idProjekt" + p.getIdProjekt());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public void delete(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE from projekt" + "WHERE idProjekt =" + p.getIdProjekt());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
