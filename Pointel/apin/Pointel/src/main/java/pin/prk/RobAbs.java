package pin.prk;

import pin.libutl.UtlPro;

public abstract class RobAbs {

    private String nome;

    public String pNome() {
        return nome;
    }

    public RobAbs mNome(String nome) {
        this.nome = nome;
        return this;
    }

    private ServProcessarRoboticoExc robotico;

    public ServProcessarRoboticoExc pRobotico() {
        return robotico;
    }

    public RobAbs mRobotico(ServProcessarRoboticoExc robotico) {
        this.robotico = robotico;
        return this;
    }

    public abstract String executa() throws Exception;

    public Object proc(String aExpressao) throws Exception {
        return UtlPro.proc(aExpressao, pRobotico().pVariaveis());
    }

    public Boolean procLog(String aExpressao) throws Exception {
        return UtlPro.procLog(aExpressao, pRobotico().pVariaveis());
    }

    public Character procCr(String aExpressao) throws Exception {
        return UtlPro.procCr(aExpressao, pRobotico().pVariaveis());
    }

    public String procCrs(String aExpressao) throws Exception {
        return UtlPro.procCrs(aExpressao, pRobotico().pVariaveis());
    }

    public Integer procInt(String aExpressao) throws Exception {
        return UtlPro.procInt(aExpressao, pRobotico().pVariaveis());
    }

    public Long procIntLon(String aExpressao) throws Exception {
        return UtlPro.procIntLon(aExpressao, pRobotico().pVariaveis());
    }

    public Float procNum(String aExpressao) throws Exception {
        return UtlPro.procNum(aExpressao, pRobotico().pVariaveis());
    }

    public Double procNumLon(String aExpressao) throws Exception {
        return UtlPro.procNumLon(aExpressao, pRobotico().pVariaveis());
    }

    public java.util.Date procDat(String aExpressao) throws Exception {
        return UtlPro.procDat(aExpressao, pRobotico().pVariaveis());
    }

    public java.util.Date procHor(String aExpressao) throws Exception {
        return UtlPro.procHor(aExpressao, pRobotico().pVariaveis());
    }

    public java.util.Date procDatHor(String aExpressao) throws Exception {
        return UtlPro.procDatHor(aExpressao, pRobotico().pVariaveis());
    }

    public java.util.Date procMom(String aExpressao) throws Exception {
        return UtlPro.procMom(aExpressao, pRobotico().pVariaveis());
    }

}
