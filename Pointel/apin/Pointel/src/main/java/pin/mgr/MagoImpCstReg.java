package pin.mgr;

import java.util.List;
import pin.modinf.TabRelacao;

public class MagoImpCstReg {

    public String conexaoDe;
    public String conexaoPara;
    public String tabelaDe;
    public String tabelaPara;
    public List<TabRelacao> relacoes;
    public Boolean excluir;
    public Boolean inserir;
    public Boolean atualizar;
    public Boolean pausarSeErrar;

    public MagoImpCstReg(MagoImpCst daConstrucao) {
        conexaoDe = daConstrucao.conexaoDe;
        conexaoPara = daConstrucao.conexaoPara;
        tabelaDe = daConstrucao.tabelaDe.pEsquemaENome();
        tabelaPara = daConstrucao.tabelaPara.pEsquemaENome();
        relacoes = daConstrucao.relacoes;
        excluir = daConstrucao.excluir;
        inserir = daConstrucao.inserir;
        atualizar = daConstrucao.atualizar;
        pausarSeErrar = daConstrucao.pausarSeErrar;
    }

}
