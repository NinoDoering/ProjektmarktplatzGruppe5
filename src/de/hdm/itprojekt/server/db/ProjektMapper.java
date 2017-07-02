package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class ProjektMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Variable "projektMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static ProjektMapper projektMapper = null;

	/**
	 * Konstruktor, der verhindert, dass man neue Instanzen dieser Klasse
	 * erstellen kann. Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected ProjektMapper() {
	};

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "ProjektMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>ProjektMapper</code> existieren kann.
	 * 
	 * @return projektMapper
	 */
	public static ProjektMapper projektMapper() {
		if (projektMapper == null) {
			projektMapper = new ProjektMapper();
		}
		return projektMapper;
	}

	/**
	 * Einfuegen eines <code>Projekt</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param p
	 * @return Projektobjekt wird in die Datenbank eingefuegt
	 */
	public Projekt insertProjekt(Projekt p) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idProjekt) AS maxid " + " FROM projekt ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						" INSERT INTO projekt (idProjekt, beschreibung, bezeichnung, idPerson, idMarktplatz, startDatum, endDatum)"
								+ " VALUES (" + p.getId() + " ,'" + p.getBeschreibung() + "','" + p.getBezeichnung()
								+ "','" + p.getIdPerson() + "','" + p.getIdMarktplatz() + "','"
								+ format.format(p.getStartDatum()) + "', '" + format.format(p.getEndDatum()) + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return p;
	}

	/**
	 * Suchen einer Projekt über die übergebene Projektnummer
	 * 
	 * @param idProjekt
	 * @return Projektobjekt, das der übergebenen Projektnummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Projekt findProjektByKey(int idProjekt) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt"
							+ " WHERE idProjekt= " + idProjekt + " ORDER BY bezeichnung");

			if (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson"));
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	/**
	 * Objekt Projekt ausgeben nach der ID
	 * @param projekt
	 * @return Projektobjekt, das der übergebenen ID entspricht
	 */
	public Projekt findByProjekt(Projekt p){
		  return this.findProjektByKey(p.getId());		  
	 }
	
	/**
	 * Suchen aller Projekte
	 * @return alle Projektobjekte
	 */
	public Vector<Projekt> findAllProjekt() {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt " 
			+ " ORDER BY idProjekt DESC");
			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson"));
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
			
				result.addElement(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Suchen von Projekten nach Bezeichnung
	 * @param bezeichnung
	 * @return Vector mit allen Projekten, die der übergebenen Bezeichnung entsprechen
	 */
	public Vector<Projekt> findProjektByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ " WHERE bezeichnung= '" + bezeichnung + "' ORDER BY bezeichnung");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				
				result.addElement(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Suchen von Projekten über den Fremdschlüssel (idMarktplatz) auf dem
	 * Marktplatz, in dem die Projekte angelegt wurden
	 * 
	 * @param idMarktplatz
	 * @return Vector mit Projektobjekten, die zum Marktplatz mit
	 *         übergebenem Fremdschlüssel gehören
	 */
	public Vector<Projekt> findProjektbyMarktplatz(int idMarktplatz) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ " WHERE idMarktplatz= '" + idMarktplatz + "' ORDER BY idProjekt DESC");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson"));
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));

				result.addElement(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Suchen von Projekten über den Fremdschlüssel (idPerson) die das
	 * Projekt angelegt hat
	 * 
	 * @param idPerson
	 * @return Vector mit Projektobjekten, die von der Person
	 *         übergebenem Fremdschlüssel angelegt wurden
	 */
	public Vector<Projekt> findProjektbyPerson(int idPerson) {
		Connection con = DBConnection.connection();
		Vector<Projekt> result = new Vector<Projekt>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idProjekt, bezeichnung, beschreibung, startDatum, endDatum, idPerson, idMarktplatz FROM projekt "
							+ "WHERE idPerson= '" + idPerson + "' ORDER BY idProjekt DESC");

			while (rs.next()) {
				Projekt p = new Projekt();
				p.setId(rs.getInt("idProjekt"));
				p.setBezeichnung(rs.getString("bezeichnung"));
				p.setBeschreibung(rs.getString("beschreibung"));
				p.setStartDatum(rs.getDate("startDatum"));
				p.setEndDatum(rs.getDate("endDatum"));
				p.setIdPerson(rs.getInt("idPerson")); 
				p.setIdMarktplatz(rs.getInt("idMarktplatz"));
				
				result.addElement(p);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Update des übergebenen Projektobjekts
	 * @param p
	 * @return Das übergebene Projektobjekt
	 */
	public Projekt updateProjekt(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// SQL Statment, welches das Updaten von Projekte erlaubt

//			stmt.executeUpdate("UPDATE projekt " + " SET bezeichnung='" + p.getBezeichnung() + "' ,"
//				+ "beschreibung='" + p.getBeschreibung() + "' ,'" 
//					+ "startDatum='" + format.format(p.getStartDatum()) + "' ,'"
//					+ "endDatum='" + format.format(p.getEndDatum()) + "' ,'" 
//					+ "idPerson='" + p.getIdPerson() + "' ,'"
//					+ "idMarktplatz='" + p.getIdMarktplatz() + "' ,'"
//					+ "WHERE idProjekt= '" + p.getId());
			 stmt.executeUpdate("UPDATE projekt " + "SET bezeichnung='"
			          + p.getBezeichnung() + "', beschreibung='" + p.getBeschreibung() + "', startDatum='" 
			          + format.format(p.getStartDatum()) + "', endDatum= '" 
			          + format.format(p.getEndDatum()) 
			          + "'WHERE idProjekt=" + p.getId());

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * Loeschen des uebergebenen Projektobjekts
	 * @param p das uebergebene Projektobjekt
	 */
	public void deleteProjekt(Projekt p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM projekt" + " WHERE idProjekt= " + p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	
	
}
