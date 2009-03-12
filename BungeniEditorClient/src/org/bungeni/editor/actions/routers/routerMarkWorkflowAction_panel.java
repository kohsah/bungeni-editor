/*
 * routerMarkWorkflowAction_panel.java
 *
 * Created on March 12, 2009, 3:08 PM
 */

package org.bungeni.editor.actions.routers;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bungeni.error.BungeniValidatorState;
import org.bungeni.utils.BungeniEditorProperties;

/**
 *
 * @author  undesa
 */
public abstract class routerMarkWorkflowAction_panel extends BaseRouterSelectorPanel {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerMarkWorkflowAction_panel.class.getName());
    SimpleDateFormat attributeDateFormat;
    /** Creates new form routerMarkWorkflowAction_panel */
    public routerMarkWorkflowAction_panel() {
        initComponents();
        attributeDateFormat = new SimpleDateFormat(BungeniEditorProperties.getEditorProperty("attributeDateFormat"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboActionType = new javax.swing.JComboBox();
        lblActionType = new javax.swing.JLabel();
        dtActionDate = new org.jdesktop.swingx.JXDatePicker();
        lbActionDate = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        cboActionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        cboActionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblActionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        lblActionType.setText("Action Type");

        dtActionDate.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N

        lbActionDate.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        lbActionDate.setText("Action Date");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboActionType, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblActionType))
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbActionDate)
                    .addComponent(dtActionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActionType)
                    .addComponent(lbActionDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboActionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtActionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnApply)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
// TODO add your handling code here:
    try {
        String actionType = this.cboActionType.getSelectedItem().toString();
        Date actionDate = this.dtActionDate.getDate();
        makeAndInsertReference(actionType, actionDate);
        containerFrame.dispose();
    } catch (Exception ex) {
        log.error("btnApplyActionPerformed : "+ ex.getMessage());
    }
}//GEN-LAST:event_btnApplyActionPerformed


public static final String BUNGENI_WORKFLOW_EVENT_PREFIX = "BungeniWorkflowEvent:";

/**
 * The default format for the Action reference is as follows  :
 * BungeniWorkflowEvent:actionName; actionType; actionDate; #serialNo;
 * @param actionType
 * @param actionDate
 */
public void makeAndInsertReference(String actionType, Date actionDate){
        //the action name is just some descriptive text about the action
        String actionName = theSubAction.action_value();
        String strActionDate = attributeDateFormat.format(actionDate);
        String fullRefString = BUNGENI_WORKFLOW_EVENT_PREFIX  + actionName + " ;" + actionType + " ;" + strActionDate ;
        String documentRefString = fullRefString + " ;#1";
        int i = 1;
        while (ooDocument.getReferenceMarks().hasByName(documentRefString) ) {
            documentRefString = fullRefString + " ;#" + ++i;
        }
        //substitute the subAction value with the documentRefstring as we are chaining to another router action to set the refereced
        theSubAction.setActionValue(documentRefString);
        routerCreateReference rcf = new routerCreateReference();
        BungeniValidatorState bvState = rcf.route_TextSelectedInsert(theAction, theSubAction, parentFrame, ooDocument);
}

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
    containerFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void initialize() {
        
    }

    @Override
    public Component getPanelComponent() {
        return this;
    }

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cboActionType;
    private org.jdesktop.swingx.JXDatePicker dtActionDate;
    private javax.swing.JLabel lbActionDate;
    private javax.swing.JLabel lblActionType;
    // End of variables declaration//GEN-END:variables

}
