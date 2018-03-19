package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececLeyenda extends BaseModel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal idLeyenda;
    private String nombreLeyenda;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer blnActivo;

    public BigDecimal getIdLeyenda() {
        return idLeyenda;
    }

    public void setIdLeyenda(BigDecimal idLeyenda) {
        this.idLeyenda = idLeyenda;
    }

    public String getNombreLeyenda() {
        return nombreLeyenda;
    }

    public void setNombreLeyenda(String nombreLeyenda) {
        this.nombreLeyenda = nombreLeyenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Integer getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Integer blnActivo) {
        this.blnActivo = blnActivo;
    }

}
