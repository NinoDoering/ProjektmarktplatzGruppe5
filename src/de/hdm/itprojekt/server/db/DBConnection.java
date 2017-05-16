package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;

import java.*;		
import javax.*;
import java.sql.*;
//import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {

/*
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

*/
	private static Connection con = null; 
	private static String url = "jdbc:mysql://127.0.0.1:3306/projektmarktplatzgruppe5?user=root&password=";
  
    //Anlegen eines „Connection“ Objekts, welches später die Verbindung
    //zur Datenbank übernimmt
   ; 
    
    //Statische Methode „establish“, welches jedes Mal aufgerufen wird, 
    //wenn eine Datenbank Vebindung benötigt wird (z.B. bei SQL-
    //Statements. Da eine statische Methode, muss zum Methodenaufruf kein 
    //extra Objekt erstellt werden. Ein Methodenzugriff ist direkt durch 
    //Klassenaufruf möglich. (Methode kann natürlich genannt werden, wie 
    //man will)

    public static Connection connection(){

    	if(con==null){
    		String localUrl=null;
    		
    		
    		try{
    			
    			 Class.forName("com.mysql.jdbc.Driver");
    			
			
				//Übergeben von Verbindungsinformationen
    			url = localUrl; 
    			
    			
			//Rückgabe des fertig verbundenen Verbindungsobjekts
    			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projektmarktplatzgruppe5?user=root&password="); 
    			
    			
    			
    		}catch(Exception e){
    			
    			con = null; 
    			System.out.println(e+ " AMK");
    		}
    	}
    	return con;
    }
}

