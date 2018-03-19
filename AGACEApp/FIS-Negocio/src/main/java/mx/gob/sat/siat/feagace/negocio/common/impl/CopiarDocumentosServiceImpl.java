package mx.gob.sat.siat.feagace.negocio.common.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.common.CopiarDocumentosService;

@Service("copiarDocumentosService")
public class CopiarDocumentosServiceImpl extends BaseBusinessServices implements CopiarDocumentosService {

    /**
     *
     */
    private static final long serialVersionUID = -4602586987774448816L;
    private static final int SIZE_ARRAY = 1024;

    @Override
    public void copiarAnexos(String urlOrigen, String urlDestino) {

        File archivoOrigen = new File(urlOrigen);
        OutputStream out = null;
        InputStream in = null;

        if (archivoOrigen.exists()) {
            File archivoDestino = new File(urlDestino);

            try {
                in = new FileInputStream(archivoOrigen);
                out = new FileOutputStream(archivoDestino);

                byte[] buf = new byte[SIZE_ARRAY];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

            } catch (IOException e) {
                logger.error("Error al copiar el archivo." + e.getMessage());
                throw new IllegalArgumentException("Error al copiar el archivo: " + UtileriasMapperDao.getNameFileFromPath(urlOrigen), e);
            } finally {
                try {
                    if (out != null) {
                        IOUtils.closeQuietly(out);
                    }
                } catch (Exception e) {
                    logger.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
                }
                try {
                    if (in != null) {
                        IOUtils.closeQuietly(in);
                    }
                } catch (Exception e) {
                    logger.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
                }
            }
        } else {
            logger.error("Error no existe el archivo especificado.");
            throw new IllegalArgumentException("Error no existe el archivo especificado: " + urlOrigen);
        }
    }

    @Override
    public void eliminarAnexos(List<String> lstRutasAnexos) {

        for (String ruta : lstRutasAnexos) {
            File archivo = new File(ruta);

            if (archivo.delete()) {
                logger.debug("El archivo fue eliminado exitosamente");
            }
        }
    }
}
