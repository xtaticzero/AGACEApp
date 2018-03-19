package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_DOC_PRORROGA_ORDEN table.
 */
public class FecetDocProrrogaOrdenPk implements Serializable {
    private static final Long serialVersionUID = 1L;
    
    private BigDecimal idDocProrrogaOrden;

    /** 
     * Sets the value of idDocProrroga
     */
    public void setIdDocProrrogaOrden(final BigDecimal idDocProrrogaOrden) {
        this.idDocProrrogaOrden = idDocProrrogaOrden;
    }

    /** 
     * Gets the value of idDocProrrogaOrden
     */
    public BigDecimal getIdDocProrrogaOrden() {
        return idDocProrrogaOrden;
    }

    /**
     * Method 'FecetDocProrrogaOrdenPk'
     * 
     */
    public FecetDocProrrogaOrdenPk() {
    }

    /**
     * Method 'FecetDocProrrogaOrdenPk'
     * 
     * @param idDocProrrogaOrden
     */
    public FecetDocProrrogaOrdenPk(final BigDecimal idDocProrrogaOrden) {
        this.idDocProrrogaOrden = idDocProrrogaOrden;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.dto.FecetDocProrrogaOrdenPk: " );
        ret.append( "idDocProrrogaOrden=" + idDocProrrogaOrden );
        return ret.toString();
    }

}
