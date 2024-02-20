package pin.libjan;

import java.awt.Component;
import javax.swing.JFrame;
import pin.libutl.UtlBin;

public class JanTutela extends Component {

    private final JFrame janela;
    private final Object mentor;

    public JanTutela(JFrame aJanela, Object doMentor) {
        janela = aJanela;
        mentor = doMentor;
        setBounds(0, 0, 0, 0);
        setVisible(false);
    }

    public Boolean eh(Class daClasse) {
        if (mentor != null) {
            return UtlBin.eh(mentor, daClasse);
        } else {
            return UtlBin.eh(janela, daClasse);
        }
    }

    public Object pega() {
        if (mentor != null) {
            return mentor;
        } else {
            return janela;
        }
    }

    public JFrame janela() {
        return janela;
    }

    public Object mestre() {
        return mentor;
    }

    @Override
    public String toString() {
        return janela.getTitle();
    }
}
