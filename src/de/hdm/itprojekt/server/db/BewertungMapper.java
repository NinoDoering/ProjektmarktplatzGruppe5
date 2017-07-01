package de.hdm.itprojekt.server.db;

import java.sql.*;

import de.hdm.itprojekt.shared.bo.*;

public class BewertungMapper {


	/**
	 * Variable "bewertungMapper"ist aufgrund des Bezeichners "static" nur einmal für die Instanzen dieser Klasse
	 * verfügbar. Sie speichert die die einzige Instanz dieser Klasse.
	 * @author Thies
	 */
	private static BewertungMapper bewertungMapper = null;

	/**
	 * Konstruktor, der verhindert, dass dass man neue Instanzen dieser Klasse erstellen kann
	 * Sie speichert die einzige Instanz dieser Klasse
	 * @author Thies
	 */
	protected BewertungMapper(){
		
	}
	
	/**
	 * Diese Methode stellt die Singleton-Eigenschaft der "BewertungMapper"-Klasse sicher,
	 * sodass nur eine  Instanz von <code>BewertungMapper</code> existieren kann.
	 * @return bewertungMapper
	 */
	public static BewertungMapper bewertungMapper(){
		if (bewertungMapper == null){
			bewertungMapper = new BewertungMapper();
		}
		return bewertungMapper;
	}
	
	/**
	 * Suchen einer Bewertung über die übergebene Bewertungsnummer
	 * @param idBewertung
	 * @return Bewertungsobjekt, das der übergebenen Bewertungsnummer entspricht oder null bei nicht vorhandenem Datensatz
	 */
	public Bewertung findBewertungByKey(int idBewertung){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			//SQL
			ResultSet rs = stmt.executeQuery("SELECT * FROM bewertung " 
											+ " WHERE idBewertung= " + idBewertung);
		
			if(rs.next()){
				Bewertung bewertung = new Bewertung();
				
				bewertung.setId(rs.getInt("idBewertung"));
				bewertung.setTextuelleBewertung(rs.getString("textuelleBewertung"));
				bewertung.setFliesskommaBewertung(rs.getFloat("fliesskommaBewertung"));
				
				return bewertung;
			}
		}
	catch (SQLException e){
		e.printStackTrace();
		return null;
	}
	return null;
	}
	
	/**
	 * Einfuegen eines <code>Bewertung</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschlüssel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt. @author Thies
	 * 
	 * @param bewertung
	 * @return Bewertungsobjekt wird in die Datenbank eingefuegt
	 */
	public Bewertung insertBewertung(Bewertung bewertung) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(idBewertung) AS maxid " + " FROM bewertung ");

			if (rs.next()) {

				bewertung.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO bewertung (idBewertung, idBewerbung, textuelleBewertung, fliesskommaBewertung) "
								+ "VALUES ('" + bewertung.getId() + "','" + bewertung.getIdBewerbung() + "','"
								+ bewertung.getTextuelleBewertung() + "','" + bewertung.getFliesskommaBewertung()
								+ "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bewertung;
	}

	/**
	 * Loeschen des uebergebenen Bewertungsobjekts
	 * @param bewertung das uebergebene Bewertungsobjekt
	 */
	public void deleteBewertung(Bewertung bewertung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE * FROM bewertung" + " WHERE idBewertung= " + bewertung.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update des übergebenen Bewertungsobjekts
	 * @param bewertung
	 * @return Das übergebene Bewertungsobjekt
	 */
	public Bewertung updateBewertung(Bewertung bewertung) {
		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE bewertung " + "SET idBewertung='" + bewertung.getId() + "' ,'" + "idBewerbung='"
					+ bewertung.getIdBewerbung() + "' ,'" + "textuelleBewertung='" + bewertung.getTextuelleBewertung()
					+ "' ,'" + "fliesskommaBewertung='" + bewertung.getFliesskommaBewertung() + "' ,'"
					+ " WHERE idBewertung= '" + bewertung.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bewertung;
	}
	
	
	/**
	 * Objekt Bewertung ausgeben nach der ID
	 * @param bewertung
	 * @return Bewertungsobjekt, das der übergebenen ID entspricht
	 */
	public Bewertung findByBewertung(Bewertung bewertung) {
		return this.findBewertungByKey(bewertung.getId());
	}

	/**
	 * Suchen von Bewertungen über Fremdschlüssel der Bewerbung
	 * @param idBewerbung
	 * @return Vector mit allen Bewertungen, die dem übergebenen Fremdschlüssel der Bewerbung entsprechen
	 */
	public Bewertung findBewertungByBewerbung(int idBewerbung) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM bewertung " + " WHERE idBewerbung= " + idBewerbung);

			if (rs.next()) {
				Bewertung bewertung = new Bewertung();
				bewertung.setId(rs.getInt("idBewertung"));
				bewertung.setTextuelleBewertung(rs.getString("textuelleBewertung"));
				bewertung.setFliesskommaBewertung(rs.getFloat("fliesskommaBewertung"));
				bewertung.setIdBewerbung(rs.getInt("idBewerbung"));
				return bewertung;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}