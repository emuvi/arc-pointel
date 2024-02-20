package pin.libamg;

import java.util.ArrayList;

public class EdtPaiLins extends ArrayList<EdtPaiLin> {

    public EdtPaiLins() {
        super();
    }

    public EdtPaiLin pegaLinha(Integer local) {
        while (local > size()) {
            add(new EdtPaiLin());
        }
        return get(local - 1);
    }

}
