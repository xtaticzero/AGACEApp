/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.InputStream;
import java.io.Serializable;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultarPropuestasAsignadasStringHelper implements Serializable {

    private static final long serialVersionUID = 3522688649127996860L;

    private String labelRegistro;
    private String labelHeaderHistorico;
    private String descripcionTransferir;
    private String descripcionRetroalimentar;
    private String nombreArchivoDescarga;
    private String tipoColaborador;
    private String mensajeNoMediosContacto;
    private String mensajeNoEnvioCorreo;
    private String rfcRLLista;
    private String mensajeNoColaboradores;
    private String observaciones;
    private String labelFechaComite;
    private String encabezadoPropuestas;

    private transient InputStream archivoDescargableIS;
    private StreamedContent documentoDescarga;
    private transient UploadedFile archivoRechazo;
    private transient UploadedFile archivoTransferir;
    private transient UploadedFile archivoRetroalimentar;
    private transient UploadedFile archivoInfoComplementaria;
    private transient UploadedFile archivoCancelar;
    private transient UploadedFile papelTrabajo;
    private transient UploadedFile archivoOficio;

    public ConsultarPropuestasAsignadasStringHelper() {
        this.descripcionTransferir = null;
        this.descripcionRetroalimentar = null;
        this.archivoRechazo = null;
        this.archivoTransferir = null;
        this.archivoRetroalimentar = null;
        this.archivoInfoComplementaria = null;
        this.documentoDescarga = null;
        this.labelHeaderHistorico = null;
        this.rfcRLLista = null;
        this.tipoColaborador = null;
        this.mensajeNoMediosContacto = null;
        this.mensajeNoEnvioCorreo = null;
        this.observaciones = null;
        this.archivoCancelar = null;
        this.papelTrabajo = null;
        this.archivoOficio = null;
    }

    public void setArchivoRechazo(UploadedFile archivoRechazo) {
        this.archivoRechazo = archivoRechazo;
    }

    public UploadedFile getArchivoRechazo() {
        return archivoRechazo;
    }

    public void setArchivoTransferir(UploadedFile archivoTransferir) {
        this.archivoTransferir = archivoTransferir;
    }

    public UploadedFile getArchivoTransferir() {
        return archivoTransferir;
    }

    public void setDescripcionTransferir(String descripcionTransferir) {
        this.descripcionTransferir = descripcionTransferir;
    }

    public String getDescripcionTransferir() {
        return descripcionTransferir;
    }

    public void setArchivoRetroalimentar(UploadedFile archivoRetroalimentar) {
        this.archivoRetroalimentar = archivoRetroalimentar;
    }

    public UploadedFile getArchivoRetroalimentar() {
        return archivoRetroalimentar;
    }

    public void setDescripcionRetroalimentar(String descripcionRetroalimentar) {
        this.descripcionRetroalimentar = descripcionRetroalimentar;
    }

    public String getDescripcionRetroalimentar() {
        return descripcionRetroalimentar;
    }

    public void setArchivoInfoComplementaria(UploadedFile archivoInfoComplementaria) {
        this.archivoInfoComplementaria = archivoInfoComplementaria;
    }

    public UploadedFile getArchivoInfoComplementaria() {
        return archivoInfoComplementaria;
    }

    public void setLabelRegistro(String labelRegistro) {
        this.labelRegistro = labelRegistro;
    }

    public String getLabelRegistro() {
        return labelRegistro;
    }

    public void setLabelHeaderHistorico(String labelHeaderHistorico) {
        this.labelHeaderHistorico = labelHeaderHistorico;
    }

    public String getLabelHeaderHistorico() {
        return labelHeaderHistorico;
    }

    public void setArchivoDescargableIS(InputStream archivoDescargableIS) {
        this.archivoDescargableIS = archivoDescargableIS;
    }

    public InputStream getArchivoDescargableIS() {
        return archivoDescargableIS;
    }

    public void setNombreArchivoDescarga(String nombreArchivoDescarga) {
        this.nombreArchivoDescarga = nombreArchivoDescarga;
    }

    public String getNombreArchivoDescarga() {
        return nombreArchivoDescarga;
    }

    public void setRfcRLLista(String rfcRLLista) {
        this.rfcRLLista = rfcRLLista;
    }

    public String getRfcRLLista() {
        return rfcRLLista;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setMensajeNoMediosContacto(String mensajeNoMediosContacto) {
        this.mensajeNoMediosContacto = mensajeNoMediosContacto;
    }

    public String getMensajeNoMediosContacto() {
        return mensajeNoMediosContacto;
    }

    public void setMensajeNoEnvioCorreo(String mensajeNoEnvioCorreo) {
        this.mensajeNoEnvioCorreo = mensajeNoEnvioCorreo;
    }

    public String getMensajeNoEnvioCorreo() {
        return mensajeNoEnvioCorreo;
    }

    public StreamedContent getDocumentoDescarga() {
        return documentoDescarga;
    }

    public void setDocumentoDescarga(StreamedContent documentoDescarga) {
        this.documentoDescarga = documentoDescarga;
    }

    public String getMensajeNoColaboradores() {
        return mensajeNoColaboradores;
    }

    public void setMensajeNoColaboradores(String mensajeNoColaboradores) {
        this.mensajeNoColaboradores = mensajeNoColaboradores;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public UploadedFile getArchivoCancelar() {
        return archivoCancelar;
    }

    public void setArchivoCancelar(UploadedFile archivoCancelar) {
        this.archivoCancelar = archivoCancelar;
    }

    public UploadedFile getPapelTrabajo() {
        return papelTrabajo;
    }

    public void setPapelTrabajo(UploadedFile papelTrabajo) {
        this.papelTrabajo = papelTrabajo;
    }

    public String getLabelFechaComite() {
        return labelFechaComite;
    }

    public void setLabelFechaComite(String labelFechaComite) {
        this.labelFechaComite = labelFechaComite;
    }

    public UploadedFile getArchivoOficio() {
        return archivoOficio;
    }

    public void setArchivoOficio(UploadedFile archivoOficio) {
        this.archivoOficio = archivoOficio;
    }

    public String getEncabezadoPropuestas() {
        return encabezadoPropuestas;
    }

    public void setEncabezadoPropuestas(String encabezadoPropuestas) {
        this.encabezadoPropuestas = encabezadoPropuestas;
    }
}
