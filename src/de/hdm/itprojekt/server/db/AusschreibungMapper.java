package de.hdm.itprojekt.server.db;


import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class AusschreibungMapper {
	//neuerVersuch........
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static AusschreibungMapper ausschreibungmapper = null;

	protected AusschreibungMapper() {
	}

	public static AusschreibungMapper ausschreibungmapper() {
		if (ausschreibungmapper == null) {
			ausschreibungmapper = new AusschreibungMapper();
		}

		return ausschreibungmapper;
	}

	public Ausschreibung findByKey(int idAusschreibung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, owner FROM ausschreibung " + "WHERE id=" + idAusschreibung + " ORDER BY ausschreibung");
			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setIdAusschreibung(rs.getInt("idAusschreibung"));
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
			ResultSet rs = stmt.executeQuery("SELECT idAusschreibung, owner FROM ausschreibung " + " ORDER BY idAusschreibung");
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setIdAusschreibung(rs.getInt("idAusschreibung"));
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Ausschreibung> findByOwner(int idAusschreibung) {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idAusschreibung, owner FROM ausschreibung " + "WHERE owner=" + idAusschreibung + " ORDER BY idAusschreibung");
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setIdAusschreibung(rs.getInt("idAusschreibung"));
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

				a.setIdAusschreibung(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO ausschreibung (id, bezeichnung, endDatum, beschreibung) " 
				+ "VALUES (" + a.getIdAusschreibung() + "','" 
								+ a.getBezeichnung() + "','" 
				+ format.format(a.getEndDatum())+ "','" 
								+ a.getBeschreibung() + "')");
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
					+ a.getIdAusschreibung());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}

	public void delete(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM ausschreibung " + "WHERE id=" + a.getIdAusschreibung());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

}
