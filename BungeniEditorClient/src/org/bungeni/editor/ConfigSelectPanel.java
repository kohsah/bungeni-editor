/*
 * Copyright (C) 2013 PC
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
package org.bungeni.editor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.bungeni.editor.config.PluggableConfigReader;
import org.bungeni.editor.config.PluggableConfigReader.PluggableConfig;
import org.bungeni.utils.BungeniDialog;
import org.jdom.JDOMException;

/**
 *
 * @author Ashok Hariharan
 */
public class ConfigSelectPanel extends javax.swing.JPanel {

  private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConfigSelectPanel.class.getName());
  private BungeniDialog parentDialog = null;
  private boolean configSelected = false;
    /**
     * Creates new form ConfigSelectPanel
     */
    public ConfigSelectPanel(BungeniDialog dlg) {
        initComponents();
        parentDialog = dlg;
        init();
    }

    private void init() {
       try {
        
        DefaultListModel model = new DefaultListModel();
        model.addElement(PluggableConfigReader.getInstance().getDefaultConfig());
        for (PluggableConfig object : PluggableConfigReader.getInstance().getConfigs()) {
            if (!object.configDefault){
                model.addElement(object);
            }
        }
        this.listConfigs.setModel(model);
        this.listConfigs.setSelectedIndex(0);
       } catch(JDOMException ex ) {
           log.error("Error while loading config info ", ex);
       }
        this.listConfigs.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if (index != -1) {
                       selectAction();
                    }
                }
            }   
        });
    }
    
    public boolean getConfigSelected(){
        return configSelected ; 
    }
    
    private void selectAction(){
        PluggableConfig cfg = (PluggableConfig) this.listConfigs.getSelectedValue();
        configSelected = true;
        try {
            //write to pluggable config
            PluggableConfigReader.getInstance().makeDefault(cfg);
        } catch (JDOMException ex) {
            log.error("Error while setting default config" , ex);
        } finally {
            parentDialog.dispose();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollConfigs = new javax.swing.JScrollPane();
        listConfigs = new javax.swing.JList();
        btnSelectConfig = new javax.swing.JButton();

        scrollConfigs.setViewportView(listConfigs);

        btnSelectConfig.setText("Use Config");
        btnSelectConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollConfigs, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnSelectConfig)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollConfigs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelectConfig))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectConfigActionPerformed
        // TODO add your handling code here:
        selectAction();
    }//GEN-LAST:event_btnSelectConfigActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectConfig;
    private javax.swing.JList listConfigs;
    private javax.swing.JScrollPane scrollConfigs;
    // End of variables declaration//GEN-END:variables
}