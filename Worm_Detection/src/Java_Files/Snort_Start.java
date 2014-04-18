package Java_Files;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * 
 */
public class Snort_Start extends Thread{
        public void run(){
            
      //       TODO add your handling code here:
              try
            {
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec("sudo snort -c /etc/snort/snort.conf");
                InputStream stderr = proc.getErrorStream();
                InputStreamReader isr = new InputStreamReader(stderr);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                System.out.println("<ERROR>");
                while ( (line = br.readLine()) != null)
                    System.out.println(line);
                System.out.println("</ERROR>");
                int exitVal = proc.waitFor();
                System.out.println("Process exitValue: " + exitVal);
            } catch (Throwable t)
              {
                t.printStackTrace();
              }
        }
    }
