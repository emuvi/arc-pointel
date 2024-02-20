package pin.mgr;

import pin.libtex.LeTXT;
import pin.libtex.LeTXTCmp;
import java.io.File;

public class ImportarTXTReg {

    public File arquivo;
    public String codificacao;
    public LeTXT.Tp tipo;
    public LeTXTCmp[] campos;
    public String tabelaNome;
    public ImportarTXTCmp[] tabelaCampos;
    public Boolean pararSeErrar;
    public Boolean tambemNosTratamentos;

}
