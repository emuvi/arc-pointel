package pin.modinf;

import java.util.ArrayList;
import pin.libbas.Conjunto;
import pin.libvlr.Vlrs;

public class Recorrentes extends ArrayList<Recorrente> {

    private Banco banco;

    public Recorrentes(Banco doBanco) {
        super();
        banco = doBanco;
    }

    public Conjunto busca(String daSelecao, Vlrs comParametros) throws Exception {
        Conjunto rst = null;
        if (daSelecao != null) {
            for (int ic = 0; ic < size(); ic++) {
                Recorrente rec = get(ic);
                if (rec.ehIgual(daSelecao, comParametros)) {
                    rst = rec.resultado();
                    rst.reinicia();
                    break;
                }
            }
        }
        if (rst == null) {
            rst = banco.busca(daSelecao, comParametros);
            add(new Recorrente(daSelecao, comParametros, rst));
        }
        return rst;
    }

    public void tira(String aSelecao, Vlrs comParametros) {
        if (aSelecao != null) {
            for (int ic = 0; ic < size(); ic++) {
                Recorrente rec = get(ic);
                if (rec.ehIgual(aSelecao, comParametros)) {
                    remove(ic);
                    break;
                }
            }
        }
    }

}
