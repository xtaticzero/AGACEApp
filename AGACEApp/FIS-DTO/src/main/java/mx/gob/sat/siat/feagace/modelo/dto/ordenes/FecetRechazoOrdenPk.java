package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_RECHAZO_ORDEN table.
 */
public class FecetRechazoOrdenPk implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idRechazoOrden;

    /**
     * This attribute represents whether the primitive attribute idRechazoOrden
     * is null.
     */
    private boolean idRechazoOrdenNull;

    /**
     * Sets the value of idRechazoOrden
     */
    public void setIdRechazoOrden(BigDecimal idRechazoOrden) {
        this.idRechazoOrden = idRechazoOrden;
    }

    /**
     * Gets the value of idRechazoOrden
     */
    public BigDecimal getIdRechazoOrden() {
        return idRechazoOrden;
    }

    /**
     * Method 'FecetRechazoOrdenPk'
     * 
     */
    public FecetRechazoOrdenPk() {
    }

    /**
     * Method 'FecetRechazoOrdenPk'
     * 
     * @param idRechazoOrden
     */
    public FecetRechazoOrdenPk(final BigDecimal idRechazoOrden) {
        this.idRechazoOrden = idRechazoOrden;
    }

    /**
     * Sets the value of idRechazoOrdenNull
     */
    public void setIdRechazoOrdenNull(boolean idRechazoOrdenNull) {
        this.idRechazoOrdenNull = idRechazoOrdenNull;
    }

    /**
     * Gets the value of idRechazoOrdenNull
     */
    public boolean isIdRechazoOrdenNull() {
        return idRechazoOrdenNull;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetRechazoOrdenPk: ");
        ret.append("idRechazoOrden=");
        ret.append(idRechazoOrden);
        return ret.toString();
    }

}
