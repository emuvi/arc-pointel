package pin.libutl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import pin.libbas.Globais;
import pin.libbas.PegaCorrente;
import pin.libbas.Retornos;
import pin.libpro.Operador;

public class UtlPro {

    public static Boolean comando(String oComando) throws Exception {
        return comando(oComando, true, null);
    }

    public static Boolean comando(String oComando, Retornos comRetornos) throws Exception {
        return comando(oComando, true, comRetornos);
    }

    public static Boolean comando(String oComando, Boolean esperar) throws Exception {
        return comando(oComando, esperar, null);
    }

    public static Boolean comando(String oComando, Boolean esperar, Retornos comRetornos) throws Exception {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(oComando);
            if (comRetornos != null) {
                PegaCorrente errorGobbler = new PegaCorrente(proc.getErrorStream(), comRetornos, "Erro");
                PegaCorrente outputGobbler = new PegaCorrente(proc.getInputStream(), comRetornos, "Comando");
                errorGobbler.start();
                outputGobbler.start();
            }
            if (esperar) {
                proc.waitFor();
            }
            return true;
        } catch (Exception ex) {
            Retornos.bota(comRetornos, ex);
            throw ex;
        }
    }

    public static Boolean comando(String[] osComandos) throws Exception {
        return comando(osComandos, true, null);
    }

    public static Boolean comando(String[] osComandos, Retornos comRetornos) throws Exception {
        return comando(osComandos, true, comRetornos);
    }

    public static Boolean comando(String[] osComandos, Boolean esperar) throws Exception {
        return comando(osComandos, esperar, null);
    }

    public static Boolean comando(String[] osComandos, Boolean esperar, Retornos comRetornos) throws Exception {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(osComandos);
            if (comRetornos != null) {
                PegaCorrente errorGobbler = new PegaCorrente(proc.getErrorStream(), comRetornos, "Erro");
                PegaCorrente outputGobbler = new PegaCorrente(proc.getInputStream(), comRetornos, "Comando");
                errorGobbler.start();
                outputGobbler.start();
            }
            if (esperar) {
                proc.waitFor();
            }
            return true;
        } catch (Exception ex) {
            Retornos.bota(comRetornos, ex);
            throw ex;
        }
    }

    public static Object roteiro(String doCodigo) throws Exception {
        return roteiro(doCodigo, null, null);
    }

    public static Object roteiro(String doCodigo, Retornos comRetornos) throws Exception {
        return roteiro(doCodigo, comRetornos, null);
    }

    public static Object roteiro(String doCodigo, Object eOrigem) throws Exception {
        return roteiro(doCodigo, null, eOrigem);
    }

    public static Object roteiro(String doCodigo, Retornos comRetornos, Object eOrigem) throws Exception {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            doCodigo = UtlCrs.troca(doCodigo, "pck.", "Packages.");
            engine.put("mainCons", Globais.pega("mainCons"));
            engine.put("mainConf", Globais.pega("mainConf"));
            engine.put("mainConc", Globais.pega("mainConc"));
            engine.put("mainMesa", Globais.pega("mainMesa"));
            engine.put("mainIntel", Globais.pega("mainIntel"));
            engine.put("mainJarbs", Globais.pega("mainJarbs"));
            engine.put("mainSupr", Globais.pega("mainSupr"));
            engine.put("mainServ", Globais.pega("mainServ"));
            engine.put("origem", eOrigem);
            Object retorno = engine.eval(doCodigo);
            Retornos.bota(comRetornos, "Roteiro", retorno);
            return retorno;
        } catch (Exception ex) {
            Retornos.bota(comRetornos, ex);
            throw ex;
        }
    }

    public static Object trata(String comCodigo, Object oValor) throws Exception {
        if (comCodigo == null) {
            return oValor;
        } else if (comCodigo.isEmpty()) {
            return oValor;
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        comCodigo = UtlCrs.troca(comCodigo, "pck.", "Packages.");
        engine.put("valor", oValor);
        return engine.eval(comCodigo);
    }

    public static Object pVar(String comNome, Map<String, Object> dasVariaveis) {
        return pVar(comNome, dasVariaveis, null);
    }

    public static Object pVar(String comNome, Map<String, Object> dasVariaveis, Object comPadrao) {
        if (dasVariaveis != null) {
            return dasVariaveis.getOrDefault(comNome, comPadrao);
        }
        return comPadrao;
    }

    public static Object pSinal(String dosCaracteres, Map<String, Object> comVariaveis) throws Exception {
        return pSinal(dosCaracteres, comVariaveis, null);
    }

    public static Object pSinal(String dosCaracteres, Map<String, Object> comVariaveis, Object ePadrao) throws Exception {
        if (dosCaracteres != null) {
            if (!dosCaracteres.isEmpty()) {
                if (UtlLis.tem(dosCaracteres.toLowerCase(), new String[]{"true", "t", "sim", "s"})) {
                    return true;
                } else if (UtlLis.tem(dosCaracteres.toLowerCase(), new String[]{"false", "f", "não", "n"})) {
                    return false;
                } else if (dosCaracteres.toLowerCase().equals("null")) {
                    return null;
                } else if (dosCaracteres.startsWith("\"")) {
                    if (dosCaracteres.endsWith("\"")) {
                        return dosCaracteres.substring(1, dosCaracteres.length() - 1);
                    } else {
                        return dosCaracteres.substring(1, dosCaracteres.length());
                    }
                } else if (dosCaracteres.startsWith("$")) {
                    return pVar(dosCaracteres.substring(1, dosCaracteres.length()), comVariaveis, ePadrao);
                } else {
                    if (UtlLis.tem(dosCaracteres, Operador.Tp.pSinais())) {
                        return new Operador(dosCaracteres);
                    } else if (dosCaracteres.endsWith("d")) {
                        dosCaracteres = dosCaracteres.substring(0, dosCaracteres.length() - 1);
                        dosCaracteres = UtlTem.ajustaDigitado(dosCaracteres, UtlMom.formatoMaquina);
                        return new SimpleDateFormat(UtlMom.formatoMaquina).parse(dosCaracteres);
                    } else if (dosCaracteres.endsWith("f") || dosCaracteres.contains(".")) {
                        if (dosCaracteres.endsWith("f")) {
                            dosCaracteres = dosCaracteres.substring(0, dosCaracteres.length() - 1);
                            return new Float(dosCaracteres);
                        } else {
                            return new Double(dosCaracteres);
                        }

                    } else {
                        if (dosCaracteres.endsWith("l")) {
                            dosCaracteres = dosCaracteres.substring(0, dosCaracteres.length() - 1);
                            return new Long(dosCaracteres);
                        } else {
                            return new Integer(dosCaracteres);
                        }
                    }
                }
            }
        }
        return ePadrao;
    }

    public static Boolean confSinal(String oSinal) {
        try {
            Boolean retorno = true;
            if (oSinal != null) {
                if (!oSinal.isEmpty()) {
                    if (oSinal.startsWith("\"")) {
                        retorno = true;
                    } else if (oSinal.startsWith("$")) {
                        retorno = oSinal.length() > 1;
                    } else {
                        if (UtlLis.tem(oSinal.toLowerCase(), new String[]{"true", "t", "sim", "s", "false", "f", "não", "n", "null"})) {
                            retorno = true;
                        } else if (UtlLis.tem(oSinal, Operador.Tp.pSinais())) {
                            retorno = true;
                        } else if (oSinal.endsWith("d")) {
                            oSinal = oSinal.substring(0, oSinal.length() - 1);
                            oSinal = UtlTem.ajustaDigitado(oSinal, UtlMom.formatoMaquina);
                            retorno = UtlTem.confDigitado(oSinal, UtlMom.formatoMaquina);
                        } else if (oSinal.endsWith("f") || oSinal.contains(".")) {
                            if (oSinal.endsWith("f")) {
                                oSinal = oSinal.substring(0, oSinal.length() - 1);
                            }
                            retorno = UtlCrs.ehSomenteNumericosComNegativos(oSinal.substring(0, 1));
                            if (oSinal.length() > 1) {
                                retorno = retorno && UtlCrs.ehSomenteNumericos(oSinal.substring(1));
                                retorno = retorno && (UtlLis.conta(',', oSinal.toCharArray()) <= 1);
                                retorno = retorno && (UtlLis.conta('.', oSinal.toCharArray()) <= 1);
                            }
                        } else {
                            if (oSinal.endsWith("l")) {
                                oSinal = oSinal.substring(0, oSinal.length() - 1);
                            }
                            retorno = UtlCrs.ehSomenteInteirosComNegativos(oSinal.substring(0, 1));
                            if (oSinal.length() > 1) {
                                retorno = retorno && UtlCrs.ehSomenteInteiros(oSinal.substring(1));
                            }
                        }
                    }
                }
            }
            return retorno;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Boolean conf(String aExpressao) {
        List<String> sinais = pSinais(aExpressao);
        for (String sinal : sinais) {
            if (!confSinal(sinal)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> pSinais(String daExpressao) {
        List<String> retorno = new ArrayList<>();
        String atual = "";
        boolean abriu = false;
        if (daExpressao != null) {
            int ic = 0;
            while (ic < daExpressao.length()) {
                char ch = daExpressao.charAt(ic);
                switch (ch) {
                    case '\\':
                        if (ic < daExpressao.length() - 1) {
                            char pr = daExpressao.charAt(ic + 1);
                            switch (pr) {
                                case 'n':
                                    atual += "\n";
                                    break;
                                case 'r':
                                    atual += "\r";
                                    break;
                                case 't':
                                    atual += "\t";
                                    break;
                                default:
                                    atual += pr;
                                    break;
                            }
                            ic++;
                        } else {
                            atual += ch;
                        }
                        break;
                    case '"':
                        abriu = !abriu;
                        atual += ch;
                        break;
                    case ' ':
                        if (abriu) {
                            atual += ch;
                        } else {
                            atual = atual.trim();
                            if (!atual.isEmpty()) {
                                retorno.add(atual);
                                atual = "";
                            }
                        }
                        break;
                    default:
                        atual += ch;
                        break;
                }
                ic++;
            }
        }
        atual = atual.trim();
        if (!atual.isEmpty()) {
            retorno.add(atual);
        }
        return retorno;
    }

    public static Object proc(String aExpressao) throws Exception {
        return proc(aExpressao, null);
    }

    public static Object proc(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        if (aExpressao == null) {
            return null;
        }
        Object retorno = null;
        String mVar = null;
        if (aExpressao.contains(" = ")) {
            int mPos = aExpressao.indexOf(" = ");
            mVar = aExpressao.substring(0, mPos);
            aExpressao = aExpressao.substring(mPos + 3);
            if (mVar.startsWith("$")) {
                mVar = mVar.substring(1);
            }
        }
        Operador operador = null;
        List<String> sinais = pSinais(aExpressao);
        for (String sinal : sinais) {
            Object atual = pSinal(sinal, comVariaveis);
            if (atual instanceof Operador) {
                operador = (Operador) atual;
            } else {
                if (operador == null) {
                    retorno = atual;
                } else {
                    retorno = operador.opera(retorno, atual);
                    operador = null;
                }
            }
        }
        if (mVar != null) {
            if (!mVar.isEmpty()) {
                if (comVariaveis != null) {
                    comVariaveis.put(mVar, retorno);
                }
            }
        }
        return retorno;
    }

    public static Boolean procLog(String aExpressao) throws Exception {
        return procLog(aExpressao, null);
    }

    public static Boolean procLog(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlLog.pega(proc(aExpressao, comVariaveis));
    }

    public static Character procCr(String aExpressao) throws Exception {
        return procCr(aExpressao, null);
    }

    public static Character procCr(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlCr.pega(proc(aExpressao, comVariaveis));
    }

    public static String procCrs(String aExpressao) throws Exception {
        return procCrs(aExpressao, null);
    }

    public static String procCrs(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlCrs.pega(proc(aExpressao, comVariaveis));
    }

    public static Integer procInt(String aExpressao) throws Exception {
        return procInt(aExpressao, null);
    }

    public static Integer procInt(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlInt.pega(proc(aExpressao, comVariaveis));
    }

    public static Long procIntLon(String aExpressao) throws Exception {
        return procIntLon(aExpressao, null);
    }

    public static Long procIntLon(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlIntLon.pega(proc(aExpressao, comVariaveis));
    }

    public static Float procNum(String aExpressao) throws Exception {
        return procNum(aExpressao, null);
    }

    public static Float procNum(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlNum.pega(proc(aExpressao, comVariaveis));
    }

    public static Double procNumLon(String aExpressao) throws Exception {
        return procNumLon(aExpressao, null);
    }

    public static Double procNumLon(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlNumLon.pega(proc(aExpressao, comVariaveis));
    }

    public static java.util.Date procDat(String aExpressao) throws Exception {
        return procDat(aExpressao, null);
    }

    public static java.util.Date procDat(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlDat.pega(proc(aExpressao, comVariaveis));
    }

    public static java.util.Date procHor(String aExpressao) throws Exception {
        return procHor(aExpressao, null);
    }

    public static java.util.Date procHor(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlHor.pega(proc(aExpressao, comVariaveis));
    }

    public static java.util.Date procDatHor(String aExpressao) throws Exception {
        return procDatHor(aExpressao, null);
    }

    public static java.util.Date procDatHor(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlDatHor.pega(proc(aExpressao, comVariaveis));
    }

    public static java.util.Date procMom(String aExpressao) throws Exception {
        return procMom(aExpressao, null);
    }

    public static java.util.Date procMom(String aExpressao, Map<String, Object> comVariaveis) throws Exception {
        return UtlMom.pega(proc(aExpressao, comVariaveis));
    }
}
