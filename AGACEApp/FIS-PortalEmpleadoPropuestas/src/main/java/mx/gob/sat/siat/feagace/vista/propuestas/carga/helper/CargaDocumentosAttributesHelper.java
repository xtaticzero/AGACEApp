/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.carga.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosAttributesHelper implements Serializable{
    private static final long serialVersionUID = 550667960969215915L;
    
    private boolean mostrarPruebasAlegatos;
    private boolean estadoRegresa;
    private boolean fromOrden;
    private boolean mostrarPruebasAlegatosOficio;

    private String contextoJarFirmante;
    private String rfcLogueado;
    private String rfc12;
    private String numCertificado;
    private String jsonFirmado;
    private String rutaArchivoAcusePromocion;
    private String leyenda;
    private String leyendaDoc;

    private BigDecimal idPromocionOficioSeleccionadaHistorico;
    private BigDecimal idProrrogaOficioSeleccionado;
    private Date fechaEnvioPromocionOficioSeleccionadaHistorico;   
    private Date fechaEnvioProrrogaOficioSeleccionada;
    
    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }
    
    public void setIdProrrogaOficioSeleccionado(BigDecimal idProrrogaOficioSeleccionado) {
        this.idProrrogaOficioSeleccionado = idProrrogaOficioSeleccionado;
    }

    public BigDecimal getIdProrrogaOficioSeleccionado() {
        return idProrrogaOficioSeleccionado;
    }

    public void setFechaEnvioProrrogaOficioSeleccionada(Date fechaEnvioProrrogaOficioSeleccionada) {
        this.fechaEnvioProrrogaOficioSeleccionada
                = (fechaEnvioProrrogaOficioSeleccionada != null) ? (Date) fechaEnvioProrrogaOficioSeleccionada : null;
    }

    public Date getFechaEnvioProrrogaOficioSeleccionada() {
        return (fechaEnvioProrrogaOficioSeleccionada != null) ? (Date) fechaEnvioProrrogaOficioSeleccionada : null;
    }
    
    public void setLeyendaDoc(String leyendaDoc) {
        this.leyendaDoc = leyendaDoc;
    }

    public String getLeyendaDoc() {
        return leyendaDoc;
    }
    
    public void setIdPromocionOficioSeleccionadaHistorico(BigDecimal idPromocionOficioSeleccionadaHistorico) {
        this.idPromocionOficioSeleccionadaHistorico = idPromocionOficioSeleccionadaHistorico;
    }

    public BigDecimal getIdPromocionOficioSeleccionadaHistorico() {
        return idPromocionOficioSeleccionadaHistorico;
    }

    public void setFechaEnvioPromocionOficioSeleccionadaHistorico(Date fechaEnvioPromocionOficioSeleccionadaHistorico) {
        this.fechaEnvioPromocionOficioSeleccionadaHistorico
                = (fechaEnvioPromocionOficioSeleccionadaHistorico != null) ? (Date) fechaEnvioPromocionOficioSeleccionadaHistorico : null;
    }

    public Date getFechaEnvioPromocionOficioSeleccionadaHistorico() {
        return (fechaEnvioPromocionOficioSeleccionadaHistorico != null) ? (Date) fechaEnvioPromocionOficioSeleccionadaHistorico : null;
    }
    
    public void setMostrarPruebasAlegatosOficio(boolean mostrarPruebasAlegatosOficio) {
        this.mostrarPruebasAlegatosOficio = mostrarPruebasAlegatosOficio;
    }

    public boolean isMostrarPruebasAlegatosOficio() {
        return mostrarPruebasAlegatosOficio;
    }
    
    public void setFromOrden(boolean fromOrden) {
        this.fromOrden = fromOrden;
    }

    public boolean isFromOrden() {
        return fromOrden;
    }
    
    public void setRutaArchivoAcusePromocion(String rutaArchivoAcusePromocion) {
        this.rutaArchivoAcusePromocion = rutaArchivoAcusePromocion;
    }

    public String getRutaArchivoAcusePromocion() {
        return rutaArchivoAcusePromocion;
    }
    
    public void setContextoJarFirmante(String contextoJarFirmante) {
        this.contextoJarFirmante = contextoJarFirmante;
    }

    public String getContextoJarFirmante() {
        return contextoJarFirmante;
    }

    public void setRfcLogueado(String rfcLogueado) {
        this.rfcLogueado = rfcLogueado;
    }

    public String getRfcLogueado() {
        return rfcLogueado;
    }

    public void setEstadoRegresa(boolean estadoRegresa) {
        this.estadoRegresa = estadoRegresa;
    }

    public boolean isEstadoRegresa() {
        return estadoRegresa;
    }

    public void setRfc12(String rfc12) {
        this.rfc12 = rfc12;
    }

    public String getRfc12() {
        return rfc12;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setJsonFirmado(String jsonFirmado) {
        this.jsonFirmado = jsonFirmado;
    }

    public String getJsonFirmado() {
        return jsonFirmado;
    }
    
    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
    }
    
}
