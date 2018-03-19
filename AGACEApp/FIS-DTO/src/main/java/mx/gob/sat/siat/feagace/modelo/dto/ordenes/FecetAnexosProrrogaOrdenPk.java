package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_ANEXOS_PRORROGA_ORDEN table.
 */
public class FecetAnexosProrrogaOrdenPk implements Serializable {
    private static final Long serialVersionUID = 1L;
    
    private BigDecimal idAnexosProrrogaOrden;

    /** 
     * Sets the value of idAnexosProrrogaOrden
     */
    public void setIdAnexosProrrogaOrden(final BigDecimal idAnexosProrrogaOrden) {
        this.idAnexosProrrogaOrden = idAnexosProrrogaOrden;
    }

    /** 
     * Gets the value of idAnexosProrrogaOrden
     */
    public BigDecimal getIdAnexosProrrogaOrden() {
        return idAnexosProrrogaOrden;
    }

    /**
     * Method 'FecetAnexosProrrogaOrdenPk'
     * 
     */
    public FecetAnexosProrrogaOrdenPk() {
    }

    /**
     * Method 'FecetAnexosProrrogaOrdenPk'
     * 
     * @param idAnexosProrrogaOrden
     */
    public FecetAnexosProrrogaOrdenPk(final BigDecimal idAnexosProrrogaOrden) {
        this.idAnexosProrrogaOrden = idAnexosProrrogaOrden;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.dto.FecetAnexosProrrogaOrdenPk: " );
        ret.append( "idAnexosProrrogaOrden=" + idAnexosProrrogaOrden );
        return ret.toString();
    }

}
