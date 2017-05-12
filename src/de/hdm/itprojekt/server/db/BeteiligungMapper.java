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

	public Beteiligung findByKey(int idBeteiligung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt
					.executeQuery("SELECT idBeteiligung, owner FROM beteiligung " + "WHERE id=" + idBeteiligung + " ORDER BY owner");
			if (rs.next()) {
				Beteiligung b = new Beteiligung();
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				return b;
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
			ResultSet rs = stmt.executeQuery("SELECT idBeteiligung, owner FROM beteiligung " + " ORDER BY idBeteiligung");
			while (rs.next()) {
				Beteiligung b = new Beteiligung();
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				result.addElement(b);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public Vector<Beteiligung> findByOwner(int idBeteiligung) {
		Connection con = DBConnection.connection();
		Vector<Beteiligung> result = new Vector<Beteiligung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idBeteiligung, owner FROM beteiligung " + "WHERE owner=" + idBeteiligung + " ORDER BY idBeteiligung");
			while (rs.next()) {
				Beteiligung b = new Beteiligung();
				b.setIdBeteiligung(rs.getInt("idBeteiligung"));
				result.addElement(b);
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
	public Beteiligung insert(Beteiligung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBeteiligung) AS maxid " + "FROM ausschreibung ");
			if (rs.next()) {

				b.setIdBeteiligung(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO accounts (idBeteiligung, idProjekt, idPerson) " + "VALUES (" + b.getIdBeteiligung() + ","
						+ b.getIdProjekt() + "," + b.getIdPerson() + "," + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return b;
	}

	public Beteiligung update(Beteiligung b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			/// owner ?
			stmt.executeUpdate("UPDATE beteiligung" + "SET idProjekt=\"" + b.getIdProjekt() + "\" ," + "idPerson=\""
					+ b.getIdPerson() + "WHERE idBeteiligung=" + b.getIdBeteiligung());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return b;
	}

	public void delete(Beteiligung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beteiligung " + "WHERE idBeteiligung=" + b.getIdBeteiligung());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
