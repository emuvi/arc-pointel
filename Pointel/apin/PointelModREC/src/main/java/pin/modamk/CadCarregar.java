package pin.modamk;

import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCor;
import pin.libbas.Conjunto;

public class CadCarregar extends Thread {

    public Cadastro cadastro;

    private volatile Boolean parar;
    private volatile Boolean pausar;
    private volatile Boolean carregando;
    private volatile Boolean recarregar;
    private volatile Boolean terminar;

    private volatile Boolean selecionarPrimeiro;
    private volatile Boolean atualizarSelecionando;
    public Boolean recarregarUltimoSelecionado;

    public Object[] ultimoSelecionadoChaves;
    public Object[] ultimoSelecionadoValores;

    public Integer ultimoSelecionadoIndex;

    public Boolean carregarTudo;

    public Color fundo;
    public Color fundoCarregado;
    public Color frente;
    public Color frenteCarregado;

    public CadCarregar(Cadastro aCadastro) {
        super("Cadastro Carregar " + aCadastro.nome);

        cadastro = aCadastro;
        parar = false;
        carregando = false;
        selecionarPrimeiro = false;
        atualizarSelecionando = false;
        recarregarUltimoSelecionado = false;

        ultimoSelecionadoChaves = new Object[aCadastro.pegaChavesQuantidade()];
        ultimoSelecionadoValores = new Object[cadastro.dtmLista.getColumnCount()];
        ultimoSelecionadoIndex = -1;

        carregarTudo = false;

        fundo = UIManager.getColor("Table.background");
        fundoCarregado = UtlCor.diferencia(fundo, 25);
        frente = UIManager.getColor("Table.foreground");
        frenteCarregado = UtlCor.diferencia(frente, 25);
    }

    public void selecionaPrimeiro() {
        selecionarPrimeiro = true;
    }

    public void atualizar() {
        parar = true;
        recarregar = true;
    }

    public void atualizarSelecionando() {
        parar = true;
        recarregar = true;
        atualizarSelecionando = true;
    }

    public void atualizar(CadProcurar aProcurar) {
        parar = true;
        pausar = false;
        recarregar = true;
    }

    public void parar() {
        parar = true;
    }

    public void pausar() {
        pausar = true;
    }

    public void continuar() {
        pausar = false;
        parar = false;
    }

    public void carregaTudo() {
        carregarTudo = true;
        continuar();
    }

    public void carregaPartes() {
        carregarTudo = false;
        continuar();
    }

    public void terminar() {
        terminar = true;
    }

    public Boolean estaCarregando() {
        return carregando;
    }

    @Override
    public void run() {
        recarregar = true;
        terminar = false;

        while (!terminar) {
            try {
                if (recarregar) {
                    cadastro.jtbLista.setBackground(fundo);
                    cadastro.jtbLista.setForeground(frente);

                    parar = false;
                    recarregar = false;
                    if (atualizarSelecionando) {
                        selecionarPrimeiro = true;
                        atualizarSelecionando = false;
                    }

                    ultimoSelecionadoIndex = -1;
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            while (cadastro.dtmLista.getRowCount() > 0) {
                                cadastro.dtmLista.removeRow(0);
                            }
                        }
                    });

                    Boolean continua = true;
                    carregando = true;
                    cadastro.cadSQL.selectPular = 0;

                    int gruposCarregados = 0;

                    while (continua) {
                        try {
                            Conjunto rst = cadastro.cadSQL.selecionar();

                            Boolean achou = false;
                            if (rst != null) {
                                while (rst.proximo()) {
                                    achou = true;

                                    Object[] valores = new Object[cadastro.dtmLista.getColumnCount()];
                                    for (int i1 = 0; i1 < cadastro.dtmLista.getColumnCount(); i1++) {
                                        Object valor = rst.pgVlr(i1 + 1);
                                        valores[i1] = valor;
                                    }

                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                cadastro.dtmLista.addRow(valores);

                                                if (selecionarPrimeiro) {
                                                    cadastro.jtbLista.setRowSelectionInterval(0, 0);
                                                    carregarSelecao();
                                                    if (cadastro.janelaDeEdicao) {
                                                        cadastro.botaModoEditar(false);
                                                    } else {
                                                        cadastro.botaModoProcurando(false, false);
                                                    }

                                                    selecionarPrimeiro = false;
                                                }
                                            } catch (Exception ex) {
                                                Utl.registra(ex);
                                            }
                                        }
                                    });

                                    if (parar) {
                                        break;
                                    }
                                }
                            }

                            if (cadastro.cadSQL.selectLimite > -1) {
                                cadastro.cadSQL.selectPular += cadastro.cadSQL.selectLimite;
                            }

                            if (!carregarTudo) {
                                gruposCarregados++;
                                if (gruposCarregados >= 3) {
                                    gruposCarregados = 0;

                                    pausar = true;
                                    while (pausar) {
                                        UtlBin.esperaMilis(300);

                                        if (parar) {
                                            break;
                                        }
                                    }
                                }
                            }

                            continua = ((achou) && (!parar));
                        } catch (Exception ex) {
                            Utl.registra(ex);
                            while ((!cadastro.conexao.estaConectado()) && (!parar) && (!terminar)) {
                                UtlBin.esperaSegundo();
                            }
                        }
                    }

                    carregando = false;
                    parar = false;

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                botaListaNoUltimoSelecionado();
                                if (recarregarUltimoSelecionado) {
                                    recarregarUltimoSelecionado = false;
                                    carregarSelecao();
                                }
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    });

                    cadastro.jtbLista.setBackground(fundoCarregado);
                    cadastro.jtbLista.setForeground(frenteCarregado);
                }

                try {
                    sleep(300);
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }
        }
    }

    public void carregarSelecao() throws Exception {
        if (!cadastro.ehReferencia) {
            cadastro.limparCampos();
            ultimoSelecionadoIndex = -1;
            int sel = cadastro.jtbLista.getSelectedRow();
            int ichave = 0;
            if (sel > -1) {
                ultimoSelecionadoIndex = sel;
                for (int i1 = 0; i1 < cadastro.dtmLista.getColumnCount(); i1++) {
                    String titulo = cadastro.jtbLista.getColumnName(i1);
                    Object valor = cadastro.dtmLista.getValueAt(sel, i1);
                    cadastro.mudaCampoTituloValor(titulo, valor);
                    if (cadastro.ehCampoTituloChave(titulo)) {
                        ultimoSelecionadoChaves[ichave] = cadastro.dtmLista.getValueAt(sel, i1);
                        ichave++;
                    }
                    ultimoSelecionadoValores[i1] = cadastro.dtmLista.getValueAt(sel, i1);
                }
                TrataIntf.posicionaNaSelecao(cadastro.jtbLista, cadastro.jspLista);
            } else {
                cadastro.botaModoNovo();
            }
        }
    }

    public void recarregarUltimoSelecionado() throws Exception {
        for (int i1 = 0; i1 < cadastro.dtmLista.getColumnCount(); i1++) {
            cadastro.mudaCampoTituloValor(cadastro.jtbLista.getColumnName(i1), ultimoSelecionadoValores[i1]);
        }
    }

    public Boolean estaListaNoUltimoSelecionado() {
        int sel = cadastro.jtbLista.getSelectedRow();
        Object[] chaves = new Object[cadastro.pegaChavesQuantidade()];

        int ichave = 0;
        for (int i1 = 0; i1 < cadastro.dtmLista.getColumnCount(); i1++) {
            String titulo = cadastro.jtbLista.getColumnName(i1);
            if (cadastro.ehCampoTituloChave(titulo)) {
                chaves[ichave] = cadastro.dtmLista.getValueAt(sel, i1);
                ichave++;
            }
        }

        Boolean retorno = true;
        for (int i3 = 0; i3 < chaves.length; i3++) {
            if (!chaves[i3].equals(ultimoSelecionadoChaves[i3])) {
                retorno = false;
                break;
            }
        }

        return retorno;
    }

    public void botaListaNoUltimoSelecionado() {
        if (ultimoSelecionadoIndex > -1 && ultimoSelecionadoIndex < cadastro.jtbLista.getRowCount()) {
            cadastro.jtbLista.setRowSelectionInterval(ultimoSelecionadoIndex, ultimoSelecionadoIndex);
        }
    }

}
