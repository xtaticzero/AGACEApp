package mx.gob.sat.siat.feagace.vista.carga;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

public class CargaPruebasPericialesAbstractManagedBean extends BaseManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected static final String MSN_ERROR_SELECCIONAR_ARCHIVO = "error.debe.seleccionar.archivo";

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    private AgaceOrden ordenSeleccionado;
    private PerfilContribuyenteVO perfil;

    private transient UploadedFile archivoPruebaPericial;
    private List<FecetDocPruebasPericiales> listaDocsPruebasPericiales;
    private FecetPruebasPericiales pruebaPericialNuevo;
    private FecetDocPruebasPericiales docPruebaPericialSeleccionada;

    private transient StreamedContent pruebaPericialSeleccionadaDescarga;

    private AcuseRevisionElectronica acuse;
    private transient StreamedContent acuseFilePruebaPericial;

    private boolean visibleBtnEliminarOrden;
    
    private Date fechaHoy;

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

    public void setVisibleBtnEliminarOrden(boolean visibleBtnEliminarOrden) {
        this.visibleBtnEliminarOrden = visibleBtnEliminarOrden;
    }

    public boolean isVisibleBtnEliminarOrden() {
        return visibleBtnEliminarOrden;
    }

    public UploadedFile getArchivoPruebaPericial() {
        return archivoPruebaPericial;
    }

    public void setArchivoPruebaPericial(UploadedFile archivoPruebaPericial) {
        this.archivoPruebaPericial = archivoPruebaPericial;
    }

    public List<FecetDocPruebasPericiales> getListaDocsPruebasPericiales() {
        return listaDocsPruebasPericiales;
    }

    public void setListaDocsPruebasPericiales(List<FecetDocPruebasPericiales> listaDocsPruebasPericiales) {
        this.listaDocsPruebasPericiales = listaDocsPruebasPericiales;
    }

    public FecetPruebasPericiales getPruebaPericialNuevo() {
        return pruebaPericialNuevo;
    }

    public void setPruebaPericialNuevo(FecetPruebasPericiales pruebaPericialNuevo) {
        this.pruebaPericialNuevo = pruebaPericialNuevo;
    }

    public FecetDocPruebasPericiales getDocPruebaPericialSeleccionada() {
        return docPruebaPericialSeleccionada;
    }

    public void setDocPruebaPericialSeleccionada(FecetDocPruebasPericiales docPruebaPericialSeleccionada) {
        this.docPruebaPericialSeleccionada = docPruebaPericialSeleccionada;
    }

    public StreamedContent getPruebaPericialSeleccionadaDescarga() {
        byte[] archivo = getArchivoTempService().consultaArchivoTemp(getDocPruebaPericialSeleccionada().getIdArchivoTemp(),
                getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.pruebaPericialSeleccionadaDescarga = new DefaultStreamedContent(byteArchivo,
                getDocPruebaPericialSeleccionada().getNombreArchivo(), getDocPruebaPericialSeleccionada().getNombreArchivo());
        return pruebaPericialSeleccionadaDescarga;
    }

    public void setPruebaPericialSeleccionadaDescarga(StreamedContent pruebaPericialSeleccionadaDescarga) {
        this.pruebaPericialSeleccionadaDescarga = pruebaPericialSeleccionadaDescarga;
    }

    public StreamedContent getAcuseFilePruebaPericial() {
        return acuseFilePruebaPericial;
    }

    public void setAcuseFilePruebaPericial(StreamedContent acuseFilePruebaPericial) {
        this.acuseFilePruebaPericial = acuseFilePruebaPericial;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public Date getFechaHoy() {
        return (fechaHoy != null) ? (Date) fechaHoy.clone() : null;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy != null ? new Date(fechaHoy.getTime()) : null;
    }

}
