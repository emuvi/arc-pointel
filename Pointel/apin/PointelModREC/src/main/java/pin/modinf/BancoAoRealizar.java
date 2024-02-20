package pin.modinf;

import pin.libvlr.Vlrs;

public abstract class BancoAoRealizar {

    public enum Tp {
        Procurar, Executar
    };

    public abstract void realizado(Banco doBanco, Tp doTipo, String oComando, Vlrs comParametros, Integer eRetornos);
}
