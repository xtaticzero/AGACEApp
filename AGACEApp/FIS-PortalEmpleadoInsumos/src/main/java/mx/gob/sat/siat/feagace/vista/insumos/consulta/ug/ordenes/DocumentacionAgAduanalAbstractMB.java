package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocumentacionAgAduanalAbstractMB extends AbstractManagedBean {

    private static final long serialVersionUID = 5972806096377725939L;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;
    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;
    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;
    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;

    private AgaceOrden ordenSeleccionado;
    private ColaboradorVO agenteAduanal;
    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaPromocionSeleccionada;
    private transient StreamedContent archivoDescargaAnexoSeleccionada;
    private transient UploadedFile archivoPromocion;
    private transient UploadedFile archivoPruebasAlegatos;
    private List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanal;
    private FecetAlegatoAgenteAduanal alegatoAgenteAduanalSeleccionado;
    private List<FecetPromocionAgenteAduanal> listaPromocionesCargadas;
    private List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanalCargadas;
    private List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal;
    private FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado;

    private boolean visibleBtnEliminarOrden;
    private boolean mostrarPruebasAlegatos;
    private boolean paginaCargada;
    private boolean noVerEditables;
    private boolean mostrarBtnDocsAgenteAduanal;

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
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

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        archivoDescargaPruebasAlegatos = getDescargaArchivo(
                getAlegatoAgenteAduanalSeleccionado().getRutaArchivo() + getAlegatoAgenteAduanalSeleccionado().getNombreArchivo(),
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo());

        return archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public List<FecetAlegatoAgenteAduanal> getListaAlegatoAgenteAduanal() {
        return listaAlegatoAgenteAduanal;
    }

    public List<FecetPromocionAgenteAduanal> getListaPromocionesCargadas() {
        return listaPromocionesCargadas;
    }

    public void setListaPromocionesCargadas(List<FecetPromocionAgenteAduanal> listaPromocionesCargadas) {
        this.listaPromocionesCargadas = listaPromocionesCargadas;
    }

    public UploadedFile getArchivoPromocion() {
        return archivoPromocion;
    }

    public void setArchivoPromocion(UploadedFile archivoPromocion) {
        this.archivoPromocion = archivoPromocion;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public boolean isVisibleBtnEliminarOrden() {
        return visibleBtnEliminarOrden;
    }

    public void setVisibleBtnEliminarOrden(boolean visibleBtnEliminarOrden) {
        this.visibleBtnEliminarOrden = visibleBtnEliminarOrden;
    }

    public StreamedContent getArchivoDescargaPromocionSeleccionada() {
        byte[] archivo = getArchivoTempService().consultaArchivoTemp(getPromocionAgenteAduanalSeleccionado().getIdArchivoTemp(), getUserProfile().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.archivoDescargaPromocionSeleccionada = new DefaultStreamedContent(byteArchivo, getPromocionAgenteAduanalSeleccionado().getNombreArchivo(),
                getPromocionAgenteAduanalSeleccionado().getNombreArchivo());

        return archivoDescargaPromocionSeleccionada;
    }

    public FecetPromocionAgenteAduanal getPromocionAgenteAduanalSeleccionado() {
        return promocionAgenteAduanalSeleccionado;
    }

    public void setPromocionAgenteAduanalSeleccionado(FecetPromocionAgenteAduanal promocionAgenteAduanalSeleccionado) {
        this.promocionAgenteAduanalSeleccionado = promocionAgenteAduanalSeleccionado;
    }

    public void setArchivoDescargaPromocionSeleccionada(StreamedContent archivoDescargaPromocionSeleccionada) {
        this.archivoDescargaPromocionSeleccionada = archivoDescargaPromocionSeleccionada;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
    }

    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaAnexoSeleccionada() {
        byte[] archivo = getArchivoTempService().consultaArchivoTemp(getAlegatoAgenteAduanalSeleccionado().getIdArchivoTemp(), getUserProfile().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.archivoDescargaAnexoSeleccionada = new DefaultStreamedContent(byteArchivo, getAlegatoAgenteAduanalSeleccionado().getNombreArchivo(),
                getAlegatoAgenteAduanalSeleccionado().getNombreArchivo());
        return archivoDescargaAnexoSeleccionada;

    }

    public void setArchivoDescargaAnexoSeleccionada(StreamedContent archivoDescargaAnexoSeleccionada) {
        this.archivoDescargaAnexoSeleccionada = archivoDescargaAnexoSeleccionada;
    }

    public List<FecetAlegatoAgenteAduanal> getListaAlegatoAgenteAduanalCargadas() {
        return listaAlegatoAgenteAduanalCargadas;
    }

    public void setListaAlegatoAgenteAduanalCargadas(List<FecetAlegatoAgenteAduanal> listaAlegatoAgenteAduanalCargadas) {
        this.listaAlegatoAgenteAduanalCargadas = listaAlegatoAgenteAduanalCargadas;
    }

    public UploadedFile getArchivoPruebasAlegatos() {
        return archivoPruebasAlegatos;
    }

    public void setArchivoPruebasAlegatos(UploadedFile archivoPruebasAlegatos) {
        this.archivoPruebasAlegatos = archivoPruebasAlegatos;
    }

    public ColaboradorVO getAgenteAduanal() {
        return agenteAduanal;
    }

    public void setAgenteAduanal(ColaboradorVO agenteAduanal) {
        this.agenteAduanal = agenteAduanal;
    }

    public boolean isNoVerEditables() {
        return noVerEditables;
    }

    public void setNoVerEditables(boolean noVerEditables) {
        this.noVerEditables = noVerEditables;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public List<FecetPromocionAgenteAduanal> getListaPromocionAgenteAduanal() {
        return listaPromocionAgenteAduanal;
    }

    public void setListaPromocionAgenteAduanal(List<FecetPromocionAgenteAduanal> listaPromocionAgenteAduanal) {
        this.listaPromocionAgenteAduanal = listaPromocionAgenteAduanal;
    }

    public boolean isPaginaCargada() {
        return paginaCargada;
    }

    public void setPaginaCargada(boolean paginaCargada) {
        this.paginaCargada = paginaCargada;
    }

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public boolean isMostrarBtnDocsAgenteAduanal() {
        return mostrarBtnDocsAgenteAduanal;
    }

    public void setMostrarBtnDocsAgenteAduanal(boolean mostrarBtnDocsAgenteAduanal) {
        this.mostrarBtnDocsAgenteAduanal = mostrarBtnDocsAgenteAduanal;
    }

}
