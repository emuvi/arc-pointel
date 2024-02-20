/*
GNU Lesser General Public License

FormatAction
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
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;

/**
 * Class for implementing HTML format actions (NOTE : Does not toggle. User must use the "Clear Format" option to remove formatting correctly.)
 */
public class FormatAction extends StyledEditorKit.StyledTextAction {

    protected LdtDoc parentLDoc;
    HTML.Tag htmlTag;

    public FormatAction(LdtDoc lDoc, String actionName, HTML.Tag inTag) {
        super(actionName);
        parentLDoc = lDoc;
        htmlTag = inTag;
    }

    public void actionPerformed(ActionEvent ae) {
        JTextPane parentTextPane = parentLDoc.controle();
        String selText = parentTextPane.getSelectedText();
        int textLength = -1;
        if (selText != null) {
            textLength = selText.length();
        }
        if (selText == null || textLength < 1) {
            SimpleInfoDialog sidWarn = new SimpleInfoDialog(null, "", true, "Nenhum Texto Selecionado", SimpleInfoDialog.ERROR).mostra();
        } else {
            SimpleAttributeSet sasText = new SimpleAttributeSet(parentTextPane.getCharacterAttributes());
            sasText.addAttribute(htmlTag, new SimpleAttributeSet());
            int caretOffset = parentTextPane.getSelectionStart();
            parentTextPane.select(caretOffset, caretOffset + textLength);
            parentTextPane.setCharacterAttributes(sasText, false);
            parentLDoc.atualiza();
            parentTextPane.select(caretOffset, caretOffset + textLength);
        }
    }
}
