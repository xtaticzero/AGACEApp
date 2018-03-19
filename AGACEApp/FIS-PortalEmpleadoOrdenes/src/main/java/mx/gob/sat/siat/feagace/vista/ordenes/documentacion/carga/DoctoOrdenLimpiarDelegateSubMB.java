package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.util.ArrayList;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class DoctoOrdenLimpiarDelegateSubMB extends DoctoOrdenLimpiarDelegateMB {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected static final String MENSAJE_EXITO_CARGA_OFICIO = "msg.exito.carga.oficio.validacion";
    protected static final String MENSAJE_ERROR_CARGA_RESOLUCION = "msg.error.carga.prorroga.resolucion";
    protected static final String MENSAJE_ERROR_CARGA_RECHAZO = "msg.error.carga.prorroga.rechazo";
    protected static final String MSGEXITODOCS = "msgExitoGuardarDocsMsg";
    protected static final String MSGEXITOSUBORDINADOS = "msgExitoSubordinados";
    protected static final String CONFIRMAR_APROBAR_SOLICITUD_CONTR_HIDE = "PF('confirmarAprobarSolicitudContr').hide();";
    protected static final String MSG_MOTIVO_RECHAZO = "error.rechazo.prorroga.sin.descripcion";
    protected static final String MSG_ARCHIVO_RECHAZO = "error.seleccionar.archivo.prorroga.rechazo";

    public void limpiarDocumentacionAvisoContribuyente() {
        limpiarAvisoContribuyente();
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyente(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarPruebasDesahogo() {
        getLstOficioHelper().setListaOfPruebasDesahogo(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionPruebasDesahogo() {
        limpiarPruebasDesahogo();
        getLstOficioAnexoHelper().setListaAnexosPruebasDesahogo(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarConclusionRevision() {
        getLstOficioHelper().setListaOfConclusionRevision(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionConclusionRevision() {
        limpiarConclusionRevision();
        getLstOficioAnexoHelper().setListaAnexosConclusionRevision(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarCancelacionOrden() {
        getLstOficioHelper().setListaOfCancelacionOrden(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionCancelacionOrden() {
        limpiarCancelacionOrden();
        getLstOficioAnexoHelper().setListaAnexosCancelacionOrden(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarSinObservaciones() {
        getLstOficioHelper().setListaOfSinObservaciones(new ArrayList<FecetOficio>());
    }

    public void limpiarMedidasApremio() {
        getLstOficioHelper().setListaOfMedidasApremio(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionCompInternacional() {
        limpiarCompInternacional();
        getLstOficioAnexoHelper().setListaAnexosCompInternacional(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarDocumentacionCambioMetodoUCAMCA() {
        limpiarCambioMetodoUCAMCA();
        getLstOficioAnexoHelper().setListaAnexosCambioMetodoUCAMCA(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarCompInternacional() {
        getLstOficioHelper().setListaOfCompInternacional(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionSinObservaciones() {
        limpiarSinObservaciones();
        getLstOficioAnexoHelper().setListaAnexosSinObservaciones(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarDocumentacionMedidasApremio() {
        limpiarMedidasApremio();
        getLstOficioAnexoHelper().setListaAnexosMedidasApremio(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarConObservaciones() {
        getLstOficioHelper().setListaOfConObservaciones(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionConObservaciones() {
        limpiarConObservaciones();
        getLstOficioAnexoHelper().setListaAnexosConObservaciones(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarRequerimientoReincidencia() {
        getLstOficioHelper().setListaOfRequerimientoReincidencia(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionRequerimientoReincidencia() {
        limpiarRequerimientoReincidencia();
        getLstOficioAnexoHelper().setListaAnexosRequerimientoReincidencia(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarCambioMetodoUCAMCA() {
        getLstOficioHelper().setListaOfCambioMetodoUCAMCA(new ArrayList<FecetOficio>());
    }

    public void limpiarAvisoCircunstancial() {
        getLstOficioHelper().setListaOfAvisoCircunstancial(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionAvisoCircunstancial() {
        limpiarAvisoCircunstancial();
        getLstOficioAnexoHelper().setListaAnexosAvisoCircunstancial(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarOficiosAdmin() {
        getAttributeHelper().setNombreDocumentoOrden(null);
        getLstOficioHelper().setListaOficiosAdmin(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionMultaOrden() {
        limpiarMultaOrden();
        getLstOficioAnexoHelper().setListaAnexosMultaOrden(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarMultaOrden() {
        getLstOficioHelper().setListaOfMultaOrden(new ArrayList<FecetOficio>());
    }

    public void limpiarDocumentacionAvisoContribuyenteOrden() {
        limpiarAvisoContribuyenteOrden();
        getLstOficioAnexoHelper().setListaAnexosAvisoContribuyenteOrden(new ArrayList<FecetOficioAnexos>());
    }

    public void limpiarAvisoContribuyenteOrden() {
        getLstOficioHelper().setListaOfAvisoContribuyenteOrden(new ArrayList<FecetOficio>());
    }

    public void mostrarPanelDocumentacion() {
        getAttributeHelper().setMostrarDocumentosSeguimiento(true);
        getAttributeHelper().setMostrarOrdenesSeguimiento(false);
    }

    public void mostrarPanelOrdenes() {
        getAttributeHelper().setMostrarDocumentosSeguimiento(false);
        getAttributeHelper().setMostrarOrdenesSeguimiento(true);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        externalContext.getFlash().setKeepMessages(true);
    }

    public void getPruebasAlegatosPromocion() {
        getLstHelper().setListaPruebasAlegatos(
                getSeguimientoOrdenesService().getPruebasAlegatosPromocion(getDtoHelper().getPromocionSeleccionada().getIdPromocion()));
        getAttributeHelper().setIdPromocionSeleccionada(getDtoHelper().getPromocionSeleccionada().getIdPromocion());
        getAttributeHelper().setFechaEnvioPromocionSeleccionada(getDtoHelper().getPromocionSeleccionada().getFechaCarga());
    }

    public void getOficiosDependientesEstatusOficioRechazado() {
        getLstOficioHelper().setListaOficiosDependientes(getSeguimientoOrdenesService()
                .getOficiosDependientesByIdEstatus(getDtoHelper().getOficioSeleccionado().getIdOficio(), Constantes.ESTATUS_OFICIO_RECHAZADO));
    }

    public void getOficiosDependientesEstatusOficioNotificadoContribuyente() {
        getLstOficioHelper().setListaOficiosDependientes(
                getSeguimientoOrdenesService().getOficiosDependientesFirmados(getDtoHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosRechazoOficio() {
        getLstHelper().setListaAnexosRechazoOficio(
                getSeguimientoOrdenesService().getAnexosRechazoOficio(getDtoHelper().getOficioSeleccionado().getFecetRechazoOficio().getIdRechazoOficio()));
    }

    public void getAnexosOficio() {
        getLstOficioAnexoHelper().setListaAnexosOficio(getSeguimientoOrdenesService().getAnexosOficio(getDtoHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getDocumentacionProrrogaContribuyente() {
        getLstHelper().setListaDocumentosProrroga(getSeguimientoOrdenesService()
                .getDocumentacionProrrogaContribuyente(getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getIdProrrogaOrden()));
        getLstHelper()
                .setListaSolicitudContribuyenteDocVO(getDtoHelper().llenaListaSolicitudContribuyenteDocVO(getLstHelper().getListaDocumentosProrroga(), null));
    }

    public void getDocumentacionSolicitudContribuyente() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            getDocumentacionProrrogaContribuyente();
        } else {
            getDocumentacionPruebaPericialContribuyente();
        }
    }

    public void getDocumentacionPruebaPericialContribuyente() {
        getLstHelper().setListaDocumentosPruebaPericial(getSeguimientoOrdenesService().getDocumentacionPruebaPericialContribuyente(
                getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getIdPruebasPericiales()));
        getLstHelper().setListaSolicitudContribuyenteDocVO(
                getDtoHelper().llenaListaSolicitudContribuyenteDocVO(null, getLstHelper().getListaDocumentosPruebaPericial()));
    }

    public void getDocumentacionRechazoSolicitudContribuyente() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            getDocumentacionRechazoProrroga();
        } else {
            getDocumentacionRechazoPruebasPericiales();
        }
    }

    public void getDocumentacionRechazoProrroga() {
        getLstHelper().setListaDocumentosAnexosRechazoSolicitudContribuyente(
                getLstHelper().llenaAnexoSolicitudContribuyenteProrroga(getSeguimientoOrdenesService().getAnexosRechazoProrrogaOrden(
                        getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getFecetFlujoProrrogaOrden().getIdFlujoProrrogaOrden())));
    }

    public void getDocumentacionRechazoPruebasPericiales() {
        getLstHelper().setListaDocumentosAnexosRechazoSolicitudContribuyente(
                getLstHelper().llenaAnexoSolicitudContribuyentePruebasPericiales(getSeguimientoOrdenesService().getAnexosRechazoPruebasPericiales(getDtoHelper()
                        .getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getFlujoPruebasPericiales().getIdFlujoPruebasPericiales())));
    }

    public void getAnexosOficioRechazo() {
        getLstOficioAnexoHelper()
                .setListaOficioAnexosRechazo(getSeguimientoOrdenesService().getAnexosOficioRechazo(getDtoHelper().getOficioSeleccionado().getIdOficio()));
    }

}
