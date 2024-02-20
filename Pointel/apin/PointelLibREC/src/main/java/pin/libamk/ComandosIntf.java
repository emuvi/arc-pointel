package pin.libamk;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libamg.EdtArq;
import pin.libbas.Comando;
import pin.libbas.Dados;

public class ComandosIntf extends Intf {

    private AbstractAction aoConfirmar;

    public ComandosIntf() {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs),
                new Cmp(1, 2, "comando", "Comando", Dados.Tp.Crs),
                new Cmp(1, 3, "mostrador", "Mostrador", Dados.Tp.Log),
                new Cmp(2, 1, "argumentos", "Argumentos", Dados.Tp.Lis),
                new Cmp(2, 2, "variaveis", "Variáveis", Dados.Tp.Lis),
                new Cmp(3, 1, "diretorio", "Diretório", Dados.Tp.Arq).botaParams(EdtArq.Params.SO_DIRETORIOS)
        ));
        aoConfirmar = null;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Comandos");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public Comando pComando() throws Exception {
        Comando retorno = new Comando(cmps().pgVlr("nome").pgCrs(), cmps().pgVlr("comando").pgCrs());
        retorno.mudaArgumentos(cmps().pgVlrLis("argumentos").pgMatCrs());
        retorno.mudaVariaveis(cmps().pgVlrLis("variaveis").pgMatCrs());
        retorno.mudaDiretorio(cmps().pgVlr("diretorio").pgArqCam());
        return retorno;
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        if (aoConfirmar != null) {
            aoConfirmar.actionPerformed(new ActionEvent(comOrigem, 0, "AoConfirmar"));
        } else {
            Comando com = pComando();
            if (cmps().pgVlr("mostrador").pgLog(false)) {
                com.mostrador("Comando " + cmps().pgVlr("nome").pgCrs());
            }
            com.inicia();
        }
    }

    public ComandosIntf mudaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

}
