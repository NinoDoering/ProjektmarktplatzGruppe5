package de.hdm.itprojekt.server.db;
import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

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
