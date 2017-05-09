package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Projekt;
import de.hdm.itprojekt.shared.bo.Team;

public class TeamMapper {

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

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM projekt ");

			if (rs.next()) {

				t.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO Team (id, teamName, mitgliederAnzahl )" + "VALUES ( " + t.getId()
						+ " ,'" + t.getTeamName() + "','" + t.getMitgliederAnzahl() + "')");
			}
		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return t;
	}

	public Team findbyKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Teams sollen alphabetisch nach Team-Namen ausgegeben
			ResultSet rs = stmt.executeQuery(
					"SELECT id, teamName, mitgliederAnzahl FROM team" + "WHERE id=" + id + "ORDER by teamName");

			if (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("id"));
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
					.executeQuery("SELECT id, teamName, mitgliederAnzahl" + "FROM team " + "ORDER BY teamName");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("id"));
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

			ResultSet rs = stmt.executeQuery("SELECT id, teamName, mitgliederAnzahl" + " FROM team "
					+ "WHERE bezeichnung LIKE '" + teamName + "' ORDER BY teamName ");

			while (rs.next()) {
				Team t = new Team();
				t.setId(rs.getInt("id"));
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
					+ t.getMitgliederAnzahl() + "\" " + "WHERE id" + t.getId());
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
			stmt.executeQuery("DELETE from team" + "WHERE id = " + t.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}