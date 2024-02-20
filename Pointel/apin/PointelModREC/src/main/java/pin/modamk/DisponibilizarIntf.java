package pin.modamk;

import java.awt.Component;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Conjunto;
import pin.libbas.Dados;
import pin.libbas.Globais;
import pin.libvlr.Vlrs;
import pin.modinf.Conexao;

public class DisponibilizarIntf extends Intf {

    private Recurso recurso;
    private Object[] parametros;
    private Component origem;
    private Integer posX;
    private Integer posY;

    public DisponibilizarIntf(Recurso oRecurso) {
        this(oRecurso, null, null, null, null);
    }

    public DisponibilizarIntf(Recurso oRecurso, Object[] comParametros, Component eOrigem, Integer ePosX, Integer ePosY) {
        super("Você Não tem Permissão para Acessar o Recurso:\n"
                + oRecurso.pegaRaizENome()
                + "\n\nSolicite Permissão de um Usuário que Tenha",
                new Campos(
                        new Cmp(1, 1, "usuario", "Usuário", Dados.Tp.Crs),
                        new Cmp(1, 2, "senha", "Senha", Dados.Tp.Sen)
                )
        );
        recurso = oRecurso;
        parametros = comParametros;
        origem = eOrigem;
        posX = ePosX;
        posY = ePosY;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Disponibilizar Recurso");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        Conexao conexao = (Conexao) Globais.pega("mainConc");
        String usuario = cmps().pgVlr("usuario").pgCrs();
        String senha = cmps().pgVlr("senha").pgCrs();
        Conjunto res = conexao.busca("SELECT codigo, super FROM usuarios WHERE usuario = ? AND senha = ?",
                new Vlrs(usuario, senha));
        if (res.vazio()) {
            throw new Exception("Usuário e Senha Não Encontrados");
        }
        if (!res.pgVlr("super").pgLog()) {
            Conjunto resHab = conexao.busca("SELECT habilitado FROM usuarios_habilitacoes WHERE usuario = ? AND nome = ?",
                    new Vlrs(res.pgVlr("codigo"), recurso.pegaRaizENome()));
            if (resHab.vazio()) {
                throw new Exception("Usuário Não Possui Esta Habilitação");
            }
            Boolean habilitado = resHab.pgVlr("habilitado").pgLog();
            if (!habilitado) {
                throw new Exception("Usuário Não Possui Esta Habilitação");
            }
        }
        recurso.botaHabilitado();
        recurso.executa(parametros, origem, posX, posY);
        recurso.tiraHabilitado();
        fecha();
    }

}
