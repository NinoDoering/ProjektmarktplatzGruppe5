package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

//Alle Mappermethoden in dieser Klasse funktionieren!

public class UnternehmenMapper {

	private static UnternehmenMapper unternehmenMapper = null;

	protected UnternehmenMapper() {
	}

	public static UnternehmenMapper unternehmenMapper() {
		if (unternehmenMapper == null) {
			unternehmenMapper = new UnternehmenMapper();
		}

		return unternehmenMapper;
	}

	public Unternehmen findByKey(int idUnternehmen) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idUnternehmen, firmenName FROM unternehmen " 
			+ "WHERE idUnternehmen= " + idUnternehmen);

			if (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setIdUnternehmen(rs.getInt("idUnternehmen"));
				u.setFirmenName(rs.getString("firmenName"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
	
	
	// FindByFirmenName -- Was ist mit Id??
	public Unternehmen findByFirmenName(String firmenName) {
		Connection con = DBConnection.connection();

		//WICHTIG: In TestMapper muss abfrage folgendermaßen aussehen:
		//System.out.println(UnternehmenMapper.unternehmenMapper().findByFirmenName("'StukkateurKosova'"));
		//Sehr wichtig ist auf die Anführungszeichen zu achten!
		
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idUnternehmen, firmenName" + " FROM unternehmen " + "WHERE firmenName=" + firmenName);

			if (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setFirmenName(rs.getString("firmenName"));
				u.setIdUnternehmen(rs.getInt("idUnternehmen"));

				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	// INSERT 
	public Unternehmen insert(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idUnternehmen) AS maxid " + "FROM unternehmen ");
			// Id wird womöglich benötigt!

			if (rs.next()) {

				u.setIdUnternehmen(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO unternehmen (idUnternehmen, firmenName) " + "VALUES ('" + u.getIdUnternehmen() + "','" + u.getFirmenName() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	public Unternehmen update(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE unternehmen " 
			+ "SET firmenName=\"" + u.getFirmenName()  
			+ "\" " +" WHERE idUnternehmen= " + u.getIdUnternehmen());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return u;
	}

	// DELETE
	public void delete(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Schon wieder wird id verwendet --> sollten Id als Attribut
			// hinzufügen
			stmt.executeUpdate("DELETE FROM unternehmen " + "WHERE idUnternehmen=" + u.getIdUnternehmen());
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}