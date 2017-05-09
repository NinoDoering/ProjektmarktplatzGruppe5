package de.hdm.itprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.*;

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
	
	//FindByKey
	public Person findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt
	          .executeQuery("SELECT id, vorname, nachname, geschlecht FROM person "
	              + "WHERE id=" + id + " ORDER BY nachname");

	      
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        Person p = new Person();
	        p.setId(rs.getInt("id"));
	        p.setVorname(rs.getString("vorname"));
	        p.setNachname(rs.getString("nachname"));
	        p.setGeschlecht(rs.getChar("geschlecht")); //Was tun hier? 
	        										   //char undefined for rs.

	        return p;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }
	
	//FindAll
	 public Vector<Person> findAll() {
		    Connection con = DBConnection.connection();
		    // Ergebnisvektor vorbereiten
		    Vector<Person> result = new Vector<Person>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname "
		          + "FROM person " + "ORDER BY nachname");

		      while (rs.next()) {
		        Person p = new Person();
		        p.setId(rs.getInt("id"));
		        p.setVorname(rs.getString("vorname"));
		        p.setNachname(rs.getString("nachname"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(p);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
		  }
	 
	 public Vector<Person> findByNachname(String nachname) {
		    Connection con = DBConnection.connection();
		    Vector<Person> result = new Vector<Person>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname "
		          + "FROM person " + "WHERE nachname LIKE '" + nachname
		          + "' ORDER BY nachname");

		      
		      while (rs.next()) {
		        Person p = new Person();
		        p.setId(rs.getInt("id"));
		        p.setVorname(rs.getString("vorname"));
		        p.setNachname(rs.getString("nachname"));

		        result.addElement(p);
		      }
		    }
		    catch (SQLException e3) {
		      e3.printStackTrace();
		    }

		    return result;
		  }
	
	 
	 //INSERT
	 public Person insert(Person p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM person ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        p.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
		        stmt.executeUpdate("INSERT INTO person (id, vorname, nachname) "
		            + "VALUES (" + p.getId() + ",'" + p.getVorname() + "','"
		            + p.getNachname() + "')");
		      }
		    }
		    catch (SQLException e4) {
		      e4.printStackTrace();
		    }

		    /*
		     * Rückgabe, des evtl. korrigierten Customers.
		     * 
		     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		     * Objekte übergeben werden, wäre die Anpassung des Customer-Objekts auch
		     * ohne diese explizite Rückgabe au�erhalb dieser Methode sichtbar. Die
		     * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
		     * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
		     */
		    return p;
		  }
	 
	 //UPDATE
	 public Person update(Person p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("UPDATE person " + "SET vorname=\""
		          + p.getVorname() + "\", " + "nachname=\"" + p.getNachname() + "\" "
		          + "WHERE id=" + p.getId());

		    }
		    catch (SQLException e5) {
		      e5.printStackTrace();
		    }

		    return p;
		  }
	 
	 //DELETE
	 public void delete(Person p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      stmt.executeUpdate("DELETE FROM person " + "WHERE id=" + p.getId());
		    }
		    catch (SQLException e6) {
		      e6.printStackTrace();
		    }
		  }
	 
	 //FAN-IN-FAN-OUT-Analyse -->RICHTIG??
	 public Vector<Person> getPersonOf(Person p) {
		    /*
		     * Wir bedienen uns hier einfach des AccountMapper. Diesem geben wir einfach
		     * den in dem Customer-Objekt enthaltenen Primärschlüssel.Der CustomerMapper
		     * löst uns dann diese ID in eine Reihe von Konto-Objekten auf.
		     */
		    return PersonMapper.personMapper().findByKey(id); //FALSCH, WARUM?
		  }
	 
}
