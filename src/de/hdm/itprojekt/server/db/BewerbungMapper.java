package de.hdm.itprojekt.server.db;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;


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
	
	//Objet Bewerbung ausgabe
	 public Bewerbung findByBewerbung(Bewerbung b) {

	    	return this.findBewerbungByKey(b.getId());
	    }

	public Bewerbung findBewerbungByKey (int idBewerbung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			// SQL STATEMENT
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung " 
											+ " WHERE idBewerbung= " + idBewerbung 
											+ " ORDER BY idBewerbung");
			
			if (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsstatus")));
				
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	//Alle Bewerbungen ausgeben
	public Vector<Bewerbung> findAllBewerbungen () {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung " 
											+ " ORDER BY idBewerbung");
			
			while (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsStatus")));
				
				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//Bewerbung nach Bewerber ausgeben
	public Vector<Bewerbung> findBewerbungByBewerber (int idBewerbung) {
		Connection con = DBConnection.connection();
		Vector<Bewerbung> result = new Vector<Bewerbung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM bewerbung " 
											+ " WHERE idBewerbung= " + idBewerbung 
											+ " ORDER BY idBewerbung");
			
			while (rs.next()) {
				Bewerbung b = new Bewerbung();
				b.setId(rs.getInt("idBewerbung"));
				b.setBewerbungsText(rs.getString("bewerbungsText"));
				b.setErstellDatum(rs.getDate("erstellDatum"));
				b.setIdAusschreibung(rs.getInt("idAusschreibung"));
				b.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));
				b.setBewerbungsStatus(BewerbungsStatus.valueOf(rs.getString("bewerbungsstatus")));
				
				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	//insert
	public Bewerbung insertBewerbung (Bewerbung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewerbung) AS maxid " + " FROM bewerbung ");
			if (rs.next()) {

				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO bewerbung (idBewerbung, bewerbungsText, erstellDatum, idOrganisationseinheit, idAusschreibung, bewerbungsstatus) " 
									+ "VALUES ('"
									+ b.getId() + "','" 
									+ b.getIdAusschreibung() + "','" 
									+ b.getBewerbungsText() + "','"
									+ b.getIdOrganisationseinheit() + "','" 
									+ b.getBewerbungsStatus() + "','" 
									+ format.format(b.getErstellDatum()) + "')");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	//update
	public Bewerbung updateBewerbung (Bewerbung b) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE bewerbung " 
					+ "SET idBewerbung='" + b.getId() + "' ,'" 
					+ "idOrganisationseinheit='" + b.getIdOrganisationseinheit() + "' ,'" 
					+ "idAusschreibung='" + b.getIdAusschreibung() + "' ,'" 
					+ "bewerbungsText='" + b.getBewerbungsText() + "' ,'" 
					+ "erstellDatum='" + b.getErstellDatum() + "' ,'" 
					+ "bewerbungsstatus='" + b.getBewerbungsStatus() + "' ,'" 
					+ " WHERE idBewerbung= '"+ b.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	//delete
	public void deleteBewerbung (Bewerbung b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE * FROM bewerbung" 
								+ " WHERE idBewerbung= " + b.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
