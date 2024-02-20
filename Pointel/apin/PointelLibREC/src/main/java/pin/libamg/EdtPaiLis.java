package pin.libamg;

import java.util.ArrayList;
import java.util.Objects;

public class EdtPaiLis extends ArrayList<Edt> {

    public EdtPaiLis() {
        super();
    }

    public Edt pega(String comNome) {
        for (Edt editor : this) {
            if (Objects.equals(editor.pNome(), comNome)) {
                return editor;
            }
        }
        return null;
    }

}
