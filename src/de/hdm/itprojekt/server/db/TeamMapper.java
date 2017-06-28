package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;

public class TeamMapper extends OrganisationseinheitMapper{
//Alle Mappermethoden in dieser Klasse funktionieren
	private static TeamMapper teamMapper = null;

	protected TeamMapper() {
	};

	public static TeamMapper teamMapper() {
		if (teamMapper == null) {
			teamMapper = new TeamMapper();
		}
		return teamMapper;
	}

	//insert
	public Team insertTeam(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			
			t.setId(super.insertOrganisationseinheit(t));
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(idTeam) AS maxid " + "FROM projekt ");
			
			if (rs.next()) {

				t.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO team (idTeam, teamName, mitgliederAnzahl, idUnternehmen )" 
				+ "VALUES ( " + t.getId() + " ,'" + t.getTeamName() + "','" + t.getMitgliederAnzahl() + "','" + t.getIdUnternehmen() +"')");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return t;
	}

	//Team nach ID ausgeben
	public Team findTeamByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Teams sollen alphabetisch nach Team-Namen ausgegeben
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM  team" + " WHERE idTeam= " + id );

			if (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));
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

	//alle Teams ausgeben
	public Vector<Team> findAllTeam() {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idTeam, teamName, mitgliederAnzahl, idUnternehmen FROM team " 
			+ " ORDER BY teamName");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));
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

	//Objekt Team
	public Team findByTeam(Team t){
			return this.findTeamByKey(t.getId());
			  
		  }
	
	public Vector<Team> findByTeamName(String teamName) {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idTeam, teamName, mitgliederAnzahl, idUnternehmen FROM team "
					+ " WHERE teamName= '" + teamName + "' ORDER BY teamName ");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));
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

	//update
	public Team updateTeam(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE team " 
			+ "SET teamName=\"" + t.getTeamName() + "\", " 
			+ "mitgliederAnzahl=\"" + t.getMitgliederAnzahl() + "\" " 
			+ "idUnternehmen=\"" + t.getIdUnternehmen() + "\" "
			+ " WHERE idTeam= " + t.getId());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	//delete
	public void deleteTeam(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE * FROM team " + " WHERE idTeam= " + t.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}