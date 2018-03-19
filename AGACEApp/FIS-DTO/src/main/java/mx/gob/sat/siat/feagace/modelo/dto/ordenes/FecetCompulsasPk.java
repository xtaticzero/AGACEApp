/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
 
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * This class represents the primary key of the FECET_COMPULSAS table.
 */
public class FecetCompulsasPk implements Serializable {
    private BigDecimal idCompulsa;

    /** 
     * Sets the value of idCompulsa
     */
    public void setIdCompulsa(BigDecimal idCompulsa) {
        this.idCompulsa = idCompulsa;
    }

    /** 
     * Gets the value of idCompulsa
     */
    public BigDecimal getIdCompulsa() {
        return idCompulsa;
    }

    /**
     * Method 'FecetCompulsasPk'
     * 
     */
    public FecetCompulsasPk() {
    }

    /**
     * Method 'FecetCompulsasPk'
     * 
     * @param idCompulsa
     */
    public FecetCompulsasPk(final BigDecimal idCompulsa) {
        this.idCompulsa = idCompulsa;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dao.ordenes.dto.FecetCompulsasPk: " );
        ret.append( "idCompulsa=").append(idCompulsa );
        return ret.toString();
    }

}
