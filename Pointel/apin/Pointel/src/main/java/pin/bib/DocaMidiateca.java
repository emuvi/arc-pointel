package pin.bib;

import java.io.File;
import pin.libamk.Doca;
import pin.libbas.Erro;
import pin.libbas.Analisa;

public class DocaMidiateca extends Doca {

    public DocaMidiateca() {
        super("Midiateca Doca");
        botaAoReceber(new Recebedor());
    }

    private class Recebedor implements Analisa {

        @Override
        public void analisa(Object oValor) throws Exception {
            if (!(oValor instanceof File)) {
                throw new Erro("Valor Recebido Não é Válido");
            }
            File arquivo = (File) oValor;
            new RotMidiateca(arquivo).mostra();
        }

    }

}
