package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.*;	
import java.util.*; //Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public class OrganisationseinheitMapper {

	/**
	 * Variable "organisationseinheitMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static OrganisationseinheitMapper organisationseinheitMapper = null;

	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse
	 * erstellen kann Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected OrganisationseinheitMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "OrganisationseinheitMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>OrganisationseinheitMapper</code> existieren kann.
	 * 
	 * @return organisationseinheitMapper
	 */
	public static OrganisationseinheitMapper organisationseinheitMapper() {
		if (organisationseinheitMapper == null) {
			organisationseinheitMapper = new OrganisationseinheitMapper();
		}
		return organisationseinheitMapper;
	}

	/**
	 * Einfuegen eines <code>Organisationseinheit</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param b
	 * @return Organisationseinheitsobjekt wird in die Datenbank eingefuegt
	 */
	public int insertOrganisationseinheit (Organisationseinheit o) {

		Connection con = DBConnection.connection();
		int id=0;
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idOrganisationseinheit) AS maxid " + "FROM organisationseinheit ");

			if (rs.next()) {

				o.setId(rs.getInt("maxid") + 1);
				id=o.getId();
				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO organisationseinheit (idOrganisationseinheit, adresse, standort, idPartnerprofil)" 
								+ " VALUES (" 
								+ o.getId()+ ",'" + o.getAdresse()+ "','" + o.getStandort()+ "'," + o.getIdPartnerprofil()+ ")");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * Suchen einer Organisationseinheit über die übergebene Organisationseinheitsnummer
	 * 
	 * @param id
	 * @return Ausschreibungsobjekt, das der übergebenen Ausschreibungsnummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Organisationseinheit findOrganisationseinheitByKey (int id) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idOrganisationseinheit, adresse, standort, idPartnerprofil FROM organisationseinheit " 
											+ " WHERE idOrganisationseinheit= " + id);
			

			if (rs.next()) {
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("idOrganisationseinheit"));
				o.setAdresse(rs.getString("adresse"));
				o.setStandort(rs.getString("standort"));
				o.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				
				return o;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Suchen der Organisationseinheit über den Fremdschlüssel (idPartnerprofil) des
	 * zugehoerigen Partnerprofils
	 * 
	 * @param idPartnerprofil
	 * @return Organisationseinheitsobjekt das zum Partnerprofil mit übergebenem
	 *         Fremdschlüssel gehoert oder null, bei nicht vorhandenem
	 *         Datensatz.
	 */
	public Organisationseinheit findOrganisationseinheitByPartnerprofil(int idPartnerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT idOrganisationseinheit, adresse, standort, idPartnerprofil FROM organisationseinheit "
							+ " WHERE idPartnerprofil= " + idPartnerprofil);

			if (rs.next()) {
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("idOrganisationseinheit"));
				o.setAdresse(rs.getString("adresse"));
				o.setStandort(rs.getString("standort"));
				o.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				return o;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Objekt Organisationseinheit ausgeben nach der ID
	 * @param o
	 * @return Organisationseinheitsobjekt, das der übergebenen ID entspricht
	 */
	public Organisationseinheit findByOrganisationseinheit(Organisationseinheit o){
		return this.findOrganisationseinheitByKey(o.getId());
		}
	
	
	/**
	 * Loeschen des uebergebenen Organisationseinheitsobjekts
	 * @param o das uebergebene Organisationseinheitsobjekt
	 */
	public void deleteOrganisationseinheit (Organisationseinheit o) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM `organisationseinheit` WHERE `idOrganisationseinheit` =" + o.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
	
	/**
	 * Update des übergebenen Organisationseinheitsobjekts
	 * @param o
	 * @return Das übergebene Organisationseinheitsobjekt
	 */
	public int updateOrganisationseinheit(Organisationseinheit o) {
		Connection con = DBConnection.connection();
		int id = 0;
		try {
			id = o.getId();

			Statement stmt = con.createStatement();
			if (o.getIdPartnerprofil() != null) {
				stmt.executeUpdate("UPDATE organisationseinheit " + "SET adresse='" + o.getAdresse() + "',"
						+ "standort='" + o.getStandort() + "'," + "idPartnerprofil=" + o.getIdPartnerprofil()
						+ " WHERE idOrganisationseinheit=" + o.getId());
			} else {
				stmt.executeUpdate("UPDATE organisationseinheit " + "SET adresse='" + o.getAdresse() + "',"
						+ "standort='" + o.getStandort() + "',"
						+ "idPartnerprofil = NULL WHERE idOrganisationseinheit= " + o.getId());
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return id;

	}
	
	
	/**
	 * Update des übergebenen Organisationseinheitsobjekts
	 * @param o
	 * @return Das übergebene Organisationseinheitsobjekt
	 */
	public int updateOrganisationseinheitVonTeam(Organisationseinheit o) {
		Connection con = DBConnection.connection();
		int id = 0;
		try {
			id = o.getId();

			Statement stmt = con.createStatement();
			if (o.getIdPartnerprofil() != null) {
				stmt.executeUpdate("UPDATE organisationseinheit " + "SET adresse='" + o.getAdresse() + "',"
						+ "standort='" + o.getStandort() + "'" + "WHERE idOrganisationseinheit= " + o.getId());
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return id;

	}
}