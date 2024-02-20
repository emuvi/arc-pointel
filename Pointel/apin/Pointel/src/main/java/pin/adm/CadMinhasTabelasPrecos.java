package pin.adm;

import pin.libvlr.Vlrs;
import pin.libbas.Conjunto;
import java.util.ArrayList;
import java.util.List;

public class CadMinhasTabelasPrecos extends CadTabelasPrecos {

    public CadMinhasTabelasPrecos() throws Exception {
        super();
        titulo = "Minhas Tabelas de Preços";
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        List<Object> restritos = new ArrayList<>();
        Conjunto res = conexao.recorrentes().busca("SELECT tabela FROM pessoas_comissoes WHERE pessoa = ?",
                new Vlrs(conexao.pegaBaseUsuarioPessoa()));
        while (res.proximo()) {
            restritos.add(res.pgVlr(1));
        }
        pegaCampo("codigo").botaRestritos(restritos.toArray());
        campos.remove(pegaCampo("comissao"));
    }

}
