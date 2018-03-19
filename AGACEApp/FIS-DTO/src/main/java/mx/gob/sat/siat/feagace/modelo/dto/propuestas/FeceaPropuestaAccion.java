package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FeceaPropuestaAccion extends BaseModel implements Serializable {

    private static final long serialVersionUID = -8202052550988261562L;

    private BigDecimal idPropuestaAccion;

    private BigDecimal idPropuesta;

    private BigDecimal idAccion;

    private BigDecimal idAccionOrigen;

    public BigDecimal getIdPropuestaAccion() {
        return idPropuestaAccion;
    }

    public void setIdPropuestaAccion(BigDecimal idPropuestaAccion) {
        this.idPropuestaAccion = idPropuestaAccion;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(BigDecimal idAccion) {
        this.idAccion = idAccion;
    }

    public BigDecimal getIdAccionOrigen() {
        return idAccionOrigen;
    }

    public void setIdAccionOrigen(BigDecimal idAccionOrigen) {
        this.idAccionOrigen = idAccionOrigen;
    }

}
