package org.bungeni.editor.selectors.debaterecord.questions;

import java.awt.Component;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import org.bungeni.editor.config.OntologiesReader;
import org.bungeni.editor.selectors.BaseMetadataPanel;
import org.bungeni.editor.selectors.debaterecord.question.ObjectQuestionType;
import org.bungeni.ooo.OOComponentHelper;
import org.bungeni.editor.config.BungeniEditorPropertiesHelper;
import org.bungeni.extutils.CommonEditorXmlUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdom.Element;
import org.jdom.JDOMException;

/**
 *
 * @author  Ashok Hariharan
 */
public class QuestionType extends BaseMetadataPanel {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(QuestionType.class.getName());
 
    /** Creates new form QuestionTitle */
    public QuestionType() {
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

        lblQuestionType = new javax.swing.JLabel();
        cboQuestionType = new javax.swing.JComboBox();

        lblQuestionType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/selectors/debaterecord/questions/Bundle"); // NOI18N
        lblQuestionType.setText(bundle.getString("QuestionType.lblQuestionType.text")); // NOI18N
        lblQuestionType.setName("lbl_question_title"); // NOI18N

        cboQuestionType.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        cboQuestionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addComponent(cboQuestionType, 0, 265, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblQuestionType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getPanelName() {
        return getName();
    }

    public Component getPanelComponent() {
        return this;
    }

    @Override
    public boolean doCancel() {
        return true;
    }

    @Override
    public boolean doReset() {
        return true;
    }

    @Override
    public boolean preFullEdit() {
        return true;
    }

    @Override
    public boolean processFullEdit() {
        String editSectionName = getContainerPanel().getEditSectionName();
        if (editSectionName.length() > 0) {
            
            HashMap<String, String> sectionMeta = new HashMap<String, String>();
            ObjectQuestionType oqt = (ObjectQuestionType) this.cboQuestionType.getSelectedItem();

            sectionMeta.put("BungeniQuestionType", oqt.getOntologyURI());
            sectionMeta.put("BungeniQuestionTypeShowAs", oqt.getOntologyShowAs());

            getContainerPanel().getOoDocument().setSectionMetadataAttributes(editSectionName, sectionMeta);

        }
        return true;
    }

    @Override
    public boolean postFullEdit() {
        return true;
    }

    @Override
    public boolean preFullInsert() {
        return true;
    }

    @Override
    public boolean processFullInsert() {

        return true;
    }

    @Override
    public boolean postFullInsert() {
        return true;
    }

    @Override
    public boolean preSelectEdit() {
        return true;
    }

    @Override
    public boolean processSelectEdit() {
        return true;
    }

    @Override
    public boolean postSelectEdit() {
        return true;
    }

    @Override
    public boolean preSelectInsert() {
        return true;
    }

    @Override
    public boolean processSelectInsert() {
        OOComponentHelper ooDoc = getContainerPanel().getOoDocument();
        HashMap<String, String> sectionMeta = new HashMap<String, String>();
        String newSectionName = (getContainerPanel()).mainSectionName;
        ObjectQuestionType oqs = ((ObjectQuestionType)this.cboQuestionType.getSelectedItem());
        String sURI = oqs.getOntologyURI();
        String sShowAs = oqs.getOntologyShowAs();
        sectionMeta.put("BungeniQuestionType", sURI);
        sectionMeta.put("BungeniQuestionTypeShowAs", sShowAs);

        ooDoc.setSectionMetadataAttributes(newSectionName, sectionMeta);
        return true;
    }

    @Override
    public boolean postSelectInsert() {
        return true;
    }

    @Override
    public boolean validateSelectedEdit() {
        return true;
    }

    @Override
    public boolean validateSelectedInsert() {
        return true;
    }

    @Override
    public boolean validateFullInsert() {
        return true;
    }

    @Override
    public boolean validateFullEdit() {
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboQuestionType;
    private javax.swing.JLabel lblQuestionType;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void initFieldsSelectedEdit() {
        return;
    }

    @Override
    protected void initFieldsSelectedInsert() {
        return;
    }

    @Override
    protected void initFieldsInsert() {
        return;
    }

    @Override
    protected void initFieldsEdit() {
        //get the metadata attribute for the question type from the document
        //set it as the seleciton in the dialog
        return;
    }

    @Override
    public void commonInitFields() {
        try {
            initComboSelect();
        } catch (IOException ex) {
            log.error("in initComboSelect " , ex);
        }
    }

    private void initComboSelect() throws IOException {
        Vector<ObjectQuestionType> questionTypeObjects = getQuestionTypeObjects();
        this.cboQuestionType.setModel(new DefaultComboBoxModel(questionTypeObjects));
        AutoCompleteDecorator.decorate(this.cboQuestionType);
    }

    private Vector<ObjectQuestionType> getQuestionTypeObjects() throws IOException {
        Vector<ObjectQuestionType> questionTypes = new Vector<ObjectQuestionType>();
        List<Element> listOntoElements = null;
        try {
            listOntoElements = OntologiesReader.getInstance().getOntologiesByType("question");
        } catch (JDOMException ex) {
            log.error("Error while loading ontology" , ex);
        }
        if (null != listOntoElements) {
            
        /**
         *
        <ontology
        type="question"
        href="/ontology/concept/questions.private.notice">
        <showAs xml:lang="eng">Questions By Private Notice</showAs>
        </ontology>
         *
         */
            for (Element ontoElement : listOntoElements) {
                String sHref = ontoElement.getAttributeValue("href");
                String showAs = CommonEditorXmlUtils.getLocalizedChildElementValue(
                            BungeniEditorPropertiesHelper.getLangAlpha3Part2(),
                            ontoElement,
                            "title"
                            );
                ObjectQuestionType ost = new ObjectQuestionType(sHref, showAs);
                questionTypes.add(ost);
            }
        }
        return questionTypes;
    }


 

}
