/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_RECHAZO_PROPUESTA table.
 */
public class FecetRechazoPropuestaPk implements Serializable {
    private BigDecimal idRechazoPropuesta;

    /**
     * Sets the value of idRechazoPropuesta
     */
    public void setIdRechazoPropuesta(final BigDecimal idRechazoPropuesta) {
        this.idRechazoPropuesta = idRechazoPropuesta;
    }

    /**
     * Gets the value of idRechazoPropuesta
     */
    public BigDecimal getIdRechazoPropuesta() {
        return idRechazoPropuesta;
    }

    /**
     * Method 'FecetRechazoPropuestaPk'
     *
     */
    public FecetRechazoPropuestaPk() {
    }

    /**
     * Method 'FecetRechazoPropuestaPk'
     *
     * @param idRechazoPropuesta
     */
    public FecetRechazoPropuestaPk(final BigDecimal idRechazoPropuesta) {
        this.idRechazoPropuesta = idRechazoPropuesta;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetRechazoPropuestaPk: ");
        ret.append("idRechazoPropuesta=" + idRechazoPropuesta);
        return ret.toString();
    }

}
