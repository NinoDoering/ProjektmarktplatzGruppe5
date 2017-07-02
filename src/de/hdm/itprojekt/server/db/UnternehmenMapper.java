package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;
import de.hdm.itprojekt.server.db.PersonMapper;
import de.hdm.itprojekt.shared.bo.*;

public class UnternehmenMapper extends OrganisationseinheitMapper {

	/**
	 * Variable "unternehmenMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static UnternehmenMapper unternehmenMapper = null;

	/**
	 * Konstruktor, der verhindert, dass man neue Instanzen dieser Klasse
	 * erstellen kann. Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected UnternehmenMapper() {
	}

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "UnternehmenMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>UnternehmenMapper</code> existieren kann.
	 * 
	 * @return unternehmenMapper
	 */
	public static UnternehmenMapper unternehmenMapper() {
		if (unternehmenMapper == null) {
			unternehmenMapper = new UnternehmenMapper();
		}

		return unternehmenMapper;
	}

	/**
	 * Suchen aller Unternehmen
	 * @return alle Unternehmensobjekte
	 */
	public Unternehmen findUnternehmenByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idUnternehmen, firmenName FROM unternehmen " + " WHERE idUnternehmen= " + id);

			if (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("idUnternehmen"));
				u.setFirmenName(rs.getString("firmenName"));
				u.setAdresse(super.findOrganisationseinheitByKey(id).getAdresse());
				u.setStandort(super.findOrganisationseinheitByKey(id).getStandort());
				u.setIdPartnerprofil(super.findOrganisationseinheitByKey(id).getIdPartnerprofil());

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
	
	/**
	 * Suchen der Unternehmen nach dem uebergebenen Firmenname
	 * @param firmenName
	 * @return Unternehmen-Objekt, das dem übergebenen Firmennamen entspricht
	 */
	public Unternehmen findUnternehmenByFirmenName(String firmenName) {
		Connection con = DBConnection.connection();
		Unternehmen result = new Unternehmen();
		
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM unternehmen WHERE firmenName = '" + firmenName + "' ORDER BY idUnternehmen");

			while (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setFirmenName(rs.getString("firmenName"));
				u.setId(rs.getInt("idUnternehmen"));
				u.setAdresse(super.findByOrganisationseinheit(u).getAdresse());
				u.setStandort(super.findByOrganisationseinheit(u).getStandort());
				u.setIdPartnerprofil(super.findByOrganisationseinheit(u).getIdPartnerprofil());
				
				result=u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * Objekt Unternehmen ausgeben nach der ID
	 * @param unternehmen
	 * @return Unternehmensobjekt, das der übergebenen ID entspricht
	 */
	public Unternehmen findByUnternehmen(Unternehmen u){
		return this.findUnternehmenByKey(u.getId());}

	/**
	 * Suchen aller Unternehmen
	 * @return alle Unternehmensobjekte
	 */
	public Vector<Unternehmen> findAllUnternehmen() {
		Connection con = DBConnection.connection();
		Vector<Unternehmen> result = new Vector<Unternehmen>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM unternehmen " + " ORDER BY firmenName");

			while (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("idUnternehmen"));
				u.setFirmenName(rs.getString("firmenName"));
				u.setAdresse(super.findByOrganisationseinheit(u).getAdresse());
				u.setStandort(super.findByOrganisationseinheit(u).getStandort());
				u.setIdPartnerprofil(super.findByOrganisationseinheit(u).getIdPartnerprofil());

				result.addElement(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * Einfuegen eines <code>Unternehmen</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param u
	 * @return Unternehmensobjekt wird in die Datenbank eingefuegt
	 */
	public Unternehmen insertUnternehmen(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			u.setId(super.insertOrganisationseinheit(u));
			ResultSet rs = stmt.executeQuery("SELECT MAX(idUnternehmen) AS maxid " + "FROM unternehmen ");
			// Id wird wom�glich ben�tigt!

			if (rs.next()) {
				
//				u.setId(rs.getInt("maxid") + 1);
				

				stmt = con.createStatement();	

				stmt.executeUpdate("INSERT INTO unternehmen (idUnternehmen, firmenName) " 
				+ "VALUES (" + u.getId() + ",'" + u.getFirmenName() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	/**
	 * Update des übergebenen Unternehmenobjekts
	 * @param u
	 * @return Das übergebene Unternehmensobjekt
	 */
	public Unternehmen updateUnternehmen(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			u.setId(super.updateOrganisationseinheit(u));
			super.organisationseinheitMapper().updateOrganisationseinheit(u);
			stmt.executeUpdate("UPDATE unternehmen SET " + "firmenName=\"" 
			+ u.getFirmenName()  + "\"" + "WHERE idUnternehmen=" + u.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return u;
	}

	/**
	 * Loeschen des uebergebenen Unternehmenobjekts
	 * @param u das uebergebene Unternehmensobjekt
	 */
	public void deleteUnternehmenInteger(Integer u) {
		Unternehmen u1 = new Unternehmen();
		u1.setId(u);

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `unternehmen` WHERE `idUnternehmen` =" + u1.getId());
			super.deleteOrganisationseinheit(u1);
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
	/**
	 * Loeschen des uebergebenen Unternehmenobjekts
	 * @param u das uebergebene Unternehmensobjekt
	 */
	public void deleteUnternehmen(Unternehmen u) {
		this.deleteUnternehmenInteger(u.getId());
	}

}