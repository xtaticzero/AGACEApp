/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author sergio.vaca
 *
 */
public final class RutaUtil {
    

    private static final Logger LOG = Logger.getLogger(RutaUtil.class);
    private static final String SIAT_FECE_CONFIGURACION = "/siat/fece/configuracion/fece.properties";
    private static final String RUTA_NAS = "ruta.nas";
    
    private static String origenRuta = "/siat";
    
    static {
        FileInputStream input = null;
        try {
            input = new FileInputStream(SIAT_FECE_CONFIGURACION); 
            Properties properties = new Properties();
            properties.load(input);
            String origen = properties.getProperty(RUTA_NAS);
            if (origen != null && !origen.isEmpty()) {
                origenRuta = origen;
            }
        } catch (IOException e) {
            LOG.error("Error al inicializar la ruta. ", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("error al liberar el recurso.", e);
                }
            }
        }
    }

    private RutaUtil() {
        super();
    }
    
    public static String getOrigenRuta() {
        return origenRuta;
    }
}
