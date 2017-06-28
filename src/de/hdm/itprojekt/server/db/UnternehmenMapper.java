package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

//Alle Mappermethoden in dieser Klasse funktionieren!

public class UnternehmenMapper extends OrganisationseinheitMapper {

	private static UnternehmenMapper unternehmenMapper = null;

	protected UnternehmenMapper() {
	}

	public static UnternehmenMapper unternehmenMapper() {
		if (unternehmenMapper == null) {
			unternehmenMapper = new UnternehmenMapper();
		}

		return unternehmenMapper;
	}

	//alle Unternehem nach id ausgeben
	public Unternehmen findUnternehmenByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idUnternehmen, firmenName FROM unternehmen " 
			+ " WHERE idUnternehmen= " + id);

			if (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("idUnternehmen"));
				u.setFirmenName(rs.getString("firmenName"));
				u.setAdresse(super.findOrganisationseinheitByKey(id).getAdresse());
				u.setStandort(super.findOrganisationseinheitByKey(id).getStandort());
				u.setIdPartnerprofil(super.findOrganisationseinheitByKey(id).getIdPartnerprofil());
				
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
	
	// FindByFirmenName 
	public Vector <Unternehmen> findUnternehmenByFirmenName(String firmenName) {
		Connection con = DBConnection.connection();
		Vector<Unternehmen> result = new Vector<Unternehmen>();


		//WICHTIG: In TestMapper muss abfrage folgenderma�en aussehen:
		//System.out.println(UnternehmenMapper.unternehmenMapper().findByFirmenName("'Name'"));
		//Sehr wichtig ist auf die Anf�hrungszeichen zu achten!
		
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idUnternehmen, firmenName FROM unternehmen " + " WHERE firmenName= '" + firmenName + "' ORDER BY idUnternehmen");

			while (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setFirmenName(rs.getString("firmenName"));
				u.setId(rs.getInt("idUnternehmen"));
				u.setAdresse(super.findByOrganisationseinheit(u).getAdresse());
				u.setStandort(super.findByOrganisationseinheit(u).getStandort());
				u.setIdPartnerprofil(super.findByOrganisationseinheit(u).getIdPartnerprofil());
				
				result.addElement(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}
	
	//Unternehmen ausgeben
	public Unternehmen findByUnternehmen(Unternehmen u){
		return this.findUnternehmenByKey(u.getId());}

	//Alle Unternehmen ausgeben
	public Vector<Unternehmen> findAllUnternehmen() {
		Connection con = DBConnection.connection();
		Vector<Unternehmen> result = new Vector<Unternehmen>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM unternehmen " 
			+ " ORDER BY firmenName");

			while (rs.next()) {
				Unternehmen u = new Unternehmen();
				u.setId(rs.getInt("idUnternehmen"));
				u.setFirmenName(rs.getString("firmenName"));
				u.setAdresse(super.findByOrganisationseinheit(u).getAdresse());
				u.setStandort(super.findByOrganisationseinheit(u).getStandort());
				u.setIdPartnerprofil(super.findByOrganisationseinheit(u).getIdPartnerprofil());

				result.addElement(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	// INSERT 
	public Unternehmen insertUnternehmen(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			u.setId(super.insertOrganisationseinheit(u));
			ResultSet rs = stmt.executeQuery("SELECT MAX(idUnternehmen) AS maxid " + "FROM unternehmen ");
			// Id wird wom�glich ben�tigt!

			if (rs.next()) {
				
//				u.setId(rs.getInt("maxid") + 1);
				

				stmt = con.createStatement();	

				stmt.executeUpdate("INSERT INTO unternehmen (idUnternehmen, firmenName) " 
				+ "VALUES (" + u.getId() + ",'" + u.getFirmenName() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	//update
	public Unternehmen updateUnternehmen(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE unternehmen " 
			+ "SET firmenName=\"" + u.getFirmenName()  
			+ "\" " + " WHERE idUnternehmen= " + u.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return u;
	}

	// DELETE
	public void deleteUnternehmen(Unternehmen u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			// Schon wieder wird id verwendet --> sollten Id als Attribut
			// hinzuf�gen
			stmt.executeUpdate("DELETE * FROM unternehmen " + " WHERE idUnternehmen= " + u.getId());
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}
}