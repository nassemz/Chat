/*
 * Notify.java
 * Created on January 19, 2007, 4:04 PM
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mainserver;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import messages.ServerMessages;

/**
 * @author Nissim Zohar
 */

public class Notify extends Thread{
    private Hashtable OnlineUsers;  // list of online users
    private String Status;          // for telling online or offline
    private String USER;            // the user name that geting online
    private DesEncrypter encrypter; // the encryption class
    
    /** Creates a new instance of Notify */
    public Notify(String status,Hashtable a,String User,DesEncrypter encrypt) {
        OnlineUsers = a;
        Status = status;
        USER = User;
        encrypter = encrypt;
    }
    
    public void run(){
        User CurrentOne;    // getting the online users reference
        Enumeration enume = OnlineUsers.keys();
        try {
            // go over all the users and notify them that there is a users that his status 
            // online or offline
            while ( enume.hasMoreElements() ) {
                String values =  ( String )  enume.nextElement(  ) ;
                if(!values.equals(USER)) {
                    CurrentOne = (User) OnlineUsers.get(values);
                    if(Status.equals(ServerMessages.ONLINEUSERS))
                        CurrentOne.sendData(ServerMessages.ADD_ONE_USER);
                    else
                        CurrentOne.sendData(ServerMessages.DELETE_ONE_USER);
                    CurrentOne.sendData(USER);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
}
