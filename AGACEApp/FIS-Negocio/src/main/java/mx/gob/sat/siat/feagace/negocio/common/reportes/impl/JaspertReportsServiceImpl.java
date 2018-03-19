/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.reportes.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.negocio.common.reportes.JaspertReportsService;
import mx.gob.sat.siat.feagace.negocio.exception.JaspertReportsServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service
@Qualifier("jaspertReportsService")
public class JaspertReportsServiceImpl extends BaseBusinessServices implements JaspertReportsService {

    private static final long serialVersionUID = -4509714827447639506L;

    @Override
    public byte[] makeReport(String rutaReporte, String nombreReporte, Map<String, Object> parametros, List<?> detalle) throws JaspertReportsServiceException {
        try {
            InputStream fileIS = new FileInputStream(rutaReporte);
            return GeneradorReportes.crearReporte(fileIS, nombreReporte, parametros, detalle);
        } catch (JaspertReportsServiceException cje) {
            logger.error("Error al generar el reporte");
            throw new JaspertReportsServiceException(ReporteJasperUtil.ERROR_GENERAR_REPORTE, cje);
        } catch (FileNotFoundException ex) {
            logger.error("Error al intentar abrir el archivo :", rutaReporte);
            logger.error(ex.getMessage(), ex);
            throw new JaspertReportsServiceException(ReporteJasperUtil.ERROR_RUTA_PLANTILLA_INVALIDA, ex);
        }
    }

}
