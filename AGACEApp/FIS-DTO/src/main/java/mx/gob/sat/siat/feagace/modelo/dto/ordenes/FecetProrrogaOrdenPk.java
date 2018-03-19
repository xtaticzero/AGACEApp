package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * This class represents the primary key of the FECET_PRORROGA_ORDEN table.
 */
public class FecetProrrogaOrdenPk implements Serializable {
    private static final Long serialVersionUID = 1L;
    
    private BigDecimal idProrrogaOrden;

    /** 
     * Sets the value of idProrroga
     */
    public void setIdProrrogaOrden(final BigDecimal idProrrogaOrden) {
        this.idProrrogaOrden = idProrrogaOrden;
    }

    /** 
     * Gets the value of idProrrogaOrden
     */
    public BigDecimal getIdProrrogaOrden() {
        return idProrrogaOrden;
    }

    /**
     * Method 'FecetProrrogaOrdenPk'
     * 
     */
    public FecetProrrogaOrdenPk() {
    }

    /**
     * Method 'FecetProrrogaOrdenPk'
     * 
     * @param idProrrogaOrden
     */
    public FecetProrrogaOrdenPk(final BigDecimal idProrrogaOrden) {
        this.idProrrogaOrden = idProrrogaOrden;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.dto.FecetProrrogaOrdenPk: " );
        ret.append( "idProrrogaOrden=" + idProrrogaOrden );
        return ret.toString();
    }

}
