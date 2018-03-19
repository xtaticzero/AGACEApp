package mx.gob.sat.siat.feagace.vista.reportes.managedbean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.ValidacionReportesDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.reportes.ReportesManagedBeanAbstract;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;


/**
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@ManagedBean(name = "reporteInsumosMB")
@ViewScoped
public class ReporteInsumosMB extends ReportesManagedBeanAbstract {

    private static final long serialVersionUID = -3812310678232484944L;

    @PostConstruct
    public void init() {
        try {
            getReporteDTOHelper().setRfc(getRFCSession());
            getRutaImagenes();
            getReporteDTOHelper().setFechaActual(new Date());
            getReporteDTOHelper().setFechaMinima(null);
            getReporteDTOHelper().setMostrarResultados(false);
            getReporteDTOHelper().setMostrarFormulario(true);
            getReporteDTOHelper().setTipoReporteEjecutivo(false);
            getReporteDTOHelper().setTipoReporteGerencial(false);
            getReporteLstHelper().setListaInsumos(null);
            cargarCatalogoEstatus();
            cargarCatalogoSubprograma();
            cargarCatalogoSector();
            cargarCatalogoActividad();
            cargarCatalogoEntidadInsumos();
            cargarCatalogoMeses();
            cargarCatalogoAnios();
            getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
            getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
            getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
            getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
            getReporteDTOHelper().getReportesVO().setReporte(ConstantesReportes.REPORTE_INSUMOS);
            getReporteDTOHelper().setTipoReporte(getValidarEmpleadoReportesService().validarEmpleado(getRFCSession()));            
            getReporteDTOHelper().getReportesVO().setLstRfcCreacion(getValidarEmpleadoReportesService().obtenerLstRfcUsuario(TipoEmpleadoEnum.USUARIO_INSUMOS, TipoAraceEnum.ACIACE));
            getReporteDTOHelper().getTipoReporte().setModuloReporte(ConstantesReportes.REPORTE_INSUMOS);
            getReporteDTOHelper().getTipoReporte().setTituloReporte(getMessageResourceString("lbl.reporte.tipo.reporte"));
            getReporteDTOHelper().getReportesVO().setAraceId(ConstantesReportes.ARACE_INSUMOS);
            obtieneUnidad();
        } catch (Exception e) {
            logger.error("Error al iniciar la clase ReporteInsumosMB: [{}]", e);
            usuarioNoPermitido(e);
        }
    }

    public void selectRadioItem(ValueChangeEvent event) {
        String selectedItems = (String)event.getNewValue();
        if (selectedItems != null) {
            logger.debug("Tipo Reporte [{}]", selectedItems);
            regresarFormulario();
            limpiarFormulario();
            if (selectedItems.equals(getMessageResourceString("lbl.reporte.titulo.gerencial"))) {
                getReporteDTOHelper().setTipoReporteEjecutivo(false);
                getReporteDTOHelper().setTipoReporteGerencial(true);
                getReporteDTOHelper().getReportesVO().setTipoGrafica(null);
                getReporteDTOHelper().getTipoReporte().setTituloReporte(getMessageResourceString("lbl.reporte.titulo.gerencial"));
                
            } else if (selectedItems.equals(getMessageResourceString("lbl.reporte.titulo.ejecutivo"))) {
                getReporteDTOHelper().setTipoReporteEjecutivo(true);
                getReporteDTOHelper().setTipoReporteGerencial(false);
                getReporteDTOHelper().getReportesVO().setTipoGrafica(getMessageResourceString("lbl.reporte.tipo.grafica.barras"));
                getReporteDTOHelper().getTipoReporte().setTituloReporte(getMessageResourceString("lbl.reporte.titulo.ejecutivo"));
            }
        }
    }

    public void crearFechasReporteEjecutivo() {
        if (getReporteDTOHelper().getReportesVO().getTipoFecha().trim().equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) {
            Date fechaInicial =
                getValidacionFechasReportesHelper().crearFecha(getReporteDTOHelper().getReportesVO().getMesInicial(), getReporteDTOHelper().getReportesVO().getAnioInicial(),
                                                               false);
            Date fechaFinal =
                getValidacionFechasReportesHelper().crearFecha(getReporteDTOHelper().getReportesVO().getMesFinal(), getReporteDTOHelper().getReportesVO().getAnioFinal(),
                                                               true);
            getReporteDTOHelper().getReportesVO().setFechaInicioPeriodo(null);
            getReporteDTOHelper().getReportesVO().setFechaFinPeriodo(null);
            getReporteDTOHelper().getReportesVO().setFechaInicio(fechaInicial);
            getReporteDTOHelper().getReportesVO().setFechaFinal(fechaFinal);
            logger.debug("Fecha Inicial: [{}]",
                         getValidacionFechasReportesHelper().getFormatoFecha().format(fechaInicial));
            logger.debug("Fecha Final: [{}]",
                         getValidacionFechasReportesHelper().getFormatoFecha().format(fechaFinal));
        }
    }

    public void generarReporteGerencial() {
        try {
            ValidacionReportesDTO dtoValidacion =
                getValidarReportesHelper().validarFormulario(getReporteDTOHelper().getReportesVO());
            if (dtoValidacion.getErrorTotal() > ConstantesReportes.N_0) {
                FacesUtil.addErrorMessage(ConstantesReportes.MSG_REPORTES, dtoValidacion.getMensaje());
            } else {
                logger.debug("Se genera el reporte");
                getReporteLstHelper().setListaInsumos(getValidarReportesMapHelper().amparadoPPEEInsumos(getGenerarReportesService().generarReporteInsumos(getReporteDTOHelper().getReportesVO())));
                getAuditoriaService().generarReporteGerencialInsumos(getRFCSession());
                if (getReporteLstHelper().getListaInsumos() != null && getReporteLstHelper().getListaInsumos().size() > ConstantesReportes.N_0) {
                    getReporteDTOHelper().setRangoFechasReporte(String.format(getMessageResourceString("lbl.reporte.insumos.columna.periodo"),
                                                        getValidacionFechasReportesHelper().getFormatoFecha().format(getReporteDTOHelper().getReportesVO().getFechaInicioPeriodo()),
                                                        getValidacionFechasReportesHelper().getFormatoFecha().format(getReporteDTOHelper().getReportesVO().getFechaFinPeriodo())));
                    logger.debug("Total registros: [{}]", getReporteLstHelper().getListaInsumos().size());
                    exportarExcel();
                    getReporteDTOHelper().setMostrarResultados(true);
                    getReporteDTOHelper().setMostrarFormulario(false);
                } else {
                    FacesUtil.addInfoMessage(ConstantesReportes.MSG_REPORTES,
                                             "No se encontraron registros para los criterios de b\u00fasqueda");
                    getReporteDTOHelper().setMostrarResultados(false);
                    getReporteDTOHelper().setMostrarFormulario(true);
                    limpiarFormulario();
                }
            }
        } catch (Exception e) {
            FacesUtil.addErrorMessage(ConstantesReportes.MSG_REPORTES, ERROR_AL_GENERAR_REPORTE);
            logger.error("Metodo generarReporteGerencial: [{}]", e);
        }
    }

    public void obtenerDatosGrafica() {
        int resultado = getValidarTipoCriterioReportesHelper().getTipoCriterio(getReporteDTOHelper().getReportesVO());
        logger.debug("Tipo Criterio: [{}]", resultado);
        getReporteDTOHelper().getGrafica().setListaValores(null);
        getReporteDTOHelper().getGrafica().setTituloGrafica("Insumos");
        getReporteDTOHelper().getGrafica().setNivelGrafica(resultado);
        getReporteDTOHelper().getGrafica().setFechaInicio(getValidacionFechasReportesHelper().validarFecha(getReporteDTOHelper().getReportesVO().getFechaInicioPeriodo(),
                                                                                          getReporteDTOHelper().getReportesVO().getFechaInicio()));
        getReporteDTOHelper().getGrafica().setFechaFinal(getValidacionFechasReportesHelper().validarFecha(getReporteDTOHelper().getReportesVO().getFechaFinPeriodo(),
                                                                                         getReporteDTOHelper().getReportesVO().getFechaFinal()));
        getReporteDTOHelper().getGrafica().setTipoGrafica(getReporteDTOHelper().getReportesVO().getTipoGrafica());
        getReporteDTOHelper().getGrafica().setPresentaEntidad(getReporteDTOHelper().getReportesVO().isMostrarEntidad());
        getReporteDTOHelper().getGrafica().setPresentaEstatus(getReporteDTOHelper().getReportesVO().isMostrarEstatus());
      
        if (resultado == ConstantesReportes.N_0) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaInsumosCero(getReporteDTOHelper().getReportesVO()));
        }
        if (resultado == ConstantesReportes.N_1) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaInsumosUno(getReporteDTOHelper().getReportesVO()));
        }
        if (resultado == ConstantesReportes.N_2 || resultado == ConstantesReportes.N_4) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaInsumosDos(getReporteDTOHelper().getReportesVO()));
        }
        if (resultado == ConstantesReportes.N_3 || resultado == ConstantesReportes.N_5) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaInsumosTres(getReporteDTOHelper().getReportesVO()));
        }
    }

    public void generarReporteEjecutivo() {
        try {
            ValidacionReportesDTO dtoValidacion =
                getValidarReportesHelper().validarFormulario(getReporteDTOHelper().getReportesVO());
            if (dtoValidacion.getErrorTotal() > ConstantesReportes.N_0) {
                FacesUtil.addErrorMessage(ConstantesReportes.MSG_REPORTES, dtoValidacion.getMensaje());
            } else {
                logger.debug("Generar Reporte Ejecutivo");
                getReporteLstHelper().setListaImagenes(null);
                crearFechasReporteEjecutivo();
                logger.debug("Crear grafica");
                obtenerDatosGrafica();
                getAuditoriaService().generarReporteEjecutivoInsumos(getRFCSession());
                if (getReporteDTOHelper().getGrafica().getListaValores() != null &&
                    getReporteDTOHelper().getGrafica().getListaValores().size() > ConstantesReportes.N_0) {
                    getReporteDTOHelper().setRangoFechasReporte(String.format(getMessageResourceString("lbl.reporte.insumos.columna.periodo"),
                                                        getValidacionFechasReportesHelper().getFormatoFecha().format(getReporteDTOHelper().getGrafica().getFechaInicio()),
                                                        getValidacionFechasReportesHelper().getFormatoFecha().format(getReporteDTOHelper().getGrafica().getFechaFinal())));
                    getReporteDTOHelper().setMostrarResultados(true);
                    getReporteDTOHelper().setMostrarFormulario(false);
                    generarGrafica(getReporteDTOHelper().getGrafica());                  
                } else {
                    FacesUtil.addInfoMessage(ConstantesReportes.MSG_REPORTES,
                                             "No se encontraron registros para los criterios de b\u00fasqueda");
                    getReporteDTOHelper().setMostrarResultados(false);
                    getReporteDTOHelper().setMostrarFormulario(true);
                    resetTipoFecha();
                    limpiarFormulario();
                }
            }
        } catch (Exception e) {
            FacesUtil.addErrorMessage(ConstantesReportes.MSG_REPORTES, ERROR_AL_GENERAR_REPORTE);
            logger.error("Metodo generarReporteEjecutivo: [{}]", e);
        }
    }

    public void limpiarFormulario() {
        getReporteLstHelper().setListaInsumos(null);
        getReporteLstHelper().setListaImagenes(null);
        getReporteDTOHelper().setHabilitarFechaPeriodos(true);
        getReporteDTOHelper().setHabilitarFechaPeriodoFin(true);
        getReporteDTOHelper().setHabilitarMesAnio(true);
        getReporteDTOHelper().setRangoFechasReporte(null);
        getReporteDTOHelper().getReportesVO().setActividad(null);
        getReporteDTOHelper().getReportesVO().setActividadPreponderanteId(null);
        getReporteDTOHelper().getReportesVO().setAnioFinal(null);
        getReporteDTOHelper().getReportesVO().setAnioInicial(null);
        getReporteDTOHelper().getReportesVO().setEntidadId(null);
        getReporteDTOHelper().getReportesVO().setEntidad(null);
        getReporteDTOHelper().getReportesVO().setEstatusId(null);
        getReporteDTOHelper().getReportesVO().setFechaFinPeriodo(null);
        getReporteDTOHelper().getReportesVO().setFechaInicioPeriodo(null);
        getReporteDTOHelper().getReportesVO().setMesFinal(null);
        getReporteDTOHelper().getReportesVO().setMesInicial(null);
        getReporteDTOHelper().getReportesVO().setFechaInicio(null);
        getReporteDTOHelper().getReportesVO().setFechaFinal(null);
        getReporteDTOHelper().getReportesVO().setRfc(null);
        getReporteDTOHelper().getReportesVO().setSectorId(null);
        getReporteDTOHelper().getReportesVO().setSubprogramaId(null);
        getReporteDTOHelper().getReportesVO().setTipoFecha(null);
        getReporteDTOHelper().getReportesVO().setTipoGrafica(getMessageResourceString("lbl.reporte.tipo.grafica.barras"));        
        getReporteDTOHelper().getReportesVO().setNumeroPropuesta(null);
        getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
        getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
        getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
        getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
        getReporteDTOHelper().getGrafica().setTipoGrafica(null);
        getReporteDTOHelper().getGrafica().setFechaInicio(null);
        getReporteDTOHelper().getGrafica().setFechaFinal(null);
        getReporteDTOHelper().getGrafica().setListaValores(null);
        getReporteDTOHelper().getGrafica().setNivelGrafica(null);
        getReporteDTOHelper().setMostarCheckCondicion(false);
        getReporteDTOHelper().setContadorCondicion(0);
        getReporteDTOHelper().setReporteEjecutivo(false);
        getGraficaMB().setListaIdGraficas(null);
        getGraficaMB().getListaGraficasId().clear();
        resetPaginaTabla();
    }

    public void regresarFormulario() {
        getReporteDTOHelper().setMostrarResultados(false);
        getReporteDTOHelper().setMostrarFormulario(true);
        resetTipoFecha();
        limpiarFormulario();
    }

    public void exportarExcel() {
        logger.debug("Exportar Excel");
        getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE +
                                                        getReporteDTOHelper().getTipoReporte().getModuloReporte() +
                                                        ConstantesReportes.REPORTE_GERENCIAL);
        getExportarReportesService().crearHeaderReporte(getReporteLstHelper().getUnidadAdministrativa().get(0).getNombre().toUpperCase(),
                                                             getReporteDTOHelper().getTipoReporte().getTituloReporte());
        getExportarReportesService().agregarRegistrosReporte(getValidarReportesMapHelper().listaInsumos(getReporteLstHelper().getListaInsumos()));
    }

    
    public void activarGrafica(ValueChangeEvent event){
        String selectedItems = (String)event.getNewValue();
        if(selectedItems.equals("Barras")){
            getReporteDTOHelper().setReporteEjecutivo(false);
        }else{
            getReporteDTOHelper().setReporteEjecutivo(true);
        }
        getReporteDTOHelper().setContadorCondicion(0);
        getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
        getReporteDTOHelper().getReportesVO().setEstatusId(null);
        getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
        getReporteDTOHelper().getReportesVO().setEntidad(null);
        getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
        getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
        
        getReporteDTOHelper().setMostarCheckCondicion(false);
    }
    
}
