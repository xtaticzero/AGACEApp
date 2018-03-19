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
 * This class represents the primary key of the FECET_CONT_AUDIT table.
 */
public class FecetContAuditPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idContAudit;

    /**
     * Method 'FecetContAuditPk'
     *
     */
    public FecetContAuditPk() {
    }

    /**
     * Method 'FecetContAuditPk'
     *
     * @param idContAudit
     */
    public FecetContAuditPk(final BigDecimal idContAudit) {
        this.idContAudit = idContAudit;
    }

    /**
     * Sets the value of idContAudit
     */
    public void setIdContAudit(final BigDecimal idContAudit) {
        this.idContAudit = idContAudit;
    }

    /**
     * Gets the value of idContAudit
     */
    public BigDecimal getIdContAudit() {
        return idContAudit;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetContAuditPk: ");
        ret.append("idContAudit=" + idContAudit);
        return ret.toString();
    }

}
