/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class AsociarColaboradoresFileStreamedHelper extends AsociarColaboradoresFileStreamedAbstractHelper implements Serializable {

    private static final long serialVersionUID = -8709789763832818457L;

    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaAcuseProrroga;
    private transient StreamedContent archivoSelecciondaDescargaAcuse;
    private transient StreamedContent archivoSelecciondaDescargaAcuseProrroga;
    private transient StreamedContent archivoDescargaAnexoOficio;
    private transient StreamedContent archivoDescargaDocProrrogaOrdenSeleccionado;
    private transient StreamedContent archivoDescargaDocumentacion;
    private transient StreamedContent archivoOrdenSeleccionDescarga;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoSelecciondaDescargaAcusePruebaPericial;
    private transient StreamedContent archivoDescargaAcusePruebasPericiales;
    private transient StreamedContent archivoDescargaDocPruebaPericialSeleccionado;

    public StreamedContent getArchivoDescargaDocPruebaPericialSeleccionado() {
        return archivoDescargaDocPruebaPericialSeleccionado;
    }

    public void setArchivoDescargaDocPruebaPericialSeleccionado(StreamedContent archivoDescargaDocPruebaPericialSeleccionado) {
        this.archivoDescargaDocPruebaPericialSeleccionado = archivoDescargaDocPruebaPericialSeleccionado;
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        return archivoOficioSeleccionDescarga;
    }

    public void setArchivoOficioSeleccionDescarga(StreamedContent archivoOficioSeleccionDescarga) {
        this.archivoOficioSeleccionDescarga = archivoOficioSeleccionDescarga;
    }

    private transient StreamedContent archivoOficioSeleccionDescarga;

    public StreamedContent getArchivoDescargaAcusePruebasPericiales() {
        return archivoDescargaAcusePruebasPericiales;
    }

    public void setArchivoDescargaAcusePruebasPericiales(StreamedContent archivoDescargaAcusePruebasPericiales) {
        this.archivoDescargaAcusePruebasPericiales = archivoDescargaAcusePruebasPericiales;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcusePruebaPericial() {
        return archivoSelecciondaDescargaAcusePruebaPericial;
    }

    public void setArchivoSelecciondaDescargaAcusePruebaPericial(StreamedContent archivoSelecciondaDescargaAcusePruebaPericial) {
        this.archivoSelecciondaDescargaAcusePruebaPericial = archivoSelecciondaDescargaAcusePruebaPericial;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        return archivoDescargaPromocion;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        return archivoDescargaAcuse;
    }

    public void setArchivoDescargaAcuse(StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public StreamedContent getArchivoDescargaAcuseProrroga() {
        return archivoDescargaAcuseProrroga;
    }

    public void setArchivoDescargaAcuseProrroga(StreamedContent archivoDescargaAcuseProrroga) {
        this.archivoDescargaAcuseProrroga = archivoDescargaAcuseProrroga;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuse() {
        return archivoSelecciondaDescargaAcuse;
    }

    public void setArchivoSelecciondaDescargaAcuse(StreamedContent archivoSelecciondaDescargaAcuse) {
        this.archivoSelecciondaDescargaAcuse = archivoSelecciondaDescargaAcuse;
    }

    public StreamedContent getArchivoSelecciondaDescargaAcuseProrroga() {
        return archivoSelecciondaDescargaAcuseProrroga;
    }

    public void setArchivoSelecciondaDescargaAcuseProrroga(StreamedContent archivoSelecciondaDescargaAcuseProrroga) {
        this.archivoSelecciondaDescargaAcuseProrroga = archivoSelecciondaDescargaAcuseProrroga;
    }

    public StreamedContent getArchivoDescargaAnexoOficio() {
        return archivoDescargaAnexoOficio;
    }

    public void setArchivoDescargaAnexoOficio(StreamedContent archivoDescargaAnexoOficio) {
        this.archivoDescargaAnexoOficio = archivoDescargaAnexoOficio;
    }

    public StreamedContent getArchivoDescargaDocProrrogaOrdenSeleccionado() {
        return archivoDescargaDocProrrogaOrdenSeleccionado;
    }

    public void setArchivoDescargaDocProrrogaOrdenSeleccionado(StreamedContent archivoDescargaDocProrrogaOrdenSeleccionado) {
        this.archivoDescargaDocProrrogaOrdenSeleccionado = archivoDescargaDocProrrogaOrdenSeleccionado;
    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        return archivoDescargaDocumentacion;
    }

    public void setArchivoDescargaDocumentacion(StreamedContent archivoDescargaDocumentacion) {
        this.archivoDescargaDocumentacion = archivoDescargaDocumentacion;
    }

    public StreamedContent getArchivoOrdenSeleccionDescarga() {
        return archivoOrdenSeleccionDescarga;
    }

    public void setArchivoOrdenSeleccionDescarga(StreamedContent archivoOrdenSeleccionDescarga) {
        this.archivoOrdenSeleccionDescarga = archivoOrdenSeleccionDescarga;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        return archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

}
