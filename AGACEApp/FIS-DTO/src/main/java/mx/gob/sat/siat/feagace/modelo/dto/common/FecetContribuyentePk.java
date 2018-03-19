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
 * This class represents the primary key of the FECET_CONTRIBUYENTE table.
 */
public class FecetContribuyentePk implements Serializable {
    private BigDecimal idContribuyente;

    /**
     * Sets the value of idContribuyente
     */
    public void setIdContribuyente(final BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    /**
     * Gets the value of idContribuyente
     */
    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    /**
     * Method 'FecetContribuyentePk'
     *
     */
    public FecetContribuyentePk() {
    }

    /**
     * Method 'FecetContribuyentePk'
     *
     * @param idContribuyente
     */
    public FecetContribuyentePk(final BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    /**
     * Method 'equals'
     *
     * @param other
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (!(other instanceof FecetContribuyentePk)) {
            return false;
        }

        final FecetContribuyentePk cast = (FecetContribuyentePk)other;
        if (idContribuyente == null ? cast.idContribuyente != idContribuyente :
            !idContribuyente.equals(cast.idContribuyente)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'hashCode'
     *
     * @return int
     */
    public int hashCode() {
        int hashCode = 0;
        final int posicion = 29;
        if (idContribuyente != null) {
            hashCode = posicion * hashCode + idContribuyente.hashCode();
        }

        return hashCode;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetContribuyentePk: ");
        ret.append("idContribuyente=" + idContribuyente);
        return ret.toString();
    }

}
