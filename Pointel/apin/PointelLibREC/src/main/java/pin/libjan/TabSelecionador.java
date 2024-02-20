package pin.libjan;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import pin.libutl.UtlTab;

public class TabSelecionador {

    private JTable tabela;
    private DefaultTableModel modelo;
    private List<Object[]> selecionados;

    public TabSelecionador(JTable paraTabela) {
        tabela = paraTabela;
        modelo = (DefaultTableModel) tabela.getModel();
        selecionados = null;
    }

    public TabSelecionador registra() {
        selecionados = new ArrayList<>();
        for (int selecionado : tabela.getSelectedRows()) {
            selecionados.add(UtlTab.pegaLinha(modelo, selecionado));
        }
        return this;
    }

    public TabSelecionador reseleciona() {
        if (selecionados != null) {
            ListSelectionModel slt = tabela.getSelectionModel();
            slt.clearSelection();
            if (!selecionados.isEmpty()) {
                for (int il = 0; il < modelo.getRowCount(); il++) {
                    Object[] linha = UtlTab.pegaLinha(modelo, il);
                    if (UtlTab.encontra(selecionados, linha) > -1) {
                        slt.addSelectionInterval(il, il);
                    }
                }
            }
        }
        return this;
    }
}
