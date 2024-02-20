package pin.libamg;

import pin.libutl.Utl;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EdtPlaLog extends AbstractCellEditor implements TableCellEditor {

    JComponent componente;

    public EdtPlaLog() throws Exception {
        componente = new EdtLog();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        try {
            ((EdtLog) componente).mdVlr(value);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return componente;
    }

    public Object getCellEditorValue() {
        try {
            return ((EdtLog) componente).pgVlr();
        } catch (Exception ex) {
            Utl.registra(ex);
            return null;
        }
    }
}
