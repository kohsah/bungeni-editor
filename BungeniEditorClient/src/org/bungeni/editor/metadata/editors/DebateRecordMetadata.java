/*
 * DebateRecordMetadata.java
 *
 * Created on November 4, 2008, 1:43 PM
 */

package org.bungeni.editor.metadata.editors;

import java.awt.Component;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import org.bungeni.editor.config.BungeniEditorProperties;
import org.bungeni.editor.config.BungeniEditorPropertiesHelper;
import org.bungeni.editor.metadata.BaseEditorDocMetadataDialog;
import org.bungeni.editor.selectors.SelectorDialogModes;
import org.bungeni.utils.BungeniFileSavePathFormat;
import org.bungeni.editor.metadata.LanguageCode;
import org.bungeni.editor.metadata.CountryCode;
import org.bungeni.editor.metadata.DebateRecordMetaModel;
import org.bungeni.editor.metadata.DocumentPart;
import org.bungeni.extutils.CommonStringFunctions;

/**
 *
 * @author  undesa
 */
public class DebateRecordMetadata extends BaseEditorDocMetadataDialog {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DebateRecordMetadata.class.getName());
   DebateRecordMetaModel docMetaModel = new DebateRecordMetaModel();
     
    
 //   OOComponentHelper ooDocument  = null;
 //   JFrame parentFrame = null;
 //    SelectorDialogModes dlgMode = null;
    
    
    public DebateRecordMetadata(){
        super();
        initComponents();
    }
    
    @Override
    public void initialize() {
        super.initialize();
        this.docMetaModel.setup();
        initControls();
           if (theMode == SelectorDialogModes.TEXT_EDIT) {
            try {
                //retrieve metadata... and set in controls....
                docMetaModel.loadModel(ooDocument);

               String sParlId = docMetaModel.getItem("BungeniParliamentID");
                String sParlSitting = docMetaModel.getItem("BungeniParliamentSitting");
                String sParlSession = docMetaModel.getItem("BungeniParliamentSession");
                String sCountryCode = docMetaModel.getItem("BungeniCountryCode");
                String sLanguageCode = docMetaModel.getItem("BungeniLanguageCode");
                String sOfficDate =docMetaModel.getItem("BungeniDebateOfficialDate");
                String sOfficTime = docMetaModel.getItem("BungeniDebateOfficialTime");
                String sPartName = docMetaModel.getItem("BungeniDocPart");
                String sPublicationName = docMetaModel.getItem("BungeniPublicationName");
                String sPublicationDate = docMetaModel.getItem("BungeniPublicationDate");
                //official date
                if (!CommonStringFunctions.emptyOrNull(sOfficDate)) {
                SimpleDateFormat formatter = new SimpleDateFormat(BungeniEditorProperties.getEditorProperty("metadataDateFormat"));
                this.dt_initdebate_hansarddate.setDate(formatter.parse(sOfficDate));
                }
                //official time
                if (!CommonStringFunctions.emptyOrNull(sOfficTime) ) {
                    SimpleDateFormat timeFormat = new SimpleDateFormat(BungeniEditorProperties.getEditorProperty("metadataTimeFormat"));
                    dt_initdebate_timeofhansard.setValue(timeFormat.parse(sOfficTime));
                }
                if (!CommonStringFunctions.emptyOrNull(sPublicationDate)) {
                    SimpleDateFormat timeFormat = new SimpleDateFormat(BungeniEditorProperties.getEditorProperty("metadataDateFormat"));
                    this.dt_publication_date.setDate(timeFormat.parse(sPublicationDate));
                }
                if (!CommonStringFunctions.emptyOrNull(sParlId)) {
                    this.BungeniParliamentID.setText(sParlId);
                }
                if (!CommonStringFunctions.emptyOrNull(sParlSession)) {
                    this.txtParliamentSession.setText(sParlSession);
                }
                if (!CommonStringFunctions.emptyOrNull(sParlSitting)) {
                    this.txtParliamentSitting.setText(sParlSitting);
                }
                if (!CommonStringFunctions.emptyOrNull(sCountryCode)) {
                    this.cboCountry.setSelectedItem(findCountryCode(sCountryCode));
                }
                if (!CommonStringFunctions.emptyOrNull(sLanguageCode)) {
                    this.cboLanguage.setSelectedItem(findLanguageCodeAlpha2(sLanguageCode));
                }
                if (!CommonStringFunctions.emptyOrNull(sPartName)) {
                    this.cboDocumentPart.setSelectedItem(findDocumentPart(sPartName));
                }
                if (!CommonStringFunctions.emptyOrNull(sPublicationName)) {
                    this.txtPublicationName.setText(sPublicationName);
                }
                
            } catch (ParseException ex) {
                log.error("initalize()  =  "  + ex.getMessage());
            }
         
        }
    }

    public Component getPanelComponent() {
        return this;
    }
 
    
    private void initControls(){
        //String popupDlgBackColor = BungeniEditorProperties.getEditorProperty("popupDialogBackColor");
        //this.setBackground(Color.decode(popupDlgBackColor));
        cboCountry.setModel(new DefaultComboBoxModel(countryCodes));
        cboLanguage.setModel(new DefaultComboBoxModel(languageCodes));
        cboDocumentPart.setModel(new DefaultComboBoxModel(documentParts));
         dt_initdebate_timeofhansard.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR));
        dt_initdebate_timeofhansard.setEditor(new JSpinner.DateEditor(dt_initdebate_timeofhansard, BungeniEditorProperties.getEditorProperty("metadataTimeFormat")));
        ((JSpinner.DefaultEditor)dt_initdebate_timeofhansard.getEditor()).getTextField().setEditable(false);
 
    }
    

public boolean applySelectedMetadata(BungeniFileSavePathFormat spf){
    boolean bState = false;
    try {
    String sParliamentID = this.BungeniParliamentID.getText();
    String sParliamentSitting = this.txtParliamentSitting.getText();
    String sParliamentSession = this.txtParliamentSession.getText();
    CountryCode selCountry = (CountryCode)this.cboCountry.getSelectedItem();
    LanguageCode selLanguage = (LanguageCode) this.cboLanguage.getSelectedItem();
    DocumentPart selPart = (DocumentPart) this.cboDocumentPart.getSelectedItem();
    //ooDocMetadata docMeta = new ooDocMetadata(ooDocument);
    //get the official time
        SimpleDateFormat tformatter = new SimpleDateFormat (BungeniEditorProperties.getEditorProperty("metadataTimeFormat"));
       Object timeValue = this.dt_initdebate_timeofhansard.getValue();
       Date hansardTime = (Date) timeValue;
    final String strTimeOfHansard = tformatter.format(hansardTime);
    //get the offical date
       SimpleDateFormat dformatter = new SimpleDateFormat (BungeniEditorProperties.getEditorProperty("metadataDateFormat"));
       final String strDebateDate = dformatter.format( dt_initdebate_hansarddate.getDate());
    //get the current date
       Calendar cal = Calendar.getInstance();
       final String strCurrentDate = dformatter.format(cal.getTime());
    //get the publication date
       final String strPubDate = dformatter.format( this.dt_publication_date.getDate());
       final String strPubName = this.txtPublicationName.getText();
       
   // docMetaModel.updateItem("BungeniParliamentID")
    docMetaModel.updateItem("BungeniParliamentID", sParliamentID);
    docMetaModel.updateItem("BungeniParliamentSitting", sParliamentSitting);
    docMetaModel.updateItem("BungeniParliamentSession", sParliamentSession);
    docMetaModel.updateItem("BungeniCountryCode", selCountry.getCountryCodeLower());
    docMetaModel.updateItem("BungeniLanguageCode", selLanguage.getLanguageCodeAlpha3());
    docMetaModel.updateItem("BungeniDebateOfficialDate", strDebateDate);
    docMetaModel.updateItem("BungeniWorkDate", strDebateDate);
    docMetaModel.updateItem("BungeniExpDate", strCurrentDate);
    docMetaModel.updateItem("BungeniManDate", strCurrentDate);
    docMetaModel.updateItem("BungeniPublicationDate", strPubDate);
    docMetaModel.updateItem("BungeniPublicationName", strPubName);
    docMetaModel.updateItem("BungeniDocAuthor", "Ashok");
    
    docMetaModel.updateItem("BungeniDebateOfficialTime", strTimeOfHansard);
    docMetaModel.updateItem("BungeniDocPart", selPart.getPartName());
    
    spf.setSaveComponent("DocumentType", BungeniEditorPropertiesHelper.getCurrentDocType());
    spf.setSaveComponent("CountryCode", selCountry.getCountryCodeLower());
    spf.setSaveComponent("LanguageCode", selLanguage.getLanguageCodeAlpha3());
    Date dtHansardDate = dt_initdebate_hansarddate.getDate();
    GregorianCalendar debateCal = new GregorianCalendar();
    debateCal.setTime(dtHansardDate);
    spf.setSaveComponent("Year", debateCal.get(Calendar.YEAR));
    spf.setSaveComponent("Month", debateCal.get(Calendar.MONTH) + 1);
    spf.setSaveComponent("Day", debateCal.get(Calendar.DAY_OF_MONTH));
    spf.setSaveComponent("PartName", selPart.getPartName());
   // spf.setSaveComponent("FileName", spf.getFileName());
    
    //docMetaModel.updateItem("__BungeniDocMeta", "true");
    docMetaModel.saveModel(ooDocument);
    bState = true;
    } catch (Exception ex) {
        log.error("applySelectedMetadata : " + ex.getMessage());
        bState = false;
    } finally {
        return bState;
    }
}    


private final static String STORE_TO_URL = "StoreToURL";
private final static String STORE_AS_URL = "StoreAsURL";

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboCountry = new javax.swing.JComboBox();
        cboLanguage = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        BungeniParliamentID = new javax.swing.JTextField();
        txtParliamentSession = new javax.swing.JTextField();
        txtParliamentSitting = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        dt_initdebate_hansarddate = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        dt_initdebate_timeofhansard = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        cboDocumentPart = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        dt_publication_date = new org.jdesktop.swingx.JXDatePicker();
        jLabel9 = new javax.swing.JLabel();
        txtPublicationName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(java.awt.Color.lightGray);

        cboCountry.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCountry.setName("fld.BungeniCountryCode"); // NOI18N
        cboCountry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCountryActionPerformed(evt);
            }
        });

        cboLanguage.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLanguage.setName("fld.BungeniLanguageID"); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel1.setText("Country");
        jLabel1.setName("lbl.BungeniCountryCode"); // NOI18N

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel2.setText("Language");
        jLabel2.setName("lbl.BungeniLanguageID"); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel3.setText("Parliament ID");
        jLabel3.setName("lbl.BungeniParliamentID"); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel4.setText("Parliament Session");
        jLabel4.setName("lbl.BungeniParliamentSession"); // NOI18N

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel5.setText("Parliament Sitting");
        jLabel5.setName("lbl.BungeniParliamentSitting"); // NOI18N

        btnSave.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnSave.setText("Save");

        btnCancel.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        BungeniParliamentID.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        BungeniParliamentID.setName("fld.BungeniParliamentID"); // NOI18N
        BungeniParliamentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BungeniParliamentIDActionPerformed(evt);
            }
        });

        txtParliamentSession.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        txtParliamentSession.setName("fld.BungeniParliamentSession"); // NOI18N

        txtParliamentSitting.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        txtParliamentSitting.setName("BungeniParliamentSitting"); // NOI18N

        jTextArea1.setBackground(java.awt.Color.lightGray);
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("This is a new document, Please select and enter required metadata to initialize the document");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);

        dt_initdebate_hansarddate.setFont(new java.awt.Font("DejaVu Sans", 0, 10));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel6.setText("Date");
        jLabel6.setName("lbl.BungeniParliamentSitting"); // NOI18N

        dt_initdebate_timeofhansard.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        dt_initdebate_timeofhansard.setName("dt_initdebate_timeofhansard"); // NOI18N

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel7.setText("TIme");
        jLabel7.setName("lbl.BungeniParliamentSitting"); // NOI18N

        cboDocumentPart.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        cboDocumentPart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel8.setText("Document Part");
        jLabel8.setName("lbl.BungeniLanguageID"); // NOI18N

        dt_publication_date.setFont(new java.awt.Font("DejaVu Sans", 0, 10));

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel9.setText("Publication Date");

        txtPublicationName.setFont(new java.awt.Font("DejaVu Sans", 0, 10));

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        jLabel10.setText("Publication Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dt_initdebate_hansarddate, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dt_initdebate_timeofhansard, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLanguage, javax.swing.GroupLayout.Alignment.LEADING, 0, 381, Short.MAX_VALUE)
                            .addComponent(cboCountry, javax.swing.GroupLayout.Alignment.LEADING, 0, 381, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(208, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cboDocumentPart, 0, 381, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtParliamentSitting)
                                .addComponent(BungeniParliamentID, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dt_publication_date, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtParliamentSession, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                .addGap(39, 39, 39))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPublicationName, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDocumentPart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt_publication_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPublicationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BungeniParliamentID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParliamentSession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtParliamentSitting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dt_initdebate_hansarddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dt_initdebate_timeofhansard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void cboCountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCountryActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_cboCountryActionPerformed

private void BungeniParliamentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BungeniParliamentIDActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_BungeniParliamentIDActionPerformed

private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
// TODO add your handling code here:
    parentFrame.dispose();
}//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BungeniParliamentID;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboCountry;
    private javax.swing.JComboBox cboDocumentPart;
    private javax.swing.JComboBox cboLanguage;
    private org.jdesktop.swingx.JXDatePicker dt_initdebate_hansarddate;
    private javax.swing.JSpinner dt_initdebate_timeofhansard;
    private org.jdesktop.swingx.JXDatePicker dt_publication_date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtParliamentSession;
    private javax.swing.JTextField txtParliamentSitting;
    private javax.swing.JTextField txtPublicationName;
    // End of variables declaration//GEN-END:variables

 

    @Override
    public Dimension getFrameSize() {
           int DIM_X = 405 ; int DIM_Y = 419 ;
        return new Dimension(DIM_X, DIM_Y + 10);
    }

   


}
