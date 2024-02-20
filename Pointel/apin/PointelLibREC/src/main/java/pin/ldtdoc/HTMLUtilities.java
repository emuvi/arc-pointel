package pin.ldtdoc;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class HTMLUtilities {

    private LdtDoc parent;
    private Hashtable<String, HTML.Tag> tags = new Hashtable<String, HTML.Tag>();

    public HTMLUtilities(LdtDoc newParent) {
        parent = newParent;
        HTML.Tag[] tagList = HTML.getAllTags();
        for (int i = 0; i < tagList.length; i++) {
            tags.put(tagList[i].toString(), tagList[i]);
        }
    }

    public void insertListElement(String content) {
        int pos = parent.pCursor();
        String source = parent.pFonte();
        boolean hit = false;
        String idString;
        int counter = 0;
        do {
            hit = false;
            idString = "pointelprodocidpadronizado" + counter;
            if (source.contains(idString)) {
                counter++;
                hit = true;
                if (counter > 10000) {
                    return;
                }
            }
        } while (hit);
        Element element = getListItemParent();
        if (element == null) {
            return;
        }
        SimpleAttributeSet sa = new SimpleAttributeSet(element.getAttributes());
        sa.addAttribute("id", idString);
        parent.pDoc().replaceAttributes(element, sa, HTML.Tag.LI);
        parent.atualiza();
        source = parent.pFonte();
        StringBuffer newHtmlString = new StringBuffer();
        int[] positions = getPositions(element, source, true, idString);
        newHtmlString.append(source.substring(0, positions[3]));
        newHtmlString.append("<li>");
        newHtmlString.append(content);
        newHtmlString.append("</li>");
        newHtmlString.append(source.substring(positions[3] + 1, source.length()));
        parent.mFonte(newHtmlString.toString());
        parent.atualiza();
        parent.mCursor(pos - 1);
        element = getListItemParent();
        sa = new SimpleAttributeSet(element.getAttributes());
        sa = removeAttributeByKey(sa, "id");
        parent.pDoc().replaceAttributes(element, sa, HTML.Tag.LI);
    }

    public void removeTag(Element element, boolean closingTag) {
        if (element == null) {
            return;
        }
        HTML.Tag tag = getHTMLTag(element);
        String source = parent.pFonte();
        boolean hit = false;
        String idString;
        int counter = 0;
        do {
            hit = false;
            idString = "pointelprodocidpadronizado" + counter;
            if (source.indexOf(idString) > -1) {
                counter++;
                hit = true;
                if (counter > 10000) {
                    return;
                }
            }
        } while (hit);
        SimpleAttributeSet sa = new SimpleAttributeSet(element.getAttributes());
        sa.addAttribute("id", idString);
        parent.pDoc().replaceAttributes(element, sa, tag);
        parent.atualiza();
        source = parent.pFonte();
        StringBuffer newHtmlString = new StringBuffer();
        int[] position = getPositions(element, source, closingTag, idString);
        if (position == null) {
            return;
        }
        for (int i = 0; i < position.length; i++) {
            if (position[i] < 0) {
                return;
            }
        }
        int beginStartTag = position[0];
        int endStartTag = position[1];
        if (closingTag) {
            int beginEndTag = position[2];
            int endEndTag = position[3];
            newHtmlString.append(source.substring(0, beginStartTag));
            newHtmlString.append(source.substring(endStartTag, beginEndTag));
            newHtmlString.append(source.substring(endEndTag, source.length()));
        } else {
            newHtmlString.append(source.substring(0, beginStartTag));
            newHtmlString.append(source.substring(endStartTag, source.length()));
        }
        parent.mFonte(newHtmlString.toString());
        parent.atualiza();
    }

    private int[] getPositions(Element element, String source, boolean closingTag, String idString) {
        HTML.Tag tag = getHTMLTag(element);
        int[] position = new int[4];
        for (int i = 0; i < position.length; i++) {
            position[i] = -1;
        }
        String searchString = "<" + tag.toString();
        int caret;
        if ((caret = source.indexOf(idString)) != -1) {
            position[0] = source.lastIndexOf("<", caret);
            position[1] = source.indexOf(">", caret) + 1;
        }
        if (closingTag) {
            String searchEndTagString = "</" + tag.toString() + ">";
            int hitUp = 0;
            int beginEndTag = -1;
            int endEndTag = -1;
            caret = position[1];
            boolean end = false;
            beginEndTag = source.indexOf(searchEndTagString, caret);
            endEndTag = beginEndTag + searchEndTagString.length();
            int interncaret = position[1];
            do {
                int temphitpoint = -1;
                boolean flaghitup = false;
                hitUp = 0;
                do {
                    flaghitup = false;
                    temphitpoint = source.indexOf(searchString, interncaret);
                    if (temphitpoint > 0 && temphitpoint < beginEndTag) {
                        hitUp++;
                        flaghitup = true;
                        interncaret = temphitpoint + searchString.length();
                    }
                } while (flaghitup);
                if (hitUp == 0) {
                    end = true;
                } else {
                    for (int i = 1; i <= hitUp; i++) {
                        caret = endEndTag;
                        beginEndTag = source.indexOf(searchEndTagString, caret);
                        endEndTag = beginEndTag + searchEndTagString.length();
                    }
                    end = false;
                }
            } while (!end);
            if (beginEndTag < 0 | endEndTag < 0) {
                return null;
            }
            position[2] = beginEndTag;
            position[3] = endEndTag;
        }
        return position;
    }

    public boolean checkParentsTag(HTML.Tag tag) {
        Element e = parent.pDoc().getParagraphElement(parent.pCursor());
        String tagString = tag.toString();
        if (e.getName().equalsIgnoreCase(tag.toString())) {
            return true;
        }
        do {
            if ((e = e.getParentElement()).getName().equalsIgnoreCase(tagString)) {
                return true;
            }
        } while (!(e.getName().equalsIgnoreCase("html")));
        return false;
    }

    public Element getListItemParent() {
        String listItemTag = HTML.Tag.LI.toString();
        Element eleSearch = parent.pDoc().getCharacterElement(parent.pCursor());
        do {
            if (listItemTag.equals(eleSearch.getName())) {
                return eleSearch;
            }
            eleSearch = eleSearch.getParentElement();
        } while (HTML.Tag.HTML.toString() == null ? eleSearch.getName() != null : !HTML.Tag.HTML.toString().equals(eleSearch.getName()));
        return null;
    }

    public SimpleAttributeSet removeAttributeByKey(SimpleAttributeSet sourceAS, String removeKey) {
        SimpleAttributeSet temp = new SimpleAttributeSet();
        temp.addAttribute(removeKey, "NULL");
        return removeAttribute(sourceAS, temp);
    }

    public SimpleAttributeSet removeAttribute(SimpleAttributeSet sourceAS, SimpleAttributeSet removeAS) {
        try {
            String[] sourceKeys = new String[sourceAS.getAttributeCount()];
            String[] sourceValues = new String[sourceAS.getAttributeCount()];
            Enumeration sourceEn = sourceAS.getAttributeNames();
            int i = 0;
            while (sourceEn.hasMoreElements()) {
                Object temp = new Object();
                temp = sourceEn.nextElement();
                sourceKeys[i] = (String) temp.toString();
                sourceValues[i] = new String();
                sourceValues[i] = (String) sourceAS.getAttribute(temp).toString();
                i++;
            }
            String[] removeKeys = new String[removeAS.getAttributeCount()];
            String[] removeValues = new String[removeAS.getAttributeCount()];
            Enumeration removeEn = removeAS.getAttributeNames();
            int j = 0;
            while (removeEn.hasMoreElements()) {
                removeKeys[j] = (String) removeEn.nextElement().toString();
                removeValues[j] = (String) removeAS.getAttribute(removeKeys[j]).toString();
                j++;
            }
            SimpleAttributeSet result = new SimpleAttributeSet();
            boolean hit;
            for (int countSource = 0; countSource < sourceKeys.length; countSource++) {
                hit = false;
                if ("name".equals(sourceKeys[countSource]) | "resolver".equals(sourceKeys[countSource])) {
                    hit = true;
                } else {
                    for (int countRemove = 0; countRemove < removeKeys.length; countRemove++) {
                        if (!"NULL".equals(removeKeys[countRemove])) {
                            if (sourceKeys[countSource] == null ? removeKeys[countRemove] == null : sourceKeys[countSource].equals(removeKeys[countRemove])) {
                                if (!"NULL".equals(removeValues[countRemove])) {
                                    if (sourceValues[countSource] == null ? removeValues[countRemove] == null : sourceValues[countSource].equals(removeValues[countRemove])) {
                                        hit = true;
                                    }
                                } else if ("NULL".equals(removeValues[countRemove])) {
                                    hit = true;
                                }
                            }
                        } else if ("NULL".equals(removeKeys[countRemove])) {
                            if (sourceValues[countSource] == null ? removeValues[countRemove] == null : sourceValues[countSource].equals(removeValues[countRemove])) {
                                hit = true;
                            } else {
                            }
                        }
                    }
                }
                if (!hit) {
                    result.addAttribute(sourceKeys[countSource], sourceValues[countSource]);
                }
            }
            return result;
        } catch (ClassCastException cce) {
            return null;
        }
    }

    public HTML.Tag getHTMLTag(Element e) {
        if (tags.containsKey(e.getName())) {
            return (HTML.Tag) tags.get(e.getName());
        } else {
            return null;
        }
    }

    public String[] getUniString(int strings) {
        parent.atualiza();
        String[] result = new String[strings];
        String source = parent.pFonte();
        for (int i = 0; i < strings; i++) {
            int start = -1, end = -1;
            boolean hit = false;
            String idString;
            int counter = 0;
            do {
                hit = false;
                idString = "pointelprodocidpadronizado" + counter + "#" + i;
                if (source.contains(idString)) {
                    counter++;
                    hit = true;
                    if (counter > 10000) {
                        return null;
                    }
                }
            } while (hit);
            result[i] = idString;
        }
        return result;
    }

    public void delete()
            throws BadLocationException, IOException {
        JTextPane jtpMain = parent.controle();
        HTMLDoc htmlDoc = parent.pDoc();
        int selStart = jtpMain.getSelectionStart();
        int selEnd = jtpMain.getSelectionEnd();
        String[] posStrings = getUniString(2);
        if (posStrings == null) {
            return;
        }
        htmlDoc.insertString(selStart, posStrings[0], null);
        htmlDoc.insertString(selEnd + posStrings[0].length(), posStrings[1], null);
        parent.atualiza();
        String docText = jtpMain.getText();
        int start = docText.indexOf(posStrings[0]);
        int end = docText.indexOf(posStrings[1]);
        if (start == -1 || end == -1) {
            return;
        }
        String htmlString = new String();
        htmlString += docText.substring(0, start);
        htmlString += docText.substring(start + posStrings[0].length(), end);
        htmlString += docText.substring(end + posStrings[1].length(), docText.length());
        String source = htmlString;
        end = end - posStrings[0].length();
        htmlString = new String();
        htmlString += source.substring(0, start);
        htmlString += getAllTableTags(source.substring(start, end));
        htmlString += source.substring(end, source.length());
        parent.mFonte(htmlString);
        parent.atualiza();
    }

    private String getAllTableTags(String source)
            throws BadLocationException, IOException {
        StringBuilder result = new StringBuilder();
        int caret = -1;
        do {
            caret++;
            int[] tableCarets = new int[6];
            tableCarets[0] = source.indexOf("<table", caret);
            tableCarets[1] = source.indexOf("<tr", caret);
            tableCarets[2] = source.indexOf("<td", caret);
            tableCarets[3] = source.indexOf("</table", caret);
            tableCarets[4] = source.indexOf("</tr", caret);
            tableCarets[5] = source.indexOf("</td", caret);
            java.util.Arrays.sort(tableCarets);
            caret = -1;
            for (int i = 0; i < tableCarets.length; i++) {
                if (tableCarets[i] >= 0) {
                    caret = tableCarets[i];
                    break;
                }
            }
            if (caret != -1) {
                result.append(source.substring(caret, source.indexOf(">", caret) + 1));
            }
        } while (caret != -1);
        return result.toString();
    }

}
