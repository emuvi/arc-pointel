package pin.ldtpla;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import pin.libutl.UtlCrs;
import pin.libutl.UtlIma;

public class Celulador extends DefaultTableCellRenderer {

    private JLabel celula = new JLabel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        celula.setOpaque(true);
        Color fg = null;
        Color bg = null;
        JTable.DropLocation dropLocation = table.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsertRow()
                && !dropLocation.isInsertColumn()
                && dropLocation.getRow() == row
                && dropLocation.getColumn() == column) {
            fg = UIManager.getColor("Table.dropCellForeground");
            bg = UIManager.getColor("Table.dropCellBackground");
            isSelected = true;
        }
        if (isSelected) {
            celula.setForeground(fg == null ? table.getSelectionForeground() : fg);
            celula.setBackground(bg == null ? table.getSelectionBackground() : bg);
        } else {
            celula.setForeground(table.getForeground());
            celula.setBackground(table.getBackground());
        }
        if (UtlIma.eh(value)) {
            celula.setIcon(new ImageIcon(UtlIma.pega(value)));
        } else {
            celula.setText(UtlCrs.pega(value));
        }
        return celula;
    }

}
