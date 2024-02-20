package pin.libamg;

import java.awt.Point;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlInt;
import pin.libutl.UtlTem;

public class EdtDatAux extends javax.swing.JFrame {

    private EdtDat editor;
    private DefaultComboBoxModel dcmMeses;
    private Dia[] dias;
    private Border brdLimpa;
    private Border brdCheia;

    public EdtDatAux(EdtDat doEditor) throws Exception {
        initComponents();
        editor = doEditor;
        brdLimpa = BorderFactory.createEmptyBorder();
        brdCheia = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        getRootPane().setBorder(brdCheia);
        iniciaDias();
        dcmMeses = new DefaultComboBoxModel();
        for (int im = 1; im <= 12; im++) {
            dcmMeses.addElement(UtlTem.pegaMes(im));
        }
        jcbMes.setModel(dcmMeses);

        JSpinner.NumberEditor jspEdt = new JSpinner.NumberEditor(jspAno, "#");
        jspAno.setEditor(jspEdt);
        Date inicial = editor.pgVlr().pgDat(new Date());
        jcbMes.setSelectedIndex(UtlTem.pegaMes(inicial) - 1);
        jspAno.setValue(UtlTem.pegaAno(inicial));
        atualiza();
        Janelas.inicia(this);
    }

    private void iniciaDias() {
        dias = new Dia[42];
        dias[0] = new Dia(dia1);
        dias[1] = new Dia(dia2);
        dias[2] = new Dia(dia3);
        dias[3] = new Dia(dia4);
        dias[4] = new Dia(dia5);
        dias[5] = new Dia(dia6);
        dias[6] = new Dia(dia7);
        dias[7] = new Dia(dia8);
        dias[8] = new Dia(dia9);
        dias[9] = new Dia(dia10);
        dias[10] = new Dia(dia11);
        dias[11] = new Dia(dia12);
        dias[12] = new Dia(dia13);
        dias[13] = new Dia(dia14);
        dias[14] = new Dia(dia15);
        dias[15] = new Dia(dia16);
        dias[16] = new Dia(dia17);
        dias[17] = new Dia(dia18);
        dias[18] = new Dia(dia19);
        dias[19] = new Dia(dia20);
        dias[20] = new Dia(dia21);
        dias[21] = new Dia(dia22);
        dias[22] = new Dia(dia23);
        dias[23] = new Dia(dia24);
        dias[24] = new Dia(dia25);
        dias[25] = new Dia(dia26);
        dias[26] = new Dia(dia27);
        dias[27] = new Dia(dia28);
        dias[28] = new Dia(dia29);
        dias[29] = new Dia(dia30);
        dias[30] = new Dia(dia31);
        dias[31] = new Dia(dia32);
        dias[32] = new Dia(dia33);
        dias[33] = new Dia(dia34);
        dias[34] = new Dia(dia35);
        dias[35] = new Dia(dia36);
        dias[36] = new Dia(dia37);
        dias[37] = new Dia(dia38);
        dias[38] = new Dia(dia39);
        dias[39] = new Dia(dia40);
        dias[40] = new Dia(dia41);
        dias[41] = new Dia(dia42);
    }

    private void limpaTodos() {
        for (int il = 0; il < dias.length; il++) {
            dias[il].limpa();
        }
    }

    public EdtDatAux mostra() {
        Point pos = editor.getLocationOnScreen();
        pos.x += editor.getWidth();
        pos.y += editor.getHeight();
        setLocation(pos);
        setVisible(true);
        return this;
    }

    private void atualiza() throws Exception {
        limpaTodos();
        if (jcbMes.getSelectedItem() == null || jspAno.getValue() == null) {
            return;
        }
        Integer mes = UtlTem.pegaMes(jcbMes.getSelectedItem().toString());
        Integer ano = UtlInt.pega(jspAno.getValue());
        Integer inicio = UtlTem.diaDaSemana(UtlTem.pega(1, mes, ano)) - 2;
        Integer tamanho = UtlTem.tamanhoMes(mes, ano);
        for (Integer id = 1; id <= tamanho; id++) {
            dias[inicio + id].mostrador.setText(id.toString());
            dias[inicio + id].mostrador.setBorder(brdCheia);
        }
    }

    private class Dia {

        public JLabel mostrador;

        public Dia(JLabel mostrador) {
            this.mostrador = mostrador;
        }

        public void limpa() {
            mostrador.setText("");
            mostrador.setBorder(brdLimpa);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbMes = new javax.swing.JComboBox<>();
        jspAno = new javax.swing.JSpinner();
        sem1 = new javax.swing.JLabel();
        sem2 = new javax.swing.JLabel();
        sem3 = new javax.swing.JLabel();
        sem4 = new javax.swing.JLabel();
        sem5 = new javax.swing.JLabel();
        sem6 = new javax.swing.JLabel();
        sem7 = new javax.swing.JLabel();
        dia1 = new javax.swing.JLabel();
        dia2 = new javax.swing.JLabel();
        dia3 = new javax.swing.JLabel();
        dia4 = new javax.swing.JLabel();
        dia5 = new javax.swing.JLabel();
        dia6 = new javax.swing.JLabel();
        dia7 = new javax.swing.JLabel();
        dia8 = new javax.swing.JLabel();
        dia9 = new javax.swing.JLabel();
        dia10 = new javax.swing.JLabel();
        dia11 = new javax.swing.JLabel();
        dia12 = new javax.swing.JLabel();
        dia13 = new javax.swing.JLabel();
        dia14 = new javax.swing.JLabel();
        dia15 = new javax.swing.JLabel();
        dia16 = new javax.swing.JLabel();
        dia17 = new javax.swing.JLabel();
        dia18 = new javax.swing.JLabel();
        dia19 = new javax.swing.JLabel();
        dia20 = new javax.swing.JLabel();
        dia21 = new javax.swing.JLabel();
        dia22 = new javax.swing.JLabel();
        dia23 = new javax.swing.JLabel();
        dia24 = new javax.swing.JLabel();
        dia25 = new javax.swing.JLabel();
        dia26 = new javax.swing.JLabel();
        dia27 = new javax.swing.JLabel();
        dia28 = new javax.swing.JLabel();
        dia29 = new javax.swing.JLabel();
        dia30 = new javax.swing.JLabel();
        dia31 = new javax.swing.JLabel();
        dia32 = new javax.swing.JLabel();
        dia33 = new javax.swing.JLabel();
        dia34 = new javax.swing.JLabel();
        dia35 = new javax.swing.JLabel();
        dia36 = new javax.swing.JLabel();
        dia37 = new javax.swing.JLabel();
        dia38 = new javax.swing.JLabel();
        dia39 = new javax.swing.JLabel();
        dia40 = new javax.swing.JLabel();
        dia41 = new javax.swing.JLabel();
        dia42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jcbMes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jcbMesFocusLost(evt);
            }
        });
        jcbMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMesActionPerformed(evt);
            }
        });

        jspAno.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspAnoStateChanged(evt);
            }
        });
        jspAno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jspAnoFocusLost(evt);
            }
        });

        sem1.setFont(sem1.getFont().deriveFont((sem1.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem1.setText("D");

        sem2.setFont(sem2.getFont().deriveFont((sem2.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem2.setText("S");

        sem3.setFont(sem3.getFont().deriveFont((sem3.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem3.setText("T");

        sem4.setFont(sem4.getFont().deriveFont((sem4.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem4.setText("Q");

        sem5.setFont(sem5.getFont().deriveFont((sem5.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem5.setText("Q");

        sem6.setFont(sem6.getFont().deriveFont((sem6.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem6.setText("S");

        sem7.setFont(sem7.getFont().deriveFont((sem7.getFont().getStyle() | java.awt.Font.ITALIC) | java.awt.Font.BOLD));
        sem7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sem7.setText("S");

        dia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia1.setText("1");
        dia1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia2.setText("1");
        dia2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia3.setText("1");
        dia3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia4.setText("1");
        dia4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia5.setText("1");
        dia5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia6.setText("1");
        dia6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia7.setText("1");
        dia7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia8.setText("1");
        dia8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia9.setText("1");
        dia9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia10.setText("1");
        dia10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia11.setText("1");
        dia11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia12.setText("1");
        dia12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia13.setText("1");
        dia13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia14.setText("1");
        dia14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia15.setText("1");
        dia15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia16.setText("1");
        dia16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia17.setText("1");
        dia17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia18.setText("1");
        dia18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia19.setText("1");
        dia19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia20.setText("1");
        dia20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia21.setText("1");
        dia21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia22.setText("1");
        dia22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia23.setText("1");
        dia23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia24.setText("1");
        dia24.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia25.setText("1");
        dia25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia26.setText("1");
        dia26.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia27.setText("1");
        dia27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia28.setText("1");
        dia28.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia29.setText("1");
        dia29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia30.setText("1");
        dia30.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia31.setText("1");
        dia31.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia32.setText("1");
        dia32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia33.setText("1");
        dia33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia34.setText("1");
        dia34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia35.setText("1");
        dia35.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia36.setText("1");
        dia36.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia37.setText("1");
        dia37.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia38.setText("1");
        dia38.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia39.setText("1");
        dia39.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia40.setText("1");
        dia40.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia41.setText("1");
        dia41.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        dia42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dia42.setText("1");
        dia42.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dia42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dia1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dia36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sem1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sem2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sem3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sem4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sem5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sem6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sem7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jspAno))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sem1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sem7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dia36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia37, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia38, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia39, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia42, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowDeactivated

    private void jcbMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMesActionPerformed
        try {
            atualiza();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jcbMesActionPerformed

    private void dia1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dia1MouseClicked
        try {
            if (jcbMes.getSelectedItem() == null || jspAno.getValue() == null) {
                return;
            }
            JLabel jlb = (JLabel) evt.getSource();
            if (jlb.getText().isEmpty()) {
                return;
            }
            if (editor.editavel()) {
                Integer mes = UtlTem.pegaMes(jcbMes.getSelectedItem().toString());
                Integer ano = UtlInt.pega(jspAno.getValue());
                Integer dia = UtlInt.pega(jlb.getText());
                Date valor = UtlTem.pega(dia, mes, ano);
                editor.mdVlr(valor);
            }
            editor.botaFoco();
            setVisible(false);
            dispose();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_dia1MouseClicked

    private void jspAnoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspAnoStateChanged
        try {
            atualiza();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jspAnoStateChanged

    private void jspAnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jspAnoFocusLost
        try {
            atualiza();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jspAnoFocusLost

    private void jcbMesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcbMesFocusLost
        try {
            atualiza();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
    }//GEN-LAST:event_jcbMesFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dia1;
    private javax.swing.JLabel dia10;
    private javax.swing.JLabel dia11;
    private javax.swing.JLabel dia12;
    private javax.swing.JLabel dia13;
    private javax.swing.JLabel dia14;
    private javax.swing.JLabel dia15;
    private javax.swing.JLabel dia16;
    private javax.swing.JLabel dia17;
    private javax.swing.JLabel dia18;
    private javax.swing.JLabel dia19;
    private javax.swing.JLabel dia2;
    private javax.swing.JLabel dia20;
    private javax.swing.JLabel dia21;
    private javax.swing.JLabel dia22;
    private javax.swing.JLabel dia23;
    private javax.swing.JLabel dia24;
    private javax.swing.JLabel dia25;
    private javax.swing.JLabel dia26;
    private javax.swing.JLabel dia27;
    private javax.swing.JLabel dia28;
    private javax.swing.JLabel dia29;
    private javax.swing.JLabel dia3;
    private javax.swing.JLabel dia30;
    private javax.swing.JLabel dia31;
    private javax.swing.JLabel dia32;
    private javax.swing.JLabel dia33;
    private javax.swing.JLabel dia34;
    private javax.swing.JLabel dia35;
    private javax.swing.JLabel dia36;
    private javax.swing.JLabel dia37;
    private javax.swing.JLabel dia38;
    private javax.swing.JLabel dia39;
    private javax.swing.JLabel dia4;
    private javax.swing.JLabel dia40;
    private javax.swing.JLabel dia41;
    private javax.swing.JLabel dia42;
    private javax.swing.JLabel dia5;
    private javax.swing.JLabel dia6;
    private javax.swing.JLabel dia7;
    private javax.swing.JLabel dia8;
    private javax.swing.JLabel dia9;
    private javax.swing.JComboBox<String> jcbMes;
    private javax.swing.JSpinner jspAno;
    private javax.swing.JLabel sem1;
    private javax.swing.JLabel sem2;
    private javax.swing.JLabel sem3;
    private javax.swing.JLabel sem4;
    private javax.swing.JLabel sem5;
    private javax.swing.JLabel sem6;
    private javax.swing.JLabel sem7;
    // End of variables declaration//GEN-END:variables
}
