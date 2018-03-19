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
 * This class represents the primary key of the FECEAAPODLEGAL table.
 */
public class FececApodLegalPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idApodLegal;

    /**
     * Method 'FeceaApodLegalPk'
     *
     */
    public FececApodLegalPk() {
    }

    /**
     * Method 'FeceaApodLegalPk'
     *
     * @param idApodLegal
     */
    public FececApodLegalPk(final BigDecimal idApodLegal) {
        this.idApodLegal = idApodLegal;
    }

    /**
     * Sets the value of idApodLegal
     */
    public void setIdApodLegal(final BigDecimal idApodLegal) {
        this.idApodLegal = idApodLegal;
    }

    /**
     * Gets the value of idApodLegal
     */
    public BigDecimal getIdApodLegal() {
        return idApodLegal;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FeceaApodLegalPk: ");
        ret.append("idApodLegal=" + idApodLegal);
        return ret.toString();
    }

}
