/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 
 */
public class Request implements Serializable  {

 public int RequestType;
  public int ScheduleType;
  public String Date;
  public String Time;
  public String IPAddress;
  public String ScheduleID;
  //DATA DETAILS
  public String Filename;
  public int Filesize;
  public byte[] FileData;
  public String Filepath;
  
  //ERROR
  public String ErrorExplanation;
  Vector Data = new Vector();
  Vector ColNames = new Vector();




  
  public void setScheduleNow(String IPAddress, String Filename,
          byte[] FileData,String Filepath){
    
    this.IPAddress = IPAddress;
    
    this.Filename = Filename;
    this.FileData = FileData;
     this.Filepath = Filepath;
  }
   public void setDisplaySchedule(String Filename ,byte[] FileData) {
    this.Filename = Filename;
    this.FileData = FileData;
    
  }
  



   
}
