package pin.ldtpla;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pin.libutl.Utl;
import pin.libutl.UtlCr;
import pin.libutl.UtlPla;
import pin.libbas.Conjunto;
import pin.libvlr.Vlr;
import pin.libvlr.VPla;
import pin.libvlr.Vlrs;

public class LdtPla extends JPanel {

    private JTable controle;
    private JScrollPane rolagem;
    private JToolBar barraStatus;
    private JLabel jlbDescricao;
    private Modelador modelador;
    private Colunador colunador;
    private Celulador celulador;
    private CelulaEdt celulaEdt;
    private Boolean todosEditaveis;
    private Integer[] colunasEditaveis;
    private Integer[] linhasEditaveis;
    private Point[] celulasEditaveis;
    private Boolean mostraNumeroLinhas;
    private String[] colunasNomes;
    private Boolean autoDimensionarColunas;
    private Integer autoDimensionarMaximo;

    public LdtPla() {
        super(new BorderLayout());
        controle = new JTable();
        rolagem = new JScrollPane(controle);
        add(rolagem, BorderLayout.CENTER);
        modelador = new Modelador(this);
        controle.setModel(modelador);
        colunador = new Colunador();
        controle.setColumnModel(colunador);
        celulador = new Celulador();
        controle.setDefaultRenderer(Object.class, celulador);
        celulaEdt = new CelulaEdt();
        controle.setDefaultEditor(Object.class, celulaEdt);
        controle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        controle.getTableHeader().setReorderingAllowed(false);
        controle.getTableHeader().setResizingAllowed(true);
        controle.setRowHeight(controle.getRowHeight() + 10);
        controle.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        controle.setAlignmentY(JComponent.TOP_ALIGNMENT);
        todosEditaveis = true;
        colunasEditaveis = null;
        linhasEditaveis = null;
        celulasEditaveis = null;
        mostraNumeroLinhas = true;
        colunasNomes = null;
        autoDimensionarColunas = true;
        autoDimensionarMaximo = 330;
        barraStatus = new JToolBar(JToolBar.HORIZONTAL);
        jlbDescricao = new JLabel("Editor Vazio");
        barraStatus.add(jlbDescricao);
    }

    public JTable controle() {
        return controle;
    }

    public JScrollPane rolagem() {
        return rolagem;
    }

    public JToolBar barraStatus() {
        return barraStatus;
    }

    public Modelador modelador() {
        return modelador;
    }

    public Colunador colunador() {
        return colunador;
    }

    public Celulador celulador() {
        return celulador;
    }

    public CelulaEdt celulaEdt() {
        return celulaEdt;
    }

    public Boolean ehTodosEditaveis() {
        return todosEditaveis;
    }

    public LdtPla botaTodosEditaveis() {
        todosEditaveis = true;
        return this;
    }

    public LdtPla mudaTodosEditaveis(Boolean para) {
        todosEditaveis = para;
        return this;
    }

    public LdtPla tiraTodosEditaveis() {
        todosEditaveis = false;
        return this;
    }

    public Integer[] colunasEditaveis() {
        return colunasEditaveis;
    }

    public Integer[] linhasEditaveis() {
        return linhasEditaveis;
    }

    public Point[] celulasEditaveis() {
        return celulasEditaveis;
    }

    public Boolean ehMostraNumeroLinhas() {
        return mostraNumeroLinhas;
    }

    public LdtPla botaMostraNumeroLinhas() {
        botaNumeroLinhas();
        mostraNumeroLinhas = true;
        return this;
    }

    public LdtPla mudaMostraNumeroLinhas(Boolean para) {
        if (para) {
            botaNumeroLinhas();
        } else {
            tiraNumeroLinhas();
        }
        mostraNumeroLinhas = para;
        return this;
    }

    public LdtPla tiraMostraNumeroLinhas() {
        tiraNumeroLinhas();
        mostraNumeroLinhas = false;
        return this;
    }

    private void botaNumeroLinhas() {
        if (!mostraNumeroLinhas) {
            if (modelador.getColumnCount() > 0) {
                List<Integer> larguras = new ArrayList<>();
                larguras.add(30);
                for (int col = 0; col < modelador.getColumnCount(); col++) {
                    larguras.add(colunador.getColumn(col).getWidth());
                }
                List<String> nomes = new ArrayList<>();
                nomes.add("#");
                for (int col = 0; col < modelador.getColumnCount(); col++) {
                    nomes.add(modelador.getColumnName(col));
                }
                modelador.addColumn("");
                for (int lin = 0; lin < modelador.getRowCount(); lin++) {
                    for (int col = colunador.getColumnCount() - 2; col >= 0; col--) {
                        modelador.setValueAt(modelador.getValueAt(lin, col), lin, col + 1);
                    }
                    modelador.setValueAt(lin + 1, lin, 0);
                }
                modelador.setColumnIdentifiers(nomes.toArray());
                for (int col = 0; col < modelador.getColumnCount(); col++) {
                    colunador.getColumn(col).setPreferredWidth((int) larguras.get(col));
                }
            }
            mostraNumeroLinhas = true;
        }
    }

    private void tiraNumeroLinhas() {
        if (mostraNumeroLinhas) {
            if (modelador.getColumnCount() > 0) {
                List<Integer> larguras = new ArrayList<>();
                for (int col = 1; col < modelador.getColumnCount(); col++) {
                    larguras.add(colunador.getColumn(col).getWidth());
                }
                List<String> nomes = new ArrayList<>();
                for (int col = 1; col < modelador.getColumnCount(); col++) {
                    nomes.add(modelador.getColumnName(col));
                }

                for (int lin = 0; lin < modelador.getRowCount(); lin++) {
                    for (int col = 1; col < colunador.getColumnCount(); col++) {
                        modelador.setValueAt(modelador.getValueAt(lin, col), lin, col - 1);
                    }
                }
                modelador.setColumnCount(modelador.getColumnCount() - 1);
                modelador.setColumnIdentifiers(nomes.toArray());
                for (int col = 0; col < modelador.getColumnCount(); col++) {
                    colunador.getColumn(col).setPreferredWidth((int) larguras.get(col));
                }
            }
            mostraNumeroLinhas = false;
        }
    }

    public String[] colunasNomes() {
        return colunasNomes;
    }

    public Boolean ehAutoDimensionarColunas() {
        return autoDimensionarColunas;
    }

    public LdtPla botaAutoDimensionarColunas() {
        autoDimensionarColunas = true;
        return this;
    }

    public LdtPla mudaAutoDimensionarColunas(Boolean para) {
        autoDimensionarColunas = para;
        return this;
    }

    public LdtPla tiraAutoDimensionarColunas() {
        autoDimensionarColunas = false;
        return this;
    }

    public Integer pegaAutoDimensionarMaximo() {
        return autoDimensionarMaximo;
    }

    public LdtPla mudaAutoDimensionarMaximo(Integer para) {
        autoDimensionarMaximo = para;
        return this;
    }

    public LdtPla botaCelulaSelecao() {
        controle.setCellSelectionEnabled(true);
        return this;
    }

    public LdtPla tiraCelulaSelecao() {
        controle.setCellSelectionEnabled(false);
        return this;
    }

    public LdtPla botaSelecaoSimples() {
        controle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        return this;
    }

    public LdtPla botaSelecaoIntervalo() {
        controle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        return this;
    }

    public LdtPla botaSelecaoIntervaloMultiplo() {
        controle.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return this;
    }

    public VPla pegaVlr() {
        return null;
    }

    public Boolean vazio() {
        return modelador.getRowCount() <= 0;
    }

    public void limpa() {
        while (modelador.getRowCount() > 0) {
            modelador.removeRow(0);
        }
    }

    public Boolean mudaVlr(Object para) throws Exception {
        limpa();
        return botaVlr(para);
    }

    public Boolean botaVlr(Object oValor) throws Exception {
        Conjunto cnj = UtlPla.pega(oValor);
        if (cnj != null) {
            if (colunasNomes == null) {
                if (cnj.pegaNomes() != null) {
                    botaColunas(cnj.pegaNomes());
                } else {
                    botaColunas();
                }
            }
            for (Vlrs linha : ((Conjunto) oValor)) {
                try {
                    botaLinha(linha.toArray());
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        }
        return true;
    }

    public Boolean estaEditavel() {
        Boolean retorno = false;
        if (todosEditaveis) {
            retorno = true;
        } else if (colunasEditaveis != null) {
            retorno = true;
        } else if (celulasEditaveis != null) {
            retorno = true;
        }
        if (!controle.getCellSelectionEnabled()) {
            retorno = false;
        }
        return retorno;
    }

    public Integer tamanhoLinhas() {
        return modelador.getRowCount();
    }

    public Integer tamanhoColunas() {
        return modelador.getColumnCount();
    }

    public Object[] pegaLinhaVals(Integer doIndice) {
        Object[] retorno = new Object[tamanhoColunas()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = modelador.getValueAt(doIndice, i1);
        }
        return retorno;
    }

    public Vlr[] pegaLinhaVlrs(Integer doIndice) {
        Vlr[] retorno = new Vlr[tamanhoColunas()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = Vlr.novo(modelador.getValueAt(doIndice, i1));
        }
        return retorno;
    }

    public Boolean temSelecionado() {
        return !controle.getSelectionModel().isSelectionEmpty();
    }

    public int pegaLinhaSelecionada() {
        return controle.getSelectedRow();
    }

    public int[] pegaLinhasSelecionadas() {
        return controle.getSelectedRows();
    }

    public Object[] pegaLinhaSelecionadaVals() {
        return pegaLinhaVals(pegaLinhaSelecionada());
    }

    public Vlr[] pegaLinhaSelecionadaVlrs() {
        return pegaLinhaVlrs(pegaLinhaSelecionada());
    }

    public List<Object[]> pegaLinhasSelecionadasVals() {
        List<Object[]> retorno = new ArrayList<>();
        int[] sels = pegaLinhasSelecionadas();
        if (sels != null) {
            for (int sel : sels) {
                retorno.add(pegaLinhaVals(sel));
            }
        }
        return retorno;
    }

    public List<Vlr[]> pegaLinhasSelecionadasVlrs() {
        List<Vlr[]> retorno = new ArrayList<>();
        int[] sels = pegaLinhasSelecionadas();
        if (sels != null) {
            for (int sel : sels) {
                retorno.add(pegaLinhaVlrs(sel));
            }
        }
        return retorno;
    }

    public int pegaColunaSelecionada() {
        return controle.getSelectedColumn();
    }

    public int[] pegaColunasSelecionadas() {
        return controle.getSelectedColumns();
    }

    public Object[] pegaColunaVals() {
        return pegaColunaVals(pegaColunaSelecionada());
    }

    public Object[] pegaColunaVals(Integer doIndice) {
        Object[] retorno = new Object[tamanhoLinhas()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = modelador.getValueAt(i1, doIndice);
        }
        return retorno;
    }

    public Vlr[] pegaColunaVlrs() {
        return pegaColunaVlrs(pegaColunaSelecionada());
    }

    public Vlr[] pegaColunaVlrs(Integer doIndice) {
        Vlr[] retorno = new Vlr[tamanhoLinhas()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = Vlr.novo(modelador.getValueAt(i1, doIndice));
        }
        return retorno;
    }

    public LdtPla botaColunas() {
        Vector colunas = new Vector();
        if (mostraNumeroLinhas) {
            colunas.add("#");
        }
        for (char ch : UtlCr.crsMaiusculosSimples) {
            colunas.add(ch);
        }
        modelador.setColumnIdentifiers(colunas);
        return this;
    }

    public LdtPla botaColunas(String... comNomes) {
        if (!mostraNumeroLinhas) {
            modelador.setColumnIdentifiers(comNomes);
        } else {
            Vector colunas = new Vector();
            colunas.add("#");
            colunas.addAll(Arrays.asList(comNomes));
            modelador.setColumnIdentifiers(colunas);
        }
        colunasNomes = comNomes;
        return this;
    }

    public LdtPla botaLinha(Object... comValores) {
        List<Object> linha = new ArrayList<>();
        int iCom = 0;
        if (mostraNumeroLinhas) {
            linha.add(modelador.getRowCount() + 1);
            iCom++;
        }
        int iVal = 0;
        while (iCom < modelador.getColumnCount()) {
            if (comValores == null) {
                linha.add(null);
            } else if (iVal >= comValores.length) {
                linha.add(null);
            } else {
                linha.add(comValores[iVal]);
                iVal++;
            }
            iCom++;
        }
        modelador.addRow(linha.toArray());
        return this;
    }

    public LdtPla botaLinhas() {
        for (int i1 = 0; i1 < 100; i1++) {
            botaLinha();
        }
        return this;
    }

    public LdtPla selecionaUltimo() {
        controle.setRowSelectionInterval(modelador.getRowCount() - 1, modelador.getRowCount() - 1);
        return this;
    }

    public LdtPla tiraLinha() {
        if (controle.getSelectedRow() > -1) {
            modelador.removeRow(controle.getSelectedRow());
        }
        return this;
    }

    public LdtPla mudaLinha(Object[] para) {
        int sel = controle.getSelectedRow();
        if (sel == -1) {
            Vector linha = new Vector();
            if (mostraNumeroLinhas) {
                linha.add(modelador.getRowCount() + 1);
            }
            linha.addAll(Arrays.asList(para));
            modelador.addRow(linha);
        } else {
            int ival = 0;
            int iini = 0;
            if (mostraNumeroLinhas) {
                iini++;
            }
            for (int i1 = iini; i1 < modelador.getColumnCount(); i1++) {
                modelador.setValueAt(para[ival], sel, i1);
                ival++;
            }
        }
        return this;
    }

    public Object pegaVal(String daColuna) {
        Object retorno = null;
        int sel = controle.getSelectedRow();
        if (sel > -1) {
            for (int i1 = 0; i1 < modelador.getColumnCount(); i1++) {
                if (modelador.getColumnName(i1).equals(daColuna)) {
                    retorno = modelador.getValueAt(sel, i1);
                }
            }
        }
        return retorno;
    }

    public Object pegaVal(String daColuna, Integer eLinha) {
        Object retorno = null;
        int linha = eLinha;
        if (linha > -1) {
            for (int i1 = 0; i1 < modelador.getColumnCount(); i1++) {
                if (modelador.getColumnName(i1).equals(daColuna)) {
                    retorno = modelador.getValueAt(linha, i1);
                }
            }
        }
        return retorno;
    }

    public LdtPla botaSelecaoAcao(ActionListener aAcao) {
        controle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (aAcao != null) {
                    aAcao.actionPerformed(null);
                }
            }
        });
        return this;
    }

}
