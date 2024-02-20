package pin.modrec;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import pin.libutl.Utl;
import pin.libutl.UtlTex;

public class XMLReg {

    private final XStream motor;

    public XMLReg() {
        motor = new XStream();
        XStream.setupDefaultSecurity(motor);
        motor.allowTypesByWildcard(new String[]{
            "pin.**"
        });
        motor.registerConverter(new BufferedImageConversor());
    }

    public static XMLReg novo() {
        return new XMLReg();
    }

    public XMLReg omiti(Class daClasse, String oCampo) {
        motor.omitField(daClasse, oCampo);
        return this;
    }

    public String pReg(Object doValor) {
        String retorno = null;
        if (doValor != null) {
            retorno = motor.toXML(doValor);
        }
        return retorno;
    }

    public XMLReg salvaReg(Object doValor, File noArquivo) throws Exception {
        UtlTex.salva(pReg(doValor), noArquivo);
        return this;
    }

    public Object pValor(File doArquivo) throws Exception {
        return pValor(UtlTex.abre(doArquivo));
    }

    public Object pValor(String dosCaracteres) {
        Object retorno = null;
        if (dosCaracteres != null) {
            if (!dosCaracteres.isEmpty()) {
                retorno = motor.fromXML(dosCaracteres);
            }
        }
        return retorno;
    }

    class BufferedImageConversor implements Converter {

        @Override
        public boolean canConvert(Class type) {
            return type.isAssignableFrom(BufferedImage.class);
        }

        @Override
        public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
            BufferedImage bufferedImage = (BufferedImage) source;
            try {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
                ImageIO.write(bufferedImage, "png", objectOutputStream);
                outStream.flush();
                Base64Encoder encorder = new Base64Encoder();
                String imageString = encorder.encode(outStream.toByteArray());
                outStream.close();
                writer.setValue(imageString);
            } catch (Exception ex) {
                Utl.registra(ex);
            }

        }

        @Override
        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
            BufferedImage image = null;
            try {
                Base64Encoder encoder = new Base64Encoder();
                byte[] imageBytes = encoder.decode(reader.getValue());
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(imageBytes));
                image = ImageIO.read(inputStream);
                inputStream.close();
            } catch (Exception ex) {
                Utl.registra(ex);
            }
            return image;
        }
    }
}
