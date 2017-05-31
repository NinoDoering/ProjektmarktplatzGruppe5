package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class PartnerprofilMapper {
//Alle Mappermethoden in dieser Klasse funktionieren
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
	public Partnerprofil findPartnerprofilByKey(int idPartnerprofil) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idPartnerprofil FROM partnerprofil " 
			+ "WHERE idPartnerprofil= " + idPartnerprofil);

			if (rs.next()) {
				Partnerprofil pp = new Partnerprofil();
				pp.setId(rs.getInt("idPartnerprofil"));

				return pp;
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
				Partnerprofil pp = new Partnerprofil();
				pp.setId(rs.getInt("idPartnerprofil"));

				result.addElement(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Partnerprofil findByPartnerprofil(Partnerprofil pp){
		 return this.findPartnerprofilByKey(pp.getId());
		 
	  }


	// INSERT INTO
	public Partnerprofil insert(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPartnerprofil) AS maxid " + "FROM partnerprofil ");

			if (rs.next()) {

				pp.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO partnerprofil (idPartnerprofil) " 
				+ "VALUES (" + pp.getId() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pp;
	}

	// UPDATE unn�tig
	public Partnerprofil update(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE partnerprofil " 
			+ "SET idPartnerprofil=\"" + pp.getId() + "\" "
					+ "WHERE idPartnerprofil=" + pp.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pp;
	}

	// DELETE
	public void delete(Partnerprofil pp) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM partnerprofil " 
			+ "WHERE idPartnerprofil=" + pp.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}