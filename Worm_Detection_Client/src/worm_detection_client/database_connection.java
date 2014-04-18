package worm_detection_client;

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
    try {System.out.println("DATA4");
//    Class.forName("com.mysql.jdbc.Driver");
//          con = DriverManager.getConnection("jdbc:mysql://192.168.1.15:3306/wormdetection","snortusr","snort");
         Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://192.168.16.61:3306/wormdetection", "snortusr", "snort");
 System.out.println("DATA3");
    } catch (Exception ex) {
        // log an exception. for example:
        System.out.println("data1"+ex);
    }
    return con;
}
    
    
}
