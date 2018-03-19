/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 *
 * @author jose.aguilar
 */
public class FececTipoInsumo extends BaseModel implements Serializable {
    
    private static final long serialVersionUID = -3463334022019114341L;
    private BigDecimal idTipoInsumo;
    private BigDecimal idGeneral;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;

    public BigDecimal getIdTipoInsumo() {
        return idTipoInsumo;
    }

    public void setIdTipoInsumo(BigDecimal idTipoInsumo) {
        this.idTipoInsumo = idTipoInsumo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(BigDecimal idGeneral) {
        this.idGeneral = idGeneral;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}
