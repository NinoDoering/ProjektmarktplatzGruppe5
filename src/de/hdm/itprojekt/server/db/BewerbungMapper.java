package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public class BewerbungMapper {
//neuerVersuch
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static BewerbungMapper bewerbungMapper = null;

	protected BewerbungMapper() {
	}

	public static BewerbungMapper bewerbungmapper() {
		if (bewerbungMapper == null) {
			bewerbungMapper = new BewerbungMapper();
		}

		return bewerbungMapper;
	}

	public Bewerbung findByKey(int idBewerbung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, bewerber, bewerbungsText, erstellDatum FROM bewerbung " + "WHERE idBewerbung=" + idBewerbung + " ORDER BY idBewerbung");
			if (rs.next()) {
				Bewerbung bw = new Bewerbung();
				bw.setIdBewerbung(rs.getInt("idBewerbung"));
				return bw;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Bewerbung> findAll() {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, bewerber, bewerbungsText, erstellDatum FROM bewerbung " + " ORDER BY idBewerbung");
			while (rs.next()) {
				Bewerbung bw = new Bewerbung();
				bw.setIdBewerbung(rs.getInt("idBewerbung"));
				result.addElement(bw);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Bewerbung> findByOwner(int idBewerbung) {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, bewerber, bewerbungsText, erstellDatum FROM bewerber " + "WHERE idBewerbung=" + idBewerbung + " ORDER BY idBewerbung");
			while (rs.next()) {
				Bewerbung bw = new Bewerbung();
				bw.setIdBewerbung(rs.getInt("idBewerbung"));
				result.addElement(bw);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Bewerbung insert(Bewerbung bw) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewerbung) AS maxid " + "FROM bewerbung ");
			if (rs.next()) {

				bw.setIdBewerbung(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO bewerbung (idBewerbung, bewerber, bewerbungsText, erstellDatum) " 
				+ "VALUES ('"
						+ bw.getIdBewerbung() + "','" 
						+ bw.getBewerber() + "','" 
						+ bw.getBewerbungsText() + "','"
						+ format.format(bw.getErstellDatum()) + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return bw;
	}

	public Bewerbung update(Bewerbung bw) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE bewerbung" 
					+ "SET bewerber=\"" + "'" + bw.getBewerber() +  "'" 
					+ bw.getBewerbungsText() + "\" ," + "bewerbungsText=\""
					+ bw.getErstellDatum() + "WHERE idBewerbung="+ "'" + bw.getIdBewerbung());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return bw;
	}

	public void delete(Bewerbung bw) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bewerbung" + "WHERE idBewerbung=" + bw.getIdBewerbung());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
}
