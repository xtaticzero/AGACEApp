package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga.managedbean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.ToggleEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SeguimientoOrdenesTipoGuardadoEnum;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga.DoctoOrdenLimpiarDelegateSubMB;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "documentacionOrdenMB")
@SessionScoped
public class DocumentacionOrdenMB extends DoctoOrdenLimpiarDelegateSubMB {

    private static final long serialVersionUID = -4913045704135617712L;
    private static final String FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS = "formDocumentacionOrden:msgExitoGuardarDocsOficios";
    private static final String ERROR_APROBADO_ORDEN_SIN_DESCRIPCION = "error.aprobado.orden.sin.descripcion";
    private static final String FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR = "formDocumentacionOrden:pnlSolicitudContr";
    private static final String FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_APROBADA = "formDocumentacionOrden:messagesSolicitudContrAprobada";
    private static final String MSG_SOLICTUD_CONTR_RECHAZADA_ERROR = "msgSolictudContrRechazadaError";
    private static final String PF_CONFIRMAR_RECHAZAR_SOLICITUD_CONTR_HIDE = "PF('confirmarRechazarSolicitudContr').hide();";
    private static final String FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_RECHAZADA = "formDocumentacionOrden:messagesSolicitudContrRechazada";
    private static final String MSG_SOLICITUD_CONTR_APROBADA_ERROR = "msgSolicitudContrAprobadaError";

    public void guardar2aCartaInvitacion() {
        try {
            getSeguimientoOrdenesService().guardar2aCartaInvitacion(getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOf2aCartaInv(),
                    getLstOficioAnexoHelper().getListaAnexos2aCartaInv());

            actualizarGuardarPapelTrabjo("flt2daUnicaCartaInvitacion");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage());
        }
    }

    public void guardar2aCartaInvitacionMasiva() {
        try {
            getSeguimientoOrdenesService().guardar2aCartaInvitacionMasiva(getDtoHelper().getOrdenSeleccionada(),
                    getLstOficioHelper().getListaOf2aCartaInvitacionMasiva(), getLstOficioAnexoHelper().getListaAnexos2aCartaInvitacionMasiva());

            actualizarGuardarPapelTrabjo("flt2daCartaInvitacionMasiva");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarConclusionUCAMCA() {
        try {
            getSeguimientoOrdenesService().guardarConclusionUCAMCA(getDtoHelper().getOrdenSeleccionada(), getLstOficioHelper().getListaOfConclusionUCAMCA(),
                    getLstOficioAnexoHelper().getListaAnexosConclusionUCAMCA());

            actualizarGuardarPapelTrabjo("fltConclusionMCAUCA");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);

        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarSegundoRequerimiento() {
        try {

            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfSegundoRequerimiento());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosSegundoRequerimiento());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfMulta());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosMulta());
            reglasNegocioOrdenesBO.setListaOfDependiente2(getLstOficioHelper().getListaOfBajaPadron());
            reglasNegocioOrdenesBO.setListaAnexosDependiente2(getLstOficioAnexoHelper().getListaAnexosBajaPadron());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.SEGUNDO_REQUERIMIENTO, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltSegundoRequerimeiento");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarResolucionDefinitiva() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfResolucionDefinitiva());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosResolucionDefinitiva());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.RESOLUCION_DEFINITIVA, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltResolucionDefinitiva");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarPruebasPericiales() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfPruebasPericiales());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosPruebasPericiales());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfAvisoContribuyente());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.PRUEBAS_PERICIALES, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltPruebasPericiales");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarPruebasDesahogo() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfPruebasDesahogo());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosPruebasDesahogo());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfAvisoContribuyente());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.PRUEBAS_DESAHOGO, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltPruebasDesahogo");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarConclusionRevision() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfConclusionRevision());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosConclusionRevision());
            reglasNegocioOrdenesBO.setListaOfDependiente3(getLstOficioHelper().getListaOfCancelacionOrden());
            reglasNegocioOrdenesBO.setListaAnexosDependiente3(getLstOficioAnexoHelper().getListaAnexosCancelacionOrden());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfMulta());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosMulta());
            reglasNegocioOrdenesBO.setListaOfDependiente2(getLstOficioHelper().getListaOfBajaPadron());
            reglasNegocioOrdenesBO.setListaAnexosDependiente2(getLstOficioAnexoHelper().getListaAnexosBajaPadron());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.CONCLUSION_REVISION, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltConclusionRevision");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarSinObservaciones() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfSinObservaciones());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosSinObservaciones());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.SIN_OBSERVACIONES, reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltConclusionSinObservaciones");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarMedidasApremio() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfMedidasApremio());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosMedidasApremio());

            getSeguimientoOrdenesService().guardarMedidaApremio(reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltMedidaApremio");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (Exception e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarConObservaciones() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfConObservaciones());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosConObservaciones());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.CON_OBSERVACIONES, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltObservacionesRevisionEscritorio");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarRequerimientoReincidencia() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfRequerimientoReincidencia());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosRequerimientoReincidencia());
            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfMulta());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosMulta());
            reglasNegocioOrdenesBO.setListaOfDependiente2(getLstOficioHelper().getListaOfBajaPadron());
            reglasNegocioOrdenesBO.setListaAnexosDependiente2(getLstOficioAnexoHelper().getListaAnexosBajaPadron());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.REQUERIMIENTO_REINCIDENCIA, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltRequerimientoReincidencia");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarCambioMetodoUCAMCA() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfCambioMetodoUCAMCA());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosCambioMetodoUCAMCA());

            getSeguimientoOrdenesService().guardarCambioMetodoUCAMCA(reglasNegocioOrdenesBO, getDtoHelper().getFecetCambioMetodo());

            actualizarGuardarPapelTrabjo("fltCambioMetodoUCAMCA");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarCompInternacional() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfCompInternacional());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosCompInternacional());

            reglasNegocioOrdenesBO.setListaOfDependiente1(getLstOficioHelper().getListaOfAvisoContribuyente());
            reglasNegocioOrdenesBO.setListaAnexosDependiente1(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyente());

            reglasNegocioOrdenesBO.setAutoridadCompulsada(getAttributeHelper().getAutoridadCompulsada());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.COMP_INTERNACIONAL, reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltCompulsaInternacional");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarMultaOrden() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfMultaOrden());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosMultaOrden());
            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.MULTA, reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltMulta");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void guardarAvisoContribuyenteOrden() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfAvisoContribuyenteOrden());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosAvisoContribuyenteOrden());
            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.AVISO_CONTRIBUYENTE, reglasNegocioOrdenesBO);
            actualizarGuardarPapelTrabjo("fltAvisoContribuyente");

            this.getAttributeHelper().setRecargarInformacion(true);
            init();
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MSG_EXITO_GUARDAR_DOCS_OFICIOS);

        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public String regresaPaginaPrincipal() {
        getAttributeHelper().setProrrogasVisible(false);
        return "indexDocumentacion";
    }

    public void integrarExpediente() {
        getDtoHelper().getOrdenSeleccionada().setFechaIntegraExp(getSeguimientoOrdenesService().integrarExpediente(getDtoHelper().getOrdenSeleccionada()));
        getAttributeHelper().setRecargarInformacion(true);
        init();
        addMessage(null, getMessageResourceString("exito.guardado"), "");
        RequestContext.getCurrentInstance().update("formDocumentacionOrden:msgExitoGuardarReactIntegrar");
    }

    public void reactivarPlazo() {
        if (!getAttributeHelper().getIdOficioReactivacion().equals(Constantes.BIG_DECIMAL_CERO)) {
            getSeguimientoOrdenesService().reactivaPlazoOficio(getDtoHelper().getOrdenSeleccionada().getId(), getAttributeHelper().getIdOficioReactivacion());
            visualizarReactivarPlazoREE();
            addMessage("msgExitoGuardarReactIntegrar", getMessageResourceString("exito.guardado"), "");
        } else {
            addErrorMessage("msgExitoGuardarReactIntegrar", "Favor de seleccionar un oficio", "");
        }
    }

    public void reactivarPlazoAcuerdoConclusivo() {
        getSeguimientoOrdenesService().reactivarPlazoAcuerdoConclusivo(getDtoHelper().getOrdenSeleccionada());
        addMessage(null, getMessageResourceString("exito.guardado"), "");
    }

    public void guardarFlujoSolicitudContr() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            guardarFlujoProrrogaAprobada();
        } else {
            guardarFlujoPruebaPericialAprobada();
        }
    }

    public void guardarFlujoProrrogaAprobada() {
        try {
            if (getLstHelper().getListaSolicitudContribuyenteResolucionVO() != null && !getLstHelper().getListaSolicitudContribuyenteResolucionVO().isEmpty()) {
                if (getAttributeHelper().getJustificacion() != null && !getAttributeHelper().getJustificacion().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoProrrogaOrdenAprobadaAuditor(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden(), getAttributeHelper().getJustificacion(),
                            getDtoHelper().llenaAnexoSolicitudContProrroga(getLstHelper().getListaSolicitudContribuyenteAnexoVO()));
                    getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden()
                            .setIdAuditor(getDtoHelper().getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucion(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden(),
                            getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteResolucionVO()));
                    getAuditoriaService().aprobarProrrogaOrdenAuditor(getDtoHelper().getOrdenSeleccionada());
                    getSeguimientoOrdenesService().enviarCorreoProrrogaAprobada(getDtoHelper().getOrdenSeleccionada(),
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    getAttributeHelper().setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarAutorizarSolicitudContrAuditor').hide();");
                } else {
                    addErrorMessage(MSG_SOLICITUD_CONTR_APROBADA_ERROR, FacesUtil.getMessageResourceString(ERROR_APROBADO_ORDEN_SIN_DESCRIPCION));
                }

            } else {
                addErrorMessage(MSG_SOLICITUD_CONTR_APROBADA_ERROR, getMessageResourceString(MENSAJE_ERROR_CARGA_RESOLUCION), "");
            }
            RequestContext.getCurrentInstance().execute(CONFIRMAR_APROBAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_APROBADA);
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgSolicitudAprobadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute(CONFIRMAR_APROBAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_APROBADA);
        }
    }

    public void guardarFlujoPruebaPericialAprobada() {
        try {
            if (getLstHelper().getListaSolicitudContribuyenteResolucionVO() != null && !getLstHelper().getListaSolicitudContribuyenteResolucionVO().isEmpty()) {
                if (getAttributeHelper().getJustificacion() != null && !getAttributeHelper().getJustificacion().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoPruebasPericialesAprobadaAuditor(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales(), getAttributeHelper().getJustificacion(),
                            getDtoHelper().llenaAnexoSolicitudContPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteAnexoVO()));
                    getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales()
                            .setIdAuditor(getDtoHelper().getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucionPruebasPericiales(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales(),
                            getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteResolucionVO()));
                    getSeguimientoOrdenesService().enviarCorreoPruebaPericialAprobada(getDtoHelper().getOrdenSeleccionada(),
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    getAttributeHelper().setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarAutorizarSolicitudContrAuditor').hide();");
                } else {
                    addErrorMessage(MSG_SOLICITUD_CONTR_APROBADA_ERROR, FacesUtil.getMessageResourceString(ERROR_APROBADO_ORDEN_SIN_DESCRIPCION));
                }

            } else {
                addErrorMessage(MSG_SOLICITUD_CONTR_APROBADA_ERROR, getMessageResourceString(MENSAJE_ERROR_CARGA_RESOLUCION), "");
            }
            RequestContext.getCurrentInstance().execute(CONFIRMAR_APROBAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_APROBADA);
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgSolicitudAprobadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute(CONFIRMAR_APROBAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_APROBADA);
        }
    }

    public void guardarFlujoSolicitudContrRechazada() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            guardarFlujoProrrogaRechazada();
        } else {
            guardarFlujoPruebaPericialRechazada();
        }
    }

    public void guardarFlujoProrrogaRechazada() {
        try {
            if ((getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO() != null
                    && !getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO().isEmpty())) {
                if (getAttributeHelper().getJustificacionRechazo() != null && !getAttributeHelper().getJustificacionRechazo().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoProrrogaOrdenRechazadaAuditor(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden(), getAttributeHelper().getJustificacionRechazo(),
                            getDtoHelper().llenaAnexoSolicitudContProrroga(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()));
                    getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden()
                            .setIdAuditor(getDtoHelper().getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucion(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden(),
                            getLstHelper().llenaAnexosProrrogaOrden(getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()));
                    getAuditoriaService().noAprobarProrrogaOrdenAuditor(getDtoHelper().getOrdenSeleccionada());
                    getSeguimientoOrdenesService().enviarCorreoProrrogaRechazada(getDtoHelper().getOrdenSeleccionada(),
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    getAttributeHelper().setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarRechazarSolicitudContrAuditor').hide();");
                } else {
                    addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, FacesUtil.getMessageResourceString("error.rechazo.orden.sin.descripcion"));
                }

            } else {
                addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, getMessageResourceString(MENSAJE_ERROR_CARGA_RECHAZO), "");
            }
            RequestContext.getCurrentInstance().execute(PF_CONFIRMAR_RECHAZAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_RECHAZADA);
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgSolicitudRechazadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute(PF_CONFIRMAR_RECHAZAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_RECHAZADA);
        }
    }

    public void guardarFlujoPruebaPericialRechazada() {
        try {
            if (getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO() != null
                    && !getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO().isEmpty()) {
                if (getAttributeHelper().getJustificacionRechazo() != null && !getAttributeHelper().getJustificacionRechazo().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoPruebasPericialesRechazadaAuditor(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales(), getAttributeHelper().getJustificacionRechazo(),
                            getDtoHelper().llenaAnexoSolicitudContPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteAnexoRechazoVO()));
                    getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales()
                            .setIdAuditor(getDtoHelper().getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucionPruebasPericiales(
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales(),
                            getLstHelper().llenaAnexoPruebasPericiales(getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO()));
                    getSeguimientoOrdenesService().enviarCorreoPruebaPericialRechazada(getDtoHelper().getOrdenSeleccionada(),
                            getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    getAttributeHelper().setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarRechazarSolicitudContrAuditor').hide();");
                } else {
                    addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, FacesUtil.getMessageResourceString("error.rechazo.orden.sin.descripcion"));
                }
            } else {
                addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, getMessageResourceString(MENSAJE_ERROR_CARGA_RECHAZO), "");
            }
            RequestContext.getCurrentInstance().execute(PF_CONFIRMAR_RECHAZAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_RECHAZADA);
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgSolicitudRechazadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute(PF_CONFIRMAR_RECHAZAR_SOLICITUD_CONTR_HIDE);
            RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_MESSAGES_SOLICITUD_CONTR_RECHAZADA);
        }
    }

    public void getAnexosSolicitudContribuyente() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            getAnexosProrrogaOrden();
        } else {
            getAnexosPruebasPericiales();
        }
    }

    public void getAnexosProrrogaOrden() {
        getLstHelper().setListaAnexosSolicitudContribuyenteHistorico(
                getLstHelper().llenaAnexoSolicitudContribuyenteProrroga(getSeguimientoOrdenesService().getAnexosProrrogaOrdenHistorico(
                                getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden())));
    }

    public void getAnexosPruebasPericiales() {
        getLstHelper().setListaAnexosSolicitudContribuyenteHistorico(getLstHelper()
                .llenaAnexoSolicitudContribuyentePruebasPericiales(getSeguimientoOrdenesService().getAnexosPruebasPericialesHistorico(getDtoHelper()
                                .getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getFlujoPruebasPericiales().getIdFlujoPruebasPericiales())));
    }

    public void guardarAvisoCircunstancial() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getDtoHelper().getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getLstOficioHelper().getListaOfAvisoCircunstancial());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getLstOficioAnexoHelper().getListaAnexosAvisoCircunstancial());

            getSeguimientoOrdenesService().guardar(SeguimientoOrdenesTipoGuardadoEnum.AVISO_CIRCUNSTANCIAL, reglasNegocioOrdenesBO);

            actualizarGuardarPapelTrabjo("fltAvisoCircunstancial");
            addMessage(MSGEXITODOCS, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
            this.getAttributeHelper().setRecargarInformacion(true);
            init();
        } catch (NegocioException e) {
            addErrorMessage(MSGEXITODOCS, e.getMessage(), "");
        }
    }

    public void cargaDocumentosOrdenOficio() {
        getAttributeHelper().setExpediente(getSeguimientoOrdenesService().obtenerExpedienteOrden(getDtoHelper().getOrdenSeleccionDescarga()));
        getLstOficioHelper()
                .setListaOficiosAdmin(getSeguimientoOrdenesService().getOficiosAdministrables(getDtoHelper().getOrdenSeleccionDescarga().getIdOrden()));
        RequestContext.getCurrentInstance().execute("PF('docDialog').show();");
    }

    public void guardarPapeleTrabajoOrden() {
        String carpetaPapelTrabajo = Constantes.CARPETAPAPELTRABAJO;
        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));

        try {
            for (DocumentoOrdenModel papel : getLstHelper().getListaPapelesTrabajo()) {
                if (papel.isIsEliminar()) {
                    papel.getPapelesTrabajo()
                            .setRutaArchivo(RutaArchivosUtilOrdenes.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PAPELES_TRABAJO_ORDENES,
                                            getDtoHelper().getOrdenSeleccionada().getNumeroOrden(), carpetaPapelTrabajo, carpetaUnica)
                                    + papel.getPapelesTrabajo().getNombreArchivo());

                    getConsultarPapelesTrabajoService().insertaPapelesTrabajo(papel.getPapelesTrabajo());
                    InputStream myInputStream = new ByteArrayInputStream(
                            getArchivoTempService().consultaArchivoTemp(papel.getIdDocumentoTemporal(), getRFCSession()));
                    papel.setInput(myInputStream);
                    getConsultarPapelesTrabajoService().guardarPapelTrabajo(papel);

                }
            }
            addMessage("msgPapelesTrabajo", "Se han guardado exitosamente los papeles de trabajo en el sistema.", "");
            getAttributeHelper().setNumeroDocumentoPapelTrabajo(0);
            cargarPapelesTrabajoOrden();
        } catch (IOException e) {
            addMessage("msgPapelesTrabajo", e.getMessage(), "");
        }
    }

    public void guardarPapeleTrabajoOficio() {
        int numeroDocumentosGuardados = 0;
        String carpetaPapelTrabajo = Constantes.CARPETAPAPELTRABAJO;
        String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
        try {
            for (DocumentoOrdenModel papel : getLstHelper().getListaPapelesTrabajoOficio()) {
                if (papel.isIsEliminar()) {

                    StringBuilder carpetaOfico = new StringBuilder();
                    carpetaOfico.append("Oficio_").append(getAttributeHelper().getIdTipoOficio());

                    papel.getPapelesTrabajo()
                            .setRutaArchivo(RutaArchivosUtilOrdenes.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PAPELES_TRABAJO_ORDENES,
                                            getDtoHelper().getOrdenSeleccionada().getNumeroOrden(), carpetaPapelTrabajo, carpetaOfico.toString(), carpetaUnica)
                                    + papel.getPapelesTrabajo().getNombreArchivo());

                    getConsultarPapelesTrabajoService().insertaPapelesTrabajo(papel.getPapelesTrabajo());
                    InputStream myInputStream = new ByteArrayInputStream(
                            getArchivoTempService().consultaArchivoTemp(papel.getIdDocumentoTemporal(), getRFCSession()));
                    papel.setInput(myInputStream);
                    getConsultarPapelesTrabajoService().guardarPapelTrabajo(papel);
                    numeroDocumentosGuardados++;
                }
            }
        } catch (IOException e) {
            addMessage("msgPapelesTrabajoOficio", e.getMessage(), "");
        }

        getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);
        cargarPapelesTrabajoOficio(getAttributeHelper().getIdTipoOficio());

        if (numeroDocumentosGuardados != 0) {
            addMessage("msgPapelesTrabajoOficio", "Se han guardado exitosamente los papeles de trabajo en el sistema.", "");
            RequestContext context = RequestContext.getCurrentInstance();
            StringBuilder update = new StringBuilder("formDocumentacionOrden:panelTabsDocumentos:");
            update.append(getAttributeHelper().getFieldsetActivoPapelTrabajo());
            context.update(update.toString());
        }
    }

    public void handleTogglePapelTrabajoOficio(ToggleEvent event) {
        UIComponent tog = event.getComponent();
        BigDecimal idTipoOficio = getAttributeHelper().getTipoOficioEnum().get(tog.getId());

        getAttributeHelper().setAdjuntarArchivo(false);
        if (idTipoOficio.equals(new BigDecimal(-1))) {
            idTipoOficio = new BigDecimal(getAttributeHelper().getTipoMedidaApremio());
            if (idTipoOficio.equals(BigDecimal.ZERO)) {
                getAttributeHelper().setAdjuntarArchivo(true);
            }
            getAttributeHelper().setFieldSetMedidasApremio(true);
        }

        if (event.getVisibility().toString().equals("VISIBLE")) {
            getAttributeHelper().setIdTipoOficio(idTipoOficio);
            cargarPapelesTrabajoOficio(idTipoOficio);
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);
            getAttributeHelper().setFieldsetActivoPapelTrabajo(tog.getId());

        } else {
            if (tog.getId().equals("fltMedidaApremio")) {
                getAttributeHelper().setFieldSetMedidasApremio(false);
            }
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);
            if (getAttributeHelper().getFieldsetActivoPapelTrabajo().equals(tog.getId())) {
                getAttributeHelper().setFieldsetActivoPapelTrabajo("");
            }

        }
    }

    private void actualizarGuardarPapelTrabjo(String idFieldset) {
        if (idFieldset.equals("fltMedidaApremio")) {
            if (getAttributeHelper().getFieldsetActivoPapelTrabajo().equals(idFieldset)) {
                if (getAttributeHelper().getIdTipoOficio().equals(new BigDecimal(getAttributeHelper().getTipoMedidaApremio()))) {
                    guardarPapeleTrabajoOficio();
                } else {
                    cargarPapelesTrabajoOficio(new BigDecimal(getAttributeHelper().getTipoMedidaApremio()));
                }
            } else {
                cargarPapelesTrabajoOficio(new BigDecimal(getAttributeHelper().getTipoMedidaApremio()));
            }
        } else {
            if (getAttributeHelper().getFieldsetActivoPapelTrabajo().equals(idFieldset)) {
                guardarPapeleTrabajoOficio();
            } else {
                cargarPapelesTrabajoOficio(getAttributeHelper().getTipoOficioEnum().get(idFieldset));
            }
        }

        for (DocumentoOrdenModel papel : getLstHelper().getListaPapelesTrabajoOficio()) {
            papel.getPapelesTrabajo().setIdOficio(getSeguimientoOrdenesService().obtenerIdOficioPadre());
            getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papel.getPapelesTrabajo());
        }
    }

    public void actualizarFieldset(TabChangeEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();

        if (getAttributeHelper().getFieldsetActivoPapelTrabajo().equals("fltCompulsaInternacional")) {
            getAttributeHelper().setFieldsetActivoPapelTrabajo("");
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);
            getAttributeHelper().setIdTipoOficio(BigDecimal.ZERO);
        }

        if (!getAttributeHelper().getFieldsetActivoPapelTrabajo().equals("")) {
            StringBuilder idFielset = new StringBuilder();
            idFielset.append("PF('").append(getAttributeHelper().getFieldsetActivoPapelTrabajo()).append("').toggle();");
            context.execute(idFielset.toString());
            getAttributeHelper().setFieldsetActivoPapelTrabajo("");
            getAttributeHelper().setNumeroDocumentoPapelTrabajoOficio(0);
            getAttributeHelper().setIdTipoOficio(BigDecimal.ZERO);
        }
    }

    public void cerrarFieldsetMedidaApremio() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (getAttributeHelper().isFieldSetMedidasApremio()) {
            context.execute("PF('fltMedidaApremio').toggle();");
            getAttributeHelper().setFieldSetMedidasApremio(false);
        }
    }

    public String cargaDocumentosAgenteAduanal() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("orden", getDtoHelper().getOrdenSeleccionada());
        map.put("agenteAduanal", getDtoHelper().getColaboradoresDTO().getAgenteAduanal());
        map.put("auditor", true);
        getSession().setAttribute("mapOrdenAA", map);
        return "cargaDocsAgenteAduanal.jsf?faces-redirect=true";
    }

    public void cargaAsignarConsulta() {
        cargarJefeDepartamento();
        cargarEnlace();
        List<AsociadoFuncionarioDTO> jefeDepto = obtenerJefeDeptoAsignado(getDtoHelper().getOrdenSeleccionada());
        List<AsociadoFuncionarioDTO> enlace = obtenerEnlaceAsignado(getDtoHelper().getOrdenSeleccionada());
        boolean enlaceActivo = false;
        boolean jefeDeptoActivo = false;
        boolean tieneJefeAsignado = false;
        boolean tieneEnlaceAsignado = false;
        String nombreJefeDepartamento = null;
        String nombreEnlace = null;

        if (jefeDepto != null && !jefeDepto.isEmpty()) {
            tieneJefeAsignado = true;

            if (esEmpleadoActivo(getLstHelper().getListaJefeDepartamento(), jefeDepto.get(0).getIdEmpleado())) {
                getAttributeHelper().setIdEmpleadoJefeDepartamento(jefeDepto.get(0).getIdEmpleado());
                getAttributeHelper().setIdEmpleadoJefeDepartamentoAnterior(getAttributeHelper().getIdEmpleadoJefeDepartamento());
                jefeDeptoActivo = true;
                try {

                    for (EmpleadoDTO empleadoJefe : getLstHelper().getListaJefeDepartamento()) {

                        if (empleadoJefe.getIdEmpleado().equals(jefeDepto.get(0).getIdEmpleado())) {
                            EmpleadoDTO jefeAsignado = new EmpleadoDTO();
                            jefeAsignado.setNombre(empleadoJefe.getNombreCompleto());
                            nombreJefeDepartamento = jefeAsignado.getNombre();
                        }

                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                    nombreJefeDepartamento = "";
                }
            }

        }

        if (enlace != null && !enlace.isEmpty()) {
            tieneEnlaceAsignado = true;
            if (esEmpleadoActivo(getLstHelper().getListaEnlace(), enlace.get(0).getIdEmpleado())) {
                getAttributeHelper().setIdEmpleadoEnlace(enlace.get(0).getIdEmpleado());
                getAttributeHelper().setIdEmpleadoEnlaceAnterior(getAttributeHelper().getIdEmpleadoEnlace());
                enlaceActivo = true;
                try {

                    for (EmpleadoDTO empleadoEnlace : getLstHelper().getListaEnlace()) {

                        if (empleadoEnlace.getIdEmpleado().equals(enlace.get(0).getIdEmpleado())) {
                            EmpleadoDTO enlaceAsignado = new EmpleadoDTO();
                            enlaceAsignado.setNombre(empleadoEnlace.getNombreCompleto());
                            nombreEnlace = enlaceAsignado.getNombre();
                        }

                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    nombreEnlace = "";
                }
            }
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (validaConstruccionMensaje(tieneJefeAsignado, tieneEnlaceAsignado, jefeDeptoActivo, enlaceActivo)) {
            String mensajeAsignacionConsulta = construyeMensaje(jefeDeptoActivo, enlaceActivo, nombreJefeDepartamento, nombreEnlace);
            addMessage("msgAvisoSubordinadosAsoc", mensajeAsignacionConsulta, "");
            requestContext.update("formDocumentacionOrden:msgAvisoSubordinadosAsoc");
        }

        // getCpaBooleanHelper().setMuestraAsignarConsulta(true)
        requestContext.execute("PF('dlgAsignarConsulta').show();");

    }

    public void asignarConsulta() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        boolean realizoAsignacion = false;

        if (!getAttributeHelper().getIdEmpleadoJefeDepartamento().equals(getAttributeHelper().getIdEmpleadoJefeDepartamentoAnterior())) {
            insertarJefeDepartamental(getLstHelper().getListaJefeDepartamento());
            realizoAsignacion = true;

        }

        if (!getAttributeHelper().getIdEmpleadoEnlace().equals(getAttributeHelper().getIdEmpleadoEnlaceAnterior())) {
            insertarEnlace(getLstHelper().getListaEnlace());
            realizoAsignacion = true;
        }

        if (realizoAsignacion) {
            getAuditoriaService().asignarConsulta(getDtoHelper().getOrdenSeleccionada());
            addMessage(MSGEXITOSUBORDINADOS, "Se ha asignado la consulta de documentaci\u00f3n correctamente", "");
            requestContext.update("formDocumentacionOrden:msgExitoSubordinadosAsoc");
            cancelaAsignarConsulta();
            requestContext.execute("PF('dlgAsignarConsulta').hide();");
        }

    }

    public void cancelaAsignarConsulta() {
        getLstHelper().setListaJefeDepartamento(new ArrayList<EmpleadoDTO>());
        getLstHelper().setListaEnlace(new ArrayList<EmpleadoDTO>());
        getAttributeHelper().setIdEmpleadoJefeDepartamento(new BigDecimal(-1));
        getAttributeHelper().setIdEmpleadoJefeDepartamentoAnterior(new BigDecimal(-1));
        getAttributeHelper().setIdEmpleadoEnlace(new BigDecimal(-1));
        getAttributeHelper().setIdEmpleadoEnlaceAnterior(new BigDecimal(-1));
    }

    public void cargarJefeDepartamento() {

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = getEmpleadoLogueado().getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.AUDITOR);
        List<EmpleadoDTO> lstJefesDpto = sub.get(TipoEmpleadoEnum.JEFE_DE_DEPARTAMENTO);

        for (EmpleadoDTO empleadoJefe : lstJefesDpto) {
            empleadoJefe.setNombre(empleadoJefe.getNombreCompleto());
        }

        getLstHelper().setListaJefeDepartamento(lstJefesDpto);
    }

    public void cargarEnlace() {
        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = getEmpleadoLogueado().getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.AUDITOR);
        List<EmpleadoDTO> lstEnlaces = sub.get(TipoEmpleadoEnum.ENLACE);

        if (lstEnlaces != null && !lstEnlaces.isEmpty()) {
            for (EmpleadoDTO empleadoEnlace : lstEnlaces) {
                empleadoEnlace.setNombre(empleadoEnlace.getNombreCompleto());
            }
        }
        getLstHelper().setListaEnlace(lstEnlaces);
    }

    public List<AsociadoFuncionarioDTO> obtenerJefeDeptoAsignado(AgaceOrden orden) {
        AsociadoFuncionarioDTO jefeDepartamento = new AsociadoFuncionarioDTO();
        jefeDepartamento.setIdTipoEmpleado(Constantes.USUARIO_JEFE_DEPARTAMENTO);
        jefeDepartamento.setIdPropuesta(orden.getIdPropuesta());
        jefeDepartamento.setIdOrden(orden.getIdOrden());
        jefeDepartamento.setBlnActivo(Constantes.ENTERO_UNO);
        return getSeguimientoOrdenesService().buscarAsociadoFuncionarioActivoByOrden(jefeDepartamento);
    }

    public List<AsociadoFuncionarioDTO> obtenerEnlaceAsignado(AgaceOrden orden) {
        AsociadoFuncionarioDTO enlace = new AsociadoFuncionarioDTO();
        enlace.setIdTipoEmpleado(Constantes.USUARIO_ENLACE);
        enlace.setIdPropuesta(orden.getIdPropuesta());
        enlace.setIdOrden(orden.getIdOrden());
        enlace.setBlnActivo(Constantes.ENTERO_UNO);
        return getSeguimientoOrdenesService().buscarAsociadoFuncionarioActivoByOrden(enlace);
    }

    public boolean esEmpleadoActivo(List<EmpleadoDTO> empleado, BigDecimal idEmpleado) {
        for (EmpleadoDTO emp : empleado) {
            if (emp.getIdEmpleado().equals(idEmpleado)) {
                return true;
            }
        }
        return false;
    }

    public boolean validaConstruccionMensaje(boolean tieneJefe, boolean tieneEnlace, boolean jefeActivo, boolean enlaceActivo) {
        boolean isConstruccionValida = false;

        if (tieneJefe && !jefeActivo) {
            isConstruccionValida = true;
        }

        if (tieneEnlace && !enlaceActivo) {
            isConstruccionValida = true;
        }

        return isConstruccionValida;
    }

    public String construyeMensaje(boolean jefeDeptoActivo, boolean enlaceActivo, String nombreJefeDepto, String nombreEnlace) {

        StringBuilder mensajeEmpleadoBaja = new StringBuilder();
        mensajeEmpleadoBaja.append("");

        if (!nombreJefeDepto.isEmpty() || !nombreEnlace.isEmpty()) {
            if (!jefeDeptoActivo && !enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.JEFE_DEPARTAMENTO);
                mensajeEmpleadoBaja.append(nombreJefeDepto);
                mensajeEmpleadoBaja.append(Constantes.CONJUNCION_REASIGNAR_CONSULTA);
                mensajeEmpleadoBaja.append(Constantes.ENLACE);
                mensajeEmpleadoBaja.append(nombreEnlace);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }

            if (!jefeDeptoActivo && enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.JEFE_DEPARTAMENTO);
                mensajeEmpleadoBaja.append(nombreJefeDepto);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }

            if (jefeDeptoActivo && !enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.ENLACE);
                mensajeEmpleadoBaja.append(nombreEnlace);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }
        }

        return mensajeEmpleadoBaja.toString();
    }

    private void insertarJefeDepartamental(List<EmpleadoDTO> listaFuncionarios) {
        EmpleadoDTO empleado = new EmpleadoDTO();
        AsociadoFuncionarioDTO asociadoFuncionario;

        BigDecimal id;
        if (getAttributeHelper().getIdEmpleadoJefeDepartamento().equals(new BigDecimal(-1))) {
            id = getAttributeHelper().getIdEmpleadoJefeDepartamentoAnterior();
        } else {
            id = getAttributeHelper().getIdEmpleadoJefeDepartamento();
        }

        if (listaFuncionarios != null && !listaFuncionarios.isEmpty()) {
            for (EmpleadoDTO funcionario : listaFuncionarios) {
                if (funcionario.getIdEmpleado().equals(id)) {
                    empleado = funcionario;
                    break;
                }
            }
            asociadoFuncionario = llenarAsociadoFuncionario(empleado);
            List<AsociadoFuncionarioDTO> asociadoRepetido = getSeguimientoOrdenesService().buscarAsociadoFuncionarioActivoByOrden(asociadoFuncionario);

            if (asociadoRepetido != null && !asociadoRepetido.isEmpty()) {
                asociadoRepetido.get(0).setBlnActivo(Constantes.ENTERO_CERO);
                getSeguimientoOrdenesService().actualizarAsociadoFuncionario(asociadoRepetido.get(0));
            }

            if (!getAttributeHelper().getIdEmpleadoJefeDepartamento().equals(new BigDecimal(-1))) {
                getSeguimientoOrdenesService().insertarAsociadoFuncionario(asociadoFuncionario, getDtoHelper().getOrdenSeleccionada());
            }
        }

    }

    private void insertarEnlace(List<EmpleadoDTO> listaFuncionarios) {
        EmpleadoDTO empleado = new EmpleadoDTO();
        AsociadoFuncionarioDTO asociadoFuncionario;

        BigDecimal id;
        if (getAttributeHelper().getIdEmpleadoEnlace().equals(new BigDecimal(-1))) {
            id = getAttributeHelper().getIdEmpleadoEnlaceAnterior();
        } else {
            id = getAttributeHelper().getIdEmpleadoEnlace();
        }

        if (listaFuncionarios != null && !listaFuncionarios.isEmpty()) {
            for (EmpleadoDTO funcionario : listaFuncionarios) {
                if (funcionario.getIdEmpleado().equals(id)) {
                    empleado = funcionario;
                    break;
                }
            }
            asociadoFuncionario = llenarAsociadoFuncionario(empleado);
            List<AsociadoFuncionarioDTO> asociadoRepetido = getSeguimientoOrdenesService().buscarAsociadoFuncionarioActivoByOrden(asociadoFuncionario);

            if (asociadoRepetido != null && !asociadoRepetido.isEmpty()) {
                asociadoRepetido.get(0).setBlnActivo(Constantes.ENTERO_CERO);
                getSeguimientoOrdenesService().actualizarAsociadoFuncionario(asociadoRepetido.get(0));
            }

            if (!getAttributeHelper().getIdEmpleadoEnlace().equals(new BigDecimal(-1))) {
                getSeguimientoOrdenesService().insertarAsociadoFuncionario(asociadoFuncionario, getDtoHelper().getOrdenSeleccionada());
            }

        }

    }

    private AsociadoFuncionarioDTO llenarAsociadoFuncionario(EmpleadoDTO empleado) {
        AsociadoFuncionarioDTO asociadoFuncionarioDTO = new AsociadoFuncionarioDTO();
        asociadoFuncionarioDTO.setIdPropuesta(getDtoHelper().getOrdenSeleccionada().getIdPropuesta());
        asociadoFuncionarioDTO.setIdOrden(getDtoHelper().getOrdenSeleccionada().getIdOrden());
        asociadoFuncionarioDTO.setIdEmpleado(empleado.getIdEmpleado());
        asociadoFuncionarioDTO.setIdTipoEmpleado(empleado.getDetalleEmpleado().get(0).getTipoEmpleado().getBigId());
        asociadoFuncionarioDTO.setBlnActivo(Constantes.ENTERO_UNO);

        return asociadoFuncionarioDTO;
    }

    public void validaDoctosRechazoProrroga() {
        if (getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO() != null
                && !getLstHelper().getListaSolicitudContribuyenteResolucionRechazoVO().isEmpty()) {
            if (getAttributeHelper().getJustificacionRechazo() != null && !getAttributeHelper().getJustificacionRechazo().trim().equals("")) {
                RequestContext.getCurrentInstance().execute("PF('confirmarRechazarSolicitudContr').show();");
            } else {
                addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, getMessageResourceString(MSG_MOTIVO_RECHAZO), "");
            }
        } else {
            addErrorMessage(MSG_SOLICTUD_CONTR_RECHAZADA_ERROR, getMessageResourceString(MSG_ARCHIVO_RECHAZO), "");
        }
        RequestContext.getCurrentInstance().update(FORM_DOCUMENTACION_ORDEN_PNL_SOLICITUD_CONTR);
        RequestContext.getCurrentInstance().update("formDocumentacionOrden:messagesSolicitudContrRechazadaError");
    }

}
