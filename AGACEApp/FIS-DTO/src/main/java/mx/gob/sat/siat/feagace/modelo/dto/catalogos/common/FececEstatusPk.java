/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_ESTATUS table.
 */
public class FececEstatusPk implements Serializable {
    private BigDecimal idEstatus;

    /**
     * Sets the value of idEstatus
     */
    public void setIdEstatus(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * Gets the value of idEstatus
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'FececEstatusPk'
     *
     */
    public FececEstatusPk() {
    }

    /**
     * Method 'FececEstatusPk'
     *
     * @param idEstatus
     */
    public FececEstatusPk(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }


    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececEstatusPk: ");
        ret.append("idEstatus=").append(idEstatus);
        return ret.toString();
    }

}
