package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.carga;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenAttributeHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenDTOHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstOficioAnexoHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenLstOficioHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosOrdenStreamedHelper;
import org.primefaces.model.DefaultStreamedContent;

import org.primefaces.model.StreamedContent;

public class DocumentacionOrdenAtributeMBAbstract extends OrdenAtributeAbstractMB {

    private static final long serialVersionUID = -6377791551276348495L;

    private DoctosOrdenAttributeHelper attributeHelper;
    private DoctosOrdenDTOHelper dtoHelper;
    private DoctosOrdenLstHelper lstHelper;
    private DoctosOrdenLstOficioHelper lstOficioHelper;
    private DoctosOrdenLstOficioAnexoHelper lstOficioAnexoHelper;
    private DoctosOrdenStreamedHelper streamedHelper;

    private EmpleadoDTO empleadoLogueado;

    public EmpleadoDTO getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public DoctosOrdenAttributeHelper getAttributeHelper() {
        return attributeHelper;
    }

    public void setAttributeHelper(DoctosOrdenAttributeHelper attributeHelper) {
        this.attributeHelper = attributeHelper;
    }

    public DoctosOrdenDTOHelper getDtoHelper() {
        return dtoHelper;
    }

    public void setDtoHelper(DoctosOrdenDTOHelper dtoHelper) {
        this.dtoHelper = dtoHelper;
    }

    public DoctosOrdenLstHelper getLstHelper() {
        return lstHelper;
    }

    public void setLstHelper(DoctosOrdenLstHelper lstHelper) {
        this.lstHelper = lstHelper;
    }

    public DoctosOrdenLstOficioHelper getLstOficioHelper() {
        return lstOficioHelper;
    }

    public void setLstOficioHelper(DoctosOrdenLstOficioHelper lstOficioHelper) {
        this.lstOficioHelper = lstOficioHelper;
    }

    public DoctosOrdenLstOficioAnexoHelper getLstOficioAnexoHelper() {
        return lstOficioAnexoHelper;
    }

    public void setLstOficioAnexoHelper(DoctosOrdenLstOficioAnexoHelper lstOficioAnexoHelper) {
        this.lstOficioAnexoHelper = lstOficioAnexoHelper;
    }

    public DoctosOrdenStreamedHelper getStreamedHelper() {
        return streamedHelper;
    }

    public void setStreamedHelper(DoctosOrdenStreamedHelper streamedHelper) {
        this.streamedHelper = streamedHelper;
    }

    public void setEmpleadoLogueado(EmpleadoDTO empleadoLogueado) {
        this.empleadoLogueado = empleadoLogueado;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        streamedHelper
                .setArchivoDescargaPromocion(getDescargaArchivo(getDtoHelper().getPromocionSeleccionada().getRutaArchivo(),
                                getDtoHelper().getPromocionSeleccionada().getNombreArchivo()));

        return streamedHelper.getArchivoDescargaPromocion();
    }

    public StreamedContent getArchivoDescargaAcuse() {
        streamedHelper.setArchivoDescargaAcuse(getDescargaArchivo(dtoHelper.getOficioSeleccionado().getRutaAcuseNyv(),
                dtoHelper.getOficioSeleccionado().getNombreAcuseNyv()));
        return streamedHelper.getArchivoDescargaAcuse();
    }

    public StreamedContent getArchivoDescargaAcusePromocion() {
        streamedHelper.setArchivoDescargaAcusePromocion(
                getDescargaArchivo(dtoHelper.getPromocionSeleccionada().getRutaAcuse(),
                        dtoHelper.getPromocionSeleccionada().getNombreAcuse()));
        return streamedHelper.getArchivoDescargaAcusePromocion();
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        streamedHelper.setArchivoDescargaPruebasAlegatos(
                getDescargaArchivo(dtoHelper.getPruebaAlegatoSeleccionada().getRutaArchivo(),
                        dtoHelper.getPruebaAlegatoSeleccionada().getNombreArchivo()));

        return streamedHelper.getArchivoDescargaPruebasAlegatos();
    }

    public StreamedContent getArchivoDescargaProrroga() {
        streamedHelper
                .setArchivoDescargaProrroga(getDescargaArchivo(dtoHelper.getProrrogaOrdenSeleccionada().getRutaAcuse(),
                                dtoHelper.getProrrogaOrdenSeleccionada().getNombreAcuse()));
        return streamedHelper.getArchivoDescargaProrroga();
    }

    public StreamedContent getArchivoDescargaDocumentacionProrroga() {
        streamedHelper.setArchivoDescargaDocumentacionProrroga(
                getDescargaArchivo(dtoHelper.getDocumentacionProrrogaOrdenSeleccionado().getRutaArchivo(),
                        dtoHelper.getDocumentacionProrrogaOrdenSeleccionado().getNombreArchivo()));
        return streamedHelper.getArchivoDescargaDocumentacionProrroga();
    }

    public StreamedContent getArchivoDescargaDocumentacionSolicitudContribuyente() {
        return getDescargaArchivo(dtoHelper.getDocumentacionSolicitudContribuyenteSeleccionado().getRutaArchivo(),
                dtoHelper.getDocumentacionSolicitudContribuyenteSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoAnexoSolicitudContribuyente() {
        streamedHelper.setArchivoAnexoSolicitudContribuyente(
                getDescargaArchivo(dtoHelper.getSolicitudContribuyenteAnexoSeleccionado().getRutaArchivo(),
                        dtoHelper.getSolicitudContribuyenteAnexoSeleccionado().getNombreArchivo()));
        return streamedHelper.getArchivoAnexoSolicitudContribuyente();
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        final String nombre = CargaArchivoUtilOrdenes.getNombreArchivoOrdenPdf(dtoHelper.getOrdenSeleccionDescarga());
        final String path = RutaArchivosUtilOrdenes.armarRutaDocOrden(dtoHelper.getOrdenSeleccionDescarga()) + nombre;
        streamedHelper.setArchivoSeleccionDescarga(getDescargaArchivo(path, nombre));
        return streamedHelper.getArchivoSeleccionDescarga();
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        final String nombre = dtoHelper.getOficioSeleccionDescarga().getNombreArchivo();
        final String path = dtoHelper.getOficioSeleccionDescarga().getRutaArchivo();
        streamedHelper.setArchivoOficioSeleccionDescarga(getDescargaArchivo(path, nombre));
        return streamedHelper.getArchivoOficioSeleccionDescarga();
    }

    public StreamedContent getOficioSeleccionDescarga() {
        byte[] archivoBytes = lecturaArchivo(dtoHelper.getOficioSeleccionado().getArchivo());
        dtoHelper.getOficioSeleccionado().setArchivo(new ByteArrayInputStream(archivoBytes));
        streamedHelper.setOficioSeleccionDescarga(new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                CargaArchivoUtilOrdenes.obtenContentTypeArchivoPropuesta(dtoHelper.getOficioSeleccionado().getNombreArchivo()),
                CargaArchivoUtilOrdenes.aplicarCodificacionTexto(dtoHelper.getOficioSeleccionado().getNombreArchivo())));

        return streamedHelper.getOficioSeleccionDescarga();
    }

    public StreamedContent getAnexoSeleccionDescarga() {
        byte[] archivoBytes = lecturaArchivo(dtoHelper.getAnexoSeleccionado().getArchivo());
        dtoHelper.getAnexoSeleccionado().setArchivo(new ByteArrayInputStream(archivoBytes));
        streamedHelper.setAnexoSeleccionDescarga(new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                CargaArchivoUtilOrdenes.obtenContentTypeArchivoPropuesta(dtoHelper.getAnexoSeleccionado().getNombreArchivo()),
                CargaArchivoUtilOrdenes.aplicarCodificacionTexto(dtoHelper.getAnexoSeleccionado().getNombreArchivo())));

        return streamedHelper.getAnexoSeleccionDescarga();
    }

    public StreamedContent getArchivoDescargaOfRechazado() {
        return getArchivoGenerico(dtoHelper.getOfRechazadoSeleccionado().getRutaArchivo(),
                dtoHelper.getOfRechazadoSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaOficio() {
        return getArchivoGenerico(dtoHelper.getOficioSeleccionado().getRutaArchivo(),
                dtoHelper.getOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaOfDependiente() {
        return getArchivoGenerico(dtoHelper.getOfDependienteSeleccionado().getRutaArchivo(),
                dtoHelper.getOfDependienteSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAnexoOficio() {
        return getArchivoGenericoOficioAnexo(dtoHelper.getAnexoOficioSeleccionado().getRutaArchivo(),
                dtoHelper.getAnexoOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAnexoRechazoOficio() {
        return getArchivoGenerico(dtoHelper.getAnexoRechazoOficioSeleccionado().getRutaArchivo(),
                dtoHelper.getAnexoRechazoOficioSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAnexoSolicitudContribuyente() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            return getArchivoDescargaAnexoProrrogaOrden();
        } else {
            return getArchivoGenericoAnexoProrrogaOrden(dtoHelper.getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexoPruebasPericiales().getRutaArchivo(),
                    dtoHelper.getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexoPruebasPericiales().getNombreArchivo());
        }

    }

    public StreamedContent getArchivoDescargaAnexoProrrogaOrden() {
        return getArchivoGenericoAnexoProrrogaOrden(dtoHelper.getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexosProrrogaOrden().getRutaArchivo(),
                dtoHelper.getSolicitudContribuyenteAnexoHistoricoVOSeleccionada().getFecetAnexosProrrogaOrden().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaAnexoProrrogaOficio() {
        return getArchivoGenericoAnexoProrrogaOrden(dtoHelper.getAnexoProrrogaOrdenSeleccionado().getRutaArchivo(),
                dtoHelper.getAnexoProrrogaOrdenSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargaResolucionSolicitudContribuyente() {
        if (getDtoHelper().getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden() != null) {
            return getArchivoDescargaResolucionProrrogaOrden();
        } else {
            return getArchivoGenerico(dtoHelper.getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getRutaResolucion(),
                    dtoHelper.getSolicitudContribuyenteVOSeleccionada().getPruebasPericiales().getNombreResolucion());
        }

    }

    public StreamedContent getArchivoDescargaResolucionProrrogaOrden() {
        return getArchivoGenerico(dtoHelper.getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getRutaResolucion(),
                dtoHelper.getSolicitudContribuyenteVOSeleccionada().getProrrogaOrden().getNombreResolucion());
    }

    public StreamedContent getArchivoDescargaResolucionProrrogaOficio() {
        return getArchivoGenerico(dtoHelper.getProrrogaOrdenSeleccionada().getRutaResolucion(),
                dtoHelper.getProrrogaOrdenSeleccionada().getNombreResolucion());
    }

    public StreamedContent getArchivoDescargaAcuseProrrogaOrden() {
        return getArchivoGenerico(dtoHelper.getProrrogaOrdenSeleccionada().getRutaAcuse(),
                dtoHelper.getProrrogaOrdenSeleccionada().getNombreAcuse());
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {

        if (dtoHelper.getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(dtoHelper.getPapelTrabajoSeleccionado().getIdDocumentoTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", dtoHelper.getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
        }

        return getArchivoGenerico(dtoHelper.getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo(),
                dtoHelper.getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
    }

    public StreamedContent descargarArchivoRL() {
        return getArchivoGenerico(getDtoHelper().getDocmentoRLSeleccionado().getRutaArchivo(), getDtoHelper().getDocmentoRLSeleccionado().getNombreArchivo());
    }

    public StreamedContent getArchivoDescargar() {
        return getArchivoGenerico(getAttributeHelper().getRutaArchivo(), getAttributeHelper().getNombreArchivo());
    }

}
