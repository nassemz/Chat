/*
 * ListenThread.java
 *
 * Created on January 24, 2007, 3:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package databasesevrer;
import java.awt.TextArea;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Nissimzo
 */

public class ListenThread extends Thread{
    
    private int port;           // port of the DataBase Listening
    private Socket socket;      // the socket fo the accepting users
    private ServerSocket ss;    // The Server Socket for listening
    private TextArea textArea1; // The TextArea that writing the msgs
    private JTextArea jtextArea1;   // the JtextArea for printing the thread status
    private DataBase ChatDB;    // The DataBase Class for working with DataBase
    
    // Creates a new instance of ListenThread
    public ListenThread(JTextArea text,int port_t,DataBase DB) {
        jtextArea1 = text;
        port = port_t;
        ChatDB = DB;
    }
    
    // the run function thats called in .start() function
    public void run(){
        try{
            // telling that it's starts
            jtextArea1.append("Nassim Data Base Server Started.\n");
            jtextArea1.append("listening... at port:");
            jtextArea1.append(Integer.toString(port) + "\n");
            // set the scroll bar at the end "auto scroll"
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
            //create a new serversocket
            ss = new ServerSocket(port);
            // ---------------------------------------------------------------
            // Now start accepting connections from clients in a while loop
            // The server should run in an infinite loop to accept a new requsts
            while(true) {
                // telling that it's starts
                jtextArea1.append("Server Waiting for accept new Requst.\n");
                jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
                Socket socket = ss.accept();	// accept connection from client
                // create a thread to allow simultaneous connections
                RequstServer ServerR = new RequstServer(socket,jtextArea1,ChatDB);
                // start the Service User
                ServerR.start();
            }	// End of while
        }	// End of try
        catch(Exception e) {
            jtextArea1.append("Some kind of error has occurred.\n");
        }	// End of exception
    }
    
    public void stop2(){
        // my stop function for closing the listen mode
        // it's called for stoping the listen mode and to tell the status that it's stoped
        try {
            //close the socket
            ss.close();
            // tell the status
            jtextArea1.append("Stoped listening...\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    
}
