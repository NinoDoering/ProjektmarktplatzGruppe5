package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Organisationseinheit;
import de.hdm.itprojekt.shared.bo.Bewerbung;

public class BewerbungMapper {
//neuerVersuch
	
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
			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, owner FROM bewerbung " + "WHERE idBewerbung=" + idBewerbung + " ORDER BY owner");
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
			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, owner FROM bewerbung " + " ORDER BY idBewerbung");
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

			ResultSet rs = stmt.executeQuery("SELECT idBewerbung, owner FROM bewerber " + "WHERE owner=" + idBewerbung + " ORDER BY idBewerbung");
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
/*
	public Vector<Ausschreibung> findByOwner(Organisationseinheit owner) {

		return findByOwner(owner.getId());
	}
	*/

	public Bewerbung insert(Bewerbung bw) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM bewerbung ");
			if (rs.next()) {

				bw.setIdBewerbung(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO accounts (idBewerbung, bewerber, bewerbungsText, erstelluDatum) " + "VALUES ("
						+ "'" + bw.getIdBewerbung() + "," 
						+ "," + bw.getBewerber() + "," 
						+ "'" + bw.getBewerbungsText() + ","
						+ "'" + bw.getErstellDatum() + ")");
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
			stmt.executeUpdate("UPDATE beteiligung" + "SET bewerber=\"" 
					+ "'" + bw.getBewerber() +  "'" +"\" ," + "bewerbungsText=\""
					+ bw.getBewerbungsText() + "\" ," + "erstellDatum=\"" 
					+ bw.getErstellDatum() + "WHERE id="
					+ "'" + bw.getIdBewerbung());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return bw;
	}

	public void delete(Bewerbung bw) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bewerber" + "WHERE idBewerbung=" + bw.getIdBewerbung());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
}
