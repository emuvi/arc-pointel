package pin.jrb;

import java.util.Objects;
import pin.Pointel;
import pin.libamg.EdtSen;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libutl.Utl;

public class JarbsSenha extends Intf {

    public JarbsSenha() {
        super(new Campos(
                new Cmp(1, 1, "atual", "Atual", Dados.Tp.Sen),
                new Cmp(1, 1, "nova", "Nova", Dados.Tp.Sen).botaParams(EdtSen.Params.CONFIRMAR)
        ));
    }

    @Override
    public void preparaEstr() throws Exception {
        mTitulo("PointelJarbs Senha");
        if (!Pointel.mainJarbs.confereLogado()) {
            throw new Exception("PointelJarbs Não Está Logado");
        }
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotConfimar());
    }

    private class BotConfimar extends Botao {

        public BotConfimar() {
            super("Confirmar");
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            String atual = Pointel.mainConf.pegaCrsCrpt("PointelJarbs - Senha", "jarbozo");
            String atualConfere = cmps().pgVlr("atual", "").pgCrs();
            if (!Objects.equals(atual, atualConfere)) {
                throw new Exception("A Senha Atual Não Conferiu");
            }
            String nova = cmps().pgVlr("nova", "").pgCrs();
            if (nova.length() < 6) {
                throw new Exception("A Senha deve Conter no Mínimo 6 Caracteres");
            }
            Pointel.mainConf.botaCrsCrpt("PointelJarbs - Senha", nova);
            Utl.alerta("Senha do PointelJarbs Modificada");
        }

    }

}
