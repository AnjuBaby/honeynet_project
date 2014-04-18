package Java_Files;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.Request;

/**
 *
 * 
 */
public class Backup_server extends Thread {

    private Connection con, con1;
    public static ObjectInputStream receive;
    public static byte[] FileData;
    public static Socket socket;
    public static byte[] filedata;
    BufferedReader br;
    Server2Connection server2connection;
    ObjectOutputStream oos;
    Request request;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    //System.out.println(dateFormat.format(date));

    public Backup_server(Socket socket, Server2Connection server2connection) {
        try {
            this.socket = socket;
            this.server2connection = server2connection;
            request = new Request();
            database_connection dc = new database_connection();
            con = dc.get_Connection();
            Snort_database_Connection dc1 = new Snort_database_Connection();
            con1 = dc1.get_Connection();
        } catch (Exception e) {
        }

    }

    public void run() {
        while (true) {
            try {

                System.out.println("**************************");
                receive = new ObjectInputStream(socket.getInputStream());
                request = (Request) receive.readObject();
                filedata = request.FileData;
//Connection con1=DriverManager.getConnection("jdbc:mysql://192.168.1.54:3306/snort", "snortusr", "snort");
//Connection con=DriverManager.getConnection("jdbc:mysql://192.168.1.54:3306/wormdetection", "snortusr", "snort");
                String ip_Client = socket.getInetAddress().getHostAddress();
                String sqlquery1 = "SELECT ip_src, inet_ntoa(ip_src) FROM iphdr; ";
                PreparedStatement pstshow = con1.prepareStatement(sqlquery1);
                ResultSet resultset = pstshow.executeQuery();
                while (resultset.next()) {
                   // System.out.println("value"+resultset.getInt(1));
                    long ip_src1 = resultset.getLong(1);
                    int Decoded_ip_src = resultset.getInt(2);
                    String Decoded_ip_src1 = "" + Decoded_ip_src;
                    String Decoded_ip_src2 = Decoded_ip_src1.trim();
                    if (ip_Client == Decoded_ip_src2) {
                        PreparedStatement pstshow1 = con1.prepareStatement("SELECT TIMESTAMP FROM iphdr,event WHERE iphdr.sid=event.sid AND iphdr.cid=event.cid AND iphdr.ip_src=?");
                       int ip_src2=(int)ip_src1;
                        pstshow1.setInt(1, ip_src2);
                        ResultSet resultset1 = pstshow.executeQuery();
                        while (resultset1.next()) {
                            String timestamp = resultset1.getString(1);
                            String current_time = dateFormat.format(date);
                            String timestamp1 = timestamp.substring(0, 16);
                            String current_time1 = current_time.substring(0, 16);
                            if (timestamp1 == current_time1) {
                                String sqlquery = "INSERT INTO File_Transfer VALUES(?,?,?,?,?)";
                                PreparedStatement pstregister_f = con.prepareStatement(sqlquery);
                                pstregister_f.setString(1, socket.getInetAddress().getHostAddress());
                                pstregister_f.setString(2, request.Filepath);
                                pstregister_f.setString(3, "worm affected file");
                                pstregister_f.setString(4, request.IPAddress);
                                pstregister_f.setString(5, dateFormat.format(date));
                                pstregister_f.executeUpdate();
                                socket.close();
                            }
                        }

                    }
                }


                if (filedata != null) {
                    Socket country = (Socket) server2connection.clientmanager.get(request.IPAddress);

                    System.out.println("pppppppppppppppppppppppp" + country);
                    oos = new ObjectOutputStream(country.getOutputStream());
                    String filename = request.Filename;
                    request.setDisplaySchedule(filename, filedata);
                    oos.writeObject(request);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Backup_server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SocketException se) {
                socketException();
                System.out.println(se);

            } catch (NullPointerException ne) {
                nullpointerException();
                System.out.println("NULLPOINTER"+ne);
            } catch (IOException ioe) {
            } catch (ClassNotFoundException cne) {
            }

        }

    }

    public void socketException() {
        try {
            String sqlquery = "INSERT INTO File_Transfer VALUES(?,?,?,?,?)";
            PreparedStatement pstregister_f = con.prepareStatement(sqlquery);
            pstregister_f.setString(1, socket.getInetAddress().getHostAddress());
            pstregister_f.setString(2, request.Filepath);
            pstregister_f.setString(3, "Offline");
            pstregister_f.setString(4, request.IPAddress);
            pstregister_f.setString(5, dateFormat.format(date));
            pstregister_f.executeUpdate();
        } catch (Exception e) {
            System.out.println("dffghjhjkj"+e);
        }

    }

    public void nullpointerException() {
        try {
            String sqlquery = "INSERT INTO File_Transfer VALUES(?,?,?,?,?)";
            PreparedStatement pstregister_f = con.prepareStatement(sqlquery);
            System.out.println("ffffffffffffffffffffffffffffff" + socket.getInetAddress().getHostAddress());
            pstregister_f.setString(1, socket.getInetAddress().getHostAddress());

            pstregister_f.setString(2, request.Filepath);
            pstregister_f.setString(3, "Not Connected");
            pstregister_f.setString(4, request.IPAddress);
            pstregister_f.setString(5, dateFormat.format(date));
            pstregister_f.executeUpdate();
        } catch (Exception e) {
            System.out.println("bbbbbbbbbbbbbb  " + e);
        }

    }
}
