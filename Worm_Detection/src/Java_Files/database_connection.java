package Java_Files;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 *
 */
import java.sql.*;
public class database_connection {
    private static Connection con;
public  Connection get_Connection() {
    try {System.out.println("DATA1");
//    Class.forName("com.mysql.jdbc.Driver");
//      System.out.println("DATA1");
//      
//          con = DriverManager.getConnection("jdbc:mysql://192.168.1.15:3306/wormdetection","snortusr","snort");
      Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://192.168.16.61:3306/wormdetection", "snortusr", "snort");
          
          System.out.println("DATA22"); // Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/evps", "root", "root")
    } catch (Exception ex) {
        // log an exception. for example:
        System.out.println("database"+ex);
    }
    return con;
}
    
    
}
