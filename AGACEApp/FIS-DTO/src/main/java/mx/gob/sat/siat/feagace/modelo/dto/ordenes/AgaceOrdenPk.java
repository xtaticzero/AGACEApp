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
 * This class represents the primary key of the FECET_ORDEN table.
 */
public class AgaceOrdenPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idOrden;

    /**
     * Method 'AgaceOrdenPk'
     *
     */
    public AgaceOrdenPk() {
    }

    /**
     * Method 'AgaceOrdenPk'
     *
     * @param idOrden
     */
    public AgaceOrdenPk(final BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * Sets the value of idOrden
     */
    public void setIdOrden(final BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    /**
     * Gets the value of idOrden
     */
    public BigDecimal getIdOrden() {
        return idOrden;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.AgaceOrdenPk: ");
        ret.append("idOrden=" + idOrden);
        return ret.toString();
    }

}
