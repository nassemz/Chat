/*
 * User.java
 *
 * Created on January 12, 2007, 11:56 PM
 * @author Nissim Zohar
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mainserver;
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JTextArea;
import messages.ServerMessages;

public class User extends Thread {
    
    private Hashtable OnlineUsers;          // list of online users
    private JTextArea textArea1;            // jtextare for telling status
    private String Data;                    // a varible that uses by most of the function
    private Date now;                       // the current Date
    
    private String strUserName;             // the user name
    private ObjectInputStream input;        // the input user socket stream
    public  ObjectOutputStream output;      // the output user socket stream
    private DesEncrypter encrypter;         // the encryption class
    private Socket socket;                  // the user socket
    
    private String DB_Server;               // the DataBase server ip
    private int DB_port;                    // the DataBase server port
    private Socket DB_socket;               // the Databse server socket
    private DesEncrypter DBencrypter;       // the DataBase encryption class
    private ObjectInputStream DBinput;      // the DataBase input socket stream
    public  ObjectOutputStream DBoutput;    // the DataBase output socket stream
    
    /** Creates a new instance of User */
    public User(Socket s,Hashtable onuser,JTextArea text,String server,int port_db) throws IOException{
        socket = s;
        DB_port = port_db;
        DB_Server = server;
        OnlineUsers = onuser;
        textArea1 = text;
        InputStream in;
        OutputStream out;
        // to get data to and from server
        out = socket.getOutputStream();
        output = new ObjectOutputStream(out);
        in = socket.getInputStream();
        input = new ObjectInputStream(in);
    }
    
// -------------------run------------------------------
    public void run(){
        try{
            // get the function that user want to execute ?
            // Check Password
            // Add New use?
            if(!GetKey())
                return;
            // recive the function that wanna execute!!
            String function = RecivedString();
            // read user name from the client and store in table
            // in the format username + User
            strUserName = RecivedString();
            //check if the user loged on
            if((OnlineUsers.containsKey(strUserName)) &&
                    ((function.equals(ServerMessages.ADD_USER))||(function.equals(ServerMessages.CHECK_PASSWORD)))) {
                sendData(ServerMessages.LOGED_ON);
                return;
            }
            // server must connect to the database server and ask about the user
            DB_socket = new Socket(DB_Server, DB_port);
            OutputStream out = DB_socket.getOutputStream();
            DBoutput = new ObjectOutputStream(out);
            InputStream in = DB_socket.getInputStream();
            DBinput = new ObjectInputStream(in);
            // genarte the databse key and send it
            if(!GenerateSendKeyDB()) {
                // if can't genarte or send the key then send to the user taht the function failed
                sendData(ServerMessages.CONNECTION_FAIL);
                return;
            }
            sendDBData(ServerMessages.ADMINNAME); //Admin
            sendDBData(ServerMessages.ADMINPASSWORD); //admin
            // wait for reply if success or not
            Data = RecivedDBString();
            if(!Data.equals(ServerMessages.GRANT_ACCESS)) {
                //if not send that fail ...
                sendData(ServerMessages.CONNECTION_FAIL);
                sendDBData(ServerMessages.CANCEL_CONNECTION);
                return;
            }
            if(function.equals(ServerMessages.DELETE_USER)){
                DeleteUser(strUserName);
                return;
            }
            // getting the password from the user
            String strPassword = RecivedString();
            if(function.equals(ServerMessages.ADD_USER))
                if(!AddUser(strUserName,strPassword))
                    return;
            
            if(function.equals(ServerMessages.CHECK_PASSWORD))
                if(!LogonUser(strUserName,strPassword))
                    return;
            // send to the user all the online users names
            Enumeration enume = OnlineUsers.keys();
            // send that you wanna send him online users ServerMessages.ONLINEUSERS
            sendData(ServerMessages.ONLINEUSERS);
            while ( enume.hasMoreElements() )
                // while there is more users send it to the user
            {
                String values =  ( String )  enume.nextElement(  ) ;
                sendData(values);
            }
            // send that this is the end of online users ServerMessages.END_ONLINEUSERS
            sendData(ServerMessages.END_ONLINEUSERS);
            //if success then notify all users that this user was loged in
            // create a thread to contenue working
            Notify newconn = new Notify(ServerMessages.ONLINEUSERS,OnlineUsers,strUserName,encrypter);
            newconn.start();
            //and if success and it to the online users hashtable
            OnlineUsers.put(strUserName, this);
            now = new Date();
            textArea1.append("Username: " + strUserName + " connected " +
                    socket.getInetAddress() +
                    " At : [" + DateFormat.getTimeInstance().format(now) + "]\n");
            textArea1.setCaretPosition(textArea1.getDocument().getLength());
            while(true) {
                Data = (String) RecivedString(); //waiting a requsts from the user every time
                SendFileMsg(Data,output); // if there is a requst then do it
            }
        } catch(Exception e) {
            now = new Date(); //calculate a new Date
            //in the end of connection remove him from the online useres hashtable
            try{
                OnlineUsers.remove(strUserName); // remove the user from the list of online users
                //notify all users that this user was loged out
                // create a thread to contenue working
                Notify newconn = new Notify(ServerMessages.OFFLINEUSERS,OnlineUsers,strUserName,encrypter);
                newconn.start();
            }catch(NullPointerException ex){}
            // print in the server montor that User Disconnected
            textArea1.append("Username: " + strUserName + " Disconnected " +
                    socket.getInetAddress() +
                    " At : [" + DateFormat.getTimeInstance().format(now) + "]\n");
            textArea1.setCaretPosition(textArea1.getDocument().getLength());
            try {
                sendData(ServerMessages.CONNECTION_FAIL);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            CloseConnetions();
        }// End of exception
    }
    
    private boolean GetKey() {
        try{
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            Object received = input.readObject();
            encrypter = new DesEncrypter((SecretKey) received);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    private boolean AddUser(String strUserName, String strPassword) throws IOException, ClassNotFoundException {
        //TELL THE DATABASE THAT WE WANNA ADD USER NAME
        sendDBData(ServerMessages.ADD_USER);
        // SEND USER NAME AND PASSWORD
        sendDBData(strUserName);
        sendDBData(strPassword);
        //get the answer
        Data = RecivedDBString();
        sendData(Data);
        if(!Data.equals(ServerMessages.USER_IS_ADDED))
            return false;
        return true;
    }
    
    private boolean LogonUser(String strUserName, String strPassword) throws IOException, ClassNotFoundException {
        //TELL THE DATABASE THAT WE WANNA CHECK USER NAME AND PASSWORD
        sendDBData(ServerMessages.CHECK_PASSWORD);
        // SEND USER NAME AND PASSWORD
        sendDBData(strUserName);
        sendDBData(strPassword);
        //get the answer
        Data = RecivedDBString();
        sendData(Data);
        if(!Data.equals(ServerMessages.GRANT_ACCESS))
            return false;
        return true;
    }
    
    private boolean DeleteUser(String strUserName) throws IOException, ClassNotFoundException {
        //TELL THE DATABASE THAT WE WANNA CHECK USER NAME AND PASSWORD
        sendDBData(ServerMessages.CHECK_PASSWORD);
        // SEND USER NAME AND PASSWORD
        sendDBData(strUserName);
        //get the answer
        Data = RecivedDBString();
        
        sendData(Data);
        if(!Data.equals(ServerMessages.GRANT_ACCESS))
            return false;
        return true;
    }
    
    private void SendFileMsg(String temp,ObjectOutputStream output){
        if (temp.length() < ServerMessages.DefultMaxIndex)
            return;
        // send the msg to the user that his name in the first of the temp string
        String Tempo = GetReciver(temp,ServerMessages.DefultMaxIndex); // get the Reciver name
        if(Tempo.equals("Empty")) //check if there is a Reciver
            return;
        if(Tempo.equals(ServerMessages.GENERAL_CHANNEL)) //if the reciver is a general channel
        {
            ChannelMsg(temp);
            return;
        }
        User Reciver = (User) OnlineUsers.get(Tempo); //check the Reciver if exists ?
        if (Reciver == null)
            return;
        
        if(temp.substring(ServerMessages.DefultMaxIndex).equals(ServerMessages.SEND_FILE)) {
            try {
                String DataforFile = Reciver.strUserName;
                int length = ServerMessages.DefultMaxIndex-DataforFile.length(); //get number of spaces have to add after the user name
                if (length < 0) //if the user name more than the DefultMaxIndex then return
                    return;
                for(int i=length ; i!=0 ; i--) //fill the new Name with spaces like : "nassem_z@hotmail.com         ...-> until DefultMaxIndex"
                    DataforFile = DataforFile + " ";
                
                sendData(ServerMessages.SEND_FILE); //sends the function
                DataforFile = DataforFile + Reciver.GetIP();
                sendData(DataforFile); // sends the IP address and user name
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        }
        //send to the Reciver the msg after we found it with the sender name in the first 30 bytes
        Reciver.GetUserMsg(strUserName,temp.substring(ServerMessages.DefultMaxIndex));
    }
    
    public String GetIP(){
        String aa = new String(this.socket.getInetAddress().toString());
        return aa;
    }
    
    public void GetUserMsg(String SenderName,String Data){
        int length = ServerMessages.DefultMaxIndex-SenderName.length(); //get number of spaces have to add after the user name
        String Name = new String(SenderName); //copy the user name
        if (length < 0) //if the user name more than the DefultMaxIndex then return
            return;
        for(int i=length ; i!=0 ; i--) //fill the new Name with spaces like : "nassem_z@hotmail.com         ...-> until DefultMaxIndex"
            Name = Name + " ";
        Name = Name + Data;
        try {
            sendData(Name); // then Write it to the user;
        } catch (IOException ex) {
            ex.printStackTrace();
        } // then Write it to the user;
    }
    
    private String GetReciver(String temp,int index){
        // retrn the Sender Name it's taken from the string that the user sent
        // if there if no user will return the string "Empty"
        for(int i = index ; i!=-1 ; i--)
            if(!temp.substring(i-1,i).equals(" "))
                return temp.substring(0,i);
        return "Empty";
    }
    
    private void ChannelMsg(String temp) {
        // String Message = GetChannelData(temp);
        // String Sender = GetChannelSender(temp);
        // if(Message.equals(ServerMessages.DEFAULTEMPTY))
        //     return;
        User CurrentOne;
        Enumeration enume = OnlineUsers.keys();
        while ( enume.hasMoreElements() ) {
            String values =  ( String )  enume.nextElement(  ) ;
            if(!values.equals(strUserName)) {
                CurrentOne = (User) OnlineUsers.get(values);
                try {
                    CurrentOne.sendData(temp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private String GetChannelData(String temp) {
        try{
            return temp.substring(ServerMessages.DefultMaxIndex);
        } catch(Exception ex) {
            return ServerMessages.DEFAULTEMPTY;
        }
    }
    
    private String GetChannelSender(String temp) {
        String Temp = temp.substring(ServerMessages.DefultMaxIndex);
        return GetReciver(Temp,ServerMessages.DefultMaxIndex);
    }
    
    public void sendData(String str) throws IOException {
        /*******************************************************************
         * The sendData() method in the client sends data to the server
         ***************************************************************** */
        //output.println( str );
        output.writeObject(encrypter.encrypt(str));//sending to the user a string as object
    }
    
    public String RecivedString() throws IOException, ClassNotFoundException{
        /*******************************************************************
         * The RecivedString() method in the client Recived data from server
         ***************************************************************** */
        //output.println( str );
        String rec = (String) input.readObject();
        return encrypter.decrypt(rec);
    }
    
    public void sendDBData(String str) throws IOException {
        /*******************************************************************
         * The sendData() method in the client sends data to the server
         ***************************************************************** */
        //output.println( str );
        DBoutput.writeObject(DBencrypter.encrypt(str));//sending to the user a string as object
    }
    
    public String RecivedDBString() throws IOException, ClassNotFoundException{
        /*******************************************************************
         * The RecivedString() method in the client Recived data from server
         ***************************************************************** */
        //output.println( str );
        String rec = (String) DBinput.readObject();
        return DBencrypter.decrypt(rec);
    }
    
    private boolean GenerateSendKeyDB() {
        try{
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            DBoutput.writeObject(key);//sending the key to the user
            DBencrypter = new DesEncrypter(key);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    private void CloseConnetions(){
        try {
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            DB_socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            DBinput.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            DBoutput.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}