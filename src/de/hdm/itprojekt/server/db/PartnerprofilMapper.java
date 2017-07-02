package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class PartnerprofilMapper {

	/**
	 * Variable "partnerprofilMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static PartnerprofilMapper partnerprofilMapper = null;

	
	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse
	 * erstellen kann Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected PartnerprofilMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "PartnerprofilMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>PartnerprofilMapper</code> existieren kann.
	 * 
	 * @return partnerprofilMapper
	 */
	public static PartnerprofilMapper partnerprofilMapper() {
		if (partnerprofilMapper == null) {
			partnerprofilMapper = new PartnerprofilMapper();
		}

		return partnerprofilMapper;
	}

	/**
	 * Suchen eines Partnerprofils über die übergebene Partnerprofilnummer
	 * 
	 * @param idPartnerprofil
	 * @return Partnerprofilobjekt, das der übergebenen Partnerprofilnummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Partnerprofil findPartnerprofilByKey(int idPartnerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idPartnerprofil FROM partnerprofil " 
			+ " WHERE idPartnerprofil= " + idPartnerprofil);

			if (rs.next()) {
				Partnerprofil pp = new Partnerprofil();
				pp.setId(rs.getInt("idPartnerprofil"));

				return pp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen aller Partnerprofile
	 * @return alle Partnerprofilobjekte
	 */
	public Vector<Partnerprofil> findAllPartnerprofil() {
		Connection con = DBConnection.connection();

		Vector<Partnerprofil> result = new Vector<Partnerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM partnerprofil " + " ORDER BY idPartnerprofil");

			while (rs.next()) {
				Partnerprofil pp = new Partnerprofil();
				pp.setId(rs.getInt("idPartnerprofil"));

				result.addElement(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * Objekt Partnerprofil ausgeben nach der ID
	 * @param pp
	 * @return Partnerprofilobjekt, das der übergebenen ID entspricht
	 */
	public Partnerprofil findByPartnerprofil(Partnerprofil pp){
		 return this.findPartnerprofilByKey(pp.getId());
		 
	  }


	/**
	 * Einfuegen eines <code>Partnerprofil</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschlüssel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt. @author Thies
	 * 
	 * @param pp
	 * @return Partnerprofilobjekt wird in die Datenbank eingefuegt
	 */
	public Partnerprofil insert(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPartnerprofil) AS maxid " + "FROM partnerprofil ");

			if (rs.next()) {

				pp.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO partnerprofil (idPartnerprofil) " + "VALUES (" + pp.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pp;
	}

	/**
	 * Update des übergebenen Partnerprofilobjekts
	 * 
	 * @param pp
	 * @return Das übergebene Partnerprofilobjekt
	 */
	public Partnerprofil update(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE partnerprofil " 
			+ "SET idPartnerprofil=\"" + pp.getId() + "\" "
					+ " WHERE idPartnerprofil= " + pp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pp;
	}

	/**
	 * Loeschen des uebergebenen Partnerprofilobjekts
	 * @param pp das uebergebene Partnerprofilobjekt
	 */
	public void delete(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM partnerprofil " 
			+ " WHERE idPartnerprofil= " + pp.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}