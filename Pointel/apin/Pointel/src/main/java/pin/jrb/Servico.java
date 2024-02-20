package pin.jrb;

import pin.libutl.UtlTem.Temporidade;
import java.util.Date;

public class Servico {

    public static enum Tp {
        Roteiro, Comando, Conectar, SuporteServidor, ProcessarRobotico, PublicarWebSite, PointelMigre, Arquivador;
    };

    public Integer codigo;
    public Boolean executar;
    public Tp tipo;
    public String fonte;
    public Temporidade aCada;
    public Integer vezes;
    public Date de;
    public Date ate;
    public Date ultimo;

    public Servico() {
        this.codigo = null;
        this.executar = false;
        this.aCada = null;
        this.ultimo = null;
        this.tipo = null;
        this.fonte = null;
    }

    public Servico(Integer codigo, Boolean executar, Tp tipo, String fonte, Temporidade aCada, Integer vezes, Date de, Date ate, Date ultimo) {
        this.codigo = codigo;
        this.executar = executar;
        this.tipo = tipo;
        this.fonte = fonte;
        this.aCada = aCada;
        this.vezes = vezes;
        this.de = de;
        this.ate = ate;
        this.ultimo = ultimo;
    }
}
