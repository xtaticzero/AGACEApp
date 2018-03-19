package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public final class CrearInsumoHelper {

    private static final Logger LOG = Logger.getLogger(CrearInsumoHelper.class);

    public static final String TEMPLATE_REGISTRO = "ES<%area%><%yyyy%><%consecutivo%>";
    public static final String TEMPLATE_AREA = "<%area%>";
    public static final String TEMPLATE_ANIO = "<%yyyy%>";
    public static final String TEMPLATE_CONSECUTIVO = "<%consecutivo%>";
    public static final int CUATRO_DIGITOS = 4;
    public static final int DOS_DIGITOS = 2;

    private CrearInsumoHelper() {
        super();
    }

    public static String getIdRegistro(BigDecimal area, BigDecimal consecutivo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TEMPLATE_REGISTRO);
        String idRegistro = stringBuilder.toString();
        idRegistro = idRegistro.replace(TEMPLATE_AREA, getDigist(area, DOS_DIGITOS));
        idRegistro = idRegistro.replace(TEMPLATE_ANIO, getAnio());
        idRegistro = idRegistro.replace(TEMPLATE_CONSECUTIVO, getDigist(consecutivo, CUATRO_DIGITOS));
        return idRegistro;
    }

    private static String getDigist(BigDecimal valor, int digitos) {
        return String.format(new StringBuilder().append("%0").append(digitos).append("d").toString(), valor.intValue());
    }

    private static String getAnio() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    public static void actualizaArchivo(String rutaAnterior, String rutaDestino, String nombreArchivo)
            throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;
        String rutaActual = rutaAnterior.replace(nombreArchivo, "");

        crearDestino(rutaDestino);

        File actualFile = new File(rutaActual.concat(nombreArchivo));
        File newFile = new File(rutaDestino.concat(nombreArchivo));
        try {
            inStream = new FileInputStream(actualFile);
            outStream = new FileOutputStream(newFile);

            byte[] buffer = new byte[Constantes.BYTE];
            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
        } finally {
            if (inStream != null) {
                IOUtils.closeQuietly(inStream);
            }
            if (outStream != null) {
                try {
                    outStream.flush();
                    outStream.close();
                } catch (Exception e) {
                    LOG.error(e.getCause());
                    IOUtils.closeQuietly(outStream);
                }

            }
        }
    }

    private static void crearDestino(final String destino) {
        File folder = new File(destino);
        if (!folder.mkdirs()) {
            LOG.error("No se creo el directorio destino: ".concat(destino));
        }

    }

    public static void borrarDirectorioTemporal(String rutaActual) {
        if (rutaActual != null && !rutaActual.isEmpty() && rutaActual.indexOf("SIN_FOLIO") < 0) {
            String ruta = rutaActual;
            short index = 0;
            while (index <= 1) {
                ruta = ruta.substring(0, ruta.lastIndexOf('/'));
                File actualFile = new File(ruta);
                if (actualFile.exists() && actualFile.isDirectory() && actualFile.list().length == 0
                        && actualFile.delete()) {
                    index++;
                } else {
                    break;
                }
            }
        }

    }

}
