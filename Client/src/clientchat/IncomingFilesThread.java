/*
 * IncomingFilesThread.java
 *
 * Created on February 2, 2007, 9:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package clientchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author N-Z
 */

public class IncomingFilesThread extends Thread{
    private String OUTPUTFILENAME = "C:\\"; // the pdefult save file path
    private final int PORT= 5792;                 // the port incoming files
    private MenuUI menu;                    // a reference for the menu
    private ServerSocket srvr;
    
    /** Creates a new instance of IncomingFilesThread */
    public IncomingFilesThread(MenuUI menu_t) {
        menu = menu_t;
    }
    
    private void TryBuildServer() {
        // this function for build the server socket of the incomining file for the cilents
        // if the there is any user in the same computer after he end his application send build my
        // incoming file server socket.
        try {
            srvr = new ServerSocket(PORT); // create a server socket with the given port
        } catch (IOException ex) {
            try {
                Thread.sleep(60000); //wait one min
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TryBuildServer();
        } // create a server socket with the given port
    }
    
    public void run(){
        //the running thread
        try {
            TryBuildServer();
            while ( true ) {
                Socket socket = srvr.accept(); // accept a new connections
                RecivedFile aa = new RecivedFile(socket); // if there is a new requst then create a new recive file form and set it visible
                aa.setVisible(true);
            }
        } catch(Exception e) {
        }
        
    }
}

