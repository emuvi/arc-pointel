package pin.ldtpla;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;

public class CelulaEdt extends AbstractCellEditor implements TableCellEditor {

    private JTextField editor = null;
    private Dados.Tp tipo = null;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        try {
            tipo = Dados.pega(value);
            if (tipo == null) {
                tipo = Dados.Tp.Crs;
            }
            editor = new JTextField();
            editor.setText(UtlCrs.pega(value));
            return editor;
        } catch (Exception ex) {
            Utl.registra(ex);
            return new JLabel(ex.getMessage());
        }
    }

    @Override
    public Object getCellEditorValue() {
        if (editor != null) {
            try {
                return Dados.pegaVlr(tipo, editor.getText());
            } catch (Exception ex) {
                Utl.registra(ex);
                return null;
            }
        } else {
            return null;
        }
    }
}
