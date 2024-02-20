package pin.libjan;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class PaiTabela extends JPanel {

    private JComponent componente;
    private GridBagConstraints modelo;
    private Integer indice;

    public PaiTabela() {
        super(new GridBagLayout());
        setFocusable(false);
        modelo = new GridBagConstraints();
        limpa();
    }

    private void limpa() {
        componente = null;
        indice = null;
        modelo.gridy = 0;
        modelo.gridx = 0;
        ancoraNO();
        preencheNao();
    }

    public PaiTabela terminaComDimensionavel() {
        preencheAmbos();
        bota();
        insere();
        preencheNao();
        return this;
    }

    public PaiTabela bota() {
        componente = new JPanel();
        modelo.gridy++;
        modelo.gridx = 0;
        return this;
    }

    public PaiTabela bota(JComponent oComponente) {
        componente = oComponente;
        modelo.gridy++;
        modelo.gridx = 0;
        return this;
    }

    public PaiTabela bota(JComponent oComponente, Integer naLinha) {
        componente = oComponente;
        modelo.gridy = naLinha;
        modelo.gridx = 0;
        return this;
    }

    public PaiTabela bota(JComponent oComponente, Integer naLinha, Integer eColuna) {
        componente = oComponente;
        modelo.gridy = naLinha;
        modelo.gridx = eColuna;
        return this;
    }

    public PaiTabela tamanho(int largura, int altura) {
        modelo.gridwidth = largura;
        modelo.gridheight = altura;
        return this;
    }

    public PaiTabela ancora(int ancora) {
        modelo.anchor = ancora;
        return this;
    }

    public PaiTabela ancoraN() {
        modelo.anchor = GridBagConstraints.NORTH;
        return this;
    }

    public PaiTabela ancoraNE() {
        modelo.anchor = GridBagConstraints.NORTHEAST;
        return this;
    }

    public PaiTabela ancoraNO() {
        modelo.anchor = GridBagConstraints.NORTHWEST;
        return this;
    }

    public PaiTabela ancoraL() {
        modelo.anchor = GridBagConstraints.EAST;
        return this;
    }

    public PaiTabela ancoraS() {
        modelo.anchor = GridBagConstraints.SOUTH;
        return this;
    }

    public PaiTabela ancoraSE() {
        modelo.anchor = GridBagConstraints.SOUTHEAST;
        return this;
    }

    public PaiTabela ancoraSO() {
        modelo.anchor = GridBagConstraints.SOUTHWEST;
        return this;
    }

    public PaiTabela ancoraO() {
        modelo.anchor = GridBagConstraints.WEST;
        return this;
    }

    public PaiTabela ancoraCentro() {
        modelo.anchor = GridBagConstraints.CENTER;
        return this;
    }

    public PaiTabela ancoraRelativa() {
        modelo.anchor = GridBagConstraints.RELATIVE;
        return this;
    }

    public PaiTabela ancoraProximo() {
        modelo.anchor = GridBagConstraints.REMAINDER;
        return this;
    }

    public PaiTabela ancoraPaginaIni() {
        modelo.anchor = GridBagConstraints.PAGE_START;
        return this;
    }

    public PaiTabela ancoraPaginaFim() {
        modelo.anchor = GridBagConstraints.PAGE_END;
        return this;
    }

    public PaiTabela ancoraLinhaIni() {
        modelo.anchor = GridBagConstraints.LINE_START;
        return this;
    }

    public PaiTabela ancoraLinhaFim() {
        modelo.anchor = GridBagConstraints.LINE_END;
        return this;
    }

    public PaiTabela ancoraPrimeiraLinhaIni() {
        modelo.anchor = GridBagConstraints.FIRST_LINE_START;
        return this;
    }

    public PaiTabela ancoraPrimeiraLinhaFim() {
        modelo.anchor = GridBagConstraints.FIRST_LINE_END;
        return this;
    }

    public PaiTabela ancoraUltimaLinhaIni() {
        modelo.anchor = GridBagConstraints.LAST_LINE_START;
        return this;
    }

    public PaiTabela ancoraUltimaLinhaFim() {
        modelo.anchor = GridBagConstraints.LAST_LINE_END;
        return this;
    }

    public PaiTabela botaAncorLinhaBase() {
        modelo.anchor = GridBagConstraints.BASELINE;
        return this;
    }

    public PaiTabela ancoraLinhaBaseEmpurra() {
        modelo.anchor = GridBagConstraints.BASELINE_LEADING;
        return this;
    }

    public PaiTabela ancoraLinhaBasePuxa() {
        modelo.anchor = GridBagConstraints.BASELINE_TRAILING;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAcima() {
        modelo.anchor = GridBagConstraints.ABOVE_BASELINE;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAcimaEmpurra() {
        modelo.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAcimaPuxa() {
        modelo.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAbaixo() {
        modelo.anchor = GridBagConstraints.BELOW_BASELINE;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAbaixoEmpurra() {
        modelo.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
        return this;
    }

    public PaiTabela ancoraLinhaBaseAbaixoPuxa() {
        modelo.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        return this;
    }

    public PaiTabela preenche(int preenchimento) {
        modelo.fill = preenchimento;
        return this;
    }

    public PaiTabela preencheNao() {
        modelo.fill = GridBagConstraints.NONE;
        return engrossaNao();
    }

    public PaiTabela preencheVertical() {
        modelo.fill = GridBagConstraints.VERTICAL;
        return engrossa(0, 1);
    }

    public PaiTabela preencheHorizontal() {
        modelo.fill = GridBagConstraints.HORIZONTAL;
        return engrossa(1, 0);
    }

    public PaiTabela preencheAmbos() {
        modelo.fill = GridBagConstraints.BOTH;
        return engrossa();
    }

    public PaiTabela engrossa(double naLargura, double eAltura) {
        modelo.weightx = naLargura;
        modelo.weighty = eAltura;
        return this;
    }

    public PaiTabela engrossa() {
        modelo.weightx = 1;
        modelo.weighty = 1;
        return this;
    }

    public PaiTabela engrossaNao() {
        modelo.weightx = 0;
        modelo.weighty = 0;
        return this;
    }

    public PaiTabela margem(int distancia) {
        modelo.insets = new java.awt.Insets(
                distancia, distancia, distancia, distancia);
        return this;
    }

    public PaiTabela margem(int superior, int esquerda, int inferior, int direita) {
        modelo.insets = new java.awt.Insets(superior, esquerda, inferior, direita);
        return this;
    }

    public PaiTabela espaco(int naLargura, int eAltura) {
        modelo.ipadx = naLargura;
        modelo.ipady = eAltura;
        return this;
    }

    public PaiTabela insere() {
        if (indice == null) {
            add(componente, modelo);
        } else {
            add(componente, modelo, indice.intValue());
        }
        return this;
    }

}
