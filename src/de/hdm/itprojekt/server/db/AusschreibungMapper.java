package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;

public class AusschreibungMapper {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Variable "ausschreibungMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static AusschreibungMapper ausschreibungMapper = null;

	/**
	 * Konstruktor, der verhindert, dass man neue Instanzen dieser Klasse
	 * erstellen kann. Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected AusschreibungMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "AusschreibungMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>AusschreibungMapper</code> existieren kann.
	 * 
	 * @return ausschreibungMapper
	 */
	public static AusschreibungMapper ausschreibungMapper() {
		if (ausschreibungMapper == null) {
			ausschreibungMapper = new AusschreibungMapper();
		}

		return ausschreibungMapper;
	}

	/**
	 * Suchen einer Ausschreibung über den Fremdschlüssel des zugehoerigen
	 * Partnerprofils (idPartnerprofil)
	 * 
	 * @param idPartnerprofil
	 * @return Ausschreibungsobjekt das zum Partnerprofil mit übergebenem
	 *         Fremdschlüssel gehoert oder null, bei nicht vorhandenem
	 *         Datensatz.
	 */
	public Ausschreibung findAusschreibungbyIdPartnerprofil(int idPartnerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM ausschreibung " + "WHERE idPartnerprofil= " + idPartnerprofil);

			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				return a;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen eines Partnerprofils über den Fremdschlüssel "idAusschreibung" der
	 * zugehoerigen Ausschreibung
	 * 
	 * @param idAusschreibung
	 * @return Partnerprofil das dem übergebenem Fremdschlüssel der
	 *         Ausschreibung entspricht oder null bei nicht vorhandenem
	 *         Datensatz
	 */
	public Ausschreibung findpartnerprofilIdbyAusschreibung(int idAusschreibung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT idPartnerprofil FROM ausschreibung " + "WHERE idAusschreibung= " + idAusschreibung);

			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen einer Ausschreibung über die übergebene Ausschreibungsnummer
	 * 
	 * @param idAusschreibung
	 * @return Ausschreibungsobjekt, das der übergebenen Ausschreibungsnummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Ausschreibung findAusschreibungByKey(int idAusschreibung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " WHERE idAusschreibung= "
					+ idAusschreibung + " ORDER BY bezeichnung");

			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Suchen aller Ausschreibungen
	 * @return alle Ausschreibungsobjekte
	 */
	public Vector<Ausschreibung> findAllAusschreibungen() {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " ORDER BY idAusschreibung DESC");

			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Suchen von Ausschreibungen über den Fremdschlüssel (idProjekt) der
	 * zugehoerigen Projekte
	 * 
	 * @param idProjekt
	 * @return Vector mit Ausschreibungsobjekten, die zum Projekt mit
	 *         übergebenem Fremdschlüssel gehören
	 */
	public Vector<Ausschreibung> findAusschreibungByProjekt(int idProjekt) {

		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " WHERE idProjekt= " + idProjekt
					+ " ORDER BY idAusschreibung DESC");

			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();

				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				result.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// #############################################################################################
	// ###################################################### METHODENKORREKTUR
	// UNTERHALB #########################################################
	// #######################################################################################

	/**
	 * Eine bestimmte Ausschreibung Anzeigen lassen
	 * 
	 * @param a
	 * @return Vector mit genau einer bestimmten Ausschreibung
	 */
	public Vector<Ausschreibung> findByAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " WHERE idAusschreibung = " + a.getId()
					+ " ORDER BY idAusschreibung");

			while (rs.next()) {
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Suchen einer Ausschreibung über Fremdschlüssel des zugehoerigen
	 * Partnerprofils
	 * 
	 * @param idPartnerprofil
	 * @return Vector mit Ausschreibungen, die dem übergebenen Fremdschlüssel
	 *         des Partnerprofils entsprechen
	 */
	public Vector<Ausschreibung> findAusschreibungByPartnerprofil(int idPartnerprofil) {

		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " WHERE idPartnerprofil= "
					+ idPartnerprofil + " ORDER BY idAusschreibung");

			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();

				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				result.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Suchen von Ausschreibungen über Fremdschlüssel des Ausschreibenden
	 * 
	 * @param idAusschreibender
	 * @return Vector mit allen Ausschreibungen, die eine Organisationseinheit
	 *         mit übergebenem Fremdschlüssel erstellt hat.
	 */
	public Vector<Ausschreibung> findAusschreibungByAusschreibender(int idAusschreibender) {

		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " + " WHERE idAusschreibender= "
					+ idAusschreibender + " ORDER BY idAusschreibung DESC");

			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();

				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				result.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Einfuegen eines <code>Ausschreibung</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param a
	 * @return Ausschreibungsobjekt wird in die Datenbank eingefuegt
	 */
	public Ausschreibung insertAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idAusschreibung) AS maxid FROM ausschreibung ");

			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO ausschreibung (idAusschreibung, bezeichnung, endDatum, idProjekt, beschreibung, idPartnerprofil, idAusschreibender, status) "
								+ "VALUES ('" + a.getId() + "','" + a.getBezeichnung() + "','"
								+ format.format(a.getEndDatum()) + "','" + a.getIdProjekt() + "','"
								+ a.getBeschreibung() + "','" + a.getIdPartnerprofil() + "','"
								+ a.getIdAusschreibender() + "','" + a.getAusschreibungsstatus() + "')");
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	/**
	 * Update des übergebenen Ausschreibungsobjekts
	 * @param a
	 * @return Das übergebene Ausschreibungsobjekt
	 */
	public Ausschreibung updateAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// stmt.executeUpdate("UPDATE ausschreibung "
			// + "SET idAusschreibung= '" + a.getId() + "' ,'"
			// + " bezeichnung= '" + a.getBezeichnung() + "' ,'"
			// + " beschreibung= '" + a.getBeschreibung() + "' ,'"
			// + " idAusschreibender= '" + a.getIdAusschreibender() + "' ,'"
			// + " status= '" + a.getAusschreibungsstatus() + "' ,'"
			// + " endDatum= '" + format.format(a.getEndDatum()) + "' ,'"
			// + " idProjekt= '" + a.getIdProjekt() + "' ,'"
			// + " WHERE idAusschreibung= '" + a.getId());

			stmt.executeUpdate("UPDATE ausschreibung " + "SET bezeichnung='" + a.getBezeichnung() + "', beschreibung='"
					+ a.getBeschreibung() + "', status='" + a.getAusschreibungsstatus() + "', endDatum= '"
					+ format.format(a.getEndDatum()) + "'WHERE idAusschreibung=" + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;

	}

	/**
	 * Loeschen des uebergebenen Ausschreibungsobjekts
	 * @param a das uebergebene Ausschreibungsobjekt
	 */
	public void deleteAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM ausschreibung " + " WHERE idAusschreibung= " + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

}
