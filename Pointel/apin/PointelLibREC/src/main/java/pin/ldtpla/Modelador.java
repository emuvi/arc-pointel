package pin.ldtpla;

import java.awt.Point;
import javax.swing.table.DefaultTableModel;

public class Modelador extends DefaultTableModel {

    private LdtPla proTab;

    public Modelador(LdtPla proTab) {
        this.proTab = proTab;
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        Boolean retorno = false;
        if (proTab.ehTodosEditaveis()) {
            retorno = true;
            if ((coluna == 0) && (proTab.ehMostraNumeroLinhas())) {
                retorno = false;
            }
        } else if (proTab.colunasEditaveis() != null) {
            if (proTab.ehMostraNumeroLinhas()) {
                coluna++;
            }
            for (Integer col : proTab.colunasEditaveis()) {
                if (col == coluna) {
                    retorno = true;
                    break;
                }
            }
        } else if (proTab.linhasEditaveis() != null) {
            if (proTab.ehMostraNumeroLinhas()) {
                coluna++;
            }
            for (Integer lin : proTab.linhasEditaveis()) {
                if (lin == linha) {
                    retorno = true;
                    break;
                }
            }
        } else if (proTab.celulasEditaveis() != null) {
            if (proTab.ehMostraNumeroLinhas()) {
                coluna++;
            }
            for (Point cel : proTab.celulasEditaveis()) {
                if ((cel.x == coluna) && (cel.y == linha)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }
}
