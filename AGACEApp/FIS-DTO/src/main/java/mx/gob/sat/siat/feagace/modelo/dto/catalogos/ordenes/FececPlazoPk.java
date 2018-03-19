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
 * This class represents the primary key of the FECEC_PLAZO table.
 */
public class FececPlazoPk implements Serializable {
    private BigDecimal idPlazo;

    /**
     * Sets the value of idPlazo
     */
    public void setIdPlazo(BigDecimal idPlazo) {
        this.idPlazo = idPlazo;
    }

    /**
     * Gets the value of idPlazo
     */
    public BigDecimal getIdPlazo() {
        return idPlazo;
    }

    /**
     * Method 'FececPlazoPk'
     *
     */
    public FececPlazoPk() {
    }

    /**
     * Method 'FececPlazoPk'
     *
     * @param idPlazo
     */
    public FececPlazoPk(final BigDecimal idPlazo) {
        this.idPlazo = idPlazo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececPlazoPk: ");
        ret.append("idPlazo=" + idPlazo);
        return ret.toString();
    }

}
