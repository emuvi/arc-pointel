package pin.jrb;

import pin.Pointel;
import pin.libbas.Partes;
import pin.libutl.UtlMom;

public class Falado {

    public volatile String autor;
    public volatile String momento;
    public volatile String conteudo;

    public Falado(String comConteudo) {
        autor = Pointel.mainJarbs.pegaInterlocutor();
        momento = UtlMom.pegaAtualParaMaquina();
        conteudo = comConteudo;
    }

    public Falado(String doAutor, String comConteudo) {
        autor = doAutor;
        momento = UtlMom.pegaAtualParaMaquina();
        conteudo = comConteudo;
    }

    public Falado(String doAutor, String noMomento, String comConteudo) {
        autor = doAutor;
        momento = noMomento;
        conteudo = comConteudo;
    }

    public static Falado descrito(String nosCaracteres) {
        String[] prts = Partes.pega(nosCaracteres);
        Falado retorno = new Falado(prts[0], prts[1], prts[2]);
        return retorno;
    }

    @Override
    public String toString() {
        return autor + " - " + momento + " - " + conteudo;
    }

    public String descreve() {
        return Partes.junta(autor, momento, conteudo);
    }

}
