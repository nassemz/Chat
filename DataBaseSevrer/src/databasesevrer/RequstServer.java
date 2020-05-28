/*
 * RequstServer.java
 *
 * Created on January 19, 2007, 6:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package databasesevrer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.swing.JTextArea;
import messages.ServerMessages;

/**
 *
 * @author N-Z
 */

public class RequstServer extends Thread{
    
    /************************
     *      Functions       *
     ************************/
    
    public RequstServer(Socket sock,JTextArea text,DataBase DB) throws IOException {
        socket = sock;                              // the requst socket
        jtextArea1 = text;
        // to get data to and from server
        OutputStream out = socket.getOutputStream();
        output = new ObjectOutputStream(out);       //create a input stream
        InputStream in = socket.getInputStream();
        input = new ObjectInputStream(in);          //create a output stream
        ChatDB = DB;                                // the DataBase refernce
    }
    
    public void run(){
        try {
            now = new Date();   // get current Date
            // tell the stuts of the current function to the
            jtextArea1.append("new Connection from Chat Server from :" + socket.getInetAddress() +
                    " At : [" + DateFormat.getTimeInstance().format(now) + "]\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength()); // set the scroll bar to the end
            if(!GetKey()) // get the encryption key
                return;
            String strUserName = RecivedString();   //get the user name of the admin
            String strPassword = RecivedString();   //get the password of the admin
            String Reply = ChatDB.isValidPasswrodAdmin(strUserName,strPassword); //check if the password valid
            sendData(Reply);
            if(!Reply.equals(ServerMessages.GRANT_ACCESS)) { // if access then tell the requsted server
                return;
            }
            String Data = RecivedString();
            if(Data.equals(ServerMessages.ADD_USER)) {
                this.AddUser();
                return;
            }
            if(Data.equals(ServerMessages.DELETE_USER)) {
                this.DeleteUser();
                return;
            }
            if(Data.equals(ServerMessages.LIST_OF_USER)) {
                this.ListOFUsers();
                return;
            }
            if(Data.equals(ServerMessages.CHECK_USER)) {
                this.CheckUser();
                return;
            }
            if(Data.equals(ServerMessages.CHECK_PASSWORD)) {
                this.CheckPassword();
                return;
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean GetKey() {
        try{
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            // SecretKey key ;
            Object received = input.readObject();
            encrypter = new DesEncrypter((SecretKey) received);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    private void AddUser() {
        try {
            // for adding a new user we have to recive the username and the password
            String UserName = RecivedString();
            String Password = RecivedString();
            String Temp;
            // then will try to insert it to the database as new user
            // the insert function reply to Temp if success or not
            Temp = ChatDB.InsertUser(UserName, Password);
            now = new Date();
            jtextArea1.append("Requst To add User : " + UserName + " At: ["
                    + DateFormat.getTimeInstance().format(now) + "] " +
                    "Answer : "+ Temp + "\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
            // sending the reply answer to the MainServer
            sendData(Temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void DeleteUser() {
        try {
            //for deleting user have to get the username from the main server
            String UserName = RecivedString();
            String Temp;
            // then will try to delete it
            // the deleteuser function reply to Temp if success or not
            Temp = ChatDB.deleteUser(UserName);
            now = new Date();
            jtextArea1.append("Requst To Delete User : " + UserName + " At: ["
                    + DateFormat.getTimeInstance().format(now) + "] " +
                    "Answer : "+ Temp + "\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
            // sending the reply answer to the MainServer
            sendData(Temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void CheckUser() {
        try {
            //for check user have to get the username from the main server
            String UserName = RecivedString();
            String Temp;
            // and reply to the mainserver the answer if valid or not !!
            Temp = ChatDB.isValidUser(UserName);
            now = new Date();
            jtextArea1.append("Requst To Check User : " + UserName + " At: ["
                    + DateFormat.getTimeInstance().format(now) + "] " +
                    "Answer : "+ Temp + "\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
            sendData(Temp);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void CheckPassword() {
        try {
            // for check password we have to recive the username and the password
            String UserName = RecivedString();
            String Password = RecivedString();
            String Temp;
            // and reply to the mainserver the answer
            Temp = ChatDB.isValidPasswrodUser(UserName, Password);
            now = new Date();
            jtextArea1.append("Requst To Check User Password: " + UserName + " At: ["
                    + DateFormat.getTimeInstance().format(now) + "] " +
                    "Answer : "+ Temp + "\n");
            jtextArea1.setCaretPosition(jtextArea1.getDocument().getLength());
            sendData(Temp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendData(String str) throws IOException {
        /*******************************************************************
         * The sendData() method in the client sends data to the server
         ***************************************************************** */
        output.writeObject(encrypter.encrypt(str));//sending to the user a string as object
    }
    
    public String RecivedString() throws IOException, ClassNotFoundException{
        /*******************************************************************
         * The RecivedString() method in the client Recived data from server
         ***************************************************************** */
        Object rec = input.readObject();
        String incomingmsg = encrypter.decrypt((String) rec);
        return incomingmsg;
    }
    
    private void ListOFUsers() {
        // send to the mainserver list of all users
        try {
            ChatDB.SendAllUsers(output,encrypter);
            sendData(ServerMessages.END_OF_USERS);
        } catch (Exception ex) {
            try {
                sendData(ServerMessages.END_OF_USERS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /************************
     *      Varibles        *
     ************************/
    
    private Socket socket;              // thye requst sockect
    private JTextArea jtextArea1;       // the textarea that writing there the stuts of the conniction
    private Date now;                   // a refernce for a current Date & Time
    private DataBase ChatDB;            // a refernce for DataBase
    private DesEncrypter encrypter;     //the encrypt class for encryption and decryption incomming and outcomming data
    private ObjectInputStream input;        //input for input data from the server
    private ObjectOutputStream output;      //output for output data from the server
    
}
