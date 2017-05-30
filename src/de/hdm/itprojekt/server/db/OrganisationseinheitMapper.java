package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.*;		//Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;
import de.hdm.itprojekt.shared.bo.*;
import de.hdm.itprojekt.server.db.*;

public class OrganisationseinheitMapper {

	private static OrganisationseinheitMapper organisationseinheitMapper = null;

	protected OrganisationseinheitMapper() {
	};

	public static OrganisationseinheitMapper organisationseinheitMapper() {
		if (organisationseinheitMapper == null) {
			organisationseinheitMapper = new OrganisationseinheitMapper();
		}
		return organisationseinheitMapper;
	}

	public Organisationseinheit insertOrganisationseinheit (Organisationseinheit o) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idOrganisationseinheit) AS maxid " + "FROM organisationseinheit ");

			if (rs.next()) {

				o.setIdOrganisationseinheit(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO organisationseinheit (idOrganisationseinheit)" 
								+ " VALUES ('" 
								+ o.getIdOrganisationseinheit()+ "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public Organisationseinheit findOrganisationseinheitById (int idOrganisationseinheit) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idOrganisationseinheit FROM organisationseinheit " 
											+ " WHERE idOrganisationseinheit= " + idOrganisationseinheit 
											+ " ORDER BY idOrganisationseinheit ");
			
			// Organisationseinheit sollen nach id angezeigt werden

			if (rs.next()) {
				Organisationseinheit o = new Organisationseinheit();
				o.setIdOrganisationseinheit(rs.getInt("idOrganisationseinheit"));

				return o;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void deleteOrganisationseinheit (Organisationseinheit o) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM organisationseinheit " 
							+ " WHERE idOrganisationseinheit= " + o.getIdOrganisationseinheit());
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}
	
		public Organisationseinheit updateOrganisationseinheit (Organisationseinheit org){
			Connection con = DBConnection.connection();
			try {
				Statement stmt = con.createStatement();
				/// owner ?
				stmt.executeUpdate("UPDATE organisationseinheit " 
						+ "SET idOrganisationseinheit='" + org.getIdOrganisationseinheit() + "' ,'" 
						+ "WHERE idOrganisationseinheit ='"+ org.getIdOrganisationseinheit());
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return org;
		
	}

}