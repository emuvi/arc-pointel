package pin.jrb;

import pin.libbas.ParsVals;
import pin.libutl.Utl;

public class Conversa {

    private ParsVals<Falado> entrada;

    public Conversa() {
        super();
        entrada = new ParsVals<>();
    }

    public Conversa bota(Falado oFalado) {
        entrada.botaPrimeiro(oFalado);
        return this;
    }

    public void executa() {
        Falado termo = entrada.tiraUltimo();
        if (termo != null) {
            Utl.mensagem(termo.conteudo);
        }
    }

    public void fecha() {
        entrada.limpa();
    }

}
