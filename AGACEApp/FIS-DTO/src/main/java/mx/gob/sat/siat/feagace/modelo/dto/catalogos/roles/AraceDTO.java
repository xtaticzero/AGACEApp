/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class AraceDTO extends BaseModel {

    private static final long serialVersionUID = -8989149903718619806L;
    private static final int CONSTANT_HASCODE = 47;

    private Integer idArace;
    private String nombre;
    private String sede;
    private EstadoBooleanoEnum central;
    private String descripcion;

    public Integer getIdArace() {
        return idArace;
    }

    public void setIdArace(Integer idArace) {
        this.idArace = idArace;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public EstadoBooleanoEnum getCentral() {
        return central;
    }

    public void setCentral(EstadoBooleanoEnum central) {
        this.central = central;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = CONSTANT_HASCODE * hash + (this.idArace != null ? this.idArace.hashCode() : 0);
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
        final AraceDTO other = (AraceDTO) obj;
        if (this.idArace != other.idArace && (this.idArace == null || !this.idArace.equals(other.idArace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AraceDTO{" + "idArace=" + idArace + ", nombre=" + nombre + ", sede=" + sede + ", central=" + central + '}';
    }

}
