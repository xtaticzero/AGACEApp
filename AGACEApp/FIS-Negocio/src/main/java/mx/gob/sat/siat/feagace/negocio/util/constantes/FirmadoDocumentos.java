package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;

public class FirmadoDocumentos {
    private static final Logger LOGGER = Logger.getLogger(FirmadoDocumentos.class);
    private String url;

    /**
     * Metodo firmandoDocumento
     * @param ordenSeleccionada
     * @param cadena
     * @param firma
     * Permite llamar el metodo para el firmado de los documentos de la orden
     */
    public void firmandoDocumento(final String ordenSeleccionada, final String cadena, final String firma) {
        try {
            escribirFirma(cadena, "Cadena Original:");
            escribirFirma(firma, "Firma digital:");

        } catch (IOException e) {
            LOGGER.error(ConstantesError.ERROR_ESCRIBIR_FIRMA, e);
        }
    }

    /**
     * Metodo escribirFirma
     * @param tipoDato
     * @param dato
     * Permite escribir la firma y la cadena en un documento .docx
     */
    private void escribirFirma(final String dato, final String tipoDato) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        HWPFDocument doc = null;

        try {
            in = new FileInputStream(this.url);
            doc = new HWPFDocument(in);


            Range range = doc.getRange();
            Paragraph paragraphEspacio = range.insertAfter(new ParagraphProperties(), 0);
            CharacterRun runEspacio = paragraphEspacio.insertAfter("  ");
            Paragraph paragraphTipoDato = runEspacio.insertAfter(new ParagraphProperties(), 0);
            CharacterRun runTipoDato = paragraphTipoDato.insertAfter(tipoDato);
            runTipoDato.setBold(true);
            Paragraph paragraphEspacioDos = runTipoDato.insertAfter(new ParagraphProperties(), 0);
            CharacterRun runEspacioDos = paragraphEspacioDos.insertAfter("  ");
            Paragraph paragraphDato = runEspacioDos.insertAfter(new ParagraphProperties(), 0);
            paragraphDato.insertAfter(dato);

            out = new FileOutputStream(url);
            doc.write(out);

            out.flush();
        } catch (FileNotFoundException e) {
            LOGGER.error(ConstantesError.ERROR_ENCONTAR_RUTA, e);
        } catch (IOException e) {
            LOGGER.error(ConstantesError.ERROR_ABRIR_ARCHIVO, e);
        } finally {
            
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
            }
            
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
            }
        }

    }

    /**
     * Metodo setUrl
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Metodo getUrl
     * @return String
     */
    public String getUrl() {
        return url;
    }
}
