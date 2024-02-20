package pin.libbas;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import pin.libutl.Utl;
import pin.libutl.UtlArq;

public class ConsoleLog extends PrintStream {

    public String caminho;
    public String registro;
    public PrintStream consoleOut;
    public PrintStream consoleErr;
    public SimpleDateFormat dataFormata;

    public ConsoleLog(FileOutputStream arquivoLog, String noCaminho) throws Exception {
        super(arquivoLog, true, "UTF8");
        caminho = noCaminho;
        registro = "";
        consoleOut = null;
        consoleErr = null;
        dataFormata = new SimpleDateFormat("hh:mm:ss.SSS");
    }

    public void botaParaSistema() {
        consoleOut = System.out;
        consoleErr = System.err;
        System.setOut(this);
        System.setErr(this);
    }

    public void fecha() {
        try {
            System.setOut(consoleOut);
            System.setErr(consoleErr);
            close();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }

    public static void limpa() throws Exception {
        UtlArq.exclui(UtlArq.pegaPointelDir("cons"));
    }

    public String pegaRegistro() {
        return registro;
    }

    public Integer tamanho() {
        return registro.length();
    }

    @Override
    public PrintStream append(CharSequence csq) {
        if (consoleOut != null) {
            consoleOut.append(csq);
        }
        registro += csq;
        return super.append(csq);
    }

    @Override
    public PrintStream append(char c) {
        if (consoleOut != null) {
            consoleOut.append(c);
        }
        registro += c;
        return super.append(c);
    }

    @Override
    public PrintStream append(CharSequence csq, int start, int end) {
        if (consoleOut != null) {
            consoleOut.append(csq, start, end);
        }
        CharSequence cs = (csq == null ? "null" : csq);
        registro += (cs.subSequence(start, end).toString());
        return super.append(csq, start, end);
    }

    @Override
    public void close() {
        if (consoleOut != null) {
            consoleOut.close();
        }
        if (consoleErr != null) {
            consoleErr.close();
        }
        super.close();
    }

    @Override
    public void flush() {
        if (consoleOut != null) {
            consoleOut.flush();
        }
        if (consoleErr != null) {
            consoleErr.flush();
        }
        super.flush();
    }

    @Override
    public void print(Object obj) {
        print(String.valueOf(obj));
    }

    @Override
    public void print(String s) {
        if (consoleOut != null) {
            consoleOut.print(s);
        }
        registro += s;
        super.print(s);
    }

    @Override
    public void print(boolean b) {
        print(String.valueOf(b));
    }

    @Override
    public void print(char c) {
        print(String.valueOf(c));
    }

    @Override
    public void print(char[] s) {
        print(String.valueOf(s));
    }

    @Override
    public void print(double d) {
        print(String.valueOf(d));
    }

    @Override
    public void print(float f) {
        print(String.valueOf(f));
    }

    @Override
    public void print(int i) {
        print(String.valueOf(i));
    }

    @Override
    public void print(long l) {
        print(String.valueOf(l));
    }

    @Override
    public void println() {
        print(System.lineSeparator());
    }

    @Override
    public void println(Object x) {
        println(String.valueOf(x));
    }

    private String pegaData() {
        return dataFormata.format(new Date());
    }

    private String pegaOrigem() {
        String retorno = "Prc: " + Thread.currentThread().getName() + " ";
        StringBuilder stb = new StringBuilder();
        StackTraceElement[] stc = Thread.currentThread().getStackTrace();
        for (int it = stc.length - 1; it >= 0; it--) {
            StackTraceElement ste = stc[it];
            if (ste.getClassName().startsWith("pin.")) {
                if (!ste.getClassName().equals("pin.libbas.ConsoleLog")) {
                    stb.append(ste.getFileName().substring(0, ste.getFileName().indexOf(".")) + "(" + ste.getLineNumber() + ")" + " ");
                }
            }
        }
        retorno = retorno + "[ " + stb.toString() + "]";
        return retorno;
    }

    @Override
    public void println(String x) {
        print(pegaData() + " - " + pegaOrigem() + " = " + x + System.lineSeparator());
    }

    @Override
    public void println(boolean x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(char x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(char[] x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(double x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(float x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(int x) {
        println(String.valueOf(x));
    }

    @Override
    public void println(long x) {
        println(String.valueOf(x));
    }
}
