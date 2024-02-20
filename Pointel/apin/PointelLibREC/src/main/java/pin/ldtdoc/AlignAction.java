package pin.ldtdoc;

import java.awt.event.ActionEvent;
import javax.swing.text.StyledEditorKit;

public class AlignAction extends StyledEditorKit.AlignmentAction {

    protected LdtDoc parent;

    public AlignAction(LdtDoc parent, String actionName, int actionType) {
        super(actionName, actionType);
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent ae) {
        int selStart = parent.controle().getSelectionStart();
        int selEnd = parent.controle().getSelectionEnd();
        super.actionPerformed(ae);
        parent.controle().setText(parent.controle().getText());
        parent.controle().setSelectionStart(selStart);
        parent.controle().setSelectionEnd(selEnd);
    }
}
