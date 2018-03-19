/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECECFERIADOS table.
 */
public class FececFeriadosPk implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal idFeriado;

    /**
     * Method 'FececFeriadosPk'
     *
     */
    public FececFeriadosPk() {
    }

    /**
     * Method 'FececFeriadosPk'
     *
     * @param idFeriado
     */
    public FececFeriadosPk(final BigDecimal idFeriado) {
        this.idFeriado = idFeriado;
    }

    /**
     * Sets the value of idFeriado
     */
    public void setIdFeriado(final BigDecimal idFeriado) {
        this.idFeriado = idFeriado;
    }

    /**
     * Gets the value of idFeriado
     */
    public BigDecimal getIdFeriado() {
        return idFeriado;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececFeriadosPk: ");
        ret.append("idFeriado=" + idFeriado);
        return ret.toString();
    }

}
