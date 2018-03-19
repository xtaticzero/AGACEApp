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
 * This class represents the primary key of the FECEC_REVISION table.
 */
public class FececRevisionPk implements Serializable {
    private BigDecimal idRevision;

    /**
     * Sets the value of idRevision
     */
    public void setIdRevision(final BigDecimal idRevision) {
        this.idRevision = idRevision;
    }

    /**
     * Gets the value of idRevision
     */
    public BigDecimal getIdRevision() {
        return idRevision;
    }

    /**
     * Method 'FececRevisionPk'
     *
     */
    public FececRevisionPk() {
    }

    /**
     * Method 'FececRevisionPk'
     *
     * @param idRevision
     */
    public FececRevisionPk(final BigDecimal idRevision) {
        this.idRevision = idRevision;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececRevisionPk: ");
        ret.append("idRevision=" + idRevision);
        return ret.toString();
    }

}
