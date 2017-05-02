package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Bewerber;

public class BewerberMapper {
//neuerVersuch
	
	private static BewerberMapper bewerberMapper = null;

	protected BewerberMapper() {
	}

	public static BewerberMapper ausschreibungmapper() {
		if (bewerberMapper == null) {
			bewerberMapper = new BewerberMapper();
		}

		return bewerberMapper;
	}

	public Bewerber findByKey(int id) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM bewerbung " + "WHERE id=" + id + " ORDER BY owner");
			if (rs.next()) {
				Bewerber a = new Bewerber();
				a.setId(rs.getInt("id"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Bewerber> findAll() {
		Connection con = DBConnection.connection();
		Vector<Bewerber> result = new Vector<Bewerber>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM bewerbung " + " ORDER BY id");
			while (rs.next()) {
				Bewerber a = new Bewerber();
				a.setId(rs.getInt("id"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Bewerber> findByOwner(int id) {
		Connection con = DBConnection.connection();
		Vector<Bewerber> result = new Vector<Bewerber>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, owner FROM bewerber " + "WHERE owner=" + id + " ORDER BY id");
			while (rs.next()) {
				Bewerber a = new Bewerber();
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

	public Bewerber insert(Bewerber a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM ausschreibung ");
			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO accounts (id, bewerber, bewerbungsText, erstelluDatum) " + "VALUES ("
						+ a.getId() + "," + "," + a.getBewerber() + "," + a.getBewerbungsText() + ","
						+ a.getErstellDatum() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public Bewerber update(Bewerber a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE beteiligung" + "SET bewerber=\"" + a.getBewerber() + "\" ," + "bewerbungsText=\""
					+ a.getBewerbungsText() + "\" ," + "erstellDatum=\"" + a.getErstellDatum() + "WHERE id="
					+ a.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public void delete(Bewerber a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bewerber" + "WHERE id=" + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
