/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.consulta;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.vista.consulta.helper.DetalleOrdenSolicitudHelper;
import mx.gob.sat.siat.feagace.vista.consulta.helper.DetalleOrdenUnifHelper;
import mx.gob.sat.siat.feagace.vista.consulta.helper.DetalleOrdenVistaHelper;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DetalleOrdenAbstractMB extends ReimprimirDocumentosAbstractMB {

    private static final long serialVersionUID = 6150223668956207860L;

    protected static final String CU_13 = "consultarPropuestasOrdenes";
    protected static final String CONSULTAR_REIMPRIMIR_DOCS = "consultarReimprimirDocs";

    protected static final String ORIGEN_CONSULTA = "origenConsulta";
    protected static final String USER_PROFILE = "userProfile";
    protected static final String ORDEN_CONSULTA_SELECCIONADA = "ordenConsultaSeleccionada";

    protected static final String MSG_ERR_DESCARGA = "No se pudo descargar el archivo. ";
    protected static final String MSG_ERR_DOC_SELECC = "No se encontro el documento seleccionado";

    private DetalleOrdenUnifHelper helper;
    private DetalleOrdenSolicitudHelper solHelper;
    private OrdenConsultaDTO ordenConsultaSeleccionada;
    private DetalleOrdenVistaHelper vistaHelper;

    protected AgaceOrden convertToAgaceOrden(OrdenConsultaDTO ordenConsultaDTO) {

        try {
            List<AgaceOrden> listaOrden = getConsultarReimprimirDocumentosService().buscarOrden(
                    ordenConsultaDTO.getNumeroOrden(), ordenConsultaDTO.getIdRegistro(), null, null, null);

            return listaOrden.get(0);
        } catch (Exception npe) {
            logger.error("No se pudo cargar la orden. ", npe);
            return null;
        }
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    protected void tablasDatosAMostrar() {

        getVistaHelper().setPnlDocRequerida(true);
        getVistaHelper().setPnlOficios(true);
        getVistaHelper().setPnlSolContrib(true);
        getVistaHelper().setPnlRepreLegal(true);
        getVistaHelper().setPnlAsociados(false);
        getVistaHelper().setPnlAgenteAdnl(true);
        getVistaHelper().setPnlPapelesTrab(true);

        try {
            EmpleadoDTO empleado = obtenerEmpleado();
            if (getEmpleadoService().validarExistenciaTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE)
                    || getEmpleadoService().validarExistenciaTipoEmpleado(empleado, TipoEmpleadoEnum.CENTRAL)) {
                getVistaHelper().setPnlPapelesTrab(false);
            }
        } catch (EmpleadoServiceException e) {
            informeErrorSession(
                    new NegocioException("Error al ejecturar el Servicio de Empleados"));
        }

        if (getHelper().getOrigenConsulta().equalsIgnoreCase(CONSULTAR_REIMPRIMIR_DOCS)) {
            getVistaHelper().setPnlAsociados(true);
            getVistaHelper().setPnlRepreLegal(false);
        }

        if (getHelper().getOrigenConsulta().equalsIgnoreCase(CU_13)) {
            getVistaHelper().setPnlAgenteAdnl(false);
            getVistaHelper().setPnlOficiosHist(true);
            getVistaHelper().setPnlSolContribHist(true);
        }

    }

    public EmpleadoDTO obtenerEmpleado() throws EmpleadoServiceException {
        return getEmpleadoService().getEmpleadoCompleto(getRFCSession());
    }

    public OrdenConsultaDTO getOrdenConsultaSeleccionada() {
        return ordenConsultaSeleccionada;
    }

    public void setOrdenConsultaSeleccionada(OrdenConsultaDTO ordenConsultaSeleccionada) {
        getHelper().setOrdenSeleccionada(convertToAgaceOrden(ordenConsultaSeleccionada));
        this.ordenConsultaSeleccionada = ordenConsultaSeleccionada;
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

    public StreamedContent getArchivoPromocionOrden() {
        StreamedContent content = null;
        try {

            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());

            if (getHelper().getOrigenConsulta().equalsIgnoreCase(CONSULTAR_REIMPRIMIR_DOCS)) {
                getPistaAuditoriaDescargaDocumentosService().consultarPromocionOrdenInterno(orden);
            } else {
                getPistaAuditoriaDescargaDocumentosService().consultarPromocion(orden);
            }
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoPromocionAnexoOrden() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            getPistaAuditoriaDescargaDocumentosService().consultarPromocionAnexo(orden);
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoOficio() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            if (getHelper().getOrigenConsulta().equalsIgnoreCase(CONSULTAR_REIMPRIMIR_DOCS)) {
                logger.debug("Insertando ista oficio");
                if (helper.getOficioConsultaSeleccionado().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())
                        || helper.getOficioConsultaSeleccionado().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())
                        || helper.getOficioConsultaSeleccionado().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio())) {
                    getPistaAuditoriaDescargaDocumentosService().consultarOficioCompulsaInterno(orden);
                } else {
                    getPistaAuditoriaDescargaDocumentosService().consultarOficioInterno(orden);
                }
            } else {
                getPistaAuditoriaDescargaDocumentosService().consultarOficio(orden);
            }
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoOficioAnexos() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            getPistaAuditoriaDescargaDocumentosService().consultarAnexoOficio(orden);
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoDependienteOficio() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            getPistaAuditoriaDescargaDocumentosService().consultarOficioDependiente(orden);
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoDependienteAnexoOficio() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            getPistaAuditoriaDescargaDocumentosService().consultarAnexoOficioDependiente(orden);
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoProrrogaOrden() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            if(getHelper().getOrigenConsulta().equalsIgnoreCase(CONSULTAR_REIMPRIMIR_DOCS)){
                getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOrdenInterno(orden);
            }else{
                getPistaAuditoriaDescargaDocumentosService().consultarProrroga(orden);
            }

            content = getDescargaArchivo(getSolHelper().getDocumentacionSolicitudContribuyenteSeleccionado().getRutaArchivo(),
                    getSolHelper().getDocumentacionSolicitudContribuyenteSeleccionado().getNombreArchivo());

        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }

    public StreamedContent getArchivoResolucionProrrogaOrden() {
        StreamedContent content = null;
        try {
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden());
            if (getHelper().getOrigenConsulta().equalsIgnoreCase(CONSULTAR_REIMPRIMIR_DOCS)) {
                getPistaAuditoriaDescargaDocumentosService().consultarProrrogaOrdenInterno(orden);
            } else {
                getPistaAuditoriaDescargaDocumentosService().consultarResolucionProrroga(orden);
            }
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(), getNombreArchivo());
        } catch (final Exception exception) {
            logger.error(MSG_ERR_DESCARGA, exception);
            addErrorMessage(MSG_ERR_DOC_SELECC);
        }
        return content;
    }
}
