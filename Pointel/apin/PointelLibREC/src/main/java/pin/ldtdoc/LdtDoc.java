package pin.ldtdoc;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.ChangedCharSetException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import pin.libutl.Utl;
import pin.libutl.UtlArq;

public class LdtDoc extends JPanel implements KeyListener {

    private JTextPane controle;
    private JScrollPane rolagem;
    private HTMLKit htmlKit;
    private HTMLDoc htmlDoc;
    private StyleSheet estilosDoc;
    private HTMLUtilities htmlUtilities = new HTMLUtilities(this);

    private StyledEditorKit.BoldAction actNegrito;
    private StyledEditorKit.ItalicAction actItalico;
    private StyledEditorKit.UnderlineAction actSublinhado;
    private FormatAction actRiscado;
    private FormatAction actSobrescrito;
    private FormatAction actSubescrito;
    private ListAutomationAction actLista;
    private ListAutomationAction actOrdenada;
    private FonteNomeAcao actFonte;
    private AlignAction actEsquerda;
    private AlignAction actCentro;
    private AlignAction actDireita;
    private AlignAction actJustificado;
    private CustomAction actAncora;

    protected UndoManager undoMngr;
    protected UndoAction undoAction;
    protected RedoAction redoAction;

    private final boolean usaFormularioIndicador = true;
    private final String corFormularioIndicador = "#eeeeee";

    private java.awt.datatransfer.Clipboard sysClipboard;
    private SecurityManager secManager;

    private String lastSearchFindTerm = null;
    private String lastSearchReplaceTerm = null;
    private boolean lastSearchCaseSetting = false;
    private boolean lastSearchTopSetting = false;

    private File arquivo = null;
    private String imageChooserStartDir = ".";

    private int indent = 0;
    private final int indentStep = 4;

    private final String[] extsHTML = {"html", "htm", "shtml"};
    private final String[] extsCSS = {"css"};
    private final String[] extsIMG = {"gif", "png", "jpg", "jpeg"};
    private final String[] extsRTF = {"rtf"};

    private int CTRLKEY = KeyEvent.CTRL_MASK;

    public LdtDoc() {
        super(new BorderLayout());
        secManager = System.getSecurityManager();
        if (secManager != null) {
            try {
                secManager.checkSystemClipboardAccess();
                sysClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            } catch (SecurityException se) {
                sysClipboard = null;
            }
        }
        if (!(GraphicsEnvironment.isHeadless())) {
            CTRLKEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        }
        controle = new JTextPane();
        htmlKit = new HTMLKit();
        htmlDoc = (HTMLDoc) (htmlKit.createDefaultDocument());
        htmlDoc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        htmlDoc.setPreservesUnknownTags(true);
        estilosDoc = htmlDoc.getStyleSheet();
        htmlKit.setDefaultCursor(new Cursor(Cursor.TEXT_CURSOR));
        controle.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        controle.setEditorKit(htmlKit);
        controle.setDocument(htmlDoc);
        controle.setMargin(new Insets(4, 4, 4, 4));
        controle.addKeyListener(this);
        controle.setDragEnabled(true);
        controle.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent ce) {
                trataMudancaCursor(ce);
            }
        });
        undoMngr = new UndoManager();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        controle.getDocument().addUndoableEditListener(new CustomUndoableEditListener());

        controle.setCaretPosition(0);
        Hashtable<Object, Action> actions = new Hashtable<Object, Action>();
        Action[] actionsArray = controle.getActions();
        for (Action a : actionsArray) {
            actions.put(a.getValue(Action.NAME), a);
        }
        actNegrito = new StyledEditorKit.BoldAction();
        actItalico = new StyledEditorKit.ItalicAction();
        actSublinhado = new StyledEditorKit.UnderlineAction();
        actRiscado = new FormatAction(this, "Riscado", HTML.Tag.STRIKE);
        actSobrescrito = new FormatAction(this, "Sobrescrito", HTML.Tag.SUP);
        actSubescrito = new FormatAction(this, "Subscrito", HTML.Tag.SUB);
        actLista = new ListAutomationAction(this, "Lista Comum", HTML.Tag.UL);
        actOrdenada = new ListAutomationAction(this, "Lista Ordenada", HTML.Tag.OL);
        actFonte = new FonteNomeAcao(this, "[MENUFONTSELECTOR]");
        actEsquerda = new AlignAction(this, "Esquerda", StyleConstants.ALIGN_LEFT);
        actCentro = new AlignAction(this, "Centro", StyleConstants.ALIGN_CENTER);
        actDireita = new AlignAction(this, "Direita", StyleConstants.ALIGN_RIGHT);
        actJustificado = new AlignAction(this, "Justificado", StyleConstants.ALIGN_JUSTIFIED);
        actAncora = new CustomAction(this, "Vínculo", HTML.Tag.A);
        rolagem = new JScrollPane(controle);
        rolagem.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rolagem.setPreferredSize(new Dimension(400, 400));
        rolagem.setMinimumSize(new Dimension(32, 32));
        add(rolagem, BorderLayout.CENTER);
    }

    public LdtDoc novo() {
        htmlDoc = (HTMLDoc) (htmlKit.createDefaultDocument());
        htmlDoc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        htmlDoc.setPreservesUnknownTags(true);
        controle.setText("<HTML><BODY></BODY></HTML>");
        registraDocumento(htmlDoc);
        return this;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Element elem;
        int pos = this.pCursor();
        int repos = -1;
        try {
            switch (ke.getKeyChar()) {
                case KeyEvent.VK_BACK_SPACE:
                    if (pos > 0) {
                        if (controle.getSelectedText() != null) {
                            htmlUtilities.delete();
                        } else {
                            int sOffset = htmlDoc.getParagraphElement(pos).getStartOffset();
                            if (sOffset == controle.getSelectionStart()) {
                                boolean content = true;
                                if (htmlUtilities.checkParentsTag(HTML.Tag.LI)) {
                                    elem = htmlUtilities.getListItemParent();
                                    content = false;
                                    int so = elem.getStartOffset();
                                    int eo = elem.getEndOffset();
                                    if (so + 1 < eo) {
                                        char[] temp = controle.getText(so, eo - so).toCharArray();
                                        for (char c : temp) {
                                            if (!(new Character(c)).isWhitespace(c)) {
                                                content = true;
                                            }
                                        }
                                    }
                                    if (!content) {
                                        elem.getParentElement();
                                        htmlUtilities.removeTag(elem, true);
                                        this.mCursor(sOffset - 1);
                                        return;
                                    } else {
                                        controle.setCaretPosition(controle.getCaretPosition() - 1);
                                        controle.moveCaretPosition(controle.getCaretPosition() - 2);
                                        controle.replaceSelection("");
                                        return;
                                    }
                                } else if (htmlUtilities.checkParentsTag(HTML.Tag.TABLE)) {
                                    controle.setCaretPosition(controle.getCaretPosition() - 1);
                                    ke.consume();
                                    return;
                                }
                            }
                            controle.replaceSelection("");
                        }
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    insertNonbreakingSpace();
                    ke.consume();
                    break;
                case KeyEvent.VK_ENTER:
                    if (htmlUtilities.checkParentsTag(HTML.Tag.UL) == true | htmlUtilities.checkParentsTag(HTML.Tag.OL) == true) {
                        elem = htmlUtilities.getListItemParent();
                        int so = elem.getStartOffset();
                        int eo = elem.getEndOffset();
                        char[] temp = this.controle().getText(so, eo - so).toCharArray();
                        boolean content = false;
                        for (char c : temp) {
                            if (!(new Character(c)).isWhitespace(c)) {
                                content = true;
                            }
                        }
                        if (content) {
                            int end = -1;
                            int j = temp.length;
                            do {
                                j--;
                                if (new Character(temp[j]).isLetterOrDigit(temp[j])) {
                                    end = j;
                                }
                            } while (end == -1 && j >= 0);
                            j = end;
                            do {
                                j++;
                                if (!new Character(temp[j]).isSpaceChar(temp[j])) {
                                    repos = j - end - 1;
                                }
                            } while (repos == -1 && j < temp.length);
                            if (repos == -1) {
                                repos = 0;
                            }
                        }
                        if (elem.getStartOffset() == elem.getEndOffset() || !content) {
                            manageListElement(elem);
                        } else {
                            if (this.pCursor() + 1 == elem.getEndOffset()) {
                                insertListStyle(elem);
                                this.mCursor(pos - repos);
                            } else {
                                int caret = this.pCursor();
                                String tempString = this.controle().getText(caret, eo - caret);
                                this.controle().select(caret, eo - 1);
                                this.controle().replaceSelection("");
                                htmlUtilities.insertListElement(tempString);
                                Element newLi = htmlUtilities.getListItemParent();
                                this.mCursor(newLi.getEndOffset() - 1);
                            }
                        }
                    } else if (ke.getModifiers() == CTRLKEY) {
                        insertBreak();
                        ke.consume();
                    } else {
                        insertParagraph();
                        ke.consume();
                    }
                    break;
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
            ke.consume();
        } else if (ke.getKeyChar() == KeyEvent.VK_SPACE) {
            ke.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
            ke.consume();
        } else if (ke.getKeyChar() == KeyEvent.VK_SPACE) {
            ke.consume();
        }
    }

    public void registraDocumento(HTMLDoc htmlDoc) {
        controle.setDocument(htmlDoc);
        controle.getDocument().addUndoableEditListener(new CustomUndoableEditListener());
        controle.setCaretPosition(0);
        purgeUndos();
    }

    public void mudaAtributos(AttributeSet osAtributos, boolean substitui) {
        int p0 = controle.getSelectionStart();
        int p1 = controle.getSelectionEnd();
        if (p0 != p1) {
            htmlDoc.setCharacterAttributes(p0, p1 - p0, osAtributos, substitui);
        }
        MutableAttributeSet inputAttributes = htmlKit.getInputAttributes();
        if (substitui) {
            inputAttributes.removeAttributes(inputAttributes);
        }
        inputAttributes.addAttributes(osAtributos);
    }

    public void insert(String html, Tag tag) throws Exception {
        if (controle.getSelectedText() != null) {
            controle.replaceSelection("");
        }
        int caret = controle.getCaretPosition();
        int caretPlus = 1;
        Element caretElm = null;
        if (tag.isBlock()) {
            caretElm = htmlDoc.getParagraphElement(caret);
        } else {
            caretElm = htmlDoc.getCharacterElement(caret);
        }
        Element parentEl = caretElm.getParentElement();
        String parentName = parentEl.getName();
        boolean parentEmpty = parentEl.getEndOffset() - parentEl.getStartOffset() <= 1;
        if (parentEmpty && tag.breaksFlow() && !tag.isBlock()) {
            htmlDoc.insertAfterStart(parentEl, "&nbsp;");
            caretPlus++;
        }
        HTML.Tag parentTag = HTML.getTag(parentName);
        HTMLEditorKit.InsertHTMLTextAction Action = new HTMLEditorKit.InsertHTMLTextAction("genericHTML", html, parentTag, tag);
        Action.actionPerformed(new ActionEvent(controle, ActionEvent.ACTION_PERFORMED, "genericHTML"));
        mCursor(caret + caretPlus);
    }

    public void insertListStyle(Element element) throws Exception {
        if ("ol".equals(element.getParentElement().getName())) {
            actOrdenada.actionPerformed(new ActionEvent(new Object(), 0, "newListPoint"));
        } else {
            actLista.actionPerformed(new ActionEvent(new Object(), 0, "newListPoint"));
        }
    }

    public void insertTable(Hashtable attribs, String[] fieldNames, String[] fieldTypes, String[] fieldValues) throws Exception {
        int caretPos = controle.getCaretPosition();
        StringBuilder compositeElement = new StringBuilder("<TABLE");
        if (attribs != null && attribs.size() > 0) {
            Enumeration attribEntries = attribs.keys();
            while (attribEntries.hasMoreElements()) {
                Object entryKey = attribEntries.nextElement();
                Object entryValue = attribs.get(entryKey);
                if (entryValue != null && entryValue != "") {
                    compositeElement.append(" " + entryKey + "=" + '"' + entryValue + '"');
                }
            }
        }
        int rows = 0;
        int cols = 0;
        if (fieldNames != null && fieldNames.length > 0) {
            PropertiesDialog propertiesDialog = new PropertiesDialog(null, fieldNames, fieldTypes, fieldValues, "Tabela", true);
            propertiesDialog.setVisible(true);
            String decision = propertiesDialog.getDecisionValue();
            if (decision.equals("Cancela")) {
                propertiesDialog.dispose();
                propertiesDialog = null;
                return;
            } else {
                for (String fieldName : fieldNames) {
                    String propValue = propertiesDialog.getFieldValue(fieldName);
                    if (propValue != null && propValue != "" && propValue.length() > 0) {
                        if (fieldName.equals("rows")) {
                            rows = Integer.parseInt(propValue);
                        } else if (fieldName.equals("cols")) {
                            cols = Integer.parseInt(propValue);
                        } else {
                            compositeElement.append(" " + fieldName + "=" + '"' + propValue + '"');
                        }
                    }
                }
            }
            propertiesDialog.dispose();
            propertiesDialog = null;
        }
        compositeElement.append(">");
        for (int i = 0; i < rows; i++) {
            compositeElement.append("<TR>");
            for (int j = 0; j < cols; j++) {
                compositeElement.append("<TD></TD>");
            }
            compositeElement.append("</TR>");
        }
        compositeElement.append("</TABLE>&nbsp;");
        htmlKit.insertHTML(htmlDoc, caretPos, compositeElement.toString(), 0, 0, HTML.Tag.TABLE);
        controle.setCaretPosition(caretPos + 1);
        atualiza();
    }

    public void editTable() {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(caretPos);
        Element elementParent = element.getParentElement();
        while (elementParent != null && !elementParent.getName().equals("table")) {
            elementParent = elementParent.getParentElement();
        }
        if (elementParent != null) {
            HTML.Attribute[] fieldKeys = {HTML.Attribute.BORDER, HTML.Attribute.CELLSPACING, HTML.Attribute.CELLPADDING, HTML.Attribute.WIDTH, HTML.Attribute.VALIGN};
            String[] fieldNames = {"border", "cellspacing", "cellpadding", "width", "valign"};
            String[] fieldTypes = {"text", "text", "text", "text", "combo"};
            String[] fieldValues = {"", "", "", "", "top,middle,bottom,"};
            MutableAttributeSet myatr = (MutableAttributeSet) elementParent.getAttributes();
            for (int i = 0; i < fieldNames.length; i++) {
                if (myatr.isDefined(fieldKeys[i])) {
                    if (fieldTypes[i].equals("combo")) {
                        fieldValues[i] = myatr.getAttribute(fieldKeys[i]).toString() + "," + fieldValues[i];
                    } else {
                        fieldValues[i] = myatr.getAttribute(fieldKeys[i]).toString();
                    }
                }
            }
            PropertiesDialog propertiesDialog = new PropertiesDialog(null, fieldNames, fieldTypes, fieldValues, "Edita Tabela", true);
            propertiesDialog.setVisible(true);
            if (!propertiesDialog.getDecisionValue().equals("Cancela")) {
                String myAtributes = "";
                SimpleAttributeSet mynew = new SimpleAttributeSet();
                for (int i = 0; i < fieldNames.length; i++) {
                    String propValue = propertiesDialog.getFieldValue(fieldNames[i]);
                    if (propValue != null && propValue.length() > 0) {
                        myAtributes = myAtributes + fieldNames[i] + "=\"" + propValue + "\" ";
                        mynew.addAttribute(fieldKeys[i], propValue);
                    }
                }
                htmlDoc.replaceAttributes(elementParent, mynew, HTML.Tag.TABLE);
                atualiza();
            }
            propertiesDialog.dispose();
        } else {
            new SimpleInfoDialog(null, "Tabela", true, "Cursor Não Está em Uma Tabela").mostra();
        }
    }

    public void editCell() {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(caretPos);
        Element elementParent = element.getParentElement();
        while (elementParent != null && !elementParent.getName().equals("td")) {
            elementParent = elementParent.getParentElement();
        }
        if (elementParent != null) {
            HTML.Attribute[] fieldKeys = {HTML.Attribute.WIDTH, HTML.Attribute.HEIGHT, HTML.Attribute.ALIGN, HTML.Attribute.VALIGN, HTML.Attribute.BGCOLOR};
            String[] fieldNames = {"width", "height", "align", "valign", "bgcolor"};
            String[] fieldTypes = {"text", "text", "combo", "combo", "combo"};
            String[] fieldValues = {"", "", "left,right,center", "top,middle,bottom", "none,aqua,black,fuchsia,gray,green,lime,maroon,navy,olive,purple,red,silver,teal,white,yellow"};
            MutableAttributeSet myatr = (MutableAttributeSet) elementParent.getAttributes();
            for (int i = 0; i < fieldNames.length; i++) {
                if (myatr.isDefined(fieldKeys[i])) {
                    if (fieldTypes[i].equals("combo")) {
                        fieldValues[i] = myatr.getAttribute(fieldKeys[i]).toString() + "," + fieldValues[i];
                    } else {
                        fieldValues[i] = myatr.getAttribute(fieldKeys[i]).toString();
                    }
                }
            }
            PropertiesDialog propertiesDialog = new PropertiesDialog(null, fieldNames, fieldTypes, fieldValues, "Edita Célula", true);
            propertiesDialog.setVisible(true);
            if (!propertiesDialog.getDecisionValue().equals("Cancela")) {
                String myAtributes = "";
                SimpleAttributeSet mynew = new SimpleAttributeSet();
                for (int i = 0; i < fieldNames.length; i++) {
                    String propValue = propertiesDialog.getFieldValue(fieldNames[i]);
                    if (propValue != null && propValue.length() > 0) {
                        myAtributes = myAtributes + fieldNames[i] + "=\"" + propValue + "\" ";
                        mynew.addAttribute(fieldKeys[i], propValue);
                    }
                }
                htmlDoc.replaceAttributes(elementParent, mynew, HTML.Tag.TD);
                atualiza();
            }
            propertiesDialog.dispose();
        } else {
            new SimpleInfoDialog(null, "Célula", true, "Cursor Não Está em Uma Célula").mostra();
        }
    }

    public void insertTableRow() {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(controle.getCaretPosition());
        Element elementParent = element.getParentElement();
        int startPoint = -1;
        int columnCount = -1;
        while (elementParent != null && !elementParent.getName().equals("body")) {
            if (elementParent.getName().equals("tr")) {
                startPoint = elementParent.getStartOffset();
                columnCount = elementParent.getElementCount();
                break;
            } else {
                elementParent = elementParent.getParentElement();
            }
        }
        if (startPoint > -1 && columnCount > -1) {
            controle.setCaretPosition(startPoint);
            StringBuffer sRow = new StringBuffer();
            sRow.append("<TR>");
            for (int i = 0; i < columnCount; i++) {
                sRow.append("<TD></TD>");
            }
            sRow.append("</TR>");
            ActionEvent actionEvent = new ActionEvent(controle, 0, "insertTableRow");
            new HTMLEditorKit.InsertHTMLTextAction("insertTableRow", sRow.toString(), HTML.Tag.TABLE, HTML.Tag.TR).actionPerformed(actionEvent);
            atualiza();
            controle.setCaretPosition(caretPos);
        }
    }

    public void insertTableColumn() {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(controle.getCaretPosition());
        Element elementParent = element.getParentElement();
        int startPoint = -1;
        int rowCount = -1;
        int cellOffset = 0;
        while (elementParent != null && !elementParent.getName().equals("body")) {
            if (elementParent.getName().equals("table")) {
                startPoint = elementParent.getStartOffset();
                rowCount = elementParent.getElementCount();
                break;
            } else if (elementParent.getName().equals("tr")) {
                int rowCells = elementParent.getElementCount();
                for (int i = 0; i < rowCells; i++) {
                    Element currentCell = elementParent.getElement(i);
                    if (controle.getCaretPosition() >= currentCell.getStartOffset() && controle.getCaretPosition() <= currentCell.getEndOffset()) {
                        cellOffset = i;
                    }
                }
                elementParent = elementParent.getParentElement();
            } else {
                elementParent = elementParent.getParentElement();
            }
        }
        if (startPoint > -1 && rowCount > -1) {
            controle.setCaretPosition(startPoint);
            String sCell = "<TD></TD>";
            ActionEvent actionEvent = new ActionEvent(controle, 0, "insertTableCell");
            for (int i = 0; i < rowCount; i++) {
                Element row = elementParent.getElement(i);
                Element whichCell = row.getElement(cellOffset);
                controle.setCaretPosition(whichCell.getStartOffset());
                new HTMLEditorKit.InsertHTMLTextAction("insertTableCell", sCell, HTML.Tag.TR, HTML.Tag.TD, HTML.Tag.TH, HTML.Tag.TD).actionPerformed(actionEvent);
            }
            atualiza();
            controle.setCaretPosition(caretPos);
        }
    }

    public void insertTableCell() {
        String sCell = "<TD></TD>";
        ActionEvent actionEvent = new ActionEvent(controle, 0, "insertTableCell");
        new HTMLEditorKit.InsertHTMLTextAction("insertTableCell", sCell, HTML.Tag.TR, HTML.Tag.TD, HTML.Tag.TH, HTML.Tag.TD).actionPerformed(actionEvent);
        atualiza();
    }

    public void deleteTableRow() throws Exception {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(controle.getCaretPosition());
        Element elementParent = element.getParentElement();
        int startPoint = -1;
        int endPoint = -1;
        while (elementParent != null && !elementParent.getName().equals("body")) {
            if (elementParent.getName().equals("tr")) {
                startPoint = elementParent.getStartOffset();
                endPoint = elementParent.getEndOffset();
                break;
            } else {
                elementParent = elementParent.getParentElement();
            }
        }
        if (startPoint > -1 && endPoint > startPoint) {
            htmlDoc.remove(startPoint, endPoint - startPoint);
            controle.setDocument(htmlDoc);
            registraDocumento(htmlDoc);
            atualiza();
            if (caretPos >= htmlDoc.getLength()) {
                caretPos = htmlDoc.getLength() - 1;
            }
            controle.setCaretPosition(caretPos);
        }
    }

    public void deleteTableColumn() throws Exception {
        int caretPos = controle.getCaretPosition();
        Element element = htmlDoc.getCharacterElement(controle.getCaretPosition());
        Element elementParent = element.getParentElement();
        Element elementCell = (Element) null;
        Element elementRow = (Element) null;
        Element elementTable = (Element) null;
        while (elementParent != null && !elementParent.getName().equals("body")) {
            if (elementParent.getName().equals("td")) {
                elementCell = elementParent;
            } else if (elementParent.getName().equals("tr")) {
                elementRow = elementParent;
            } else if (elementParent.getName().equals("table")) {
                elementTable = elementParent;
            }
            elementParent = elementParent.getParentElement();
        }
        int whichColumn = -1;
        if (elementCell != null && elementRow != null && elementTable != null) {
            int myOffset = 0;
            for (int i = 0; i < elementRow.getElementCount(); i++) {
                if (elementCell == elementRow.getElement(i)) {
                    whichColumn = i;
                    myOffset = elementCell.getEndOffset();
                }
            }
            if (whichColumn > -1) {
                int mycaretPos = caretPos;
                for (int i = 0; i < elementTable.getElementCount(); i++) {
                    elementRow = elementTable.getElement(i);
                    elementCell = (elementRow.getElementCount() > whichColumn ? elementRow.getElement(whichColumn) : elementRow.getElement(elementRow.getElementCount() - 1));
                    int columnCellStart = elementCell.getStartOffset();
                    int columnCellEnd = elementCell.getEndOffset();
                    int dif = columnCellEnd - columnCellStart;
                    if (columnCellStart < myOffset) {
                        mycaretPos = mycaretPos - dif;
                        myOffset = myOffset - dif;
                    }
                    if (whichColumn == 0) {
                        htmlDoc.remove(columnCellStart, dif);
                    } else {
                        htmlDoc.remove(columnCellStart - 1, dif);
                    }
                }
                controle.setDocument(htmlDoc);
                registraDocumento(htmlDoc);
                atualiza();
                if (mycaretPos >= htmlDoc.getLength()) {
                    mycaretPos = htmlDoc.getLength() - 1;
                }
                if (mycaretPos < 1) {
                    mycaretPos = 1;
                }
                controle.setCaretPosition(mycaretPos);
            }
        }
    }

    public void insertParagraph() throws Exception {
        insert("<P></P>", Tag.P);
    }

    public void insertBreak() throws Exception {
        insert("<BR>", Tag.BR);
    }

    public void insertLine() throws Exception {
        insert("<HR>", Tag.HR);
    }

    public void insertUnicode(int index) throws Exception {
        new UnicodeDialog(this, "Unicode", false, index);
    }

    public void insertUnicodeChar(String sChar) throws Exception {
        int caretPos = controle.getCaretPosition();
        if (sChar != null) {
            htmlDoc.insertString(caretPos, sChar, controle.getInputAttributes());
            controle.setCaretPosition(caretPos + 1);
        }
    }

    public void insertNonbreakingSpace() throws Exception {
        int caretPos = controle.getCaretPosition();
        htmlDoc.insertString(caretPos, "\240", controle.getInputAttributes());
        controle.setCaretPosition(caretPos + 1);
    }

    public void insertFormElement(HTML.Tag baseTag, String baseElement, Hashtable attribs, String[] fieldNames, String[] fieldTypes, String[] fieldValues, boolean hasClosingTag) throws Exception {
        int caretPos = controle.getCaretPosition();
        StringBuffer compositeElement = new StringBuffer("<" + baseElement);
        if (attribs != null && attribs.size() > 0) {
            Enumeration attribEntries = attribs.keys();
            while (attribEntries.hasMoreElements()) {
                Object entryKey = attribEntries.nextElement();
                Object entryValue = attribs.get(entryKey);
                if (entryValue != null && entryValue != "") {
                    compositeElement.append(" " + entryKey + "=" + '"' + entryValue + '"');
                }
            }
        }
        if (fieldNames != null && fieldNames.length > 0) {
            PropertiesDialog propertiesDialog = new PropertiesDialog(null, fieldNames, fieldTypes, fieldValues, "Fonte", true);
            propertiesDialog.setVisible(true);
            String decision = propertiesDialog.getDecisionValue();
            if (decision.equals("Cancela")) {
                propertiesDialog.dispose();
                propertiesDialog = null;
                return;
            } else {
                for (String fieldName : fieldNames) {
                    String propValue = propertiesDialog.getFieldValue(fieldName);
                    if (propValue != null && propValue.length() > 0) {
                        if (fieldName.equals("checked")) {
                            if (propValue.equals("true")) {
                                compositeElement.append(" " + fieldName + "=" + '"' + propValue + '"');
                            }
                        } else {
                            compositeElement.append(" " + fieldName + "=" + '"' + propValue + '"');
                        }
                    }
                }
            }
            propertiesDialog.dispose();
            propertiesDialog = null;
        }
        if (usaFormularioIndicador && baseElement.equals("form")) {
            compositeElement.append(" bgcolor=" + '"' + corFormularioIndicador + '"');
        }
        compositeElement.append(">");
        if (baseTag == HTML.Tag.FORM) {
            compositeElement.append("<P>&nbsp;</P>");
            compositeElement.append("<P>&nbsp;</P>");
            compositeElement.append("<P>&nbsp;</P>");
        }
        if (hasClosingTag) {
            compositeElement.append("</" + baseElement + ">");
        }
        if (baseTag == HTML.Tag.FORM) {
            compositeElement.append("<P>&nbsp;</P>");
        }
        htmlKit.insertHTML(htmlDoc, caretPos, compositeElement.toString(), 0, 0, baseTag);
        // jtpMain.setCaretPosition(caretPos + 1);
        atualiza();
    }

    public void insertFormElement(HTML.Tag baseTag, String baseElement, Hashtable attribs, String[] fieldNames, String[] fieldTypes, boolean hasClosingTag) throws Exception {
        insertFormElement(baseTag, baseElement, attribs, fieldNames, fieldTypes, new String[fieldNames.length], hasClosingTag);
    }

    public void manageListElement(Element element) {
        Element h = htmlUtilities.getListItemParent();
        h.getParentElement();
        if (h != null) {
            htmlUtilities.removeTag(h, true);
        }
    }

    public void doSearch(String searchFindTerm, String searchReplaceTerm, boolean bIsFindReplace, boolean bCaseSensitive, boolean bStartAtTop) {
        boolean bReplaceAll = false;
        JTextComponent searchPane = (JTextComponent) controle;
        if (searchFindTerm == null || (bIsFindReplace && searchReplaceTerm == null)) {
            SearchDialog sdSearchInput = new SearchDialog(null, "Procura", true, bIsFindReplace, bCaseSensitive, bStartAtTop);
            searchFindTerm = sdSearchInput.getFindTerm();
            searchReplaceTerm = sdSearchInput.getReplaceTerm();
            bCaseSensitive = sdSearchInput.getCaseSensitive();
            bStartAtTop = sdSearchInput.getStartAtTop();
            bReplaceAll = sdSearchInput.getReplaceAll();
        }
        if (searchFindTerm != null && (!bIsFindReplace || searchReplaceTerm != null)) {
            if (bReplaceAll) {
                int results = findText(searchFindTerm, searchReplaceTerm, bCaseSensitive, 0);
                int findOffset = 0;
                if (results > -1) {
                    while (results > -1) {
                        findOffset = findOffset + searchReplaceTerm.length();
                        results = findText(searchFindTerm, searchReplaceTerm, bCaseSensitive, findOffset);
                    }
                } else {
                    new SimpleInfoDialog(null, "", true, "Não Encontrado" + ":\n" + searchFindTerm, SimpleInfoDialog.WARNING).mostra();
                }
            } else {
                int results = findText(searchFindTerm, searchReplaceTerm, bCaseSensitive, (bStartAtTop ? 0 : searchPane.getCaretPosition()));
                if (results == -1) {
                    new SimpleInfoDialog(null, "", true, "Não Encontrado" + ":\n" + searchFindTerm, SimpleInfoDialog.WARNING).mostra();
                }
            }
            lastSearchFindTerm = new String(searchFindTerm);
            if (searchReplaceTerm != null) {
                lastSearchReplaceTerm = new String(searchReplaceTerm);
            } else {
                lastSearchReplaceTerm = (String) null;
            }
            lastSearchCaseSetting = bCaseSensitive;
            lastSearchTopSetting = bStartAtTop;
        }
    }

    public int findText(String findTerm, String replaceTerm, boolean bCaseSenstive, int iOffset) {
        JTextComponent jtpFindSource = (JTextComponent) controle;
        int searchPlace = -1;
        try {
            Document baseDocument = jtpFindSource.getDocument();
            searchPlace
                    = (bCaseSenstive
                            ? baseDocument.getText(0, baseDocument.getLength()).indexOf(findTerm, iOffset)
                            : baseDocument.getText(0, baseDocument.getLength()).toLowerCase().indexOf(findTerm.toLowerCase(), iOffset));
            if (searchPlace > -1) {
                if (replaceTerm != null) {
                    AttributeSet attribs = null;
                    if (baseDocument instanceof HTMLDocument) {
                        Element element = ((HTMLDocument) baseDocument).getCharacterElement(searchPlace);
                        attribs = element.getAttributes();
                    }
                    baseDocument.remove(searchPlace, findTerm.length());
                    baseDocument.insertString(searchPlace, replaceTerm, attribs);
                    jtpFindSource.setCaretPosition(searchPlace + replaceTerm.length());
                    jtpFindSource.requestFocus();
                    jtpFindSource.select(searchPlace, searchPlace + replaceTerm.length());
                } else {
                    jtpFindSource.setCaretPosition(searchPlace + findTerm.length());
                    jtpFindSource.requestFocus();
                    jtpFindSource.select(searchPlace, searchPlace + findTerm.length());
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return searchPlace;
    }

    public void insereLocalImage(File whatImage) throws Exception {
        if (whatImage == null) {
            whatImage = getImageFromChooser(imageChooserStartDir, extsIMG, "Imagem (*.gif, *.png, *.jpg, *.jpeg)");
        }
        if (whatImage != null) {
            imageChooserStartDir = whatImage.getParent().toString();
            int caretPos = controle.getCaretPosition();
            htmlKit.insertHTML(htmlDoc, caretPos, "<IMG SRC=\"" + whatImage + "\">", 0, 0, HTML.Tag.IMG);
            controle.setCaretPosition(caretPos + 1);
            atualiza();
        }
    }

    public void insereURLImage() throws Exception {
        ImageURLDialog iurlDialog = new ImageURLDialog(null, "Imagem URL", true);
        iurlDialog.pack();
        iurlDialog.setVisible(true);
        String whatImage = iurlDialog.getURL();
        if (whatImage != null) {
            int caretPos = controle.getCaretPosition();
            htmlKit.insertHTML(htmlDoc, caretPos, "<IMG SRC=\"" + whatImage + "\">", 0, 0, HTML.Tag.IMG);
            controle.setCaretPosition(caretPos + 1);
            atualiza();
        }
    }

    public LdtDoc editaNegrito(ActionEvent e) {
        actNegrito.actionPerformed(e);
        return this;
    }

    public LdtDoc editaItalico(ActionEvent e) {
        actItalico.actionPerformed(e);
        return this;
    }

    public LdtDoc editaSublinhado(ActionEvent e) {
        actSublinhado.actionPerformed(e);
        return this;
    }

    public LdtDoc editaRiscado(ActionEvent e) {
        actRiscado.actionPerformed(e);
        return this;
    }

    public LdtDoc editaSobrescrito(ActionEvent e) {
        actSobrescrito.actionPerformed(e);
        return this;
    }

    public LdtDoc editaSubescrito(ActionEvent e) {
        actSubescrito.actionPerformed(e);
        return this;
    }

    public LdtDoc editaLista(ActionEvent e) {
        actLista.actionPerformed(e);
        return this;
    }

    public LdtDoc editaOrdenada(ActionEvent e) {
        actOrdenada.actionPerformed(e);
        return this;
    }

    public LdtDoc editaFonte(ActionEvent e) {
        actFonte.actionPerformed(e);
        return this;
    }

    public LdtDoc editaEsquerda(ActionEvent e) {
        actEsquerda.actionPerformed(e);
        return this;
    }

    public LdtDoc editaCentro(ActionEvent e) {
        actCentro.actionPerformed(e);
        return this;
    }

    public LdtDoc editaDireita(ActionEvent e) {
        actDireita.actionPerformed(e);
        return this;
    }

    public LdtDoc editaJustificado(ActionEvent e) {
        actJustificado.actionPerformed(e);
        return this;
    }

    public LdtDoc editaAncora(ActionEvent e) {
        actAncora.actionPerformed(e);
        return this;
    }

    public HTMLDoc pDoc() {
        return (HTMLDoc) htmlDoc;
    }

    public HTMLDocument pDocControle() {
        return (HTMLDocument) (controle.getDocument());
    }

    public String pDocRTF() throws Exception {
        StyledDocument doc = (StyledDocument) (controle.getStyledDocument());
        StringWriter strwriter = new StringWriter();
        RTFEditorKit rtfKit = new RTFEditorKit();
        rtfKit.write(strwriter, doc, 0, doc.getLength());
        return strwriter.toString();
    }

    public void abre() throws Exception {
        File arq = UtlArq.abreArq(corFormularioIndicador, extsHTML);
        if (arq != null) {
            abre(arq);
        }
    }

    public void abre(File doArquivo) throws Exception {
        this.abre(doArquivo, null);
    }

    public void abre(File doArquivo, HTMLEditorKit.ParserCallback comPassador) throws Exception {
        try {
            abre(doArquivo, null, comPassador);
        } catch (ChangedCharSetException ccse) {
            String charsetType = ccse.getCharSetSpec().toLowerCase();
            int pos = charsetType.indexOf("charset");
            if (pos == -1) {
                throw ccse;
            }
            while (pos < charsetType.length() && charsetType.charAt(pos) != '=') {
                pos++;
            }
            pos++; // Places file cursor past the equals sign (=)
            String whatEncoding = charsetType.substring(pos).trim();
            abre(doArquivo, whatEncoding, comPassador);
        }
        atualiza();
    }

    public void abre(File oArquivo, String naCodificacao, HTMLEditorKit.ParserCallback comPassador) throws Exception {
        Reader rp = null;
        Reader rr = null;
        htmlDoc = (HTMLDoc) (htmlKit.createDefaultDocument());
        htmlDoc.putProperty("pin.libdoc.docsource", oArquivo.toString());
        try {
            if (naCodificacao == null) {
                rp = new InputStreamReader(new FileInputStream(oArquivo));
                rr = new InputStreamReader(new FileInputStream(oArquivo));
            } else {
                rp = new InputStreamReader(new FileInputStream(oArquivo), naCodificacao);
                rr = new InputStreamReader(new FileInputStream(oArquivo), naCodificacao);
                htmlDoc.putProperty("IgnoreCharsetDirective", true);
            }
            if (comPassador != null) {
                HTMLEditorKit.Parser parser = htmlDoc.getParser();
                parser.parse(rp, comPassador, true);
                rp.close();
            }
            htmlKit.read(rr, htmlDoc, 0);
            registraDocumento(htmlDoc);
            arquivo = oArquivo;
        } finally {
            if (rr != null) {
                rr.close();
            }
        }
    }

    public LdtDoc salva() throws Exception {
        File arq = UtlArq.salvaArq(arquivo, "Documento HTML (*.htm, *.html, *.shtml)", extsHTML);
        if (arq != null) {
            salva(pDoc(), arq);
        }
        return this;
    }

    public void salva(HTMLDocument oDocumento, File noArquivo) throws Exception {
        try (FileWriter fw = new FileWriter(noArquivo)) {
            htmlKit.write(fw, oDocumento, 0, oDocumento.getLength());
            fw.flush();
        }
        arquivo = noArquivo;
        atualiza();
    }

    public void salva(HTMLDocument oDocumento, String comMarca) throws Exception {
        File arq = UtlArq.salvaArq("Documento HTML (*.htm, *.html. *.shtml)", extsHTML);
        if (arq != null) {
            salva(oDocumento, comMarca, arq);
        }
    }

    public void salva(HTMLDocument oDocumento, String comMarca, File noArquivo) throws Exception {
        FileWriter fw = new FileWriter(noArquivo);
        String docTextCase = controle.getText().toLowerCase();
        int tagStart = docTextCase.indexOf("<" + comMarca.toLowerCase());
        int tagStartClose = docTextCase.indexOf(">", tagStart) + 1;
        String closeTag = "</" + comMarca.toLowerCase() + ">";
        int tagEndOpen = docTextCase.indexOf(closeTag);
        if (tagStartClose < 0) {
            tagStartClose = 0;
        }
        if (tagEndOpen < 0 || tagEndOpen > docTextCase.length()) {
            tagEndOpen = docTextCase.length();
        }
        String bodyText = controle.getText().substring(tagStartClose, tagEndOpen);
        fw.write(bodyText, 0, bodyText.length());
        fw.flush();
        fw.close();
        atualiza();
    }

    public void exportaRTF(StyledDocument oDocumento) throws Exception {
        File arq = UtlArq.abreArq("Documento RTF (*.rtf)", extsRTF);
        if (arq != null) {
            exportaRTF(oDocumento, arq);
        }
    }

    public void exportaRTF(StyledDocument oDocumento, File noArquivo) throws Exception {
        FileOutputStream fos = new FileOutputStream(noArquivo);
        RTFEditorKit rtfKit = new RTFEditorKit();
        rtfKit.write(fos, oDocumento, 0, oDocumento.getLength());
        fos.flush();
        fos.close();
        atualiza();
    }

    public void carregaEstilos() throws Exception {
        File arq = UtlArq.abreArq("Estilos CSS (*.css)", extsCSS);
        if (arq != null) {
            carregaEstilos(arq);
        }
    }

    public void carregaEstilos(File doArquivo) throws Exception {
        String currDocText = controle.getText();
        htmlDoc = (HTMLDoc) (htmlKit.createDefaultDocument());
        htmlDoc.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        htmlDoc.setPreservesUnknownTags(true);
        estilosDoc = htmlDoc.getStyleSheet();
        URL cssUrl = doArquivo.toURI().toURL();
        InputStream is = cssUrl.openStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            estilosDoc.loadRules(br, cssUrl);
        }
        htmlDoc = new HTMLDoc(estilosDoc);
        registraDocumento(htmlDoc);
        controle.setText(currDocText);
        atualiza();
    }

    public File getImageFromChooser(String startDir, String[] exts, String desc) {
        ImageFileChooser jImageDialog = new ImageFileChooser(startDir);
        jImageDialog.setDialogType(JFileChooser.CUSTOM_DIALOG);
        jImageDialog.setFileFilter(new MutableFilter(exts, desc));
        jImageDialog.setDialogTitle("Imagem");
        int optionSelected;
        optionSelected = jImageDialog.showDialog(this, "Insere");
        if (optionSelected == JFileChooser.APPROVE_OPTION) {
            return jImageDialog.getSelectedFile();
        }
        return (File) null;
    }

    public void descreveMarcas() {
        Utl.imp("Caret Position : " + controle.getCaretPosition());
        AttributeSet attribSet = controle.getCharacterAttributes();
        Enumeration attribs = attribSet.getAttributeNames();
        Utl.imp("Attributes     : ");
        while (attribs.hasMoreElements()) {
            String attribName = attribs.nextElement().toString();
            Utl.imp("                 " + attribName + " | " + attribSet.getAttribute(attribName));
        }
    }

    public void descreveDocumento(StyledDocument doc) {
        Element[] elements = doc.getRootElements();
        for (Element elem : elements) {
            indent = indentStep;
            for (int j = 0; j < indent; j++) {
                System.out.print(" ");
            }
            System.out.print(elem);
            descreveElemento(elem);
            Utl.imp("");
        }
    }

    public void descreveElemento(Element element) {
        indent += indentStep;
        for (int i = 0; i < element.getElementCount(); i++) {
            for (int j = 0; j < indent; j++) {
                System.out.print(" ");
            }
            System.out.print(element.getElement(i));
            descreveElemento(element.getElement(i));
        }
        indent -= indentStep;
    }

    public JTextPane controle() {
        return controle;
    }

    public JScrollPane rolagem() {
        return rolagem;
    }

    public File pArquivo() {
        return arquivo;
    }

    public String pFonte() {
        return controle.getText();
    }

    public String pFonte(String naMarcacao) {
        String docText = controle.getText();
        String docTextCase = docText.toLowerCase();
        int tagStart = docTextCase.indexOf("<" + naMarcacao.toLowerCase());
        int tagStartClose = docTextCase.indexOf(">", tagStart) + 1;
        String closeTag = "</" + naMarcacao.toLowerCase() + ">";
        int tagEndOpen = docTextCase.indexOf(closeTag);
        if (tagStartClose < 0) {
            tagStartClose = 0;
        }
        if (tagEndOpen < 0 || tagEndOpen > docTextCase.length()) {
            tagEndOpen = docTextCase.length();
        }
        return docText.substring(tagStartClose, tagEndOpen);
    }

    public String pFonteCorpo() {
        return pFonte("body");
    }

    public void mFonte(String sText) {
        controle.setText(sText);
        ((HTMLEditorKit) (controle.getEditorKit())).setDefaultCursor(new Cursor(Cursor.TEXT_CURSOR));
    }

    public void mDocumento(StyledDocument sDoc) {
        JTextPane src = new JTextPane();
        src.setEditorKit(new HTMLKit());
        src.setDocument(sDoc);
        controle.setText(src.getText());
        ((HTMLEditorKit) (controle.getEditorKit())).setDefaultCursor(new Cursor(Cursor.TEXT_CURSOR));
    }

    public void purgeUndos() {
        if (undoMngr != null) {
            undoMngr.discardAllEdits();
            undoAction.updateUndoState();
            redoAction.updateRedoState();
        }
    }

    public void atualiza() {
        Rectangle r = rolagem.getViewport().getViewRect();
        int p = controle.getCaretPosition();
        int s = controle.getSelectionStart();
        int e = controle.getSelectionEnd();
        controle.setText(controle.getText());
        purgeUndos();
        this.repaint();
        try {
            controle.setCaretPosition(p);
        } catch (Exception ex) {
        }
        try {
            controle.setSelectionStart(s);
        } catch (Exception ex) {
        }
        try {
            controle.setSelectionEnd(e);
        } catch (Exception ex) {
        }
        try {
            rolagem.scrollRectToVisible(r);
        } catch (Exception ex) {
        }
    }

    public String pEstilo(Element element) {
        AttributeSet as = element.getAttributes();
        if (as == null) {
            return null;
        }
        Object val = as.getAttribute(HTML.Attribute.CLASS);
        if (val != null && (val instanceof String)) {
            return (String) val;
        }
        for (Enumeration e = as.getAttributeNames(); e.hasMoreElements();) {
            Object key = e.nextElement();
            if (key instanceof HTML.Tag) {
                AttributeSet eas = (AttributeSet) (as.getAttribute(key));
                if (eas != null) {
                    val = eas.getAttribute(HTML.Attribute.CLASS);
                    if (val != null) {
                        return (String) val;
                    }
                }
            }

        }
        return null;
    }

    public void trataMudancaCursor(CaretEvent ce) {
        int caretPos = ce.getDot();
        Element element = htmlDoc.getCharacterElement(caretPos);
        if (controle.hasFocus()) {
            if (element == null) {
                return;
            }
            String style = null;
            Vector<Element> vcStyles = new Vector<Element>();
            while (element != null) {
                if (style == null) {
                    style = pEstilo(element);
                }
                vcStyles.add(element);
                element = element.getParentElement();
            }
        }
    }

    public int pCursor() {
        return controle.getCaretPosition();
    }

    public void mCursor(int newPositon) {
        boolean end = true;
        do {
            end = true;
            try {
                controle.setCaretPosition(newPositon);
            } catch (IllegalArgumentException iae) {
                end = false;
                newPositon--;
            }
        } while (!end && newPositon >= 0);
    }

    private class UndoAction extends AbstractAction {

        public UndoAction() {
            super("Desfaz");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undoMngr.undo();
            } catch (CannotUndoException ex) {
                ex.printStackTrace();
            }
            updateUndoState();
            redoAction.updateRedoState();
        }

        protected void updateUndoState() {
            setEnabled(undoMngr.canUndo());
        }
    }

    private class RedoAction extends AbstractAction {

        public RedoAction() {
            super("Refaz");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoMngr.redo();
            } catch (CannotUndoException ex) {
                ex.printStackTrace();
            }
            updateRedoState();
            undoAction.updateUndoState();
        }

        protected void updateRedoState() {
            setEnabled(undoMngr.canRedo());
        }
    }

    private class CustomUndoableEditListener implements UndoableEditListener {

        public void undoableEditHappened(UndoableEditEvent uee) {
            undoMngr.addEdit(uee.getEdit());
            undoAction.updateUndoState();
            redoAction.updateRedoState();
        }
    }

}
