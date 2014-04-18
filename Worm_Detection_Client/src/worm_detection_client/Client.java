/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package worm_detection_client;

import Client_GUI.ServerConnection;
import java.io.*;
import java.net.*;

/**
 *
 * 
 */
public class Client {

    public static String filepath;
    public static String selected_IP;

    public static void test11(String filepath, String selected_IP) {
        try {

            filepath = filepath;
           
             File file = new File(filepath);
             String filename=file.getName();
              selected_IP = selected_IP+"/"+filename;
         Socket socket = ServerConnection.socket;
            
            //*******************passing selected_IP to server***************//
            
            
            PrintWriter printwriter = new PrintWriter(socket.getOutputStream(), true);
            printwriter.println(selected_IP);
            
            //*********passing selectedfile to server********************//

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String clientIpAddress = socket.getLocalAddress().getHostAddress();
           
            
            if (file.exists()) {
                FileInputStream inputstream = new FileInputStream(file);
                byte[] FileData = new byte[(int) file.length()];
                new FileInputStream(file).read(FileData);
                oos.writeObject(FileData);
                oos.flush();
            }



        } catch (Exception e) {
            System.out.println(e);

        }

    }
}
