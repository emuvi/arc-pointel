package pin.libbas;

import java.util.Map;

public class ParsChaves extends ParsParams<String, Object> {

    public void limpaComecando(String comChave) {
        for (Map.Entry<String, Object> ent : entrySet()) {
            if (ent.getKey() != null) {
                if (ent.getKey().startsWith(comChave)) {
                    tira(ent.getKey());
                }
            }
        }
    }

}
