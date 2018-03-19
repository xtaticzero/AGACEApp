/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.common;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACAOCE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACOECE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORESTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORTE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_OCCIDENTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_PACIFICO_NORTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_SUR;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.EHO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ORG;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.REE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.UCA;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.component.UIComponent;

import org.apache.commons.lang.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ValidarRetrolaimentarFuncionesVistaAbstract extends ValidarRetroalimentarServicioVistaAbstract {

    private static final long serialVersionUID = -6612543085931880458L;

    protected static final String ERROR_FECHA_PRESENTACION = "error.fecha.presentacion.comite";
    protected static final String ERROR_EMPLEADO_NO_AUTORIZADO = "error.propuestas.validar.retroalimentar.empleado.no.autorizado";
    protected static final String LABEL_RETRO_DETALLE = "label.prpuestas.validar.retro.detalle.dialog.msj";
    protected static final String ERROR_IMPUESTO = "error.propuestas.validar.impuesto";

    private static final String VISIBLE = "VISIBLE";

    public void propuestasXValidarToggle(ToggleEvent event) {
        UIComponent tog = event.getComponent();

        if (event.getVisibility().toString().equals(VISIBLE)) {
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            getRetroalimentarLstHelper().setListaPropuestaPendienteSeleccionada(new ArrayList<FecetPropuesta>());
            setFlgHabilitarPendiente(false);
            setFlgHabilitarRechazo(false);
            getValidarYRetroalimentarHelper().getPropuestaPendiente().setObservaciones("");
            getValidarYRetroalimentarHelper().getPropuestaRechazo().setDescripcion("");
            setEsProcesable(false);
            if (!getTipoFieldset().equals("") && !getTipoFieldset().equals(tog.getId())) {
                cerrarFieldeset();
            }
            setTipoFieldset(tog.getId());
            getRetroalimentarLstHelper().setListaDocumentoPendiente(new ArrayList<FecetPropPendiente>());
            getRetroalimentarLstHelper().setListaDocumentoRechazo(new ArrayList<FecetRechazoPropuesta>());
            getRetroalimentarLstHelper().setListaImpuesto(new ArrayList<FecetImpuesto>());
            try {
                resetPanelPropuestasPorValidar();
            } catch (ValidarRetroalimentarPropuestaException ex) {
                logger.error(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()), ex);
                addErrorMessage(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()));
            }
        } else {
            if (getTipoFieldset().equals(tog.getId())) {
                setTipoFieldset("");
            }
        }
    }

    public void propuestasPendientesToggle(ToggleEvent event) {
        UIComponent tog = event.getComponent();

        if (event.getVisibility().toString().equals(VISIBLE)) {
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            getRetroalimentarLstHelper().setListaPropuestaPendienteSeleccionada(new ArrayList<FecetPropuesta>());
            setFlgHabilitarPendiente(false);
            setFlgHabilitarRechazo(false);
            getValidarYRetroalimentarHelper().getPropuestaRechazo().setDescripcion("");
            setEsProcesable(false);
            if (!getTipoFieldset().equals("") && !getTipoFieldset().equals(tog.getId())) {
                cerrarFieldeset();
            }
            setTipoFieldset(tog.getId());
            getRetroalimentarLstHelper().setListaImpuesto(new ArrayList<FecetImpuesto>());
            getRetroalimentarLstHelper().setListaDocumentoRechazo(new ArrayList<FecetRechazoPropuesta>());
            try {
                resetPanelPropuestasPendientes();
            } catch (ValidarRetroalimentarPropuestaException ex) {
                logger.error(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()), ex);
                addErrorMessage(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()));
            }
        } else {
            if (getTipoFieldset().equals(tog.getId())) {
                setTipoFieldset("");
            }
        }
    }

    public void propuestasXRetroToggle(ToggleEvent event) {
        UIComponent tog = event.getComponent();

        if (event.getVisibility().toString().equals(VISIBLE)) {
            if (!getTipoFieldset().equals("") && !getTipoFieldset().equals(tog.getId())) {
                cerrarFieldeset();
            }
            setTipoFieldset(tog.getId());
            try {
                resetPanelRetroalimentar();
            } catch (ValidarRetroalimentarPropuestaException ex) {
                logger.error(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()), ex);
                addErrorMessage(getMessageResourceString(ERROR_EMPLEADO_NO_AUTORIZADO, getRFCSession()));
            }
        } else {
            if (getTipoFieldset().equals(tog.getId())) {
                setTipoFieldset("");
            }
        }
    }

    public void cambioMetodoToggle(ToggleEvent event) {
        UIComponent tog = event.getComponent();

        if (event.getVisibility().toString().equals(VISIBLE)) {
            if (!getTipoFieldset().equals("") && !getTipoFieldset().equals(tog.getId())) {
                cerrarFieldeset();
            }
            setTipoFieldset(tog.getId());
            try {
                resetPanelCambioMetodo();
            } catch (ValidarRetroalimentarPropuestaException ex) {
                addErrorMessage(ex.getMessage());
            }
        } else {
            if (getTipoFieldset().equals(tog.getId())) {
                setTipoFieldset("");
            }
        }
    }

    public void cerrarFieldeset() {
        RequestContext context = RequestContext.getCurrentInstance();
        StringBuilder fieldset = new StringBuilder();
        fieldset.append("PF('").append(getTipoFieldset()).append("').toggle();");
        context.execute(fieldset.toString());
    }

    public void resetPanelPropuestasPorValidar() throws ValidarRetroalimentarPropuestaException {
        if (getRetroalimentarLstHelper().getListaPropuestasXValidar() != null) {
            getRetroalimentarLstHelper().getListaPropuestasXValidar().clear();
        }
        getRetroalimentarLstHelper().setListaPropuestasXValidar(
                getValidarRetroalimentarPropuestaService().buscaPropuestasPorValidar(getRFCSession()));
    }

    public void resetPanelPropuestasPendientes() throws ValidarRetroalimentarPropuestaException {
        if (getRetroalimentarLstHelper().getListaPropuestasPendientes() != null) {
            getRetroalimentarLstHelper().setListaPropuestasPendientes(new ArrayList<FecetPropuesta>());
        }
        getRetroalimentarLstHelper().setListaPropuestasPendientes(
                getValidarRetroalimentarPropuestaService().buscaPropuestasMarcadasPendientes(getRFCSession()));
    }

    public void resetPanelCambioMetodo() throws ValidarRetroalimentarPropuestaException {
        if (getRetroalimentarLstHelper().getListaCambioMetodo() != null) {
            getRetroalimentarLstHelper().setListaCambioMetodo(new ArrayList<FecetPropuesta>());
        }
        getRetroalimentarLstHelper().setListaCambioMetodo(
                getValidarRetroalimentarPropuestaService().buscaPropuestasConCambioDeMetodo(getRFCSession()));
    }

    public void resetPanelRetroalimentar() throws ValidarRetroalimentarPropuestaException {
        if (getRetroalimentarLstHelper().getListaPropuestasXRetroalimentar() != null) {
            getRetroalimentarLstHelper().setListaPropuestasXRetroalimentar(new ArrayList<FecetPropuesta>());
        }
        getRetroalimentarLstHelper().setListaPropuestasXRetroalimentar(
                getValidarRetroalimentarPropuestaService().buscaPropuestasRetroalimentacion(getRFCSession()));
    }

    private void inicializaLstDetalle() throws ValidarRetroalimentarPropuestaException, AgacePropuestasRulesException {
        setEsProcesable(getValidarRetroalimentarPropuestaService()
                .esProcesable(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()));
        getRetroalimentarLstHelper().setListaDocumentoRechazo(new ArrayList<FecetRechazoPropuesta>());
        getRetroalimentarLstHelper().setListaDocumentoPendiente(new ArrayList<FecetPropPendiente>());
        getRetroalimentarLstHelper().setListaImpuesto(new ArrayList<FecetImpuesto>());
        getValidarYRetroalimentarHelper().setPropuestaRechazo(new FecetRechazoPropuesta());
        getValidarYRetroalimentarHelper().setPropuestaPendiente(new FecetPropPendiente());
        getValidarYRetroalimentarHelper().setFile(null);
        tipoPropuesta();
        inicializarCatalogos();
        setFlujoXValidar(true);
    }

    public void tipoPropuesta() {
        BigDecimal tipo = getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdTipoPropuesta();
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            setTipoPropuesta(false);
        } else {
            setTipoPropuesta(true);
        }
    }

    public void tipoPropuestaTraferida() {
        BigDecimal tipo = getValidarYRetroalimentarHelper().getPropuestaTransferida().getIdTipoPropuesta();
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            setTipoPropuesta(false);
        } else {
            setTipoPropuesta(true);
        }
    }

    public void verDetalle() {

        try {
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().add(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
            getPistasAuditoriasPropuestasService().pistaDetallePropuestaPorValidar(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());            
            setEsProcesable(getValidarRetroalimentarPropuestaService()
                    .esProcesableFecha(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada())
                    .intValue() > 0);
            validaMetodoArace(
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFececArace().getIdArace(),
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFeceaMetodo()
                    .getIdMetodo());
            inicializaLstDetalle();
            inicializarPropuestaPendiente();
            visiblePanelHistorico();
            setFlgRetroalimentacion(false);
            setFlgPropuestaPendiente(false);
            setFlgHabilitarRechazo(false);
            setFlgBtnPendiente(true);
            getMsjRechazo(true);
            visiblePanelDetalle();
            logger.debug("Detalle de la propuesta", getValidarYRetroalimentarHelper()
                    .getPropuestasXValidarSeleccionada().getFecetContribuyente().getRfc());
        } catch (AgacePropuestasRulesException ex) {
            addErrorMessage(getMessageResourceString(ERROR_FECHA_PRESENTACION,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro(),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                            .getFechaPresentacion())));
            setEsProcesable(false);
            setFlujoXValidar(true);
            visiblePanelDetalle();
        } catch (ValidarRetroalimentarPropuestaException ex) {
            addErrorMessage(ex.getMessage());
        }
    }

    public void verDetallePendientes() {
        try {
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().add(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());

            validaMetodoArace(
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFececArace().getIdArace(),
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFeceaMetodo()
                    .getIdMetodo());
            inicializaLstDetalle();
            inicializarPropuestaPendiente();
            setFlgRetroalimentacion(false);
            setFlgHabilitarRechazo(false);
            setFlgBtnPendiente(false);
            visiblePanelDetalle();
            getMsjRechazo(false);
            setFlgPropuestaPendiente(true);
            setEsProcesable(
                    getValidarRetroalimentarPropuestaService()
                    .esProcesablePendiente(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada())
                    .intValue() > 0);
            getValidarYRetroalimentarHelper()
                    .setPropuestaPendiente(getValidarRetroalimentarPropuestaService().obtieneArchivoPendientePorValidar(
                                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta()));
            logger.debug("Detalle de la propuesta", getValidarYRetroalimentarHelper()
                    .getPropuestasXValidarSeleccionada().getFecetContribuyente().getRfc());
        } catch (AgacePropuestasRulesException ex) {
            setEsProcesable(false);
            setFlgPropuestaPendiente(true);
            visiblePanelDetalle();
            addErrorMessage(getMessageResourceString(ERROR_FECHA_PRESENTACION,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro(),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                            .getFechaPresentacion())));
        } catch (ValidarRetroalimentarPropuestaException ex) {
            addErrorMessage(ex.getMessage());
        }
    }

    public String verDetalleCambioMetodo()
            throws AgacePropuestasRulesException, IOException, NoExisteEmpleadoException {
        try {
            inicializaLstDetalle();
            inicializarCatalogos();
            getRetroalimentarLstHelper().setListaDocumentoExpediente(new ArrayList<FecetDocExpediente>());
            setEsProcesable(
                    getValidarRetroalimentarPropuestaService()
                    .esProcesableCambioMetodo(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada())
                    .intValue() > 0);

            getRetroalimentarLstHelper()
                    .setListaImpuestos(getValidarRetroalimentarPropuestaService().getImpuestosPropuesta(
                                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta()));
            getSession().setAttribute("isRegistroPropuesta", Boolean.TRUE);
            getSession().setAttribute("cambioMetodo", getValidarYRetroalimentarHelper());
            getSession().setAttribute("empleado",
                    getValidarRetroalimentarPropuestaService().getEmpleadoProgramadorActivo(getRFCSession()));

        } catch (ValidarRetroalimentarPropuestaException ex) {
            addErrorMessage(ex.getMessage());
        }
        return "../../../propuestas/programador/indexCrear.jsf?faces-redirect=true";
    }

    public String verDetalleOrdenCambioMetodo() {
        getSession().setAttribute("idOrdenPropuesta",
                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getAgaceOrden().getIdOrden());
        return "../../../propuestas/programador/validarYRetroalimentar/ordenCambioMetodo.jsf?faces-redirect=true";
    }

    public void verDetalleRetroalimentacion() throws AgacePropuestasRulesException {
        try {
            setEsProcesable(getValidarRetroalimentarPropuestaService()
                    .esProcesableRetro(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada())
                    .intValue() > 0);
            validaMetodoArace(
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFececArace().getIdArace(),
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getFeceaMetodo()
                    .getIdMetodo());
            setHistorial();
            inicializaLstDetalle();
            visiblePanelHistorico();
            inicializarPropuestaPendiente();
            setFlgPropuestaPendiente(false);
            setFlgHabilitarRechazo(false);
            setFlgRetroalimentacion(true);
            setFlujoXValidar(false);
            visiblePanelDetalle();
            iniciaVisibilidadComboMetodo();
            habilitarComboMetodo();
            generarRetroalimentacion();
            logger.debug("Detalle de la propuesta", getValidarYRetroalimentarHelper()
                    .getPropuestasXValidarSeleccionada().getFecetContribuyente().getRfc());
        } catch (AgacePropuestasRulesException ex) {
            addErrorMessage(getMessageResourceString(ERROR_FECHA_PRESENTACION,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro(),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                            .getFechaPresentacion())));
            setEsProcesable(false);
            visiblePanelDetalle();
        } catch (ValidarRetroalimentarPropuestaException ex) {
            addErrorMessage(ex.getMessage());
        }
    }

    private void habilitarComboMetodo() {
        TipoMetodoEnum metodo = TipoMetodoEnum.getById(
                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo().longValue());
        if (TipoMetodoEnum.ORG.equals(metodo) && getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                .getPrioridadSugerida().equals("2")) {
            setFlgComboMetodo(true);
            setFlgTipoRevision(true);
        } else {
            setFlgComboMetodo(false);
            if (TipoMetodoEnum.ORG.equals(metodo)) {
                setFlgTipoRevision(false);
            } else {
                setFlgTipoRevision(true);
                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().setIdRevision(null);
            }
        }
    }

    private void inicializarPropuestaPendiente() {
        getValidarYRetroalimentarHelper().setPropuestaPendiente(new FecetPropPendiente());
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada() != null) {
            getValidarYRetroalimentarHelper().getPropuestaPendiente().setIdPropuesta(
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
            getValidarYRetroalimentarHelper().getPropuestaPendiente().setRfcCreacion(getRFCSession());
        }
    }

    private void inicializarCatalogos() {
        getValidarYRetroalimentarHelper().setPropuestaPendiente(new FecetPropPendiente());
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada() != null) {
            getRetroalimentarLstHelper().setListaSector(getValidarRetroalimentarPropuestaService().getCatalogoSector());
            getRetroalimentarLstHelper()
                    .setListaMetodoRevision(getValidarRetroalimentarPropuestaService().getCatalogoMetodo());
            getRetroalimentarLstHelper()
                    .setListaSubprograma(getValidarRetroalimentarPropuestaService().getCatalogoSubprograma());
            getRetroalimentarLstHelper()
                    .setListaUnidades(getValidarRetroalimentarPropuestaService().getCatalogoUnidad());
            getRetroalimentarLstHelper()
                    .setListaTipoPropuesta(getValidarRetroalimentarPropuestaService().getCatalogoTipoPropuesta());
            getRetroalimentarLstHelper()
                    .setListaTipoRevision(getValidarRetroalimentarPropuestaService().getCatalogoRevision());
            getRetroalimentarLstHelper().setListaCausaProgramacion(
                    getValidarRetroalimentarPropuestaService().getCatalogoCausaProgramacion());
            getRetroalimentarLstHelper()
                    .setListaPrioridad(getValidarRetroalimentarPropuestaService().getCatalogoPrioridad());
            getRetroalimentarLstHelper()
                    .setListaTipoImpuesto(getValidarRetroalimentarPropuestaService().getCatalogoImpuesto());
        }
    }

    public void generarRetroalimentacion() {
        logger.debug("Se genera Retroalimentacion carga listas");
        getValidarYRetroalimentarHelper().setRetroalimentacion(new FecetRetroalimentacion());
        getRetroalimentarLstHelper().setLstDocRetroalimentacion(new ArrayList<DocRetroalimentacionDTO>());
    }

    public String getMsjConfirmacionAprobar() {
        return (getMessageResourceString(LABEL_RETRO_DETALLE,
                ""));

    }

    public String getMsjConfirmacionRechazo() {
        return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazar.msj",
                ""));

    }

    public String getMsjConfirmacionRechazoRetroalimentacion() {
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada() == null) {
            return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazar.retro.msj",
                    Integer.valueOf("0")));
        } else {
            return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazar.retro.msj",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
        }
    }

    public String getMsjConfirmacionPendiente() {
        return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.pendiente.msj",
                ""));
    }

    public String getMsjConfirmacionRetroalimentacion() {
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada() == null) {
            return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.retroalimentacion.msj",
                    Integer.valueOf("0")));
        } else {
            return (getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.retroalimentacion.msj",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
        }
    }

    public void getMsjRechazo(boolean flujo) {

        if (flujo) {
            getValidarYRetroalimentarHelper().setMsjRechazo(
                    getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazarValida.heder"));
            getValidarYRetroalimentarHelper().setMsjDocumento(
                    getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazarDoc.doc"));

        } else {
            getValidarYRetroalimentarHelper().setMsjRechazo(
                    getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazarPendiente.herder"));
            getValidarYRetroalimentarHelper().setMsjDocumento(
                    getMessageResourceString("label.prpuestas.validar.retro.detalle.dialog.rechazarDocPendiente.doc"));
        }
    }

    public UnidadAdministrativaEnum[] getUnidadValues() {
        return UnidadAdministrativaEnum.values();
    }

    public void validaMetodoArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        if (abreviaturaMetodo != null && sedeUnidad != null && !abreviaturaMetodo.equals(new BigDecimal(-1))) {
            boolean validaSedeUnidad = (sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE));
            boolean validaAbreviaturaMetodo = (abreviaturaMetodo.equals(EHO) || abreviaturaMetodo.equals(REE)
                    || abreviaturaMetodo.equals(UCA));
            if ((sedeUnidad.equals(ACAOCE) || sedeUnidad.equals(ACOECE)) && abreviaturaMetodo.equals(ORG)) {
                if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida()
                        .equals("1")) {
                    setVisibleFechaPre(false);
                    setVisibleFechaInf(true);
                    getValidarYRetroalimentarHelper()
                            .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
                } else if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida()
                        .equals("2")
                        || getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida()
                        .equals("3")) {
                    setVisibleFechaPre(true);
                    setVisibleFechaInf(false);
                    getValidarYRetroalimentarHelper()
                            .setPresentacionComite(getMessageResourceString("lbl.propuestas.fechaPresentacionComite"));
                }
            } else if (validaSedeUnidad && validaAbreviaturaMetodo) {
                setVisibleFechaPre(false);
                setVisibleFechaInf(true);
                getValidarYRetroalimentarHelper()
                        .setInformacionComite(getMessageResourceString("lbl.propuestas.fechaInformacionComite"));
            } else {
                validaUnidadArace(sedeUnidad, abreviaturaMetodo);
            }
        }
    }

    public void validaUnidadArace(final BigDecimal sedeUnidad, final BigDecimal abreviaturaMetodo) {
        boolean validaUnidad = sedeUnidad.equals(ARACE_NORESTE) || sedeUnidad.equals(ARACE_OCCIDENTE)
                || sedeUnidad.equals(ARACE_CENTRO) || sedeUnidad.equals(ARACE_SUR);
        if (sedeUnidad.equals(ARACE_PACIFICO_NORTE) || sedeUnidad.equals(ARACE_NORTE_CENTRO) || validaUnidad) {
            if (abreviaturaMetodo.equals(ORG) && !getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                    .getPrioridadSugerida().equals("1")) {
                setVisibleFechaPre(true);
                setVisibleFechaInf(false);
                getValidarYRetroalimentarHelper().setPresentacionComite(
                        getMessageResourceString("lbl.propuestas.fechaPresentacionComiteRegional"));
            } else {
                setVisibleFechaPre(false);
                setVisibleFechaInf(true);
                getValidarYRetroalimentarHelper().setInformacionComite(
                        getMessageResourceString("lbl.propuestas.fechaInformacionComiteRegional"));
            }
        }
    }

    public void setHistorial() {
        FecetPropuesta historico = (FecetPropuesta) SerializationUtils
                .clone(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
        getValidarYRetroalimentarHelper().setRetroalimentarHistoricoSeleccionada(historico);
    }
}
