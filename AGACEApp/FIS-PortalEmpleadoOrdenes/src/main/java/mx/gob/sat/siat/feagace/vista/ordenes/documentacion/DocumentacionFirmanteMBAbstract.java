package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorDocumentoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper.DoctosFirmanteHelper;

public abstract class DocumentacionFirmanteMBAbstract extends DocFirmanteAttributesAbstractMB {

    private static final long serialVersionUID = 1123456785432L;

    private List<DocumentoVO> listaDocumentoUpLoad;

    private DoctosFirmanteHelper helper = new DoctosFirmanteHelper();

    private ColaboradorDocumentoDTO colaboradoresDTO;

    private transient StreamedContent archivoDescargaDocumentacion;
    private transient StreamedContent descargaDocumentoResolucionProrroga;
    private transient StreamedContent descargaDocumentoResolucionPruebaPericial;
    private transient StreamedContent archivoAnexoSolicitudContribuyente;

    private SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoSeleccionado;

    private transient StreamedContent archivoDescargaPromocionSeleccionada;
    private transient StreamedContent archivoDescargaAnexoSeleccionada;
    private List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanal;
    private FecetAlegatoAgenteAduanal alegatoAgenteAduanalSeleccionado;
    private List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal;
    private FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado;

    private BigDecimal idPromocionSeleccionada;
    private Date fechaEnvioPromocionSeleccionada;

    public StreamedContent getDescargaDocumentoResolucionProrroga() {
        descargaDocumentoResolucionProrroga
                = getDescargaArchivo(getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden().getRutaResolucion(), getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden().getNombreResolucion());
        return descargaDocumentoResolucionProrroga;
    }

    public StreamedContent getDescargaDocumentoResolucionPruebaPericial() {
        descargaDocumentoResolucionPruebaPericial
                = getDescargaArchivo(getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales().getRutaResolucion(), getSolicitudContribuyenteVOSeleccionado().getPruebasPericiales().getNombreResolucion());
        return descargaDocumentoResolucionPruebaPericial;
    }

    public StreamedContent getDescargaDocumentoSolicitudContribuyente() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            return getDescargaDocumentoResolucionProrroga();
        } else {
            return getDescargaDocumentoResolucionPruebaPericial();
        }

    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        archivoDescargaDocumentacion
                = getDescargaArchivo(getOficioSeleccionado().getRutaArchivo(), getOficioSeleccionado().getNombreArchivo());
        return archivoDescargaDocumentacion;
    }

    public List<DocumentoVO> getListaDocumentoUpLoad() {
        return listaDocumentoUpLoad;
    }

    public void setListaDocumentoUpLoad(List<DocumentoVO> listaDocumentoUpLoad) {
        this.listaDocumentoUpLoad = listaDocumentoUpLoad;
    }

    public void setArchivoDescargaDocumentacion(StreamedContent archivoDescargaDocumentacion) {
        this.archivoDescargaDocumentacion = archivoDescargaDocumentacion;
    }

    public void setDescargaDocumentoResolucionProrroga(StreamedContent descargaDocumentoResolucionProrroga) {
        this.descargaDocumentoResolucionProrroga = descargaDocumentoResolucionProrroga;
    }

    public final ColaboradorDocumentoDTO getColaboradoresDTO() {
        return colaboradoresDTO;
    }

    public final void setColaboradoresDTO(ColaboradorDocumentoDTO colaboradoresDTO) {
        this.colaboradoresDTO = colaboradoresDTO;
    }

    public StreamedContent getArchivoDescargaSolicitudContribuyente() {
        if (getSolicitudContribuyenteVOSeleccionado().getProrrogaOrden() != null) {
            return getArchivoDescargaProrroga();
        } else {
            return getArchivoDescargaPruebaPericial();
        }

    }

    public DoctosFirmanteHelper getHelper() {
        return helper;
    }

    public void setHelper(DoctosFirmanteHelper helper) {
        this.helper = helper;
    }

    public void validaMetodo() {
        setRenderedProrrogas(getSeguimientoOrdenesService().validaMetodoProrrogaOficio(getFirmanteSeguimientoMB().getOrdenSeleccionada(), getOficioSeleccionado()));

    }

    public void setDescargaDocumentoResolucionPruebaPericial(StreamedContent descargaDocumentoResolucionPruebaPericial) {
        this.descargaDocumentoResolucionPruebaPericial = descargaDocumentoResolucionPruebaPericial;
    }

    public StreamedContent getArchivoAnexoSolicitudContribuyente() {
        setArchivoAnexoSolicitudContribuyente(
                getDescargaArchivo(getSolicitudContribuyenteAnexoSeleccionado().getRutaArchivo(),
                        getSolicitudContribuyenteAnexoSeleccionado().getNombreArchivo()));
        return archivoAnexoSolicitudContribuyente;

    }

    public void setArchivoAnexoSolicitudContribuyente(StreamedContent archivoAnexoSolicitudContribuyente) {
        this.archivoAnexoSolicitudContribuyente = archivoAnexoSolicitudContribuyente;
    }

    public SolicitudContribuyenteAnexoVO getSolicitudContribuyenteAnexoSeleccionado() {
        return solicitudContribuyenteAnexoSeleccionado;
    }

    public void setSolicitudContribuyenteAnexoSeleccionado(
            SolicitudContribuyenteAnexoVO solicitudContribuyenteAnexoSeleccionado) {
        this.solicitudContribuyenteAnexoSeleccionado = solicitudContribuyenteAnexoSeleccionado;
    }

    @Override
    public StreamedContent getArchivoDescargaPromocionAgenteAduanal() {
        return getDescargaArchivo(getPromocionAgenteAduanalSeleccionado().getRutaArchivo() + getPromocionAgenteAduanalSeleccionado().getNombreArchivo(),
                getPromocionAgenteAduanalSeleccionado().getNombreArchivo());

    }

    @Override
    public StreamedContent getArchivoDescargaPruebasAlegatosAgenteAduanal() {
        return getDescargaArchivo(getAlegatoAgenteAduanalSeleccionado().getRutaArchivo() + getAlegatoAgenteAduanalSeleccionado().getNombreArchivo(),
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo());

    }

    public StreamedContent getArchivoDescargaPromocionSeleccionada() {
        return archivoDescargaPromocionSeleccionada;
    }

    public void setArchivoDescargaPromocionSeleccionada(StreamedContent archivoDescargaPromocionSeleccionada) {
        this.archivoDescargaPromocionSeleccionada = archivoDescargaPromocionSeleccionada;
    }

    public StreamedContent getArchivoDescargaAnexoSeleccionada() {
        return archivoDescargaAnexoSeleccionada;
    }

    public void setArchivoDescargaAnexoSeleccionada(StreamedContent archivoDescargaAnexoSeleccionada) {
        this.archivoDescargaAnexoSeleccionada = archivoDescargaAnexoSeleccionada;
    }

    public List<FecetAlegatoAgenteAduanal> getListaAlegatoAgenteAduanal() {
        return listaAlegatoAgenteAduanal;
    }

    public void setListaAlegatoAgenteAduanal(List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanal) {
        this.listaAlegatoAgenteAduanal = listaAlegatoAgenteAduanal;
    }

    public FecetAlegatoAgenteAduanal getAlegatoAgenteAduanalSeleccionado() {
        return alegatoAgenteAduanalSeleccionado;
    }

    public void setAlegatoAgenteAduanalSeleccionado(FecetAlegatoAgenteAduanal alegatoAgenteAduanalSeleccionado) {
        this.alegatoAgenteAduanalSeleccionado = alegatoAgenteAduanalSeleccionado;
    }

    public List<FecetPromocionAgenteAduanal> getListaPromocionAgenteAduanal() {
        return listaPromocionAgenteAduanal;
    }

    public void setListaPromocionAgenteAduanal(List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal) {
        this.listaPromocionAgenteAduanal = listaPromocionAgenteAduanal;
    }

    public FecetPromocionAgenteAduanal getPromocionAgenteAduanalSeleccionado() {
        return promocionAgenteAduanalSeleccionado;
    }

    public void setPromocionAgenteAduanalSeleccionado(FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado) {
        this.promocionAgenteAduanalSeleccionado = promocionAgenteAduanalSeleccionado;
    }

    public BigDecimal getIdPromocionSeleccionada() {
        return idPromocionSeleccionada;
    }

    public void setIdPromocionSeleccionada(BigDecimal idPromocionSeleccionada) {
        this.idPromocionSeleccionada = idPromocionSeleccionada;
    }

    public Date getFechaEnvioPromocionSeleccionada() {
        return (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada.clone() : null;
    }

    public void setFechaEnvioPromocionSeleccionada(Date fechaEnvioPromocionSeleccionada) {
        this.fechaEnvioPromocionSeleccionada = fechaEnvioPromocionSeleccionada != null ? new Date(fechaEnvioPromocionSeleccionada.getTime()) : null;
    }

}
