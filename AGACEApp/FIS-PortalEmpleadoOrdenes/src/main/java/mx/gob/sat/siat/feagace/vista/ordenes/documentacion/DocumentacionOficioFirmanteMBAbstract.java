package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.DocumentacionOficioFirmanteService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

public abstract class DocumentacionOficioFirmanteMBAbstract extends DocumentacionFirmanteMBAbstract {

    private static final long serialVersionUID = 1345675476453L;

    private FecetPromocionOficio promocionOficioSeleccionado;
    private FecetAlegatoOficio alegatoOficioSeleccionado;
    private FecetProrrogaOficio prorrogaOficioSeleccionada;    
    private FecetDocProrrogaOficio docProrrogaOficio;
    private FecetAnexosProrrogaOficio anexosProrrogaOficioSeleccionada;
    
    private transient StreamedContent descargaArchivoAlegatoOficio;
    private transient StreamedContent descargaArchivoAcuseSello;
    private transient StreamedContent descargaArchivoAcuseSelloPromocion;
    private transient StreamedContent descargaArchivoPromocion;
    private transient StreamedContent descargaDocContribuyenteProrroga;
    private transient StreamedContent descargaAnexoResolucionProrroga;
    private transient StreamedContent descargaDocumentoResolucionProrroga;
    
    @ManagedProperty(value = "#{documentacionOficioFirmanteService}")
    private transient DocumentacionOficioFirmanteService documentacionOficioFirmanteService;
   

    private FecetDocProrrogaOficio fecetDocProrrogaOficio;
    
    private List<FecetPromocionOficio> listaPromocionesOficio;
    private List<FecetAlegatoOficio> listAlegatosOficio;
    private List<FecetProrrogaOficio> listaProrrogasOficioPendientes;
    private List<FecetProrrogaOficio> listaProrrogasOficioFirmadas;
    private List<FecetAnexosProrrogaOficio> listaAnexosOficioProrroga;
    private List<FecetDocProrrogaOficio> listaDocumentacionProrrogaOficio;
    private List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio;
    
    private boolean renderBtnFirmaProrroga;
    private boolean renderBtnFirmaOficio;
    private boolean firmadoProrroga = false;
    private String motivoRechazo;

    protected static final String FIRMA_PRORROGA_APPLET = "appletFirmadoOficio?faces-redirect=true";
    protected static final String RETURN_INDEX_OFICIO = "documentacionOficio";
    protected static final String PARAM_DESCRIPCION_RECHAZO_PRORROGA = "documentacionOficioFirmanteMB:textAreaRechazoProrroga";
    
    private BigDecimal idPromocionOficioSeleccionada;
    private Date fechaEnvioPromocionOficioSeleccionada;
    
    private FecetProrrogaOficio prorrogaOficioFirmadaSeleccionada;
    
    private DocumentoVO docEliminarSeleccionado;

   

    public FecetPromocionOficio getPromocionOficioSeleccionado() {
        return promocionOficioSeleccionado;
    }

    public void setPromocionOficioSeleccionado(FecetPromocionOficio promocionOficioSeleccionado) {
        this.promocionOficioSeleccionado = promocionOficioSeleccionado;
    }

    public FecetAlegatoOficio getAlegatoOficioSeleccionado() {
        return alegatoOficioSeleccionado;
    }

    public void setAlegatoOficioSeleccionado(FecetAlegatoOficio alegatoOficioSeleccionado) {
        this.alegatoOficioSeleccionado = alegatoOficioSeleccionado;
    }

    public FecetProrrogaOficio getProrrogaOficioSeleccionada() {
        return prorrogaOficioSeleccionada;
    }

    public void setProrrogaOficioSeleccionada(FecetProrrogaOficio prorrogaOficioSeleccionada) {
        this.prorrogaOficioSeleccionada = prorrogaOficioSeleccionada;
    }

    /**
     * Descarga de documentos
     */
    public StreamedContent getDescargaArchivoPromocion() {
        descargaArchivoPromocion = getDescargaArchivo(promocionOficioSeleccionado.getRutaArchivo(), promocionOficioSeleccionado.getNombreArchivo());
        return descargaArchivoPromocion;
    }
    
    public StreamedContent getDescargaArchivoAcuseSelloPromocion() {
        descargaArchivoAcuseSelloPromocion = getDescargaArchivo(promocionOficioSeleccionado.getRutaAcuse(), Constantes.NOMBRE_ACUSE_RECIBO);
        return descargaArchivoAcuseSelloPromocion;
    }

    public StreamedContent getDescargaArchivoAcuseSello() {
        descargaArchivoAcuseSello = getDescargaArchivo(prorrogaOficioSeleccionada.getRutaAcuse(), Constantes.NOMBRE_ACUSE_RECIBO);
        return descargaArchivoAcuseSello;
    }

    public StreamedContent getDescargaArchivoAlegatoOficio() {
        descargaArchivoAlegatoOficio = getDescargaArchivo(alegatoOficioSeleccionado.getRutaArchivo(), alegatoOficioSeleccionado.getNombreArchivo());
        return descargaArchivoAlegatoOficio;
    }
    
    public StreamedContent getDescargaDocContribuyenteProrroga() {
        descargaDocContribuyenteProrroga = getDescargaArchivo(docProrrogaOficio.getRutaArchivo(), docProrrogaOficio.getNombreArchivo());
        return descargaDocContribuyenteProrroga;
    }
    
    public StreamedContent getDescargaAnexoResolucionProrroga() {        
        descargaAnexoResolucionProrroga = getDescargaArchivo(anexosProrrogaOficioSeleccionada.getRutaArchivo(), anexosProrrogaOficioSeleccionada.getNombreArchivo());
        return descargaAnexoResolucionProrroga;
    }
    
    public StreamedContent getDescargaDocumentoResolucionProrroga() {
        descargaDocumentoResolucionProrroga = getDescargaArchivo(prorrogaOficioSeleccionada.getRutaResolucion(), prorrogaOficioSeleccionada.getNombreResolucion());
        return descargaDocumentoResolucionProrroga;
    }

    
    /**
     * Setter
     **/

    public void setDescargaArchivoAcuseSello(StreamedContent descargaArchivoAcuseSello) {
        this.descargaArchivoAcuseSello = descargaArchivoAcuseSello;
    }

    public void setDescargaArchivoPromocion(StreamedContent descargaArchivoPromocion) {
        this.descargaArchivoPromocion = descargaArchivoPromocion;
    }

    public void setDescargaArchivoAlegatoOficio(StreamedContent descargaArchivoAlegatoOficio) {
        this.descargaArchivoAlegatoOficio = descargaArchivoAlegatoOficio;
    }


    public void setDescargaDocContribuyenteProrroga(StreamedContent descargaDocContribuyenteProrroga) {
        this.descargaDocContribuyenteProrroga = descargaDocContribuyenteProrroga;
    }

    public FecetDocProrrogaOficio getDocProrrogaOficio() {
        return docProrrogaOficio;
    }

    public void setDocProrrogaOficio(FecetDocProrrogaOficio docProrrogaOficio) {
        this.docProrrogaOficio = docProrrogaOficio;
    }

    public FecetAnexosProrrogaOficio getAnexosProrrogaOficioSeleccionada() {
        return anexosProrrogaOficioSeleccionada;
    }

    public void setAnexosProrrogaOficioSeleccionada(FecetAnexosProrrogaOficio anexosProrrogaOficioSeleccionada) {
        this.anexosProrrogaOficioSeleccionada = anexosProrrogaOficioSeleccionada;
    }

    public void setDescargaAnexoResolucionProrroga(StreamedContent descargaAnexoResolucionProrroga) {
        this.descargaAnexoResolucionProrroga = descargaAnexoResolucionProrroga;
    }

    public void setDescargaDocumentoResolucionProrroga(StreamedContent descargaDocumentoResolucionProrroga) {
        this.descargaDocumentoResolucionProrroga = descargaDocumentoResolucionProrroga;
    }

    public void setDescargaArchivoAcuseSelloPromocion(StreamedContent descargaArchivoAcuseSelloPromocion) {
        this.descargaArchivoAcuseSelloPromocion = descargaArchivoAcuseSelloPromocion;
    }
    
    /**
     * Getters and Setters Prorrogas
     */
    public List<FecetProrrogaOficio> getListaProrrogasOficioPendientes() {
        return listaProrrogasOficioPendientes;
    }

    public void setListaProrrogasOficioPendientes(List<FecetProrrogaOficio> listaProrrogasOficioPendientes) {
        this.listaProrrogasOficioPendientes = listaProrrogasOficioPendientes;
    }

    public List<FecetProrrogaOficio> getListaProrrogasOficioFirmadas() {
        return listaProrrogasOficioFirmadas;
    }

    public void setListaProrrogasOficioFirmadas(List<FecetProrrogaOficio> listaProrrogasOficioFirmadas) {
        this.listaProrrogasOficioFirmadas = listaProrrogasOficioFirmadas;
    }

    public List<FecetAnexosProrrogaOficio> getListaAnexosOficioProrroga() {
        return listaAnexosOficioProrroga;
    }

    public void setListaAnexosOficioProrroga(List<FecetAnexosProrrogaOficio> listaAnexosOficioProrroga) {
        this.listaAnexosOficioProrroga = listaAnexosOficioProrroga;
    }

    public List<FecetDocProrrogaOficio> getListaDocumentacionProrrogaOficio() {
        return listaDocumentacionProrrogaOficio;
    }

    public void setListaDocumentacionProrrogaOficio(List<FecetDocProrrogaOficio> listaDocumentacionProrrogaOficio) {
        this.listaDocumentacionProrrogaOficio = listaDocumentacionProrrogaOficio;
    }

    public boolean isFirmadoProrroga() {
        return firmadoProrroga;
    }

    public void setFirmadoProrroga(boolean firmadoProrroga) {
        this.firmadoProrroga = firmadoProrroga;
    }

    public DocumentacionOficioFirmanteService getDocumentacionOficioFirmanteService() {
        return documentacionOficioFirmanteService;
    }

    public void setDocumentacionOficioFirmanteService(DocumentacionOficioFirmanteService documentacionOficioFirmanteService) {
        this.documentacionOficioFirmanteService = documentacionOficioFirmanteService;
    }

    

    public boolean isRenderBtnFirmaProrroga() {
        return renderBtnFirmaProrroga;
    }

    public void setRenderBtnFirmaProrroga(boolean renderBtnFirmaProrroga) {
        this.renderBtnFirmaProrroga = renderBtnFirmaProrroga;
    }

    public boolean isRenderBtnFirmaOficio() {
        return renderBtnFirmaOficio;
    }

    public void setRenderBtnFirmaOficio(boolean renderBtnFirmaOficio) {
        this.renderBtnFirmaOficio = renderBtnFirmaOficio;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public FecetDocProrrogaOficio getFecetDocProrrogaOficio() {
        return fecetDocProrrogaOficio;
    }

    public void setFecetDocProrrogaOficio(FecetDocProrrogaOficio fecetDocProrrogaOficio) {
        this.fecetDocProrrogaOficio = fecetDocProrrogaOficio;
    }

    public List<FecetAnexosProrrogaOficio> getListaAnexosProrrogaOficio() {
        return listaAnexosProrrogaOficio;
    }

    public void setListaAnexosProrrogaOficio(List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio) {
        this.listaAnexosProrrogaOficio = listaAnexosProrrogaOficio;
    }

    public List<FecetAlegatoOficio> getListAlegatosOficio() {
        return listAlegatosOficio;
    }

    public void setListAlegatosOficio(List<FecetAlegatoOficio> listAlegatosOficio) {
        this.listAlegatosOficio = listAlegatosOficio;
    }

    public List<FecetPromocionOficio> getListaPromocionesOficio() {
        return listaPromocionesOficio;
    }

    public void setListaPromocionesOficio(List<FecetPromocionOficio> listaPromocionesOficio) {
        this.listaPromocionesOficio = listaPromocionesOficio;
    }

    public BigDecimal getIdPromocionOficioSeleccionada() {
        return idPromocionOficioSeleccionada;
    }

    public void setIdPromocionOficioSeleccionada(BigDecimal idPromocionOficioSeleccionada) {
        this.idPromocionOficioSeleccionada = idPromocionOficioSeleccionada;
    }

    public Date getFechaEnvioPromocionOficioSeleccionada() {
        return (fechaEnvioPromocionOficioSeleccionada != null) ? (Date) fechaEnvioPromocionOficioSeleccionada.clone() : null;
    }

    public void setFechaEnvioPromocionOficioSeleccionada(Date fechaEnvioPromocionOficioSeleccionada) {
        this.fechaEnvioPromocionOficioSeleccionada = fechaEnvioPromocionOficioSeleccionada != null ? new Date(fechaEnvioPromocionOficioSeleccionada.getTime()) : null;
    }

    public FecetProrrogaOficio getProrrogaOficioFirmadaSeleccionada() {
        return prorrogaOficioFirmadaSeleccionada;
    }

    public void setProrrogaOficioFirmadaSeleccionada(FecetProrrogaOficio prorrogaOficioFirmadaSeleccionada) {
        this.prorrogaOficioFirmadaSeleccionada = prorrogaOficioFirmadaSeleccionada;
    }

    public DocumentoVO getDocEliminarSeleccionado() {
        return docEliminarSeleccionado;
    }

    public void setDocEliminarSeleccionado(DocumentoVO docEliminarSeleccionado) {
        this.docEliminarSeleccionado = docEliminarSeleccionado;
    }

    
}
