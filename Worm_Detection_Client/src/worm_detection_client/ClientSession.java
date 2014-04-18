/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package worm_detection_client;

import module.Request;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
class ClientSession extends Thread{
 private Socket socket;
    private Request request;
    private ObjectInputStream receive;
    private ObjectOutputStream send;
    public String clientIpAddress;
    public String ServerIP;
    public ClientSession(String serverIpAddress) {
         try {
            ServerIP=serverIpAddress;
            socket = new Socket(ServerIP, 63400);
            clientIpAddress = socket.getLocalAddress().getHostAddress();
            receive = new ObjectInputStream(socket.getInputStream());
            send = new ObjectOutputStream(socket.getOutputStream());
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     public void sendData(Request request) {
        try {
            send.writeObject(request);
            send.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

