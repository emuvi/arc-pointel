package pin.modinf;

import pin.libbas.ParsChaves;

public class ConAuxImpOpeContinua extends ConAuxImpOpe {

    public ConAuxImpOpeContinua() {
        super(ConAuxImpOpe.Tp.Continua);
    }

    @Override
    public int opera(ParsChaves chaves) throws Exception {
        return -1;
    }

}
