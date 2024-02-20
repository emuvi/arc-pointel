package pin.libutl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;
import pin.libima.Salva;
import pin.libout.CompoundIcon;
import pin.libout.Scale;
import pin.libout.TextIcon;
import pin.libpic.Pics;
import pin.libvlr.VArq;
import pin.libvlr.VIma;
import pin.libvlr.Vlr;

public class UtlIma {

    public static Boolean ehExtensao(String aExtensao) {
        aExtensao = aExtensao.toLowerCase();
        return UtlLis.tem(aExtensao, pegaExtensoes());
    }

    public static String[] pegaExtensoes() {
        return new String[]{"jpg", "jpeg", "bmp", "gif", "png", "wbmp", "ico"};
    }

    public static String pegaExtensoesDescricao() {
        return "Imagens (*.jpg, *.jpeg, *.bmp, *.gif, *.png, *.wbmp, *.ico)";
    }

    public static Boolean eh(Object oValor) {
        Boolean retorno = false;
        if (oValor instanceof BufferedImage) {
            retorno = true;
        } else if (oValor instanceof VIma) {
            retorno = true;
        } else if (oValor instanceof File) {
            retorno = ehExtensao(UtlArq.pExtensao(((File) oValor).getAbsolutePath()));
        } else if (oValor instanceof VArq) {
            retorno = ehExtensao(UtlArq.pExtensao(((VArq) oValor).pegaNome()));
        }
        return retorno;
    }

    public static BufferedImage pega(Object doValor) {
        return pega(doValor, null);
    }

    public static BufferedImage pega(Object doValor, BufferedImage comPadrao) {
        BufferedImage retorno = comPadrao;
        if (doValor instanceof VArq) {
            doValor = ((VArq) doValor).pgArq();
        } else if (doValor instanceof VIma) {
            doValor = ((VIma) doValor).pgIma();
        } else if (doValor instanceof Vlr) {
            doValor = ((Vlr) doValor).pg();
        }
        if (doValor instanceof byte[]) {
            retorno = pega((byte[]) doValor, comPadrao);
        } else if (doValor instanceof File) {
            retorno = pega((File) doValor, comPadrao);
        } else if (doValor instanceof String) {
            retorno = UtlIma.descrito((String) doValor, comPadrao);
        } else if (doValor instanceof BufferedImage) {
            retorno = (BufferedImage) doValor;
        } else if (doValor instanceof Icon) {
            retorno = new BufferedImage(((Icon) doValor).getIconWidth(), ((Icon) doValor).getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) retorno.getGraphics();
            ((Icon) doValor).paintIcon(null, g2d, 0, 0);
            g2d.dispose();
        }
        return retorno;
    }

    public static BufferedImage pega(ImageIcon doImagemIcone) {
        BufferedImage retorno = new BufferedImage(doImagemIcone.getIconWidth(), doImagemIcone.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = retorno.createGraphics();
        doImagemIcone.paintIcon(null, g, 0, 0);
        g.dispose();
        return retorno;
    }

    public static BufferedImage pega(byte[] doValor) {
        return pega(doValor, null);
    }

    public static BufferedImage pega(byte[] doValor, BufferedImage comPadrao) {
        BufferedImage retorno = comPadrao;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(doValor);
            retorno = ImageIO.read(bais);
            bais.close();
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static BufferedImage pega(File doArquivo) {
        return pega(doArquivo, null);
    }

    public static BufferedImage pega(File doArquivo, BufferedImage comPadrao) {
        BufferedImage retorno = comPadrao;
        try {
            retorno = ImageIO.read(doArquivo);
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static BufferedImage descrito(String dosCaracteres) {
        return UtlIma.descrito(dosCaracteres, null);
    }

    public static BufferedImage descrito(String dosCaracteres, BufferedImage comPadrao) {
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
            return ImageIO.read(new ByteArrayInputStream(UtlArq.pegaB64(dosCaracteres)));
        } catch (Exception ex) {
            return comPadrao;
        }
    }

    public static String descreve(BufferedImage oValor) {
        return descreve(oValor, null);
    }

    public static String descreve(BufferedImage oValor, String comPadrao) {
        String retorno = comPadrao;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(oValor, "PNG", baos);
            retorno = UtlArq.formataB64(baos.toByteArray());
        } catch (Exception ex) {
            Utl.registra(ex);
        }
        return retorno;
    }

    public static String formata(BufferedImage oValor) {
        return formata(oValor, null);
    }

    public static String formata(BufferedImage oValor, String comPadrao) {
        String retorno = comPadrao;
        if (oValor != null) {
            retorno = "Imagem";
        }
        return retorno;
    }

    public static BufferedImage nova(Integer comLargura, Integer eAltura) {
        BufferedImage retorno = new BufferedImage(comLargura, eAltura, BufferedImage.TYPE_INT_ARGB);
        return retorno;
    }

    public static BufferedImage nova(Color naCor, Integer comLargura, Integer eAltura) {
        BufferedImage retorno = new BufferedImage(comLargura, eAltura, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = retorno.createGraphics();
        g.setColor(naCor);
        g.fillRect(0, 0, comLargura, eAltura);
        return retorno;
    }

    public static BufferedImage copia(BufferedImage de) {
        if (de == null) {
            return null;
        }
        ColorModel cm = de.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = de.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static BufferedImage copiaParaARGB(BufferedImage de) {
        if (de == null) {
            return null;
        }
        BufferedImage retorno = new BufferedImage(de.getWidth(), de.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = retorno.createGraphics();
        g.drawImage(de, 0, 0, de.getWidth(), de.getHeight(), null);
        g.dispose();
        return retorno;
    }

    public static List<Rectangle> demarca(BufferedImage aImagem) {
        return demarca(aImagem, 200);
    }

    public static List<Rectangle> demarca(BufferedImage aImagem, Integer comTamanho) {
        List<Rectangle> retorno = null;
        if (aImagem != null) {
            retorno = new ArrayList<>();
            int quantL = aImagem.getWidth() / comTamanho + (aImagem.getWidth() % comTamanho == 0 ? 0 : 1);
            int quantA = aImagem.getHeight() / comTamanho + (aImagem.getWidth() % comTamanho == 0 ? 0 : 1);
            int tamL = aImagem.getWidth() / quantL + (aImagem.getWidth() % quantL == 0 ? 0 : 1);
            int tamA = aImagem.getHeight() / quantA + (aImagem.getHeight() % quantA == 0 ? 0 : 1);
            int lin = 0;
            int col = 0;
            while (true) {
                Rectangle rct = new Rectangle(col * tamL, lin * tamA, tamL, tamA);
                if (rct.x + rct.width >= aImagem.getWidth()) {
                    rct.width = aImagem.getWidth() - rct.x;
                    col = -1;
                    lin++;
                }
                if (rct.y + rct.height > aImagem.getHeight()) {
                    rct.height = aImagem.getHeight() - rct.y;
                    if (rct.height <= 0) {
                        break;
                    }
                }
                retorno.add(rct);
                col++;
            }
        }
        return retorno;
    }

    public static File abreArq() {
        return abreArq("Seleciona Imagem");
    }

    public static File abreArq(String comTitulo) {
        File retorno = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(comTitulo);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return UtlLis.tem(UtlArq.pExtensao(f.getName()), pegaExtensoes());
                }
            }

            @Override
            public String getDescription() {
                return pegaExtensoesDescricao();
            }
        };
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            retorno = chooser.getSelectedFile();
        }
        return retorno;
    }

    public static BufferedImage pergunta() {
        return pergunta("Seleciona Imagem");
    }

    public static BufferedImage pergunta(String comTitulo) {
        BufferedImage retorno = null;
        File arq = abreArq(comTitulo);
        if (arq != null) {
            retorno = pega(arq);
        }
        return retorno;
    }

    public static byte[] pegaJPGBytes(BufferedImage daImagem, Double comQualidade) throws Exception {
        byte[] retorno = null;
        BufferedImage image = daImagem;
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(comQualidade.floatValue());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        jpgWriter.setOutput(ios);
        IIOImage outputImage = new IIOImage(image, null, null);
        jpgWriter.write(null, outputImage, jpgWriteParam);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        jpgWriter.dispose();
        retorno = imageInByte;
        return retorno;
    }

    public static byte[] pegaTela(Double comQualidade) throws Exception {
        BufferedImage image = UtlRob.pegaTela();
        return pegaJPGBytes(image, comQualidade);
    }

    public static BufferedImage corta(BufferedImage aImagem, Integer aPos1X, Integer aPos1Y, Integer aPos2X, Integer aPos2Y) {
        Integer largura = aPos2X - aPos1X;
        Integer altura = aPos2Y - aPos1Y;
        BufferedImage nov = new BufferedImage(largura, altura, aImagem.getType());
        Graphics2D g = nov.createGraphics();
        g.drawImage(aImagem, 0, 0, largura, altura, aPos1X, aPos1Y, aPos2X, aPos2Y, null);
        return nov;
    }

    public static Boolean corta(File aArquivo, Double aQualidade, Integer aPos1X, Integer aPos1Y, Integer aPos2X, Integer aPos2Y) throws Exception {
        Boolean retorno = false;
        BufferedImage img = ImageIO.read(aArquivo);
        BufferedImage nov = corta(img, aPos1X, aPos1Y, aPos2X, aPos2Y);
        Salva.faz(nov, aQualidade, aArquivo);
        retorno = true;
        return retorno;
    }

    public static Boolean cortaMargens(File aArquivo, String aReferenciaEsquerda, String aReferenciaDireita, Double aSensibilidadePer, Double aQualidade) throws Exception {
        BufferedImage img = ImageIO.read(aArquivo);
        int xSup = img.getWidth() / 2;
        int xInf = img.getWidth() / 2;
        int yEsq = 0;
        int yDir = 0;
        if (aReferenciaEsquerda.equals("Centro")) {
            yEsq = img.getHeight() / 2;
        } else if (aReferenciaEsquerda.equals("Inferior")) {
            yEsq = img.getHeight() - 1;
        }
        if (aReferenciaDireita.equals("Centro")) {
            yDir = img.getHeight() / 2;
        } else if (aReferenciaDireita.equals("Inferior")) {
            yDir = img.getHeight() - 1;
        }
        int ySup = 0;
        int yInf = img.getHeight() - 1;
        int xEsq = 0;
        int xDir = img.getWidth() - 1;
        int iSup = 1;
        while (true) {
            if (iSup >= (img.getHeight() - 10) / 2) {
                break;
            }
            Color clSupO = new Color(img.getRGB(xSup, ySup));
            Color clSupI = new Color(img.getRGB(xSup, ySup + iSup));
            if (UtlCor.diferencaPer(clSupO, clSupI) <= aSensibilidadePer) {
                iSup++;
            } else {
                break;
            }
        }
        int iInf = 1;
        while (true) {
            if (iInf >= (img.getHeight() - 10) / 2) {
                break;
            }
            Color clInfO = new Color(img.getRGB(xInf, yInf));
            Color clInfI = new Color(img.getRGB(xInf, yInf - iInf));
            if (UtlCor.diferencaPer(clInfO, clInfI) <= aSensibilidadePer) {
                iInf++;
            } else {
                break;
            }
        }
        Double dps = img.getWidth() * (aSensibilidadePer / 10.0);
        int ips = dps.intValue();
        if (aReferenciaEsquerda.equals("Superior")) {
            yEsq = yEsq + (iSup + ips);
        } else if (aReferenciaEsquerda.equals("Inferior")) {
            yEsq = yEsq - (iInf + ips);
        }
        if (aReferenciaDireita.equals("Superior")) {
            yDir = yDir + (iSup + ips);
        } else if (aReferenciaDireita.equals("Inferior")) {
            yDir = yDir - (iInf + ips);
        }
        int iEsq = 1;
        while (true) {
            if (iEsq >= (img.getWidth() - 10) / 2) {
                break;
            }
            Color clEsqO = new Color(img.getRGB(xEsq, yEsq));
            Color clEsqI = new Color(img.getRGB(xEsq + iEsq, yEsq));
            if (UtlCor.diferencaPer(clEsqO, clEsqI) <= aSensibilidadePer) {
                iEsq++;
            } else {
                break;
            }
        }
        int iDir = 1;
        while (true) {
            if (iDir >= (img.getWidth() - 10) / 2) {
                break;
            }
            Color clDirO = new Color(img.getRGB(xDir, yDir));
            Color clDirI = new Color(img.getRGB(xDir - iDir, yDir));
            if (UtlCor.diferencaPer(clDirO, clDirI) <= aSensibilidadePer) {
                iDir++;
            } else {
                break;
            }
        }
        int cutSup = 0;
        int cutInf = img.getHeight();
        int cutEsq = 0;
        int cutDir = img.getWidth();
        cutSup = cutSup + iSup;
        cutInf = cutInf - iInf;
        cutEsq = cutEsq + iEsq;
        cutDir = cutDir - iDir;
        corta(img, cutEsq, cutSup, cutDir, cutInf);
        return true;
    }

    public static BufferedImage pegaParte(BufferedImage daImagem, Rectangle daArea) {
        BufferedImage retorno = nova(daArea.width, daArea.height);
        Graphics2D g = (Graphics2D) retorno.getGraphics();
        int totX = daArea.x + daArea.width;
        int totY = daArea.y + daArea.height;
        int xn = 0;
        int yn = 0;
        for (int x = daArea.x; x < totX; x++) {
            for (int y = daArea.y; y < totY; y++) {
                if (x < daImagem.getWidth() && y < daImagem.getHeight()) {
                    g.setColor(new Color(daImagem.getRGB(x, y)));
                    g.fillRect(xn, yn, 1, 1);
                }
                yn++;
            }
            yn = 0;
            xn++;
        }
        return retorno;
    }

    public static Float diferencaPer(BufferedImage daImagem, BufferedImage comImagem) {
        Float retorno = 0f;
        int maxLarg = Math.max(daImagem.getWidth(), comImagem.getWidth());
        int maxAltu = Math.max(daImagem.getHeight(), comImagem.getHeight());
        Float difPorPixel = 100f / (maxLarg * maxAltu);
        for (int x = 0; x < maxLarg; x++) {
            for (int y = 0; y < maxAltu; y++) {
                if (x < daImagem.getWidth() && y < daImagem.getHeight() && x < comImagem.getWidth() && y < comImagem.getHeight()) {
                    retorno += UtlCor.diferencaPer(new Color(daImagem.getRGB(x, y)), new Color(comImagem.getRGB(x, y))) / 100 * difPorPixel;
                } else {
                    retorno += difPorPixel;
                }
            }
        }
        return retorno;
    }

    public static Boolean temCorNoPonto(BufferedImage naImagem, Color aCor, Float comTolerancia, Point noPonto) {
        return UtlCor.diferencaPer(new Color(naImagem.getRGB(noPonto.x, noPonto.y)), aCor) <= comTolerancia;
    }

    public static Boolean temCorNaArea(BufferedImage naImagem, Color aCor, Float comTolerancia, Rectangle naArea) {
        int totX = naArea.x + naArea.width;
        int totY = naArea.y + naArea.height;
        for (int x = naArea.x; x < totX; x++) {
            for (int y = naArea.y; y < totY; y++) {
                if (temCorNoPonto(naImagem, aCor, comTolerancia, new Point(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean temImagemNoPonto(BufferedImage naImagem, BufferedImage aImagem, Float comTolerancia, Point noPonto) {
        BufferedImage parteAnalisada = pegaParte(naImagem, new Rectangle(noPonto.x, noPonto.y, aImagem.getWidth(), aImagem.getHeight()));
        return diferencaPer(parteAnalisada, aImagem) <= comTolerancia;
    }

    public static Boolean temImagemNaArea(BufferedImage naImagem, BufferedImage aImagem, Float comTolerancia, Rectangle naArea) {
        int totX = naArea.x + naArea.width;
        int totY = naArea.y + naArea.height;
        for (int x = naArea.x; x < totX; x++) {
            for (int y = naArea.y; y < totY; y++) {
                if (temImagemNoPonto(naImagem, aImagem, comTolerancia, new Point(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean temImagemNaImagem(BufferedImage naImagem, BufferedImage aImagem, Float comTolerancia) {

        return false;
    }

    public static ImageIcon redimensiona(ImageIcon naImagem, Integer comLargura, Integer eAltura) {
        BufferedImage bi = new BufferedImage(naImagem.getIconWidth(), naImagem.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        naImagem.paintIcon(null, g, 0, 0);
        g.dispose();
        return new ImageIcon(UtlIma.redimensiona(bi, comLargura, eAltura));
    }

    public static BufferedImage redimensiona(BufferedImage naImagem, Integer comLargura, Integer eAltura) {
        return UtlIma.redimensiona(naImagem, comLargura, eAltura, true);
    }

    public static BufferedImage redimensiona(BufferedImage naImagem, Integer comLargura, Integer eAltura, Boolean melhorQualidade) {
        if (melhorQualidade) {
            return Scale.resize(naImagem, Scale.Method.ULTRA_QUALITY, Scale.Mode.FIT_EXACT, comLargura, eAltura);
        } else {
            return Scale.resize(naImagem, Scale.Method.AUTOMATIC, Scale.Mode.FIT_EXACT, comLargura, eAltura);
        }
    }

    public static Boolean redimensiona(File oArquivo, Double naQualidade, Integer comLargura, Integer eAltura) throws Exception {
        BufferedImage img = ImageIO.read(oArquivo);
        img = UtlIma.redimensiona(img, comLargura, eAltura);
        Salva.faz(img, naQualidade, oArquivo);
        return true;
    }

    public static void botaPic(JComponent noComponente, String comNome, String eTitulo) {
        try {
            CompoundIcon icone = new CompoundIcon(CompoundIcon.Axis.Y_AXIS, 3, Pics.pega(comNome), new TextIcon(noComponente, eTitulo));
            if (noComponente instanceof JButton) {
                ((JButton) noComponente).setIcon(icone);
                ((JButton) noComponente).setFocusPainted(false);
            } else if (noComponente instanceof JLabel) {
                ((JLabel) noComponente).setIcon(icone);
            }
        } catch (Exception ex) {
            Utl.imp("Erro ao Botar Pim " + comNome + ": " + ex.getMessage());
        }
    }

    public static void botaPic(JComponent noComponente, String comNome) {
        try {
            ImageIcon icone = Pics.pega(comNome);
            if (noComponente.getClass().getName().equals("javax.swing.JButton")) {
                ((JButton) noComponente).setIcon(icone);
                ((JButton) noComponente).setFocusPainted(false);
            } else if (noComponente.getClass().getName().equals("javax.swing.JLabel")) {
                ((JLabel) noComponente).setIcon(icone);
            }
        } catch (Exception ex) {
            Utl.imp("Erro ao Botar Pim " + comNome + ": " + ex.getMessage());
        }
    }

}
