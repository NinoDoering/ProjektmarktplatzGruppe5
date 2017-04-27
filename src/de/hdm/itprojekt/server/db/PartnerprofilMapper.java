package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

public class PartnerprofilMapper {

	private static PartnerprofilMapper partnerprofilMapper = null;
	
	protected PartnerprofilMapper() {
	  }
	
	public static PartnerprofilMapper partnerprofilMapper() {
	    if (partnerprofilMapper == null) {
	      partnerprofilMapper = new PartnerprofilMapper();
	    }

	    return partnerprofilMapper;
	  }
	
	public Partnerprofil findbyKey(int id){
		Connection con = DBConnection.connection();
	
	
	 try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt
	          .executeQuery("SELECT id FROM partnerprofil "
	              + "WHERE id=" + id); // " ORDER BY nachname) --> kommt nicht rein;

	      if (rs.next()) {    
	          Partnerprofil p = new Partnerprofil();
	          p.setId(rs.getInt("id"));

	          return p;
	        }
	      }
	      catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	      }

	      return null;
	    }
	
	 public Vector<Partnerprofil> findAll() {
		    Connection con = DBConnection.connection();

		    Vector<Partnerprofil> result = new Vector<Partnerprofil>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id "
		          + "FROM partnerprofil ");

		      while (rs.next()) {
		        Partnerprofil p = new Partnerprofil();
		        p.setId(rs.getInt("id"));

		        //Hier ist letzter Speicherpunkt
		        result.addElement(p);
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	
}

