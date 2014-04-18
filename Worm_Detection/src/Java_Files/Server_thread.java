package Java_Files;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;

public class Server_thread {

    private String Client_ip;
    private Connection con;
    // declare a server socket and a client socket for the server;
    // declare the number of connections
    ServerSocket BidServer = null;
    Socket clientSocket = null;
    int port;

    public Server_thread(int port) {
        this.port = port;
        try {
            database_connection dc = new database_connection();
            con = dc.get_Connection();
            startServer();
        } catch (SQLException ex) {
            Logger.getLogger(Server_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopServer() {
        System.out.println("Server cleaning up.");
        System.exit(0);
    }

    public void startServer() throws SQLException {
        try {
            BidServer = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            Server2Connection oneconnection = new Server2Connection(BidServer, con);
            oneconnection.start();
        } catch (Exception e) {
        }
    }
}
class Server2Connection extends Thread {

    ServerSocket ss;
    Socket clientsocket;
    Connection con;
    public Hashtable<String, Socket> clientmanager;

    public Server2Connection(ServerSocket serversocket, Connection con) {


        this.con = con;
        this.ss = serversocket;
        clientmanager = new Hashtable();
    }

    public void run() {
        while (true) {

            String line;
            try {
                Connection con=DriverManager.getConnection("jdbc:mysql://192.168.16.83:3306/wormdetection", "snortusr", "snort");
                clientsocket = ss.accept();
                String Client_ip = clientsocket.getInetAddress().getHostAddress();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM registration where ip_adress=?");
                ps.setString(1, Client_ip);
                System.out.println(Client_ip + "####################################");
                ResultSet rs1 = ps.executeQuery();
                if (rs1.next()) {
                    JOptionPane.showMessageDialog(null, "Authorised Client!!");
                    String ip = clientsocket.getInetAddress().getHostAddress();
                    clientmanager.put(ip, clientsocket);
                    Backup_server backup_server = new Backup_server(clientsocket, this);
                    backup_server.start();
                } else {
                    JOptionPane.showMessageDialog(null, "New Client!! Please Register!!");
                    String sqlquery = "SELECT COUNT(*) FROM New_Clients WHERE New_Clientip=?";
                    PreparedStatement pstregister = con.prepareStatement(sqlquery);
                    pstregister.setString(1, Client_ip);
                    ResultSet pstresult = pstregister.executeQuery();
                    pstresult.next();
                    if (pstresult.getInt(1) != 0) {
                        clientsocket.close();
                    }
                    else{
                        sqlquery = "INSERT INTO New_Clients VALUES(?)";
                        pstregister = con.prepareStatement(sqlquery);
                        pstregister.setString(1, Client_ip);
                        pstregister.executeUpdate();
                        System.out.println("!!!!!!!!!!!!!!!"+Client_ip);
                    }
                    clientsocket.close();
                }
                rs1.close();
            } catch (Exception e) {
                System.out.println("CONNECTION"+e);
            }
        }
    }
}
