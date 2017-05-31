package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.*;	
import java.util.*; //Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public class OrganisationseinheitMapper {

	private static OrganisationseinheitMapper organisationseinheitMapper = null;

	protected OrganisationseinheitMapper() {
	}

	public static OrganisationseinheitMapper organisationseinheitMapper() {
		if (organisationseinheitMapper == null) {
			organisationseinheitMapper = new OrganisationseinheitMapper();
		}
		return organisationseinheitMapper;
	}

	public int insertOrganisationseinheit (Organisationseinheit o) {

		Connection con = DBConnection.connection();
		int id=0;
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idOrganisationseinheit) AS maxid " + "FROM organisationseinheit ");

			if (rs.next()) {

				o.setId(rs.getInt("maxid") + 1);
				id=o.getId();
				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO organisationseinheit (idOrganisationseinheit, adresse, standort, idPartnerprofil)" 
								+ " VALUES ('" 
								+ o.getId()+ "','" + o.getAdresse()+ "','" + o.getStandort()+ "','" + o.getIdPartnerprofil()+ "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public Organisationseinheit findOrganisationseinheitByKey (int id) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idOrganisationseinheit, adresse, standort, idPartnerprofil FROM organisationseinheit " 
											+ " WHERE idOrganisationseinheit= " + id);
			
			// Organisationseinheit sollen nach id angezeigt werden

			if (rs.next()) {
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("idOrganisationseinheit"));
				o.setAdresse(rs.getString("adresse"));
				o.setStandort(rs.getString("standort"));
				o.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
				
				return o;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Organisationseinheit findByPartnerprofil(int idPartnerprofil){
	Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idOrganisationseinheit, adresse, standort, idPartnerprofil"
					+ " FROM organisationseinheit " + "WHERE idPartnerprofil= " + idPartnerprofil);
			
			
			if(rs.next()){
				Organisationseinheit o = new Organisationseinheit();
				o.setId(rs.getInt("idOrganisationseinheit"));
				o.setAdresse(rs.getString("adresse"));
				o.setStandort(rs.getString("standort"));
				o.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
		return o;
			
			}
	}
		catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
		return null;
	}
	
	public Organisationseinheit findByOrganisationseinheit(Organisationseinheit o){
		return this.findOrganisationseinheitByKey(o.getId());
		}
	
	public void deleteOrganisationseinheit (Organisationseinheit o) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM organisationseinheit " 
							+ " WHERE idOrganisationseinheit= " + o.getId());
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
	
		public int updateOrganisationseinheit (Organisationseinheit o){
			Connection con = DBConnection.connection();
			int id = 0;
			try {
				Statement stmt = con.createStatement();
				
				stmt.executeUpdate("UPDATE organisationseinheit "
						+ "SET adresse='" + o.getAdresse() + "'," 
						+ "standort='" + o.getStandort() + "'," 
						+ "idPartnerprofil=" + o.getIdPartnerprofil()  
						+ "' WHERE Organisationseinheit_Id="+o.getId());
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return id;
		
	}

}