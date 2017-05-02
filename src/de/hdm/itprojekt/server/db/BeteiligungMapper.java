package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Beteiligung;

public class BeteiligungMapper {
	//neuerVersuch
	private static BeteiligungMapper beteiligungMapper = null;

	protected BeteiligungMapper() {
	}

	public static BeteiligungMapper ausschreibungmapper() {
		if (beteiligungMapper == null) {
			beteiligungMapper = new BeteiligungMapper();
		}

		return beteiligungMapper;
	}

	public Ausschreibung findByKey(int id) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt
					.executeQuery("SELECT id, owner FROM beteiligung " + "WHERE id=" + id + " ORDER BY owner");
			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("id"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Beteiligung> findAll() {
		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM beteiligung " + " ORDER BY id");
			while (rs.next()) {
				Beteiligung a = new Beteiligung();
				a.setId(rs.getInt("id"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Beteiligung> findByOwner(int id) {
		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, owner FROM beteiligung " + "WHERE owner=" + id + " ORDER BY id");
			while (rs.next()) {
				Beteiligung a = new Beteiligung();
				a.setId(rs.getInt("id"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/*
	public Vector<Beteiligung> findByOwner(Organisationseinheit owner) {

		return findByOwner(owner.getId());
	}
*/
	public Beteiligung insert(Beteiligung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM ausschreibung ");
			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO accounts (id, idProjekt, idPerson) " + "VALUES (" + a.getId() + ","
						+ a.getIdProjekt() + "," + a.getIdPerson() + "," + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public Beteiligung update(Beteiligung a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE beteiligung" + "SET idProjekt=\"" + a.getIdProjekt() + "\" ," + "idPerson=\""
					+ a.getIdPerson() + "WHERE id=" + a.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public void delete(Beteiligung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beteiligung " + "WHERE id=" + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
