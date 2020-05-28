/*
 * ListenThread.java
 *
 * Created on January 24, 2007, 3:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mainserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import javax.swing.JTextArea;

/**
 *
 * @author Nissimzo
 */
public class ListenThread extends Thread{
    private String database_server;
    private int DBport;
    private int port;
    private Socket socket;
    private ServerSocket ss;
    private Hashtable tOnlineUsers;
    private JTextArea textArea1;
    
    
    /** Creates a new instance of ListenThread */
    public ListenThread(int DataBaseport,String DataBaseServer,int ListenPort,JTextArea text
            ,Hashtable tOnlineUsers_t) {
        DBport = DataBaseport;
        database_server = DataBaseServer;
        port = ListenPort;
        textArea1 = text;
        tOnlineUsers= tOnlineUsers_t;
        
    }
    
    public void run(){
        try{
            //System.out.println("Server Started.");
            textArea1.append("Nassim Messnger Server Started.\n");
            textArea1.append("listening... at port:");
            textArea1.setCaretPosition(textArea1.getDocument().getLength());
            // Create a socket on server
            textArea1.append(Integer.toString(port) + "\n");
            ss = new ServerSocket(port);
            // Now start accepting connections from clients in a while loop
            // The server should run in an infinite loop
            while(true) {
                textArea1.append("Server Waiting for accept new user.\n");
                textArea1.setCaretPosition(textArea1.getDocument().getLength());
                socket = ss.accept();	// accept connection from client
                User UserConnected = new User(socket,tOnlineUsers,textArea1,database_server,DBport);
                // start the Service User
                UserConnected.start();
            }	// End of while
            
        }	// End of try
        catch(Exception e) {
            //textArea1.append("Some kind of error has occurred.\n");
        }	// End of exception
    }
    
    public void stop2(){
        // my stop function that close the connection only without destroy the thread 
        // then we can destroy the thread with the ,stop function
        try {
            ss.close();
            textArea1.append("Stoped listening...\n");
            textArea1.setCaretPosition(textArea1.getDocument().getLength());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
