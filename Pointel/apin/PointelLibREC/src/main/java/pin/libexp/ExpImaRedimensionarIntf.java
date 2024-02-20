package pin.libexp;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import javax.swing.AbstractAction;
import pin.libamg.EdtNumLon;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libbas.MultiParalelo;
import pin.libbas.Paralelo;
import pin.libutl.UtlIma;

public class ExpImaRedimensionarIntf extends Intf {

    private List<File> arquivos;
    private Integer largura;
    private Integer altura;
    private Double qualidade;
    private Integer paralelos;

    public ExpImaRedimensionarIntf(List<File> dosArquivos) {
        super(new Campos(
                new Cmp(1, 1, "largura", "Largura", Dados.Tp.Int),
                new Cmp(1, 2, "altura", "Altura", Dados.Tp.Int),
                new Cmp(2, 1, "qualidade", "Qualidade", Dados.Tp.NumLon).botaParams(EdtNumLon.Params.Progressivo, new EdtNumLon.ParamMinimo(0.0), new EdtNumLon.ParamMaximo(100.0)).mVlrInicial(90.0),
                new Cmp(2, 2, "paralelos", "Paralelos", Dados.Tp.Int).mVlrInicial(4)
        )
        );
        arquivos = dosArquivos;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Redimensionar Imagens");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        botaConfirmarECancelar();
    }

    @Override
    public void aoConfirmar(Object comOrigem) throws Exception {
        largura = cmps().pgVlr("largura").pgInt();
        altura = cmps().pgVlr("altura").pgInt();
        qualidade = cmps().pgVlr("qualidade").pgNumLon();
        paralelos = cmps().pgVlr("paralelos").pgInt(1);
        if (largura == null || altura == null || qualidade == null) {
            throw new Exception("Informações Insuficientes para Realização");
        }
        MultiParalelo<File> mult = new MultiParalelo<>("Redimensionar Imagens");
        mult.mostrador();
        mult.botaCarregador(new ParCarrega(mult));
        for (int ie = 1; ie <= paralelos; ie++) {
            mult.botaExecutor(new ParExecuta(mult, ie));
        }
        mult.botaAoTerminar(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mult.termina();
            }
        });
        mult.inicia();
    }

    private class ParCarrega extends Paralelo {

        public ParCarrega(MultiParalelo doMultiplo) {
            super(doMultiplo);
        }

        @Override
        public void run() {
            for (File arquivo : arquivos) {
                if (pMulti().pausarEdeveCarregadorTerminar()) {
                    return;
                }
                pMulti().botaPrimeiro(arquivo);
                retorna("Carregado: " + arquivo.getAbsolutePath());
            }
        }
    }

    private class ParExecuta extends Paralelo {

        public ParExecuta(MultiParalelo doMultiplo, Integer noIndice) {
            super(doMultiplo, noIndice);
        }

        @Override
        public void run() {
            while (!pMulti().pausarEdeveExecutorTerminar()) {
                File arquivo = (File) pMulti().tiraUltimo();
                if (arquivo != null) {
                    try {
                        UtlIma.redimensiona(arquivo, qualidade / 100, largura, altura);
                        retorna("Rediomensionado: " + arquivo.getAbsolutePath());
                    } catch (Exception ex) {
                        retorna(ex);
                    }
                }
            }
        }
    }

}
