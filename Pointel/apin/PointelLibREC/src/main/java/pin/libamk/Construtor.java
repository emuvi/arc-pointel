package pin.libamk;

import pin.libamg.EdtPaiLis;
import pin.libamg.Edt;
import pin.libamg.EdtPai;
import pin.libjan.Janelas;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Construtor {

    public Campos campos;

    public Construtor(Campos dosCampos) {
        campos = dosCampos;
    }

    public void abre(String comTitulo) throws Exception {
        JFrame janela = new JFrame(comTitulo);
        janela.setLayout(new BorderLayout());
        janela.add(constroi(janela), BorderLayout.CENTER);
        Janelas.inicia(janela);
        janela.setVisible(true);
    }

    public EdtPai constroi(Object comOrigem) throws Exception {
        campos.orderna();
        EdtPai retorno = new EdtPai();
        EdtPaiLis pais = new EdtPaiLis();
        for (Cmp cmp : campos) {
            Edt editor = cmp.criaEdt(comOrigem);
            retorno.botaEditor(editor, pais);
        }
        retorno.constroi();
        return retorno;
    }
}
