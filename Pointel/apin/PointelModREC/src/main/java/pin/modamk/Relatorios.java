package pin.modamk;

import java.awt.print.PrinterJob;
import javax.print.PrintService;

public class Relatorios {

    public Relatorio[] relatorios;

    public RelatoriosEscolher escolha;

    public Relatorios(Relatorio... comRelatorios) {
        this.relatorios = comRelatorios;
    }

    public String[] getTitulos() {
        String[] retorno = new String[relatorios.length];
        for (int i1 = 0; i1 < relatorios.length; i1++) {
            retorno[i1] = relatorios[i1].titulo + " - " + relatorios[i1].tipo.toString().toLowerCase();
        }
        return retorno;
    }

    public Relatorio getRelatorio(String aTitulo) {
        Relatorio retorno = null;
        for (int i1 = 0; i1 < relatorios.length; i1++) {
            if ((relatorios[i1].titulo + " - " + relatorios[i1].tipo.toString().toLowerCase()).equals(aTitulo)) {
                retorno = relatorios[i1];
                break;
            }
        }
        return retorno;
    }

    public String[] getSaidas() {
        String[] retorno = null;

        PrintService[] prtSrs = PrinterJob.lookupPrintServices();
        retorno = new String[prtSrs.length + 2];

        retorno[0] = "Na Tela";
        retorno[1] = "No Arquivo";

        int i1 = 2;
        for (PrintService prtSr : prtSrs) {
            retorno[i1] = prtSr.getName();
            i1++;
        }

        return retorno;
    }

    public void escolher() {
        escolha = new RelatoriosEscolher(this);
        escolha.setVisible(true);
    }
}
