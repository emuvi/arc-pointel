package pin.libutl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import pin.libbas.Erro;
import pin.libbas.Globais;
import pin.libbas.ParsParams;
import pin.libbas.Partes;
import pin.libbas.Retornos;
import pin.libitr.Alerta;
import pin.libitr.Mensagem;
import pin.libitr.Mostrador;
import pin.libitr.Ver;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libpro.Operador;
import pin.libvlr.Vlr;

public class Utl {

    public static enum Tp {
        Ind, Log, Cr, Crs, Int, IntLon, Num, NumLon, Dat, Hor, DatHor, Mom, Lis, Arq;
    };

    public static Tp pegaTipo(Class daClasse) {
        Tp retorno = Tp.Ind;
        if (UtlLog.eh(daClasse)) {
            retorno = Tp.Log;
        } else if (UtlCr.eh(daClasse)) {
            retorno = Tp.Cr;
        } else if (UtlCrs.eh(daClasse)) {
            retorno = Tp.Crs;
        } else if (UtlInt.eh(daClasse)) {
            retorno = Tp.Int;
        } else if (UtlIntLon.eh(daClasse)) {
            retorno = Tp.IntLon;
        } else if (UtlNum.eh(daClasse)) {
            retorno = Tp.Num;
        } else if (UtlNumLon.eh(daClasse)) {
            retorno = Tp.NumLon;
        } else if (UtlMom.eh(daClasse)) {
            retorno = Tp.Mom;
        } else if (UtlDatHor.eh(daClasse)) {
            retorno = Tp.DatHor;
        } else if (UtlDat.eh(daClasse)) {
            retorno = Tp.Dat;
        } else if (UtlHor.eh(daClasse)) {
            retorno = Tp.Hor;
        } else if (UtlLis.eh(daClasse)) {
            retorno = Tp.Lis;
        } else if (UtlArq.eh(daClasse)) {
            retorno = Tp.Arq;
        }
        return retorno;
    }

    public static Tp pegaTipo(Object doValor) {
        Tp retorno = Tp.Ind;
        if (UtlLog.eh(doValor)) {
            retorno = Tp.Log;
        } else if (UtlCr.eh(doValor)) {
            retorno = Tp.Cr;
        } else if (UtlCrs.eh(doValor)) {
            retorno = Tp.Crs;
        } else if (UtlInt.eh(doValor)) {
            retorno = Tp.Int;
        } else if (UtlIntLon.eh(doValor)) {
            retorno = Tp.IntLon;
        } else if (UtlNum.eh(doValor)) {
            retorno = Tp.Num;
        } else if (UtlNumLon.eh(doValor)) {
            retorno = Tp.NumLon;
        } else if (UtlMom.eh(doValor)) {
            retorno = Tp.Mom;
        } else if (UtlDatHor.eh(doValor)) {
            retorno = Tp.DatHor;
        } else if (UtlDat.eh(doValor)) {
            retorno = Tp.Dat;
        } else if (UtlHor.eh(doValor)) {
            retorno = Tp.Hor;
        } else if (UtlLis.eh(doValor)) {
            retorno = Tp.Lis;
        } else if (UtlArq.eh(doValor)) {
            retorno = Tp.Arq;
        }
        return retorno;
    }

    public static Object pega(Object eValor) {
        return pega(pegaTipo(eValor), eValor);
    }

    public static Object pega(Tp doTipo, Object eValor) {
        Object retorno = eValor;
        if (Tp.Log.equals(doTipo)) {
            retorno = UtlLog.pega(eValor);
        } else if (Tp.Cr.equals(doTipo)) {
            retorno = UtlCr.pega(eValor);
        } else if (Tp.Crs.equals(doTipo)) {
            retorno = UtlCrs.pega(eValor);
        } else if (Tp.Int.equals(doTipo)) {
            retorno = UtlInt.pega(eValor);
        } else if (Tp.IntLon.equals(doTipo)) {
            retorno = UtlIntLon.pega(eValor);
        } else if (Tp.Num.equals(doTipo)) {
            retorno = UtlNum.pega(eValor);
        } else if (Tp.NumLon.equals(doTipo)) {
            retorno = UtlNumLon.pega(eValor);
        } else if (Tp.Mom.equals(doTipo)) {
            retorno = UtlMom.pega(eValor);
        } else if (Tp.DatHor.equals(doTipo)) {
            retorno = UtlDatHor.pega(eValor);
        } else if (Tp.Dat.equals(doTipo)) {
            retorno = UtlDat.pega(eValor);
        } else if (Tp.Hor.equals(doTipo)) {
            retorno = UtlHor.pega(eValor);
        } else if (Tp.Lis.equals(doTipo)) {
            retorno = UtlLis.pega(eValor);
        } else if (Tp.Arq.equals(doTipo)) {
            retorno = UtlArq.pega(eValor);
        } else {
            if (eValor instanceof Vlr) {
                retorno = ((Vlr) eValor).pg();
            }
        }
        return retorno;
    }

    public static String pegaConsSQL(Object doValor) {
        return pegaConsSQL(doValor, null);
    }

    public static String pegaConsSQL(Object doValor, String comPadrao) {
        String retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Vlr) {
                retorno = ((Vlr) doValor).paraConsSQL();
            } else if (UtlLog.eh(doValor)) {
                retorno = UtlLog.formataSQL(UtlLog.pega(doValor), comPadrao);
            } else if (UtlCr.eh(doValor)) {
                retorno = UtlCr.formataSQL(UtlCr.pega(doValor), comPadrao);
            } else if (UtlCrs.eh(doValor)) {
                retorno = UtlCrs.formataSQL(UtlCrs.pega(doValor), comPadrao);
            } else if (UtlInt.eh(doValor)) {
                retorno = UtlInt.formataSQL(UtlInt.pega(doValor), comPadrao);
            } else if (UtlIntLon.eh(doValor)) {
                retorno = UtlIntLon.formataSQL(UtlIntLon.pega(doValor), comPadrao);
            } else if (UtlNum.eh(doValor)) {
                retorno = UtlNum.formataSQL(UtlNum.pega(doValor), comPadrao);
            } else if (UtlNumLon.eh(doValor)) {
                retorno = UtlNumLon.formataSQL(UtlNumLon.pega(doValor), comPadrao);
            } else if (UtlMom.eh(doValor)) {
                retorno = UtlMom.formataSQL(UtlDatHor.pega(doValor), comPadrao);
            } else if (UtlDatHor.eh(doValor)) {
                retorno = UtlDatHor.formataSQL(UtlDatHor.pega(doValor), comPadrao);
            } else if (UtlHor.eh(doValor)) {
                retorno = UtlHor.formataSQL(UtlHor.pega(doValor), comPadrao);
            } else if (UtlDat.eh(doValor)) {
                retorno = UtlDat.formataSQL(UtlDat.pega(doValor), comPadrao);
            } else if (UtlArq.eh(doValor)) {
                retorno = UtlArq.formataSQL(UtlArq.pega(doValor), comPadrao);
            } else {
                retorno = UtlCrs.formataSQL(UtlCrs.pega(doValor));
            }
        } else {
            if (comPadrao == null) {
                retorno = "IS NULL";
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public static Object pegaParamSQL(Object doValor) {
        return pegaParamSQL(doValor, null);
    }

    public static Object pegaParamSQL(Object doValor, Object comPadrao) {
        Object retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Vlr) {
                retorno = ((Vlr) doValor).paraParamSQL();
            } else if (UtlLog.eh(doValor)) {
                retorno = UtlLog.pegaSQL(doValor);
            } else if (UtlCr.eh(doValor)) {
                retorno = UtlCr.pegaSQL(doValor);
            } else if (UtlCrs.eh(doValor)) {
                retorno = UtlCrs.pegaSQL(doValor);
            } else if (UtlInt.eh(doValor)) {
                retorno = UtlInt.pegaSQL(doValor);
            } else if (UtlIntLon.eh(doValor)) {
                retorno = UtlIntLon.pegaSQL(doValor);
            } else if (UtlNum.eh(doValor)) {
                retorno = UtlNum.pegaSQL(doValor);
            } else if (UtlNumLon.eh(doValor)) {
                retorno = UtlNumLon.pegaSQL(doValor);
            } else if (UtlMom.eh(doValor)) {
                retorno = UtlMom.pegaSQL(UtlDatHor.pega(doValor));
            } else if (UtlDatHor.eh(doValor)) {
                retorno = UtlDatHor.pegaSQL(UtlDatHor.pega(doValor));
            } else if (UtlHor.eh(doValor)) {
                retorno = UtlHor.pegaSQL(UtlHor.pega(doValor));
            } else if (UtlDat.eh(doValor)) {
                retorno = UtlDat.pegaSQL(UtlDat.pega(doValor));
            } else if (UtlArq.eh(doValor)) {
                retorno = UtlArq.pegaSQL(doValor);
            } else {
                retorno = doValor.toString();
            }
        }
        if (retorno == null) {
            retorno = comPadrao;
        }
        return retorno;
    }

    public static Object soma(Object oValor1, Object comValor2) {
        return soma(oValor1, comValor2, null);
    }

    public static Object soma(Object oValor1, Object comValor2, Object comPadrao) {
        try {
            return new Operador(Operador.Tp.Somar).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Object diminui(Object oValor1, Object comValor2) {
        return diminui(oValor1, comValor2, null);
    }

    public static Object diminui(Object oValor1, Object comValor2, Object comPadrao) {
        try {
            return new Operador(Operador.Tp.Diminuir).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Object multiplica(Object oValor1, Object comValor2) {
        return multiplica(oValor1, comValor2, null);
    }

    public static Object multiplica(Object oValor1, Object comValor2, Object comPadrao) {
        try {
            return new Operador(Operador.Tp.Multiplicar).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Object divide(Object oValor1, Object comValor2) {
        return divide(oValor1, comValor2, null);
    }

    public static Object divide(Object oValor1, Object comValor2, Object comPadrao) {
        try {
            return new Operador(Operador.Tp.Dividir).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Object restoDivide(Object oValor1, Object comValor2) {
        return restoDivide(oValor1, comValor2, null);
    }

    public static Object restoDivide(Object oValor1, Object comValor2, Object comPadrao) {
        try {
            return new Operador(Operador.Tp.RestoDividir).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehIgual(Object oValor1, Object comValor2) {
        return ehIgual(oValor1, comValor2, null);
    }

    public static Boolean ehIgual(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.Igual).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehDiferente(Object oValor1, Object comValor2) {
        return ehDiferente(oValor1, comValor2, null);
    }

    public static Boolean ehDiferente(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.Diferente).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehMaior(Object oValor1, Object comValor2) {
        return ehMaior(oValor1, comValor2, null);
    }

    public static Boolean ehMaior(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.Maior).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehMaiorOuIgual(Object oValor1, Object comValor2) {
        return ehMaiorOuIgual(oValor1, comValor2, null);
    }

    public static Boolean ehMaiorOuIgual(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.MaiorIgual).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehMenor(Object oValor1, Object comValor2) {
        return ehMenor(oValor1, comValor2, null);
    }

    public static Boolean ehMenor(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.Menor).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean ehMenorOuIgual(Object oValor1, Object comValor2) {
        return ehMenorOuIgual(oValor1, comValor2, null);
    }

    public static Boolean ehMenorOuIgual(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.MenorIgual).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean logE(Object oValor1, Object comValor2) {
        return logE(oValor1, comValor2, null);
    }

    public static Boolean logE(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.LogE).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean logOu(Object oValor1, Object comValor2) {
        return logOu(oValor1, comValor2, null);
    }

    public static Boolean logOu(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.LogOu).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean logENao(Object oValor1, Object comValor2) {
        return logENao(oValor1, comValor2, null);
    }

    public static Boolean logENao(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.LogENao).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static Boolean logOuNao(Object oValor1, Object comValor2) {
        return logOuNao(oValor1, comValor2, null);
    }

    public static Boolean logOuNao(Object oValor1, Object comValor2, Boolean comPadrao) {
        try {
            return (Boolean) new Operador(Operador.Tp.LogOuNao).opera(oValor1, comValor2);
        } catch (Exception ex) {
            Utl.registra(ex, false);
        }
        return comPadrao;
    }

    public static void mensagem(Object oValor) {
        mensagem(UtlCrs.pega(oValor), null, true);
    }

    public static void mensagem(Object oValor, Boolean registra) {
        mensagem(UtlCrs.pega(oValor), null, registra);
    }

    public static void mensagem(Object oValor, String comTitulo) {
        mensagem(UtlCrs.pega(oValor), comTitulo, true);
    }

    public static void mensagem(Object oValor, String comTitulo, Boolean registra) {
        mensagem(UtlCrs.pega(oValor), comTitulo, registra);
    }

    public static void mensagem(String aMensagem) {
        mensagem(aMensagem, null, true);
    }

    public static void mensagem(String aMensagem, Boolean registra) {
        mensagem(aMensagem, null, registra);
    }

    public static void mensagem(String aMensagem, String comTitulo) {
        mensagem(aMensagem, comTitulo, true);
    }

    public static void mensagem(String aMensagem, String comTitulo, Boolean registra) {
        new Mensagem(aMensagem, comTitulo).mostra();
        if (registra) {
            registra(aMensagem, comTitulo, false);
        }
    }

    public static void alerta(Object oValor) {
        alerta(UtlCrs.pega(oValor), null, true);
    }

    public static void alerta(Object oValor, Boolean registra) {
        alerta(UtlCrs.pega(oValor), null, registra);
    }

    public static void alerta(Object oValor, String comTitulo) {
        alerta(UtlCrs.pega(oValor), comTitulo, true);
    }

    public static void alerta(Object oValor, String comTitulo, Boolean registra) {
        alerta(UtlCrs.pega(oValor), comTitulo, registra);
    }

    public static void alerta(String aMensagem) {
        alerta(aMensagem, null, true);
    }

    public static void alerta(String aMensagem, Boolean registra) {
        alerta(aMensagem, null, registra);
    }

    public static void alerta(String aMensagem, String comTitulo) {
        alerta(aMensagem, comTitulo, true);
    }

    public static void alerta(String aMensagem, String comTitulo, Boolean registra) {
        new Alerta(aMensagem, comTitulo).mostra();
        if (registra) {
            registra(aMensagem, comTitulo, false);
        }
    }

    public static Boolean continua() {
        return continua("Deseja Continuar?", "Pointel Confirmação");
    }

    public static Boolean continua(String comMensagem) {
        return continua(comMensagem, "Pointel Confirmação");
    }

    public static Boolean continua(String comMensagem, String eTitulo) {
        if (!comMensagem.contains("Deseja Continuar?")) {
            comMensagem = comMensagem + UtlTex.quebra(2) + "Deseja Continuar?";
        }
        return new Alerta(comMensagem, eTitulo, true).confirmado();
    }

    public static void registra(Throwable lancada) {
        registra(lancada, null, null, null, true, true, null, null, null, null);
    }

    public static void registra(Throwable lancada, Ver ver) {
        registra(lancada, null, null, null, true, true, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, null, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, null, null, ver);
    }

    public static void registra(Throwable lancada, Mostrador mostrador) {
        registra(lancada, null, null, null, true, true, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, true, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, List<String> agrupador) {
        registra(lancada, null, null, null, true, true, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, List<String> agrupador, Ver ver) {
        registra(lancada, null, null, null, true, true, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, List<String> agrupador) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, List<String> agrupador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, null, null, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento) {
        registra(lancada, comComplemento, null, null, true, true, null, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Ver ver) {
        registra(lancada, comComplemento, null, null, true, true, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, true, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, true, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, List<String> agrupador) {
        registra(lancada, comComplemento, null, null, true, true, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, true, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, List<String> agrupador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar) {
        registra(lancada, null, null, null, true, alertar, null, null, null, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Ver ver) {
        registra(lancada, null, null, null, true, alertar, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, null, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, null, null, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, Mostrador mostrador) {
        registra(lancada, null, null, null, true, alertar, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, List<String> agrupador) {
        registra(lancada, null, null, null, true, alertar, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, null, null, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar) {
        registra(lancada, comComplemento, null, null, true, alertar, null, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, alertar, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, List<String> agrupador) {
        registra(lancada, comComplemento, null, null, true, alertar, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos) {
        registra(lancada, null, null, null, true, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, null, null, null, true, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, null, null, null, true, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, null, null, null, true, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, null, null, true, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, true, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, true, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, true, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, true, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, null, null, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(lancada, comComplemento, null, null, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(lancada, comComplemento, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(Throwable lancada, String comComplemento, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        Erro erro;
        if (lancada instanceof Erro) {
            erro = (Erro) lancada;
        } else {
            erro = new Erro(lancada);
        }
        String erroMsg = erro.getMessage();
        if (comComplemento != null) {
            erroMsg += UtlTex.quebra() + comComplemento;
        }
        if (imprimir) {
            Utl.imp(erroMsg);
        }
        erroMsg += UtlTex.quebra(2);
        String origem = erro.pOrigem();
        if (alertar) {
            if (lancada instanceof Erro) {
                alertar = ((Erro) lancada).pAlertavel();
            }
        }
        if (alertar) {
            new Alerta(erroMsg, "Erro", origem).mostra();
        }
        if (varNomes != null) {
            String varsMsg = "Com a(s) Variável(is):";
            for (int i1 = 0; i1 < varNomes.length; i1++) {
                varsMsg += UtlTex.quebra() + "Nome: " + varNomes[i1];
                varsMsg += " Valor: " + UtlCrs.pega(varVlrs[i1]);
            }
            erroMsg += varsMsg + UtlTex.quebra(2);
        }
        erroMsg += origem + UtlTex.quebra(3);
        Retornos.bota(retornos, "Erro", erroMsg);
        if (agrupador != null) {
            agrupador.add(erroMsg);
        }
        if (mostrador != null) {
            mostrador.muda(erroMsg);
        }
        if (ver != null) {
            ver.bota(erroMsg);
        }
    }

    public static void registra(String comTitulo, String eDescricao) {
        registra(comTitulo, eDescricao, null, null, true, true, null, null, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, true, null, null, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, true, true, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, true, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, List<String> agrupador) {
        registra(comTitulo, eDescricao, null, null, true, true, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, List<String> agrupador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, true, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo) {
        registra(comTitulo, null, null, null, true, true, null, null, null, null);
    }

    public static void registra(String comTitulo, Ver ver) {
        registra(comTitulo, null, null, null, true, true, null, null, null, ver);
    }

    public static void registra(String comTitulo, Mostrador mostrador) {
        registra(comTitulo, null, null, null, true, true, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, true, true, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, List<String> agrupador) {
        registra(comTitulo, null, null, null, true, true, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, null, null, true, true, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, null, null, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, null, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, null, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, List<String> agrupador) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, true, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, null, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, null, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, List<String> agrupador) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, Boolean alertar) {
        registra(comTitulo, null, null, null, true, alertar, null, null, null, null);
    }

    public static void registra(String comTitulo, Boolean alertar, Ver ver) {
        registra(comTitulo, null, null, null, true, alertar, null, null, null, ver);
    }

    public static void registra(String comTitulo, Boolean alertar, Mostrador mostrador) {
        registra(comTitulo, null, null, null, true, alertar, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, Boolean alertar, List<String> agrupador) {
        registra(comTitulo, null, null, null, true, alertar, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, null, null, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, null, null, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, null, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, null, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, true, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, null, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, null, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, null, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, List<String> agrupador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, agrupador, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, List<String> agrupador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, null, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, null, null, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, null, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, null, null, imprimir, alertar, retornos, agrupador, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, null, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Ver ver) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, null, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, Mostrador mostrador, Ver ver) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, null, mostrador, ver);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, null);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Ver ver) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, null, ver);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador) {
        registra(comTitulo, eDescricao, varNomes, varVlrs, imprimir, alertar, retornos, agrupador, mostrador, null);
    }

    public static void registra(String comTitulo, String eDescricao, String[] varNomes, Object[] varVlrs, Boolean imprimir, Boolean alertar, Retornos retornos, List<String> agrupador, Mostrador mostrador, Ver ver) {
        String regMsg = comTitulo;
        if (eDescricao != null) {
            regMsg += UtlTex.quebra() + eDescricao;
        }
        if (imprimir) {
            Utl.imp(regMsg);
        }
        regMsg += UtlTex.quebra();
        String origem = UtlBin.pegaOrigem();
        if (alertar) {
            new Alerta(regMsg, "Mensagem", origem).mostra();
        }
        if (varNomes != null) {
            String varsMsg = "Com a(s) Variável(is):";
            for (int i1 = 0; i1 < varNomes.length; i1++) {
                varsMsg += UtlTex.quebra() + "Nome: " + varNomes[i1];
                varsMsg += " , Valor: '" + UtlCrs.pega(varVlrs[i1]) + "'";
            }
            regMsg += varsMsg + UtlTex.quebra(2);
        }
        regMsg += origem + UtlTex.quebra(3);
        Retornos.bota(retornos, "Registro", regMsg);
        if (agrupador != null) {
            agrupador.add(regMsg);
        }
        if (mostrador != null) {
            mostrador.muda(regMsg);
        }
        if (ver != null) {
            ver.bota(regMsg);
        }
    }

    public static void imp(Object oObjeto) {
        imp(true, oObjeto);
    }

    public static void imp(Boolean seCondicao, Object oObjeto) {
        if (seCondicao) {
            System.out.println(UtlCrs.pega(oObjeto));
        }
    }

    public static ParsParams<String, Ver> visoes = null;

    public static void verInicia() {
        if (visoes == null) {
            visoes = new ParsParams();
        }
    }

    public static void ver(Object oValor) {
        ver(true, "Pointel Ver", oValor, true, null, null);
    }

    public static void ver(Object oValor, Boolean registra) {
        ver(true, "Pointel Ver", oValor, registra, null, null);
    }

    public static void ver(String noTitulo, Object oValor) {
        ver(true, noTitulo, oValor, true, null, null);
    }

    public static void ver(Object oValor, List<String> agrupador) {
        ver(true, "Pointel Ver", oValor, true, agrupador, null);
    }

    public static void ver(Object oValor, Boolean registra, List<String> agrupador) {
        ver(true, "Pointel Ver", oValor, registra, agrupador, null);
    }

    public static void ver(String noTitulo, Object oValor, List<String> agrupador) {
        ver(true, noTitulo, oValor, true, agrupador, null);
    }

    public static void ver(Object oValor, Mostrador mostrador) {
        ver(true, "Pointel Ver", oValor, true, null, mostrador);
    }

    public static void ver(Object oValor, Boolean registra, Mostrador mostrador) {
        ver(true, "Pointel Ver", oValor, registra, null, mostrador);
    }

    public static void ver(String noTitulo, Object oValor, Mostrador mostrador) {
        ver(true, noTitulo, oValor, true, null, mostrador);
    }

    public static void ver(Object oValor, List<String> agrupador, Mostrador mostrador) {
        ver(true, "Pointel Ver", oValor, true, agrupador, mostrador);
    }

    public static void ver(Object oValor, Boolean registra, List<String> agrupador, Mostrador mostrador) {
        ver(true, "Pointel Ver", oValor, registra, agrupador, mostrador);
    }

    public static void ver(String noTitulo, Object oValor, List<String> agrupador, Mostrador mostrador) {
        ver(true, noTitulo, oValor, true, agrupador, mostrador);
    }

    public static void ver(String noTitulo, Object oValor, Boolean registra, List<String> agrupador, Mostrador mostrador) {
        ver(true, noTitulo, oValor, registra, agrupador, mostrador);
    }

    public static void ver(Boolean mostrar, Object oValor) {
        ver(mostrar, "Pointel Ver", oValor, true, null, null);
    }

    public static void ver(Boolean mostrar, Object oValor, Boolean registra) {
        ver(mostrar, "Pointel Ver", oValor, registra, null, null);
    }

    public static void ver(Boolean mostrar, String noTitulo, Object oValor) {
        ver(mostrar, noTitulo, oValor, true, null, null);
    }

    public static void ver(Boolean mostrar, Object oValor, List<String> agrupador) {
        ver(mostrar, "Pointel Ver", oValor, false, agrupador, null);
    }

    public static void ver(Boolean mostrar, Object oValor, Boolean registra, List<String> agrupador) {
        ver(mostrar, "Pointel Ver", oValor, registra, agrupador, null);
    }

    public static void ver(Boolean mostrar, String noTitulo, Object oValor, List<String> agrupador) {
        ver(mostrar, noTitulo, oValor, true, agrupador, null);
    }

    public static void ver(Boolean mostrar, Object oValor, Mostrador mostrador) {
        ver(mostrar, "Pointel Ver", oValor, true, null, mostrador);
    }

    public static void ver(Boolean mostrar, Object oValor, Boolean registra, Mostrador mostrador) {
        ver(mostrar, "Pointel Ver", oValor, registra, null, mostrador);
    }

    public static void ver(Boolean mostrar, String noTitulo, Object oValor, Mostrador mostrador) {
        ver(mostrar, noTitulo, oValor, true, null, mostrador);
    }

    public static void ver(Boolean mostrar, Object oValor, List<String> agrupador, Mostrador mostrador) {
        ver(mostrar, "Pointel Ver", oValor, true, agrupador, mostrador);
    }

    public static void ver(Boolean mostrar, Object oValor, Boolean registra, List<String> agrupador, Mostrador mostrador) {
        ver(mostrar, "Pointel Ver", oValor, registra, agrupador, mostrador);
    }

    public static void ver(Boolean mostrar, String noTitulo, Object oValor, List<String> agrupador, Mostrador mostrador) {
        ver(mostrar, noTitulo, oValor, true, agrupador, mostrador);
    }

    public static void ver(Boolean mostrar, String noTitulo, Object oValor, Boolean registra, List<String> agrupador, Mostrador mostrador) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                verInicia();
                String titulo = noTitulo;
                if (titulo == null) {
                    titulo = "Pointel Ver";
                }
                if (mostrar) {
                    Ver ver = visoes.pega(titulo);
                    if (ver == null) {
                        ver = new Ver(titulo);
                        visoes.bota(titulo, ver);
                    }
                    ver.bota(oValor);
                    if (!ver.isVisible()) {
                        Janelas.mostra(ver);
                    }
                }
                if (registra) {
                    registra(titulo, UtlCrs.pega(oValor), false);
                }
                if (agrupador != null) {
                    agrupador.add(Partes.junta(titulo, UtlBin.descreve(oValor)));
                }
                if (mostrador != null) {
                    mostrador.muda(oValor);
                }
            }
        });
    }

    public static class AlertaPoped {

        public JFrame janela;
        public JTextArea descricao;
        public JLabel icone;

        public AlertaPoped(JFrame janela, JTextArea descricao) {
            this.janela = janela;
            this.descricao = descricao;
            this.icone = null;
        }

        public AlertaPoped(JFrame janela, JLabel icone) {
            this.janela = janela;
            this.descricao = null;
            this.icone = icone;
        }
    }

    public static ParsParams<Integer, AlertaPoped> alertaPops = null;

    public static Boolean alertaPopEh(JFrame aJanela) {
        Boolean retorno = false;
        if (alertaPops != null) {
            for (Map.Entry<Integer, AlertaPoped> poped : alertaPops.lista()) {
                if (Objects.equals(aJanela, poped.getValue().janela)) {
                    retorno = true;
                    break;
                }
            }
        }
        return retorno;
    }

    public static Integer alertaPopNovoId() {
        Integer retorno = null;
        if (alertaPops == null) {
            alertaPops = new ParsParams();
        }
        do {
            retorno = UtlAle.pegaInt();
        } while (alertaPops.temChave(retorno));
        return retorno;
    }

    public static Integer alertaPop(String aMsg) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, aMsg, true, null);
        return retorno;
    }

    public static Integer alertaPop(String aMsg, Component paraComponente) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, aMsg, true, paraComponente);
        return retorno;
    }

    public static Integer alertaPop(String aMsg, Boolean eAutoFechar) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, aMsg, eAutoFechar, null);
        return retorno;
    }

    public static Integer alertaPop(String aMsg, Boolean eAutoFechar, Component paraComponente) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, aMsg, eAutoFechar, paraComponente);
        return retorno;
    }

    public static void alertaPop(Integer comId, String aMsg) {
        alertaPop(comId, aMsg, false, null);
    }

    public static void alertaPop(Integer comId, String aMsg, Component paraComponente) {
        alertaPop(comId, aMsg, false, paraComponente);
    }

    public static void alertaPop(Integer comId, String aMsg, Boolean eAutoFechar) {
        alertaPop(comId, aMsg, eAutoFechar, null);
    }

    public static void alertaPop(Integer comId, String aMsg, Boolean eAutoFechar, Component paraComponente) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (alertaPops == null) {
                    alertaPops = new ParsParams();
                }
                Integer iid = comId;
                if (iid == null) {
                    iid = alertaPopNovoId();
                }
                JFrame jfrJanela = new JFrame();
                jfrJanela.setAlwaysOnTop(true);
                jfrJanela.setFocusable(false);
                jfrJanela.setFocusableWindowState(false);
                jfrJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfrJanela.setResizable(false);
                jfrJanela.setUndecorated(true);
                ((JPanel) jfrJanela.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JTextArea jtaDescricao = new JTextArea();
                jtaDescricao.setFocusable(false);
                jtaDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);
                jtaDescricao.setLineWrap(true);
                jtaDescricao.setWrapStyleWord(true);
                jtaDescricao.setEditable(false);
                jtaDescricao.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                jtaDescricao.setText(aMsg);
                jtaDescricao.setSize(new Dimension(450, 30));
                Integer maior = 0;
                for (Object vl : alertaPops.values()) {
                    JFrame jnl = ((AlertaPoped) vl).janela;
                    if (jnl.isVisible()) {
                        if (jnl.getBounds().y + jnl.getBounds().height > maior) {
                            maior = jnl.getBounds().y + jnl.getBounds().height;
                        }
                    }
                }
                Point lc = new Point(45, 45);
                try {
                    if (paraComponente == null) {
                        Rectangle rscr = ((Rectangle) Globais.pega("mainArea"));
                        lc.x = rscr.width - (jtaDescricao.getWidth() + 30);
                        lc.y = maior + 10;
                    } else {
                        lc = paraComponente.getLocationOnScreen();
                    }
                } catch (Exception ex) {
                    Utl.registra(ex, false);
                }
                jfrJanela.setLocation(lc);
                jfrJanela.setLayout(new BorderLayout());
                jfrJanela.add(jtaDescricao, BorderLayout.CENTER);
                jfrJanela.pack();
                jfrJanela.setVisible(true);
                alertaPops.bota(iid, new AlertaPoped(jfrJanela, jtaDescricao));
                final Integer fId = iid;
                if (eAutoFechar) {
                    new Thread("Alertar Popup Auto Fechar") {
                        @Override
                        public void run() {
                            UtlBin.esperaMilis(7000);
                            jfrJanela.setVisible(false);
                            alertaPops.tira(fId);
                        }
                    }.start();
                }
            }
        });
    }

    public static void alertaPopMudar(Integer noId, String paraMsg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (alertaPops != null) {
                    try {
                        JFrame jfrJanela = ((AlertaPoped) alertaPops.pega(noId)).janela;
                        JTextArea jtaDescricao = ((AlertaPoped) alertaPops.pega(noId)).descricao;
                        jtaDescricao.setText(paraMsg);
                        jfrJanela.pack();
                        TrataIntf.piscaVisao(jfrJanela);
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }
        });
    }

    public static Integer alertaPop(ImageIcon oIcone) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, oIcone, true, null);
        return retorno;
    }

    public static Integer alertaPop(ImageIcon oIcone, Component paraComponente) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, oIcone, true, paraComponente);
        return retorno;
    }

    public static Integer alertaPop(ImageIcon oIcone, Boolean eAutoFechar) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, oIcone, eAutoFechar, null);
        return retorno;
    }

    public static Integer alertaPop(ImageIcon oIcone, Boolean eAutoFechar, Component paraComponente) {
        Integer retorno = alertaPopNovoId();
        alertaPop(retorno, oIcone, eAutoFechar, paraComponente);
        return retorno;
    }

    public static void alertaPop(Integer comId, ImageIcon oIcone) {
        alertaPop(comId, oIcone, false, null);
    }

    public static void alertaPop(Integer comId, ImageIcon oIcone, Component paraComponente) {
        alertaPop(comId, oIcone, false, paraComponente);
    }

    public static void alertaPop(Integer comId, ImageIcon oIcone, Boolean eAutoFechar) {
        alertaPop(comId, oIcone, eAutoFechar, null);
    }

    public static void alertaPop(Integer comId, ImageIcon oIcone, Boolean eAutoFechar, Component paraComponente) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (alertaPops == null) {
                    alertaPops = new ParsParams();
                }
                Integer iid = comId;
                if (iid == null) {
                    iid = alertaPopNovoId();
                }
                JFrame jfrJanela = new JFrame();
                jfrJanela.setAlwaysOnTop(true);
                jfrJanela.setFocusable(false);
                jfrJanela.setFocusableWindowState(false);
                jfrJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jfrJanela.setResizable(false);
                jfrJanela.setUndecorated(true);
                ((JPanel) jfrJanela.getContentPane()).setBorder(BorderFactory.createLineBorder(Color.black));
                JLabel jlbIcone = new JLabel();
                jlbIcone.setFocusable(false);
                jlbIcone.setAlignmentX(Component.LEFT_ALIGNMENT);
                jlbIcone.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
                jlbIcone.setIcon(oIcone);
                jlbIcone.setSize(jlbIcone.getPreferredSize());
                jlbIcone.setMaximumSize(jlbIcone.getPreferredSize());
                jlbIcone.setMinimumSize(jlbIcone.getPreferredSize());
                Integer maior = 0;
                for (Object vl : alertaPops.values()) {
                    JFrame jnl = ((AlertaPoped) vl).janela;
                    if (jnl.isVisible()) {
                        if (jnl.getBounds().y + jnl.getBounds().height > maior) {
                            maior = jnl.getBounds().y + jnl.getBounds().height;
                        }
                    }
                }
                Point lc = new Point(45, 45);
                try {
                    if (paraComponente == null) {
                        Rectangle rscr = ((Rectangle) Globais.pega("mainArea"));
                        lc.x = rscr.width - (jlbIcone.getWidth() + 30);
                        lc.y = maior + 10;
                    } else {
                        lc = paraComponente.getLocationOnScreen();
                    }
                } catch (Exception ex) {
                    Utl.registra(ex, false);
                }
                jfrJanela.setLocation(lc);
                jfrJanela.setLayout(new BorderLayout());
                jfrJanela.add(jlbIcone, BorderLayout.CENTER);
                jfrJanela.pack();
                jfrJanela.setVisible(true);
                alertaPops.bota(iid, new AlertaPoped(jfrJanela, jlbIcone));
                final Integer fId = iid;
                if (eAutoFechar) {
                    new Thread("Alertar Popup Auto Fechar") {
                        @Override
                        public void run() {
                            UtlBin.esperaMilis(5000);
                            jfrJanela.setVisible(false);
                            alertaPops.tira(fId);
                        }
                    }.start();
                }
            }
        });
    }

    public static void alertaPopMudar(Integer noId, ImageIcon oIcone) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (alertaPops != null) {
                    try {
                        JFrame jfrJanela = ((AlertaPoped) alertaPops.pega(noId)).janela;
                        JLabel jlbIcone = ((AlertaPoped) alertaPops.pega(noId)).icone;
                        jlbIcone.setIcon(oIcone);
                        TrataIntf.piscaVisao(jfrJanela);
                    } catch (Exception ex) {
                        Utl.registra(ex, false);
                    }
                }
            }
        });
    }

    public static void alertaPopFechar(Integer aId) {
        if (aId != null) {
            if (alertaPops != null) {
                if (alertaPops.temChave(aId)) {
                    JFrame jfrJanela = ((AlertaPoped) alertaPops.pega(aId)).janela;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            jfrJanela.setVisible(false);
                            alertaPops.tira(aId);
                        }
                    });
                }
            }
        }
    }

    public static void alertaPopAutoFechar(Integer aId) {
        new Thread("Alertar Popup Auto Fechar") {
            @Override
            public void run() {
                UtlBin.esperaMilis(5000);
                alertaPopFechar(aId);
            }
        }.start();
    }

}
