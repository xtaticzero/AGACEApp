package mx.gob.sat.siat.feagace.vista.propuestas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecebAccionPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.HistoricoPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarFirmarDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.VerificarProcedenciaService;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropAsignadasMBHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.ValidarOrdenesREMB;

/**
 * @author sergio.vaca
 *
 */
@ManagedBean(name = "validarFirmarDoctoMB")
@SessionScoped
public class ValidarFirmarDoctoMB extends AbstractValidarFirmarDoctoMB {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String VALIDAR_RESUMEN_REDIRECCIONAR = "validarResumen?faces-redirect=true";
    private static final String VALIDAR_RESUMEN_DETALLE_REDIRECCIONAR = "validarResumenDetalle?faces-redirect=true";
    private static final String TOGLE_VISIBLE = "VISIBLE";

    @ManagedProperty(value = "#{validarFirmarDoctoService}")
    private transient ValidarFirmarDoctoService validarFirmarDoctoService;

    @ManagedProperty(value = "#{validarOrdenesRE}")
    private transient ValidarOrdenesREMB validarOrdenesREMB;

    @ManagedProperty(value = "#{fecebAccionPropuestaService}")
    private transient FecebAccionPropuestaService fecebAccionPropuestaService;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;

    @ManagedProperty(value = "#{consultarPropAsignadasMBHelper}")
    private ConsultarPropAsignadasMBHelper consultarPropAsignadasMBHelper;

    @ManagedProperty(value = "#{historicoDocPropuestaService}")
    private transient HistoricoPropuestaService historicoPropuestaService;

    @ManagedProperty(value = "#{verificarProcedenciaService}")
    private transient VerificarProcedenciaService verificarProcedenciaService;

    @ManagedProperty(value = "#{validarRetroalimentarPropuestaService}")
    private transient ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;

    @ManagedProperty(value = "#{auditoriaPropuestas}")
    private transient PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService;

    public String obtenerDetalleResumen() {
        setEstatusActual(TiposEstatusPropuestaEnum.obtenerEnumById(getIdOpcion()));
        setUnidadesAdmin(validarFirmarDoctoService.obtenerUnidades());
        String redireccion = "";
        switch (getEstatusActual()) {
            case REVISION_NO_APROBADA:
            case REVISION_EMISION:
                getValidarOrdenesREMB().setMetodoSeleccionado(null);
                getValidarOrdenesREMB().setAccionOrigen(getEstatusActual().getIdAccionOrigen());
                getValidarOrdenesREMB().setTitulo(getEstatusActual().getDescGrid());
                redireccion = getValidarOrdenesREMB().cargarOrdenesValidar();
                break;
            default:
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                boolean esSuplente = (Boolean) session.getAttribute("essuplente");
                setRfcFirmante(getRFCSession());
                setIdEmpleadoFirmado(null);
                if (esSuplente) {
                    setRfcFirmante((String) session.getAttribute("rfcSuplente"));
                    setIdEmpleadoFirmado(new BigDecimal(session.getAttribute("suplente").toString()));
                } else {
                    obtenerIdEmpleado();
                }
                if (getIdEmpleadoFirmado() != null) {
                    setRegistrosResumen(getValidarFirmarDoctoService().obtenerDetalleResumen(getRfcFirmante(), getIdOpcion(), getUnidadesAdmin()));
                    redireccion = VALIDAR_RESUMEN_REDIRECCIONAR;
                }
                break;
        }
        return redireccion;
    }

    public String consultarRfc() {
        inicializarListas();
        getValidarFirmarDoctoService().obtenerInformacionAdicional(getPropuestaActual(), getRegistrosCancelacion(), getRegistrosRechazo(), getRegistrosTransferencia(),
                getRegistrosRetroalimentacion(), getEstatusActual());
        validarPresentacion();
        return VALIDAR_RESUMEN_DETALLE_REDIRECCIONAR;
    }

    public void consultarDoctoCancelacion() {
        setListDocumentos(getValidarFirmarDoctoService().obtenerDocumentosCancelacion(getCancelacionActual()));
        abrirDialogoDocumentos();
    }

    public void consultarDoctoRechazo() {
        setListDocumentos(getValidarFirmarDoctoService().obtenerDocumentosRechazo(getRechazoActual()));
        abrirDialogoDocumentos();
    }

    public void consultarDoctoTransferencia() {
        setListDocumentos(getValidarFirmarDoctoService().obtenerDocumentosTransferencia(getTransferenciaActual()));
        abrirDialogoDocumentos();
    }

    public void consultarDoctoRetroalimentacion() {
        setListDocumentos(getValidarFirmarDoctoService().obtenerDocumentosRetroalimentacion(getRetroActual()));
        abrirDialogoDocumentos();
    }

    public void iniciaAceptarProceso() {
        abrirDialogoAceptaProceso();
    }

    public void iniciaRechazarProceso() {
        abrirDialogoRechazarProceso();
    }

    public void aceptarProceso() {

        if (TiposEstatusPropuestaEnum.CANCELADAS_VALIDAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.CANCELADADAS_NO_APROBAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.CANCELADAS_EMISION.equals(getEstatusActual())) {
            getPistasAuditoriasPropuestasService().pistaAprobarCanelacionPendienteValidacion(getPropuestaActual());
        }

        if (TiposEstatusPropuestaEnum.RECHAZADAS_VALIDAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.RECHAZADAS_NO_APROBAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.RECHAZADAS_EMISION.equals(getEstatusActual())) {
            getPistasAuditoriasPropuestasService().pistaAprobarRechazoPendienteValidacion(getPropuestaActual());
        }

        if (TiposEstatusPropuestaEnum.TRANSFERIDAS_VALIDAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.TRANSFERIDAS_NO_APROBAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.TRANSFERIDAS_EMISION.equals(getEstatusActual())) {
            getPistasAuditoriasPropuestasService().pistaAprobarTransferenciaPendienteValidacion(getPropuestaActual());
        }

        if (TiposEstatusPropuestaEnum.RETROALIMENTADAS_VALIDAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.RETROALIMENTADAS_NO_APROBAR.equals(getEstatusActual())
                || TiposEstatusPropuestaEnum.RETROALIMENTADAS_EMISION.equals(getEstatusActual())) {
            getPistasAuditoriasPropuestasService().pistaAprobarRetroPendienteValidacion(getPropuestaActual());
        }
        getValidarFirmarDoctoService().aceptarProceso(getPropuestaActual(), getEstatusActual(), getRegistrosTransferencia(), getIdEmpleadoFirmado(), getRegistrosRetroalimentacion());
        addMessage(null, String.format(getEstatusActual().getOpcion().getMsgExitoAprobar(), getPropuestaActual().getIdRegistro()));
        abrirDialogoFinal();
    }

    public void rechazarProceso() {
        setFechaNoAprobacion(new Date());
        setObservaciones("");
        abrirDialogoRechazarProcesoObservaciones();
    }

    public void rechazarProcesoAceptar() {
        if (getObservaciones() == null || getObservaciones().trim().equals("")) {
            addErrorMessage(null, "Ingrese el campo de Observaciones");
            abrirDialogoRechazarProcesoObservaciones();
        } else {
            if (TiposEstatusPropuestaEnum.CANCELADAS_VALIDAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.CANCELADADAS_NO_APROBAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.CANCELADAS_EMISION.equals(getEstatusActual())) {
                getPistasAuditoriasPropuestasService().pistaNoAprobarCanelacionPendienteValidacion(getPropuestaActual());
            }

            if (TiposEstatusPropuestaEnum.RECHAZADAS_VALIDAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.RECHAZADAS_NO_APROBAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.RECHAZADAS_EMISION.equals(getEstatusActual())) {
                getPistasAuditoriasPropuestasService().pistaNoAprobarRechazoPendienteValidacion(getPropuestaActual());
            }

            if (TiposEstatusPropuestaEnum.TRANSFERIDAS_VALIDAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.TRANSFERIDAS_NO_APROBAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.TRANSFERIDAS_EMISION.equals(getEstatusActual())) {
                getPistasAuditoriasPropuestasService().pistaNoAprobarTransferenciaPendienteValidacion(getPropuestaActual());
            }

            if (TiposEstatusPropuestaEnum.RETROALIMENTADAS_VALIDAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.RETROALIMENTADAS_NO_APROBAR.equals(getEstatusActual())
                    || TiposEstatusPropuestaEnum.RETROALIMENTADAS_EMISION.equals(getEstatusActual())) {
                getPistasAuditoriasPropuestasService().pistaNoAprobarRetroPendienteValidacion(getPropuestaActual());
            }

            getValidarFirmarDoctoService().rechazarProceso(getPropuestaActual(), getEstatusActual(), getObservaciones(), getIdEmpleadoFirmado(), getRegistrosRetroalimentacion());
            addMessage(null, String.format(getEstatusActual().getOpcion().getMsgExitoNoAprobar(), getPropuestaActual().getIdRegistro()));
            abrirDialogoFinal();
        }
    }

    public String redireccionar() {
        setRegistrosResumen(getValidarFirmarDoctoService().obtenerDetalleResumen(getRfcFirmante(), getIdOpcion(), getUnidadesAdmin()));
        return VALIDAR_RESUMEN_REDIRECCIONAR;
    }

    private void obtenerIdEmpleado() {

        try {
            EmpleadoDTO empleadoLogueado = validarFirmarDoctoService.obtenerDatosFirmante(getRFCSession());
            setIdEmpleadoFirmado(empleadoLogueado.getIdEmpleado());
            throw new NoExisteEmpleadoException("empleado.no.existente");
        } catch (Exception e) {
            addErrorMessage(null, "No se encuentra el empleado " + getRfcFirmante());
        }
    }

    private void inicializarListas() {
        setRegistrosCancelacion(new ArrayList<FecetCancelacion>());
        setRegistrosRechazo(new ArrayList<FecetContadorPropuestasRechazados>());
        setRegistrosTransferencia(new ArrayList<FecetTransferenciaContador>());
        setRegistrosRetroalimentacion(new ArrayList<FecetRetroContador>());
    }

    public final ValidarFirmarDoctoService getValidarFirmarDoctoService() {
        return validarFirmarDoctoService;
    }

    public final void setValidarFirmarDoctoService(ValidarFirmarDoctoService validarFirmarDoctoService) {
        this.validarFirmarDoctoService = validarFirmarDoctoService;
    }

    public final ValidarOrdenesREMB getValidarOrdenesREMB() {
        return validarOrdenesREMB;
    }

    public final void setValidarOrdenesREMB(ValidarOrdenesREMB validarOrdenesREMB) {
        this.validarOrdenesREMB = validarOrdenesREMB;
    }

    public FecebAccionPropuestaService getFecebAccionPropuestaService() {
        return fecebAccionPropuestaService;
    }

    public void setFecebAccionPropuestaService(FecebAccionPropuestaService fecebAccionPropuestaService) {
        this.fecebAccionPropuestaService = fecebAccionPropuestaService;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void setConsultarPropuestasAsignadasService(
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }

    public ConsultarPropAsignadasMBHelper getConsultarPropAsignadasMBHelper() {
        return consultarPropAsignadasMBHelper;
    }

    public void setConsultarPropAsignadasMBHelper(ConsultarPropAsignadasMBHelper consultarPropAsignadasMBHelper) {
        this.consultarPropAsignadasMBHelper = consultarPropAsignadasMBHelper;
    }

    public HistoricoPropuestaService getHistoricoPropuestaService() {
        return historicoPropuestaService;
    }

    public void setHistoricoPropuestaService(HistoricoPropuestaService historicoPropuestaService) {
        this.historicoPropuestaService = historicoPropuestaService;
    }

    public VerificarProcedenciaService getVerificarProcedenciaService() {
        return verificarProcedenciaService;
    }

    public void setVerificarProcedenciaService(VerificarProcedenciaService verificarProcedenciaService) {
        this.verificarProcedenciaService = verificarProcedenciaService;
    }

    public ValidarRetroalimentarPropuestaService getValidarRetroalimentarPropuestaService() {
        return validarRetroalimentarPropuestaService;
    }

    public void setValidarRetroalimentarPropuestaService(
            ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService) {
        this.validarRetroalimentarPropuestaService = validarRetroalimentarPropuestaService;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void handleToggleAcciones(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            logger.error("Consultando Historial de Acciones de la propuesta");
            Map<BigDecimal, EmpleadoDTO> listaEmpleados = new HashMap<BigDecimal, EmpleadoDTO>();

            setListaHistoricoAccion(new ArrayList<FecebAccionPropuesta>());

            List<FecebAccionPropuesta> listaAccionSinTabla = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaSinTabla(getPropuestaActual().getIdPropuesta());
            List<FecebAccionPropuesta> listaAccionTablaRechazo = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaRechazadas(getPropuestaActual().getIdPropuesta(),
                            AccionesFuncionarioEnum.RECHAZAR);
            List<FecebAccionPropuesta> listaAccionTablaCancelar = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaCancelada(getPropuestaActual().getIdPropuesta(),
                            AccionesFuncionarioEnum.CANCELAR);
            List<FecebAccionPropuesta> listaAccionTablaRetrolimentar = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaRetroalimentar(getPropuestaActual().getIdPropuesta(),
                            AccionesFuncionarioEnum.RETROALIMENTAR, AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA);
            List<FecebAccionPropuesta> listaAccionTablaTransferir = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaTransferir(getPropuestaActual().getIdPropuesta(),
                            AccionesFuncionarioEnum.TRANSFERIR);

            agregarDocumentoLista(listaAccionSinTabla);
            agregarDocumentoLista(listaAccionTablaRechazo);
            agregarDocumentoLista(listaAccionTablaCancelar);
            agregarDocumentoLista(listaAccionTablaRetrolimentar);
            agregarDocumentoLista(listaAccionTablaTransferir);

            Collections.sort(getListaHistoricoAccion(), new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    FecebAccionPropuesta p1 = (FecebAccionPropuesta) o1;
                    FecebAccionPropuesta p2 = (FecebAccionPropuesta) o2;
                    return p1.getIdAccionPropuesta().compareTo(p2.getIdAccionPropuesta());
                }
            });

            for (FecebAccionPropuesta accion : getListaHistoricoAccion()) {
                try {
                    EmpleadoDTO empleado = consultarPropAsignadasMBHelper.obtenEmpleadoById(
                            consultarPropuestasAsignadasService, accion, listaEmpleados);
                    TipoEmpleadoEnum tipoEmpleadoEnum = consultarPropAsignadasMBHelper.obtenerRolEmpleado(accion,
                            empleado);
                    accion.setDescripcionTipoEmpleado(tipoEmpleadoEnum.getDescripcion());
                    accion.setEmpleadoDto(empleado);
                } catch (Exception e) {
                    EmpleadoDTO empleado = new EmpleadoDTO();
                    accion.setEmpleadoDto(empleado);
                    logger.error(e.getMessage());
                }
            }
        }
    }

    private void agregarDocumentoLista(List<FecebAccionPropuesta> listaDocumetos) {
        if (listaDocumetos != null && !listaDocumetos.isEmpty()) {
            getListaHistoricoAccion().addAll(listaDocumetos);
        }
    }

    public void obtenerDocumentosHistoricoAccion() {

        AccionesFuncionarioEnum accionesFuncionarioEnum = getHistoricoAccion()
                .getAccionFuncionarioEnum();
        List<DocumentoVista> listaDocumentos = new ArrayList<DocumentoVista>();

        if (accionesFuncionarioEnum != null) {
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RECHAZAR) {

                listaDocumentos = obtenerDocumentosHistorial(historicoPropuestaService.buscarDoctosRechazoPorIdRechazo(
                        getHistoricoAccion().getIdRechazo()));
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTAR) {
                listaDocumentos = obtenerDocumentosHistorial(historicoPropuestaService.obtenerDocumentoByIdRetro(
                        getHistoricoAccion().getIdRetroalimentacion()));
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.TRANSFERIR) {
                listaDocumentos = obtenerDocumentosHistorial(historicoPropuestaService.obtenerDocumentoByIdTransferencia(
                        getHistoricoAccion().getIdTransferencia()));
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.CANCELAR) {
                listaDocumentos = obtenerDocumentosHistorial(historicoPropuestaService.obtenerDocumentoByIdCancelacion(
                        getHistoricoAccion().getIdCancelacion()));
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA) {
                listaDocumentos = obtenerDocumentosHistorial(historicoPropuestaService.obtenerDocumentoByIdRetro(
                        getHistoricoAccion().getIdRetroalimentacion()));
            }
        }
        setListDocumentos(listaDocumentos);
        setLabelHeader("Hist\u00f3rico");
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoDocumentos').show();");
    }

    public void handleToggleExpedienteOrden(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            setListaHistorialOrden(
                    historicoPropuestaService.obtenerDocsOrdenHistorial(getPropuestaActual().getIdPropuesta()));

            setListaHistorialOficio(null);
            if (getListaHistorialOrden() != null && !getListaHistorialOrden().isEmpty()) {

                setListaHistorialOficio(historicoPropuestaService.oficiosHistorial(verificarProcedenciaService
                        .obtenerOrden(getPropuestaActual().getIdPropuesta()).get(0).getIdOrden()));
            }
        }
    }

    public void handleToggleRetrolimentacion(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            setListaHistoricoRetroalimentar(validarRetroalimentarPropuestaService
                    .getLstHistorialRetroalimentacionPropuesta(getPropuestaActual().getIdPropuesta()));
        }
    }

    public void obtenerImpuestosRetro() {
        setListaImpuestos(getValidarRetroalimentarPropuestaService().getImpuestosRetroPropuesta(
                getSolicitudRetroalimentacion().getIdRetroalimentacion()));

        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getListaImpuestos() != null) {
            for (FecetImpuesto impuesto : getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(impuesto.getMonto());
            }
        }
        setPresuntiva(totalImpuesto);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoImpuestosRetro').show();");
    }

    public void obtenerDocumentosRetroalimentados() {
        setListaDocumentos(getValidarRetroalimentarPropuestaService().getDetalleDocRetroalimentacionByIdRetro(
                getSolicitudRetroalimentacion().getIdRetroalimentacion()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public void obtenerSolictudRetroalimentacion() {
        setListaSolicitudRetroalimentacion(getValidarRetroalimentarPropuestaService().getLstOrigenRetroalimentacionPropuesta(
                getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoRetroalimentacion').show();");
    }

    public void obtenerDocumentosRetroalimentacion() {
        setListaDocumentos(getValidarRetroalimentarPropuestaService().getDetalleDocRetroalimentacionByIdRetro(
                getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public PistasAuditoriasPropuestasService getPistasAuditoriasPropuestasService() {
        return pistasAuditoriasPropuestasService;
    }

    public void setPistasAuditoriasPropuestasService(PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService) {
        this.pistasAuditoriasPropuestasService = pistasAuditoriasPropuestasService;
    }

}
