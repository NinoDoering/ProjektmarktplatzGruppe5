package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;
import java.*;		//Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;

import de.hdm.itprojekt.shared.bo.*;

public class EigenschaftMapper {

	/**
	 * Variable "eigenschaftMapper"ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static EigenschaftMapper eigenschaftMapper = null;

	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse erstellen kann
	 * Sie speichert die einzige Instanz dieser Klasse
	 * @author Thies
	 */
	protected EigenschaftMapper() {
	
	}
	
	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der "EigenschaftMapper"-Klasse sicher,
	 * sodass nur eine  Instanz von <code>EigenschaftMapper</code> existieren kann.
	 * @return eigenschaftMapper
	 */
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	/**
	 * Objekt Eigenschaft ausgeben nach der ID
	 * @param eigenschaft
	 * @return Eigenschaftsobjekt, das der übergebenen ID entspricht
	 */
	public Eigenschaft findByEigenschaft(Eigenschaft e) {
    	return this.findEigenschaftByKey(e.getId());
    }
	
	/**
	 * Einfuegen eines <code>Eigenschaft</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschlüssel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt. @author Thies
	 * 
	 * @param e
	 * @return Eigenschaftsobjekt wird in die Datenbank eingefuegt
	 */
	public Eigenschaft insertEigenschaft(Eigenschaft e) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idEigenschaft) AS maxid " + " FROM eigenschaft ");

			if (rs.next()) {

				e.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO eigenschaft (idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, idPartnerprofil, employmentStatus)"
								+ " VALUES ('" + e.getId() + "','" + e.getAusbildung() + "','" + e.getAbschluss()
								+ "','" + e.getBerufserfahrungsJahre() + "','" + e.getArbeitsgebiet() + "','"
								+ e.getSprachkenntnisse() + "','" + e.getIdPartnerprofil() + "','"
								+ e.getEmploymentStatus() + "')");

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	/**
	 * Suchen einer Eigenschaft über die übergebene Eigenschaftsnummer
	 * @param idEigenschaft
	 * @return Eigenschaftsobjekt, das der übergebenen Eigenschaftsnummer entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Eigenschaft findEigenschaftByKey(int idEigenschaft) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft " + " WHERE idEigenschaft= " + idEigenschaft
					+ " ORDER BY idEigenschaft");

			if (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setAusbildung(rs.getString("ausbildung"));
				e.setAbschluss(rs.getString("abschluss"));
				e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
				e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				e.setEmploymentStatus(rs.getString("employmentStatus"));
				e.setIdPartnerprofil(rs.getInt("IdPartnerprofil"));

				return e;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Suchen aller Eigenschaften
	 * @return alle Eigenschaftsobjekte
	 */
	public Vector<Eigenschaft> findAllEigenschaften() {
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> vector = new Vector<Eigenschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft " + " ORDER BY idEigenschaft ");

			while (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setAusbildung(rs.getString("ausbildung"));
				e.setAbschluss(rs.getString("abschluss"));
				e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
				e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				e.setEmploymentStatus(rs.getString("employmentStatus"));
				e.setIdPartnerprofil(rs.getInt("idPartnerprofil"));

				vector.addElement(e);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return vector;
	}
	
	/**
	 * Suchen einer Eigenschaft über Fremdschlüssel des zugehoerigen
	 * Partnerprofils
	 * 
	 * @param idPartnerprofil
	 * @return Vector mit Eigenschaften, die dem übergebenen Fremdschlüssel
	 *         des Partnerprofils entsprechen
	 */
	public Vector<Eigenschaft> findEigenschaftByPartnerprofil(int idPartnerprofil) {

		Vector<Eigenschaft> vector = new Vector<Eigenschaft>();
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM eigenschaft " + " WHERE idPartnerprofil= " + idPartnerprofil);

			while (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setAusbildung(rs.getString("ausbildung"));
				e.setAbschluss(rs.getString("abschluss"));
				e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
				e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				e.setEmploymentStatus(rs.getString("employmentStatus"));
				e.setIdPartnerprofil(rs.getInt("idPartnerprofil"));

				vector.add(e);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return vector;
	}

	/**
	 * Update des übergebenen Eigenschaftsobjekts
	 * 
	 * @param e
	 * @return Das übergebene Eigenschaftsobjekt
	 */
	public Eigenschaft updateEigenschaft(Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE eigenschaft " + "SET ausbildung='" + e.getAusbildung() + "', abschluss='"
					+ e.getAbschluss() + "', berufserfahrungsJahre='" + e.getBerufserfahrungsJahre()
					+ "', arbeitsgebiet='" + e.getArbeitsgebiet() + "', sprachkenntnisse='" + e.getSprachkenntnisse()
					+ "', employmentStatus='" + e.getEmploymentStatus() + "', idPartnerprofil='"
					+ e.getIdPartnerprofil() + "'WHERE idEigenschaft=" + e.getId());

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return e;
	}

	/**
	 * Update des übergebenen Eigenschaftsobjekts
	 * @param e
	 * @return Das übergebene Eigenschaftsobjekt
	 */
	public Eigenschaft updateEigenschaftnachPP (Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE eigenschaft " 
					+ "SET ausbildung='" + e.getAusbildung() + "', abschluss='" 
					+ e.getAbschluss() + "', berufserfahrungsJahre='" + e.getBerufserfahrungsJahre() + "', arbeitsgebiet='" 
					+ e.getArbeitsgebiet()+ "', sprachkenntnisse='" 
					+ e.getSprachkenntnisse()+ "', employmentStatus='" 
					+ e.getEmploymentStatus()+ "', idPartnerprofil='" + e.getIdPartnerprofil() + "'WHERE idPartnerprofil=" + e.getIdPartnerprofil());

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return e;
	}
	
	
	/**
	 * Loeschen des uebergebenen Eigenschaftsobjekts
	 * @param e das uebergebene Eigenschaftsobjekt
	 */
	public void deleteEigenschaft(Eigenschaft e) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM `eigenschaft` WHERE `idEigenschaft`= " + e.getId());

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
		
	