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

		        result.addElement(p);
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return result;
		  }
	 
	//n�chster Schritt: 
	 public Vector<Partnerprofil> findByAusschreibung(int AusschreibungID) {
		 //pr�fen wegen Fremdschl�ssel von Ausschreibung: gegebenenfalls korrigieren!!
		    Connection con = DBConnection.connection();
		    Vector<Partnerprofil> result = new Vector<Partnerprofil>();

		    try {
		      Statement stmt = con.createStatement();
		      
		      //FREMDSCHL�SSEL �BERPR�FUEN!!
		      ResultSet rs = stmt.executeQuery("SELECT id FROM partnerprofil "
		          + "WHERE ausschreibung=" + ausschreibungID + " ORDER BY id");

		      while (rs.next()) {
		        Partnerprofil p = new Partnerprofil();
		        p.setId(rs.getInt("id"));
		        p.setAusschreibungID(rs.getInt("ausschreibung"));

		        // Hinzufügen des neuen Objekts zum Ergebnisvektor
		        result.addElement(p);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	
	 //INSERT INTO 
	 public Partnerprofil insert(Partnerprofil p) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
		          + "FROM partnerprofil ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		        p.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfuegeoperation
		        stmt.executeUpdate("INSERT INTO partnerprofil (id) "
		            + "VALUES (" + p.getId());
		      }
		    }
		    catch (SQLException e3) {
		      e3.printStackTrace();
		    }

		    /*
		     * Rueckgabe, des evtl. korrigierten Customers.
		     * 
		     * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		     * Objekte uebergeben werden, wäre die Anpassung des Customer-Objekts auch
		     * ohne diese explizite Rueckgabe au�erhalb dieser Methode sichtbar. Die
		     * explizite Rückgabe von c ist eher ein Stilmittel, um zu signalisieren,
		     * dass sich das Objekt evtl. im Laufe der Methode veraendert hat.
		     */
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

		    // Um Analogie zu insert(Customer c) zu wahren, geben wir c zurück
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
	 /**
	   * Auslesen der zugehörigen <code>Account</code>-Objekte zu einem gegebenen
	   * Kunden.
	   * 
	   * @param c der Kunde, dessen Konten wir auslesen möchten
	   * @return ein Vektor mit sömtlichen Konto-Objekten des Kunden
	   */
	  public Vector<Partnerprofil> getPartnerprofilOf(Partnerprofil p) {
	    /*
	     * Wir bedienen uns hier einfach des AccountMapper. Diesem geben wir einfach
	     * den in dem Customer-Objekt enthaltenen Primärschlüssel.Der CustomerMapper
	     * löst uns dann diese ID in eine Reihe von Konto-Objekten auf.
	     */
		 
		  return PartnerprofilMapper.partnerprofilMapper().findbyKey(id);
	    //return AccountMapper.accountMapper().findByOwner(c); richtig �bersetzt?
	  }
	}