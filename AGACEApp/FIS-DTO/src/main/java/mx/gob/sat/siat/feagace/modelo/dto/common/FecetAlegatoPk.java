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
 * This class represents the primary key of the FECET_ALEGATO table.
 */
public class FecetAlegatoPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idAlegato;

    /**
     * Method 'FECET_ALEGATOPk'
     *
     */
    public FecetAlegatoPk() {
    }

    /**
     * Method 'FECET_ALEGATOPk'
     *
     * @param idAlegato
     */
    public FecetAlegatoPk(final BigDecimal idAlegato) {
        this.idAlegato = idAlegato;
    }

    /**
     * Sets the value of idAlegato
     */
    public void setIdAlegato(final BigDecimal idAlegato) {
        this.idAlegato = idAlegato;
    }

    /**
     * Gets the value of idAlegato
     */
    public BigDecimal getIdAlegato() {
        return idAlegato;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FECET_ALEGATOPk: ");
        ret.append("idAlegato=" + idAlegato);
        return ret.toString();
    }

}
