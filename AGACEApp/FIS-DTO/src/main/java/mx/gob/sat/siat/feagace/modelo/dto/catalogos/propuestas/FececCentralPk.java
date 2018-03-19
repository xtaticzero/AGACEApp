/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_CENTRAL table.
 */
public class FececCentralPk implements Serializable {
    private BigDecimal idCentral;

    /**
     * Sets the value of idCentral
     */
    public void setIdCentral(final BigDecimal idCentral) {
        this.idCentral = idCentral;
    }

    /**
     * Gets the value of idCentral
     */
    public BigDecimal getIdCentral() {
        return idCentral;
    }

    /**
     * Method 'FececCentralPk'
     *
     */
    public FececCentralPk() {
    }

    /**
     * Method 'FececCentralPk'
     *
     * @param idCentral
     */
    public FececCentralPk(final BigDecimal idCentral) {
        this.idCentral = idCentral;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececCentralPk: ");
        ret.append("idCentral=" + idCentral);
        return ret.toString();
    }

}
