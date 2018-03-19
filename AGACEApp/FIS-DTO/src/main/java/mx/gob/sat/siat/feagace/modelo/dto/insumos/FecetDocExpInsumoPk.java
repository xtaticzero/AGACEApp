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
 * This class represents the primary key of the FECET_DOC_EXP_INSUMO table.
 */
public class FecetDocExpInsumoPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idDocExpInsumo;

    /**
     * Sets the value of idDocExpInsumo
     */
    public void setIdDocExpInsumo(BigDecimal idDocExpInsumo) {
        this.idDocExpInsumo = idDocExpInsumo;
    }

    /**
     * Gets the value of idDocExpInsumo
     */
    public BigDecimal getIdDocExpInsumo() {
        return idDocExpInsumo;
    }

    /**
     * Method 'FecetDocExpInsumoPk'
     * 
     */
    public FecetDocExpInsumoPk() {
    }

    /**
     * Method 'FecetDocExpInsumoPk'
     * 
     * @param idDocExpInsumo
     */
    public FecetDocExpInsumoPk(final BigDecimal idDocExpInsumo) {
        this.idDocExpInsumo = idDocExpInsumo;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetDocExpInsumoPk: ");
        ret.append("idDocExpInsumo=");
        ret.append(idDocExpInsumo);
        return ret.toString();
    }

}
