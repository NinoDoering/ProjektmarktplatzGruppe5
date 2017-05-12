package de.hdm.itprojekt.server.db;

import java.sql.*;

import de.hdm.itprojekt.shared.bo.Organisationseinheit;

public class OrganisationseinheitMapper {

	private static OrganisationseinheitMapper organisationseinheitMapper = null;

	protected OrganisationseinheitMapper() {
	};

	public static OrganisationseinheitMapper organisationseinheitMapper() {
		if (organisationseinheitMapper == null) {
			organisationseinheitMapper = new OrganisationseinheitMapper();
		}
		return organisationseinheitMapper;
	}

	public Organisationseinheit insert(Organisationseinheit o) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idOrganisationseinheit) AS maxid " + "FROM organisationseinheit ");

			if (rs.next()) {

				o.setIdOrganisationseinheit(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(" INSERT INTO organisationseinheit (idOrganisationseinheit)" + " VALUES (" + o.getIdOrganisationseinheit());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public Organisationseinheit findByKey(int idOrganisationseinheit) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idOrganisationseinheit FROM organisationseinheit" + "WHERE idOrganisationseinheit=" + idOrganisationseinheit + "ORDER BY idOrganisationseinheit");
			// Organisationseinheit sollen nach id angezeigt werden

			if (rs.next()) {
				Organisationseinheit o = new Organisationseinheit();
				o.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));

				return o;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void delete(Organisationseinheit o) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM organisationseinheit " + "WHERE idOrganisationseinheit=" + o.getIdOrganisationseinheit());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}