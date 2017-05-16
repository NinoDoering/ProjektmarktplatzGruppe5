package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;
import java.*; //Pakete, welche zum Ausführen benötigt werden.
import javax.*;
import java.sql.*;

public class PersonMapper {

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
	public Person findPersonByKey(int idPerson) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT idPerson, titel, vorname, nachname" + " FROM person " + "WHERE idPerson=" + idPerson);

			if (rs.next()) {
				Person p = new Person();
				p.setIdPerson(rs.getInt("idPerson"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));
				p.setTitel(rs.getString("titel"));

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
					.executeQuery("SELECT idPerson, vorname, nachname " + "FROM person " + "ORDER BY nachname");

			while (rs.next()) {
				Person p = new Person();
				p.setIdPerson(rs.getInt("idPerson"));
				p.setVorname(rs.getString("vorname"));
				p.setNachname(rs.getString("nachname"));

				result.addElement(p);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

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

	// INSERT
	public Person insert(Person p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idPerson) AS maxid " + "FROM person ");

			if (rs.next()) {

				p.setIdPerson(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO person (idPerson, titel, vorname, nachname) " + "VALUES (" + p.getIdPerson() + ",'"
								+ p.getTitel() + "','" + p.getVorname() + "','" + p.getNachname() + "')");
			}
		} catch (SQLException e4) {
			e4.printStackTrace();
		}

		return p;
	}

	// UPDATE
	public Person update(Person p) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE person " + "SET vorname=\"" + p.getVorname() + "\", " + "nachname=\""
					+ p.getNachname() + "\" " + "WHERE idPerson=" + p.getIdPerson());

		} catch (SQLException e5) {
			e5.printStackTrace();
		}

		return p;
	}

	// DELETE
	public void delete(int idPerson) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE " + " FROM person " + "WHERE idPerson=" + idPerson);
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
