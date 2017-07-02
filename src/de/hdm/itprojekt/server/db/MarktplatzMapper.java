package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class MarktplatzMapper {

	/**
	 * Variable "marktplatzMapper"ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static MarktplatzMapper marktplatzMapper = null;

	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse
	 * erstellen kann Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected MarktplatzMapper() {
	};

	
	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "MarktplatzMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>MarktplatzMapper</code> existieren kann.
	 * 
	 * @return marktplatzMapper
	 */
	public static MarktplatzMapper marktplatzMapper() {
		if (marktplatzMapper == null) {
			marktplatzMapper = new MarktplatzMapper();
		}
		return marktplatzMapper;
	}

	/**
	 * Einfuegen eines <code>Marktplatz</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param pm
	 * @return Marktplatzobjekt wird in die Datenbank eingefuegt
	 */
	public Marktplatz insertMarktplatz(Marktplatz pm) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idMarktplatz) AS maxid FROM marktplatz ");

			if (rs.next()) {

				pm.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO marktplatz (idMarktplatz, geschaeftsgebiet, bezeichnung) " + "VALUES ('"
						+ pm.getId() + "','" + pm.getGeschaeftsgebiet() + "','" + pm.getBezeichnung() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return pm;
	}

	/**
	 * Suchen eines Marktplatzes über die übergebene Marktplatznummer
	 * 
	 * @param idMarktplatz
	 * @return Marktplatzobjekt, das der übergebenen Marktplatznummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Marktplatz findMarktplatzByKey(int idMarktplatz) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM marktplatz " + " WHERE idMarktplatz= " + idMarktplatz);
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

	/**
	 * Suchen aller Marktplaetze
	 * @return alle Marktplatzobjekte
	 */
	public Vector<Marktplatz> findAllMarktplatz() {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> vector = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			// Datenbankabfrage aller Projekte alphabetisch sortiert nach
			// bezeichnung

			ResultSet rs = stmt.executeQuery("SELECT * FROM marktplatz " + " ORDER BY bezeichnung DESC");
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

	/**
	 * Suche des Marktplatzes nach übergebener bezeichnung
	 * @param bezeichnung
	 * @return Vector mit allen Marktplatzobjekten, die die übergebene Bezeichnung enthalten
	 */
	public Vector<Marktplatz> findMarktplatzByBezeichnung(String bezeichnung) {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> vector = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idMarktplatz, bezeichnung, geschaeftsgebiet " + " FROM marktplatz "
					+ " WHERE bezeichnung= '" + bezeichnung + "' ORDER BY bezeichnung");

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
	
	
	/**
	 * Suche nach Marktplatz über Fremdschlüssel der zugehoerigen Person
	 * 
	 * @param idPerson
	 * @return Vector mit Marktplätzen, die dem übergebenen Fremdschlüssel der
	 *         Person entsprechen
	 */
	public Vector<Marktplatz> findMarktplatzByPerson(int idPerson) {
		Connection con = DBConnection.connection();
		Vector<Marktplatz> result = new Vector<Marktplatz>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idMarktplatz, geschaeftsgebiet, bezeichnung FROM Marktplatz "
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
	

	/**
	 * Update des übergebenen Marktplatzobjekts
	 * @param pm
	 * @return Das übergebene Marktplatzobjekt
	 */
	public Marktplatz updateMarktplatz(Marktplatz pm) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE marktplatz " + "SET bezeichnung='" + pm.getBezeichnung()
					+ "', geschaeftsgebiet='" + pm.getGeschaeftsgebiet() + "' WHERE idMarktplatz= " + pm.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pm;
	}

	/**
	 * Loeschen des uebergebenen Marktplatzobjekts
	 * @param pm das uebergebene Marktplatzobjekt
	 */
	public void deleteMarktplatz(Marktplatz pm) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM marktplatz " + "WHERE idMarktplatz=" + pm.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}