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
 * This class represents the primary key of the FECET_ERROR_NYV table.
 */
public class FecetErrorNyvPk implements Serializable {
    private BigDecimal idErrorNyv;

    /**
     * This attribute represents whether the primitive attribute idErrorNyv is null.
     */
    private boolean idErrorNyvNull;

    /**
     * Sets the value of idErrorNyv
     */
    public void setIdErrorNyv(final BigDecimal idErrorNyv) {
        this.idErrorNyv = idErrorNyv;
    }

    /**
     * Gets the value of idErrorNyv
     */
    public BigDecimal getIdErrorNyv() {
        return idErrorNyv;
    }

    /**
     * Method 'FecetErrorNyvPk'
     *
     */
    public FecetErrorNyvPk() {
    }

    /**
     * Method 'FecetErrorNyvPk'
     *
     * @param idErrorNyv
     */
    public FecetErrorNyvPk(final BigDecimal idErrorNyv) {
        this.idErrorNyv = idErrorNyv;
    }

    /**
     * Sets the value of idErrorNyvNull
     */
    public void setIdErrorNyvNull(boolean idErrorNyvNull) {
        this.idErrorNyvNull = idErrorNyvNull;
    }

    /**
     * Gets the value of idErrorNyvNull
     */
    public boolean isIdErrorNyvNull() {
        return idErrorNyvNull;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetErrorNyvPk: ");
        ret.append("idErrorNyv=" + idErrorNyv);
        return ret.toString();
    }

}
