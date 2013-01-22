/*
 *  Copyright (C) 2012 Africa i-Parliaments
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
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



package org.bungeni.extpanels.bungeni;

//~--- non-JDK imports --------------------------------------------------------

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import org.bungeni.editor.config.PluggableConfigReader.PluggableConfig;
import org.bungeni.editor.input.IInputDocumentReceiver;
import org.bungeni.extpanels.bungeni.BungeniDocument.Attachment;
import org.bungeni.extpanels.bungeni.BungeniListDocuments.BungeniListDocument;
import org.bungeni.extutils.FrameLauncher;
import org.bungeni.utils.BungeniDialog;
import org.jdom.Element;

/**
 * Reciever class for importing files from Bungeni
 * @author Ashok
 */
public class BungeniDocumentReceiver implements IInputDocumentReceiver {
    private static org.apache.log4j.Logger log =
        org.apache.log4j.Logger.getLogger(BungeniDocumentReceiver.class.getName());
    
    public String receiveDocument(final JFrame parentFrame, final PluggableConfig customConfig, HashMap inputParams) {
        //String sDocURL = (String) JOptionPane.showInputDialog(parentFrame, "Enter the URL of the document to Import",
        //                     "Import document from Bungeni", JOptionPane.QUESTION_MESSAGE);
        if (login(parentFrame)) {
            List<BungeniListDocument> listDocs = listDocuments(parentFrame, customConfig);
            if(listDocs.size() > 0 ) {
                //show list documents
                BungeniListDocument selectedDocument =  selectDocument(parentFrame, listDocs);
                if (selectedDocument != null){
                    BungeniDocument bungeniDoc = selectAttachment(parentFrame, selectedDocument, customConfig);
                    File fodf = loadAttachment(parentFrame, bungeniDoc, customConfig);
                    if (null != fodf ) {
                         return fodf.getAbsolutePath();   
                    }
                }
            }
        }   
        return null;
    }
    
    
    private BungeniListDocument selectDocument(final JFrame parentFrame, List<BungeniListDocument> listDocuments) {
        BungeniDialog               dlgdocs = new BungeniDialog(parentFrame, "Select a Bungeni Document", true);
        BungeniSelectDocument       panelSelectDocument = new BungeniSelectDocument(dlgdocs, listDocuments);
        dlgdocs.getContentPane().add(panelSelectDocument);
        dlgdocs.pack();
        FrameLauncher.CenterFrame(dlgdocs);
        dlgdocs.setVisible(true);
        return panelSelectDocument.getSelectedListDocument();
    }
    
    private BungeniDocument selectAttachment(final JFrame parentFrame, BungeniListDocument selectedDocument, final PluggableConfig customConfig) {
         String docUrlBase = this.documentURLBase(customConfig);
         BungeniDialog               dlgdoc = new BungeniDialog(parentFrame, selectedDocument.title, true);
         BungeniDocumentAttListPanel  panelShowDocument = new BungeniDocumentAttListPanel(
                    dlgdoc, 
                    selectedDocument,
                    docUrlBase + selectedDocument.idBase
                    );
         panelShowDocument.init();
         //!+CONTINUE_HERE
         dlgdoc.view(panelShowDocument);
         BungeniDocument aDoc = panelShowDocument.getDocument();
         if (aDoc != null ){
             Attachment attDoc = aDoc.getSelectedAttachment();
             if (attDoc != null) {
                 return aDoc;
             }
         }
         return null;
    }
    
    public File loadAttachment(JFrame pFrame, BungeniDocument bungeniDocument, final PluggableConfig customConfig){
        //first get the properties of the document 
         BungeniDialog  dlgatt = new BungeniDialog(pFrame, bungeniDocument.getSelectedAttachment().title, true);
         BungeniAttLoadingPanel  panelShowAtt = new BungeniAttLoadingPanel(
                 dlgatt,
                 bungeniDocument
                 );
         panelShowAtt.init();
         dlgatt.view(panelShowAtt);
         File fOdf = panelShowAtt.getOdfDocument();
        return fOdf;
    }
    
    private String searchURL(final PluggableConfig customConfig){
        Element searchElem = customConfig.customConfigElement.getChild("search");
        String sSearchBase = searchElem.getAttributeValue("base");
        return sSearchBase;
    }

    private String documentURLBase(final PluggableConfig customConfig){
        Element docElem = customConfig.customConfigElement.getChild("document");
        String sDocBase = docElem.getAttributeValue("base");
        return sDocBase;
    }

    private List<BungeniListDocument> listDocuments(JFrame parentFrame, final PluggableConfig customConfig) {
        String sSearchBungeniURL = searchURL(customConfig);
        // access the input URL
        List<BungeniListDocument> listDocuments = null;
        try {
            listDocuments = 
                BungeniServiceAccess.getInstance().availableDocumentsForEditing(sSearchBungeniURL);
            
        } catch (Exception ex) {
            log.error("Error while accessin url : " + sSearchBungeniURL , ex);
        }
        return listDocuments;
        //  BungeniDocument          jdoc   = new BungeniDocument(sDocURL, doc);
          //  Attachment               attDoc = promptForAttachment(parentFrame, jdoc);

       // if (attDoc != null) {
       //     importAttachment(jdoc, attDoc);
       // }
    }

    private boolean login(JFrame parentFrame) {
        BungeniDialog frm = new BungeniDialog(parentFrame, "Login", true);
        frm.initFrame();
        BungeniLogin login = new BungeniLogin(frm);
        frm.getContentPane().add(login);
        frm.pack();
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        return login.loginSuccessful();
    }

}


//~ Formatted by Jindent --- http://www.jindent.com
