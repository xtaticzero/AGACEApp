/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper.DetalleOrdenSolicitudHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper.DetalleOrdenUnifHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper.DetalleOrdenVistaHelper;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DetalleOrdenAbtrsctMB extends ReimprimirDocumentosAbstractMB {

    private static final long serialVersionUID = 4192707809774496L;

    protected static final String ORIGEN_CONSULTA = "origenConsulta";
    protected static final String USER_PROFILE = "userProfile";
    protected static final String ORDEN_CONSULTA_SELECCIONADA = "ordenConsultaSeleccionada";
    
    protected static final String MSG_ERROR_DESCARGA = "No se pudo descargar el archivo. ";
    protected static final String MSG_ERROR_DOC_SELECC = "No se encontro el documento seleccionado";

    private DetalleOrdenUnifHelper helper;
    private DetalleOrdenSolicitudHelper solHelper;
    private OrdenConsultaDTO ordenConsultaSeleccionada;
    private DetalleOrdenVistaHelper vistaHelper;

    public OrdenConsultaDTO getOrdenConsultaSeleccionada() {
        return ordenConsultaSeleccionada;
    }

    public StreamedContent getArchivoDescargar() {
        return getArchivoGenerico(getRutaArchivo(), getNombreArchivo());
    }

    protected StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    public void getOficiosDependientesEstatusOficioNotificadoContribuyente() {
        getHelper().setListaOficiosDependientes(getSeguimientoOrdenesService()
                .getOficiosDependientesFirmados(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosOficio() {
        getHelper().setListaAnexosOficio(
                getSeguimientoOrdenesService().getAnexosOficio(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getAnexosOficioRechazo() {
        getHelper().setListaOficioAnexosRechazo(getSeguimientoOrdenesService()
                .getAnexosOficioRechazo(getHelper().getOficioSeleccionado().getIdOficio()));
    }

    public void getOficiosDependientesEstatusOficioRechazado() {
        
        getHelper().setListaOficiosDependientes(getSeguimientoOrdenesService().getOficiosDependientesByIdEstatus(
                getHelper().getOficioSeleccionado().getIdOficio(), Constantes.ESTATUS_OFICIO_RECHAZADO));
    }

    public void getAnexosRechazoOficio() {
        getHelper().setListaAnexosRechazoOficio(getSeguimientoOrdenesService().getAnexosRechazoOficio(
                getHelper().getOficioSeleccionado().getFecetRechazoOficio().getIdRechazoOficio()));
    }

    public StreamedContent getArchivoDescargaAnexoRechazoOficio() {
        
        StreamedContent content = null;

        try {
            content = getArchivoGenerico(getHelper().getAnexoRechazoOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoRechazoOficioSeleccionado().getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERROR_DESCARGA, exception);
            addErrorMessage(MSG_ERROR_DOC_SELECC);
        }
        
        return content;
    }

    public StreamedContent getArchivoDescargaAnexoOficio() {
        StreamedContent content = null;

        try {
            content = getDescargaArchivo(getHelper().getAnexoOficioSeleccionado().getRutaArchivo(),
                getHelper().getAnexoOficioSeleccionado().getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERROR_DESCARGA, exception);
            addErrorMessage(MSG_ERROR_DOC_SELECC);
        }
        
        return content;
    }

    public StreamedContent getArchivoDescargaOfDependiente() {
        
        StreamedContent content = null;

        try {
            content = getArchivoGenerico(getHelper().getOfDependienteSeleccionado().getRutaArchivo(),
                getHelper().getOfDependienteSeleccionado().getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERROR_DESCARGA, exception);
            addErrorMessage(MSG_ERROR_DOC_SELECC);
        }
        
        return content;
    }

    public StreamedContent getArchivoDescargaOfRechazado() {
        StreamedContent content = null;

        try {
            content = getArchivoGenerico(getHelper().getOfRechazadoSeleccionado().getRutaArchivo(),
                    getHelper().getOfRechazadoSeleccionado().getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERROR_DESCARGA, exception);
            addErrorMessage(MSG_ERROR_DOC_SELECC);
        }
        
        return content;
    }

    public void setOrdenConsultaSeleccionada(OrdenConsultaDTO ordenConsultaSeleccionada) {
        getHelper().setOrdenSeleccionada(convertToAgaceOrden(ordenConsultaSeleccionada));
        this.ordenConsultaSeleccionada = ordenConsultaSeleccionada;
    }

    protected AgaceOrden convertToAgaceOrden(OrdenConsultaDTO ordenConsultaDTO) {

        try {
            List<AgaceOrden> listaOrden = getConsultarReimprimirDocumentosService()
                    .buscarOrden(ordenConsultaDTO.getNumeroOrden(), ordenConsultaDTO.getIdRegistro(), null, null, null);

            return listaOrden.get(0);
        } catch (Exception npe) {
            logger.error("No se pudo cargar la orden. ", npe);
            return null;
        }
    }

    public DetalleOrdenUnifHelper getHelper() {
        return helper;
    }

    public void setHelper(DetalleOrdenUnifHelper helper) {
        this.helper = helper;
    }

    public DetalleOrdenSolicitudHelper getSolHelper() {
        return solHelper;
    }

    public void setSolHelper(DetalleOrdenSolicitudHelper solHelper) {
        this.solHelper = solHelper;
    }

    public DetalleOrdenVistaHelper getVistaHelper() {
        return vistaHelper;
    }

    public void setVistaHelper(DetalleOrdenVistaHelper vistaHelper) {
        this.vistaHelper = vistaHelper;
    }

}
