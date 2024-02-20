/*
GNU Lesser General Public License

SetFontFamilyAction
Copyright (C) 2004 Howard Kistler

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

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import pin.libitr.QuestaoFonte;

public class FonteNomeAcao extends StyledEditorKit.FontFamilyAction {

    protected String nome;
    protected LdtDoc proDoc;

    public FonteNomeAcao(LdtDoc lDoc, String actionName) {
        super(actionName, "");
        this.nome = actionName;
        proDoc = lDoc;
    }

    public void actionPerformed(ActionEvent ae) {
        String nm = "";
        if ("[FONTSELECTOR]".equals(nome)) {
            if (nm != null) {
                MutableAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attr, nm);
                proDoc.mudaAtributos(attr, true);
            }
        } else {
            Font fnt = new Font(nm, Font.PLAIN, 12);
            new QuestaoFonte(fnt) {
                @Override
                public Boolean aoEscolher(Font comEscolhida) {
                    MutableAttributeSet attr = new SimpleAttributeSet();
                    StyleConstants.setFontFamily(attr, comEscolhida.getFamily());
                    proDoc.mudaAtributos(attr, true);
                    return true;
                }
            }.mostra("Escolha a Fonte", true, false, false);
        }
    }
}
