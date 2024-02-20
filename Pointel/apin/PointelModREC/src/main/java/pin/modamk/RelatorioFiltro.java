package pin.modamk;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import pin.libamg.Edt;
import pin.libbas.Configs;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libvlr.Vlr;
import pin.libvlr.Vlrs;
import pin.modinf.Conexao;

public class RelatorioFiltro {

    public ArrayList<RelatorioPagina> paginas;
    public ArrayList<RelatorioFiltroCampo> campos;
    public Relatorio relatorio;
    public String tabela;

    public String ordem;
    public String ordemTitulos;

    public ArrayList<CadRelacao> cadEstrangeiros;
    public ArrayList<CadReferenciar> cadReferenciar;

    public JFrame jfrJanela;
    public JPanel jpnJanela;
    public JTabbedPane jtbAbas;

    public JPanel jpnCampos;
    public JPanel jpnOpcoes;
    public JPanel jpnAcoes;
    public JPanel jpnBarra;

    public JButton jbtImprimir;

    public RelatorioFiltro(Relatorio aRelatorio, RelatorioFiltroCampo[] aCampos) {
        relatorio = aRelatorio;
        tabela = null;
        paginas = relatorio.paginas;
        campos = new ArrayList<>(Arrays.asList(aCampos));

        cadEstrangeiros = null;
        cadReferenciar = null;

        ordem = "";
        ordemTitulos = "";
    }

    public RelatorioFiltro(Relatorio aRelatorio, String aTabela, RelatorioFiltroCampo[] aCampos) {
        relatorio = aRelatorio;
        tabela = aTabela;
        paginas = relatorio.paginas;
        campos = new ArrayList<>(Arrays.asList(aCampos));

        cadEstrangeiros = null;
        cadReferenciar = null;
        ordem = "";
        ordemTitulos = "";
    }

    public void botaEstrangeiro(CadRelacao aEstrangeiro) {
        if (cadEstrangeiros == null) {
            cadEstrangeiros = new ArrayList<>();
        }

        cadEstrangeiros.add(aEstrangeiro);
    }

    public void botaReferencia(CadReferenciar aReferenciar) {
        if (cadReferenciar == null) {
            cadReferenciar = new ArrayList<>();
        }

        cadReferenciar.add(aReferenciar);
    }

    public void botaOrdemTitulos(String aTitulos) {
        ordem = "";
        ordemTitulos = aTitulos;
        for (String tit : aTitulos.split(";")) {
            RelatorioFiltroCampo cmp = pegaCampoPeloTitulo(tit);
            if (cmp != null) {
                ordem = ordem + (ordem.isEmpty() ? "" : " , ") + (cmp.nome.contains(".") ? "" : tabela + ".") + cmp.nome;
            }
        }
    }

    public void abrir() throws Exception {
        jfrJanela = new JFrame("Filtrar " + relatorio.titulo);
        jfrJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfrJanela.setResizable(false);

        jpnJanela = new JPanel();
        jpnJanela.setLayout(new BorderLayout(4, 4));
        jpnJanela.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        jfrJanela.setContentPane(jpnJanela);

        criarCampos();
        jpnJanela.add(jpnCampos, BorderLayout.CENTER);

        criarBarra();
        jpnJanela.add(jpnBarra, BorderLayout.SOUTH);

        mudaCamposEditavel(true);
        criarAtalhos();

        jfrJanela.getRootPane().setDefaultButton(jbtImprimir);

        Janelas.inicia(jfrJanela, RelatorioFiltro.this);
        jfrJanela.setVisible(true);
    }

    private void criarCampos() throws Exception {
        jpnCampos = new JPanel();
        jpnCampos.setLayout(new BoxLayout(jpnCampos, BoxLayout.PAGE_AXIS));

        jtbAbas = null;
        ArrayList<JPanel> lins = new ArrayList<>();

        int ultAbaIndice = 0;
        String ultAbaNome = "";
        Boolean temAba = false;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.abaIndice != ultAbaIndice) {
                temAba = true;

                if (jtbAbas == null) {
                    jtbAbas = new JTabbedPane();
                }

                if (!ultAbaNome.isEmpty()) {
                    JPanel aba = new JPanel();
                    aba.setLayout(new BoxLayout(aba, BoxLayout.PAGE_AXIS));
                    for (JPanel lin : lins) {
                        aba.add(lin);
                    }

                    jtbAbas.addTab(ultAbaNome, aba);
                    lins.clear();
                }

                ultAbaIndice = cmp.abaIndice;
                ultAbaNome = cmp.abaNome;
            }

            while (cmp.linha > lins.size()) {
                JPanel linv = new JPanel();
                linv.setLayout(new BoxLayout(linv, BoxLayout.LINE_AXIS));
                linv.setAlignmentX(Component.LEFT_ALIGNMENT);
                lins.add(linv);
            }

            Edt edtEditor = Edt.novo(cmp.titulo, cmp.obrigatorio || cmp.chave, cmp.tipo, cmp.tamanho, cmp.precisao, cmp.opcoes, cmp.parametros, cmp.dica, null, cmp.valorInicial);
            edtEditor.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            edtEditor.setMinimumSize(edtEditor.getPreferredSize());
            edtEditor.setMaximumSize(edtEditor.getPreferredSize());
            edtEditor.setSize(edtEditor.getPreferredSize());
            cmp.editor = edtEditor;

            if (cmp.largura > 0) {
                edtEditor.mLargura(cmp.largura);
            }
            if (cmp.altura > 0) {
                edtEditor.mAltura(cmp.altura);
            }

            JPanel lin = lins.get(cmp.linha - 1);
            lin.add(edtEditor);
        }

        if (temAba) {
            if (jtbAbas == null) {
                jtbAbas = new JTabbedPane();
            }

            JPanel aba = new JPanel();
            aba.setLayout(new GridBagLayout());
            for (JPanel lin : lins) {
                aba.add(lin);
            }

            jtbAbas.addTab(ultAbaNome, aba);
            lins.clear();
        }

        if (temAba) {
            jpnCampos.add(jtbAbas);
        } else {
            for (JPanel linCmps : lins) {
                jpnCampos.add(linCmps);
            }
        }

        criarEstrangeiros();
    }

    private void criarBarra() {
        jpnBarra = new JPanel(new BorderLayout(4, 4));

        jpnAcoes = new JPanel(new FlowLayout(FlowLayout.LEADING, 4, 4));

        RelatorioFiltro ths = this;
        JButton jbtOrdenar = new JButton("Ordenar");
        jbtOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RelatorioFiltroOrdenar ord = new RelatorioFiltroOrdenar(ths);
                ord.setVisible(true);
            }
        });
        jpnAcoes.add(jbtOrdenar);

        jbtImprimir = new JButton("Imprimir");
        jbtImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String faltou = "";
                    for (RelatorioFiltroCampo cmp : campos) {
                        if (cmp.obrigatorio) {
                            if (cmp.editor.vazio()) {
                                faltou = faltou + (faltou.isEmpty() ? "" : " , ") + cmp.titulo;
                            }
                        }
                    }

                    if (!faltou.isEmpty()) {
                        Utl.alerta("Faltou Preencher Os Campos Obrigatórios:\n\n" + faltou);
                        return;
                    }

                    new Thread("Processando Relatório") {
                        @Override
                        public void run() {
                            final Integer mId = Utl.alertaPopNovoId();
                            try {
                                Utl.alertaPop(mId, "Processando Relatório " + relatorio.titulo, false);
                                imprimir();
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            } finally {
                                Utl.alertaPopFechar(mId);
                            }
                        }
                    }.start();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        });
        jpnAcoes.add(jbtImprimir);

        JButton jbtCancelar = new JButton("Cancelar");
        jbtCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janelas.execWindowClosing(jfrJanela);
                jfrJanela.setVisible(false);
            }
        });
        jpnAcoes.add(jbtCancelar);

        jpnBarra.add(jpnAcoes, BorderLayout.SOUTH);
    }

    private void criarEstrangeiros() throws Exception {
        if (cadEstrangeiros != null) {
            for (RelatorioFiltroCampo cmp : campos) {
                if (cmp.estrangeiro) {
                    final RelatorioFiltroCampo cmpAtualizar = cmp;
                    String tabNome = cmp.nome.split("\\.")[0];

                    for (CadRelacao est : cadEstrangeiros) {
                        if (est.pegaNome().equals(tabNome)) {
                            for (CmpRelacao crl : est.relacoes()) {
                                RelatorioFiltroCampo cmpChave = pegaCampo(crl.pegaReferenciado());
                                cmpChave.editor.controle().addFocusListener(new FocusAdapter() {
                                    @Override
                                    public void focusLost(FocusEvent e) {
                                        atualizarEstrangeiro(cmpAtualizar);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    private void criarAtalhos() {
        Janelas.botaAtalho(jfrJanela, "Buscar Referencia", "F5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarReferencia();
            }
        });

        Janelas.botaAtalho(jfrJanela, "Criar Referencia", "F6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarReferencia();
            }
        });
    }

    public String[] pegaCamposTitulos() {
        String[] retorno = new String[campos.size()];
        for (int i1 = 0; i1 < campos.size(); i1++) {
            retorno[i1] = campos.get(i1).titulo;
        }
        return retorno;
    }

    public String[] pegaCamposTitulosOrdenaveis() {
        String[] retorno = new String[campos.size()];
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).podeOrdenar) {
                retorno[i1] = campos.get(i1).titulo;
            }
        }
        return retorno;
    }

    public RelatorioFiltroCampo pegaCampo(String aNome) {
        RelatorioFiltroCampo retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).nome.equals(aNome)) {
                retorno = campos.get(i1);
                break;
            }
        }
        return retorno;
    }

    public RelatorioFiltroCampo pegaCampo(Component aEditor) {
        RelatorioFiltroCampo retorno = null;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.editor.controle().equals(aEditor)) {
                retorno = cmp;
                break;
            }
        }
        return retorno;
    }

    public RelatorioFiltroCampo pegaCampoPeloTitulo(String oTitulo) {
        RelatorioFiltroCampo retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).titulo.equals(oTitulo)) {
                retorno = campos.get(i1);
                break;
            }
        }
        return retorno;
    }

    public String pegaCampoNomeCompostoTitulo(String aTitulo) {
        String retorno = null;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).titulo.equals(aTitulo)) {
                retorno = (campos.get(i1).nome.contains(".") ? "" : tabela + ".") + campos.get(i1).nome;
                break;
            }
        }
        return retorno;
    }

    public Integer pegaCampoIndice(String aNome) {
        Integer retorno = -1;
        for (int i1 = 0; i1 < campos.size(); i1++) {
            if (campos.get(i1).nome.equals(aNome)) {
                retorno = i1;
            }
        }
        return retorno;
    }

    public void mudaCamposEditavel(Boolean aEditavel) {
        for (RelatorioFiltroCampo cmp : campos) {
            if ((!cmp.estrangeiro) && (cmp.valorFixo == null) && (cmp.editavel)) {
                cmp.editor.mEditavel(aEditavel);
            } else {
                cmp.editor.mEditavel(false);
            }
        }
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

    private String botaTabelaAssociada(String aTabela, CadRelacao doEstrangeiro, CmpRelacao daRelacao, ArrayList<Object> comParametros) throws Exception {
        String retorno = "";

        for (CadRelacao cadEst : cadEstrangeiros) {
            if (cadEst.pegaNome().equals(aTabela)) {
                retorno += " JOIN " + cadEst.pegaEsquemaETabela() + (cadEst.pegaAlias().isEmpty() ? "" : " AS " + cadEst.pegaAlias()) + " ON ";
                retorno += daRelacao.pegaReferenciado() + " = " + doEstrangeiro.pegaNome() + "." + daRelacao.pegaReferencia() + " AND ";

                ArrayList<String> sqlAssoc = new ArrayList<>();
                ArrayList<Object> obsAssoc = new ArrayList<>();
                for (CmpRelacao crl : cadEst.relacoes()) {
                    if (!crl.pegaReferenciado().contains(".")) {
                        retorno += cadEst.pegaNome() + "." + crl.pegaReferencia() + " = ? AND ";
                        comParametros.add(pegaCampo(crl.pegaReferenciado()).editor.pgVlr());
                    } else {
                        String tabAss = crl.pegaReferenciado().split("\\.")[0];
                        if (cadEst.pegaNome().equals(tabAss)) {
                            retorno += cadEst.pegaNome() + "." + crl.pegaReferencia() + " = ? AND ";
                            comParametros.add(pegaCampo(crl.pegaReferenciado()).editor.pgVlr());
                        } else {
                            String sqlTabAss = botaTabelaAssociada(tabAss, cadEst, crl, obsAssoc);
                            sqlAssoc.add(sqlTabAss);
                        }
                    }
                }

                retorno = UtlCrs.corta(retorno, 5);

                for (String sAs : sqlAssoc) {
                    retorno += " " + sAs;
                }

                for (Object oAs : obsAssoc) {
                    comParametros.add(oAs);
                }

                break;
            }

        }
        return retorno;
    }

    public void atualizarEstrangeiro(RelatorioFiltroCampo aCampo) {
        new Thread("Relatório Filtro Atualizar Estrangeiro") {

            @Override
            public void run() {
                if (cadEstrangeiros != null) {
                    try {
                        aCampo.editor.botaAtualizando();
                        String sqlSelect = "SELECT ";

                        String[] parsNm = aCampo.nome.split("\\.");

                        String tabOrigem = parsNm[0];
                        String cmpNome = parsNm[1];

                        String sqlWhere = " WHERE ";

                        ArrayList<Object> parsAssoc = new ArrayList<>();
                        ArrayList<Object> parsWhere = new ArrayList<>();

                        for (CadRelacao cadEst : cadEstrangeiros) {
                            if (cadEst.pegaNome().equals(tabOrigem)) {
                                sqlSelect += cadEst.pegaNome() + "." + cmpNome + " FROM " + cadEst.pegaEsquemaETabela() + (cadEst.pegaAlias().isEmpty() ? "" : " AS " + cadEst.pegaAlias());

                                for (CmpRelacao crl : cadEst.relacoes()) {
                                    if (!crl.pegaReferenciado().contains(".")) {
                                        sqlWhere += cadEst.pegaNome() + "." + crl.pegaReferencia() + " = ? AND ";
                                        parsWhere.add(pegaCampo(crl.pegaReferenciado()).editor.pgVlr());
                                    } else {
                                        String tabAss = crl.pegaReferenciado().split("\\.")[0];
                                        if (cadEst.pegaNome().equals(tabAss)) {
                                            sqlWhere += crl.pegaReferencia() + " = ? AND ";
                                            parsWhere.add(pegaCampo(crl.pegaReferenciado()).editor.pgVlr());
                                        } else {
                                            sqlSelect += botaTabelaAssociada(tabAss, cadEst, crl, parsAssoc);
                                        }
                                    }
                                }

                                break;
                            }
                        }

                        if (!sqlWhere.equals(" WHERE ")) {
                            sqlSelect += UtlCrs.corta(sqlWhere, 5);
                        }

                        Vlrs todosPars = new Vlrs();

                        for (Object par : parsAssoc) {
                            todosPars.novo(par);
                        }

                        for (Object par : parsWhere) {
                            todosPars.novo(par);
                        }

                        Conjunto rst = relatorio.conexao.busca(sqlSelect, todosPars);
                        if (rst.proximo()) {
                            aCampo.editor.mdVlr(rst.pgVlr(1));
                        }
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    } finally {
                        aCampo.editor.tiraAtualizando();
                    }
                }
            }

        }.start();

    }

    public void buscarReferencia() {
        Component cfo = jfrJanela.getFocusOwner();
        RelatorioFiltroCampo cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.nome);
            if (ref != null) {
                try {
                    Object cad = ref.buscar.getDeclaredConstructor().newInstance();
                    ((Cadastro) cad).mostraReferenciador(ref);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void criarReferencia() {
        Component cfo = jfrJanela.getFocusOwner();
        RelatorioFiltroCampo cmp = pegaCampo(cfo);
        if (cmp != null) {
            CadReferenciar ref = pegaReferencia(cmp.nome);
            if (ref != null) {
                try {
                    Object cad = ref.buscar.getDeclaredConstructor(Configs.class, Conexao.class).newInstance(relatorio.cfgs, relatorio.conexao);
                    ((Cadastro) cad).mostra();
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
    }

    public void imprimir() throws Exception {
        relatorio.finalizar();
    }

    public String pegaOperador(RelatorioFiltroCampo aCampo, Integer comIndice) {
        String retorno = "=";
        if (aCampo.operador != null) {
            retorno = aCampo.operador + " ?";
        } else if (aCampo.tipo == Dados.Tp.Crs) {
            retorno = "LIKE ?";
        } else {
            retorno = "= ?";
        }
        return retorno;
    }

    public String pegaCondicao(String comComeco) throws Exception {
        return pegaCondicao(comComeco, 1);
    }

    public String pegaCondicao(String comComeco, Integer indiceInicial) throws Exception {
        String cond = "";
        int ic = indiceInicial;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (!cmp.estrangeiro) {
                    if ((cmp.obrigatorio) || (!cmp.editor.vazio())) {
                        cond = cond + (cmp.nome.contains(".") ? "" : tabela + ".") + cmp.nome + " " + pegaOperador(cmp, ic) + " AND ";
                        ic++;
                    }
                }
            }
        }
        if (!cond.isEmpty()) {
            cond = UtlCrs.corta(cond, 5);
            cond = (comComeco.isEmpty() ? "" : comComeco + " ") + cond;
        }
        return " " + cond + " ";
    }

    public String pegaCondicao(String comComeco, String comTermino) throws Exception {
        String cond = "";

        int ic = 1;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (!cmp.estrangeiro) {
                    if ((cmp.obrigatorio) || (!cmp.editor.vazio())) {
                        cond = cond + (cmp.nome.contains(".") ? "" : tabela + ".") + cmp.nome + " " + pegaOperador(cmp, ic) + " AND ";
                        ic++;
                    }
                }
            }
        }

        if (!cond.isEmpty()) {
            cond = UtlCrs.corta(cond, 5);
            cond = (comComeco.isEmpty() ? "" : comComeco + " ") + cond;
        }

        if (!comTermino.isEmpty()) {
            cond = cond + (cond.isEmpty() ? comComeco : " AND ") + comTermino;
        }

        return " " + cond + " ";
    }

    public String pegaCondicaoObrigatoria(String aComecarCom) {
        String cond = "";

        int ic = 1;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (cmp.obrigatorio) {
                    if (!cmp.estrangeiro) {
                        cond = cond + (cmp.nome.contains(".") ? "" : tabela + ".") + cmp.nome + " " + pegaOperador(cmp, ic) + " AND ";
                        ic++;
                    }
                }
            }
        }

        if (!cond.isEmpty()) {
            cond = UtlCrs.corta(cond, 5);
            cond = (aComecarCom.isEmpty() ? "" : aComecarCom + " ") + cond;
        }

        return " " + cond + " ";
    }

    public String pegaCondicaoNaoObrigatoria(String aComecarCom) throws Exception {
        String cond = "";

        int ic = 1;
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (!cmp.obrigatorio) {
                    if (!cmp.estrangeiro) {
                        if (!cmp.editor.vazio()) {
                            cond = cond + (cmp.nome.contains(".") ? "" : tabela + ".") + cmp.nome + " " + pegaOperador(cmp, ic) + " AND ";
                            ic++;
                        }
                    }
                }
            }
        }

        if (!cond.isEmpty()) {
            cond = UtlCrs.corta(cond, 5);
            cond = (aComecarCom.isEmpty() ? "" : aComecarCom + " ") + cond;
        }

        return " " + cond + " ";
    }

    public String pegaOrdem(String comComeco) {
        String retorno = "";
        if (!ordem.isEmpty()) {
            retorno = (comComeco.isEmpty() ? "" : comComeco + " ") + ordem;
        }
        return " " + retorno + " ";
    }

    public String pegaOrdem(String comComeco, String comTermino) {
        String retorno = "";
        if (!ordem.isEmpty()) {
            retorno = (comComeco.isEmpty() ? "" : comComeco + " ") + ordem + " , " + comTermino;
        } else {
            retorno = (comComeco.isEmpty() ? "" : comComeco + " ") + comTermino;
        }
        return " " + retorno + " ";
    }

    public String pegaDistincao(String comComeco, String comTermino) {
        String retorno = pegaOrdem(comComeco, comTermino);
        if (retorno != null) {
            retorno = retorno.toLowerCase().replaceAll("desc", "").replaceAll("asc", "");
        }
        return " " + retorno + " ";
    }

    public Vlrs pegaParametros() throws Exception {
        return pegaParametros(null);
    }

    public Vlrs pegaParametros(Vlrs primeiros) throws Exception {
        Vlrs params = new Vlrs();
        if (primeiros != null) {
            for (Vlr prm : primeiros) {
                params.bota(prm);
            }
        }
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (!cmp.estrangeiro) {
                    if ((cmp.obrigatorio) || (!cmp.editor.vazio())) {
                        params.bota(cmp.editor.pgVlr());
                    }
                }
            }
        }
        return params;
    }

    public Vlrs pegaParametrosObrigatorios() throws Exception {
        Vlrs params = new Vlrs();
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (cmp.obrigatorio) {
                    if (!cmp.estrangeiro) {
                        params.bota(cmp.editor.pgVlr());
                    }
                }
            }
        }
        return params;
    }

    public Vlrs pegaParametrosNaoObrigatorios(Object[] comObrigatorios) throws Exception {
        Vlrs params = new Vlrs(comObrigatorios);
        for (RelatorioFiltroCampo cmp : campos) {
            if (cmp.automatico) {
                if (!cmp.obrigatorio) {
                    if (!cmp.estrangeiro) {
                        if (!cmp.editor.vazio()) {
                            params.bota(cmp.editor.pgVlr());
                        }
                    }
                }
            }
        }
        return params;
    }

}
