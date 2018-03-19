/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper;

import java.io.Serializable;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleOrdenStreamedHelper implements Serializable {

    private static final long serialVersionUID = -5067128619212300175L;

    private transient StreamedContent archivoOrdenSeleccionDescarga;
    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaAcuseProrroga;
    private transient StreamedContent archivoSelecciondaDescargaAcuse;
    private transient StreamedContent archivoSelecciondaDescargaAcuseProrroga;
    private transient StreamedContent archivoDescargaDocProrrogaOrdenSeleccionado;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaOficio;
    private transient StreamedContent archivoDescargaOficioAcuse;
    private transient UploadedFile archivoPromocion;
    private transient UploadedFile archivoPruebasAlegatos;
    private transient UploadedFile archivoProrroga;

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaAcuse(StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        return archivoDescargaAcuse;

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

    public void setArchivoOrdenSeleccionDescarga(StreamedContent archivoOrdenSeleccionDescarga) {
        this.archivoOrdenSeleccionDescarga = archivoOrdenSeleccionDescarga;
    }

    public StreamedContent getArchivoOrdenSeleccionDescarga() {
        return archivoOrdenSeleccionDescarga;
    }

    public void setArchivoSelecciondaDescargaAcuse(StreamedContent archivoSelecciondaDescargaAcuse) {
        this.archivoSelecciondaDescargaAcuse = archivoSelecciondaDescargaAcuse;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuse() {
        return archivoSelecciondaDescargaAcuse;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        return archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaOficio(StreamedContent archivoDescargaOficio) {
        this.archivoDescargaOficio = archivoDescargaOficio;
    }

    public StreamedContent getArchivoDescargaOficio() {
        return archivoDescargaOficio;
    }

    public void setArchivoDescargaOficioAcuse(StreamedContent archivoDescargaOficioAcuse) {
        this.archivoDescargaOficioAcuse = archivoDescargaOficioAcuse;
    }

    public StreamedContent getArchivoDescargaOficioAcuse() {
        return archivoDescargaOficioAcuse;
    }

    public void setArchivoDescargaAcuseProrroga(StreamedContent archivoDescargaAcuseProrroga) {
        this.archivoDescargaAcuseProrroga = archivoDescargaAcuseProrroga;
    }

    public StreamedContent getArchivoDescargaAcuseProrroga() {
        return archivoDescargaAcuseProrroga;
    }

    public void setArchivoSelecciondaDescargaAcuseProrroga(StreamedContent archivoSelecciondaDescargaAcuseProrroga) {
        this.archivoSelecciondaDescargaAcuseProrroga = archivoSelecciondaDescargaAcuseProrroga;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuseProrroga() {
        return archivoSelecciondaDescargaAcuseProrroga;
    }

    public void setArchivoDescargaDocProrrogaOrdenSeleccionado(StreamedContent archivoDescargaDocProrrogaOrdenSeleccionado) {
        this.archivoDescargaDocProrrogaOrdenSeleccionado = archivoDescargaDocProrrogaOrdenSeleccionado;
    }

    public StreamedContent getArchivoDescargaDocProrrogaOrdenSeleccionado() {
        return archivoDescargaDocProrrogaOrdenSeleccionado;
    }
}
