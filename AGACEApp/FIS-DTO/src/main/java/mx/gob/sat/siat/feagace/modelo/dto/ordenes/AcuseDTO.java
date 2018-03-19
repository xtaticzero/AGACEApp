/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;


/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public class AcuseDTO {

    private BigDecimal folioActaAdministrativo;
    private String folioNyV;
    private Calendar fechaAcuseNotificacion;
    private Calendar fechaSurteEfectosNotificacion;
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private Date fechaRespuesta;

    public AcuseDTO() {
        super();
    }

    public void setFolioActaAdministrativo(BigDecimal folioActaAdministrativo) {
        this.folioActaAdministrativo = folioActaAdministrativo;
    }

    public BigDecimal getFolioActaAdministrativo() {
        return folioActaAdministrativo;
    }

    public void setFolioNyV(String folioNyV) {
        this.folioNyV = folioNyV;
    }

    public String getFolioNyV() {
        return folioNyV;
    }

    public void setFechaAcuseNotificacion(Calendar fechaAcuseNotificacion) {
        this.fechaAcuseNotificacion =
                (fechaAcuseNotificacion != null) ? (Calendar)fechaAcuseNotificacion.clone() : null;
    }

    public Calendar getFechaAcuseNotificacion() {
        return (fechaAcuseNotificacion != null) ? (Calendar)fechaAcuseNotificacion.clone() : null;
    }

    public void setFechaSurteEfectosNotificacion(Calendar fechaSurteEfectosNotificacion) {
        this.fechaSurteEfectosNotificacion =
                (fechaSurteEfectosNotificacion != null) ? (Calendar)fechaSurteEfectosNotificacion.clone() : null;
    }

    public Calendar getFechaSurteEfectosNotificacion() {
        return (fechaSurteEfectosNotificacion != null) ? (Calendar)fechaSurteEfectosNotificacion.clone() : null;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta != null ? new Date(fechaRespuesta.getTime()) : null;
    }

    public Date getFechaRespuesta() {
        return (fechaRespuesta != null) ? (Date)fechaRespuesta.clone() : null;
    }
}
