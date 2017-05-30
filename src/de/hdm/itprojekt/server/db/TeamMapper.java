package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;

public class TeamMapper {
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

	public Team insert(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idTeam) AS maxid " + "FROM projekt ");

			if (rs.next()) {

				t.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO Team (idTeam, teamName, mitgliederAnzahl )" + "VALUES ( " + t.getIdTeam()
						+ " ,'" + t.getTeamName() + "','" + t.getMitgliederAnzahl() + "')");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return t;
	}

	public Team findbyKey(int idTeam) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Teams sollen alphabetisch nach Team-Namen ausgegeben
			ResultSet rs = stmt.executeQuery(
					"SELECT idTeam, teamName, mitgliederAnzahl" + " FROM  team" + " WHERE idTeam=" + idTeam );

			if (rs.next()) {
				Team t = new Team();
				t.setIdTeam(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));

				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;

	}

	public Vector<Team> findAll() {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idTeam, teamName, mitgliederAnzahl " + "FROM team " + "ORDER BY teamName");

			while (rs.next()) {
				Team t = new Team();
				t.setIdTeam(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));

				result.addElement(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Team> findByTeamName(String teamName) {
		Connection con = DBConnection.connection();
		Vector<Team> result = new Vector<Team>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idTeam, teamName, mitgliederAnzahl" + " FROM team "
					+ "WHERE teamName LIKE '" + teamName + "' ORDER BY teamName ");

			while (rs.next()) {
				Team t = new Team();
				t.setIdTeam(rs.getInt("idTeam"));
				t.setTeamName(rs.getString("teamName"));
				t.setMitgliederAnzahl(rs.getInt("mitgliederAnzahl"));

				result.addElement(t);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Team update(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE team " + "SET teamName=\"" + t.getTeamName() + "\", " + "mitgliederAnzahl=\""
					+ t.getMitgliederAnzahl() + "\" " + "WHERE idTeam" + t.getIdTeam());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public void delete(Team t) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE from team" + "WHERE idTeam = " + t.getIdTeam());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}