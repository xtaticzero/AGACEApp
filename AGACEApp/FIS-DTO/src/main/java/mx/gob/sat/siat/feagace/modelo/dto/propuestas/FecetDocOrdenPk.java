package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_DOC_ORDEN table.
 */
public class FecetDocOrdenPk implements Serializable{
    private static final long serialVersionUID = 1L;
    private BigDecimal idDocOrden;


    public BigDecimal getIdDocOrden() {
        return idDocOrden;
    }

    public void setIdDocOrden(BigDecimal idDocOrden) {
        this.idDocOrden = idDocOrden;
    }
    
    /**
     * Method 'FecetDocOrdenPk'
     * 
     */
    public FecetDocOrdenPk() {
    }
    
    /**
     * Method 'FecetDocOrdenPk'
     * 
     * @param idDocExpInsumo
     */
    public FecetDocOrdenPk(final BigDecimal idDocOrden) {
        this.idDocOrden = idDocOrden;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetDocOrdenPk: ");
        ret.append("idDocOrden=");
        ret.append(idDocOrden);
        return ret.toString();
    }
   
}