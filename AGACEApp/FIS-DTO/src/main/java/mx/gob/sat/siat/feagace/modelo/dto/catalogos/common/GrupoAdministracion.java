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
public class GrupoAdministracion extends BaseModel {

    private static final long serialVersionUID = -4640812283325892161L;
    private BigDecimal idGrupo;
    private String nombre;
    private String descripcion;
    private Boolean central;
    private Date fechaInicio;
    private Date fechaFin;

    public GrupoAdministracion() {
        central = false;
    }
    
    

    public BigDecimal getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigDecimal idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCentral() {
        return central;
    }

    public void setCentral(Boolean central) {
        this.central = central;
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
        int hash = 5;
        hash = 47 * hash + (this.idGrupo != null ? this.idGrupo.hashCode() : 0);
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
        final GrupoAdministracion other = (GrupoAdministracion) obj;
        if (this.idGrupo != other.idGrupo && (this.idGrupo == null || !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

}
