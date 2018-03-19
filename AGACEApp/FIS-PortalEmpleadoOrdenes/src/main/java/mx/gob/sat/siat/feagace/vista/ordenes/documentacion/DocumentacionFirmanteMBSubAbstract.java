package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public abstract class DocumentacionFirmanteMBSubAbstract extends DocumentacionFirmanteAbstractMB implements Serializable {

    private static final long serialVersionUID = 1480144741100699615L;

    private transient StreamedContent descargaAnexosResolucionPorroga;
    private transient StreamedContent descargaArchivoContribuyente;

    private Map<String, List<FecetOficio>> mapaOficioOrden;
    private Map<String, List<FecetProrrogaOrden>> mapaProrrogas;
    private Map<String, List<FecetPruebasPericiales>> mapaPruebasPericiales;

    private FecetAnexosProrrogaOrden anexosProrrogaOrdenSeleccionada;
    private FecetDocProrrogaOrden documentacionContribuyenteSeleccionada;

    private String rutaArchivo;
    private String nombreArchivo;

    private SolicitudContribuyenteAnexoVO anexosSolicitudContribuyenteSeleccionada;
    private SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionada;

    public SolicitudContribuyenteAnexoVO getAnexosSolicitudContribuyenteSeleccionada() {
        return anexosSolicitudContribuyenteSeleccionada;
    }

    public void setAnexosSolicitudContribuyenteSeleccionada(
            SolicitudContribuyenteAnexoVO anexosSolicitudContribuyenteSeleccionada) {
        this.anexosSolicitudContribuyenteSeleccionada = anexosSolicitudContribuyenteSeleccionada;
    }

    public SolicitudContribuyenteDocVO getDocumentacionSolicitudContribuyenteSeleccionada() {
        return documentacionSolicitudContribuyenteSeleccionada;
    }

    public void setDocumentacionSolicitudContribuyenteSeleccionada(
            SolicitudContribuyenteDocVO documentacionSolicitudContribuyenteSeleccionada) {
        this.documentacionSolicitudContribuyenteSeleccionada = documentacionSolicitudContribuyenteSeleccionada;
    }

    public Map<String, List<FecetProrrogaOrden>> getMapaProrrogas() {
        return mapaProrrogas;
    }

    public void setMapaProrrogas(Map<String, List<FecetProrrogaOrden>> mapaProrrogas) {
        this.mapaProrrogas = mapaProrrogas;
    }

    public FecetDocProrrogaOrden getDocumentacionContribuyenteSeleccionada() {
        return documentacionContribuyenteSeleccionada;
    }

    public void setDocumentacionContribuyenteSeleccionada(FecetDocProrrogaOrden documentacionContribuyenteSeleccionada) {
        this.documentacionContribuyenteSeleccionada = documentacionContribuyenteSeleccionada;
    }

    public FecetAnexosProrrogaOrden getAnexosProrrogaOrdenSeleccionada() {
        return anexosProrrogaOrdenSeleccionada;
    }

    public void setAnexosProrrogaOrdenSeleccionada(FecetAnexosProrrogaOrden anexosProrrogaOrdenSeleccionada) {
        this.anexosProrrogaOrdenSeleccionada = anexosProrrogaOrdenSeleccionada;
    }

    public Map<String, List<FecetOficio>> getMapaOficioOrden() {
        return mapaOficioOrden;
    }

    public void setMapaOficioOrden(Map<String, List<FecetOficio>> mapaOficioOrden) {
        this.mapaOficioOrden = mapaOficioOrden;
    }

    public StreamedContent getDescargaArchivoContribuyente() {
        descargaArchivoContribuyente
                = getDescargaArchivo(documentacionSolicitudContribuyenteSeleccionada.getRutaArchivo(), documentacionSolicitudContribuyenteSeleccionada.getNombreArchivo());
        return descargaArchivoContribuyente;
    }

    public StreamedContent getDescargaAnexosResolucionPorroga() {
        descargaAnexosResolucionPorroga
                = getDescargaArchivo(anexosProrrogaOrdenSeleccionada.getRutaArchivo(), anexosProrrogaOrdenSeleccionada.getNombreArchivo());
        return descargaAnexosResolucionPorroga;
    }

    public StreamedContent getDescargarArchivo() {
        StreamedContent archivo = null;
        if (rutaArchivo != null && nombreArchivo != null) {
            String path = rutaArchivo.replace(nombreArchivo, "").concat(nombreArchivo);
            archivo = getDescargaArchivo(path, nombreArchivo);
        }
        return archivo;
    }

    public void setDescargaArchivoContribuyente(StreamedContent descargaArchivoContribuyente) {
        this.descargaArchivoContribuyente = descargaArchivoContribuyente;
    }

    public void setDescargaAnexosResolucionPorroga(StreamedContent descargaAnexosResolucionPorroga) {
        this.descargaAnexosResolucionPorroga = descargaAnexosResolucionPorroga;
    }

    public final String getRutaArchivo() {
        return rutaArchivo;
    }

    public final void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public final String getNombreArchivo() {
        return nombreArchivo;
    }

    public final void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Map<String, List<FecetPruebasPericiales>> getMapaPruebasPericiales() {
        return mapaPruebasPericiales;
    }

    public void setMapaPruebasPericiales(Map<String, List<FecetPruebasPericiales>> mapaPruebasPericiales) {
        this.mapaPruebasPericiales = mapaPruebasPericiales;
    }

}
