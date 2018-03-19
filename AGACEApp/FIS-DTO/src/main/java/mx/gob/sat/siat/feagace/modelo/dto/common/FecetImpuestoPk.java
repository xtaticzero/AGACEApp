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
 * This class represents the primary key of the FECET_IMPUESTO table.
 */
public class FecetImpuestoPk implements Serializable {
    private BigDecimal idImpuesto;

    /**
     * Sets the value of idImpuesto
     */
    public void setIdImpuesto(final BigDecimal idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    /**
     * Gets the value of idImpuesto
     */
    public BigDecimal getIdImpuesto() {
        return idImpuesto;
    }

    /**
     * Method 'FecetImpuestoPk'
     *
     */
    public FecetImpuestoPk() {
    }

    /**
     * Method 'FecetImpuestoPk'
     *
     * @param idImpuesto
     */
    public FecetImpuestoPk(final BigDecimal idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetImpuestoPk: ");
        ret.append("idImpuesto=" + idImpuesto);
        return ret.toString();
    }

}
