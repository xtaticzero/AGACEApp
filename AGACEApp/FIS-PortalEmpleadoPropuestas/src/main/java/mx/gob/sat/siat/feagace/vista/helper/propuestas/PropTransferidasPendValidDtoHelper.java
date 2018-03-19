/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.propuestas;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropTransferidasPendValidDtoHelper implements Serializable {

    private static final long serialVersionUID = -5423227553578944490L;

    private String rfc;
    private String idRegistro;
    private String idPropuesta;
    private String idTransferencia;
    private FecetContribuyente contribuyente;
    private FecetPropuesta propuesta;
    private FecetTransferencia transferencia;
    private FecetDocExpediente documentoSeleccionado;
    private FecetTransferencia documentoSeleccionadoTransfer;
    private StreamedContent documentoSeleccionDescarga;
    private StreamedContent documentoSeleccionDescargaTransfer;
    private FecetPropuesta propuestaNueva;
    private Date fechaActual;
    private transient UploadedFile archivoCarga;
    private FecetRechazoPropuesta documentoSeleccionadoRechazo;
    private String motivoRechazo;
    private Calendar calendario = Calendar.getInstance();
    private boolean isTransferible;
    private boolean tipoPropuesta;

    public Calendar getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
    }

    public void setIsTransferible(boolean isTransferible) {
        this.isTransferible = isTransferible;
    }

    public boolean isIsTransferible() {
        return isTransferible;
    }

    public void setDocumentoSeleccionadoRechazo(FecetRechazoPropuesta documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public FecetRechazoPropuesta getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setTransferencia(FecetTransferencia transferencia) {
        this.transferencia = transferencia;
    }

    public FecetTransferencia getTransferencia() {
        return transferencia;
    }

    public void setPropuestaNueva(FecetPropuesta propuestaNueva) {
        this.propuestaNueva = propuestaNueva;
    }

    public FecetPropuesta getPropuestaNueva() {
        return propuestaNueva;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setDocumentoSeleccionadoTransfer(FecetTransferencia documentoSeleccionadoTransfer) {
        this.documentoSeleccionadoTransfer = documentoSeleccionadoTransfer;
    }

    public FecetTransferencia getDocumentoSeleccionadoTransfer() {
        return documentoSeleccionadoTransfer;
    }

    public void setDocumentoSeleccionDescargaTransfer(StreamedContent documentoSeleccionDescargaTransfer) {
        this.documentoSeleccionDescargaTransfer = documentoSeleccionDescargaTransfer;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        return documentoSeleccionDescarga;
    }

    public void setIdTransferencia(String idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public String getIdTransferencia() {
        return idTransferencia;
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionDescarga(StreamedContent documentoSeleccionDescarga) {
        this.documentoSeleccionDescarga = documentoSeleccionDescarga;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public StreamedContent getDocumentoSeleccionDescargaTransfer() {
        return documentoSeleccionDescargaTransfer;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

}
