/*
GNU Lesser General Public License

ListAutomationAction
Copyright (C) 2000 Howard Kistler

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package pin.ldtdoc;

import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Class for automatically creating bulleted lists from selected text
 */
public class ListAutomationAction extends HTMLEditorKit.InsertHTMLTextAction {

    protected LdtDoc parentLDoc;
    private HTML.Tag baseTag;
    private String sListType;
    private HTMLUtilities htmlUtilities;

    public ListAutomationAction(LdtDoc lDoc, String sLabel, HTML.Tag listType) {
        super(sLabel, "", listType, HTML.Tag.LI);
        parentLDoc = lDoc;
        baseTag = listType;
        htmlUtilities = new HTMLUtilities(lDoc);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            JEditorPane jepEditor = (JEditorPane) (parentLDoc.controle());
            String selTextBase = jepEditor.getSelectedText();
            int textLength = -1;
            if (selTextBase != null) {
                textLength = selTextBase.length();
            }
            if (selTextBase == null || textLength < 1) {
                int pos = parentLDoc.pCursor();
                parentLDoc.mCursor(pos);
                if (ae.getActionCommand() != "newListPoint") {
                    if (htmlUtilities.checkParentsTag(HTML.Tag.OL) || htmlUtilities.checkParentsTag(HTML.Tag.UL)) {
                        new SimpleInfoDialog(null, "Erro", true, "Lista Interna Não Suportado").mostra();
                        return;
                    }
                }
                String sListType = (baseTag == HTML.Tag.OL ? "ol" : "ul");
                StringBuffer sbNew = new StringBuffer();
                if (htmlUtilities.checkParentsTag(baseTag)) {
                    sbNew.append("<li></li>");
                    insertHTML(parentLDoc.controle(), parentLDoc.pDoc(), parentLDoc.controle().getCaretPosition(), sbNew.toString(), 0, 0, HTML.Tag.LI);
                } else {
                    sbNew.append("<" + sListType + "><li></li></" + sListType + "><p>&nbsp;</p>");
                    insertHTML(parentLDoc.controle(), parentLDoc.pDoc(), parentLDoc.controle().getCaretPosition(), sbNew.toString(), 0, 0, (sListType.equals("ol") ? HTML.Tag.OL : HTML.Tag.UL));
                }
                parentLDoc.atualiza();
            } else {
                String sListType = (baseTag == HTML.Tag.OL ? "ol" : "ul");
                HTMLDocument htmlDoc = (HTMLDocument) (jepEditor.getDocument());
                int iStart = jepEditor.getSelectionStart();
                int iEnd = jepEditor.getSelectionEnd();
                String selText = htmlDoc.getText(iStart, iEnd - iStart);
                StringBuffer sbNew = new StringBuffer();
                String sToken = ((selText.indexOf("\r") > -1) ? "\r" : "\n");
                StringTokenizer stTokenizer = new StringTokenizer(selText, sToken);
                sbNew.append("<" + sListType + ">");
                while (stTokenizer.hasMoreTokens()) {
                    sbNew.append("<li>");
                    sbNew.append(stTokenizer.nextToken());
                    sbNew.append("</li>");
                }
                sbNew.append("</" + sListType + "><p>&nbsp;</p>");
                htmlDoc.remove(iStart, iEnd - iStart);
                insertHTML(jepEditor, htmlDoc, iStart, sbNew.toString(), 1, 1, null);
            }
        } catch (BadLocationException ble) {
        }
    }
}
