/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.reportes;

import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.negocio.exception.JaspertReportsServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface JaspertReportsService {

    byte[] makeReport(String rutaReporte, String nombreReporte,
            Map<String, Object> parametros,
            List<?> detalle) throws JaspertReportsServiceException;
}
