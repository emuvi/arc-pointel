package pin.libout;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class JCheckList<T> extends JList {

    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    private Boolean simpleSelection;
    private DefaultListModel<JCheckBox> checkList;
    private List<T> values;

    public JCheckList() {
        super();
        initList();
    }

    private void initList() {
        simpleSelection = true;
        checkList = new DefaultListModel();
        setModel(checkList);
        values = new ArrayList<>();
        setCellRenderer(new CheckRenderer());
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyStroke.getKeyStrokeForEvent(e).equals(KeyStroke.getKeyStroke("SPACE"))) {
                    for (int is = 0; is < length(); is++) {
                        if (getSelectionModel().isSelectedIndex(is)) {
                            select(is);
                        }
                    }
                    repaint();
                } else if (KeyStroke.getKeyStrokeForEvent(e).equals(KeyStroke.getKeyStroke("control alt SPACE"))) {
                    for (int is = 0; is < length(); is++) {
                        if (getSelectionModel().isSelectedIndex(is)) {
                            unselect(is);
                        }
                    }
                    repaint();
                }
            }
        });
        setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public Boolean isSelectionSimple() {
        return simpleSelection;
    }

    public void setSelectionSimple() {
        simpleSelection = true;
    }

    public void setSelectionMultiple() {
        simpleSelection = false;
    }

    public void clear() {
        checkList.removeAllElements();
        values.clear();
    }

    public void addItems(T... items) {
        if (items != null) {
            for (T elm : items) {
                addItem(elm);
            }
        }
    }

    public void addItems(List<T> items) {
        if (items != null) {
            for (T elm : items) {
                addItem(elm);
            }
        }
    }

    public void addItem(T item) {
        if (item != null) {
            if (item instanceof JCheckBox) {
                checkList.addElement((JCheckBox) item);
            } else {
                checkList.addElement(new JCheckBox(item.toString()));
            }
            values.add(item);
            revalidate();
        }
    }

    public void addItem(int index, T item) {
        if (item != null) {
            if (item instanceof JCheckBox) {
                checkList.add(index, (JCheckBox) item);
            } else {
                checkList.add(index, new JCheckBox(item.toString()));
            }
            values.add(index, item);
            revalidate();
        }
    }

    public Boolean isselect(int index) {
        return checkList.get(index).isSelected();
    }

    public void select(T... values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    select(idx);
                }
            }
        }
    }

    public void select(List<T> values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    select(idx);
                }
            }
        }
    }

    public void select(int index) {
        if (index > -1) {
            checkList.get(index).setSelected(true);
            if (simpleSelection) {
                for (int ic = 0; ic < checkList.getSize(); ic++) {
                    if (!Objects.equals(index, ic)) {
                        checkList.get(ic).setSelected(false);
                    }
                }
            }
            revalidate();
        }
    }

    public void selectAll() {
        if (!simpleSelection) {
            for (int ic = 0; ic < checkList.getSize(); ic++) {
                checkList.get(ic).setSelected(true);
            }
            revalidate();
        }
    }

    public void unselect(T... values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    unselect(idx);
                }
            }
        }
    }

    public void unselect(List<T> values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    unselect(idx);
                }
            }
        }
    }

    public void unselect(int index) {
        checkList.get(index).setSelected(false);
        revalidate();
    }

    public void unselectAll() {
        for (int ic = 0; ic < checkList.getSize(); ic++) {
            checkList.get(ic).setSelected(false);
            checkList.get(ic).repaint();
        }
    }

    public void invertSelect(T... values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    invertSelect(idx);
                }
            }
        }
    }

    public void invertSelect(List<T> values) {
        if (values != null) {
            for (T value : values) {
                int idx = indexOf(value);
                if (idx > -1) {
                    invertSelect(idx);
                }
            }
        }
    }

    public void invertSelect(int index) {
        if (index > -1) {
            Boolean ts = !checkList.get(index).isSelected();
            checkList.get(index).setSelected(ts);
            if (simpleSelection && ts) {
                for (int ic = 0; ic < checkList.getSize(); ic++) {
                    if (!Objects.equals(index, ic)) {
                        checkList.get(ic).setSelected(false);
                    }
                }
            }
            revalidate();
        }
    }

    public void invertSelectAll() {
        for (int ic = 0; ic < checkList.getSize(); ic++) {
            invertSelect(ic);
        }
    }

    public T get(int index) {
        return values.get(index);
    }

    public int indexOf(T value) {
        return values.indexOf(value);
    }

    public int length() {
        return values.size();
    }

    public boolean isEmpty() {
        return getSelectedIndex() == -1;
    }

    @Override
    public int getSelectedIndex() {
        for (int ic = 0; ic < checkList.getSize(); ic++) {
            if (checkList.get(ic).isSelected()) {
                return ic;
            }
        }
        return -1;
    }

    public int getSelectedSize() {
        int ret = 0;
        for (int ic = 0; ic < checkList.getSize(); ic++) {
            if (checkList.get(ic).isSelected()) {
                ret++;
            }
        }
        return ret;
    }

    @Override
    public int[] getSelectedIndices() {
        int[] ret = new int[getSelectedSize()];
        int idx = 0;
        for (int ic = 0; ic < checkList.getSize(); ic++) {
            if (checkList.get(ic).isSelected()) {
                ret[idx] = ic;
                idx++;
            }
        }
        return ret;
    }

    @Override
    public T getSelectedValue() {
        T ret = null;
        int idx = getSelectedIndex();
        if (idx > -1) {
            ret = get(idx);
        }
        return ret;
    }

    @Override
    public List<T> getSelectedValuesList() {
        int[] idxs = getSelectedIndices();
        List<T> ret = new ArrayList<>();
        for (int ir = 0; ir < idxs.length; ir++) {
            ret.add(get(idxs[ir]));
        }
        return ret;
    }

    public List<T> getValues() {
        return values;
    }

    public List<JCheckBox> getChecks() {
        List<JCheckBox> ret = new ArrayList<>();
        for (int ic = 0; ic < checkList.size(); ic++) {
            ret.add(checkList.get(ic));
        }
        return ret;
    }

    protected class CheckRenderer implements ListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground(isSelected ? getSelectionBackground() : getBackground());
            checkbox.setForeground(isSelected ? getSelectionForeground() : getForeground());
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
            if (isSelected && checkbox.isSelected()) {
                if (simpleSelection) {
                    int idx = checkList.indexOf(checkbox);
                    for (int iu = 0; iu < length(); iu++) {
                        if (!Objects.equals(idx, iu)) {
                            unselect(iu);
                        }
                    }
                }
            }
            return checkbox;
        }
    }
}
