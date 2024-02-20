package pin.libutl;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import pin.libbas.Configs;
import pin.libbas.Globais;

public class UtlTab {

    public static DefaultTableModel fazTabela(JTable daTabela, Window naJanela, String... comNomesColunas) {
        return fazTabela(daTabela, naJanela, "Padr\u00e3o", 90, comNomesColunas, null, true);
    }

    public static DefaultTableModel fazTabela(JTable daTabela, Window naJanela, String comNome, String[] comNomesColunas) {
        return fazTabela(daTabela, naJanela, comNome, 90, comNomesColunas, null, true);
    }

    public static DefaultTableModel fazTabela(JTable daTabela, Window naJanela, String[] comNomesColunas, Integer[] eColunasEditaveis) {
        return fazTabela(daTabela, naJanela, "Padr\u00e3o", 90, comNomesColunas, eColunasEditaveis, true);
    }

    public static DefaultTableModel fazTabela(JTable daTabela, Window naJanela, String comNome, String[] comNomesColunas, Integer[] eColunasEditaveis) {
        return fazTabela(daTabela, naJanela, comNome, 90, comNomesColunas, eColunasEditaveis, true);
    }

    public static DefaultTableModel fazTabela(JTable daTabela, Window naJanela, String comNome, Integer eLarguraPadrao, String[] comNomesColunas, Integer[] eColunasEditaveis, Boolean comSelecaoSimples) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (eColunasEditaveis == null) {
                    return false;
                } else {
                    for (Integer colEdt : eColunasEditaveis) {
                        if (colEdt == column) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        daTabela.putClientProperty("terminateEditOnFocusLost", true);
        daTabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        if (comSelecaoSimples) {
            daTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        } else {
            daTabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
        modelo.setColumnIdentifiers(comNomesColunas);
        daTabela.setModel(modelo);
        final TableColumnModel columnModel = daTabela.getColumnModel();
        String titulo = null;
        if (naJanela instanceof JFrame) {
            titulo = ((JFrame) naJanela).getTitle();
        } else if (naJanela instanceof JDialog) {
            titulo = ((JDialog) naJanela).getTitle();
        }
        if (titulo != null) {
            final String ftitulo = titulo;
            Configs cfgs = (Configs) Globais.pega("mainConf");
            if (cfgs != null) {
                String raiz = "Janela " + titulo + " - Tabela " + comNome + " - Coluna ";
                for (int ic = 0; ic < daTabela.getColumnCount(); ic++) {
                    String icn = String.valueOf(ic);
                    if (comNomesColunas != null) {
                        if (ic < comNomesColunas.length) {
                            icn = comNomesColunas[ic];
                        }
                    }
                    int largura = cfgs.pegaInt(raiz + icn, eLarguraPadrao);
                    columnModel.getColumn(ic).setPreferredWidth(largura);
                }
                naJanela.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        String raiz = "Janela " + ftitulo + " - Tabela " + comNome + " - Coluna ";
                        for (int ic = 0; ic < daTabela.getColumnCount(); ic++) {
                            String icn = String.valueOf(ic);
                            if (comNomesColunas != null) {
                                if (ic < comNomesColunas.length) {
                                    icn = comNomesColunas[ic];
                                }
                            }
                            int largura = columnModel.getColumn(ic).getPreferredWidth();
                            cfgs.botaInt(raiz + icn, largura);
                        }
                    }
                });
            } else {
                for (int ic = 0; ic < daTabela.getColumnCount(); ic++) {
                    columnModel.getColumn(ic).setPreferredWidth(eLarguraPadrao);
                }
            }
        }
        return modelo;
    }

    public static Integer encontra(List<Object[]> naTabela, Object[] aLinha) {
        if (naTabela == null) {
            return -1;
        }
        for (int il = 0; il < naTabela.size(); il++) {
            if (UtlLis.compara(naTabela.get(il), aLinha)) {
                return il;
            }
        }
        return -1;
    }

    public static Object[] pegaLinha(DefaultTableModel doModelo, Integer doIndice) {
        if (doIndice >= doModelo.getRowCount() || doIndice < 0) {
            return null;
        }
        Object[] retorno = new Object[doModelo.getColumnCount()];
        for (int ic = 0; ic < retorno.length; ic++) {
            retorno[ic] = doModelo.getValueAt(doIndice, ic);
        }
        return retorno;
    }

    public static Boolean botaLinha(DefaultTableModel doModelo, final Integer doIndice, Object[] aLinha) throws Exception {
        if (doIndice == null) {
            return false;
        }
        if (doIndice < 0) {
            return false;
        }
        if (aLinha != null) {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    while (aLinha.length < doModelo.getColumnCount()) {
                        doModelo.addColumn(null);
                    }
                    while (doIndice >= doModelo.getRowCount()) {
                        doModelo.addRow(UtlLis.anular(new Object[doModelo.getColumnCount()]));
                    }
                    for (int ic = 0; ic < doModelo.getColumnCount(); ic++) {
                        doModelo.setValueAt(aLinha[ic], doIndice, ic);
                    }
                }
            });
        } else {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    while (doIndice >= doModelo.getRowCount()) {
                        doModelo.addRow(UtlLis.anular(new Object[doModelo.getColumnCount()]));
                    }
                    for (int ic = 0; ic < doModelo.getColumnCount(); ic++) {
                        doModelo.setValueAt(null, doIndice, ic);
                    }
                }
            });
        }
        return true;
    }

    public static Boolean tiraLinha(DefaultTableModel doModelo, Object[] comValores) {
        Boolean tirado = false;
        for (int iLin = doModelo.getRowCount() - 1; iLin >= 0; iLin--) {
            Object[] linha = new Object[doModelo.getColumnCount()];
            for (int iCol = 0; iCol < linha.length; iCol++) {
                linha[iCol] = doModelo.getValueAt(iLin, iCol);
            }
            if (UtlLis.compara(linha, comValores)) {
                doModelo.removeRow(iLin);
                tirado = true;
            }
        }
        return tirado;
    }
}
