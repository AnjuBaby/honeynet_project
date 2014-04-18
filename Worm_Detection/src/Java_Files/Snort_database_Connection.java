package Java_Files;



import java.sql.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
public class Snort_database_Connection {
  private static Connection con;
public  Connection get_Connection() {
    try {
        Class.forName("com.mysql.jdbc.Driver");
      
     con=DriverManager.getConnection("jdbc:mysql://192.168.16.61:3306/snort", "snortusr", "snort");
        System.out.println("enter snort database");
    } catch (Exception ex) {
        // log an exception. for example:
        System.out.println("@snorts"+ex);
    }
    return con;
}
      
}
