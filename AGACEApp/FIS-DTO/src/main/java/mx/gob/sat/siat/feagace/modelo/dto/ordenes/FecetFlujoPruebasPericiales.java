package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;

public class FecetFlujoPruebasPericiales extends BaseModel {
    
    private BigDecimal idFlujoPruebasPericiales;
    private BigDecimal idPruebasPericiales;
    private Date fechaCreacion;
    private String justificacion;
    private String justificacionFirmante;
    private BigDecimal idEstatus;
    private Date fechaRechazoFirmante;
    private Integer totalAnexos;
    private FececEstatus fececEstatus;
    private Boolean aprobada;
    
    public FecetFlujoPruebasPericiales() {
    }

    public void setIdFlujoPruebasPericiales(BigDecimal idFlujoPruebasPericiales) {
        this.idFlujoPruebasPericiales = idFlujoPruebasPericiales;
    }

    public BigDecimal getIdFlujoPruebasPericiales() {
        return idFlujoPruebasPericiales;
    }

    public void setIdPruebasPericiales(BigDecimal idPruebasPericiales) {
        this.idPruebasPericiales = idPruebasPericiales;
    }

    public BigDecimal getIdPruebasPericiales() {
        return idPruebasPericiales;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacionFirmante(String justificacionFirmante) {
        this.justificacionFirmante = justificacionFirmante;
    }

    public String getJustificacionFirmante() {
        return justificacionFirmante;
    }

    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    public void setFechaRechazoFirmante(Date fechaRechazoFirmante) {
        this.fechaRechazoFirmante = fechaRechazoFirmante != null ? new Date(fechaRechazoFirmante.getTime()) : null;
    }

    public Date getFechaRechazoFirmante() {
        return (fechaRechazoFirmante != null) ? (Date)fechaRechazoFirmante.clone() : null;
    }

    public void setTotalAnexos(Integer totalAnexos) {
        this.totalAnexos = totalAnexos;
    }

    public Integer getTotalAnexos() {
        return totalAnexos;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }

    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setAprobada(Boolean aprobada) {
        this.aprobada = aprobada;
    }

    public Boolean getAprobada() {
        return aprobada;
    }
}
