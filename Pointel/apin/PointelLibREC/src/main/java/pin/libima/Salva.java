package pin.libima;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import pin.libutl.UtlArq;

public class Salva {

    public static Boolean faz(BufferedImage aImagem, Double naQualidade, File noArquivo) throws Exception {
        Boolean retorno = false;
        String ext = UtlArq.pExtensao(noArquivo.getName());
        if (ext.equals("jpg") || ext.equals("jpeg")) {
            retorno = fazJPG(aImagem, naQualidade, noArquivo);
        } else {
            retorno = ImageIO.write(aImagem, UtlArq.pExtensao(noArquivo.getName()), noArquivo);
        }
        return retorno;
    }

    public static Boolean fazJPG(BufferedImage aImagem, Double naQualidade, File noArquivo) throws Exception {
        ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(naQualidade.floatValue() / 100);
        jpgWriter.setOutput(new FileImageOutputStream(noArquivo));
        IIOImage outputImage = new IIOImage(aImagem, null, null);
        jpgWriter.write(null, outputImage, jpgWriteParam);
        jpgWriter.dispose();
        return true;
    }

}
