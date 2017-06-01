package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import java.*; //Pakete, welche zum Ausf�hren ben�tigt werden.
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

			ResultSet rs = stmt.executeQuery ("SELECT idPerson, titel, vorname, nachname, idTeam, idUnternehmen" 
			+ " FROM person " + "WHERE idPerson= " + id);
			
			if (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("idPerson"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setTitel(rs.getString("titel"));
				p.setIdUnternehmen(rs.getInt("idUnternehmen"));
				p.setIdTeam(rs.getInt("idTeam"));
				p.setAdresse(super.findOrganisationseinheitByKey(id).getAdresse());
				p.setStandort(super.findOrganisationseinheitByKey(id).getStandort());
				p.setIdPartnerprofil(super.findOrganisationseinheitByKey(id).getIdPartnerprofil());
				
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	// FindAll
	public Vector<Person> findAll() {
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT idPerson, titel, vorname, nachname, idTeam, idUnternehmen " 
			+ "FROM person " + "ORDER BY nachname");

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("idPerson"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setTitel(rs.getString("titel"));
				p.setIdUnternehmen(rs.getInt("idUnternehmen"));
				p.setIdTeam(rs.getInt("idTeam"));
				p.setAdresse(super.findByOrganisationseinheit(p).getAdresse());
				p.setStandort(super.findByOrganisationseinheit(p).getStandort());
				p.setIdPartnerprofil(super.findByOrganisationseinheit(p).getIdPartnerprofil());

				result.addElement(p);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

public Vector<Person> findByTeam(int idTeam){
		Connection con = DBConnection.connection();
		Vector<Person> result = new Vector<Person>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * " + " FROM person " 
			+ "WHERE idTeam= " + idTeam + " ORDER BY nachname");
			
			
			while (rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("idPerson"));
				p.setTitel(rs.getString("titel"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setIdUnternehmen(rs.getInt("idUnternehmen"));
				p.setIdTeam(rs.getInt("idTeam"));
				p.setAdresse(super.findOrganisationseinheitByKey(idTeam).getAdresse());
				p.setStandort(super.findOrganisationseinheitByKey(idTeam).getStandort());
				p.setIdPartnerprofil(super.findOrganisationseinheitByKey(idTeam).getIdPartnerprofil());	

			
				result.addElement(p);
				}
			}   
		catch (SQLException e) {
		e.printStackTrace();
		}
		return result;
		
	}
	
public Person findByPerson(Person p){
	return this.findPersonByKey(p.getId());	
}

public Vector<Person> findByUnternehmen(int idUnternehmen){
	Connection con = DBConnection.connection();
	Vector<Person> result = new Vector<Person>();
	
	try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * " + " FROM person " 
		+ "WHERE idUnternehmen= " + idUnternehmen + " ORDER BY nachname");
		
		
		while (rs.next()){
			Person p = new Person();
			p.setId(rs.getInt("idPerson"));
			p.setTitel(rs.getString("titel"));
			p.setVorname(rs.getString("vorname"));
			p.setNachname(rs.getString("nachname"));
			p.setIdUnternehmen(rs.getInt("idUnternehmen"));
			p.setIdTeam(rs.getInt("idTeam"));
			p.setAdresse(super.findOrganisationseinheitByKey(idUnternehmen).getAdresse());
			p.setStandort(super.findOrganisationseinheitByKey(idUnternehmen).getStandort());
			p.setIdPartnerprofil(super.findOrganisationseinheitByKey(idUnternehmen).getIdPartnerprofil());	

		
			result.addElement(p);
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
	public int insertPerson(Person p) {
		Connection con = DBConnection.connection();
		int id=0;
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPerson) AS maxid " + "FROM person ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);
				id=p.getId();
				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO person (idPerson, titel, vorname, nachname, idTeam, idUnternehmen) " 
				+ "VALUES (" + p.getId() + ",'"
								+ p.getTitel() + "','" 
								+ p.getVorname() + "','" 
								+ p.getNachname() + "','"
								+ p.getIdTeam() + "','"
								+ p.getIdUnternehmen() + "')");
			}
		} catch (SQLException e4) {
			e4.printStackTrace();
		}

		return id;
	}

	// UPDATE
	public Person updatePerson(Person p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE person " + "SET vorname=\"" + p.getVorname() + "\", "  
					+ "nachname=\"" + p.getNachname() + "\" " 
					+ "titel=\"" + p.getTitel() +  "\" " 
					+ "idUnternehmen=\"" + p.getIdUnternehmen() + "\", " 
					+ "idTeam=\""+ p.getIdTeam() + "\" " 
					+ "WHERE idPerson=" + p.getId());

		} catch (SQLException e5) {
			e5.printStackTrace();
		}

		return p;
	}

	// DELETE
	public void deletePerson(Person p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE " + " FROM person " + "WHERE idPerson=" + p.getId());
		} catch (SQLException e6) {
			e6.printStackTrace();
		}
	}

	// FAN-IN-FAN-OUT-Analyse -->RICHTIG??
	/*
	 * public Vector<Person> getPersonOf(Person p) {
	 * 
	 * return PersonMapper.personMapper().findByKey(p); // FALSCH, WARUM? }
	 */
}
