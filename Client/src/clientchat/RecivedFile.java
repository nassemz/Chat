/*
 * RecivedFile.java
 *
 * Created on February 2, 2007, 10:56 PM
 */

package clientchat;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import sun.audio.*;

public class RecivedFile extends javax.swing.JFrame   {
    
    JFileChooser fc;
    private Socket socket;
    private String filenamee = "";
    private String usernamee = "";
    private String OUTPUTFILENAME = "C:\\";//receivedData";
    BufferedOutputStream out ;
    BufferedInputStream in;
    
    public RecivedFile(Socket socket_t) {
        initComponents();
        socket = socket_t;
        jProgressBar1.setMinimum(0);
        jProgressBar1.setValue(jProgressBar1.getMinimum());
        StatusLabel.setText("Waiting...");
        SaveToLabel.setText("Click Save as to choose location.");
        fc = new JFileChooser();
        PlaySound("C:\\VwV\\sounds\\file.wav");
        try {
            in = new BufferedInputStream( socket.getInputStream() );
            char j=0;
            while((j =(char)in.read()) != '}') {
                filenamee=filenamee+j;
            }
            FileNameLabel.setText(filenamee); //set the file name
            
            j=0;
            while((j =(char)in.read()) != '}') {
                usernamee=usernamee+j;
            }
            FromLabel.setText(usernamee); //set the file name
            this.setTitle("NFM Recived File from " + usernamee);
        }catch(Exception e){
        }
        Image im = Toolkit.getDefaultToolkit().getImage("C:\\VwV\\icons\\File.png");
        setIconImage(im);
        setLocationRelativeTo(null); //Display the window center it.
    }  
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        FileNameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FromLabel = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        StatusLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SaveToLabel = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        SaveasButton = new javax.swing.JButton();
        CloseCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jLabel1.setText("Recived File :");

        FileNameLabel.setText("jLabel2");

        jLabel3.setText("From : ");

        FromLabel.setText("jLabel4");

        jLabel5.setText("Status : ");

        StatusLabel.setText("jLabel6");

        jLabel7.setText("Saved to : ");

        SaveToLabel.setText("jLabel8");

        CancelButton.setText("Cancel");
        CancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CancelButtonMouseReleased(evt);
            }
        });

        SaveasButton.setText("Save as");
        SaveasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SaveasButtonMouseReleased(evt);
            }
        });

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FileNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FromLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveToLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SaveasButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CloseCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {CancelButton, SaveasButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(FileNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(FromLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(SaveToLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(StatusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CloseCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveasButton)
                    .addComponent(CancelButton))
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
    
    private void CancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelButtonMouseReleased
// TODO add your handling code here:
        closeConnection();
        this.dispose();
    }//GEN-LAST:event_CancelButtonMouseReleased
    
    private void SaveasButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveasButtonMouseReleased
        try{
            File file = new File("C:\\" + filenamee);
            fc.setSelectedFile(file);
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                //This is where a real application would save the file.
                SaveToLabel.setText(file.getPath());
                OUTPUTFILENAME = file.getPath().toString().substring(0,file.getPath().toString().length() - file.getName().length());
                filenamee = new String(file.getName());
            } else {
                //output.write('n');
                closeConnection();
                this.dispose();
                return;
            }
            //output.write('k');
            StatusLabel.setText("Connecting...");
            RecivingData aa = new RecivingData(filenamee,jProgressBar1,StatusLabel,this);//.start();
            aa.start();
            SaveasButton.setVisible(false);
            //log.setCaretPosition(log.getDocument().getLength());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_SaveasButtonMouseReleased
    
    class RecivingData extends Thread{
        
        private String filename;
        private long SizeOfFile;
        private JProgressBar PB;
        private JLabel label;
        private RecivedFile rf;
        
        public RecivingData( String filename_t, JProgressBar PB_t, JLabel Label_t , RecivedFile rf_t) {
            rf = rf_t;
            filename = filename_t;
            label = Label_t;
            PB = PB_t;
        }
        
        public void run(){
            try{
                FileOutputStream fos = new FileOutputStream(OUTPUTFILENAME+filenamee);
                out = new BufferedOutputStream(fos);
                
                char j=0;
                String FILELENGTH = "";
                while((j =(char)in.read()) != '}') {
                    FILELENGTH=FILELENGTH + j;
                }
                
                long l = Long.parseLong(FILELENGTH.trim());
                jProgressBar1.setMaximum((int) l);
                
                int i;
                int count = 0;
                int rec = 0;
                int x = (int) l;
                double asa = 0;
                String str = Integer.toString(x/1000);
                
                while ((i = in.read()) != -1) {
                    count ++;
                    out.write(i);
                    jProgressBar1.setValue(jProgressBar1.getValue() + 1);
                    
                    if(count == 1000) {
                        count = 0;
                        rec++;
                        asa = (double)rec/((double)x/1000);
                        asa = asa * 100;
                        if(asa < 10)
                            rf.setTitle( Double.toString(asa).substring(0,1) +  "% Recievd From " + usernamee);
                        else
                            rf.setTitle( Double.toString(asa).substring(0,2) +  "% Recievd From " + usernamee);
                        
                        
                        StatusLabel.setText("Recived " + rec + ".0" + " KB of " + str +" KB");
                    }
                    StatusLabel.setText("Recived " + rec + "." + count + " KB of " + str +" KB");
                }
                if(((rec*1000)+count) == x)
                    rf.setTitle("100% Recievd From " + usernamee);
                else
                    StatusLabel.setText("Recived " + rec + "." + count + " KB of " + str +" KB  canceled by sender [Not Completed]");
                
                rf.setVisible(true);
                jProgressBar1.setValue(jProgressBar1.getMinimum());
                out.flush();
                closeConnection();
                CancelButton.setText("Exit");
                if(CloseCheckBox.isSelected())
                    rf.dispose();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void PlaySound(String soundpath){
        try{
// AudioPlayer instantiated to force run of static initializers.
            AudioPlayer audioPlayer = AudioPlayer.player;
            AudioDataStream audioDataStream;
            ContinuousAudioDataStream continuousAudioDataStream;
            // Applet role
            
            FileInputStream fis = new FileInputStream( new File( soundpath) );
            AudioStream as = new AudioStream( fis ); // header plus audio data
            AudioData ad = as.getData(); // audio data only, no header
            audioDataStream = new AudioDataStream( ad );
            //continuousAudioDataStream = new ContinuousAudioDataStream( ad );
            
            
            audioPlayer.start( audioDataStream );
            
        }catch(Exception e){}
    }
    /**
     * @param args the command line arguments
     */
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JCheckBox CloseCheckBox;
    private javax.swing.JLabel FileNameLabel;
    private javax.swing.JLabel FromLabel;
    private javax.swing.JLabel SaveToLabel;
    private javax.swing.JButton SaveasButton;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    
}


