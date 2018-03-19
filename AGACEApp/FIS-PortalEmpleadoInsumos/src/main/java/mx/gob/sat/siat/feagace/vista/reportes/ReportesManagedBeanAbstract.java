package mx.gob.sat.siat.feagace.vista.reportes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaDTO;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.reportes.ExportarReportesService;
import mx.gob.sat.siat.feagace.negocio.reportes.GenerarReportesService;
import mx.gob.sat.siat.feagace.negocio.reportes.ReportesCatalogosService;
import mx.gob.sat.siat.feagace.negocio.reportes.ValidarEmpleadoReportesService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.helper.ValidacionFechasReportesHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarReportesHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarReportesMapHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarTipoCriterioReportesHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.grafica.GraficaManagedBean;
import mx.gob.sat.siat.feagace.vista.reportes.helper.ReporteDTOHelper;
import mx.gob.sat.siat.feagace.vista.reportes.helper.ReporteLstHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public abstract class ReportesManagedBeanAbstract extends BaseManagedBean {

    private static final long serialVersionUID = 6061471399343906398L;

    private static final int UNIDAD = 1;
    private static final int DOS = 2;

    protected static final String ERROR_AL_GENERAR_REPORTE = "Error al generar reporte";

    private ReporteDTOHelper reporteDTOHelper;
    private ReporteLstHelper reporteLstHelper;

    @ManagedProperty(value = "#{reportesCatalogosService}")
    private transient ReportesCatalogosService reportesCatalogosService;

    @ManagedProperty(value = "#{generarReportesService}")
    private transient GenerarReportesService generarReportesService;

    @ManagedProperty(value = "#{validarEmpleadoReportesService}")
    private transient ValidarEmpleadoReportesService validarEmpleadoReportesService;

    @ManagedProperty(value = "#{exportarReportesService}")
    private transient ExportarReportesService exportarReportesService;

    @ManagedProperty(value = "#{validacionFechasReportesHelper}")
    private ValidacionFechasReportesHelper validacionFechasReportesHelper;

    @ManagedProperty(value = "#{validarReportesMapHelper}")
    private ValidarReportesMapHelper validarReportesMapHelper;

    @ManagedProperty(value = "#{validarTipoCriterioReportesHelper}")
    private ValidarTipoCriterioReportesHelper validarTipoCriterioReportesHelper;

    @ManagedProperty(value = "#{validarReportesHelper}")
    private ValidarReportesHelper validarReportesHelper;

    @ManagedProperty(value = "#{graficaMB}")
    private GraficaManagedBean graficaMB;

    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private AuditoriaService auditoriaService;

    private transient StreamedContent archivoExcel;
    private transient StreamedContent archivoPDF;

    public ReportesManagedBeanAbstract() {
        reporteDTOHelper = new ReporteDTOHelper();
        reporteLstHelper = new ReporteLstHelper();
    }

    public void verificarCentral() {
        if (reporteDTOHelper.getTipoReporte().getFececEmpleado() != null && reporteDTOHelper.getTipoReporte().getFececEmpleado().getDetalleEmpleado().get(0) != null) {
            BigDecimal idCentral = new BigDecimal(reporteDTOHelper.getTipoReporte().getFececEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace());
            logger.debug("Id Central: [{}]", idCentral);
            if (ConstantesReportes.CENTRAL.compareTo(idCentral) == 0) {
                reporteDTOHelper.setHabilitarUnidad(false);
            } else {
                reporteDTOHelper.getReportesVO().setAraceId(idCentral);
                reporteDTOHelper.setHabilitarUnidad(true);
            }
        }
    }

    public void getRutaImagenes() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ServletContext c = (ServletContext) fc.getExternalContext().getContext();
        reporteDTOHelper.setPathArchivo(c.getRealPath(File.separator) + getValidarReportesMapHelper().crearRutaFinal(File.separator, reporteDTOHelper.getRfc()));
        File directorio = new File(reporteDTOHelper.getPathArchivo());

        if (!directorio.exists()) {
            boolean flgCarpetaCreada = directorio.mkdirs();
            if (!flgCarpetaCreada) {
                logger.error("Error al crear la caerpeta");
            }
        }
        reporteDTOHelper.setPathArchivo(reporteDTOHelper.getPathArchivo() + File.separator);
    }

    public void validarCheckMetodo() {
        logger.debug("Activar/Desactivar Metodo");
        if (reporteDTOHelper.getReportesVO().isMostrarMetodo()) {
            reporteDTOHelper.getReportesVO().setActivarMetodo(false);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() + UNIDAD);
        } else {
            reporteDTOHelper.getReportesVO().setActivarMetodo(true);
            reporteDTOHelper.getReportesVO().setMetodoId(null);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() - UNIDAD);
        }
        reporteDTOHelper.setMostarCheckCondicion(validarCheckCondicion());
    }

    public void validarCheckEstatus() {
        logger.debug("Activar/Desactivar Estatus");
        if (reporteDTOHelper.getReportesVO().isMostrarEstatus()) {
            reporteDTOHelper.getReportesVO().setActivarEstatus(false);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() + UNIDAD);
        } else {
            reporteDTOHelper.getReportesVO().setActivarEstatus(true);
            reporteDTOHelper.getReportesVO().setEstatusId(null);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() - UNIDAD);
        }
        reporteDTOHelper.setMostarCheckCondicion(validarCheckCondicion());
    }

    public void validarCheckEntidad() {
        logger.debug("Activar/Desactivar Entidad");
        if (reporteDTOHelper.getReportesVO().isMostrarEntidad()) {
            reporteDTOHelper.getReportesVO().setActivarEntidad(false);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() + UNIDAD);
        } else {
            reporteDTOHelper.getReportesVO().setActivarEntidad(true);
            reporteDTOHelper.getReportesVO().setEntidad(null);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() - UNIDAD);
        }
        reporteDTOHelper.setMostarCheckCondicion(validarCheckCondicion());
    }

    public void validarCheckUnidad() {
        logger.debug("Activar/Desactivar Unidad Administrativa");
        if (reporteDTOHelper.getReportesVO().isMostrarUnidad()) {
            reporteDTOHelper.getReportesVO().setActivarUnidad(false);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() + UNIDAD);
        } else {
            reporteDTOHelper.getReportesVO().setActivarUnidad(true);
            reporteDTOHelper.getReportesVO().setAraceId(null);
            reporteDTOHelper.setContadorCondicion(reporteDTOHelper.getContadorCondicion() - UNIDAD);
        }
        reporteDTOHelper.setMostarCheckCondicion(validarCheckCondicion());
    }

    public boolean validarCheckCondicion() {
        if (reporteDTOHelper.isReporteEjecutivo() && reporteDTOHelper.getContadorCondicion() == UNIDAD) {
            return true;
        }
        if (!reporteDTOHelper.isReporteEjecutivo() && reporteDTOHelper.getContadorCondicion() == DOS) {
            return true;
        }
        return false;
    }

    public void usuarioNoPermitido(Exception e) {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("mensaje", e);
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public void selectRadioTipoFecha(ValueChangeEvent event) {
        String selectedItems = (String) event.getNewValue();
        if (selectedItems != null) {
            logger.debug("Tipo Fecha [{}]", selectedItems);
            if (selectedItems.equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_FECHAS))) {
                reporteDTOHelper.setHabilitarFechaPeriodos(false);
                reporteDTOHelper.setHabilitarMesAnio(true);
                reporteDTOHelper.getReportesVO().setAnioInicial(null);
                reporteDTOHelper.getReportesVO().setAnioFinal(null);
                reporteDTOHelper.getReportesVO().setMesInicial(null);
                reporteDTOHelper.getReportesVO().setMesFinal(null);
                reporteDTOHelper.getReportesVO().setFechaInicio(null);
                reporteDTOHelper.getReportesVO().setFechaFinal(null);
            } else if (selectedItems.equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) {
                reporteDTOHelper.setHabilitarFechaPeriodos(true);
                reporteDTOHelper.setHabilitarMesAnio(false);
                reporteDTOHelper.setHabilitarFechaPeriodoFin(true);
                reporteDTOHelper.getReportesVO().setFechaInicioPeriodo(null);
                reporteDTOHelper.getReportesVO().setFechaFinPeriodo(null);
                reporteDTOHelper.getReportesVO().setFechaInicio(null);
                reporteDTOHelper.getReportesVO().setFechaFinal(null);
            }
        }
    }

    public void resetTipoFecha() {
        if (reporteDTOHelper.getReportesVO() != null && reporteDTOHelper.getReportesVO().getTipoFecha() != null) {
            if (reporteDTOHelper.getReportesVO().getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_FECHAS))) {
                reporteDTOHelper.getReportesVO().setFechaInicio(null);
                reporteDTOHelper.getReportesVO().setFechaFinal(null);
            }
            if (reporteDTOHelper.getReportesVO().getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) {
                reporteDTOHelper.getReportesVO().setFechaInicioPeriodo(null);
                reporteDTOHelper.getReportesVO().setFechaFinPeriodo(null);
            }
        }
    }

    /**
     * Evento al seleccionar fecha de inicio de periodo
     *
     */
    public void handleDateSelectFechaInicio(SelectEvent event) {
        Date dateSeleccionado = (Date) event.getObject();
        reporteDTOHelper.setHabilitarFechaPeriodoFin(true);
        if (dateSeleccionado != null) {
            logger.debug("Fecha seleccionado: [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(dateSeleccionado));
            reporteDTOHelper.setFechaMinima(getValidacionFechasReportesHelper().sumarFecha(dateSeleccionado, ConstantesReportes.NUMDIAS));
            if (reporteDTOHelper.getFechaMinima().after(reporteDTOHelper.getFechaActual())) {
                reporteDTOHelper.setFechaMinima(reporteDTOHelper.getFechaActual());
            }
            logger.debug("2 años maximo: [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(reporteDTOHelper.getFechaMinima()));
            reporteDTOHelper.setHabilitarFechaPeriodoFin(false);
        }
    }

    public List<String> generarGrafica(GraficaDTO graficaDTO) {
        return getGraficaMB().crearGraficaReporte(graficaDTO);
    }

    public void cargarCatalogoEstatus() {
        getReporteLstHelper().setListaEstatusInsumos(getReportesCatalogosService().getCatalogoEstatus(ConstantesReportes.ID_MODULO_INSUMOS));
        getReporteLstHelper().setListaEstatusPropuestas(getReportesCatalogosService().getCatalogoEstatus(ConstantesReportes.ID_MODULO_PROPUESTAS));
        getReporteLstHelper().setListaEstatusOrdenes(getReportesCatalogosService().getCatalogoEstatus(ConstantesReportes.ID_MODULO_ORDENES));
    }

    public void cargarCatalogoSubprograma() {
        getReporteLstHelper().setListaSubprograma(getReportesCatalogosService().getCatalogoSubprograma());
    }

    public void cargarCatalogoSector() {
        getReporteLstHelper().setListaSector(getReportesCatalogosService().getCatalogoSector());
    }

    public void cargarCatalogoActividad() {
        getReporteLstHelper().setListaActividad(getReportesCatalogosService().getCatalogoActividadPreponderante());
    }

    public void cargarCatalogoEntidadInsumos() {
        getReporteLstHelper().setListaEntidadInsumos(getReportesCatalogosService().getCatalogoEntidadInsumos());
    }

    public void cargarCatalogoEntidadPropuestas() {
        getReporteLstHelper().setListaEntidadPropuestas(getReportesCatalogosService().getCatalogoEntidadPropuestas());
    }

    public void cargarCatalogoEntidadOrdenes() {
        getReporteLstHelper().setListaEntidadOrdenes(getReportesCatalogosService().getCatalogoEntidadOrdenes());
    }

    public void cargarCatalogoMeses() {
        getReporteLstHelper().setListaMesesInicial(getReportesCatalogosService().getCatalogoMesesInicial());
    }

    public void cargarCatalogoAnios() {
        getReporteLstHelper().setListaAnios(getReportesCatalogosService().getCatalogoAnios(reporteDTOHelper.getFechaActual()));
    }

    public void cargarCatalogoUnidad() {
        getReporteLstHelper().setListaUnidad(getReportesCatalogosService().getCatalogoUnidadAdministrativa());
    }

    public void obtieneUnidad() {
        BigDecimal idUnidad = new BigDecimal(reporteDTOHelper.getTipoReporte().getFececEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace());
        getReporteLstHelper().setUnidadAdministrativa(getReportesCatalogosService().getUnidadAdministrativa(idUnidad));
    }

    public void cargarCatalogoMetodos() {
        getReporteLstHelper().setListaMetodos(getReportesCatalogosService().getCatalogoMetodos());
    }

    public void cargarCatalogoTipoPropuesta() {
        getReporteLstHelper().setListaTipoPropuesta(getReportesCatalogosService().getCatalogoTipoPropuesta());
    }

    public void cargarCatalogoTipoRevision() {
        getReporteLstHelper().setListaTipoRevision(getReportesCatalogosService().getCatalogoTipoRevision());
    }

    public void cargarCatalogoCausaProgramacion() {
        getReporteLstHelper().setListaCausaProgramacion(getReportesCatalogosService().getCatalogoCausaProgramacion());
    }

    public void setReportesCatalogosService(ReportesCatalogosService reportesCatalogosService) {
        this.reportesCatalogosService = reportesCatalogosService;
    }

    public ReportesCatalogosService getReportesCatalogosService() {
        return reportesCatalogosService;
    }

    public void setGenerarReportesService(GenerarReportesService generarReportesService) {
        this.generarReportesService = generarReportesService;
    }

    public GenerarReportesService getGenerarReportesService() {
        return generarReportesService;
    }

    public void setValidacionFechasReportesHelper(ValidacionFechasReportesHelper validacionFechasReportesHelper) {
        this.validacionFechasReportesHelper = validacionFechasReportesHelper;
    }

    public ValidacionFechasReportesHelper getValidacionFechasReportesHelper() {
        return validacionFechasReportesHelper;
    }

    public void setValidarReportesMapHelper(ValidarReportesMapHelper validarReportesMapHelper) {
        this.validarReportesMapHelper = validarReportesMapHelper;
    }

    public ValidarReportesMapHelper getValidarReportesMapHelper() {
        return validarReportesMapHelper;
    }

    public void setValidarTipoCriterioReportesHelper(ValidarTipoCriterioReportesHelper validarTipoCriterioReportesHelper) {
        this.validarTipoCriterioReportesHelper = validarTipoCriterioReportesHelper;
    }

    public ValidarTipoCriterioReportesHelper getValidarTipoCriterioReportesHelper() {
        return validarTipoCriterioReportesHelper;
    }

    public void setValidarEmpleadoReportesService(ValidarEmpleadoReportesService validarEmpleadoReportesService) {
        this.validarEmpleadoReportesService = validarEmpleadoReportesService;
    }

    public ValidarEmpleadoReportesService getValidarEmpleadoReportesService() {
        return validarEmpleadoReportesService;
    }

    public void setArchivoExcel(StreamedContent archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public StreamedContent getArchivoExcel() {
        try {
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_EXCEL, "xls");
            this.setArchivoExcel(new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/vnd.ms-excel", ConstantesReportes.ARCHIVO_EXCEL + ".xls"));
        } catch (FileNotFoundException e) {
            logger.error("No existe archivo xls ", e);
        }
        return archivoExcel;
    }

    public void setArchivoPDF(StreamedContent archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public StreamedContent getArchivoPDF() {
        try {
            exportarPDF();
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PDF, "pdf");
            this.setArchivoPDF(new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/pdf", ConstantesReportes.ARCHIVO_PDF + ".pdf"));
        } catch (FileNotFoundException e) {
            FacesUtil.addErrorMessage(ConstantesReportes.MSG_REPORTES, "" + e);
            logger.error("NO existe archivo pdf ", e);
        }
        return archivoPDF;
    }

    public void exportarPDF() {
        logger.debug("Exportar PDF");
        getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + reporteDTOHelper.getTipoReporte().getModuloReporte() + ConstantesReportes.REPORTE_EJECTIVO);
        getExportarReportesService().crearHeaderReporte(getReporteLstHelper().getUnidadAdministrativa().get(0).getNombre().toUpperCase(), reporteDTOHelper.getTipoReporte().getTituloReporte());
        getExportarReportesService().agregarRegistrosReporte(getValidarReportesMapHelper().listaImagenesGraficas(getGraficaMB().getListaGraficas()));
    }

    public void resetPaginaTabla() {
        final DataTable tabla = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                .findComponent(":formReportes:listaInsumos");
        tabla.reset();
    }

    public void setGraficaMB(GraficaManagedBean graficaMB) {
        this.graficaMB = graficaMB;
    }

    public GraficaManagedBean getGraficaMB() {
        return graficaMB;
    }

    public void setExportarReportesService(ExportarReportesService exportarReportesService) {
        this.exportarReportesService = exportarReportesService;
    }

    public ExportarReportesService getExportarReportesService() {
        return exportarReportesService;
    }

    public void setValidarReportesHelper(ValidarReportesHelper validarReportesHelper) {
        this.validarReportesHelper = validarReportesHelper;
    }

    public ValidarReportesHelper getValidarReportesHelper() {
        return validarReportesHelper;
    }

    public ReporteDTOHelper getReporteDTOHelper() {
        return reporteDTOHelper;
    }

    public void setReporteDTOHelper(ReporteDTOHelper reporteDTOHelper) {
        this.reporteDTOHelper = reporteDTOHelper;
    }

    public ReporteLstHelper getReporteLstHelper() {
        return reporteLstHelper;
    }

    public void setReporteLstHelper(ReporteLstHelper reporteLstHelper) {
        this.reporteLstHelper = reporteLstHelper;
    }

    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }
}
