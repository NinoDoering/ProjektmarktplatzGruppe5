package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import java.*; //Pakete, welche zum Ausf?hren ben?tigt werden.
import javax.*;
import java.sql.*;

public class PersonMapper extends OrganisationseinheitMapper{
//Alle Mappermethoden in dieser Klasse funktionieren
	private static PersonMapper personMapper = null;

	protected PersonMapper() {
	}

	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}

		return personMapper;
	}

	// FindByKey
	public Person findPersonByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery ("SELECT idPerson, titel, vorname, nachname, idTeam, idUnternehmen FROM person " 
							+ " WHERE idPerson= " + id);
			
			if (rs.next()) {
				Person pe = new Person();
				pe.setId(rs.getInt("idPerson"));
				pe.setVorname(rs.getString("vorname"));
				pe.setNachname(rs.getString("nachname"));
				pe.setTitel(rs.getString("titel"));
				pe.setIdUnternehmen(rs.getInt("idUnternehmen"));
				pe.setIdTeam(rs.getInt("idTeam"));
				pe.setAdresse(super.findOrganisationseinheitByKey(id).getAdresse());
				pe.setStandort(super.findOrganisationseinheitByKey(id).getStandort());
				pe.setIdPartnerprofil(super.findOrganisationseinheitByKey(id).getIdPartnerprofil());
				
				return pe;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	// FindAll
	public Vector<Person> findAllPerson() {
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idPerson, titel, vorname, nachname, idTeam, idUnternehmen " 
			+ "FROM person " + "ORDER BY idPerson DESC");

			while (rs.next()) {
				Person pe = new Person();
				pe.setId(rs.getInt("idPerson"));
				pe.setVorname(rs.getString("vorname"));
				pe.setNachname(rs.getString("nachname"));
				pe.setTitel(rs.getString("titel"));
				pe.setIdUnternehmen(rs.getInt("idUnternehmen"));
				pe.setIdTeam(rs.getInt("idTeam"));
				pe.setAdresse(super.findByOrganisationseinheit(pe).getAdresse());
				pe.setStandort(super.findByOrganisationseinheit(pe).getStandort());
				pe.setIdPartnerprofil(super.findByOrganisationseinheit(pe).getIdPartnerprofil());

				result.addElement(pe);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	//Team durch Personen ausgeben
public Vector<Person> findPersonByTeam(int idTeam){
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM person " 
			+ "WHERE idTeam= " + idTeam 
			+ " ORDER BY idPerson ASC");
			
			
			while (rs.next()){
				Person pe = new Person();
				pe.setId(rs.getInt("idPerson"));
				pe.setTitel(rs.getString("titel"));
				pe.setVorname(rs.getString("vorname"));
				pe.setNachname(rs.getString("nachname"));
				pe.setIdUnternehmen(rs.getInt("idUnternehmen"));
				pe.setIdTeam(rs.getInt("idTeam"));
				pe.setAdresse(super.findOrganisationseinheitByKey(idTeam).getAdresse());
				pe.setStandort(super.findOrganisationseinheitByKey(idTeam).getStandort());
				pe.setIdPartnerprofil(super.findOrganisationseinheitByKey(idTeam).getIdPartnerprofil());	

			
				result.addElement(pe);
				}
			}   
		catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
		
	}
	
//Objekt Person ausgeben
public Person findByPerson(Person p){
	return this.findPersonByKey(p.getId());	
}

//Personen die einem Unternehmen angehören ausgeben
public Vector<Person> findPersonByUnternehmen(int idUnternehmen){
	Connection con = DBConnection.connection();
	Vector<Person> result = new Vector<Person>();
	
	try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM person " 
		+ " WHERE idUnternehmen= " + idUnternehmen 
		+ " ORDER BY nachname ASC");
		
		
		while (rs.next()){
			Person pe = new Person();
			pe.setId(rs.getInt("idPerson"));
			pe.setTitel(rs.getString("titel"));
			pe.setVorname(rs.getString("vorname"));
			pe.setNachname(rs.getString("nachname"));
			pe.setIdUnternehmen(rs.getInt("idUnternehmen"));
			pe.setIdTeam(rs.getInt("idTeam"));
			pe.setAdresse(super.findOrganisationseinheitByKey(idUnternehmen).getAdresse());
			pe.setStandort(super.findOrganisationseinheitByKey(idUnternehmen).getStandort());
			pe.setIdPartnerprofil(super.findOrganisationseinheitByKey(idUnternehmen).getIdPartnerprofil());	

		
			result.addElement(pe);
			}
		}   
	catch (SQLException e) {
	e.printStackTrace();
	}
	return result;
	
}

/*
	
	public Vector<Person> findByNachname(String nachname) {
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT idPerson, vorname, nachname " + "FROM person "
					+ "WHERE nachname LIKE '" + nachname + "' ORDER BY nachname");

			while (rs.next()) {
				Person p = new Person();
				p.setIdPerson(rs.getInt("idPerson"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));

				result.addElement(p);
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}

		return result;
	}
*/

	// INSERT
	public Person insertPerson(Person pe) {
		Connection con = DBConnection.connection();
		//int id=0;
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPerson) AS maxid " + "FROM person ");

			if (rs.next()) {

				pe.setId(rs.getInt("maxid") + 1);
				//id=pe.getId();
				stmt = con.createStatement();

				if(pe.getIdTeam()==null && pe.getIdUnternehmen()==null){
			        stmt.executeUpdate("INSERT INTO person (emailAddresse, idPerson, titel, vorname, nachname) "
				            + "VALUES ('" + pe.getEmailAddresse() + "'," + pe.getId() + ",'" + pe.getTitel() + "','"
				            + pe.getVorname() + "','" + pe.getNachname() +"')");
		        }else if(pe.getIdTeam()!=null && pe.getIdUnternehmen()==null){
			        stmt.executeUpdate("INSERT INTO person (emailAddresse, idPerson, titel, vorname, nachname, idTeam) "
				            + "VALUES ('" + pe.getEmailAddresse() + "'," + pe.getId() + ",'" + pe.getTitel() + "','"
				            + pe.getVorname() + "','" + pe.getNachname() + "','" + pe.getIdTeam() +"')");
		        }else if(pe.getIdTeam()==null && pe.getIdUnternehmen()!=null){
			        stmt.executeUpdate("INSERT INTO person (emailAddresse, idPerson, titel, vorname, nachname, idUnternehmen) "
				            + "VALUES ('" + pe.getEmailAddresse() + "'," + pe.getId() + ",'" + pe.getTitel() + "','"
				            + pe.getVorname() + "','" + pe.getNachname() + "','" + pe.getIdUnternehmen() +"')");
		        }else if(pe.getIdTeam()!=null && pe.getIdUnternehmen()!=null){
			        
			        stmt.executeUpdate("INSERT INTO person (emailAddresse, idPerson, titel, norname, nachname, idUnternehmen, idTeam) "
			            + "VALUES ('" + pe.getEmailAddresse() + "'," + pe.getId() + ",'" + pe.getTitel() + "','"
			            + pe.getVorname() + "','" + pe.getNachname() + "','" + pe.getIdUnternehmen() + "','" + pe.getIdTeam() +"')");
		        }        
		}} 
			catch (SQLException e4) {
			e4.printStackTrace();
		}

		return pe;
	}

	// UPDATE
	public Person updatePerson(Person pe) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE person " + "SET vorname=\"" + pe.getVorname() + "\", "  
					+ "nachname=\"" + pe.getNachname() + "\" " 
					+ "titel=\"" + pe.getTitel() +  "\" " 
					+ "idUnternehmen=\"" + pe.getIdUnternehmen() + "\", " 
					+ "idTeam=\""+ pe.getIdTeam() + "\" " 
					+ " WHERE idPerson= " + pe.getId());

		} catch (SQLException e5) {
			e5.printStackTrace();
		}

		return pe;
	}

	// DELETE
	public void deletePerson(Person pe) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE * FROM person " + " WHERE idPerson= " + pe.getId());
		} catch (SQLException e6) {
			e6.printStackTrace();
		}
	}

}
