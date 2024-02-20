package pin.bib;

import java.io.File;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.modamk.Rotina;

public class RotMidiateca extends Rotina {

    private final File arquivo;

    public RotMidiateca(File doArquivo) {
        super("Midiateca Rotina", new Cmp[]{
            new Cmp(1, 1, "assunto", "Assunto - Cod.", Dados.Tp.Crs).mTamanho(18),
            new Cmp(1, 1, "assuntos.nome", "Assunto - Nome", Dados.Tp.Crs).mTamanho(80)
        });
        botaDescricao("Arquivo: " + doArquivo.getName());
        arquivo = doArquivo;
    }

}
