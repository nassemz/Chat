/*
 * SendFile.java
 *
 * Created on February 3, 2007, 3:53 PM
 */

package clientchat;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author  Nissimzo
 */
public class SendFile extends javax.swing.JFrame {
    JFileChooser fc;
    private final String newline = "\n";
    private String HOST;
    private Socket socket=null;
    private BufferedOutputStream out;
    private String UserName;
    private String Reciver;
    
    /** Creates new form SendFile */
    public SendFile(String Reciver_t,String IP_t,String UserName_t) {
        super("Sending File");
        initComponents();
        FileNameLabel.setText(Reciver_t);
        Reciver = Reciver_t;
        HOST = IP_t;
        UserName = UserName_t;
        HOST = HOST.substring(1);
        //Create a file chooser
        fc = new JFileChooser();
        jLabel2.setText("Waiting for selecting file.");
        Image im = Toolkit.getDefaultToolkit().getImage("C:\\VwV\\icons\\File.png");
        setIconImage(im);
        setLocationRelativeTo(null); //Display the window center it.
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SelectFileButt = new javax.swing.JButton();
        CancelButt = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextField1 = new javax.swing.JTextField();
        FileNameLabel = new javax.swing.JLabel();
        CloseCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setText("Select file for sending to :");

        jLabel2.setText("jLabel2");

        SelectFileButt.setText("Select File");
        SelectFileButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SelectFileButtMouseReleased(evt);
            }
        });

        CancelButt.setText("Cancel");
        CancelButt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CancelButtMouseReleased(evt);
            }
        });

        jTextField1.setEditable(false);

        FileNameLabel.setText("jLabel3");

        CloseCheckBox.setText("Close this dialog box when download completes");
        CloseCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        CloseCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FileNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CancelButt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SelectFileButt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)))
                    .addComponent(CloseCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(SelectFileButt)
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(CancelButt)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(FileNameLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(CloseCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void CancelButtMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelButtMouseReleased
// TODO add your handling code here:
        CloseConnection();
        this.dispose();
    }//GEN-LAST:event_CancelButtMouseReleased
    
    private void SelectFileButtMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectFileButtMouseReleased
// TODO add your handling code here:
        if(jProgressBar1.getValue() != jProgressBar1.getMinimum()) {
            JOptionPane.showMessageDialog(this,"Sending File Please Wait...","Sending...",JOptionPane.CANCEL_OPTION);
            return;
        }
        int returnVal = fc.showOpenDialog(this);
        File file;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            //This is where a real application would open the file.
            jTextField1.setText(file.getPath());
            jLabel2.setText("Waiting to connect...");
        } else {
            jTextField1.setText("");
            jLabel2.setText("Open command cancelled by user.");
            return;
        }
        
        int leng = (int)file.length();
        Client_socket client = new Client_socket(file.getPath().toString(),file.getName()
        ,file.length(),HOST,UserName,this);
        jProgressBar1.setMaximum(leng);
        jProgressBar1.setMinimum(0);
        client.start();
    }//GEN-LAST:event_SelectFileButtMouseReleased
    
    private void CloseConnection() {
        if (socket == null)
            return;
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    class Client_socket extends Thread{
       
        private int PORT    = 5792;
        private String HOST;
        private String filename;
        private String fname;
        private long SizeOfFile;
        private String UserName;
        private SendFile sendFile;
        
        public Client_socket( String filename_t,String fname_t ,long sizeoffile_T
                ,String ip,String user,SendFile sf) {
            filename = filename_t;
            sendFile = sf;
            UserName = user;
            fname = fname_t;
            SizeOfFile = sizeoffile_T; //size of the file
            HOST = ip; //the reciver ip
        }
        
        public void run(){
            try {
                socket = new Socket(HOST, PORT);
                FileInputStream fis = new FileInputStream(filename);
                BufferedInputStream in = new BufferedInputStream(fis);
                out = new BufferedOutputStream( socket.getOutputStream() );
                
                //BufferedInputStream input = new BufferedInputStream( socket.getInputStream() );
                
                sendFile.jLabel2.setText("Start Sending ...");
                
                fname=fname+"}"; // "}" to say that end of file
                byte[] name=fname.getBytes(); //sending the file name
                for(int i=0;i<name.length;i++)
                    out.write(name[i]);
                
                String user = new String(UserName);
                user=user+"}";  // "}" to say that end of file
                name = user.getBytes(); //sending the user name
                for(int i=0;i<name.length;i++)
                    out.write(name[i]);
                
                String str = (new Long(SizeOfFile)).toString();
                str = str + "}";
                byte[] filelength = str.getBytes();
                for(int j=0;j<filelength.length;j++) //sending the file size
                    out.write(filelength[j]);
                
                sendFile.jProgressBar1.setValue(sendFile.jProgressBar1.getMinimum());
                int i; // in.read() reads from file if reads -1 will be end of file
                //in = new BufferedInputStream(fis);
                int count = 0;
                int rec = 0;
                double asa = 0;
                while ((i = in.read()) != -1) { //sending the file
                    count++;
                    if(count == 1000) {
                        rec++;
                        count = 0;
                        asa = (double)rec/((double)SizeOfFile/1000);
                        asa = asa * 100;
                        if(asa < 10)
                            sendFile.setTitle( Double.toString(asa).substring(0,1) +  "% Sent To " + Reciver);
                        else
                            sendFile.setTitle( Double.toString(asa).substring(0,2) +  "% Sent To " + Reciver);
                        sendFile.jLabel2.setText("Sent " + rec + ".0" + " KB of " + Long.toString(SizeOfFile/1000) +" KB");
                    }
                    sendFile.jLabel2.setText("Sent " + rec + "." + count + " KB of " + Long.toString(SizeOfFile/1000) +" KB");
                    out.write(i);
                    sendFile.jProgressBar1.setValue(sendFile.jProgressBar1.getValue() + 1);
                }
                sendFile.setTitle("100% Sent To " + Reciver);
                sendFile.jProgressBar1.setValue(sendFile.jProgressBar1.getMinimum());
                //close all of the resources...
                in.close();
                out.flush();
                CloseConnection();
                sendFile.CancelButt.setText("Exit");
                sendFile.jLabel2.setText("Success To send file.");
                
                if(CloseCheckBox.isSelected())
                    sendFile.dispose();
                
            } catch( Exception e ) {
                sendFile.jLabel2.setText("Fail To send file.");
                sendFile.jProgressBar1.setValue(sendFile.jProgressBar1.getMinimum());
            }
            
        }
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButt;
    private javax.swing.JCheckBox CloseCheckBox;
    private javax.swing.JLabel FileNameLabel;
    private javax.swing.JButton SelectFileButt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
