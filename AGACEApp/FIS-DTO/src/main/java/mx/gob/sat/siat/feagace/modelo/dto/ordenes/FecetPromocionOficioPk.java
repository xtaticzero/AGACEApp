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
 * This class represents the primary key of the FECETPROMOCIONOficio table.
 */
public class FecetPromocionOficioPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idPromocionOficio;

    /**
     * Method 'FecetPromocionOficioPk'
     *
     * @param idPromocionOficio
     */
    public FecetPromocionOficioPk(final BigDecimal idPromocionOficio) {
        this.idPromocionOficio = idPromocionOficio;
    }

    /**
     * Sets the value of idPromocion
     */
    public void setIdPromocionOficio(final BigDecimal idPromocionOficio) {
        this.idPromocionOficio = idPromocionOficio;
    }

    /**
     * Gets the value of idPromocion
     */
    public BigDecimal getIdPromocionOficio() {
        return idPromocionOficio;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetPromocionOficioPk: ");
        ret.append("idPromocionOficio=" + idPromocionOficio);
        return ret.toString();
    }

}
