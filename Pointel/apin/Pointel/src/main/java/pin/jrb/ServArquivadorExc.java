package pin.jrb;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.AbstractAction;
import pin.libbas.Analisado;
import pin.libbas.Conjunto;
import pin.libbas.MultiParalelo;
import pin.libbas.Paralelo;
import pin.libbas.ParsVals;
import pin.libbas.Retornos;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlrs;
import pin.modinf.Carregador;
import pin.modinf.Conexao;
import pin.modinf.Tabela;

public class ServArquivadorExc {

    private volatile ServArquivador arquivar;
    private volatile Retornos retornos;
    private volatile AbstractAction aoTerminar;
    private volatile String iniciado;
    private volatile Conexao conArquivar;
    private volatile java.sql.Timestamp dataCorte;
    private volatile Vlrs vlrsCorte;
    private volatile MultiParalelo<Referencia> mltParte1;
    private volatile MultiParalelo<Referencia> mltParte2;
    private volatile ParsVals<Referencia> valsExcluir;
    private volatile Boolean errouInicio;
    private volatile Boolean terminouInicio;
    private volatile Boolean errouCarrega;
    private volatile AtomicInteger carregadores;
    private volatile AtomicInteger terminouCarrega;
    private volatile Boolean errouSalva;
    private volatile AtomicInteger salvadores;
    private volatile AtomicInteger terminouSalva;
    private volatile Boolean errouExclui;
    private volatile Boolean terminouExclui;

    public ServArquivadorExc(ServArquivador doArquivar, Retornos comRetornos) {
        this(doArquivar, comRetornos, null);
    }

    public ServArquivadorExc(ServArquivador doArquivar, Retornos comRetornos, AbstractAction eAoTerminar) {
        arquivar = doArquivar;
        retornos = comRetornos;
        aoTerminar = eAoTerminar;
    }

    public void executar() throws Exception {
        //Todo
    }

    private class Referencia {

        public volatile String esquemaENome;
        public volatile String campoData;
        public volatile Boolean terminouCarregamento;
        public volatile AtomicInteger filhasQuantidade;
        public volatile AtomicInteger filhasCarregadas;
        public volatile ParsVals<Conjunto> carregados;
        public volatile AtomicInteger salvosQuantidade;
        public volatile AtomicInteger salvosTerminados;
        public volatile ParsVals<String> cmdExclusao;

        public Referencia(String esquemaENome) {
            this(esquemaENome, null);
        }

        public Referencia(String esquemaENome, String campoData) {
            this.esquemaENome = esquemaENome;
            this.campoData = campoData;
            terminouCarregamento = false;
            filhasQuantidade = new AtomicInteger(0);
            filhasCarregadas = new AtomicInteger(0);
            carregados = new ParsVals<>();
            salvosQuantidade = new AtomicInteger(0);
            salvosTerminados = new AtomicInteger(0);
            cmdExclusao = new ParsVals<>();
        }
    }

    public Boolean errouPrincipal() {
        return errouInicio || errouCarrega || errouSalva || errouExclui;
    }

    private class ParInicia extends Paralelo<Referencia> {

        private Conexao conexao;
        private List<String> feitos;

        public ParInicia(MultiParalelo doMultiplo) {
            super(doMultiplo, "Inicia");
            feitos = new ArrayList<>();
        }

        private void bota(Referencia aReferencia) {
            retorna("Nova Referência", aReferencia.esquemaENome);
            botaPrimeiro(aReferencia);
            feitos.add(aReferencia.esquemaENome);
        }

        @Override
        public void run() {
            try {
                conexao = conArquivar.replicaLeve();
                bota(new Referencia("public.auditoria", "momento"));
                if ("I".equals(arquivar.conjunto)) {
                    bota(new Referencia("public.lancamentos", "data"));
                    bota(new Referencia("public.trocas", "data"));
                    bota(new Referencia("public.faturas", "emitido_data"));
                    bota(new Referencia("public.pedidos", "emitido_data"));
                    bota(new Referencia("public.mobile_arquivos", "data"));
                    bota(new Referencia("public.prepedidos", "emitido_data"));
                    bota(new Referencia("public.entradas_materiais", "data"));
                    bota(new Referencia("public.entradas_produtos", "data"));
                    bota(new Referencia("public.mensagens", "data"));
                    bota(new Referencia("public.chamados", "fechou_data"));
                    for (int it = 0; it < conexao.bancoTabelas().tamanho(); it++) {
                        if (errouPrincipal()) {
                            retorna("Errou Principal");
                            break;
                        }
                        Tabela tab = conexao.bancoTabelas().pega(it);
                        retorna("Verifica Referência da Tabela", tab.pEsquemaENome());
                        if (!tab.ehFilha()) {
                            if (!feitos.contains(tab.pEsquemaENome())) {
                                bota(new Referencia(tab.pEsquemaENome()));
                            }
                        } else {
                            retorna("Tabela é Filha", tab.pEsquemaENome());
                        }
                    }
                }
                retorna("Terminou Inicio");
                terminouInicio = true;
            } catch (Exception ex) {
                retorna("Errou Inicio", ex);
                errouInicio = true;
            }
        }
    }

    private class ParCarrega extends Paralelo<Referencia> {

        private Conexao conexao;
        private AoErrar aoErrar;

        public ParCarrega(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice, "Carrega");
        }

        @Override
        public void run() {
            try {
                aoErrar = new AoErrar();
                retorna("Replicando Conexão");
                conexao = conArquivar.replicaLeve();
                while (true) {
                    if (errouPrincipal()) {
                        retorna("Errou Principal");
                        break;
                    }
                    Referencia ref = tiraUltimo();
                    if (ref == null && terminouInicio) {
                        retorna("Não Há Nada Mais a Carregar");
                        break;
                    }
                    if (ref != null) {
                        retorna("Iniciando", ref.esquemaENome);
                        Tabela tab = conexao.bancoTabelas().pega(ref.esquemaENome);
                        String slc = "SELECT " + ref.esquemaENome + ".* FROM " + ref.esquemaENome;
                        String whr = "";
                        Vlrs params = new Vlrs();
                        if (ref.campoData != null && dataCorte != null) {
                            whr = " WHERE " + ref.esquemaENome + "." + ref.campoData + " <= ?";
                            params.novo(dataCorte);
                        }
                        while (carregadores.get() > arquivar.velocidade * 3) {
                            UtlBin.esperaMilis(100);
                        }
                        carregadores.incrementAndGet();
                        retorna("Iniciando Carregamento", slc + whr);
                        new Carregador(conArquivar.replicaLeve(), 300, slc + whr, params, new AoCarregar(ref, tab))
                                .botaAoErrar(aoErrar)
                                .botaAoTerminar(new ActTerminar(ref))
                                .botaDesconectaAoTerminar()
                                .inicia();
                        for (Tabela filha : tab.pFilhas()) {
                            ref.filhasQuantidade.incrementAndGet();
                            if (!whr.isEmpty()) {
                                String dltFilha = "DELETE FROM " + filha.pEsquemaENome();
                                String juncao = tab.fazJuncao(filha);
                                String using = UtlCrs.troca(juncao, "JOIN", "USING");
                                using = UtlCrs.troca(using, "ON", "WHERE");
                                dltFilha += using;
                                dltFilha += UtlCrs.troca(whr, "WHERE", "AND");
                                ref.cmdExclusao.botaPrimeiro(dltFilha);
                            }
                            String slcFilha = "SELECT " + filha.pEsquemaENome() + ".* FROM " + filha.pEsquemaENome();
                            slcFilha += tab.fazJuncao(filha);
                            while (carregadores.get() > arquivar.velocidade * 3) {
                                UtlBin.esperaMilis(100);
                            }
                            carregadores.incrementAndGet();
                            retorna("Iniciando Carregamento Filha", slcFilha + whr);
                            new Carregador(conArquivar.replicaLeve(), 300, slcFilha + whr, params, new AoCarregar(ref, filha))
                                    .botaAoErrar(aoErrar)
                                    .botaAoTerminar(new ActTerminarFilha(ref))
                                    .botaDesconectaAoTerminar()
                                    .inicia();
                        }
                        if (!whr.isEmpty()) {
                            String dlt = "DELETE FROM " + ref.esquemaENome;
                            dlt += whr;
                            ref.cmdExclusao.botaPrimeiro(dlt);
                        }
                        mltParte2.botaPrimeiro(ref);
                    }
                }
                conexao.desconecta();
                retorna("Conexão Desconectada");
                terminouCarrega.incrementAndGet();
                retorna("Terminou Carrega");
            } catch (Exception ex) {
                retorna("Errou Carrega", ex);
                errouCarrega = true;
            }
        }

        private class AoErrar implements Analisado<Throwable, Boolean> {

            @Override
            public Boolean analisa(Throwable oValor) {
                retorna("Errou Carregador", oValor);
                errouCarrega = true;
                return false;
            }

        }

        private class AoCarregar implements Analisado<Conjunto, Boolean> {

            private volatile Referencia referencia;
            private volatile Tabela tabela;

            public AoCarregar(Referencia refCarregador, Tabela tabela) {
                this.referencia = refCarregador;
                this.tabela = tabela;
            }

            @Override
            public Boolean analisa(Conjunto oValor) {
                retorna("Carregou Conjunto", referencia.esquemaENome);
                referencia.salvosQuantidade.incrementAndGet();
                oValor.mudaNome(tabela.pEsquemaENome());
                referencia.carregados.botaPrimeiro(oValor);
                return true;
            }

        }

        private class ActTerminar extends AbstractAction {

            private Referencia referencia;

            public ActTerminar(Referencia referencia) {
                this.referencia = referencia;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                retorna("Terminou Carregador", referencia.esquemaENome);
                referencia.terminouCarregamento = true;
                carregadores.decrementAndGet();
            }
        }

        private class ActTerminarFilha extends AbstractAction {

            private Referencia referencia;

            public ActTerminarFilha(Referencia referencia) {
                this.referencia = referencia;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                retorna("Terminou Carregador Filha", referencia.esquemaENome);
                referencia.filhasCarregadas.incrementAndGet();
                carregadores.decrementAndGet();
            }
        }
    }

    private class ParSalva extends Paralelo<Referencia> {

        public ParSalva(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice, "Salva");
        }

        @Override
        public void run() {
            //TODO
        }

        private class Salvador extends Thread {

            private Referencia referencia;
            private Conjunto conjunto;

            public Salvador(Referencia refSalvador, Conjunto conjunto) {
                super("Arquivador Salvador");
                this.referencia = refSalvador;
                this.conjunto = conjunto;
            }

            @Override
            public void run() {
                //TODO
            }
        }
    }

    private class ParExclui extends Paralelo<Referencia> {

        private Conexao conexao;
        private String cmdDelete;

        public ParExclui() {
            super("Arquivador Exclui");
            mudaRetornos(retornos);
        }

        @Override
        public void run() {
            //TODO
        }
    }

}
