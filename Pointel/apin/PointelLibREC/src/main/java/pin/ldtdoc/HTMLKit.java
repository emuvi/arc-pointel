package pin.ldtdoc;

import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class HTMLKit extends HTMLEditorKit {

    public HTMLKit() {
    }

    public ViewFactory getViewFactory() {
        return new HTMLFactoryExtended();
    }

    public Document createDefaultDocument() {
        StyleSheet styles = getStyleSheet();
        StyleSheet ss = new StyleSheet();
        ss.addStyleSheet(styles);
        HTMLDoc doc = new HTMLDoc(ss);
        doc.setParser(getParser());
        doc.setAsynchronousLoadPriority(4);
        doc.setTokenThreshold(100);
        return doc;
    }

    public static class HTMLFactoryExtended extends HTMLFactory implements ViewFactory {

        public HTMLFactoryExtended() {
        }

        public View create(Element elem) {
            Object obj = elem.getAttributes().getAttribute(StyleConstants.NameAttribute);
            if (obj instanceof HTML.Tag) {
                HTML.Tag tagType = (HTML.Tag) obj;
                if (tagType == HTML.Tag.IMG) {
                    return new PdtImageView(elem);
                }
            }
            return super.create(elem);
        }
    }
}
