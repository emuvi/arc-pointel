package pin.modinf;

import java.util.Objects;
import pin.libbas.Conjunto;
import pin.libvlr.Vlrs;

public class Recorrente {

    private String selecao;
    private Vlrs parametros;
    private Conjunto resultado;

    public Recorrente(String daSelecao, Vlrs comParametros, Conjunto oResultado) {
        selecao = daSelecao;
        parametros = comParametros;
        resultado = oResultado;
    }

    public Boolean ehIgual(String daSelecao, Vlrs comParametros) {
        if (Objects.equals(selecao, daSelecao)) {
            if (Objects.equals(parametros, comParametros)) {
                return true;
            }
        }
        return false;
    }

    public Conjunto resultado() {
        return resultado;
    }
}
