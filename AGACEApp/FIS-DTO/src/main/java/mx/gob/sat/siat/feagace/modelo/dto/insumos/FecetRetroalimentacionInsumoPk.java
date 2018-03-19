/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECET_RETROALIMENTACION_INSUMO
 * table.
 */
public class FecetRetroalimentacionInsumoPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 3252982854891223195L;

    private BigDecimal idRetroalimentacionInsumo;

    /**
     * This attribute represents whether the primitive attribute
     * idRetroalimentacionInsumo is null.
     */
    private boolean idRetroalimentacionInsumoNull;

    /**
     * Sets the value of idRetroalimentacionInsumo
     */
    public void setIdRetroalimentacionInsumo(
            BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    /**
     * Gets the value of idRetroalimentacionInsumo
     */
    public BigDecimal getIdRetroalimentacionInsumo() {
        return idRetroalimentacionInsumo;
    }

    /**
     * Method 'FecetRetroalimentacionInsumoPk'
     * 
     */
    public FecetRetroalimentacionInsumoPk() {
    }

    /**
     * Method 'FecetRetroalimentacionInsumoPk'
     * 
     * @param idRetroalimentacionInsumo
     */
    public FecetRetroalimentacionInsumoPk(
            final BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    /**
     * Sets the value of idRetroalimentacionInsumoNull
     */
    public void setIdRetroalimentacionInsumoNull(
            boolean idRetroalimentacionInsumoNull) {
        this.idRetroalimentacionInsumoNull = idRetroalimentacionInsumoNull;
    }

    /**
     * Gets the value of idRetroalimentacionInsumoNull
     */
    public boolean isIdRetroalimentacionInsumoNull() {
        return idRetroalimentacionInsumoNull;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumoPk: ");
        ret.append(new StringBuffer("idRetroalimentacionInsumo=").append(idRetroalimentacionInsumo));
        return ret.toString();
    }

}
