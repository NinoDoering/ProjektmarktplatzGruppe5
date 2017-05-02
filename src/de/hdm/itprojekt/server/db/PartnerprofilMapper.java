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
	
	//findByKey
	public Partnerprofil findByKey(int id){
		Connection con = DBConnection.connection();
	
	
	 try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt
	          .executeQuery("SELECT id FROM partnerprofil "
	              + "WHERE id=" + id);

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

		        result.addElement(p);
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	 
	//findByAusschreibung
	 public Vector<Partnerprofil> findByAusschreibung(int idAusschreibung) {
		 //prüfen wegen Fremdschlüssel von Ausschreibung: gegebenenfalls korrigieren!!
		    Connection con = DBConnection.connection();
		    Vector<Partnerprofil> result = new Vector<Partnerprofil>();

		    try {
		      Statement stmt = con.createStatement();
		      
		      //FREMDSCHLÜSSEL ÜBERPRÜFUEN!!
		      ResultSet rs = stmt.executeQuery("SELECT id FROM partnerprofil "
		          + "WHERE ausschreibung=" + idAusschreibung + "INNER JOIN ausschreibung ON "
		          		+ "partnerprofil.idAusschreibung = ausschreibung.idAusschreibung ORDER BY id" );//?? 

		      while (rs.next()) {
		        Partnerprofil p = new Partnerprofil();
		        p.setId(rs.getInt("id"));
		        p.setIdAusschreibung(rs.getInt("ausschreibung"));

		        result.addElement(p);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
		  }
	
	 //INSERT INTO 
	 public Partnerprofil insert(Partnerprofil p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM partnerprofil ");

		      if (rs.next()) {

		        p.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        stmt.executeUpdate("INSERT INTO partnerprofil (id) "
		            + "VALUES (" + p.getId());
		      }
		    }
		    catch (SQLException e3) {
		      e3.printStackTrace();
		    }

		    return p;
		  }
	 
	 //UPDATE
	 public Partnerprofil update(Partnerprofil p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      //WARUM SIND HIER SLASHES???
		      stmt.executeUpdate("UPDATE partnerprofil " + "SET id=\""
		          + p.getId() + "\", " + "lastName=\"" + p.getLastName() + "\" "
		          + "WHERE id=" + p.getId());

		    }
		    catch (SQLException e4) {
		      e4.printStackTrace();
		    }

		    return p;
		  }
	 
	 //DELETE
	 public void delete(Partnerprofil p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM partnerprofil " + "WHERE id=" + p.getId());
		    }
		    catch (SQLException e5) {
		      e5.printStackTrace();
		    }
		  }
	 
	 
	 //FAN-IN-FAN-OUT-ANALYSE?
	  public Vector<Partnerprofil> getPartnerprofilOf(Partnerprofil p) {

		 
		  return PartnerprofilMapper.partnerprofilMapper().findbyKey(id);
	    //return AccountMapper.accountMapper().findByOwner(c); richtig übersetzt?
	  }
	}