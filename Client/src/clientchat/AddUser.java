/*
 * AddUser.java
 *
 * Created on January 22, 2007, 10:00 PM
 */

package clientchat;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import messages.ServerMessages;

public class AddUser extends javax.swing.JFrame {
    
    /** Creates new form AddUser */
    
    public AddUser(String server_t,String port_t) {
        initComponents();
        ServerPorttext.setText(port_t);
        ServerIPtext.setText(server_t);
        Image im = Toolkit.getDefaultToolkit().getImage("C:\\VwV\\icons\\AddUser.png");
        setIconImage(im);
        setLocationRelativeTo(null); //Display the window center it.
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UserNametext = new javax.swing.JTextField();
        Password1text = new javax.swing.JPasswordField();
        Password2text = new javax.swing.JPasswordField();
        AddUserButt = new javax.swing.JButton();
        ExitButt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ServerIPtext = new javax.swing.JTextField();
        ServerPorttext = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VwV Registeration");
        setResizable(false);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setText("Enter User Name:");

        jLabel2.setText("Password :");

        jLabel3.setText("Confirm Password:");

        UserNametext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UserNametextKeyReleased(evt);
            }
        });

        Password1text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Password1textKeyReleased(evt);
            }
        });

        Password2text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Password2textKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UserNametext, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Password1text, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Password2text, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UserNametext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Password1text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Password2text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddUserButt.setText("Add User");
        AddUserButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddUserButtMouseClicked(evt);
            }
        });

        ExitButt.setText("Exit");
        ExitButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtMouseClicked(evt);
            }
        });

        jLabel4.setText("Status : ");

        jLabel5.setText("Type your new user and password");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jLabel5))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server Connection", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel6.setText("Server IP:");

        jLabel7.setText("Server Port:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(ServerIPtext, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(ServerPorttext, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ServerIPtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ServerPorttext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddUserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(ExitButt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddUserButt)
                    .addComponent(ExitButt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void Password2textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Password2textKeyReleased
        // get the press key and chek it if it's a ESCAPE or ENTER key
        int code = evt.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE)
            GoodBye(); // if it's ESCAPE call the goodbye function'
        if(code == KeyEvent.VK_ENTER)
            AddNewUser(); // if it's ENTER call the AddNewUser function
    }//GEN-LAST:event_Password2textKeyReleased
    
    private void Password1textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Password1textKeyReleased
        // get the press key and chek it if it's a ESCAPE or ENTER key
        int code = evt.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE)
            GoodBye();// if it's ESCAPE call the goodbye function'
        if(code == KeyEvent.VK_ENTER)
            AddNewUser();// if it's ENTER call the AddNewUser function
    }//GEN-LAST:event_Password1textKeyReleased
    
    private void UserNametextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserNametextKeyReleased
        // get the press key and chek it if it's a ESCAPE or ENTER key
        int code = evt.getKeyCode();
        if(code == KeyEvent.VK_ESCAPE)
            GoodBye();// if it's ESCAPE call the goodbye function'
        if(code == KeyEvent.VK_ENTER)
            AddNewUser();// if it's ENTER call the AddNewUser function
    }//GEN-LAST:event_UserNametextKeyReleased
    
    private void ExitButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtMouseClicked
// TODO add your handling code here:
        GoodBye();
    }//GEN-LAST:event_ExitButtMouseClicked
    
    private void AddUserButtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddUserButtMouseClicked
// TODO add your handling code here:
        AddNewUser();
        //closeCoUserNametext
    }//GEN-LAST:event_AddUserButtMouseClicked
    
    private void AddNewUser()
    {
        try {
            if (UserNametext.getText().length() > ServerMessages.DefultMaxIndex ||
                    UserNametext.getText().length() == 0 ) {
                JOptionPane.showMessageDialog(this, "User Name must be low than " + ServerMessages.DefultMaxIndex + " chars","Add User",JOptionPane.CANCEL_OPTION);
                return;
            }
            
            if((!Password1text.getText().equals(Password2text.getText())) || (Password1text.getText().length() == 0)) {
                JOptionPane.showMessageDialog(this, "Wrong Password","Add User",JOptionPane.CANCEL_OPTION);
                return;
            }
            jLabel5.setText("Connecting...");
            if(!connect()){
                jLabel5.setText("Fail to connect to server...");
                return;}
            jLabel5.setText("Connected...");
            if (!SendKey()) {
                closeConnection();
                return;
            }
            jLabel5.setText("Checking User Name And Password.");
            sendData(ServerMessages.ADD_USER);
            sendData(UserNametext.getText());
            sendData(Password1text.getText());
            String incoming;
            
            incoming = RecivedString();            
            if(incoming.equals(ServerMessages.USER_IS_ADDED)) {
                MenuUI mainmenu = new MenuUI(UserNametext.getText(),input,output,encrypter);
                this.dispose();
            } else{
                if(incoming.equals(ServerMessages.CONNECTION_FAIL)) {
                    JOptionPane.showMessageDialog(this, "Conniction Fail with Data Base Server Try latter..","Adding User...",JOptionPane.CANCEL_OPTION);
                }
                else
                    JOptionPane.showMessageDialog(this, "User Already Exist","Adding User...",JOptionPane.CANCEL_OPTION);
                return;
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeConnection() {
        // close all of the connections
        try {
            if (!socket.isClosed())
                socket.close();
            input.close();
            output.close();
        } catch (Exception e) {
        }
    }
    
    public boolean connect() {
        /*****************************************************************
         * This method connect to the server.
         ***************************************************************** */
        try {
            OutputStream out;
            InputStream in;
            Server = ServerIPtext.getText();
            try{ port = Integer.parseInt(ServerPorttext.getText());  } catch (NumberFormatException e){ port = ServerMessages.DEFULTPORT;  }
            socket = new Socket(Server, port);
            out = socket.getOutputStream();
            output = new ObjectOutputStream(out);
            in = socket.getInputStream();
            input = new ObjectInputStream(in);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cannot Connect to Server...","Connecting...",JOptionPane.CANCEL_OPTION);
            return false;
        }
        return true;
    }
    
    private boolean SendKey() {
        try{
            // Generate a temporary key. In practice, you would save this key.
            // See also e464 Encrypting with DES Using a Pass Phrase.
            SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            //sending the key to the user
            output.writeObject(key);
            encrypter = new DesEncrypter(key); // create the encyrpt class
            return true;
        }catch(Exception e) {
            return false;
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
        
    private void GoodBye() {
        closeConnection();
        System.exit(0);
    }
    
    private Socket socket = null;
    private ObjectInputStream input;    //input for input data from the server
    private ObjectOutputStream output;  //output for output data from the server
    private int port;                   // the server port
    private String Server;              // the server ip
    private DesEncrypter encrypter;     //the encrypt class for encryption and decryption incomming and outcomming data
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddUserButt;
    private javax.swing.JButton ExitButt;
    private javax.swing.JPasswordField Password1text;
    private javax.swing.JPasswordField Password2text;
    private javax.swing.JTextField ServerIPtext;
    private javax.swing.JTextField ServerPorttext;
    private javax.swing.JTextField UserNametext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
    
}
