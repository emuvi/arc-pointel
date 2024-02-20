package pin.modinf;

import java.util.List;
import javax.swing.AbstractAction;
import pin.libamg.EdtPla;
import pin.libbas.Analisado;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libjan.TabCarregador;
import pin.libjan.TabSelecionador;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libvlr.VInt;
import pin.libvlr.Vlrs;

public class Carregador extends Thread {

    private volatile Banco banco;
    private volatile Boolean desconectaAoTerminar;
    private volatile Integer partes;
    private volatile String selecao;
    private volatile Vlrs parametros;
    private volatile List<Dados.Tp> tipos;
    private volatile EdtPla editor;
    private volatile Analisado<Conjunto, Boolean> aoCarregar;
    private volatile Analisado<Throwable, Boolean> aoErrar;
    private volatile AbstractAction aoModificar;
    private volatile AbstractAction aoTerminar;
    private volatile Boolean primeiro;
    private volatile Boolean carregar;
    private volatile Boolean manterAberto;
    private volatile Boolean pausar;
    private volatile Boolean parar;
    private volatile Boolean errou;
    private volatile Boolean fechar;

    public Carregador(String daSelecao) {
        this(null, null, daSelecao, (Vlrs) null, null);
    }

    public Carregador(Banco doBanco, String daSelecao) {
        this(doBanco, null, daSelecao, (Vlrs) null, null);
    }

    public Carregador(String daSelecao, Vlrs comParametros) {
        this(null, null, daSelecao, comParametros, null);
    }

    public Carregador(Banco doBanco, String daSelecao, Vlrs comParametros) {
        this(doBanco, null, daSelecao, comParametros, null);
    }

    public Carregador(Integer porPartes, String daSelecao) {
        this(null, porPartes, daSelecao, (Vlrs) null, null);
    }

    public Carregador(Banco doBanco, Integer porPartes, String daSelecao) {
        this(doBanco, porPartes, daSelecao, (Vlrs) null, null);
    }

    public Carregador(Integer porPartes, String daSelecao, Vlrs comParametros) {
        this(null, porPartes, daSelecao, comParametros, null);
    }

    public Carregador(Banco doBanco, Integer porPartes, String daSelecao, Vlrs comParametros) {
        this(doBanco, porPartes, daSelecao, comParametros, null);
    }

    public Carregador(String daSelecao, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(null, null, daSelecao, (Vlrs) null, eAoCarregar);
    }

    public Carregador(Banco doBanco, String daSelecao, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(doBanco, null, daSelecao, (Vlrs) null, eAoCarregar);
    }

    public Carregador(String daSelecao, Vlrs comParametros, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(null, null, daSelecao, comParametros, eAoCarregar);
    }

    public Carregador(Banco doBanco, String daSelecao, Vlrs comParametros, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(doBanco, null, daSelecao, comParametros, eAoCarregar);
    }

    public Carregador(Integer porPartes, String daSelecao, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(null, porPartes, daSelecao, (Vlrs) null, eAoCarregar);
    }

    public Carregador(Banco doBanco, Integer porPartes, String daSelecao, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(doBanco, porPartes, daSelecao, (Vlrs) null, eAoCarregar);
    }

    public Carregador(Integer porPartes, String daSelecao, Vlrs comParametros, Analisado<Conjunto, Boolean> eAoCarregar) {
        this(null, porPartes, daSelecao, comParametros, eAoCarregar);
    }

    public Carregador(Banco doBanco, Integer porPartes, String daSelecao, Vlrs comParametros, Analisado<Conjunto, Boolean> eAoCarregar) {
        super("Carregador");
        selecao = daSelecao;
        parametros = comParametros;
        editor = null;
        primeiro = true;
        carregar = true;
        manterAberto = false;
        pausar = false;
        parar = false;
        errou = false;
        fechar = false;
        configura(doBanco, porPartes, eAoCarregar);
    }

    private void configura(Banco doBanco, Integer porPartes, Analisado<Conjunto, Boolean> eAoCarregar) {
        aoCarregar = eAoCarregar;
        if (doBanco == null) {
            banco = (Conexao) Globais.pega("mainConc");
        } else {
            banco = doBanco;
        }
        desconectaAoTerminar = false;
        if (porPartes == null) {
            try {
                partes = 30;
                Conexao main = (Conexao) Globais.pega("mainConc");
                if (main != null) {
                    if (main.estaConectado()) {
                        partes = main.conCfgs().pegaInt("PointelInfo - Carregador - Partes", 30);
                    } else {
                        partes = 30;
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        } else {
            partes = porPartes;
        }
    }

    public Carregador mudaNome(String oNome) {
        setName(oNome);
        return this;
    }

    public Boolean ehManterAberto() {
        return manterAberto;
    }

    public Carregador mudaManterAberto(Boolean para) {
        manterAberto = para;
        return this;
    }

    public Carregador botaManterAberto() {
        manterAberto = true;
        return this;
    }

    public Carregador tiraManterAberto() {
        manterAberto = false;
        return this;
    }

    public Boolean desconectaAoTerminar() {
        return desconectaAoTerminar;
    }

    public Carregador mudaDesconectaAoTerminar(Boolean para) {
        desconectaAoTerminar = para;
        return this;
    }

    public Carregador botaDesconectaAoTerminar() {
        desconectaAoTerminar = true;
        return this;
    }

    public Carregador tiraDesconectaAoTerminar() {
        desconectaAoTerminar = false;
        return this;
    }

    public String selecao() {
        return selecao;
    }

    public Carregador mudaSelecao(String comSelecao) {
        selecao = comSelecao;
        return this;
    }

    public Vlrs parametros() {
        return parametros;
    }

    public Carregador mudaParametros(Vlrs eParametros) {
        parametros = eParametros;
        return this;
    }

    public Carregador mudaSelecao(String comSelecao, Vlrs eParametros) {
        selecao = comSelecao;
        parametros = eParametros;
        return this;
    }

    public List<Dados.Tp> tipos() {
        return tipos;
    }

    public Carregador mudaTipos(List<Dados.Tp> osTipos) {
        tipos = osTipos;
        return this;
    }

    public EdtPla editor() {
        return editor;
    }

    public Carregador mudaEditor(EdtPla para) {
        editor = para;
        return this;
    }

    public Analisado<Conjunto, Boolean> aoCarregar() {
        return aoCarregar;
    }

    public Carregador mudaAoCarregar(Analisado<Conjunto, Boolean> aAcao) {
        this.aoCarregar = aAcao;
        return this;
    }

    public Analisado<Throwable, Boolean> aoErrar() {
        return aoErrar;
    }

    public Carregador botaAoErrar(Analisado<Throwable, Boolean> aAcao) {
        this.aoErrar = aAcao;
        return this;
    }

    public AbstractAction aoModificar() {
        return aoModificar;
    }

    public Carregador botaAoModificar(AbstractAction aAcao) {
        this.aoModificar = aAcao;
        return this;
    }

    public AbstractAction aoTerminar() {
        return aoTerminar;
    }

    public Carregador botaAoTerminar(AbstractAction aAcao) {
        this.aoTerminar = aAcao;
        return this;
    }

    public Carregador inicia() {
        start();
        return this;
    }

    public Carregador carrega() {
        carregar = true;
        return this;
    }

    public Carregador pausa() {
        pausar = true;
        return this;
    }

    public Carregador continua() {
        pausar = false;
        return this;
    }

    public Boolean pausado() {
        return pausar;
    }

    public Carregador parar() {
        parar = true;
        return this;
    }

    public Carregador fecha() {
        parar = true;
        fechar = true;
        return this;
    }

    public Boolean errou() {
        return errou;
    }

    @Override
    public void run() {
        while (!fechar) {
            try {
                if (!carregar) {
                    UtlBin.esperaSegundo();
                }
                while (carregar) {
                    carregar = false;
                    pausar = false;
                    parar = false;
                    errou = false;
                    TabCarregador tabCarregador = null;
                    TabSelecionador tabSelecao = null;
                    if (editor != null) {

                    }
                    String selAtual = selecao;
                    Vlrs parsAtual = null;
                    if (parametros != null) {
                        parsAtual = new Vlrs(parametros.toArray());
                    }
                    Integer prtsAtual = partes;
                    if (editor != null) {
                        tabCarregador = new TabCarregador(editor.controle());
                        tabSelecao = new TabSelecionador(editor.controle());
                        tabCarregador.mudaDeveAtualizar(!primeiro);
                        tabCarregador.reinicia();
                        tabSelecao.registra();
                    }
                    primeiro = false;
                    if (!manterAberto) {
                        fechar = true;
                    }
                    Long off = 0l;
                    Integer numLinha = 0;
                    while (true) {
                        String slt = selAtual + " LIMIT " + prtsAtual + " OFFSET " + off;
                        Conjunto res = banco.busca(slt, parsAtual, tipos);
                        off = off + prtsAtual;
                        if (res.vazio()) {
                            break;
                        } else {
                            if (aoCarregar != null) {
                                if (!aoCarregar.analisa(res)) {
                                    break;
                                }
                            }
                            if (editor != null) {
                                for (Vlrs vlrs : res) {
                                    if (editor.controlador().ehMostraNumeroLinhas()) {
                                        numLinha++;
                                        vlrs.add(0, new VInt(numLinha));
                                    }
                                    tabCarregador.bota(vlrs.toArray());
                                    while (pausar) {
                                        UtlBin.esperaMilis(100);
                                    }
                                    if (parar) {
                                        break;
                                    }
                                }
                            }
                        }
                        while (pausar) {
                            UtlBin.esperaMilis(100);
                        }
                        if (parar) {
                            break;
                        }
                    }
                    if (editor != null) {
                        tabCarregador.limpaExcesso();
                        tabSelecao.reseleciona();
                        if (tabCarregador.foiModificado()) {
                            if (aoModificar != null) {
                                aoModificar.actionPerformed(null);
                            }
                        }
                    }
                    if (aoTerminar != null) {
                        aoTerminar.actionPerformed(null);
                    }
                    if (desconectaAoTerminar) {
                        banco.desconecta();
                    }
                }
            } catch (Exception ex) {
                errou = true;
                if (aoErrar != null) {
                    try {
                        fechar = aoErrar.analisa(ex);
                    } catch (Exception ex2) {
                        Utl.registra(ex2);
                    }
                } else {
                    Utl.registra(ex);
                }
            }
        }
    }
}
