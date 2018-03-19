package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class AsociadoFuncionarioDTO extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idAsociado;
    private BigDecimal idPropuesta;
    private BigDecimal idTipoEmpleado;
    private BigDecimal idOrden;
    private BigDecimal idEmpleado;
    private Integer blnActivo;

    public BigDecimal getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Integer blnActivo) {
        this.blnActivo = blnActivo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEmpleado == null) ? 0 : idEmpleado.hashCode());
        result = prime * result + ((idOrden == null) ? 0 : idOrden.hashCode());
        result = prime * result + ((idPropuesta == null) ? 0 : idPropuesta.hashCode());
        result = prime * result + ((idTipoEmpleado == null) ? 0 : idTipoEmpleado.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AsociadoFuncionarioDTO other = (AsociadoFuncionarioDTO) obj;
        if (idEmpleado == null) {
            if (other.idEmpleado != null) {
                return false;
            }
        } else if (!idEmpleado.equals(other.idEmpleado)) {
            return false;
        }
        if (idOrden == null) {
            if (other.idOrden != null) {
                return false;
            }
        } else if (!idOrden.equals(other.idOrden)) {
            return false;
        }
        if (idPropuesta == null) {
            if (other.idPropuesta != null) {
                return false;
            }
        } else if (!idPropuesta.equals(other.idPropuesta)) {
            return false;
        }
        if (idTipoEmpleado == null) {
            if (other.idTipoEmpleado != null) {
                return false;
            }
        } else if (!idTipoEmpleado.equals(other.idTipoEmpleado)) {
            return false;
        }
        return true;
    }

}
