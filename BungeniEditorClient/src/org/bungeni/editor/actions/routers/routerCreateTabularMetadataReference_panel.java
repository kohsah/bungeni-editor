/*
 * routerCreateTabularMetadataReference_panel.java
 *
 * Created on February 25, 2009, 2:41 PM
 */

package org.bungeni.editor.actions.routers;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.bungeni.editor.actions.toolbarAction;
import org.bungeni.editor.actions.toolbarSubAction;
import org.bungeni.editor.metadata.TabularMetadataLoader;
import org.bungeni.editor.selectors.IMetadataContainerPanel;
import org.bungeni.editor.selectors.SelectorDialogModes;
import org.bungeni.ooo.OOComponentHelper;

/**
 *
 * @author  undesa
 */
public class routerCreateTabularMetadataReference_panel extends javax.swing.JPanel implements IMetadataContainerPanel {
    JFrame parentFrame;
    Window containerFrame;
    OOComponentHelper ooDocument;
    toolbarAction theAction;
    toolbarSubAction theSubAction;
    SelectorDialogModes dialogMode;
    
      private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(routerCreateTabularMetadataReference_panel.class.getName());
 
    /** Creates new form routerCreateSideNote_panel */
    public routerCreateTabularMetadataReference_panel(OOComponentHelper ooDoc, JFrame pFrame, JFrame cFrame) {
        parentFrame = pFrame;
        containerFrame = cFrame;
        ooDocument = ooDoc;
        initComponents();
    }
    
    
    /** Creates new form routerCreateTabularMetadataReference_panel */
    public routerCreateTabularMetadataReference_panel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollMetadataContainer = new javax.swing.JScrollPane();
        tblRefMetadata = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        tblRefMetadata.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        tblRefMetadata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollMetadataContainer.setViewportView(tblRefMetadata);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel1.setText("Create Reference to Metadata");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btnApply, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                .addGap(94, 94, 94))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMetadataContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMetadataContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
// TODO add your handling code here:
    containerFrame.dispose();
}//GEN-LAST:event_btnApplyActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
     containerFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollMetadataContainer;
    private javax.swing.JTable tblRefMetadata;
    // End of variables declaration//GEN-END:variables

    public void initVariables(OOComponentHelper ooDoc, JFrame parentFrm, toolbarAction aAction, toolbarSubAction aSubAction, SelectorDialogModes dlgMode) {
       parentFrame = parentFrm;
       ooDocument = ooDoc;
       theAction = aAction;
       theSubAction = aSubAction;
       dialogMode = dlgMode;
    }

    public void initialize() {
        //override matisse generated table
        tblRefMetadata = new JTable(){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tblRefMetadata.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        scrollMetadataContainer.setViewportView(tblRefMetadata);
        DefaultTableModel tblModel = TabularMetadataLoader.getTabularMetadataTableModel(ooDocument,theSubAction.action_value() );
        this.tblRefMetadata.setModel(tblModel);
        this.tblRefMetadata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tblRefMetadata.addMouseListener(new tblRefMetadataMouseListener());
        
    }

    private class tblRefMetadataMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Object sourceTable = e.getSource();
            if (sourceTable.getClass().getName().equals(JTable.class.getName())) {
                JTable tbl = (JTable)sourceTable;
                Point p = e.getPoint();
                if (e.getClickCount() == 2) {
                    int nRow = tbl.rowAtPoint(p);
                    int nCol = tbl.columnAtPoint(p);
                    Vector vRowData = (Vector) ((DefaultTableModel)tbl.getModel()).getDataVector().elementAt(nRow);
                    
                }
            }
         
            
        }
    }
    
    public void setContainerFrame(Window frame) {
       this.containerFrame = frame;
    }

    public Component getPanelComponent() {
       return this;
    }

}