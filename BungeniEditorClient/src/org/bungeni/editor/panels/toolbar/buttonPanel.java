/*
 * buttonPanel.java
 *
 * Created on Apr 27, 2009, 11:58:51 AM
 */

package org.bungeni.editor.panels.toolbar;

import java.awt.event.ActionListener;

/**
 *
 * @author Ashok Hariharan
 */
public class buttonPanel extends javax.swing.JPanel {

    /** Creates new form buttonPanel */
    public buttonPanel() {
        initComponents();
    }

    /**
     * Sets the button text for the button action and adds an action listener to it.
     * @param text - the display text for the button
     * @param btnListener - the action listener associated wiht the button
     */
    public buttonPanel(String text, ActionListener btnListener) {
        initComponents();
        setButtonText(text);
        setButtonActionListener(btnListener);
    }

    public void setButtonText(String text) {
        this.btnAction.setText(text);
    }

    public void setButtonActionListener(ActionListener btnListener) {
        this.btnAction.addActionListener(btnListener);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAction = new javax.swing.JButton();

        btnAction.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        btnAction.setText("some text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAction)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    // End of variables declaration//GEN-END:variables

}