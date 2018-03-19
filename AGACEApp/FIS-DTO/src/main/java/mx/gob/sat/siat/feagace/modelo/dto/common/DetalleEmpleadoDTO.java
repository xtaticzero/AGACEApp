/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleEmpleadoDTO extends BaseModel {

    private static final long serialVersionUID = -6584310544108949856L;
    private static final int CONSTANT_HASCODE = 97;

    private BigDecimal idEmpleado;
    private TipoEmpleadoEnum tipoEmpleado;
    private AraceDTO central;
    private List<AraceDTO> lstAraces;

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoEmpleadoEnum getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleadoEnum tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public AraceDTO getCentral() {
        return central;
    }

    public void setCentral(AraceDTO central) {
        this.central = central;
    }

    public List<AraceDTO> getLstAraces() {
        return lstAraces;
    }

    public void setLstAraces(List<AraceDTO> lstAraces) {
        this.lstAraces = lstAraces;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = CONSTANT_HASCODE * hash + (this.idEmpleado != null ? this.idEmpleado.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.tipoEmpleado != null ? this.tipoEmpleado.hashCode() : 0);
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
        final DetalleEmpleadoDTO other = (DetalleEmpleadoDTO) obj;
        if (this.idEmpleado != other.idEmpleado && (this.idEmpleado == null || !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        if (this.tipoEmpleado != other.tipoEmpleado) {
            return false;
        }        
        return true;
    }

}
