package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;

import java.*;		
import javax.*;

import com.google.appengine.api.utils.SystemProperty;

import java.sql.*;
//import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {


    private static Connection con = null;

  
    private static String googleUrl = "jdbc:google:mysql://it-projektgruppe5:itprojektgruppe5?user=demo&password=demo";
    private static String localUrl = "jdbc:mysql://173.194.109.156:3306/itprojektgruppe5?user=demo&password=demo";

   
    public static Connection connection() {
        if (con == null) {
            String url = null;
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            
                    Class.forName("com.mysql.jdbc.GoogleDriver");
                    url = googleUrl;
                } else {
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                }
              
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }
        return con;
    }

}

/*
	private static Connection con = null; 
	private static String url = "jdbc:mysql://127.0.0.1:3306/projektmarktplatzgruppe5?user=root&password=";
  
    //Anlegen eines �Connection� Objekts, welches sp�ter die Verbindung
    //zur Datenbank �bernimmt
   ; 
    
    //Statische Methode �establish�, welches jedes Mal aufgerufen wird, 
    //wenn eine Datenbank Vebindung ben�tigt wird (z.B. bei SQL-
    //Statements. Da eine statische Methode, muss zum Methodenaufruf kein 
    //extra Objekt erstellt werden. Ein Methodenzugriff ist direkt durch 
    //Klassenaufruf m�glich. (Methode kann nat�rlich genannt werden, wie 
    //man will)

    public static Connection connection(){

    	if(con==null){
    		String localUrl=null;
    		
    		
    		try{
    			
    			 Class.forName("com.mysql.jdbc.Driver");
    			
			
				//�bergeben von Verbindungsinformationen
    			url = localUrl; 
    			
    			
			//R�ckgabe des fertig verbundenen Verbindungsobjekts
    			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projektmarktplatzgruppe5?user=root&password="); 
    			
    			
    			
    		}catch(Exception e){
    			
    			con = null; 
    			System.out.println(e+ " Fehler");
    		}
    	}
    	return con;
    }
}

*/