/*
 * Copyright (C) 2013 Africa i-Parliaments
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.bungeni.extpanels.bungeni;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.apache.http.message.BasicNameValuePair;
import org.bungeni.extutils.CommonUIFunctions;
import org.bungeni.extutils.DisabledGlassPane;
import org.bungeni.extutils.NotifyBox;
import org.bungeni.utils.BungeniDialog;

/**
 *
 * @author Ashok Hariharan
 */
public class AttachmentVersionPanel extends javax.swing.JPanel {

    private BungeniDialog parentFrame ; 
    private String attachmentSourceURL ; 
    
    private DisabledGlassPane glassPane = new DisabledGlassPane();
    
    ResourceBundle BUNDLE = java.util.ResourceBundle.getBundle(
           "org/bungeni/extpanels/bungeni/Bundle"
           );
    

    private static org.apache.log4j.Logger log =
        org.apache.log4j.Logger.getLogger(AttachmentVersionPanel.class.getName());

    /**
     * Creates new form BillVersionPanel
     */
    private boolean proceed = false;
    
    public AttachmentVersionPanel(BungeniDialog frame, String attachmentPageURL) {
        initComponents();
        this.parentFrame = frame;
        this.attachmentSourceURL = attachmentPageURL;
    }

    class VersionExec extends SwingWorker<BungeniAppConnector.WebResponse, Boolean>{

        private String comment ; 
        
        public VersionExec(String sComment){
            this.comment = sComment ; 
        }
        
        @Override
        protected BungeniAppConnector.WebResponse doInBackground() throws Exception {
            List<BasicNameValuePair> formFields = 
                    BungeniServiceAccess.getInstance().getAttachmentVersionSubmitInfo(
                        attachmentSourceURL
                    );
            //get the post parameters
            List<BasicNameValuePair> postParams = BungeniServiceAccess.getInstance().
                    attachmentVersionSubmitPostQuery(
                        formFields,
                        this.comment
                    );

            // post
            BungeniAppConnector.WebResponse wr = BungeniServiceAccess.getInstance().doVersion(
                    attachmentSourceURL, 
                    postParams 
                    );
            return wr;
        }
       
        @Override
        protected void done(){
            try {
                BungeniAppConnector.WebResponse wr  = get();
                glassPane.deactivate();
                if (wr.getStatusCode() == 200 ) {
                    proceed = true;
                    parentFrame.dispose();
                } else {
                    proceed = false;
                    NotifyBox.error("The versioning of the attachment failed, will not proceed !", "Versioning Failure");
                }
            } catch (InterruptedException ex) {
                log.error("Versioning was interrupted", ex);
            } catch (ExecutionException ex) {
                log.error("Versioning was interrupted", ex);
            }
        } 
        
   }
    
    
    public boolean proceed(){
        return this.proceed;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtComment = new javax.swing.JTextArea();
        lblComment = new javax.swing.JLabel();
        btnVersionAndUpload = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        txtComment.setColumns(20);
        txtComment.setLineWrap(true);
        txtComment.setRows(5);
        jScrollPane1.setViewportView(txtComment);

        lblComment.setText("Comment for the Version");

        btnVersionAndUpload.setText("Create Version & Continue to Upload");
        btnVersionAndUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVersionAndUploadActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblComment, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 87, Short.MAX_VALUE)
                        .addComponent(btnVersionAndUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblComment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnVersionAndUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.parentFrame.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnVersionAndUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVersionAndUploadActionPerformed
        // TODO add your handling code here:
        CommonUIFunctions.disablePanel(this.glassPane, this.parentFrame, "Versioning Attachment" );
        String sComment = txtComment.getText();
        this.proceed = false;
        VersionExec exec = new VersionExec(sComment);
        exec.execute();
    }//GEN-LAST:event_btnVersionAndUploadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnVersionAndUpload;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblComment;
    private javax.swing.JTextArea txtComment;
    // End of variables declaration//GEN-END:variables
}
