/*
 * transformXMLPanel.java
 *
 * Created on May 20, 2008, 10:59 AM
 */

package org.bungeni.editor.panels.loadable;

import com.sun.star.container.XNamed;
import com.sun.star.text.XTextSection;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.bungeni.db.DefaultInstanceFactory;
import org.bungeni.utils.BungeniEditorProperties;
import org.bungeni.utils.BungeniEditorPropertiesHelper;
import org.bungeni.editor.macro.ExternalMacro;
import org.bungeni.editor.macro.ExternalMacroFactory;
import org.bungeni.editor.panels.impl.BaseClassForITabbedPanel;
import org.bungeni.editor.providers.DocumentSectionIterator2;
import org.bungeni.editor.providers.IBungeniSectionIteratorListener2;
import org.bungeni.editor.rules.ui.panelStructuralError;
import org.bungeni.editor.rulesimpl.StructuralError;
import org.bungeni.editor.rulesimpl.StructuralRulesConfig;
import org.bungeni.editor.rulesimpl.StructuralRulesEngine;
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.ooo.ooQueryInterface;
import org.bungeni.ooo.transforms.impl.BungeniTransformationTarget;
import org.bungeni.ooo.transforms.impl.BungeniTransformationTargetFactory;
import org.bungeni.ooo.transforms.impl.IBungeniDocTransform;
import org.bungeni.utils.BungeniFrame;
import org.bungeni.utils.CommonEditorFunctions;
import org.bungeni.utils.FrameLauncher;
import org.bungeni.utils.MessageBox;

/**
 *
 * @author  Administrator
 */
public class transformXMLPanel extends BaseClassForITabbedPanel{
       private static org.apache.log4j.Logger log = Logger.getLogger(transformXMLPanel.class.getName());

    /** Creates new form transformXMLPanel */
    public transformXMLPanel() {
        initComponents();
    }
    
    class exportDestination extends Object {
        String exportDestName;
        String exportDestDesc;
        
        exportDestination(String name, String desc) {
            this.exportDestDesc = desc;
            this.exportDestName = name;
        }

        @Override
        public String toString(){
            return this.exportDestDesc;
        }
    }
    
    private void initTransfromTargetCombo(){
        ArrayList<BungeniTransformationTarget> transArr = new ArrayList<BungeniTransformationTarget>(0);
        transArr.add(new BungeniTransformationTarget("PDF", "PDF (Portable Document Format)", "pdf" , "org.bungeni.ooo.transforms.loadable.PDFTransform"));
        transArr.add(new BungeniTransformationTarget("HTML", "HTML (Hypertext Web Document)", "html", "org.bungeni.ooo.transforms.loadable.HTMLTransform"));
        transArr.add(new BungeniTransformationTarget("AN-XML", "AN (AkomaNtoso XML Document)", "xml", "org.bungeni.ooo.transforms.loadable.AnXmlTransform"));
        DefaultComboBoxModel model = new DefaultComboBoxModel(transArr.toArray());
        this.cboTransformFrom.setModel(model);
    }
    
    private void initExportDestCombo(){
        ArrayList<exportDestination> transArr = new ArrayList<exportDestination>(0);
        transArr.add(new exportDestination("FileSystem", "Folder on your computer"));
        transArr.add(new exportDestination("ToBungeniServer", "Export into a Bungeni Server"));
        transArr.add(new exportDestination("FTP", "Export using FTP"));
        DefaultComboBoxModel model = new DefaultComboBoxModel(transArr.toArray());
        this.cboExportTo.setModel(model);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboTransformFrom = new javax.swing.JComboBox();
        cboExportTo = new javax.swing.JComboBox();
        lblExportTo = new javax.swing.JLabel();
        lblTransformFrom = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        checkChangeColumns = new javax.swing.JCheckBox();
        checkboxMakePlain = new javax.swing.JCheckBox();
        btnValidateStructure = new javax.swing.JButton();

        cboTransformFrom.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboTransformFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Portable Document Format (PDF)", "AkomaNtoso XML", "XHTML - eXtensible HTML", "Marginalia-safe HTML export" }));

        cboExportTo.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboExportTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Export to File-System path", "Export to Server" }));

        lblExportTo.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        lblExportTo.setText("Export To:");

        lblTransformFrom.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        lblTransformFrom.setText("Transformation Target");

        btnExport.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnExport.setText("Export...");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        checkChangeColumns.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        checkChangeColumns.setText("Switch to 2 columns");
        checkChangeColumns.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkChangeColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkChangeColumnsActionPerformed(evt);
            }
        });

        checkboxMakePlain.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        checkboxMakePlain.setText("Make Document Plain");
        checkboxMakePlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        checkboxMakePlain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxMakePlainActionPerformed(evt);
            }
        });

        btnValidateStructure.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnValidateStructure.setText("Validate Structure");
        btnValidateStructure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidateStructureActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(lblExportTo)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, cboExportTo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, cboTransformFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 223, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(btnExport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 143, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(lblTransformFrom)
                    .add(checkboxMakePlain)
                    .add(checkChangeColumns, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .add(btnValidateStructure, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(btnValidateStructure)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 47, Short.MAX_VALUE)
                .add(checkChangeColumns)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(checkboxMakePlain)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(lblTransformFrom)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cboTransformFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblExportTo)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cboExportTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnExport)
                .add(54, 54, 54))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxMakePlainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxMakePlainActionPerformed
// TODO add your handling code here:
            ExternalMacro replaceStyle = ExternalMacroFactory.getMacroDefinition("StyleReplace");
            replaceStyle.addParameter(ooDocument.getComponent());
            ooDocument.executeMacro(replaceStyle.toString(), replaceStyle.getParams());
    }//GEN-LAST:event_checkboxMakePlainActionPerformed

    private void checkChangeColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkChangeColumnsActionPerformed
// TODO add your handling code here:
        short columns = ooDocument.getPageColumns();
        if (this.checkChangeColumns.isSelected()) {
            if (2 != columns ) {
            ooDocument.setPageColumns((short)2);
            }
        } else {
            if (1 != columns ) {
                ooDocument.setPageColumns((short)1);
            }
        }
    }//GEN-LAST:event_checkChangeColumnsActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
// TODO add your handling code here:
        BungeniTransformationTarget transform = (BungeniTransformationTarget) this.cboTransformFrom.getSelectedItem();
        IBungeniDocTransform iTransform = BungeniTransformationTargetFactory.getTransformClass(transform);
        
        String exportPath = BungeniEditorProperties.getEditorProperty("defaultExportPath");
        exportPath = exportPath.replace('/', File.separatorChar);
        exportPath = DefaultInstanceFactory.DEFAULT_INSTALLATION_PATH() + File.separator + exportPath;
        exportPath = exportPath + File.separatorChar + OOComponentHelper.getFrameTitle(ooDocument.getTextDocument()).trim()+"."+transform.targetExt;
        File fileExp = new File(exportPath);
        String exportPathURL = "";
            exportPathURL = fileExp.toURI().toString();
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("StoreToUrl", exportPathURL);
        
        iTransform.setParams(params);
        boolean bState= iTransform.transform(ooDocument);
        if (bState ) {
            MessageBox.OK(parentFrame, "Document was successfully Exported to the workspace folder");
        } else
            MessageBox.OK(parentFrame, "Document export failed");
    }//GEN-LAST:event_btnExportActionPerformed

    private void btnValidateStructureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidateStructureActionPerformed
        //set the correct application path for the structural rules checker
        StructuralRulesConfig.APPLN_PATH_PREFIX=CommonEditorFunctions.getSettingsFolder() + File.separator + "structural_rules";
        //configure the source files
        String ruleEnginesFile = StructuralRulesConfig.getRuleEnginesPath() + BungeniEditorPropertiesHelper.getCurrentDocType() + ".xml";
        String docRulesFile  = StructuralRulesConfig.getDocRulesPath() + BungeniEditorPropertiesHelper.getCurrentDocType() + ".xml";
        //initalize the rules engine
        StructuralRulesEngine sre = new
                StructuralRulesEngine(docRulesFile,
                                        ruleEnginesFile);
        //iterate through the document and process the rules for every section
        processSections(sre);
    }//GEN-LAST:event_btnValidateStructureActionPerformed

    private void processSections(StructuralRulesEngine sre){
         String currentDocType = BungeniEditorPropertiesHelper.getCurrentDocType();
         //docType is the same name as the root section
         XTextSection xSection = ooDocument.getSection(currentDocType);
         DocumentSectionIterator2 sectionIterator = new DocumentSectionIterator2(ooDocument,
                                                            currentDocType, new StructureIterator(sre));
         sectionIterator.startIterator();
         ArrayList<StructuralError> sErrors = sre.getErrors();
         /**
          * Display the errors if any errors were returned
          */
         if (sErrors.size() > 0) {
             launchErrorFrame(sErrors);
         }
    }

    /**
     * Launches a window with the returned structural errors
     * @param sErrors
     */
    private void launchErrorFrame(ArrayList<StructuralError> sErrors) {
           BungeniFrame floatingFrame = new BungeniFrame();
           floatingFrame.setTitle("Structural Errors Found");
           BungeniFrame.BUNGENIFRAME_ALWAYS_ON_TOP = true;
           BungeniFrame.BUNGENIFRAME_RESIZABLE = true;
           panelStructuralError pPanel = new panelStructuralError(sErrors, ooDocument);
           floatingFrame.launch(pPanel, pPanel.getFrameSize() );
           FrameLauncher.CenterFrame(floatingFrame);
    }
    /**
     * The IteratorCallback of the StructureIterator class is called for every section
     * in the document
     */
    class StructureIterator implements IBungeniSectionIteratorListener2 {
        StructuralRulesEngine rulesEngine;
        StructureIterator (StructuralRulesEngine sre) {
            rulesEngine = sre;
        }

        public boolean iteratorCallback(XTextSection xSection) {
                //we process the rules for each section and build the stack of error messages
                XNamed xNamedSection = ooQueryInterface.XNamed(xSection);
                //the rule engine rules are applied to every section individually
                rulesEngine.processRules(ooDocument, xNamedSection.getName());
                return true;
        }

    }

    public void initialize() {
        this.initTransfromTargetCombo();
        this.initExportDestCombo();
        String docType = BungeniEditorPropertiesHelper.getCurrentDocType();
        if (docType.equalsIgnoreCase("debaterecord")) {
            this.checkboxMakePlain.setVisible(true);
        } else
            this.checkboxMakePlain.setVisible(false);
    }

    public void refreshPanel() {
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnValidateStructure;
    private javax.swing.JComboBox cboExportTo;
    private javax.swing.JComboBox cboTransformFrom;
    private javax.swing.JCheckBox checkChangeColumns;
    private javax.swing.JCheckBox checkboxMakePlain;
    private javax.swing.JLabel lblExportTo;
    private javax.swing.JLabel lblTransformFrom;
    // End of variables declaration//GEN-END:variables
    
}
