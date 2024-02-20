package pin.libpro;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import pin.libutl.UtlCrs;
import pin.libutl.UtlInt;
import pin.libutl.UtlIntLon;
import pin.libutl.UtlLog;
import pin.libutl.UtlMom;
import pin.libutl.UtlNum;
import pin.libutl.UtlNumLon;

public class Operador {

    public static enum Tp {
        Somar("+"), Diminuir("-"), Multiplicar("*"), Dividir("/"), RestoDividir("%"),
        Igual("=="), Diferente("!="), Maior(">"), Menor("<"), MaiorIgual(">="), MenorIgual("<="),
        LogE("&&"), LogOu("||"), LogENao("&!"), LogOuNao("|!");

        private String sinal;

        private Tp(String sinal) {
            this.sinal = sinal;
        }

        public String pSinal() {
            return sinal;
        }

        public static Tp pega(String doSinal) {
            for (Tp tp : Tp.values()) {
                if (Objects.equals(tp.pSinal(), doSinal)) {
                    return tp;
                }
            }
            return null;
        }

        public static String[] pSinais() {
            return new String[]{
                "+", "-", "*", "/", "%",
                "==", "!=", ">", "<", ">=", "<=",
                "&&", "||", "&!", "|!"
            };
        }

    }

    private Tp tipo;

    public Operador(Tp tipo) {
        this.tipo = tipo;
    }

    public Operador(String doSinal) {
        this.tipo = Tp.pega(doSinal);
    }

    public Tp pTipo() {
        return tipo;
    }

    public Operador mTipo(Tp tipo) {
        this.tipo = tipo;
        return this;
    }

    public Object opera(Object oValor1, Object comValor2) throws Exception {
        if (null == tipo) {
            throw new NullPointerException("Tipo do Operador Não Pode Ser Nulo");
        } else {
            switch (tipo) {
                case Igual:
                    return (Objects.equals(oValor1, comValor2));
                case Diferente:
                    return (!Objects.equals(oValor1, comValor2));
                case LogE:
                    return UtlLog.pega(oValor1) && UtlLog.pega(comValor2);
                case LogOu:
                    return UtlLog.pega(oValor1) || UtlLog.pega(comValor2);
                case LogENao:
                    return UtlLog.pega(oValor1) && !UtlLog.pega(comValor2);
                case LogOuNao:
                    return UtlLog.pega(oValor1) || !UtlLog.pega(comValor2);
            }
        }
        if (oValor1 == null) {
            return comValor2;
        } else if (comValor2 == null) {
            return oValor1;
        } else {
            if (UtlCrs.eh(oValor1) || UtlCrs.eh(comValor2)) {
                String val1 = UtlCrs.pega(oValor1);
                String val2 = UtlCrs.pega(comValor2);
                switch (tipo) {
                    case Somar:
                        return val1 + val2;
                    case Diminuir:
                        return UtlCrs.troca(val1, val2, "");
                    case Multiplicar:
                        return UtlCrs.repete(val1, UtlInt.pega(comValor2));
                    case Dividir:
                        return val1.split(val2);
                    case RestoDividir:
                        return val1.split(val2).length;
                    case Maior:
                        return val1.compareTo(val2) > 0;
                    case Menor:
                        return val1.compareTo(val2) < 0;
                    case MaiorIgual:
                        return val1.compareTo(val2) >= 0;
                    case MenorIgual:
                        return val1.compareTo(val2) <= 0;
                }
            } else if (UtlMom.eh(oValor1) || UtlMom.eh(comValor2)) {
                Date val1;
                Object val2;
                if (UtlMom.eh(oValor1)) {
                    val1 = UtlMom.pega(oValor1);
                    val2 = comValor2;
                } else {
                    val1 = UtlMom.pega(comValor2);
                    val2 = oValor1;
                }
                switch (tipo) {
                    case Somar:
                        if (val2 instanceof Date) {
                            return new Date(val1.getTime() + ((Date) val2).getTime());
                        } else if (val2 instanceof Long || val2 instanceof Double) {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.MILLISECOND, UtlInt.pega(val2));
                            return cl.getTime();
                        } else {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.DATE, UtlInt.pega(val2));
                            return cl.getTime();
                        }
                    case Diminuir:
                        if (val2 instanceof Date) {
                            return new Date(val1.getTime() - ((Date) val2).getTime());
                        } else if (val2 instanceof Long || val2 instanceof Double) {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.MILLISECOND, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        } else {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.DATE, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        }
                    case Multiplicar:
                        if (val2 instanceof Date) {
                            return new Date(val1.getTime() * ((Date) val2).getTime());
                        } else if (val2 instanceof Long || val2 instanceof Double) {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.MILLISECOND, UtlInt.pega(val2));
                            return cl.getTime();
                        } else {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.DATE, UtlInt.pega(val2));
                            return cl.getTime();
                        }
                    case Dividir:
                        if (val2 instanceof Date) {
                            return new Date(val1.getTime() / ((Date) val2).getTime());
                        } else if (val2 instanceof Long || val2 instanceof Double) {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.MILLISECOND, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        } else {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.DATE, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        }
                    case RestoDividir:
                        if (val2 instanceof Date) {
                            return new Date(val1.getTime() % ((Date) val2).getTime());
                        } else if (val2 instanceof Long || val2 instanceof Double) {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.MILLISECOND, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        } else {
                            Calendar cl = Calendar.getInstance();
                            cl.setTime(val1);
                            cl.add(Calendar.DATE, -1 * UtlInt.pega(val2));
                            return cl.getTime();
                        }
                    case Maior:
                        if (val2 instanceof Date) {
                            return val1.getTime() > ((Date) val2).getTime();
                        } else {
                            return val1.getTime() > UtlNumLon.pega(val2);
                        }
                    case Menor:
                        if (val2 instanceof Date) {
                            return val1.getTime() > ((Date) val2).getTime();
                        } else {
                            return val1.getTime() > UtlNumLon.pega(val2);
                        }
                    case MaiorIgual:
                        if (val2 instanceof Date) {
                            return val1.getTime() >= ((Date) val2).getTime();
                        } else {
                            return val1.getTime() >= UtlNumLon.pega(val2);
                        }
                    case MenorIgual:
                        if (val2 instanceof Date) {
                            return val1.getTime() <= ((Date) val2).getTime();
                        } else {
                            return val1.getTime() <= UtlNumLon.pega(val2);
                        }
                }
            } else if (UtlNumLon.eh(oValor1) || UtlNumLon.eh(comValor2)) {
                switch (tipo) {
                    case Somar:
                        return UtlNumLon.pega(oValor1) + UtlNumLon.pega(comValor2);
                    case Diminuir:
                        return UtlNumLon.pega(oValor1) - UtlNumLon.pega(comValor2);
                    case Multiplicar:
                        return UtlNumLon.pega(oValor1) * UtlNumLon.pega(comValor2);
                    case Dividir:
                        return UtlNumLon.pega(oValor1) / UtlNumLon.pega(comValor2);
                    case RestoDividir:
                        return UtlNumLon.pega(oValor1) % UtlNumLon.pega(comValor2);
                    case Maior:
                        return UtlNumLon.pega(oValor1) > UtlNumLon.pega(comValor2);
                    case Menor:
                        return UtlNumLon.pega(oValor1) < UtlNumLon.pega(comValor2);
                    case MaiorIgual:
                        return UtlNumLon.pega(oValor1) >= UtlNumLon.pega(comValor2);
                    case MenorIgual:
                        return UtlNumLon.pega(oValor1) <= UtlNumLon.pega(comValor2);
                }
            } else if (UtlNum.eh(oValor1) || UtlNum.eh(comValor2)) {
                switch (tipo) {
                    case Somar:
                        return UtlNum.pega(oValor1) + UtlNum.pega(comValor2);
                    case Diminuir:
                        return UtlNum.pega(oValor1) - UtlNum.pega(comValor2);
                    case Multiplicar:
                        return UtlNum.pega(oValor1) * UtlNum.pega(comValor2);
                    case Dividir:
                        return UtlNum.pega(oValor1) / UtlNum.pega(comValor2);
                    case RestoDividir:
                        return UtlNum.pega(oValor1) % UtlNum.pega(comValor2);
                    case Maior:
                        return UtlNum.pega(oValor1) > UtlNum.pega(comValor2);
                    case Menor:
                        return UtlNum.pega(oValor1) < UtlNum.pega(comValor2);
                    case MaiorIgual:
                        return UtlNum.pega(oValor1) >= UtlNum.pega(comValor2);
                    case MenorIgual:
                        return UtlNum.pega(oValor1) <= UtlNum.pega(comValor2);
                }
            } else if (UtlIntLon.eh(oValor1) || UtlIntLon.eh(comValor2)) {
                switch (tipo) {
                    case Somar:
                        return UtlIntLon.pega(oValor1) + UtlIntLon.pega(comValor2);
                    case Diminuir:
                        return UtlIntLon.pega(oValor1) - UtlIntLon.pega(comValor2);
                    case Multiplicar:
                        return UtlIntLon.pega(oValor1) * UtlIntLon.pega(comValor2);
                    case Dividir:
                        return UtlIntLon.pega(oValor1) / UtlIntLon.pega(comValor2);
                    case RestoDividir:
                        return UtlIntLon.pega(oValor1) % UtlIntLon.pega(comValor2);
                    case Maior:
                        return UtlIntLon.pega(oValor1) > UtlIntLon.pega(comValor2);
                    case Menor:
                        return UtlIntLon.pega(oValor1) < UtlIntLon.pega(comValor2);
                    case MaiorIgual:
                        return UtlIntLon.pega(oValor1) >= UtlIntLon.pega(comValor2);
                    case MenorIgual:
                        return UtlIntLon.pega(oValor1) <= UtlIntLon.pega(comValor2);
                }
            } else if (UtlInt.eh(oValor1) || UtlInt.eh(comValor2)) {
                switch (tipo) {
                    case Somar:
                        return UtlInt.pega(oValor1) + UtlInt.pega(comValor2);
                    case Diminuir:
                        return UtlInt.pega(oValor1) - UtlInt.pega(comValor2);
                    case Multiplicar:
                        return UtlInt.pega(oValor1) * UtlInt.pega(comValor2);
                    case Dividir:
                        return UtlInt.pega(oValor1) / UtlInt.pega(comValor2);
                    case RestoDividir:
                        return UtlInt.pega(oValor1) % UtlInt.pega(comValor2);
                    case Maior:
                        return UtlInt.pega(oValor1) > UtlInt.pega(comValor2);
                    case Menor:
                        return UtlInt.pega(oValor1) < UtlInt.pega(comValor2);
                    case MaiorIgual:
                        return UtlInt.pega(oValor1) >= UtlInt.pega(comValor2);
                    case MenorIgual:
                        return UtlInt.pega(oValor1) <= UtlInt.pega(comValor2);
                }
            } else if (UtlLog.eh(oValor1) || UtlLog.eh(comValor2)) {
                switch (tipo) {
                    case Somar:
                        return UtlLog.pega(oValor1) || UtlLog.pega(comValor2);
                    case Diminuir:
                        return UtlLog.pega(oValor1) && UtlLog.pega(comValor2);
                    case Multiplicar:
                        return UtlLog.pega(oValor1) || !UtlLog.pega(comValor2);
                    case Dividir:
                        return UtlLog.pega(oValor1) && !UtlLog.pega(comValor2);
                    case RestoDividir:
                        return !UtlLog.pega(oValor1) && UtlLog.pega(comValor2);
                    case Maior:
                        return UtlLog.pega(oValor1) || !UtlLog.pega(comValor2);
                    case Menor:
                        return !UtlLog.pega(oValor1) || UtlLog.pega(comValor2);
                    case MaiorIgual:
                        return !UtlLog.pega(oValor1) && !UtlLog.pega(comValor2);
                    case MenorIgual:
                        return !UtlLog.pega(oValor1) || !UtlLog.pega(comValor2);
                }
            }
        }
        return null;
    }

}
