package pin.libbas;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pin.libutl.Utl;
import pin.libutl.UtlApr;
import pin.libutl.UtlArq;
import pin.libutl.UtlArv;
import pin.libutl.UtlAud;
import pin.libutl.UtlCor;
import pin.libutl.UtlCr;
import pin.libutl.UtlCrs;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlDoc;
import pin.libutl.UtlFor;
import pin.libutl.UtlFrm;
import pin.libutl.UtlHor;
import pin.libutl.UtlIma;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLis;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;
import pin.libutl.UtlPla;
import pin.libutl.UtlTex;
import pin.libutl.UtlVid;
import pin.libvlr.VApr;
import pin.libvlr.VArq;
import pin.libvlr.VArv;
import pin.libvlr.VAud;
import pin.libvlr.VCor;
import pin.libvlr.VCr;
import pin.libvlr.VCrs;
import pin.libvlr.VDat;
import pin.libvlr.VDatHor;
import pin.libvlr.VDoc;
import pin.libvlr.VEsc;
import pin.libvlr.VFor;
import pin.libvlr.VFrm;
import pin.libvlr.VHor;
import pin.libvlr.VIma;
import pin.libvlr.VInt;
import pin.libvlr.VIntLon;
import pin.libvlr.VLis;
import pin.libvlr.VLog;
import pin.libvlr.VMom;
import pin.libvlr.VNum;
import pin.libvlr.VNumLon;
import pin.libvlr.VPla;
import pin.libvlr.VTex;
import pin.libvlr.VVid;
import pin.libvlr.Vlr;
import pin.libvlr.Vlrs;

public class Dados {

    public static enum Tp {
        Abs(false, false, false, "AS", "Abas"), Cts(false, false, false, "CT", "Cartões"), Pai(false, false, false, "P", "Painel"), Bot(false, false, false, "B", "Botão"),
        Log(true, true, false, "L", "Lógico"), Cr(true, true, false, "C", "Caracter"), Crs(true, true, false, "CS", "Caracteres"),
        Cor(true, false, false, "CR", "Cor"), Sen(true, false, false, "SN", "Senha"), Enu(true, false, false, "EN", "Enumeração"), Sug(true, false, false, "SU", "Sugestão"),
        Int(true, true, false, "I", "Inteiro"), IntLon(true, true, false, "IL", "Inteiro Longo"), Ser(true, false, false, "SE", "Serial"), SerLon(true, false, false, "SL", "Serial Longo"),
        Num(true, true, false, "N", "Numérico"), NumLon(true, true, false, "NL", "Numérico Longo"),
        Dat(true, true, false, "D", "Data"), Hor(true, true, false, "H", "Hora"), DatHor(true, true, false, "DH", "DataHora"), Mom(true, true, false, "MO", "Momento"),
        Esc(true, false, true, "ES", "Escolha"), Lis(true, false, true, "LI", "Lista"), Arv(true, false, true, "AR", "Árvore"), Tex(true, false, true, "TE", "Texto"), Ima(true, false, true, "IM", "Imagem"), Aud(true, false, true, "AU", "Áudio"), Vid(true, false, true, "VI", "Vídeo"),
        For(true, false, true, "FO", "Forma"), Pla(true, false, true, "PL", "Planilha"), Doc(true, false, true, "DO", "Documento"), Frm(true, false, true, "FR", "Formulário"), Apr(true, false, true, "AP", "Apresentação"), Arq(true, false, true, "AR", "Arquivo"),
        Ind(true, false, false, "IN", "Indefinido");

        private final Boolean valor;
        private final Boolean basico;
        private final Boolean arquivo;
        private final String codigo;
        private final String titulo;

        private Tp(Boolean valor, Boolean basico, Boolean arquivo, String codigo, String titulo) {
            this.valor = valor;
            this.basico = basico;
            this.arquivo = arquivo;
            this.codigo = codigo;
            this.titulo = titulo;
        }

        public Boolean pValor() {
            return valor;
        }

        public Boolean pBasico() {
            return basico;
        }

        public Boolean pArquivo() {
            return arquivo;
        }

        public String pCodigo() {
            return codigo;
        }

        public String pTitulo() {
            return titulo;
        }

    };

    public static Dados.Tp pega(String comNome) {
        return Dados.Tp.valueOf(comNome);
    }

    public static Dados.Tp pega(Class daClasse) {
        Dados.Tp retorno = null;
        if (daClasse == VLog.class) {
            retorno = Dados.Tp.Log;
        } else if (daClasse == VCr.class) {
            retorno = Dados.Tp.Cr;
        } else if (daClasse == VCrs.class) {
            retorno = Dados.Tp.Crs;
        } else if (daClasse == VCor.class) {
            retorno = Dados.Tp.Cor;
        } else if (daClasse == VInt.class) {
            retorno = Dados.Tp.Int;
        } else if (daClasse == VIntLon.class) {
            retorno = Dados.Tp.IntLon;
        } else if (daClasse == VNum.class) {
            retorno = Dados.Tp.Num;
        } else if (daClasse == VNumLon.class) {
            retorno = Dados.Tp.NumLon;
        } else if (daClasse == VDat.class) {
            retorno = Dados.Tp.Dat;
        } else if (daClasse == VHor.class) {
            retorno = Dados.Tp.Hor;
        } else if (daClasse == VDatHor.class) {
            retorno = Dados.Tp.DatHor;
        } else if (daClasse == VMom.class) {
            retorno = Dados.Tp.Mom;
        } else if (daClasse == VEsc.class) {
            retorno = Dados.Tp.Esc;
        } else if (daClasse == VLis.class) {
            retorno = Dados.Tp.Lis;
        } else if (daClasse == VArv.class) {
            retorno = Dados.Tp.Arv;
        } else if (daClasse == VTex.class) {
            retorno = Dados.Tp.Tex;
        } else if (daClasse == VIma.class) {
            retorno = Dados.Tp.Ima;
        } else if (daClasse == VAud.class) {
            retorno = Dados.Tp.Aud;
        } else if (daClasse == VVid.class) {
            retorno = Dados.Tp.Vid;
        } else if (daClasse == VDoc.class) {
            retorno = Dados.Tp.Doc;
        } else if (daClasse == VPla.class) {
            retorno = Dados.Tp.Pla;
        } else if (daClasse == VFor.class) {
            retorno = Dados.Tp.For;
        } else if (daClasse == VFrm.class) {
            retorno = Dados.Tp.Frm;
        } else if (daClasse == VApr.class) {
            retorno = Dados.Tp.Apr;
        } else if (daClasse == VArq.class) {
            retorno = Dados.Tp.Arq;
        } else if (daClasse == Vlr.class) {
            retorno = Dados.Tp.Ind;
        } else if (UtlLog.eh(daClasse)) {
            retorno = Dados.Tp.Log;
        } else if (UtlCr.eh(daClasse)) {
            retorno = Dados.Tp.Cr;
        } else if (UtlCrs.eh(daClasse)) {
            retorno = Dados.Tp.Crs;
        } else if (UtlCor.eh(daClasse)) {
            retorno = Dados.Tp.Cor;
        } else if (UtlInt.eh(daClasse)) {
            retorno = Dados.Tp.Int;
        } else if (UtlIntLon.eh(daClasse)) {
            retorno = Dados.Tp.IntLon;
        } else if (UtlNum.eh(daClasse)) {
            retorno = Dados.Tp.Num;
        } else if (UtlNumLon.eh(daClasse)) {
            retorno = Dados.Tp.NumLon;
        } else if (UtlMom.eh(daClasse)) {
            retorno = Dados.Tp.Mom;
        } else if (UtlDatHor.eh(daClasse)) {
            retorno = Dados.Tp.DatHor;
        } else if (UtlDat.eh(daClasse)) {
            retorno = Dados.Tp.Dat;
        } else if (UtlHor.eh(daClasse)) {
            retorno = Dados.Tp.Hor;
        } else if (UtlArq.eh(daClasse)) {
            retorno = Dados.Tp.Arq;
        } else if (UtlLis.eh(daClasse)) {
            retorno = Dados.Tp.Lis;
        } else if (UtlArv.eh(daClasse)) {
            retorno = Dados.Tp.Arv;
        }
        return retorno;
    }

    public static Dados.Tp pega(Object doValor) {
        Dados.Tp retorno = null;
        if (doValor instanceof VLog) {
            retorno = Dados.Tp.Log;
        } else if (doValor instanceof VCr) {
            retorno = Dados.Tp.Cr;
        } else if (doValor instanceof VCrs) {
            retorno = Dados.Tp.Crs;
        } else if (doValor instanceof VCor) {
            retorno = Dados.Tp.Cor;
        } else if (doValor instanceof VInt) {
            retorno = Dados.Tp.Int;
        } else if (doValor instanceof VIntLon) {
            retorno = Dados.Tp.IntLon;
        } else if (doValor instanceof VNum) {
            retorno = Dados.Tp.Num;
        } else if (doValor instanceof VNumLon) {
            retorno = Dados.Tp.NumLon;
        } else if (doValor instanceof VDat) {
            retorno = Dados.Tp.Dat;
        } else if (doValor instanceof VHor) {
            retorno = Dados.Tp.Hor;
        } else if (doValor instanceof VDatHor) {
            retorno = Dados.Tp.DatHor;
        } else if (doValor instanceof VMom) {
            retorno = Dados.Tp.Mom;
        } else if (doValor instanceof VEsc) {
            retorno = Dados.Tp.Esc;
        } else if (doValor instanceof VLis) {
            retorno = Dados.Tp.Lis;
        } else if (doValor instanceof VArv) {
            retorno = Dados.Tp.Arv;
        } else if (doValor instanceof VTex) {
            retorno = Dados.Tp.Tex;
        } else if (doValor instanceof VIma) {
            retorno = Dados.Tp.Ima;
        } else if (doValor instanceof VAud) {
            retorno = Dados.Tp.Aud;
        } else if (doValor instanceof VVid) {
            retorno = Dados.Tp.Vid;
        } else if (doValor instanceof VDoc) {
            retorno = Dados.Tp.Doc;
        } else if (doValor instanceof VPla) {
            retorno = Dados.Tp.Pla;
        } else if (doValor instanceof VFor) {
            retorno = Dados.Tp.For;
        } else if (doValor instanceof VFrm) {
            retorno = Dados.Tp.Frm;
        } else if (doValor instanceof VApr) {
            retorno = Dados.Tp.Apr;
        } else if (doValor instanceof VArq) {
            retorno = Dados.Tp.Arq;
        } else if (doValor instanceof Vlr) {
            retorno = pega(((Vlr) doValor).pg());
        } else if (UtlLog.eh(doValor)) {
            retorno = Dados.Tp.Log;
        } else if (UtlCr.eh(doValor)) {
            retorno = Dados.Tp.Cr;
        } else if (UtlCrs.eh(doValor)) {
            retorno = Dados.Tp.Crs;
        } else if (UtlCrs.eh(doValor)) {
            retorno = Dados.Tp.Cor;
        } else if (UtlInt.eh(doValor)) {
            retorno = Dados.Tp.Int;
        } else if (UtlIntLon.eh(doValor)) {
            retorno = Dados.Tp.IntLon;
        } else if (UtlNum.eh(doValor)) {
            retorno = Dados.Tp.Num;
        } else if (UtlNumLon.eh(doValor)) {
            retorno = Dados.Tp.NumLon;
        } else if (UtlMom.eh(doValor)) {
            retorno = Dados.Tp.Mom;
        } else if (UtlDatHor.eh(doValor)) {
            retorno = Dados.Tp.DatHor;
        } else if (UtlDat.eh(doValor)) {
            retorno = Dados.Tp.Dat;
        } else if (UtlHor.eh(doValor)) {
            retorno = Dados.Tp.Hor;
        } else if (UtlArq.eh(doValor)) {
            retorno = Dados.Tp.Arq;
        } else if (UtlLis.eh(doValor)) {
            retorno = Dados.Tp.Lis;
        } else if (UtlArv.eh(doValor)) {
            retorno = Dados.Tp.Arv;
        }
        return retorno;
    }

    public static Dados.Tp pegaPelo(String titulo) {
        for (Tp tp : Tp.values()) {
            if (Objects.equals(tp.pTitulo(), titulo)) {
                return tp;
            }
        }
        return null;
    }

    public static Dados.Tp pTipoPelaExtensao(String extensao) {
        Dados.Tp retorno = Dados.Tp.Arq;
        if (extensao != null) {
            if (UtlLis.ehExtensao(extensao)) {
                retorno = Dados.Tp.Lis;
            } else if (UtlArv.ehExtensao(extensao)) {
                retorno = Dados.Tp.Arv;
            } else if (UtlTex.ehExtensao(extensao)) {
                retorno = Dados.Tp.Tex;
            } else if (UtlPla.ehExtensao(extensao)) {
                retorno = Dados.Tp.Pla;
            } else if (UtlIma.ehExtensao(extensao)) {
                retorno = Dados.Tp.Ima;
            } else if (UtlAud.ehExtensao(extensao)) {
                retorno = Dados.Tp.Aud;
            } else if (UtlVid.ehExtensao(extensao)) {
                retorno = Dados.Tp.Vid;
            } else if (UtlDoc.ehExtensao(extensao)) {
                retorno = Dados.Tp.Doc;
            } else if (UtlFor.ehExtensao(extensao)) {
                retorno = Dados.Tp.For;
            } else if (UtlFrm.ehExtensao(extensao)) {
                retorno = Dados.Tp.Frm;
            } else if (UtlApr.ehExtensao(extensao)) {
                retorno = Dados.Tp.Apr;
            }
        }
        return retorno;
    }

    public static Dados.Tp pTipoEnu(Object doSelecionado) {
        if (doSelecionado == null) {
            return null;
        }
        for (Tp tp : Tp.values()) {
            if (Objects.equals(tp.pCodigo(), doSelecionado)) {
                return tp;
            }
        }
        return null;
    }

    public static String pNomeCompletoEnu(Dados.Tp doTipo) {
        return "(" + doTipo.pCodigo() + ") " + doTipo.pTitulo();
    }

    public static Boolean ehBasico(Tp oTipo) {
        if (oTipo == null) {
            return false;
        } else {
            return oTipo.pBasico();
        }
    }

    public static Object[] pTiposEnu() {
        List<Object> ret = new ArrayList<>();
        for (Tp tp : Tp.values()) {
            ret.add(pNomeCompletoEnu(tp));
        }
        return ret.toArray();
    }

    public static Object[] pTiposBasicosEnu() {
        List<Object> ret = new ArrayList<>();
        for (Tp tp : Tp.values()) {
            if (tp.pBasico()) {
                ret.add(pNomeCompletoEnu(tp));
            }
        }
        return ret.toArray();
    }

    public static Object[] pTiposValorEnu() {
        List<Object> ret = new ArrayList<>();
        for (Tp tp : Tp.values()) {
            if (tp.pValor()) {
                ret.add(pNomeCompletoEnu(tp));
            }
        }
        return ret.toArray();
    }

    public static Object[] pTiposArquivoEnu() {
        List<Object> ret = new ArrayList<>();
        for (Tp tp : Tp.values()) {
            if (tp.pArquivo()) {
                ret.add(pNomeCompletoEnu(tp));
            }
        }
        return ret.toArray();
    }

    public static Vlr pegaVlr(Dados.Tp doTipo, Object eValor) throws Exception {
        Vlr retorno = null;
        if (doTipo != null) {
            switch (doTipo) {
                case Log:
                    retorno = new VLog(eValor);
                    break;
                case Cr:
                    retorno = new VCr(eValor);
                    break;
                case Crs:
                case Sen:
                case Sug:
                    retorno = new VCrs(eValor);
                    break;
                case Cor:
                    retorno = new VCor(eValor);
                    break;
                case Int:
                case Ser:
                    retorno = new VInt(eValor);
                    break;
                case IntLon:
                case SerLon:
                    retorno = new VIntLon(eValor);
                    break;
                case Num:
                    retorno = new VNum(eValor);
                    break;
                case NumLon:
                    retorno = new VNumLon(eValor);
                    break;
                case Dat:
                    retorno = new VDat(eValor);
                    break;
                case Hor:
                    retorno = new VHor(eValor);
                    break;
                case DatHor:
                    retorno = new VDatHor(eValor);
                    break;
                case Mom:
                    retorno = new VMom(eValor);
                    break;
                case Enu:
                    retorno = new Vlr(eValor);
                    break;
                case Esc:
                    retorno = new VEsc(eValor);
                    break;
                case Lis:
                    retorno = new VLis(eValor);
                    break;
                case Arv:
                    retorno = new VArv(eValor);
                    break;
                case Tex:
                    retorno = new VTex(eValor);
                    break;
                case Pla:
                    retorno = new VPla(eValor);
                    break;
                case Ima:
                    retorno = new VIma(eValor);
                    break;
                case Aud:
                    retorno = new VAud(eValor);
                    break;
                case Vid:
                    retorno = new VVid(eValor);
                    break;
                case Doc:
                    retorno = new VDoc(eValor);
                    break;
                case For:
                    retorno = new VFor(eValor);
                    break;
                case Frm:
                    retorno = new VFrm(eValor);
                    break;
                case Apr:
                    retorno = new VApr(eValor);
                    break;
                case Arq:
                    retorno = new VArq(eValor);
                    break;
                default:
                    Utl.registra(new Exception("Tipo de Dados Ainda Não Preparado."));
                    break;
            }
        }
        return retorno;
    }

    public static void botaParametros(PreparedStatement naDeclaracao, Vlrs osParametros) throws Exception {
        if (osParametros != null) {
            for (int i1 = 0; i1 < osParametros.tamanho(); i1++) {
                Vlr vlr = osParametros.pgVlr(i1);
                if (vlr instanceof VNum) {
                    vlr = new VNum(UtlNum.mantem(vlr.pgNum(), 3));
                } else if (vlr instanceof VNumLon) {
                    vlr = new VNumLon(UtlNumLon.mantem(vlr.pgNumLon(), 3));
                }
                naDeclaracao.setObject(i1 + 1, vlr.paraParamSQL());
            }
        }
    }

}
