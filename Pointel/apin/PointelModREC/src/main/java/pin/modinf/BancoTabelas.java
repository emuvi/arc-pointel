package pin.modinf;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BancoTabelas extends Tabelas {

    private final Banco banco;
    private final List<Tabela> lista;

    public BancoTabelas(Banco doBanco) {
        banco = doBanco;
        lista = new ArrayList<>();
    }

    @Override
    public Tabelas bota(Tabela aTabela) {
        aTabela.mBanco(banco);
        lista.add(aTabela);
        return this;
    }

    @Override
    public Tabela pega(int doIndice) throws Exception {
        Tabela retorno = lista.get(doIndice);
        return retorno.mDasTabelas(this);
    }

    @Override
    public Tabela pega(String noEsquema, String comNome) throws Exception {
        return pega(noEsquema + "." + comNome);
    }

    @Override
    public Tabela pega(String comEsquemaENome) throws Exception {
        for (Tabela tab : lista) {
            if (Objects.equals(comEsquemaENome, tab.pEsquemaENome())) {
                return tab.mDasTabelas(this);
            }
        }
        return null;
    }

    @Override
    public Boolean tem(String noEsquema, String comNome) {
        return tem(noEsquema + "." + comNome);
    }

    @Override
    public Boolean tem(String comEsquemaENome) {
        for (Tabela tab : lista) {
            if (Objects.equals(comEsquemaENome, tab.pEsquemaENome())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer tamanho() {
        return lista.size();
    }

    @Override
    public void limpa() {
        lista.clear();
    }

    public Tabela carrega(int doIndice) throws Exception {
        Tabela retorno = lista.get(doIndice);
        retorno.pCampos();
        retorno.pEstrangeiros();
        retorno.pIndices();
        retorno.pVerificadores();
        return retorno.mDasTabelas(this);
    }

    public Tabela carrega(String noEsquema, String comNome) throws Exception {
        return carrega(noEsquema + "." + comNome);
    }

    public Tabela carrega(String comEsquemaENome) throws Exception {
        for (Tabela tab : lista) {
            if (Objects.equals(comEsquemaENome, tab.pEsquemaENome())) {
                tab.pCampos();
                tab.pEstrangeiros();
                tab.pIndices();
                tab.pVerificadores();
                return tab.mDasTabelas(this);
            }
        }
        return null;
    }

}
