package pin.libjan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlLis;
import pin.libutl.UtlTab;

public class TabCarregador {

    private volatile JTable tabela;
    private volatile DefaultTableModel modelo;
    private volatile Integer linha;
    private volatile Boolean deveAtualizar;
    private volatile List<Integer> modificados;
    private volatile Boolean parar;
    private volatile AtomicBoolean carregando;
    private volatile AbstractAction aoModificar;
    private volatile AbstractAction aoCarregar;

    public TabCarregador(JTable paraTabela) {
        tabela = paraTabela;
        modelo = (DefaultTableModel) tabela.getModel();
        linha = -1;
        deveAtualizar = true;
        modificados = new ArrayList<>();
        parar = false;
        carregando = new AtomicBoolean(false);
    }

    public TabCarregador limpa() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        return this;
    }

    public TabCarregador reinicia() {
        linha = -1;
        modificados.clear();
        return this;
    }

    public Boolean pegaDeveAtualizar() {
        return deveAtualizar;
    }

    public TabCarregador mudaDeveAtualizar(Boolean para) {
        this.deveAtualizar = para;
        return this;
    }

    public Boolean foiModificado() {
        return !modificados.isEmpty();
    }

    public List<Integer> pegaModificados() {
        return modificados;
    }

    public AbstractAction aoModificar() {
        return aoModificar;
    }

    public TabCarregador botaAoModificar(AbstractAction aoModificar) {
        this.aoModificar = aoModificar;
        return this;
    }

    public AbstractAction aoCarregar() {
        return aoCarregar;
    }

    public TabCarregador botaAoCarregar(AbstractAction aoCarregar) {
        this.aoCarregar = aoCarregar;
        return this;
    }

    public Boolean bota(Object[] aLinha) throws Exception {
        linha++;
        if (deveAtualizar) {
            Object[] atual = UtlTab.pegaLinha(modelo, linha);
            if (!UtlLis.compara(aLinha, atual)) {
                UtlTab.botaLinha(modelo, linha, aLinha);
                modificados.add(linha);
                return true;
            } else {
                return false;
            }
        } else {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    modelo.addRow(aLinha);
                    modificados.add(modelo.getRowCount() - 1);
                }
            });
            return true;
        }
    }

    public TabCarregador limpaExcesso() {
        if (modelo.getRowCount() > 0) {
            while (modelo.getRowCount() - 1 > linha) {
                modelo.removeRow(modelo.getRowCount() - 1);
            }
        }
        return this;
    }

    public TabCarregador parar() {
        parar = true;
        return this;
    }

    public TabCarregador continuar() {
        parar = false;
        return this;
    }

    public TabCarregador carrega(List<Object[]> asLinhas) {
        new ThdCarregador(asLinhas).start();
        return this;
    }

    private class ThdCarregador extends Thread {

        private List<Object[]> linhas;

        public ThdCarregador(List<Object[]> asLinhas) {
            super("Carrega Tabela");
            linhas = asLinhas;
        }

        @Override
        public void run() {
            while (!carregando.compareAndSet(false, true)) {
                UtlBin.esperaMilis(100);
            }
            if (!parar) {
                try {
                    if (deveAtualizar) {
                        reinicia();
                    }
                    TabSelecionador selecionador = new TabSelecionador(tabela);
                    selecionador.registra();
                    for (Object[] linha : linhas) {
                        bota(linha);
                        if (parar) {
                            break;
                        }
                    }
                    if (!parar) {
                        limpaExcesso();
                        selecionador.reseleciona();
                        if (foiModificado()) {
                            if (aoModificar != null) {
                                aoModificar.actionPerformed(null);
                            }
                        }
                        if (aoCarregar != null) {
                            aoCarregar.actionPerformed(null);
                        }
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                } finally {
                    carregando.set(false);
                }
            }
        }
    }

}
