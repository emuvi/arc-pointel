package pin.libamk;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import pin.libbas.Dados;
import pin.libbas.Modulo;

public class ModulosIntf extends Intf {

    private AbstractAction aoConfirmar;

    public ModulosIntf() {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs),
                new Cmp(2, 1, "legenda", "Legenda", Dados.Tp.Crs),
                new Cmp(2, 2, "mostrador", "Mostrador", Dados.Tp.Log),
                new Cmp(3, 1, "argumentos", "Argumentos", Dados.Tp.Lis)
        ));
        aoConfirmar = null;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Módulos");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    public Modulo pgModulo() throws Exception {
        Modulo retorno = new Modulo(cmps().pgVlr("nome").pgCrs(), cmps().pgVlr("legenda").pgCrs());
        retorno.mudaArgumentos(cmps().pgVlrLis("argumentos").pgMatCrs());
        return retorno;
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        if (aoConfirmar != null) {
            aoConfirmar.actionPerformed(new ActionEvent(comOrigem, 0, "AoConfirmar"));
        } else {
            Modulo mod = pgModulo();
            if (cmps().pgVlr("mostrador").pgLog(false)) {
                mod.mostrador("Módulo " + cmps().pgVlr("nome").pgCrs());
            }
            mod.inicia();
        }
    }

    public ModulosIntf mudaAoConfirmar(AbstractAction aAcao) {
        aoConfirmar = aAcao;
        return this;
    }

}
