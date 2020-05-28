/*
 * InputThread.java
 *
 * Created on January 16, 2007, 4:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package clientchat;

import java.io.*;
import javax.swing.JOptionPane;
import messages.ServerMessages;

/**
 * @author Nissim Zohar
 */

public class InputThread extends Thread {
    private ObjectInputStream input;        //input for input data from the server
    private ObjectOutputStream output;      //output for output data from the server
    private String userName;                //Client UserName
    private MenuUI menu;                    // a refernce for the menu
    private DesEncrypter encrypter;         //the encrypt class for encryption and decryption incomming and outcomming data
    
    /** Creates a new instance of InputThread */
    public InputThread(String userName_t,ObjectInputStream input_t,ObjectOutputStream output_t,DesEncrypter encrypt,MenuUI men) {
        userName = userName_t;
        input = input_t;
        output = output_t;
        encrypter = encrypt;
        menu = men;
    }
    
      
    public String RecivedString() throws IOException, ClassNotFoundException{
        /*******************************************************************
         * The RecivedString() method in the client sends data to the server
         ***************************************************************** */
        //output.println( str );
        String rec = (String) input.readObject();
        return encrypter.decrypt(rec);
    }
    
    public void run(){
        String temp;
        while(true) {
            try {
                // recive a new mesge then check this function refere to wich funvtion from this list :
                // 1) menu.receivedUsers();
                // 2) menu.AddOneUser();
                // 3) menu.SendFile();
                // 4) menu.DeleteOneUser();
                // 5) menu.receivedInput(temp);
                
                temp = RecivedString();
                if(temp.equals(ServerMessages.ONLINEUSERS)) {
                    menu.receivedUsers();
                    continue;
                }
                if(temp.equals(ServerMessages.ADD_ONE_USER)) {
                    menu.AddOneUser();
                    continue;
                }
                 if(temp.equals(ServerMessages.SEND_FILE)) {
                    menu.SendFile();
                    continue;
                }
                if(temp.equals(ServerMessages.DELETE_ONE_USER)) {
                    menu.DeleteOneUser();
                    continue;
                }
                menu.receivedInput(temp);
            } catch (IOException e){
                JOptionPane.showMessageDialog(menu, ServerMessages.ConFailWhileCon ,ServerMessages.ConFaill,JOptionPane.CANCEL_OPTION);
                System.exit(0);
            } catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(menu, ServerMessages.ConFailWhileCon ,ServerMessages.ConFaill,JOptionPane.CANCEL_OPTION);
                System.exit(0);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
