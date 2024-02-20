package pin.libamg;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import pin.libout.GBCWrap;

public class EdtPaiLin extends ArrayList<Edt> {

    public EdtPaiLin() {
        super();
    }

    public EdtPaiLin botaColuna(Edt oEditor) {
        int idx = size();
        for (int i = 0; i < size(); i++) {
            Edt edt = get(i);
            if (oEditor.pPaiColuna() < edt.pPaiColuna()) {
                idx = i;
                break;
            }
        }
        add(idx, oEditor);
        return this;
    }

    public JPanel controi() {
        JPanel retorno = new JPanel(new GridBagLayout());
        int indice = 0;
        for (Edt edt : this) {
            if (edt instanceof EdtAbs) {
                ((EdtAbs) edt).controi();
            }
            if (edt instanceof EdtCts) {
                ((EdtCts) edt).controi();
            }
            if (edt instanceof EdtPai) {
                ((EdtPai) edt).constroi();
            }
            retorno.add(edt, new GBCWrap(indice, 0).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.NONE).setWeight(0, 0));
            indice++;
            if (edt instanceof EdtBot) {
                ((EdtBot) edt).botaAPreencher();
            }
        }
        retorno.add(new JPanel(), new GBCWrap(indice, 0).setAnchor(GridBagConstraints.NORTHWEST).setFill(GridBagConstraints.BOTH).setWeight(1, 1));
        retorno.revalidate();
        return retorno;
    }

}
