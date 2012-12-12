
package org.bungeni.editor.selectors.general.ref;

import java.awt.Component;
import java.util.HashMap;
import org.bungeni.editor.selectors.BaseMetadataPanel;
import org.bungeni.extutils.CommonUIFunctions;
import org.bungeni.ooo.OOComponentHelper;

/**
 *
 * @author  undesa
 */
public class RefURI extends  BaseMetadataPanel {

    /** Creates new form PersonSelector */
    public RefURI() {
        super();
        initComponents();
        CommonUIFunctions.compOrientation(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_RefURI = new javax.swing.JLabel();
        txt_RefURI = new javax.swing.JTextField();

        setName("Speech By"); // NOI18N

        lbl_RefURI.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/bungeni/editor/selectors/general/ref/Bundle"); // NOI18N
        lbl_RefURI.setText(bundle.getString("RefURI.lbl_RefURI.text")); // NOI18N

        txt_RefURI.setFont(new java.awt.Font("DejaVu Sans", 0, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_RefURI, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_RefURI, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_RefURI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_RefURI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

      public String getPanelName() {
        return getName();
    }

    public Component getPanelComponent() {
        return this;
    }

    private boolean validateRefURI() {
        String sRefURI = this.txt_RefURI.getText();
        if (sRefURI.trim().length() == 0) {
            this.addErrorMessage(this.txt_RefURI, "Please enter Ref URI");
            return false;
        }
        return true;
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
        //!+FIX_THIS(ah, may-2012) The edit mode metadata processing needs to be fixed.
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
          final String strHref = this.txt_RefURI.getText();
          HashMap<String,String> inlineMap = new HashMap<String,String>(){
                {
                    put("BungeniInlineType", "ref");
                    put("BungeniRefURI", strHref );
                }
          };
          ooDoc.setSelectedTextAttributes(inlineMap);
        return true;
    }

    @Override
    public boolean doUpdateEvent(){
        return true;
    }
    @Override
    public boolean postSelectInsert() {
       return true;
    }

    @Override
    public boolean validateSelectedEdit() {
        return validateRefURI();
    }

    @Override
    public boolean validateSelectedInsert() {
        return validateRefURI();
    }

    @Override
    public boolean validateFullInsert() {
        return validateRefURI();
    }

    @Override
    public boolean validateFullEdit() {
        return validateRefURI();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_RefURI;
    private javax.swing.JTextField txt_RefURI;
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
        //!+FIX_THIS (ah, may-2012) Fix this for inline metadata editing
        //this.txt_RefURI.setText(getSectionMetadataValue("BungeniRefURI"));
        return;
    }
}
