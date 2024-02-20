package pin.mgr;

import pin.libvlr.Vlr;

public class ImportarTXTCmp {

    public static enum Tp {
        Importado, Fixo, Roteiro
    }

    public String nome;
    public Tp tipo;
    public Integer impIndice;
    public String impTipo;
    public Vlr fixValor;
    public String rotCodigo;
    public Tratamento[] tratamentos;

    public ImportarTXTCmp() {
        this.nome = "";
        this.tipo = Tp.Importado;
        this.impIndice = 0;
        this.impTipo = "CS";
        this.fixValor = null;
        this.tratamentos = null;
    }

    @Override
    public String toString() {
        String retorno = nome + " - " + tipo + " / ";
        if (tipo.equals(Tp.Importado)) {
            retorno += impIndice + " - " + impTipo;
        } else if (tipo.equals(Tp.Fixo)) {
            if (fixValor == null) {
                retorno += "nulo";
            } else {
                retorno += fixValor.toString();
            }
        } else if (tipo.equals(Tp.Roteiro)) {
            if (rotCodigo == null) {
                retorno += "nulo";
            } else {
                retorno += rotCodigo;
            }
        }
        retorno += " / ";
        if (tratamentos == null) {
            retorno += "Sem Tratamentos";
        } else {
            retorno += "Com " + tratamentos.length + " Tratamento(s)";
        }
        return retorno;
    }

}
