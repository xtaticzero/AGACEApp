package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetPropCanceladaPk implements Serializable {
    
    private BigDecimal idCancelacionPropuesta;

    public void setIdCancelacionPropuesta(BigDecimal idCancelacionPropuesta) {
        this.idCancelacionPropuesta = idCancelacionPropuesta;
    }

    public BigDecimal getIdCancelacionPropuesta() {
        return idCancelacionPropuesta;
    }
    
    public FecetPropCanceladaPk() {
    }
    
    public FecetPropCanceladaPk(final BigDecimal idCancelacionPropuesta) {
        this.idCancelacionPropuesta = idCancelacionPropuesta;
    }
    
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPropCanceladaPk: ");
        ret.append("idCancelcionPropuesta=" + idCancelacionPropuesta);
        return ret.toString();
    }
}
