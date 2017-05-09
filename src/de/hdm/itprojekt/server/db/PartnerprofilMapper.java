package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class PartnerprofilMapper {

	private static PartnerprofilMapper partnerprofilMapper = null;

	protected PartnerprofilMapper() {
	}

	public static PartnerprofilMapper partnerprofilMapper() {
		if (partnerprofilMapper == null) {
			partnerprofilMapper = new PartnerprofilMapper();
		}

		return partnerprofilMapper;
	}

	// findByKey
	public Partnerprofil findByKey(int idPartnerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idPartnerprofil FROM partnerprofil " + "WHERE idPartnerprofil=" + idPartnerprofil);

			if (rs.next()) {
				Partnerprofil p = new Partnerprofil();
				p.setIdPartnerprofil(rs.getInt("idPartnerprofil"));

				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Partnerprofil> findAll() {
		Connection con = DBConnection.connection();

		Vector<Partnerprofil> result = new Vector<Partnerprofil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idPartnerprofil " + "FROM partnerprofil ");

			while (rs.next()) {
				Partnerprofil p = new Partnerprofil();
				p.setIdPartnerprofil(rs.getInt("idPartnerprofil"));

				result.addElement(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// INSERT INTO
	public Partnerprofil insert(Partnerprofil p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerprofil ");

			if (rs.next()) {

				p.setIdPartnerprofil(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO partnerprofil (idPartnerprofil) " + "VALUES (" + p.getIdPartnerprofil() + ")");
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}

		return p;
	}

	// UPDATE
	public Partnerprofil update(Partnerprofil p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE partnerprofil " + "SET idPartnerprofil=\"" + p.getIdPartnerprofil() + "\" "
					+ "WHERE idPartnerprofil=" + p.getIdPartnerprofil());

		} catch (SQLException e4) {
			e4.printStackTrace();
		}

		return p;
	}

	// DELETE
	public void delete(Partnerprofil p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM partnerprofil " + "WHERE idPartnerprofil=" + p.getIdPartnerprofil());
		} catch (SQLException e5) {
			e5.printStackTrace();
		}
	}
}