package pin.libamk;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Botoes extends ArrayList<Botao> {

    public Botoes bota(Botao oBotao) {
        add(oBotao);
        return this;
    }

    public Botao pega(int doIndice) {
        return get(doIndice);
    }

    public Botoes tira(int doIndice) {
        remove(doIndice);
        return this;
    }

    public Integer tamanho() {
        return size();
    }

    public Boolean vazio() {
        return isEmpty();
    }

    public Boolean tem() {
        return !isEmpty();
    }

    public JPanel constroi(Intf.AlinhamentoTp comAlinhamento, Object comOrigem) {
        JPanel retorno = new JPanel(new FlowLayout(comAlinhamento.pegaCFG(), 4, 4));
        for (Botao bot : this) {
            if (bot.temOrdem()) {
                retorno.add(bot.cria(comOrigem), bot.pOrdem().intValue());
            } else {
                retorno.add(bot.cria(comOrigem));
            }
        }
        return retorno;
    }

    public JButton pegaPadrao() {
        JButton retorno = null;
        if (tem()) {
            retorno = pega(0).pControle();
            for (Botao bot : this) {
                if (bot.ehPadrao()) {
                    retorno = bot.pControle();
                    break;
                }
            }
        }
        return retorno;
    }

}
