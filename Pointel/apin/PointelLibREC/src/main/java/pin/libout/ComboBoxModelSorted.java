package pin.libout;

import java.util.*;
import javax.swing.*;

public class ComboBoxModelSorted<E> extends DefaultComboBoxModel<E> {

    private Comparator comparator;

    public ComboBoxModelSorted() {
        super();
    }

    public ComboBoxModelSorted(Comparator comparator) {
        super();
        this.comparator = comparator;
    }

    public ComboBoxModelSorted(E items[]) {
        this(items, null);
    }

    public ComboBoxModelSorted(E items[], Comparator comparator) {
        this.comparator = comparator;
        for (E item : items) {
            addElement(item);
        }
    }

    public ComboBoxModelSorted(Vector<E> items) {
        this(items, null);
    }

    public ComboBoxModelSorted(Vector<E> items, Comparator comparator) {
        this.comparator = comparator;
        for (E item : items) {
            addElement(item);
        }
    }

    @Override
    public void addElement(E element) {
        insertElementAt(element, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insertElementAt(E element, int index) {
        int size = getSize();
        for (index = 0; index < size; index++) {
            if (comparator != null) {
                E o = getElementAt(index);
                if (comparator.compare(o, element) > 0) {
                    break;
                }
            } else {
                Comparable c = (Comparable) getElementAt(index);
                if (c.compareTo(element) > 0) {
                    break;
                }
            }
        }
        super.insertElementAt(element, index);
        if (index == 0 && element != null) {
            setSelectedItem(element);
        }
    }
}
