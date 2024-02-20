package pin.libbas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import pin.libutl.UtlCr;
import pin.libutl.UtlDat;
import pin.libutl.UtlDatHor;
import pin.libutl.UtlHor;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;

public class IniParams {

    public List<String> parametros;

    public IniParams(String[] comParametros) {
        parametros = Arrays.asList(comParametros);
    }

    public IniParams(String comParametros) {
        parametros = new ArrayList<>();
        if (comParametros != null) {
            boolean abriu = false;
            StringBuilder stb = new StringBuilder();
            for (int ic = 0; ic < comParametros.length(); ic++) {
                char ch = comParametros.charAt(ic);
                if (ch == ' ') {
                    if (!abriu) {
                        parametros.add(stb.toString());
                        stb = new StringBuilder();
                    } else {
                        stb.append(ch);
                    }
                } else if (ch == '"') {
                    if (ic == 0) {
                        abriu = !abriu;
                    } else {
                        char chAnt = comParametros.charAt(ic - 1);
                        if (chAnt == '\\') {
                            stb.append(ch);
                        } else {
                            abriu = !abriu;
                        }
                    }
                } else {
                    stb.append(ch);
                }
            }
            parametros.add(stb.toString());
        }
    }

    public Boolean contem(String oNome) {
        for (String par : parametros) {
            if (Objects.equals(oNome, par)) {
                return true;
            }
        }
        return false;
    }

    public Boolean pegaLog(String comNome) {
        return contem(comNome);
    }

    public Character pegaCr(String comNome) {
        return pegaCr(comNome, null);
    }

    public Character pegaCr(String comNome, Character ePadrao) {
        return UtlCr.pega(pegaCrs(comNome), ePadrao);
    }

    public String pegaCrs(String comNome) {
        return pegaCrs(comNome, null);
    }

    public String pegaCrs(String comNome, String ePadrao) {
        String retorno = ePadrao;
        for (int ip = 0; ip < parametros.size(); ip++) {
            if (Objects.equals(parametros.get(ip), comNome)) {
                if (ip < parametros.size() - 1) {
                    retorno = parametros.get(ip + 1);
                    break;
                }
            }
        }
        return retorno;
    }

    public Integer pegaInt(String comNome) {
        return pegaInt(comNome, null);
    }

    public Integer pegaInt(String comNome, Integer ePadrao) {
        return UtlInt.pega(pegaCrs(comNome), ePadrao);
    }

    public Long pegaIntLon(String comNome) {
        return pegaIntLon(comNome, null);
    }

    public Long pegaIntLon(String comNome, Long ePadrao) {
        return UtlIntLon.pega(pegaCrs(comNome), ePadrao);
    }

    public Float pegaNum(String comNome) {
        return pegaNum(comNome, null);
    }

    public Float pegaNum(String comNome, Float ePadrao) {
        return UtlNum.pega(pegaCrs(comNome), ePadrao);
    }

    public Double pegaNumLon(String comNome) {
        return pegaNumLon(comNome, null);
    }

    public Double pegaNumLon(String comNome, Double ePadrao) {
        return UtlNumLon.pega(pegaCrs(comNome), ePadrao);
    }

    public java.util.Date pegaDat(String comNome) {
        return pegaDat(comNome, null);
    }

    public java.util.Date pegaDat(String comNome, java.util.Date ePadrao) {
        return UtlDat.pega(pegaCrs(comNome), ePadrao);
    }

    public java.util.Date pegaHor(String comNome) {
        return pegaHor(comNome, null);
    }

    public java.util.Date pegaHor(String comNome, java.util.Date ePadrao) {
        return UtlHor.pega(pegaCrs(comNome), ePadrao);
    }

    public java.util.Date pegaDatHor(String comNome) {
        return pegaDatHor(comNome, null);
    }

    public java.util.Date pegaDatHor(String comNome, java.util.Date ePadrao) {
        return UtlDatHor.pega(pegaCrs(comNome), ePadrao);
    }

    public java.util.Date pegaMom(String comNome) {
        return pegaMom(comNome, null);
    }

    public java.util.Date pegaMom(String comNome, java.util.Date ePadrao) {
        return UtlMom.pega(pegaCrs(comNome), ePadrao);
    }

}
