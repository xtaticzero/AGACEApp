/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.helper;

import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DoctosOrdenStreamedHelper extends DoctosOrdenUploadFileHelper {

    private static final long serialVersionUID = 3702794871001904691L;

    private transient StreamedContent archivoDescargaPromocion;
    private transient StreamedContent archivoDescargaProrroga;
    private transient StreamedContent archivoDescargaDocumentacionProrroga;
    private transient StreamedContent archivoAnexoSolicitudContribuyente;
    private transient StreamedContent archivoDescargaPruebasAlegatos;
    private transient StreamedContent archivoDescargaAcuse;
    private transient StreamedContent archivoDescargaAcusePromocion;
    private transient StreamedContent archivoSeleccionDescarga;
    private transient StreamedContent archivoOficioSeleccionDescarga;
    private transient StreamedContent oficioSeleccionDescarga;
    private transient StreamedContent anexoSeleccionDescarga;
    private transient StreamedContent papelTrabajoDescarga;
    private transient StreamedContent archivoDescargaDocumentacionPruebaPericial;
    private transient StreamedContent archivoDescargaDocumentacionSolicitudContr;

    public StreamedContent getArchivoDescargaDocumentacionPruebaPericial() {
        return archivoDescargaDocumentacionPruebaPericial;
    }

    public void setArchivoDescargaDocumentacionPruebaPericial(StreamedContent archivoDescargaDocumentacionPruebaPericial) {
        this.archivoDescargaDocumentacionPruebaPericial = archivoDescargaDocumentacionPruebaPericial;
    }

    public StreamedContent getArchivoDescargaDocumentacionSolicitudContr() {
        return archivoDescargaDocumentacionSolicitudContr;
    }

    public void setArchivoDescargaDocumentacionSolicitudContr(StreamedContent archivoDescargaDocumentacionSolicitudContr) {
        this.archivoDescargaDocumentacionSolicitudContr = archivoDescargaDocumentacionSolicitudContr;
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        return archivoOficioSeleccionDescarga;
    }

    public void setArchivoOficioSeleccionDescarga(StreamedContent archivoOficioSeleccionDescarga) {
        this.archivoOficioSeleccionDescarga = archivoOficioSeleccionDescarga;
    }

    public void setAnexoSeleccionDescarga(StreamedContent anexoSeleccionDescarga) {
        this.anexoSeleccionDescarga = anexoSeleccionDescarga;
    }

    public void setArchivoSeleccionDescarga(final StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public void setOficioSeleccionDescarga(final StreamedContent oficioSeleccionDescarga) {
        this.oficioSeleccionDescarga = oficioSeleccionDescarga;
    }

    public void setArchivoDescargaDocumentacionProrroga(StreamedContent archivoDescargaDocumentacionProrroga) {
        this.archivoDescargaDocumentacionProrroga = archivoDescargaDocumentacionProrroga;
    }

    public void setArchivoDescargaProrroga(StreamedContent archivoDescargaProrroga) {
        this.archivoDescargaProrroga = archivoDescargaProrroga;
    }

    public void setArchivoDescargaPruebasAlegatos(StreamedContent archivoDescargaPruebasAlegatos) {
        this.archivoDescargaPruebasAlegatos = archivoDescargaPruebasAlegatos;
    }

    public void setArchivoDescargaAcusePromocion(final StreamedContent archivoDescargaAcusePromocion) {
        this.archivoDescargaAcusePromocion = archivoDescargaAcusePromocion;
    }

    public void setArchivoDescargaAcuse(final StreamedContent archivoDescargaAcuse) {
        this.archivoDescargaAcuse = archivoDescargaAcuse;
    }

    public void setArchivoDescargaPromocion(StreamedContent archivoDescargaPromocion) {
        this.archivoDescargaPromocion = archivoDescargaPromocion;
    }

    public StreamedContent getArchivoDescargaPromocion() {
        return archivoDescargaPromocion;
    }

    public StreamedContent getArchivoDescargaProrroga() {
        return archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaDocumentacionProrroga() {
        return archivoDescargaDocumentacionProrroga;
    }

    public StreamedContent getArchivoDescargaPruebasAlegatos() {
        return archivoDescargaPruebasAlegatos;
    }

    public StreamedContent getArchivoDescargaAcuse() {
        return archivoDescargaAcuse;
    }

    public StreamedContent getArchivoDescargaAcusePromocion() {
        return archivoDescargaAcusePromocion;
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        return archivoSeleccionDescarga;
    }

    public StreamedContent getOficioSeleccionDescarga() {
        return oficioSeleccionDescarga;
    }

    public StreamedContent getAnexoSeleccionDescarga() {
        return anexoSeleccionDescarga;
    }

    public StreamedContent getPapelTrabajoDescarga() {
        return papelTrabajoDescarga;
    }

    public void setPapelTrabajoDescarga(StreamedContent papelTrabajoDescarga) {
        this.papelTrabajoDescarga = papelTrabajoDescarga;
    }

    public StreamedContent getArchivoAnexoSolicitudContribuyente() {
        return archivoAnexoSolicitudContribuyente;
    }

    public void setArchivoAnexoSolicitudContribuyente(StreamedContent archivoAnexoSolicitudContribuyente) {
        this.archivoAnexoSolicitudContribuyente = archivoAnexoSolicitudContribuyente;
    }

}
