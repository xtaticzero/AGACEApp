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
 * This class represents the primary key of the FECEC_SUBMODULOS table.
 */
public class FececSubmodulosPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idSubmodulo;

    /**
     * Sets the value of idSubmodulo
     */
    public void setIdSubmodulo(BigDecimal idSubmodulo) {
        this.idSubmodulo = idSubmodulo;
    }

    /**
     * Gets the value of idSubmodulo
     */
    public BigDecimal getIdSubmodulo() {
        return idSubmodulo;
    }

    /**
     * Method 'FececSubmodulosPk'
     * 
     */
    public FececSubmodulosPk() {
    }

    /**
     * Method 'FececSubmodulosPk'
     * 
     * @param idSubmodulo
     */
    public FececSubmodulosPk(final BigDecimal idSubmodulo) {
        this.idSubmodulo = idSubmodulo;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececSubmodulosPk: ");
        ret.append("idSubmodulo=").append(idSubmodulo);
        return ret.toString();
    }

}
