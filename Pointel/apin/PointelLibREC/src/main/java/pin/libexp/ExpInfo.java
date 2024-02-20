package pin.libexp;

import pin.libjan.Janelas;
import pin.libutl.Utl;
import pin.libutl.UtlArq;
import pin.libutl.UtlDatHor;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class ExpInfo extends javax.swing.JFrame {

    public File arquivo;

    public ExpInfo(File doArquivo) {
        initComponents();
        arquivo = doArquivo;

        try {
            jtfNome.setText(arquivo.getName());
            jtfTamanho.setText(UtlArq.formataTamanho(arquivo.length()));
            jtfCaminho.setText(arquivo.getParent());
            BasicFileAttributes attr = Files.readAttributes(arquivo.toPath(), BasicFileAttributes.class);
            jtfCriacao.setText(UtlDatHor.formata(new Date(attr.creationTime().toMillis())));
            jtfModificacao.setText(UtlDatHor.formata(new Date(arquivo.lastModified())));
            jckLer.setSelected(arquivo.canRead());
            jckEscrever.setSelected(arquivo.canWrite());
            jckExecutar.setSelected(arquivo.canExecute());
        } catch (Exception ex) {
            Utl.registra(ex);
        }

        Janelas.inicia(this, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbNome = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jlbTamanho = new javax.swing.JLabel();
        jtfTamanho = new javax.swing.JTextField();
        jlbCaminho = new javax.swing.JLabel();
        jtfCaminho = new javax.swing.JTextField();
        jlbCriacao = new javax.swing.JLabel();
        jtfCriacao = new javax.swing.JTextField();
        jlbModificacao = new javax.swing.JLabel();
        jtfModificacao = new javax.swing.JTextField();
        jlbPermissoes = new javax.swing.JLabel();
        jbtSalvar = new javax.swing.JButton();
        jbtFechar = new javax.swing.JButton();
        jckLer = new javax.swing.JCheckBox();
        jckEscrever = new javax.swing.JCheckBox();
        jckExecutar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informações do Arquivo");
        setResizable(false);

        jlbNome.setText("Nome");

        jlbTamanho.setText("Tamanho");

        jtfTamanho.setEditable(false);

        jlbCaminho.setText("Caminho");

        jlbCriacao.setText("Criação");

        jtfCriacao.setEditable(false);

        jlbModificacao.setText("Modificação");

        jlbPermissoes.setText("Permissões");

        jbtSalvar.setText("Salvar");
        jbtSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSalvarActionPerformed(evt);
            }
        });

        jbtFechar.setText("Fechar");
        jbtFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFecharActionPerformed(evt);
            }
        });

        jckLer.setText("Ler");

        jckEscrever.setText("Escrever");

        jckExecutar.setText("Executar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfCaminho)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbNome)
                            .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbTamanho)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jtfTamanho)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbCriacao)
                            .addComponent(jtfCriacao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfModificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbModificacao)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbCaminho)
                            .addComponent(jlbPermissoes)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jckLer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jckEscrever)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jckExecutar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtFechar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlbTamanho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfTamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbCaminho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbCriacao)
                            .addComponent(jlbModificacao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfCriacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jtfModificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbPermissoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckLer)
                    .addComponent(jckEscrever)
                    .addComponent(jckExecutar))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtFechar)
                    .addComponent(jbtSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSalvarActionPerformed
        if (jckExecutar.isSelected() != arquivo.canExecute()) {
            arquivo.setExecutable(jckExecutar.isSelected());
        }

        if (jckEscrever.isSelected() != arquivo.canWrite()) {
            arquivo.setWritable(jckEscrever.isSelected());
        }

        if (jckLer.isSelected() != arquivo.canRead()) {
            arquivo.setReadable(jckLer.isSelected());
        }

        Date dtLM = UtlDatHor.pega(jtfModificacao.getText());
        if (dtLM != null) {
            if (dtLM.getTime() != arquivo.lastModified()) {
                arquivo.setLastModified(dtLM.getTime());
            }
        }

        if ((!arquivo.getName().equals(jtfNome.getText())) || (!arquivo.getParent().equals(jtfCaminho.getText()))) {
            arquivo.renameTo(new File(jtfCaminho.getText() + File.separator + jtfNome.getText()));
        }

        Janelas.fecha(this);
    }//GEN-LAST:event_jbtSalvarActionPerformed

    private void jbtFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFecharActionPerformed
        Janelas.fecha(this);
    }//GEN-LAST:event_jbtFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtFechar;
    private javax.swing.JButton jbtSalvar;
    private javax.swing.JCheckBox jckEscrever;
    private javax.swing.JCheckBox jckExecutar;
    private javax.swing.JCheckBox jckLer;
    private javax.swing.JLabel jlbCaminho;
    private javax.swing.JLabel jlbCriacao;
    private javax.swing.JLabel jlbModificacao;
    private javax.swing.JLabel jlbNome;
    private javax.swing.JLabel jlbPermissoes;
    private javax.swing.JLabel jlbTamanho;
    private javax.swing.JTextField jtfCaminho;
    private javax.swing.JTextField jtfCriacao;
    private javax.swing.JTextField jtfModificacao;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfTamanho;
    // End of variables declaration//GEN-END:variables
}
