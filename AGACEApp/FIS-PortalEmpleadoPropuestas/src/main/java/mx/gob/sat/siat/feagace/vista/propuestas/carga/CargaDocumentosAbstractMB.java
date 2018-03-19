/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.carga;

import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaProrrogaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.CargarFirmaPruebasPromoService;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaDocumentosAttributesHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.carga.helper.CargaDocumentosDTOHelper;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class CargaDocumentosAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = -2158768734734510025L;

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

    private CargaDocumentosAttributesHelper cargaDoctosAttributesHelper;
    private CargaDocumentosDTOHelper cargaDoctosDTOHelper;

    @ManagedProperty(value = "#{cargarFirmaPruebasPromoService}")
    private transient CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService;

    @ManagedProperty(value = "#{cargarFirmaProrrogaService}")
    private transient CargarFirmaProrrogaService cargarFirmaProrrogaService;

    public void setCargarFirmaProrrogaService(CargarFirmaProrrogaService cargarFirmaProrrogaService) {
        this.cargarFirmaProrrogaService = cargarFirmaProrrogaService;
    }

    public CargarFirmaProrrogaService getCargarFirmaProrrogaService() {
        return cargarFirmaProrrogaService;
    }

    public void setCargarFirmaPruebasPromoService(CargarFirmaPruebasPromoService cargarFirmaPruebasPromoService) {
        this.cargarFirmaPruebasPromoService = cargarFirmaPruebasPromoService;
    }

    public CargarFirmaPruebasPromoService getCargarFirmaPruebasPromoService() {
        return cargarFirmaPruebasPromoService;
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
        return archivoDescargaHistoricoPromocionOficio;
    }

    public void setArchivoPromocionOficioHistoricoDescargaAcuse(StreamedContent archivoPromocionOficioHistoricoDescargaAcuse) {
        this.archivoPromocionOficioHistoricoDescargaAcuse = archivoPromocionOficioHistoricoDescargaAcuse;
    }

    public StreamedContent getArchivoPromocionOficioHistoricoDescargaAcuse() {
        return archivoPromocionOficioHistoricoDescargaAcuse;
    }

    public void setArchivoPruebaOficioHistoricoDescarga(StreamedContent archivoPruebaOficioHistoricoDescarga) {
        this.archivoPruebaOficioHistoricoDescarga = archivoPruebaOficioHistoricoDescarga;
    }

    public StreamedContent getArchivoPruebaOficioHistoricoDescarga() {
        return archivoPruebaOficioHistoricoDescarga;
    }

    public void setArchivoDescargaProrrogaOficioAcuse(StreamedContent archivoDescargaProrrogaOficioAcuse) {
        this.archivoDescargaProrrogaOficioAcuse = archivoDescargaProrrogaOficioAcuse;
    }

    public StreamedContent getArchivoDescargaProrrogaOficioAcuse() {
        return archivoDescargaProrrogaOficioAcuse;
    }

    public void setArchivoDocProrrogaOficioDescarga(StreamedContent archivoDocProrrogaOficioDescarga) {
        this.archivoDocProrrogaOficioDescarga = archivoDocProrrogaOficioDescarga;
    }

    public StreamedContent getArchivoDocProrrogaOficioDescarga() {
        return archivoDocProrrogaOficioDescarga;
    }

    public void setPromocionSeleccionadaDescarga(StreamedContent promocionSeleccionadaDescarga) {
        this.promocionSeleccionadaDescarga = promocionSeleccionadaDescarga;
    }

    public StreamedContent getPromocionSeleccionadaDescarga() {
        this.promocionSeleccionadaDescarga
                = new DefaultStreamedContent(getCargaDoctosDTOHelper().getPromocionSeleccionada().getArchivo(),
                        getCargaDoctosDTOHelper().getPromocionSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPromocionSeleccionada().getNombreArchivo());

        return promocionSeleccionadaDescarga;
    }

    public void setPromocionOficioSeleccionadaDescarga(StreamedContent promocionOficioSeleccionadaDescarga) {
        this.promocionOficioSeleccionadaDescarga = promocionOficioSeleccionadaDescarga;
    }

    public StreamedContent getPromocionOficioSeleccionadaDescarga() {
        this.promocionOficioSeleccionadaDescarga
                = new DefaultStreamedContent(getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getArchivo(),
                        getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPromocionOficioSeleccionada().getNombreArchivo());

        return promocionOficioSeleccionadaDescarga;
    }

    public void setPruebaSeleccionadaDescarga(StreamedContent pruebaSeleccionadaDescarga) {
        this.pruebaSeleccionadaDescarga = pruebaSeleccionadaDescarga;
    }

    public StreamedContent getPruebaSeleccionadaDescarga() {
        this.pruebaSeleccionadaDescarga
                = new DefaultStreamedContent(getCargaDoctosDTOHelper().getPruebaSeleccionada().getArchivo(),
                        getCargaDoctosDTOHelper().getPruebaSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPruebaSeleccionada().getNombreArchivo());
        return pruebaSeleccionadaDescarga;
    }

    public void setPruebaOficioSeleccionadaDescarga(StreamedContent pruebaOficioSeleccionadaDescarga) {
        this.pruebaOficioSeleccionadaDescarga = pruebaOficioSeleccionadaDescarga;
    }

    public StreamedContent getPruebaOficioSeleccionadaDescarga() {
        this.pruebaOficioSeleccionadaDescarga
                = new DefaultStreamedContent(getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getArchivo(),
                        getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getNombreArchivo(),
                        getCargaDoctosDTOHelper().getPruebaOficioSeleccionada().getNombreArchivo());
        return pruebaOficioSeleccionadaDescarga;
    }

}
