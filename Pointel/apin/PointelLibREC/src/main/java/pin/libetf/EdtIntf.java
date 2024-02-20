package pin.libetf;

import pin.libamg.Edt;
import pin.libamk.Campos;
import pin.libamk.Cmp;
import pin.libamk.Intf;
import pin.libbas.Dados;
import pin.libvlr.Vlr;

public class EdtIntf extends Intf {

    private Dados.Tp tipo;

    public EdtIntf(Dados.Tp tipo) {
        super(new Campos(new Cmp(1, 1, "edt", tipo)));
        this.tipo = tipo;
    }

    public EdtIntf(Dados.Tp tipo, String comDescricao) {
        super(comDescricao, new Campos(new Cmp(1, 1, "edt", tipo)));
        this.tipo = tipo;
    }

    public EdtIntf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Editor");
    }

    @Override
    public void preparaIntf() throws Exception {
        super.preparaIntf();
        tiraLinhaCampos();
        if (!Dados.ehBasico(tipo)) {
            mDimensao(700, 500);
            botaDimensionavel();
        }
    }

    @Override
    public void especializaIntf() throws Exception {
        edt().botaDimensionavel();
        botaLinha(60, edt(), PreencherTp.Ambos, 1, 1);
    }

    @Override
    public EdtIntf mostra() throws Exception {
        super.mostra();
        cmps().pega("edt").pEdt().botaFoco();
        return this;
    }

    @Override
    public EdtIntf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        cmps().pega("edt").pEdt().botaFoco();
        return this;
    }

    public EdtIntf abre(Object oValor) throws Exception {
        cmps().pega("edt").pEdt().mdVlr(oValor);
        return this;
    }

    public Cmp campo() {
        return cmps().pega("edt");
    }

    public Edt edt() {
        return cmps().pega("edt").pEdt();
    }

    public synchronized Vlr pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlr();
    }

    public synchronized Vlr pgVlr(Object comPadrao) throws Exception {
        return cmps().pega("edt").pEdt().pgVlr(comPadrao);
    }

    public static EdtIntf abreEtf(Object oValor) throws Exception {
        return EdtIntf.abreEtf(oValor, Dados.pega(oValor), null);
    }

    public static EdtIntf abreEtf(Object oValor, Dados.Tp doTipo) throws Exception {
        return EdtIntf.abreEtf(oValor, doTipo, null);
    }

    public static EdtIntf abreEtf(Object oValor, String comTitulo) throws Exception {
        return EdtIntf.abreEtf(oValor, Dados.pega(oValor), comTitulo);
    }

    public static EdtIntf abreEtf(Object oValor, Dados.Tp doTipo, String comTitulo) throws Exception {
        EdtIntf retorno = null;
        if (oValor != null && doTipo != null) {
            switch (doTipo) {
                case Esc: {
                    EscEtf jan = new EscEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Lis: {
                    LisEtf jan = new LisEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Arv: {
                    ArvEtf jan = new ArvEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Tex: {
                    TexEtf jan = new TexEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Ima: {
                    ImaEtf jan = new ImaEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Aud: {
                    AudEtf jan = new AudEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Vid: {
                    VidEtf jan = new VidEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Pla: {
                    PlaEtf jan = new PlaEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Doc: {
                    DocEtf jan = new DocEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Frm: {
                    FrmEtf jan = new FrmEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                case Apr: {
                    AprEtf jan = new AprEtf();
                    jan.mostra(comTitulo);
                    jan.abre(oValor);
                    retorno = jan;
                    break;
                }
                default:
                    break;
            }
        }
        return retorno;
    }
}
