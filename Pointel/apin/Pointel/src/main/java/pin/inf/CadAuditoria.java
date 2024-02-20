package pin.inf;

import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import pin.adm.CadNegocios;
import pin.adm.CadPessoas;
import pin.libamk.Botao;
import pin.libitr.QuestaoTp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libutl.UtlTem;
import pin.libvlr.Vlrs;
import pin.modamk.CadRelacao;
import pin.modamk.Cadastro;
import pin.libamk.Cmp;
import pin.modamk.CadReferenciar;

public class CadAuditoria extends Cadastro {

    public CadAuditoria() throws Exception {
        super("auditoria", "Auditoria", new Cmp[]{
            new Cmp(1, 1, "usuario", "Usuário - Cód.", Dados.Tp.Crs).mTamanho(4).botaDetalhe("chave", true),
            new Cmp(1, 2, "usuarios.usuario", "Usuário - Nome", Dados.Tp.Crs).mTamanho(40).botaDetalhe("estrangeiro", true),
            new Cmp(1, 3, "momento", "Momento", Dados.Tp.Mom).botaDetalhe("chave", true).mVlrPadrao("CURRENT_TIMESTAMP"),
            new Cmp(1, 4, "retorno", "Retorno", Dados.Tp.Int),
            new Cmp(2, 1, "comando", "Comando", Dados.Tp.Tex).mLargura(600),
            new Cmp(3, 1, "parametros", "Parâmetros", Dados.Tp.Tex).mLargura(600),
            new Cmp(4, 1, "negocio", "Negócio - Cód.", Dados.Tp.Crs).mTamanho(6),
            new Cmp(4, 2, "negocios.nome", "Negócio - Nome", Dados.Tp.Crs).mTamanho(60).botaDetalhe("estrangeiro", true)
        });
    }

    @Override
    public void preparaEstr() throws Exception {
        botaRelacao(new CadRelacao("usuarios").botaRelacao("usuario", "codigo"));
        botaReferencia(new CadReferenciar(this, "usuario", CadPessoas.class).botaChave("usuario", "codigo").botaEstrangeiro("usuarios.usuario", "usuario"));
        botaRelacao(new CadRelacao("negocios").botaRelacao("negocio", "codigo"));
        botaReferencia(new CadReferenciar(this, "negocio", CadNegocios.class).botaChave("negocio", "codigo").botaEstrangeiro("negocios.nome", "nome"));
    }

    @Override
    public void preparaIntf() throws Exception {
        botaBotao(new BotLimpar());
    }

    private class BotLimpar extends Botao {

        public BotLimpar() {
            super("Limpar", 'L');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            QuestaoTp ent = new QuestaoTp(Dados.Tp.Mom, "Excluir Registros Anteriores à", UtlTem.somaDias(new Date(), -30));
            ent.aoConfirmar = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread("CadAuditoria Limpar") {
                        @Override
                        public void run() {
                            try {
                                Integer aid = Utl.alertaPopNovoId();
                                Utl.alertaPop(aid, "Limpando Registro(s) de Auditoria...");
                                Integer afetados = conexao.opera("DELETE FROM auditoria WHERE momento < ?",
                                        new Vlrs(ent.retorno));
                                Utl.alertaPopMudar(aid, "Limpado(s) " + afetados + " Registro(s) de Auditoria");
                                Utl.alertaPopAutoFechar(aid);
                            } catch (Exception ex) {
                                Utl.registra(ex);
                            }
                        }
                    }.start();

                }
            };
            ent.mostra("Limpar Auditoria");
        }
    }

}
