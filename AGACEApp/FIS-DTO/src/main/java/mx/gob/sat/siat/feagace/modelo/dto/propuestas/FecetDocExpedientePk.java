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
 * This class represents the primary key of the FECET_DOC_EXPEDIENTE table.
 */
public class FecetDocExpedientePk implements Serializable {
    private BigDecimal idDocExpediente;

    /**
     * Sets the value of idDocExpediente
     */
    public void setIdDocExpediente(final BigDecimal idDocExpediente) {
        this.idDocExpediente = idDocExpediente;
    }

    /**
     * Gets the value of idDocExpediente
     */
    public BigDecimal getIdDocExpediente() {
        return idDocExpediente;
    }

    /**
     * Method 'FecetDocExpedientePk'
     *
     */
    public FecetDocExpedientePk() {
    }

    /**
     * Method 'FecetDocExpedientePk'
     *
     * @param idDocExpediente
     */
    public FecetDocExpedientePk(final BigDecimal idDocExpediente) {
        this.idDocExpediente = idDocExpediente;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetDocExpedientePk: ");
        ret.append("idDocExpediente=" + idDocExpediente);
        return ret.toString();
    }

}
