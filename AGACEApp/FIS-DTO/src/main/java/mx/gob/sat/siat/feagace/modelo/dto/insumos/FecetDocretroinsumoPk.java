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
 * This class represents the primary key of the FECET_DOCRETROINSUMO table.
 */
public class FecetDocretroinsumoPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 6755133979687765886L;

    private BigDecimal idDocretroinsumo;

    /**
     * This attribute represents whether the primitive attribute
     * idDocretroinsumo is null.
     */
    private boolean idDocretroinsumoNull;

    /**
     * Sets the value of idDocretroinsumo
     */
    public void setIdDocretroinsumo(BigDecimal idDocretroinsumo) {
        this.idDocretroinsumo = idDocretroinsumo;
    }

    /**
     * Gets the value of idDocretroinsumo
     */
    public BigDecimal getIdDocretroinsumo() {
        return idDocretroinsumo;
    }

    /**
     * Method 'FecetDocretroinsumoPk'
     * 
     */
    public FecetDocretroinsumoPk() {
    }

    /**
     * Method 'FecetDocretroinsumoPk'
     * 
     * @param idDocretroinsumo
     */
    public FecetDocretroinsumoPk(final BigDecimal idDocretroinsumo) {
        this.idDocretroinsumo = idDocretroinsumo;
    }

    /**
     * Sets the value of idDocretroinsumoNull
     */
    public void setIdDocretroinsumoNull(boolean idDocretroinsumoNull) {
        this.idDocretroinsumoNull = idDocretroinsumoNull;
    }

    /**
     * Gets the value of idDocretroinsumoNull
     */
    public boolean isIdDocretroinsumoNull() {
        return idDocretroinsumoNull;
    }

}
