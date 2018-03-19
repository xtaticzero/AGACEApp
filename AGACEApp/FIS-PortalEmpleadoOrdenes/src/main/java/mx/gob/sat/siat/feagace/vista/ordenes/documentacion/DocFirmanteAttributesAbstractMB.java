/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmanteSeguimientoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.ordenes.firma.FirmanteOrdenSuplenteMB;
import mx.gob.sat.siat.feagace.vista.ordenes.firma.FirmanteSeguimientoMB;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocFirmanteAttributesAbstractMB extends AbstractManagedBean {

    private static final long serialVersionUID = 4242109851160744455L;

    protected static final String APPLET = "appletFirmado";
    protected static final String FIRMA = "firmarOficio?faces-redirect=true";
    protected static final String FIRMA_PRORROGA = "firmarProrroga?faces-redirect=true";
    protected static final String EXITO_GUARDADO = "exito.guardado";
    protected static final String RETURN_INDEX = "documentacionOrden";
    protected static final String PARAM_DESCRIPCION_RECHAZO = "documentacionFirmanteMB:textAreaMotivo";
    protected static final String PARAM_CADENA_FIRMADA = "cadena_firmada";
    protected static final String PARAM_CADENA_ORIGINAL = "cadena_original";
    protected static final String PARAM_DESCRIPCION_RECHAZO_SOLICITUD_CONTRIBUYENTE = "documentacionFirmanteMB:textAreaRechazoSolicitudContr";
    protected static final String FIRMA_PRUEBA_PERICIAL = "firmarPruebaPericial?faces-redirect=true";

    private boolean renderedProrrogas;
    private boolean firmadoDocumento;
    private boolean firmadoProrroga;
    private String motivoRechazo;

    private FecetOficio oficioSeleccionado;
    private FecetPromocion promocionSeleccionada;
    private FecetAlegato pruebaAlegatoSeleccionada;
    private FecetOficioAnexos documentoAnexoSeleccionado;
    private FecetProrrogaOrden prorrogaOrdenSeleccionada;
    private AgaceOrden ordenSeleccionado;
    private SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionado;
    private transient StreamedContent archivoDescargaPruebaPericial;
    private transient StreamedContent archivoDescargaProrroga;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaDocumentoAnexo;
    private transient StreamedContent archivoDescargaDocumentoAnexoOficio;
    private transient StreamedContent archivoDescargaPromocionAgenteAduanal;
    private transient StreamedContent archivoDescargaPruebasAlegatosAgenteAduanal;

    @ManagedProperty(value = "#{firmanteSeguimientoMB}")
    private FirmanteSeguimientoMB firmanteSeguimientoMB;

    @ManagedProperty(value = "#{firmanteSuplenteOrden}")
    private FirmanteOrdenSuplenteMB firmanteSuplente;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{firmanteSeguimientoService}")
    private transient FirmanteSeguimientoService firmanteSeguimientoService;

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public final FirmanteOrdenSuplenteMB getFirmanteSuplente() {
        return firmanteSuplente;
    }

    public final void setFirmanteSuplente(FirmanteOrdenSuplenteMB firmanteSuplente) {
        this.firmanteSuplente = firmanteSuplente;
    }

    public void setFirmanteSeguimientoService(FirmanteSeguimientoService firmanteSeguimientoService) {
        this.firmanteSeguimientoService = firmanteSeguimientoService;
    }

    public FirmanteSeguimientoService getFirmanteSeguimientoService() {
        return firmanteSeguimientoService;
    }

    public FirmanteSeguimientoMB getFirmanteSeguimientoMB() {
        return firmanteSeguimientoMB;
    }

    public void setFirmanteSeguimientoMB(FirmanteSeguimientoMB firmanteSeguimientoMB) {
        this.firmanteSeguimientoMB = firmanteSeguimientoMB;
    }

    public boolean isRenderedProrrogas() {
        return renderedProrrogas;
    }

    public void setRenderedProrrogas(boolean renderedProrrogas) {
        this.renderedProrrogas = renderedProrrogas;
    }

    public boolean isFirmadoDocumento() {
        return firmadoDocumento;
    }

    /**
     * @param firmadoDocumento
     */
    public void setFirmadoDocumento(boolean firmadoDocumento) {
        this.firmadoDocumento = firmadoDocumento;
    }

    /**
     * @param firmadoProrroga
     */
    public void setFirmadoProrroga(boolean firmadoProrroga) {
        this.firmadoProrroga = firmadoProrroga;
    }

    /**
     * @docRoot Muestra los botones para firmar o rechazar la prorroga
     *
     * @return boolean
     */
    public boolean isFirmadoProrroga() {
        return firmadoProrroga;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public StreamedContent getArchivoDescargaProrroga() {
        archivoDescargaProrroga
                = getDescargaArchivo(solicitudContribuyenteVOSeleccionado.getProrrogaOrden().getRutaAcuse(), solicitudContribuyenteVOSeleccionado.getProrrogaOrden().getNombreAcuse());
        return archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        archivoDescargaPruebasAlegatos
                = getDescargaArchivo(pruebaAlegatoSeleccionada.getRutaArchivo(), pruebaAlegatoSeleccionada.getNombreArchivo());
        return archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        archivoDescargaPromocion
                = getDescargaArchivo(promocionSeleccionada.getRutaArchivo(), promocionSeleccionada.getNombreArchivo());
        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaAcuse(StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public void setArchivoDescargaProrroga(StreamedContent archivoDescargaProrroga) {
        this.archivoDescargaProrroga = archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        logger.info("Acuse ");
        logger.info(promocionSeleccionada.getAcuseRecibo());
        archivoDescargaAcuse
                = getDescargaArchivo(promocionSeleccionada.getRutaAcuse(), Constantes.NOMBRE_ACUSE_RECIBO);
        return archivoDescargaAcuse;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetProrrogaOrden getProrrogaOrdenSeleccionada() {
        return prorrogaOrdenSeleccionada;
    }

    public void setProrrogaOrdenSeleccionada(FecetProrrogaOrden prorrogaOrdenSeleccionada) {
        this.prorrogaOrdenSeleccionada = prorrogaOrdenSeleccionada;
    }

    public void setPromocionSeleccionada(FecetPromocion promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocion getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public FecetOficioAnexos getDocumentoAnexoSeleccionado() {
        return documentoAnexoSeleccionado;
    }

    public void setDocumentoAnexoSeleccionado(FecetOficioAnexos documentoAnexoSeleccionado) {
        this.documentoAnexoSeleccionado = documentoAnexoSeleccionado;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegato pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public FecetAlegato getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public StreamedContent getArchivoDescargaDocumentoAnexo() {
        archivoDescargaDocumentoAnexo = null;
        if (oficioSeleccionado.getNombreAcuseNyv() != null && oficioSeleccionado.getFecetDetalleNyV().getRutaAcuseNyv() != null) {
            archivoDescargaDocumentoAnexo = getDescargaArchivo(oficioSeleccionado.getFecetDetalleNyV().getRutaAcuseNyv(), oficioSeleccionado.getNombreAcuseNyv());
        }
        return archivoDescargaDocumentoAnexo;
    }

    public StreamedContent getArchivoDescargaDocumentoAnexoOficio() {
        logger.info("documentoAnexoSeleccionado {} ", documentoAnexoSeleccionado);
        archivoDescargaDocumentoAnexoOficio
                = getDescargaArchivo(documentoAnexoSeleccionado.getRutaArchivo(), documentoAnexoSeleccionado.getNombreArchivo());
        return archivoDescargaDocumentoAnexoOficio;
    }

    public StreamedContent getArchivoDescargaPruebaPericial() {
        archivoDescargaPruebaPericial
                = getDescargaArchivo(solicitudContribuyenteVOSeleccionado.getPruebasPericiales().getRutaAcuse(), solicitudContribuyenteVOSeleccionado.getPruebasPericiales().getNombreAcuse());
        return archivoDescargaPruebaPericial;
    }

    public void setArchivoDescargaPruebaPericial(StreamedContent archivoDescargaPruebaPericial) {
        this.archivoDescargaPruebaPericial = archivoDescargaPruebaPericial;
    }

    public SolicitudContribuyenteVO getSolicitudContribuyenteVOSeleccionado() {
        return solicitudContribuyenteVOSeleccionado;
    }

    public void setSolicitudContribuyenteVOSeleccionado(SolicitudContribuyenteVO solicitudContribuyenteVOSeleccionado) {
        this.solicitudContribuyenteVOSeleccionado = solicitudContribuyenteVOSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaDocumentoAnexo(StreamedContent archivoDescargaDocumentoAnexo) {
        this.archivoDescargaDocumentoAnexo = archivoDescargaDocumentoAnexo;
    }

    public void setArchivoDescargaDocumentoAnexoOficio(StreamedContent archivoDescargaDocumentoAnexoOficio) {
        this.archivoDescargaDocumentoAnexoOficio = archivoDescargaDocumentoAnexoOficio;
    }

    public void setArchivoDescargaPromocionAgenteAduanal(StreamedContent archivoDescargaPromocionAgenteAduanal) {
        this.archivoDescargaPromocionAgenteAduanal = archivoDescargaPromocionAgenteAduanal;
    }

    public void setArchivoDescargaPruebasAlegatosAgenteAduanal(
            StreamedContent archivoDescargaPruebasAlegatosAgenteAduanal) {
        this.archivoDescargaPruebasAlegatosAgenteAduanal = archivoDescargaPruebasAlegatosAgenteAduanal;
    }

    public StreamedContent getArchivoDescargaPromocionAgenteAduanal() {
        return archivoDescargaPromocionAgenteAduanal;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatosAgenteAduanal() {
        return archivoDescargaPruebasAlegatosAgenteAduanal;
    }

}
