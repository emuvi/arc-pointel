package pin.libtex;

public class Porcao {

    public Integer abridorIni;
    public Integer abridorFim;
    public Integer valorIni;
    public Integer valorFim;
    public Integer fechadorIni;
    public Integer fechadorFim;

    public Porcao(Integer valorIni, Integer valorFim) {
        this.abridorIni = valorIni;
        this.abridorFim = valorIni;
        this.valorIni = valorIni;
        this.valorFim = valorFim;
        this.fechadorIni = valorFim;
        this.fechadorFim = valorFim;
    }

    public Porcao mAbridor(Integer comInicio, Integer eFim) {
        abridorIni = comInicio;
        abridorFim = eFim;
        return this;
    }

    public Porcao mFechador(Integer comInicio, Integer eFim) {
        fechadorIni = comInicio;
        fechadorFim = eFim;
        return this;
    }

}
