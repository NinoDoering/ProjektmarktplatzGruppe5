package de.hdm.itprojekt.server.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;


public class BeteiligungMapper {
	//neuerVersuch
	private static BeteiligungMapper beteiligungMapper = null;

	protected BeteiligungMapper() {
	}

	public static BeteiligungMapper beteiligungMapper() {
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
					.executeQuery("SELECT idBeteiligung,  FROM beteiligung " + "WHERE idBeteiligung=" + idBeteiligung + " ORDER BY idBeteilgung");
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
			ResultSet rs = stmt.executeQuery("SELECT idBeteiligung, idBewerbung, idProjekt, idBewertung FROM beteiligung " + " ORDER BY idBeteiligung");
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
					.executeQuery("SELECT idBeteiligung, idBeteiligung, idBewerbung, idProjekt, idBewertung FROM beteiligung " + "WHERE idBeteiligung=" + idBeteiligung + " ORDER BY idBeteiligung");
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

	public Beteiligung insert(Beteiligung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBeteiligung) AS maxid " + "FROM beteiligung ");
			if (rs.next()) {

				b.setIdBeteiligung(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO beteiligung (idBeteiligung, idBewerbung, idProjekt, idBewertung) " 
				+ "VALUES ('" 
						+ b.getIdBeteiligung() + "','"
						+ b.getIdBewertung() + "','"
						+ b.getIdProjekt() + "','" 
						+ b.getIdBewerbung() + "')");
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
			stmt.executeUpdate("UPDATE beteiligung" 
			+ "SET idBeteiligung=\"" + b.getIdBeteiligung()  + "\" ,"
			+ "idProjekt=\"" + b.getIdProjekt()  + "\" ,"
			+ "idBewerbung=\"" + b.getIdBewerbung()  + "\" ,"
			+ "WHERE idBeteiligung=" + b.getIdBeteiligung());
			
			
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
