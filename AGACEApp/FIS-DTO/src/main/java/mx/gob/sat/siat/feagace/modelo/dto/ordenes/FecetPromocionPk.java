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
 * This class represents the primary key of the FECETPROMOCION table.
 */
public class FecetPromocionPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idPromocion;

    /**
     * Method 'FecetPromocionPk'
     *
     * @param idPromocion
     */
    public FecetPromocionPk(final BigDecimal idPromocion) {
        this.idPromocion = idPromocion;
    }

    /**
     * Sets the value of idPromocion
     */
    public void setIdPromocion(final BigDecimal idPromocion) {
        this.idPromocion = idPromocion;
    }

    /**
     * Gets the value of idPromocion
     */
    public BigDecimal getIdPromocion() {
        return idPromocion;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPromocionPk: ");
        ret.append("idPromocion=" + idPromocion);
        return ret.toString();
    }

}
