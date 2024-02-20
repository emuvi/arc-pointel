package pin.jrb;

import pin.libamk.Botao;
import pin.libetf.EdtIntf;
import pin.libetf.PlaEtf;
import pin.libbas.Conferencia;
import pin.libbas.Dados;
import pin.libbas.RetornosIntf;
import pin.libvlr.Vlr;
import pin.modinf.Carregador;

public class ServicosExecutadosIntf extends PlaEtf {

    private Servicos servicos;
    private Integer codServico;
    private Carregador carregador;

    public ServicosExecutadosIntf(Servicos dosServicos, Integer doCodServico, String eTipo) {
        super("Executados do Serviço " + eTipo + " (" + doCodServico + ")");
        servicos = dosServicos;
        codServico = doCodServico;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Executados do Serviço");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaDepoisDeAbrir(new CnfDepoisDeAbrir());
        botaDepoisDeAbrir(new CnfAntesDeFechar());
        botaBotao(new BotRegistro());
        botaBotao(new BotRetornos());
        botaBotao(new BotLimpar());
    }

    @Override
    public void especializaIntf() throws Exception {
        super.especializaIntf();
        edt().mEditavel(false);
    }

    @Override
    public ServicosExecutadosIntf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public ServicosExecutadosIntf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public ServicosExecutadosIntf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    private class CnfDepoisDeAbrir extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            carregador = servicos.carregaExecutados(codServico, edt());
        }
    }

    private class CnfAntesDeFechar extends Conferencia {

        @Override
        public void confere(Object comOrigem) throws Exception {
            carregador.fecha();
        }
    }

    private class BotRegistro extends Botao {

        public BotRegistro() {
            super("Registro", 'G');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            Vlr[] linha = edt().controlador().pegaLinhaSelecionadaVlrs();
            EdtIntf.abreEtf(linha[2], Dados.Tp.Tex).edt().mEditavel(false);
        }
    }

    private class BotRetornos extends Botao {

        public BotRetornos() {
            super("Retornos", 'R');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            Vlr[] linha = edt().controlador().pegaLinhaSelecionadaVlrs();
            new RetornosIntf().abre(linha[3].pgArqCam()).mostra();
        }
    }

    private class BotLimpar extends Botao {

        public BotLimpar() {
            super("Limpar", 'L');
        }

        @Override
        public void aoExecutar(Object comOrigem) throws Exception {
            carregador.fecha();
            servicos.limpaExecutados(codServico);
            fecha();
        }
    }
}
