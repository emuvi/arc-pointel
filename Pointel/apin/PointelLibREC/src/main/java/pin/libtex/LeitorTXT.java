package pin.libtex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LeitorTXT {

    private File arquivo;
    private BufferedReader leitor;
    private String codificacao;

    private Long tamanho;
    private Long progresso;
    private Boolean terminou;

    public LeitorTXT(File doArquivo) throws Exception {
        this(doArquivo, "UTF8");
    }

    public LeitorTXT(File doArquivo, String naCodificacao) throws Exception {
        progresso = 0l;
        tamanho = doArquivo.length();
        codificacao = naCodificacao;
        leitor = new BufferedReader(new InputStreamReader(new FileInputStream(doArquivo), codificacao));
        terminou = false;
    }

    public String leLinha() throws Exception {
        String linha = leitor.readLine();
        if (linha != null) {
            progresso = progresso + linha.getBytes(codificacao).length;
        } else {
            terminou = true;
        }
        return linha;
    }

    public File pegaArquivo() {
        return arquivo;
    }

    public String pegaCodificacao() {
        return codificacao;
    }

    public Long pegaTamanho() {
        return tamanho;
    }

    public Long pegaProgresso() {
        return progresso;
    }

    public Boolean terminou() {
        return terminou;
    }

}
