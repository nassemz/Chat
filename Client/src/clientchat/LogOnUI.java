/*
 * LogOnUI.java
 * @author  N-Z
 * Created on January 13, 2007, 5:52 PM
 */

package clientchat;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.crypto.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.net.*;
import messages.ServerMessages;

public class LogOnUI extends javax.swing.JFrame{
    
    private Socket socket = null;           //the socket
    private ObjectInputStream input;        //input for input data from the server
    private ObjectOutputStream output;      //output for output data from the server
    private DesEncrypter encrypter;         //the encrypt class for encryption and decryption incomming and outcomming data
    private String userName;                // the client User Name
    
    public LogOnUI() {
        /** Creates new form LogOnUI */
        super( "Client" ) ;
        initComponents();
        Image im = Toolkit.getDefaultToolkit().getImage("C:\\VwV\\icons\\Logon.png");
        setIconImage(im);
        setLocationRelativeTo(null); //Display the window center it.
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UserNametext = new javax.swing.JTextField();
        Passwordtext = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ServerIPtext = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ServerPorttext = new javax.swing.JTextField();
        ConnectButt = new javax.swing.JButton();
        ExitButt = new javax.swing.JButton();
        RegisterButt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VwV LogOn User's");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setText("User Name:");

        jLabel2.setText("Password:");

        UserNametext.setText("nassim");
        UserNametext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserNametextKeyReleased(evt);
            }
        });

        Passwordtext.setText("nassim1");
        Passwordtext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PasswordtextKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Passwordtext, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(UserNametext, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UserNametext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Passwordtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel3.setText("Server IP:");

        ServerIPtext.setText("nissimzo.myvnc.com");

        jLabel4.setText("Server Port:");

        ServerPorttext.setText("55555");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ServerIPtext, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addComponent(ServerPorttext, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ServerIPtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(ServerPorttext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        ConnectButt.setText("Connect");
        ConnectButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectButtMouseClicked(evt);
            }
        });

        ExitButt.setText("Exit");
        ExitButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtMouseClicked(evt);
            }
        });

        RegisterButt.setText("Register new User");
        RegisterButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterButtMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ConnectButt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RegisterButt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExitButt))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ConnectButt, ExitButt});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConnectButt)
                    .addComponent(ExitButt)
                    .addComponent(RegisterButt))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void PasswordtextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordtextKeyReleased
// TODO add your handling code here:
        // TODO add your handling code here:
        int code = evt.getKeyCode();
//Key pressed is the Enter key. SendChatMessage
        if(code == KeyEvent.VK_ENTER)
            LogON();
    }//GEN-LAST:event_PasswordtextKeyReleased
    
    private void UserNametextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNametextKeyReleased
// TODO add your handling code here:
        int code = evt.getKeyCode();
//Key pressed is the Enter key. SendChatMessage
        if(code == KeyEvent.VK_ENTER)
            LogON();
    }//GEN-LAST:event_UserNametextKeyReleased
    
    private void RegisterButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtMouseClicked
        // create a new user
        AddUser user = new AddUser(ServerIPtext.getText(),ServerPorttext.getText());
//        this.setVisible(false);
        user.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegisterButtMouseClicked
    
    private void ConnectButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectButtMouseClicked
        LogON();
    }//GEN-LAST:event_ConnectButtMouseClicked
    
    private void ExitButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtMouseClicked
// TODO add your handling code here:
        closeConnection() ;
        System.exit(0);
    }//GEN-LAST:event_ExitButtMouseClicked
    
    private void LogON(){
        // check if the user type a user name and password or not?
        if((UserNametext.getText().equals("")) || (Passwordtext.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "!! Fill all the fileds !!","User Loged On...",JOptionPane.CANCEL_OPTION);
            return;}
        if (!connect())         //then connect to the server
            return;
        if (!SendKey()){        // genarte a key and send it to the server!!
            closeConnection();  // if cont creat a key or send it then close the connection
            return;
        }
        if (CheckUser()){       //then cheak user
            MenuUI mainmenu = new MenuUI(userName,input,output,encrypter);//and create the menu
            this.dispose();
        } else
            closeConnection();
    }
    
    public static void main(String[] args) {
        final LogOnUI client = new LogOnUI() ;
        client.setVisible(true);
        client.addWindowListener(
                new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                client.closeConnection();}
        });}
    
    public boolean connect() {
        /*****************************************************************
         * This method connect to the server.
         ***************************************************************** */
        String server;
        int port;
        try {
            try{ port = Integer.parseInt(ServerPorttext.getText());  } catch (NumberFormatException e){ port = ServerMessages.DEFULTPORT;  }
            server = ServerIPtext.getText(); // get the server host
            socket = new Socket(server, port);  // create the socket
            OutputStream out = socket.getOutputStream();
            output = new ObjectOutputStream(out);       // create the input stream
            InputStream in = socket.getInputStream();
            input = new ObjectInputStream(in);          // create the output stream
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot Connect to Server...","Connecting...",JOptionPane.CANCEL_OPTION);
            return false;
        }
        return true;
    }
    
    public  void closeConnection() {
        // function for closing all the connections
        try {
            if (!socket.isClosed())
                socket.close();
            output.close();
            input.close();
        } catch (Exception e) {
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
         * The RecivedString() method in the client sends data to the server
         ***************************************************************** */
        String rec = (String) input.readObject();
        return encrypter.decrypt(rec);
    }
    
    public boolean CheckUser(){
        String answer;
        try{
            // send the user and the password to check it and wait for the answer from the server
            sendData(ServerMessages.CHECK_PASSWORD);
            sendData(UserNametext.getText());
            sendData(Passwordtext.getText());
            answer = RecivedString();
        } catch(Exception e)  { return false; }
        
        if ( answer.equals(ServerMessages.GRANT_ACCESS)) {
            userName = new String(UserNametext.getText());
            return true;
        }
        if ( answer.equals(ServerMessages.LOGED_ON)) {
            JOptionPane.showMessageDialog(this, "User Loged On...","Can't Access Logon...",JOptionPane.CANCEL_OPTION);
            return false;
        }
        if ( answer.equals(ServerMessages.CONNECTION_FAIL)) {
            JOptionPane.showMessageDialog(this, "Connection to server failed","Connection Failed...",JOptionPane.CANCEL_OPTION);
            return false;
        }
        JOptionPane.showMessageDialog(this, "User Name not exist","Can't Access Logon...",JOptionPane.CANCEL_OPTION);
        return false;
    }
    
    private boolean SendKey() {
        try{
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            output.writeObject(key);//sending the key to the user
            encrypter = new DesEncrypter(key);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectButt;
    private javax.swing.JButton ExitButt;
    private javax.swing.JPasswordField Passwordtext;
    private javax.swing.JButton RegisterButt;
    private javax.swing.JTextField ServerIPtext;
    private javax.swing.JTextField ServerPorttext;
    private javax.swing.JTextField UserNametext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
