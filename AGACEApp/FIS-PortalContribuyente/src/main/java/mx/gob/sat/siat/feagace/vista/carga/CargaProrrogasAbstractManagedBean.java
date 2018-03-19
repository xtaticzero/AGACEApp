package mx.gob.sat.siat.feagace.vista.carga;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

public class CargaProrrogasAbstractManagedBean extends BaseManagedBean {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected static final String MSN_ERROR_SELECCIONAR_ARCHIVO = "error.debe.seleccionar.archivo";

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    private AgaceOrden ordenSeleccionado;
    private PerfilContribuyenteVO perfil;

    private transient UploadedFile archivoProrroga;
    private List<FecetDocProrrogaOrden> listaDocsProrrogasOrden;
    private FecetProrrogaOrden prorrogaOrdenNuevo;
    private FecetDocProrrogaOrden docProrrogaSeleccionada;

    private transient StreamedContent prorrogaSeleccionadaDescarga;

    private AcuseRevisionElectronica acuse;
    private transient StreamedContent acuseFileProrroga;

    private boolean fromOrden;

    private FecetOficio oficioSeleccionado;
    private List<FecetDocProrrogaOficio> listaDocsProrrogasOficio;
    private transient UploadedFile archivoProrrogaOficio;
    private FecetProrrogaOficio prorrogaOficioNuevo;
    private FecetDocProrrogaOficio docProrrogaOficioSeleccionada;

    private transient StreamedContent prorrogaOficioSeleccionadaDescarga;

    private boolean visibleBtnEliminarOrden;
    private boolean visibleBtnEliminarOficio;
    
    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setAcuse(AcuseRevisionElectronica acuse) {
        this.acuse = acuse;
    }

    public AcuseRevisionElectronica getAcuse() {
        return acuse;
    }

    public void setFromOrden(boolean fromOrden) {
        this.fromOrden = fromOrden;
    }

    public boolean isFromOrden() {
        return fromOrden;
    }

    public void setArchivoProrroga(UploadedFile archivoProrroga) {
        this.archivoProrroga = archivoProrroga;
    }

    public UploadedFile getArchivoProrroga() {
        return archivoProrroga;
    }

    public void setListaDocsProrrogasOrden(List<FecetDocProrrogaOrden> listaDocsProrrogasOrden) {
        this.listaDocsProrrogasOrden = listaDocsProrrogasOrden;
    }

    public List<FecetDocProrrogaOrden> getListaDocsProrrogasOrden() {
        return listaDocsProrrogasOrden;
    }

    public void setProrrogaOrdenNuevo(FecetProrrogaOrden prorrogaOrdenNuevo) {
        this.prorrogaOrdenNuevo = prorrogaOrdenNuevo;
    }

    public FecetProrrogaOrden getProrrogaOrdenNuevo() {
        return prorrogaOrdenNuevo;
    }

    public void setAcuseFileProrroga(StreamedContent acuseFileProrroga) {
        this.acuseFileProrroga = acuseFileProrroga;
    }

    public StreamedContent getAcuseFileProrroga() {
        return acuseFileProrroga;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setListaDocsProrrogasOficio(List<FecetDocProrrogaOficio> listaDocsProrrogasOficio) {
        this.listaDocsProrrogasOficio = listaDocsProrrogasOficio;
    }

    public List<FecetDocProrrogaOficio> getListaDocsProrrogasOficio() {
        return listaDocsProrrogasOficio;
    }

    public void setArchivoProrrogaOficio(UploadedFile archivoProrrogaOficio) {
        this.archivoProrrogaOficio = archivoProrrogaOficio;
    }

    public UploadedFile getArchivoProrrogaOficio() {
        return archivoProrrogaOficio;
    }

    public void setProrrogaOficioNuevo(FecetProrrogaOficio prorrogaOficioNuevo) {
        this.prorrogaOficioNuevo = prorrogaOficioNuevo;
    }

    public FecetProrrogaOficio getProrrogaOficioNuevo() {
        return prorrogaOficioNuevo;
    }

    public void setVisibleBtnEliminarOrden(boolean visibleBtnEliminarOrden) {
        this.visibleBtnEliminarOrden = visibleBtnEliminarOrden;
    }

    public boolean isVisibleBtnEliminarOrden() {
        return visibleBtnEliminarOrden;
    }

    public void setVisibleBtnEliminarOficio(boolean visibleBtnEliminarOficio) {
        this.visibleBtnEliminarOficio = visibleBtnEliminarOficio;
    }

    public boolean isVisibleBtnEliminarOficio() {
        return visibleBtnEliminarOficio;
    }

    public void setProrrogaSeleccionadaDescarga(StreamedContent prorrogaSeleccionadaDescarga) {
        this.prorrogaSeleccionadaDescarga = prorrogaSeleccionadaDescarga;
    }

    public StreamedContent getProrrogaSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getDocProrrogaSeleccionada().getIdArchivoTemp(), getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.prorrogaSeleccionadaDescarga
                = new DefaultStreamedContent(byteArchivo, getDocProrrogaSeleccionada().getNombreArchivo(),
                        getDocProrrogaSeleccionada().getNombreArchivo());
        return prorrogaSeleccionadaDescarga;
    }

    public void setDocProrrogaSeleccionada(FecetDocProrrogaOrden docProrrogaSeleccionada) {
        this.docProrrogaSeleccionada = docProrrogaSeleccionada;
    }

    public FecetDocProrrogaOrden getDocProrrogaSeleccionada() {
        return docProrrogaSeleccionada;
    }

    public void setDocProrrogaOficioSeleccionada(FecetDocProrrogaOficio docProrrogaOficioSeleccionada) {
        this.docProrrogaOficioSeleccionada = docProrrogaOficioSeleccionada;
    }

    public FecetDocProrrogaOficio getDocProrrogaOficioSeleccionada() {
        return docProrrogaOficioSeleccionada;
    }

    public void setProrrogaOficioSeleccionadaDescarga(StreamedContent prorrogaOficioSeleccionadaDescarga) {
        this.prorrogaOficioSeleccionadaDescarga = prorrogaOficioSeleccionadaDescarga;
    }

    public StreamedContent getProrrogaOficioSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getDocProrrogaOficioSeleccionada().getIdArchivoTemp(), getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.prorrogaOficioSeleccionadaDescarga
                = new DefaultStreamedContent(byteArchivo, getDocProrrogaOficioSeleccionada().getNombreArchivo(),
                        getDocProrrogaOficioSeleccionada().getNombreArchivo());
        return prorrogaOficioSeleccionadaDescarga;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

}
