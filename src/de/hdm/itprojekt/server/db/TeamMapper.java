package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;
import de.hdm.itprojekt.shared.bo.Unternehmen;

public class TeamMapper extends OrganisationseinheitMapper{
	
	/**
	 * Variable "teamMapper" ist aufgrund des Bezeichners "static" nur
	 * einmal für die Instanzen dieser Klasse verfügbar. Sie speichert die
	 * einzige Instanz dieser Klasse.
	 * 
	 * @author Thies
	 */
	private static TeamMapper teamMapper = null;

	/**
	 * Konstruktor, der verhindert, dass man neue Instanzen dieser Klasse
	 * erstellen kann. Sie speichert die einzige Instanz dieser Klasse
	 * 
	 * @author Thies
	 */
	protected TeamMapper() {
	};

	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der
	 * "TeamMapper"-Klasse sicher, sodass nur eine Instanz von
	 * <code>TeamMapper</code> existieren kann.
	 * 
	 * @return teamMapper
	 */
	public static TeamMapper teamMapper() {
		if (teamMapper == null) {
			teamMapper = new TeamMapper();
		}
		return teamMapper;
	}

	/**
	 * Einfuegen eines <code>Team</code>-Objekts in die Datenbank.
	 * Dabei wird auch der Primaerschlüssel des uebergebenen Objekts geprueft
	 * und ggf. berichtigt. @author Thies
	 * 
	 * @param t
	 * @return Teamobjekt wird in die Datenbank eingefuegt
	 */
	public Team insertTeam(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			t.setId(super.insertOrganisationseinheit(t));
			ResultSet rs = stmt.executeQuery("SELECT MAX(idTeam) AS maxid " + "FROM team");

			if (rs.next()) {

				// t.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO team (idTeam, teamName, idUnternehmen) " + "VALUES (" + t.getId() + ",'"
						+ t.getTeamName() + "'," + t.getIdUnternehmen() + ")");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return t;
	}

	/**
	 * Suchen einer Team über die übergebene Teamnummer
	 * 
	 * @param id
	 * @return Teamobjekt, das der übergebenen Teamnummer
	 *         entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Team findTeamByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM  team" + " WHERE idTeam= " + id);

			if (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setIdUnternehmen(rs.getInt("idUnternehmen"));
				t.setAdresse(super.findOrganisationseinheitByKey(id).getAdresse());
				t.setStandort(super.findOrganisationseinheitByKey(id).getStandort());
				t.setIdPartnerprofil(super.findOrganisationseinheitByKey(id).getIdPartnerprofil());

				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	/**
	 * Suchen aller Teams
	 * @return alle Teamobjekte
	 */
	public Vector<Team> findAllTeam() {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idTeam, teamName, idUnternehmen FROM team " + " ORDER BY teamName");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setIdUnternehmen(rs.getInt("idUnternehmen"));
				t.setAdresse(super.findByOrganisationseinheit(t).getAdresse());
				t.setStandort(super.findByOrganisationseinheit(t).getStandort());
				t.setIdPartnerprofil(super.findByOrganisationseinheit(t).getIdPartnerprofil());

				result.addElement(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Objekt Team ausgeben nach der ID
	 * @param t
	 * @return Teamobjekt, das der übergebenen ID entspricht
	 */
	public Team findByTeam(Team t){
			return this.findTeamByKey(t.getId());
			  
		  }
	
	/**
	 * Suchen des Teams nach dem Namen des Teams
	 * @param teamName
	 * @return Vector mit Team-Objekten, die dem übergebenen Teamnamen entsprechen
	 */
	public Vector<Team> findByTeamName(String teamName) {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idTeam, teamName,  idUnternehmen FROM team " + " WHERE teamName= '"
					+ teamName + "' ORDER BY teamName ");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setAdresse(super.findByOrganisationseinheit(t).getAdresse());
				t.setStandort(super.findByOrganisationseinheit(t).getStandort());
				t.setIdPartnerprofil(super.findByOrganisationseinheit(t).getIdPartnerprofil());

				result.addElement(t);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Update des übergebenen Teamobjekts
	 * @param t
	 * @return Das übergebene Teamobjekt
	 */
	public Team updateTeam(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			t.setId(super.updateOrganisationseinheitVonTeam(t));
			super.organisationseinheitMapper().updateOrganisationseinheitVonTeam(t);
			stmt.executeUpdate("UPDATE team " 
			+ "SET teamName=\"" + t.getTeamName() + "\", " 
			+ "idUnternehmen=\"" + t.getIdUnternehmen() + "\""
			+ " WHERE idTeam= " + t.getId());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Loeschen des uebergebenen Teamobjekts
	 * @param t das uebergebene Teamobjekt
	 */
	public void deleteTeamInteger(Integer t) {
		Team t1 = new Team();	
		t1.setId(t);
		
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM `team` WHERE `idTeam` = " + t1.getId());
			super.deleteOrganisationseinheit(t1);
		} catch (SQLException e) {
			e.printStackTrace();
		}}
		public void deleteTeam(Team t) {
			this.deleteTeamInteger(t.getId());
		}
	
}