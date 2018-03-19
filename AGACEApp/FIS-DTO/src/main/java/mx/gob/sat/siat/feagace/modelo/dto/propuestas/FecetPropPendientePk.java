package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_PROP_PENDIENTE table.
 */
public class FecetPropPendientePk implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal idPropPendiente;

    /**
     * Sets the value of idPropPendiente
     */
    public void setIdPropPendiente(BigDecimal idPropPendiente) {
        this.idPropPendiente = idPropPendiente;
    }

    /**
     * Gets the value of idPropPendiente
     */
    public BigDecimal getIdPropPendiente() {
        return idPropPendiente;
    }

    /**
     * Method 'FecetPropPendientePk'
     * 
     */
    public FecetPropPendientePk() {
    }

    /**
     * Method 'FecetPropPendientePk'
     * 
     * @param idPropPendiente
     */
    public FecetPropPendientePk(final BigDecimal idPropPendiente) {
        this.idPropPendiente = idPropPendiente;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPropPendientePk: ");
        ret.append("idPropPendiente=").append(idPropPendiente);
        return ret.toString();
    }

}
