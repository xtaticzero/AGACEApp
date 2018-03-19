/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_TIPO_IMPUESTO table.
 */
public class FececTipoImpuestoPk implements Serializable {
    private BigDecimal idTipoImpuesto;

    /**
     * Sets the value of idTipoImpuesto
     */
    public void setIdTipoImpuesto(final BigDecimal idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    /**
     * Gets the value of idTipoImpuesto
     */
    public BigDecimal getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    /**
     * Method 'FececTipoImpuestoPk'
     *
     */
    public FececTipoImpuestoPk() {
    }

    /**
     * Method 'FececTipoImpuestoPk'
     *
     * @param idTipoImpuesto
     */
    public FececTipoImpuestoPk(final BigDecimal idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececTipoImpuestoPk: ");
        ret.append("idTipoImpuesto=").append(idTipoImpuesto);
        return ret.toString();
    }

}
