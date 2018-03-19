/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_DOC_TERCERO table.
 */
public class FecetDocTerceroPk implements Serializable {
    private BigDecimal idDocTercero;

    /**
     * Sets the value of idDocTercero
     */
    public void setIdDocTercero(BigDecimal idDocTercero) {
        this.idDocTercero = idDocTercero;
    }

    /**
     * Gets the value of idDocTercero
     */
    public BigDecimal getIdDocTercero() {
        return idDocTercero;
    }

    /**
     * Method 'FecetDocTerceroPk'
     *
     */
    public FecetDocTerceroPk() {
    }

    /**
     * Method 'FecetDocTerceroPk'
     *
     * @param idDocTercero
     */
    public FecetDocTerceroPk(final BigDecimal idDocTercero) {
        this.idDocTercero = idDocTercero;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetDocTerceroPk: ");
        ret.append("idDocTercero=" + idDocTercero);
        return ret.toString();
    }

}
