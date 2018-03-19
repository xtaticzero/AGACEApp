/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosStreamedHelper implements Serializable{
    private static final long serialVersionUID = 1433488584511867894L;
    
    private transient UploadedFile archivoPromocionCompulsa;
    private transient UploadedFile archivoPruebasAlegatosCompulsa;
    private transient UploadedFile archivoProrroga;
    private transient StreamedContent archivoDocProrrogaCompulsaDescarga;
    private transient StreamedContent archivoDescargaProrrogaCompulsaAcuse;
    private transient StreamedContent archivoPromocionCompulsaHistoricoDescargaAcuse;
    private transient StreamedContent archivoPruebaCompulsaHistoricoDescarga;
    private transient StreamedContent archivoDescargaHistoricoPromocionCompulsa;
    private transient StreamedContent pruebaCompulsaSeleccionadaDescarga;
    private transient StreamedContent acuseFilePromocionCompulsa;
    private transient StreamedContent promocionCompulsaSeleccionadaDescarga;
    private transient StreamedContent archivoPromocionOficioHistoricoDescargaAcuse;
    
    public void setPromocionCompulsaSeleccionadaDescarga(StreamedContent promocionCompulsaSeleccionadaDescarga) {
        this.promocionCompulsaSeleccionadaDescarga = promocionCompulsaSeleccionadaDescarga;
    }
    
    public void setArchivoDocProrrogaCompulsaDescarga(StreamedContent archivoDocProrrogaCompulsaDescarga) {
        this.archivoDocProrrogaCompulsaDescarga = archivoDocProrrogaCompulsaDescarga;
    }
    
    public void setArchivoPruebaCompulsaHistoricoDescarga(StreamedContent archivoPruebaCompulsaHistoricoDescarga) {
        this.archivoPruebaCompulsaHistoricoDescarga = archivoPruebaCompulsaHistoricoDescarga;
    }
    
    public void setArchivoDescargaProrrogaCompulsaAcuse(StreamedContent archivoDescargaProrrogaCompulsaAcuse) {
        this.archivoDescargaProrrogaCompulsaAcuse = archivoDescargaProrrogaCompulsaAcuse;
    }
    
    public void setArchivoPromocionCompulsaHistoricoDescargaAcuse(StreamedContent archivoPromocionCompulsaHistoricoDescargaAcuse) {
        this.archivoPromocionCompulsaHistoricoDescargaAcuse = archivoPromocionCompulsaHistoricoDescargaAcuse;
    }
    
    public void setArchivoPromocionCompulsa(UploadedFile archivoPromocionCompulsa) {
        this.archivoPromocionCompulsa = archivoPromocionCompulsa;
    }

    public UploadedFile getArchivoPromocionCompulsa() {
        return archivoPromocionCompulsa;
    }

    public void setArchivoPruebasAlegatosCompulsa(UploadedFile archivoPruebasAlegatosCompulsa) {
        this.archivoPruebasAlegatosCompulsa = archivoPruebasAlegatosCompulsa;
    }

    public UploadedFile getArchivoPruebasAlegatosCompulsa() {
        return archivoPruebasAlegatosCompulsa;
    }

    public void setArchivoProrroga(UploadedFile archivoProrroga) {
        this.archivoProrroga = archivoProrroga;
    }

    public UploadedFile getArchivoProrroga() {
        return archivoProrroga;
    }

    public void setAcuseFilePromocionCompulsa(StreamedContent acuseFilePromocionCompulsa) {
        this.acuseFilePromocionCompulsa = acuseFilePromocionCompulsa;
    }

    public StreamedContent getAcuseFilePromocionCompulsa() {
        return acuseFilePromocionCompulsa;
    }

    public void setArchivoDescargaHistoricoPromocionCompulsa(StreamedContent archivoDescargaHistoricoPromocionCompulsa) {
        this.archivoDescargaHistoricoPromocionCompulsa = archivoDescargaHistoricoPromocionCompulsa;
    }
    
    public void setPruebaCompulsaSeleccionadaDescarga(StreamedContent pruebaCompulsaSeleccionadaDescarga) {
        this.pruebaCompulsaSeleccionadaDescarga = pruebaCompulsaSeleccionadaDescarga;
    }

    public StreamedContent getArchivoDocProrrogaCompulsaDescarga() {
        return archivoDocProrrogaCompulsaDescarga;
    }

    public StreamedContent getArchivoDescargaProrrogaCompulsaAcuse() {
        return archivoDescargaProrrogaCompulsaAcuse;
    }

    public StreamedContent getArchivoPromocionCompulsaHistoricoDescargaAcuse() {
        return archivoPromocionCompulsaHistoricoDescargaAcuse;
    }

    public StreamedContent getArchivoPruebaCompulsaHistoricoDescarga() {
        return archivoPruebaCompulsaHistoricoDescarga;
    }

    public StreamedContent getArchivoDescargaHistoricoPromocionCompulsa() {
        return archivoDescargaHistoricoPromocionCompulsa;
    }

    public StreamedContent getPruebaCompulsaSeleccionadaDescarga() {
        return pruebaCompulsaSeleccionadaDescarga;
    }

    public StreamedContent getPromocionCompulsaSeleccionadaDescarga() {
        return promocionCompulsaSeleccionadaDescarga;
    }

    public void setArchivoPromocionOficioHistoricoDescargaAcuse(StreamedContent archivoPromocionOficioHistoricoDescargaAcuse) {
        this.archivoPromocionOficioHistoricoDescargaAcuse = archivoPromocionOficioHistoricoDescargaAcuse;
    }

    public StreamedContent getArchivoPromocionOficioHistoricoDescargaAcuse() {
        return archivoPromocionOficioHistoricoDescargaAcuse;
    }
}
