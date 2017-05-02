package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.BusinessObject;


public class BusinessObjectMapper {
	
	private static BusinessObjectMapper businessObjectMapper = null;
	
	protected BusinessObjectMapper(){}; 
	
	public static BusinessObjectMapper businessObjectMapper(){
		if (businessObjectMapper == null){ 
			businessObjectMapper = new BusinessObjectMapper();
		}
		return businessObjectMapper;
	}
	
	
	public BusinessObject insert(BusinessObject bo){
		
		Connection con = DBConnection.connection(); 
	
		try{ 
			Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	          + "FROM businessObject");
	 
	   if (rs.next()) { 
		   
		  
	        bo.setId(rs.getInt("maxid") + 1);

	        stmt = con.createStatement();
	   
	
	        stmt.executeUpdate(" INSERT INTO projekt (id)"
	        		+ " VALUES (" + bo.getIdBusinessObject() + ")"); 
	
	   }
	   }
 catch (SQLException e) {
	 e.printStackTrace();
 }
		return bo;
}
	public BusinessObject findByKey (int idBusinessObject) {
		
		Connection con = DBConnection.connection();
		
		
	try	{
		Statement stmt = con.createStatement(); 
		
		ResultSet rs = stmt.executeQuery("SELECT idBusinessObject"
				+ "WHERE idBusinessObject=" + idBusinessObject + "ORDER BY idBusinessObject");
		
		
		if (rs.next()) {
			BusinessObject bo = new BusinessObject();
			bo.setIdBusinessObject(rs.getInt("idBusinessObject"));
			/// Fehler beheben 
			
			return bo; 
		}
	}
	catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	return null;
	}
	
	public void delete(BusinessObject bo) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM businessObject " + "WHERE idBusinessObject=" + bo.getIdBusinessObject());
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
}