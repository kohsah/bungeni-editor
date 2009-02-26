/*
 * routerCreateSideNote_panel.java
 *
 * Created on February 1, 2009, 11:47 PM
 */

package org.bungeni.editor.actions.routers;

import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextViewCursor;
import java.awt.Component;
import java.awt.Window;
import javax.swing.JFrame;
import org.bungeni.editor.actions.toolbarAction;
import org.bungeni.editor.actions.toolbarSubAction;
import org.bungeni.editor.selectors.IMetadataContainerPanel;
import org.bungeni.editor.selectors.SelectorDialogModes;
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.ooo.ooQueryInterface;
import org.bungeni.utils.MessageBox;

/**
 *
 * @author  undesa
 */
public class routerCreateSideNote_panel extends javax.swing.JPanel implements IMetadataContainerPanel {

    JFrame parentFrame;
    Window containerFrame;
    OOComponentHelper ooDocument;
    toolbarAction theAction;
    toolbarSubAction theSubAction;
    SelectorDialogModes dialogMode;
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerCreateSideNote_panel.class.getName());
 
    /** Creates new form routerCreateSideNote_panel */
    public routerCreateSideNote_panel(OOComponentHelper ooDoc, JFrame pFrame, JFrame cFrame) {
        parentFrame = pFrame;
        containerFrame = cFrame;
        ooDocument = ooDoc;
        initComponents();
    }
    
       public routerCreateSideNote_panel() {
        initComponents();
    }


    private boolean applySideNote(String sideNoteTxt){
        boolean bState = false;
        try {

//        oDoc = ThisComponent
//	oTextCursor = oDoc.getCurrentController().getViewCursor()
            XTextViewCursor viewCursor = ooDocument.getViewCursor();
            Object objAnnotation = ooDocument.createInstance("com.sun.star.text.TextField.Annotation");
            XPropertySet xProps = ooQueryInterface.XPropertySet(objAnnotation);
            xProps.setPropertyValue("Author", "SN");
            xProps.setPropertyValue("Content", sideNoteTxt);
            XTextContent annotContent = ooQueryInterface.XTextContent(objAnnotation);
            ooDocument.getTextDocument().getText().insertTextContent(viewCursor.getEnd(), annotContent, true);
            bState = true;
        } catch (UnknownPropertyException ex) {
            log.info("applySideNote : " + ex.getMessage());
        } catch (PropertyVetoException ex) {
            log.info("applySideNote : " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("applySideNote : " + ex.getMessage());
        } catch (WrappedTargetException ex) {
            log.info("applySideNote : " + ex.getMessage());
        } finally{
            return bState;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtSideNote = new javax.swing.JTextArea();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        txtSideNote.setColumns(20);
        txtSideNote.setRows(5);
        jScrollPane1.setViewportView(txtSideNote);

        btnApply.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
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
                .addGap(108, 108, 108)
                .addComponent(btnApply)
                .addGap(18, 18, 18)
                .addComponent(btnCancel)
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnApply)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
// TODO add your handling code here:
    String sideNoteTxt = this.txtSideNote.getText().trim();
    if (sideNoteTxt.length() == 0 ) {
        MessageBox.OK(containerFrame, "You must enter side-note text!");
        return;
    }
    applySideNote(sideNoteTxt);
    containerFrame.dispose();
}//GEN-LAST:event_btnApplyActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
    containerFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtSideNote;
    // End of variables declaration//GEN-END:variables

    public void initVariables(OOComponentHelper ooDoc, JFrame parentFrm, toolbarAction aAction, toolbarSubAction aSubAction, SelectorDialogModes dlgMode) {
       parentFrame = parentFrm;
       ooDocument = ooDoc;
       theAction = aAction;
       theSubAction = aSubAction;
       dialogMode = dlgMode;
    }

    public void initialize() {
       //do nothing
    }

    public void setContainerFrame(Window frame) {
        this.containerFrame = frame;
    }

    public Component getPanelComponent() {
        return this;
    }

}