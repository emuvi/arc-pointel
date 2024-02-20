package pin.adm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pin.libamk.Cmp;
import pin.libbas.Dados;
import pin.libutl.Utl;
import pin.libvlr.Vlrs;
import pin.modamk.Rotina;

public class RotDesbloquearFatura extends Rotina {

    private Boolean especifico;

    public RotDesbloquearFatura() {
        this(null, null);
    }

    public RotDesbloquearFatura(String comSerie, String eCodigo) {
        super("Desbloquear Fatura", new Cmp[]{
            new Cmp(1, 1, "serie", "Série", Dados.Tp.Crs).mTamanho(4).botaObrigatorio().mVlrInicial(comSerie),
            new Cmp(1, 2, "codigo", "Código", Dados.Tp.Crs).mTamanho(10).botaObrigatorio().mVlrInicial(eCodigo)
        });

        especifico = comSerie != null && eCodigo != null;

        aoConfirmar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    conexao.opera("DELETE FROM lancamentos WHERE serie = ? AND fatura = ? AND per_nf = 'S'",
                            new Vlrs(pegaVlr("serie"), pegaVlr("codigo")));
                    conexao.opera("UPDATE faturas SET lan_ger = 'N' WHERE serie = ? AND codigo = ?",
                            new Vlrs(pegaVlr("serie"), pegaVlr("codigo")));
                    Utl.alerta("Fatura Desbloqueada");
                    if (especifico) {
                        fechar();
                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }
        };
    }
}
