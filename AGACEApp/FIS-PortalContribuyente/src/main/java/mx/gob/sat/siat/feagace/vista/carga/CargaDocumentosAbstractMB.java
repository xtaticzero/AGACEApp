/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosAttributesHelper;
import mx.gob.sat.siat.feagace.vista.carga.helper.CargaDocumentosDTOHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class CargaDocumentosAbstractMB extends BaseManagedBean {
    
    protected static final String PERFIL_SESSION = "perfil";

    protected static final String MSN_ERROR_ARCHIVO = "error.debe.seleccionar.archivo";

    private String paginaFirmado = "firma";

    private transient UploadedFile archivoPromocion;
    private transient UploadedFile archivoPruebasAlegatos;
    private transient UploadedFile archivoProrroga;
    private transient UploadedFile archivoPromocionOficio;
    private transient UploadedFile archivoPruebasAlegatosOficio;
    private transient StreamedContent promocionSeleccionadaDescarga;
    private transient StreamedContent pruebaSeleccionadaDescarga;
    private transient StreamedContent acuseFilePromocion;
    private transient StreamedContent promocionOficioSeleccionadaDescarga;
    private transient StreamedContent pruebaOficioSeleccionadaDescarga;
    private transient StreamedContent acuseFilePromocionOficio;
    private transient StreamedContent archivoDescargaHistoricoPromocionOficio;
    private transient StreamedContent archivoPromocionOficioHistoricoDescargaAcuse;
    private transient StreamedContent archivoPruebaOficioHistoricoDescarga;
    private transient StreamedContent archivoDescargaProrrogaOficioAcuse;
    private transient StreamedContent archivoDocProrrogaOficioDescarga;
    private transient StreamedContent archivoDescargaProrrogaResolucionOficio;

    private static final long serialVersionUID = -9027256424897498868L;
    
    private CargaDocumentosAttributesHelper cargaDoctosAttributesHelper;
    private CargaDocumentosDTOHelper cargaDoctosDTOHelper;

    @ManagedProperty(value = "#{cargarFirmaPruebasPromoService}")
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;

    @ManagedProperty(value = "#{cargarFirmaProrrogaService}")
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;
    
    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private transient AuditoriaService auditoriaService;

    public void setCargarFirmaPruebasPromoService(CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService) {
        this.cargarFirmaPruebasPromoService = cargarFirmaPruebasPromoService;
    }

    public CargarFirmaPruebasPromoService getCargarFirmaPruebasPromoService() {
        return cargarFirmaPruebasPromoService;
    }

    public void setCargarFirmaProrrogaService(CargarFirmaProrrogaService cargarFirmaProrrogaService) {
        this.cargarFirmaProrrogaService = cargarFirmaProrrogaService;
    }

    public CargarFirmaProrrogaService getCargarFirmaProrrogaService() {
        return cargarFirmaProrrogaService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public CargaDocumentosAttributesHelper getCargaDoctosAttributesHelper() {
        return cargaDoctosAttributesHelper;
    }

    public void setCargaDoctosAttributesHelper(CargaDocumentosAttributesHelper cargaDoctosAttributesHelper) {
        this.cargaDoctosAttributesHelper = cargaDoctosAttributesHelper;
    }

    public CargaDocumentosDTOHelper getCargaDoctosDTOHelper() {
        return cargaDoctosDTOHelper;
    }

    public void setCargaDoctosDTOHelper(CargaDocumentosDTOHelper cargaDoctosDTOHelper) {
        this.cargaDoctosDTOHelper = cargaDoctosDTOHelper;
    }
    
    public void setArchivoPromocion(UploadedFile archivoPromocion) {
        this.archivoPromocion = archivoPromocion;
    }

    public UploadedFile getArchivoPromocion() {
        return archivoPromocion;
    }

    public void setArchivoPruebasAlegatos(UploadedFile archivoPruebasAlegatos) {
        this.archivoPruebasAlegatos = archivoPruebasAlegatos;
    }

    public UploadedFile getArchivoPruebasAlegatos() {
        return archivoPruebasAlegatos;
    }

    public void setArchivoProrroga(UploadedFile archivoProrroga) {
        this.archivoProrroga = archivoProrroga;
    }

    public UploadedFile getArchivoProrroga() {
        return archivoProrroga;
    }

    public void setAcuseFilePromocion(StreamedContent acuseFilePromocion) {
        this.acuseFilePromocion = acuseFilePromocion;
    }

    public StreamedContent getAcuseFilePromocion() {
        return acuseFilePromocion;
    }

    public void setAcuseFilePromocionOficio(StreamedContent acuseFilePromocionOficio) {
        this.acuseFilePromocionOficio = acuseFilePromocionOficio;
    }

    public StreamedContent getAcuseFilePromocionOficio() {
        return acuseFilePromocionOficio;
    }

    public void setArchivoPromocionOficio(UploadedFile archivoPromocionOficio) {
        this.archivoPromocionOficio = archivoPromocionOficio;
    }

    public UploadedFile getArchivoPromocionOficio() {
        return archivoPromocionOficio;
    }

    public void setArchivoPruebasAlegatosOficio(UploadedFile archivoPruebasAlegatosOficio) {
        this.archivoPruebasAlegatosOficio = archivoPruebasAlegatosOficio;
    }

    public UploadedFile getArchivoPruebasAlegatosOficio() {
        return archivoPruebasAlegatosOficio;
    }

    public void setArchivoDescargaHistoricoPromocionOficio(StreamedContent archivoDescargaHistoricoPromocionOficio) {
        this.archivoDescargaHistoricoPromocionOficio = archivoDescargaHistoricoPromocionOficio;
    }

    public StreamedContent getArchivoDescargaHistoricoPromocionOficio() {
        archivoDescargaHistoricoPromocionOficio =
                getDescargaArchivo(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getRutaArchivo(),
                                   getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getNombreArchivo());
        return archivoDescargaHistoricoPromocionOficio;
    }

    public void setArchivoPromocionOficioHistoricoDescargaAcuse(StreamedContent archivoPromocionOficioHistoricoDescargaAcuse) {
        this.archivoPromocionOficioHistoricoDescargaAcuse = archivoPromocionOficioHistoricoDescargaAcuse;
    }

    public StreamedContent getArchivoPromocionOficioHistoricoDescargaAcuse() {
        archivoPromocionOficioHistoricoDescargaAcuse =
                getDescargaArchivo(getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getRutaAcuse(),
                                   getCargaDoctosDTOHelper().getPromocionHistoricoOficioSeleccionada().getNombreAcuse());
        return archivoPromocionOficioHistoricoDescargaAcuse;
    }

    public void setArchivoPruebaOficioHistoricoDescarga(StreamedContent archivoPruebaOficioHistoricoDescarga) {
        this.archivoPruebaOficioHistoricoDescarga = archivoPruebaOficioHistoricoDescarga;
    }

    public StreamedContent getArchivoPruebaOficioHistoricoDescarga() {
        archivoPruebaOficioHistoricoDescarga =
                getDescargaArchivo(getCargaDoctosDTOHelper().getPruebaHistoricoOficioSeleccionada().getRutaArchivo(),
                                   getCargaDoctosDTOHelper().getPruebaHistoricoOficioSeleccionada().getNombreArchivo());
        return archivoPruebaOficioHistoricoDescarga;
    }

    public void setArchivoDescargaProrrogaOficioAcuse(StreamedContent archivoDescargaProrrogaOficioAcuse) {
        this.archivoDescargaProrrogaOficioAcuse = archivoDescargaProrrogaOficioAcuse;
    }

    public StreamedContent getArchivoDescargaProrrogaOficioAcuse() {
        archivoDescargaProrrogaOficioAcuse =
                getDescargaArchivo(getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getRutaAcuse(),
                                   getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getNombreAcuse());
        return archivoDescargaProrrogaOficioAcuse;
    }

    public void setArchivoDocProrrogaOficioDescarga(StreamedContent archivoDocProrrogaOficioDescarga) {
        this.archivoDocProrrogaOficioDescarga = archivoDocProrrogaOficioDescarga;
    }

    public StreamedContent getArchivoDocProrrogaOficioDescarga() {
        archivoDocProrrogaOficioDescarga =
                getDescargaArchivo(getCargaDoctosDTOHelper().getDocProrrogaOficioSeleccionado().getRutaArchivo(),
                                   getCargaDoctosDTOHelper().getDocProrrogaOficioSeleccionado().getNombreArchivo());
        return archivoDocProrrogaOficioDescarga;
    }

    public void setPromocionSeleccionadaDescarga(StreamedContent promocionSeleccionadaDescarga) {
        this.promocionSeleccionadaDescarga = promocionSeleccionadaDescarga;
    }

    public StreamedContent getPromocionSeleccionadaDescarga() {
        byte[] archivo =
            getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPromocionSeleccionada().getIdArchivoTemp(),
                                                        getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.promocionSeleccionadaDescarga =
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPromocionSeleccionada().getNombreArchivo(),
                                           getCargaDoctosDTOHelper().getPromocionSeleccionada().getNombreArchivo());

        return promocionSeleccionadaDescarga;
    }

    public void setPromocionOficioSeleccionadaDescarga(StreamedContent promocionOficioSeleccionadaDescarga) {
        this.promocionOficioSeleccionadaDescarga = promocionOficioSeleccionadaDescarga;
    }

    public StreamedContent getPromocionOficioSeleccionadaDescarga() {
        byte[] archivo =
            getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getIdArchivoTemp(),
                                                        getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);

        this.promocionOficioSeleccionadaDescarga =
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getNombreArchivo(),
                                           getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getNombreArchivo());

        return promocionOficioSeleccionadaDescarga;
    }

    public void setPruebaSeleccionadaDescarga(StreamedContent pruebaSeleccionadaDescarga) {
        this.pruebaSeleccionadaDescarga = pruebaSeleccionadaDescarga;
    }

    public StreamedContent getPruebaSeleccionadaDescarga() {
        byte[] archivo =
            getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPruebaSeleccionada().getIdArchivoTemp(),
                                                        getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.pruebaSeleccionadaDescarga =
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPruebaSeleccionada().getNombreArchivo(),
                                           getCargaDoctosDTOHelper().getPruebaSeleccionada().getNombreArchivo());
        return pruebaSeleccionadaDescarga;
    }

    public void setPruebaOficioSeleccionadaDescarga(StreamedContent pruebaOficioSeleccionadaDescarga) {
        this.pruebaOficioSeleccionadaDescarga = pruebaOficioSeleccionadaDescarga;
    }

    public StreamedContent getPruebaOficioSeleccionadaDescarga() {
        byte[] archivo =
            getArchivoTempService().consultaArchivoTemp(getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getIdArchivoTemp(),
                                                        getCargaDoctosDTOHelper().getPerfil().getRfc());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.pruebaOficioSeleccionadaDescarga =
                new DefaultStreamedContent(byteArchivo, getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getNombreArchivo(),
                                           getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getNombreArchivo());
        return pruebaOficioSeleccionadaDescarga;
    }

    public void setPaginaFirmado(String paginaFirmado) {
        this.paginaFirmado = paginaFirmado;
    }

    public String getPaginaFirmado() {
        return paginaFirmado;
    }

    public void setArchivoDescargaProrrogaResolucionOficio(StreamedContent archivoDescargaProrrogaResolucionOficio) {
        this.archivoDescargaProrrogaResolucionOficio = archivoDescargaProrrogaResolucionOficio;
    }

    public StreamedContent getArchivoDescargaProrrogaResolucionOficio() {
        archivoDescargaProrrogaResolucionOficio =
                getDescargaArchivo(getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getRutaResolucion(),
                                   getCargaDoctosDTOHelper().getProrrogaOficioSeleccionada().getNombreResolucion());
        return archivoDescargaProrrogaResolucionOficio;
    }
    
    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

}
