/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class Regla extends BaseModel {

    private static final long serialVersionUID = 478981891388250446L;

    private BigDecimal idRegla;
    private String clave;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;

    public BigDecimal getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(BigDecimal idRegla) {
        this.idRegla = idRegla;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.idRegla != null ? this.idRegla.hashCode() : 0);
        hash = 97 * hash + (this.clave != null ? this.clave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Regla other = (Regla) obj;
        if (this.idRegla != other.idRegla && (this.idRegla == null || !this.idRegla.equals(other.idRegla))) {
            return false;
        }
        if ((this.clave == null) ? (other.clave != null) : !this.clave.equals(other.clave)) {
            return false;
        }
        return true;
    }
    
    

}
