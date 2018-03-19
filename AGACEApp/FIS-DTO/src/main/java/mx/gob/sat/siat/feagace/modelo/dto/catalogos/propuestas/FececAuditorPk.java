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
 * This class represents the primary key of the FECEC_AUDITOR table.
 */
public class FececAuditorPk implements Serializable {
    private BigDecimal idAuditor;

    /**
     * Method 'FececAuditorPk'
     *
     */
    public FececAuditorPk() {
    }

    /**
     * Method 'FececAuditorPk'
     *
     * @param idAuditor
     */
    public FececAuditorPk(final BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    /**
     * Sets the value of idAuditor
     */
    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
    }

    /**
     * Gets the value of idAuditor
     */
    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececAuditorPk: ");
        ret.append("idAuditor=" + idAuditor);
        return ret.toString();
    }

}
