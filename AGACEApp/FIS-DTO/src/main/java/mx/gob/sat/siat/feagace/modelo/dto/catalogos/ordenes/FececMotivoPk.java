/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_MOTIVO table.
 */
public class FececMotivoPk implements Serializable {
    private BigDecimal idMotivo;

    /**
     * Sets the value of idMotivo
     */
    public void setIdMotivo(final BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    /**
     * Gets the value of idMotivo
     */
    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    /**
     * Method 'FececMotivoPk'
     *
     */
    public FececMotivoPk() {
    }

    /**
     * Method 'FececMotivoPk'
     *
     * @param idMotivo
     */
    public FececMotivoPk(final BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececMotivoPk: ");
        ret.append("idMotivo=" + idMotivo);
        return ret.toString();
    }

}
