package pin.ldtdoc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.text.html.HTMLEditorKit;
import pin.libutl.Utl;

/**
 * Custom ParserCallback class by Janko Jochimsen
 */
public class StandardParserCallback extends HTMLEditorKit.ParserCallback {

    HashMap theErrors;
    boolean debug = false;

    public StandardParserCallback() {
        theErrors = new HashMap();
    }

    /**
     * Process all Errors and take node
     */
    public void handleError(String errorMsg, int pos) {
        Integer value = (Integer) theErrors.get(errorMsg);
        if (value == null) {
            Integer one = new Integer(1);
            theErrors.put(errorMsg, one);
        } else {
            Integer incvalue = value + 1;
            theErrors.put(errorMsg, incvalue);
        }
        if (debug) {
            Utl.imp("ParserCallback " + errorMsg + ", POS: " + pos);
        }
    }

    /**
     * Get the Errors for futher investigation
     */
    public HashMap getTheErrors() {
        return theErrors;
    }

    /**
     * Service Method to output the Errors. Might be used as a Template for futher methods
     */
    public void reportCB() {
        Set theErrorTypes = theErrors.keySet();
        if (theErrorTypes.size() < 1) {
            Utl.imp("No Errors parsing file ");
        } else {
            Iterator iter = theErrorTypes.iterator();
            while (iter.hasNext()) {
                String error = (String) iter.next();
                Integer count = (Integer) theErrors.get(error);
                Utl.imp("Error [" + error + "] happend " + count + " times");
            }
        }
    }
}
