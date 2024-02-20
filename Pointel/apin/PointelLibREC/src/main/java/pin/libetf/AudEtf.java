package pin.libetf;

import pin.libamg.EdtAud;
import pin.libbas.Dados;
import pin.libvlr.VAud;

public class AudEtf extends EdtIntf {

    public AudEtf() {
        super(Dados.Tp.Aud);
    }

    public AudEtf(String comDescricao) {
        super(Dados.Tp.Aud, comDescricao);
    }

    @Override
    public AudEtf mInicial(Object oValor) {
        cmps().pega("edt").mVlrInicial(oValor);
        return this;
    }

    @Override
    public void preparaEstr() throws Exception {
        super.preparaEstr();
        mTitulo("Aúdio Etf");
    }

    @Override
    public AudEtf mostra() throws Exception {
        super.mostra();
        return this;
    }

    @Override
    public AudEtf mostra(String comTitulo) throws Exception {
        super.mostra(comTitulo);
        return this;
    }

    @Override
    public EdtAud edt() {
        return (EdtAud) super.edt();
    }

    @Override
    public AudEtf abre(Object oValor) throws Exception {
        super.abre(oValor);
        return this;
    }

    @Override
    public synchronized VAud pgVlr() throws Exception {
        return cmps().pega("edt").pEdt().pgVlrAud();
    }

}
