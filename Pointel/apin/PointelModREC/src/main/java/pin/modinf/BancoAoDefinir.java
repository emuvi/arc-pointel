package pin.modinf;

public abstract class BancoAoDefinir {

    public enum Tp {
        Conectar, Desconectar
    };

    public abstract Boolean definido(Banco oBanco, Tp doTipo);

}
