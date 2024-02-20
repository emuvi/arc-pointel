package pin.modinf;

import pin.libbas.ParsChaves;

public class ConAuxImpOpeInsere extends ConAuxImpOpe {

    private String comando;

    public ConAuxImpOpeInsere() {
        super(ConAuxImpOpe.Tp.Insere);
    }

    public String pComando() {
        return comando;
    }

    public ConAuxImpOpeInsere mComando(String comando) {
        this.comando = comando;
        return this;
    }

    @Override
    public int opera(ParsChaves chaves) throws Exception {

        return ((Integer) chaves.pega("indice")) + 1;
    }

}
