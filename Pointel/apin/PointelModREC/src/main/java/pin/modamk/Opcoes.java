package pin.modamk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Objects;
import pin.libamk.Cmp;
import pin.libbas.Conjunto;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLog;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;

public class Opcoes extends Rotina {

    public Boolean deConexao;

    public Opcoes(Cmp[] eCampos) {
        this(true, eCampos);
    }

    public Opcoes(Boolean daConexao, Cmp[] eCampos) {
        super("Opções", eCampos);
        deConexao = daConexao;
        if (deConexao) {
            Object[] pars = new Object[campos.size()];
            for (int ip = 0; ip < campos.size(); ip++) {
                pars[ip] = campos.get(ip).pDetalhe("referencia");
            }

            try {
                Conjunto res = conexao.conCfgs().pegaTodas(pars);

                while (res.proximo()) {
                    String chave = res.pgVlr("chave").pgCrs();
                    String valor = res.pgVlr("valor").pgCrs();

                    Cmp cmp = pegaCampoDaReferencia(chave);
                    if (cmp != null) {
                        switch (cmp.pTipo()) {
                            case Log:
                                cmp.mVlrInicial(UtlLog.pega(valor, (Boolean) cmp.pVlrInicial()));
                                break;
                            case Cr:
                            case Crs:
                            case Enu:
                            case Sug:
                                cmp.mVlrInicial(UtlCrs.pega(valor, (String) cmp.pVlrInicial()));
                                break;
                            case Int:
                                cmp.mVlrInicial(UtlInt.pega(valor, (Integer) cmp.pVlrInicial()));
                                break;
                            case IntLon:
                                cmp.mVlrInicial(UtlIntLon.pega(valor, (Long) cmp.pVlrInicial()));
                                break;
                            case Num:
                                cmp.mVlrInicial(UtlNum.pega(valor, (Float) cmp.pVlrInicial()));
                                break;
                            case NumLon:
                                cmp.mVlrInicial(UtlNumLon.pega(valor, (Double) cmp.pVlrInicial()));
                                break;
                            case Dat:
                                cmp.mVlrInicial(UtlDat.pegaMaquina(valor, (Date) cmp.pVlrInicial()));
                                break;
                            case Hor:
                                cmp.mVlrInicial(UtlHor.pegaMaquina(valor, (Date) cmp.pVlrInicial()));
                                break;
                            case DatHor:
                                cmp.mVlrInicial(UtlDatHor.pegaMaquina(valor, (Date) cmp.pVlrInicial()));
                                break;
                        }
                    }
                }
            } catch (Exception ex) {
                Utl.registra(ex);
            }

            aoConfirmar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        for (Cmp cmp : campos) {
                            Object ini = cmp.pVlrInicial();
                            Object fim = cmp.pEdt().pgVlr();
                            if (!Objects.equals(ini, fim)) {
                                switch (cmp.pTipo()) {
                                    case Log:
                                        conexao.conCfgs().botaLog((String) cmp.pDetalhe("referencia"), UtlLog.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Cr:
                                    case Crs:
                                    case Enu:
                                    case Sug:
                                        conexao.conCfgs().botaCrs((String) cmp.pDetalhe("referencia"), UtlCrs.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Int:
                                        conexao.conCfgs().botaInt((String) cmp.pDetalhe("referencia"), UtlInt.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case IntLon:
                                        conexao.conCfgs().botaIntLon((String) cmp.pDetalhe("referencia"), UtlIntLon.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Num:
                                        conexao.conCfgs().botaNum((String) cmp.pDetalhe("referencia"), UtlNum.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case NumLon:
                                        conexao.conCfgs().botaNumLon((String) cmp.pDetalhe("referencia"), UtlNumLon.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Dat:
                                        conexao.conCfgs().botaCrs((String) cmp.pDetalhe("referencia"), UtlDat.formataMaquina(UtlDat.pega(cmp.pEdt().pgVlr())));
                                        break;
                                    case Hor:
                                        conexao.conCfgs().botaCrs((String) cmp.pDetalhe("referencia"), UtlHor.formataMaquina(UtlHor.pega(cmp.pEdt().pgVlr())));
                                        break;
                                    case DatHor:
                                        conexao.conCfgs().botaCrs((String) cmp.pDetalhe("referencia"), UtlDatHor.formataMaquina(UtlDatHor.pega(cmp.pEdt().pgVlr())));
                                        break;
                                }
                            }
                        }

                        Utl.alerta("Opções Atualizadas");
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            };
        } else {
            for (int ip = 0; ip < campos.size(); ip++) {
                Cmp cmp = campos.get(ip);
                String chave = (String) campos.get(ip).pDetalhe("referencia");

                try {
                    if (cmp != null) {
                        switch (cmp.pTipo()) {
                            case Log:
                                cmp.mVlrInicial(cfgs.pegaLog(chave, (Boolean) cmp.pVlrInicial()));
                                break;
                            case Cr:
                            case Crs:
                            case Enu:
                            case Sug:
                                cmp.mVlrInicial(cfgs.pegaCrs(chave, (String) cmp.pVlrInicial()));
                                break;
                            case Int:
                                cmp.mVlrInicial(cfgs.pegaInt(chave, (Integer) cmp.pVlrInicial()));
                                break;
                            case IntLon:
                                cmp.mVlrInicial(cfgs.pegaIntLon(chave, (Long) cmp.pVlrInicial()));
                                break;
                            case Num:
                                cmp.mVlrInicial(cfgs.pegaNum(chave, (Float) cmp.pVlrInicial()));
                                break;
                            case NumLon:
                                cmp.mVlrInicial(cfgs.pegaNumLon(chave, (Double) cmp.pVlrInicial()));
                                break;
                            case Dat:
                                cmp.mVlrInicial(cfgs.pegaDat(chave, (Date) cmp.pVlrInicial()));
                                break;
                            case Hor:
                                cmp.mVlrInicial(cfgs.pegaHor(chave, (Date) cmp.pVlrInicial()));
                                break;
                            case DatHor:
                                cmp.mVlrInicial(cfgs.pegaDatHor(chave, (Date) cmp.pVlrInicial()));
                                break;
                        }

                    }
                } catch (Exception ex) {
                    Utl.registra(ex);
                }
            }

            aoConfirmar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        for (Cmp cmp : campos) {
                            Object ini = cmp.pVlrInicial();
                            Object fim = cmp.pEdt().pgVlr();
                            if (!Objects.equals(ini, fim)) {
                                switch (cmp.pTipo()) {
                                    case Log:
                                        cfgs.botaLog((String) cmp.pDetalhe("referencia"), UtlLog.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Cr:
                                    case Crs:
                                    case Enu:
                                    case Sug:
                                        cfgs.botaCrs((String) cmp.pDetalhe("referencia"), UtlCrs.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Int:
                                        cfgs.botaInt((String) cmp.pDetalhe("referencia"), UtlInt.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case IntLon:
                                        cfgs.botaIntLon((String) cmp.pDetalhe("referencia"), UtlIntLon.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Num:
                                        cfgs.botaNum((String) cmp.pDetalhe("referencia"), UtlNum.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case NumLon:
                                        cfgs.botaNumLon((String) cmp.pDetalhe("referencia"), UtlNumLon.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Dat:
                                        cfgs.botaDat((String) cmp.pDetalhe("referencia"), UtlDat.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case Hor:
                                        cfgs.botaHor((String) cmp.pDetalhe("referencia"), UtlHor.pega(cmp.pEdt().pgVlr()));
                                        break;
                                    case DatHor:
                                        cfgs.botaDatHor((String) cmp.pDetalhe("referencia"), UtlDatHor.pega(cmp.pEdt().pgVlr()));
                                        break;
                                }
                            }
                        }

                        Utl.alerta("Opções Atualizadas");
                    } catch (Exception ex) {
                        Utl.registra(ex);
                    }
                }
            };
        }
    }

}
