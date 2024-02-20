package pin.libitr;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import pin.libbas.Configs;
import pin.libbas.Globais;
import pin.libbas.Partes;
import pin.libjan.Janelas;
import pin.libjan.TrataIntf;
import pin.libutl.Utl;
import pin.libutl.UtlCrs;
import pin.libutl.UtlTex;

public class FormulaCrs extends javax.swing.JFrame {

    private Configs cfgs;
    private Pattern padrao;
    private ActionListener aoConfirmar;
    private ActionListener aoCancelar;
    private DefaultComboBoxModel dcmPadroes;

    public FormulaCrs() {
        this(false);
    }

    public FormulaCrs(Boolean usaContendo) {
        initComponents();
        jckContendo.setVisible(usaContendo);
        cfgs = ((Configs) Globais.pega("mainConf"));
        aoConfirmar = null;
        aoCancelar = null;
        dcmPadroes = new DefaultComboBoxModel();
        jcbFormula.setModel(dcmPadroes);
        String pads = cfgs.pegaCrs("PointelLibREC - FormulaCrs", "");
        for (String pad : Partes.pega(pads)) {
            dcmPadroes.addElement(pad);
        }
        iniciaMago();
        getRootPane().setDefaultButton(jbtConfirmar);
        Janelas.inicia(this);
    }

    private void iniciaMago() {
        ActionListener actBtn = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TrataIntf.botaNoCursor(((JTextField) jcbFormula.getEditor().getEditorComponent()), ((JMenuItem) ae.getSource()).getText());
            }
        };
        jmiGrp1Itn1.addActionListener(actBtn);
        jmiGrp1Itn2.addActionListener(actBtn);
        jmiGrp1Itn3.addActionListener(actBtn);
        jmiGrp1Itn4.addActionListener(actBtn);
        jmiGrp1Itn5.addActionListener(actBtn);
        jmiGrp1Itn6.addActionListener(actBtn);
        jmiGrp1Itn7.addActionListener(actBtn);
        jmiGrp1Itn8.addActionListener(actBtn);
        jmiGrp1Itn9.addActionListener(actBtn);
        jmiGrp1Itn10.addActionListener(actBtn);
        jmiGrp1Itn11.addActionListener(actBtn);
        jmiGrp2Itn1.addActionListener(actBtn);
        jmiGrp2Itn2.addActionListener(actBtn);
        jmiGrp2Itn3.addActionListener(actBtn);
        jmiGrp2Itn4.addActionListener(actBtn);
        jmiGrp3Itn1.addActionListener(actBtn);
        jmiGrp3Itn2.addActionListener(actBtn);
        jmiGrp3Itn3.addActionListener(actBtn);
        jmiGrp3Itn4.addActionListener(actBtn);
        jmiGrp3Itn5.addActionListener(actBtn);
        jmiGrp3Itn6.addActionListener(actBtn);
        jmiGrp3Itn7.addActionListener(actBtn);
        jmiGrp3Itn8.addActionListener(actBtn);
        jmiGrp3Itn9.addActionListener(actBtn);
        jmiGrp3Itn10.addActionListener(actBtn);
        jmiGrp3Itn11.addActionListener(actBtn);
        jmiGrp3Itn12.addActionListener(actBtn);
        jmiGrp3Itn13.addActionListener(actBtn);
        jmiGrp3Itn14.addActionListener(actBtn);
        jmiGrp3Itn15.addActionListener(actBtn);
        jmiGrp3Itn16.addActionListener(actBtn);
        jmiGrp3Itn17.addActionListener(actBtn);
        jmiGrp3Itn18.addActionListener(actBtn);
        jmiGrp3Itn19.addActionListener(actBtn);
        jmiGrp3Itn20.addActionListener(actBtn);
        jmiGrp3Itn21.addActionListener(actBtn);
        jmiGrp3Itn22.addActionListener(actBtn);
        jmiGrp3Itn23.addActionListener(actBtn);
        jmiGrp3Itn24.addActionListener(actBtn);
        jmiGrp3Itn25.addActionListener(actBtn);
        jmiGrp3Itn26.addActionListener(actBtn);
        jmiGrp4Itn1.addActionListener(actBtn);
        jmiGrp4Itn2.addActionListener(actBtn);
        jmiGrp4Itn3.addActionListener(actBtn);
        jmiGrp4Itn4.addActionListener(actBtn);
        jmiGrp4Itn5.addActionListener(actBtn);
        jmiGrp4Itn6.addActionListener(actBtn);
        jmiGrp5Itn1.addActionListener(actBtn);
        jmiGrp5Itn2.addActionListener(actBtn);
        jmiGrp5Itn3.addActionListener(actBtn);
        jmiGrp5Itn4.addActionListener(actBtn);
        jmiGrp5Itn5.addActionListener(actBtn);
        jmiGrp5Itn6.addActionListener(actBtn);
        jmiGrp6Itn1.addActionListener(actBtn);
        jmiGrp6Itn2.addActionListener(actBtn);
        jmiGrp6Itn3.addActionListener(actBtn);
    }

    public FormulaCrs mostra() {
        Janelas.mostra(this);
        return this;
    }

    public FormulaCrs mostra(String comTitulo) {
        setTitle(comTitulo);
        Janelas.mostra(this);
        return this;
    }

    public FormulaCrs fechar() {
        Janelas.fecha(this);
        return this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmMago = new javax.swing.JPopupMenu();
        jmnGrupo1 = new javax.swing.JMenu();
        jmiGrp1Itn1 = new javax.swing.JMenuItem();
        jmiGrp1Itn2 = new javax.swing.JMenuItem();
        jmiGrp1Itn3 = new javax.swing.JMenuItem();
        jmiGrp1Itn4 = new javax.swing.JMenuItem();
        jmiGrp1Itn5 = new javax.swing.JMenuItem();
        jmiGrp1Itn6 = new javax.swing.JMenuItem();
        jmiGrp1Itn7 = new javax.swing.JMenuItem();
        jmiGrp1Itn8 = new javax.swing.JMenuItem();
        jmiGrp1Itn9 = new javax.swing.JMenuItem();
        jmiGrp1Itn10 = new javax.swing.JMenuItem();
        jmiGrp1Itn11 = new javax.swing.JMenuItem();
        jmnGrupo2 = new javax.swing.JMenu();
        jmiGrp2Itn1 = new javax.swing.JMenuItem();
        jmiGrp2Itn2 = new javax.swing.JMenuItem();
        jmiGrp2Itn3 = new javax.swing.JMenuItem();
        jmiGrp2Itn4 = new javax.swing.JMenuItem();
        jmnGrupo3 = new javax.swing.JMenu();
        jmiGrp3Itn1 = new javax.swing.JMenuItem();
        jmiGrp3Itn2 = new javax.swing.JMenuItem();
        jmiGrp3Itn3 = new javax.swing.JMenuItem();
        jmiGrp3Itn4 = new javax.swing.JMenuItem();
        jmiGrp3Itn5 = new javax.swing.JMenuItem();
        jmiGrp3Itn6 = new javax.swing.JMenuItem();
        jmiGrp3Itn7 = new javax.swing.JMenuItem();
        jmiGrp3Itn8 = new javax.swing.JMenuItem();
        jmiGrp3Itn9 = new javax.swing.JMenuItem();
        jmiGrp3Itn10 = new javax.swing.JMenuItem();
        jmiGrp3Itn11 = new javax.swing.JMenuItem();
        jmiGrp3Itn12 = new javax.swing.JMenuItem();
        jmiGrp3Itn13 = new javax.swing.JMenuItem();
        jmiGrp3Itn14 = new javax.swing.JMenuItem();
        jmiGrp3Itn15 = new javax.swing.JMenuItem();
        jmiGrp3Itn16 = new javax.swing.JMenuItem();
        jmiGrp3Itn17 = new javax.swing.JMenuItem();
        jmiGrp3Itn18 = new javax.swing.JMenuItem();
        jmiGrp3Itn19 = new javax.swing.JMenuItem();
        jmiGrp3Itn20 = new javax.swing.JMenuItem();
        jmiGrp3Itn21 = new javax.swing.JMenuItem();
        jmiGrp3Itn22 = new javax.swing.JMenuItem();
        jmiGrp3Itn23 = new javax.swing.JMenuItem();
        jmiGrp3Itn24 = new javax.swing.JMenuItem();
        jmiGrp3Itn25 = new javax.swing.JMenuItem();
        jmiGrp3Itn26 = new javax.swing.JMenuItem();
        jmnGrupo4 = new javax.swing.JMenu();
        jmiGrp4Itn1 = new javax.swing.JMenuItem();
        jmiGrp4Itn2 = new javax.swing.JMenuItem();
        jmiGrp4Itn3 = new javax.swing.JMenuItem();
        jmiGrp4Itn4 = new javax.swing.JMenuItem();
        jmiGrp4Itn5 = new javax.swing.JMenuItem();
        jmiGrp4Itn6 = new javax.swing.JMenuItem();
        jmnGrupo5 = new javax.swing.JMenu();
        jmiGrp5Itn1 = new javax.swing.JMenuItem();
        jmiGrp5Itn2 = new javax.swing.JMenuItem();
        jmiGrp5Itn3 = new javax.swing.JMenuItem();
        jmiGrp5Itn4 = new javax.swing.JMenuItem();
        jmiGrp5Itn5 = new javax.swing.JMenuItem();
        jmiGrp5Itn6 = new javax.swing.JMenuItem();
        jmnGrupo6 = new javax.swing.JMenu();
        jmiGrp6Itn1 = new javax.swing.JMenuItem();
        jmiGrp6Itn2 = new javax.swing.JMenuItem();
        jmiGrp6Itn3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiNumeroPagina = new javax.swing.JMenuItem();
        jlbPadrao = new javax.swing.JLabel();
        jcbFormula = new javax.swing.JComboBox<>();
        jbtLimpar = new javax.swing.JButton();
        jbtConfirmar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();
        jckContendo = new javax.swing.JCheckBox();
        jbtMago = new javax.swing.JButton();

        jmnGrupo1.setText("Grupo 1");

        jmiGrp1Itn1.setText(".");
        jmiGrp1Itn1.setToolTipText("Matches any character except line breaks. Equivalent to [^\\n\\r].");
        jmnGrupo1.add(jmiGrp1Itn1);

        jmiGrp1Itn2.setText("[\\s\\S]");
        jmiGrp1Itn2.setToolTipText("A character set that can be used to match any character, including line breaks.");
        jmnGrupo1.add(jmiGrp1Itn2);

        jmiGrp1Itn3.setText("\\w");
        jmiGrp1Itn3.setToolTipText("Matches any word character (alphanumeric & underscore). Only matches low-ascii characters (no accented or non-roman characters). Equivalent to [A-Za-z0-9_]");
        jmnGrupo1.add(jmiGrp1Itn3);

        jmiGrp1Itn4.setText("\\W");
        jmiGrp1Itn4.setToolTipText("Matches any character that is not a word character (alphanumeric & underscore). Equivalent to [^A-Za-z0-9_]");
        jmnGrupo1.add(jmiGrp1Itn4);

        jmiGrp1Itn5.setText("\\d");
        jmiGrp1Itn5.setToolTipText("Matches any digit character (0-9). Equivalent to [0-9].");
        jmnGrupo1.add(jmiGrp1Itn5);

        jmiGrp1Itn6.setText("\\D");
        jmiGrp1Itn6.setToolTipText("Matches any character that is not a digit character (0-9). Equivalent to [^0-9].");
        jmnGrupo1.add(jmiGrp1Itn6);

        jmiGrp1Itn7.setText("\\s");
        jmiGrp1Itn7.setToolTipText("Matches any whitespace character (spaces, tabs, line breaks).");
        jmnGrupo1.add(jmiGrp1Itn7);

        jmiGrp1Itn8.setText("\\S");
        jmiGrp1Itn8.setToolTipText("Matches any character that is not a whitespace character (spaces, tabs, line breaks).");
        jmnGrupo1.add(jmiGrp1Itn8);

        jmiGrp1Itn9.setText("[abc]");
        jmiGrp1Itn9.setToolTipText("Match any character in the set.");
        jmnGrupo1.add(jmiGrp1Itn9);

        jmiGrp1Itn10.setText("[^abc]");
        jmiGrp1Itn10.setToolTipText("Match any character that is not in the set.");
        jmnGrupo1.add(jmiGrp1Itn10);

        jmiGrp1Itn11.setText("[a-c]");
        jmiGrp1Itn11.setToolTipText("Matches a character having a character code between the two specified characters inclusive.");
        jmnGrupo1.add(jmiGrp1Itn11);

        jpmMago.add(jmnGrupo1);

        jmnGrupo2.setText("Grupo 2");

        jmiGrp2Itn1.setText("^");
        jmiGrp2Itn1.setToolTipText("Matches the beginning of the string, or the beginning of a line if the multiline flag (m) is enabled. This matches a position, not a character.");
        jmnGrupo2.add(jmiGrp2Itn1);

        jmiGrp2Itn2.setText("$");
        jmiGrp2Itn2.setToolTipText("Matches the end of the string, or the end of a line if the multiline flag (m) is enabled. This matches a position, not a character.");
        jmnGrupo2.add(jmiGrp2Itn2);

        jmiGrp2Itn3.setText("\\b");
        jmiGrp2Itn3.setToolTipText("Matches a word boundary position such as whitespace, punctuation, or the start/end of the string. This matches a position, not a character.");
        jmnGrupo2.add(jmiGrp2Itn3);

        jmiGrp2Itn4.setText("\\B");
        jmiGrp2Itn4.setToolTipText("Matches any position that is not a word boundary. This matches a position, not a character.");
        jmnGrupo2.add(jmiGrp2Itn4);

        jpmMago.add(jmnGrupo2);

        jmnGrupo3.setText("Grupo 3");

        jmiGrp3Itn1.setText("\\000");
        jmiGrp3Itn1.setToolTipText("Octal escaped character in the form \\000. Value must be less than 255 (\\251 for ©).");
        jmnGrupo3.add(jmiGrp3Itn1);

        jmiGrp3Itn2.setText("\\xFF");
        jmiGrp3Itn2.setToolTipText("Hexadecimal escaped character in the form \\xFF. (\\xA9 for ©)");
        jmnGrupo3.add(jmiGrp3Itn2);

        jmiGrp3Itn3.setText("\\xFF");
        jmiGrp3Itn3.setToolTipText("Hexadecimal escaped character in the form \\xFF. (\\xA9 for ©)");
        jmnGrupo3.add(jmiGrp3Itn3);

        jmiGrp3Itn4.setText("\\uFFFF");
        jmiGrp3Itn4.setToolTipText("Unicode escaped character in the form \\uFFFF. (\\u00A9 for ©)");
        jmnGrupo3.add(jmiGrp3Itn4);

        jmiGrp3Itn5.setText("\\cI");
        jmiGrp3Itn5.setToolTipText("Escaped control character in the form \\cZ. This can range from \\cA (NULL, char code 0) to \\cZ (EM, char code 25). \\cI matches TAB (char code 9).");
        jmnGrupo3.add(jmiGrp3Itn5);

        jmiGrp3Itn6.setText("\\t");
        jmiGrp3Itn6.setToolTipText("Matches a TAB character (char code 9).");
        jmnGrupo3.add(jmiGrp3Itn6);

        jmiGrp3Itn7.setText("\\n");
        jmiGrp3Itn7.setToolTipText("Matches a LINE FEED character (char code 10).");
        jmnGrupo3.add(jmiGrp3Itn7);

        jmiGrp3Itn8.setText("\\v");
        jmiGrp3Itn8.setToolTipText("Matches a VERTICAL TAB character (char code 11).");
        jmnGrupo3.add(jmiGrp3Itn8);

        jmiGrp3Itn9.setText("\\f");
        jmiGrp3Itn9.setToolTipText("Matches a FORM FEED character (char code 12).");
        jmnGrupo3.add(jmiGrp3Itn9);

        jmiGrp3Itn10.setText("\\r");
        jmiGrp3Itn10.setToolTipText("Matches a CARRIAGE RETURN character (char code 13).");
        jmnGrupo3.add(jmiGrp3Itn10);

        jmiGrp3Itn11.setText("\\0");
        jmiGrp3Itn11.setToolTipText("Matches a NULL character (char code 0).");
        jmnGrupo3.add(jmiGrp3Itn11);

        jmiGrp3Itn12.setText("\\.");
        jmiGrp3Itn12.setToolTipText("Matches a \".\" character");
        jmnGrupo3.add(jmiGrp3Itn12);

        jmiGrp3Itn13.setText("\\\\");
            jmiGrp3Itn13.setToolTipText("Matches a \"\\\" character");
            jmnGrupo3.add(jmiGrp3Itn13);

            jmiGrp3Itn14.setText("\\*");
            jmiGrp3Itn14.setToolTipText("Matches a \"*\" character");
            jmnGrupo3.add(jmiGrp3Itn14);

            jmiGrp3Itn15.setText("\\+");
            jmiGrp3Itn15.setToolTipText("Matches a \"+\" character");
            jmnGrupo3.add(jmiGrp3Itn15);

            jmiGrp3Itn16.setText("\\?");
            jmiGrp3Itn16.setToolTipText("Matches a \"?\" character");
            jmnGrupo3.add(jmiGrp3Itn16);

            jmiGrp3Itn17.setText("\\{");
            jmiGrp3Itn17.setToolTipText("Matches a \"{\" character");
            jmnGrupo3.add(jmiGrp3Itn17);

            jmiGrp3Itn18.setText("\\]");
            jmiGrp3Itn18.setToolTipText("Matches a \"]\" character");
            jmnGrupo3.add(jmiGrp3Itn18);

            jmiGrp3Itn19.setText("\\[");
            jmiGrp3Itn19.setToolTipText("Matches a \"[\" character");
            jmnGrupo3.add(jmiGrp3Itn19);

            jmiGrp3Itn20.setText("\\$");
            jmiGrp3Itn20.setToolTipText("Matches a \"$\" character");
            jmnGrupo3.add(jmiGrp3Itn20);

            jmiGrp3Itn21.setText("\\^");
            jmiGrp3Itn21.setToolTipText("Matches a \"^\" character");
            jmnGrupo3.add(jmiGrp3Itn21);

            jmiGrp3Itn22.setText("\\/");
            jmiGrp3Itn22.setToolTipText("Matches a \"/\" character");
            jmnGrupo3.add(jmiGrp3Itn22);

            jmiGrp3Itn23.setText("\\|");
            jmiGrp3Itn23.setToolTipText("Matches a \"|\" character");
            jmnGrupo3.add(jmiGrp3Itn23);

            jmiGrp3Itn24.setText("\\)");
            jmiGrp3Itn24.setToolTipText("Matches a \")\" character");
            jmnGrupo3.add(jmiGrp3Itn24);

            jmiGrp3Itn25.setText("\\(");
            jmiGrp3Itn25.setToolTipText("Matches a \"(\" character");
            jmnGrupo3.add(jmiGrp3Itn25);

            jmiGrp3Itn26.setText("\\}");
            jmiGrp3Itn26.setToolTipText("Matches a \"}\" character");
            jmnGrupo3.add(jmiGrp3Itn26);

            jpmMago.add(jmnGrupo3);

            jmnGrupo4.setText("Grupo 4");

            jmiGrp4Itn1.setText("(abc)");
            jmiGrp4Itn1.setToolTipText("Groups multiple tokens together and creates a capture group for extracting a substring or using a backreference.");
            jmnGrupo4.add(jmiGrp4Itn1);

            jmiGrp4Itn2.setText("\\1");
            jmiGrp4Itn2.setToolTipText("Matches the results of a previous capture group. For example \\1 matches the results of the first capture group & \\3 matches the third.");
            jmnGrupo4.add(jmiGrp4Itn2);

            jmiGrp4Itn3.setText("(?:abc)");
            jmiGrp4Itn3.setToolTipText("Groups multiple tokens together without creating a capture group.");
            jmnGrupo4.add(jmiGrp4Itn3);

            jmiGrp4Itn4.setText("(?=abc)");
            jmiGrp4Itn4.setToolTipText("Matches a group after the main expression without including it in the result.");
            jmnGrupo4.add(jmiGrp4Itn4);

            jmiGrp4Itn5.setText("(?!abc)");
            jmiGrp4Itn5.setToolTipText("Specifies a group that can not match after the main expression (if it matches, the result is discarded).");
            jmnGrupo4.add(jmiGrp4Itn5);

            jmiGrp4Itn6.setText("(?!abc)");
            jmiGrp4Itn6.setToolTipText("Specifies a group that can not match after the main expression (if it matches, the result is discarded).");
            jmnGrupo4.add(jmiGrp4Itn6);

            jpmMago.add(jmnGrupo4);

            jmnGrupo5.setText("Grupo 5");
            jmnGrupo5.setToolTipText("");

            jmiGrp5Itn1.setText("+");
            jmiGrp5Itn1.setToolTipText("Matches 1 or more of the preceding token.");
            jmnGrupo5.add(jmiGrp5Itn1);

            jmiGrp5Itn2.setText("*");
            jmiGrp5Itn2.setToolTipText("Matches 0 or more of the preceding token.");
            jmnGrupo5.add(jmiGrp5Itn2);

            jmiGrp5Itn3.setText("{1,3}");
            jmiGrp5Itn3.setToolTipText("Matches the specified quantity of the previous token. {1,3} will match 1 to 3. {3} will match exactly 3. {3,} will match 3 or more. ");
            jmnGrupo5.add(jmiGrp5Itn3);

            jmiGrp5Itn4.setText("?");
            jmiGrp5Itn4.setToolTipText("Matches 0 or 1 of the preceding token, effectively making it optional.");
            jmnGrupo5.add(jmiGrp5Itn4);

            jmiGrp5Itn5.setText("|");
            jmiGrp5Itn5.setToolTipText("Acts like a boolean OR. Matches the expression before or after the |.  It can operate within a group, or on a whole expression. The patterns will be tested in order.");
            jmnGrupo5.add(jmiGrp5Itn5);

            jmiGrp5Itn6.setText("?");
            jmiGrp5Itn6.setToolTipText("Makes the preceding quantifier lazy, causing it to match as few characters as possible. By default, quantifiers are greedy, and will match as many characters as possible.");
            jmnGrupo5.add(jmiGrp5Itn6);

            jpmMago.add(jmnGrupo5);

            jmnGrupo6.setText("Grupo 6");

            jmiGrp6Itn1.setText("i");
            jmiGrp6Itn1.setToolTipText("Makes the whole expression case-insensitive. For example, /aBc/i would match AbC.");
            jmnGrupo6.add(jmiGrp6Itn1);

            jmiGrp6Itn2.setText("g");
            jmiGrp6Itn2.setToolTipText("Retain the index of the last match, allowing subsequent searches to start from the end of the previous match.  Without the global flag, subsequent searches will return the same match.");
            jmnGrupo6.add(jmiGrp6Itn2);

            jmiGrp6Itn3.setText("m");
            jmiGrp6Itn3.setToolTipText("When the multiline flag is enabled, beginning and end anchors (^ and $) will match the start and end of a line, instead of the start and end of the whole string.");
            jmnGrupo6.add(jmiGrp6Itn3);

            jpmMago.add(jmnGrupo6);
            jpmMago.add(jSeparator1);

            jmiNumeroPagina.setText("Número de Página");
            jmiNumeroPagina.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jmiNumeroPaginaActionPerformed(evt);
                }
            });
            jpmMago.add(jmiNumeroPagina);

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("Formula de Caracteres");
            setResizable(false);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent evt) {
                    formWindowClosing(evt);
                }
            });

            jlbPadrao.setText("Fórmula");

            jcbFormula.setEditable(true);

            jbtLimpar.setText("Limpar Histórico");
            jbtLimpar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jbtLimparActionPerformed(evt);
                }
            });

            jbtConfirmar.setText("Confirmar");
            jbtConfirmar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jbtConfirmarActionPerformed(evt);
                }
            });

            jbtFechar.setText("Fechar");
            jbtFechar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jbtFecharActionPerformed(evt);
                }
            });

            jckContendo.setSelected(true);
            jckContendo.setText("Contendo");

            jbtMago.setText("Mago");
            jbtMago.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jbtMagoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlbPadrao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jckContendo))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jcbFormula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtMago))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jbtLimpar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                            .addComponent(jbtConfirmar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtFechar)))
                    .addGap(10, 10, 10))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbPadrao)
                        .addComponent(jckContendo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbtMago))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtFechar)
                        .addComponent(jbtConfirmar)
                        .addComponent(jbtLimpar))
                    .addGap(10, 10, 10))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jbtLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimparActionPerformed
        try {
            String sel = (String) jcbFormula.getSelectedItem();
            cfgs.botaCrs("PointelLibREC - FormulaCrs", "");
            dcmPadroes.removeAllElements();
            jcbFormula.setSelectedItem(sel);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtLimparActionPerformed

    public Boolean ehContendo() {
        return jckContendo.isSelected();
    }

    public String pegaFormula() {
        return (String) jcbFormula.getSelectedItem();
    }

    public Pattern padrao() {
        return padrao;
    }

    public Boolean verifica(String nosCaracteres) {
        return padrao.matcher(nosCaracteres).find();
    }

    public List<Point> procura(String nosCaracteres) {
        return UtlTex.procura(nosCaracteres, padrao);
    }

    public Boolean executa(FormulaCrs comFormula) {
        return true;
    }

    public ActionListener aoConfirmar() {
        return aoConfirmar;
    }

    public FormulaCrs botaAoConfirmar(ActionListener aoConfirmar) {
        this.aoConfirmar = aoConfirmar;
        return this;
    }

    public ActionListener aoCancelar() {
        return aoCancelar;
    }

    public FormulaCrs botaAoCancelar(ActionListener aoCancelar) {
        this.aoCancelar = aoCancelar;
        return this;
    }

    private void jbtConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtConfirmarActionPerformed
        try {
            padrao = Pattern.compile(pegaFormula());
            if (executa(this)) {
                if (aoConfirmar != null) {
                    aoConfirmar.actionPerformed(null);
                }
            } else {
                if (aoCancelar != null) {
                    aoCancelar.actionPerformed(null);
                }
            }
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jbtConfirmarActionPerformed

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        if (aoCancelar != null) {
            aoCancelar.actionPerformed(null);
        }
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    private void jmiNumeroPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNumeroPaginaActionPerformed
        jcbFormula.setSelectedItem("[\\n\\r]\\d+[\\n\\r]");
    }//GEN-LAST:event_jmiNumeroPaginaActionPerformed

    private void jbtMagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMagoActionPerformed
        jpmMago.show(jbtMago, 0, jbtMago.getHeight());
    }//GEN-LAST:event_jbtMagoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        String pad = pegaFormula();
        int ipad = dcmPadroes.getIndexOf(pad);
        String pads = pad;
        for (int i1 = 0; i1 < dcmPadroes.getSize(); i1++) {
            if (!Objects.equals(ipad, i1)) {
                pads = Partes.bota(pads, UtlCrs.pega(dcmPadroes.getElementAt(i1)));
            }
        }
        cfgs.botaCrs("PointelApps - Caracteres Procurar", pads);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JButton jbtConfirmar;
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtLimpar;
    private javax.swing.JButton jbtMago;
    private javax.swing.JComboBox<String> jcbFormula;
    private javax.swing.JCheckBox jckContendo;
    private javax.swing.JLabel jlbPadrao;
    private javax.swing.JMenuItem jmiGrp1Itn1;
    private javax.swing.JMenuItem jmiGrp1Itn10;
    private javax.swing.JMenuItem jmiGrp1Itn11;
    private javax.swing.JMenuItem jmiGrp1Itn2;
    private javax.swing.JMenuItem jmiGrp1Itn3;
    private javax.swing.JMenuItem jmiGrp1Itn4;
    private javax.swing.JMenuItem jmiGrp1Itn5;
    private javax.swing.JMenuItem jmiGrp1Itn6;
    private javax.swing.JMenuItem jmiGrp1Itn7;
    private javax.swing.JMenuItem jmiGrp1Itn8;
    private javax.swing.JMenuItem jmiGrp1Itn9;
    private javax.swing.JMenuItem jmiGrp2Itn1;
    private javax.swing.JMenuItem jmiGrp2Itn2;
    private javax.swing.JMenuItem jmiGrp2Itn3;
    private javax.swing.JMenuItem jmiGrp2Itn4;
    private javax.swing.JMenuItem jmiGrp3Itn1;
    private javax.swing.JMenuItem jmiGrp3Itn10;
    private javax.swing.JMenuItem jmiGrp3Itn11;
    private javax.swing.JMenuItem jmiGrp3Itn12;
    private javax.swing.JMenuItem jmiGrp3Itn13;
    private javax.swing.JMenuItem jmiGrp3Itn14;
    private javax.swing.JMenuItem jmiGrp3Itn15;
    private javax.swing.JMenuItem jmiGrp3Itn16;
    private javax.swing.JMenuItem jmiGrp3Itn17;
    private javax.swing.JMenuItem jmiGrp3Itn18;
    private javax.swing.JMenuItem jmiGrp3Itn19;
    private javax.swing.JMenuItem jmiGrp3Itn2;
    private javax.swing.JMenuItem jmiGrp3Itn20;
    private javax.swing.JMenuItem jmiGrp3Itn21;
    private javax.swing.JMenuItem jmiGrp3Itn22;
    private javax.swing.JMenuItem jmiGrp3Itn23;
    private javax.swing.JMenuItem jmiGrp3Itn24;
    private javax.swing.JMenuItem jmiGrp3Itn25;
    private javax.swing.JMenuItem jmiGrp3Itn26;
    private javax.swing.JMenuItem jmiGrp3Itn3;
    private javax.swing.JMenuItem jmiGrp3Itn4;
    private javax.swing.JMenuItem jmiGrp3Itn5;
    private javax.swing.JMenuItem jmiGrp3Itn6;
    private javax.swing.JMenuItem jmiGrp3Itn7;
    private javax.swing.JMenuItem jmiGrp3Itn8;
    private javax.swing.JMenuItem jmiGrp3Itn9;
    private javax.swing.JMenuItem jmiGrp4Itn1;
    private javax.swing.JMenuItem jmiGrp4Itn2;
    private javax.swing.JMenuItem jmiGrp4Itn3;
    private javax.swing.JMenuItem jmiGrp4Itn4;
    private javax.swing.JMenuItem jmiGrp4Itn5;
    private javax.swing.JMenuItem jmiGrp4Itn6;
    private javax.swing.JMenuItem jmiGrp5Itn1;
    private javax.swing.JMenuItem jmiGrp5Itn2;
    private javax.swing.JMenuItem jmiGrp5Itn3;
    private javax.swing.JMenuItem jmiGrp5Itn4;
    private javax.swing.JMenuItem jmiGrp5Itn5;
    private javax.swing.JMenuItem jmiGrp5Itn6;
    private javax.swing.JMenuItem jmiGrp6Itn1;
    private javax.swing.JMenuItem jmiGrp6Itn2;
    private javax.swing.JMenuItem jmiGrp6Itn3;
    private javax.swing.JMenuItem jmiNumeroPagina;
    private javax.swing.JMenu jmnGrupo1;
    private javax.swing.JMenu jmnGrupo2;
    private javax.swing.JMenu jmnGrupo3;
    private javax.swing.JMenu jmnGrupo4;
    private javax.swing.JMenu jmnGrupo5;
    private javax.swing.JMenu jmnGrupo6;
    private javax.swing.JPopupMenu jpmMago;
    // End of variables declaration//GEN-END:variables
}
