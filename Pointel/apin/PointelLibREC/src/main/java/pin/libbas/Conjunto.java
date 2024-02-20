package pin.libbas;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pin.libtex.Marcados;
import pin.libutl.Utl;
import pin.libutl.UtlBin;
import pin.libutl.UtlCrs;
import pin.libutl.UtlLis;
import pin.libutl.UtlTex;
import pin.libvlr.VApr;
import pin.libvlr.VArq;
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

public class Conjunto extends ArrayList<Vlrs> {

    private volatile String nome;
    private volatile Integer posicao;
    private volatile String[] nomes;
    private volatile String[] tipos;

    public Conjunto() {
        super();
        nome = null;
        posicao = -1;
        nomes = null;
        tipos = null;
    }

    public Conjunto(Vlrs... dasLinhas) {
        super(Arrays.asList(dasLinhas));
        nome = null;
        posicao = -1;
        nomes = null;
        tipos = null;
    }

    public Conjunto(ResultSet dosResultados) throws Exception {
        this(dosResultados, null);
    }

    public Conjunto(ResultSet dosResultados, List<Dados.Tp> comTipos) throws Exception {
        this();
        carrega(dosResultados, comTipos);
    }

    public Conjunto carrega(ResultSet dosResultados, List<Dados.Tp> comTipos) throws Exception {
        ResultSetMetaData rstm = dosResultados.getMetaData();
        int cols = rstm.getColumnCount();
        if (nomes == null) {
            nomes = new String[cols];
            for (int ic = 1; ic <= cols; ic++) {
                nomes[ic - 1] = rstm.getColumnName(ic);
            }
        }
        if (tipos == null) {
            tipos = new String[cols];
            UtlLis.anular(tipos);
        }
        while (dosResultados.next()) {
            Vlrs linha = new Vlrs();
            linha.botaNomes(nomes);
            for (int ic = 0; ic < cols; ic++) {
                Object obj = dosResultados.getObject(ic + 1);
                Vlr vlr = null;
                if (comTipos != null) {
                    if (ic < comTipos.size()) {
                        vlr = Dados.pegaVlr(comTipos.get(ic), obj);
                    } else {
                        vlr = Vlr.novo(obj);
                    }
                } else {
                    vlr = Vlr.novo(obj);
                }
                linha.bota(vlr);
                if (vlr != null && tipos[ic] == null) {
                    if (!vlr.vazio()) {
                        tipos[ic] = vlr.getClass().getCanonicalName();
                    }
                }
            }
            bota(linha);
        }
        return this;
    }

    public static Conjunto descrito(String dosCaracteres) throws Exception {
        if (dosCaracteres == null) {
            return null;
        }
        Conjunto retorno = new Conjunto();
        String cnj = Marcados.preparado(dosCaracteres, "conjunto");
        String nms = Marcados.preparado(cnj, "nms");
        String[] nomes = Partes.pega(nms);
        retorno.botaNomes(nomes);
        String tps = Marcados.preparado(cnj, "tps");
        String[] tipos = Partes.pega(tps);
        retorno.botaTipos(tipos);
        List<String> lins = Marcados.preparados(cnj, "lin");
        for (String lin : lins) {
            Vlrs linha = new Vlrs();
            String[] cols = Partes.pega(lin);
            int icl = 0;
            for (String col : cols) {
                String tipo = null;
                Vlr coluna = null;
                if (icl < tipos.length) {
                    tipo = tipos[icl];
                }
                if (UtlBin.ehDescritoClasse(col)) {
                    tipo = Marcados.preparado(col, "classe");
                }
                if (tipo != null) {
                    String mrTipo = Marcados.prepara(tipo, "classe");
                    String fonte = Marcados.preparado(col, "fonte");
                    String mrFonte = Marcados.prepara(fonte, "fonte");
                    Object obj = UtlBin.descrito(mrTipo + mrFonte);
                    if (obj instanceof Vlr) {
                        coluna = (Vlr) obj;
                    } else {
                        coluna = Vlr.novo(obj);
                    }
                }
                linha.bota(coluna);
                icl++;
            }
            retorno.add(linha);
        }
        return retorno;
    }

    public String pegaNome() {
        return nome;
    }

    public Conjunto mudaNome(String para) {
        nome = para;
        return this;
    }

    public Integer pegaPosicao() {
        return posicao;
    }

    public Conjunto botaPosicao(Integer aPosicao) {
        posicao = aPosicao;
        return this;
    }

    public String[] pegaNomes() {
        return nomes;
    }

    public Conjunto botaNomes(String... nomes) {
        this.nomes = nomes;
        return this;
    }

    public String[] pegaTipos() {
        if (tipos == null) {
            tipos = analisaTipos();
        }
        return tipos;
    }

    public Conjunto botaTipos(String... tipos) {
        this.tipos = tipos;
        return this;
    }

    public Boolean vazio() {
        return isEmpty();
    }

    public Boolean tem() {
        return !isEmpty();
    }

    public Integer tamanho() {
        return size();
    }

    public Integer tamanhoCols() {
        if (vazio()) {
            return 0;
        } else {
            return pegaLinha(0).tamanho();
        }
    }

    public Boolean reinicia() {
        posicao = -1;
        return true;
    }

    public Boolean primeiro() {
        if (size() > 0) {
            posicao = 0;
            return true;
        } else {
            posicao = -1;
            return false;
        }
    }

    public Boolean anterior() {
        if (posicao - 1 > 0 && posicao - 1 > size()) {
            posicao--;
            return true;
        } else {
            return false;
        }
    }

    public Boolean proximo() {
        if (posicao + 1 < size()) {
            posicao++;
            return true;
        } else {
            return false;
        }
    }

    public Boolean ultimo() {
        if (size() > 0) {
            posicao = size() - 1;
            return true;
        } else {
            posicao = -1;
            return false;
        }
    }

    public Boolean posicionado() {
        return (posicao >= 0 && posicao < size());
    }

    public Boolean ehPrimeiro() {
        return (size() > 0 && posicao == 0);
    }

    public Boolean ehUltimo() {
        return (size() > 0 && posicao == size() - 1);
    }

    public Vlrs pegaLinha(Integer daPosicao) {
        return get(daPosicao);
    }

    public Vlrs pegaLinha() {
        if (posicionado()) {
            return pegaLinha(posicao);
        } else {
            return null;
        }
    }

    public Conjunto bota(Vlrs aLinha) {
        add(aLinha);
        return this;
    }

    public Vlrs pegaColuna(Integer daColunaPosicao) {
        Vlrs retorno = null;
        if (!vazio()) {
            retorno = new Vlrs();
            for (Vlrs linha : this) {
                retorno.bota(linha.pgVlr(daColunaPosicao - 1));
            }
        }
        return retorno;
    }

    public Vlrs pegaColuna(String daColunaNome) {
        return pegaColuna(pegaColunaPos(daColunaNome));
    }

    public Integer quantasColunas() {
        Vlrs cmps = pegaLinha();
        if (cmps == null) {
            return 0;
        } else {
            return cmps.size();
        }
    }

    public String pegaColunaNome(Integer comIndice) {
        return nomes[comIndice];
    }

    public Integer pegaColunaPos(String doNome) {
        Integer retorno = -1;
        if (nomes != null) {
            for (int in = 0; in < nomes.length; in++) {
                if (Objects.equals(doNome, nomes[in])) {
                    retorno = in;
                    break;
                }

            }
        }
        return retorno;
    }

    public Vlr pgCol() {
        return pgCol((Object) null);
    }

    public Vlr pgCol(Object comPadrao) {
        if (posicionado()) {
            return pegaLinha().pegaCol(comPadrao);
        } else if (!vazio()) {
            return pegaLinha(0).pegaCol(comPadrao);
        } else {
            return Vlr.novo(comPadrao);
        }
    }

    public <T> T pgCol(Class<? extends T> daClasse) throws Exception {
        return pgCol(daClasse, null);
    }

    public <T> T pgCol(Class<? extends T> daClasse, Object comPadrao) throws Exception {
        return UtlBin.pega(daClasse, pgCol(comPadrao));
    }

    public String paraConsSQL(Integer daColuna) {
        return pgVlr(daColuna).paraConsSQL();
    }

    public String paraConsSQL(String doNome) {
        return pgVlr(doNome).paraConsSQL();
    }

    public Object paraParamSQL(Integer daColuna) {
        return pgVlr(daColuna).paraParamSQL();
    }

    public Object paraParamSQL(String doNome) {
        return pgVlr(doNome).paraParamSQL();
    }

    public Vlr pgVlr(Integer daColuna) {
        return pgVlr(daColuna, null);
    }

    public Vlr pgVlr(Integer daColuna, Object comPadrao) {
        if (posicionado()) {
            return pegaLinha().pgVlr(daColuna - 1, comPadrao);
        } else if (!vazio()) {
            return pegaLinha(0).pgVlr(daColuna - 1, comPadrao);
        } else {
            return Vlr.novo(comPadrao);
        }
    }

    public VLog pgVlrLog(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrLog();
    }

    public VLog pgVlrLog(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrCr();
    }

    public VCr pgVlrCr(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrCrs();
    }

    public VCrs pgVlrCrs(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrCor();
    }

    public VCor pgVlrCor(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrInt();
    }

    public VInt pgVlrInt(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrNum();
    }

    public VNum pgVlrNum(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrDat();
    }

    public VDat pgVlrDat(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrHor();
    }

    public VHor pgVlrHor(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrMom();
    }

    public VMom pgVlrMom(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrEsc();
    }

    public VEsc pgVlrEsc(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrLis();
    }

    public VLis pgVlrLis(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrTex();
    }

    public VTex pgVlrTex(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrIma();
    }

    public VIma pgVlrIma(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrDoc();
    }

    public VDoc pgVlrDoc(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrPla();
    }

    public VPla pgVlrPla(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrAud();
    }

    public VAud pgVlrAud(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrVid();
    }

    public VVid pgVlrVid(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrFor();
    }

    public VFor pgVlrFor(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrFrm();
    }

    public VFrm pgVlrFrm(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrApr();
    }

    public VApr pgVlrApr(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq(Integer daColuna) throws Exception {
        return pgVlr(daColuna).pgVlrArq();
    }

    public VArq pgVlrArq(Integer daColuna, Object comPadrao) throws Exception {
        return pgVlr(daColuna).pgVlrArq(comPadrao);
    }

    public Vlr pgVlr(String doNome) {
        return pgVlr(doNome, null);
    }

    public Vlr pgVlr(String doNome, Object comPadrao) {
        if (posicionado()) {
            return pegaLinha().pgVlr(doNome, comPadrao);
        } else if (!vazio()) {
            return pegaLinha(0).pgVlr(doNome, comPadrao);
        } else {
            return Vlr.novo(comPadrao);
        }
    }

    public VLog pgVlrLog(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrLog();
    }

    public VLog pgVlrLog(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrLog(comPadrao);
    }

    public VCr pgVlrCr(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCr();
    }

    public VCr pgVlrCr(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCr(comPadrao);
    }

    public VCrs pgVlrCrs(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCrs();
    }

    public VCrs pgVlrCrs(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCrs(comPadrao);
    }

    public VCor pgVlrCor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrCor();
    }

    public VCor pgVlrCor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrCor(comPadrao);
    }

    public VInt pgVlrInt(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrInt();
    }

    public VInt pgVlrInt(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrInt(comPadrao);
    }

    public VIntLon pgVlrIntLon(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrIntLon();
    }

    public VIntLon pgVlrIntLon(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrIntLon(comPadrao);
    }

    public VNum pgVlrNum(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrNum();
    }

    public VNum pgVlrNum(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrNum(comPadrao);
    }

    public VNumLon pgVlrNumLon(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrNumLon();
    }

    public VNumLon pgVlrNumLon(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrNumLon(comPadrao);
    }

    public VDat pgVlrDat(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDat();
    }

    public VDat pgVlrDat(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDat(comPadrao);
    }

    public VHor pgVlrHor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrHor();
    }

    public VHor pgVlrHor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrHor(comPadrao);
    }

    public VDatHor pgVlrDatHor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDatHor();
    }

    public VDatHor pgVlrDatHor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDatHor(comPadrao);
    }

    public VMom pgVlrMom(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrMom();
    }

    public VMom pgVlrMom(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrMom(comPadrao);
    }

    public VEsc pgVlrEsc(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrEsc();
    }

    public VEsc pgVlrEsc(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrEsc(comPadrao);
    }

    public VLis pgVlrLis(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrLis();
    }

    public VLis pgVlrLis(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrLis(comPadrao);
    }

    public VTex pgVlrTex(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrTex();
    }

    public VTex pgVlrTex(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrTex(comPadrao);
    }

    public VIma pgVlrIma(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrIma();
    }

    public VIma pgVlrIma(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrIma(comPadrao);
    }

    public VDoc pgVlrDoc(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrDoc();
    }

    public VDoc pgVlrDoc(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrDoc(comPadrao);
    }

    public VPla pgVlrPla(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrPla();
    }

    public VPla pgVlrPla(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrPla(comPadrao);
    }

    public VAud pgVlrAud(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrAud();
    }

    public VAud pgVlrAud(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrAud(comPadrao);
    }

    public VVid pgVlrVid(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrVid();
    }

    public VVid pgVlrVid(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrVid(comPadrao);
    }

    public VFor pgVlrFor(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrFor();
    }

    public VFor pgVlrFor(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrFor(comPadrao);
    }

    public VFrm pgVlrFrm(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrFrm();
    }

    public VFrm pgVlrFrm(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrFrm(comPadrao);
    }

    public VApr pgVlrApr(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrApr();
    }

    public VApr pgVlrApr(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrApr(comPadrao);
    }

    public VArq pgVlrArq(String doNome) throws Exception {
        return pgVlr(doNome).pgVlrArq();
    }

    public VArq pgVlrArq(String doNome, Object comPadrao) throws Exception {
        return pgVlr(doNome).pgVlrArq(comPadrao);
    }

    public Boolean tem(Object oValor) {
        for (Vlrs linha : this) {
            for (Vlr vlr : linha) {
                if (Objects.equals(oValor, vlr)) {
                    return true;
                }
            }
        }
        return true;
    }

    public Boolean tem(Integer naColuna, Object oValor) {
        for (Vlrs linha : this) {
            if (Objects.equals(oValor, linha.pgVlr(naColuna - 1))) {
                return true;
            }
        }
        return false;
    }

    public Boolean tem(String naColuna, Object oValor) {
        return tem(pegaColunaPos(naColuna), oValor);
    }

    public String[] analisaTipos() {
        String[] retorno = new String[pegaLinha().tamanho()];
        for (int ir = 0; ir < retorno.length; ir++) {
            retorno[ir] = null;
        }
        for (Vlrs lin : this) {
            if (retorno.length < lin.size()) {
                int il = retorno.length;
                retorno = Arrays.copyOf(retorno, lin.size());
                for (int ir = il; ir < retorno.length; ir++) {
                    retorno[ir] = null;
                }
            }
            for (int it = 0; it < lin.size(); it++) {
                if (retorno[it] == null) {
                    Vlr vlr = lin.get(it);
                    if (vlr != null) {
                        retorno[it] = vlr.getClass().getCanonicalName();
                    }
                }
            }
            boolean terminou = true;
            for (String ret : retorno) {
                if (ret == null) {
                    terminou = false;
                    break;
                }
            }
            if (terminou) {
                break;
            }
        }
        return retorno;
    }

    public List<Object[]> pegaTab() {
        List<Object[]> retorno = new ArrayList<>();
        for (Vlrs linha : this) {
            retorno.add(linha.toArray());
        }
        return retorno;
    }

    public void imprimi() {
        Utl.imp(toString());
    }

    @Override
    public String toString() {
        String retorno = "Conjunto: ";
        if (vazio()) {
            retorno += "vazio.";
        } else {
            if (nomes != null) {
                retorno += UtlTex.quebra() + "Nomes: " + UtlCrs.soma(nomes, ", ");
            }
        }
        for (Vlrs vlrs : this) {
            retorno += UtlTex.quebra() + vlrs.toString();
        }
        return retorno;
    }

    public String descreve() {
        String[] nomes = pegaNomes();
        String retorno = Marcados.prepara(Partes.junta(nomes), "nms");
        String[] tipos = pegaTipos();
        retorno += Marcados.prepara(Partes.junta(tipos), "tps");
        for (Vlrs linha : this) {
            String cols = "";
            for (int ic = 0; ic < linha.tamanho(); ic++) {
                Vlr colVlr = linha.pgVlr(ic);
                if (!colVlr.vazio()) {
                    String colCls = colVlr.getClass().getCanonicalName();
                    String colFnt = UtlBin.descreveFonte(colVlr);
                    boolean tipoDifere = false;
                    if (ic > tipos.length - 1) {
                        tipoDifere = true;
                    } else if (!Objects.equals(tipos[ic], colCls)) {
                        tipoDifere = true;
                    }
                    if (tipoDifere) {
                        colFnt = Marcados.prepara(colCls, "classe") + colFnt;
                    }
                    cols = UtlCrs.soma(cols, Partes.separador, colFnt);
                } else {
                    cols = UtlCrs.soma(cols, Partes.separador, Marcados.prepara("null", "fonte"));
                }
            }
            String lin = Marcados.prepara(cols, "lin");
            retorno += lin;
        }
        retorno = Marcados.prepara(retorno, "conjunto");
        return retorno;
    }
}
