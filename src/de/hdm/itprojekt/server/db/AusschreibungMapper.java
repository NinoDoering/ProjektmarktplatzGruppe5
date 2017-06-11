package de.hdm.itprojekt.server.db;


import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.shared.bo.Ausschreibung.Status;
import de.hdm.itprojekt.shared.bo.Bewerbung.BewerbungsStatus;


public class AusschreibungMapper {
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	private static AusschreibungMapper ausschreibungMapper = null;

	protected AusschreibungMapper() {
	}

	public static AusschreibungMapper ausschreibungMapper() {
		if (ausschreibungMapper == null) {
			ausschreibungMapper = new AusschreibungMapper();
		}

		return ausschreibungMapper;
	}

	//Ausschreibung werden nach ID ausgegeben
	public Ausschreibung findAusschreibungByKey(int idAusschreibung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM ausschreibung " 
							+ " WHERE idAusschreibung= " + idAusschreibung 
							+ " ORDER BY bezeichnung");
			
			if (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));

				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	//Alle Ausschreibungen werden ausgegeben
	public Vector <Ausschreibung> findAllAusschreibungen () {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " 
											+ " ORDER BY idAusschreibung DESC");
			
			while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));
				
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	//Ausschreibung nach einem Projekt ausgegeben
	public Vector<Ausschreibung> findAusschreibungByProjekt(int idProjekt){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung "
			  		+ " WHERE idProjekt= " + idProjekt + " ORDER BY idAusschreibung DESC");
			  
			  while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));
				
				result.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
	  }
	
	
	//Eine Ausschreibung explizit anzeigen bzw. ausgeben
	public Vector<Ausschreibung> findByAusschreibung (Ausschreibung a) {
		Connection con = DBConnection.connection();
		Vector<Ausschreibung> result = new Vector<Ausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung " 
											+ " WHERE idAusschreibung = " + a.getId() 
											+ " ORDER BY idAusschreibung");
			
			while (rs.next()) {
				a.setId(rs.getInt("idAusschreibung"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setBezeichnung(rs.getString("bezeichnung"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));
				
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}
	
	//Ausschreibung über Partnerprofil ausgeben
	public Vector<Ausschreibung> findAusschreibungByPartnerprofil(int idPartnerprofil){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung "
			  		+ " WHERE idPartnerprofil= " + idPartnerprofil 
			  		+ " ORDER BY idAusschreibung");
			  
			  while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));
				
				result.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
	  }
	
	//Ausgabe der Ausschreibung vom Ersteller
	public Vector<Ausschreibung> findAusschreibungByAusschreibender(int idAusschreibender){
		
		  Connection con = DBConnection.connection();
		  Vector<Ausschreibung> result = new Vector<Ausschreibung>();
		  
		  try {
			
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery("SELECT * FROM ausschreibung "
			  		+ " WHERE idAusschreibender= " + idAusschreibender + " ORDER BY idAusschreibung DESC");
			  
			  while (rs.next()) {
				Ausschreibung a = new Ausschreibung();
				
				a.setId(rs.getInt("idAusschreibung"));
				a.setBezeichnung(rs.getString("Bezeichnung"));
				a.setIdAusschreibender(rs.getInt("idAusschreibender"));
				a.setBeschreibung(rs.getString("beschreibung"));
				a.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				a.setEndDatum(rs.getDate("endDatum"));
				a.setIdProjekt(rs.getInt("idProjekt"));
				a.setAusschreibungsstatus(Status.valueOf(rs.getString("status")));
				
				result.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return result;
	  }
	
	//insert
	public Ausschreibung insertAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idAusschreibung) AS maxid FROM ausschreibung ");
			
			if (rs.next()) {

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO ausschreibung (idAusschreibung, bezeichnung, endDatum, idProjekt, beschreibung, idPartnerprofil, idAusschreibender, status) " 
								+ "VALUES ('" + a.getId() + "','" 
								+ a.getBezeichnung() + "','" 
								+ format.format(a.getEndDatum())+ "','" 
								+ a.getIdProjekt()+ "','"
								+ a.getBeschreibung()+ "','"
								+ a.getIdPartnerprofil()+ "','"
								+ a.getIdAusschreibender()+ "','"
								+ a.getAusschreibungsstatus() + "')");
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	//update
	public Ausschreibung updateAusschreibung (Ausschreibung a) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE ausschreibung " 
					+ "SET idAusschreibung='" + a.getId() + "' ,'" 
					+ "bezeichnung='" + a.getBezeichnung() + "' ,'" 
					+ "beschreibung='" + a.getBeschreibung() + "' ,'" 
					+ "idAusschreibender='" + a.getIdAusschreibender() + "' ,'"
					+ "status='" + a.getAusschreibungsstatus() + "' ,'"
					+ "endDatum='" + format.format(a.getEndDatum()) + "' ,'" 
					+ "idProjekt='" + a.getIdProjekt() + "' ,'" 
					+ " WHERE idAusschreibung= '" + a.getId());
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
			return a;
		
	}

	//delete
	public void deleteAusschreibung(Ausschreibung a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM ausschreibung " 
								+ " WHERE idAusschreibung= " + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

}
