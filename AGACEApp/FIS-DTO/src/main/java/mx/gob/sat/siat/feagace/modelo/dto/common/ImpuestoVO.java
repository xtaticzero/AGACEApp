/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;


/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public class ImpuestoVO implements Serializable{
    private static final long serialVersionUID = 8091621449569657850L;

    private BigDecimal idImpuesto;
    private String descripcion;
    private BigDecimal monto;
    private Date fechaInicial;
    private Date fechaFin;
    private BigDecimal idConcepto;
    private String descripcionConcepto;

    public ImpuestoVO() {
        super();
    }

    public void setIdImpuesto(BigDecimal idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public BigDecimal getIdImpuesto() {
        return idImpuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(final BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaInicial() {
        return (fechaInicial != null) ? (Date)fechaInicial.clone() : null;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial != null ? new Date(fechaInicial.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public void setIdConcepto(BigDecimal idConcepto) {
        this.idConcepto = idConcepto;
    }

    public BigDecimal getIdConcepto() {
        return idConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }
}
