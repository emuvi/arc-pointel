package pin.libexp;

import pin.libutl.UtlArq;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;

public class ExpModelo extends DefaultListModel {

    public void carregar(File[] aArquivos, String[] aExts) {
        ArrayList<File> pastas = new ArrayList<>();
        ArrayList<File> selecionados = new ArrayList<>();

        for (File arquivo : aArquivos) {
            if (arquivo.isDirectory()) {
                pastas.add(arquivo);
            } else if (UtlArq.ehArquivoDasExtencoes(arquivo, aExts)) {
                selecionados.add(arquivo);
            }
        }

        Collections.sort(pastas);
        Collections.sort(selecionados);

        clear();
        addElement(new File(".."));
        for (File arquivo : pastas) {
            addElement(arquivo);
        }
        for (File arquivo : selecionados) {
            addElement(arquivo);
        }
    }

    public void carregarRoots(File[] aArquivos) {
        ArrayList<File> pastas = new ArrayList<>();

        for (File arquivo : aArquivos) {
            pastas.add(arquivo);
        }

        Collections.sort(pastas);

        clear();
        for (File arquivo : pastas) {
            addElement(arquivo);
        }
    }

    public File[] getTodos() {
        File[] retorno = new File[getSize()];
        for (int i1 = 0; i1 < retorno.length; i1++) {
            retorno[i1] = (File) getElementAt(i1);
        }
        return retorno;
    }
}
