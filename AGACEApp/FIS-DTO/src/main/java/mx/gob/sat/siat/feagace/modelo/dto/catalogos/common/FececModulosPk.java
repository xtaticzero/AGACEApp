/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_MODULOS table.
 */
public class FececModulosPk implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private BigDecimal idModulo;

    /**
     * Sets the value of idModulo
     */
    public void setIdModulo(BigDecimal idModulo) {
        this.idModulo = idModulo;
    }

    /**
     * Gets the value of idModulo
     */
    public BigDecimal getIdModulo() {
        return idModulo;
    }

    /**
     * Method 'FececModulosPk'
     * 
     */
    public FececModulosPk() {
    }

    /**
     * Method 'FececModulosPk'
     * 
     * @param idModulo
     */
    public FececModulosPk(final BigDecimal idModulo) {
        this.idModulo = idModulo;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececModulosPk: ");
        ret.append("idModulo=").append(idModulo);
        return ret.toString();
    }

}
