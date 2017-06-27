package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;
import java.*;		//Pakete, welche zum Ausf�hren ben�tigt werden.
import javax.*;
import java.sql.*;

import de.hdm.itprojekt.shared.bo.*;

public class EigenschaftMapper {

	private static EigenschaftMapper eigenschaftMapper = null;

	protected EigenschaftMapper() {
	
	}
	
	public static EigenschaftMapper eigenschaftMapper() {
		if (eigenschaftMapper == null) {
			eigenschaftMapper = new EigenschaftMapper();
		}
		return eigenschaftMapper;
	}

	//Eigenschaft nach ID ausgeben
	public Eigenschaft findByEigenschaft(Eigenschaft e) {
	      
    	return this.findEigenschaftByKey(e.getId());
 
    }
	
	//insert
	public Eigenschaft insertEigenschaft (Eigenschaft e) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idEigenschaft) AS maxid " + " FROM eigenschaft ");

			if (rs.next()) {

				e.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO eigenschaft (idEigenschaft, ausbildung, abschluss, berufserfahrungsJahre, arbeitsgebiet, sprachkenntnisse, idPartnerprofil, employmentStatus)"
									+ " VALUES ('" + e.getId() + "','" 
									+ e.getAusbildung() + "','" 
									+ e.getAbschluss()+ "','" 
									+ e.getBerufserfahrungsJahre() + "','" 
									+ e.getArbeitsgebiet() + "','"
									+ e.getSprachkenntnisse() + "','" 
									+ e.getIdPartnerprofil() + "','" 
									+ e.getEmploymentStatus() + "')");

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}

	//Eigenschaften nach ID ausgeben
	public Eigenschaft findEigenschaftByKey (int idEigenschaft) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft "
											+ " WHERE idEigenschaft= " + idEigenschaft
											+ " ORDER BY idEigenschaft");
			
			// Eigenschaft sollen nach id angezeigt werden
			if (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setAusbildung(rs.getString("ausbildung"));
				e.setAbschluss(rs.getString("abschluss"));
				e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
				e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				e.setEmploymentStatus(rs.getString("employmentStatus"));
				e.setIdPartnerprofil(rs.getInt("IdPartnerprofil"));

				return e;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	//alle Eigenschaften ausgeben
	
	public Vector<Eigenschaft> findAllEigenschaften () {
		Connection con = DBConnection.connection();
		Vector<Eigenschaft> vector = new Vector<Eigenschaft>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM eigenschaft " 
							+ " ORDER BY idEigenschaft ");

			while (rs.next()) {
				Eigenschaft e = new Eigenschaft();
				e.setId(rs.getInt("idEigenschaft"));
				e.setAusbildung(rs.getString("ausbildung"));
				e.setAbschluss(rs.getString("abschluss"));
				e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
				e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
				e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
				e.setEmploymentStatus(rs.getString("employmentStatus"));
				e.setIdPartnerprofil(rs.getInt("idPartnerprofil"));

				vector.addElement(e);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return vector;
	}
	
	//Eigenschaft die von einem Partnerprofil erstellt wurden ausgeben
	public Vector<Eigenschaft> findEigenschaftByPartnerprofil (int idPartnerprofil){
    	
    	Vector<Eigenschaft> vector = new Vector<Eigenschaft>();
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft "
	          + " WHERE idPartnerprofil= " + idPartnerprofil);

	     
	      while (rs.next()) {
	    	  Eigenschaft e = new Eigenschaft();
	    	  e.setId(rs.getInt("idEigenschaft"));
	    	  e.setAusbildung(rs.getString("ausbildung"));
	    	  e.setAbschluss(rs.getString("abschluss"));
	    	  e.setBerufserfahrungsJahre(rs.getString("berufserfahrungsJahre"));
	    	  e.setArbeitsgebiet(rs.getString("arbeitsgebiet"));
	    	  e.setSprachkenntnisse(rs.getString("sprachkenntnisse"));
	    	  e.setEmploymentStatus(rs.getString("employmentStatus"));
	    	  e.setIdPartnerprofil(rs.getInt("idPartnerprofil"));
		      
	    	  vector.add(e);
	      }
	   
	    }
	    catch (SQLException ex) {
	      ex.printStackTrace();
	    }  
	    return vector;
    }

	//update
	public Eigenschaft updateEigenschaft (Eigenschaft e) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

					stmt.executeUpdate("UPDATE eigenschaft " 
					+ "SET idEigenschaft='" + e.getId() + "' ,'" 
					+ "ausbildung='" + e.getAusbildung() + "' ,'" 
					+ "abschluss='" + e.getAbschluss() + "' ,'" 
					+ "berufserfahrungsJahre='" + e.getBerufserfahrungsJahre() + "' ,'" 
					+ "arbeitsgebiet='" + e.getArbeitsgebiet() + "' ,'" 
					+ "sprachkenntnisse='" + e.getSprachkenntnisse() + "' ,'" 		
					+ "employmentStatus='" + e.getEmploymentStatus() + "' ,'" 
					+ "idPartnerprofil='" + e.getIdPartnerprofil() + "' ,'" 
					+ " WHERE idEigenschaft= '"+ e.getId());

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return e;
	}

	//delete
	public void deleteEigenschaft (Eigenschaft e) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE * FROM eigenschaft " 
								+ " WHERE idEigenschaft= " + e.getId());
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			}
		}
	}
		
	