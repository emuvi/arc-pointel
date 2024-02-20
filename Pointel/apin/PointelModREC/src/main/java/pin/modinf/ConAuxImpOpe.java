package pin.modinf;

import pin.libbas.ParsChaves;

public abstract class ConAuxImpOpe {

    public static enum Tp {
        PegaCorpoSeparado, PegaCorpoMarcado, PegaParteSeparada, PegaParteMarcada, PegaParteFixada, Insere, Repete, Continua
    }

    private final Tp tipo;

    public ConAuxImpOpe(Tp tipo) {
        this.tipo = tipo;
    }

    public Tp pTipo() {
        return tipo;
    }

    public abstract int opera(ParsChaves chaves) throws Exception;

    @Override
    public String toString() {
        return pTipo().toString();
    }

    public static ConAuxImpOpe novo(Tp doTipo) {
        if (doTipo == null) {
            return null;
        }
        ConAuxImpOpe retorno = null;
        switch (doTipo) {
            case PegaCorpoSeparado:
                retorno = new ConAuxImpOpePegaCorpoSeparado();
                break;
            case PegaCorpoMarcado:
                retorno = new ConAuxImpOpePegaCorpoMarcado();
                break;
            case PegaParteSeparada:
                retorno = new ConAuxImpOpePegaParteSeparada();
                break;
            case PegaParteMarcada:
                retorno = new ConAuxImpOpePegaParteMarcada();
                break;
            case PegaParteFixada:
                retorno = new ConAuxImpOpePegaParteFixada();
                break;
            case Insere:
                retorno = new ConAuxImpOpeInsere();
                break;
            case Repete:
                retorno = new ConAuxImpOpeRepete();
                break;
            case Continua:
                retorno = new ConAuxImpOpeContinua();
                break;
        }
        return retorno;
    }

    public static Tp pTipo(Object doValor) {
        if (doValor == null) {
            return null;
        }
        Tp retorno = null;
        if (doValor instanceof ConAuxImpOpePegaCorpoSeparado) {
            retorno = Tp.PegaCorpoSeparado;
        } else if (doValor instanceof ConAuxImpOpePegaCorpoMarcado) {
            retorno = Tp.PegaCorpoMarcado;
        } else if (doValor instanceof ConAuxImpOpePegaParteSeparada) {
            retorno = Tp.PegaParteSeparada;
        } else if (doValor instanceof ConAuxImpOpePegaParteMarcada) {
            retorno = Tp.PegaParteMarcada;
        } else if (doValor instanceof ConAuxImpOpePegaParteFixada) {
            retorno = Tp.PegaParteFixada;
        } else if (doValor instanceof ConAuxImpOpeInsere) {
            retorno = Tp.Insere;
        } else if (doValor instanceof ConAuxImpOpeRepete) {
            retorno = Tp.Repete;
        } else if (doValor instanceof ConAuxImpOpeContinua) {
            retorno = Tp.Continua;
        }
        return retorno;
    }

}
