/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosAttributesHelper implements Serializable {

    private static final long serialVersionUID = 4639001580751307049L;

    private boolean fromOrden;
    private boolean mostrarPruebasAlegatos;
    private boolean mostrarPruebasAlegatosOficio;
    private boolean visibleBtnEliminarOrden;
    private boolean visibleBtnEliminarOficio;
    private boolean mostrarProrrogasOficio;
    private boolean mostrarBtnProrrogasOficio;

    private String rutaArchivoAcusePromocion;
    private String cadena;
    private String leyenda;
    private String leyendaDoc;

    private Date fechaEnvioPromocionOficioSeleccionadaHistorico;
    private Date fechaEnvioProrrogaOficioSeleccionada;
    private BigDecimal idPromocionOficioSeleccionadaHistorico;
    private BigDecimal idProrrogaOficioSeleccionado;

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCadena() {
        return cadena;
    }

    public void setVisibleBtnEliminarOrden(boolean visibleBtnEliminarOrden) {
        this.visibleBtnEliminarOrden = visibleBtnEliminarOrden;
    }

    public boolean isVisibleBtnEliminarOrden() {
        return visibleBtnEliminarOrden;
    }

    public void setVisibleBtnEliminarOficio(boolean visibleBtnEliminarOficio) {
        this.visibleBtnEliminarOficio = visibleBtnEliminarOficio;
    }

    public boolean isVisibleBtnEliminarOficio() {
        return visibleBtnEliminarOficio;
    }

    public void setMostrarProrrogasOficio(boolean mostrarProrrogasOficio) {
        this.mostrarProrrogasOficio = mostrarProrrogasOficio;
    }

    public boolean isMostrarProrrogasOficio() {
        return mostrarProrrogasOficio;
    }

    public void setMostrarBtnProrrogasOficio(boolean mostrarBtnProrrogasOficio) {
        this.mostrarBtnProrrogasOficio = mostrarBtnProrrogasOficio;
    }

    public boolean isMostrarBtnProrrogasOficio() {
        return mostrarBtnProrrogasOficio;
    }

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
        this.fechaEnvioProrrogaOficioSeleccionada = (fechaEnvioProrrogaOficioSeleccionada != null)
                ? (Date) fechaEnvioProrrogaOficioSeleccionada : null;
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
        this.fechaEnvioPromocionOficioSeleccionadaHistorico = (fechaEnvioPromocionOficioSeleccionadaHistorico != null)
                ? (Date) fechaEnvioPromocionOficioSeleccionadaHistorico : null;
    }

    public Date getFechaEnvioPromocionOficioSeleccionadaHistorico() {
        return (fechaEnvioPromocionOficioSeleccionadaHistorico != null)
                ? (Date) fechaEnvioPromocionOficioSeleccionadaHistorico : null;
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

    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
    }

}
