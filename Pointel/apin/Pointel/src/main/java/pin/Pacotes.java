package pin;

import java.util.Arrays;
import java.util.List;

public class Pacotes {

    public static List<String> lista = Arrays.asList(new String[]{
        "pin",
        "pin.adm",
        "pin.adp",
        "pin.edu",
        "pin.bib",
        "pin.hlp",
        "pin.jrb",
        "pin.amk",
        "pin.amg",
        "pin.prk",
        "pin.mgr",
        "pin.inf",
        "pin.app",
        "pin.rec"
    });

    public static List<String> pegaPacotes() {
        return lista;
    }

    public static String pegaTituloPacote(Class daClasse) {
        String tituloPacote = null;
        String pacote = daClasse.getPackage().getName();
        switch (pacote) {
            case "pin":
                tituloPacote = "Pointel";
                break;
            case "pin.adm":
                tituloPacote = "PointelAdmin";
                break;
            case "pin.adp":
                tituloPacote = "PointelAdPro";
                break;
            case "pin.edu":
                tituloPacote = "PointelEduca";
                break;
            case "pin.bib":
                tituloPacote = "PointelBiblio";
                break;
            case "pin.hlp":
                tituloPacote = "PointelHelp";
                break;
            case "pin.jrb":
                tituloPacote = "PointelJarbs";
                break;
            case "pin.amk":
                tituloPacote = "PointelAllMake";
                break;
            case "pin.amg":
                tituloPacote = "PointelAllMagi";
                break;
            case "pin.prk":
                tituloPacote = "PointelProk";
                break;
            case "pin.mgr":
                tituloPacote = "PointelMigre";
                break;
            case "pin.inf":
                tituloPacote = "PointelInfo";
                break;
            case "pin.app":
                tituloPacote = "PointelApps";
                break;
            default:
                tituloPacote = "Pointel";
                break;
        }
        return tituloPacote;
    }

}
