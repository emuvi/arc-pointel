package pin.libexp;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

public class ExpRenderizador extends DefaultListCellRenderer {
    private Border padBorder;

    public ExpRenderizador() {
        padBorder = new EmptyBorder(5,5,5,5);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        File file = (File) value;
        label.setBorder(padBorder);
        if (! file.exists()) {
            return null;
        } else if (! file.getName().isEmpty())
            label.setText(file.getName());
        else
            label.setText(file.getAbsolutePath());
        if (file.exists())
            label.setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
        return label;
    }
}
