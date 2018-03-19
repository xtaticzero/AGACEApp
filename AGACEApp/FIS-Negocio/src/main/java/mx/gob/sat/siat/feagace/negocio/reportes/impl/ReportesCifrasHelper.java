package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public final class ReportesCifrasHelper {

    public static final transient Logger LOGGER = LoggerFactory.getLogger(ReportesCifrasHelper.class);

    public static final String PATH_REPORTE_CIFRAS = "/siat/fece/configuracion/reportes/ordenes/ConsultaCifras.jasper";
    public static final String ARCHIVO_CIFRAS_COBRADAS_XLS = "cifrasCobradas";
    public static final String ARCHIVO_CIFRAS_VIRTUALES_XLS = "cifrasVirtuales";
    public static final String ARCHIVO_CIFRAS_LIQUIDADAS_XLS = "cifrasLiquidadas";
    public static final String MSG_N_A = "N/A";
    private static final int NUM_1 = 1;
    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;

    private ReportesCifrasHelper() {
        super();
    }

    public static Map<String, Object> getHeaderReporte(BigDecimal tipoCifra) {
        Map<String, Object> headerReporte = new HashMap<String, Object>();
        headerReporte.put("logoSHCP", ConstantesReportes.PATH_REPORTE_IMAGENES + Constantes.LOGO_SHCP_JPG);
        headerReporte.put("logoSAT", ConstantesReportes.PATH_REPORTE_IMAGENES + Constantes.LOGO_SAT_JPG);
        headerReporte.put("titulo2", obtenerTitulo(tipoCifra.intValue()).toUpperCase());
        return headerReporte;
    }

    public static String obtenerTitulo(int tipoCifra) {
        String tituloCifra = "";
        switch (tipoCifra) {
            case NUM_1:
                tituloCifra = "Cifras Cobradas";
                break;
            case NUM_2:
                tituloCifra = "Cifras Virtuales";
                break;
            case NUM_3:
                tituloCifra = "Cifras Liquidadas";
                break;
            default:
                break;
        }
        return tituloCifra;
    }

    public static JRDataSource dataSourceBienes(List<FeceaCifraImpuestoDTO> listaCifraDetalles) {
        JRDataSource datasource = null;
        try {
            List<Map<String, ?>> fields = new ArrayList<Map<String, ?>>();
            if (listaCifraDetalles != null && !listaCifraDetalles.isEmpty()) {
                Iterator<FeceaCifraImpuestoDTO> iter = listaCifraDetalles.iterator();
                Locale loc = new Locale("es", "MX");
                NumberFormat nfi = NumberFormat.getNumberInstance(loc);

                DecimalFormat nf = (DecimalFormat) nfi;
                nf.applyPattern("###,###,###,###,###,###.00");

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                int i = 1;
                while (iter.hasNext()) {
                    Map<String, Object> registro = new HashMap<String, Object>();
                    FeceaCifraImpuestoDTO cifra = iter.next();
                    registro.put("impuesto", cifra.getImpuestoConcepto().getImpuesto().getDescripcion());
                    registro.put("numero", i);
                    registro.put("concepto", cifra.getImpuestoConcepto().getConcepto().getDescripcion());
                    registro.put("derivado", cifra.getDerivaAntecedente().equals(BigDecimal.ONE) ? "Si" : "No");
                    registro.put("fecha_pago", df.format(cifra.getFechaPago()));
                    registro.put("importe_impuesto", cifra.getImporte() != null ? nf.format(cifra.getImporte()) : "");
                    registro.put("actualizaciones", cifra.getActualizaciones() != null ? nf.format(cifra.getActualizaciones()) : "");
                    registro.put("multas", cifra.getMultas() != null ? nf.format(cifra.getMultas()) : "");
                    registro.put("recargos", cifra.getRecargos() != null ? nf.format(cifra.getRecargos()) : "");
                    registro.put("total", cifra.getTotal() != null ? nf.format(cifra.getTotal()) : "");
                    registro.put("parcialidades", cifra.getParcialidad() != null
                            ? cifra.getParcialidad().getIdParcialidadHistorico().equals(BigDecimal.ZERO) ? "No" : "Si" : "NO");
                    registro.put("periodo_parcialidad",
                            cifra.getParcialidad() != null ? cifra.getParcialidad().getIdParcialidadHistorico().equals(BigDecimal.ZERO)
                                            ? MSG_N_A : cifra.getParcialidad().getTipoParcialidad().getTipoParcialidad() : MSG_N_A);
                    registro.put("numero_parcialidad",
                            cifra.getParcialidad() != null ? cifra.getParcialidad().getIdParcialidadHistorico().equals(BigDecimal.ZERO)
                                            ? MSG_N_A : cifra.getParcialidad().getNumeroParcialidades().toString() : MSG_N_A);
                    registro.put("monto_parcialidad",
                            cifra.getParcialidad() != null ? cifra.getParcialidad().getIdParcialidadHistorico().equals(BigDecimal.ZERO)
                                            ? MSG_N_A : nf.format(cifra.getParcialidad().getMontoTotal()) : MSG_N_A);
                    registro.put("observaciones", cifra.getObservaciones());
                    registro.put("idtipoCifra", cifra.getTipoCifra().getIdCifra());
                    registro.put("descripcionTipoCifra", cifra.getTipoCifra().getDescripcion());
                    registro.put("numero", i);
                    fields.add(registro);
                    i++;
                }
            }
            datasource = new JRBeanCollectionDataSource(fields);
        } catch (Exception e) {
            LOGGER.error("Error al generar data source de detalle cifras " + e.getMessage(), e);
        }
        return datasource;
    }

    public static File crearArchivo(String nombre, Map<String, Object> mapa, JRDataSource dataSource) {
        JasperPrint jasper = null;
        File reporte = null;
        try {
            jasper = JasperFillManager.fillReport(PATH_REPORTE_CIFRAS, mapa, new JREmptyDataSource());
            jasper.setProperty("net.sf.jasperreports.export.xls.wrap.text", "true");
            reporte = File.createTempFile(nombre, ".xls");
            JRXlsExporter xlsExporter = new JRXlsExporter();
            xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
            xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(reporte));
            xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
            xlsExporter.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
            xlsExporter.exportReport();
        } catch (JRException e) {
            LOGGER.error("JRException: [{}]", e.getMessage());
        } catch (IOException ioe) {
            LOGGER.error("IOException: [{}]", ioe.getMessage());
        }
        return reporte;
    }

    public static Boolean[] configurarDetalle(int tipoCifra) {
        Boolean[] visibles = null;
        switch (tipoCifra) {
            case NUM_1:
                visibles = new Boolean[]{true, true, true, true, true};
                break;
            case NUM_2:
                visibles = new Boolean[]{false, false, false, false, false};
                break;
            case NUM_3:
                visibles = new Boolean[]{true, true, true, true, false};
                break;
            default:
                break;
        }
        return visibles;
    }
}
