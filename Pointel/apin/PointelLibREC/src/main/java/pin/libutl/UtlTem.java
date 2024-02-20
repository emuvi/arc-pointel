package pin.libutl;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import pin.libvlr.Vlr;

public class UtlTem {

    public static Boolean eh(Object aValor) {
        Boolean retorno = false;
        if (aValor instanceof Date) {
            retorno = true;
        }
        return retorno;
    }

    public static Date pega(Object doValor) {
        return pega(doValor, (Date) null);
    }

    public static Date pega(Object doValor, Date comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Date retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Date) {
                retorno = (Date) doValor;
            } else if (doValor instanceof java.sql.Date) {
                retorno = new Date(((java.sql.Date) doValor).getTime());
            } else if (doValor instanceof java.sql.Time) {
                retorno = new Date(((java.sql.Time) doValor).getTime());
            } else if (doValor instanceof java.sql.Timestamp) {
                retorno = new Date(((java.sql.Timestamp) doValor).getTime());
            }
        }
        return retorno;
    }

    public static Date pega(Object aValor, String noFormato) {
        return pega(aValor, noFormato, null);
    }

    public static Date pega(Object doValor, String noFormato, Date comPadrao) {
        if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        Date retorno = comPadrao;
        if (doValor != null) {
            if (doValor instanceof Date) {
                retorno = (Date) doValor;
            } else if (doValor instanceof java.sql.Date) {
                retorno = new Date(((java.sql.Date) doValor).getTime());
            } else if (doValor instanceof java.sql.Time) {
                retorno = new Date(((java.sql.Time) doValor).getTime());
            } else if (doValor instanceof java.sql.Timestamp) {
                retorno = new Date(((java.sql.Timestamp) doValor).getTime());
            } else if (doValor instanceof String) {
                retorno = pega((String) doValor, noFormato, comPadrao);
            }
        }
        return retorno;
    }

    public static Date pega(String dosCaracteres, String noFormato) {
        return pega(dosCaracteres, noFormato, null);
    }

    public static Date pega(String dosCaracteres, String noFormato, Date comPadrao) {
        if (dosCaracteres == null) {
            return comPadrao;
        }
        if (dosCaracteres.isEmpty()) {
            return comPadrao;
        }
        if (dosCaracteres.equals("null")) {
            return comPadrao;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(noFormato);
            return formatter.parse(dosCaracteres);
        } catch (Exception ex) {
            return comPadrao;
        }
    }

    public static Date pega(Integer doDia, Integer eMes, Integer eAno) throws Exception {
        return new SimpleDateFormat("d/M/y").parse(doDia + "/" + eMes + "/" + eAno);
    }

    public static String formata(Date aData, String noFormato) {
        return formata(aData, noFormato, null);
    }

    public static String formata(Date aData, String noFormato, String comPadrao) {
        String retorno = comPadrao;
        if (aData != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(noFormato);
                retorno = formatter.format(aData);
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

    public static Boolean ehIgual(Object oValor, Object eValor) {
        return Objects.equals(pega(oValor), pega(eValor));
    }

    public static boolean ehMaior(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).getTime() > pega(queValor).getTime();
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean ehMenor(Object oValor, Object queValor) {
        if (oValor != null && queValor != null) {
            return pega(oValor).getTime() > pega(queValor).getTime();
        } else if (oValor == null & queValor == null) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean ehMaiorOuIgual(Object oValor, Object queValor) {
        return ehMaior(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Boolean ehMenorOuIgual(Object oValor, Object queValor) {
        return ehMenor(oValor, queValor) || ehIgual(oValor, queValor);
    }

    public static Date soma(Object oValor, Object comValor) {
        return new Date(pega(oValor, new Date()).getTime() + pega(comValor, new Date()).getTime());
    }

    public static Date maior(Date data, Date eData) {
        Date retorno = null;
        if ((data != null) && (eData == null)) {
            retorno = data;
        } else if ((data == null) && (eData != null)) {
            retorno = eData;
        } else if ((data != null) && (eData != null)) {
            if (data.getTime() > eData.getTime()) {
                retorno = data;
            } else {
                retorno = eData;
            }
        }
        return retorno;
    }

    public static Date menor(Date data, Date eData) {
        Date retorno = null;
        if ((data != null) && (eData == null)) {
            retorno = data;
        } else if ((data == null) && (eData != null)) {
            retorno = eData;
        } else if ((data != null) && (eData != null)) {
            if (data.getTime() < eData.getTime()) {
                retorno = data;
            } else {
                retorno = eData;
            }
        }
        return retorno;
    }

    public static Date somaDias(Date aData, Integer dias) {
        Calendar c = Calendar.getInstance();
        c.setTime(aData);
        c.add(Calendar.DATE, dias);
        return c.getTime();
    }

    public static Long diferencaEmSegundos(Date doInicio, Date eFim) {
        Long dat1 = doInicio.getTime() / 1000;
        Long dat2 = eFim.getTime() / 1000;
        return dat2 - dat1;
    }

    public static Long diferencaEmMiliSegundos(Date doInicio, Date eFim) {
        Long dat1 = doInicio.getTime();
        Long dat2 = eFim.getTime();
        return dat2 - dat1;
    }

    public static String diferencaFormatada(Date doFim, Date eComeco) {
        String retorno = "";
        long diff = doFim.getTime() - eComeco.getTime();
        long diffSeg = diff / 1000 % 60;
        long diffMin = diff / (60 * 1000) % 60;
        long diffHor = diff / (60 * 60 * 1000) % 60;

        if (diffHor > 0) {
            retorno = retorno + (retorno.isEmpty() ? "" : " ") + diffHor + " hora(s)";
        }
        if (diffMin > 0) {
            retorno = retorno + (retorno.isEmpty() ? "" : " ") + diffMin + " minuto(s)";
        }
        if (diffSeg > 0) {
            retorno = retorno + (retorno.isEmpty() ? "" : " ") + diffSeg + " segundo(s)";
        }

        return retorno;
    }

    public static Integer pegaDia(Date doTempo) {
        return pegaDia(doTempo, null);
    }

    public static Integer pegaDia(Date doTempo, Integer comPadrao) {
        Integer retorno = comPadrao;
        Date data = doTempo;
        if (data != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd");
                retorno = Integer.parseInt(formatter.format(data));
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

    public static Integer pegaUltimoDia(Integer doMes, Integer eAno) {
        Integer retorno = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(pega("01/" + doMes + "/" + eAno, "dd/MM/yyyy"));
        retorno = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return retorno;
    }

    public static Integer pegaMes(Date doTempo) {
        return pegaMes(doTempo, null);
    }

    public static Integer pegaMes(Date doTempo, Integer comPadrao) {
        Integer retorno = comPadrao;
        Date data = doTempo;
        if (data != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM");
                retorno = Integer.parseInt(formatter.format(data));
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

    public static Integer pegaAno(Date doTempo) {
        return pegaAno(doTempo, null);
    }

    public static Integer pegaAno(Date doTempo, Integer comPadrao) {
        Integer retorno = comPadrao;
        Date data = doTempo;
        if (data != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                retorno = Integer.parseInt(formatter.format(data));
            } catch (Exception ex) {
            }
        }
        return retorno;
    }

    public static String pegaDiaAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaDiaAtualInt() {
        return Integer.parseInt(pegaDiaAtual());
    }

    public static String pegaMesAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaMesAtualInt() {
        return Integer.parseInt(pegaMesAtual());
    }

    public static String pegaAnoAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaAnoAtualInt() {
        return Integer.parseInt(pegaAnoAtual());
    }

    public static String pegaHoraAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaHoraAtualInt() {
        return Integer.parseInt(pegaHoraAtual());
    }

    public static String pegaMinutosAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaMinutosAtualInt() {
        return Integer.parseInt(pegaMinutosAtual());
    }

    public static String pegaSegundosAtual() {
        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        Date agora = new Date();
        return formatter.format(agora);
    }

    public static Integer pegaSegundosAtualInt() {
        return Integer.parseInt(pegaSegundosAtual());
    }

    public static Long pegaMilisAtual() {
        return System.currentTimeMillis();
    }

    public static Long pegaNanoAtual() {
        return System.nanoTime();
    }

    public static char[] chrsDeFormato = {'G', 'y', 'Y', 'M', 'w', 'W', 'D', 'd', 'F', 'E', 'u', 'a', 'H', 'k', 'K', 'h', 'm', 's', 'S', 'z', 'Z', 'X'};
    public static char[] chrsDeSeparacao = {'/', '\\', ':', '.', ';', '-', ' '};

    public static String ajustaDigitado(String osCaracteres, String noFormato) {
        if (osCaracteres == null) {
            osCaracteres = "";
        }
        String retorno = osCaracteres;
        if (!retorno.isEmpty()) {
            if (noFormato != null) {
                if (noFormato.length() > osCaracteres.length()) {
                    String formatoFaltante = noFormato.substring(osCaracteres.length());
                    retorno += new SimpleDateFormat(formatoFaltante).format(UtlDat.pega(UtlDat.pegaAtual()));
                }
            }
        }
        return retorno;
    }

    public static Boolean confDigitado(String osCaracteres, String noFormato) {
        try {
            if (osCaracteres != null) {
                if (noFormato != null) {
                    if (osCaracteres.length() != noFormato.length()) {
                        return false;
                    }
                    for (int ic = 0; ic < noFormato.length(); ic++) {
                        char cf = noFormato.charAt(ic);
                        char cd = osCaracteres.charAt(ic);
                        if (UtlLis.tem(cf, chrsDeSeparacao)) {
                            if (!Objects.equals(cf, cd)) {
                                return false;
                            }
                        } else {
                            if (!UtlCr.ehInteiro(cd)) {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static enum Temporidade {

        Segundos("SG"), Minutos("MN"), Horas("HR"), Dias("DA"), Semanas("SM"), Quinzenas("QZ"), Meses("MS"), Bimestres("BM"), Trimestres("TM"), Semestres("SE"), Anos("AN"), Bienios("BE"), Trienios("TE");

        public final String abreviado;

        private Temporidade(final String noAbreviado) {
            abreviado = noAbreviado;
        }

        public Long fator() {
            Long fator = null;
            switch (this) {
                case Segundos:
                    fator = 1000l;
                    break;
                case Minutos:
                    fator = 60l * 1000l;
                    break;
                case Horas:
                    fator = 60l * 60l * 1000l;
                    break;
                case Dias:
                    fator = 24l * 60l * 60l * 1000l;
                    break;
                case Semanas:
                    fator = 24l * 60l * 60l * 1000l * 7;
                    break;
                case Quinzenas:
                    fator = 24l * 60l * 60l * 1000l * 15;
                    break;
                case Meses:
                    fator = 30l * 24l * 60l * 60l * 1000l;
                    break;
                case Bimestres:
                    fator = 30l * 24l * 60l * 60l * 1000l * 2;
                    break;
                case Trimestres:
                    fator = 30l * 24l * 60l * 60l * 1000l * 3;
                    break;
                case Semestres:
                    fator = 6l * 30l * 24l * 60l * 60l * 1000l;
                    break;
                case Anos:
                    fator = 12l * 30l * 24l * 60l * 60l * 1000l;
                    break;
                case Bienios:
                    fator = 12l * 30l * 24l * 60l * 60l * 1000l * 3;
                    break;
                case Trienios:
                    fator = 12l * 30l * 24l * 60l * 60l * 1000l * 3;
                    break;
            }
            return fator;
        }

        public static Temporidade pega(String doAbreviado) {
            Temporidade retorno = null;
            for (int i1 = 0; i1 < values().length; i1++) {
                if (Objects.equals(doAbreviado, values()[i1].abreviado)) {
                    retorno = values()[i1];
                    break;
                }
            }
            return retorno;
        }

        public static Object[] pegaOpcoes() {
            Object[] retorno = new Object[values().length];
            for (int i1 = 0; i1 < retorno.length; i1++) {
                retorno[i1] = "(" + values()[i1].abreviado + ") " + values()[i1].toString();
            }
            return retorno;
        }
    };

    public static Long periodo(Integer quantas, Temporidade unidades) {
        return unidades.fator() / quantas;
    }

    public static Long percurso(Integer quantas, Temporidade unidades) {
        return unidades.fator() * quantas;
    }

    public static Date percorrido(Integer quantas, Temporidade unidades) {
        return percorrido(new Date(), quantas, unidades);
    }

    public static Date percorrido(Date aData, Integer quantas, Temporidade unidades) {
        return new Date(aData.getTime() - percurso(quantas, unidades));
    }

    public static Date percorrer(Integer quantas, Temporidade unidades) {
        return percorrer(new Date(), quantas, unidades);
    }

    public static Date percorrer(Date aData, Integer quantas, Temporidade unidades) {
        return new Date(aData.getTime() + percurso(quantas, unidades));
    }

    public static Boolean periodico(Date doUltimo, Integer quantas, Temporidade unidades) {
        return periodico(doUltimo, new Date(), quantas, unidades);
    }

    public static Boolean periodico(Date doUltimo, Date comData, Integer quantas, Temporidade unidades) {
        Boolean retorno = false;
        Long periodo = periodo(quantas, unidades);
        if (doUltimo == null) {
            retorno = true;
        } else {
            if (doUltimo.getTime() + periodo <= comData.getTime()) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static Integer decorrido(Date aData, Integer quantas, Temporidade unidades) {
        return decorrido(aData, new Date(), quantas, unidades);
    }

    public static Integer decorrido(Date aData, Date comData, Integer quantas, Temporidade unidades) {
        Long verifica = comData.getTime() + percurso(quantas, unidades);
        Long paraVer = aData.getTime();
        if (paraVer > verifica) {
            return 1;
        } else if (paraVer < verifica) {
            return -1;
        } else {
            return 0;
        }
    }

    public static String pegaMes(Integer daOrdem) throws Exception {
        Locale BRAZIL = new Locale("pt", "BR");
        Date mes = new SimpleDateFormat("M", BRAZIL).parse(daOrdem.toString());
        return new SimpleDateFormat("MMMM", BRAZIL).format(mes);
    }

    public static Integer pegaMes(String doNome) throws Exception {
        Locale BRAZIL = new Locale("pt", "BR");
        Date mes = new SimpleDateFormat("MMMM", BRAZIL).parse(doNome);
        String retorno = new SimpleDateFormat("M", BRAZIL).format(mes);
        return Integer.parseInt(retorno);
    }

    public static Integer tamanhoMes(Integer mes, Integer ano) {
        YearMonth yearMonthObject = YearMonth.of(ano, mes);
        return yearMonthObject.lengthOfMonth();
    }

    public static Integer diaDaSemana(Date daData) {
        Calendar c = Calendar.getInstance();
        c.setTime(daData);
        return c.get(Calendar.DAY_OF_WEEK);
    }

}
