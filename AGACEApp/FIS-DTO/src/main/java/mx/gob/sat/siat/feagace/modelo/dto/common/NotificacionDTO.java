/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administración Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;


/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public class NotificacionDTO {

    public static final String FECHA_MENSAJE = "M&eacute;xico D.F.  a <%dia%> de <%mes%> del <%anio%>.";

    private String asunto;

    private String titulo;
    private String fecha;

    private String folioCarga;
    private String fechaTramite;

    private String metodo;
    private String orden;
    private String nombreContribuyente;
    private String rfcContribuyente;
    private String nombreAgenteAduanal;
    private String rfcAgenteAduanal;
    private String tipoSolicitud;

    private String mensaje;
    private String motivoRechazo;

    private int tipoCorreo;
    private int correoPor;
    private String correoPorValor;
    
    private String horaTramite;
    private String numOficio;
    
    private BigDecimal idTipoEmpleado;
    private BigDecimal idCentral;
    private BigDecimal idArace;
    private String rfcUsuario;
    
    public NotificacionDTO() {
        super();
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFolioCarga(String folioCarga) {
        this.folioCarga = folioCarga;
    }

    public String getFolioCarga() {
        return folioCarga;
    }

    public void setFechaTramite(String fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public String getFechaTramite() {
        return fechaTramite;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getOrden() {
        return orden;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setNombreAgenteAduanal(String nombreAgenteAduanal) {
        this.nombreAgenteAduanal = nombreAgenteAduanal;
    }

    public String getNombreAgenteAduanal() {
        return nombreAgenteAduanal;
    }

    public void setRfcAgenteAduanal(String rfcAgenteAduanal) {
        this.rfcAgenteAduanal = rfcAgenteAduanal;
    }

    public String getRfcAgenteAduanal() {
        return rfcAgenteAduanal;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setTipoCorreo(int tipoCorreo) {
        this.tipoCorreo = tipoCorreo;
    }

    public int getTipoCorreo() {
        return tipoCorreo;
    }

    public void setCorreoPor(int correoPor) {
        this.correoPor = correoPor;
    }

    public int getCorreoPor() {
        return correoPor;
    }

    public void setCorreoPorValor(String correoPorValor) {
        this.correoPorValor = correoPorValor;
    }

    public String getCorreoPorValor() {
        return correoPorValor;
    }

    public void setHoraTramite(String horaTramite) {
        this.horaTramite = horaTramite;
    }

    public String getHoraTramite() {
        return horaTramite;
    }

    public void setNumOficio(String numOficio) {
        this.numOficio = numOficio;
    }

    public String getNumOficio() {
        return numOficio;
    }

    public void setIdTipoEmpleado(final BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdCentral(final BigDecimal idCentral) {
        this.idCentral = idCentral;
    }

    public BigDecimal getIdCentral() {
        return idCentral;
    }

    public void setIdArace(final BigDecimal idArace) {
        this.idArace = idArace;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setRfcUsuario(final String rfcUsuario) {
        this.rfcUsuario = rfcUsuario;
    }

    public String getRfcUsuario() {
        return rfcUsuario;
    }
}
