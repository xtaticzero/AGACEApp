/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta.managedbean;

import java.math.BigDecimal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.DetalleOrdenAbtrsctMB;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PistaAuditoriaDescargaDocsMB extends DetalleOrdenAbtrsctMB {

    private static final long serialVersionUID = -6098549745053618902L;

    //OFICIOS_CON 1
    //HISTORICOS_RECHAZO 2

    private int tipoPanel;

    public StreamedContent getArchivoPromocion() {
        try {
            logger.info("Pista descarga Archivo Promocion");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaDocumentoPromocion(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga ArchivoPromocion");
            logger.error(e.getMessage());
        }
        return getArchivoDescargable();
    }

    public StreamedContent getArchivoPromocionAnexo() {
        try {
            logger.info("Pista descarga Promocion Anexo");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaDocumentoPromocionAnexo(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga ArchivoPromocionAnexo");
            logger.error(e.getMessage());
        }
        return getArchivoDescargable();
    }

    public StreamedContent getArchivoOficio() {
        try {
            logger.info("Pista descarga Archivo Oficio");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaOficio(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga ArchivoOficio");
            logger.error(e.getMessage());
        }
        return getArchivoDescargable();
    }

    public StreamedContent getAnexoOficio() {
        try {
            logger.info("Pista descarga Anexo Oficio");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaAnexoOficio(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga Anexo Oficio");
            logger.error(e.getMessage());
        }
        return getArchivoDescargable();
    }

    public StreamedContent getAnexoOficioDependiente() {

        try {
            logger.info("Pista descarga AnexoOficioDependiente");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaAnexoOficioDependiente(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga AnexoOficioDependiente");
            logger.error(e.getMessage());
        }

        return getArchivoDescargable();
    }

    public StreamedContent getOficioDependiente() {

        try {
            logger.info("Pista descarga OficioDependiente");
            AgaceOrden orden = new AgaceOrden();
            orden.setIdOrden(getOrdenConsultaSeleccionada().getIdOrden() != null ? getOrdenConsultaSeleccionada().getIdOrden() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaOficioDependiente(orden);
        } catch (Exception e) {
            logger.error("ERROR pista descarga OficioDependiente");
            logger.error(e.getMessage());
        }

        return getArchivoDescargable();

    }

    public StreamedContent getRechazoAnexoOficio() {

        try {
            logger.info("Pista descarga RechazoAnexoOficio");
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(getOrdenConsultaSeleccionada().getIdPropuesta() != null ? getOrdenConsultaSeleccionada().getIdPropuesta() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaRechazoAnexoOficio(propuesta);
        } catch (Exception e) {
            logger.error("ERROR pista descarga RechazoAnexoOficio");
            logger.error(e.getMessage());
        }

        return getArchivoDescargaAnexoOficio();

    }

    public StreamedContent getRechazoOficioDependiente() {

        try {
            logger.info("Pista descarga RechazoOficioDependiente");
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(getOrdenConsultaSeleccionada().getIdPropuesta() != null ? getOrdenConsultaSeleccionada().getIdPropuesta() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaRechazoOficioDependiente(propuesta);
        } catch (Exception e) {
            logger.error("ERROR pista descarga RechazoOficioDependiente");
            logger.error(e.getMessage());
        }

        return getArchivoDescargaOfDependiente();
    }

    public StreamedContent getRechazoAnexoOficioDependiente() {
        try {
            logger.info("Pista descarga RechazoAnexoOficioDependiente");
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(getOrdenConsultaSeleccionada().getIdPropuesta() != null ? getOrdenConsultaSeleccionada().getIdPropuesta() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaRechazoAnexoOficioDependiente(propuesta);
        } catch (Exception e) {
            logger.error("ERROR pista descarga RechazoAnexoOficioDependiente");
            logger.error(e.getMessage());
        }

        return getArchivoDescargaAnexoOficio();
    }

    public StreamedContent getRechazoOficio() {

        try {
            logger.info("Pista descarga RechazoOficio");
            FecetPropuesta propuesta = new FecetPropuesta();
            propuesta.setIdPropuesta(getOrdenConsultaSeleccionada().getIdPropuesta() != null ? getOrdenConsultaSeleccionada().getIdPropuesta() : BigDecimal.ZERO);
            getReimprimirDocsPistaService().descargaRechazoOficio(propuesta);
        } catch (Exception e) {
            logger.error("ERROR pista descarga RechazoOficio");
            logger.error(e.getMessage());
        }

        return getArchivoDescargaOfRechazado();
    }

    public StreamedContent getArchivoDescargable() {
        StreamedContent content = null;

        try {
            content = getConsultarReimprimirDocumentosService().obtenerArchivoDescarga(getRutaArchivo(),
                    getNombreArchivo());
        } catch (final Exception exception) {
            logger.error("No se pudo descargar el archivo. ", exception);
            addErrorMessage("No se encontro el documento seleccionado");
        }
        return content;
    }

    public int getTipoPanel() {
        return tipoPanel;
    }

    public void setTipoPanel(int tipoPanel) {
        this.tipoPanel = tipoPanel;
    }

}
