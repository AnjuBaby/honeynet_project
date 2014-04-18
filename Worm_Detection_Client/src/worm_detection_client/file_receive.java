/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package worm_detection_client;

import Client_GUI.Client_home;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.Request;

/**
 *
 * 
 */
public class file_receive extends Thread {

    BufferedReader br;
    public Socket socket;
    byte[] filedata1;
    public static ObjectInputStream receive;
    Request request;

    public file_receive(String ipAdress) throws UnknownHostException, IOException, InterruptedException {


        socket = new Socket(ipAdress, 63400);

        request = new Request();
        //start();

    }

    public void run() {
        while (true) {

            try {

                receive = new ObjectInputStream(socket.getInputStream());

                request = (Request) receive.readObject();
                filedata1 = request.FileData;

System.out.println("enter into file transfer ");
                if (filedata1 != null) {
                    System.out.println("File Received from sender!");

                    File file2 = new File("/home/anju");

                    if (file2.exists()) {
                    } else {
                        file2.mkdir();
                    }
                    File file1 = new File("/home/anju" + "/" + request.Filename);


                    FileOutputStream outputStream = new FileOutputStream(file1);


                    outputStream.write(filedata1);
                    outputStream.close();

                }
            } catch (Exception ex) {
                System.out.println(ex);
                Client_home.popup();
                currentThread().interrupt();
                break;

            }

        }

    }
}
