package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Ausschreibung;
import de.hdm.itprojekt.shared.bo.Organisationseinheit;

public class AusschreibungMapper {
	//neuerVersuch........
	private static AusschreibungMapper ausschreibungmapper = null;

	protected AusschreibungMapper() {
	}

	public static AusschreibungMapper ausschreibungmapper() {
		if (ausschreibungmapper == null) {
			ausschreibungmapper = new AusschreibungMapper();
		}

		return ausschreibungmapper;
	}

	public Ausschreibung findByKey(int id) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, owner FROM ausschreibung " + "WHERE id=" + id + " ORDER BY ausschreibung");
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

	public Vector<Ausschreibung> findAll() {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM ausschreibung " + " ORDER BY id");
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("id"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Ausschreibung> findByOwner(int id) {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM accounts " + "WHERE owner=" + id + " ORDER BY id");
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("id"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}
/*
	public Vector<Ausschreibung> findByOwner(Organisationseinheit owner) {

		return findByOwner(owner.getId());
	}
*/
	public Ausschreibung insert(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM ausschreibung ");
			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO accounts (id, bezeichung, endDatum, beschreibung) " + "VALUES (" + a.getId() + ","
								+ "," + a.getBezeichnung() + "," + a.getEndDatum() + "," + a.getBeschreibung() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public Ausschreibung update(Ausschreibung a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE ausschreibung" + "SET bezeichnug=\"" + a.getBezeichnung() + "\" ,"
					+ "endDatum=\"" + a.getEndDatum() + "\"," + "beschreibung=\"" + a.getBeschreibung() + "WHERE id="
					+ a.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public void delete(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM ausschreibung " + "WHERE id=" + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

}
