package de.hdm.itprojekt.server.db;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;


public class BewerbungMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Variable "bewerbungMapper"ist aufgrund des Bezeichners "static" nur einmal für die Instanzen dieser Klasse
	 * verfügbar. Sie speichert die die einzige Instanz dieser Klasse.
	 * @author Thies
	 */
	private static BewerbungMapper bewerbungMapper = null;

	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse erstellen kann
	 * Sie speichert die einzige Instanz dieser Klasse
	 * @author Thies
	 */
	protected BewerbungMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der "BewerbungMapper"-Klasse sicher,
	 * sodass nur eine  Instanz von <code>BewerbungMapper</code> existieren kann.
	 * @return bewerbungMapper
	 */
	public static BewerbungMapper bewerbungmapper() {
		if (bewerbungMapper == null) {
			bewerbungMapper = new BewerbungMapper();
		}

		return bewerbungMapper;
	}
	
	/*##########################################################
	*################### METHODE ERKLAEREN #############################
	*######################################################## 
	*/
	
	//Objet Bewerbung ausgabe
	public Bewerbung findByBewerbung(Bewerbung b) {

		return this.findBewerbungByKey(b.getId());
	}

	public Bewerbung findBewerbungByKey(int idBewerbung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM bewerbung " + " WHERE idBewerbung= " + idBewerbung + " ORDER BY idBewerbung");

			if (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsstatus")));

				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen aller Bewerbungen
	 * @return alle Bewerbungsobjekte
	 */
	public Vector<Bewerbung> findAllBewerbungen () {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung " 
											+ " ORDER BY idBewerbung");
			
			while (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsStatus")));
				
				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Suchen einer Bewerbung über den Fremdschlüssel "idAusschreibung" der zugehoerigen Ausschreibung
	 * @param idAusschreibung
	 * @return Bewerbung die dem übergebenem Fremdschlüssel der Ausschreibung entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Vector<Bewerbung> findBewerbungByAusschreibung(int idAusschreibung) {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> vector = new Vector();

		try {
			Statement stmt = con.createStatement();

			// Hole alle Bewerbungen, deren Ids nicht bereits bei der Tabelle
			// Bewertungen in der Spalte idBewerbung sind.
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung WHERE idAusschreibung= " + idAusschreibung
					+ " having idBewerbung not in (select idBewerbung from bewertung)");

			// DAS ALTE STATEMENT
			// ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung "
			// + " WHERE idAusschreibung= " + idAusschreibung);

			while (rs.next()) {

				Bewerbung b = new Bewerbung();

				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsStatus")));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));

				vector.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vector;
	}
	
	
	
	/**
	 * Der Bewerber wird über die Bewerbung gefunden
	 * Suchen des Bewerbers über den Fremdschlüssel "idBewerbung" der zugehoerigen Bewerbung
	 * @param idBewerbung
	 * @return Bewerber der dem übergebenem Fremdschlüssel der Bewerbung entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Vector<Bewerbung> findBewerbungByBewerber(int idBewerbung) {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung " + " WHERE idOrganisationseinheit = "
					+ idBewerbung + " ORDER BY idBewerbung");

			while (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsstatus")));

				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Einfuegen eines <code>Bewerbung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschlüssel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt. @author Thies
	 * 
	 * @param b
	 * @return Bewerbungsobjekt wird in die Datenbank eingefuegt
	 */
	public Bewerbung insertBewerbung(Bewerbung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewerbung) AS maxid " + " FROM bewerbung ");
			if (rs.next()) {

				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO bewerbung (idBewerbung, bewerbungstext, erstellDatum, idOrganisationseinheit, idAusschreibung, bewerbungsStatus) "
								+ "VALUES (" + b.getId() + ",'" + b.getBewerbungsText() + "','"
								+ format.format(b.getErstellDatum()) + "','" + b.getIdOrganisationseinheit() + "','"
								+ b.getIdAusschreibung() + "','" + b.getBewerbungsStatus() + "')");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * Update des übergebenen Bewerbungsobjekts
	 * @param b
	 * @return Das übergebene Bewerbungsobjekt
	 */
	public Bewerbung updateBewerbung (Bewerbung b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE bewerbung " 
					+ "SET idBewerbung='" + b.getId() + "' ,'" 
					+ "idOrganisationseinheit='" + b.getIdOrganisationseinheit() + "' ,'" 
					+ "idAusschreibung='" + b.getIdAusschreibung() + "' ,'" 
					+ "bewerbungsText='" + b.getBewerbungsText() + "' ,'" 
					+ "erstellDatum='" + b.getErstellDatum() + "' ,'" 
					+ "bewerbungsstatus='" + b.getBewerbungsStatus() + "' ,'" 
					+ " WHERE idBewerbung= '"+ b.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * Loeschen des uebergebenen Bewerbungsobjekts
	 * @param b das uebergebene Bewerbungsobjekt
	 */
	public void deleteBewerbung(Bewerbung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bewerbung" + " WHERE idBewerbung= " + b.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
