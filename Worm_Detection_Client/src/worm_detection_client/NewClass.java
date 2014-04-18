/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package worm_detection_client;

import java.sql.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class NewClass {

    private static Connection con;
    public static String ip;
    public static Socket socket;
    public static String file_name;

    public static void test() {
        File[] files = File.listRoots();

        for (File f : files) {

            parseAllFiles(f.getAbsolutePath());
        }

        NewClass.delete_worm_File();

    }

    public static void delete_worm_File() {
        try {
            database_connection dc = new database_connection();
            con = dc.get_Connection();
            ip = socket.getInetAddress().getHostAddress();
            String sqlquery = "SELECT Filename FROM File_Transfer where Client_ip=? and Status='worm affected file'";
            PreparedStatement pstshow = con.prepareStatement(sqlquery);
            pstshow.setString(1, ip);
            ResultSet resultset = pstshow.executeQuery();
            while (resultset.next()) {
                file_name = resultset.getString("Filename");
            }
            File f1 = new File(file_name);



            //*****************************************************//                  


            //f1.delete();



            //***********************************************************//   


        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void parseAllFiles(String parentDirectory) {
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        for (File f : filesInDirectory) {
            try {
                String s = f.getAbsolutePath();
//                System.out.println(s);
                if (f.isDirectory()) {

                    Path ps = Paths.get(s);
                    if (Files.isSymbolicLink(ps)) {
                        System.out.println("link.............................................");
                    } else {
                        // System.out.println("filesssssssssssss"+f.getAbsolutePath());
                        parseAllFiles(f.getAbsolutePath());
                    }
                } else {
                    File dir = new File(f.getAbsolutePath());
                    if (dir.canExecute()) {
                        System.out.println("files!!!!!!! " + f.getAbsolutePath());
                        File ff = new File("/home/anju/PR/worm_info");
                        FileReader fr = new FileReader(ff);
                        BufferedReader br = new BufferedReader(fr);
                        String str2;
                        String str1 = f.getName();
                        while ((str2 = br.readLine()) != null) {
                            String line = str2.trim();
                            if (str1.toLowerCase().contains(line.toLowerCase())) {
                                System.out.println("File is: " + f.getAbsolutePath());
                                //            f.delete();
                                System.out.println("file deleted********************" + f.getAbsolutePath());
                            }
                        }
                        ///////////////// TESTING OF WORM EXTENSIONS FROM THE FILE: worm_extensions/////////////////////             


                        File fi2 = new File("/home/anju/PR/worm_extensions");
                        FileReader fileReader2 = new FileReader(fi2);
                        BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

                        String line3;
                        while ((line3 = bufferedReader2.readLine()) != null) {
                            String name = line3.trim();
                            int mid = str1.lastIndexOf(".");
                            String ext = str1.substring(mid + 1, str1.length());

                            if (ext.toLowerCase().contains(name.toLowerCase())) {
                                // System.out.println("File is: " + f.getAbsolutePath());
                                //////// //// //////     f.delete();
                                System.out.println("file detected by extension********************" + f.getAbsolutePath());
                            }

                        }
                        /// }                    
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
