package pin.modamk;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.List;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Configs;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLog;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;
import pin.modinf.Conexao;

public class Rotina {

    public volatile Configs cfgs;
    public volatile Conexao conexao;
    public volatile String titulo;
    public volatile String descricao;
    public volatile Campos campos;
    public volatile List<CadReferenciar> cadReferenciar;
    public volatile ActionListener aoConfirmar;
    public volatile Boolean fecharAoConfirmar;
    public volatile Intf intf;

    public Rotina(String comTitulo, Cmp[] eCampos) {
        this(comTitulo, false, eCampos);
    }

    public Rotina(String comTitulo, Boolean eFecharAoConfirmar, Cmp[] eCampos) {
        cfgs = ((Configs) Globais.pega("mainConf"));
        conexao = ((Conexao) Globais.pega("mainConc"));
        titulo = comTitulo;
        campos = new Campos(eCampos);
        fecharAoConfirmar = eFecharAoConfirmar;
        aoConfirmar = null;
        descricao = null;
    }

    public Rotina botaDescricao(String aDescricao) {
        descricao = aDescricao;
        return this;
    }

//    private void atualizarEstrangeiros(Cmp daOrigem) {
//        for (Cmp cmp : campos) {
//            if (cmp.estrangeiro) {
//                if (UtlLis.tem(daOrigem.nome, cmp.parametros)) {
//                    atualizarEstrangeiro(cmp);
//                }
//            }
//        }
//    }
//
//    private void atualizarEstrangeiro(Cmp doDestino) {
//        new Thread("Rotina Atualizar Estrangeiro") {
//            @Override
//            public void run() {
//                try {
//                    doDestino.editor.botaAtualizando();
//                    Vlrs parametros = new Vlrs();
//                    if (doDestino.parametros != null) {
//                        for (int ip = 0; ip < doDestino.parametros.length; ip++) {
//                            parametros.novo(pegaVlr(doDestino.parametros[ip]));
//                        }
//                    }
//
//                    doDestino.editor.mdVlr(conexao.busca(doDestino.selecao, parametros).pgCol());
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                } finally {
//                    doDestino.editor.tiraAtualizando();
//                }
//            }
//        }.start();
//    }
//
//    public Boolean temEstrangeiros(Cmp aOrigem) {
//        Boolean retorno = false;
//        for (Cmp cmp : campos) {
//            if (cmp.estrangeiro) {
//                if (UtlLis.tem(aOrigem.nome, cmp.parametros)) {
//                    retorno = true;
//                    break;
//                }
//            }
//        }
//        return retorno;
//    }
//
//    public void botaReferencia(CadReferenciar aReferenciar) {
//        if (cadReferenciar == null) {
//            cadReferenciar = new ArrayList<>();
//        }
//        cadReferenciar.add(aReferenciar);
//    }
//
//    public void buscarReferencia() {
//        Component cfo = janela.getFocusOwner();
//        Cmp cmp = pegaCampo(cfo);
//        if (cmp != null) {
//            CadReferenciar ref = pegaReferencia(cmp.nome);
//            if (ref != null) {
//                try {
//                    Object cad = ref.buscar.getDeclaredConstructor().newInstance();
//                    ((Cadastro) cad).mostraReferenciador(ref);
//                } catch (Exception ex) {
//                    Utl.registra(ex);
//                }
//            }
//        }
//    }
    public Cmp pegaCampo(String comNome) {
        return campos.pega(comNome);
    }

    public Cmp pegaCampo(Component doEditor) {
        return campos.pega(doEditor);
    }

    public Cmp pegaCampoDaReferencia(String aReferencia) {
        Cmp retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (aReferencia.equals(campos.get(i1).pDetalhe("referencia"))) {
                retorno = campos.get(i1);
                break;
            }
        }
        return retorno;
    }

    public Integer pegaCampoIndice(String comNome) {
        Integer retorno = -1;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).pNome().equals(comNome)) {
                retorno = i1;
            }
        }
        return retorno;
    }

    public CadReferenciar pegaReferencia(String aReferenciado) {
        CadReferenciar retorno = null;
        for (CadReferenciar ref : cadReferenciar) {
            if (ref.selecionado.equals(aReferenciado)) {
                retorno = ref;
                break;
            }
        }
        return retorno;
    }

    public Rotina mostra() throws Exception {
        intf = new Intf(descricao, campos) {
            @Override
            public void preparaIntf() throws Exception {
                botaConfirmarECancelar();
            }

            @Override
            public void aoConfirmar(Object comOrigem) throws Exception {
                super.aoConfirmar(comOrigem);
                for (Cmp cmp : campos) {
                    if (cmp.pObrigatorio()) {
                        if (cmp.pEdt().vazio()) {
                            throw new Erro("Necessário Preencher o Campo " + cmp.pTitulo());
                        }
                    }
                }
                if (aoConfirmar != null) {
                    aoConfirmar.actionPerformed(null);
                }
                if (fecharAoConfirmar) {
                    fecha();
                }
            }
        };
        intf.mFecharAoConfirmar(fecharAoConfirmar);
        intf.mostra(titulo);
        return this;
    }

    public void fechar() {
        intf.fecha();
    }

    public synchronized Object pegaVlr(String doCampo) throws Exception {
        return pegaVlr(doCampo, null);
    }

    public synchronized Object pegaVlr(String doCampo, Object comPadrao) throws Exception {
        Object retorno = comPadrao;
        for (Cmp cmp : campos) {
            if (cmp.pNome().equals(doCampo)) {
                retorno = cmp.pEdt().pgVlr();
                break;
            }
        }
        return retorno;
    }

    public Boolean botaVlr(String noCampo, Object oValor) throws Exception {
        Boolean retorno = false;
        for (Cmp cmp : campos) {
            if (cmp.pNome().equals(noCampo)) {
                cmp.pEdt().mdVlr(oValor);
                retorno = true;
                break;
            }
        }
        return retorno;
    }

    public Boolean pegaLog(String doCampo) throws Exception {
        return UtlLog.pega(pegaVlr(doCampo, null), null);
    }

    public Boolean pegaLog(String doCampo, Boolean comPadrao) throws Exception {
        return UtlLog.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public String pegaCrs(String doCampo) throws Exception {
        return UtlCrs.pega(pegaVlr(doCampo, null), null);
    }

    public String pegaCrs(String doCampo, String comPadrao) throws Exception {
        return UtlCrs.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public Integer pegaInt(String doCampo) throws Exception {
        return UtlInt.pega(pegaVlr(doCampo, null), null);
    }

    public Integer pegaInt(String doCampo, Integer comPadrao) throws Exception {
        return UtlInt.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public Long pegaIntLon(String doCampo) throws Exception {
        return UtlIntLon.pega(pegaVlr(doCampo, null), null);
    }

    public Long pegaIntLon(String doCampo, Long comPadrao) throws Exception {
        return UtlIntLon.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public Float pegaNum(String doCampo) throws Exception {
        return UtlNum.pega(pegaVlr(doCampo, null), null);
    }

    public Float pegaNum(String doCampo, Float comPadrao) throws Exception {
        return UtlNum.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public Double pegaNumLon(String doCampo) throws Exception {
        return UtlNumLon.pega(pegaVlr(doCampo, null), null);
    }

    public Double pegaNumLon(String doCampo, Double comPadrao) throws Exception {
        return UtlNumLon.pega(pegaVlr(doCampo, comPadrao), comPadrao);
    }

    public void limpar() throws Exception {
        for (Cmp cmp : campos) {
            cmp.pEdt().mdVlr(cmp.pVlrInicial());
        }
    }
}
