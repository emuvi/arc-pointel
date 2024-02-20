package pin.libjan;

import javax.swing.JDialog;
import javax.swing.JFrame;

public interface JanEspeciais {

    public void botaAtalhos(JFrame naJanela, Class daClasse);

    public void botaAtalhos(JDialog naJanela, Class daClasse);

    public void botaIcone(JFrame naJanela, Class daClasse);

    public void botaIcone(JDialog naJanela, Class daClasse);

    public void atualizaListaJanelas();

}
