/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_INSUMO table.
 */
public class FecetInsumoPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idInsumo;

    /**
     * Sets the value of idInsumo
     */
    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Gets the value of idInsumo
     */
    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    /**
     * Method 'FecetInsumoPk'
     * 
     */
    public FecetInsumoPk() {
    }

    /**
     * Method 'FecetInsumoPk'
     * 
     * @param idInsumo
     */
    public FecetInsumoPk(final BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetInsumoPk: ");
        ret.append("idInsumo=");
        ret.append(idInsumo);
        return ret.toString();
    }

}
