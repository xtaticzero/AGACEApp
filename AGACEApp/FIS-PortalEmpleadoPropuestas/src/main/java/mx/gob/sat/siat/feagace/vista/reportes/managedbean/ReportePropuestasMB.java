package mx.gob.sat.siat.feagace.vista.reportes.managedbean;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.ValidacionReportesDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.reportes.ReportesManagedBeanAbstract;

/**
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@ManagedBean(name = "reportePropuestasMB")
@ViewScoped
public class ReportePropuestasMB extends ReportesManagedBeanAbstract {

    private static final long serialVersionUID = 1L;

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
            getReporteLstHelper().setListaPropuestas(null);
            cargarCatalogoEstatus();
            cargarCatalogoSubprograma();
            cargarCatalogoSector();
            cargarCatalogoMeses();
            cargarCatalogoAnios();
            cargarCatalogoUnidad();
            cargarCatalogoMetodos();
            cargarCatalogoTipoRevision();
            cargarCatalogoTipoPropuesta();
            cargarCatalogoCausaProgramacion();
            cargarCatalogoEntidadPropuestas();
            getReporteDTOHelper().getReportesVO().setMostrarMetodo(false);
            getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
            getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
            getReporteDTOHelper().getReportesVO().setMostrarUnidad(false);
            getReporteDTOHelper().getReportesVO().setActivarMetodo(true);
            getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
            getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
            getReporteDTOHelper().getReportesVO().setActivarUnidad(true);
            getReporteDTOHelper().getReportesVO().setReporte(ConstantesReportes.REPORTE_PROPUESTAS);
            getReporteDTOHelper().setTipoReporte(getValidarEmpleadoReportesService().validarEmpleado(getRFCSession()));
            verificarCentral();
            getReporteDTOHelper().getTipoReporte().setModuloReporte(ConstantesReportes.REPORTE_PROPUESTAS);
            getReporteDTOHelper().getTipoReporte().setTituloReporte(getMessageResourceString("lbl.reporte.tipo.reporte"));
            /**
             * Debemos de identificar si el usuario es central y crear la lista
             * o filtrar por el arace que le corresponde
             */
            obtieneUnidad();

            if (!getReporteDTOHelper().getTipoReporte().getFececEmpleado().getDetalleEmpleado().get(0).getCentral()
                    .getIdArace().equals(Constantes.ACPPCE.intValue())) {
                getReporteDTOHelper().getReportesVO().setAraceId(new BigDecimal(getReporteDTOHelper().getTipoReporte()
                        .getFececEmpleado().getDetalleEmpleado().get(0).getCentral().getIdArace()));
            } else {
                getReporteLstHelper().setListaProgramadores(getValidarEmpleadoReportesService().obtenerProgramadores(
                        getReporteDTOHelper().getTipoReporte().getFececEmpleado(), Constantes.USUARIO_PROGRAMADOR));
                getReporteLstHelper().setListaSubAdministradores(getValidarEmpleadoReportesService()
                        .obtenerProgramadores(getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                                Constantes.USUARIO_SUBADMINISTRADOR));

                if (getReporteLstHelper().getListaProgramadores() != null
                        && !getReporteLstHelper().getListaProgramadores().isEmpty()) {
                    getReporteLstHelper()
                            .setListaEmpleadosCreanPropuesta(getReporteLstHelper().getListaProgramadores());
                }

                if (getReporteLstHelper().getListaSubAdministradores() != null
                        && !getReporteLstHelper().getListaSubAdministradores().isEmpty()) {
                    getReporteLstHelper().getListaEmpleadosCreanPropuesta()
                            .addAll(getReporteLstHelper().getListaSubAdministradores());
                }
            }

        } catch (Exception e) {
            logger.error("Error al iniciar la clase ReportePropuestasMB: [{}]", e);
            usuarioNoPermitido(e);
        }
    }

    public void activarTipoRevicion() {
        if ((getReporteDTOHelper().getReportesVO() != null) && (getReporteDTOHelper().getReportesVO().getMetodoId() != null)) {
            int idMetodo = 0;
            if (getReporteLstHelper().getListaMetodos() != null) {
                for (FececMetodo metodo : getReporteLstHelper().getListaMetodos()) {
                    if ("ORG".equals(metodo.getAbreviatura())) {
                        idMetodo = metodo.getIdMetodo().intValue();
                    }
                }
            }

            if (getReporteDTOHelper().getReportesVO().getMetodoId().intValue() == idMetodo) {
                getReporteDTOHelper().setFlgTipoRevision(true);
                return;
            } else {
                getReporteDTOHelper().setFlgTipoRevision(false);
            }
        }
        getReporteDTOHelper().setFlgTipoRevision(false);
    }

    public void validarPresuntivaInicial() {
        try {
            if (getReporteDTOHelper().getReportesVO().getPresuntivaInicio() != null) {

                if (getReporteDTOHelper().getReportesVO().getPresuntivaFinal() != null
                        && getReporteDTOHelper().getReportesVO().getPresuntivaInicio().compareTo(
                                getReporteDTOHelper().getReportesVO().getPresuntivaFinal()) > ConstantesReportes.N_0) {
                    addErrorMessage(ConstantesReportes.MSG_PRESUNTIVA,
                            "Datos incorrectos, verifique el campo Presuntiva");
                }

            } else if (getReporteDTOHelper().getReportesVO().getPresuntivaInicio() == null
                    && getReporteDTOHelper().getReportesVO().getPresuntivaFinal() != null) {
                addErrorMessage(ConstantesReportes.MSG_PRESUNTIVA, "Debe capturar Presuntiva Inicial");
            }
        } catch (Exception e) {
            logger.error("Error en el metodo validarPresuntivaInicial: [{}]", e);
        }
    }

    public void validarPresuntivaFinal() {
        try {
            if (getReporteDTOHelper().getReportesVO().getPresuntivaInicio() != null) {
                if (getReporteDTOHelper().getReportesVO().getPresuntivaFinal() != null) {
                    if (getReporteDTOHelper().getReportesVO().getPresuntivaInicio().compareTo(
                            getReporteDTOHelper().getReportesVO().getPresuntivaFinal()) > ConstantesReportes.N_0) {
                        addErrorMessage(ConstantesReportes.MSG_PRESUNTIVA,
                                "Presuntiva Final debe ser mayor a Presuntiva Inicial");
                    }
                } else {
                    addErrorMessage(ConstantesReportes.MSG_PRESUNTIVA, "Debe capturar Presuntiva Final");
                }
            } else {
                addErrorMessage(ConstantesReportes.MSG_PRESUNTIVA, "Debe capturar Presuntiva Inicial");
            }
        } catch (Exception e) {
            logger.error("Error en el metodo validarPresuntiva: [{}]", e);
        }
    }

    public void selectRadioItem(ValueChangeEvent event) {
        String selectedItems = (String) event.getNewValue();
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
        if (getReporteDTOHelper().getReportesVO().getTipoFecha().trim()
                .equals(getMessageResourceString(ConstantesReportes.TIPO_FECHA_MESES))) {
            Date fechaInicial = getValidacionFechasReportesHelper().crearFecha(
                    getReporteDTOHelper().getReportesVO().getMesInicial(),
                    getReporteDTOHelper().getReportesVO().getAnioInicial(), false);
            Date fechaFinal = getValidacionFechasReportesHelper().crearFecha(
                    getReporteDTOHelper().getReportesVO().getMesFinal(),
                    getReporteDTOHelper().getReportesVO().getAnioFinal(), true);
            getReporteDTOHelper().getReportesVO().setFechaInicioPeriodo(null);
            getReporteDTOHelper().getReportesVO().setFechaFinPeriodo(null);
            getReporteDTOHelper().getReportesVO().setFechaInicio(fechaInicial);
            getReporteDTOHelper().getReportesVO().setFechaFinal(fechaFinal);
            logger.debug("Fecha Inicial: [{}]",
                    getValidacionFechasReportesHelper().getFormatoFecha().format(fechaInicial));
            logger.debug("Fecha Final: [{}]", getValidacionFechasReportesHelper().getFormatoFecha().format(fechaFinal));
        }
    }

    public void generarReporteGerencial() {
        try {
            ValidacionReportesDTO dtoValidacion = getValidarReportesHelper()
                    .validarFormulario(getReporteDTOHelper().getReportesVO());
            if (dtoValidacion.getErrorTotal() > ConstantesReportes.N_0) {
                addErrorMessage(ConstantesReportes.MSG_REPORTES, dtoValidacion.getMensaje());
            } else {
                logger.debug("Se genera el reporte");
                getReporteLstHelper().setListaPropuestas(getValidarReportesMapHelper().amparadoPPEEPropuestas(
                        getGenerarReportesService().generarReportePropuestas(getReporteDTOHelper().getReportesVO(),
                                getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                                getReporteLstHelper().getListaEmpleadosCreanPropuesta(), getReporteLstHelper().getListaUnidad())));
                
                getAuditoriaService().generarReporteGerencialPropuestas(getRFCSession());
                if (getReporteLstHelper().getListaPropuestas() != null
                        && getReporteLstHelper().getListaPropuestas().size() > ConstantesReportes.N_0) {
                    getReporteDTOHelper()
                            .setRangoFechasReporte(
                                    String.format(getMessageResourceString("lbl.reporte.insumos.columna.periodo"),
                                            getValidacionFechasReportesHelper().getFormatoFecha()
                                            .format(getReporteDTOHelper().getReportesVO()
                                                    .getFechaInicioPeriodo()),
                                            getValidacionFechasReportesHelper().getFormatoFecha().format(
                                                    getReporteDTOHelper().getReportesVO().getFechaFinPeriodo())));
                    logger.debug("Total registros: [{}]", getReporteLstHelper().getListaPropuestas().size());
                    exportarExcel();
                    getReporteDTOHelper().setMostrarResultados(true);
                    getReporteDTOHelper().setMostrarFormulario(false);
                } else {
                    addMessage(ConstantesReportes.MSG_REPORTES,
                            "No se encontraron registros para los criterios de b\u00fasqueda");
                    getReporteDTOHelper().setMostrarResultados(false);
                    getReporteDTOHelper().setMostrarFormulario(true);
                    limpiarFormulario();
                }
            }
        } catch (Exception e) {
            logger.error("Metodo generarReporte: [{}]", e);
        }
    }

    public void obtenerDatosGrafica() {
        int resultado = getValidarTipoCriterioReportesHelper().getTipoCriterio(getReporteDTOHelper().getReportesVO());
        logger.debug("Tipo Criterio: [{}]", resultado);
        getReporteDTOHelper().getGrafica().setListaValores(null);
        getReporteDTOHelper().getGrafica().setTituloGrafica("Propuestas");
        getReporteDTOHelper().getGrafica().setNivelGrafica(resultado);
        getReporteDTOHelper().getGrafica()
                .setFechaInicio(getValidacionFechasReportesHelper().validarFecha(
                                getReporteDTOHelper().getReportesVO().getFechaInicioPeriodo(),
                                getReporteDTOHelper().getReportesVO().getFechaInicio()));
        getReporteDTOHelper().getGrafica()
                .setFechaFinal(getValidacionFechasReportesHelper().validarFecha(
                                getReporteDTOHelper().getReportesVO().getFechaFinPeriodo(),
                                getReporteDTOHelper().getReportesVO().getFechaFinal()));
        getReporteDTOHelper().getGrafica().setTipoGrafica(getReporteDTOHelper().getReportesVO().getTipoGrafica());
        getReporteDTOHelper().getGrafica().setPresentaEntidad(getReporteDTOHelper().getReportesVO().isMostrarEntidad());
        getReporteDTOHelper().getGrafica().setPresentaEstatus(getReporteDTOHelper().getReportesVO().isMostrarEstatus());
        getReporteDTOHelper().getGrafica().setPresentaMetodo(getReporteDTOHelper().getReportesVO().isMostrarMetodo());
        getReporteDTOHelper().getGrafica()
                .setPresentaUnidadAdministrativa(getReporteDTOHelper().getReportesVO().isMostrarUnidad());

        if (resultado == ConstantesReportes.N_0) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaPropuestasCero(
                    getReporteDTOHelper().getReportesVO(), getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                    getReporteLstHelper().getListaEmpleadosCreanPropuesta()));
        }
        if (resultado == ConstantesReportes.N_1) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaPropuestasUno(
                    getReporteDTOHelper().getReportesVO(), getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                    getReporteLstHelper().getListaEmpleadosCreanPropuesta()));
        }
        if (resultado == ConstantesReportes.N_2 || resultado == ConstantesReportes.N_4) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaPropuestasDos(
                    getReporteDTOHelper().getReportesVO(), getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                    getReporteLstHelper().getListaEmpleadosCreanPropuesta()));
        }
        if (resultado == ConstantesReportes.N_3 || resultado == ConstantesReportes.N_5) {
            getReporteDTOHelper().getGrafica().setListaValores(getGenerarReportesService().graficaPropuestasTres(
                    getReporteDTOHelper().getReportesVO(), getReporteDTOHelper().getTipoReporte().getFececEmpleado(),
                    getReporteLstHelper().getListaEmpleadosCreanPropuesta()));
        }
    }

    public void generarReporteEjecutivo() {
        ValidacionReportesDTO dtoValidacion = getValidarReportesHelper()
                .validarFormulario(getReporteDTOHelper().getReportesVO());
        if (dtoValidacion.getErrorTotal() > ConstantesReportes.N_0) {
            addErrorMessage(ConstantesReportes.MSG_REPORTES, dtoValidacion.getMensaje());
        } else {
            logger.error("Generar Reporte Ejecutivo");
            crearFechasReporteEjecutivo();
            logger.debug("Crear grafica");
            obtenerDatosGrafica();
            getAuditoriaService().generarReporteEjecutivoPropuestas(getRFCSession());
            if (getReporteDTOHelper().getGrafica().getListaValores() != null
                    && getReporteDTOHelper().getGrafica().getListaValores().size() > ConstantesReportes.N_0) {
                getReporteDTOHelper().setRangoFechasReporte(
                        String.format(getMessageResourceString("lbl.reporte.insumos.columna.periodo"),
                                getValidacionFechasReportesHelper().getFormatoFecha()
                                .format(getReporteDTOHelper().getGrafica().getFechaInicio()),
                                getValidacionFechasReportesHelper().getFormatoFecha()
                                .format(getReporteDTOHelper().getGrafica().getFechaFinal())));
                getReporteDTOHelper().setMostrarResultados(true);
                getReporteDTOHelper().setMostrarFormulario(false);
                generarGrafica(getReporteDTOHelper().getGrafica());
            } else {
                addMessage(ConstantesReportes.MSG_REPORTES,
                        "No se encontraron registros para los criterios de b\u00fasqueda");
                getReporteDTOHelper().setMostrarResultados(false);
                getReporteDTOHelper().setMostrarFormulario(true);
                resetTipoFecha();
                limpiarFormulario();
            }
        }
    }

    public void limpiarFormulario() {
        getReporteLstHelper().setListaPropuestas(null);
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
        getReporteDTOHelper().getReportesVO()
                .setTipoGrafica(getMessageResourceString("lbl.reporte.tipo.grafica.barras"));
        getReporteDTOHelper().getReportesVO().setMetodoId(null);
        getReporteDTOHelper().getReportesVO().setTipoRevisionId(null);

        if (getReporteDTOHelper().getTipoReporte().getFececEmpleado().getDetalleEmpleado().get(0).getCentral()
                .getIdArace().equals(Constantes.ACPPCE.intValue())) {
            getReporteDTOHelper().getReportesVO().setAraceId(null);
        }
        getReporteDTOHelper().getReportesVO().setTipoPropuestaId(null);
        getReporteDTOHelper().getReportesVO().setCausaProgramacionId(null);
        getReporteDTOHelper().getReportesVO().setPresuntivaInicio(null);
        getReporteDTOHelper().getReportesVO().setPresuntivaFinal(null);
        getReporteDTOHelper().getReportesVO().setNumeroPropuesta(null);
        getReporteDTOHelper().getGrafica().setTipoGrafica(null);
        getReporteDTOHelper().getGrafica().setFechaInicio(null);
        getReporteDTOHelper().getGrafica().setFechaFinal(null);
        getReporteDTOHelper().getGrafica().setListaValores(null);
        getReporteDTOHelper().getGrafica().setNivelGrafica(null);
        getReporteDTOHelper().getReportesVO().setMostrarMetodo(false);
        getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
        getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
        getReporteDTOHelper().getReportesVO().setMostrarUnidad(false);
        getReporteDTOHelper().getReportesVO().setActivarMetodo(true);
        getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
        getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
        getReporteDTOHelper().getReportesVO().setActivarUnidad(true);
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
        getReporteDTOHelper().setFlgTipoRevision(false);
        limpiarFormulario();
    }

    public void exportarExcel() {
        logger.debug("Exportar Excel");
        getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE
                + getReporteDTOHelper().getTipoReporte().getModuloReporte() + ConstantesReportes.REPORTE_GERENCIAL);
        getExportarReportesService().crearHeaderReporte(
                getReporteLstHelper().getUnidadAdministrativa().get(0).getNombre().toUpperCase(),
                getReporteDTOHelper().getTipoReporte().getTituloReporte());
        getExportarReportesService().agregarRegistrosReporte(
                getValidarReportesMapHelper().listaPropuestas(getReporteLstHelper().getListaPropuestas()));
    }

    public void activarGrafica(ValueChangeEvent event) {
        String selectedItems = (String) event.getNewValue();
        if (selectedItems.equals("Barras")) {
            getReporteDTOHelper().setReporteEjecutivo(false);
        } else {
            getReporteDTOHelper().setReporteEjecutivo(true);
        }
        getReporteDTOHelper().setContadorCondicion(0);
        getReporteDTOHelper().getReportesVO().setActivarEstatus(true);
        getReporteDTOHelper().getReportesVO().setEstatusId(null);
        getReporteDTOHelper().getReportesVO().setActivarEntidad(true);
        getReporteDTOHelper().getReportesVO().setEntidad(null);
        getReporteDTOHelper().getReportesVO().setActivarMetodo(true);
        getReporteDTOHelper().getReportesVO().setMetodoId(null);
        getReporteDTOHelper().getReportesVO().setMostrarEntidad(false);
        getReporteDTOHelper().getReportesVO().setMostrarEstatus(false);
        getReporteDTOHelper().getReportesVO().setMostrarMetodo(false);
        getReporteDTOHelper().setMostarCheckCondicion(false);
    }

}
