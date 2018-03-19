package mx.gob.sat.siat.feagace.vista.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.annotation.PostConstruct;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.propuestas.ReportesService;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.CartesianChartModel;

public class ReportesManagedBeanAbstract extends AbstractManagedBean implements Serializable {
    @SuppressWarnings("compatibility:-5294320548200871970")
    protected static final long serialVersionUID = 1L;

    public static final Logger LOGGER = Logger.getLogger(ReportesManagedBean.class);
    public static final int TIPO_BUSQUEDA_ORDEN = 0;
    public static final int TIPO_BUSQUEDA_COMPLEJA = 1;
    private static final String LOGO_SHCP = "logoSHCP.jpg";
    private static final String LOGO_SAT = "logoSAT.jpg";
    private static final String ARCHIVO_REPORTES = Propiedades.get("jasper.ubicacion.reportes");
    private static final String ARCHIVO_ACUSE = "reporteEjecutivo.jasper";
    private static final String RUTA_IMAGENES = Propiedades.get("jasper.ubicacion.acuse.imagenes");


    private boolean visibleCompleja;
    private boolean visibleOrden;

    private transient ReportesService reportesService;
    private List<FececMetodo> listaMetodos;
    private BigDecimal metodoSeleccionado;
    private boolean ordenVisible;
    private BigDecimal tipoBusqueda;
    private boolean tipoBusquedaNumeroOrden;
    private boolean tipoBusquedaCompleja;
    private String labelOrden;
    private BigDecimal areaEmisora;
    private transient List<ReporteEjecutivoDTO> listaEjecutivo;
    private boolean visibleExportarGerencial;

    private String cveOrden;

    private FecetAlegato fecetAlegato;
    private transient StreamedContent fecetAlegatoDescarga;


    private CartesianChartModel valoresGraficaEjecutivo;
    private transient List<ReporteGerencialDTO> listaReporteGerencial;
    private transient ReporteGerencialDTO reporteGerencialDTO;
    private boolean visibleGrafica;
    private transient List<FececArace> listaArace;
    private Date rangoInferior;
    private Date rangoSuperior;
    private BigDecimal estatus;

    private transient StreamedContent pdfEjecutivo;
    private transient StreamedContent xlsGerencial;

    private transient HSSFWorkbook workbook = null;
    private transient HSSFSheet sheet = null;

    private transient HSSFCellStyle style = null;
    private transient Row row = null;
    private transient Cell cell = null;
    private int count = 0;
    private File file;

    public ReportesManagedBeanAbstract() {
        super();
    }

    @PostConstruct
    public void init() {

        inicializaExcel();

        if (listaMetodos != null) {
            listaMetodos.clear();
        }
        listaMetodos = getMetodos();
        metodoSeleccionado = BigDecimal.valueOf(-1);
        ordenVisible = false;
        tipoBusquedaNumeroOrden = true;
        tipoBusquedaCompleja = true;

        listaArace = getReportesService().getAreas();
        areaEmisora = BigDecimal.valueOf(-1);
        valoresGraficaEjecutivo = new CartesianChartModel();

        visibleCompleja = false;
        visibleOrden = false;
        visibleExportarGerencial = false;

        visibleGrafica = false;
        estatus = BigDecimal.valueOf(1);

        listaReporteGerencial = new ArrayList<ReporteGerencialDTO>();
    }

    void inicializaExcel() {
        HSSFFont font = null;
        this.workbook = new HSSFWorkbook();
        this.sheet = workbook.createSheet("Sample sheet");
        font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        this.style = workbook.createCellStyle();
        this.style.setFont(font);
    }

    public void setReportesService(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    public ReportesService getReportesService() {
        return reportesService;
    }

    public List<FececMetodo> getMetodos() {
        return getReportesService().getMetodos();
    }

    public void setListaMetodos(List<FececMetodo> listaMetodos) {
        this.listaMetodos = listaMetodos;
    }

    public List<FececMetodo> getListaMetodos() {
        return listaMetodos;
    }

    public void setMetodoSeleccionado(BigDecimal metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }

    public BigDecimal getMetodoSeleccionado() {
        return metodoSeleccionado;
    }


    public void setOrdenVisible(boolean ordenVisible) {
        this.ordenVisible = ordenVisible;
    }

    public boolean isOrdenVisible() {
        return ordenVisible;
    }

    public void setTipoBusqueda(BigDecimal tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public BigDecimal getTipoBusqueda() {
        return tipoBusqueda;
    }


    public void setTipoBusquedaNumeroOrden(boolean tipoBusquedaNumeroOrden) {
        this.tipoBusquedaNumeroOrden = tipoBusquedaNumeroOrden;
    }

    public boolean isTipoBusquedaNumeroOrden() {
        return tipoBusquedaNumeroOrden;
    }

    public void setTipoBusquedaCompleja(boolean tipoBusquedaCompleja) {
        this.tipoBusquedaCompleja = tipoBusquedaCompleja;
    }

    public boolean isTipoBusquedaCompleja() {
        return tipoBusquedaCompleja;
    }

    public void setLabelOrden(String labelOrden) {
        this.labelOrden = labelOrden;
    }

    public String getLabelOrden() {
        return labelOrden;
    }

    public void setListaArace(List<FececArace> listaArace) {
        this.listaArace = listaArace;
    }

    public List<FececArace> getListaArace() {
        return listaArace;
    }

    public void setAreaEmisora(BigDecimal areaEmisora) {
        this.areaEmisora = areaEmisora;
    }

    public BigDecimal getAreaEmisora() {
        return areaEmisora;
    }

    public void setValoresGraficaEjecutivo(CartesianChartModel valoresGraficaEjecutivo) {
        this.valoresGraficaEjecutivo = valoresGraficaEjecutivo;
    }

    public CartesianChartModel getValoresGraficaEjecutivo() {
        return valoresGraficaEjecutivo;
    }

    public void setVisibleGrafica(boolean visibleGrafica) {
        this.visibleGrafica = visibleGrafica;
    }

    public boolean isVisibleGrafica() {
        return visibleGrafica;
    }

    public void setRangoInferior(final Date rangoInferior) {
        this.rangoInferior = (rangoInferior != null) ? (Date)rangoInferior.clone() : null;
    }

    public Date getRangoInferior() {
        return (rangoInferior != null) ? (Date)rangoInferior.clone() : null;
    }

    public void setRangoSuperior(final Date rangoSuperior) {
        this.rangoSuperior = (rangoSuperior != null) ? (Date)rangoSuperior.clone() : null;
    }

    public Date getRangoSuperior() {
        return (rangoSuperior != null) ? (Date)rangoSuperior.clone() : null;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getEstatus() {
        return estatus;
    }


    public void setListaReporteGerencial(List<ReporteGerencialDTO> listaReporteGerencial) {
        this.listaReporteGerencial = listaReporteGerencial;
    }

    public List<ReporteGerencialDTO> getListaReporteGerencial() {
        return listaReporteGerencial;
    }

    public void setPdfEjecutivo(StreamedContent pdfEjecutivo) {
        this.pdfEjecutivo = pdfEjecutivo;
    }

    public void setXlsGerencial(StreamedContent xlsGerencial) {
        this.xlsGerencial = xlsGerencial;
    }


    public StreamedContent getXlsGerencial() {
        this.file = null;
        this.row = sheet.createRow(count);
        this.cell = row.createCell(0);
        cell.setCellValue("ID de Registro");
        cell = row.createCell(1);
        cell.setCellValue("No. de orden");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Método");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_TRES);
        cell.setCellValue("RFC Contribuyente");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_CUATRO);
        cell.setCellValue("Nombre Contribuyente");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_CINCO);
        cell.setCellValue("RFC Rep. Legal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_SEIS);
        cell.setCellValue("Nombre Representante Legal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_SIETE);
        cell.setCellValue("RFC Apod. Legal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_OCHO);
        cell.setCellValue("Nombre Apod. Legal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_NUEVE);
        cell.setCellValue("RFC Agte. Aduanal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_DIEZ);
        cell.setCellValue("Nombre Agte. Aduanal");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_ONCE);
        cell.setCellValue("�rea emisora");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_DOCE);
        cell.setCellValue("Nombre Auditor");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_TRECE);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_CATORCE);
        cell.setCellValue("Fecha de Env�o");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_QUINCE);
        cell.setCellValue("Fecha de Env�o a Notificaci�n");
        cell.setCellStyle(style);
        cell = row.createCell(Constantes.ENTERO_DIECISEIS);
        cell.setCellValue("Estatus");
        cell.setCellStyle(style);
        count++;

        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(listaReporteGerencial.get(0).getCveRegistro());
        cell = row.createCell(1);
        cell.setCellValue(listaReporteGerencial.get(0).getCveOrden());
        cell = row.createCell(2);
        cell.setCellValue(listaReporteGerencial.get(0).getMetodo());
        cell = row.createCell(Constantes.ENTERO_TRES);
        cell.setCellValue(listaReporteGerencial.get(0).getRfcContribuyente());
        cell = row.createCell(Constantes.ENTERO_CUATRO);
        cell.setCellValue(listaReporteGerencial.get(0).getNombrecontribuyente());
        cell = row.createCell(Constantes.ENTERO_CINCO);
        cell.setCellValue(listaReporteGerencial.get(0).getRfcRepresentanteLegal());
        cell = row.createCell(Constantes.ENTERO_SEIS);
        cell.setCellValue(listaReporteGerencial.get(0).getNombreRepresentanteLegal());
        cell = row.createCell(Constantes.ENTERO_SIETE);
        cell.setCellValue(listaReporteGerencial.get(0).getRfcApoderadoLegal());
        cell = row.createCell(Constantes.ENTERO_OCHO);
        cell.setCellValue(listaReporteGerencial.get(0).getNombreApoderadoLegal());
        cell = row.createCell(Constantes.ENTERO_NUEVE);
        cell.setCellValue(listaReporteGerencial.get(0).getRfcAgenteAduanal());
        cell = row.createCell(Constantes.ENTERO_DIEZ);
        cell.setCellValue(listaReporteGerencial.get(0).getNombreAgenteAduanal());
        cell = row.createCell(Constantes.ENTERO_ONCE);
        cell.setCellValue(listaReporteGerencial.get(0).getArea());
        cell = row.createCell(Constantes.ENTERO_DOCE);
        cell.setCellValue(listaReporteGerencial.get(0).getNombreAuditor());
        cell = row.createCell(Constantes.ENTERO_TRECE);
        cell.setCellValue(listaReporteGerencial.get(0).getFechaRegistro() == null ? "" :
                          "" + listaReporteGerencial.get(0).getFechaRegistro());
        cell = row.createCell(Constantes.ENTERO_CATORCE);
        cell.setCellValue(listaReporteGerencial.get(0).getFechaEnvio() == null ? "" :
                          "" + listaReporteGerencial.get(0).getFechaEnvio());
        cell = row.createCell(Constantes.ENTERO_QUINCE);
        cell.setCellValue(listaReporteGerencial.get(0).getFechaEnvioNotificacion() == null ? "" :
                          "" + listaReporteGerencial.get(0).getFechaEnvioNotificacion());
        cell = row.createCell(Constantes.ENTERO_DIECISEIS);

        cell.setCellValue("");
        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Alegatos");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        validaAlegatos();
        
        return getXlsGerencialSecondPart();

        
    }
    
    public StreamedContent getXlsGerencialSecondPart(){

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Oficio de Ampliaci�n de Plazo");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Carta de Invitaci�n");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Complementos");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Requerimientos");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Observaciones");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        count += 2;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue("Conclusiones");
        cell.setCellStyle(style);
        count++;
        row = sheet.createRow(count);
        cell = row.createCell(0);
        cell.setCellValue(Constantes.FECHA_REGISTRO);
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(Constantes.DOCUMENTO);
        cell.setCellStyle(style);
        count++;

        FileOutputStream out = null;

        try {
            this.file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(this.file);
            workbook.write(out);
            out.close();
            xlsGerencial =
                    new DefaultStreamedContent(new FileInputStream(this.file), "application/xls", "reporteGerencial.xls");
            this.file.deleteOnExit();
        } catch (IOException e) {
            LOGGER.error("Error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("No se pudo limpiar la memoria", e);
                }
            }
        }
        
        return xlsGerencial;
    }

    void validaAlegatos() {
        if (reporteGerencialDTO.getAlegatos() == null || reporteGerencialDTO.getAlegatos().isEmpty()) {
            row = sheet.createRow(count);
            cell = row.createCell(0);
            cell.setCellValue(Constantes.SIN_REGISTROS);
        } else {
            for (FecetAlegato alegato : reporteGerencialDTO.getAlegatos()) {
                row = sheet.createRow(count);
                cell = row.createCell(0);
                cell.setCellValue("" + alegato.getFechaCarga());
                cell = row.createCell(1);
                cell.setCellValue(alegato.getNombreArchivo());
                count++;
            }
        }

    }

    public void setReporteGerencialDTO(ReporteGerencialDTO reporteGerencialDTO) {
        this.reporteGerencialDTO = reporteGerencialDTO;
    }

    public ReporteGerencialDTO getReporteGerencialDTO() {
        return reporteGerencialDTO;
    }

    public void setFecetAlegato(FecetAlegato fecetAlegato) {
        this.fecetAlegato = fecetAlegato;
    }

    public FecetAlegato getFecetAlegato() {
        return fecetAlegato;
    }

    public void setFecetAlegatoDescarga(StreamedContent fecetAlegatoDescarga) {
        this.fecetAlegatoDescarga = fecetAlegatoDescarga;
    }

    public StreamedContent getFecetAlegatoDescarga() {
        try {
            fecetAlegatoDescarga =
                    new DefaultStreamedContent(new FileInputStream(getFecetAlegato().getRutaArchivo()), Constantes.CONTENT_TYPE_WORD,
                                               getFecetAlegato().getNombreArchivo());

        } catch (FileNotFoundException e) {
            LOGGER.error("No se pudo descargar el archivo alegato. ", e);
        }
        return fecetAlegatoDescarga;
    }

    public void setVisibleCompleja(boolean visibleCompleja) {
        this.visibleCompleja = visibleCompleja;
    }

    public boolean isVisibleCompleja() {
        return visibleCompleja;
    }

    public void setVisibleOrden(boolean visibleOrden) {
        this.visibleOrden = visibleOrden;
    }

    public boolean isVisibleOrden() {
        return visibleOrden;
    }

    public void setCveOrden(String cveOrden) {
        this.cveOrden = cveOrden;
    }

    public String getCveOrden() {
        return cveOrden;
    }

    public void setVisibleExportarGerencial(boolean visibleExportarGerencial) {
        this.visibleExportarGerencial = visibleExportarGerencial;
    }

    public boolean isVisibleExportarGerencial() {
        return visibleExportarGerencial;
    }

    public void setListaEjecutivo(List<ReporteEjecutivoDTO> listaEjecutivo) {
        this.listaEjecutivo = listaEjecutivo;
    }

    public List<ReporteEjecutivoDTO> getListaEjecutivo() {
        return listaEjecutivo;
    }

    public StreamedContent getPdfEjecutivo() {
        this.file = null;

        try {
            this.file = File.createTempFile("reporte", "pdf");
            JasperReport jasperReport = (JasperReport)JRLoader.loadObjectFromFile(ARCHIVO_REPORTES + ARCHIVO_ACUSE);
            Map map;

            map = new HashMap();
            String areaNombre = "--";

            if (getAreaEmisora().intValue() != -1) {
                for (FececArace fececArace : getListaArace()) {
                    if (fececArace.getIdArace().intValue() == getAreaEmisora().intValue()) {
                        areaNombre = fececArace.getNombre();
                    }
                }
            }

            map.put("area", areaNombre);
            map.put("logoSHCP", RUTA_IMAGENES + LOGO_SHCP);
            map.put("logoSAT", RUTA_IMAGENES + LOGO_SAT);

            String metodoNombre = "--";
            if (getMetodoSeleccionado().intValue() != -1) {
                for (FececMetodo feceaMetodo : getListaMetodos()) {
                    if (feceaMetodo.getIdMetodo().intValue() == getMetodoSeleccionado().intValue()) {
                        metodoNombre = feceaMetodo.getNombre();
                    }
                }
            }
            map.put("metodo", metodoNombre);
            map.put("estatus", "Enviada a Firma");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            map.put("periodo",
                    simpleDateFormat.format(getRangoInferior()) + " - " + simpleDateFormat.format(getRangoSuperior()));

            List lista = new ArrayList();
            Integer total = 0;
            for (ReporteEjecutivoDTO reporteEjecutivoDTO : getListaEjecutivo()) {
                Map mapaLista = new HashMap();
                mapaLista.put("metodo", reporteEjecutivoDTO.getMetodo());
                mapaLista.put("numeroOrdenes", reporteEjecutivoDTO.getNumeroOrdenes().doubleValue());
                total += reporteEjecutivoDTO.getNumeroOrdenes().intValue();
                lista.add(mapaLista);
            }

            map.put("total", total);
            map.put("ordenes", "Ordenes");

            JasperPrint jasperPrint;
            JRMapCollectionDataSource jRMapCollectionDataSource = new JRMapCollectionDataSource(lista);
            jasperPrint = JasperFillManager.fillReport(jasperReport, map, jRMapCollectionDataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(this.file));

            setPdfEjecutivo(new DefaultStreamedContent(new FileInputStream(this.file), "application/pdf",
                                                       "reporte.pdf"));
        } catch (IOException e) {
            LOGGER.error("Error al exportar: ", e);
        } catch (JRException e) {
            LOGGER.error("Error en el llenado del reporte: ", e);
        }

        return pdfEjecutivo;
    }
}
