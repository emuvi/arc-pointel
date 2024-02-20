package pin.prk;

import pin.jrb.Serv;
import pin.libamg.Edt;
import pin.libamg.EdtArq;
import pin.libamk.Botao;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;

public class ServPublicarWebSiteIntf extends Intf {

    public Edt editor;

    public ServPublicarWebSiteIntf() {
        this(null, new ServPublicarWebSite());
    }

    public ServPublicarWebSiteIntf(Edt paraEditor) {
        this(paraEditor, new ServPublicarWebSite());
    }

    public ServPublicarWebSiteIntf(ServPublicarWebSite comInicial) {
        this(null, comInicial);
    }

    public ServPublicarWebSiteIntf(Edt paraEditor, ServPublicarWebSite comInicial) {
        super(new Campos(
                new Cmp(1, 1, "nome", "Nome", Dados.Tp.Crs).mVlrInicial(comInicial.nome),
                new Cmp(1, 2, "endereco", "Endereço", Dados.Tp.Crs).mVlrInicial(comInicial.endereco),
                new Cmp(1, 3, "porta", "Porta", Dados.Tp.Int).mVlrInicial(comInicial.porta),
                new Cmp(2, 1, "tipo", "Tipo", Dados.Tp.Enu).botaOpcoes("(F) FTP").mVlrInicial(comInicial.tipo),
                new Cmp(2, 2, "usuario", "Usuário", Dados.Tp.Crs).mVlrInicial(comInicial.usuario),
                new Cmp(2, 3, "senha", "Senha", Dados.Tp.Crs).mVlrInicial(comInicial.senha),
                new Cmp(3, 1, "diretorio", "Diretório", Dados.Tp.Crs).mVlrInicial(comInicial.diretorio),
                new Cmp(3, 2, "tempoEspera", "Tempo de Espera", Dados.Tp.Int).mVlrInicial(comInicial.tempoEspera),
                new Cmp(3, 3, "tentativas", "Tentativas", Dados.Tp.Int).mVlrInicial(comInicial.tentativas),
                new Cmp(4, 1, "origem", "Origem", Dados.Tp.Arq).botaParams(EdtArq.Params.SO_CAMINHO, EdtArq.Params.SO_DIRETORIOS).mVlrInicial(comInicial.origem),
                new Cmp(4, 2, "soModificados", "Só Modificados", Dados.Tp.Log).mVlrInicial(comInicial.soModificados),
                new Cmp(4, 3, "paralelos", "Paralelos", Dados.Tp.Int).mVlrInicial(comInicial.paralelos),
                new Cmp(4, 4, "retornos", "Retornos", Dados.Tp.Log).mVlrInicial(comInicial.retornos),
                new Cmp(4, 5, "mostrador", "Mostrador", Dados.Tp.Log).mVlrInicial(comInicial.mostrador)
        ));
        editor = paraEditor;
    }

    @Override
    public void preparaEstr() throws Exception {
        mTitulo("Publicar WebSite");
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
            ServPublicarWebSite pws = new ServPublicarWebSite();
            pws.nome = cmps().pgVlr("nome").pgCrs();
            pws.endereco = cmps().pgVlr("endereco").pgCrs();
            pws.porta = cmps().pgVlr("porta").pgInt();
            pws.tipo = cmps().pgVlr("tipo").pgCrs();
            pws.usuario = cmps().pgVlr("usuario").pgCrs();
            pws.senha = cmps().pgVlr("senha").pgCrs();
            pws.diretorio = cmps().pgVlr("diretorio").pgCrs();
            pws.tempoEspera = cmps().pgVlr("tempoEspera").pgInt();
            pws.tentativas = cmps().pgVlr("tentativas").pgInt();
            pws.origem = cmps().pgVlr("origem").pgArqCam();
            pws.soModificados = cmps().pgVlr("soModificados").pgLog();
            pws.paralelos = cmps().pgVlr("paralelos").pgInt();
            pws.retornos = cmps().pgVlr("retornos").pgLog();
            pws.mostrador = cmps().pgVlr("mostrador").pgLog();
            if (editor != null) {
                editor.mdVlr(pws.descreve());
                editor.botaFoco();
            } else {
                Serv.inicia(pws);
            }
        }

    }

}
