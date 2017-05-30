package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class ProjektMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
						" INSERT INTO projekt (idProjekt, beschreibung, bezeichnung, idPerson, startDatum, endDatum)"
								+ " VALUES (" 
								+ p.getIdProjekt() + " ,'" 
								+ p.getBeschreibung() + "','" 
								+ p.getBezeichnung()+ "','" 
								+ p.getIdPerson() + "','" 
								+ format.format(p.getStartDatum())  + "', '" 
								+ format.format(p.getEndDatum())
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
		
		ResultSet rs = stmt.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson FROM projekt"
				+ " WHERE idProjekt= " + idProjekt + " ORDER BY bezeichnung");
		// Projekte sollen alphabetisch nach Namen bzw. Bezeichnung angezeigt werden	
		
		if (rs.next()) {
			Projekt p = new Projekt ();
			p.setIdProjekt(rs.getInt("idProjekt"));
			p.setBezeichnung(rs.getString("bezeichnung"));
			p.setBeschreibung(rs.getString("beschreibung"));
			p.setStartDatum(rs.getDate("startDatum"));
			p.setEndDatum(rs.getDate("endDatum"));
			p.setIdPerson(rs.getInt("idPerson")); 
			
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
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson "
							+ " FROM projekt" + " ORDER BY bezeichnung");
			while (rs.next()) {
				Projekt p = new Projekt();
				p.setIdProjekt(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson"));
			
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
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson "
							+ " FROM projekt "
							+ "WHERE bezeichnung LIKE '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setIdProjekt(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				
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
				+ "beschreibung=\"" + p.getBeschreibung() + "\", " 
					+ "startDatum=\"" + format.format(p.getStartDatum()) + "\","
					+ "endDatum=\"" + format.format(p.getEndDatum()) + "\"," 
					+ "idPerson=\"" + p.getIdPerson() + "\" "
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
