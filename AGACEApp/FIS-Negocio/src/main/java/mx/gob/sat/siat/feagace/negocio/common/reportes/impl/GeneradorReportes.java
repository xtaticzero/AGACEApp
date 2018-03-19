/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.common.reportes.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.negocio.common.reportes.constantes.ReportesConstantes;
import mx.gob.sat.siat.feagace.negocio.exception.JaspertReportsServiceException;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public final class GeneradorReportes {

    /**
     * String de error para formato no soportado.
     */
    public static final String ERROR_TIPO_REPORTE_NO_SOPORTADO = "extencion.no.soportada";
    public static final String ERROR_NOMBRE_REPORTE = "nombre.reporte";
    public static final String ERROR_GENERAR_REPORTE = "nombre.reporte";

    private GeneradorReportes() {

    }

    /**
     * M&eacute;todo generico para crear reportes.
     *
     * @param reportIS
     * @param nombreReporte nombre del reporte con todo y su extenci&oacute;n
     * (.xls o .pdf). Ej. miReporte.pdf
     * @param parametros Mapa con los parametros que estaran en el reporte.
     * @param detalle Lista de mapas para insertar en la banda detail del
     * reporte.
     * @return arreglo de bytes del reporte generado.
     * @throws JaspertReportsServiceException en caso de haber un problema al
     * generar el reporte.
     */
    public static byte[] crearReporte(InputStream reportIS, String nombreReporte,
            Map<String, Object> parametros,
            List<?> detalle) throws JaspertReportsServiceException {

        ReporteJasperUtil reporte = new ReporteJasperUtil();
        reporte.setReporteJasper(reportIS);
        reporte.setNombreReporte(nombreReporte);
        reporte.setFormatoReporte(getFormatoReporte(nombreReporte));
        reporte.setParametrosReporte(parametros);
        reporte.setDatosReporte(detalle);
        try {
            return reporte.generarBytesReporte();
        } catch (JaspertReportsServiceException cex) {
            if (cex.getMessage() != null) {
                throw new JaspertReportsServiceException(cex.getMessage(), cex);
            } else {
                throw new JaspertReportsServiceException(ERROR_GENERAR_REPORTE, cex);
            }
        }
    }

    private static String getFormatoReporte(String nombreReporte) throws JaspertReportsServiceException {

        if (nombreReporte == null || nombreReporte.isEmpty()) {
            throw new JaspertReportsServiceException(ERROR_TIPO_REPORTE_NO_SOPORTADO, nombreReporte);
        }

        if (nombreReporte.endsWith(ReportesConstantes.EXCEL_ANTES_2007)) {
            return ReporteJasperUtil.XLS;
        }
        if (nombreReporte.endsWith(ReportesConstantes.EXCEL_DESPUES_2007)) {
            return ReporteJasperUtil.XLSX;
        }
        if (nombreReporte.endsWith(ReportesConstantes.ARCHIVO_PDF)) {
            return ReporteJasperUtil.PDF;
        }
        if (nombreReporte.endsWith(ReportesConstantes.ARCHIVO_CSV)) {
            return ReporteJasperUtil.CSV;
        }
        if (nombreReporte.endsWith(ReportesConstantes.WORD_2003)) {
            return ReporteJasperUtil.DOC;
        }
        if (nombreReporte.endsWith(ReportesConstantes.WORD_2007)) {
            return ReporteJasperUtil.DOCX;
        }
        throw new JaspertReportsServiceException(ERROR_TIPO_REPORTE_NO_SOPORTADO, nombreReporte);

    }
}
