package mx.gob.sat.siat.feagace.negocio.propuestas.carga.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.dao.common.UtilidadesDao;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaDocumentacionMasivaService;

@Service("cargaDocumentacionMasivaService")
public class CargaDocumentacionMasivaServiceImpl extends BaseBusinessServices 
    implements CargaDocumentacionMasivaService {



    private static final Logger LOGGER = Logger.getLogger(CargaDocumentacionMasivaServiceImpl.class);

    private static final long serialVersionUID = 1L;
    private static final int NUM_31 = 31;
    private static final int NUM_45 = 45;

    @Autowired
    private transient UtilidadesDao utilidadesDao;
    private static final String STRCEROSIZQ = "%03d";

    public CargaDocumentacionMasivaServiceImpl() {
        super();
    }

    @Override
    public String getConsecutivo() {
        Formatter fmt = new Formatter();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("CDM")
                    .append(new SimpleDateFormat("MMddyyyy").format(new Date()))
                    .append(fmt.format(STRCEROSIZQ, Long.parseLong(this.utilidadesDao.getConsecutivoCargaMasiva().toString())).toString());
        } finally {
            fmt.close();
        }

        return sb.toString();
    }

    @Override
    public String cargaDocumento(String destino, InputStream is, String nombreArchivo) throws NegocioException {
        File fileToDirectory = new File(destino);
        boolean creado = fileToDirectory.mkdirs();

        OutputStream out = null;

        try {
            if (!fileToDirectory.exists() || !creado) {
                creado = fileToDirectory.mkdirs();
            }
            out = new FileOutputStream(new File(destino + nombreArchivo));
            if (is != null && !creado) {
                byte bytes[] = new byte[Constantes.BYTE];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
                is.close();
            }

            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw new NegocioException(ConstantesError.ERROR_EXISTENCIA_ARCHIVO + e.getCause(), e);

        } catch (IOException ioe) {
            throw new NegocioException(ConstantesError.ERROR_ESCRIBIR_ARCHIVO + ioe.getCause(), ioe);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                LOGGER.error(ConstantesError.ERROR_CERRAR_OUTPUTSTREAM, e);
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error(ConstantesError.ERROR_CERRAR_INPUTSTREAM, e);
            }
        }
        return destino.substring(NUM_31, NUM_45);
    }

    public void setUtilidadesDao(UtilidadesDao utilidadesDao) {
        this.utilidadesDao = utilidadesDao;
    }

    public UtilidadesDao getUtilidadesDao() {
        return utilidadesDao;
    }
}
