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
 * This class represents the primary key of the FECET_RECHAZO_INSUMO table.
 */
public class FecetRechazoInsumoPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -6469976339460783943L;
    private BigDecimal idRechazoInsumo;

    /**
     * Sets the value of idRechazoInsumo
     */
    public void setIdRechazoInsumo(final BigDecimal idRechazoInsumo) {
        this.idRechazoInsumo = idRechazoInsumo;
    }

    /**
     * Gets the value of idRechazoInsumo
     */
    public BigDecimal getIdRechazoInsumo() {
        return idRechazoInsumo;
    }

    /**
     * Method 'FecetRechazoInsumoPk'
     * 
     */
    public FecetRechazoInsumoPk() {
    }

    /**
     * Method 'FecetRechazoInsumoPk'
     * 
     * @param idRechazoInsumo
     */
    public FecetRechazoInsumoPk(final BigDecimal idRechazoInsumo) {
        this.idRechazoInsumo = idRechazoInsumo;
    }

}
