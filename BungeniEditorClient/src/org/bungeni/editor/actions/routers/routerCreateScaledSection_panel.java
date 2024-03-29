/*
 *  Copyright (C) 2012 UN/DESA Africa i-Parliaments Action Plan
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 3
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.bungeni.editor.actions.routers;

import com.sun.star.container.XNamed;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XTextSection;
import com.sun.star.text.XTextViewCursor;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.bungeni.extutils.MessageBox;
import org.bungeni.ooo.ooQueryInterface;

/**
 *
 * @author  undesa
 */
public class routerCreateScaledSection_panel extends BaseRouterSelectorPanel {
    private String originSection = "";
    private XTextSection xoriginSection = null;
    private String originBookmarkName = "";
    private String targetBookmarkName = "";
    private int nBookmarkCounter = 0;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerCreateScaledSection_panel.class.getName());

    /** Creates new form routerCreateScaledSection_panel */
    public routerCreateScaledSection_panel() {
        super();
        initComponents();
    }

    private String getUniqueBookmarkName(String prefix) {
            String bookmarkName = prefix;
            int i = 0;
            String fullBookmarkName = bookmarkName + i;

            boolean bExists = ooDocument.bookmarkExists(fullBookmarkName);
            while (bExists) {
                fullBookmarkName = bookmarkName + (++i);
                bExists = ooDocument.bookmarkExists(fullBookmarkName);
            }
        
            return fullBookmarkName;
    }
    
    private void applyStartSectionBookmark(){
        try {
            //generate a new bookmark name to flag the start of the section
            String bookmarkNamePrefix = "start-sec-";
            Object objBookmark = ooDocument.createInstance("com.sun.star.text.Bookmark");
            String fullBookmarkName = getUniqueBookmarkName(bookmarkNamePrefix);
            //create the bookmark and apply it at the current cursor position
            XNamed namedBookmark = ooQueryInterface.XNamed(objBookmark);
            namedBookmark.setName(fullBookmarkName);

            XTextViewCursor currentCursor = ooDocument.getViewCursor();
            XTextRange startRange = currentCursor.getStart();
            XTextContent xContent = ooQueryInterface.XTextContent(namedBookmark);
            currentCursor.getText().insertTextContent(currentCursor, xContent, false);
            
            this.originBookmarkName = fullBookmarkName;
            this.originSection = ooDocument.currentSectionName();
            //disable the start section button
            this.btnStartSection.setEnabled(false);
            this.btnEndSection.setEnabled(true);
           
        } catch (IllegalArgumentException ex) {
            log.error("applyStartSectionBookmark : "+ ex.getMessage());
        }
    }
    
    private void applyEndSectionBookmark(){
        try {
            //generate a new bookmark name to flag the start of the section
            String bookmarkNamePrefix = "end-sec-";
            Object objBookmark = ooDocument.createInstance("com.sun.star.text.Bookmark");
            String fullBookmarkName = getUniqueBookmarkName(bookmarkNamePrefix);
            //create the bookmark and apply it at the current cursor position
            XNamed namedBookmark = ooQueryInterface.XNamed(objBookmark);
            namedBookmark.setName(fullBookmarkName);

            XTextViewCursor currentCursor = ooDocument.getViewCursor();
            XTextContent xContent = ooQueryInterface.XTextContent(namedBookmark);
            currentCursor.getText().insertTextContent(currentCursor, xContent, false);
            
            this.targetBookmarkName = fullBookmarkName;
            //disable the start section button
        } catch (IllegalArgumentException ex) {
            log.error("applyStartSectionBookmark : "+ ex.getMessage());
        }
    }

    
    protected void cleanupBookmarks() {
             ooDocument.deleteBookmark(this.originBookmarkName);
             ooDocument.deleteBookmark(this.targetBookmarkName);
    }
    
    protected void createSection(){
         String newSectionName;
         newSectionName = CommonRouterActions.get_newSectionNameForAction(theSubAction, ooDocument);
         if (newSectionName.length() == 0 ) {
             log.error("createSection : new section name could not be generated");
         } else {
            boolean bAction = CommonRouterActions.action_createSpannedContainer(ooDocument, newSectionName, this.originBookmarkName, this.targetBookmarkName);
            if (bAction ) {
                log.info("createSection : spanned section was created");
                //set section type metadata
                CommonRouterActions.setSectionProperties(theSubAction, newSectionName, ooDocument);
                ooDocument.setSectionMetadataAttributes(newSectionName, CommonRouterActions.get_newSectionMetadata(theSubAction));
            } else {
                log.error("createSection: error while creating section ");
            }
         }      
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollMessage = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        btnStartSection = new javax.swing.JButton();
        btnEndSection = new javax.swing.JButton();

        txtMessage.setColumns(20);
        txtMessage.setEditable(false);
        txtMessage.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        txtMessage.setLineWrap(true);
        txtMessage.setRows(3);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/actions/routers/Bundle"); // NOI18N
        txtMessage.setText(bundle.getString("routerCreateScaledSection_panel.txtMessage.text")); // NOI18N
        txtMessage.setWrapStyleWord(true);
        txtMessage.setBorder(null);
        scrollMessage.setViewportView(txtMessage);

        btnStartSection.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnStartSection.setText(bundle.getString("routerCreateScaledSection_panel.btnStartSection.text")); // NOI18N
        btnStartSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSectionActionPerformed(evt);
            }
        });

        btnEndSection.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnEndSection.setText(bundle.getString("routerCreateScaledSection_panel.btnEndSection.text")); // NOI18N
        btnEndSection.setEnabled(false);
        btnEndSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndSectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnStartSection, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEndSection, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addComponent(scrollMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartSection)
                    .addComponent(btnEndSection)))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
private void btnStartSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSectionActionPerformed
// TODO add your handling code here:
   //generate a unique name for the bookmark
   //insert a bookmar at the current cursor position
   //save the bookmark name
   applyStartSectionBookmark();
}//GEN-LAST:event_btnStartSectionActionPerformed

private void btnEndSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndSectionActionPerformed
// TODO add your handling code here:
    String endSectionName = ooDocument.currentSectionName();
    if (endSectionName.equals(this.originSection)) {
        //origin == end 
        //we are good to go and apply the end marker
        applyEndSectionBookmark();
        createSection();
        cleanupBookmarks();
        this.containerFrame.dispose();
    } else {
        MessageBox.OK(this.containerFrame, "The new section must start and end within the same section. \n Please select an end point within the source section boundary.");
    }
    
}//GEN-LAST:event_btnEndSectionActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEndSection;
    private javax.swing.JButton btnStartSection;
    private javax.swing.JScrollPane scrollMessage;
    private javax.swing.JTextArea txtMessage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void initialize() {
       
    }

    @Override
    public Component getPanelComponent() {
        return this;
    }

    @Override
    public void setContainerFrame(Window frm) {
        super.setContainerFrame(frm);
        this.containerFrame.addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent wEvent) {
                cleanupBookmarks();
            }      
        });
        }
    
}

