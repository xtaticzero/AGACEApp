/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEA_OPERACIONES table.
 */
public class FeceaOperacionesPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idOperacion;

    /**
     * Sets the value of idOperacion
     */
    public void setIdOperacion(BigDecimal idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     * Gets the value of idOperacion
     */
    public BigDecimal getIdOperacion() {
        return idOperacion;
    }

    /**
     * Method 'FeceaOperacionesPk'
     * 
     */
    public FeceaOperacionesPk() {
    }

    /**
     * Method 'FeceaOperacionesPk'
     * 
     * @param idOperacion
     */
    public FeceaOperacionesPk(final BigDecimal idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FeceaOperacionesPk: ");
        ret.append("idOperacion=");
        ret.append(idOperacion);
        return ret.toString();
    }

}
